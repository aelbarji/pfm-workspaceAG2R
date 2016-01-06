<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<s:include value="/jsp/user_pages/commun/html_head.jsp" />
<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/validationEngine.jquery.css">
<script type="text/javascript" src="js/jquery.validationEngine.js" charset="utf-8"></script>
<script type="text/javascript" src="js/languages/jquery.validationEngine-fr.js" charset="utf-8"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#mainForm").validationEngine();
	});
	function valider() {

		var modifierForm = document.getElementById("mainForm");

		if (modifierForm.libelleCaisse.value.replace(/^\s+/g, '').replace(
				/\s+$/g, '') != '<s:property value="%{libelleCaisse}" />') {
			modifierForm.libelleCaisseChanged.value = 'true';
		}
		if (modifierForm.nomCaisseComplet.value.replace(/^\s+/g, '').replace(
				/\s+$/g, '') != '<s:property value="%{nomCaisseComplet}" />') {
			modifierForm.libelleCaisseChanged.value = 'true';
		}
		$("#mainForm").validationEngine('validate');
	}

	function retour() {
		submitData('showCaisseAction.action');
	}
</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre">
			<s:property value="#session.TITLE_IN_SESSION.get('SRV_MOD')" />
		</s:param>
	</s:include>

	<div class="contentTable" style="width: 40%">
		<s:form theme="simple" id="mainForm" action="modifyCaisseAction">
			<s:token></s:token>
			<s:hidden name="caisseID" id="caisseID" />
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage" />
			<s:hidden name="nrPages" id="nrPages" />

			<s:hidden name="libelleCaisseChanged" value="false" />
			<table class="formTable">
				<tr>
					<td align="left" colspan="2">Veuillez entrer le nom de la
						caisse et son abréviation</td>
				</tr>
				<tr>
					<td>Nom complet de la caisse <span class="champObligatoire">*</span>
						:</td>
					<td><s:textfield name="nomCaisseComplet" id="nomCaisseComplet"
							maxlength="80" size="44" data-validation-engine="validate[required]"></s:textfield></td>
				</tr>
				<tr>
					<td>Abréviation <span class="champObligatoire">*</span> :</td>
					<td><s:textfield name="libelleCaisse" id="libelleCaisse"
							maxlength="25" size="44" data-validation-engine="validate[required]"></s:textfield></td>
				</tr>

				<tr>
					<td colspan="2"><s:include
							value="/jsp/user_pages/commun/html_body_div_contentTableBottom_submit.jsp" />
					</td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>