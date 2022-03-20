<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <table>
    	<tr>
    		<th>year</th>
    		<th>salary</th>
    	</tr>
    	<%
    		for(int i = 0;i <= 50;i ++){
    			out.println("<tr>");
    			out.println("<td>" + i + "</td>");
    			int sal = 0;
    			if(i <= 5){
    				sal = 1500 + i * 150;
    			}else if(i <= 10) sal = 2250 + 300 * (i - 5);
    			else sal = 3750 + (i - 10) * 375;
    			out.println("<td>" + sal +"</td>");
    			out.println("</tr>");
    		}
    	%>
   
    </table>
</body>
</html>