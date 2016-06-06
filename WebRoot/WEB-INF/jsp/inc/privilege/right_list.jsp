<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.liyao.com/taskManage" prefix="privilege"%>
<html>
<body>
  <table style="width: 100%">
    <tr>
      <td style="width: 10%"> <div style="text-align: center;color: #FFFFFF">权限组ID</div></td>
      <td style="width: 30%"> <div style="text-align: center;color: #FFFFFF">权限组名称</div></td>
      <td style="width: 30%"> <div style="text-align: center;color: #FFFFFF">操作</div></td>
    </tr>
	<!---------------------------LOOP START------------------------------>
	<c:forEach items="${pageView.records}" var="entry">
	    <tr>
	      <td style="text-align: center;color: #FFFFFF">${entry.groupId }</td>
		  <td style="text-align: center;color: #FFFFFF">${entry.name }</td>
		  
		  <td style="text-align: center">
		  	<privilege:permission module="privilegeGroup" privilege="edit">
		  		<a href="/user/privilegeGroup_editUI?groupId=${entry.groupId }"><font style="color: 74E5E5">修改</font></a> 
		  	</privilege:permission>
		  	
		  	<privilege:permission module="privilegeGroup" privilege="delete">
		  		<a href="/user/privilegeGroup_delete?groupId=${entry.groupId }" onclick="return confirm('你确定要删除该权限组吗？')"><font style="color: DBB743">删除</font></a> 
		  	</privilege:permission>
		  </td>
		</tr>
	</c:forEach>
  </table>
</body>
</html>