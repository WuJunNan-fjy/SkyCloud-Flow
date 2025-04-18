package com.wjn.flow.common.enums.definition;


import com.wjn.common.validation.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * BPM 模型的类型的枚举
 *
 * @author wjn
 */
@Getter
@AllArgsConstructor
public enum BpmModelTypeEnum implements IntArrayValuable {

    BPMN(10, "BPMN 设计器"), // https://bpmn.io/toolkit/bpmn-js/
    SIMPLE(20, "SIMPLE 设计器"); // 参考钉钉、飞书工作流的设计器

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(BpmModelTypeEnum::getType).toArray();

    private final Integer type;
    private final String name;

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
