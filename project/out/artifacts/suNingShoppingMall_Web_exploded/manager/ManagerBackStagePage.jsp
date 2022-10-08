<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>有你商城后台管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="images/commonTopandBottom/favicon.ico"  rel="shortcut icon"  type="image/x-icon"   />
	<link rel="stylesheet" type="text/css" href="'css/managerStyle/FrameStyle.css">

  </head>
  <!-- 引用框架 -->
  <frameset  rows="90,*"  frameborder="no" >
  		<frame src="manager/ManagerTop.jsp" />  <!-- 框架头部 -->
  		<frameset cols="180,*">
  			<frame src="manager/ManagerLeft.jsp"/><!-- 框架左部 -->
  			<!--  框架中央 -->
  			<frame src="manager/ManagerCentre.jsp"  name="allManagePage"/>
  		</frameset>
  </frameset>
  <body>
  </body>
</html>
