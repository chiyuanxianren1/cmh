package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysConferenceMapper;
import com.ruoyi.common.core.domain.entity.SysConference;
import com.ruoyi.system.service.ISysConferenceService;

/**
 * 会议管理Service业务层处理
 * 
 * @author wby
 * @date 2024-06-28
 */
@Service
public class SysConferenceServiceImpl implements ISysConferenceService 
{
    @Autowired
    private SysConferenceMapper sysConferenceMapper;

    /**
     * 查询会议管理
     * 
     * @param id 会议管理主键
     * @return 会议管理
     */
    @Override
    public SysConference selectSysConferenceById(Long id)
    {
        return sysConferenceMapper.selectSysConferenceById(id);
    }

    /**
     * 查询会议管理列表
     * 
     * @param sysConference 会议管理
     * @return 会议管理
     */
    @Override
    public List<SysConference> selectSysConferenceList(SysConference sysConference)
    {
        return sysConferenceMapper.selectSysConferenceList(sysConference);
    }

    /**
     * 新增会议管理
     * 
     * @param sysConference 会议管理
     * @return 结果
     */
    @Override
    public int insertSysConference(SysConference sysConference)
    {
        sysConference.setStatus(0L);
        return sysConferenceMapper.insertSysConference(sysConference);
    }

    /**
     * 修改会议管理
     * 
     * @param sysConference 会议管理
     * @return 结果
     */
    @Override
    public int updateSysConference(SysConference sysConference)
    {
        return sysConferenceMapper.updateSysConference(sysConference);
    }

    /**
     * 批量删除会议管理
     * 
     * @param ids 需要删除的会议管理主键
     * @return 结果
     */
    @Override
    public int deleteSysConferenceByIds(Long[] ids)
    {
        return sysConferenceMapper.deleteSysConferenceByIds(ids);
    }

    /**
     * 删除会议管理信息
     * 
     * @param id 会议管理主键
     * @return 结果
     */
    @Override
    public int deleteSysConferenceById(Long id)
    {
        return sysConferenceMapper.deleteSysConferenceById(id);
    }
}
