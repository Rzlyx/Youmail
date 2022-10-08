<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'HomePageManage' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/managerStyle/CommodityTypeManager.css">
	<script type="text/javascript"  src="js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="js/Managerjs/Framejs.js"></script>
	<script type="text/javascript">
	
			$(function(){
				$(":file.home_file").change(function(){
					$("img.home_img").attr("src",$(this).val());
				});
			});
	
			function addAndEdit(){
				
				$("#commodity_addAndEdit").show();
			}
	
	
	</script>

  </head>
  
  <body>
  				<div><input type="button"  onclick="addAndEdit()" value="添加图片" /></div>
     			<table id="BroadType_tbl">
     			
     					<thead>
     							<tr>
     									<td>首页图片编号</td>
     									<td>首页图片</td>
     									<td>编辑</td>
     							</tr>
     					
     					</thead>
     					<tbody>
     							<c:forEach items="${requestScope.home }" var="s">
										<tr>
		     									<td>${s.id }</td><td><img alt="图片" src="${s.img }" style="width:400px; height:120px;"> </td>
		     									<td><a href="deleteHome.do?id=${s.id }">删除</a></td>
		     									
		     							</tr>     							
     							</c:forEach>
     					
     					</tbody>
     			
     			
     			
     			</table>
     			
     			<div id="commodity_addAndEdit">
     					<form action="addAndeditHome.do"  method="post"  enctype="multipart/form-data">
     							
     							<label class="home">上传图片</label>
     							<input type="file"   name = "img"  class="home_file" ><br>
     							<img alt="" src=""  class="home_img"  style="width:490px; height:250px;">
     							<input type= "submit" value="提交" class="home">
     							<input type= "button" value="取消"  onclick="cancel()"  class="home">
     							
     					
     					
     					</form>
     			
     			
     			
     			</div>
  </body>
</html>
