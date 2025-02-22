package com.wjn.flow.domain.vo.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wjn.common.handler.BooleanToIntegerDeserializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "管理后台 - 动态表单创建/更新 Request VO")
@Data
public class FlwFormSaveReqVO {

    @Schema(description = "表单主键")
    private Long id;

    @Schema(description = "表单名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "wjn")
    @NotNull(message = "表单名称不能为空")
    private String name;

    @Schema(description = "表单的配置-JSON 字符串", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "表单的配置不能为空")
    private String conf;

    @Schema(description = "表单项的数组-JSON 字符串的数组", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "表单项的数组不能为空")
    private List<String> fieldList;

    @Schema(description = "表单状态-参见 CommonStatusEnum 枚举", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "表单状态不能为空")
    private Integer status;

    @Schema(description = "备注", example = "我是备注")
    private String remark;

}
