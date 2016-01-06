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

	$(document).ready(function() {
		$("#mainForm").validationEngine();
	});

	function valider() {
		$("#mainForm").validationEngine('validate');
	}		
	
		function retour(){
			submitData("showURLAction.action");
		}
		
	</script>
</head>

<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
			<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('URL_ADD')" /></s:param>
	</s:include>
		<div class="contentTable" style="width:50%">
			<s:form id="mainForm" method="POST" action="createURLAction" theme="simple" enctype="multipart/form-data">
				<s:token></s:token>
				<s:hidden name="page" id="page" />
				<s:hidden name="nrPerPage" id="nrPerPage"/>
				<s:hidden name="nrPages" value="%{pagination.nrPages}" />
				
				<table class="formTable" width="500px">
					<tr>
						<td align="left" colspan="2">Veuillez entrer le nom de l'application et son adresse</td>
					</tr>
					<tr>
						<td align="left">Application <span class="starObligatoire">*</span> : </td>
						<td align="left"><s:textfield id="appli" name="appli" maxlength="25" size="45" data-validation-engine="validate[required]"/></td>
					</tr>
					<tr>
						<td align="left">Adresse <span class="starObligatoire">*</span> : </td>
						<td align="left"><s:textfield id="adresse" name="adresse" maxlength="400" size="45" data-validation-engine="validate[required]"/></td>
					</tr>
					<tr>
						<td align="left">Image <span class="starObligatoire">*</span> : </td>
						<td align="left"><s:file id="image" name="image" data-validation-engine="validate[required]"/></td>
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