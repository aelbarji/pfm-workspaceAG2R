<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/admin.css">
	<link rel="shortcut icon" type="image/x-icon" href="<s:property value="#session.ENSEIGNE" />/img/favicon.ico" />
	<script type="text/javascript" src="<s:property value="#session.ENSEIGNE" />/js/jscolor.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
	<script type="text/javascript" src="js/common_admin.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
		function valider(){
			document.getElementById("mainForm").submit();
		}

		function hideInfos(){
			document.getElementById("info").style.display = "none";
			document.getElementById("error").style.display = "none";
		}
		
	</script>
</head>
<body>
	<div class="error" id="error"><s:label value="%{error}" cssClass="error" id="error"></s:label></div>
	<div class="info" id="info"><s:label value="%{info}" cssClass="info" id="info"></s:label></div>
	<div class="titrePage">
		<s:label value="Couleurs dans la checklist" cssClass="title"></s:label>
	</div>
	<div class="Pagecontents" style="width: 90%;">
		<s:form theme="simple" id="mainForm" action="modifyChecklistColorAdminAction">
			<s:hidden id="selectRow" name="selectRow" value=""></s:hidden>
			<table border="1" rules="rows" cellpadding="0" cellspacing="0" width="500px" class="tabCenter">
				<thead>
					<tr>
						<th class="colTitle" width="">Statut</th>
						<th class="colTitle" width="20%">Couleur</th>
						<th class="colTitle" width="20%">1er retard</th>
						<th class="colTitle" width="20%">2ème retard</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="%{listChecklistColor.isEmpty()}">
						<tr>
							<td class="emptyListText" colspan="4">
								Aucun statut n'est d&eacute;fini pour la checklist
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listChecklistColor" status="stat">
							<s:if test="#stat.odd == true"><tr class="oddRow"></s:if>
							<s:else><tr class="evenRow"></s:else>
								<td align="left">
									&nbsp;&nbsp;<s:property value="statut.status"/>
									<input type="hidden" name="id<s:property value="#stat.index" />" value="<s:property value="id"/>"/>
								</td>
								<td align="center">
									<input onclick="hideInfos()" class="color" name="couleur<s:property value="#stat.index" />" size="8" maxlength="6" value="<s:property value="couleur"/>">
								</td>
								<td align="center">
									<input onclick="hideInfos()" class="color" name="premierRetard<s:property value="#stat.index" />" size="8" maxlength="6" value="<s:property value="retard1"/>">
								</td>
								<td align="center">
									<input onclick="hideInfos()" class="color" name="secondRetard<s:property value="#stat.index" />" size="8" maxlength="6" value="<s:property value="retard2"/>">
								</td>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4" align="center">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td align="center"><s:a href="#" onclick="javascript:valider();" cssClass="valider">VALIDER</s:a></td>
								</tr>
							</table>
						</td>
					</tr>
				</tfoot>
			</table>
		</s:form>
	</div>
</body>
</html>