<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" type="image/x-icon" href="<s:property value="#session.ENSEIGNE" />/img/favicon.ico" />
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/loginUtilisateur.css">
	<script type="text/javascript" src="js/login.js"></script>
	<title><s:property value="#session.TITRE_APPLICATION" /></title>
</head>
<body>
	<div class="loginTop">&nbsp;</div>
	<div class="error" id="errormessage"><s:actionerror /></div>
	<div class="login">
		<h1><s:property value="#session.TITRE_APPLICATION" /></h1>
		
		<s:form action="loginAction" method="POST" onsubmit="return verifyLogin(this)">
			<s:hidden name="timezone" id="timezone"/>
			<table cellpadding="5px" class="loginForm">
				<tr>
					<td class="leftCell">Login:</td>
					<td class="rightCell"><s:textfield name="username" theme="simple" size="40" maxLength="30"></s:textfield></td>
				</tr>
				<tr>
					<td>Mot de passe:</td>
					<td><s:password name="password" theme="simple" size="40"></s:password></td>
				</tr>
			</table>
			<input type="submit" value="VALIDER" id="submit" class="boutonValider"/>
		</s:form>
		<p><s:a href="passwordReinitPage.action" cssClass="initialize">Vous avez perdu votre mot de passe?</s:a></p>
		<p><img src='<s:property value="#session.ENSEIGNE" />/img/logo.png' title='logo' class="logo" /></p>
	</div>
	<div class="loginBottom">&nbsp;</div>
</body>
</html>