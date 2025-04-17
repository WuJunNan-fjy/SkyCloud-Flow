package com.wjn.ledger.service.impl;

import java.util.List;
import com.wjn.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.wjn.ledger.mapper.TLedCategoryMapper;
import com.wjn.ledger.domain.TLedCategory;
import com.wjn.ledger.service.ITLedCategoryService;

import javax.annotation.Resource;

/**
 * 记账分类Service业务层处理
 *
 * @author wjn
 * @date 2025-04-17
 */
@Service
public class TLedCategoryServiceImpl implements ITLedCategoryService
{
    @Resource
    private TLedCategoryMapper tLedCategoryMapper;

    /**
     * 查询记账分类
     *
     * @param categoryId 记账分类主键
     * @return 记账分类
     */
    @Override
    public TLedCategory selectTLedCategoryByCategoryId(Long categoryId)
    {
        return tLedCategoryMapper.selectTLedCategoryByCategoryId(categoryId);
    }

    /**
     * 查询记账分类列表
     *
     * @param tLedCategory 记账分类
     * @return 记账分类
     */
    @Override
    public List<TLedCategory> selectTLedCategoryList(TLedCategory tLedCategory)
    {
        return tLedCategoryMapper.selectTLedCategoryList(tLedCategory);
    }

    /**
     * 新增记账分类
     *
     * @param tLedCategory 记账分类
     * @return 结果
     */
    @Override
    public int insertTLedCategory(TLedCategory tLedCategory)
    {
        tLedCategory.setCreateTime(DateUtils.getNowDate());
        return tLedCategoryMapper.insertTLedCategory(tLedCategory);
    }

    /**
     * 修改记账分类
     *
     * @param tLedCategory 记账分类
     * @return 结果
     */
    @Override
    public int updateTLedCategory(TLedCategory tLedCategory)
    {
        tLedCategory.setUpdateTime(DateUtils.getNowDate());
        return tLedCategoryMapper.updateTLedCategory(tLedCategory);
    }

    /**
     * 批量删除记账分类
     *
     * @param categoryIds 需要删除的记账分类主键
     * @return 结果
     */
    @Override
    public int deleteTLedCategoryByCategoryIds(Long[] categoryIds)
    {
        return tLedCategoryMapper.deleteTLedCategoryByCategoryIds(categoryIds);
    }

    /**
     * 删除记账分类信息
     *
     * @param categoryId 记账分类主键
     * @return 结果
     */
    @Override
    public int deleteTLedCategoryByCategoryId(Long categoryId)
    {
        return tLedCategoryMapper.deleteTLedCategoryByCategoryId(categoryId);
    }
}
