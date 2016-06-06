<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.user.entity.User"%>
<%@page import="com.dept.entity.Dept"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="/style/calendar.css" >
<script type="text/javascript" src="/js/calendar.js" ></script>  
<script type="text/javascript" src="/js/calendar-zh.js" ></script>
<script type="text/javascript" src="/js/calendar-setup.js"></script>
</head>
<body>
	<div class="form-wrapper">
		<div class="top"><div class="colors"></div></div>
		<form name="form" action="/user/Fixwork" method="post" onsubmit="return checkForm(this)">
			<div class="form">
				<div class="form-item">
					<input type="text" name="workname" required placeholder="任务名称" autocomplete="off"/>
				</div>
				<div class="form-item">
					<select name="dept_no" required>
						<option value="">-----所属部门-----</option>
						<%
						ArrayList al2 = (ArrayList)request.getAttribute("Resultlist2");//要强转成ArrayList类型
						for(int i=0;i<al2.size();i++){
						   Dept dept = (Dept)al2.get(i);
						%>
						<option value="<%=i+1 %>"><%=dept.getDeptname() %></option>
						<%}%>
					</select>  
				</div>
				<div class="form-item">
					<select name="grand" required> 
				       <option value="">-----任务级别-----</option>
				       <option value="Important">重要</option> 
				       <option value="Simple">普通</option>  
				   </select> 
				</div>
				<div class="form-item"> 
				   <select name="type" required> 
				       <option value="">-----任务类别-----</option>
				       <option value="huodong">活动</option> 
				       <option value="tongzhi">通知</option>  
				       <option value="huiyi">会议</option>  
				       <option value="jiancha">检查</option>  
				       <option value="peixun">培训</option>  
				       <option value="qita">其他</option>  
				   	</select> 
				</div>
				<div class="form-item">
					<input type="text" id="EntTime" name="playtime" required readonly placeholder="任务计划完成时间" autocomplete="off" onclick="return showCalendar('EntTime', 'y-mm-dd');"  />
				</div>
				<div class="form-item">
					<select name="user_no" required>
						<option value="">-----任务负责人-----</option>
						<%
						ArrayList al = (ArrayList)request.getAttribute("Resultlist");//要强转成ArrayList类型
						for(int i=0;i<al.size();i++){
						   User user = (User)al.get(i);
						%>
						<option value="<%=user.getUserno()%>"><%=user.getRealname()+" "+user.getUserUM() %></option>
						<%}%>
					</select>  
				</div>
				<div class="form-item">
					<input type="text" name="info" required  readonly value="${user.getRealname() }" placeholder="任务发起人" autocomplete="off" />
				</div>
				<div class="form-item" style="color: red">
					${message}
				</div>
				<div class="button-panel">
					<input type="submit" class="button" title="点击" value="开始指派...">
	      		</div>
			</div>
		</form>
	</div>	
	<script>
	solid();
	</script>
</body>
</html>