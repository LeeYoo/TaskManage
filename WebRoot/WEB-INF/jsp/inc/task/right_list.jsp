<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.liyao.com/taskManage" prefix="privilege"%>
<html>
<body>
  <table>
    <tr>
      <td style="width: 5%"> <div style="text-align: center;color: #FFFFFF">序号</div></td>
      <td style="width: 10%"> <div style="text-align: center;color: #FFFFFF">工作名称</div></td>
      <td style="width: 5%"> <div style="text-align: center;color: #FFFFFF">级别</div></td>
      <td style="width: 5%"> <div style="text-align: center;color: #FFFFFF">类型</div></td>
	  <td style="width: 10%"> <div style="text-align: center;color: #FFFFFF">计划完成</div></td>
	  <!-- <td style="width: 10%"><div style="text-align: center;color: #FFFFFF">开始时间</div></td> -->
	  <!-- <td style="width: 10%"> <div style="text-align: center;color: #FFFFFF">实际完成</div></td> -->
	  <td style="width: 10%"> <div style="text-align: center;color: #FFFFFF">完成进度(%)</div></td>	  
	  <!-- 【存在异步交互显示的问题，数据不同步】
	  	<td style="width: 7%"> <div style="text-align: center;color: #FFFFFF">任务状态</div></td> 
	  -->
	  <td style="width: 8%"> <div style="text-align: center;color: #FFFFFF">所属部门</div></td>
	  <td style="width: 5%"> <div style="text-align: center;color: #FFFFFF">负责人</div></td>
	  <td style="width: 5%"> <div style="text-align: center;color: #FFFFFF">发起者</div></td>
	  <td style="width: 5%"> <div style="text-align: center;color: #FFFFFF">更新</div></td>
	  <td style="width: 5%"> <div style="text-align: center;color: #FFFFFF">删除</div></td>
	  <td style="width: 10%"> <div style="text-align: center;color: #FFFFFF">进度详情</div></td>
	  <td style="width: 7%"> <div style="text-align: center;color: #FFFFFF">任务评分</div></td>
    </tr>
	<!---------------------------LOOP START------------------------------>
	<c:forEach items="${pageView.records}" var="entry">
	    <tr>
	      <td style="text-align: center;color: #FFFFFF">${entry.workId }</td>
		  <td style="text-align: center;color: #FFFFFF">${entry.workname }</td>
		  <td style="text-align: center;color: #FFFFFF">${entry.grand.name }</td>
		  <td style="text-align: center;color: #FFFFFF">${entry.type.name }</td>
		  <td style="text-align: center;color: #FFFFFF">${entry.playtime }</td>
		  <%-- <td style="text-align: center;color: #FFFFFF">${entry.begintime }</td> --%>
		  <%-- <td style="text-align: center;color: #FFFFFF">${entry.completetime }</td> --%>
		  <td style="text-align: center;color: #FFFFFF">${entry.progress }%</td>
		  <%-- <td style="text-align: center;color: #FFFFFF">${entry.sta.name }</td> --%>
		  <td style="text-align: center;color: #FFFFFF">${entry.dept.deptname }</td>
		  <td style="text-align: center;color: #FFFFFF">${entry.user.realname }</td>
		  <td style="text-align: center;color: #FFFFFF">${entry.info }</td>

		  <td style="text-align: center">
		  	<a href="/user/task_editUI?workId=${entry.workId }"><font style="color: 74E5E5">修改</font></a> 
		  </td>
		  <td style="text-align: center;color: white">
		  	<a href="/user/task_delete?workId=${entry.workId }" onclick="return confirm('你确定要删除这条记录吗？')"><font style="color: DBB743">删除</font></a> 
		  </td>
		  <td style="text-align: center;color: white">
		  	<privilege:permission module="chart" privilege="view">
		  		<a href="/user/chart?workId=${entry.workId }" ><font style="color: 5CD242">查看</font></a>
		  	</privilege:permission> 
		  </td> 
		  <td style="text-align: center;color: #FFFFFF">
		  	<privilege:permission module="score" privilege="view">
		  		${entry.score.score }  分
		  	</privilege:permission>
		  </td>
		</tr>
	</c:forEach>
  </table>
</body>
</html>