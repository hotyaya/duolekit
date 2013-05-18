<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    	<meta http-equiv="X-UA-Compatible" content="IE=8">
    	<title>编辑页面</title>   	 
    	<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
    	<base href="<%=basePath%>">    		
		</head>
		<body>
		
			<form action="users/updatenote.action" method="post">
				<input type="hidden" id="id" name="id" value="<%=request.getParameter("id")%>">
				<textarea id="editor1" name="article"   class="ckeditor">
				<%=request.getAttribute("article")%>        
				</textarea>
				<!--  
				<input type="submit" value="保存">
				-->	
			</form>				
		</body>
		<script type="text/javascript">
		 	var oCKeditor;
   	  		(function () {
   	  		 	oCKeditor = CKEDITOR.replace('editor1');
   	  			oCKeditor.on('instanceReady', function (event) {
   	 			var editor = event.editor;
   	 			
   	  			setTimeout(function () {
   	  			// Delay bit more if editor is still not ready.
   	  				if (!editor.element) {
	   	  				setTimeout(arguments.callee, 100);
	   	  				return;
   	  				}
   	  				event.removeListener('instanceReady', this.callee);
   	  				if (editor.name == 'editor1') {
	   	  				var command = editor.getCommand('maximize');
	   	  				command.exec();
   	  				}
   	  			}, 0);
   	  			
   	  			}, null, null, 9999);
   	  		})(); 	  	 
          </script>
  		
</html>




