/**
	隐藏显示错误信息
	@param offOn true-隐藏 false-显示
	@param input 表单域选择器
	@param errSelector 错误信息选择器
	@param message 错误信息
 */

function switchValid(off,input,errSelector,message){
	if(off == false){
		$(errSelector).text(message);
		$(input).addClass("error_input"); //文本置红
		$(errSelector).addClass("error_message");
	}else{
		$(errSelector).text("");
		$(input).removeClass("error_input"); 
		$(errSelector).removeClass("error_message");
	}
}


/**
	检查输入项是否为空
	@param input 表单域选择器
	@param errSelector 错误信息选择器
	@param true-校验成功 false-校验失败
 */
 
 function checkEmpty(input,errSelector){
	var val = $(input).val();
	if($.trim(val) == ""){ //去除字符串两边空格
		switchValid(false,input,errSelector,"请输入内容");
		return false;
	}else{
		switchValid(true ,input,errSelector,"");
		return true;
	}
}

/**
	检查类别是否正确
	@param input 表单域选择器
	@param errSelector 错误信息选择器
	@param true-校验成功 false-校验失败
 */
function checkCategory(input,errSelector){
	var val = $(input).val();
	if(val == -1){
		 switchValid(false,input,errSelector,"请选择油画类型");
		 false;
	}else{
		switchValid(true,input,errSelector,"");
		return true;
	}
}

/**
	 检查价格是否是正整数
	 @param input 表单域选择器
	 @param errSelector 错误信息选择器
	 @param true-校验成功 false-校验失败
 */
 function checkPrice(input,errSelector){
	var val = $(input).val();
	var regex = /^[1-9][0-9]*$/;
	if(regex.test(val) == false){
		switchValid(false,input,errSelector,"无效的价格");
		false;
	}else{
		switchValid(true,input,errSelector,"");
		return true;
	}
}

/**
	 检查上传的文件是否是图片 
	 @param input 表单域选择器
	 @param errSelector 错误信息选择器
	 @param true-校验成功 false-校验失败
 */
 function checkFile(input,errSelector){
	if(checkEmpty(input,errSelector) == false){
		return false;
	}
	//.val() 方法获取上传的文件名
	var val = $(input).val().toLowerCase(); //解决文件扩展名不规范的问题
	if(val.length < 4){
		switchValid(false,input,errSelector,"请选择有效的图片");
		return false;
	}
	
	suffix = val.substring(val.length - 3);
	if(suffix == "jpg" || suffix == "png" || suffix == "gif" || suffix == "peg") {
		switchValid(true,input,errSelector);
		return true; 
	}else{
		switchValid(false,input,errSelector,"请选择有效的图片");
		return false; 
	}
	
	
}

