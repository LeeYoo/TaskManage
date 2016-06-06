<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>权限列表</title>
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
		
		<!-- center.jsp -->
		<jsp:include flush="true" page="/WEB-INF/jsp/inc/share/left.jsp"></jsp:include>
  		<jsp:include flush="true" page="/WEB-INF/jsp/inc/privilege/systemPrivilege/right.jsp"></jsp:include>
		
		<!-- foot.jsp -->
		<jsp:include flush="true" page="/WEB-INF/jsp/inc/share/foot.jsp"></jsp:include>
		
		<script type="text/javascript" src="ripple.js"></script>
	</div>
 </body>
</html>