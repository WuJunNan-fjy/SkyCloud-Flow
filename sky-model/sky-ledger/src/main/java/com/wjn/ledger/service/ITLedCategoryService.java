package com.wjn.ledger.service;

import java.util.List;
import com.wjn.ledger.domain.TLedCategory;

/**
 * 记账分类Service接口
 * 
 * @author wjn
 * @date 2025-04-17
 */
public interface ITLedCategoryService 
{
    /**
     * 查询记账分类
     * 
     * @param categoryId 记账分类主键
     * @return 记账分类
     */
    public TLedCategory selectTLedCategoryByCategoryId(Long categoryId);

    /**
     * 查询记账分类列表
     * 
     * @param tLedCategory 记账分类
     * @return 记账分类集合
     */
    public List<TLedCategory> selectTLedCategoryList(TLedCategory tLedCategory);

    /**
     * 新增记账分类
     * 
     * @param tLedCategory 记账分类
     * @return 结果
     */
    public int insertTLedCategory(TLedCategory tLedCategory);

    /**
     * 修改记账分类
     * 
     * @param tLedCategory 记账分类
     * @return 结果
     */
    public int updateTLedCategory(TLedCategory tLedCategory);

    /**
     * 批量删除记账分类
     * 
     * @param categoryIds 需要删除的记账分类主键集合
     * @return 结果
     */
    public int deleteTLedCategoryByCategoryIds(Long[] categoryIds);

    /**
     * 删除记账分类信息
     * 
     * @param categoryId 记账分类主键
     * @return 结果
     */
    public int deleteTLedCategoryByCategoryId(Long categoryId);
}
