package com.wjn.flow.common.convert;

import com.wjn.common.utils.SnowFlake;
import com.wjn.flow.domain.dto.definition.FlwCategoryDO;
import com.wjn.flow.domain.vo.category.FlwCategorySaveReqVO;
import org.mapstruct.Mapper;
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
    FlwCategoryDO convertToDO(FlwCategorySaveReqVO vo);


    // 需要处理转换逻辑
    default FlwCategoryDO createConvertToDO(FlwCategorySaveReqVO vo) {
        if (vo == null) {
            return null;
        }
        FlwCategoryDO categoryDO = new FlwCategoryDO();
        categoryDO.setId(SnowFlake.getId());
        categoryDO.setCategoryCode(vo.getCategoryCode());
        categoryDO.setCategoryName(vo.getCategoryName());
        categoryDO.setNum(vo.getNum());
        categoryDO.setRemark(vo.getRemark());
        categoryDO.setUserId(vo.getUserId());
        categoryDO.setDeptId(vo.getDeptId());
        categoryDO.setCreateTime(vo.getCreateTime());
        categoryDO.setCreateBy(vo.getCreateBy());
        // 可以加更多的自定义转换逻辑
        return categoryDO;
    }

    // 需要处理转换逻辑
    default FlwCategoryDO updateConvertToDO(FlwCategorySaveReqVO vo) {
        if (vo == null) {
            return null;
        }
        FlwCategoryDO categoryDO = new FlwCategoryDO();
        categoryDO.setId(vo.getId());
        categoryDO.setCategoryCode(vo.getCategoryCode());
        categoryDO.setCategoryName(vo.getCategoryName());
        categoryDO.setNum(vo.getNum());
        categoryDO.setRemark(vo.getRemark());
        categoryDO.setUserId(vo.getUserId());
        categoryDO.setDeptId(vo.getDeptId());
        categoryDO.setUpdateTime(vo.getUpdateTime());
        categoryDO.setUpdateBy(vo.getUpdateBy());
        // 可以加更多的自定义转换逻辑
        return categoryDO;
    }
}
