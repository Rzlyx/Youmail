<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://suNing.CommodityPageShow.com"  prefix="p"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'UserInfoManage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/managerStyle/CommodityTypeManager.css">
	<script src="js/dateControl/laydate/laydate.js"  type="text/javascript"></script>
	<script type="text/javascript" src="js/jquery-1.8.3.js">	</script>
	<script type="text/javascript" src="js/Managerjs/Framejs.js"></script>
	<script type="text/javascript" src="js/Managerjs/userInfo.js"></script>
	<script type="text/javascript" src="js/Managerjs/userAccountSelect.js"></script>
	
  </head>
  		
  <body>
    		
    		<div id="commodity_select">
  			 		<div  class="commodity_manage">
  			 				<input type="button"  value="添加用户" onclick="addAndEdit('添加用户','null')"  class="btn">
  			 				<input type="button"  value="修改用户" onclick=" addAndEdit('修改用户',1); " class="btn">
  			 				<input type="button"  value="删除用户" onclick=" addAndEdit('删除',2); "  class="btn  deletebtn" > 
  			 				
  			 		</div>
  			 
  			 
  			 
  			 
  			 		<!-- 翻页的表单  和 查询用户信息的表单 -->
		  		     <form action="getAllUserInfo.do" method="post" >
		  		     		
		  		     		
		  		     		<label class="select_condition">用户名：
		  		     		</label><input type="text" name = "userName"  value="${requestScope.userName}" class="select_condition">
		  		     		
		  		     		<input type="submit"  value="搜索"  class="sbm  select_condition" >
		  		     </form>
  			 
  			 </div>
    		<table  id="BroadType_tbl">
    		
    				<tr>
    						<td><input  type="checkbox"  name="allcheck" /></td><td>账户</td><td>密码</td><td>账户余额</td><td>用户名</td><td>性别</td><td>出生日期</td>
    						<td>身份证</td><td>Email</td><td>联系电话</td>
    				</tr>
    				<c:forEach items="${requestScope.list }" var="s">
    					<tr>
    							<td><input  type="checkbox"   value="${s.user.userAccount }" class="che" /></td><td>${s.user.userAccount }</td>
    							<td>${s.user.userPwd }</td>
    							<td>${s.user.accountBalance }</td><td>${s.userName }</td><td>${s.userGender }</td><td>${s.userBirthDate }</td>
    							<td>${s.userIdentityMark }</td><td>${s.userEmail }</td><td>${s.userPhone }</td>
    					</tr>
    				</c:forEach>
    				
    		</table>
    		
    		<div  class="setPageStyle_Parentdiv">
    			<form action="getAllUserInfo.do"  method="post"  id="maForm">
  					  				
    				<p:page formId="maForm" pageBean="${requestScope.pageBean }"/>
    			</form>
    			
    		</div>
    		
    		
    		 <div id="commodity_addAndEdit">
		     	<div class="addedit_top"><span>添加用户</span></div>
		        <div class="addedit_body">
			           <form action="userInfoAddandEdit.do" method="POST"  id="deitandadd_Commodity">
			           		<input type="hidden"  name="pageNum2"   id="EditNo"  value="${page.pageNum}">  <!--  当修改的时候也把当前页个提交过去-->
			           		<input  type="hidden"  name="isEdit"  value="1" />
			                <label class="addandedit">用户账户</label>
			                <input type="text" name="userAccount"  class="addandedit"  id="userAccount_select"><span style="color: red;" class="err_content"></span><br>
			                <input type="hidden" name="userAccount"  class="addandedit"  >
			                <label class="addandedit">用户密码</label>
			                <input type="text" name="userPwd"  class="addandedit">
			                <label class="addandedit">账户余额</label>
			                <input type="text" name="accountBalance"  class="addandedit"><br>
			                <label class="addandedit">用户昵称</label>
			                <input type="text" name="userName"  class="addandedit">
			                <label class="addandedit">性别</label>
			                <input type="radio"  name="userGender"  value="男" class="addandedit" id="nan"><label for="nan" class="addandedit">男</label>
			                <input type="radio"  name="userGender"  value="女"  class="addandedit" id="nv"><label for="nv"  class="addandedit">女</label><br>
			                <label class="addandedit" style="">出生日期</label>
			                
			                <%-- <fmt:formatDate  var= "df" value=""/> --%>
			                 <input class="laydate-icon" id="demo" value=""    name="userBirthDate">
			                <label class="addandedit">身份证</label>
			                <input type="text" name="userIdentityMark" class="addandedit"><br>
			                
			                <label class="addandedit">Email</label><input type="text" name="userEmail"  class="addandedit producing">
			                <label class="addandedit">手机号</label><input type="text" name="userPhone"  class="addandedit"><br>
			                
			                <input type="submit" value="提交" class="sbtandbtn "  id="sub_no"><input type="button" value="取消"  id="sub_no1" class="sbtandbtn" onclick="cancel();">
			            </form>
			            <script>
							!function(){
									laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
									laydate({elem: '#demo'});//绑定元素
							}();

					</script>
		        </div>
	    </div>
    		
    		
    		
    		
  </body>
</html>
