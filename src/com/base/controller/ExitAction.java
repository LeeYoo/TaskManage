package com.base.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.user.service.UserService;
//【退出系统】
@Controller		//默认bean的名称是：exitAction
@Scope("prototype")
public class ExitAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	//既然把action交给了Spring来管理，那就可以使用Spring的依赖注入功能，把业务bean注入进来。
	@Resource UserService userService;//【注入业务bean】，通过接口来获取
	
	//覆盖这个类当中的execute方法(执行的意思)
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.invalidate();//【销毁session】
		return SUCCESS;
	}
}