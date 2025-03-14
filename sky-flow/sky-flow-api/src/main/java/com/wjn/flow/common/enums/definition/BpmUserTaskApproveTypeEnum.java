package com.wjn.flow.common.enums.definition;


import com.wjn.common.validation.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 用户任务的审批类型枚举
 *
 * @author wjn
 */
@Getter
@AllArgsConstructor
public enum BpmUserTaskApproveTypeEnum implements IntArrayValuable {

    USER(1), // 人工审批
    AUTO_APPROVE(2), // 自动通过
    AUTO_REJECT(3); // 自动拒绝

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(BpmUserTaskApproveTypeEnum::getType).toArray();

    private final Integer type;

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
