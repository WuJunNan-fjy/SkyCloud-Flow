package com.wjn.flow.controller;

import com.wjn.common.annotation.CommFields;
import com.wjn.common.core.controller.BaseController;
import com.wjn.common.core.domain.AjaxResult;
import com.wjn.common.core.page.TableDataInfo;
import com.wjn.common.enums.CommonStatusEnum;
import com.wjn.common.utils.bean.BeanUtils;
import com.wjn.flow.domin.vo.category.FlwCategoryPageReqVO;
import com.wjn.flow.domin.vo.category.FlwCategoryRespVO;
import com.wjn.flow.domin.vo.category.FlwCategorySaveReqVO;
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
    @PreAuthorize("@ss.hasPermi('flow:category:create')")
    public AjaxResult createCategory(@Valid @RequestBody FlwCategorySaveReqVO createReqVO) {
        return toAjax(categoryService.createCategory(createReqVO));
    }

    @CommFields
    @PutMapping("/update")
    @Operation(summary = "更新流程分类")
    @PreAuthorize("@ss.hasPermi('flow:category:update')")
    public AjaxResult updateCategory(@Valid @RequestBody FlwCategorySaveReqVO updateReqVO) {
        return toAjax(categoryService.updateCategory(updateReqVO));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除流程分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermi('flow:category:delete')")
    public AjaxResult deleteCategory(@RequestParam("id") Long id) {
        return toAjax(categoryService.deleteCategory(id));
    }

    @GetMapping("/get")
    @Operation(summary = "获得流程分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermi('flow:category:query')")
    public AjaxResult getCategory(@RequestParam("id") Long id) {
        return success(categoryService.getCategory(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得流程分类分页")
    @PreAuthorize("@ss.hasPermi('flow:category:query')")
    public TableDataInfo getCategoryPage(@Valid FlwCategoryPageReqVO pageReqVO) {
        List<FlwCategoryRespVO> pageResult = categoryService.getCategoryPage(pageReqVO);
        return getDataTable(pageResult);
    }

    @GetMapping("/simple-list")
    @Operation(summary = "获取流程分类的精简信息列表", description = "只包含被开启的分类，主要用于前端的下拉选项")
    public List<FlwCategoryRespVO> getCategorySimpleList() {
        // List<BpmCategoryDO> list = categoryService.getCategoryListByStatus(CommonStatusEnum.ENABLE.getStatus());
        // list.sort(Comparator.comparingInt(BpmCategoryDO::getSort));
        // return success(convertList(list, category -> new BpmCategoryRespVO().setId(category.getId())
        //         .setName(category.getName()).setCode(category.getCode())));
        return null;
    }
}
