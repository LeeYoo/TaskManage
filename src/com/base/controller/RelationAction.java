package com.base.controller;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.dept.entity.Dept;
import com.opensymphony.xwork2.ActionSupport;
import com.paging.entity.PageView;
import com.paging.entity.QueryResult;
import com.user.entity.User;
import com.user.service.UserService;
//����Ա�ṹ��ѯ��
@Controller		//Ĭ��bean�������ǣ�relationAction
@Scope("prototype")
public class RelationAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private Dept dept;			
	private User user;			
	private int page;			
	
	public RelationAction() {}
	
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getPage() {
		return page<1 ? 1 : page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	@Resource UserService userService;
	
	//��������൱�е�execute����(ִ�е���˼)
	public String execute() throws Exception {
		
		//�����õ�request,response,session,context����
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sessions=request.getSession();
		User user = (User)sessions.getAttribute("user");
		String username=(String)sessions.getAttribute("username");
		
		StringBuilder sb = new StringBuilder("o.parent.userno=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(user.getUserno());	//��?1��������ֵ
		
		//����PageView���󲢳�ʼ����ע�⣺��ǰҳ������������������������ġ���>������Ҫ�õ�formbean����
		PageView<User> pageView = new PageView<User>(8, this.getPage());	//������ÿҳ��ʾ��¼������ǰҳ��
		int firstindex = (pageView.getCurrentpage()-1) * pageView.getMaxresult();//��ȡ����ʼ����
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("userno", "asc");
		
		QueryResult<User> qr = userService.getScrollData(User.class, 
				firstindex, pageView.getMaxresult(), sb.toString(), params.toArray(),orderby);
		pageView.setQueryResult(qr);//set�����ڲ������������ã��Ѿ���������ҳ������������ֵ
		
		request.setAttribute("pageView", pageView);//����װ�õ�����pageview�����ŵ�request��
		return SUCCESS;
	}
}