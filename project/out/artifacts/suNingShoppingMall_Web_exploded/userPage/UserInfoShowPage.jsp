<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人中心</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!--引入当前页面的css样式-->
    <link rel="stylesheet" href="css/usercss/userOrderForm.css" type="text/css">
	<!--购物车css样式-->
    <link rel="stylesheet" href="css/usercss/huaweishopping.css" type="text/css">
    <link href="images/commonTopandBottom/favicon.ico"  rel="shortcut icon"  type="image/x-icon"   />
    <!--时间控件js-->
    <script src="js/dateControl/laydate/laydate.js"></script>
    <!--引入jquery-->
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<!--这是设置所有的js样式-->
    <script src="js/userjs/userOrderForm.js" type="text/javascript"></script>
     <!--这是购物车的js-->
    <script src="js/userjs/huaweishopping.js" type="text/javascript"></script>
    <!--这是设置显示订单状态的js样式-->
    <script src="
    js/userjs/showOrderFormInfo.js" type="text/javascript"></script> 
	
	
	

  </head>
  <jsp:include page="../commonhtml/storeTop.jsp"></jsp:include>
  <body>
	  <div id="orderForm_all">
	  			<form action="">
	  				<input type="hidden" value="${requestScope.stateId }" id="stateId">
	  				
	  				
	  			</form>
		        <!--左部导航-->
		        <div id="option_left">
		
		            <ul class="option_ul">
		                <li class="option_libackgroundColor">我的订单</li>
		                <li>我的购物车</li>
		                <li>我的信息</li>
		                <li>地址管理</li>
		                <li>修改密码</li>
		                <li>余额充值</li>
		            </ul>
		
		        </div>
		
		        <!--我的订单-->
		        <div class="all_function" id="orderForm_page">
		            <div class="orderForm_option ofpt">
		                <ul>
		                    <li class="orderForm_option_liInitialStyle">所有订单</li>
		                    <li class="orderForm_option_liInitialStyle">待付款</li>
		                    <li class="orderForm_option_liInitialStyle">待收货</li>
		                    <li class="orderForm_option_liInitialStyle">已收货</li>
		                    
		                    
		                </ul>
		            </div>
		            <div style="clear: both;"></div>
		            <div id="orderForm_body">
		                <div class="orderForm_body_top">
		                    <ul>
		
		                        <li class="orderForm_body_top01">商品名称</li>
		                        <li>售价</li>
		                        <li>数量</li>
		                        <li>总金额(元)</li>
		                        <li>交易状态</li>
		                        <li>交易操作</li>
		                    </ul>
		                </div>
		                <!--这是显示订单的div-->
		                <c:forEach items="${requestScope.uof}" var = "s">
			                <div class="orderForm_body_body">
			                    <div class="show_orderNumberAndTime">
			                        <p class="orderNumber">订单号:${s.orderFormNumber }</p>
			                        
			                        
			                        <p class="orderTime">下单时间:
			                        	<fmt:formatDate value="${s.nextOneTime }" type="both" pattern="yyyy-MM-dd HH:mm"/>
			                        </p>
			                    </div>
			                    <ul>
				                        <li class="orderForm_body_body_top01">
				                            <img src="${s.commodity.commodityImage }" alt="商品">
				                            <p>${s.commodity.commodityName }</p>
				                        </li>
				                        <li><span>￥${s.commodity.commodityPrice }</span></li>
				                        <li><span>${s.addCommodityCount }</span></li>
				                        <li><span class="zongmoney">￥${s.addCommodityCount * s.commodity.commodityPrice }</span></li>
				                        <li><span style="color:red;">${s.ofs.getOrderFormType() }</span></li>
										<c:if test="${s.orderFormStateId eq 1 }">
						                        <!--这是未付款样式 payMoney 是前去支付  cancelOF是取消订单-->
						                        <li class="hh">
						                            <p class="cancel_orderForm execute cancelOF"><a href="userRemoveOrderForm.do?orderFormId=${s.id }"  onclick="return confirm('确定移除该订单？')">取消订单</a></p>
						                            <p class="goma_commodity execute payMoney">现在支付</p>
						                             <!-- 这是 购买商品输入密码的div -->
						                            <div class="goumaiCommodity_payMoney">
						                                <form action="orderFormSettle.do" method="post">
						                                	<input type="hidden"  name="userAccount"  value="${requestScope. user.userAccount}">
						                                	<!-- 这是买完商品账户剩余的钱 -->
						                                	<input type="hidden"  name="YueMoney"  value=""   class="shengyuMoney">
						                                	<!-- 商品数量 -->
						                                	<input type="hidden"  name="commodityCount"  value="${s.addCommodityCount }" >
						                                	<!-- 商品id -->
						                                	<input type="hidden"  name="commodityId"  value="${s.commodityId }">
						                                	<!-- 订单id -->
						                                	<input type="hidden"  name="orderFormId"  value="${s.id }">
						                                	<!-- 订单状态id -->
						                                	<input type="hidden"  name="orderFormStateId"  value="${s.orderFormStateId+1 }">
						                                    <label >请输入您的密码：</label><input type="password" class="ipt"><br>
						                                    <div class=" err-prompt  err-inof">密码错误</div>
						                                    <input type="button" value="提交" class="sub"> <input type="button" value="取消" class="sub cancel">
						                                </form>
						                            </div>
						                        </li>
										</c:if>
										<c:if test="${s.orderFormStateId eq 2 }">
						                        
						                        <!--这是确认收货样式  consig是确认收货  点击确认收货是 把 订单id 和 状态带走-->
						                        <li>
						                            <p class="goma_commodity execute consig" style="margin-top:25px;">
						                            <a href="orderFormStateUpdate.do?stateId=${s.orderFormStateId+1 }&orderFormId=${s.id}" class="quer_shouhuo" onclick="return confirm('确定收货');">确认收货</a>
						                            </p>
						                        </li>
										</c:if>
										<c:if test="${s.orderFormStateId eq 3 }">
						                        <!--这是已收货样式 -->
						                        <li>
						                            
						                        </li>
										</c:if>
			                    </ul>
			                </div>
		                </c:forEach>
		            </div>
		            </div>
		
		        <!--我的购物车-->
		        <div class="all_function" id="shoopcity_page">
		                    <!--购物车头部-->
		                    <div class="body-top" id="check">
		                        <i><input type="checkbox" class="checkAll"></i>
		                        <p class="hh">商品</p>
		                        <span>单价(元)</span>
		                        <span>数量</span>
		                        <span>小计(元)</span>
		                        <span class="last">操作</span>
		                    </div>
		                    <c:forEach items="${requestScope.shve }"  var="sh">
				                    <div class="body-shopping shopping">
				                        <table>
				                            <tbody>
				                                <tr>
				                                    <td class="td-check">
				                                        <input type="checkbox" class="checked"  value="${sh.id }">
				                                        <!-- 这是设置隐藏的商品id -->
				                                        <input type ="hidden"  class="commodityId" value="${sh.commodityId }">
				                                        <!--  这是用户选择的商品数量-->
				                                        <input class="num" type="hidden"  value="${sh.commodityCount }">
				                                    </td>
				                                    <td class="td-shopping">
				                                        <div>
				                                            <p><img src="${sh.commodity.commodityImage }" alt=""></p>
				                                            <a href="">${sh.commodity.commodityName }</a>
				                                            <span></span>
				                                        </div>
				                                    </td>
				                                    <td class="td-price">
				                                        <p>¥${sh.commodity.commodityPrice }</p>
				                                    </td>
				                                    <td class="td-num">
				
				                                        <input class="num" type="text" readonly="readonly" value="${sh.commodityCount }">
				                                        <a title="加" class="jia" href="#hh"></a>
				                                        <a title="减" class="jian" href="#hh"></a>
				                                    </td>
				                                    <td class="td-money">
				                                        <p class="money"></p>
				                                    </td>
				                                    <td class="td-remove">
				                                        <a class="remove" href="" title="删除" ></a>
				                                    </td>
				                                </tr>
				                            </tbody>
				                        </table>
				                    </div>
		                    </c:forEach>
		                    <div class="body-bottom">
		                        <div class="checked-remove">
		                            <input type="checkbox" class="checkAll">
		                            <p class="quanx">全选</p>
		                            <p class="remove">删除选中商品</p>
		                        </div>
		                        <div class="total-money">
		                            <p class="total-p1">总计金额: <span class="total">￥0.00</span></p>
		                            <p class="total-p2">共节省: <span>￥0.00</span></p>
		                            <p class="total-p3 ">合计(不含运费): <span class="total total2">￥0.00</span></p>
		                        </div>
		                    </div>
		                    <div class="bottom-top">
		                    		<!--  结算商品表单 -->
		                    		<form action="SettlementShoppingCartCommodity.do"  method="post"  id="SettleAccountsCommodity">
		                    				<!-- 这是储存往订单里添加的商品信息 包括商品id 购买的商品数量 和购物车的id -->
		                    				<input  type="hidden"  name = "orderFormObj"  id="ofObject"  value="">
		                    				
		                    				<!-- 用户账户 -->
		                    				<input  type="hidden"  name = "userAcc"  value="${requestScope. user.userAccount}">
		                    				<!-- 用户买完商品后账户剩余的余额 -->
		                    				<input  type="hidden"  name = "shengyuMoney"  id="syMoney"  value="">
		                    				
		                    		</form>
		                            
		                            <p class="settle"  onclick="commoditySettleAccounts()">去结算</p>
		                    </div>
		        </div>
		
		        <!--用户信息管理-->
		        <div class="all_function" id="userInfo_page">
			            <h3>个人资料</h3>
			            <div class="userInfo_div">
			                <label class="lbl">账号:</label><!--  获取用户账户-->
			                <input type="text" disabled="disabled" id="userAccount" value="${requestScope. user.userAccount}" name="userAccount" class="ipt userAccunte"><br>
			                <label class="lbl">姓名:</label><input type="text"  value="${requestScope.userInfo.userName}" name="name" class="ipt check_value"><br>
			                <label class="lbl">性别:</label>
			                <input type="hidden"  id="gender" value="${requestScope.userInfo.userGender}">
			                <input type="radio" name="userGender" value="男" class="ipt" id="man" ><label for="man">男</label>
			                <input type="radio" name="userGender" value="女" class="ipt" id="woman" ><label for="woman">女</label><br>
			                <fmt:formatDate value="${requestScope.userInfo.userBirthDate }" type="both" pattern="yyyy-MM-dd"  var="borthDate"/> 
			                <label class="lbl">出生日期:</label>
			                <input class="laydate-icon ipt check_value" id="demo" value="${borthDate}" name="birthDate"><br>
			                <label class="lbl">身份证号:</label><input type="text"   maxlength="18" value="${requestScope.userInfo.userIdentityMark}" name="dentityMark" class="ipt check_value"><br>
			                <label class="lbl">手机号:</label><input type="text" maxlength="11" value="${requestScope.userInfo.userPhone}" name="phone" class="ipt check_value"><br>
			                <label class="lbl">Email:</label><input type="text"  value="${requestScope.userInfo.userEmail}" name="email" class="ipt check_value"><br>
			                <input type="button" value="保存" class="sub" id="addUserInfo_and_EditUserInfo" onclick="addUserInfoandEditUserInfo()">
			            </div>
			            <script>
				                !function(){
				                    	laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
				                    	laydate({elem: '#demo'});//绑定元素
				                }();
			            </script>
		
		
		        </div>
		        <!--地址管理-->
		        <div class="all_function" id="consigAddress_page">
		            <p style="font-size:13px; color:red; margin-left:15px;">收货地址管理(选择表示为默认)</p>
		            <div class="userAddress_manage">
						<c:forEach items="${requestScope.userAddress }"  var="address">
				                <input type="radio" class="ipt" value="${address.acquiesceStatus }"  name="userAddress"  data="${address.id }"  id="addressId${ address.id}">
				                <label class="lbl" for="addressId${address.id }">${address.consigneeAddress}</label><br>
						</c:forEach>
		                <span class="botton_address default" onclick="moRenAddress()">设置默认</span>
		                <span class="botton_address delete" onclick="deleteAddress()">删除</span>
		                <span class="botton_address add" >增加</span>
		            </div>
		            <div class="add_userAddress">
		                	<!-- 添加用户地址 -->
							<form action="userAddaddress.do"  method="post">
			                    <label class="lbl">请输入您的收货地址</label>
			                    <input type="hidden"  name = "userAccount"  value="${requestScope. user.userAccount}">
			                    <input type="text" value="" id="userAddress" class="ipt_address"  name="consigneeAddress">
			                    <input type="button" value="添加" class="sub_addAddredd"><input type="button" value="取消" class="sub_cancel2">
		                	</form>
		             </div>
		        </div>
		        <!--修改密码div-->
		        <div class="all_function" id="updatePwd_page">
		            <P style="color:red; font-size:13px; margin-left:20px;">修改密码</P>
		
		            <div class="update_pwd">
		                
		                    <label class="lbl">原密码</label><br>
		                    <input type="password" name="jiu_pwd"  class="ipt jiupwd">
		                    <span class="err_prompt err_yuanpwd"></span>
		                    <br>
		                    <label class="lbl">请密码</label><br>
		                    <input type="password" name="xin_pwd" class="ipt xinpwd">
		                    <span class="err_prompt err_xinpwd">两次密码不一致</span>
		                    <br>
		                    <label class="lbl">确认密码</label><br>
		                    <input type="password" name="xin_pwd" class="ipt querpwd">
		                    <span class="err_prompt err_xinpwd">两次密码不一致</span>
		                    <br>
		                    <input type="button" value="提交" class="yes_btn btn" onclick="checkXinPwd()">
		                
		            </div>
		        </div>
		        <!--账户充值-->
		        <div class="all_function" id="add_balance">
	            <h3 style="margin:20px 0px 10px 20px;">余额充值</h3>
	            <div class="userBalance_div">
	                <form action="">
	                    <label class="lbl">当前余额为：</label>
	                    <input type="text" class="ipt dangq_ye" value="${requestScope.user.accountBalance }" disabled="disabled"   id="userBalance">
	                    <br>
	                    <label class="lbl">请输入您要充值的金额:</label><input type="text" name="accountBalance" class="ipt accountBalance">
	                    <label style="font-size:12px; color:red;">金额必须大于100元人民币</label>
	                    <br>
	                    <input type="button" class="sbm" value="确认充值" onclick="chongzhiYue()">
	                </form>
	            </div>
	
	        </div>
	  </div>
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  
  	
    	
  </body>
  <jsp:include page="../commonhtml/storeBottom.jsp"></jsp:include>
</html>
