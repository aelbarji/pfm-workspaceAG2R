<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function soumettre(id, adresse){
			reinitFilterValues();
			reinitPageValues();
			document.getElementById("mainForm").typeSelected.value = '<s:property value="typeSelected" escape="false"/>';
			changeData('selectedID', id);
			submitData(adresse);
		}		

		function ajouter() {
			reinitFilterValues();
			reinitPageValues();
			document.getElementById("mainForm").typeSelected.value = '<s:property value="typeSelected" escape="false"/>';
			submitData("redirectCreateIncident.action");
		}
		
		function changeFiltre(a) {
			document.getElementById("mainForm").filtreIncidents.value = a;
			//document.getElementById("mainForm").typeSelected.value = '<s:property value="typeSelected" escape="false"/>';
			//reinitPageValues();
			document.getElementById("mainForm").submit();
		}
		//reinitialisation des filtres comme à l'entrée de la page
		function reinitFilterValues() {
			document.getElementById("mainForm").filtreIncidents.value = '<s:property value="filtreIncidents" escape="false"/>';
		}

		//reinitialisation de page comme à l'entrée de la page
		function reinitPageValues() {
			document.getElementById("mainForm").pageIncident.value = '<s:property value="pageIncident" escape="false"/>';
			document.getElementById("mainForm").nrPerPageIncident.value = '<s:property value="nrPerPageIncident" escape="false"/>';
			document.getElementById("mainForm").nrPagesIncident.value = '<s:property value="nrPagesIncident" escape="false"/>';
		}

	    //ouvrir dans un autre onglet	
		function newTab(id,destina){			
			var form =document.getElementById("mainForm");
			form.target = "_blank";
			changeData('selectedID', id);
			submitData(destina);
			form.target = "_self";		
		}

		function modify(id){		
			var form =document.getElementById("mainForm");
			form.target = "_blank";
			changeData('selectedID', id);
			submitData('redirectModifyIncident.action');
			form.target = "_self";		
		}

		function redirectAstreinte(id, red){
			document.getElementById("idInc").value = id;
			document.getElementById("validAst").value = red; // affection de la valeur ici 1: ajouter, 2 : voir astreintes
			if(red == "1"){
				submitData("redirectCreateAppelAstreinteAction.action");
			}
			if(red == "2"){
				submitData("showAppelAstreinteAction.action");
			}
		}

		function detail(idInc){
			changeData("selectedID", idInc);
			submitData('detailGestionIncident.action');
		}
		
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('ICD_LST')" /></s:param>
	</s:include>
	
	<div class="contentTable" >
		<s:form id="mainForm" theme="simple" action="showIncidents">
		<s:token></s:token>
			<s:hidden name="provenance" id="provenance"/>
			<s:hidden name="selectedID" id="selectedID"/>
			<s:hidden name="idInc" id="idInc"/>
			<s:hidden name="validAst" id="validAst"/>
			<s:hidden name="pageIncident" id="pageIncident" />
			<s:hidden name="nrPerPageIncident" id="nrPerPageIncident"/>
			<s:hidden name="nrPagesIncident" value="%{paginationIncident.nrPages}" />
			
			<s:if test="#session.USER_DROITS.contains('ICD_ADD')">
				<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
				<br/>
			</s:if>
			<div class="plus">
				<table width="100%">
					<tr>
						<td>Type&nbsp;:&nbsp;
							<s:select list="listTypes" name="typeSelected" listKey="id" listValue="type" headerKey="-1" headerValue="Tous" onchange="javascript:submitData('showIncidents.action');"></s:select>
						</td>
					</tr>
					<tr>
						<td align="left">Filtre : 
							<input type="radio" id="incidentsJour" name="filtreIncidents" value="1" onclick="javascript:changeFiltre(1)"
							<s:if test="filtreIncidents == 1">checked="checked"</s:if>/>incidents du jour 
							
							<input type="radio" id="incidentsTous" name="filtreIncidents" value="0" onclick="javascript:changeFiltre(0)"
							<s:if test="filtreIncidents == 0">checked="checked"</s:if>/>incidents non clotur&eacute;s
						</td>
						<td align="right"> <b> En cours </b> <span style="font-weight:100">/  Cloturé</span></td>
					</tr>
				</table>
			</div>
			<br/>
			
			<table class="dataTable" rules="all">
				<col width="3%" />
				<col width="3%"/>
				<col width="8%" />
				<col width="7%" />
				<col width="8%" />
				<col width="8%" />
				<col width="8%" />
				<col width="10%" />
				<col width="8%" />
				<col width="32%" />
				<s:if test="#session.USER_DROITS.contains('ICD_MOD')">
					<col width="7%" />
				</s:if>
				<thead>
					<tr>
						<td colspan="11">
							<s:include value="/jsp/user_pages/pagination/pagination_incident.jsp" />
						</td>
					</tr>
					<tr>
						<th class="titreColonne">Ars</th>
						<th class="titreColonne">Ref</th>
						<th class="titreColonne">Pilote</th>
						<th class="titreColonne">Date et heure</th>
						<th class="titreColonne">Serveur</th>
						<th class="titreColonne">Application</th>
						<th class="titreColonne">Hardware</th>
						<th class="titreColonne">Domaine</th>
						<th class="titreColonne">Coupure service</th>
						<th class="titreColonne">Observations</th>
						<th class="titreColonne">Actions</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="listIncidents.isEmpty()">
						<tr>
							<td colspan="11" class="emptyListText">
								Aucun incident <s:if test="typeSelected != -1">de ce type </s:if>en cours
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listIncidents">
							<s:if test="dateFin == null"> <tr class="incidentEnCours"> </s:if>
								<s:else><tr></s:else>
								<td align="center">
									<s:if test='ars == null || ars.length() == 0'>
										<img class="icone" alt="ARS absent" title="Pas de numéro d'incident attribué" src="<s:property value="#session.ENSEIGNE" />/img/attention-16.png" />
									</s:if>
									<s:else>
										<s:property value="%{ars}"/>
									</s:else>
								</td>
								
								<td align="center"><s:property value="%{id}"/></td>
								<td align="center"><s:property value="%{user.prenom}"/>&nbsp;<s:property value="%{user.nom}"/></td>
								<td align="center">
									<s:date name="%{dateDebut}" format="dd/MM/yyyy HH:mm" /><br/>
								
								<td align="center"><s:property value="%{machine.nom}"/></td>
								<td align="center">
									<s:iterator value="appMap.get(id)">
										<s:property value="%{applicatif}"/><br/>
									</s:iterator>
								</td>
								<td align="center">
									<s:if test="hardMap != null">
										<s:iterator value="hardMap.get(id)">
											<s:property value="%{libelle}"/><br/>
										</s:iterator>
									</s:if>
								</td>
								<td align="center"><s:property value="%{domaine.environnement}"/></td>
								<td align="center">
									<s:if test="coupure == 0">Pas de coupure</s:if>
									<s:else>Coupure</s:else>
								<td align="center"><s:property escapeHtml="false" value='%{observation.replace("\n","<br>")}' /></td>
								<td align="center">
									<s:if test="#session.USER_DROITS.contains('ICD_MOD')">
										<s:a href="#" onclick="javascript:modify('%{id}');">
											<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
										</s:a>
									</s:if>	
									<a href="#" onclick="javascript:detail(<s:property value="%{id}"/>);">
											<img class="icone" alt="Détail" title="Détail" src="<s:property value="#session.ENSEIGNE" />/img/detail-16.png">
										</a>
									<s:if test="dateFin != null">
										<s:a href="#" onclick="javascript:soumettre('%{id}','reinitialiserIncident.action');">
											<img class="icone" alt="Réinitialiser un incident" title="Réinitialiser un incident" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" />
										</s:a>
									</s:if>	
									<s:if test="hasAstreinte == 1">
										<s:a href="#" onclick="javascript:redirectAstreinte('%{id}', 2);">
											<img class="icone" alt="Voir astreinte" src="<s:property value="#session.ENSEIGNE" />/img/phone_vert.png" />
										</s:a>
									</s:if>
									<s:if test="hasAstreinte == 0">
										<s:a href="#" onclick="javascript:redirectAstreinte('%{id}', 1);">
											<img class="icone" alt="Ajouter astreinte" src="<s:property value="#session.ENSEIGNE" />/img/phone_2_plus.png" />
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
							<s:include value="/jsp/user_pages/pagination/pagination_incident.jsp" />
						</td>
					</tr>
				</tfoot>
			</table>
		</s:form>
	</div>
</body>
</html>
