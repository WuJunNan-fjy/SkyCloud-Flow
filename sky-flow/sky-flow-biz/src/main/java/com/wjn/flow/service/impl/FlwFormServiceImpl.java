package com.wjn.flow.service.impl;

import com.wjn.common.core.service.impl.BaseServiceImpl;
import com.wjn.flow.common.convert.FlwFormConvert;
import com.wjn.flow.domain.dto.flow.FlwFormDO;
import com.wjn.flow.domain.vo.form.FlwFormPageReqVO;
import com.wjn.flow.domain.vo.form.FlwFormRespVO;
import com.wjn.flow.domain.vo.form.FlwFormSaveReqVO;
import com.wjn.flow.mapper.FlwFormMapper;
import com.wjn.flow.service.FlwFormService;
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
public class FlwFormServiceImpl extends BaseServiceImpl<FlwFormDO> implements FlwFormService {

    @Resource
    private FlwFormMapper formMapper;


    /**
     * 功能描述: 创建流程分类
     * @author wjn
     * @create 2024/12/7 星期六 9:47
     * @param: [createReqVO]
     * @return int
     */
    @Override
    public int createForm(FlwFormSaveReqVO createReqVO) {
        FlwFormDO flwFormDO = FlwFormConvert.INSTANCE.createConvertToDO(createReqVO);
        return formMapper.insert(flwFormDO);
    }

    /**
     * 功能描述: 更新流程分类
     * @author wjn
     * @create 2024/12/7 星期六 9:47
     * @param: [updateReqVO]
     * @return int
     */
    @Override
    public int updateForm(FlwFormSaveReqVO updateReqVO){
        FlwFormDO flwFormDO = FlwFormConvert.INSTANCE.updateConvertToDO(updateReqVO);
        return formMapper.updateByPrimaryKeySelective(flwFormDO);
    }

    /**
     * 功能描述: 删除流程分类
     * @author wjn
     * @create 2024/12/7 星期六 9:47
     * @param: [idStr]
     * @return int
     */
    @Override
    public int deleteForm(String idStr){
        return formMapper.deleteByIds(idStr);
    }

    /**
     * 功能描述: 获得流程分类详情
     * @author wjn
     * @create 2024/12/7 星期六 9:48
     * @param: [id]
     * @return java.lang.String
     */
    @Override
    public FlwFormRespVO getForm(Long id){
        FlwFormDO formDO = formMapper.selectByPrimaryKey(id);
        FlwFormRespVO formRespVO = FlwFormConvert.INSTANCE.detailConvertToResVO(formDO);
        return formRespVO;
    }

    /**
     * 功能描述: 获得流程分类分页
     * @author wjn
     * @create 2024/12/7 星期六 9:48
     * @param: [pageReqVO]
     * @return java.util.List<com.wjn.flow.domin.vo.form.FlwFormRespVO>
     */
    @Override
    public List<FlwFormDO> getFormPage(FlwFormPageReqVO pageReqVO){
        List<FlwFormDO> pageResult = formMapper.queryFormPage(pageReqVO);
        return pageResult;
    }
}
