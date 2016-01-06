<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function valider(){
			var mainForm = document.getElementById("mainForm");
			if(mainForm.nom.value.replace(/^\s+/g,'').replace(/\s+$/g,'') == 0){
				alert("Veuillez entrer un nom");
				mainForm.nom.focus();
				return false;
			}
			else if(mainForm.prenom.value.replace(/^\s+/g,'').replace(/\s+$/g,'') == 0){
				alert("Veuillez entrer un prenom");
				mainForm.prenom.focus();
				return false;
			}
			else if(mainForm.email.value.replace(/^\s+/g,'').replace(/\s+$/g,'') == 0){
				alert("Veuillez entrer un email");
				mainForm.email.focus();
				return false;
			}

			else if(!checkOldPassword(mainForm)){
				return false;
			}
			
			mainForm.submit();
		}

		function checkOldPassword(mainForm){

			if (mainForm.oldPassword.value.length != 0){
				if (mainForm.newPassword.value.length == 0){
					alert("Veuillez entrer votre nouveau mot de passe");
					mainForm.newPassword.focus();
					return false;
				}
				else{
					if (mainForm.newPassword.value != mainForm.confirmPassword.value){
						alert("Les 2 champs de nouveau mot de passe ne correspondent pas. Veuillez les entrer à nouveau.");
						mainForm.newPassword.value = "";
						mainForm.confirmPassword.value = "";
						mainForm.newPassword.focus();
						return false;
					}
					else{
						return true;
					}
				}
			}
			else if(mainForm.newPassword.value.length > 0 || mainForm.confirmPassword.value.length > 0){
				alert("Veuillez entrer votre mot de passe actuel pour le modifier");
				mainForm.oldPassword.focus();
				return false;
			}
			else{
				return true;
			}
		}
		
		function retour(){
			submitData('redirectUserProfilAction.action');
		}
		
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('USR_PROF')" /></s:param>
	</s:include>
	
	<div class="contentTable" style="width: 40%">
		<s:form id="mainForm" theme="simple" action="modifyUserProfilAction" method="POST">
			<s:token></s:token>
			<s:hidden name="userId"/>
			
			<table class="formTable">
				<tr>
					<td align="left">Nom <span class="starObligatoire">*</span> :</td>
					<td align="left"><s:textfield name="nom" maxlength="30" size="35" onkeydown="submitOnKeyPress(event);"/></td>
				</tr>
				<tr>
					<td align="left">Prénom <span class="starObligatoire">*</span> :</td>
					<td align="left"><s:textfield name="prenom" maxlength="30" size="35" onkeydown="submitOnKeyPress(event);"/></td>
				</tr>
				<tr>
					<td align="left">E-mail <span class="starObligatoire">*</span> :</td>
					<td align="left"><s:textfield name="email" size="35" onkeydown="submitOnKeyPress(event);"/></td>
				</tr>
				<tr>
					<td align="left">Mot de passe actuel :</td>
					<td align="left"><s:password name="oldPassword" theme="simple" size="40" onkeydown="submitOnKeyPress(event);"></s:password></td>
				</tr>
				<tr>
					<td align="left">Nouveau mot de passe :</td>
					<td align="left"><s:password name="newPassword" theme="simple" size="40" onkeydown="submitOnKeyPress(event);"></s:password></td>
				</tr>
				<tr>
					<td align="left">Confirmez le nouveau mot de passe :</td>
					<td align="left"><s:password name="confirmPassword" theme="simple" size="40" onkeydown="submitOnKeyPress(event);"></s:password></td>
				</tr>
				<tr>
					<td colspan="2">
						<s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom.jsp" />
					</td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>