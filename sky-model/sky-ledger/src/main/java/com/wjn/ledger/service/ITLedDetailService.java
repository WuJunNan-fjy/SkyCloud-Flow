package com.wjn.ledger.service;

import java.util.List;
import com.wjn.ledger.domain.TLedDetail;

/**
 * 记账明细Service接口
 * 
 * @author wjn
 * @date 2025-04-17
 */
public interface ITLedDetailService 
{
    /**
     * 查询记账明细
     * 
     * @param detailId 记账明细主键
     * @return 记账明细
     */
    public TLedDetail selectTLedDetailByDetailId(Long detailId);

    /**
     * 查询记账明细列表
     * 
     * @param tLedDetail 记账明细
     * @return 记账明细集合
     */
    public List<TLedDetail> selectTLedDetailList(TLedDetail tLedDetail);

    /**
     * 新增记账明细
     * 
     * @param tLedDetail 记账明细
     * @return 结果
     */
    public int insertTLedDetail(TLedDetail tLedDetail);

    /**
     * 修改记账明细
     * 
     * @param tLedDetail 记账明细
     * @return 结果
     */
    public int updateTLedDetail(TLedDetail tLedDetail);

    /**
     * 批量删除记账明细
     * 
     * @param detailIds 需要删除的记账明细主键集合
     * @return 结果
     */
    public int deleteTLedDetailByDetailIds(Long[] detailIds);

    /**
     * 删除记账明细信息
     * 
     * @param detailId 记账明细主键
     * @return 结果
     */
    public int deleteTLedDetailByDetailId(Long detailId);
}
