<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE html PUBLIC “-//W3C//DTD XHTML 1.0 Transitional//EN” “http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd”>
<html xmlns=”http://www.w3.org/1999/xhtml”>
<html>
	<head>
	
	<title>笔记之家--登陆</title>
	<link rel="Shortcut Icon" href="../images/book.ico">
	<link rel="stylesheet" href="./ext/resources/css/ext-all.css" type="text/css"></link>
	<script type="text/javascript" src="./ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="./ext/ext-all.js"></script>
	<script type="text/javascript" src="./js/login.js"></script>
	<script type="text/javascript" src="./js/regform.js"></script>
	<script language="javaScript" >
		window.onresize = window.onload = function(){
			    var w,h
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
	</script>
	<style type="text/css">
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
	<div id="Layer1" style="position:absolute;top:0px;left:0px;width:100%; height:100%; z-index:-1">    
	<img src="./images/desktop.jpg" height="100%" width="100%"/> 
	</div>
	-->
	</body>
</html>
