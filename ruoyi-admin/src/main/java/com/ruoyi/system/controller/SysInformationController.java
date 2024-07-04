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
import com.ruoyi.common.core.domain.entity.SysInformation;
import com.ruoyi.system.service.ISysInformationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 行业动态（资讯）管理Controller
 * 
 * @author wby
 * @date 2024-06-27
 */
@RestController
@RequestMapping("/system/information")
public class SysInformationController extends BaseController
{
    @Autowired
    private ISysInformationService sysInformationService;

    /**
     * 查询行业动态（资讯）管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:information:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysInformation sysInformation)
    {
        startPage();
        List<SysInformation> list = sysInformationService.selectSysInformationList(sysInformation);
        return getDataTable(list);
    }

    /**
     * 导出行业动态（资讯）管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:information:export')")
    @Log(title = "行业动态（资讯）管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysInformation sysInformation)
    {
        List<SysInformation> list = sysInformationService.selectSysInformationList(sysInformation);
        ExcelUtil<SysInformation> util = new ExcelUtil<SysInformation>(SysInformation.class);
        util.exportExcel(response, list, "行业动态（资讯）管理数据");
    }

    /**
     * 获取行业动态（资讯）管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:information:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysInformationService.selectSysInformationById(id));
    }

    /**
     * 新增行业动态（资讯）管理
     */
    @PreAuthorize("@ss.hasPermi('system:information:add')")
    @Log(title = "行业动态（资讯）管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysInformation sysInformation)
    {
        return toAjax(sysInformationService.insertSysInformation(sysInformation));
    }

    /**
     * 修改行业动态（资讯）管理
     */
    @PreAuthorize("@ss.hasPermi('system:information:edit')")
    @Log(title = "行业动态（资讯）管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysInformation sysInformation)
    {
        return toAjax(sysInformationService.updateSysInformation(sysInformation));
    }

    /**
     * 删除行业动态（资讯）管理
     */
    @PreAuthorize("@ss.hasPermi('system:information:remove')")
    @Log(title = "行业动态（资讯）管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysInformationService.deleteSysInformationByIds(ids));
    }
}
