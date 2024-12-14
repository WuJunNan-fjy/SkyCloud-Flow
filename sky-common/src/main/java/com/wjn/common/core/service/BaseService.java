package com.wjn.common.core.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

public interface BaseService<T> {

	/**
	 * 根据Example条件进行查询
	 *
	 * @param example
	 * @return
	 */
	List<T> queryByExample(Example example);

	/**
	 * 根据Example条件进行查询总数
	 *
	 * @param example
	 * @return
	 */
	public int queryCountByExample(Example example);

	/**
	 * @Description: 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
	 * @Param: id 主键id
	 * @return: pojo单个实体对象
	 **/
	T queryByPrimaryKey(Object key);

	/**
	 * @Description: 查询所有
	 * @Param: 无
	 * @return: java.util.List<T> list对象
	 **/
	List<T> queryAll();

	/**
	 * @Description: 根据实体中的属性值进行查询，查询条件使用等号
	 * @Param: param 条件
	 * @return: java.util.List<T> 返回多条记录
	 **/
	List<T> queryListByWhere(T pojo);

	/**
	 * @Description: 根据实体中的属性查询总数，查询条件使用等号
	 * @Param: pojo 实体对象
	 * @return: 记录数
	 **/
	int queryCount(T pojo);

	/**
	 * @Description: 查询一条记录 ,通常条件是给一个实体对象，这个对象只有初始了一个id值(其它唯一值的字段也可以）
	 * @Param: pojo 条件对象,它的属性必须与表字段对应（可以用@Transient忽略字段），否则会报错，并且id必须唯一
	 * @return: 返回单个pojo实体对象
	 **/
	T queryOne(T pojo);

	/**
	 * @Description: 分页查询
	 * @Param: pojo 条件,根据实体中的属性查询，查询条件使用等号
	 * @Param: pageNo 当前页号
	 * @Param: pageSize 页大小
	 * @return:
	 **/
	PageInfo<T> queryPageListByWhere(T pojo, int pageNo, int pageSize);

	/**
	 * @Description: 通过字段分页查询
	 * @Param:
	 * @Param:
	 * @return:
	 **/
	PageInfo<T> queryPageListByField(Integer pageNo, Integer pageSize, Sqls where, String orderByField,
			String ascOrDesc, String... fields);

	/**
	 * 获取子类泛型类型
	 *
	 * @return
	 */
	Class<T> getTypeArguement();

	/**
	 * @Description: 插入一个pojo对象,null的属性也会保存，不会使用数据库默认值
	 * @Param: pojo 实体对象
	 * @return: 如果数值大于0则成功，null为失败
	 **/
	int save(T pojo);

	/**
	 * @Description: 插入非空字段,null的属性不会保存，会使用数据库默认值
	 * @Param: pojo 实体对象
	 * @return: 如果数值大于0则成功，null为失败
	 **/
	int saveSelect(T pojo);

	/**
	 * @Description: 批量插入对象，非空字段,null的属性不会保存，会使用数据库默认值
	 * @Param: list 实体对象集合
	 * @return: 如果数值大于0 则成功，null为失败
	 **/
	int batchSave(List<T> list);

	/**
	 * 批量插入
	 *
	 * @param list
	 * @return
	 */
	public int batchInsert(List<T> list);

	/**
	 * @Description: 根据主键更新实体全部字段，null值会被更新
	 * @Param: pojo 实体对象
	 * @return: 如果数值大于0则成功，null为失败
	 **/
	int update(T pojo);

	/**
	 * @Description: 根据主键更新属性不为null的值
	 * @Param: pojo 实体对象
	 * @return: 如果数值大于0 则成功，null为失败
	 **/
	int updateSelective(T pojo);

	/**
	 * @Description: 根据主键删除记录
	 * @Param: id 主键id
	 * @return: 删除的记录数量,如果数值大于0 则成功，null为失败
	 **/
	int deleteByPrimaryKey(T key);

	/**
	 * @Description: 根据主键的list集合,批量删除对象
	 * @Param: clazz 实体对象
	 * @Param: ids 主键的list集合
	 * @return: 删除的记录数量,如果数值大于0 则成功，null为失败
	 **/
	int deleteByIds(Class<T> clazz, List<Object> ids);

	/**
	 *
	 * @Description:ids 需要删除的数据ID逗号“,”隔开 @param ids @return int @throws
	 */
	int deleteByIds(String ids);

	/**
	 * @Description: 根据实体属性作为条件进行删除，查询条件使用等号
	 * @Param: pojo 实体对象
	 * @return: 删除的记录数量,如果数值大于0 则成功，null为失败
	 **/
	int deleteByWhere(T pojo);

	/**
	 * @Description: 批量删除对象，根据实体属性作为条件进行删除，查询条件使用等号
	 * @Param:
	 * @Param:
	 * @return:
	 **/
	int batchDelete(List<T> list);

	/**
	 * @Description: 根据Example条件进行删除
	 * @Param: example 查询条件对象
	 * @return: 删除的记录数量,如果数值大于0 则成功，null为失败
	 **/
	int deleteByExample(Object example);

}
