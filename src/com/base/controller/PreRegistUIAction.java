package com.base.controller;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dept.entity.Dept;
import com.dept.service.DeptService;
import com.opensymphony.xwork2.ActionSupport;
import com.paging.entity.QueryResult;
import com.user.entity.User;
import com.user.service.UserService;
//【注册表单(带有相关数据)】
@Controller		//默认bean的名称是：preRegistUIAction
@Scope("prototype")
public class PreRegistUIAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	//既然把action交给了Spring来管理，那就可以使用Spring的依赖注入功能，把业务bean注入进来。
	@Resource UserService userService;//【注入业务bean】，通过接口来获取
	@Resource DeptService deptService;
	
	//覆盖这个类当中的execute方法(执行的意思)
	public String execute() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		StringBuilder sb = new StringBuilder("o.identity=?1 or o.identity=?2");
		List<Object> params = new ArrayList<Object>();
		params.add(1);	//给?1参数设置值
		params.add(2);
		
		QueryResult<User> qr = userService.getScrollData(User.class, sb.toString(), params.toArray());
		QueryResult<Dept> qr2 = deptService.getScrollData(Dept.class);
		
		request.setAttribute("Resultlist", qr.getResultlist());		//把查询列表放到request中
		request.setAttribute("Resultlist2", qr2.getResultlist());
		
		return SUCCESS;
	}
}