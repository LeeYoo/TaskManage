package com.base.dao;
import java.util.LinkedHashMap;

import com.paging.entity.QueryResult;
import com.user.entity.User;
//【公共接口】
public interface DAO {
	
	/**
	 * 【保存实体】
	 */
	public void save(Object entity);
	/**
	 * 【更新实体】
	 */
	public void update(Object entity);
	/**
	 *【删除实体1】——根据实体id
	 */
	public <T> void delete(Class<T> entityClass ,Object entityid);
	/**
	 *【删除实体2】——根据实体id的数组
	 */
	public <T> void delete(Class<T> entityClass ,Object[] entityids);
	/**
	 * 【获取实体的总记录数】
	 * @return	总记录数
	 */
	public <T> long getCount(Class<T> entityClass);
	/**
	 * 【查找实体】
	 * ——给定一个实体类，就返回这个实体类的对象(不再需要类型转化)，
	 * 我们就需要使用到泛型!
	 */
	public <T> T find(Class<T> entityClass ,Object entityid);
	public User find(String username,String password,Integer deptno,Integer identity);
	/**
	 * 【获取分页数据的封装】
	 * @param entityClass 要操作的是哪个实体类
	 * @param firstindex 从什么位置开始
	 * @param maxresult 需要获取的总记录数
	 * @param whereStr 条件查询字符串
	 * @param queryparams 条件查询所需位置参数
	 * @param orderby 排序对象
	 */
	//【分页+条件+排序】
	public <T> QueryResult<T> getScrollData(Class<T> entityClass ,int firstindex ,int maxresult 
			,String whereStr ,Object[] queryparams ,LinkedHashMap<String, String> orderby);
	//【分页+条件】
	public <T> QueryResult<T> getScrollData(Class<T> entityClass ,int firstindex ,int maxresult 
			,String whereStr ,Object[] queryparams);
	//【分页+排序】
	public <T> QueryResult<T> getScrollData(Class<T> entityClass ,int firstindex ,int maxresult 
			,LinkedHashMap<String, String> orderby);
	//【分页】
	public <T> QueryResult<T> getScrollData(Class<T> entityClass ,int firstindex ,int maxresult);
	//【获取所有】
	public <T> QueryResult<T> getScrollData(Class<T> entityClass);
	//【获取所有2】
	public <T> QueryResult<T> getScrollData_2(Class<T> entityClass,
			int firstindex, int maxresult ,String whereStr ,Object[] queryparams ,LinkedHashMap<String, String> orderby);
	public <T> QueryResult<T> getScrollData_3(Class<T> entityClass);
	//【条件获取所有】
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,String whereStr,Object[] queryparams);
}
