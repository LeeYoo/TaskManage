<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.List"%>
    <%@page import="java.util.Date"%>
    <%@page import="com.user.entity.User"%>
    <%@page import="com.work.entity.Work"%>
    <%@page import="com.chart.entity.ChartInfo"%>
    <%@page import="com.paging.entity.PageView"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://www.liyao.com/taskManage" prefix="privilege"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%
	HttpSession sessions=request.getSession();
	User user = (User)sessions.getAttribute("user");
	Integer identity=(Integer)sessions.getAttribute("identity");
	Work work = (Work)request.getAttribute("work");
	PageView<ChartInfo> pageview = (PageView<ChartInfo>)request.getAttribute("pageView");
%>
</head>
<body>
	<div class="right_head_out">
		<div class="right_head_in">
		  hello，<%=user.getRealname() %>  先生/女士，当前<font color="yellow" size="5">【任务】</font>的进度详情如下：
			&nbsp;&nbsp;&nbsp;&nbsp;
			<font size="4">当前进度
				<font color="red" size="4">
					【<%
						if(work.getProgress() == null){
							out.print("未开始");
						}else{
							out.print(work.getProgress()+"%");
						}
					%>】
				</font>
			</font>
			<font size="4">任务状态
				<font color="red" size="4">
					【<%
						if(work.getProgress() == null){
							out.print("未开始");
						}else{
							if(work.getProgress() == 100){
								List<ChartInfo> list = pageview.getRecords();
								for (int i = 0; i < list.size(); i++)
				                {
				                	if(list.get(i).getProgress() == 100){
				                		Date date = list.get(i).getUpdateTime();
				                		if(date.before(work.getPlaytime())){
				                			out.print("提前完成");
				                			break;
				                		}
				                		if(date.after(work.getPlaytime())){
				                			out.print("延迟完成");
				                			break;
				                		}
				                	}
				                }
							}else{
								if(work.getProgress() > 0 && work.getProgress() < 100){
									out.print("正在进行");
								}
								if(work.getProgress() < 0 || work.getProgress() >100){
									out.print("不正常");
								}
							}
						}
					%>】
				</font>
			</font>
			<privilege:permission module="score" privilege="add">
				<a href="/user/score_scoreUI?workId=<%=work.getWorkId() %>" ><font style="color: 5CD242;float: right" size="5">任务评分</font></a>
			</privilege:permission>
			<div style="clear:both;width:100%;"></div>
		</div>
		<div class="right_list">
			<!-- chart_line.jsp -->
			<jsp:include flush="true" page="/WEB-INF/jsp/inc/chart/chart_line.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>