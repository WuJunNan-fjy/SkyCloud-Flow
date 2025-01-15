package com.wjn.flow.common.convert;

import com.alibaba.fastjson2.JSON;
import com.wjn.common.utils.SnowFlake;
import com.wjn.flow.domain.dto.flow.FlwFormDO;
import com.wjn.flow.domain.vo.form.FlwFormRespVO;
import com.wjn.flow.domain.vo.form.FlwFormSaveReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @description: 流程表单保存对象转换成流程分类DO对象
 * @author: wjn
 * @create: 2024-12-17 10:00
 * @version:v1.0
 */
@Mapper
public interface FlwFormConvert {


    FlwFormConvert INSTANCE = Mappers.getMapper(FlwFormConvert.class);

    // 方法定义：将 保存表单VO 转换为 DO
    FlwFormDO convertToDO(FlwFormSaveReqVO vo);

    // 方法定义：将 DO 转换为 响应VO对象
    FlwFormRespVO convertToResVO(FlwFormDO formDO);


    // 创建表单转换为DO
    default FlwFormDO createConvertToDO(FlwFormSaveReqVO vo) {
        FlwFormDO formDO = convertToDO(vo);
        formDO.setId(SnowFlake.getId());
        formDO.setFields(JSON.toJSONString(vo.getFieldList()));
        return formDO;
    }

    // 修改表单转换为DO
    default FlwFormDO updateConvertToDO(FlwFormSaveReqVO vo) {
        FlwFormDO formDO = convertToDO(vo);
        formDO.setFields(JSON.toJSONString(vo.getFieldList()));
        return formDO;
    }

    // 详情-DO转换为响应对象
    default FlwFormRespVO detailConvertToResVO(FlwFormDO formDO) {
        FlwFormRespVO formRespVO = convertToResVO(formDO);
        if (formDO != null) {
            formRespVO.setFieldList(JSON.parseArray(formDO.getFields(), String.class));
        }
        return formRespVO;
    }
}
