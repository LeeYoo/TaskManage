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
//【部门实体类】
@Entity
public class Dept implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer deptID;			//【部门ID】
	private String deptname;			//【部门名称】
	private Set<User> users = new HashSet<User>();		//【关联员工】一对多——不维护关联关系
	private Set<Work> works = new HashSet<Work>();	//【关联任务】一对多——不维护关联关系
	
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
 	上面设置了级联保存，所以保存部门的时候，也会将其集合里的所有用户保存。
	往部门中放入用户的时候，如果采用Set<user>集合的方式不是很方便，
	所以为了添加方便，提供一个【增加用户的方法】*/
	public void addUser(User user){
		/*因为部门是关系的维护端，只有它才能够更新外键字段(往外键字段设值)，
		所以必须通过用户来设用户跟部门之间的关系.this代表部门*/
		user.setDept(this);
		//将订单项加入到订单中。
		this.users.add(user);
	}
	
	public void addWork(Work work){
		work.setDept(this);
		this.works.add(work);
	}
}