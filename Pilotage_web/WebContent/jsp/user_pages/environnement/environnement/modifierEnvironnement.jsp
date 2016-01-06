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
			submitData('showEnvironnementAction.action');
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('ENV_MOD')" /></s:param>
	</s:include>
	
	<div class="contentTable" style="width: 40%">
		<s:form id="mainForm" theme="simple" action="modifierEnvironnementAction" method="POST">
			<s:token></s:token>
			<s:hidden name="selectRow"/>
			<s:hidden name="libelleChanged" value="false"/>
			<s:hidden name="sens"/>
			<s:hidden name="sort"/>
			<s:hidden name="page"/>
			<s:hidden name="nrPages"/>
			<s:hidden name="nrPerPage"/>
			<s:hidden name="filtreEnvironnement"/>
			<s:hidden name="filtreType"/>
			
			<table class="formTable">
				<tr>
					<td align="left" colspan="2">Veuillez entrer les nouvelles informations de l'environnement</td>
				</tr>
				<tr>
					<td align="left">Environnement <span class="starObligatoire">*</span> : </td>
					<td align="left">
						<s:textfield name="libelle" size="50" maxlength="80" onkeydown="submitOnKeyPress(event);" data-validation-engine="validate[required]"> </s:textfield>
					</td>
				</tr>
				<tr>
					<td align="left">Type <span class="starObligatoire">*</span> : </td>
					<td align="left">
						<s:select list="%{environnement_Types}" listKey="id" listValue="type" name="type" onkeydown="submitOnKeyPress(event);" data-validation-engine="validate[required]"/>
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