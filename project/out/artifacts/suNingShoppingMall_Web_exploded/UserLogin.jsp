<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>登陆</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="shortcut icon" type="image/x-icon" href="images/commonTopandBottom/sun.ico"/>
    <link rel="stylesheet" type="text/css" href="css/usercss/loginAndRegister.css">

    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/userjs/LoginAndRegister.js"></script>

</head>

<body>

<div id="login_top">
    <a href="showSuNing.do" class="login_top_img"><img src="images/commonTopandBottom/tuplog.png"></a>


</div>
<div id="login_body">

    <div class="login_form">
        <div class="form_top">
            <a href="#">账户登录</a>
        </div>
        <div class="err_msg">
            <p>

            </p>
        </div>

        <div class="login-txtbox useracc">
            <%
                // 这是获取从那个页面跳转过来的那页面的id
                String pageId = request.getParameter("pageId");
                // 如果要是从商品信息页面跳转过来的 那还的获取商品的id
                String commodityId = request.getParameter("comId");//商品id
                // 和商品大类的id
                String barodId = request.getParameter("braodTypeId");
            %>
            <!--  这个标签是用来 接收从那个页面跳过来的 页面id   -->
            <input type="hidden" id="enterPageWay" value="<%= pageId==null?0:pageId %>">
            <!--  这是用来放置商品id 的标签 -->
            <input type="hidden" id="commodityId" value="<%= commodityId %>">
            <!--  这是用来放置商品大类id 的标签 -->
            <input type="hidden" id="barodId" value="<%= barodId %>">
            <label class="input-label ipt-acc">用户名/邮箱/手机号</label>
            <input type="text" name="userAccount" class="txt-input text-email" id="login_account" tabindex="1"
                   autocomplete="off" value="">
        </div>
        <div class="login-txtbox usepwd">
            <input type="hidden" id="logIn_result" value="">
            <label class="input-label ipt-pwd">密码</label>
            <input type="text" name="userPwd" class="txt-input text-Pwd" tabindex="1" id="login_userPwd"
                   autocomplete="off " value="">
        </div>
        <div class="auto-login">
            <label> <input type="checkbox" checked="checked"></label><span>自动登录</span>
            <div class="warn-msg"><p>公共场所不建议自动登录</p></div>
        </div>
        <div style="clear:both;"></div>
        <p class="mop">
            <span class="sub" id="login_user" onclick=" longInCheck()">登     录</span>
        </p>
        <p style="float:right; font:12px/1.5 Arial,Tahoma,'\5b8b\4f53'; margin-right:20px; margin-top:5px;">忘记密码？</p>

    </div>

</div>


</body>
</html>
