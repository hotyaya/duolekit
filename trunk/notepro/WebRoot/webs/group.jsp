<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
    <title>乐享群组</title>  
    <link rel="Shortcut Icon" href="../images/book.ico">    
	<style type="text/css">
		html body{
			font-size: 12px;
			font-family: Georgia,serif;
			background: url("./images/bg.jpg") center center repeat;
			background-attachment: fixed;
			padding: 0;
			margin: 0;
		}
		.sName{
			font-family: Gill Sans MT, Gill Sans;
			font-size: 25px;
			color: #D1D1D1;
		}
		.sDis{
				margin-left: 30px;
				padding-left: 50px;
				font-family: 'Trebuchet MS, Myriad Pro, Arial, sans-serif';
				font-size: 14px;
				color: #A9A7A7;
			}
		#container{
			margin: 0px auto;
			width: 960px;
		}
		#header{
			margin-top: 15px;
			margin-left: 35px;
			width: 910px;
			height: 100px;
		}
		
	</style> 
	<script type="text/javascript">
		function check(id)
		{
			/*
			if(confirm("你确定想定此票?"))
			{
				return true;
			}
			return false;
			*/
		}
	</script> 
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    </head> 
    <body>
    	<div id="container">
  		<div id="header">
				<div class="sName"><s:property value="#request.group.getName()"/></div>
				<div class="sDis"><s:property value="#request.group.getDescription()"/></div>			
  		</div>
  		<div id="mainContent">
    		<div id="sidebar">
    	       <h1><font color="red"><center>本群组文章表列</center></font></h1>
   
			   <table border="1" width="80%" align="center">
			   
			   	<tr>
			   		<td>标题
			   		</td>
			   		
			   		<td>写作日期
			   		</td>
			   		
			   		<td>作者                                       
			   		</td>
			   		
			   		<td>查看
			   		</td>
			   		
			   	
			   	</tr>
			   	
			   	<s:iterator value="#request.listnotes" id="us">
			   		<tr>
			   			<td><s:property value="#us.getTitle()"/>
			   			</td>
			   			
			   			<td><s:property value="#us.getDisplaytime()"/>
			   			</td>
			   			
			   			<td><s:property value="#us.getUser().getName()"/>
			   			</td>			   						   			
			   			
			   			<td><input type= "button " onclick= "window.location= 'group/checknote.action?id=<s:property value="#us.getId()"/>'; " value="查看笔记">
			   			</td>
			   					   		
			   		</tr>
			   	</s:iterator>
			   	
			   
			   </table>
    		</div>
    		<div id="content"></div>

		</div>
    </body>
</html>
