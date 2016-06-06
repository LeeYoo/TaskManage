<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://www.liyao.com/taskManage" prefix="privilege"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
$(document).ready(function()
{ 
	//1.点击样式
	$("#firstpane p.menu_head").click(function()
    {
		$(this).css({backgroundImage:"url(/img/down.png)"}).next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
       	$(this).siblings().css({backgroundImage:"url(/img/left.png)"});
	});
	//2.浮动样式
	$("#secondpane p.menu_head").mouseover(function()
    {
	     $(this).css({backgroundImage:"url(/img/down.png)"}).next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
         $(this).siblings().css({backgroundImage:"url(/img/left.png)"});
	});
});
</script>
<style type="text/css">
.menu_list { width:180px; margin-top:5px; float:left; }
.menu_head { padding: 5px 10px; cursor: pointer; position: relative; margin:1px; font-weight:bold; background: #eef4d3 url(img/left.png) center right no-repeat; }
.menu_body { display:none; }
.menu_body a { display:block; color:#006699; background-color:#EFEFEF; padding-left:10px; font-weight:bold; text-decoration:none;}
.menu_body a:hover { color: #000000; text-decoration:underline; }
</style>
</head>
<body>
<div style="border: 0px red solid;margin-top:10px;width: 180px;float: left;margin-left:20px;">
	 <div style="border: 0px black solid;margin-top: 0px;padding: 0px;height: 40px;background-color: #F2F28B;line-height: 35px;">
		 <span style="margin-left: 50px;font-size: 20px">任务管理</span>
	  </div>
	  <div>
		  <div class="menu_list" id="firstpane">
		    <p class="menu_head">我的任务</p>
		    <div class="menu_body">
		    	<a href="/user/listWork">所有任务</a>
		    	<a href="/user/YesWork">我已完成</a>
		    	<a href="/user/NoWork">我未完成</a>
		    	<a href="/user/welcome">我在进行</a>
		    </div>
		    <p class="menu_head">下属任务</p>
		    <div class="menu_body">
 		    	<a href="/user/employeeWork">所有任务</a>
		    	<a href="/user/preFindUI">查询下属任务</a>
		    	<a href="/user/task_fixUI">指派下属任务</a>
		    </div>
		    <p class="menu_head">人员信息</p>
		    <div class="menu_body">
		    	<a href="/user/relation">人员结构</a>
		    	<a href="/user/preMyself">我的信息</a>
		    </div>
		    
		    <p class="menu_head">权限管理</p>
		    <div class="menu_body">
		    	<privilege:permission module="privilege" privilege="view">
		    		<a href="/user/privilegelist">权限列表</a>
		    	</privilege:permission>
		    </div>
		    
		    <p class="menu_head">权限组管理</p>
		    <div class="menu_body">
		    	<privilege:permission module="privilegeGroup" privilege="add">
		    		<a href="/user/privilegeGroup_addUI">添加权限组</a>
		    	</privilege:permission>
		    	<privilege:permission module="privilegeGroup" privilege="view">
		    		<a href="/user/privilegeGrouplist">权限组列表</a>
		    	</privilege:permission>
		    </div>
		    
		    <p class="menu_head">用户管理</p>
		    <div class="menu_body">
		    	<privilege:permission module="user" privilege="view">
		    		<a href="/user/userlist">用户列表</a>
		    	</privilege:permission>
		    </div>
		    
		    <p class="menu_head">部门管理</p>
		    <div class="menu_body">
		    	<privilege:permission module="dept" privilege="add">
		    		<a href="/user/dept_addUI">添加部门</a>
		    	</privilege:permission>
		    	<privilege:permission module="dept" privilege="view">
		    		<a href="/user/deptlist">部门列表</a>
		    	</privilege:permission>
		    </div>
		    <!-- <p class="menu_head">共享信息</p>
		    <div class="menu_body">
		    	<a href="/user/fileList">信息列表</a>
		    	<a href="/user/upload">信息录入</a>
		    </div> -->
		  </div>
	</div>
	  <div style="clear:both;width:100%;"></div>
	  <script>
		roller.init('nav_container3','h',-200,-24,100,20);
	  </script>
</div>
</body>
</html>