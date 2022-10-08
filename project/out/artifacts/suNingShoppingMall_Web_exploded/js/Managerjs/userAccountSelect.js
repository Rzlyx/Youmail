
	$(function(){
		$("#userAccount_select").blur(function(){
			selectAccount($(this).val());
		});
	});
	
	

	function selectAccount(account){
		
		$.post("SelectUserCount.do",{userAccount:account},function(data){
			var account = data;
			if(account == 1){
				$(".addedit_body .err_content").text("该账户已存在!");
				$("#sub_no").attr({"disabled":"false"});  // 让提交表单禁用
			}else{
				$(".addedit_body .err_content").text("");
				$("#sub_no").removeAttr("disabled");
				
			}
			
		});
		
		
	}
