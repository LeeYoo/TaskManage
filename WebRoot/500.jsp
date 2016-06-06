<!--【需要手动添加编码方式，然后复制下边的html即可】-->
<%@ page pageEncoding="utf-8" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>500提示页面</title>
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
				很抱歉，您的请求不能被处理，请确认操作，如仍不能解决问题，请和我们管理人员联系！	
    	  	</p>
    	  	<!-- history.back() 从哪儿来回哪儿去 -->
    	  	<input type="button" value="返回" onclick="history.back()"/>
    	  </div>
    </div>
</body>
</html>
