package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.domain.SysUserTenement;
import com.ruoyi.system.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.domain.entity.SysTenement;
import com.ruoyi.system.service.ISysTenementService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 租户管理Service业务层处理
 * 
 * @author wby
 * @date 2024-06-20
 */
@Service
public class SysTenementServiceImpl implements ISysTenementService 
{
    @Autowired
    private SysTenementMapper sysTenementMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 查询租户管理
     * 
     * @param id 租户管理主键
     * @return 租户管理
     */
    @Override
    public SysTenement selectSysTenementById(Long id)
    {
        return sysTenementMapper.selectSysTenementById(id);
    }

    /**
     * 查询租户管理列表
     * 
     * @param sysTenement 租户管理
     * @return 租户管理
     */
    @Override
    public List<SysTenement> selectSysTenementList(SysTenement sysTenement)
    {
        return sysTenementMapper.selectSysTenementList(sysTenement);
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        boolean b=bCryptPasswordEncoder.matches("2F93D21A3280489896957772374CFF4B",
                "$2a$10$S6qaDqJxMZ34340T/wuaP.yLhMzXsIualDqRsAIZgqrC8KFjt13se");
        System.out.println(b);
    }

    /**
     * 新增租户管理
     * 
     * @param sysTenement 租户管理
     * @return 结果
     */
    @Override
    @Transactional
    public int insertSysTenement(SysTenement sysTenement)
    {
        String rawPassword = UUID.MyRandomUUID();
        //8232939475BE4A409ADCA73691360D3D
        //2F93D21A3280489896957772374CFF4B
        sysTenement.setIdentification(rawPassword);
        int result=sysTenementMapper.insertSysTenement(sysTenement);
        //创建租户对应的企业
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
        sysDeptMapper.insertDept(sysDept);
        //创建企业管理员
        SysUser sysUser=new SysUser();
        long deptId=sysDeptMapper.selectDeptIdByName(sysTenement.getName());
        sysUser.setDelFlag("0");
        sysUser.setDeptId(deptId);
        sysUser.setDept(sysDept);
        sysUser.setPhonenumber(sysTenement.getPhone());
        sysUser.setNickName(sysTenement.getAdmin());
        sysUser.setUserName(sysTenement.getAdmin());
        BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();
        //默认密码123456
        sysUser.setPassword(bCryptPasswordEncoder.encode("123456"));
        sysUser.setStatus("0");
        sysUser.setRemark(sysTenement.getName()+"管理员");
        sysUser.setCreateBy("admin");
        sysUser.setCreateTime(date);
        sysUserMapper.insertUser(sysUser);
        //添加关联
        Long userId=sysUserMapper.selectUserIdByName(sysUser.getUserName());
        Long tId = sysTenementMapper.selectIdByName(sysTenement.getName());
        SysUserTenement sysUserTenement=new SysUserTenement(userId,tId);
        SysUserRole sysUserRole=new SysUserRole(userId,100L);
        List<SysUserRole> list = new ArrayList<>();
        list.add(sysUserRole);
        sysUserRoleMapper.batchUserRole(list);
        sysTenementMapper.insertUserIdAndTenementId(sysUserTenement);
        return result;
    }

    /**
     * 修改租户管理
     * 
     * @param sysTenement 租户管理
     * @return 结果
     */
    @Override
    public int updateSysTenement(SysTenement sysTenement)
    {
        return sysTenementMapper.updateSysTenement(sysTenement);
    }

    /**
     * 批量删除租户管理
     * 
     * @param ids 需要删除的租户管理主键
     * @return 结果
     */
    @Override
    public int deleteSysTenementByIds(Long[] ids)
    {
        return sysTenementMapper.deleteSysTenementByIds(ids);
    }

    /**
     * 删除租户管理信息
     * 
     * @param id 租户管理主键
     * @return 结果
     */
    @Override
    public int deleteSysTenementById(Long id)
    {
        return sysTenementMapper.deleteSysTenementById(id);
    }

    @Override
    public Long selectIdByName(String name) {
        return sysTenementMapper.selectIdByName(name);
    }

    @Override
    public void insertUserIdAndTenementId(SysUserTenement sysUserTenement) {
        sysTenementMapper.insertUserIdAndTenementId(sysUserTenement);
    }
}
