package com.wjn.flow.common.enums.definition;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * BPM 流程监听器的类型
 *
 * @author wjn
 */
@Getter
@AllArgsConstructor
public enum BpmProcessListenerType {

    EXECUTION("execution", "执行监听器"),
    TASK("task", "任务执行器");

    private final String type;
    private final String name;

}
