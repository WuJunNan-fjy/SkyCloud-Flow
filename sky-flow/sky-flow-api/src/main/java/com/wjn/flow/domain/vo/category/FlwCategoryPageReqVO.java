package com.wjn.flow.domain.vo.category;

import com.wjn.common.core.page.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "流程管理 - flw 流程分类分页 Request VO")
public class FlwCategoryPageReqVO extends PageParam {

    /** 分类编号 */
    @Schema(name = "categoryCode",description = "分类编号")
    private String categoryCode;

    /** 分类名称 */
    @Schema(name = "categoryName",description = "分类名称")
    private String categoryName;
}
