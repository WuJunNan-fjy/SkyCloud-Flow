package com.wjn.flow.domain.dto.flow;

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

/**
 * BPM 工作流的表单定义
 * 用于工作流的申请表单，需要动态配置的场景
 *
 * @author wjn
 */
@Table(name = "flw_form")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlwFormDO extends BaseEntity {

    /**
     * 编号
     */
    @Id
    @Schema(name = "id", description = "主键")
    @JsonFormat(shape = JsonFormat.Shape.STRING)//long类型返回字符串，防止前台js出现精度损失问题，造成后台报错
    private Long id;
    /**
     * 表单名
     */
    @Schema(name = "name", description = "表单名")
    @Excel(name = "表单名")
    private String name;
    /**
     * 状态
     */
    @Schema(name = "status", description = "状态")
    @Excel(name = "状态")
    private Integer status;
    /**
     * 表单的配置
     */
    @Schema(name = "conf", description = "表单的配置")
    @Excel(name = "表单项的数组")
    private String conf;
    /**
     * 表单项的数组
     *
     * 目前直接将 https://github.com/JakHuang/form-generator 生成的 JSON 串，直接保存
     * 定义：https://github.com/JakHuang/form-generator/issues/46
     */
    private String fields;
    /**
     * 备注
     */
    @Schema(name = "remark", description = "备注")
    @Excel(name = "备注")
    private String remark;

}
