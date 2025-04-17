package com.wjn.ledger.domain;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wjn.common.annotation.Excel;
import com.wjn.common.core.domain.BaseEntity;

/**
 * 记账分类对象 t_led_category
 *
 * @author wjn
 * @date 2025-04-17
 */
@Data
@Builder
public class TLedCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Tolerate
    public TLedCategory() {}

    /** 分类主键 */
    private Long categoryId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String categoryName;

    /** 分类备注 */
    @Excel(name = "分类备注")
    private String categoryDesc;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 是否删除：00正常，01删除 */
    @Excel(name = "是否删除：00正常，01删除")
    private Long isDeleted;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createUser;

    /** 更信人 */
    @Excel(name = "更信人")
    private String updateUser;
}
