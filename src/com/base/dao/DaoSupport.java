package com.base.dao;
import java.util.LinkedHashMap;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.paging.entity.QueryResult;
import com.user.entity.User;
//�������ࡿ���ܹ���������ֻ��ͨ��ҵ��bean���̳���������ʵ��DAO�ӿ�
@Transactional
public abstract class DaoSupport implements DAO{
	
	//��һ������Spring������ע��һ���������������ע��ʵ�����������������ҵ�񷽷�ִ��֮ǰ�����񣬽���֮��ر�����
	@PersistenceContext protected EntityManager em;
	
	public void save(Object entity) {	
		em.persist(entity);
	}
	
	public void update(Object entity) {
		//������״̬��ʵ��beanͬ�������ݿ�
		em.merge(entity);
	}

	public <T> void delete(Class<T> entityClass, Object entityid) {
		//ֱ�ӵ��ü���
		delete(entityClass, new Object[] {entityid});
	}

	public <T> void delete(Class<T> entityClass, Object[] entityids) {
		for( Object id : entityids){
			//ɾ��ʵ�壬����Ҫ�õ�ʵ����йܶ���(������ʵ�����ͣ�ʵ��id)
			em.remove(em.getReference(entityClass , id));
		}
	}
	
	//����Ҫ������Ϊ,����Ĵ�����Ϊ�ǲ���������
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> T find(Class<T> entityClass, Object entityid) {
		return em.find(entityClass, entityid);
	}
	public User find(String username,String password,Integer deptno,Integer identity) {
		User user = new User();
		user = (User)em.createQuery("select o from User o where o.username=?1 and o.password=?2 and o.dept.deptID=?3 and o.identity=?4")
				.setParameter(1,username).setParameter(2, password)
				.setParameter(3, deptno).setParameter(4, identity).getSingleResult();
		return user;
	}
	
