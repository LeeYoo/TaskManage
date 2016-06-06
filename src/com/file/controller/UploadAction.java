package com.file.controller;
import java.util.ArrayList;
import java.util.Date;
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
import com.work.entity.GrandNum;
import com.work.entity.NumberSta;
import com.work.entity.Work;
import com.work.service.WorkService;
//【文件上传】
@Controller		//默认bean的名称是："uploadAction"
@Scope("prototype")
public class UploadAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private Integer workId;									//【任务ID】
	private String workname;								//【任务名称】
	private Date begintime;									//【任务开始时间】
	private Date playtime;									//【计划完成时间】
	private Date completetime;								//【实际完成时间】
	private NumberSta sta = NumberSta.NO;			//【完成状态】
	private GrandNum grand = GrandNum.Simple;	//【任务级别】
	private Dept dept;			//【关联部门】
	private User user;			//【负责人】
	private String info;			//【备注信息】
	private String query;
	private int page;			
	
	public UploadAction() {}
	public Integer getWorkId() {
		return workId;
	}
	public void setWorkId(Integer workId) {
		this.workId = workId;
	}
	public String getWorkname() {
		return workname;
	}
	public void setWorkname(String workname) {
		this.workname = workname;
	}
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	public Date getPlaytime() {
		return playtime;
	}
	public void setPlaytime(Date playtime) {
		this.playtime = playtime;
	}
	public Date getCompletetime() {
		return completetime;
	}
	public void setCompletetime(Date completetime) {
		this.completetime = completetime;
	}
	public NumberSta getSta() {
		return sta;
	}
	public void setSta(NumberSta sta) {
		this.sta = sta;
	}
	public GrandNum getGrand() {
		return grand;
	}
	public void setGrand(GrandNum grand) {
		this.grand = grand;
	}
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
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
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

	@Resource WorkService workService;
	
	//覆盖这个类当中的execute方法(执行的意思)
	public String execute() throws Exception {
		
		//首先拿到request,response,session,context对象
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sessions=request.getSession();
		String username=(String)sessions.getAttribute("username");
		Integer identity=(Integer)sessions.getAttribute("identity");
		Integer deptno=(Integer)sessions.getAttribute("deptno");
		
		StringBuilder sb = new StringBuilder("o.user.username=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(username);	//给?1参数设置值
		
		sb.append(" and o.sta=?2");
		params.add(NumberSta.NO);
		
		//创建PageView对象并初始化！注意：当前页参数，是由请求参数传过来的——>所以需要得到formbean对象！
		PageView<Work> pageView = new PageView<Work>(8, this.getPage());	//参数：每页显示记录数，当前页！
		int firstindex = (pageView.getCurrentpage()-1) * pageView.getMaxresult();//获取到开始索引
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("workId", "asc");
		
		QueryResult<Work> qr = workService.getScrollData(Work.class, 
				firstindex, pageView.getMaxresult(), sb.toString(), params.toArray(),orderby);
		pageView.setQueryResult(qr);//set方法内部存在连环调用！已经计算了总页数和两个索引值
		request.setAttribute("pageView", pageView);//将封装好的整个pageview对象存放到request中
		return SUCCESS;
	}
}