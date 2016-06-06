package com.privilege.entity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.user.entity.User;
/*��Ȩ����ʵ�塿����Ȩ�޺�Ȩ�����Ƕ�Զ������ϵ������Ȩ����ʵ�塿�ǹ�����ϵά����
 * ���罨����ϵ��privilegeGroup.getPrivileges().add(privilege);�������м����൱��insert into ...��䡣
 * ��������ϵ��privilegeGroup.getPrivileges().remove(privilege);�������м����൱��delete from ...��䡣
 * ��Щ���������ڹ�����ϵ��ά������ɵġ�
 *  */
@Entity
public class PrivilegeGroup implements  Serializable{
	private static final long serialVersionUID = 1L;
	
	private String groupId;		//����UUID����
	private String name;		//Ȩ��������
	
	//Ȩ���������е�Ȩ�ޡ���Ȩ�޺�Ȩ�����Ƕ�Զ������ϵ������Ȩ����ʵ�塿�ǹ�����ϵά����
	private Set<SystemPrivilege> privileges = new HashSet<SystemPrivilege>();	
	
	//��Ȩ��������Ӧ��Ա����_��Զ�
	private Set<User> users = new HashSet<User>();
	
	//���캯��
	public PrivilegeGroup() {}
	public PrivilegeGroup(String groupId) {
		this.groupId = groupId;
	}
	@Id @Column(length=36)
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	@Column(length=20)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/*��Ȩ�޺�Ȩ�����Ƕ�Զ��ϵ��������������ɶ���Ȩ����Ϊ������ϵ��ά���ˣ����ڸ���Ȩ�����ʱ�򣬸����������е�Ȩ�ޣ�
	 * ��Զ������ϵ�������м��(������)����ɹ��������ģ�
	 * �������������ԡ�����ֻ��Ҫ����ˢ�¼��ɣ�
	 * ����Ҫ��������(Ȩ����ϵͳ��ʼ����ʱ����Ѿ��ȴ����ˣ�����Ҫ���ڱ���Ȩ�����ʱ���ٽ���Ȩ�޵ı������)
	 * ����Ҫ��������(����ģ��Ȩ��һ��ϵͳ��ʼ�����֮�󣬾Ͳ���Ҫ�޸���)
	 * ����Ҫ����ɾ��(��ɾ��Ȩ�����ʱ�򣬿϶������ܼ���ɾ���������µ�����Ȩ��)
	 * ������ץȡ���ԡ�����Ĭ�����ӳټ��صģ�����������Ҫ���������ء�����Ϊ�û�һ����½����̨��Ҫʹ�õ���̨�Ĺ��ܣ���ô����ҪȨ�ޡ�
	 * �ڶ�Զ������ϵ�У���ϵ��ά���������ϵ������Ȳ������������м��Ķ��塿��Ҳ������ά���˶��塣
	 * ��������Ķ�����@JoinTable��ɡ�����ά�����ǡ�Ȩ���顿����ά�����ǡ�Ȩ�ޡ�������Ȩ����������������������ô����Ҫ��������ֶ�
	 * joinColumns:�����м���������ϵά����֮���������壬Ҳ�����м���и������������
	 * inverseJoinColumns�������м���������ϵ�ı�ά����֮���������塣
	 * referencedColumnName�����������м�������ֶξ����Ǻ͹�����ϵ��ά�����е���һ���ֶ�������ģ�
	 */
	@ManyToMany(cascade={CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinTable(name="group_privilege",
					//�����м���������ϵ��ά���ˡ�֮����������,����>����Ͳ���Ҫ����ָ���ˣ���Ϊֻ��һ��������
					joinColumns=@JoinColumn(name="g_Id"),				
					//�����м���������ϵ�ġ���ά���ˡ�����ά���������������ġ�֮���������塪��>����������Ҫ����ָ����
					inverseJoinColumns={@JoinColumn(name="p_module",referencedColumnName="module"),
													@JoinColumn(name="p_privilege",referencedColumnName="privilege")})		
	public Set<SystemPrivilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(Set<SystemPrivilege> privileges) {
		this.privileges = privileges;
	}
	
	/*��Ȩ������û��Ƕ�Զ��ϵ��������ǰ���ǹ�����ϵ����ά���ˡ���
	 * �������ԣ�����Ӧ��������û�м�������ͼ������£�������Ҳ�ò�������ɾ����
	 * ����mappedBy��Ĭ�ϱ�������ӳټ��ء�
	 */
	@ManyToMany(cascade=(CascadeType.REFRESH),mappedBy="groups")
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	//�ṩ�����privilegeȨ�ޡ��ķ���
	public void addSystemPrivilege(SystemPrivilege privilege){
		this.getPrivileges().add(privilege);//this.privileges.add(privilege);
	}
	//�ṩ��ɾ��privilegeȨ�ޡ��ķ���
	public void removeSystemPrivilege(SystemPrivilege privilege){
		/*	ɾ��ĳ��ָ����privilege����Ҫ�ж�privileges�������Ƿ����Ҫɾ����privilege��
			����������ͽ���ɾ����
			��ô����ж�privilege�����ڸ�privileges�������أ�����Ҫͨ��privilege��id���ж��ˣ�
			������privilege������Ҫ��дhashCode()��equals(Object obj)�������ж��ˡ�*/
		if(this.privileges.contains(privilege)){	//contains�ײ���Զ�����hashCode�������жϡ�	
			this.getPrivileges().remove(privilege);			
		}
	}
		
}