	$(function(){
	    
	    // 这是登陆时文本框获取焦点
	    $("#login_account").focus(function(){
	        textFocusClick($(this));
	    });
	    // 这是注册时文本框获取焦点
	    $("#account").focus(function(){
	        textFocusClick($(this));
	    });
	    // 这是注册时文本框获取焦点
	    $("#userPwd").focus(function(){
	        textFocusClick($(this));
	    });
	    // 这是登陆时文本框获取焦点
	    $("#login_userPwd").focus(function(){
	        textFocusClick($(this));
	    });
	    // 这是注册时文本框时 失去焦点事件
	    $("#account").blur(function(){
	        $(this).parent().next(".err-por").text("");
	        checkTextValue($(this),$(this).val());  // 这是进行判断文本框是否为空和长度是否合法
	        
	        checkAccountisExist($(this),$(this).val());  // 这是进入判断是否已经有该账户了
	        
	    });
	    // 这是登陆时文本框时 失去焦点事件
	    $("#login_account").blur(function(){
	        checkTextValue($(this),$(this).val())
	    });
	    // 这是注册时文本框时 失去焦点事件
	    $("#userPwd").blur(function(){
	        $(this).parent().next(".err-por").text("");
	        checkTextValue($(this),$(this).val())
	        
	    });
	    // 这是登陆时文本框时 失去焦点事件
	    $("#login_userPwd").blur(function(){
	        checkTextValue($(this),$(this).val())
	    });
	    // 这是点击注册按钮是触发的函数
	    $("#sbt-reg").click(function(){
	        if(registerAccountCheck()){
	            $("#MyForm").trigger("submit");
	        }else{
	            alert("账户或密码格式不正确！");
	        }
	    });
	    // 这是用户点击登陆按钮是的事件函数
	    /*$("#login_user").click(function(){
	        if(longInCheck()){
	            alert("登陆成功");
	            return false;
	        }else{
	            alert("账户或密码格式不正确")
	            $(".login_form .err_msg").addClass("hide");
	            $(".login_form .err_msg p").show();
	            return false;
	        }
	    })*/
	});
	
	// 这是 文本框获取焦点时的事件
	function textFocusClick(dom){
	    $(dom).prev().hide();
	}
	
	// 这是点击登陆时的函数判断
	function longInCheck(){
		// 表示用户输入的账户和密码都是合法的
	    if($("#login_account").val().length>=6 && $("#login_userPwd").val().length>= 6){
	    	var usacc = $("#login_account").val();
	    	var uspwd = $("#login_userPwd").val();
	    //  表示 进入那个页面的id 0 表示进入首页 1表示进入商品信息页面 2 表示进入用户个人中心
	    	var pageId = $("#enterPageWay").val();   // 表示进入那个页面的id
	    	var num = 1; // 该数字用来表示是用户登录的标识
	    	$.post("checkUserLogIn.do",{check:num,userAccount:usacc,userPwd:uspwd},function(data){
	    		var res = data;
	    		if(res == 1){
	    			if(pageId == 0){
		    			location.href = "showSuNing.do";// 这是往首页跳转的
		    	    	return true;
	    			}
	    			else if(pageId == 1){
	    				var commodityId = $("#commodityId").val();// 获取商品id
	    				var braodTypeId = $("#barodId").val();  // 获取商品大类id
	    				// 这是往商品信息页面跳转的  并且把商品信息页面需要的商品id和商品大类id一并传过去
	    				location.href = "getCommodityInfo.do?comId="+commodityId+"&braodTypeId="+braodTypeId;
		    	    	return true;
	    			}
	    			else if(pageId == 2){
	    				location.href = "showUserOrderFormInfo.do";// 这是往用户个人中心跳转的
		    	    	return true;
	    			}
	    		}else{
	    			$(".login_form .err_msg").addClass("hide");
	                $(".login_form .err_msg p").html("账户名与密码不匹配请重新输入！" +
	                "<a href='#'>忘记密码</a>或<a href='#'>账户名</a>?").show();
	                return false;
	    		}
			});
	    }else{
	    	 // 直接提示 用户输入的账户或密码不合法
		    $(".login_form .err_msg").addClass("hide");
	        $(".login_form .err_msg p").text("您输入的账户或密码格式不正确！").show();
		    return false;
	    }


	}
	
	// 这是注册时的 函数判断 
	function registerAccountCheck(){
	
	    if($("#userPwd").val().length>=6 && $("#account").val().length>=6){
	        return true;
	    }
	    return false;
	}
	
	// 这是鼠标离开注册文本框的事件
	function checkTextValue(dom,value){
	    if(value == ""){
	        $(dom).prev().show();
	        return false;
	    }
	    if(value.length < 6){
	        $(dom).parent().next(".err-por").text("长度必须大于6位！");
	        return false;
	    }
	    return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// 这个函数是用来判断 该账户是否存在
	function checkAccountisExist(dom,value){
		if(value != "" && value.length>= 6){
			
			$.post("SelectUserCount.do",{userAccount:value},function(data){
				var account = data;
				if(account == 1){
					$(dom).parent().next(".err-por").text("该账户已存在!");
					$("#sbt-reg").attr({"disabled":"disabled"});  // 让提交表单禁用
				}else{
					$(dom).parent().next(".err-por").text("");
					$("#sbt-reg").removeAttr("disabled");
				}
		});
		}
		
	}