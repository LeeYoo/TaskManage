package com.user.service.impl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.base.dao.DaoSupport;
import com.user.service.UserService;
//【员工业务接口的具体实现类】
@Service
@Transactional	//父类当中有这个注解，不加也可以
public class UserServiceBean extends DaoSupport implements UserService{
	
	//【注册验证用户名】验证用户是否已经存在————只是验证用户名,覆盖接口的方法
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public boolean exsit1(String username){
		long count = (Long)em.createQuery("select count(o) from User o where o.username=?1")
				.setParameter(1,username).getSingleResult();//hql语句查询，设置查询参数，获取单结果
		return count>0;//只要count大于0，就说明用户存在！
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public boolean exsit2(String userUM){
		long count = (Long)em.createQuery("select count(o) from User o where o.userUM=?1")
				.setParameter(1,userUM).getSingleResult();//hql语句查询，设置查询参数，获取单结果
		return count>0;//只要count大于0，就说明用户存在！
	}
	
	//【登陆校验用户名和密码】校验用户是否已经存在————校验用户名和密码,覆盖接口的方法
	public boolean validate(String username,String password,Integer deptno,Integer identity){
		//hql语句查询，设置查询参数(密码必须要写成由MD5处理过后的形式)，获取单结果
		long count = (Long)em.createQuery("select count(o) from User o where o.username=?1 and o.password=?2 and o.dept.deptID=?3 and o.identity=?4")
				.setParameter(1,username).setParameter(2, password)
				.setParameter(3, deptno).setParameter(4, identity).getSingleResult();
		//System.out.println(count);
		return count>0;//只要count大于0，就说明用户存在！
	}
	public boolean validate1(String username,String password,Integer deptno){
		long count = (Long)em.createQuery("select count(o) from User o where o.username=?1 and o.password=?2 and o.dept.deptID=?3")
				.setParameter(1,username).setParameter(2, password)
				.setParameter(3, deptno).getSingleResult();
		return count>0;
	}
	public boolean validate2(String username,String password,Integer identity){
		long count = (Long)em.createQuery("select count(o) from User o where o.username=?1 and o.password=?2 and o.identity=?3")
				.setParameter(1,username).setParameter(2, password)
				.setParameter(3, identity).getSingleResult();
		return count>0;
	}
	
	public boolean validate3(String username,String password){
		long count = (Long)em.createQuery("select count(o) from User o where o.username=?1 and o.password=?2")
				.setParameter(1,username).setParameter(2, password).getSingleResult();
		return count>0;
	}
}