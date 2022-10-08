$(function(){


     // 获取当前点击的订单状态id'
    var stateId = $("#stateId").val();
    $("#orderForm_page .ofpt li").addClass("orderForm_option_liInitialStyle");
   $("#orderForm_page .ofpt li").eq(stateId).addClass("orderForm_option_liStyle");//显示订单状态样式


    //showAllOrderForm()





    // 这是点击 订单状态是 执行的函数
    $("#orderForm_page .ofpt li").click(function(){
        $("#orderForm_page .ofpt li").addClass("orderForm_option_liInitialStyle");
        $(this).addClass("orderForm_option_liStyle");
        $(this).siblings("li").removeClass("orderForm_option_liStyle");
        var orderFormOptionIndex = $(this).index();
        selectOrderFomrStateId(orderFormOptionIndex);//调用进入servlet页面的函数
        
    });

});

function selectOrderFomrStateId(stateid){
	// 通过订单状态id 进入servelt查询结果
	location.href = "showUserOrderFormInfo.do?orderFormStateId="+stateid;
	
}


// function showAllOrderForm(){

//     $("#orderForm_body .orderForm_body_body").html("<h1>所有订单</h1>");



// }