package com.wjn.flow.service.impl;

import com.wjn.common.core.service.impl.BaseServiceImpl;
import com.wjn.common.utils.SnowFlake;
import com.wjn.flow.common.convert.FlwCategoryConvert;
import com.wjn.flow.domin.dto.definition.FlwCategoryDO;
import com.wjn.flow.domin.vo.category.FlwCategoryPageReqVO;
import com.wjn.flow.domin.vo.category.FlwCategoryRespVO;
import com.wjn.flow.domin.vo.category.FlwCategorySaveReqVO;
import com.wjn.flow.mapper.FlwCategoryMapper;
import com.wjn.flow.service.FlwCategoryService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 流程分类服务实现
 * @author: wjn
 * @create: 2024-12-06 18:37
 * @version:v1.0
 */
@Service
public class FlwCategoryServiceImpl extends BaseServiceImpl<FlwCategoryDO> implements FlwCategoryService {

    @Resource
    private FlwCategoryMapper categoryMapper;


    /**
     * 功能描述: 创建流程分类
     * @author wjn
     * @create 2024/12/7 星期六 9:47
     * @param: [createReqVO]
     * @return int
     */
    @Override
    public int createCategory(FlwCategorySaveReqVO createReqVO) {
        FlwCategoryDO flwCategoryDO = FlwCategoryConvert.INSTANCE.convertToDO(createReqVO);
        flwCategoryDO.setId(SnowFlake.getId());
        return categoryMapper.insert(flwCategoryDO);
    }

    /**
     * 功能描述: 更新流程分类
     * @author wjn
     * @create 2024/12/7 星期六 9:47
     * @param: [updateReqVO]
     * @return int
     */
    @Override
    public int updateCategory(FlwCategorySaveReqVO updateReqVO){
        FlwCategoryDO flwCategoryDO = FlwCategoryConvert.INSTANCE.convertToDO(updateReqVO);
        return categoryMapper.updateByPrimaryKey(flwCategoryDO);
    }

    /**
     * 功能描述: 删除流程分类
     * @author wjn
     * @create 2024/12/7 星期六 9:47
     * @param: [id]
     * @return int
     */
    @Override
    public int deleteCategory(Long id){
        return 0;
    }

    /**
     * 功能描述: 获得流程分类详情
     * @author wjn
     * @create 2024/12/7 星期六 9:48
     * @param: [id]
     * @return java.lang.String
     */
    @Override
    public FlwCategoryRespVO getCategory(Long id){
        return null;
    }

    /**
     * 功能描述: 获得流程分类分页
     * @author wjn
     * @create 2024/12/7 星期六 9:48
     * @param: [pageReqVO]
     * @return java.util.List<com.wjn.flow.domin.vo.category.FlwCategoryRespVO>
     */
    @Override
    public List<FlwCategoryRespVO> getCategoryPage(@Valid FlwCategoryPageReqVO pageReqVO){
        return null;
    }
}