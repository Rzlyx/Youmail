$(function(){


    // 这是 点击左侧导航栏是执行的函数
    $("#option_left .option_ul li").click(function(){
        $(this).addClass("option_libackgroundColor").siblings("li").removeClass();
        $(".all_function").eq($(this).index()).show().siblings(".all_function").hide();
    });

    var gender = $("#gender").val();
    $("#userInfo_page .userInfo_div input[name='userGender'][value='"+gender+"']").prop("checked",true)
    
    


    // 当焦点离开输入原密码是 执行的函数
    $("#updatePwd_page .update_pwd .jiupwd").blur(function(){
        
        checkJiuPwd();
    });


    // 这是点击增加地址按钮是的函数'
    $("#consigAddress_page .userAddress_manage .add").click(function(){
        $("#consigAddress_page .add_userAddress").show();
    });
    
    $("#consigAddress_page .userAddress_manage .ipt[name='userAddress'][value='0']").prop("checked",true);
    
    //这是添加地址的div 点击取消 按钮是的函数
    $("#consigAddress_page .add_userAddress .sub_cancel2").click(function(){
       
        $(this).siblings(".ipt_address").val("");
        $(this).parent().parent(".add_userAddress").hide();
    })
    // 这是点击添加地址按钮执行的事件
    $("#consigAddress_page .add_userAddress .sub_addAddredd").click(function(){
    	var address = $(this).prev(".ipt_address").val();
    	if(address == "" || address.length<= 5){
    		alert("地址不合法");
    	}else{
    		$(this).parent().trigger("submit");
    	}
    });


    // 这是添加或修改用户信息的文本框失去焦点是的函数
    $("#userInfo_page .userInfo_div .check_value").blur(function(){
        checkUserInfoAddValue($(this));
        

    });


    // 当点击 购买商品按钮是执行的事件
    $("#orderForm_page .orderForm_body_body .payMoney").click(function(){
        $(this).next(".goumaiCommodity_payMoney").show();
    });


     // 当点击 购买商品取消按钮是执行的事件
    $("#orderForm_body .orderForm_body_body .goumaiCommodity_payMoney .cancel").click(function(){
        $(this).siblings(":password").val("");
        $(this).prev().prev(".err-inof").hide();
        //$("#orderForm_body .orderForm_body_body .goumaiCommodity_payMoney .ipt").val("");
        $(this).parent().parent(".goumaiCommodity_payMoney").hide()
        //$("#orderForm_body .orderForm_body_body .goumaiCommodity_payMoney").hide();
    });

    // 当点击 购买商品提交按钮是执行的事件
    $("#orderForm_body .orderForm_body_body .goumaiCommodity_payMoney .sub").click(function(){
       var dom = $(this);
       var Account = $("#userAccount").val(); // 获取账号
       var pwd = $(this).siblings(":password").val();
       var num = 0; // 该数字用来表示是验证密码的标识 0 表示验证密码用的
       if(pwd != "" && pwd != null && pwd.length>=6){
           // 进入验证密码区域
    	   $.post("checkUserLogIn.do",{check:num,userAccount:Account,userPwd:pwd},function(data){
    		   var executeResult = data;
    		   if(executeResult == 1){// 表示密码输入正确
    			   $(dom).prev(".err-inof").hide();
    			   
    			   var sumMoney =$(dom).parents("li").prev().prev().children(".zongmoney").text().substring(1);// 获取总金额
    			   
    			   var userBalance = $("#userBalance").val();// 获取账号余额
    			   
    			   if(parseFloat(userBalance) >= parseFloat(sumMoney)){// 表示余额够
    				   userBalance= userBalance-sumMoney;
    				   $(dom).siblings(".shengyuMoney").val(userBalance);//把算完的钱赋值到隐藏域里
        				$(dom).parent().trigger("submit");
    			   }else{// 余额不足
    				   alert("对不起您的余额不足!");
    			   }
    		   }else{// 表示密码错误
    			   $(dom).prev(".err-inof").show();
    		   }
    	   });
       }else{
           return;
       }
    });




});

// 这是点击充值余额是的函数
function chongzhiYue(){
	var Account = $("#userAccount").val(); // 获取账号
	 var userBalance = $("#userBalance");// 获取账号余额
    var chongzhiMoney = $("#add_balance .userBalance_div .accountBalance").val();  // 获取充值的余额
    if(chongzhiMoney >= 100){
    	var accountMoney = userBalance.val(); // 获取当前余额
    	chongzhiMoney = parseFloat(chongzhiMoney) + parseFloat(accountMoney);  // 进行累加
    	$.post("UserbalanceUpdate.do",{userAccount:Account,newMoney:chongzhiMoney},function(data){
    		var res = data;
    		if(res >0 ){ // 充值成功
    			userBalance.val(chongzhiMoney); 
    			userBalance.siblings(".accountBalance").val("");
    			alert("充值成功");
    		}else{
    			alert("充值失败");
    		}
    		
    	});
    	
       
    }else{
        alert("充值的金额必须大于100元!");
        return;
    }
}


// 这是当输入完旧密码是执行的焦点离开事件
function checkJiuPwd(){
	var sub = $("#updatePwd_page .update_pwd .btn");  // 获得这个标签对象
    var errpwd = $("#updatePwd_page .update_pwd .err_yuanpwd");// 旧密码输入错误是显示的对象
    var jiupwd = $(errpwd).prev(".jiupwd").val();  // 获取密码
    var Account = $("#userAccount").val(); // 获取账号
    var num = 0; // 该数字用来表示是验证密码的标识 0 表示验证密码用的
    $.post("checkUserLogIn.do",{check:num,userAccount:Account,userPwd:jiupwd},function(data){
        var result = data;
        if(result<=0){
        	errpwd.text("原密码输入错误").show();
            sub.attr("disabled","disabled");
        }else{
        	errpwd.hide();
            sub.removeAttr("disabled");
            
        }
   });

}

