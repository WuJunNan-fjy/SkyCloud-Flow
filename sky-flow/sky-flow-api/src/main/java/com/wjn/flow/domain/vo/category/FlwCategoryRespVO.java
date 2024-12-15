package com.wjn.flow.domain.vo.category;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wjn.common.annotation.Excel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - BPM 流程分类 Response VO")
@Data
public class FlwCategoryRespVO {

    /** 主键 */
    @Schema(name = "id",description = "主键")
    @JsonFormat(shape=JsonFormat.Shape.STRING)//long类型返回字符串，防止前台js出现精度损失问题，造成后台报错
    private Long id;

    /** 分类编号 */
    @Schema(name = "categoryCode",description = "分类编号")
    @Excel(name = "分类编号")
    private String categoryCode;

    /** 分类名称 */
    @Schema(name = "categoryName",description = "分类名称")
    @Excel(name = "分类名称")
    private String categoryName;

    /** 序号 */
    @Schema(name = "num",description = "序号")
    @Excel(name = "序号")
    @JsonFormat(shape=JsonFormat.Shape.STRING)//long类型返回字符串，防止前台js出现精度损失问题，造成后台报错
    private Long num;

    /** 备注 */
    @Schema(name = "remark",description = "备注")
    @Excel(name = "备注")
    private String remark;
}
