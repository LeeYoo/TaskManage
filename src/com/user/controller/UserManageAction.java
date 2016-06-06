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
//【用户管理】——增删改查
@Controller		//默认bean的名称是：userManageAction
@Scope("prototype")
public class UserManageAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private Integer userno;		//【id号】
	private String message;
	private String urladdress;
	private String groupIds[];	//权限组id
	
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
	
	//【删除用户】
	@Permission(module="employee",privilege="delete")	//【权限拦截】在执行方法之前需要注解判断。
	public String delete() throws Exception{
		userService.delete(User.class, userno);
		message = "删除用户成功！";
		urladdress = SiteUrl.readUrl("manager.user");
		return "message";
	}
	
	//【设置权限组的UI】
	public String setPrivilegeUI() {
		//首先拿到request,response,session,context对象
		HttpServletRequest request = ServletActionContext.getRequest();			
		//获取当前用户自身具有的权限组
		User user = userService.find(User.class, userno);
		request.setAttribute("usergroups", user.getGroups());
		request.setAttribute("groups", groupService.getScrollData_3(PrivilegeGroup.class).getResultlist());//获取所有权限组
		return "setPrivilegeUI";
	}
	
	//【为用户设置权限组】
	public String setPrivilege() throws Exception{
		User user = userService.find(User.class, userno);
		user.getGroups().clear();
		if(groupIds!=null){
			for(String groupid : groupIds){
				user.addPrivilegeGroup(new PrivilegeGroup(groupid));
			}
			userService.update(user);//简历用户和权限组的关系（中间表）
			message = "设置权限组成功！";
			urladdress = SiteUrl.readUrl("manager.user");
			return "message";
		}
		message = "请为用户指定权限组！";
		urladdress = SiteUrl.readUrl("manager.user");
		return "message";
	}
}