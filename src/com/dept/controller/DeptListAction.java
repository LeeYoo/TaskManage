package com.dept.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dept.entity.Dept;
import com.dept.service.DeptService;
import com.opensymphony.xwork2.ActionSupport;
import com.paging.entity.PageView;
import com.paging.entity.QueryResult;
import com.privilege.annotation.Permission;
import com.user.entity.User;
//��������Ϣ�б�
@Controller		//Ĭ��bean�������ǣ�deptListAction
@Scope("prototype")
public class DeptListAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private String query;
	private int page;			
	
	public DeptListAction() {}
	
	public int getPage() {
		return page<1 ? 1 : page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}

	@Resource DeptService deptService;
	
	//��������൱�е�execute����(ִ�е���˼)
	/*��Ȩ�����ء���ִ�з���֮ǰ��Ҫע���жϡ�
	 * �������������ע�⣬�����ܹ�׼ȷ�������û���Ϊ���ж��û�Ȩ��
	 * ϸ���ȵ�Ȩ�����أ�ʵʩ��action�ķ����������أ���Ҫʹ�õ�AOP������
	 */
	@Permission(module="dept",privilege="view")	
	public String execute() throws Exception {
		
		//�����õ�request,response,session,context����
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sessions=request.getSession();
		Integer identity=(Integer)sessions.getAttribute("identity");
		Integer deptno=(Integer)sessions.getAttribute("deptno");
		User user = (User)sessions.getAttribute("user");
		
		//����PageView���󲢳�ʼ����ע�⣺��ǰҳ������������������������ġ���>������Ҫ�õ�formbean����
		PageView<Dept> pageView = new PageView<Dept>(8, this.getPage());//������ÿҳ��ʾ��¼������ǰҳ��		
		int firstindex = (pageView.getCurrentpage()-1) * pageView.getMaxresult();//��ȡ����ʼ����
		
		QueryResult<Dept> qr = deptService.getScrollData(Dept.class, 
				firstindex, pageView.getMaxresult(), null,null,null);
		pageView.setQueryResult(qr);//set�����ڲ������������ã��Ѿ���������ҳ������������ֵ
		request.setAttribute("pageView", pageView);//����װ�õ�����pageview�����ŵ�request��
		return SUCCESS;
	}
}
