package com.wjn.flow.service.impl;

import com.wjn.common.core.service.impl.BaseServiceImpl;
import com.wjn.flow.common.convert.FlwCategoryConvert;
import com.wjn.flow.domain.dto.flow.FlwCategoryDO;
import com.wjn.flow.domain.vo.category.FlwCategoryPageReqVO;
import com.wjn.flow.domain.vo.category.FlwCategorySaveReqVO;
import com.wjn.flow.mapper.FlwCategoryMapper;
import com.wjn.flow.service.FlwCategoryService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
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
        FlwCategoryDO flwCategoryDO = FlwCategoryConvert.INSTANCE.createConvertToDO(createReqVO);
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
        FlwCategoryDO flwCategoryDO = FlwCategoryConvert.INSTANCE.updateConvertToDO(updateReqVO);
        return categoryMapper.updateByPrimaryKeySelective(flwCategoryDO);
    }

    /**
     * 功能描述: 删除流程分类
     * @author wjn
     * @create 2024/12/7 星期六 9:47
     * @param: [idStr]
     * @return int
     */
    @Override
    public int deleteCategory(String idStr){
        return categoryMapper.deleteByIds(idStr);
    }

    /**
     * 功能描述: 获得流程分类详情
     * @author wjn
     * @create 2024/12/7 星期六 9:48
     * @param: [id]
     * @return java.lang.String
     */
    @Override
    public FlwCategoryDO getCategory(Long id){
        return categoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 功能描述: 获得流程分类分页
     * @author wjn
     * @create 2024/12/7 星期六 9:48
     * @param: [pageReqVO]
     * @return java.util.List<com.wjn.flow.domin.vo.category.FlwCategoryRespVO>
     */
    @Override
    public List<FlwCategoryDO> getCategoryPage(FlwCategoryPageReqVO pageReqVO){
        List<FlwCategoryDO> pageResult = categoryMapper.queryCategoryPage(pageReqVO);
        return pageResult;
    }
}
