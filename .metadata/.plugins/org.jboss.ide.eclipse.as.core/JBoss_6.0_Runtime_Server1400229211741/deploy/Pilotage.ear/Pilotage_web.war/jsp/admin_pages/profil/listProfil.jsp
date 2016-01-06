<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/admin.css">
	<link rel="shortcut icon" type="image/x-icon" href="<s:property value="#session.ENSEIGNE" />/img/favicon.ico" />
	<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
	<script type="text/javascript" src="js/common_admin.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
		function supprimer(id){
			if(confirm("Voulez-vous vraiment supprimer ce profil ?")){
				changeData('selectRow', id); 
				submitData('supprimerProfilAdminAction.action');
			}
		}
	</script>
</head>
<body>
	<div class="error" id="error"><s:label value="%{error}" cssClass="error" id="error"></s:label></div>
	<div class="info" id="info"><s:label value="%{info}" cssClass="info" id="info"></s:label></div>
	<div class="titrePage">
		<s:label value="Liste des profils" cssClass="title"></s:label>
	</div>
	<div class="Pagecontents" style="width: 90%">
		<div class="plus" >
			<s:a cssClass="ajouter" href="showCreateProfilAdminAction.action" >
				<img class="icone" id="ajouterimg" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" >&nbsp;ajouter
			</s:a>
		</div>
		<s:form theme="simple" id="mainForm">
			<s:hidden id="selectRow" name="selectRow" value=""></s:hidden>
			<table border="1" rules="rows" cellpadding="0" cellspacing="0" width="100%" class="tabCenter">
				<thead>
					<tr>
						<th class="colTitle" width="5%">ID</th>
						<th class="colTitle" width="40%">Libellé</th>
						<th class="colTitle" width="25%">Page d'accueil</th>
						<th class="colTitle" width="15%">Clignotement consignes</th>
						<th class="colTitle" width="10%">Admin/Pilote</th>
						<th class="colTitle" width="5%"></th>
					</tr>
				</thead>
				<tbody>
					<s:if test="%{listProfil.size() == 0}">
						<tr>
							<td class="emptyListText" colspan="6">
								Aucun profil n'est défini en base de données
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listProfil" status="status">
							<tr class=<s:if test="#status.odd == true">"oddRow"</s:if>
										<s:else>"evenRow"</s:else>>
								<td align="center"><s:property value="id"/> </td>
								<td align="center"><s:property value="libelle"/></td>
								<td align="center">
									<s:if test="%{accueil != null}"><s:property value="accueil.getLibelle()"/></s:if>
								</td>
								<td align="center"><s:property value="clign_consigne"/></td>
								<td align="center"><s:if test="%{admin}">Admin</s:if><s:elseif test="%{pilote}">Pilote</s:elseif></td>
								<td align="center">
									<s:a href="#" onclick="javascript:changeData('selectRow', '%{id}'); submitData('showModifyProfilAdminAction.action')">
										<img border="0" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png"/>
									</s:a>
									<s:a href="#" onclick="javascript:supprimer('%{id}');">
										<img border="0" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png"/>
									</s:a>
								</td>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
			</table>
		</s:form>
	</div>
</body>
</html>