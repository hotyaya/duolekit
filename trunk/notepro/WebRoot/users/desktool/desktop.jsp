<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC “-//W3C//DTD XHTML 1.0 Transitional//EN” “http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd”>
<html xmlns=”http://www.w3.org/1999/xhtml”>
<html>
  <head>
    <base href="<%=basePath%>">
    
    
	<!--
	<script type="text/javascript" src="users/desktool/calendar/calendar.js"></script>
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	 <script type="text/javascript">
	 var w,h;
	 window.onresize = window.onload = function(){		    
		    if(!!(window.attachEvent && !window.opera))
		    {
			    h = document.documentElement.clientHeight;
			    w = document.documentElement.clientWidth;
		    }else{
			    h =	window.innerHeight;
			    w = window.innerWidth;
		    }
		
		    var bgImg = document.getElementById('bg').getElementsByTagName('img')[0];
		    bgImg.width = w;
		    bgImg.height= h;
	  }
	  /*
	  window.onresize = function() {
		    var bgImg = document.getElementById('bg').getElementsByTagName('img')[0];
		    bgImg.width = w;
		    bgImg.height= h;
	  }
	  */
  	 </script>
  	 <style type="text/css">
  	 	*{margin:0;padding:0;}
		 #bg{
			    position:absolute;
			    top:0px;
			    left:0px;
			    z-index:-999;
		    }  
	</style> 	 
  </head>
  <body>
  	 <div id="bg"><img  src="./images/desktop.jpg"></div>	
     <!--  
     <div id="Layer1" style="position:absolute; width:100%; height:100%; z-index:-1">
     <img src="./images/desktop.jpg" height="100%" width="100%"/>
     </div>
     -->
  </body>
</html>
