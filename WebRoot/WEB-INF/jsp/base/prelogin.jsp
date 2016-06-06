<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="com.dept.entity.Dept"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户登录</title>
    <link rel="stylesheet" href="/style/style.css">
	<link rel="stylesheet" href="/style/fort.min.css">
    <script type="text/javascript" src="/js/ripple.js"></script>
    <script type="text/javascript" src="/js/fort.min.js"></script>
    <script type="text/javascript" src="/js/jquery.js"></script>
	<script type="text/javascript" src="/js/jquery.MyFloatingBg.js"></script>
  </head>
 <body id="demoDiv2" bg="/img/star.jpg">
	<div>
		<!-- head.jsp -->
		<jsp:include flush="true" page="/WEB-INF/jsp/inc/share/head.jsp"></jsp:include>
		
		<div class="form-wrapper">
	  		<div class="top"><div class="colors"></div></div>
				<h2 style="color: white">用户登录</h2>
					<form name="form" action="/system/login" method="post">
						<div class="form">
							<div class="form-item">
								<input type="text" name="username" required placeholder="Username" autocomplete="off"/>
							</div>
							<div class="form-item">
								<input type="password" name="password" required placeholder="Password" autocomplete="off"/>
							</div>
							<div class="form-item">
								<select name="deptno" required>
									<option value="">-----所属部门-----</option>
									<%
									ArrayList al = (ArrayList)request.getAttribute("Resultlist");//要强转成ArrayList类型
									for(int i=0;i<al.size();i++){
									   Dept dept = (Dept)al.get(i);
									%>
									<option value="<%=i+1 %>"><%=dept.getDeptname() %></option>
									<%}%>
								</select>  
							</div> 
							<div class="form-item">
								<select name="identity" required> 
								   <option value="">-----您的身份-----</option>
							       <option value="1">部门总</option> 
							       <option value="2">室经理</option> 
							       <option value="3">室员工</option> 
							   </select>  
							</div> 
			 				<div class="form-item" style="color: red">
								${message}
							</div>
							<div class="button-panel">
								<input type="submit" class="button" title="Sign In" value="GOTO...">
				      		</div>
						</div>
					</form>
		</div>
		<script>
			solid();
		</script>
		
		<!-- foot.jsp -->
		<jsp:include flush="true" page="/WEB-INF/jsp/inc/share/foot.jsp"></jsp:include>
		
		<script type="text/javascript" src="ripple.js"></script>
	</div>
 </body>
</html>