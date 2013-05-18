<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  </head>
  
  <body>
     <h1><font color="red"><center>Your Note</center></font></h1>   
     <h2><font color="black">标题：<%=request.getAttribute("title")%></font></h2><br>
     <h2><font color="black">正文：<%=request.getAttribute("article")%></font></h2>
  </body>
</html>
