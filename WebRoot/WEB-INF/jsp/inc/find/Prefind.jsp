<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="com.user.entity.User"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="JavaScript">
<!--
	//到指定的分页页面
	function topage(page){
		var form = document.forms[0];
		form.page.value=page;
		form.submit();
	}
//-->
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
	  	hello，<%= (identity==1 ? "部门总" : (identity==2 ? "室经理" : "室员工" )) %> 
	  			<%=user.getRealname() %>  先生/女士
	  			<font color="pink" size="5">【查询任务】:</font>
		</div>
	<s:form action="/user/findWork" method="post">
		<s:hidden name="page" /> 
		<div class="right_list">
			<!-- find_table.jsp -->
			<jsp:include flush="true" page="/WEB-INF/jsp/inc/find/find_table.jsp"></jsp:include>
		</div>
	</s:form>
	</div>
	<div style="clear:both;width:100%;"></div>
</body>
</html>