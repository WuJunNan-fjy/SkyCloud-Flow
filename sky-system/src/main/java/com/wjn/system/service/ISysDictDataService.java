package com.wjn.system.service;

import java.util.List;
import com.wjn.common.core.domain.entity.SysDictData;
import org.springframework.lang.Nullable;

/**
 * 字典 业务层
 *
 * @author wjn
 */
public interface ISysDictDataService
{
    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    public List<SysDictData> selectDictDataList(SysDictData dictData);

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    public String selectDictLabel(String dictType, String dictValue);

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    public SysDictData selectDictDataById(Long dictCode);

    /**
     * 批量删除字典数据信息
     *
     * @param dictCodes 需要删除的字典数据ID
     */
    public void deleteDictDataByIds(Long[] dictCodes);

    /**
     * 新增保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    public int insertDictData(SysDictData dictData);

    /**
     * 修改保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    public int updateDictData(SysDictData dictData);

    /**
     * 功能描述: 获得全部的字典数据列表
     * @author wjn
     * @create 2024/12/19 星期四 22:55
     * @param: [status, o]
     * @return java.util.List<com.wjn.common.core.domain.entity.SysDictData>
     */
    List<SysDictData> getDictDataList(@Nullable Integer status, @Nullable String dictType);
}
