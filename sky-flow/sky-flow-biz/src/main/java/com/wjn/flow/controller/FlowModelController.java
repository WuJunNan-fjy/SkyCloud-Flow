package com.wjn.flow.controller;

import com.wjn.common.core.controller.BaseController;
import com.wjn.common.core.domain.AjaxResult;
import com.wjn.flow.domin.vo.model.BpmModelSaveReqVO;
import com.wjn.flow.service.FlowModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
public class FlowModelController  extends BaseController {

    @Resource
    private FlowModelService modelService;


    @PostMapping("/create")
    @Operation(summary = "新建模型")
    @PreAuthorize("@ss.hasPermission('flow:model:create')")
    public AjaxResult createModel(@Valid @RequestBody BpmModelSaveReqVO createRetVO) {
        return success(modelService.createModel(createRetVO));
    }
}
