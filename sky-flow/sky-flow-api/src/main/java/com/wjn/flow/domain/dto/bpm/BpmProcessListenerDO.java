package com.wjn.flow.domain.dto.bpm;

import com.wjn.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BPM 流程监听器 DO
 *
 * 目的：本质上它是流程监听器的模版，用于 BPMN 在设计时，直接选择这些模版
 *
 * @author wjn
 */
@Table(name = "bpm_process_listener")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BpmProcessListenerDO extends BaseEntity {

    /**
     * 主键 ID，自增
     */
    @Id
    private Long id;
    /**
     * 监听器名字
     */
    private String name;
    /**
     * 状态
     *
     * 枚举 {@link com.wjn.common.enums.CommonStatusEnum}
     */
    private Integer status;
    /**
     * 监听类型
     *
     * 枚举 {@link com.wjn.flow.common.enums.definition.BpmProcessListenerType}
     *
     * 1. execution：ExecutionListener <a href="https://tkjohn.github.io/flowable-userguide/#executionListeners">执行监听器</a>
     * 2. task：TaskListener <a href="https://tkjohn.github.io/flowable-userguide/#taskListeners">任务监听器</a>
     */
    private String type;
    /**
     * 监听事件
     *
     * execution 时：start、end
     * task 时：create 创建、assignment 指派、complete 完成、delete 删除、update 更新、timeout 超时
     */
    private String event;

    /**
     * 值类型
     *
     * 1. class：Java 类，ExecutionListener 需要 {@link org.flowable.engine.delegate.JavaDelegate}，TaskListener 需要 {@link org.flowable.engine.delegate.TaskListener}
     * 2. delegateExpression：委托表达式，在 class 的基础上，需要注册到 Spring 容器里，后续表达式通过 Spring Bean 名称即可
     * 3. expression：表达式，一个普通类的普通方法，将这个普通类注册到 Spring 容器中，然后表达式中还可以执行这个类中的方法
     */
    private String valueType;
    /**
     * 值
     */
    private String value;

}
