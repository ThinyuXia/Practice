 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	 <h1>${requestScope.score}</h1>
	 <c:if test="${requestScope.score >= 60}">
	 	<h1 style="color:green">恭喜，通过测试</h1>
	 </c:if>
	 <c:if test="${requestScope.score < 60 }">
	 	<h1 style="color:red">对不起，再接再厉</h1>
	 </c:if>
	 
	 <!-- choose内部的when otherwise想当于{if elseif} 和 {else} -->
	 <h1>${requestScope.grade}</h1>
	 <c:choose>
	 	<c:when test="${grade == 'A+'}">
	 		<h2>你很优秀</h2>
	 	</c:when>
	 	<c:when test="${grade == 'B'}">
	 		<h2>不错呦</h2>
	 	</c:when>
	 	<c:when test="${grade == 'A+'}">
	 		<h2>需要提高哟</h2>
	 	</c:when>
	 	<c:when test="${grade == 'D'}">
	 		<h2>需要努力啦</h2>
	 	</c:when>
	 	<c:otherwise>
	 		<h2>一切随缘吧</h2>
	 	</c:otherwise>
	 </c:choose>
	 
	 <%
	 request.setAttribute("num",11);
	 request.setAttribute("day","222");
	 %>
	 <c:if test="${requestScope.num >= 0 && requestScope.num <= 10}">
	 	<h2>1-10之间的整数</h2>
	 </c:if>
	 <c:if test="${requestScope.num > 10 && requestScope.num <= 20}">
	 	<h2>11-20之间的整数</h2>
	 </c:if>
	 <c:choose>
	 	<c:when test="${requestScope.day=='MONDAY'}">
	 		<h2>星期一</h2>
	 	</c:when>
	 	<c:when test="${requestScope.day=='TUESDAY'}">
	 		<h2>星期二</h2>
	 	</c:when>
	 	<c:when test="${requestScope.day=='WEDNESDAY'}">
	 		<h2>星期三</h2>
	 	</c:when>
	 	<c:when test="${requestScope.day=='TURSDAY'}">
	 		<h2>星期四</h2>
	 	</c:when>
	 	<c:when test="${requestScope.day=='FRIDAY'}">
	 		<h2>星期五</h2>
	 	</c:when>
	 	<c:when test="${requestScope.day=='SATURDAY'}">
	 		<h2>星期六</h2>
	 	</c:when>
	 	<c:when test="${requestScope.day=='SUNDAY'}">
	 		<h2>星期日</h2>
	 	</c:when>
	 	<c:otherwise>
	 		<h2 style="color:red">内容不对呦</h2>
	 	</c:otherwise>
	 </c:choose>
	 
	 <c:forEach items="${requestScope.companies}" var="company" varStatus="idx">
	 	<h2>${idx.index + 1} ${company.cname} - ${company.curl}</h2>
	 </c:forEach>
</body>
</html>