package com.wjn.ledger.mapper;

import java.util.List;
import com.wjn.ledger.domain.TLedCategory;

/**
 * 记账分类Mapper接口
 * 
 * @author wjn
 * @date 2025-04-17
 */
public interface TLedCategoryMapper 
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
     * 删除记账分类
     * 
     * @param categoryId 记账分类主键
     * @return 结果
     */
    public int deleteTLedCategoryByCategoryId(Long categoryId);

    /**
     * 批量删除记账分类
     * 
     * @param categoryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTLedCategoryByCategoryIds(Long[] categoryIds);
}
