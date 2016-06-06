package com.privilege.entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
/*
 	【权限实体对应的联合主键类】————>只需要定义作为联合主键的字段的相关定义和描述【权限模块和权限名称】
	联合主键：由两个或者多个字段组成的主键称之为联合主键
	怎么样定义联合主键呢？
	我们将组成联合主键的这些字段看成是一个整体，那么我们就可以定义一个主键类来描述这些复合字段。
	主键类的定义：例如一个【权限模块和权限名称】就可以作为该权限的联合主键，即就是SystemPrivilegePK类。
	——————>【联合主键类】必须要遵守的JPA规范：
	要求：	1.必须提供一个public的无参的构造函数.
			2.必须要实现序列化接口Serializable.
			3.必须要重写hashCode和equals这两个方法
			
	注意：	在【权限实体类】中只是用到了主键类SystemPrivilegePK的两个属性module和privilege，
			所以要通过注解@Embeddable来告诉JPA的实现产品：当前类SystemPrivilegePK用
			在实体类Privilege当中，只是使用了自己类(SystemPrivilegePK)中定义的属性而已。
			也就是说，实体类只是使用主键类中定义的属性作为这个实体的持久化字段！
*/
@Embeddable		//【嵌入的意思】把这个类里面的属性都嵌入到对应的实体中去
public class SystemPrivilegePK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String module;	//【权限模块】
	private String privilege;	//【权限值】
	
	//【构造函数】
	public SystemPrivilegePK() {}
	public SystemPrivilegePK(String module, String privilege) {
		this.module = module;
		this.privilege = privilege;
	}

	@Column(length=20,name="module")
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	@Column(length=20,name="privilege")
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	
	//【复写hash和equals方法】
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		result = prime * result
				+ ((privilege == null) ? 0 : privilege.hashCode());
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
		SystemPrivilegePK other = (SystemPrivilegePK) obj;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		if (privilege == null) {
			if (other.privilege != null)
				return false;
		} else if (!privilege.equals(other.privilege))
			return false;
		return true;
	}
}