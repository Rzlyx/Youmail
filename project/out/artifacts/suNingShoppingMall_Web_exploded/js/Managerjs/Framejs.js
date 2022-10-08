
/* 点击隐藏div */
	function cancel(){
		$("#add_and_edit").hide();
		$(" #commodity_addAndEdit").hide();
		$(".addedit_body .err_content").text("");
		$("#sub_no").removeAttr("disabled");
	};
	
	/*  当点击翻页的话会出发该函数  并进行提交表单 */
	function pageSet(pageNum){
		$(":hidden[name='pageNum']").val(pageNum);
		
		$("#myForm").trigger("submit");
	};
	
	$(function(){
		/*  设置复选框 只让他选中一个 */
		$(":checkbox.che").click(function(){
			
			if($(this).is(":checked")){
				$(":checkbox.che").attr("checked",false);
				$(this).attr("checked",true);
				
			}
		});
	});