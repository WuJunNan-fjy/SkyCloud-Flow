package com.wjn.flow.controller;

import com.wjn.common.annotation.CommFields;
import com.wjn.common.core.controller.BaseController;
import com.wjn.common.core.domain.AjaxResult;
import com.wjn.common.core.page.TableDataInfo;
import com.wjn.flow.domain.dto.flow.FlwFormDO;
import com.wjn.flow.domain.vo.form.FlwFormPageReqVO;
import com.wjn.flow.domain.vo.form.FlwFormSaveReqVO;
import com.wjn.flow.service.FlwFormService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


/**
 * @description: 流程表单控制器
 * @author: wjn
 * @create: 2024-12-06 18:35
 * @version:v1.0
 */
@RestController
@RequestMapping("/flow/form")
public class FlwFormController extends BaseController {

    @Resource
    private FlwFormService formService;

    @CommFields
    @PostMapping("/create")
    @Operation(summary = "创建流程表单")
    @PreAuthorize("@ss.hasPermission('flow:form:create')")
    public AjaxResult createForm(@Valid @RequestBody FlwFormSaveReqVO createReqVO) {
        return toAjax(formService.createForm(createReqVO));
    }

    @CommFields
    @PutMapping("/update")
    @Operation(summary = "更新流程表单")
    @PreAuthorize("@ss.hasPermission('flow:form:update')")
    public AjaxResult updateForm(@Valid @RequestBody FlwFormSaveReqVO updateReqVO) {
        return toAjax(formService.updateForm(updateReqVO));
    }

    @DeleteMapping("/delete/{idStr}")
    @Operation(summary = "删除流程表单")
    @Parameter(name = "idStr", description = "id", required = true)
    @PreAuthorize("@ss.hasPermission('flow:form:delete')")
    public AjaxResult deleteForm(@PathVariable("idStr") String idStr) {
        return toAjax(formService.deleteForm(idStr));
    }

    @GetMapping("/getById/{id}")
    @Operation(summary = "获得流程表单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('flow:form:query')")
    public AjaxResult getForm(@PathVariable("id") Long id) {
        return success(formService.getForm(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得流程表单分页")
    @PreAuthorize("@ss.hasPermission('flow:form:page')")
    public TableDataInfo getFormPage(@Valid FlwFormPageReqVO pageReqVO) {
        startPage();
        List<FlwFormDO> pageResult = formService.getFormPage(pageReqVO);
        return getDataTable(pageResult);
    }
}
