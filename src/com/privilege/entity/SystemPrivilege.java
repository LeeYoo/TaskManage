package com.privilege.entity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
/*【系统权限实体】——通过联合主键的方式确定权限标识：【权限模块+权限名称】该实体中需要用到复合主键类SystemPrivilegePK
 * ——权限和权限组是多对多关联关系
 * ——【权限实体】是关联关系被维护端
 */
@Entity(name="systemprivilege")
public class SystemPrivilege implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private SystemPrivilegePK id;	//复合主键类作为权限实体类的标识符。
	private String name;				//权限名称

	//权限所属的权限组——权限和权限组是多对多关联关系——【权限实体】是关联关系被维护端
	private Set<PrivilegeGroup> privilegeGroups = new HashSet<PrivilegeGroup>(); 
	
	//构造函数
	public SystemPrivilege(SystemPrivilegePK id) {
		this.id = id;
	}
	public SystemPrivilege() {}
	public SystemPrivilege(String module,String privilege, String name) {
		this.id = new SystemPrivilegePK(module, privilege);
		this.name = name;
	}
	
	//由于该实体的实体标识符是一个可嵌入的类(复合主键类),所以要使用@EmbeddedId对其进行id标识。
	@EmbeddedId		//等同于@Id，但不能使用@Id
	public SystemPrivilegePK getId() {
		return id;
	}
	public void setId(SystemPrivilegePK id) {
		this.id = id;
	}
	
	@Column(length=20,nullable=false)		//权限名称不允许为空
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/*【权限和权限组是多对多关系】——当前类是关联关系【被维护端】！
	 * 级联策略：根据应用需求，它没有级联保存和级联更新，基本上也用不到级联删除！
	 * 声明mappedBy和默认本身就是延迟加载。
	 */
	@ManyToMany(cascade=(CascadeType.REFRESH),mappedBy="privileges")
	public Set<PrivilegeGroup> getPrivilegeGroups() {
		return privilegeGroups;
	}
	public void setPrivilegeGroups(Set<PrivilegeGroup> privilegeGroups) {
		this.privilegeGroups = privilegeGroups;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		SystemPrivilege other = (SystemPrivilege) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}