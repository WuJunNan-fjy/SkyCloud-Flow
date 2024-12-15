package com.wjn.common.core.domain;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * Entity基类
 *
 * @author wjn
 */
@Data
public class BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 创建者 */
    @Transient
    private String createBy;

    /** 创建时间 */
    @Transient
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "GMT+8")
    private Date createTime;

    /** 更新者 */
    @Transient
    private String updateBy;

    /** 更新时间 */
    @Transient
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "GMT+8")
    private Date updateTime;

    /** 备注 */
    @Transient
    private String remark;

    /** 用户ID */
    @Transient
    private Long userId;

    /** 部门ID */
    @Transient
    private Long deptId;

    /** 请求参数 */
    private Map<String, Object> params;

    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }
}
