
$(function(){

    $(".topAll-top .top-left li").hover(function(){
        
        $(this).children("ul").slideDown();
        
    },function(){
        $(this).children("ul").slideUp();
    });
    
    $(".topAll-top .top-right .userUrl").click(function(){
    	checkLoginTopPage();
    });

});

//判断用户又没登陆
function checkLoginTopPage(){
	var Account = $("#userAccount").val();  // 这是获取用户的账号
	var userUrl = $(".topAll-top .top-right .userUrl");
	if(Account == ""){// 表示没有登陆
		alert("您还没有登陆！")
		//  这是从首页面进入登陆页面
		userUrl.attr("href","UserLogin.jsp?pageId=0"); // 没有登陆就他的href属性赋上去登陆页面的值
		
	}else{// 表示登陆过了
		userUrl.attr("href","showUserOrderFormInfo.do");  // 登陆过后就去个人页面
	}
	
}