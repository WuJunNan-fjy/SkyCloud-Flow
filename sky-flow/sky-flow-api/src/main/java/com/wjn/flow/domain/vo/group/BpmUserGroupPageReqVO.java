package com.wjn.flow.domain.vo.group;

import com.wjn.common.core.page.PageParam;
import com.wjn.common.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 用户组分页 Request VO")
@Data
public class BpmUserGroupPageReqVO extends PageParam {

    @Schema(description = "编号", example = "1024")
    private Long id;

    @Schema(description = "组名", example = "wjn")
    private String name;

    @Schema(description = "状态", example = "1")
    private Integer status;

    @DateTimeFormat(pattern = DateUtils.FORMAT_YYYY_MM_DD_HH_MM_SS)
    @Schema(description = "创建时间")
    private LocalDateTime[] createTime;

}
