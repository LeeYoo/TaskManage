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
//��ע���(�����������)��
@Controller		//Ĭ��bean�������ǣ�preRegistUIAction
@Scope("prototype")
public class PreRegistUIAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	//��Ȼ��action������Spring�������ǾͿ���ʹ��Spring������ע�빦�ܣ���ҵ��beanע�������
	@Resource UserService userService;//��ע��ҵ��bean����ͨ���ӿ�����ȡ
	@Resource DeptService deptService;
	
	//��������൱�е�execute����(ִ�е���˼)
	public String execute() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		StringBuilder sb = new StringBuilder("o.identity=?1 or o.identity=?2");
		List<Object> params = new ArrayList<Object>();
		params.add(1);	//��?1��������ֵ
		params.add(2);
		
		QueryResult<User> qr = userService.getScrollData(User.class, sb.toString(), params.toArray());
		QueryResult<Dept> qr2 = deptService.getScrollData(Dept.class);
		
		request.setAttribute("Resultlist", qr.getResultlist());		//�Ѳ�ѯ�б�ŵ�request��
		request.setAttribute("Resultlist2", qr2.getResultlist());
		
		return SUCCESS;
	}
}