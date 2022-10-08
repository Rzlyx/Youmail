
$(document).ready(function(){
    //选择全选标签的执行函数
    $(".body-bottom .quanx").click(function(){
        //alert("hh")
        var booles = $(this).prev().is(":checked");
        if(booles){
            $(this).prev().attr("checked",false);
            $("#shoopcity_page .shopping :checkbox").attr("checked",false);
            $(".body-top .checkAll").attr("checked",false)
            calculateSumPrice();
        }else{
            $(this).prev().attr("checked",true);
            $("#shoopcity_page .shopping :checkbox").attr("checked",true);
            $(".body-top .checkAll").attr("checked",true)
            calculateSumPrice();
        }

        //$(".checkAll").attr("checked",booles);
    });
    //选择全选框的函数
    $(".checkAll").click(function(){
        var booles = $(this).is(":checked");
        if(booles){
            $(".body-top .checkAll").attr("checked",booles);
            $("#shoopcity_page .shopping :checkbox").attr("checked",booles);
            $(".body-bottom .checkAll").attr("checked",booles);
            calculateSumPrice();
        }else{
            $(".body-top .checkAll").attr("checked",booles);
            $("#shoopcity_page .shopping :checkbox").attr("checked",booles);
            $(".body-bottom .checkAll").attr("checked",booles);
            calculateSumPrice();
        }

    });
    //判断单个复选框
    $(".checked").click(function(){
        checkeddange();
    });





    //删除单个的商品
    $(".shopping .remove").click(function(){
    	var dom = $(this);
        if(confirm("确定删除该商品？")){
        	var shveId = $(this).parent().siblings(".td-check").children(".checked").val();
        	$.post("userDeleteVehicleCommodity.do",{shveId:shveId},function(data){
        		var result = data;
        		if(result >0)
        			 removeShopping(dom);
        		else
        			alert("删除失败！");
        	});
            return false;
        }
        return false;
    });
    //删除所有选中的复选框
    $(".body-bottom .remove").click(function(){
    	var remChe = $("#shoopcity_page .shopping :checkbox[checked=checked]");
    	var shoppingId = new Array();
    	if(remChe.length <= 0){
    		alert("请选择后再删除");
    		return;
    	}
        if(confirm("确定删除该商品？")){
        	
        	remChe.each(function(){
        		shoppingId.push($(this).val());
        	});
        	$.ajax({  
                type:'post',  
                traditional :true,  
                url:'userForShopping.do',  
                data:{'shoppingId':shoppingId},  
                success:function(data){  
                    var result = data;
                    if(result >0){
                    	removeShopping(remChe);
                    }else{
                    	alert("删除失败");
                    }
                }  
            });  

        	/*$.post("userForShopping.do",{shoppingId:shoppingId},function(data){
        		var result =data;
        		if(result >0){
        			removeShopping(remChe);
        		}else{
        			
        		}
        	});*/
        }
        return false;

    });
    //计算总结与小计
    function productCount(){
        var $shopping = $(".shopping");
        var money = 0;

        $shopping.each(function(i,dom){
            var num = $(dom).find("[class='num']").val();
            var xiaoji = $(dom).find("td[class='td-price'] p").text();
            $(dom).find(".money").text("¥"+(num*xiaoji.replace("¥","")).toFixed(2));
        })
    }

    productCount(); // 初始化小计的总价格
    //执行商品数量的操作 如果参数flad为true是则执行增加，否则。。。
    function changeNumber(flad,dom){
        var doms = $(dom).find("[class=num]");
        var value = doms.val();
        //var number = 1;
        if(flad){
            value++;
        }else{
            value--;
            if(value <= 0){
                value = 1;
                alert("数量不能小于1")
            }
        }
        doms.val(value);
        productCount();
        //calculateSumPrice();
    }


    //当要添加数量是的函数
    $(".td-num .jia").click(function(){
        //alert();
        changeNumber(true,$(this).parent().parent().parent().parent().parent());
        calculateSumPrice();
        return false;
    });
    //要减去数量时的函数
    $(".td-num .jian").click(function(){
        changeNumber(false,$(this).parent().parent().parent().parent().parent());
        calculateSumPrice();
        return false;
    });





});

    //删除购物车商品信息函数
    function removeShopping(shopping){
        $(shopping).parent().parent().parent().parent().parent().remove();

        
        calculateSumPrice();
    };



    //判断单个复选框
    function checkeddange(){
        var chebcbox = $(".checked");
        var size = chebcbox.size();
        var k = 0 ;
        chebcbox.each(function(index,dom){
            if($(dom).is(":checked")){
                k++;
            }
        })
        if(k==size){
            $(".body-bottom .checkAll").attr("checked",true);
            $(".body-top .checkAll").attr("checked",true);
            calculateSumPrice();
        }else{
            $(".body-bottom .checkAll").attr("checked",false);
            $(".body-top .checkAll").attr("checked",false);
            calculateSumPrice();
        }
    }

    //计算总价格的函数
    function calculateSumPrice(){
        var $shopping = $(".shopping");
        var sum = 0;
        $shopping.each(function(i,dom){

            if($(dom).find(".checked").prop("checked")){
                var money = $(dom).find(".money").text();
                sum+= parseInt(money.trim().replace("¥",""));


            }
            //alert("hh")


        })
        $(".body-bottom .total-money .total").text("¥"+sum.toFixed(2));
    }
    
    // 商品结算时执行的函数
    function commoditySettleAccounts(){
    	var remChe = $("#shoopcity_page .shopping :checkbox[checked=checked]"); // 获取所有选择的复选框
    	var userMoney = $("#userBalance").val(); // 用户的当前余额
    	var consumptionMoney = parseFloat($(".body-bottom .total-money .total2").text().replace("¥","")); // 商品结算时的总价格
    	if(remChe.length <= 0){// 判断用户有没有选中商品
    		alert("请选中需要购买的商品时再点击结算按钮！");
    		return;
    	}
    	if(parseFloat(userMoney) < consumptionMoney){  // 判断用户余额是否充足
    		alert("余额不足！");
    		return ;
    	}
    	userMoney = parseFloat(userMoney) - consumptionMoney;  // 计算购买完商品后账户剩余的钱
    	$("#syMoney").val(userMoney);// 这是吧剩余的余额赋值到隐藏域里
    	var orderFormArray = new Array();
    	
    	remChe.each(function(){// 循环所有选择的复选框
    		var shoopObj = {};
    				shoopObj.shoopId = $(this).val(),
    				shoopObj.commodityId = $(this).next().val(),
    				shoopObj.commodityCount = $(this).next().next().val();
    		orderFormArray.push(shoopObj);
    	});
    	// 把用户选择的赋值到隐藏域里
    	$("#ofObject").val(JSON.stringify(orderFormArray));
    	$("#SettleAccountsCommodity").trigger("submit"); // 提交表单
    	//alert(userMoney)
    	
    	
    }


