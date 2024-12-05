package com.wjn.flow.service;

import com.wjn.flow.domin.vo.model.BpmModelSaveReqVO;

/**
 * @description: 流程模型业务接口
 * @author: wjn
 * @create: 2024-12-04 15:56
 * @version:v1.0
 */
public interface FlowModelService {

    /**
     * 功能描述: 新建流程模型
     * @author wjn
     * @create 2024/12/4 星期三 15:59
     * @param: [createRetVO]
     * @return void
     */
    String createModel(BpmModelSaveReqVO createRetVO);
}
