package com.dept.entity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.user.entity.User;
import com.work.entity.Work;
//������ʵ���ࡿ
@Entity
public class Dept implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer deptID;			//������ID��
	private String deptname;			//���������ơ�
	private Set<User> users = new HashSet<User>();		//������Ա����һ�Զࡪ����ά��������ϵ
	private Set<Work> works = new HashSet<Work>();	//����������һ�Զࡪ����ά��������ϵ
	
	public Dept() {}
	public Dept(String deptname) {
		this.deptname = deptname;
	}
	public Dept(Integer deptID) {
		this.deptID = deptID;
	}
	
	public Dept(Integer deptID, String deptname) {
		this.deptID = deptID;
		this.deptname = deptname;
	}
	@Id @GeneratedValue
	public Integer getDeptID() {
		return deptID;
	}
	public void setDeptID(Integer deptID) {
		this.deptID = deptID;
	}

	@Column(length=20,unique=true)
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE},mappedBy="dept")
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE},mappedBy="dept")
	public Set<Work> getWorks() {
		return works;
	}
	public void setWorks(Set<Work> works) {
		this.works = works;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deptID == null) ? 0 : deptID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dept other = (Dept) obj;
		if (deptID == null) {
			if (other.deptID != null)
				return false;
		} else if (!deptID.equals(other.deptID))
			return false;
		return true;
	}	
	
	/*
 	���������˼������棬���Ա��沿�ŵ�ʱ��Ҳ�Ὣ�伯����������û����档
	�������з����û���ʱ���������Set<user>���ϵķ�ʽ���Ǻܷ��㣬
	����Ϊ����ӷ��㣬�ṩһ���������û��ķ�����*/
	public void addUser(User user){
		/*��Ϊ�����ǹ�ϵ��ά���ˣ�ֻ�������ܹ���������ֶ�(������ֶ���ֵ)��
		���Ա���ͨ���û������û�������֮��Ĺ�ϵ.this������*/
		user.setDept(this);
		//����������뵽�����С�
		this.users.add(user);
	}
	
	public void addWork(Work work){
		work.setDept(this);
		this.works.add(work);
	}
}