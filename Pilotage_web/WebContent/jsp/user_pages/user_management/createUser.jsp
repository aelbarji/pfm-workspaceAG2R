<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
		$("#mainForm").validationEngine('validate');
	}

	function retour() {
		submitData('listAllUsersAction.action');
	}
</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre">
			<s:property value="#session.TITLE_IN_SESSION.get('USR_ADD')" />
		</s:param>
	</s:include>

	<div class="contentTable" style="width: 35%">
		<s:form id="mainForm" theme="simple" action="createUserAction"
			method="POST">
			<s:token></s:token>
			<s:hidden name="sort" id="sort" />
			<s:hidden name="sens" id="sens" />
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage" />
			<s:hidden name="nrPages" id="nrPages" />

			<s:hidden name="filtreNom" id="filtreNom" />
			<s:hidden name="filtreLogin" id="filtreLogin" />
			<s:hidden name="filtreProfil" id="filtreProfil" />
			<s:hidden name="filtrePrenom" id="filtrePrenom" />
			<s:hidden name="filtreEmail" id="filtreEmail" />

			<table class="formTable">
				<tr>
					<td align="left">Nom <span class="starObligatoire">*</span> :</td>
					<td align="left"><s:textfield name="nom" maxlength="30"
							size="35" onkeydown="submitOnKeyPress(event);" data-validation-engine="validate[required]"/>
					</td>
				</tr>
				<tr>
					<td align="left">Prénom <span class="starObligatoire">*</span>
						:</td>
					<td align="left"><s:textfield name="prenom" maxlength="30"
							size="35" onkeydown="submitOnKeyPress(event);" data-validation-engine="validate[required]"/>
					</td>
				</tr>
				<tr>
					<td align="left">Login <span class="starObligatoire">*</span>
						:</td>
					<td align="left"><s:textfield name="login" maxlength="30"
							size="35" onkeydown="submitOnKeyPress(event);" data-validation-engine="validate[required]"/>
					</td>
				</tr>
				<tr>
					<td align="left">E-mail <span class="starObligatoire">*</span>
						:</td>
					<td align="left"><s:textfield name="email" size="35"
							onkeydown="submitOnKeyPress(event);" data-validation-engine="validate[required,custom[email]]"/>
					</td>
				</tr>
				<tr>
					<td align="left">Profil <span class="starObligatoire">*</span>
						:</td>
					<td align="left"><s:select list="%{listProfil}" listKey="id"
							listValue="libelle" key="%{profil}" name="profil"
							emptyOption="true" onkeydown="submitOnKeyPress(event);" data-validation-engine="validate[required]"/></td>
				</tr>
				<tr>
					<td align="left">Mot de passe <span class="starObligatoire">*</span>
						:</td>
					<td align="left"><s:password name="password" id="password" maxlength="64"
							size="35" onkeydown="submitOnKeyPress(event);" data-validation-engine="validate[required]"/>
					</td>
				</tr>
				<tr>
					<td align="left">Confirmer <span class="starObligatoire">*</span>
						:</td>
					<td align="left"><s:password name="cpassword" maxlength="64"
							size="35" onkeydown="submitOnKeyPress(event);" data-validation-engine="validate[required,equals[password]]"/>
					</td>
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