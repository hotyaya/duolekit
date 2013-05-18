<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <style type="text/css">
		html body{
			font-size: 12px;
			font-family: Georgia,serif;
			background: url("./images/bg.jpg") center center repeat;
			background-attachment: fixed;
			padding: 0;
			margin: 0;
		}
	</style>
  </head>
  
  <body>
     <h1><font color="red"><center>Group Note</center></font></h1> 
     <table border="1">
     	<tr>
     		<td>
     			<h2><font color="black">标题：<%=request.getAttribute("title")%></font></h2>
     		</td>
     	</tr>
     	<tr>
     		<td>
     			<h2><font color="black">正文：<%=request.getAttribute("article")%></font></h2>
     		</td>   		
     	</tr>
     </table>        
  </body>
</html>
