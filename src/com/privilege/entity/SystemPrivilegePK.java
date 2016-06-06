package com.privilege.entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
/*
 	��Ȩ��ʵ���Ӧ�����������ࡿ��������>ֻ��Ҫ������Ϊ�����������ֶε���ض����������Ȩ��ģ���Ȩ�����ơ�
	�������������������߶���ֶ���ɵ�������֮Ϊ��������
	��ô���������������أ�
	���ǽ����������������Щ�ֶο�����һ�����壬��ô���ǾͿ��Զ���һ����������������Щ�����ֶΡ�
	������Ķ��壺����һ����Ȩ��ģ���Ȩ�����ơ��Ϳ�����Ϊ��Ȩ�޵�����������������SystemPrivilegePK�ࡣ
	������������>�����������ࡿ����Ҫ���ص�JPA�淶��
	Ҫ��	1.�����ṩһ��public���޲εĹ��캯��.
			2.����Ҫʵ�����л��ӿ�Serializable.
			3.����Ҫ��дhashCode��equals����������
			
	ע�⣺	�ڡ�Ȩ��ʵ���ࡿ��ֻ���õ���������SystemPrivilegePK����������module��privilege��
			����Ҫͨ��ע��@Embeddable������JPA��ʵ�ֲ�Ʒ����ǰ��SystemPrivilegePK��
			��ʵ����Privilege���У�ֻ��ʹ�����Լ���(SystemPrivilegePK)�ж�������Զ��ѡ�
			Ҳ����˵��ʵ����ֻ��ʹ���������ж����������Ϊ���ʵ��ĳ־û��ֶΣ�
*/
@Embeddable		//��Ƕ�����˼�����������������Զ�Ƕ�뵽��Ӧ��ʵ����ȥ
public class SystemPrivilegePK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String module;	//��Ȩ��ģ�顿
	private String privilege;	//��Ȩ��ֵ��
	
	//�����캯����
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
	
	//����дhash��equals������
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