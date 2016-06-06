package com.user.service.impl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.base.dao.DaoSupport;
import com.user.service.UserService;
//��Ա��ҵ��ӿڵľ���ʵ���ࡿ
@Service
@Transactional	//���൱�������ע�⣬����Ҳ����
public class UserServiceBean extends DaoSupport implements UserService{
	
	//��ע����֤�û�������֤�û��Ƿ��Ѿ����ڡ�������ֻ����֤�û���,���ǽӿڵķ���
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public boolean exsit1(String username){
		long count = (Long)em.createQuery("select count(o) from User o where o.username=?1")
				.setParameter(1,username).getSingleResult();//hql����ѯ�����ò�ѯ��������ȡ�����
		return count>0;//ֻҪcount����0����˵���û����ڣ�
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public boolean exsit2(String userUM){
		long count = (Long)em.createQuery("select count(o) from User o where o.userUM=?1")
				.setParameter(1,userUM).getSingleResult();//hql����ѯ�����ò�ѯ��������ȡ�����
		return count>0;//ֻҪcount����0����˵���û����ڣ�
	}
	
	//����½У���û��������롿У���û��Ƿ��Ѿ����ڡ�������У���û���������,���ǽӿڵķ���
	public boolean validate(String username,String password,Integer deptno,Integer identity){
		//hql����ѯ�����ò�ѯ����(�������Ҫд����MD5����������ʽ)����ȡ�����
		long count = (Long)em.createQuery("select count(o) from User o where o.username=?1 and o.password=?2 and o.dept.deptID=?3 and o.identity=?4")
				.setParameter(1,username).setParameter(2, password)
				.setParameter(3, deptno).setParameter(4, identity).getSingleResult();
		//System.out.println(count);
		return count>0;//ֻҪcount����0����˵���û����ڣ�
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