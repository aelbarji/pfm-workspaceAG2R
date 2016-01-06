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

	function valider(){
		var createForm = document.getElementById("mainForm");
		if (createForm.nom.value.replace(/^\s+/g,'').replace(/\s+$/g,'') == '') {
			alert('Veuillez entrer un libellé de sous-module');
			createForm.nom.focus();
			return false;
		} 
		else {
			submitData('createSousModuleAdminAction.action');
		}		
	}

	function retour(){
		submitData('showSousModuleAdminAction.action');
	}
	
	</script>
	
</head>
<body>

<div class="error" id="error"><s:label value="%{error}" cssClass="error" id="error"></s:label></div>
	<div class="info" id="info"><s:label value="%{info}" cssClass="info" id="info"></s:label></div>
	<s:label value="Création d'un sous-module" cssClass="title"></s:label>
	<div class="Pagecontents" style="width: 700px">
	
		<s:form theme="simple" id="mainForm" action="createSousModuleAdminAction">
			<s:token></s:token>
			<s:hidden id="moduleID" name="moduleID"></s:hidden>
		<s:div align="center">
			<s:a>Sous-module <span class="starObligatoire">*</span> : </s:a>
			<s:textfield id="nom" name="nom" maxlength="127" size="40" value="%{sm.nom}" ></s:textfield>
		</s:div>
		<table class="tabSaisie" width=100%>
			<colgroup>
				<col width="20%"/>
				<col width="30%"/>
				<col width="50%"/>
			</colgroup>
			<thead>
				<tr>
					<th>&nbsp;</th>
					<th class="colTitle">Nom des droits  <span class="starObligatoire">*</span> : </th>
					<th class="colTitle">description</th>
				</tr>
			</thead>
			<tbody>
				<tr class="oddRow">
					<td>d'ajout : </td>
					<td><s:textfield id="nomAjout" name="nomAjout" maxlength="127" size="25"></s:textfield></td>
					<td><s:textfield id="descriptionAjout" name="descriptionAjout" maxlength="127" size="40"></s:textfield></td> 
				</tr>
				<tr class="evenRow">
					<td>de modification : </td>
					<td><s:textfield id="nomModif" name="nomModif" maxlength="127" size="25"></s:textfield></td> 
					<td><s:textfield id="descriptionModif" name="descriptionModif" maxlength="127" size="40"></s:textfield></td>
				</tr>
				<tr class="oddRow">
					<td>de suppression : </td>
					<td><s:textfield id="nomSuppr" name="nomSuppr" maxlength="127" size="25"></s:textfield></td>
					<td><s:textfield id="descriptionSuppr" name="descriptionSuppr" maxlength="127" size="40"></s:textfield></td>
				<tr class="evenRow">
					<td>de Détail : </td>
					<td><s:textfield id="nomDetail" name="nomDetail" maxlength="127" size="25"></s:textfield></td>
					<td><s:textfield id="descriptionDetail" name="descriptionDetail" maxlength="127" size="40"></s:textfield></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="10">
						<table align="right" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td align="right"><s:a href="#" onclick="javascript:retour();"cssClass="annuler">ANNULER</s:a></td>
								<td width="50px"><s:a href="#" onclick="javascript:valider();" cssClass="valider">VALIDER</s:a></td>
							</tr>
						</table>
					</td>
				</tr>
			</tfoot>
			</table>
		</s:form>
	</div>

</body>
</html>