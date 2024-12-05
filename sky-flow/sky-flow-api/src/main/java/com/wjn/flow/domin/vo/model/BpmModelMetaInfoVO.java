package com.wjn.flow.domin.vo.model;


import com.wjn.common.validation.InEnum;
import com.wjn.flow.common.enums.definition.BpmModelFormTypeEnum;
import com.wjn.flow.common.enums.definition.BpmModelTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * BPM 流程 MetaInfo Response DTO
 * 主要用于 { Model#setMetaInfo(String)} 的存储
 *
 * 最终，它的字段和 {@link com.wjn.flow.domin.dto.definition.BpmProcessDefinitionInfoDO} 是一致的
 *
 * @author wjn
 */
@Data
public class BpmModelMetaInfoVO {
    @Schema(description = "流程描述", example = "我是描述")
    private String description;

    @Schema(description = "流程类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    @InEnum(BpmModelTypeEnum.class)
    @NotNull(message = "流程类型不能为空")
    private Integer type;

    @Schema(description = "表单类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    @InEnum(BpmModelFormTypeEnum.class)
    @NotNull(message = "表单类型不能为空")
    private Integer formType;

    @Schema(description = "表单编号", example = "1024")
    private Long formId;  // formType 为 NORMAL 使用，必须非空

    @Schema(description = "自定义表单的提交路径，使用 Vue 的路由地址",
            example = "/bpm/oa/leave/create")
    private String formCustomCreatePath;  // 表单类型为 CUSTOM 时，必须非空

    @Schema(description = "自定义表单的查看路径，使用 Vue 的路由地址",
            example = "/bpm/oa/leave/view")
    private String formCustomViewPath;  // 表单类型为 CUSTOM 时，必须非空
}
