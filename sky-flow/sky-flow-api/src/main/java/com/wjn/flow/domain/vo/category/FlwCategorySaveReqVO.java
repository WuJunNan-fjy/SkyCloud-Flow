package com.wjn.flow.domain.vo.category;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.wjn.common.core.domain.BaseEntity;
import javax.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Schema(description = "管理后台 - BPM 流程分类新增/修改 Request VO")
@Data
public class FlwCategorySaveReqVO extends BaseEntity {

    /** 主键 */
    private Long id;

    /** 分类编号 */
    @NotBlank(message = "分类编号不能为空")
    private String categoryCode;

    /** 分类名称 */
    @NotBlank(message = "分类名称不能为空")
    private String categoryName;

    /** 序号 */
    private Long num;
}
