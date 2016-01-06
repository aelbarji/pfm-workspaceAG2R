<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/validationEngine.jquery.css">
	<script type="text/javascript" src="js/jquery.validationEngine.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/languages/jquery.validationEngine-fr.js" charset="utf-8"></script>
	<script type="text/javascript">

	$(document).ready(function(){
		$("#mainForm").validationEngine();
	});

	function valider() {
		$("#mainForm").validationEngine('validate');
	}

		function retour(){
			submitData("showMachineInterlocuteurAction.action");
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MAC_SGE_C')" /></s:param>
	</s:include>
	<div class="contentTable">
		<s:form id="mainForm" theme="simple" action="createMachineInterlocuteurAction" method="POST">
			<s:hidden name="sort" id="sort" />
			<s:hidden name="sens" id="sens" />
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage" />
			<s:hidden name="nrPages" id="nrPages" />
			<s:token></s:token>
			<s:hidden name="filtreNom" id="filtreNom" />
			<s:hidden name="filtreNomComplet" id="filtreNomComplet" />
			<s:hidden name="filtreResponsable" id="filtreResponsable" />
			<s:hidden name="filtreBAI" id="filtreBAI" />
			
			<table class="formTable">
				<tr>
					<td align="left">Nom <span class="starObligatoire">*</span> :</td>
					<td align="left"><s:textfield name="libelle" size="60" data-validation-engine="validate[required]"/></td>
				</tr>
				<tr>
					<td align="left">Nom complet<span class="starObligatoire">*</span> :</td>
					<td align="left"><s:textfield name="nomComplet" size="60" data-validation-engine="validate[required]"/></td>
				</tr>
				<tr>
					<td align="left">Responsable <span class="starObligatoire">*</span> :</td>
					<td align="left"><s:select list="%{listUsers}" listKey="id" listValue="nom + ' ' + prenom" name="idResponsable" headerKey="" headerValue="" data-validation-engine="validate[required]"/></td>
				</tr>
				<tr>
					<td align="left">BAL :</td>
					<td align="left"><s:textfield name="balService" size="60" data-validation-engine="validate[custom[email]]"/></td>
				</tr>
				<tr>
					<td colspan="2"><s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom_submit.jsp" /></td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>