<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
		submitData('showAlertesDisques.action');
	}
</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre">
			<s:property value="#session.TITLE_IN_SESSION.get('ADSK_ADD')" />
		</s:param>
	</s:include>

	<div class="contentTable" style="width: 55%">
		<s:form id="mainForm" action="modifyAlertesDisques" theme="simple">
			<s:token></s:token>
			<input type="hidden" name="selectedDate"
				value="<fmt:formatDate value='${selectedDate}' pattern='dd/MM/yyyy HH:mm:ss' />"></input>
			<input type="hidden" name="dateAlerte"
				value="<fmt:formatDate value='${dateAlerte}' pattern='dd/MM/yyyy HH:mm:ss' />"></input>
			<s:hidden name="selectedID"></s:hidden>

			<table class="formTable">
				<tr>
					<td align="left">Serveur <span class="champObligatoire">*</span>
						:</td>
					<td align="left"><s:select name="systeme" list="listServer"
							listKey="id" listValue="nom" headerKey="" headerValue="" data-validation-engine="validate[required]"></s:select>
					</td>
				</tr>
				<tr>
					<td align="left">FileSystem/TableSpace <span
						class="champObligatoire">*</span> :</td>
					<td align="left"><s:textfield name="fs" id="fs" maxlength="50"
							size="55" data-validation-engine="validate[required]"></s:textfield>
					</td>
				</tr>
				<tr>
					<td align="left">Type <span class="champObligatoire">*</span>
						:</td>
					<td align="left"><s:select name="type" list="listType"
							listKey="id" listValue="type" headerKey="" headerValue="" data-validation-engine="validate[required]"></s:select>
					</td>
				</tr>
				<tr>
					<td align="left">Alerte <span class="champObligatoire">*</span>
						:</td>
					<td align="left"><s:textfield name="alerte" id="alerte"
							maxlength="3" size="5"
							data-validation-engine="validate[required, custom[onlyNumberSp],min[0], max[100]]" />
					</td>
				</tr>
				<tr>
					<td align="left">Seuil <span class="champObligatoire">*</span>
						:</td>
					<td align="left"><s:textfield name="seuil" id="seuil"
							maxlength="3" size="5"
							data-validation-engine="validate[required, custom[onlyNumberSp],min[0], max[100]]"/>
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