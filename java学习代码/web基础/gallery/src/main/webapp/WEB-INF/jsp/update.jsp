<%@page contentType="text/html;charset=utf-8"%>
<!-- 修改油画页面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>作品更新</title>
<link rel="stylesheet" type="text/css" href="css\create.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/validation.js"></script>
<script type="text/javascript">
	<!-- 提交前表单校验 -->
	function checkSubmit(){
		var result = true;
		var r1 = checkEmpty("#pname","#errPname");
		var r2 = checkCategory('#category', '#errCategory');
		var r3 = checkPrice('#price', '#errPrice');
		// var r4 = checkFile('#painting', '#errPainting');
		var r4 = null;
		if($("#isPreviewModified").val() == "1"){
			r4 = checkFile('#painting', '#errPainting');
		}else{
			r4 = true;
		}
		var r5 = checkEmpty('#description', '#errDescription');
		if (r1 && r2 && r3 && r4 && r5){
			return true;
		}else{
			return false;
		}
	}
	
	$(function(){
		$("#category").val(${painting.category}) //根据传入的值自动选中下拉框
		
	})
	
	function selectPreview(){
		checkFile("#painting","#errPainting");
		$("#preview").hide(); //如果用户上传图片则隐藏预览图
		$("#isPreviewModified").val(1);
	}
</script>
</head>
<body>
	<div class="container">
		<fieldset>
			<legend>作品名称</legend>
			<form action="/management?method=update" method="post"
				autocomplete="off" enctype="multipart/form-data"
				onsubmit = "return checkSubmit()">
				<ul class="ulform">
					<li>
						<span>油画名称</span>
						<span id="errPname"></span>
						<input id="pname" name="pname" onblur="checkEmpty('#pname','#errPname')" value="${painting.pname}"/>
					</li>
					<li>
						<span>油画类型</span>
						<span id="errCategory"></span>
						<select id="category" name="category" onchange="checkCategory('#category','#errCategory')">
							<option value="-1">请选择油画类型</option>
							<option value="1">现实主义</option>
							<option value="2">抽象主义</option>
						</select>
					</li>
					<li>
						<span>油画价格</span>
						<span id="errPrice"></span>
						<input id="price" name="price" onblur="checkPrice('#price','#errPrice')" value="${painting.price}" />
					</li>
					<li>
						<span>作品预览</span><span id="errPainting"></span><br/>
						<input type="hidden" id="isPreviewModified" name="isPreviewModified" value="0">
						<img id="preview" src="${painting.preview}" style="width:361px;height:240px"/><br/>
						<input id="painting" name="painting" type="file" style="padding-left:0px;" accept="image/*" onchange="selectPreview()"/>
					</li>

					<li>
						<span>详细描述</span>
						<span id="errDescription"></span>
						<textarea
							id="description" name="description"
							onblur="checkEmpty('#description','#errDescription')"
							>${painting.description }</textarea>
					</li>
					<li style="text-align: center;">
						<!-- 表单隐藏域 -->
						<input type="hidden" id="id" name="id" value="${painting.id}">
						<button type="submit" class="btn-button">提交表单</button>
					</li>
				</ul>
			</form>
		</fieldset>
	</div>

</body>
</html>
