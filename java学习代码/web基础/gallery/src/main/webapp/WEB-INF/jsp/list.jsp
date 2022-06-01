<%@page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>油画列表</title>
<link rel="stylesheet" type="text/css" href="css\list.css">
<script src="js/jquery-3.4.1.min.js" type="text/javascript"></script>
<script src="js/sweetalert2.js" type="text/javascript"></script>
<script>
	//对话框显示预览图	
	function showPreview(previewObject){
		//使用jquery对超链接标签自定义属性进行提取
		var preview = $(previewObject).attr("data-preview"); 
		var pname = $(previewObject).attr("data-pname"); 
		Swal.fire({
			title:pname,
			html:"<image src='" + preview + "'style='width:361px;height:240px'>",
			showCloseButton:true,
			showConfirmButton:true
		});
	}
	
	//删除数据
	function del(delObj){
		var id = $(delObj).attr("data-id");
		var pname = $(delObj).attr("data-pname");
		var preview = $(delObj).attr("data-preview");
		Swal.fire({
			title : "确定要删除[" + pname + "]油画吗?",
			html : "<img src='" + preview + "' style='width:361px;height:240px'>",
			showCancelButton: true,
			confirmButtonText: "是",
			cancelButtonText: "否"
		}).then(function(result){
			if(result.value==true){
				//alert("你点了'是'按钮")
				$.ajax({
					url: "/management?method=delete&id=" + id,
					type : "get",
					dataType:"json",
					success : function(json){
						if(json.result=="ok"){
							window.location.reload();
						}else{
							Swal.fire({
								title : json.result
							})
						}
						//console.log(json);
					}
				})
			}
		})
	}
	
</script>
</head>
<body>
	<div class="container">
		<fieldset>
			<legend>油画列表</legend>
			<div style="height: 40px">
				<a href="/management?method=show_create" class="btn-button">新增</a>
			</div>
			<!-- 油画列表 -->
			<table cellspacing="0px">
				<thead>
					<tr style="width: 150px;">
						<th style="width: 100px">分类</th>
						<th style="width: 150px;">名称</th>
						<th style="width: 100px;">价格</th>
						<th style="width: 400px">描述</th>
						<th style="width: 100px">操作</th>
					</tr>
				</thead>
					<c:forEach items="${pageModel.pageData}" var="painting">
					<tr>
						<td>${painting.category==1 ? "现实主义" : "抽象主义"}</td>
						<td>${painting.pname}</td>
						<td><fmt:formatNumber value="${painting.price}" pattern="¥0.00"></fmt:formatNumber></td>
						<td>${painting.description }</td>
						<td>
							<!--  html5规范要求自定义属性前加 data- 前缀 -->
							<a class="oplink" data-preview="${painting.preview}" data-pname="${painting.pname}" href="javascript:void(0)" onclick="showPreview(this)">预览</a>
							<a class="oplink" href="management?method=show_update&id=${painting.id}">修改</a>
							<a class="oplink" href="javascript:void(0)" data-id="${painting.id }" data-pname="${painting.pname }" data-preview="${painting.preview }" onclick="del(this)">删除</a>
						</td>
					</tr>
					</c:forEach>
					
			</table>
			<!-- 分页组件 -->
			<ul class="page">
				<li><a href="/management?p=1&method=list">首页</a></li>
				<li><a href="/management?p=${pageModel.hasPreviousPage? pageModel.page - 1 : pageModel.page}&method=list">上页</a></li>
				<c:forEach var="pnum" begin="1" end="${pageModel.totalPages}" step="1">
					<a href="/management?p=${pnum}&method=list" ><li ${pnum==pageModel.page ? "class='active'":""}>${pnum}</li> </a>
				</c:forEach>
				<li><a href="/management?p=${pageModel.hasNextPage? pageModel.page + 1 : pageModel.page}&method=list">下页</a></li>
				<li><a href="/management?p=${pageModel.totalPages}&method=list">尾页</a></li>
			</ul>
		</fieldset>
	</div>

</body>
</html>
