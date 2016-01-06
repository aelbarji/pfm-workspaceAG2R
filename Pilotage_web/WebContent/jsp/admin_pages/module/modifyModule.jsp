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
		var mainForm = document.getElementById("mainForm");
		if (mainForm.nom.value.replace(/^\s+/g,'').replace(/\s+$/g,'') == '') {
			alert('Veuillez entrer un libellé');
			mainForm.nom.focus();
			return false;
		} 
		else {
			submitData('modifyModuleAdminAction.action');
		}
	}
	
	</script>
	
</head>
<body>

<div class="error" id="error"><s:label value="%{error}" cssClass="error" id="error"></s:label></div>
	<div class="info" id="info"><s:label value="%{info}" cssClass="info" id="info"></s:label></div>
	<s:label value="Modification d'un module" cssClass="title"></s:label>
	<div class="Pagecontents" style="width: 90%">
		
		
		<s:form theme="simple" id="mainForm" action="modifyModuleAdminAction">
		<s:token></s:token>
		<s:hidden id="moduleID" name="moduleID"></s:hidden>
		<table class="tabSaisie" width="400px">
		<tr>
			<td>Module <span class="starObligatoire">*</span> : </td>
			<td><s:textfield id="nom" name="nom" maxlength="127" size="40"></s:textfield></td>
		</tr>
		
		<tr>
			<td></td>
					<td>
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="50px"><s:a href="showModuleAdminAction.action" cssClass="annuler">ANNULER</s:a></td>
								<td>&nbsp;</td>
								<td><s:a href="#" onclick="javascript:valider();" cssClass="valider">VALIDER</s:a></td>
							</tr>
						</table>
					</td>
				</tr>
		</table>
		</s:form>
	</div>

</body>
</html>