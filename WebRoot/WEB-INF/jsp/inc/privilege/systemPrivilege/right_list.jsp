<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
  <table style="width: 100%">
    <tr>
      <td style="width: 30%"> <div style="text-align: center;color: #FFFFFF">部门模块</div></td>
      <td style="width: 30%"> <div style="text-align: center;color: #FFFFFF">权限值</div></td>
      <td style="width: 40%"> <div style="text-align: center;color: #FFFFFF">权限名称</div></td>
    </tr>
	<!---------------------------LOOP START------------------------------>
	<c:forEach items="${pageView.records}" var="entry">
	    <tr>
	      <td style="text-align: center;color: #FFFFFF">${entry.id.module }</td>
		  <td style="text-align: center;color: #FFFFFF">${entry.id.privilege }</td>
		  <td style="text-align: center;color: #FFFFFF">${entry.name }</td>
		</tr>
	</c:forEach>
  </table>
</body>
</html>