package com.wjn.flow.common.convert;

import com.wjn.flow.domin.dto.definition.FlwCategoryDO;
import com.wjn.flow.domin.vo.category.FlwCategorySaveReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @description: 流程分类保存对象转换成流程分类DO对象
 * @author: wjn
 * @create: 2024-12-07 10:02
 * @version:v1.0
 */
@Mapper
public interface FlwCategoryConvert {

    FlwCategoryConvert INSTANCE = Mappers.getMapper(FlwCategoryConvert.class);

    // 方法定义：将 VO 转换为 DO
    // @Mapping(source = "id", target = "id")
    // @Mapping(source = "categoryCode", target = "categoryCode")
    // @Mapping(source = "categoryName", target = "categoryName")
    // @Mapping(source = "num", target = "num")
    FlwCategoryDO convertToDO(FlwCategorySaveReqVO vo);
}
