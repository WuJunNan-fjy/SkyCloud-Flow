package com.wjn.flow.domin.vo.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Schema(description = "管理后台 - BPM 流程分类新增/修改 Request VO")
@Data
public class FlwCategorySaveReqVO {

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
