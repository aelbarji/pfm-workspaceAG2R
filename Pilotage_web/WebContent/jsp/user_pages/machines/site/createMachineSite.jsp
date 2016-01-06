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

//fonction de validation du formulaire
	$(document).ready(function(){
		$("#mainForm").validationEngine();
		});

		function valider(){
			$("#mainForm").validateEngine('validate');
			}

		function retour(){
			submitData("showMachineSiteAction.action");
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre">
			<s:property value="#session.TITLE_IN_SESSION.get('MAC_SIT_C')" />
		</s:param>
	</s:include>

	<div class="contentTable">
		<s:form id="mainForm" theme="simple" action="createMachineSiteAction"
			method="POST">
			<s:hidden name="sort" id="sort" />
			<s:hidden name="sens" id="sens" />
			<s:token></s:token>
			<s:hidden name="filtreSite" id="filtreSite" />
			<s:hidden name="filtreAdresse" id="filtreAdresse" />
			<s:hidden name="filtreCP" id="filtreCP" />

			<table class="formTable">
				<tr>
					<td align="left">Site <span class="starObligatoire">*</span> :</td>
					<td align="left"><s:textfield id="libelle" name="libelle"
							maxlength="45" size="50"
							data-validation-engine="validate[required]" />
					</td>
				</tr>
				<tr>
					<td align="left">Adresse <span class="starObligatoire">*</span>
						:</td>
					<td align="left"><s:textfield id="adresse1" name="adresse1"
							maxlength="30" size="40" data-validation-engine="validate[required]"/>
					</td>
				</tr>
				<tr>
					<td></td>
					<td align="left"><s:textfield id="adresse2" name="adresse2"
							maxlength="30" size="40" />
					</td>
				</tr>
				<tr>
					<td align="left">Code postal <span class="starObligatoire">*</span>
						:</td>
					<td align="left"><s:textfield id="cp" name="cp" maxlength="5"
							size="10" data-validation-engine="validate[required,custom[onlyNumberSp],minSize[5]]"/>
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