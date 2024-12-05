package com.wjn.flow.domin.dto.definition;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.wjn.common.core.mybatis.BaseDO;
import com.wjn.common.enums.CommonStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

/**
 * BPM 用户组
 *
 * @author wjn
 */
@TableName(value = "bpm_user_group", autoResultMap = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BpmUserGroupDO extends BaseDO {

    /**
     * 编号，自增
     */
    @TableId
    private Long id;
    /**
     * 组名
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 成员用户编号数组
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Set<Long> userIds;

}
