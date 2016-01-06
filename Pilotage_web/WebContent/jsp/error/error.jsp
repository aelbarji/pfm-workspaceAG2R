<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="shortcut icon" type="image/x-icon" href="<s:property value="#session.ENSEIGNE" />/img/favicon.ico" />
		<link rel="stylesheet" href='<s:property value="#session.ENSEIGNE" />/css/error.css' type="text/css" />
	</head>
	<body>
		<div class="errorText">
			<s:property value="%{error}" escape="false"/>
			<s:actionerror/>
		</div>
		<div class="errorLink">
			<a href="#" onclick="javascript:history.back(1);">Pour revenir sur la page précédente, cliquez ici</a>
		</div>
	</body>
</html>