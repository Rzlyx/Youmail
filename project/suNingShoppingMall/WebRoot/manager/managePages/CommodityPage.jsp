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
    
    <title>My JSP 'CommodityPage.jsp' starting page</title>
    
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
				/* 设置保留商品类型的查询条件  */
				$(".sel[name='commodityTypeId']").each(function(){
					$(this).val($(this).attr("data"));
				});
				/* 设置保留商品状态的查询条件  */
				$(".sel2[name='commodityStatusTypeId']").each(function(){
					$(this).val($(this).attr("data"));
				});
				/* 设置全选和全部选效果 */
				/* $("#checkAll").click(function(){
					var bool = $(this).is(":checked");
					if(bool){	
						$(":checkbox.che").attr("checked",bool);
					}else{
						$(":checkbox.che").attr("checked",bool);
					}
					}); */
					
					/* 设置隐藏的div里面的img标签显示的图片 */
					$(":file.imgfile").change(function(){
						$("img.show_img").attr("src",$(this).val());
					});
			});
			
			function addAndEdit(isaddORedit,id){
				if(id == "null"){ // 如果是1的话表示添加
					$(":hidden.hid_id").val(id);
					$("div.addedit_top span").text(isaddORedit)
					$(":text[name='commodityName2']").val("");
					$(":text[name='commodityBrandName']").val("");
					$(":text[name='commodityPrice']").val("");
					$(":text[name='commodityInventory']").val("");
				   $("img.show_img").attr("src","");
					$(":hidden[name='commodityImage']").val("");	
					$(":text[name='commodityProducing']").val("");
					$(":text[name='productType']").val("");
					$("#remark").val("");
					$("#commodity_addAndEdit").show();
				}
				else if(id == 1){ // 如果是2的话表示修改
					$("div.addedit_top span").text(isaddORedit)
					$(":checkbox.che").each(function(){ 
						if($(this).is(":checked")){
							var cheId = $(this).val();
							$(":hidden.hid_id").val(cheId);
							$.post("idGetCommodity.do",{commodityId:cheId},function(data){
								var commodity = eval("("+data+")");
								$(":text[name='commodityName2']").val(commodity.commodityName);
								$(":text[name='commodityBrandName']").val(commodity.commodityBrandName);
								$(":text[name='commodityPrice']").val(commodity.commodityPrice);
								$(":text[name='commodityInventory']").val(commodity.commodityInventory);
								$("img.show_img").attr("src",commodity.commodityImage);
								$(":hidden[name='commodityImage']").val(commodity.commodityImage);
								$("#comType").each(function(index,dao){
									$(this).val(commodity.commodityTypeId);
								});
								$("#comStatus").each(function(index,dao){
									$(this).val(commodity.commodityStatusTypeId);
								});
								
							});
							$.post("idGetCommodityInfo.do",{commid:cheId},function(data){
								var commodityInfo = eval("("+data+")");
								$(":text[name='commodityProducing']").val(commodityInfo.commodityProducing);
								$(":text[name='productType']").val(commodityInfo.productType);
								$("#remark").val(commodityInfo.commodityRemark);
							});
							$("#commodity_addAndEdit").show();
						}
						
					});
					
				}
				else if(id == 2){
					$(":checkbox.che").each(function(){
						if($(this).is(":checked")){
							var commodityId = $(this).val();
							/* 删除商品和商品信息  先删除商品信息 后再删除商品 */
							$.post("deleteCommodityInfo.do",{comid:commodityId},function(){
								/* 用ajax完成删除商品和删除商品信息 完成后执行的函数 先让他刷新一下 */
								location.href="pageSelectCommodity.do";
								alert("删除成功!");
							});
						}
						
					});
					
				}
			}
			
			
	</script>


  </head>
  
  <body>
 				 <!-- 商品页面头部 -->
  			 <div id="commodity_select">
  			 		<div  class="commodity_manage">
  			 				<input type="button"  value="添加商品" onclick="addAndEdit('添加商品','null')"  class="btn">
  			 				<input type="button"  value="修改商品" onclick=" addAndEdit('修改商品',1); " class="btn">
  			 				<input type="button"  value="删除商品" onclick=" addAndEdit('删除',2); "  class="btn  deletebtn" > 
  			 				
  			 		</div>
  			 
  			 
  			 
  			 
  			 		<!-- 翻页的表单  和 查询商品信息的表单 -->
		  		     <form action="pageSelectCommodity.do" method="post" id="myForm">
		  		     		<input type="hidden"  name="pageNum"   >
		  		     		
		  		     		<label class="select_condition">商品名称：
		  		     		</label><input type="text" name = "commodityName"  value="${requestScope.commodity. commodityName}" class="select_condition">
		  		     		<label>商品类型：</label>
		  		     		<select name="commodityTypeId" class="sel  select_condition"  data="${requestScope.commodity.commodityTypeId }">
		  		     				<option value="0" >全部</option>
		  		     				<c:forEach items="${requestScope.commodityType }"  var="type">
		  		     						<option value="${type.id}">${type.cltName }</option>
		  		     				</c:forEach>
		  		     		</select>
		  		     		<label class="select_condition">商品状态：</label>
		  		     		<select name="commodityStatusTypeId"  class="sel2 select_condition"  data="${requestScope.commodity.commodityStatusTypeId }">
		  		     				<option value="0" >全部</option>
		  		     				<c:forEach items="${requestScope.commodityStatus }"  var="status">
		  		     						<option value="${status.id }" >${status.commodityStatusType }</option>
		  		     				</c:forEach>
		  		     		</select>
		  		     		<input type="submit"  value="搜索"  class="sbm  select_condition" >
		  		     </form>
  			 
  			 </div>
  			<table id="BroadType_tbl">
  				<tr>
	    			<td><input type="checkbox"/  id="checkAll"></td><td>编号</td><td>商品图片</td><td>商品名称</td><td>价格</td><td>品牌</td><td>销量</td>
	    			<td>浏览量</td><td>库存</td><td>状态</td><td>所属商品小类</td>
	    		</tr>
	    		<c:forEach items="${requestScope.list }" var="s">
			    		<tr>
			    		
			    			<td><input type="checkbox" class="che"  value="${s.id }" ></td>
			    			<td>${s.id}</td><td><img alt="商品" src="${s.commodityImage }"></td><td>${s.commodityName }</td>
			    			<td>${s.commodityPrice }</td><td>${s.commodityBrandName }</td><td>${s.commoditySalesVolume }</td>
			    			<td>${s.commodityBrowseCount }</td><td>${s.commodityInventory }</td>
			    			<td>${s.commodityStats.commodityStatusType }</td><td>${s.commodityLittle.cltName}</td>
			    		</tr>
	    		</c:forEach>
  			</table>
  			<div id="page_Set">
		  			<a href="#"  onclick="pageSet(1); return false;">首页</a>
		  			<a href="#"  onclick="pageSet(${requestScope.page.prevPage}); return false;">上一页</a>
		  			<c:forEach begin="1" end="${requestScope.page.totalPages }" var="p">
			  				<c:if test="${p ge page.pageNum  and p le page.pageNum+2 }">
			  						<c:if test="${page.pageNum eq p }" var="currentPage">
					  						<a href="#"  style="color:orange;" onclick="return false;">${p}</a>
					  				</c:if>
					  				<c:if test="${not currentPage }">
					  						<a href="#"  onclick="pageSet(${p}); return false;">${p}</a>
					  				</c:if>
			  				</c:if>
			  				
		  			</c:forEach>
		  			<a href="#"  onclick="pageSet(${requestScope.page.nextPage}); return false;">下一页</a>
		  			<span>共有<i style="color:red;">${requestScope.page.totalCount}</i>条数据</span>
		  			<a href="#"  onclick="pageSet(${requestScope.page.totalPages}); return false;">末页</a>
  		  </div>
  		 
  		 
  		 
  		 
  		 
	  <div id="commodity_addAndEdit">
		     	<div class="addedit_top"><span>添加商品</span></div>
		        <div class="addedit_body">
			           <form action="addAndEditCommodity.do" method="POST" enctype="multipart/form-data"  id="deitandadd_Commodity">
			           		<input type="hidden"  name="pageNum2"   id="EditNo"  value="${page.pageNum}">  <!--  当修改的时候也把当前页个提交过去-->
			           		<input type="hidden"  name="commodityId"  class="hid_id"/>
			                <label class="addandedit">商品名称</label>
			                <input type="text" name="commodityName2"  class="addandedit">
			                <label class="addandedit">品牌名称</label>
			                <input type="text" name="commodityBrandName"  class="addandedit"><br>
			                <label class="addandedit">商品类别</label>
			                <select name="commodityType" id="comType" class="addandedit comtype">
			                    	<c:forEach items="${requestScope.commodityType }"  var="type">
		  		     						<option value="${type.id}">${type.cltName }</option>
		  		     				</c:forEach>
			                </select>
			                <label class="addandedit">商品状态</label>
			                <select name="commodityStatus" id="comStatus" class="addandedit">
			                    	<c:forEach items="${requestScope.commodityStatus }"  var="status">
		  		     						<option value="${status.id }" >${status.commodityStatusType }</option>
		  		     				</c:forEach>
			                </select><br>
			                <label class="addandedit">商品价格</label>
			                <input type="text" name="commodityPrice"  class="addandedit">
			                <label class="addandedit">商品库存</label>
			                <input type="text" name="commodityInventory"  class="addandedit"><br>
			                <label class="addandedit">商品图片</label><input type="file" name="imgfile"  class="addandedit imgfile">
			                <input type="hidden" name="commodityImage">
			                <img src="" alt=""  style="width:150px; height:50px;" class="show_img">
			                <br>
			                <label class="addandedit">原产地</label><input type="text" name="commodityProducing"  class="addandedit producing">
			                <label class="addandedit">规格类型</label><input type="text" name="productType"  class="addandedit"><br>
			                <label class="addandedit">具体描述</label>
			                <textarea name="commodityRemark" id="remark" cols="30" rows="3" class="addandedit"></textarea><br>
			                <input type="submit" value="提交" class="sbtandbtn"><input type="button" value="取消" class="sbtandbtn" onclick="cancel();">
			            </form>
		        </div>
	    </div>
  
  
  </body>
</html>
