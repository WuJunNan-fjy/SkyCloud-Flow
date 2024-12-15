package com.wjn.flow.domain.dto.definition;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wjn.common.annotation.Excel;
import com.wjn.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * BPM 流程分类 DO
 *
 * @author wjn
 */
@Table(name = "flw_category")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlwCategoryDO extends BaseEntity {

    /**
     * 主键
     */
    @Schema(name = "id", description = "主键")
    @Id
    @JsonFormat(shape = JsonFormat.Shape.STRING)//long类型返回字符串，防止前台js出现精度损失问题，造成后台报错
    private Long id;

    /**
     * 分类编号
     */
    @Schema(name = "categoryCode", description = "分类编号")
    @Excel(name = "分类编号")
    private String categoryCode;

    /**
     * 分类名称
     */
    @Schema(name = "categoryName", description = "分类名称")
    @Excel(name = "分类名称")
    private String categoryName;

    /**
     * 序号
     */
    @Schema(name = "num", description = "序号")
    @Excel(name = "序号")
    @JsonFormat(shape = JsonFormat.Shape.STRING)//long类型返回字符串，防止前台js出现精度损失问题，造成后台报错
    private Long num;

    /**
     * 组织机构ID
     */
    @Schema(name = "deptId", description = "组织机构ID")
    @Excel(name = "组织机构ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)//long类型返回字符串，防止前台js出现精度损失问题，造成后台报错
    private Long deptId;

    /**
     * 人员ID
     */
    @Schema(name = "userId", description = "人员ID")
    @Excel(name = "人员ID")
    private Long userId;

    /**
     * 创建者
     */
    @Schema(name = "createBy", description = "创建者")
    private String createBy;

    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间")
    private Date createTime;

    /**
     * 更新者
     */
    @Schema(name = "updateBy", description = "更新者")
    private String updateBy;

    /**
     * 更新时间
     */
    @Schema(name = "updateTime", description = "更新时间")
    private Date updateTime;

    /**
     * 备注
     */
    @Schema(name = "remark", description = "备注")
    @Excel(name = "备注")
    private String remark;

}
