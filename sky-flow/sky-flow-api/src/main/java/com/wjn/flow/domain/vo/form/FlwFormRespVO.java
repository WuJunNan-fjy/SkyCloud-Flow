package com.wjn.flow.domain.vo.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wjn.common.handler.BooleanToIntegerDeserializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 动态表单 Response VO")
@Data
public class FlwFormRespVO {

    @Schema(description = "表单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @JsonFormat(shape = JsonFormat.Shape.STRING)//long类型返回字符串，防止前台js出现精度损失问题，造成后台报错
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

    @Schema(description = "表单状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "表单状态不能为空")
    private Integer status; // 参见 CommonStatusEnum 枚举

    @Schema(description = "备注", example = "我是备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
