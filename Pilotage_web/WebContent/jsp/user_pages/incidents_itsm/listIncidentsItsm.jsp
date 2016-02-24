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
			<s:property value="#session.TITLE_IN_SESSION.get('ICD_LST')" />
		</s:param>
	</s:include>

	<div class="contentTable">

		<form action="importExcelFile" method="POST"
			enctype="multipart/form-data">
			<button type="button" id="button"
				onclick="$('#myFileInput').trigger('click');">Choisir un
				fichier</button>
			<input id="myFileInput" type="file" name="myFile"
				size="20" onchange="pressed()" /> <label id="fileLabel">Aucun
				fichier sélectionné</label> 
			<input type="submit" value="Uploader" />
		</form> 
		<br>

		<s:form id="mainForm" theme="simple" action="showIncidentsItsm">

			<s:token></s:token>
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage" />
			<s:hidden name="nrPages" value="%{pagination.nrPages}" />
			<s:hidden name="selectedID" id="selectedID" />

			<table class="dataTable" rules="all">
				<col width="8%" />
				<col width="8%" />
				<col width="8%" />
				<col width="8%" />
				<col width="8%" />
				<col width="8%" />
				<col width="8%" />
				<col width="8%" />
				<col width="25%" />
				
				<thead>
					<tr>
						<td colspan="9"><s:include
								value="/jsp/user_pages/pagination/pagination.jsp" /></td>
					</tr>
					<tr valign="middle">
						<th class="titreColonne">Id de l'incident</th>
						<th class="titreColonne">Date de cr&eacute;ation</th>
						<th class="titreColonne">Date de derni&eacute;re modification</th>
						<th class="titreColonne">Etat</th>
						<th class="titreColonne">Priorit&eacute;</th>
						<th class="titreColonne">Urgence</th>
						<th class="titreColonne">Impact</th>
						<th class="titreColonne">Nb relance</th>
						<th class="titreColonne">R&eacute;sum&eacute;</th>
						
					</tr>
				</thead>

				<tbody>
					<s:if test="%{listeIncidents.isEmpty()}">
						<tr>
							<td colspan="9" class="emptyListText">Aucun incident
								trouvé</td>
						</tr>
					</s:if>

					<s:else>
						<s:iterator value="listeIncidents" status="stuts">
							<tr>
								<td align="left">&nbsp;&nbsp;<s:property value="%{idRequete}" /></td>
								<td align="center"><s:date name="%{dateCreation}"
										format="dd/MM/yyyy" /></td>
								<td align="center"><s:date name="%{dateModification}"
										format="dd/MM/yyyy" /></td>
								<td align="center"><s:property value="%{etat}" /></td>
								<td align="center"><s:property value="%{priorite}" /></td>
								<td align="center"><s:property value="%{urgence}" /></td>
								<td align="center"><s:property value="%{impact}" /></td>
								<td align="center"><s:property value="%{nbRelance}" /></td>
								<td align="left">&nbsp;&nbsp;<s:property value="%{resume}" /></td>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="9"><s:include
								value="/jsp/user_pages/pagination/pagination.jsp" /></td>
					</tr>
				</tfoot>
			</table>
		</s:form>
	</div>
</body>
</html>