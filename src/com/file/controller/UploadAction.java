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
//���ļ��ϴ���
@Controller		//Ĭ��bean�������ǣ�"uploadAction"
@Scope("prototype")
public class UploadAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private Integer workId;									//������ID��
	private String workname;								//���������ơ�
	private Date begintime;									//������ʼʱ�䡿
	private Date playtime;									//���ƻ����ʱ�䡿
	private Date completetime;								//��ʵ�����ʱ�䡿
	private NumberSta sta = NumberSta.NO;			//�����״̬��
	private GrandNum grand = GrandNum.Simple;	//�����񼶱�
	private Dept dept;			//���������š�
	private User user;			//�������ˡ�
	private String info;			//����ע��Ϣ��
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
	
	//��������൱�е�execute����(ִ�е���˼)
	public String execute() throws Exception {
		
		//�����õ�request,response,session,context����
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sessions=request.getSession();
		String username=(String)sessions.getAttribute("username");
		Integer identity=(Integer)sessions.getAttribute("identity");
		Integer deptno=(Integer)sessions.getAttribute("deptno");
		
		StringBuilder sb = new StringBuilder("o.user.username=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(username);	//��?1��������ֵ
		
		sb.append(" and o.sta=?2");
		params.add(NumberSta.NO);
		
		//����PageView���󲢳�ʼ����ע�⣺��ǰҳ������������������������ġ���>������Ҫ�õ�formbean����
		PageView<Work> pageView = new PageView<Work>(8, this.getPage());	//������ÿҳ��ʾ��¼������ǰҳ��
		int firstindex = (pageView.getCurrentpage()-1) * pageView.getMaxresult();//��ȡ����ʼ����
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("workId", "asc");
		
		QueryResult<Work> qr = workService.getScrollData(Work.class, 
				firstindex, pageView.getMaxresult(), sb.toString(), params.toArray(),orderby);
		pageView.setQueryResult(qr);//set�����ڲ������������ã��Ѿ���������ҳ������������ֵ
		request.setAttribute("pageView", pageView);//����װ�õ�����pageview�����ŵ�request��
		return SUCCESS;
	}
}