<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="com.user.entity.User"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="JavaScript">
	//到指定的分页页面
	function topage(page){
		var form = document.forms[0];
		form.page.value=page;
		form.submit();
	}
</script>
<%
	HttpSession sessions=request.getSession();
	User user = (User)sessions.getAttribute("user");
	Integer identity=(Integer)sessions.getAttribute("identity");
%>
</head>
<body>
	<div class="right_head_out">
		<div class="right_head_in">
		  hello，<%=user.getRealname() %>  先生/女士，您当前<font color="green" size="5">【已完成】</font>的工作如下：
		</div>
	<s:form action="/user/YesWork" method="post">
		<s:hidden name="page" /> 
		<div class="right_list">
			<!-- welcome_list.jsp -->
			<jsp:include flush="true" page="/WEB-INF/jsp/inc/task/right_list.jsp"></jsp:include>
		</div>
		<div class="right_fenye">
			<!-- fenye.jsp -->
			<%@ include file="/WEB-INF/jsp/inc/share/fenye.jsp" %>
		</div>
	</s:form>
	</div>
	<div style="clear:both;width:100%;"></div>
</body>
</html>