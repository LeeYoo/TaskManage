<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.user.entity.User"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="/style/calendar.css" >
<script type="text/javascript" src="/js/calendar.js" ></script>  
<script type="text/javascript" src="/js/calendar-zh.js" ></script>
<script type="text/javascript" src="/js/calendar-setup.js"></script>
<script type="text/javascript">
      //验证表单 
      function checkForm(Form) {
	   if(Form.grand.value == ""){ 
			  alert("请选择任务级别！");
			  Form.grand.focus();
			  return false;
	   }
	   if(Form.type.value == ""){ 
			  alert("请选择任务类别！");
			  Form.type.focus();
			  return false;
	   }
	  }
</script>
</head>
<body>
	<div class="form-wrapper">
		<div class="top"><div class="colors"></div></div>
		<form name="form" action="/user/task_edit?workId=${work.workId }" method="post" onsubmit="return checkForm(this)">
			<div class="form">
				<div class="form-item">
					<p style="text-align: left"></p>
					<input type="text" value="${work.workname }" required readonly placeholder="任务名称" autocomplete="off"/>
				</div>
				<div class="form-item">
					<p style="text-align: left"></p>
					<input type="hidden" value="${work.grand }" name="grand" required readonly placeholder="任务级别" autocomplete="off"/>
					<%-- 【应该是指派任务的时候就指定的，不可以编辑的】
						<select name="grand"  required>
						  <option value="">--请选择任务级别--</option>   
						  <option value="Important" <c:if test="${'重要' eq work.grand.name}">selected</c:if> >重要</option>   
						  <option value="Simple" <c:if test="${'普通' eq work.grand.name}">selected</c:if> >普通</option>
						</select> 
					--%>
				</div>
				<div class="form-item">
					<p style="text-align: left"></p>
					<input type="hidden" value="${work.type }" name="type" required readonly placeholder="任务类别" autocomplete="off"/>
					
					<%-- 【应该是指派任务的时候就指定的，不可以编辑的】
					<select name="type"  required>   
					  <option value="">--请选择任务类别--</option>   
					  <option value="huodong" <c:if test="${'活动' eq work.type.name}">selected</c:if> >活动</option>   
					  <option value="tongzhi" <c:if test="${'通知' eq work.type.name}">selected</c:if> >通知</option>   
					  <option value="huiyi" <c:if test="${'会议' eq work.type.name}">selected</c:if> >会议</option>   
					  <option value="jiancha" <c:if test="${'检查' eq work.type.name}">selected</c:if> >检查</option>   
					  <option value="peixun" <c:if test="${'培训' eq work.type.name}">selected</c:if> >培训</option>
					  <option value="qita" <c:if test="${'其他' eq work.type.name}">selected</c:if> >其他</option>
					</select> --%>
				</div>
				<div class="form-item">
					<p style="text-align: left"></p>
					<input type="text" value="${work.user.realname }" required  readonly placeholder="责任人" autocomplete="off"/>
				</div>
				<div class="form-item">
					<%--【计划完成时间不可以修改】 
						<input type="text" id="EntTime" value="${work.playtime }" name="playtime" required readonly placeholder="任务计划开始时间" autocomplete="off" onclick="return showCalendar('EntTime', 'y-mm-dd');"/> 
					--%>
					<input type="text"  value="${work.playtime }" name="playtime" required readonly placeholder="任务计划开始时间" autocomplete="off" />
				</div>
				<div class="form-item">
					<%-- <input type="text" id="EntTime1" placeholder="任务实际开始时间" autocomplete="off" readonly value="${work.begintime }" name="begintime" onclick="return showCalendar1('EntTime1', 'y-mm-dd');"/> --%>
					<input type="text" value="${work.progress }" name="progress"  placeholder="任务完成进度（%）" autocomplete="off"/>
				</div>
				<%-- <div class="form-item">
					<input type="text" id="EntTime2" placeholder="任务实际完成时间" autocomplete="off" value="${work.completetime }" name="completetime" onclick="return showCalendar2('EntTime2', 'y-mm-dd');"/>
				</div> --%>
				<div class="form-item">
						<!-- 【存在数据显示不同步（应该采用异步交互）】——使用ajax结合当前数据库的progress和completetime、playtime来判断任务状态的值 -->
						<p style="text-align: left"></p>
						<c:if test="${work.progress == 100 && work.completetime.before(work.playtime)}">
							<input type="text" name="sta" value="YES" required  readonly placeholder="任务状态" autocomplete="off"/>
						</c:if>
						<c:if test="${work.progress > 0 && work.progress < 100}">
							<input type="text" name="sta" value="ING" required  readonly placeholder="任务状态" autocomplete="off"/>
						</c:if>
						<c:if test="${work.progress == 100 && work.completetime.after(work.playtime)}">
							<input type="text" name="sta" value="LAZY" required  readonly placeholder="任务状态" autocomplete="off"/>
						</c:if>
						<c:if test="${work.progress == 0}">
							<input type="text" name="sta" value="NO" required  readonly placeholder="任务状态" autocomplete="off"/>
						</c:if>
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