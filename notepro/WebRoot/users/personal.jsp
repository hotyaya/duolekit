<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>笔记之家</title>
    <base href="<%=basePath%>">
    <link rel="Shortcut Icon" href="images/book.ico">
	<link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css" />
	<!-- Begin system js -->
 	<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="ext/ext-all.js"></script>
    <script type="text/javascript" src="ext/source/locale/ext-lang-zh_CN.js"></script>
    <!-- End system js -->  
    <!-- Begin user defined js -->
    <script type="text/javascript" src="js/groupgird.js"></script>
    <script type="text/javascript" src="js/west.js"></script>    
    <script type="text/javascript" src="js/index.js"></script>
    <!-- End user defined js -->
 
	<style type="text/css">
		html, body {
	        font:normal 12px verdana;
	        margin:0;
	        padding:0;
	        border:0 none;
	        overflow:hidden;
	        height:100%;
	    }
		.x-panel-body p {
		    margin:5px;
		}
	    .x-column-layout-ct .x-panel {
	        margin-bottom:5px;
	    }
	    .x-column-layout-ct .x-panel-dd-spacer {
	        margin-bottom:5px;
	    }
	    .searchgroup {
	        background-image:url(images/tbargroup.png) !important;
	    }
	    .deltegroup {
	        background-image:url(images/deletegroup.png) !important;
	    }
	    .settings {
	        background-image:url(images/folder_wrench.png) !important;
	    }
	    .notes {
	        background-image:url(images/note-icon.png) !important;
	    }
	    .tools {
	        background-image:url(images/tool-icon.png) !important;
	    }
	     .calendar {
	        background-image:url(images/calendar.png) !important;
	    }
	     .group {
	        background-image:url(images/group.png) !important;
	    }
		.folder .x-tree-node-icon {
			background:transparent url(ext/resources/images/default/tree/folder.gif);
		}
		.x-tree-node-expanded .x-tree-node-icon {
			background:transparent url(ext/resources/images/default/tree/folder-open.gif);
		}
	
		#header {
	    background: #7F99BE url(images/layout-browser-hd-bg.gif) repeat-x center;
		}
		#header h1 {
		    font-size: 16px;
		    font-family:'Microsoft YaHei', 微软雅黑, 'Microsoft JhengHei', 宋体;
		    color: #fff;
		    font-weight: normal;
		    padding: 5px 10px;
		}
    </style>             
	<script type="text/javascript">		
	</script>
</head>
<body>
	<div id="header"><h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;NoteBook Project</h1></div>
  	<div id="main"></div>
</body>
</html>
