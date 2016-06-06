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
//【抽象类】不能够创建对象，只能通过业务bean来继承它！用来实现DAO接口
@Transactional
public abstract class DaoSupport implements DAO{
	
	//第一步：让Spring给我们注入一个事务管理器！【注入实体管理器】，在以下业务方法执行之前打开事务，结束之后关闭事务！
	@PersistenceContext protected EntityManager em;
	
	public void save(Object entity) {	
		em.persist(entity);
	}
	
	public void update(Object entity) {
		//把游离状态的实体bean同步回数据库
		em.merge(entity);
	}

	public <T> void delete(Class<T> entityClass, Object entityid) {
		//直接调用即可
		delete(entityClass, new Object[] {entityid});
	}

	public <T> void delete(Class<T> entityClass, Object[] entityids) {
		for( Object id : entityids){
			//删除实体，首先要得到实体的托管对象(参数：实体类型，实体id)
			em.remove(em.getReference(entityClass , id));
		}
	}
	
	//不需要事务行为,事务的传播行为是不开启事务
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
	
													/*实现【分页功能】代码重用设计*/
	/**
	 * 【实现分页封装的方法】
	 * 【分页+条件+排序】
	 */
	@Transactional(readOnly=true ,propagation = Propagation.NOT_SUPPORTED)	//不需要事务
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult ,String whereStr ,Object[] queryparams ,LinkedHashMap<String, String> orderby) {
		QueryResult<T> qr = new QueryResult<T>();		//创建查询结果类的对象
		String entityName = getEntityName(entityClass);		//获取真实的实体名称
		//return (Long)em.createQuery("select count(*) from "+getEntityName(entityClass)+" e").getSingleResult();
		String sql = "select o from "+entityName+" o "+(whereStr == null ? "" : "where "+ whereStr)+buildOrderby(orderby);
		Query query = em.createQuery(sql);
		setQueryParameter(query, queryparams);
		//3.设置要获取的开始索引值和每页要显示记录数（考虑到有方法重载，当不需要分页的时候，我们设置一个约束条件）
		if(firstindex != -1 && maxresult != -1){
			query.setFirstResult(firstindex).setMaxResults(maxresult);//设置参数,实质上也就是——>【分页查询】
		}
		qr.setResultlist(query.getResultList());//设置查询结果1
		query = em.createQuery("select  count(o) from "+entityName+" o "+(whereStr == null ? "" : "where "+ whereStr));
		setQueryParameter(query, queryparams);
		qr.setRecordtotal((Long)query.getSingleResult());//设置查询结果2
		return qr;
	}
	//【分页+条件】
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult, String whereStr, Object[] queryparams) {
		return getScrollData(entityClass, firstindex, maxresult, whereStr, queryparams, null);
	}
	//【分页+排序】
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult, LinkedHashMap<String, String> orderby) {
		return getScrollData(entityClass, firstindex, maxresult, null, null, orderby);
	}
	//【分页】
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult) {
		return getScrollData(entityClass, firstindex, maxresult, null, null, null);
	}
	//【获取所有】
	public <T> QueryResult<T> getScrollData(Class<T> entityClass) {
		return getScrollData(entityClass, -1, -1, null, null, null);
	}
	/*【获取所有2】
	 * 		原因——权限采用联合主键,默认daoSupport的分页方法时不采用联合主键的，
	 * 		select count(o) from POJO o这句会出现问题会导致异常。
	 * 		解决——当前类重写分页方法,将"select count(o) from POJO o" count()中的o改为出其主键外的任一属性(eg:o.name)既可。
	 */
	@Transactional(readOnly=true ,propagation = Propagation.NOT_SUPPORTED)	//不需要事务
	public <T> QueryResult<T> getScrollData_2(Class<T> entityClass,
			int firstindex, int maxresult ,String whereStr ,Object[] queryparams ,LinkedHashMap<String, String> orderby) {
		QueryResult<T> qr = new QueryResult<T>();		//创建查询结果类的对象
		String entityName = getEntityName(entityClass);		//获取真实的实体名称
		//return (Long)em.createQuery("select count(*) from "+getEntityName(entityClass)+" e").getSingleResult();
		String sql = "select o from "+entityName+" o "+(whereStr == null ? "" : "where "+ whereStr)+buildOrderby(orderby);
		Query query = em.createQuery(sql);
		setQueryParameter(query, queryparams);
		//3.设置要获取的开始索引值和每页要显示记录数（考虑到有方法重载，当不需要分页的时候，我们设置一个约束条件）
		if(firstindex != -1 && maxresult != -1){
			query.setFirstResult(firstindex).setMaxResults(maxresult);//设置参数,实质上也就是——>【分页查询】
		}
		qr.setResultlist(query.getResultList());//设置查询结果1
		query = em.createQuery("select  count(*) from "+entityName+" o "+(whereStr == null ? "" : "where "+ whereStr));
		setQueryParameter(query, queryparams);
		qr.setRecordtotal((Long)query.getSingleResult());//设置查询结果2
		return qr;
	}
	public <T> QueryResult<T> getScrollData_3(Class<T> entityClass) {
		return getScrollData_2(entityClass, -1, -1, null, null, null);
	}
	
	//【条件获取所有】
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,String whereStr,Object[] queryparams) {
		return getScrollData(entityClass, -1, -1, whereStr, queryparams, null);
	}

	/**
	 * 【获得实体名称】——取得对应实体类对应的数据库表的名称(可能是类的简单名称或是注解上定义的name的名称)
	 * @param entityClass，根据实体类来获取对应的实体名称
	 * @return entityName，真实的实体名称
	 */
	public <T> String getEntityName(Class<T> entityClass){
		String entityName = entityClass.getSimpleName();	//默认情况下是类的简单名称
		Entity entity = entityClass.getAnnotation(Entity.class);//得到对应实体类上的@Entity注解对象，也需要用到反射技术。
		//需要判断：(因为实体类对应的表名称有可能是实体类的简单名称，也有可能是@Entity注解上定义的name的名称)
		if(entity.name() != null && !"".equals(entity.name())){	
			//就代表实体名称被修改过（将注解上name指定的值赋给需要返回的实体名称entityName）
			entityName = entity.name();
		}
		return entityName;
	}
	
	/**
	 *【构造排序语句】实现先排序，后分页。 order by o.xxx asc,o.yyy desc——>采用别名的话，就得加上别名
	 * @param orderby  排序属性与desc/asc————>key为属性，value为asc/desc
	 * @return sb.toString()  排序语句的字符串
	 * 注意：(该方法使用不到类里面的成员变量，故将其定义为静态的)
	 */
	public static String buildOrderby(LinkedHashMap<String, String> orderby){
		StringBuilder sb = new StringBuilder();		//StringBuilder效率比StringBuffer效率高
		//判断(首先对象不为空，并且对象集合里面也不为空)
		if(orderby != null && !orderby.isEmpty()){
			//构造order by语句
			sb.append(" order by ");
			//迭代集合(第一种：迭代key值——第二种：迭代集合中的每一条元素)
			for(String key : orderby.keySet()){
				//——>上面查询的时候，采用了别名的话，就得加上别名,【注意】最后会多余一个逗号！！
				sb.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
			}
			//【删掉最后位置多余的逗号】
			sb.deleteCharAt(sb.length()-1);		//首先要得到最后多余逗号的索引值
		}
		return sb.toString();
	}
	
	/**
	 * 【设置查询参数】为查询对象设置在条件查询中出现的位置参数!
	 */
	public static void setQueryParameter(Query query,Object[] queryparams){
		//【容错处理】要求params不允许为空
		if(queryparams != null && queryparams.length>0){
			for(int i = 0 ; i < queryparams.length ; i++){
				query.setParameter(i+1, queryparams[i]);	//(未知参数的顺序规定从1开始)
			}
		}
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)	//不支持事务
	//【获取实体的总记录数】
	public <T> long getCount(Class<T> entityClass) {
		//结果必须要有值 仅且只有一个值！【实体名称的获取使用到了————>反射技术】
		//————里程碑式的问题：当查询的对象是有联合逐渐组成的id实体
		return (Long)em.createQuery("select count(*) from "+getEntityName(entityClass)+" e").getSingleResult();
	}
}