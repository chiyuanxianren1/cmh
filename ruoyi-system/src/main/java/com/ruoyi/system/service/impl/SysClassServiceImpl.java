package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.SysClass;
import com.ruoyi.system.mapper.SysClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ruoyi.system.service.ISysClassService;

import java.util.List;

/**
 * 课程管理Service业务层处理
 * 
 * @author wby
 * @date 2024-07-02
 */
@Service
public class SysClassServiceImpl implements ISysClassService 
{
    @Autowired
    private SysClassMapper sysClassMapper;

    /**
     * 查询课程管理
     * 
     * @param id 课程管理主键
     * @return 课程管理
     */
    @Override
    public SysClass selectSysClassById(Long id)
    {
        return sysClassMapper.selectSysClassById(id);
    }

    /**
     * 查询课程管理列表
     * 
     * @param sysClass 课程管理
     * @return 课程管理
     */
    @Override
    public List<SysClass> selectSysClassList(SysClass sysClass)
    {
        return sysClassMapper.selectSysClassList(sysClass);
    }

    /**
     * 新增课程管理
     * 
     * @param sysClass 课程管理
     * @return 结果
     */
    @Override
    public int insertSysClass(SysClass sysClass)
    {
        return sysClassMapper.insertSysClass(sysClass);
    }

    /**
     * 修改课程管理
     * 
     * @param sysClass 课程管理
     * @return 结果
     */
    @Override
    public int updateSysClass(SysClass sysClass)
    {
        return sysClassMapper.updateSysClass(sysClass);
    }

    /**
     * 批量删除课程管理
     * 
     * @param ids 需要删除的课程管理主键
     * @return 结果
     */
    @Override
    public int deleteSysClassByIds(Long[] ids)
    {
        return sysClassMapper.deleteSysClassByIds(ids);
    }

    /**
     * 删除课程管理信息
     * 
     * @param id 课程管理主键
     * @return 结果
     */
    @Override
    public int deleteSysClassById(Long id)
    {
        return sysClassMapper.deleteSysClassById(id);
    }
}
