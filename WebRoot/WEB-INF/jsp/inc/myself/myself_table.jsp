<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.user.entity.User"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="/style/calendar.css" >
<script type="text/javascript" src="/js/calendar.js" ></script>  
<script type="text/javascript" src="/js/calendar-zh.js" ></script>
<script type="text/javascript" src="/js/calendar-setup.js"></script>
<script type="text/javascript">
      //验证表单 
      function checkForm(Form) {
	   if(!(Form.newpassword.value == Form.repassword.value)){
			  alert("两次输入的密码不一致！");
			  Form.repassword.focus();
			  return false;
	   }
	   return true;
	}
  </script>
</head>
<body>
	<div class="form-wrapper">
		<div class="top"><div class="colors"></div></div>
		<form name="form" action="/user/myselfUpdate?userno=${user.userno }" method="post" onsubmit="return checkForm(this)">
			<div class="form">
				<div class="form-item">
					<input type="text" value="${user.realname }" name="realname" readonly placeholder="真实姓名" autocomplete="off"/>
				</div>
				<div class="form-item">
					<input type="text" value="${user.username }" name="username" readonly placeholder="用户名" autocomplete="off"/>
				</div>
				<div class="form-item">
					<input type="text" value="${user.userUM }" name="userUM" readonly placeholder="用户UM号" autocomplete="off"/>
				</div>
				<div class="form-item">
					<input type="password" name="newpassword" required placeholder="请输入新密码" autocomplete="off" />
				</div>
				<div class="form-item">
					<input type="password" name="repassword" required placeholder="请确认新密码" autocomplete="off" />
				</div>
				<div class="form-item">
					<input type="text" value="${user.email }" name="email" required placeholder="联系方式（电话/邮箱）" autocomplete="off"/>
				</div>
				<div class="form-item" style="color: red">
					${message}
				</div>
				<div class="button-panel">
					<input type="submit" class="button" title="点击" value="确定修改">
	      		</div>
			</div>
		</form>
	</div>
	<script>
	solid();
	</script>
</body>
</html>