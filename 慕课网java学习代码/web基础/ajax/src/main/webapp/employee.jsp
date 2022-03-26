<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.alibaba.fastjson.JSON" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="text-align:center;">

<button id="emps" style="width:400px">员工列表</button>
<button id="jobs" style="width:400px">职位列表</button>
<button id="departments" style="width:400px">部门列表</button>
<div style="floag:400px" id="content1">

</div>

</div>

<script type="text/javascript" src="js/jquery-3.6.0.js"></script>
<script type="text/javascript">
	var xmlHttp;
	if(window.XMLHttpRequest){
		xmlHttp = new XMLHttpRequest();
	}else{
		xmlHttp = new ActiveXObject("MicroSoft.XMLHTTP");
	}
	
	
	
	xmlHttp.open("GET","/ajax/employee","true");
	
	xmlHttp.send();

	xmlHttp.onreadystatechange = function(){

		
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
			var text = xmlHttp.responseText;
			var json = JSON.parse(text);
			$("#emps").click(function(){
				var html = "";
				for(var i = 0;i < json.length;i ++)
					html += "<h2>" + json[i].name + "</h2>";
				$("#content1").html(html);
			})
			$("#jobs").click(function(){
				// 字符串数组去重代码
				var a = [];
				for(var i = 0;i < json.length;i ++) a.push(json[i].job);
				var result = [...new Set(a)];
				console.log(result[0]);
				
				var html = "";
				for(var i = 0;i < result.length;i ++)
					html += "<h2>" + result[i] + "</h2>";
				$("#content1").html(html);	
			})
			$("#departments").click(function(){
				var html = "";
				for(var i = 0;i < json.length;i ++)
					html += "<h2>" + json[i].department + "</h2>";
				$("#content1").html(html);
			})
		}
	}
</script>
</body>
</html>