<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
  <table>
    <tr>
      <td style="width: 10%"> <div style="text-align: center;color: #FAF703">下属姓名</div></td>
      <td style="width: 10%"> <div style="text-align: center;color: #FAF703">下属性别</div></td>
      <td style="width: 10%"> <div style="text-align: center;color: #FAF703">下属UM号</div></td>
      <td style="width: 10%"> <div style="text-align: center;color: #FAF703">联系方式</div></td>
    </tr>
	<!---------------------------LOOP START------------------------------>
	<c:forEach items="${pageView.records}" var="entry">
	    <tr>
	      <td style="text-align: center;color: #FFFFFF">${entry.realname }</td>
	      <td style="text-align: center;color: #FFFFFF">${entry.gender.name }</td>
	      <td style="text-align: center;color: #FFFFFF">${entry.userUM }</td>
	      <td style="text-align: center;color: #FFFFFF">${entry.email }</td>
		</tr>
	</c:forEach>
  </table>
</body>
</html>