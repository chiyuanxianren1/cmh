package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.domain.entity.SysConference;
import com.ruoyi.system.service.ISysConferenceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 会议管理Controller
 * 
 * @author wby
 * @date 2024-06-28
 */
@RestController
@RequestMapping("/system/conference")
public class SysConferenceController extends BaseController
{
    @Autowired
    private ISysConferenceService sysConferenceService;

    /**
     * 查询会议管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:conference:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysConference sysConference)
    {
        startPage();
        List<SysConference> list = sysConferenceService.selectSysConferenceList(sysConference);
        return getDataTable(list);
    }

    /**
     * 导出会议管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:conference:export')")
    @Log(title = "会议管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysConference sysConference)
    {
        List<SysConference> list = sysConferenceService.selectSysConferenceList(sysConference);
        ExcelUtil<SysConference> util = new ExcelUtil<SysConference>(SysConference.class);
        util.exportExcel(response, list, "会议管理数据");
    }

    /**
     * 获取会议管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:conference:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysConferenceService.selectSysConferenceById(id));
    }

    /**
     * 新增会议管理
     */
    @PreAuthorize("@ss.hasPermi('system:conference:add')")
    @Log(title = "会议管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysConference sysConference)
    {
        return toAjax(sysConferenceService.insertSysConference(sysConference));
    }

    /**
     * 修改会议管理
     */
    @PreAuthorize("@ss.hasPermi('system:conference:edit')")
    @Log(title = "会议管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysConference sysConference)
    {
        return toAjax(sysConferenceService.updateSysConference(sysConference));
    }

    /**
     * 删除会议管理
     */
    @PreAuthorize("@ss.hasPermi('system:conference:remove')")
    @Log(title = "会议管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysConferenceService.deleteSysConferenceByIds(ids));
    }
}
