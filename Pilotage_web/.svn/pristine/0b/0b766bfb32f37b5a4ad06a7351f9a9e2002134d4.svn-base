<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" type="image/x-icon" href="<s:property value="#session.ENSEIGNE" />/img/favicon.ico" />
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/loginUtilisateur.css">
	<script type="text/javascript" src="js/login.js"></script>
	<title>Réinitialisation</title>
</head>
<body>
	<div class="loginTop">&nbsp;</div>
	<div class="error" id="errormessage"><s:actionerror /></div>
	<div class="login">
		<h1>OUTIL DE SUIVI PILOTAGE MUTUALISE</h1>
		<s:form name="initialize" onsubmit="return verifyReinit(this)" action="passwordReinitAction" method="POST">
			<table cellpadding="5px" class="reinitForm">
				<tr>
					<td class="center">
						R&eacute;initialisation de votre mot de passe : veuillez entrer votre login.<br/>
						Un e-mail sera envoye &agrave; votre adresse mail avec votre nouveau mot de passe.
					</td>
				</tr>
				<tr>
					<td class="center">
							Identifiant:
	
							<s:textfield name="username" theme="simple" size="40" maxLength="30"></s:textfield>
	
							<input type="submit" id="submit" name="reinitialiser" value="REINITIALISER" />
							
							<input type="button" id="retour" name="retour" value="RETOUR" onclick="window.location.replace('enterAction.action');" />
					</td>
				</tr>
			</table>
		</s:form>
		<p><img src='<s:property value="#session.ENSEIGNE" />/img/logo.png' title='logo' class="logo" /></p>
	</div>
	<div class="loginBottom">&nbsp;</div>
</body>
</html>