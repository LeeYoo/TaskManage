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
//���û�ע�᡿
@Controller		//Ĭ��bean�������ǣ�registAction
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

	//��Ȼ��action������Spring�������ǾͿ���ʹ��Spring������ע�빦�ܣ���ҵ��beanע�������
	@Resource UserService userService;//��ע��ҵ��bean����ͨ���ӿ�����ȡ

	public String execute() throws Exception {
		
		//��Ҫ�ж�������û�����UM���Ƿ��Ѿ����ڣ�ͨ��ҵ��bean��ִ��(ǰ������Ҫע��ҵ��bean)
		if(userService.exsit1(username)){
			//�������(��ʾ�û����Ѿ����ڵĴ�����Ϣ�������ص�ע��ҳ��)
			message = "���û����ѱ�ע�ᣡ";
			return INPUT;
		}else if (userService.exsit2(userUM)) {
			message = "��UM���ѱ�ע�ᣡ";
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
			//û���ϼ���ע��
			if(parentid==null || "".equals(parentid)){
				String sql="insert into user(realname,userUM,username,password,dept_no,identity,gender,email) values(?,?,?,?,?,?,?,?)";
				Object[] params={realname,userUM,username,password,dept_no,identity,gender,email};
				DB mydb=new DB();
				mydb.doPstm(sql, params);
				mydb.closed();
				
				message = "�û�ע��ɹ���";
				urladdress = SiteUrl.readUrl("user.preLogin");
				return SUCCESS;
			}//���ϼ���ע��
			else if(dept_no != null && !"".equals(dept_no) && identity != null && !"".equals(identity) && parentid != null && !"".equals(parentid)){
				String sql="insert into user(realname,userUM,username,password,dept_no,identity,parentid,gender,email) values(?,?,?,?,?,?,?,?,?)";
				Object[] params={realname,userUM,username,password,dept_no,identity,parentid,gender,email};
				DB mydb=new DB();
				mydb.doPstm(sql, params);
				mydb.closed();
				
				message = "�û�ע��ɹ���";
				urladdress = SiteUrl.readUrl("user.preLogin");
				return SUCCESS;
			}
			message = "ע�ᳬʱ��";
			return INPUT;
		}
	}
}