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
import com.ruoyi.common.core.domain.entity.SysTenement;
import com.ruoyi.system.service.ISysTenementService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 租户管理Controller
 * 
 * @author wby
 * @date 2024-06-20
 */
@RestController
@RequestMapping("/system/tenement")
public class SysTenementController extends BaseController
{
    @Autowired
    private ISysTenementService sysTenementService;

    /**
     * 查询租户管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:tenement:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysTenement sysTenement)
    {
        startPage();
        List<SysTenement> list = sysTenementService.selectSysTenementList(sysTenement);
        return getDataTable(list);
    }

    /**
     * 导出租户管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:tenement:export')")
    @Log(title = "租户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysTenement sysTenement)
    {
        List<SysTenement> list = sysTenementService.selectSysTenementList(sysTenement);
        ExcelUtil<SysTenement> util = new ExcelUtil<SysTenement>(SysTenement.class);
        util.exportExcel(response, list, "租户管理数据");
    }

    /**
     * 获取租户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:tenement:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysTenementService.selectSysTenementById(id));
    }

    /**
     * 新增租户管理
     */
    @PreAuthorize("@ss.hasPermi('system:tenement:add')")
    @Log(title = "租户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysTenement sysTenement)
    {
        return toAjax(sysTenementService.insertSysTenement(sysTenement));
    }

    /**
     * 修改租户管理
     */
    @PreAuthorize("@ss.hasPermi('system:tenement:edit')")
    @Log(title = "租户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysTenement sysTenement)
    {
        return toAjax(sysTenementService.updateSysTenement(sysTenement));
    }

    /**
     * 删除租户管理
     */
    @PreAuthorize("@ss.hasPermi('system:tenement:remove')")
    @Log(title = "租户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysTenementService.deleteSysTenementByIds(ids));
    }
}
