<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<s:include value="/jsp/user_pages/commun/html_head.jsp" />
		
		<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/validationEngine.jquery.css"></link>
		<script type="text/javascript" src="js/jquery.validationEngine.js" charset="utf-8"></script>
		<script type="text/javascript" src="js/languages/jquery.validationEngine-fr.js" charset="utf-8"></script>
		<script type="text/javascript">
	

			// fonction de validation du formulaire
			$(document).ready(function(){
		    	$("#mainForm").validationEngine();
		  	});

			function valider() {
				$("#mainForm").validationEngine('validate');
			}

			function retour(){
				submitData('showAstreinteDestinataire.action');
			}
		</script>
	</head>
	<body>
		<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
			<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('AST_DES_C')" /></s:param>
		</s:include>
		
		<div class="contentTable">
			<s:form id="mainForm" method="POST" action="createAstreinteDestinataire" theme="simple">
				<s:token></s:token>
									
				<table class="formTable">
					<tr>
						<td align="left">Nom <span class="champObligatoire">*</span> : </td>
						<td align="left"><s:textfield name="nom" id="nom" maxlength="127" size="60" data-validation-engine="validate[required]"></s:textfield></td>
					</tr>
					<tr>
						<td align="left">Prénom <span class="champObligatoire">*</span> : </td>
						<td align="left"><s:textfield name="prenom" id="prenom" maxlength="127" size="60" data-validation-engine="validate[required]"></s:textfield></td>
					</tr>
					<tr>
						<td align="left">Email <span class="champObligatoire">*</span> : </td>
						<td align="left"><s:textfield name="mail" id="mail" maxlength="255" size="60" data-validation-engine="validate[required,custom[email]]"></s:textfield></td>
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