package com.base.dao;
import java.util.LinkedHashMap;

import com.paging.entity.QueryResult;
import com.user.entity.User;
//�������ӿڡ�
public interface DAO {
	
	/**
	 * ������ʵ�塿
	 */
	public void save(Object entity);
	/**
	 * ������ʵ�塿
	 */
	public void update(Object entity);
	/**
	 *��ɾ��ʵ��1����������ʵ��id
	 */
	public <T> void delete(Class<T> entityClass ,Object entityid);
	/**
	 *��ɾ��ʵ��2����������ʵ��id������
	 */
	public <T> void delete(Class<T> entityClass ,Object[] entityids);
	/**
	 * ����ȡʵ����ܼ�¼����
	 * @return	�ܼ�¼��
	 */
	public <T> long getCount(Class<T> entityClass);
	/**
	 * ������ʵ�塿
	 * ��������һ��ʵ���࣬�ͷ������ʵ����Ķ���(������Ҫ����ת��)��
	 * ���Ǿ���Ҫʹ�õ�����!
	 */
	public <T> T find(Class<T> entityClass ,Object entityid);
	public User find(String username,String password,Integer deptno,Integer identity);
	/**
	 * ����ȡ��ҳ���ݵķ�װ��
	 * @param entityClass Ҫ���������ĸ�ʵ����
	 * @param firstindex ��ʲôλ�ÿ�ʼ
	 * @param maxresult ��Ҫ��ȡ���ܼ�¼��
	 * @param whereStr ������ѯ�ַ���
	 * @param queryparams ������ѯ����λ�ò���
	 * @param orderby �������
	 */
	//����ҳ+����+����
	public <T> QueryResult<T> getScrollData(Class<T> entityClass ,int firstindex ,int maxresult 
			,String whereStr ,Object[] queryparams ,LinkedHashMap<String, String> orderby);
	//����ҳ+������
	public <T> QueryResult<T> getScrollData(Class<T> entityClass ,int firstindex ,int maxresult 
			,String whereStr ,Object[] queryparams);
	//����ҳ+����
	public <T> QueryResult<T> getScrollData(Class<T> entityClass ,int firstindex ,int maxresult 
			,LinkedHashMap<String, String> orderby);
	//����ҳ��
	public <T> QueryResult<T> getScrollData(Class<T> entityClass ,int firstindex ,int maxresult);
	//����ȡ���С�
	public <T> QueryResult<T> getScrollData(Class<T> entityClass);
	//����ȡ����2��
	public <T> QueryResult<T> getScrollData_2(Class<T> entityClass,
			int firstindex, int maxresult ,String whereStr ,Object[] queryparams ,LinkedHashMap<String, String> orderby);
	public <T> QueryResult<T> getScrollData_3(Class<T> entityClass);
	//��������ȡ���С�
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,String whereStr,Object[] queryparams);
}
