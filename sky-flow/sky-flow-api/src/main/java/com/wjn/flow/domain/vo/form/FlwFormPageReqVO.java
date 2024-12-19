package com.wjn.flow.domain.vo.form;

import com.wjn.common.core.page.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 动态表单分页 Request VO")
@Data
public class FlwFormPageReqVO extends PageParam {

    @Schema(description = "表单名称", example = "芋道")
    private String name;

}
