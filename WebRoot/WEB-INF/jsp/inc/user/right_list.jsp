<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.liyao.com/taskManage" prefix="privilege"%>
<html>
<body>
  <table style="width: 100%">
    <tr>
      <td style="width: 5%"> <div style="text-align: center;color: #FFFFFF">ID</div></td>
      <td style="width: 10%"> <div style="text-align: center;color: #FFFFFF">用户UM号</div></td>
      <td style="width: 10%"> <div style="text-align: center;color: #FFFFFF">用户名</div></td>
      <td style="width: 10%"> <div style="text-align: center;color: #FFFFFF">真实姓名</div></td>
      <td style="width: 10%"> <div style="text-align: center;color: #FFFFFF">性别</div></td>
      <td style="width: 10%"> <div style="text-align: center;color: #FFFFFF">邮箱</div></td>
      <td style="width: 10%"> <div style="text-align: center;color: #FFFFFF">所属部门</div></td>
      <td style="width: 10%"> <div style="text-align: center;color: #FFFFFF">直接上级</div></td>
      <td style="width: 15%"> <div style="text-align: center;color: #FFFFFF">操作</div></td>
      <td style="width: 10%"> <div style="text-align: center;color: #FFFFFF">权限设置</div></td>
      
    </tr>
	<!---------------------------LOOP START------------------------------>
	<c:forEach items="${pageView.records}" var="entry">
	    <tr>
	      <td style="text-align: center;color: #FFFFFF">${entry.userno }</td>
		  <td style="text-align: center;color: #FFFFFF">${entry.userUM }</td>
		  <td style="text-align: center;color: #FFFFFF">${entry.username }</td>
		  <td style="text-align: center;color: #FFFFFF">${entry.realname }</td>
		  <td style="text-align: center;color: #FFFFFF">${entry.gender.name }</td>
		  <td style="text-align: center;color: #FFFFFF">${entry.email }</td>
		  <td style="text-align: center;color: #FFFFFF">${entry.dept.deptname }</td>
		  <td style="text-align: center;color: #FFFFFF">
		  		${entry.parent== null ? "无上级" : entry.parent.realname}
		  </td>
		 
		  <td style="text-align: center">
			  <privilege:permission module="user" privilege="delete">
			  	<a href="/user/user_delete?userno=${entry.userno }" onclick="return confirm('你确定要删除该用户吗？')"><font style="color: DBB743">删除</font></a> 
			  </privilege:permission>
		  </td>
		  
		  <td style="text-align: center">
			  <privilege:permission module="user" privilege="privilegeSet">
			  	<a href="/user/user_setPrivilegeUI?userno=${entry.userno }"><font style="color: 74E5E5">设置权限</font></a> 
			  </privilege:permission>
		  </td>
		</tr>
	</c:forEach>
  </table>
</body>
</html>