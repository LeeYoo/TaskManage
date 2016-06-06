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
//����ת��½����(�����������)��
@Controller		//Ĭ��bean�������ǣ�preLoginUIAction
@Scope("prototype")
public class PreLoginUIAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	//��Ȼ��action������Spring�������ǾͿ���ʹ��Spring������ע�빦�ܣ���ҵ��beanע�������
	@Resource DeptService deptService;
	
	//��������൱�е�execute����(ִ�е���˼)
	public String execute() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		QueryResult<Dept> qr = deptService.getScrollData(Dept.class);
		
		request.setAttribute("Resultlist", qr.getResultlist());		//�Ѳ�ѯ�б�ŵ�request��
		
		return SUCCESS;
	}
}