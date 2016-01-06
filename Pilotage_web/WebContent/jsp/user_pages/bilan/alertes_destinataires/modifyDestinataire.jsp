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
			if (mainForm.mail.value.replace(/^\s+/g, '').replace(/\s+$/g, '') != '<s:property value="%{mail}" />') {
				mainForm.libelleChanged.value = 'true';
			}
			$("#mainForm").validationEngine('validate');
		}

			function retour(){
				submitData('showAlertesDestinataire.action');
			}
		</script>
	</head>
	<body>
		<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
			<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('ALT_DST_M')" /></s:param>
		</s:include>
		
		<div class="contentTable" style="width: 50%">
			<s:form id="mainForm" action="modifyAlertesDestinataire" theme="simple" >
				<s:token></s:token>
				<s:hidden name="selectedID"/>
				<s:hidden name="libelleChanged" id="libelleChanged" />
				
				<table class="formTable">
					<tr>
						<td align="left" colspan="2">Veuillez entrer les informations du destinataire</td>
					</tr>
					<tr>
						<td>Nom <span class="champObligatoire">*</span> : </td>
						<td><s:textfield name="nom" id="nom" maxlength="127" size="60" data-validation-engine="validate[required]"/></td>
					</tr>
					<tr>
						<td>Prénom <span class="champObligatoire">*</span> : </td>
						<td><s:textfield name="prenom" id="prenom" maxlength="127" size="60" data-validation-engine="validate[required]"/></td>
					</tr>
					<tr>
						<td>Mail <span class="champObligatoire">*</span> : </td>
						<td><s:textfield name="mail" id="mail" size="60" data-validation-engine="validate[required,custom[email]]"/></td>
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