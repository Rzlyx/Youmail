<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ManagerLeft.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/managerStyle/FrameStyle.css">
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<script type="text/javascript">
	/* 对商品 元素进行点击事件     */
		$(function(){
			$("#Manager_left>ul>li span").toggle(function(){
				$(this).next("ul").slideDown();
			},function(){
				$(this).next("ul").slideUp();
			});
			
		});
	
	</script>

  </head>
  <!-- 管理界面导航页面 -->
  <body>
  		<div id="Manager_left">
			  	<ul>
			  	<!-- 对商品进行管理的 li 标签  -->
			  		<li>
			  				
			  				<span>商品管理</span>
			  				<ul>
			  						<li><a href="entranceBroadType.do" target="allManagePage">商品大类管理</a></li>
			  						<li><a href="getAllCommodityLittleType.do" target="allManagePage">商品小类管理</a></li>
			  						<li><a href="pageSelectCommodity.do" target="allManagePage">商品管理</a></li>
			  			  </ul>
			  		</li>
			  		<li>
			  				<span>首页管理</span>
			  				<ul>
			  						<li><a href="entranceHome.do" target="allManagePage">首页轮播</a></li>
			  				
			  				</ul>
			  		</li>
			  		<li>
			  				<span>用户管理</span>
			  				<ul>
			  						<li><a href="getAllUserInfo.do" target="allManagePage">用户信息</a></li>
			  				
			  				</ul>
			  		</li>
			  		<li>
			  				<span>订单管理</span>
			  				<ul>
			  						<li><a href="getAllOrderForm.do" target="allManagePage">订单信息</a></li>
			  				
			  				</ul>
			  		</li>
			  		
			  		
			  		
			  		
			  	</ul>
  		
  		
  		</div>
  </body>
</html>
