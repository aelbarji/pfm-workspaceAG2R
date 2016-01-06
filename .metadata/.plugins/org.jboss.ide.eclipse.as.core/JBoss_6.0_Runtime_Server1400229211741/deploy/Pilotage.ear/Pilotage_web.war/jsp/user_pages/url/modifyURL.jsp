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
			submitData("showURLAction.action");
		}
		
	</script>
</head>

<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
			<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('URL_MOD')" /></s:param>
	</s:include>
		<div class="contentTable" style="width:50%">
			<s:form id="mainForm" method="POST" action="modifyURLAction" theme="simple" enctype="multipart/form-data">
				<s:token></s:token>
				<s:hidden name="urlID" id="urlID" value="%{url.id}"/>
				
				<table>
					<tr>
						<td>Application <span class="starObligatoire">*</span> : </td>
						<td><s:textfield id="appli" name="appli" maxlength="25" size="45" value="%{url.application}" data-validation-engine="validate[required]"/></td>
					</tr>
					<tr>
						<td align="left">Adresse <span class="starObligatoire">*</span> : </td>
						<td align="left"><s:textfield id="adresse" name="adresse" maxlength="400" size="70" value="%{url.adresse}" data-validation-engine="validate[required]"/></td>
					</tr>
					<tr>
						<td align="left">Image <span class="starObligatoire">*</span> : </td>
						<td align="left"><img id="img" src='getImageFavoriAction.action?urlID=<s:property value="%{url.id}"/>' /><br><s:file id="image" name="image" /></td>
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