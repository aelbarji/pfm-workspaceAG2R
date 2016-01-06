<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			if (mainForm.libelle.value.replace(/^\s+/g, '').replace(/\s+$/g, '') != '<s:property value="%{type}" />') {
				mainForm.libelleChanged.value = 'true';
			}
			$("#mainForm").validationEngine('validate');
		}

		function retour(){
				submitData('showAlertesType.action');
			}
		</script>
	</head>
	<body>
		<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
			<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('ALT_TYP_M')" /></s:param>
		</s:include>
	
		<div class="contentTable" style="width: 50%">
			<s:form name="mainForm" id="mainForm" action="modifyAlertesType" theme="simple" >
				<s:token></s:token>
				<s:hidden name="selectedID"/>
				<s:hidden name="libelleChanged" id="libelleChanged"/>
				
				<table class="formTable">
					<tr>
						<td align="left" colspan="2">Veuillez entrer le nouveau type d'alerte</td>
					</tr>
					<tr>
						<td>Libellé <span class="champObligatoire">*</span> : </td>
						<td><s:textfield name="libelle" id="libelle" maxlength="50" size="60" data-validation-engine="validate[required]"></s:textfield></td>
					</tr>
					<tr>
						<td colspan="2">
							<s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom_submit.jsp" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>
	</body>
</html>