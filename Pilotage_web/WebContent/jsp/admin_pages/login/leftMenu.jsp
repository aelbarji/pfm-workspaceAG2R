<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="<s:property value="#session.ENSEIGNE" />/css/menuAdmin.css" type="text/css" />
</head>
<body>
	<table class="table">
		<tr>
			<td class="menuPremiere">
				<a href="#" onclick="window.parent.goURL('showParaAdminAction.action'); return true">Paramètres</a>
			</td>
		</tr>
		<tr>
			<td class="menuPremiere">Gestion des menus</td>
		</tr>
		<tr>
			<td class="menuSecond">
				<a href="#" onclick="window.parent.goURL('showMenuAdminAction.action'); return true">Création/modification</a>
			</td>
		</tr>
		<tr>
			<td class="menuSecond">
				<a href="#" onclick="window.parent.goURL('showAffectationAdminAction.action'); return true">Affectation</a>
			</td>
		</tr>
		<tr>
			<td class="menuPremiere">
				<a href="#" onclick="window.parent.goURL('showProfilAdminAction.action'); return true">Gestion des profils</a>
			</td>
		</tr>
		<tr>
			<td class="menuPremiere">
				<a href="#" onclick="window.parent.goURL('showModuleAdminAction.action'); return true">Gestion des droits</a>
			</td>
		</tr>
		<tr>
			<td class="menuPremiere">
				<a href="#" onclick="window.parent.goURL('showTitreAdminAction.action'); return true">Gestion des titres des pages</a>
			</td>
		</tr>
		<tr>
			<td class="menuPremiere">Gestion de la checklist</td>
		</tr>
		<tr>
			<td class="menuSecond">
				<a href="#" onclick="window.parent.goURL('showChecklistColorAdminAction.action'); return true">Couleurs</a>
			</td>
		</tr>
		<tr>
			<td class="menuPremiere">
				Gestion des documents Consignes</a>
			</td>
		</tr>
		<tr>
			<td class="menuSecond">
				<a href="#" onclick="window.parent.goURL('showChargementDocumentsAdminAction.action'); return true">Chargement des fichiers</a>
			</td>
		</tr>
		<tr>
			<td class="menuSecond">
				<a href="#" onclick="window.parent.goURL('showSupprDocumentsAdminAction.action'); return true">Gestion des fichiers</a>
			</td>
		</tr>
		<tr>
			<td class="forSpace"></td>
		</tr>
	</table>
</body>
</html>