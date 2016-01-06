<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/admin.css">
	<link rel="shortcut icon" type="image/x-icon" href="<s:property value="#session.ENSEIGNE" />/img/favicon.ico" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
	<script type="text/javascript" src="js/common_admin.js"></script>
	<script type="text/javascript">
		function unselect(option){
			document.getElementById(option).checked = false;
		}

		function valider(){
			if(document.getElementById("libelle").value.replace(/^\s+/g,'').replace(/\s+$/g,'')  == ""){
				alert("Veuillez entrer un libellé");
				document.getElementById("libelle").focus();
				return false;
			}

			if(document.getElementById("libelle").value.replace(/^\s+/g,'').replace(/\s+$/g,'')  == ""){
				document.getElementById("libelle").value = "-1";
			}
			
			submitData('createProfilAdminAction.action');
		}
	</script>
</head>
<body>
	<div class="error" id="error"><s:label value="%{error}" cssClass="error" id="error"></s:label></div>
	<div class="info" id="info"><s:label value="%{info}" cssClass="info" id="info"></s:label></div>
	<div class="titrePage">
		<s:label value="Création d'un profil" cssClass="title"></s:label>
	</div>
	<div class="Pagecontents" style="width: 900px;">
		<s:form theme="simple" id="mainForm">
			<table class="tabSaisie" width="900px">
				<tr>
					<td>Libellé <span class="starObligatoire">*</span> : </td>
					<td><s:textfield id="libelle" name="libelle" maxlength="127" size="60"></s:textfield></td>
				</tr>
				<tr>
					<td>Page d'accueil <span class="starObligatoire">**</span> : </td>
					<td><s:select list="%{listMenu}" listKey="id" listValue="libelle" name="accueil" emptyOption="true"></s:select></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td>Clignotement des consignes</td>
					<td><s:checkbox name="clignConsigne"></s:checkbox></td>
				</tr>
				<tr>
					<td>Administrateur</td>
					<td><s:checkbox name="admin" onclick="unselect('pilote')" id="admin"></s:checkbox></td>
				</tr>
				<tr>
					<td>Pilote</td>
					<td><s:checkbox name="pilote" onclick="unselect('admin')" id="pilote"></s:checkbox></td>
				</tr>
				<tr>
					<td colspan="2"><hr/></td>
				</tr>
				<tr>
					<td colspan="2">Droits : </td>
				</tr>
				<tr>
					<td colspan="4">
						<table class="tabCenter" rules="rows" width="100%" border="1">
						<s:iterator value="listSousModule" id="lm" status="status">
							<tr>
								<th class="colTitleDroit" width="5%" colspan="4"> <s:property value="%{listModule[#status.index].nom}"/></th>
							</tr>
							<s:iterator value="%{listSousModule[#status.index]}" id="lsm" status="id">
									<tr>
										<td width="30%"><s:property value="nom"/></td>
										<td width="25%">
											<s:if test="idAjout.id != NULL">
												<s:checkbox name="droitSelected" value="" fieldValue="%{idAjout.libelle}" />Ajout
											</s:if>
										</td>
										<td width="25%">
											<s:if test="idModif.id != NULL">
												<s:checkbox name="droitSelected" value="" fieldValue="%{idModif.libelle}"/>Modification
											</s:if>
										</td>
										<td width="30%">
											<s:if test="idSuppr.id != NULL">
												<s:checkbox name="droitSelected" value="" fieldValue="%{idSuppr.libelle}"/>Suppression
											</s:if>
										</td>
									</tr>
							</s:iterator>
					</s:iterator>
				<tr>
					<td></td>
					<td>
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="50px"><s:a href="showProfilAdminAction.action" cssClass="annuler">ANNULER</s:a></td>
								<td>&nbsp;</td>
								<td><s:a href="#" onclick="javascript:valider();" cssClass="valider">VALIDER</s:a></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<span class="champObligatoire">* Champs Obligatoires</span><br/>
						<span class="champObligatoire">** Le menu doit être affecté au profil pour qu'il soit pris en compte en tant que page d'accueil</span>
					</td>
				</tr>
			</table>
			</td>
			</tr>
			</table>
		</s:form>
	</div>
</body>
</html>