package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.common.core.domain.entity.SysTenement;
import com.ruoyi.system.domain.SysUserTenement;

/**
 * 租户管理Mapper接口
 * 
 * @author wby
 * @date 2024-06-20
 */
public interface SysTenementMapper 
{
    /**
     * 查询租户管理
     * 
     * @param id 租户管理主键
     * @return 租户管理
     */
    public SysTenement selectSysTenementById(Long id);

    /**
     * 查询租户管理列表
     * 
     * @param sysTenement 租户管理
     * @return 租户管理集合
     */
    public List<SysTenement> selectSysTenementList(SysTenement sysTenement);

    /**
     * 新增租户管理
     * 
     * @param sysTenement 租户管理
     * @return 结果
     */
    public int insertSysTenement(SysTenement sysTenement);

    /**
     * 修改租户管理
     * 
     * @param sysTenement 租户管理
     * @return 结果
     */
    public int updateSysTenement(SysTenement sysTenement);

    /**
     * 删除租户管理
     * 
     * @param id 租户管理主键
     * @return 结果
     */
    public int deleteSysTenementById(Long id);

    /**
     * 批量删除租户管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysTenementByIds(Long[] ids);

    public Long selectIdByName(String name);

    void insertUserIdAndTenementId(SysUserTenement sysUserTenement);
}
