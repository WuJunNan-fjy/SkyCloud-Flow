package com.wjn.flow.controller;

import com.wjn.common.annotation.CommFields;
import com.wjn.common.core.controller.BaseController;
import com.wjn.common.core.domain.AjaxResult;
import com.wjn.common.core.page.TableDataInfo;
import com.wjn.flow.domain.dto.definition.FlwCategoryDO;
import com.wjn.flow.domain.vo.category.FlwCategoryPageReqVO;
import com.wjn.flow.domain.vo.category.FlwCategorySaveReqVO;
import com.wjn.flow.service.FlwCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;


/**
 * @description: 流程分类控制器
 * @author: wjn
 * @create: 2024-12-06 18:35
 * @version:v1.0
 */
@RestController
@RequestMapping("/flow/category")
public class FlwCategoryController extends BaseController {

    @Resource
    private FlwCategoryService categoryService;

    @CommFields
    @PostMapping("/create")
    @Operation(summary = "创建流程分类")
    @PreAuthorize("@ss.hasPermission('flow:category:create')")
    public AjaxResult createCategory(@Valid @RequestBody FlwCategorySaveReqVO createReqVO) {
        return toAjax(categoryService.createCategory(createReqVO));
    }

    @CommFields
    @PutMapping("/update")
    @Operation(summary = "更新流程分类")
    @PreAuthorize("@ss.hasPermission('flow:category:update')")
    public AjaxResult updateCategory(@Valid @RequestBody FlwCategorySaveReqVO updateReqVO) {
        return toAjax(categoryService.updateCategory(updateReqVO));
    }

    @DeleteMapping("/delete/{idStr}")
    @Operation(summary = "删除流程分类")
    @Parameter(name = "idStr", description = "id", required = true)
    @PreAuthorize("@ss.hasPermission('flow:category:delete')")
    public AjaxResult deleteCategory(@PathVariable("idStr") String idStr) {
        return toAjax(categoryService.deleteCategory(idStr));
    }

    @GetMapping("/getById/{id}")
    @Operation(summary = "获得流程分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('flow:category:query')")
    public AjaxResult getCategory(@PathVariable("id") Long id) {
        return success(categoryService.getCategory(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得流程分类分页")
    @PreAuthorize("@ss.hasPermission('flow:category:page')")
    public TableDataInfo getCategoryPage(@Valid FlwCategoryPageReqVO pageReqVO) {
        startPage();
        List<FlwCategoryDO> pageResult = categoryService.getCategoryPage(pageReqVO);
        return getDataTable(pageResult);
    }
}
