<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title><s:property value="#session.TITLE_IN_SESSION.get('STAT_PRT')" /></title>
		<link rel="shortcut icon" type="image/x-icon" href="<s:property value="#session.ENSEIGNE" />/img/favicon.ico" />
		<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/pilotage.css"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script type="text/javascript">
			function valider() {
				var a = document.getElementsByName("partImprimer");
				var b = "";
				for (var i = 0; i<a.length; i++) {
					if (document.getElementById(a[i].value).checked == true) {
						b += a[i].value + ";";
					}
				}
				window.returnValue = b;
				window.close();
			}
		</script>
	</head>
	<body onblur="window.focus();">
		<form id="mainForm">
			<table width="100%" class="formTable">
				<tr>
					<td>Statistiques mensuelles :</td>
					<td><input type="checkbox" name="partImprimer" id="mensuel" value="mensuel" /></td>
				</tr>
				<tr>
					<td>Statistiques hebdomadaires :</td>
					<td><input type="checkbox" name="partImprimer" id="hebdomadaire" value="hebdomadaire" /></td>
				</tr>
				<tr>
					<td>Statistiques journalières :</td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Résumé :</td>
					<td><input type="checkbox" name="partImprimer" id="resume" value="resume" /></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Top 5 - Serveurs :</td>
					<td><input type="checkbox" name="partImprimer" id="serveur" value="serveur" /></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Top 5 - Applications :</td>
					<td><input type="checkbox" name="partImprimer" id="application" value="application" /></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Top 5 - Services :</td>
					<td><input type="checkbox" name="partImprimer" id="service" value="service" /></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Top 5 - Domaines :</td>
					<td><input type="checkbox" name="partImprimer" id="domaine" value="domaine" /></td>
				</tr>
			</table>
			<div align="center">
				<s:a href="#" onclick="javascript:valider();" cssClass="boutonValider">Valider</s:a>
			</div>
		</form>
	</body>
</html>