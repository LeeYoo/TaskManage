package com.base.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.user.service.UserService;
//���˳�ϵͳ��
@Controller		//Ĭ��bean�������ǣ�exitAction
@Scope("prototype")
public class ExitAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	//��Ȼ��action������Spring�������ǾͿ���ʹ��Spring������ע�빦�ܣ���ҵ��beanע�������
	@Resource UserService userService;//��ע��ҵ��bean����ͨ���ӿ�����ȡ
	
	//��������൱�е�execute����(ִ�е���˼)
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.invalidate();//������session��
		return SUCCESS;
	}
}