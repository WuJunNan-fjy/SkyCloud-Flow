package com.wjn.common.core.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Id;

import com.wjn.common.core.crudDao.BaseMapper;
import com.wjn.common.core.page.BasePage;
import com.wjn.common.core.service.BaseService;
import com.wjn.common.exception.base.BaseException;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

public class BaseServiceImpl<T> extends BasePage implements BaseService<T> {
    // 注入mapper
    @Autowired
    private BaseMapper<T> baseMapper;

    // 缓存子类泛型类型
    private Class<T> cache=null;

    /**
     * 根据Example条件进行查询
     * @param example
     * @return
     */
    public List<T> queryByExample(Example example){
        return this.baseMapper.selectByExample(example);
    }
    /**
     * 根据Example条件进行查询总数
     * @param example
     * @return
     */
    public int queryCountByExample(Example example){
        return this.baseMapper.selectCountByExample(example);
    }

    /**
     * @Description: 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     * @Param: id  主键id
     * @return: pojo单个实体对象
     **/
    public T queryByPrimaryKey( Object key){
        return this.baseMapper.selectByPrimaryKey(key);
    }

    /**
     * @Description: 查询所有对象
     * @Param: 无
     * @return: java.util.List<T> list对象
     **/
    public List<T> queryAll(){
        return this.baseMapper.selectAll();
    }


    /**
     * @Description: 根据实体中的属性值进行查询，查询条件使用等号
     * @Param: param 条件
     * @return: java.util.List<T> 返回多条记录
     **/
    public List<T> queryListByWhere(T pojo){
        return this.baseMapper.select(pojo);
    }


    /**
     * @Description: 根据实体中的属性查询总数，查询条件使用等号
     * @Param: pojo 实体对象
     * @return: 记录数
     **/
    public int queryCount(T pojo){
        return this.baseMapper.selectCount(pojo);
    }


    /**
     * @Description: 查询一条记录
     *,通常条件是给一个实体对象，这个对象只有初始了一个id值(其它唯一值的字段也可以）
     * @Param: pojo  条件对象,它的属性必须与表字段对应（可以用@Transient忽略字段），否则会报错，并且id必须唯一
     * @return: 返回单个pojo实体对象
     **/
    public T queryOne(T pojo){
        return this.baseMapper.selectOne(pojo);
    }

    /**
     * @Description: 分页查询
     * @Param: pojo 条件,根据实体中的属性查询，查询条件使用等号
     * @Param: pageNo 当前页号
     * @Param: pageSize 页大小
     * @return:
     **/
    public PageInfo<T> queryPageListByWhere(T pojo, int pageNo, int pageSize){
        PageHelper.startPage(pageNo, pageSize);
        List<T> list =  this.baseMapper.select(pojo);
        return new PageInfo<T>(list);

    }

    /**
     * @Description: 通过字段分页查询
     * @Param:
     * @Param:
     * @return:
     **/
    public PageInfo<T>  queryPageListByField(Integer pageNo, Integer pageSize, Sqls whereSqls, String orderByField, String ascOrDesc, String ...fields){
        return new PageInfo<T>(queryByFiledBase(pageNo,pageSize,whereSqls,orderByField, ascOrDesc, fields));
    }

   /**
    * @Description: 子函数：通用分页字段查询
    * @Param: pageNo 当前页号
    * @Param: pageSize 页大小
    * @Param: whereSqls   sql条件,where的内容拿出来进行动态sql拼接
    * @Param: orderByField  单个排序字段
    * @Param: fields  可变长度的数组等同于String [] str={}
    * @return: java.util.List<T> list 记录集合
    **/
    private List<T> queryByFiledBase(Integer pageNo,Integer pageSize,Sqls whereSqls,String orderByField, String ascOrDesc, String ...fields){
        Example.Builder builder=null;
        if(null==fields||fields.length==0){
            //查询所有
            builder = Example.builder(getTypeArguement());

        }else{
            //查询指定字段,where的内容拿出来进行动态sql拼接
            builder= Example.builder(getTypeArguement()).select(fields);
        }
        if(whereSqls!=null){
            builder=builder.where(whereSqls);
        }

        if(orderByField!=null){
            if (ascOrDesc.toUpperCase().equals("DESC")){
                builder= builder
                        .orderByDesc(orderByField);
            }else{
                builder= builder
                        .orderByAsc(orderByField);
            }
        }
        Example example=builder.build();

        if(pageNo!=null&&pageSize!=null) {
            // 分页插件
            PageHelper.startPage(pageNo, pageSize);
        }
        List list = this.baseMapper.selectByExample(example);
        return  list;
    }

