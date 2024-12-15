package com.wjn.flow.domain.dto.definition;

import com.wjn.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * BPM 工作流的表单定义
 * 用于工作流的申请表单，需要动态配置的场景
 *
 * @author wjn
 */
@Table(name = "bpm_form")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BpmFormDO extends BaseEntity {

    /**
     * 编号
     */
    @Id
    private Long id;
    /**
     * 表单名
     */
    private String name;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 表单的配置
     */
    private String conf;
    /**
     * 表单项的数组
     *
     * 目前直接将 https://github.com/JakHuang/form-generator 生成的 JSON 串，直接保存
     * 定义：https://github.com/JakHuang/form-generator/issues/46
     */
    private List<String> fields;
    /**
     * 备注
     */
    private String remark;

}
