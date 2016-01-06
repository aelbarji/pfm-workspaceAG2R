<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="<s:property value="#session.ENSEIGNE" />/css/head.css" type="text/css" />
	<script type="text/javascript">
		function deconnection() {
			parent.location.href = "logoutAdminAction.action";
		}
	</script>
</head>
<body>
	<div class="header">
		<div class="headerHaut">
			<div class="headerHautGauche"></div>
			<div class="headerHautDroite"></div>
		</div>
		<div class="headerLogo">
			<a class="Logo">
				<img alt="Logo" align="top" src="<s:property value="#session.ENSEIGNE" />/img/logo.png" />
			</a>
		</div>
		<div class="headerTitle">
			<p>Poste d'administration</p>
		</div>
		<div class="headerInfo">
			<div class="headerInfoTop">
				<p>
					<a href="#" onclick="deconnection()" class="headerLink">Déconnexion</a>
				</p>
			</div>
			<div class="headerInfoBottom">
				<p>
				</p>
			</div>
		</div>
	</div>
	<div class="headerBottom">&nbsp;
	</div>
</body>
</html>