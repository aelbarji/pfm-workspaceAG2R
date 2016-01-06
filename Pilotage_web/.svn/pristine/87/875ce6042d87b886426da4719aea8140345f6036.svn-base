<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base target="_self">
	<link rel="shortcut icon" type="image/x-icon" href="<s:property value="#session.ENSEIGNE" />/img/favicon.ico" />
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/pilotage.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv='Expires' content='-10'>
	<meta http-equiv='Pragma' content='No-cache'>
	<meta http-equiv='Cache-Control' content='private'>
	<title><s:property value="#session.TITLE_IN_SESSION.get('CSG_REP')" /></title>
	<script type="text/javascript">
		function closeDiag() {
			window.close();
		}

		function valider() {
			var form = document.getElementById("mainForm");

			if (form.response.value.replace(/^\s+/g,'').replace(/\s+$/g,'') == "") 
				alert("Veuillez entrer une réponse.");
			else 
				form.submit();
		}
	</script>
</head>
<body class="questionBorder">
	<s:if test="%{status == 0}">
		<s:form id="mainForm" action="responseQuestionAction" theme="simple">
			<s:hidden name="questionID"></s:hidden>
			<s:hidden name="piloteID"></s:hidden>
			<table class="questionTable">
				<tr>
					<td class="errorpopup" colspan="2"><s:property value="%{error}"/></td>
				</tr>
				<tr>
					<td align="left">Pilote : </td>
					<td align="left"><s:property value="%{piloteName}"/></td>
				</tr>
				<tr>
					<td align="left">Question : </td>
					<td align="left">
						<s:textarea name="question" readonly="true" cols="25" rows="3"></s:textarea>
					</td>
				</tr>
				
				<tr>
					<td align="left" colspan="2">R&eacute;ponse : </td>
				</tr>
				<tr>
					<td valign="top" align="center" colspan="2">
						<s:textarea name="response" cols="30" rows="15"></s:textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td><s:a href="#" onclick="javascript:closeDiag()" cssClass="boutonPopupRetour">Retour</s:a></td>
								<td>&nbsp;</td>
								<td><s:a href="#" onclick="javascript:valider();" cssClass="boutonPopupValider">Valider</s:a></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</s:form>
	</s:if>
	<s:elseif test="%{status==1}">
		<table  class="questionTable">
			<tr>
				<td class="infopopup"><s:property value="%{info}"/></td>
			</tr>
			<tr>
				<td align="center"><s:a href="#" onclick="javascript:closeDiag()" cssClass="boutonPopupFermer">Fermer</s:a></td>
			</tr>
		</table>
	</s:elseif>	
</body>
</html>