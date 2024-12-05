package com.wjn.flow.common.enums.definition;


import com.wjn.common.validation.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 用户任务超时处理类型枚举
 *
 * @author wjn
 */
@Getter
@AllArgsConstructor
public enum BpmUserTaskTimeoutHandlerTypeEnum implements IntArrayValuable {

    REMINDER(1,"自动提醒"),
    APPROVE(2, "自动同意"),
    REJECT(3, "自动拒绝");

    private final Integer type;
    private final String name;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(BpmUserTaskTimeoutHandlerTypeEnum::getType).toArray();

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
