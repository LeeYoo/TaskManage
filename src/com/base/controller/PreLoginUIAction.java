package com.base.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.dept.entity.Dept;
import com.dept.service.DeptService;
import com.opensymphony.xwork2.ActionSupport;
import com.paging.entity.QueryResult;
//【跳转登陆界面(带有相关数据)】
@Controller		//默认bean的名称是：preLoginUIAction
@Scope("prototype")
public class PreLoginUIAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	//既然把action交给了Spring来管理，那就可以使用Spring的依赖注入功能，把业务bean注入进来。
	@Resource DeptService deptService;
	
	//覆盖这个类当中的execute方法(执行的意思)
	public String execute() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		QueryResult<Dept> qr = deptService.getScrollData(Dept.class);
		
		request.setAttribute("Resultlist", qr.getResultlist());		//把查询列表放到request中
		
		return SUCCESS;
	}
}