$(function(){
   
	 		
	
    // 当点击加数量是执行的函数
    $(".commodity_info .jia").click(function(){
        var kucun = $(".commodity_info .commodityKucun").val(); // 这是商品的库存数量
        var num = $(".commodity_info .num").val(); // 这是用户选择的商品数量
        if(num == kucun){ // 表示 选择的数量已经和库存数量已样了 就不能选择了
        	
        }else{ // 表示 继续添加数量
        	num++;
            var commodityMoney = $(".commodity_info .commodityMoney").text(); // 获取商品价格
            var sumMoney = $(".commodity_info .commoditySumMoney").val();  // 这是获取数量计算后商品的价格
            sumMoney = parseFloat(sumMoney) + parseFloat(commodityMoney);  // 把商品的钱进行累加
            $(".commodity_info .commoditySumMoney").val(sumMoney); // 在赋值到原本的标签里
            $(".commodity_info .num").val(num);
        }
    })
    // 当点击减数量是执行的函数
    $(".commodity_info .jian").click(function(){
        var num = $(".commodity_info .num").val();// 这是用户选择的商品数量
        if(num == 1){
            
        }else{
            num--;
            var commodityMoney = $(".commodity_info .commodityMoney").text(); // 获取商品价格
            var sumMoney = $(".commodity_info .commoditySumMoney").val();  // 这是获取数量计算后商品的价格
            sumMoney = parseFloat(sumMoney) - parseFloat(commodityMoney);
            $(".commodity_info .commoditySumMoney").val(sumMoney);
            $(".commodity_info .num").val(num);
        }
    })
    // 这是当点击 加入购物车执行的事件
    $(".city_goma .btn_city").click(function(){
        addcity($(this));
    });
    // 这是当点击 加入购物车的差号的事件
    $(".city_goma .close_img").click(function(){
        caleancity();
        
    });
    // 当点击 购买商品按钮是执行的事件
    $(".city_goma .btn_goma").click(function(){
    	if(checkLogin()){
    		addOrderForm()
       		 	return ;
    	}
    	return ;
       
    });
    // 当点击 购买商品取消按钮是执行的事件
    $("#goumai .cancel").click(function(){
        $("#goumai .ipt").val("");
        $("#goumai").hide();
    });
    // 当点击 提交购买商品提交按钮是执行的事件
    $("#goumai .sub").click(function(){
    	var dom = $(this);
       var Account = $("#userAccount").val(); // 获取账号
       var pwd = $("#goumai .pwd").val();  // 获取密码
       var userMoney = dom.siblings(".accountBalance").val();// 获取用户账户余额
       var sumMoney = $(".commodity_info .commoditySumMoney").val();// 获取和商品数量计算完的商品总价格
       var num = 0; // 该数字用来表示是验证密码的标识
       if(pwd != "" && pwd != null && pwd.length>=6){
    	   $.post("checkUserLogIn.do",{check:num,userAccount:Account,userPwd:pwd},function(data){
    		   var executeResult = data;
    		   if(executeResult == 1){// 表示密码输入正确
    			   if(parseFloat(userMoney) >= parseFloat(sumMoney)){// 判断 用户的余额是否大于商品的钱
    				   userMoney = userMoney-sumMoney  // 这是吧用户的余额减去商品价钱 剩下的就是用户钱了
    				   dom.siblings(".shengyuMoney").val(userMoney)  // 这是吧用户剩下的钱 赋值到隐藏域里 一并提交走
    				    $("#Myform").trigger("submit");// 提交表单
    			   }else{ // 余额不够
    				   alert("余额不足");
    			   }
    		   }else{// 密码错误
    			   $("div.err-inof").show();
    		   }
    	   });
           
       }else{
           return;
       }
    });
    
});


// 这是当点击 加入购物车执行的事件
function addcity(dom){
		
    	var commodityId = $("#commodityId").val(); // 这是获取 商品id
    	var count = $(":text.num").val() // 获取商品数量;
    	var Account = $("#userAccount").val();  // 这是获取用户的账号
    	if(checkLogin()){// 表示登陆表示一登陆可以加入购物车
    		$.post("joinShoppingVehicle.do",{comid:commodityId,userAccount:Account,commodityCount:count},function(data){
    			var result = data;// 接收加入购物车是否成功 信息 1表示成功 0 表示加入购物车失败
    			if(result == 1){
	    			$(".city_goma").addClass("borde");
	        		$(".city_goma .close_img").show();
	        	    $(".city_goma .add_city_hid").show();
	        	    $("#commodity .city_goma .dianjiq").hide();
	                $("#commodity .city_goma .dianjih").show();
    			}else{
    				$(".city_goma").addClass("borde");
	        		$(".city_goma .close_img").show();
	        	    //$(".city_goma .add_city_hid").show();
	        	    $("#commodity .city_goma .dianjiq").hide();
	                $("#commodity .city_goma .dianjih").show();
    			}
    		});
    	}
    	return;
}
// 这是当点击 加入购物车的差号的事件
function caleancity(){
    $(".city_goma").removeClass("borde");
        $(".city_goma .close_img").hide();
        $(".city_goma .add_city_hid").hide();
        $("#commodity .city_goma .dianjiq").show();
        $("#commodity .city_goma .dianjih").hide();

}

// 判断用户又没登陆
function checkLogin(){
	var Account = $("#userAccount").val();  // 这是获取用户的账号
	var commodityId = $("#commodityId").val();// 获取商品id
	var braodTypeId = $("#barodId").val();  // 获取商品大类id
	if(Account == ""){// 表示没有登陆
		alert("您还没有登陆！")
		//  这是从商品信息页面进入登陆页面
		location.href="UserLogin.jsp?pageId=1&comId="+commodityId+"&braodTypeId="+braodTypeId;
		
		return false;
	}
	return true;
}


// 当用户点击购买商品按钮是执行加入订单函数
function addOrderForm(){
	var gommaiDom = $("#goumai");
	var commodityId = $("#commodityId").val(); // 这是获取 商品id
	var count = $(":text.num").val() // 获取商品数量;
	var Account = $("#userAccount").val();  // 这是获取用户的账号
	
	// 执行 servel 加入订单
	var  status = 0;
	$.post("addOrderForm.do",{comId:commodityId,userAccount:Account,commodityCount:count},function(data){
		
		status = data; // 获取是否添加订单成功结果
		if(status >0  ){ // 大于0 表示添加成功 并且同时也获取到了刚添加成功的订单id
			$("#comCount").val(count);// 把刚才添加的订单的商品数量赋值到隐藏域里
			$("#ofId").val(status); // 把刚才添加的订单id 赋值到隐藏域里
			gommaiDom.show();
			
			
			return true;
		}else{
			return false;
		}
			
	});
	
	
}

    