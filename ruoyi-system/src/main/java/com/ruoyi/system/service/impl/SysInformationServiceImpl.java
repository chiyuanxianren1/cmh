package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysInformationMapper;
import com.ruoyi.common.core.domain.entity.SysInformation;
import com.ruoyi.system.service.ISysInformationService;

/**
 * 行业动态（资讯）管理Service业务层处理
 * 
 * @author wby
 * @date 2024-06-27
 */
@Service
public class SysInformationServiceImpl implements ISysInformationService 
{
    @Autowired
    private SysInformationMapper sysInformationMapper;

    /**
     * 查询行业动态（资讯）管理
     * 
     * @param id 行业动态（资讯）管理主键
     * @return 行业动态（资讯）管理
     */
    @Override
    public SysInformation selectSysInformationById(Long id)
    {
        return sysInformationMapper.selectSysInformationById(id);
    }

    /**
     * 查询行业动态（资讯）管理列表
     * 
     * @param sysInformation 行业动态（资讯）管理
     * @return 行业动态（资讯）管理
     */
    @Override
    public List<SysInformation> selectSysInformationList(SysInformation sysInformation)
    {
        return sysInformationMapper.selectSysInformationList(sysInformation);
    }

    /**
     * 新增行业动态（资讯）管理
     * 
     * @param sysInformation 行业动态（资讯）管理
     * @return 结果
     */
    @Override
    public int insertSysInformation(SysInformation sysInformation)
    {
        return sysInformationMapper.insertSysInformation(sysInformation);
    }

    /**
     * 修改行业动态（资讯）管理
     * 
     * @param sysInformation 行业动态（资讯）管理
     * @return 结果
     */
    @Override
    public int updateSysInformation(SysInformation sysInformation)
    {
        return sysInformationMapper.updateSysInformation(sysInformation);
    }

    /**
     * 批量删除行业动态（资讯）管理
     * 
     * @param ids 需要删除的行业动态（资讯）管理主键
     * @return 结果
     */
    @Override
    public int deleteSysInformationByIds(Long[] ids)
    {
        return sysInformationMapper.deleteSysInformationByIds(ids);
    }

    /**
     * 删除行业动态（资讯）管理信息
     * 
     * @param id 行业动态（资讯）管理主键
     * @return 结果
     */
    @Override
    public int deleteSysInformationById(Long id)
    {
        return sysInformationMapper.deleteSysInformationById(id);
    }
}
