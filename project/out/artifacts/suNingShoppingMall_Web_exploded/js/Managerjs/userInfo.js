
function addAndEdit(isaddORedit,id){
				if(id == "null"){ // 如果是null的话表示添加
					$(":hidden.hid_id").val(id);
					$(":text[name='userAccount']").removeAttr("disabled")
					$("div.addedit_top span").text(isaddORedit)
					$(":text[name='userAccount']").val("");
					$(":text[name='userPwd']").val("");
					$(":text[name='accountBalance']").val("");
					$(":text[name='userName']").val("");
					$(":radio[name='userGender'][value='男']").prop("checked",true)
					$(":text[name='userIdentityMark']").val("");
					$(":text[name='userEmail']").val("");
					$(":text[name='userPhone']").val("");
					$(".laydate-icon").val("");//日期的
					$(":hidden[name='isEdit']" ).val(1);
					$("#commodity_addAndEdit").show();
				}
				else if(id == 1){ // 如果是1的话表示修改
					$("div.addedit_top span").text(isaddORedit)
					$(":text[name='userAccount']").attr("disabled","disabled");
					$(".addedit_body .err_content").text("");
					$("#sub_no").removeAttr("disabled");
					$(":checkbox.che").each(function(){ 
						if($(this).is(":checked")){
							var account = $(this).val();
							$.post("accountGetUserInfo.do",{userAccount:account},function(data){
								
								var userInfo = eval("("+data+")");
								$(":text[name='userAccount']").val(userInfo.user.userAccount);
								$(":hidden[name='userAccount']").val(userInfo.user.userAccount);
								$(":text[name='userPwd']").val(userInfo.user.userPwd);
								$(":text[name='accountBalance']").val(userInfo.user.accountBalance);
								$(":text[name='userName']").val(userInfo.userName);
								$(":radio[name='userGender'][value='"+userInfo.userGender+"']").prop("checked",true)
								$(":text[name='userIdentityMark']").val(userInfo.userIdentityMark);
								$(":text[name='userEmail']").val(userInfo.userEmail);
								$(":text[name='userPhone']").val(userInfo.userPhone);
								$(".laydate-icon").val(userInfo.userBirthDate);//日期的
								$(":hidden[name='isEdit']" ).val(2);
								$("#commodity_addAndEdit").show();
								
							});
							
						}
					});
				}
				else if(id == 2){
					$(":checkbox.che").each(function(){
						if($(this).is(":checked")){
							var account = $(this).val();
							/* 删除商品和商品信息  先删除商品信息 后再删除商品 */
							$.post("deleteUserAndUserInfo.do",{userAccount:account},function(result){
								/* 用ajax完成删除商品和删除商品信息 完成后执行的函数 先让他刷新一下 */
								if(result>0){
									location.href="getAllUserInfo.do";
									alert("删除成功!");
								}
								
							});
						}
						
					});
					
				}
			}