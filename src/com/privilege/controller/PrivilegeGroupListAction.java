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
import com.privilege.entity.PrivilegeGroup;
import com.privilege.service.PrivilegeGroupService;
import com.user.entity.User;
//【权限组查询_分页列表】
@Controller		//默认bean的名称是：privilegeGroupListAction
@Scope("prototype")
public class PrivilegeGroupListAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private String groupId;		//【权限组ID】
	private String name;				//【权限组名称】
	
	private String query;
	private int page;			
	
	public PrivilegeGroupListAction() {}
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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

	@Resource PrivilegeGroupService privilegeGroupService;
	
	//覆盖这个类当中的execute方法(执行的意思)
	public String execute() throws Exception {
		
		//首先拿到request,response,session,context对象
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sessions=request.getSession();
		Integer identity=(Integer)sessions.getAttribute("identity");
		Integer deptno=(Integer)sessions.getAttribute("deptno");
		User user = (User)sessions.getAttribute("user");
		
		
		//创建PageView对象并初始化！注意：当前页参数，是由请求参数传过来的——>所以需要得到formbean对象！
		PageView<PrivilegeGroup> pageView = new PageView<PrivilegeGroup>(8, this.getPage());//参数：每页显示记录数，当前页！		
		int firstindex = (pageView.getCurrentpage()-1) * pageView.getMaxresult();//获取到开始索引
		
		QueryResult<PrivilegeGroup> qr = privilegeGroupService.getScrollData(PrivilegeGroup.class, 
				firstindex, pageView.getMaxresult(), null,null,null);
		pageView.setQueryResult(qr);//set方法内部存在连环调用！已经计算了总页数和两个索引值
		request.setAttribute("pageView", pageView);//将封装好的整个pageview对象存放到request中
		return SUCCESS;
	}
}