<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://suNing.CommodityPageShow.com" prefix="p" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'SearchResultPage.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link href="images/commonTopandBottom/favicon.ico" rel="shortcut icon" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="css/commoditycss/commoditySearchResult.css">


</head>
<jsp:include page="commonhtml/storeTop.jsp"></jsp:include>
<body>

<div id="result_body">


    <div id="sales_count">
        <h4>销量TOP3</h4>
        <ul>
            <c:forEach items="${requestScope.commoditySalesVolume }" var="V">
                <li>
                    <a href="getCommodityInfo.do?comId=${V.id }&braodTypeId=${V.getCommodityBroadId()}"
                       target="block"><img alt="商品" src="${V.commodityImage }"></a>
                    <p>${V.commodityName }</p>
                    <span>价格为：￥${V.commodityPrice }</span>
                </li>
            </c:forEach>


        </ul>
    </div>


    <ul id="result_tab">


        <c:forEach items="${requestScope.list }" var="s">
            <li>
                <a href="getCommodityInfo.do?comId=${s.id }&braodTypeId=${s.getCommodityBroadId()}" target="blank"><img
                        alt="商品" src="${s.commodityImage }"></a>
                <p>${s.commodityName }</p>
                <span>价格为：￥${s.commodityPrice }</span>
            </li>

        </c:forEach>


    </ul>
    <div style=" overflow:hidden;   width:1000px;">
        <form action="searchResult.do" method="post" id="maForm">
            <p:page formId="maForm" pageBean="${requestScope.pagebean }"/>
        </form>
        <!-- <div id="page_style">

            <a href="">首页</a><a href="">上一页</a><a href="" style=" background: #ffc001;" >1</a><a href="">2</a><a href="">3</a><a href="">3</a><a href="">3</a><a href="">下一页</a><a href="">共50条数据</a><a href="">末页</a>
        </div> -->
    </div>
    <div id="clear_float"></div>

</div>

</body>
<jsp:include page="commonhtml/storeBottom.jsp"></jsp:include>
</html>
