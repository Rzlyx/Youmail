<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<%@ taglib uri="http://suNing.CommodityPageShow.com" prefix="p"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'UserOrderFormManage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/managerStyle/CommodityTypeManager.css">
	<script type="text/javascript" src="js/jquery-1.8.3.js">	</script>
	<script type="text/javascript" src="js/Managerjs/Framejs.js"></script>
	<script type="text/javascript">
		$(function(){
			$(".sel2[name='statusId']").each(function(){
				$(this).val($(this).attr("data"));
			});
		})
		
	</script>

  </head>
  	
  <body>
    	<!-- 翻页的表单  和 查询商品信息的表单 -->
	<form action="getAllOrderForm.do" method="post" id="myForm">
    	<div id="commodity_select"  style="height:22px; padding:5px 0px;">
    	
		  		     		
		  		     		
		  		     		<label class="select_condition">用户账户：
		  		     		</label><input type="text" name = "userAccount"  value="${requestScope.userAccount }" class="select_condition">
		  		     		
		  		     		<label class="select_condition">订单状态：</label>
		  		     		<select name="statusId"  class="sel2 select_condition"  data="${requestScope.orderFormStatus }">
		  		     				<option value="0" >全部</option>
		  		     				<c:forEach items="${requestScope.orfs }"  var="status">
		  		     						<option value="${status.id }" >${status.getOrderFormType() }</option>
		  		     				</c:forEach>
		  		     		</select>
		  		     		<input type="submit"  value="搜索"  class="sbm  select_condition" >
		  		     
  			 
  			 </div>
    	<table  id="BroadType_tbl">
    		
    				<tr>
    						<td>订单编号</td><td>订单账户</td><td>商品名称</td><td>价格</td><td>品牌</td>
    						<td>订单状态</td><td>商品数量</td><td>下单时间</td>
    				</tr>
    				 <c:forEach items="${requestScope.uofs }" var="s"> 
    					<tr>
    							<td>${s.orderFormNumber }</td>
    							<td>${s.userAccount }</td>
    							<td>${s.commodity.commodityName }</td><td>${s.commodity.commodityPrice }</td><td>${s.commodity.commodityBrandName }</td>
    							<td>${s.ofs.getOrderFormType() }</td>
    							<td>${s.addCommodityCount }</td>
    							<td><fmt:formatDate value="${s.nextOneTime }"  type="both"  pattern="yyyy-MM-dd HH:mm"/></td>
    					</tr>
    				</c:forEach> 
    				
    		</table>
    		
    		<p:page formId="myForm" pageBean="${requestScope.pageBean }"/>
    	
    	</form>
    	
    	
    	
    	
    	
  </body>
</html>
