<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
	
		function soumettre(id, adresse){
			changeData('selectedID', id);
			submitData(adresse);
		}		

		function ajouter() {
			reinitPageValues();
			submitData("redirectCreateIncidentsGup.action");
		}

		//reinitialisation de page comme à l'entrée de la page
		function reinitPageValues() {
			document.getElementById("mainForm").page.value = '<s:property value="page" escape="false"/>';
			document.getElementById("mainForm").nrPerPage.value = '<s:property value="nrPerPage" escape="false"/>';
			document.getElementById("mainForm").nrPages.value = '<s:property value="nrPages" escape="false"/>';
		}

		function modify(id){		
			changeData('selectedID', id);
			submitData('redirectModifyIncidentsGup.action');	
		}

		function detail(idInc){
			changeData("selectedID", idInc);
			submitData('detailGestionIncident.action');
		}

		function supprimer(id){
			if(confirm("Etes vous sur de vouloir supprimer l'incident ?")){
				changeData('selectedID', id);
				submitData('deleteIncidentsGup.action');
			}
		}
		
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('ICD_LST')" /></s:param>
	</s:include>
	
	<div class="contentTable" >
		<s:form id="mainForm" theme="simple" action="ShowIncidentsGup">
		<s:token></s:token>
			<s:hidden name="selectedID" id="selectedID"/>
			<s:hidden name="provenance" id="provenance"/>
			<s:hidden name="idInc" id="idInc"/>
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage"/>
			<s:hidden name="nrPages" value="%{pagination.nrPages}" />
			<s:if test="#session.USER_DROITS.contains('ICD_GUP_ADD')">
				<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
				<b> En cours </b> <span style="font-weight:100">/  Cloturé</span>
				<br/>
			</s:if>
			<table class="dataTable" rules="all">
				<col width="3%" />
				<col width="6%"/>
				<col width="8%" />
				<col width="9%" />
				<col width="9%" />
				<col width="9%" />
				<col width="9%" />
				<col width="8%" />
				<col width="6%" />
				<col width="25%" />
				<col width="8%" />
				<s:if test="#session.USER_DROITS.contains('ICD_GUP_MOD')">
					<col width="5%" />
				</s:if>
				<thead>
					<tr>
						<td colspan="11">
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
					<tr>
						<th class="titreColonne">Ref</th>
						<th class="titreColonne">Pilote</th>
						<th class="titreColonne">Date et heure</th>
						<th class="titreColonne">Domaine</th>
						<th class="titreColonne">Service</th>
						<th class="titreColonne">Applicatif</th>
						<th class="titreColonne">Détection</th>
						<th class="titreColonne">Etat service</th>
						<th class="titreColonne">Impact utilisateur</th>
						<th class="titreColonne">Description</th>
						<th class="titreColonne">Actions</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="listIncident.isEmpty()">
						<tr>
							<td colspan="11" class="emptyListText">
								Aucun incident en cours
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listIncident" status="status">
						<s:if test="date_fin == null"> <tr class="incidentEnCours"> </s:if>
								<s:else><tr></s:else>
								<td align="center" onclick="javascript:soumettre('<s:property value="%{id}"/>','redirectModifyIncidentsGup.action');"><s:property value="%{id}"/></td>
								<td align="center" onclick="javascript:soumettre('<s:property value="%{id}"/>','redirectModifyIncidentsGup.action');"><s:property value="%{user.prenom}"/>&nbsp;<s:property value="%{user.nom}"/></td>
								<td align="center" onclick="javascript:soumettre('<s:property value="%{id}"/>','redirectModifyIncidentsGup.action');"><s:property value="%{date[#status.index]}"/></td>
								
								<td align="center" onclick="javascript:soumettre('<s:property value="%{id}"/>','redirectModifyIncidentsGup.action');"><s:property value="%{domaine[#status.index]}"/></td>
								<td align="center" onclick="javascript:soumettre('<s:property value="%{id}"/>','redirectModifyIncidentsGup.action');"><s:property value="%{service[#status.index]}"/></td>
								<td align="center" onclick="javascript:soumettre('<s:property value="%{id}"/>','redirectModifyIncidentsGup.action');"><s:property value="%{appli[#status.index]}"/></td>
								<td align="center" onclick="javascript:soumettre('<s:property value="%{id}"/>','redirectModifyIncidentsGup.action');"><s:property value="%{detection[#status.index]}"/></td>
								
								<td align="center" onclick="javascript:soumettre('<s:property value="%{id}"/>','redirectModifyIncidentsGup.action');"><s:property value="%{etat.libelle}"/></td>
								<td align="center" onclick="javascript:soumettre('<s:property value="%{id}"/>','redirectModifyIncidentsGup.action');">
									<s:if test="impact == 0">Non</s:if>
									<s:else>Oui</s:else>
								<td align="center" onclick="javascript:soumettre('<s:property value="%{id}"/>','redirectModifyIncidentsGup.action');"><s:property value="%{description_prob}"/></td>
								
								<td align="center">
									<s:if test="#session.USER_DROITS.contains('ICD_GUP_MOD')">
										<s:a href="#" onclick="javascript:modify('%{id}');">
											<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
										</s:a>
									</s:if>	
									<s:if test="date_fin != null">
										<s:a href="#" onclick="javascript:soumettre('%{id}','reinitialiserIncidentGup.action');">
											<img class="icone" alt="Réinitialiser un incident" title="Réinitialiser un incident" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" />
										</s:a>
									</s:if>	
									<s:a href="#" onclick="javascript:soumettre('%{id}','detailIncidentGup.action');">
											<img class="icone" alt="Détail" title="Détail" src="<s:property value="#session.ENSEIGNE" />/img/detail-16.png">
									</s:a>
									<s:if test="#session.USER_DROITS.contains('ICD_GUP_DEL')">
									<s:a href="#" onclick="javascript:supprimer('%{id}');">
											<img class="icone"  alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
									</s:a>
								</s:if>
								</td>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="11">
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
				</tfoot>
			</table>
		</s:form>
	</div>
</body>
</html>
