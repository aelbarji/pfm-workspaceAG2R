<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/admin.css">
	<title><s:property value="#session.TITRE_APPLICATION" /> - Poste d'administration</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv='Expires' content='-10'/> 
	<meta http-equiv='Pragma' content='No-cache'/> 
	<meta http-equiv='Cache-Control', content='private'/>
	<link rel="shortcut icon" type="image/x-icon" href="<s:property value="#session.ENSEIGNE" />/img/favicon.ico" />
	<script type="text/javascript">
		function load() {
			if (window != top)
				top.location.href = location.href;
		}
		window.onload = load;
	
		function goURL(urlToGo) {
	
			var check = document.getElementById("showFrame");
			check.src = urlToGo;
	
		}
	</script>
</head>
	<FRAMESET rows="100, *" border="0" framespacing="0">
		<FRAME name="topFrame" src="headAction.action" scrolling="no" frameborder="0" ></FRAME>

		<FRAMESET cols="250, *" border="0" framespacing="0">
			<frame name="leftFrame" src="leftMenuAction.action" scrolling="no" frameborder="0"></frame>
			<frame id="showFrame" name="rightFrame" src="jsp/admin_pages/login/welcome.jsp" scrolling="auto" frameborder="0"></frame>
		</FRAMESET>
	</FRAMESET> 

</html>