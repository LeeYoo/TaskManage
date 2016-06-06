<!--【需要手动添加编码方式，然后复制下边的html即可】-->
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
    <div id="container">
    	  <div id="title">
    	  	  提示
    	  </div>
    	  <div id="content">
    	  	<p> 
				${message }	
    	  	</p>
    	  	 <input type="button" value="确定" onclick="window.location.href='${urladdress}'"/>
    	  </div>
    </div>
</body>
</html>
