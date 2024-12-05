package com.wjn.flow.common.enums.task;


import com.wjn.common.utils.bean.ObjectUtils;
import com.wjn.common.validation.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 流程实例 ProcessInstance 的状态
 *
 * @author wjn
 */
@Getter
@AllArgsConstructor
public enum BpmProcessInstanceStatusEnum implements IntArrayValuable {

    NOT_START(-1, "未开始"),
    RUNNING(1, "审批中"),
    APPROVE(2, "审批通过"),
    REJECT(3, "审批不通过"),
    CANCEL(4, "已取消");

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(BpmProcessInstanceStatusEnum::getStatus).toArray();

    /**
     * 状态
     */
    private final Integer status;
    /**
     * 描述
     */
    private final String desc;

    @Override
    public int[] array() {
        return ARRAYS;
    }

    public static boolean isProcessEndStatus(Integer status) {
        return ObjectUtils.equalsAny(status,
                APPROVE.getStatus(), REJECT.getStatus(), CANCEL.getStatus());
    }

}
