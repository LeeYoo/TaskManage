package com.chart.controller;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chart.entity.ChartInfo;
import com.chart.service.ChartService;
import com.opensymphony.xwork2.ActionSupport;
import com.paging.entity.PageView;
import com.paging.entity.QueryResult;
import com.work.entity.Work;
import com.work.service.WorkService;
//【显示报表的action】
@Controller		//默认bean的名称是：chartDataAction
@Scope("prototype")
public class ChartDataAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private Integer workId;
	
	public Integer getWorkId() {
		return workId;
	}
	public void setWorkId(Integer workId) {
		this.workId = workId;
	}
	
	@Resource ChartService chartService;
	@Resource WorkService workService;
	
	@Override
	public String execute() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		PageView<ChartInfo> pageView = new PageView<ChartInfo>();	//参数：每页显示记录数，当前页！	
		StringBuilder sb = new StringBuilder("o.work.workId=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(workId);	//给?1参数设置值
		QueryResult<ChartInfo> qr = chartService.getScrollData(ChartInfo.class, sb.toString(), params.toArray());
		pageView.setQueryResult(qr);//set方法内部存在连环调用！已经计算了总页数和两个索引值
		request.setAttribute("pageView", pageView);//将封装好的整个pageview对象存放到request中
		
		//_____________【把当前work对象放到request中】以便于页面拿到当前work的【分数】和【状态】等属性
		Integer workId = Integer.parseInt(request.getParameter("workId"));
		Work work = new Work();
		work = workService.find(Work.class, workId);
		request.setAttribute("work",work);		
		
		return SUCCESS;
	}
}
