<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://suNing.homeCommodityShow.com" prefix="h" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>有你易购</title>

    <link href="images/commonTopandBottom/favicon.ico" rel="shortcut icon" type="image/x-icon"/>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" type="text/css" href="css/commoditycss/commoditybody.css">
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/commodityjs/commoditybody.js"></script>

</head>

<jsp:include page="commonhtml/storeTop.jsp"></jsp:include>
<body>

<div id="lunb_img">
    <!--  这是循环商品大类的ul -->
    <ul class="lunbo_nvg" id="commodity_nvg">
        <c:forEach items="${requestScope.list }" var="s">
            <li data="${s.id  }">${s.commodityBroadName }</li>
        </c:forEach>


    </ul>
    <div class="tup">
        <!--  这是循环商品 轮播图片的-->
        <c:forEach items="${requestScope.home }" var="imgs">
            <c:if test="${img.id == 2 }">
                <img src="${imgs.img }" alt="" class="img_  img01">
            </c:if>
            <img src="${imgs.img }" alt="" class="img_">
        </c:forEach>
        <div class="yuandian">
            <!-- 这是循环 轮播图片的小点  -->
            <c:forEach items="${requestScope.home  }" var="imgs" varStatus="no">
                <c:if test="${no.count == 1 }" var="oo">
                    <span class="lunbuostyle1">${no.count }</span>
                </c:if>
                <c:if test="${!oo }">
                    <span class="lunbuostyle1">${no.count }</span>
                </c:if>
            </c:forEach>

        </div>
    </div>
    <!--  这里面是放 商品大类的 小类 div   -->
    <div id="nvg_top1" class="nvg_sy6">
    </div>

</div>

<div id="commodity_body">
    <!-- 这是循环商品大类的  -->
    <c:forEach items="${requestScope.list }" var="s">

        <div class="commodityF1">
            <h3>${s.commodityBroadName }</h3>
            <div class="commodityF1_left">
                <ul>
                    <!-- 这是显示商品的自定义标签 -->
                    <h:commodityList braodTypeId="${ s.id}"/>
                </ul>

            </div>
        </div>
    </c:forEach>

</div>

</body>
<jsp:include page="commonhtml/storeBottom.jsp"></jsp:include>
</html>
