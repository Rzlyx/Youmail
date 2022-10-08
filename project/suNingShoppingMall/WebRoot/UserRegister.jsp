<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>有你易购、愉快购物</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="images/commonTopandBottom/favicon.ico"  rel="shortcut icon"  type="image/x-icon"   />
	<link rel="stylesheet" type="text/css" href="css/usercss/loginAndRegister.css">
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="js/userjs/LoginAndRegister.js"></script>

  </head>
  
  <body>
    	${requestScope.err }
    	<div id="register_all">


        <div id="register_top">
            <a href="#" class="suNing_img">
                <img src="images/commonTopandBottom/tuplog.png" alt="有你">
            </a>
            <p class="log_url">我已经注册， <a href="UserLogin.jsp">马上登录</a></p>

        </div>
        <div id="register_body">
            <div class="register_body_top">
                <p>设置登陆名</p>
            </div>
            <div class="register_body_prompt">
                <div class="register_prompt"><span>注册完成有惊喜哦~</span></div>

            </div>
            <div class="reg_form">
                <form action="registerPage.do" id="MyForm" method="post">

                    <div class="reg_textbox  err-border">
                        <label class="input_label">账号名尽量包含数字和字母</label>
                        
                        <input type="text" tabindex="1" class="tex-input tex-account" id="account" value="" name="user-account">
						
                    </div>
                    <div class="input-tip err-por"></div>
                    <div class="reg_pwd  err-border">
                        <label class="input_label">密码长度必须大于六位</label>
                        
                        <input type="password" tabindex="1" class="tex-input tex-pwd" id="userPwd" value="" name="user-pwd">

                    </div>
                    <div class="input-tip err-por"></div>
                    <div class="reg-tip">
                        <input type="checkbox" class="check" checked="checked">同意<a href="#">《有你易购会员章程》</a>
                    </div>
                    <div class="input-tip err-treaty"></div>
                    <div class="btn-sub">
                        <input type="button" class="reg-submit" id="sbt-reg" value="同意协议并注册">
                    </div>





                </form>

            </div>

        </div>



    </div>
    	
    	
    	
    	
  </body>
</html>