    /**
     * 获取子类泛型类型
     * @return
     */
    public Class<T> getTypeArguement() {
        if(cache==null)
            cache= (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return  cache;
    }

    /**
     * @Description: 插入一个pojo对象,null的属性也会保存，不会使用数据库默认值
     * @Param: pojo  实体对象
     * @return: 如果数值大于0则成功，null为失败
     **/
    public int save(T pojo){
        return this.baseMapper.insert(pojo);
    }

    /**
     * @Description: 插入非空字段,null的属性不会保存，会使用数据库默认值
     * @Param: pojo 实体对象
     * @return: 如果数值大于0则成功，null为失败
     **/
    public int saveSelect(T pojo){
        return this.baseMapper.insertSelective(pojo);
    }

    /**
     * @Description: 批量插入对象，非空字段,null的属性不会保存，会使用数据库默认值
     * @Param:  list 实体对象集合
     * @return: 如果数值大于0 则成功，null为失败
     **/
    public int batchSave(List<T> list) {
        int result = 0;
        Object id = null;
        try {
            for (T record : list) {
                int count = 0;
                for(Field f :getTypeArguement().getDeclaredFields()){
                    if (f.isAnnotationPresent(Id.class)) {
                        Field sysid = record.getClass().getDeclaredField(f.getName());
                        sysid.setAccessible(true);
                        // 这块一定要传入record 而不是 record.getClass().newInstance()
                        id = sysid.get(record);
                        break;
                    }
                }
                if(baseMapper.existsWithPrimaryKey(id)){
                    count = baseMapper.updateByPrimaryKeySelective(record);
                }else{
                    count = baseMapper.insertSelective(record);
                }
                result += count;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return result;
    }
    /**
     * 批量插入
     * @param list
     * @return
     */
    public int batchInsert(List<T> list) {
    	int i = baseMapper.insertList(list);
		return i;
    }

    /**
     * @Description: 根据主键更新实体全部字段，null值会被更新
     * @Param: pojo  实体对象
     * @return: 如果数值大于0则成功，null为失败
     **/
    public int update(T pojo){
        return this.baseMapper.updateByPrimaryKey(pojo);
    }

    /**
     * @Description: 根据主键更新属性不为null的值
     * @Param: pojo 实体对象
     * @return:  如果数值大于0 则成功，null为失败
     **/
    public int updateSelective(T pojo){
        return this.baseMapper.updateByPrimaryKeySelective(pojo);
    }

    /**
     * @Description: 根据主键删除记录
     * @Param: id 主键id
     * @return: 删除的记录数量,如果数值大于0 则成功，null为失败
     **/
    public int deleteByPrimaryKey(T key){
        return this.baseMapper.deleteByPrimaryKey(key);
    }

    /**
     * @Description: 根据主键的list集合,批量删除对象
     * @Param: clazz 实体对象
     * @Param: ids 主键的list集合
     * @return: 删除的记录数量,如果数值大于0 则成功，null为失败
     **/
    public int deleteByIds(Class<T> clazz,List<Object> ids){
        // where条件
        Example example = new Example(clazz);
        example.createCriteria().andIn("id", ids);
        return this.baseMapper.deleteByExample(example);
        /*
           等效于where id in (#{ids})
         */
    }

    /**
     * @Description: 根据实体属性作为条件进行删除，查询条件使用等号
     * @Param: pojo 实体对象
     * @return: 删除的记录数量,如果数值大于0 则成功，null为失败
     **/
    public int  deleteByWhere(T pojo){
        return this.baseMapper.delete(pojo);
    }

    /**
     * @Description: 批量删除对象，根据实体属性作为条件进行删除，查询条件使用等号
     * @Param:
     * @Param:
     * @return:
     **/
    public int batchDelete(List<T> list) {
        int result = 0;
        for (T record : list) {
            int count = baseMapper.delete(record);
            if (count < 1) {
                throw new BaseException("删除数据失败!");
            }
            result += count;
        }
        return result;
    }

    /**
     * @Description: 根据Example条件进行删除
     * @Param:  example 查询条件对象
     * @return: 删除的记录数量,如果数值大于0 则成功，null为失败
     **/
    public int deleteByExample(Object example){
        return this.baseMapper.deleteByExample(example);
    }
    /**
     *
     * @Description: 条件进行删除，ids 需要删除的数据ID逗号“,”隔开
     * @param ids
     * @return int
     * @throws
     */
    public int deleteByIds(String ids){
        return this.baseMapper.deleteByIds(ids);
    }

}
