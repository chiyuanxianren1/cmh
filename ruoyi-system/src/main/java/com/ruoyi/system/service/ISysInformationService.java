package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.common.core.domain.entity.SysInformation;

/**
 * 行业动态（资讯）管理Service接口
 * 
 * @author wby
 * @date 2024-06-27
 */
public interface ISysInformationService 
{
    /**
     * 查询行业动态（资讯）管理
     * 
     * @param id 行业动态（资讯）管理主键
     * @return 行业动态（资讯）管理
     */
    public SysInformation selectSysInformationById(Long id);

    /**
     * 查询行业动态（资讯）管理列表
     * 
     * @param sysInformation 行业动态（资讯）管理
     * @return 行业动态（资讯）管理集合
     */
    public List<SysInformation> selectSysInformationList(SysInformation sysInformation);

    /**
     * 新增行业动态（资讯）管理
     * 
     * @param sysInformation 行业动态（资讯）管理
     * @return 结果
     */
    public int insertSysInformation(SysInformation sysInformation);

    /**
     * 修改行业动态（资讯）管理
     * 
     * @param sysInformation 行业动态（资讯）管理
     * @return 结果
     */
    public int updateSysInformation(SysInformation sysInformation);

    /**
     * 批量删除行业动态（资讯）管理
     * 
     * @param ids 需要删除的行业动态（资讯）管理主键集合
     * @return 结果
     */
    public int deleteSysInformationByIds(Long[] ids);

    /**
     * 删除行业动态（资讯）管理信息
     * 
     * @param id 行业动态（资讯）管理主键
     * @return 结果
     */
    public int deleteSysInformationById(Long id);
}
