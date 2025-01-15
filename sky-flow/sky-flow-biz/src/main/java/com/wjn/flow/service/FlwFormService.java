package com.wjn.flow.service;

import com.wjn.common.core.service.BaseService;
import com.wjn.flow.domain.dto.flow.FlwFormDO;
import com.wjn.flow.domain.vo.form.FlwFormPageReqVO;
import com.wjn.flow.domain.vo.form.FlwFormRespVO;
import com.wjn.flow.domain.vo.form.FlwFormSaveReqVO;

import java.util.List;

/**
 * @description: 流程表单接口
 * @author: wjn
 * @create: 2024-12-06 18:36
 * @version:v1.0
 */
public interface FlwFormService extends BaseService<FlwFormDO> {

    /**
     * 功能描述: 创建流程表单
     * @author wjn
     * @create 2024/12/7 星期六 9:47
     * @param: [createReqVO]
     * @return int
     */
    int createForm(FlwFormSaveReqVO createReqVO);

    /**
     * 功能描述: 更新流程表单
     * @author wjn
     * @create 2024/12/7 星期六 9:47
     * @param: [updateReqVO]
     * @return int
     */
    int updateForm(FlwFormSaveReqVO updateReqVO);

    /**
     * 功能描述: 删除流程表单
     * @author wjn
     * @create 2024/12/7 星期六 9:47
     * @param: [idStr]
     * @return int
     */
    int deleteForm(String idStr);

    /**
     * 功能描述: 获得流程表单详情
     * @author wjn
     * @create 2024/12/7 星期六 9:48
     * @param: [id]
     * @return java.lang.String
     */
    FlwFormRespVO getForm(Long id);

    /**
     * 功能描述: 获得流程表单分页
     * @author wjn
     * @create 2024/12/7 星期六 9:48
     * @param: [pageReqVO]
     * @return java.util.List<com.wjn.flow.domain.vo.form.FlwFormRespVO>
     */
    List<FlwFormDO> getFormPage(FlwFormPageReqVO pageReqVO);
}