													/*ʵ�֡���ҳ���ܡ������������*/
	/**
	 * ��ʵ�ַ�ҳ��װ�ķ�����
	 * ����ҳ+����+����
	 */
	@Transactional(readOnly=true ,propagation = Propagation.NOT_SUPPORTED)	//����Ҫ����
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult ,String whereStr ,Object[] queryparams ,LinkedHashMap<String, String> orderby) {
		QueryResult<T> qr = new QueryResult<T>();		//������ѯ�����Ķ���
		String entityName = getEntityName(entityClass);		//��ȡ��ʵ��ʵ������
		//return (Long)em.createQuery("select count(*) from "+getEntityName(entityClass)+" e").getSingleResult();
		String sql = "select o from "+entityName+" o "+(whereStr == null ? "" : "where "+ whereStr)+buildOrderby(orderby);
		Query query = em.createQuery(sql);
		setQueryParameter(query, queryparams);
		//3.����Ҫ��ȡ�Ŀ�ʼ����ֵ��ÿҳҪ��ʾ��¼�������ǵ��з������أ�������Ҫ��ҳ��ʱ����������һ��Լ��������
		if(firstindex != -1 && maxresult != -1){
			query.setFirstResult(firstindex).setMaxResults(maxresult);//���ò���,ʵ����Ҳ���ǡ���>����ҳ��ѯ��
		}
		qr.setResultlist(query.getResultList());//���ò�ѯ���1
		query = em.createQuery("select  count(o) from "+entityName+" o "+(whereStr == null ? "" : "where "+ whereStr));
		setQueryParameter(query, queryparams);
		qr.setRecordtotal((Long)query.getSingleResult());//���ò�ѯ���2
		return qr;
	}
	//����ҳ+������
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult, String whereStr, Object[] queryparams) {
		return getScrollData(entityClass, firstindex, maxresult, whereStr, queryparams, null);
	}
	//����ҳ+����
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult, LinkedHashMap<String, String> orderby) {
		return getScrollData(entityClass, firstindex, maxresult, null, null, orderby);
	}
	//����ҳ��
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult) {
		return getScrollData(entityClass, firstindex, maxresult, null, null, null);
	}
	//����ȡ���С�
	public <T> QueryResult<T> getScrollData(Class<T> entityClass) {
		return getScrollData(entityClass, -1, -1, null, null, null);
	}
	/*����ȡ����2��
	 * 		ԭ�򡪡�Ȩ�޲�����������,Ĭ��daoSupport�ķ�ҳ����ʱ���������������ģ�
	 * 		select count(o) from POJO o�����������ᵼ���쳣��
	 * 		���������ǰ����д��ҳ����,��"select count(o) from POJO o" count()�е�o��Ϊ�������������һ����(eg:o.name)�ȿɡ�
	 */
	@Transactional(readOnly=true ,propagation = Propagation.NOT_SUPPORTED)	//����Ҫ����
	public <T> QueryResult<T> getScrollData_2(Class<T> entityClass,
			int firstindex, int maxresult ,String whereStr ,Object[] queryparams ,LinkedHashMap<String, String> orderby) {
		QueryResult<T> qr = new QueryResult<T>();		//������ѯ�����Ķ���
		String entityName = getEntityName(entityClass);		//��ȡ��ʵ��ʵ������
		//return (Long)em.createQuery("select count(*) from "+getEntityName(entityClass)+" e").getSingleResult();
		String sql = "select o from "+entityName+" o "+(whereStr == null ? "" : "where "+ whereStr)+buildOrderby(orderby);
		Query query = em.createQuery(sql);
		setQueryParameter(query, queryparams);
		//3.����Ҫ��ȡ�Ŀ�ʼ����ֵ��ÿҳҪ��ʾ��¼�������ǵ��з������أ�������Ҫ��ҳ��ʱ����������һ��Լ��������
		if(firstindex != -1 && maxresult != -1){
			query.setFirstResult(firstindex).setMaxResults(maxresult);//���ò���,ʵ����Ҳ���ǡ���>����ҳ��ѯ��
		}
		qr.setResultlist(query.getResultList());//���ò�ѯ���1
		query = em.createQuery("select  count(*) from "+entityName+" o "+(whereStr == null ? "" : "where "+ whereStr));
		setQueryParameter(query, queryparams);
		qr.setRecordtotal((Long)query.getSingleResult());//���ò�ѯ���2
		return qr;
	}
	public <T> QueryResult<T> getScrollData_3(Class<T> entityClass) {
		return getScrollData_2(entityClass, -1, -1, null, null, null);
	}
	
	//��������ȡ���С�
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,String whereStr,Object[] queryparams) {
		return getScrollData(entityClass, -1, -1, whereStr, queryparams, null);
	}

	/**
	 * �����ʵ�����ơ�����ȡ�ö�Ӧʵ�����Ӧ�����ݿ�������(��������ļ����ƻ���ע���϶����name������)
	 * @param entityClass������ʵ��������ȡ��Ӧ��ʵ������
	 * @return entityName����ʵ��ʵ������
	 */
	public <T> String getEntityName(Class<T> entityClass){
		String entityName = entityClass.getSimpleName();	//Ĭ�����������ļ�����
		Entity entity = entityClass.getAnnotation(Entity.class);//�õ���Ӧʵ�����ϵ�@Entityע�����Ҳ��Ҫ�õ����似����
		//��Ҫ�жϣ�(��Ϊʵ�����Ӧ�ı������п�����ʵ����ļ����ƣ�Ҳ�п�����@Entityע���϶����name������)
		if(entity.name() != null && !"".equals(entity.name())){	
			//�ʹ���ʵ�����Ʊ��޸Ĺ�����ע����nameָ����ֵ������Ҫ���ص�ʵ������entityName��
			entityName = entity.name();
		}
		return entityName;
	}
	
	/**
	 *������������䡿ʵ�������򣬺��ҳ�� order by o.xxx asc,o.yyy desc����>���ñ����Ļ����͵ü��ϱ���
	 * @param orderby  ����������desc/asc��������>keyΪ���ԣ�valueΪasc/desc
	 * @return sb.toString()  ���������ַ���
	 * ע�⣺(�÷���ʹ�ò���������ĳ�Ա�������ʽ��䶨��Ϊ��̬��)
	 */
	public static String buildOrderby(LinkedHashMap<String, String> orderby){
		StringBuilder sb = new StringBuilder();		//StringBuilderЧ�ʱ�StringBufferЧ�ʸ�
		//�ж�(���ȶ���Ϊ�գ����Ҷ��󼯺�����Ҳ��Ϊ��)
		if(orderby != null && !orderby.isEmpty()){
			//����order by���
			sb.append(" order by ");
			//��������(��һ�֣�����keyֵ�����ڶ��֣����������е�ÿһ��Ԫ��)
			for(String key : orderby.keySet()){
				//����>�����ѯ��ʱ�򣬲����˱����Ļ����͵ü��ϱ���,��ע�⡿�������һ�����ţ���
				sb.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
			}
			//��ɾ�����λ�ö���Ķ��š�
			sb.deleteCharAt(sb.length()-1);		//����Ҫ�õ������ය�ŵ�����ֵ
		}
		return sb.toString();
	}
	
	/**
	 * �����ò�ѯ������Ϊ��ѯ����������������ѯ�г��ֵ�λ�ò���!
	 */
	public static void setQueryParameter(Query query,Object[] queryparams){
		//���ݴ���Ҫ��params������Ϊ��
		if(queryparams != null && queryparams.length>0){
			for(int i = 0 ; i < queryparams.length ; i++){
				query.setParameter(i+1, queryparams[i]);	//(δ֪������˳��涨��1��ʼ)
			}
		}
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)	//��֧������
	//����ȡʵ����ܼ�¼����
	public <T> long getCount(Class<T> entityClass) {
		//�������Ҫ��ֵ ����ֻ��һ��ֵ����ʵ�����ƵĻ�ȡʹ�õ��ˡ�������>���似����
		//����������̱�ʽ�����⣺����ѯ�Ķ���������������ɵ�idʵ��
		return (Long)em.createQuery("select count(*) from "+getEntityName(entityClass)+" e").getSingleResult();
	}
}