package com.user.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.base.Utils.SiteUrl;
import com.opensymphony.xwork2.ActionSupport;
import com.privilege.annotation.Permission;
import com.privilege.entity.PrivilegeGroup;
import com.privilege.service.PrivilegeGroupService;
import com.user.entity.User;
import com.user.service.UserService;
//���û�����������ɾ�Ĳ�
@Controller		//Ĭ��bean�������ǣ�userManageAction
@Scope("prototype")
public class UserManageAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private Integer userno;		//��id�š�
	private String message;
	private String urladdress;
	private String groupIds[];	//Ȩ����id
	
	public UserManageAction() {}
	
	public Integer getUserno() {
		return userno;
	}
	public void setUserno(Integer userno) {
		this.userno = userno;
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
	public String[] getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(String[] groupIds) {
		this.groupIds = groupIds;
	}

	@Resource UserService userService;
	@Resource PrivilegeGroupService groupService;
	
	//��ɾ���û���
	@Permission(module="employee",privilege="delete")	//��Ȩ�����ء���ִ�з���֮ǰ��Ҫע���жϡ�
	public String delete() throws Exception{
		userService.delete(User.class, userno);
		message = "ɾ���û��ɹ���";
		urladdress = SiteUrl.readUrl("manager.user");
		return "message";
	}
	
	//������Ȩ�����UI��
	public String setPrivilegeUI() {
		//�����õ�request,response,session,context����
		HttpServletRequest request = ServletActionContext.getRequest();			
		//��ȡ��ǰ�û�������е�Ȩ����
		User user = userService.find(User.class, userno);
		request.setAttribute("usergroups", user.getGroups());
		request.setAttribute("groups", groupService.getScrollData_3(PrivilegeGroup.class).getResultlist());//��ȡ����Ȩ����
		return "setPrivilegeUI";
	}
	
	//��Ϊ�û�����Ȩ���顿
	public String setPrivilege() throws Exception{
		User user = userService.find(User.class, userno);
		user.getGroups().clear();
		if(groupIds!=null){
			for(String groupid : groupIds){
				user.addPrivilegeGroup(new PrivilegeGroup(groupid));
			}
			userService.update(user);//�����û���Ȩ����Ĺ�ϵ���м��
			message = "����Ȩ����ɹ���";
			urladdress = SiteUrl.readUrl("manager.user");
			return "message";
		}
		message = "��Ϊ�û�ָ��Ȩ���飡";
		urladdress = SiteUrl.readUrl("manager.user");
		return "message";
	}
}