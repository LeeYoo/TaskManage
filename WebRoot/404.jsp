<!--【需要手动添加编码方式，然后复制下边的html即可】-->
<%@ page pageEncoding="utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

<!-- 	
		5秒后自动跳转 refresh自动刷新！
		该功能的实现还需要配置配置文件！
		配置过程 请看xml文件详细介绍（两种方式） 
-->
<meta http-equiv="refresh" content="5;URL=/system/preLogin" />

<title>404提示页面</title>
<style type="text/css">
		
	body {
	  font-family: "宋体";
	  font-size: 12px;	 
	  color: #324451;
	}
	
   
  #container {
    width : 350px;
    height : 162px;
    position : absolute;
    top : 120px;
    left : 50%;
    margin-left : -175px;   
    border : 1px solid #88bfea;
    
  }
 
  #title {
     height : 32px;
     background-color : #e5f1f8;
     border-bottom : 1px solid #88bfea; 
     font-size :13px;
     font-weight : bold;
     line-height : 32px;
     padding-left : 6px;
  }
  
  #content {
     padding-top : 40px;
     text-align : center;
  }
</style>
</head>
<body>
    <div id="container">
    	  <div id="title">
    	  	  提示:
    	  </div>
    	  <div id="content">
    	  	<p> 
    	  	<!-- 5秒后跳转，添加meta标签(见开头) -->
				对不起，您所访问的资源不存在 或是已经被删除，5秒后自动跳转到登陆页面！谢谢！	
    	  	</p>
    	  </div>
    </div>
</body>
</html>
