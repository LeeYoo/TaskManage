<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<form name="form" action="/user/score_add?workId=${work.workId }&userno=${user.getUserno()}" method=post onsubmit="return checkForm(this)">
			<div class="form">
				<div class="form-item">
					<input type="hidden"  required readonly placeholder="任务ID" autocomplete="off" value="${workId} "/>
				</div>
				<div class="form-item">
					<input type="text"  required readonly placeholder="任务名称" autocomplete="off" value="${workname} "/>
				</div>
				<div class="form-item">
					<input type="text"  required readonly placeholder="任务完成进度" autocomplete="off" value="${progress} %"/>
				</div>
				<div class="form-item">
					<input type="text"  required readonly placeholder="负责人" autocomplete="off" value="${user.getRealname()} "/>
				</div>
				<div class="form-item">
					<input type="text" name="score" required placeholder="任务评分" autocomplete="off" />
				</div>
				<div class="button-panel">
					<input type="submit" class="button" title="点击" value="确定评分">
	      		</div>
			</div>
		</form>
	</div>	
	<script>
	solid();
	</script>
</body>
</html>