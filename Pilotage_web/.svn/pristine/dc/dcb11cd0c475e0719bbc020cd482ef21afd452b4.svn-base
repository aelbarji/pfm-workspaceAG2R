<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- <link rel="icon" type="image/png" href="<s:property value="#session.ENSEIGNE" />/img/favicon.png" /> -->
	<link rel="shortcut icon" type="image/x-icon" href="<s:property value="#session.ENSEIGNE" />/img/favicon.ico" />
	<link rel="stylesheet" href="<s:property value="#session.ENSEIGNE" />/css/loginAdmin.css" type="text/css" />
	<script type="text/javascript" src="js/login.js"></script>
	<title><s:property value="#session.TITRE_APPLICATION" /></title>
	<script type="text/javascript">
		function load(){
			if (window != top)
		   	 	top.location.href = location.href;
		}
	</script>
</head>
<body onload="load()">
	<div class="loginTop">&nbsp;</div>
	<div class="error" id="errormessage"><s:actionerror /></div>
	<div class="login">
		<h1><s:property value="#session.TITRE_APPLICATION" /></h1>
		<h2>POSTE D'ADMINISTRATION</h2>
		<s:form action="loginAdminAction" method="POST" onsubmit="return verifyLogin(this)">
			<table class="loginForm">
				<tr>
					<td>
						<div class="encadreLoginForm">
							<table class="innerLoginForm">
								<tr>
									<td class="leftCell">Login:</td>
									<td class="rightCell">
										<s:textfield name="username" theme="simple" size="40" maxLength="30"/>
									</td>
								</tr>
								<tr>
									<td>Mot de passe:</td>
									<td><s:password name="password" theme="simple" size="40"/></td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
			</table>
			<input type="submit" value="VALIDER" id="submit" />
		</s:form>
		<p><img src="<s:property value="#session.ENSEIGNE" />/img/logo.png"/></p>
	</div>
	<div class="loginBottom">&nbsp;</div>
</body>
</html>