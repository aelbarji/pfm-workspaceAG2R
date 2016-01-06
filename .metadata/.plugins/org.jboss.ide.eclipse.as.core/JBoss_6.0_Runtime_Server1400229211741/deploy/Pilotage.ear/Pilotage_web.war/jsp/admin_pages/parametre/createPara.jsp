<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/admin.css">
	<link rel="shortcut icon" type="image/x-icon" href="<s:property value="#session.ENSEIGNE" />/img/favicon.ico" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache, must-revalidate" /> 
	<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
	<script type="text/javascript" src="js/common_admin.js"></script>
	<script type="text/javascript">
		function valider(){
			var mainForm = document.getElementById("mainForm");
			if(mainForm.libelle.value.replace(/^\s+/g,'').replace(/\s+$/g,'') == ""){
				alert("Veuillez entrer un libellé");
				mainForm.libelle.focus();
				return false;
			}
			mainForm.submit();
		}
	</script>
</head>
<body>
	<div class="error" id="error"><s:label value="%{error}" cssClass="error" id="error"></s:label></div>
	<div class="info" id="info"><s:label value="%{info}" cssClass="info" id="info"></s:label></div>
	<div class="titrePage">
		<s:label value="Création d'un paramètre" cssClass="title"></s:label>
	</div>
	<div class="Pagecontents">
		<s:form id="mainForm" action="createParaAdminAction" theme="simple">
			<table class="tabSaisie" width="600px;">
				<tr>
					<td width="100px">Libellé <span class="starObligatoire">*</span> : </td>
					<td><s:textfield id="libelle" name="libelle" maxlength="127" size="60"></s:textfield></td>
				</tr>
				<tr>
					<td>Valeur : </td>
					<td><s:textarea id="valeur" name="valeur"  rows="3" cols="50"  onkeypress="if(event.keyCode == 13) return false;"></s:textarea></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="50px"><s:a href="showParaAdminAction.action" cssClass="annuler">ANNULER</s:a></td>
								<td>&nbsp;</td>
								<td><s:a href="#" onclick="javascript:valider();" cssClass="valider">VALIDER</s:a></td>
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