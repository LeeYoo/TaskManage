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
		<form name="form" action="/user/privilegeGroup_edit" method="post" onsubmit="return checkForm(this)">
			<div class="form">
				<div class="form-item">
					<input type="text" name="name" required placeholder="权限组名称" autocomplete="off" value="${name} "/>
				</div>
				<div>
	  				<!-- 1.首先迭代系统所有的权限 2.然后再迭代出判断用户之前所勾选的权限 3.再判断用户所选择的权限是否等于外面迭代的权限-->
	  				<c:forEach items="${privileges}" var="privilege" varStatus="statu">
						<input type="checkbox" name="privileges" value="${privilege.id.module},${privilege.id.privilege}"
							<c:forEach items="${selectprivileges}" var="sp">
								<c:if test="${sp==privilege}">checked</c:if>
							</c:forEach>
						/>
						${privilege.name}　
						<c:if test="${statu.count%4==0}"><br></c:if>
					 </c:forEach>
				</div>
				<div class="button-panel">
					<input type="submit" class="button" title="点击" value="确定">
	      		</div>
			</div>
		</form>
	</div>	
	<script>
	solid();
	</script>
</body>
</html>