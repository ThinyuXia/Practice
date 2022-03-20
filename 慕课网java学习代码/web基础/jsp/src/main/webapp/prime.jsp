<%@page import="java.util.*" contentType="text/html;charset=utf-8"%>
<%!
	boolean isprime(int n){
		boolean flag = true;
		for(int j = 2;j < n;j ++){
			if(n % j == 0){
				flag = false;
				break;
			}
		}
		return flag;
}
%>
<%
	List<Integer> primes = new ArrayList<>();
	for(int i = 2;i <= 1000;i ++){
		if(isprime(i))
			//out.println("<h1>" + i + "</h1>");
			primes.add(i);
	}
%> 
<%
	for(int p : primes){
		//out.println("<h1>" + p + "是质数</h1>");

%>
	<h1 style="color:red"><%=p %>是质数</h1>
<%
	}
%>