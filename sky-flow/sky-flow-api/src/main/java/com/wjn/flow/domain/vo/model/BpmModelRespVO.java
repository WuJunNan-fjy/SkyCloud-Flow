package com.wjn.flow.domain.vo.model;

import com.wjn.flow.domain.vo.process.BpmProcessDefinitionRespVO;
import com.wjn.flow.domain.vo.user.UserSimpleBaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Schema(description = "管理后台 - 流程模型 Response VO")
@Data
@Builder
public class BpmModelRespVO extends BpmModelMetaInfoVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "9f2c5925-b2d0-11ef-ad00-50e0850ee2a9")
    private String id;

    @Schema(description = "流程标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "P_123456789")
    private String key;

    @Schema(description = "流程名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "天云测试流程模型")
    private String name;

    @Schema(description = "流程分类编码", example = "1")
    private String category;

    @Schema(description = "流程分类名字", example = "请假")
    private String categoryName;

    @Schema(description = "表单名字", example = "请假表单")
    private String formName;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private Date createTime;

    @Schema(description = "BPMN XML", requiredMode = Schema.RequiredMode.REQUIRED)
    private String bpmnXml;

    @Schema(description = "可发起的用户数组")
    private List<UserSimpleBaseVO> startUsers;

    /**
     * 最新部署的流程定义
     */
    private BpmProcessDefinitionRespVO processDefinition;

}
