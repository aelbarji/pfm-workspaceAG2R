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
			submitData('showAstreinteObligatoireAction.action');
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('AST_O_MOD')" /></s:param>
	</s:include>
	
	<div class="contentTable" style="width: 40%">
		<s:form id="mainForm" theme="simple" action="modifyAstreinteObligatoireAction" method="POST">
			<s:hidden name="domaineTypeChanged" />
			<s:hidden name="indicChanged"/>
			<s:hidden name="selectRow" id="selectRow" />
			<s:token></s:token>
			<s:hidden name="filtreDomaine"/>
			<s:hidden name="filtreType"/>
			<s:hidden name="filtreIndicEnvoi"/>
			<s:hidden name="sort"/>
			<s:hidden name="sens"/>
			
			<table class="formTable">
				<tr>
					<td align="left" colspan="2">Veuillez entrer les informations de l'astreinte obligatoire</td>
				</tr>
				<tr>
					<td align="left">Domaine <span class="starObligatoire">*</span> : </td>
					<td align="left">
						<s:select list="%{aDomaines}" listKey="id" listValue="domaine" name="domaine" data-validation-engine="validate[required]"/>
					</td>
				</tr>
				<tr>
					<td align="left">Type <span class="starObligatoire">*</span> : </td>
					<td align="left"><s:select list="%{aTypes}" listKey="id" listValue="type" name="type" data-validation-engine="validate[required]"/></td>
				</tr>
				<tr>
					<td align="left">Indic_envoi :</td>
					<td align="left"><s:checkbox name="indicEnvoi" ></s:checkbox></td>
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