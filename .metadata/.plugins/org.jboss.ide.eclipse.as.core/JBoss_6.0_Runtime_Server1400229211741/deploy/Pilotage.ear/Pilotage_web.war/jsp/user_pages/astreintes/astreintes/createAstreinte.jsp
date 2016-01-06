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

		// fonction de validation du formulaire
		$(document).ready(function(){
	    	$("#mainForm").validationEngine();
	  	});
	
		function valider(){
			$("#mainForm").validationEngine('validate');
		}

		function retour(){
			submitData('showAstreintesAction.action');
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('AST_ADD')" /></s:param>
	</s:include>
	
	<div class="contentTable">
		<s:form id="mainForm" theme="simple" action="createAstreinteAction" method="POST">
			<s:hidden name="sort" id="sort" />
			<s:hidden name="sens" id="sens" />
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage"/>
			<s:hidden name="nrPages" id="nrPages" />
			<s:token></s:token>
			<s:hidden name="filtreNom" id="filtreNom" />
			<s:hidden name="filtrePrenom" id="filtrePrenom" />
			<s:hidden name="filtreTel1" id="filtreTel1" />
			<s:hidden name="filtreTel2" id="filtreTel2" />
			<s:hidden name="filtreType" id="filtreType" />
			
			<table class="formTable">
				<tr>
					<td align="left" colspan="2">Veuillez entrer les informations de la nouvelle astreinte</td>
				</tr>
				<tr>
					<td align="left">Nom <span class="starObligatoire">*</span> :</td>
					<td align="left"><s:textfield name="nom" maxlength="20" size="35" data-validation-engine="validate[required]"/></td>
				</tr>
				<tr>
					<td align="left">Prénom <span class="starObligatoire">*</span> :</td>
					<td align="left"><s:textfield name="prenom" maxlength="20" size="35"  data-validation-engine="validate[required]"/></td>
				</tr>
				<tr>
					<td align="left">Téléphone 1 <span class="starObligatoire">*</span> :</td>
					<td align="left"><s:textfield name="tel1" id="tel1" maxlength="20" size="35" data-validation-engine="validate[required,custom[phone]]"/></td>
				</tr>
				<tr>
					<td align="left">Téléphone 2 :</td>
					<td align="left"><s:textfield name="tel2" id="tel2" maxlength="20" size="35" data-validation-engine="validate[custom[phone]]"/></td>
				</tr>
				<tr>
					<td align="left">Type <span class="starObligatoire">*</span> :</td>
					<td align="left">
						<s:select list="%{aTypes}" listKey="id" listValue="type" name="type" headerKey="-1" headerValue=""  data-validation-engine="validate[required]"/>
					</td>
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