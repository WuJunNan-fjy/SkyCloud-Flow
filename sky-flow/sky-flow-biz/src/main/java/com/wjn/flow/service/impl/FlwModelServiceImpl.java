package com.wjn.flow.service.impl;

import static com.wjn.common.utils.exception.ServiceExceptionUtil.exception;
import static com.wjn.flow.common.constant.ErrorCodeConstants.MODEL_KEY_EXISTS;
import static com.wjn.flow.common.constant.ErrorCodeConstants.MODEL_KEY_VALID;

import com.wjn.common.core.page.TableDataInfo;
import com.wjn.common.utils.bean.PageUtils;
import com.wjn.common.utils.validation.ValidationUtils;
import com.wjn.flow.common.convert.BpmModelConvert;
import com.wjn.flow.domin.vo.model.BpmModelPageReqVO;
import com.wjn.flow.domin.vo.model.BpmModelRespVO;
import com.wjn.flow.domin.vo.model.BpmModelSaveReqVO;
import com.wjn.flow.service.FlwModelService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Model;
import org.flowable.engine.repository.ModelQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @description: 流程模型服务实现
 * @author: wjn
 * @create: 2024-12-04 15:59
 * @version:v1.0
 */
@Service
public class FlwModelServiceImpl implements FlwModelService {

    @Resource
    private RepositoryService repositoryService; // 管理流程定义和部署的服务

    /**
     * 功能描述: 获取流程模型列表
     * @author wjn
     * @create 2024/12/5 星期四 14:36
     * @param: []
     * @return java.lang.String
     */
    @Override
    public TableDataInfo list(BpmModelPageReqVO pageReqVO) {
        ModelQuery modelQuery = repositoryService.createModelQuery();

        BpmModelConvert.INSTANCE.builderModelQuery(modelQuery, pageReqVO);

        // 执行查询
        Long count = modelQuery.count();
        if (count == 0L) {
            return null;
        }
        List<Model> models = modelQuery
                .orderByCreateTime().desc()
                .listPage(PageUtils.getStart(pageReqVO), pageReqVO.getPageSize());

        // 拼接数据、转换对象
        List<BpmModelRespVO> modelRespVOList = BpmModelConvert.INSTANCE.modelConvertPage(models);

        return new TableDataInfo(modelRespVOList, count);
    }

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
