package com.personal.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.base.Utils.SiteUrl;
import com.opensymphony.xwork2.ActionSupport;
import com.user.entity.Gender;
import com.user.entity.User;
import com.user.service.UserService;
//��������Ϣ�޸ġ�
@Controller		//Ĭ��bean�������ǣ�myselfUpdateAction
@Scope("prototype")
public class MyselfUpdateAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private String userUM;							//��UM�š�
	private String username;							//��������
	private String realname;							//����ʵ���ơ�
	private String password;							//�����롿
	private String newpassword;
	private String repassword;
	private String email;								//�����䡿
	private Gender gender;							//���Ա�
	
	private String message;
	private String urladdress;
	
	public MyselfUpdateAction() {}
	
	public String getUserUM() {
		return userUM;
	}
	public void setUserUM(String userUM) {
		this.userUM = userUM;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
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
	public String getUrladdress() {
		return urladdress;
	}
	public void setUrladdress(String urladdress) {
		this.urladdress = urladdress;
	}

	@Resource UserService userService;//��ע��ҵ��bean����ͨ���ӿ�����ȡ

	public String execute() throws Exception {
			//��Ҫ�ж�������û�����UM���Ƿ��Ѿ����ڣ�ͨ��ҵ��bean��ִ��(ǰ������Ҫע��ҵ��bean)
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession sessions=request.getSession();
			User user = (User)sessions.getAttribute("user");
			
			user.setRealname(realname);
			user.setUsername(username);
			user.setPassword(newpassword);
			user.setEmail(email);
			user.setGender(gender);
			user.setUserUM(userUM);
			
			userService.update(user);
			message = "�޸ĳɹ���";
			urladdress = SiteUrl.readUrl("manager.preMyself");
			return SUCCESS;
	}
}