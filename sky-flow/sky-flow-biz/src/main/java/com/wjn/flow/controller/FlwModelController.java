package com.wjn.flow.controller;

import com.wjn.common.core.controller.BaseController;
import com.wjn.common.core.domain.AjaxResult;
import com.wjn.common.core.page.TableDataInfo;
import com.wjn.flow.domin.vo.model.BpmModelPageReqVO;
import com.wjn.flow.domin.vo.model.BpmModelSaveReqVO;
import com.wjn.flow.service.FlwModelService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @description: 流程模型控制器
 * @author: wjn
 * @create: 2024-12-04 15:06
 * @version:v1.0
 */
@RestController
@RequestMapping("/flow/model")
public class FlwModelController extends BaseController {

    @Resource
    private FlwModelService modelService;

    @GetMapping("/list")
    @Operation(summary = "获取流程模型列表")
    @PreAuthorize("@ss.hasPermi('flow:model:list')")
    public TableDataInfo list(@Valid BpmModelPageReqVO pageReqVO) {
        return modelService.list(pageReqVO);
    }

    @PostMapping("/create")
    @Operation(summary = "新建模型")
    @PreAuthorize("@ss.hasPermi('flow:model:create')")
    public AjaxResult createModel(@Valid @RequestBody BpmModelSaveReqVO createRetVO) {
        return AjaxResult.success(modelService.createModel(createRetVO));
    }
}
