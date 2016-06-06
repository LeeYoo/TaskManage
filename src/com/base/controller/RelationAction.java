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
//【人员结构查询】
@Controller		//默认bean的名称是：relationAction
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
	
	//覆盖这个类当中的execute方法(执行的意思)
	public String execute() throws Exception {
		
		//首先拿到request,response,session,context对象
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sessions=request.getSession();
		User user = (User)sessions.getAttribute("user");
		String username=(String)sessions.getAttribute("username");
		
		StringBuilder sb = new StringBuilder("o.parent.userno=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(user.getUserno());	//给?1参数设置值
		
		//创建PageView对象并初始化！注意：当前页参数，是由请求参数传过来的——>所以需要得到formbean对象！
		PageView<User> pageView = new PageView<User>(8, this.getPage());	//参数：每页显示记录数，当前页！
		int firstindex = (pageView.getCurrentpage()-1) * pageView.getMaxresult();//获取到开始索引
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("userno", "asc");
		
		QueryResult<User> qr = userService.getScrollData(User.class, 
				firstindex, pageView.getMaxresult(), sb.toString(), params.toArray(),orderby);
		pageView.setQueryResult(qr);//set方法内部存在连环调用！已经计算了总页数和两个索引值
		
		request.setAttribute("pageView", pageView);//将封装好的整个pageview对象存放到request中
		return SUCCESS;
	}
}