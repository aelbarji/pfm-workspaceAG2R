<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<s:include value="/jsp/user_pages/commun/html_head.jsp" />
<link rel="stylesheet" type="text/css"
	href="<s:property value="#session.ENSEIGNE" />/css/validationEngine.jquery.css">
<script type="text/javascript" src="js/jquery.validationEngine.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="js/languages/jquery.validationEngine-fr.js" charset="utf-8"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#mainForm").validationEngine();
});
	function valider() {
		var mainForm = document.getElementById("mainForm");
		if (mainForm.libelle.value.replace(/^\s+/g, '').replace(/\s+$/g, '') != '<s:property value="%{libelle}" />') {
			mainForm.libelleChanged.value = 'true';
		}
		var initVar = "${filiale}";
		if ((initVar == 1 && mainForm.filialeTrue.checked)
				|| (initVar == 0 && mainForm.filialeFalse.checked)) {
			mainForm.filialeChanged.value = 'true';
		}
		$("#mainForm").validationEngine('validate');

	}

	function retour() {
		submitData('showDisque.action');
	}
</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre">
			<s:property value="#session.TITLE_IN_SESSION.get('DSQ_MOD')" />
		</s:param>
	</s:include>

	<div class="contentTable">
		<s:form name="mainForm" id="mainForm" action="modifyDisque" theme="simple" >
			<s:token></s:token>
			<s:hidden name="selectedID" />
			<s:hidden name="libelleChanged" id="libelleChanged" />
			<s:hidden name="filialeChanged" id="filialeChanged" />

			<table class="formTable">
				<tr>
					<td align="left" colspan="2">Veuillez entrer le nouveau
						libellé du disque</td>
				</tr>
				<tr>
					<td align="left">Libellé <span class="champObligatoire">*</span>
						:</td>
					<td align="left"><s:textfield name="libelle" id="libelle"
							maxlength="127" size="60" data-validation-engine="validate[required]"></s:textfield></td>
				</tr>
				<tr>
					<td align="left">Filiale <span class="champObligatoire">*</span>
						:</td>
					<td align="left"><input type="radio" id="filialeTrue"
						name="filiale" value="1"
						<s:if test="filiale == 1">checked="checked"</s:if> data-validation-engine="validate[required]"/>Oui <input
						type="radio" id="filialeFalse" name="filiale" value="0"
						<s:if test="filiale == 0">checked="checked"</s:if> data-validation-engine="validate[required]"/>Non</td>
				</tr>
				<tr>
					<td align="left">Seuil <span class="champObligatoire">*</span>
						:</td>
					<td align="left"><s:textfield name="seuil" id="seuil"
							maxlength="6" size="8" data-validation-engine="validate[required, custom[number],min[0],max[100]]"></s:textfield>%</td>
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