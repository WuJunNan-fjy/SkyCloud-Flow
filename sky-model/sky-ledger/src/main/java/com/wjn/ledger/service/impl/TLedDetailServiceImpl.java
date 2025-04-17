package com.wjn.ledger.service.impl;

import java.util.List;
import com.wjn.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.wjn.ledger.mapper.TLedDetailMapper;
import com.wjn.ledger.domain.TLedDetail;
import com.wjn.ledger.service.ITLedDetailService;

import javax.annotation.Resource;

/**
 * 记账明细Service业务层处理
 *
 * @author wjn
 * @date 2025-04-17
 */
@Service
public class TLedDetailServiceImpl implements ITLedDetailService
{
    @Resource
    private TLedDetailMapper tLedDetailMapper;

    /**
     * 查询记账明细
     *
     * @param detailId 记账明细主键
     * @return 记账明细
     */
    @Override
    public TLedDetail selectTLedDetailByDetailId(Long detailId)
    {
        return tLedDetailMapper.selectTLedDetailByDetailId(detailId);
    }

    /**
     * 查询记账明细列表
     *
     * @param tLedDetail 记账明细
     * @return 记账明细
     */
    @Override
    public List<TLedDetail> selectTLedDetailList(TLedDetail tLedDetail)
    {
        return tLedDetailMapper.selectTLedDetailList(tLedDetail);
    }

    /**
     * 新增记账明细
     *
     * @param tLedDetail 记账明细
     * @return 结果
     */
    @Override
    public int insertTLedDetail(TLedDetail tLedDetail)
    {
        tLedDetail.setCreateTime(DateUtils.getNowDate());
        return tLedDetailMapper.insertTLedDetail(tLedDetail);
    }

    /**
     * 修改记账明细
     *
     * @param tLedDetail 记账明细
     * @return 结果
     */
    @Override
    public int updateTLedDetail(TLedDetail tLedDetail)
    {
        tLedDetail.setUpdateTime(DateUtils.getNowDate());
        return tLedDetailMapper.updateTLedDetail(tLedDetail);
    }

    /**
     * 批量删除记账明细
     *
     * @param detailIds 需要删除的记账明细主键
     * @return 结果
     */
    @Override
    public int deleteTLedDetailByDetailIds(Long[] detailIds)
    {
        return tLedDetailMapper.deleteTLedDetailByDetailIds(detailIds);
    }

    /**
     * 删除记账明细信息
     *
     * @param detailId 记账明细主键
     * @return 结果
     */
    @Override
    public int deleteTLedDetailByDetailId(Long detailId)
    {
        return tLedDetailMapper.deleteTLedDetailByDetailId(detailId);
    }
}
