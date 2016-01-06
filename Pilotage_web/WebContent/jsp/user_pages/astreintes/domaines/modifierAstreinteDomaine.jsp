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
			var mainForm = document.getElementById("mainForm");
			if(mainForm.astreinteDomaine.value.replace(/^\s+/g,'').replace(/\s+$/g,'') != '<s:property value="%{astreinteDomaine}" />'){
				mainForm.libelleChanged.value = 'true';
			}
			$("#mainForm").validationEngine('validate');
		}

		function retour(){
			submitData('showAstreinteDomaineAction.action');
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('AST_D_MOD')" /></s:param>
	</s:include>
	
	<div class="contentTable" style="width: 40%">
		<s:form id="mainForm" theme="simple" action="modifyAstreinteDomaineAction" method="POST">
			<s:hidden name="domaineID" id="domaineID"/>
			<s:hidden name="libelleChanged" value="false"/>
			<s:token></s:token>
			<table class="formTable">
				<tr>
					<td align="left" colspan="2">Veuillez entrer le libellé du type d'astreinte</td>
				</tr>
				<tr>
					<td align="left">Libellé <span class="champObligatoire">*</span> :</td>
					<td align="left"><s:textfield name="astreinteDomaine" maxlength="50" size="50" data-validation-engine="validate[required]"/></td>
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