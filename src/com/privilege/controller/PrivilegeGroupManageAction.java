package com.privilege.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.base.Utils.SiteUrl;
import com.opensymphony.xwork2.ActionSupport;
import com.privilege.entity.PrivilegeGroup;
import com.privilege.entity.SystemPrivilege;
import com.privilege.entity.SystemPrivilegePK;
import com.privilege.service.PrivilegeGroupService;
import com.privilege.service.SystemPrivilegeService;
//��Ȩ�������������ɾ�Ĳ�
@Controller		//Ĭ��bean�������ǣ�privilegeGroupManageAction
@Scope("prototype")
public class PrivilegeGroupManageAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private String name;			//��Ȩ�������ơ�
	private String message;
	private String urladdress;
	private String groupId;
	/*��Ȩ��ֵ��
	 * ��ѡ����Թ�ѡ���Ȩ�ޣ���ô��Ҫ�������鷽ʽ������ѡ�е�ֵ���Ǿ�Ҫ��action�ж�����в�ִ����Ƚ��鷳��
	 * �ҿ���ֱ�ӵõ�Ȩ��ID���͵����顪�����Զ���һ������ת����SystemPrivilegePKConventer����
	 */
	private String[] privileges;		
	
	public PrivilegeGroupManageAction() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public String[] getPrivileges() {
		return privileges;
	}
	public void setPrivileges(String[] privileges) {
		this.privileges = privileges;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Resource SystemPrivilegeService systemPrivilegeService;
	@Resource PrivilegeGroupService privilegeGroupService;
	
	//�����Ȩ�����UI��
	public String addUI()  {
		//�����õ�request,response,session,context����
		HttpServletRequest request = ServletActionContext.getRequest();	
		//�õ����е�Ȩ��,����request����
		request.setAttribute("privileges", systemPrivilegeService.getScrollData_3(SystemPrivilege.class).getResultlist());
		return "addUI";
	}
	
	//�����Ȩ���顿
	public String add() throws Exception{
		PrivilegeGroup group = new PrivilegeGroup();
		group.setName(name);
		//���˾���������������ҲС, ������Filter ����inteceptor ���Ƕ�һ�����ô. û��Ҫ�ĵط�Ҳ�����й�����.����SystemPrivilegePKConventer
		for (String privilege : privileges) {
            String[] priKV= privilege.split(",");
            if (priKV.length == 2) {
                SystemPrivilegePK pk = new SystemPrivilegePK(priKV[0], priKV[1]);
                group.getPrivileges().add(new SystemPrivilege(pk));
            }
        }
		/*
		if(privileges != null && privileges.length>0){
			for(SystemPrivilegePK id : privileges){
				group.getPrivileges().add(new SystemPrivilege(id));
			}
		}*/
		privilegeGroupService.save(group);
		message = "���Ȩ����ɹ���";
		urladdress = SiteUrl.readUrl("manager.privilegeGroup");
		return "message";
	}
	
	//���༭Ȩ�����UI��
	public String editUI()  {
		//�����õ�request,response,session,context����
		HttpServletRequest request = ServletActionContext.getRequest();
		PrivilegeGroup group = privilegeGroupService.find(PrivilegeGroup.class, groupId);
		this.setName(group.getName());
		//��ŵ�ǰȨ�����е�����Ȩ��
		request.setAttribute("selectprivileges", group.getPrivileges());
		//�������е�Ȩ����
		request.setAttribute("privileges", systemPrivilegeService.getScrollData_3(SystemPrivilege.class).getResultlist());
		return "editUI";
	}
	
	//���޸�Ȩ���顿
	public String edit() throws Exception{
		//�����õ�request,response,session,context����
		HttpServletRequest request = ServletActionContext.getRequest();
		PrivilegeGroup group = privilegeGroupService.find(PrivilegeGroup.class, groupId);
		group.setName(name);
		group.getPrivileges().clear();//���֮ǰ������ѡ�����������
		
		//���˾���������������ҲС, ������Filter ����inteceptor ���Ƕ�һ�����ô. û��Ҫ�ĵط�Ҳ�����й�����.����SystemPrivilegePKConventer
		for (String privilege : privileges) {
            String[] priKV= privilege.split(",");
            if (priKV.length == 2) {
                SystemPrivilegePK pk = new SystemPrivilegePK(priKV[0], priKV[1]);
                group.getPrivileges().add(new SystemPrivilege(pk));
            }
        }
		privilegeGroupService.update(group);
		message = "�޸�Ȩ����ɹ���";
		urladdress = SiteUrl.readUrl("manager.privilegeGroup");
		return "message";
	}
	
	//��ɾ��Ȩ���顿����Ȩ���������Ϊ������ϵ��ά���ˣ��Ϳ���ֱ��ɾ���������Ϊ��ά���ˣ��򲻿���ֱ��ɾ������Ҫ��дdelete���������ڲ����Ƚ������
	public String delete() throws Exception{
		privilegeGroupService.delete(PrivilegeGroup.class, groupId);
		message = "ɾ��Ȩ����ɹ���";
		urladdress = SiteUrl.readUrl("manager.privilegeGroup");
		return "message";
	}
}