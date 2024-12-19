package com.wjn.flow.domain.dto.bpm;

import com.wjn.common.core.domain.BaseEntity;
import com.wjn.common.enums.CommonStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

/**
 * BPM 用户组
 *
 * @author wjn
 */
@Table(name = "bpm_user_group")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BpmUserGroupDO extends BaseEntity {

    /**
     * 编号，自增
     */
    @Id
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
    // @TableField("userIds")
    private Set<Long> userIds;

}
