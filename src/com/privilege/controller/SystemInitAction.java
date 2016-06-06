package com.privilege.controller;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.base.Utils.SiteUrl;
import com.opensymphony.xwork2.ActionSupport;
import com.privilege.entity.PrivilegeGroup;
import com.privilege.entity.SystemPrivilege;
import com.privilege.service.PrivilegeGroupService;
import com.privilege.service.SystemPrivilegeService;
import com.user.entity.Gender;
import com.user.entity.User;
import com.user.service.UserService;
//��ϵͳ��ʼ����Action����������ʼ��Ȩ�ޡ�
@Controller		//Ĭ��bean�������ǣ�systemInitAction
@Scope("prototype")
public class SystemInitAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private String message;		//��Ϣ
	private String urladdress;
	
	public SystemInitAction() {}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUrladdress() {
		return urladdress;
	}
	public void setUrladdress(String urladdress) {
		this.urladdress = urladdress;
	}

	//��Ȼ��action������Spring�������ǾͿ���ʹ��Spring������ע�빦�ܣ���ҵ��beanע�������
	@Resource SystemPrivilegeService privilegeService;	//��ע��ҵ��bean����ͨ���ӿ�����ȡ
	@Resource PrivilegeGroupService groupService;	
	@Resource UserService userService;
	
	//��������൱�е�execute����(ִ�е���˼)
	@Override
	public String execute() throws Exception {
		initPrivilege();			//����ʼ��Ȩ�ޡ�
		initPrivilegeGroup();	//����ʼ��Ȩ���顿
		initAdmin();				//����ʼ������Ա��
		message = "��ϲ����ϵͳ��ʼ���ɹ���";
		urladdress = SiteUrl.readUrl("user.preLogin");
		return "message";
	}

	//����ʼ������Ա��
	private void initAdmin() {
		if(userService.getCount(User.class) == 0){
			User user = new User();
			user.setIdentity(1);
			user.setUserUM("liyao001");
			user.setUsername("liyao");
			user.setRealname("��ҫ");
			user.setPassword("liyao");
			user.setEmail("youth_liyao@qq.com");
			user.setGender(Gender.MAN);
			//������ϵͳȨ���顿
			user.getGroups().addAll(groupService.getScrollData(PrivilegeGroup.class).getResultlist());
			userService.save(user);
		}
	}

	//����ʼ��Ȩ���顿������Ȩ����������е�Ȩ��
	private void initPrivilegeGroup() {
		if(groupService.getCount(PrivilegeGroup.class) == 0){
			PrivilegeGroup group = new PrivilegeGroup();
			group.setName("ϵͳȨ����");
			group.getPrivileges().addAll(privilegeService.getScrollData_3(SystemPrivilege.class).getResultlist());
			groupService.save(group);
		}
	}

	//����ʼ��Ȩ�޷�������ϵͳ�С����й��ܡ���Ȩ����ӵ����ݿ����ȥ
	private void initPrivilege() {
		//���жϡ���ֹ�û����ˢ�¸�ҳ��(���ִ�������ʼ����action·��)�����²����ɾ���޸ĵȲ������ִ�У�������Ҫ�ж��£��������
		
		if(privilegeService.getCount(SystemPrivilege.class)==0){
			List<SystemPrivilege> privileges = new ArrayList<SystemPrivilege>();
			
			//������ģ��Ȩ�����á�
			privileges.add(new SystemPrivilege("dept", "add", "�������Ȩ��"));
			privileges.add(new SystemPrivilege("dept", "view", "���Ų鿴Ȩ��"));
			privileges.add(new SystemPrivilege("dept", "edit", "�����޸�Ȩ��"));
			privileges.add(new SystemPrivilege("dept", "delete", "����ɾ��Ȩ��"));
			
			//���û�ģ��Ȩ�����á�
			privileges.add(new SystemPrivilege("user", "view", "�û��鿴Ȩ��"));
			privileges.add(new SystemPrivilege("user", "delete", "�û�ɾ��Ȩ��"));
			privileges.add(new SystemPrivilege("user", "privilegeSet", "�û�Ȩ������Ȩ��"));
	
			//��Ȩ����ģ��Ȩ�����á�
			privileges.add(new SystemPrivilege("privilegeGroup", "view", "Ȩ�����б�鿴Ȩ��"));
			privileges.add(new SystemPrivilege("privilegeGroup", "delete", "Ȩ�����б�ɾ��Ȩ��"));
			privileges.add(new SystemPrivilege("privilegeGroup", "add", "Ȩ�����б����Ȩ��"));
			privileges.add(new SystemPrivilege("privilegeGroup", "edit", "Ȩ�����б��޸�Ȩ��"));
			
			//��Ȩ��ģ��Ȩ�����á�
			privileges.add(new SystemPrivilege("privilege", "view", "Ȩ���б�鿴Ȩ��"));
			
			//������ģ��Ȩ�����á�
			privileges.add(new SystemPrivilege("chart", "view", "������Ȳ鿴Ȩ��"));
			
			//������ģ��Ȩ�����á�
			privileges.add(new SystemPrivilege("score", "add", "��������Ȩ��"));
			privileges.add(new SystemPrivilege("score", "view", "���ֲ鿴Ȩ��"));
			
			privilegeService.saves(privileges);//��ִ�б��桿
		}
	}
}