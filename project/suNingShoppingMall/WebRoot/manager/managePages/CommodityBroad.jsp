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
    
    <title>My JSP 'ShoopManage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/managerStyle/CommodityTypeManager.css">
	
	<script type="text/javascript" src="js/jquery-1.8.3.js">	</script>
	<script type="text/javascript" src="js/Managerjs/Framejs.js"></script>
	<script type="text/javascript">
			/* jquery初始化 */
			$(function(){
				/*  当file标签内容发生变化时 执行的函数*/
				$(":file[name='commImg']").change(function(){
					/* $(":hidden[name='hid_img']").val($(this).val()); */
					$("#show_img").attr("src",$(this).val());// 让id为show_img img标签的src属性等于你的那个file的val并显示出来
				});
			});
	/* 点击显示 隐藏的 div  */
	function editfun(executeName,id){
		if(id == "null"){  /* 如果 id为null 就表示 添加商品大类 */
			$("div.hid_top span").text(executeName)
			$(":hidden[name='hid_id']").val(id);
			$("#add_and_edit").show();
			return;
		}else{/*  如果商品 id 不等于null 就表示修改 */
			$.post("selectIdCommodityBroad.do",{commid:id},function(data){
				var broadType = eval("("+data+")");// 获取查询结果 并进行转换
				/* alert(broadType.commodityRemark);  */
				$("div.hid_top span").text(executeName)  // 给span 赋值
			    $(":hidden[name='hid_id']").val(broadType.id);  // id付过去
				$(".ipt[name='commName']").val(broadType.commodityBroadName);// 大类名称
				$("#show_img").attr("src",(broadType.commodityBroadImage)); // 显示在div上的图片
				$(":hidden[name='hid_img']").val(broadType.commodityBroadImage);// 大类图片路径
				$(".tex[name='commRemark']").text(broadType.commodityRemark);  // 大类备注
				
				$("#add_and_edit").show();// div 显示
				return;
			});
			
		}
	};
	
	</script>

  </head>
  <!-- 商品大类的界面 -->
  <body>
  		<div id="add_commodityBroad">
  					<input type="button"  value="添加大类" onclick="editfun('添加大类','null'); ">
  		</div>
    	<table id="BroadType_tbl">
	    		<tr>
	    			<td>大类编号</td><td>商品大类名称</td><td>大类图片</td><td>大类备注</td><td>编辑类别</td>
	    		</tr>
	    		<!-- 执行循环出所有的 商品大类的 -->
	    		<c:forEach items="${requestScope.list }" var="s">
		    		<tr>
		    			<td>${s.id }</td><td>${s.commodityBroadName }</td><td><img alt="大类" src="${s.commodityBroadImage }"></td>
		    			<td>${s.commodityRemark }</td><td><a href=""  onclick="editfun('修改大类',${s.id}); return false;">修改类别</a><br><a href="commodityBroadDelete.do?BroadTypeId=${s.id }">删除类别</a></td>
		    		</tr>
	    		</c:forEach>
    	</table>
    	<div id="add_and_edit">
    		<div class="hid_top"><span>修改大类</span><i onclick="cancel();">X</i></div>
    		<div class="hid_body">
    			<form action="addAndEditCommodityBroad.do"	method="post" enctype="multipart/form-data">
    				<input type="hidden" name = "hid_id">
    				<label>商品大类名称：</label><input type="text"  name="commName"  class="ipt"/><br>
    				<label>商品大类图片：</label><input type="file"  name="commImg"  class="fil"/> <img id="show_img" alt="大类" src="" style="width:50px;height:50px;"><br>
    				<input type="hidden"  name = "hid_img">
    				<label>商品大类备注：</label><textarea rows="5" cols="30" name="commRemark" class="tex" ></textarea><br>
    				<input type="submit"  value="提交" class="sbm"><input type="button" value="取消"  class="btn" onclick="cancel();">
    			
    			</form>
    		
    		</div>
    	
    	
    	</div>
  </body>
</html>
