package com.wjn.ledger.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wjn.common.annotation.Log;
import com.wjn.common.core.controller.BaseController;
import com.wjn.common.core.domain.AjaxResult;
import com.wjn.common.enums.BusinessType;
import com.wjn.ledger.domain.TLedCategory;
import com.wjn.ledger.service.ITLedCategoryService;
import com.wjn.common.utils.poi.ExcelUtil;
import com.wjn.common.core.page.TableDataInfo;

/**
 * 记账分类Controller
 *
 * @author wjn
 * @date 2025-04-17
 */
@RestController
@RequestMapping("/ledger/category")
public class TLedCategoryController extends BaseController
{
    @Resource
    private ITLedCategoryService tLedCategoryService;

    /**
     * 查询记账分类列表
     */
    // @PreAuthorize("@ss.hasPermission('ledger:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(TLedCategory tLedCategory)
    {
        startPage();
        List<TLedCategory> list = tLedCategoryService.selectTLedCategoryList(tLedCategory);
        return getDataTable(list);
    }

    /**
     * 导出记账分类列表
     */
    // @PreAuthorize("@ss.hasPermission('ledger:category:export')")
    @Log(title = "记账分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TLedCategory tLedCategory)
    {
        List<TLedCategory> list = tLedCategoryService.selectTLedCategoryList(tLedCategory);
        ExcelUtil<TLedCategory> util = new ExcelUtil<TLedCategory>(TLedCategory.class);
        util.exportExcel(response, list, "记账分类数据");
    }

    /**
     * 获取记账分类详细信息
     */
    // @PreAuthorize("@ss.hasPermission('ledger:category:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId)
    {
        return success(tLedCategoryService.selectTLedCategoryByCategoryId(categoryId));
    }

    /**
     * 新增记账分类
     */
    // @PreAuthorize("@ss.hasPermission('ledger:category:add')")
    @Log(title = "记账分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TLedCategory tLedCategory)
    {
        return toAjax(tLedCategoryService.insertTLedCategory(tLedCategory));
    }

    /**
     * 修改记账分类
     */
    // @PreAuthorize("@ss.hasPermission('ledger:category:edit')")
    @Log(title = "记账分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TLedCategory tLedCategory)
    {
        return toAjax(tLedCategoryService.updateTLedCategory(tLedCategory));
    }

    /**
     * 删除记账分类
     */
    // @PreAuthorize("@ss.hasPermission('ledger:category:remove')")
    @Log(title = "记账分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds)
    {
        return toAjax(tLedCategoryService.deleteTLedCategoryByCategoryIds(categoryIds));
    }
}
