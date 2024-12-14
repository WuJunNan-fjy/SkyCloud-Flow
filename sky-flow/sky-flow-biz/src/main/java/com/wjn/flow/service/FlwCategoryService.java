package com.wjn.flow.service;

import com.wjn.common.core.service.BaseService;
import com.wjn.flow.domin.dto.definition.FlwCategoryDO;
import com.wjn.flow.domin.vo.category.FlwCategoryPageReqVO;
import com.wjn.flow.domin.vo.category.FlwCategoryRespVO;
import com.wjn.flow.domin.vo.category.FlwCategorySaveReqVO;
import java.util.List;

/**
 * @description: 流程分类接口
 * @author: wjn
 * @create: 2024-12-06 18:36
 * @version:v1.0
 */
public interface FlwCategoryService extends BaseService<FlwCategoryDO> {

    /**
     * 功能描述: 创建流程分类
     * @author wjn
     * @create 2024/12/7 星期六 9:47
     * @param: [createReqVO]
     * @return int
     */
    int createCategory(FlwCategorySaveReqVO createReqVO);

    /**
     * 功能描述: 更新流程分类
     * @author wjn
     * @create 2024/12/7 星期六 9:47
     * @param: [updateReqVO]
     * @return int
     */
    int updateCategory(FlwCategorySaveReqVO updateReqVO);

    /**
     * 功能描述: 删除流程分类
     * @author wjn
     * @create 2024/12/7 星期六 9:47
     * @param: [id]
     * @return int
     */
    int deleteCategory(Long id);

    /**
     * 功能描述: 获得流程分类详情
     * @author wjn
     * @create 2024/12/7 星期六 9:48
     * @param: [id]
     * @return java.lang.String
     */
    FlwCategoryRespVO getCategory(Long id);

    /**
     * 功能描述: 获得流程分类分页
     * @author wjn
     * @create 2024/12/7 星期六 9:48
     * @param: [pageReqVO]
     * @return java.util.List<com.wjn.flow.domin.vo.category.FlwCategoryRespVO>
     */
    List<FlwCategoryRespVO> getCategoryPage(FlwCategoryPageReqVO pageReqVO);
}
