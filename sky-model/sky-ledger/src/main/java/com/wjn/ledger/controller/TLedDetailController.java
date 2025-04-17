package com.wjn.ledger.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wjn.common.annotation.Log;
import com.wjn.common.core.controller.BaseController;
import com.wjn.common.core.domain.AjaxResult;
import com.wjn.common.enums.BusinessType;
import com.wjn.ledger.domain.TLedDetail;
import com.wjn.ledger.service.ITLedDetailService;
import com.wjn.common.utils.poi.ExcelUtil;
import com.wjn.common.core.page.TableDataInfo;

/**
 * 记账明细Controller
 *
 * @author wjn
 * @date 2025-04-17
 */
@RestController
@RequestMapping("/ledger/detail")
public class TLedDetailController extends BaseController
{
    @Resource
    private ITLedDetailService tLedDetailService;

    /**
     * 查询记账明细列表
     */
    // @PreAuthorize("@ss.hasPermission('ledger:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(TLedDetail tLedDetail)
    {
        startPage();
        List<TLedDetail> list = tLedDetailService.selectTLedDetailList(tLedDetail);
        return getDataTable(list);
    }

    /**
     * 导出记账明细列表
     */
    // @PreAuthorize("@ss.hasPermission('ledger:detail:export')")
    @Log(title = "记账明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TLedDetail tLedDetail)
    {
        List<TLedDetail> list = tLedDetailService.selectTLedDetailList(tLedDetail);
        ExcelUtil<TLedDetail> util = new ExcelUtil<TLedDetail>(TLedDetail.class);
        util.exportExcel(response, list, "记账明细数据");
    }

    /**
     * 获取记账明细详细信息
     */
    // @PreAuthorize("@ss.hasPermission('ledger:detail:query')")
    @GetMapping(value = "/{detailId}")
    public AjaxResult getInfo(@PathVariable("detailId") Long detailId)
    {
        return success(tLedDetailService.selectTLedDetailByDetailId(detailId));
    }

    /**
     * 新增记账明细
     */
    // @PreAuthorize("@ss.hasPermission('ledger:detail:add')")
    @Log(title = "记账明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TLedDetail tLedDetail)
    {
        return toAjax(tLedDetailService.insertTLedDetail(tLedDetail));
    }

    /**
     * 修改记账明细
     */
    // @PreAuthorize("@ss.hasPermission('ledger:detail:edit')")
    @Log(title = "记账明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TLedDetail tLedDetail)
    {
        return toAjax(tLedDetailService.updateTLedDetail(tLedDetail));
    }

    /**
     * 删除记账明细
     */
    // @PreAuthorize("@ss.hasPermission('ledger:detail:remove')")
    @Log(title = "记账明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{detailIds}")
    public AjaxResult remove(@PathVariable Long[] detailIds)
    {
        return toAjax(tLedDetailService.deleteTLedDetailByDetailIds(detailIds));
    }
}
