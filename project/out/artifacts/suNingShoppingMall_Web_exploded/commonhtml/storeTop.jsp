<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>头部</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/commonPagecss/storeTop.css" type="text/css">
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/commonhtmljs/storeTop.js"></script>

  </head>
  
  <body>
	    <div id="topAll">
	        
	        <div class="topAll-top">
	
	           
	                <ul class="top-left">
	                    <li style=" margin-top: 5px;"> <a href="#" >网站导航</a>
	                        
	                        <ul class="top-nav hien" >
	                            <li>有你互联</li>
	                            <li>有你V购</li>
	                            <li>海外购</li>
	                            <li>大聚会</li>
	                            <li>依旧换新</li>
	                        </ul> 
	                    
	                    
	                    </li>
	                    <li class="left-li" style="position:absolute; left:150px"><a href="showSuNing.do">返回首页</a></li> 
	                    
	                </ul>
	                <ul class="top-right">
	                <c:if test="${sessionScope.userInfo == null }"  var = "us">
	                <!-- 这是从首页进入登陆页面 -->
	                	<li><a href="UserLogin.jsp?pageId=0">登陆</a></li>
	                    <li><a href="UserRegister.jsp">注册</a></li>
	                </c:if>
	                <c:if test="${!us }">
	                		<li style="color:red; font-size:13px; padding-top:4px;">欢迎：<a href="#">${sessionScope.userInfo.userName }</a></li>
	                		<li><a href="userQquit.do"  onclick="return confirm('确定退出账号吗？')">注销</a></li>
	                </c:if>
	                    <li>
	                    		<a href="showUserOrderFormInfo.do" target="blank"  class="userUrl">我的订单</a>
	                    		<input type="hidden"  value="${sessionScope.userInfo.userAccount }"  id="userAccount">
	                    </li>
	                    <li><a href="showUserOrderFormInfo.do" target="blank"  class="userUrl">我的易购</a></li>
	                    <li><a href="showUserOrderFormInfo.do" target="blank"  class="userUrl">购物车 <span id="carCount">0</span> </a></li>
	                    <li><a href="#">易付宝</a></li>
	                    <li><a href="#">企业采购</a></li>
	                    
	                </ul>
	
	            
	
	
	        </div>
	        <div class="topAll-body">
	            <div class="body-log">
	                <img src="images/commonTopandBottom/tuplog.png" alt="有你">
	                <img src="images/commonTopandBottom/slogn.jpg" alt="字体">
	            </div>
	            <div class="body-center">
	                <form action="searchResult.do" method="post">
	                    
	                    <input type="text" placeholder="男装" name="goodsName" class="ipt">
	                    <input type="submit" value="搜索" class="smb">
	                    
	                    
	                </form>
	                <div class="ipt-next">
	                    <a href="#">粮油买1增1</a><a href="#">保暖内衣</a><a href="#">空净668起</a>
	                    <a href="#">vivox9抢先看</a><a href="#">食品买2付1</a><a href="#"> 游戏直降千元</a>
	                    <a href="#"> 小米mix</a><a href="#"> 美的微波炉</a>
	                </div>
	            </div>
	            <div class="bottom-img">
	                <img src="images/commonTopandBottom/topgoodsimg.png" alt="商品">
	            </div>
	
	        </div>
	        <div class="topAll-bottom">
	            <span class="allType">全部商品分类</span>
	            <ul class="typepage">
	                <li><a href="#">有你会员 </a></li>
	                <li><a href="#">服装城 </a></li>
	                <li><a href="#">有你超市</a></li>
	                <li><a href="#">电器城</a></li>
	                <li><a href="#">红孩儿母婴</a></li>
	                <li><a href="#">大聚惠</a></li>
	                <li><a href="#">有你金融</a></li>
	                <li><a href="#">海外购</a></li>
	                <li><a href="#">中华特色馆</a></li>
	            </ul>
	
	        </div>
	    </div>
  </body>
</html>
