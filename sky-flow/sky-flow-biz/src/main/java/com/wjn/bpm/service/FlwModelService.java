package com.wjn.bpm.service;

import com.wjn.common.core.page.TableDataInfo;
import com.wjn.flow.domain.vo.model.BpmModelPageReqVO;
import com.wjn.flow.domain.vo.model.BpmModelSaveReqVO;

/**
 * @description: 流程模型业务接口
 * @author: wjn
 * @create: 2024-12-04 15:56
 * @version:v1.0
 */
public interface FlwModelService {

    /**
     * 功能描述: 获取流程模型列表
     * @author wjn
     * @create 2024/12/5 星期四 14:36
     * @param: []
     * @return java.lang.String
     */
    TableDataInfo list(BpmModelPageReqVO pageReqVO);

    /**
     * 功能描述: 新建流程模型
     * @author wjn
     * @create 2024/12/4 星期三 15:59
     * @param: [createRetVO]
     * @return void
     */
    String createModel(BpmModelSaveReqVO createRetVO);
}
