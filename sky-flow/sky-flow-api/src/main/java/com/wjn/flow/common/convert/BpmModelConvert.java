package com.wjn.flow.common.convert;

import com.wjn.common.utils.StringUtils;
import com.wjn.common.utils.bean.BeanUtils;
import com.wjn.common.utils.json.JsonUtils;
import com.wjn.flow.domain.vo.model.BpmModelMetaInfoVO;
import com.wjn.flow.domain.vo.model.BpmModelPageReqVO;
import com.wjn.flow.domain.vo.model.BpmModelRespVO;
import com.wjn.flow.domain.vo.model.BpmModelSaveReqVO;
import org.flowable.engine.repository.Model;
import org.flowable.engine.repository.ModelQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 流程模型 Convert
 * <p>
 * 该接口用于将请求对象（如 BpmModelSaveReqVO）转换为 Flowable 中的流程模型（Model）对象。
 * 用于流程模型的保存、更新等操作。
 *
 * @author wjn
 */
@Mapper
public interface BpmModelConvert {

    // 获取 BpmModelConvert 实例
    BpmModelConvert INSTANCE = Mappers.getMapper(BpmModelConvert.class);

    // 将要保存的请求对象转换为 Flowable 中的流程模型对象
    default void saveVoConvertModel(Model model, BpmModelSaveReqVO saveReqVO) {
        model.setKey(saveReqVO.getKey());
        model.setName(saveReqVO.getName());
        model.setCategory(saveReqVO.getCategory());
        model.setMetaInfo(JsonUtils.toJsonString(BeanUtils.toBean(saveReqVO, BpmModelMetaInfoVO.class)));
    }

    // 构建分页查询条件
    default ModelQuery builderModelQuery(ModelQuery modelQuery, BpmModelPageReqVO pageReqVO) {
        if (StringUtils.isNotBlank(pageReqVO.getKey())) {
            modelQuery.modelKey(pageReqVO.getKey());
        }

        if (StringUtils.isNotBlank(pageReqVO.getName())) {
            modelQuery.modelNameLike(pageReqVO.getName());
        }

        if (StringUtils.isNotBlank(pageReqVO.getCategory())) {
            modelQuery.modelCategoryLike(pageReqVO.getCategory());
        }
        return modelQuery;
    }

    // 将流程模型列表转换为返回结果对象
    default List<BpmModelRespVO> modelConvertPage(List<Model> models) {
        return models.stream()
                .map(model -> BpmModelRespVO.builder()
                        .id(model.getId())
                        .key(model.getKey())
                        .name(model.getName())
                        .category(model.getCategory())
                        .createTime(model.getCreateTime())
                        .build())
                .collect(Collectors.toList());
    }
}
