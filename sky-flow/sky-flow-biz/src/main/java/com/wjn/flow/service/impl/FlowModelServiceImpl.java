package com.wjn.flow.service.impl;

import static com.wjn.common.utils.exception.ServiceExceptionUtil.exception;
import static com.wjn.flow.common.constant.ErrorCodeConstants.MODEL_KEY_EXISTS;
import static com.wjn.flow.common.constant.ErrorCodeConstants.MODEL_KEY_VALID;
import com.wjn.common.utils.validation.ValidationUtils;
import com.wjn.flow.common.convert.BpmModelConvert;
import com.wjn.flow.domin.vo.model.BpmModelSaveReqVO;
import com.wjn.flow.service.FlowModelService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Model;
import javax.annotation.Resource;


/**
 * @description:
 * @author: wjn
 * @create: 2024-12-04 15:59
 * @version:v1.0
 */
public class FlowModelServiceImpl implements FlowModelService {

    @Resource
    private RepositoryService repositoryService; // 管理流程定义和部署的服务


    /**
     * 功能描述: 新建流程模型
     * @author wjn
     * @create 2024/12/4 星期三 15:59
     * @param: [createRetVO]
     * @return void
     */
    @Override
    public String createModel(BpmModelSaveReqVO createRetVO) {
        // 流程key格式校验
        if(!ValidationUtils.isXmlNCName(createRetVO.getKey())){
            throw exception(MODEL_KEY_VALID);
        }

        // 根据key查看model是否已存在
        Model modelExists = repositoryService.createModelQuery().modelKey(createRetVO.getKey()).singleResult();

        if(modelExists != null) {
            throw exception(MODEL_KEY_EXISTS);
        }

        Model model = repositoryService.newModel();
        BpmModelConvert.INSTANCE.saveVoConvertModel(model,createRetVO);
        repositoryService.saveModel(model);
        return model.getId();
    }
}
