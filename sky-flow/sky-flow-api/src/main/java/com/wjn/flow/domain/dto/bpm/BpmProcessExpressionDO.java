package com.wjn.flow.domain.dto.bpm;

import com.wjn.common.core.domain.BaseEntity;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BPM 流程表达式 DO
 *
 * @author wjn
 */
@Table(name = "bpm_process_expression")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BpmProcessExpressionDO extends BaseEntity {

    /**
     * 编号
     */
    @Id
    private Long id;
    /**
     * 表达式名字
     */
    private String name;
    /**
     * 表达式状态
     *
     * 枚举 {@link TODO common_status 对应的类}
     */
    private Integer status;
    /**
     * 表达式
     */
    private String expression;

}