//  这是修改密码是执行的函数 判断 两次密码是否输入的一致

function checkXinPwd(){
    // 获取久密码
    var jiuPwd = $("#updatePwd_page .update_pwd .jiupwd").val();
    // 获取新密码
   var xinpwd =  $("#updatePwd_page .update_pwd .xinpwd").val();
    // 获取确认密码
   var querpwd =  $("#updatePwd_page .update_pwd .querpwd").val();
   // 获取账号
   var Account = $("#userAccount").val(); 
   if(jiuPwd == "" || jiuPwd.length<6){
       $("#updatePwd_page .update_pwd .err_yuanpwd").text("密码格式不合法").show();
       return;
   }else{
       $("#updatePwd_page .update_pwd .err_yuanpwd").text("").hide();
   }
   
   if(xinpwd != querpwd){
	   $("#updatePwd_page .update_pwd .err_xinpwd").text("两次密码不一致").show();
	   return;
   }
   if(xinpwd.length<6 || querpwd.length<6){
	   $("#updatePwd_page .update_pwd .err_xinpwd").text("密码格式不合法").show();
	   return;
   }
   
   if(xinpwd == querpwd && querpwd.length>= 6){// 条件成立后 提交表单
       $("#updatePwd_page .update_pwd .err_xinpwd").hide();
       $.post("userUpdatePwd.do",{userAccount:Account,newPwd:querpwd},function(data){
    	   var res = data;
    	   if(res >0){
    		   alert("密码修改成功,将跳到登陆页面重新登陆");
    		   //   这是从用户个人页面的修改密码进入登陆页面 
    		   location.href = "UserLogin.jsp?pageId=2";
    		   
    	   }else{
    		   alert("密码修改失败");
    	   }
       });
   }

}

// 这是删除地址是的函数
function deleteAddress(){
    var mor = $(":radio[name='userAddress']");
    if(mor.size() == 1){
    	alert("必须至少有一个地址！");
    	return;
    }
    $(mor).each(function(){
        if($(this).attr("checked")){
            if(confirm("您确定删除该默认地址吗？")){
            	var addressId = $(this).attr("data"); //地址id
            	$(this).next().next().remove();
            	$(this).next().remove();
                 $(this).remove();
            	$.post("deleteConsigneeAddress.do",{addressId:addressId},function(data){
            		var res = data;
            		if(res>0){
            			alert("删除成功");
            		}
            	});
            	
            }else{
                alert("删除已取消")
            }
        }
    });
}


// 这是设置默认地址的函数
function moRenAddress(){
    
    var mor = $(":radio[name='userAddress']");
    $(mor).each(function(){
        if($(this).attr("checked")){
            if(confirm("您确定要把该地址设置为默认收货地址吗？ :"+ $(this).next().text())){
            	var addressId = $(this).attr("data");// 地址id
            	var Account = $("#userAccount").val();
                $.post("updateAcquiesceStatus.do",{userAccount:Account,addressId:addressId},function(data){
                	var res = data;
                	if(res >0)
                		alert("设置成功")
                });
            }else{
                alert("设置已取消")
            }
        }
    });
}

// 这是添加或修改用户信息的文本框失去焦点是的函数
function checkUserInfoAddValue(dom){
    var text_value = $(dom).val();
    if(text_value == "" || text_value.length < 1){
        $(dom).next("i").remove();
        $(dom).after("<i style='color:red; font-size:12px;'>该信息不能为空</i>");

    }else{
        $(dom).next("i").remove();
        $(dom).next("i").remove();
    }
}  


// 这是添加和修改 用户信息的函数
function addUserInfoandEditUserInfo(){

    var userinfos = $("#userInfo_page .userInfo_div .check_value");
    var infosResult = 0; // 用来判断 文本框是否合法
    $(userinfos).each(function(index){

        if($(this).val() == "" || $(this).val().length<3){
            $(this).next("i").remove();
            $(this).after("<i style='color:red; font-size:12px;'>信息格式不合法</i>");
        }else{
            $(this).next("i").remove();
            infosResult++;
        }
    });
    
    // 获取用户信息
    var account = $("#userAccount").val();
    var name = $("#userInfo_page .userInfo_div input[name='name']").val();
    //var Gender = $("#userInfo_page .userInfo_div input[name='userGender']").val();
    var gender = "";
     var $gender = $("#userInfo_page .userInfo_div input[name='userGender']");
     $($gender).each(function(){
    	 if($(this).attr("checked")){
    		 gender = $(this).val();
    	 }
     });
    var birth = $("#userInfo_page .userInfo_div input[name='birthDate']").val();
    var dymk = $("#userInfo_page .userInfo_div input[name='dentityMark']").val();
    var he = $("#userInfo_page .userInfo_div input[name='phone']").val();
    var em = $("#userInfo_page .userInfo_div input[name='email']").val();
    if(infosResult == 5){
//        alert(account);
//        alert(name);
//        alert(gender);
//        alert(birth);
//        alert(dymk);
//        alert(he);
//        alert(em);
        // 开始进行 添加或修改
        $.post("userUpdateInfo.do",{useracc:account,userName:name,usersex:gender,userdate:birth,usermk:dymk,userhe:he,userem:em},function(data){
        	
        	var executResult = data;
        	if(executResult == 1){
        		alert("修改成功");
        	}else{
        		
        	}
        	
        		
        		
        	
        	
      });
        
    }

} 




