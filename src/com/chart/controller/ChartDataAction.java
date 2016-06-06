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
//����ʾ�����action��
@Controller		//Ĭ��bean�������ǣ�chartDataAction
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
		PageView<ChartInfo> pageView = new PageView<ChartInfo>();	//������ÿҳ��ʾ��¼������ǰҳ��	
		StringBuilder sb = new StringBuilder("o.work.workId=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(workId);	//��?1��������ֵ
		QueryResult<ChartInfo> qr = chartService.getScrollData(ChartInfo.class, sb.toString(), params.toArray());
		pageView.setQueryResult(qr);//set�����ڲ������������ã��Ѿ���������ҳ������������ֵ
		request.setAttribute("pageView", pageView);//����װ�õ�����pageview�����ŵ�request��
		
		//_____________���ѵ�ǰwork����ŵ�request�С��Ա���ҳ���õ���ǰwork�ġ��������͡�״̬��������
		Integer workId = Integer.parseInt(request.getParameter("workId"));
		Work work = new Work();
		work = workService.find(Work.class, workId);
		request.setAttribute("work",work);		
		
		return SUCCESS;
	}
}
