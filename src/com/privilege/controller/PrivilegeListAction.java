package com.privilege.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;
import com.paging.entity.PageView;
import com.paging.entity.QueryResult;
import com.privilege.entity.SystemPrivilege;
import com.privilege.service.SystemPrivilegeService;
import com.user.entity.User;
//��Ȩ�޲�ѯ_��ҳ�б�
@Controller		//Ĭ��bean�������ǣ�privilegeListAction
@Scope("prototype")
public class PrivilegeListAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private String query;
	private int page;			
	
	public PrivilegeListAction() {}
	
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

	@Resource SystemPrivilegeService privilegeService;
	
	//��������൱�е�execute����(ִ�е���˼)
	public String execute() throws Exception {
		
		//�����õ�request,response,session,context����
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sessions=request.getSession();
		Integer identity=(Integer)sessions.getAttribute("identity");
		Integer deptno=(Integer)sessions.getAttribute("deptno");
		User user = (User)sessions.getAttribute("user");
		
		
		//����PageView���󲢳�ʼ����ע�⣺��ǰҳ������������������������ġ���>������Ҫ�õ�formbean����
		PageView<SystemPrivilege> pageView = new PageView<SystemPrivilege>(8, this.getPage());//������ÿҳ��ʾ��¼������ǰҳ��		
		int firstindex = (pageView.getCurrentpage()-1) * pageView.getMaxresult();//��ȡ����ʼ����
		
		//����ѯ��ʬ��SystemPrivilege���漰�����������������Ա���ʹ�ú������ǵ�dao��ѯ���������£�
		QueryResult<SystemPrivilege> qr = privilegeService.getScrollData_2(SystemPrivilege.class, 
				firstindex, pageView.getMaxresult(), null,null,null);
		
		pageView.setQueryResult(qr);//set�����ڲ������������ã��Ѿ���������ҳ������������ֵ
		request.setAttribute("pageView", pageView);//����װ�õ�����pageview�����ŵ�request��
		return SUCCESS;
	}
}