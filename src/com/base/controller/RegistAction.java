package com.base.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.base.Utils.SiteUrl;
import com.base.dao.DB;
import com.opensymphony.xwork2.ActionSupport;
import com.user.entity.Gender;
import com.user.service.UserService;
//【用户注册】
@Controller		//默认bean的名称是：registAction
@Scope("prototype")
public class RegistAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private String realname;
	private String userUM;
	private String username;
	private String password;
	private String repassword;
	private Integer deptno;
	private Integer identity;
	private String email;	
	private Gender gender;
	private String message;
	private String urladdress;
	
	public RegistAction() {}
	
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getUserUM() {
		return userUM;
	}
	public void setUserUM(String userUM) {
		this.userUM = userUM;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
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
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public Integer getDeptno() {
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

	public String execute() throws Exception {
		
		//需要判断输入的用户名和UM号是否已经存在！通过业务bean来执行(前提是需要注入业务bean)
		if(userService.exsit1(username)){
			//代表存在(提示用户名已经存在的错误信息，并返回到注册页面)
			message = "该用户名已被注册！";
			return INPUT;
		}else if (userService.exsit2(userUM)) {
			message = "该UM号已被注册！";
			return INPUT;
		}else {
			HttpServletRequest req = ServletActionContext.getRequest();
			
			String realname=req.getParameter("realname");
			String userUM=req.getParameter("userUM");
			String username=req.getParameter("username");
			String password=req.getParameter("password");
			String dept_no=req.getParameter("deptno");
			String identity=req.getParameter("identity");
			String parentid=req.getParameter("parent");
			String gender=req.getParameter("gender");
			String email=req.getParameter("email");
			//没有上级的注册
			if(parentid==null || "".equals(parentid)){
				String sql="insert into user(realname,userUM,username,password,dept_no,identity,gender,email) values(?,?,?,?,?,?,?,?)";
				Object[] params={realname,userUM,username,password,dept_no,identity,gender,email};
				DB mydb=new DB();
				mydb.doPstm(sql, params);
				mydb.closed();
				
				message = "用户注册成功！";
				urladdress = SiteUrl.readUrl("user.preLogin");
				return SUCCESS;
			}//有上级的注册
			else if(dept_no != null && !"".equals(dept_no) && identity != null && !"".equals(identity) && parentid != null && !"".equals(parentid)){
				String sql="insert into user(realname,userUM,username,password,dept_no,identity,parentid,gender,email) values(?,?,?,?,?,?,?,?,?)";
				Object[] params={realname,userUM,username,password,dept_no,identity,parentid,gender,email};
				DB mydb=new DB();
				mydb.doPstm(sql, params);
				mydb.closed();
				
				message = "用户注册成功！";
				urladdress = SiteUrl.readUrl("user.preLogin");
				return SUCCESS;
			}
			message = "注册超时！";
			return INPUT;
		}
	}
}