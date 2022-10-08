<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'CommodityBroadAddandEditPage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/managerStyle/CommodityTypeManager.css">
	<script type="text/javascript"  src="js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="js/Managerjs/Framejs.js"></script>
	<script type="text/javascript">
		
		/*  执行 添加或修改的函数  第一个参数显示是修改还是添加，第二个参数是null的话表示添加不是null的
		的话就表示修改*/
		function addAndEdit(typeName,id){
			
			if(id == "null"){
				$("#add_and_edit .hid_top>span").text(typeName);
				$(":hidden[name='hid_id']").val(id);
				$("#add_and_edit").show();
				return;
			}else{
				$.post("selectCommodityType.do",{littleTypeId:id},function(data){
					var littleType = eval("("+data+")");
					$(":hidden[name='hid_id']").val(littleType.id);
					$(".ipt[name='littleType']").val(littleType.cltName);
					$("#sel").each(function(index,dao){
						$(this).val(littleType.commodityBroadId);
						
					});
					$("#add_and_edit .hid_top>span").text(typeName);
					$("#add_and_edit").show();
					return;
				});
			}
		}
	
	</script>

  </head>
  
  <body>
  		<div id="add_commodityBroad">
  					<input type="button"  value="添加大类" onclick=" addAndEdit('添加类别','null'); ">
  		</div>
  		<table id="BroadType_tbl">
  				<tr>
						<td>类别编号</td><td>类别名称</td> <td>所属商品大类</td>  <td>类别编辑</td>   					
  				</tr>
  				<c:forEach items="${requestScope.list}" var="s">
  					<tr>
						<td>${s.id }</td><td>${s.cltName }</td> <td>${s.commodityBroad.commodityBroadName }</td>
						<td><a href="#"  onclick=" addAndEdit('修改类别',${s.id}); return false;">修改类别</a><br><a href="littleTypeDelete.do?littleTypeId=${s.id }"  >删除类别</a></td>   					
  					</tr>
  				</c:forEach>
  		</table>
  		
  		<div id="page_Set">
  			<a href="#"  onclick="pageSet(1); return false;">首页</a>
  			<a href="#"  onclick="pageSet(${requestScope.page.prevPage}); return false;">上一页</a>
  			<c:forEach begin="1" end="${requestScope.page.totalPages }" var="p">
  				<c:if test="${page.pageNum eq p }" var="currentPage">
  						<a href="#"  style="color:orange;" onclick="return false;">${p}</a>
  				</c:if>
  				<c:if test="${not currentPage }">
  						<a href="#"  onclick="pageSet(${p}); return false;">${p}</a>
  				</c:if>
  			</c:forEach>
  			
  			<a href="#"  onclick="pageSet(${requestScope.page.nextPage}); return false;">下一页</a>
  			<a href="#"  onclick="pageSet(${requestScope.page.totalPages}); return false;">末页</a>
  		</div>
  		
  		
  		
  			<!-- 翻页的表单  -->
  		     <form action="getAllCommodityLittleType.do" method="post" id="myForm">
  		     		<input type="hidden"  name=pageNum >
  		     </form>
  		
  		<div id="add_and_edit">
  				<div class="hid_top"><span></span><i onclick="cancel();">x</i></div>
  				<div class="hid_body">
  						<!-- 添加或修改的表单 -->
		  				<form action="littleTypeAddandEdit.do"	method="post" >
		    				<input type="hidden" name = "hid_id">
		    				<label>商品类别名称：</label><input type="text"  name="littleType"  class="ipt"/><br>
		    				<label>所属商品大类：</label>
		    				<select name = "broadTypeId" id="sel">
		    						<c:forEach items="${requestScope.broadTypeList }" var="type">
		    								<option value="${type.id }">${ type.commodityBroadName}</option>
		    						</c:forEach>
		    				</select><br>
		    				<input type="submit"  value="提交" class="sbm"><input type="button" value="取消"  class="btn" onclick="cancel();">
		    			
		    			</form>
  				
  				
  				
  				</div>
  		</div>
  		
  		
  		
  		
  		
    
  </body>
</html>
