package com.wjn.flow.common.convert;

import com.wjn.common.utils.bean.BeanUtils;
import com.wjn.common.utils.json.JsonUtils;
import com.wjn.flow.domin.vo.model.BpmModelMetaInfoVO;
import com.wjn.flow.domin.vo.model.BpmModelSaveReqVO;
import org.flowable.engine.repository.Model;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * 流程模型 Convert
 *
 * 该接口用于将请求对象（如 BpmModelSaveReqVO）转换为 Flowable 中的流程模型（Model）对象。
 * 用于流程模型的保存、更新等操作。
 *
 * @author wjn
 */
@Mapper
public interface BpmModelConvert {

    // 获取 BpmModelConvert 实例
    BpmModelConvert INSTANCE = Mappers.getMapper(BpmModelConvert.class);

    default void saveVoConvertModel(Model model, BpmModelSaveReqVO saveReqVO){
        model.setKey(saveReqVO.getKey());
        model.setName(saveReqVO.getName());
        model.setCategory(saveReqVO.getCategory());
        model.setMetaInfo(JsonUtils.toJsonString(BeanUtils.toBean(saveReqVO, BpmModelMetaInfoVO.class)));
    }
}
