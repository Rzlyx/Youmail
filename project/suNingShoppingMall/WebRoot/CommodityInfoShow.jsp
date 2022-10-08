<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title></title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link href="images/commonTopandBottom/favicon.ico" rel="shortcut icon" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="css/commoditycss/commodityInfo.css">
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/commodityjs/commodityInfo.js"></script>
    <script type="text/javascript">


        $(function () {
            $("title").text($("#comName").text()); // 赋值到标题栏里
        });

    </script>
</head>
<jsp:include page="commonhtml/storeTop.jsp"></jsp:include>
<body>


<div id="commodityinfo_body">
    <!--商品信息div-->
    <div id="commodity">
        <!--商品图片div-->
        <div class="commodityinfo_leftimgs">
            <a href=""><img alt="" src="${requestScope.commodity.commodityImage }"></a>

        </div>
        <!--这是商品的列表个购买的整个大div-->
        <div class="commodity_info">
            <!--这是商品信息列表的ul-->
            <ul>
                <li><h3 id="comName">${requestScope.commodity.commodityName }</h3></li>
                <li>价格￥<span class="commodityMoney">${requestScope.commodity.commodityPrice }</span></li>
                <li>浏览次数：${requestScope.commodity.commodityBrowseCount }</li>
                <li>销量：${requestScope.commodity.commoditySalesVolume }</li>
                <li>品牌：${requestScope.commodity.commodityBrandName }</li>
                <li>数量：<a href="#" onclick="return false;">
                    <img class="jian" src="images/commodityImgs/wo2.png" alt="">
                </a>
                    <!-- 这是商品库存数量  -->
                    <input type="hidden" class="commodityKucun" value="${requestScope.commodity.commodityInventory }">
                    <!-- 这个是用户选择完购买数量后计算出来的价格  -->
                    <input type="hidden" class="commoditySumMoney" value="${requestScope.commodity.commodityPrice }">
                    <!-- 这是用户用来选择的购买的商品数量文本框 -->
                    <input type="text" class="num" name="count" value="1" readonly="readonly">
                    <a href="#" onclick="return false;"><img class="jia" src="images/commodityImgs/wo1.png" alt="">
                    </a>
                    (库存${requestScope.commodity.commodityInventory } 商品状态：<span
                            style="color:red; font-size:13px;">${requestScope.commodity.commodityStats.commodityStatusType}</span>)
                </li>

            </ul>

            <!--这是购买商品和加入购物车的div-->

            <div class="city_goma">

                <img src="images/commodityImgs/close.png" alt="" class="close_img">
                <p class="add_city_hid"><img src="images/commodityImgs/ok.png" alt=""><span>商品已加入购物车</span></p>
                <a href="#" class="btn_goma initialize  dianjiq" onclick="return false;">立即购买 </a>
                <a href="#" class="btn_city initialize  dianjiq" onclick="return false;">加入购物车</a>
                <a href="ShowsuNing.do" class="btn_jixugoma initialize dianjih">继续购物</a>
                <a href="showUserOrderFormInfo.do" class="btn_cityjis initialize dianjih">去购物车结算</a>
            </div>

            <!--这是 购买商品输入密码的div-->
            <div id="goumai">
                <!--  这是购买商品的表单 -->
                <form action="orderFormSettle.do" id="Myform" method="post">

                    <label>请输入您的密码：</label><input type="password" class="ipt  pwd"><br>
                    <div class=" err-prompt  err-inof">密码错误</div>

                    <!-- 用户账户 -->
                    <input type="hidden" name="userAccount" value="${requestScope. user.userAccount}" id="userAccount">
                    <!-- 这是用户账户的余额 -->
                    <input type="hidden" value="${requestScope. user.accountBalance}" class="accountBalance">
                    <!-- 这是买完商品账户剩余的钱 -->
                    <input type="hidden" name="YueMoney" value="" class="shengyuMoney">
                    <!-- 商品id -->
                    <input type="hidden" name="commodityId" value="${requestScope.commodity.id}" id="commodityId">
                    <!-- 商品数量 -->
                    <input type="hidden" name="commodityCount" value="" id="comCount">
                    <!-- 商品大类id -->
                    <input type="hidden" id="barodId" value="${requestScope.braodTypeId}">
                    <!-- 订单id -->
                    <input type="hidden" name="orderFormId" value="" id="ofId">
                    <!-- 订单状态id -->
                    <input type="hidden" name="orderFormStateId" value="2">
                    <input type="button" value="提交" class="sub"> <input type="button" value="取消" class="sub cancel">

                </form>


            </div>


        </div>

        <!--这是清除浮动的div-->
        <div style="clear:left"></div>
        <!--这是 商品的 详情信息的div-->
        <div class="commodity_remark">
            <p class="remark"><a href="#">商品详情</a></p>

            <ul>

                <li>[商品原产地] <span>${requestScope.comInfo.commodityProducing }</span></li>
                <li>[产品规格类型] <span>${requestScope.comInfo.productType }</span></li>
                <li>[产品具体信息]<br>
                    <p class="inforemarks">
                        ${requestScope.comInfo.commodityRemark }
                    </p>
                </li>
            </ul>


        </div>

    </div>
    <!--商品推荐-->
    <div id="commodity_recommend">
        <p class="kenengxih"><span>可能喜欢</span></p>
        <ul>
            <c:forEach items="${requestScope.comlist }" var="s">
                <li>
                    <a href="getCommodityInfo.do?comId=${s.id }&braodTypeId=${s.getCommodityBroadId()}"
                       target="blank"><img alt="商品" src="${s.commodityImage }"></a>
                    <p>${s.commodityName }</p>
                    <span>价格为：￥${s.commodityPrice }</span>
                </li>
            </c:forEach>

        </ul>

    </div>


</div>


</body>
<jsp:include page="commonhtml/storeBottom.jsp"></jsp:include>
</html>
