package com.personal.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.user.entity.User;
import com.user.service.UserService;
//��������Ϣ��
@Controller		//Ĭ��bean�������ǣ�preMyselfAction
@Scope("prototype")
public class PreMyselfAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	@Resource UserService userService;//��ע��ҵ��bean����ͨ���ӿ�����ȡ
	
	//��������൱�е�execute����(ִ�е���˼)
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sessions=request.getSession();
		User user = (User)sessions.getAttribute("user");
		request.setAttribute("user",user);
		return SUCCESS;
	}
}