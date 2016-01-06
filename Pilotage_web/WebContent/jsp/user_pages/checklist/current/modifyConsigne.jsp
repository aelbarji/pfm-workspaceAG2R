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
	<title><s:property value="#session.TITLE_IN_SESSION.get('CKL_CSG')" /></title>
</head>
<body class="questionBorder">
	<script type="text/javascript">
		function closeDiag() {
			window.close();
		}

		function valider() {
			document.getElementById("mainForm").submit();
		}
	</script>
	<s:if test="%{status == 0}">
		<s:form id="mainForm" action="modifyChecklistConsigneAction" theme="simple">
			<s:hidden name="tacheID"/>
			<table class="questionTable">
				<tr>
					<td class="error" id="error"><s:property value="%{error}" /></td>
				</tr>
				<tr>
					<td align="left">Tache :</td>
				</tr>
				<tr>
					<td align="center"><s:property value="%{libelleTache}"/></td>
				</tr>
				<tr>
					<td align="left">Consigne : </td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<s:textarea name="consigne" cols="30" rows="15"></s:textarea>
					</td>
				</tr>
				<tr>
					<td>
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
				<td class="info"><s:property value="%{info}"/></td>
			</tr>
			<tr>
				<td class="error"><s:property value="%{error}"/></td>
			</tr>
			<tr>
				<td align="center"><s:a href="#" onclick="javascript:closeDiag()" cssClass="boutonPopupFermer">Fermer</s:a></td>
			</tr>
		</table>
	</s:elseif>
</body>
</html>