package com.wjn.ledger.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wjn.common.annotation.Excel;
import com.wjn.common.core.domain.BaseEntity;

/**
 * 记账明细对象 t_led_detail
 *
 * @author wjn
 * @date 2025-04-17
 */
@Data
@Builder
public class TLedDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Tolerate
    public TLedDetail() {}

    /** 明细主键 */
    private Long detailId;

    /** 分类id */
    @Excel(name = "分类id")
    private Long categoryId;

    /** 明细日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "明细日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date detailDate;

    /** 记账金额 */
    @Excel(name = "记账金额")
    private BigDecimal amount;

    /** 记账备注 */
    @Excel(name = "记账备注")
    private String description;

    /** 记账类型 */
    @Excel(name = "记账类型")
    private String detailType;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Long isDeleted;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createUser;

    /** 修改人 */
    @Excel(name = "修改人")
    private String updateUser;

    private String categoryName;
}
