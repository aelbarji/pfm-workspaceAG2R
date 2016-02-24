<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:include value="/jsp/user_pages/commun/html_head.jsp" />

<script>
	window.pressed = function() {
		var a = document.getElementById('myFileInput');
		if (a.value == "") {
			fileLabel.innerHTML = "Choisir un fichier";
		} else {
			var theSplit = a.value.split('\\');
			fileLabel.innerHTML = theSplit[theSplit.length - 1];
		}
	};
</script>

<style>
input[type=file] {
	width: 90px;
	color: transparent;
	display: none;
}
</style>
</head>

<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre">
			<s:property value="#session.TITLE_IN_SESSION.get('CHM_LST')" />
		</s:param>
	</s:include>

	<div class="contentTable">

		<form action="readExcelFile" method="POST"
			enctype="multipart/form-data">
			<button type="button" id="button"
				onclick="$('#myFileInput').trigger('click');">Choisir un
				fichier</button>
			<input id="myFileInput" type="file" name="myFile" size="20"
				onchange="pressed()" /> <label id="fileLabel">Aucun fichier
				sélectionné</label> <input type="submit" value="Uploader" />
		</form>
		<br>

		<s:form id="mainForm" theme="simple" action="showChangements">

			<s:token></s:token>
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage" />
			<s:hidden name="nrPages" value="%{pagination.nrPages}" />
			<s:hidden name="selectedID" id="selectedID" />

			<table class="dataTable" rules="all">
				<col width="25%" />
				<col width="8%" />
				<col width="8%" />
				<col width="8%" />
				<col width="8%" />
				<col width="8%" />
				<col width="8%" />

				<thead>
					<tr>
						<td colspan="7"><s:include
								value="/jsp/user_pages/pagination/pagination.jsp" /></td>
					</tr>
					<tr valign="middle">
						<th class="titreColonne">R&eacute;sum&eacute;</th>
						<th class="titreColonne">Priorit&eacute;</th>
						<th class="titreColonne">Etat</th>
						<th class="titreColonne">Date d&eacute;but</th>
						<th class="titreColonne">Date fin</th>
						<th class="titreColonne">Demandeur</th>
						<th class="titreColonne">Valideur</th>
					</tr>
				</thead>

				<tbody>
					<s:if test="%{listeChangements.isEmpty()}">
						<tr>
							<td colspan="7" class="emptyListText">Aucun changement
								trouvé</td>
						</tr>
					</s:if>

					<s:else>
						<s:iterator value="listeChangements" status="stuts">
							<tr>
								<td align="left">&nbsp;&nbsp;<s:property value="%{resume}" /></td>
								<td align="center"><s:property value="%{priorite}" /></td>
								<td align="center"><s:property value="%{etat}" /></td>
								<td align="center"><s:date name="%{dateDebut}"
										format="dd/MM/yyyy" /></td>
								<td align="center"><s:date name="%{dateFin}"
										format="dd/MM/yyyy" /></td>
								<td align="center"><s:property value="%{demandeur}" /></td>
								<td align="center"><s:property value="%{valideur}" /></td>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="7"><s:include
								value="/jsp/user_pages/pagination/pagination.jsp" /></td>
					</tr>
				</tfoot>
			</table>
		</s:form>
	</div>
</body>
</html>