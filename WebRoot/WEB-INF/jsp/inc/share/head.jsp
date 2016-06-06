<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/style/ly_style.css">
</head>
<body>
  <div id="ly-header">
  	<a href="/system/preLogin">
  		<img  class="ly-fl" id="ly-icon" src="/img/logo.jpg" />
  		<h2 class="ly-fl">员工任务管理——分配系统</h2>
  	</a>
  	<span>&nbsp;&nbsp;&nbsp;</span>
	<SCRIPT>
	setInterval("clock.innerHTML=new Date().toLocaleString()+'&nbsp;&nbsp;'+''.charAt(new Date().getDay());",1000)
	</SCRIPT>
	<SPAN id=clock style="color: white"></SPAN>
  	
  	
  	<h3 class="ly-fr" id="ly-exit">
	  	<a href="/system/preRegist">新人注册</a><span></span>
	  	<a href="/system/exit">退出系统</a>
	</h3><br/>
  </div>
</body>
</html>