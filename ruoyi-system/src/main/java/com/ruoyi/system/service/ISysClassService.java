package com.ruoyi.system.service;


import com.ruoyi.common.core.domain.entity.SysClass;

import java.util.List;

/**
 * 课程管理Service接口
 * 
 * @author wby
 * @date 2024-07-02
 */
public interface ISysClassService 
{
    /**
     * 查询课程管理
     * 
     * @param id 课程管理主键
     * @return 课程管理
     */
    public SysClass selectSysClassById(Long id);

    /**
     * 查询课程管理列表
     * 
     * @param sysClass 课程管理
     * @return 课程管理集合
     */
    public List<SysClass> selectSysClassList(SysClass sysClass);

    /**
     * 新增课程管理
     * 
     * @param sysClass 课程管理
     * @return 结果
     */
    public int insertSysClass(SysClass sysClass);

    /**
     * 修改课程管理
     * 
     * @param sysClass 课程管理
     * @return 结果
     */
    public int updateSysClass(SysClass sysClass);

    /**
     * 批量删除课程管理
     * 
     * @param ids 需要删除的课程管理主键集合
     * @return 结果
     */
    public int deleteSysClassByIds(Long[] ids);

    /**
     * 删除课程管理信息
     * 
     * @param id 课程管理主键
     * @return 结果
     */
    public int deleteSysClassById(Long id);
}
