<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/validationEngine.jquery.css">
	<script type="text/javascript" src="js/jquery.validationEngine.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/languages/jquery.validationEngine-fr.js" charset="utf-8"></script>

	<script type="text/javascript">
		$(document).ready(function(){
			$("#mainForm").validationEngine();
		});
	
		function valider(){
			$("#mainForm").validationEngine('validate');
		}
		function retour(){
			submitData("showCaisseAction.action");
		}
		
	</script>
</head>

<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
			<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MTG_CAI_A')" /></s:param>
	</s:include>
		<div class="contentTable" style="width:50%">
			<s:form id="mainForm" method="POST" action="createCaisseAction" theme="simple">
				<s:token></s:token>
				<s:hidden name="page" id="page" />
				<s:hidden name="nrPerPage" id="nrPerPage"/>
				<s:hidden name="nrPages" value="%{pagination.nrPages}" />
				<s:hidden name="caisseID" id="caisseID" value="" />
				
				<table class="formTable" width="500px">
					<tr>
						<td align="left" colspan="2">Veuillez entrer le nom de la caisse et son abréviation</td>
					</tr>
					<tr>
						<td align="left">Nom de la caisse <span class="starObligatoire">*</span> : </td>
						<td align="left"><s:textfield id="nomCaisseComplet" name="nomCaisseComplet" maxlength="80" size="45" data-validation-engine="validate[required]"/><br></td>
					</tr>
					<tr>
						<td align="left"> Abréviation <span class="starObligatoire">*</span> : </td>
						<td align="left"><s:textfield id="libelleCaisse" name="libelleCaisse" maxlength="40" size="45" data-validation-engine="validate[required,maxSize[11]]"/></td>
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