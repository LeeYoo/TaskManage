<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="com.user.entity.User"%>
    <%@page import="com.dept.entity.Dept"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户注册</title>
    <link rel="stylesheet" href="/style/style.css">
	<link rel="stylesheet" href="/style/fort.min.css">
    <script type="text/javascript" src="/js/ripple.js"></script>
    <script type="text/javascript" src="/js/fort.min.js"></script>
    <script type="text/javascript" src="/js/jquery.js"></script>
	<script type="text/javascript" src="/js/jquery.MyFloatingBg.js"></script>
	<script type="text/javascript">
	    //验证表单（两次密码是否一致，客户端校验快，不必在服务器端校验） 
		function checkForm(Form) {
		 	if(!(Form.password.value == Form.repassword.value)){
				alert("两次输入的密码不一致！");
				Form.repassword.focus();
				return false;
			}
			return true;
		}
	</script>
  </head>
 <body id="demoDiv2" bg="/img/star.jpg">
	<div>
		<!-- head.jsp -->
		<jsp:include flush="true" page="/WEB-INF/jsp/inc/share/head.jsp"></jsp:include>
		
		<div class="form-wrapper" >
		  	<div class="top"><div class="colors"></div></div>
			<h2 style="color: white">用户注册</h2>
			<form name="form" action="/system/regist" method="post" onsubmit="return checkForm(this)">
				<div class="form">
					<div class="form-item">
						<input type="text" name="realname" required placeholder="真实姓名" autocomplete="off"/>
					</div>
					<div class="form-item">
						<input type="text" name="userUM" required placeholder="用户UM号" autocomplete="off"/>
					</div>
					<div class="form-item">
						<input type="text" name="username" required placeholder="用户名" autocomplete="off"/>
					</div>
					<div class="form-item">
						<input type="text" name="email" required placeholder="联系方式(电话/邮箱)" autocomplete="off"/>
					</div>
					<div class="form-item">
						<input type="password" name="password" required placeholder="输入密码" autocomplete="off"/>
					</div>
					<div class="form-item">
						<input type="password" name="repassword" required placeholder="确认密码" autocomplete="off"/>
					</div>
					<div class="form-item">
						<select name="gender" required> 
					       <option value="">-----性别-----</option>
					       <option value="MAN">男</option> 
					       <option value="WOMEN">女</option>
					   </select>  
					</div> 
					<div class="form-item">
						<select name="deptno" required>
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
						<select name="identity" required> 
					       <option value="">-----身份角色-----</option>
					       <option value="1">部门总</option> 
					       <option value="2">室经理</option> 
					       <option value="3">室员工</option> 
					   </select>  
					</div> 
					<div class="form-item">
						<select name="parent" >
							<option value="">-----您的上级-----</option>
							<%
							ArrayList al = (ArrayList)request.getAttribute("Resultlist");//要强转成ArrayList类型
							for(int i=0;i<al.size();i++){
							   User user = (User)al.get(i);
							%>
							<option value="<%=user.getUserno()%>"><%=user.getRealname()+" "+user.getUserUM() %></option>
							<%}%>
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