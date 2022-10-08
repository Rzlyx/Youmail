$(function(){
	
	
	/*$("#nvg_top1>dd a:first").css("margin-left","50px");*/
	
    /* 导航标签 li 当鼠标悬浮事件 */
    $("#commodity_nvg>li").hover(function(){
    	
        $("#nvg_top1").show();
        
        
        
        li($(this).attr("data"));
        // $.post("",{dao:2},function(data){
        //     var hh = eval("data");
        //     $(hh).each(function(){
        //         var id = hh.id;
        //         $("td").append("<dd>"+id+"</dd>");
        //         $.post("lif",{comid:id},function(data2){

        //         })
        //     })
        // })
      
    },function(){
        $("#nvg_top1").hide();
    });
    /* 隐藏的导航div  悬浮上面后显示 离开后 隐藏 */
    $("#nvg_top1").hover(function(){
        $(this).show();
    },function(){
        $(this).hide();
    });

    var lunIndex =0;
    var imgs = $("div.tup img");
    var styleSp = $("div.yuandian span");
    function imgLunbuo(){
        lunIndex++;
        if(lunIndex == imgs.length){
            lunIndex=0;
        }
        
        styleSp.eq(lunIndex).addClass("lunbuostyle2").siblings().removeClass("lunbuostyle2");
        
        imgs.hide(0); 
        imgs.eq(lunIndex).fadeIn(500);
    }
    var interval = setInterval(imgLunbuo,3000);
    $(".tup img").hover(function(){
        clearInterval(interval);
    },function(){
         interval = setInterval(imgLunbuo,3000);
    });
    // 用来执行 大类下面没有任何内容的div 隐藏
   $(".commodityF1").each(function(index){
	   var $li = $(this).find("li p").text();
	   if($li == "" || $li == null){
		   $(this).hide();
	   }
   });
    

    

});

// 执行查询商品导航的信息
function li(data){
	// 通过大类id 查询商品类别
	$.post("broadTypeIdGetAllCommodityType.do",{typeId:data},function(commodityType){
		var types = eval(commodityType);
		$("div#nvg_top1").empty();
		
		$(types).each(function(i){
			
			
			// 通过商品类别id查询商品
			$.post("getAllCommodityBrandName.do",{typeId:types[i].id},function(comid){
				$("#nvg_top1").append("<dl><dt><a href='#'>"+types[i].cltName+"</a></dt>");
				var commodity = eval(comid);
				$(commodity).each(function(s){
					$("#nvg_top1").append("<dd><a href='#'>"+commodity[s].commodityBrandName+"</a></dd></dl>");
				});
				//$("#nvg_top1").append("</dl>");
				
			});
			
		});
		
	});
	
}

    
