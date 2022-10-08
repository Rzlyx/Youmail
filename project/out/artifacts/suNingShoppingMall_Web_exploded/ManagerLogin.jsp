<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ManagerLogin.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/managerStyle/ManagerLogin.css">
	<link rel="stylesheet" type="text/css" href="css/core/login.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/core/core.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/core/panels.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/core/table.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/core/gallery.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/core/button.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/core/report.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/core/form.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/core/error.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/core/misc.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css/core/themer.css" media="screen" />

  </head>
  
  <body>
    ${sessionScope.err }
	     <div id="mws-login">
		    	<h1>有你商城后台登陆系统</h1>
		        <div class="mws-login-lock"><img src="images/commonTopandBottom/locked-2.png" alt="" /></div>
		    	<div id="mws-login-form">
		        	<form class="mws-form" action="ManagerLogin.do" method="post">
		                <div class="mws-form-row">
		                	<div class="mws-form-item large">
		                    	<input type="text"  name="account" class="mws-login-username mws-textinput" placeholder="登录名" />
		                    </div>
		                </div>
		                <div class="mws-form-row">
		                	<div class="mws-form-item large">
		                    	<input type="password"  name="pwd" class="mws-login-password mws-textinput" placeholder="登陆密码" />
		                    </div>
		                </div>
		                <div class="mws-form-row">
		                	<input type="submit" value="登陆" class="mws-button green mws-login-button" />
		                </div>
		            </form>
		        </div>
	    </div>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
  	
  	
  	
  	
  	
  	
  </body>
</html>
