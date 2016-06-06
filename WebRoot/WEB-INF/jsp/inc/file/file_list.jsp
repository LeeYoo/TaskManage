<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
  <table>
    <tr>
      <td style="width: 16%" bgcolor="791FDE"> <div style="text-align: center;color: #FFFFFF">序号</div></td>
      <td style="width: 12%" bgcolor="791FDE"> <div style="text-align: center;color: #FFFFFF">文件名称</div></td>
      <td style="width: 12%" bgcolor="791FDE"> <div style="text-align: center;color: #FFFFFF">所属文件夹</div></td>
	  <td style="width: 10%" bgcolor="791FDE"> <div style="text-align: center;color: #FFFFFF">上传文件</div></td>
	  <td style="width: 15%" bgcolor="791FDE"><div style="text-align: center;color: #FFFFFF">备注说明</div></td>
	  <td style="width: 20%" bgcolor="791FDE"><div style="text-align: center;color: #FFFFFF">上传者</div></td>
	  <td style="width: 10%" bgcolor="791FDE"><div style="text-align: center;color: #FFFFFF">重命名</div></td>
	  <td style="width: 5%" bgcolor="791FDE" ><div style="text-align: center;color: #FFFFFF">删除</div></td>
    </tr>
	<!---------------------------LOOP START------------------------------>
	<c:forEach items="${pageView.records}" var="entry">
	    <tr>
	      <td style="text-align: center;color: #FFFFFF;bgcolor:f5f5f5">${entry.fileId }</td>
		  <td style="text-align: center;color: #FFFFFF;bgcolor:f5f5f5">
		  	<a href="/user/fileList?parentId=${entry.fileId }" style="text-decoration:none">
		  		<font style="color: #FFFFFF">${entry.fileName }</font>
		  	</a>
		  </td>
		  <td style="text-align: center;color: #FFFFFF">
		  	<c:if test="${!empty entry.parent}">${entry.parent.fileName}</c:if>
		  	<c:if test="${empty entry.parent}">根目录</c:if>
		  </td>
		  <td style="text-align: center;color: #FFFFFF">
		  	<a href="/control/product/type/manage?method=addUI&parentId=${entry.fileId}" style="text-decoration:none">
	  			<font style="color: DBB743">上传</font>
	  		</a>
		  </td>
		  <td style="text-align: center;color: #FFFFFF">${entry.fileNote }</td>
		  <td style="text-align: center;color: #FFFFFF">${entry.user.realname }（${entry.user.dept.deptname }）</td>
		  
		  <td style="text-align: center">
		  	<a href="/user/preEditUIAction?workId=${entry.fileId }"><img src="/img/icon-edit.gif" /> </a> 
		  </td>
		  <td style="text-align: center;color: white">
		  	<a href="/user/delwork?workId=${entry.fileId }" onclick="return confirm('你确定要删除这条记录吗？')" style="text-decoration:none">
		  	<font style="color: FA2408">删除</font>
		  	</a> 
		  </td>
		</tr>
	</c:forEach>
	<!---------------------------FOOT START------------------------------>
	<tr> 
      <td colspan="8" align="center">
	    <input name="AddDic" type="button" style="background-color:791FDE;color: #FFFFFF;padding: 10px;font-size: 16px" id="AddDic" onClick="javascript:window.location.href='/control/product/type/manage?method=addUI&parentId=${param.parentId}'" value="新建文件夹" /> &nbsp;&nbsp;
		<input name="query" type="button" style="background-color:791FDE;color: #FFFFFF;padding: 10px;font-size: 16px" id="query" onClick="javascript:window.location.href='/control/product/type/manage?method=queryUI'" value=" 查 询 " /> &nbsp;&nbsp;
      </td>
    </tr>
  </table>
</body>
</html>