package com.privilege.entity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
/*��ϵͳȨ��ʵ�塿����ͨ�����������ķ�ʽȷ��Ȩ�ޱ�ʶ����Ȩ��ģ��+Ȩ�����ơ���ʵ������Ҫ�õ�����������SystemPrivilegePK
 * ����Ȩ�޺�Ȩ�����Ƕ�Զ������ϵ
 * ������Ȩ��ʵ�塿�ǹ�����ϵ��ά����
 */
@Entity(name="systemprivilege")
public class SystemPrivilege implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private SystemPrivilegePK id;	//������������ΪȨ��ʵ����ı�ʶ����
	private String name;				//Ȩ������

	//Ȩ��������Ȩ���顪��Ȩ�޺�Ȩ�����Ƕ�Զ������ϵ������Ȩ��ʵ�塿�ǹ�����ϵ��ά����
	private Set<PrivilegeGroup> privilegeGroups = new HashSet<PrivilegeGroup>(); 
	
	//���캯��
	public SystemPrivilege(SystemPrivilegePK id) {
		this.id = id;
	}
	public SystemPrivilege() {}
	public SystemPrivilege(String module,String privilege, String name) {
		this.id = new SystemPrivilegePK(module, privilege);
		this.name = name;
	}
	
	//���ڸ�ʵ���ʵ���ʶ����һ����Ƕ�����(����������),����Ҫʹ��@EmbeddedId�������id��ʶ��
	@EmbeddedId		//��ͬ��@Id��������ʹ��@Id
	public SystemPrivilegePK getId() {
		return id;
	}
	public void setId(SystemPrivilegePK id) {
		this.id = id;
	}
	
	@Column(length=20,nullable=false)		//Ȩ�����Ʋ�����Ϊ��
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/*��Ȩ�޺�Ȩ�����Ƕ�Զ��ϵ��������ǰ���ǹ�����ϵ����ά���ˡ���
	 * �������ԣ�����Ӧ��������û�м�������ͼ������£�������Ҳ�ò�������ɾ����
	 * ����mappedBy��Ĭ�ϱ�������ӳټ��ء�
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