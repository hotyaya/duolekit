<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
	body{TEXT-ALIGN: center;background-image:url(images/weather.jpg);}
	#center{ 
		MARGIN-RIGHT: auto;
		MARGIN-LEFT: auto; 
		height:200px;
		width:600px;
		vertical-align:middle;
		line-height:200px;
	}
</style>
  </head>
  
  <body>
  
	  	<div id="center">
		  	<% 
			    String info = (String) request.getAttribute("info"); 
		  	
			    if(info.equals("#")){
		    %>
		    <h1>您所在的城市暂不被支持天气预报服务</h1>
		    <%
			    } else {
			    String[] infos = info.split("#") ;
		    %>
	    	<table border="1">
	    		<tr>
	    			<td colspan="3">
	     				<strong><font color="red">72小时天气预报</font></strong>
	     			</td>
	    		</tr>
	    		<tr align="center">
				     <td>
				     	<table>
				     		<tr align="center">
				     		<!-- 日期 -->
				     			<td>
				     				<font size="-1"><%=infos[6] %></font>
				     			</td>
				     		</tr>
				     		<tr align="center">
				     		<!-- 天气图标 -->
					     		<td>
					     			<img src="./images/weather-icon/a_<%=infos[8]%>" />
					     			<img src="./images/weather-icon/a_<%=infos[9]%>" />
					     		</td>
				     		</tr>
				     		<tr align="center">
				     			<!-- 天气细则 -->
					     		<td>
					     			<font size="-1"><%=infos[5] %><br><%=infos[7] %><br></font>
					     		</td>
				     		</tr>
				     	</table>
				     </td>
				     			     
				     <td>
				     	<table align="center">
				     		<tr align="center">
				     			<td>
				     			<!-- 第二天的时间 -->
				     				<font size="-1"><%=infos[13] %></font>
				     			</td>
				    	 	</tr>			     
				     		<tr align="center">
				     			<td>
				     				<!-- 第二天的天气图标 -->
				     				<img src="./images/weather-icon/a_<%=infos[15]%>">
				     				<img src="./images/weather-icon/a_<%=infos[16]%>">
								</td>
				     		</tr>		     
				     		<tr align="center">
				     			<td>
				     				<!-- 第二天的天气细则 -->
				     				<font size="-1"><%=infos[12] %><br><%=infos[14] %><br></font>
				     			</td>
				     		</tr>
				     	</table>
				     </td>
				     			     
				     <td>
				     	<table align="center">
				     		<tr align="center">
				     			<td>
				     				<!-- 第三天的天气时间 -->
				    				<font size="-1"><%=infos[18] %></font>
								</td>
				     		</tr>			     
				     		<tr align="center">
				     			<td>
				     				<!-- 第三天的天气图标 -->
				     				<img src="./images/weather-icon/a_<%=infos[20]%>" />
				     				<img src="./images/weather-icon/a_<%=infos[21]%>" />
				     			</td>
				     		</tr>
				     		<tr align="center">
				     			<td>
				     				<!-- 第三天的天气细则 -->
				     				<font size="-1"><%=infos[17] %><br><%=infos[19] %><br></font>
				     			</td>
				     		</tr>
				     	</table>
				     </td>
		    	</tr>
		    	<tr>
				 	<td colspan="3">
				 		<!-- 居家指数 -->
				 		<strong><font color="blue">温馨提醒</font></strong>
				 	</td>
				 </tr>
				 <tr>
				 	<td colspan="3">		 
				 		<table>
				 			<tr>
				 				<td>
				 					<img src="./images/weather-icon/sun.gif" />
				 				</td>
				 				<td>
				 					<font size="-1"><%=infos[10] %></font>
				 				</td>
				 			</tr>
				 		</table>					 
				 	</td>
				 </tr>
				 <tr>
					 <td colspan="3">			 				  
					  	<%				  		 
					  		  String str;
							  String s1 = "穿衣指数";
							  String s1Content = "" ;
							  String s2 = "过敏指数" ;
							  String s2Content = "" ;
							  String s3 = "运动指数" ;
							  String s3Content = "" ;
							  String s4 = "舒适度指数" ;
							  String s4Content = "" ;
							  String s5 = "晾晒指数" ;
							  String s5Content = "" ;
							  String s6 = "旅行指数" ;
							  String s6Content = "" ;
							  String s7 = "路况指数" ;
							  String s7Content = "" ;						  
							  //这里开始进行具体详细测试
							  str = infos[11] ;
							  //穿衣指数
							  //s1 = str.substring(str.indexOf("穿衣指数："),str.indexOf("穿衣指数：")+4) ;
							  s1Content = str.substring(str.indexOf("穿衣指数：")+5,str.indexOf("过敏指数：")) ;
							  //感冒指数
							  //s2 = str.substring(str.indexOf("感冒指数："),str.indexOf("感冒指数：")+4) ;
							  s2Content = str.substring(str.indexOf("过敏指数：")+5,str.indexOf("运动指数：")) ;
							  
							  //运动指数
							  //s3 = str.substring(str.indexOf("运动指数："),str.indexOf("运动指数：")+4) ;
							  s3Content = str.substring(str.indexOf("运动指数：")+5,str.indexOf("洗车指数：")) ;						  
							  //舒适度指数
							  //s4 = str.substring(str.indexOf("晾晒指数："),str.indexOf("晾晒指数：")+4) ;
							  s4Content = str.substring(str.indexOf("舒适度指数：")+6,str.indexOf("空气污染指数：")) ;
							  //晾晒指数
							  //s5 = str.substring(str.indexOf("晾晒指数："),str.indexOf("晾晒指数：")+4) ;
							  s5Content = str.substring(str.indexOf("晾晒指数：")+5,str.indexOf("旅游指数：")) ;
							  //旅行指数
							  //s6 = str.substring(str.indexOf("旅游指数："),str.indexOf("旅游指数：")+4) ;
							  s6Content = str.substring(str.indexOf("旅游指数：")+5,str.indexOf("路况指数")) ;
							//路况指数
							  //s7 = str.substring(str.indexOf("洗车指数："),str.indexOf("洗车指数：")+4) ;
							  s7Content = str.substring(str.indexOf("路况指数：")+5,str.indexOf("舒适度指数：")) ;
					  	%>				  
					    <table>
					 		<tr>
					 			<td width="50">
					 				<img src="./images/weather-icon/zhishu_01.gif" />
					 			</td>
					 			<td>
					 				<strong><font size="-1"><%=s1 %>&nbsp;</font></strong>
					 			</td>
					 			<td>
					 				<font size="-1"><%=s1Content%></font>
					 			</td>
					 		</tr>						 
					 		<tr>
					 			<td width="50">
					 				<img src="./images/weather-icon/zhishu_20.gif" />
					 			</td>
					 			<td>
					 				<strong><font size="-1"><%=s2 %>&nbsp;</font></strong>
					 			</td>
					 			<td>
					 				<font size="-1"><%=s2Content %></font>
					 			</td>
					 		</tr>			 
							<tr>
					 			<td width="50">
					 				<img src="./images/weather-icon/zhishu_03.gif" />
					 			</td>
					 			<td>
					 				<strong><font size="-1"><%=s3 %>&nbsp;</font></strong>
					 			</td>
					 			<td>
					 				<font size="-1"><%=s3Content %></font>
					 			</td>
					 		</tr>				 
					 		<tr>
					 			<td width="50">
					 				<img src="./images/weather-icon/zhishu_22.gif" />
					 			</td>
					 			<td>
					 				<strong><font size="-1"><%=s4 %>&nbsp;</font></strong>
					 			</td>
					 			<td>
					 				<font size="-1"><%=s4Content %></font>
					 			</td>
					 		</tr>
					 		<tr>
					 			<td width="50">
					 				<img src="./images/weather-icon/zhishu_07.gif" />
					 			</td>
					 			<td>
					 				<strong><font size="-1"><%=s5 %>&nbsp;</font></strong>
					 			</td>
					 			<td>
									<font size="-1"><%=s5Content %></font>
					 			</td>
					 		</tr> 
							 <tr>
								 <td width="50">
								 	<img src="./images/weather-icon/zhishu_31.gif" />
								 </td>
								 <td>
								 	<strong><font size="-1"><%=s6 %>&nbsp;</font></strong>
								 </td>
								 <td>
								 	<font size="-1"><%=s6Content %></font>
								 </td>
							 </tr>				 
							 <tr>
								 <td width="50">
								 	<img src="./images/weather-icon/zhishu_11.gif" />
								 </td>
								 <td>
								 	<strong><font size="-1"><%=s7 %>&nbsp;</font></strong>
								 </td>
								 <td>
								 	<font size="-1"><%=s7Content %></font>
								 </td>
							 </tr>				 				 
					 </table>
				</td>
			</tr>
		</table>
		    <%
		    	}
			%>
	  </div>
  </body>
</html>
