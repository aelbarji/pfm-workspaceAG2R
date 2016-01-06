<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/admin.css"/>
	<link rel="shortcut icon" type="image/x-icon" href="<s:property value="#session.ENSEIGNE" />/img/favicon.ico" />
	<meta http-equiv="content-type" content="text/html; charset=UTF-8;" />
	<meta http-equiv="cache-control" content="no-cache, must-revalidate" />
	<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
	<script type="text/javascript" src="js/common_admin.js"></script>
	<script type="text/javascript">
		function valider(){
			if(document.getElementById('id').value.replace(/^\s+/g,'').replace(/\s+$/g,'') == ""){
				alert("Veuillez entrer un identifiant");
				document.getElementById('id').focus();
			}
			else if(!checkLettresChiffres(document.getElementById('id').value)){
				alert("L'identifiant doit être composé uniquement de chiffres, de lettres et du caractère _");
				document.getElementById('id').focus();
			}
			else if(document.getElementById('description').value.replace(/^\s+/g,'').replace(/\s+$/g,'') == ""){
				alert("Veuillez entrer une description");
				document.getElementById('description').focus();
			}
			else{
				submitData('createTitreAdminAction.action');
			}
		}
	</script>
</head>
<body>
	<div class="error" id="error"><s:label value="%{error}" cssClass="error" id="error"></s:label></div>
	<div class="info" id="info"><s:label value="%{info}" cssClass="info" id="info"></s:label></div>
	<div class="titrePage">
		<s:label value="Création d'un titre de page" cssClass="title"></s:label>
	</div>
	<div class="Pagecontents" style="width : 600px">
		<s:form id="mainForm" theme="simple">
			<table class="tabSaisie">
				<tr>
					<td width="120px">Identificateur <span class="starObligatoire">*</span> : </td>
					<td><s:textfield id="id" name="id" maxlength="12" size="12"></s:textfield></td>
				</tr>
				<tr>
					<td>Description <span class="starObligatoire">*</span> : </td>
					<td><s:textfield id="description" name="description" maxlength="127" size="60"></s:textfield></td>
				</tr>
				<tr>
					<td>Titre : </td>
					<td><s:textfield id="titre" name="titre" maxlength="127" size="60"></s:textfield></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="50px"><s:a href="#" onclick="javascript:valider();" cssClass="valider">VALIDER</s:a></td>
								<td>&nbsp;</td>
								<td><s:a href="showTitreAdminAction.action" cssClass="annuler">ANNULER</s:a></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr align="left">
					<td colspan="2"><span class="champObligatoire">* Champs Obligatoires</span></td>
				</tr>
			</table>
			<br />
		</s:form>
	</div>
</body>
</html>