package com.wjn.flow.common.convert;

import com.wjn.flow.domain.dto.flow.FlwFormDO;
import com.wjn.flow.domain.vo.form.FlwFormSaveReqVO;
import org.mapstruct.factory.Mappers;

/**
 * @description: 流程表单保存对象转换成流程分类DO对象
 * @author: wjn
 * @create: 2024-12-17 10:00
 * @version:v1.0
 */
public interface FlwFormConvert {


    FlwFormConvert INSTANCE = Mappers.getMapper(FlwFormConvert.class);

    // 方法定义：将 VO 转换为 DO
    FlwFormDO convertToDO(FlwFormSaveReqVO vo);


    // 需要处理转换逻辑
    default FlwFormDO createConvertToDO(FlwFormSaveReqVO vo) {
        return null;
    }

    // 需要处理转换逻辑
    default FlwFormDO updateConvertToDO(FlwFormSaveReqVO vo) {
        return null;
    }
}
