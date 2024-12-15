package com.wjn.flow.mapper;

import com.wjn.common.core.crudDao.BaseMapper;
import com.wjn.flow.domain.dto.definition.FlwCategoryDO;
import com.wjn.flow.domain.vo.category.FlwCategoryPageReqVO;
import java.util.List;


/**
 * @description: 流程分类mapper
 * @author: wjn
 * @create: 2024-12-06 18:37
 * @version:v1.0
 */
public interface FlwCategoryMapper extends BaseMapper<FlwCategoryDO> {

    /**
     * 功能描述: 分页查询分类列表
     * @author wjn
     * @create 2024/12/15 星期日 16:15
     * @param: [pageReqVO]
     * @return java.util.List<com.wjn.flow.domin.vo.category.FlwCategoryRespVO>
     */
    List<FlwCategoryDO> queryCategoryPage(FlwCategoryPageReqVO pageReqVO);
}
