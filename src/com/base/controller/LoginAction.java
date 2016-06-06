package com.base.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.base.Utils.SiteUrl;
import com.opensymphony.xwork2.ActionSupport;
import com.user.entity.User;
import com.user.service.UserService;
//【用户登陆】
@Controller		//默认bean的名称是：loginAction
@Scope("prototype")
public class LoginAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private Integer userno;		
	private String username;
	private String password;
	private Integer deptno;
	private Integer identity;
	private String message;
	private String urladdress;
	
	public LoginAction() {}
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}
	public Integer getIdentity() {
		return identity;
	}
	public void setIdentity(Integer identity) {
		this.identity = identity;
	}
	public String getUrladdress() {
		return urladdress;
	}
	public void setUrladdress(String urladdress) {
		this.urladdress = urladdress;
	}

	//既然把action交给了Spring来管理，那就可以使用Spring的依赖注入功能，把业务bean注入进来。
	@Resource UserService userService;//【注入业务bean】，通过接口来获取
	
	//覆盖这个类当中的execute方法(执行的意思)
	public String execute() throws Exception {
		
		//首先拿到request,response,session,context对象
		HttpServletRequest request = ServletActionContext.getRequest();
		//开始校验（返回的是布尔值）
		if(userService.validate(username, password, deptno, identity)){
			
			User user = new User();
			user = userService.find(username, password, deptno, identity);
			request.getSession().setAttribute("user",user);
			
			request.getSession().setAttribute("deptno", deptno);
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("identity", identity);
			
			message = "欢迎您，"+user.getRealname()+"  先生/女士";
			urladdress = SiteUrl.readUrl("manager.welcome");
			return SUCCESS;
		}else if (!(userService.validate3(username, password))) {
			message = "用户名或密码有误！";
			return INPUT;
		}else {
			message = "请匹配正确的部门或身份！";
			return INPUT;
		}	
	}
}