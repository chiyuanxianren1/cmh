package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.common.core.domain.entity.SysConference;

/**
 * 会议管理Service接口
 * 
 * @author wby
 * @date 2024-06-28
 */
public interface ISysConferenceService 
{
    /**
     * 查询会议管理
     * 
     * @param id 会议管理主键
     * @return 会议管理
     */
    public SysConference selectSysConferenceById(Long id);

    /**
     * 查询会议管理列表
     * 
     * @param sysConference 会议管理
     * @return 会议管理集合
     */
    public List<SysConference> selectSysConferenceList(SysConference sysConference);

    /**
     * 新增会议管理
     * 
     * @param sysConference 会议管理
     * @return 结果
     */
    public int insertSysConference(SysConference sysConference);

    /**
     * 修改会议管理
     * 
     * @param sysConference 会议管理
     * @return 结果
     */
    public int updateSysConference(SysConference sysConference);

    /**
     * 批量删除会议管理
     * 
     * @param ids 需要删除的会议管理主键集合
     * @return 结果
     */
    public int deleteSysConferenceByIds(Long[] ids);

    /**
     * 删除会议管理信息
     * 
     * @param id 会议管理主键
     * @return 结果
     */
    public int deleteSysConferenceById(Long id);
}