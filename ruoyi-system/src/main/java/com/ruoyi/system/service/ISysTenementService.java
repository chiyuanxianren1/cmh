package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.common.core.domain.entity.SysTenement;
import com.ruoyi.system.domain.SysUserTenement;

/**
 * 租户管理Service接口
 * 
 * @author wby
 * @date 2024-06-20
 */
public interface ISysTenementService 
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
     * 批量删除租户管理
     * 
     * @param ids 需要删除的租户管理主键集合
     * @return 结果
     */
    public int deleteSysTenementByIds(Long[] ids);

    /**
     * 删除租户管理信息
     * 
     * @param id 租户管理主键
     * @return 结果
     */
    public int deleteSysTenementById(Long id);

    public Long selectIdByName(String name);

    public void insertUserIdAndTenementId(SysUserTenement sysUserTenement);
}
