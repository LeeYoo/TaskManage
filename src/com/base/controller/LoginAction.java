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
//���û���½��
@Controller		//Ĭ��bean�������ǣ�loginAction
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

	//��Ȼ��action������Spring�������ǾͿ���ʹ��Spring������ע�빦�ܣ���ҵ��beanע�������
	@Resource UserService userService;//��ע��ҵ��bean����ͨ���ӿ�����ȡ
	
	//��������൱�е�execute����(ִ�е���˼)
	public String execute() throws Exception {
		
		//�����õ�request,response,session,context����
		HttpServletRequest request = ServletActionContext.getRequest();
		//��ʼУ�飨���ص��ǲ���ֵ��
		if(userService.validate(username, password, deptno, identity)){
			
			User user = new User();
			user = userService.find(username, password, deptno, identity);
			request.getSession().setAttribute("user",user);
			
			request.getSession().setAttribute("deptno", deptno);
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("identity", identity);
			
			message = "��ӭ����"+user.getRealname()+"  ����/Ůʿ";
			urladdress = SiteUrl.readUrl("manager.welcome");
			return SUCCESS;
		}else if (!(userService.validate3(username, password))) {
			message = "�û�������������";
			return INPUT;
		}else {
			message = "��ƥ����ȷ�Ĳ��Ż���ݣ�";
			return INPUT;
		}	
	}
}