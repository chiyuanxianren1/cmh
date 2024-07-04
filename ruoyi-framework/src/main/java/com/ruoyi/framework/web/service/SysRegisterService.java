package com.ruoyi.framework.web.service;

import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysTenement;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.domain.SysUserTenement;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysTenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.RegisterBody;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.CaptchaExpireException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 注册校验方法
 * 
 * @author ruoyi
 */
@Component
public class SysRegisterService
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysTenementService sysTenementService;

    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    /**
     * 注册
     */
    @Transactional
    public String register(RegisterBody registerBody)
    {
        String msg = "", username = registerBody.getUsername(), password = registerBody.getPassword();
        //创建租户
        SysTenement sysTenement=new SysTenement();
        sysTenement.setIdentification(UUID.MyRandomUUID());
        sysTenement.setName(registerBody.getEnterpriseName());
        sysTenement.setPhone(registerBody.getLink());
        sysTenement.setLinkMan(username);
        sysTenement.setAdmin(username);
        sysTenementService.insertSysTenement(sysTenement);
        //创建企业
        SysDept sysDept=new SysDept();
        sysDept.setAncestors("0,100");
        sysDept.setParentId(100L);
        sysDept.setDeptName(sysTenement.getName());
        sysDept.setOrderNum(0);
        sysDept.setLeader(sysTenement.getLinkMan());
        Date date = new Date();
        sysDept.setCreateTime(date);
        sysDept.setDelFlag("0");
        sysDept.setPhone(sysTenement.getPhone());
        sysDept.setStatus("0");
        sysDept.setCreateBy("admin");
        sysDeptService.insertDept(sysDept);
        //创建用户
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);
        Long deptId=sysDeptService.selectDeptIdByName(sysTenement.getName());
        sysUser.setDelFlag("0");
        sysUser.setDeptId(deptId);
        sysUser.setPhonenumber(sysTenement.getPhone());
        sysUser.setStatus("0");
        sysUser.setRemark(sysTenement.getName()+"管理员");
        sysUser.setCreateBy("admin");
        sysUser.setCreateTime(date);


        // 验证码开关
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        if (captchaEnabled)
        {
            validateCaptcha(username, registerBody.getCode(), registerBody.getUuid());
        }

        if (StringUtils.isEmpty(username))
        {
            msg = "用户名不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        }
        else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
        }
        else if (!userService.checkUserNameUnique(sysUser))
        {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        }
        else
        {
            sysUser.setNickName(username);
            sysUser.setPassword(SecurityUtils.encryptPassword(password));
            boolean regFlag = userService.registerUser(sysUser);
            //添加关联
            Long userId=userService.selectUserIdByName(sysUser.getUserName());
            Long tId = sysTenementService.selectIdByName(sysTenement.getName());
            SysUserTenement sysUserTenement=new SysUserTenement(userId,tId);
            SysUserRole sysUserRole=new SysUserRole(userId,100L);
            List<SysUserRole> list = new ArrayList<>();
            list.add(sysUserRole);
            sysUserRoleMapper.batchUserRole(list);
            sysTenementService.insertUserIdAndTenementId(sysUserTenement);
            if (!regFlag)
            {
                msg = "注册失败,请联系系统管理人员";
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER, MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }

    /**
     * 校验验证码
     * 
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            throw new CaptchaException();
        }
    }
}
