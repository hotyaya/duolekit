<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Admin Console</title>
	<meta name="menu" content="notification" />    
</head>

<body> <!--  onload= "timer()" -->

<h1>Send Notifications 2</h1>

<%--<div style="background:#eee; margin:20px 0px; padding:20px; width:500px; border:solid 1px #999;">--%>
<div style="margin:20px 0px;">
<form action="notification.do?action=send" name="form1" method="post" style="margin: 0px;">
<table width="600" cellpadding="4" cellspacing="0" border="0">
<tr>
	<td width="20%">To:</td>
	<td width="80%">
		<input type="radio" name="broadcast" value="Y" checked="checked" />  All (Broadcast) 
        <input type="radio" name="broadcast" value="N" /> Single Device 
	</td>
</tr>
<tr id="trUsername" style="display:none;">
	<td>Username:</td>
	<td><input type="text" id="username" name="username" value="" style="width:380px;" /></td>
</tr>
<tr>
	<td>Title:</td>
	<td><input type="text" id="title" name="title" value="钱多多提示" style="width:380px;" /></td>
</tr>
<tr>
	<td>Message:</td>
	<td><textarea id="message" name="message" style="width:380px; height:80px;" >钱多多亲情提示：股票市场马上就要火爆起来了，亲请准备好出手了！</textarea></td>
</tr>
<%--
<tr>
	<td>Ticker:</td>
	<td><input type="text" id="ticker" name="ticker" value="" style="width:380px;" /></td>
</tr>
--%>
<tr>
	<td>URI:</td>
	<td><input type="text" id="uri" name="uri" value="" style="width:380px;" />
	    <br/><span style="font-size:0.8em">ex) http://www.hotyaya.com, geo:37.24,131.86, tel:111-222-3333</span>
	</td>
</tr>
<tr>
	<td>&nbsp;</td>
	<td><input type="submit" value="Submit" /></td>
</tr>
</table> 
</form>
</div>

<script type="text/javascript"> 
//<![CDATA[
 
$(function() {
	$('input[name=broadcast]').click(function() {
		if ($('input[name=broadcast]')[0].checked) {
			$('#trUsername').hide();
		} else {
			$('#trUsername').show();
		}
	});
	
	if ($('input[name=broadcast]')[0].checked) {
		$('#trUsername').hide();
	} else {
		$('#trUsername').show();
	}	
});


//cnt=0; 
//endtime=5400;//5400秒 
//function   timer(){ 
//	window.setInterval( "remain=endtime-cnt++;window.status=Math.floor(remain/60)+ ': '+(remain%60);if(remain <0)form1.submit() ",1000) 
//} 


//]]>
</script>

</body>
</html>
