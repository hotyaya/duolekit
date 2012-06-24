<%@ page language="java" contentType="text/xml;charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><?xml version="1.0" encoding="UTF-8"?>
<versions><c:forEach items="${versions}" var="version" >
	<version >
		<id>${version.id}</id>
		<currentItem>${version.currentItem}</currentItem>
		<currentVersion>${version.currentVersion}</currentVersion>
		<publishDate>${version.publishDate}</publishDate>
		<description>${version.description}</description>
	</version>
</c:forEach>
</versions>