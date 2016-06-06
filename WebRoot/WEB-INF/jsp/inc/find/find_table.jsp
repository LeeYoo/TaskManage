<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="/style/calendar.css" >
<script type="text/javascript" src="/js/calendar.js" ></script>  
<script type="text/javascript" src="/js/calendar-zh.js" ></script>
<script type="text/javascript" src="/js/calendar-setup.js"></script>
</head>
<body>
	<div class="form-wrapper">
	  	<div class="top"><div class="colors"></div></div>
			<div class="form">
				<div class="form-item">
					<input type="text" name="realname"  placeholder="下属员工姓名" autocomplete="off"/>
				</div>
				<div class="form-item">
					<input type="text" name="workname"  placeholder="下属任务名称" autocomplete="off"/>
				</div>
				<div class="form-item">
					<select name="grand" > 
				       <option value="">-----任务级别-----</option>
				       <option value="Important">重要</option> 
				       <option value="Simple">普通</option>  
				   	</select>
				</div>
				<div class="form-item">
					<select name="type" class="length"> 
				       <option value="">-----任务类别-----</option>
				       <option value="huodong">活动</option> 
				       <option value="tongzhi">通知</option>  
				       <option value="huiyi">会议</option>  
				       <option value="jiancha">检查</option>  
				       <option value="peixun">培训</option>  
				       <option value="qita">其他</option>  
				   	</select>
				</div>
				<div class="form-item">
					<input type="text" id="EntTime" name="play_begin" readonly placeholder="计划完成时间，从……开始" autocomplete="off" onclick="return showCalendar('EntTime', 'y-mm-dd');"  />
				</div>
				<div class="form-item">
					<input type="text" id="EntTime1" name="play_end" readonly placeholder="计划完成时间，到……之间" autocomplete="off" onclick="return showCalendar('EntTime1', 'y-mm-dd');"  />
				</div>
				<div class="button-panel">
					<input type="submit" class="button" title="点击" value="开始查询...">
	      		</div>
			</div>
	</div>	
	<script>
	solid();
	</script>
</body>
</html>