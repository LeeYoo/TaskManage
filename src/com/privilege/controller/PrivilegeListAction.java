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
//【权限查询_分页列表】
@Controller		//默认bean的名称是：privilegeListAction
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
	
	//覆盖这个类当中的execute方法(执行的意思)
	public String execute() throws Exception {
		
		//首先拿到request,response,session,context对象
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sessions=request.getSession();
		Integer identity=(Integer)sessions.getAttribute("identity");
		Integer deptno=(Integer)sessions.getAttribute("deptno");
		User user = (User)sessions.getAttribute("user");
		
		
		//创建PageView对象并初始化！注意：当前页参数，是由请求参数传过来的——>所以需要得到formbean对象！
		PageView<SystemPrivilege> pageView = new PageView<SystemPrivilege>(8, this.getPage());//参数：每页显示记录数，当前页！		
		int firstindex = (pageView.getCurrentpage()-1) * pageView.getMaxresult();//获取到开始索引
		
		//【查询的尸体SystemPrivilege中涉及到联合主键】，所以必须使用后来覆盖的dao查询方法，如下：
		QueryResult<SystemPrivilege> qr = privilegeService.getScrollData_2(SystemPrivilege.class, 
				firstindex, pageView.getMaxresult(), null,null,null);
		
		pageView.setQueryResult(qr);//set方法内部存在连环调用！已经计算了总页数和两个索引值
		request.setAttribute("pageView", pageView);//将封装好的整个pageview对象存放到request中
		return SUCCESS;
	}
}