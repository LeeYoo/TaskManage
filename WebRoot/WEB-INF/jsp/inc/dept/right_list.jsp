<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.liyao.com/taskManage" prefix="privilege"%>
<html>
<body>
  <table style="width: 100%">
    <tr>
      <td style="width: 10%"> <div style="text-align: center;color: #FFFFFF">部门ID</div></td>
      <td style="width: 30%"> <div style="text-align: center;color: #FFFFFF">部门名称</div></td>
      <td style="width: 30%"> <div style="text-align: center;color: #FFFFFF">编辑部门信息</div></td>
      <td style="width: 30%"> <div style="text-align: center;color: #FFFFFF">删除部门</div></td>
    </tr>
	<!---------------------------LOOP START------------------------------>
	<c:forEach items="${pageView.records}" var="entry">
	    <tr>
	      <td style="text-align: center;color: #FFFFFF">${entry.deptID }</td>
		  <td style="text-align: center;color: #FFFFFF">${entry.deptname }</td>
		  <td style="text-align: center">
			  <privilege:permission module="dept" privilege="edit">
			  	<a href="/user/dept_editUI?deptID=${entry.deptID }" ><font style="color: 74E5E5">编辑</font></a>
			  </privilege:permission>
		  </td>
		  <td style="text-align: center">  
			  <privilege:permission module="dept" privilege="delete">
			  	<a href="/user/dept_delete?deptID=${entry.deptID }" onclick="return confirm('你确定要删除该部门吗？')"><font style="color: DBB743">删除</font></a> 
			  </privilege:permission>
		  </td>
		</tr>
	</c:forEach>
  </table>
</body>
</html>