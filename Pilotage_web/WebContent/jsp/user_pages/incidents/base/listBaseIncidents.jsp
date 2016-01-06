<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function lancerRecherche() {
			document.getElementById("mainForm").pageIncident.value = '1';
			document.getElementById("mainForm").showResult.value = '1';
			document.getElementById("mainForm").submit();
		}
		
		function soumettre(id, adresse){
			reinitFilterValues();
			reinitPageValues();
			changeData('selectedID', id);
			submitData(adresse);
		}

		//reinitialisation des filtres comme à l'entrée de la page
		function reinitFilterValues() {
			document.getElementById("mainForm").filtrePilote.value = '<s:property value="filtrePilote" escape="false"/>';
			document.getElementById("mainForm").filtreServeur.value = '<s:property value="filtreServeur" escape="false"/>';
			document.getElementById("mainForm").filtreApplicatif.value = '<s:property value="filtreApplication" escape="false"/>';
			document.getElementById("mainForm").filtreEnvironnement.value = '<s:property value="filtreEnvironnement" escape="false"/>';
			document.getElementById("mainForm").filtreResoluPilotage.value = '<s:property value="filtreResoluPilotage" escape="false"/>';
			document.getElementById("mainForm").filtreCoupureService.value = '<s:property value="filtreCoupureService" escape="false"/>';
			document.getElementById("mainForm").filtreHasAstreinte.value = '<s:property value="filtreHasAstreinte" escape="false"/>';
			document.getElementById("mainForm").filtreObservation.value = '<s:property value="filtreObservation" escape="false"/>';
			document.getElementById("mainForm").filtreArs.value = '<s:property value="filtreArs" escape="false"/>';
		}

		//reinitialisation de page comme à l'entrée de la page
		function reinitPageValues() {
			document.getElementById("mainForm").pageIncident.value = '<s:property value="pageIncident" escape="false"/>';
			document.getElementById("mainForm").nrPerPageIncident.value = '<s:property value="nrPerPageIncident" escape="false"/>';
			document.getElementById("mainForm").nrPagesIncident.value = '<s:property value="nrPagesIncident" escape="false"/>';
		}

		function reinitialiserLesFiltres(){
			document.getElementById("mainForm").filtrePilote.value = '-1';
			document.getElementById("mainForm").filtreDate.value = '';
			document.getElementById("mainForm").filtreDateFin.value = '';
			document.getElementById("mainForm").filtreServeur.value = '-1';
			document.getElementById("mainForm").filtreApplicatif.value = '-1';
			document.getElementById("mainForm").filtreEnvironnement.value = '-1';
			for (var i=0; i<document.getElementsByName("filtreResoluPilotage").length; i++) {
				document.getElementsByName("filtreResoluPilotage")[i].checked = false;
			}
			for (var i=0; i<document.getElementsByName("filtreCoupureService").length; i++) {
				document.getElementsByName("filtreCoupureService")[i].checked = false;
			}
			for (var i=0; i<document.getElementsByName("filtreHasAstreinte").length; i++) {
				document.getElementsByName("filtreHasAstreinte")[i].checked = false;
			}
			document.getElementById("mainForm").filtreObservation.value = '';
			document.getElementById("mainForm").filtreArs.value = '';
			document.getElementById("mainForm").showResult.value = '0';
			document.getElementById("mainForm").pageIncident.value = '1';
			document.getElementById("mainForm").submit();
		}

		//ouvrir dans un autre onglet
		function newTab(id,destina){			
			var form =document.getElementById("mainForm");
			form.target = "_blank";
			changeData('selectedID', id);
			submitData(destina);
			form.target = "_self";
			
		}

		//ouvrir dans un autre onglet
		function modify(id){	
			var form = document.getElementById("mainForm");
			form.target = "_blank";
			changeData('selectedID', id);
			submitData('redirectModifyIncident.action');
			form.target = "_self";
		}

		function retour(){
			submitData("showStatIncident.action");
		}

		function init(){	
			/*if(redir.value == "true"){
				redir.value = false;
				document.getElementById("isredir").value = 1;
				//document.getElementById("rechercher").click();	
			}*/
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
<body onload="javascript:init()">
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('ICD_LST')" /></s:param>
	</s:include>
	
	<div class="contentTable" >
		<s:form id="mainForm" theme="simple" action="showBaseIncidents">
			<s:token></s:token>
			<s:hidden name="selectedID" id="selectedID"/>
			<s:hidden name="provenance" id="provenance"/>
			<s:hidden name="idInc" id="idInc"/>
			<s:hidden name="validAst" id="validAst"/>
			<s:hidden name="pageIncident" id="pageIncident" />
			<s:hidden name="nrPerPageIncident" id="nrPerPageIncident"/>
			<s:hidden name="nrPagesIncident" value="%{paginationIncident.nrPages}" />
			<s:hidden name="showResult"/>
			<s:hidden name="validForm" value="1" />
			<s:hidden name="redir" id="redir" />
			<s:hidden name="isredir" id="isredir"/>
			
			<s:if test = "filtreJson != null">
				<div id="filtreString" class="filtreString">
					<u><b>Filtres</b></u> <s:property value="%{filtreString}" /><br/>
					<a href="#" onclick="javascript:reinitialiserLesFiltres();">
						<img class="icone" alt="Réinitialiser les filtres" title="Réinitialiser les filtres" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png"/>
					</a>
				</div>
			</s:if>
			<br/>
			
			<div id="filtres">
				<table class="tabFilter4Col" width="600px">
					<col width="16%"/>
					<col width="35%"/>
					<col width="15%"/>
					<col width="35%"/>
					<tr>
						<td>Pilote : </td>
						<td><s:select list="listPilote" name="filtrePilote" listKey="id" listValue="nom+' ' +prenom" headerKey="-1" headerValue=""></s:select></td>
						<td>Serveur : </td>
						<td><s:select list="listServeur" name="filtreServeur" listKey="id" listValue="nom" headerKey="-1" headerValue=""></s:select></td>
					</tr>
					<tr>
						<td>Application : </td>
						<td><s:select list="listApplicatif" name="filtreApplicatif" listKey="id" listValue="applicatif" headerKey="-1" headerValue=""></s:select></td>
						<td>Environnement : </td>
						<td><s:select list="listEnvironnement" name="filtreEnvironnement" listKey="id" listValue="environnement" headerKey="-1" headerValue=""></s:select></td>
					</tr>
					<tr>
						<td>Date entre: </td>
						<td>
							<sj:datepicker name="filtreDate" 
											id="pickDate" 
											displayFormat="dd/mm/yy" 
											showOn="focus"
											value="%{filtreDate}"
											label=" Date ">
							</sj:datepicker>
						</td>
						<td>et</td>
						<td>
							<sj:datepicker name="filtreDateFin" 
											id="pickDateFin" 
											displayFormat="dd/mm/yy" 
											showOn="focus"
											value="%{filtreDateFin}"
											label=" et ">
							</sj:datepicker>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>Résolu par le pilotage : </td>
						<td><s:radio name="filtreResoluPilotage" list="#{'1':'Oui','0':'Non','-1':'Aucun'}" listKey="key" listValue="value"/></td>
						<td>Coupure de service : </td>
						<td><s:radio name="filtreCoupureService" list="#{'1':'Oui','0':'Non','-1':'Aucun'}" listKey="key" listValue="value"/></td>
					</tr>
					<tr>
						<td>Astreinte : </td>
						<td><s:radio name="filtreHasAstreinte" list="#{'1':'Oui','0':'Non','-1':'Aucun'}" listKey="key" listValue="value"/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>Type : </td>
						<td><s:select list="listType" id="filtreType" name="filtreType" listKey="id" listValue="type" headerKey="-1" headerValue=""></s:select></td>
						<td>Observation : </td>
						<td><s:textfield id="filtreObservation" name="filtreObservation" maxlength="15" size="20" />

					</tr>
					<tr>
						<td>Ars : </td>
						<td><s:textfield id="filtreArs" name="filtreArs" maxlength="15" size="20" />
						<td><s:if test="isredir == 1">
								<s:a href="#" onclick="javascript:retour();" cssClass="boutonRetour">Retour</s:a>
							</s:if>
							<s:else>&nbsp;</s:else></td>
						<td><a href="#" id="rechercher" onclick="javascript:lancerRecherche();" class="boutonRechercher">Rechercher</a></td>
					</tr>
				</table>
			</div>
			<br/>
			
			<s:if test="showResult == 1">
				<span style="text-align:center">R&eacute;sultat(s) trouv&eacute;(s): <s:property value="nbIncidents" /></span>
			
				<table class="dataTable" rules="all">
					<col width="1%" />
					<col width="3%"/>
					<col width="3%"/>
					<col width="8%" />
					<col width="7%" />
					<col width="7%" />
					<col width="7%" />
					<col width="7%" />
					<col width="7%" />
					<col width="8%" />
					<col width="32%" />
					<s:if test="#session.USER_DROITS.contains('ICD_MOD')">
						<col width="4%" />
					</s:if>
					<thead>
					<tr> <td align="right" colspan=<s:if test="#session.USER_DROITS.contains('ICD_MOD')">"12"</s:if><s:else>"11"</s:else>>
					   <b>En cours</b> / Cloturé</td>
					</tr>
					<tr>
							
							<td colspan=<s:if test="#session.USER_DROITS.contains('ICD_MOD')">"12"</s:if><s:else>"11"</s:else>>
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
							<th class="titreColonne">Type</th>
							<th class="titreColonne">Coupure service</th>
							<th class="titreColonne">Observations</th>
							<s:if test="#session.USER_DROITS.contains('ICD_MOD')">
								<th class="titreColonne">Actions</th>
							</s:if>
						</tr>
					</thead>
					<tbody>
						<s:if test="listIncidents.isEmpty()">
							<tr>
								<td colspan=<s:if test="#session.USER_DROITS.contains('ICD_MOD')">"12"</s:if><s:else>"11"</s:else> class="emptyListText">
									Aucun incident en cours
								</td>
							</tr>
						</s:if>
						<s:else>
							<s:iterator value="listIncidents">
								<s:if test="dateFin == null"> <tr class="incidentEnCours"> </s:if>
								<s:else><tr></s:else>
									<td align="center">
										<s:if test='%{ars == null || ars.isEmpty()}'>
											<img class="icone" alt="ARS absent" title="Pas de numéro d'incident attribué" src="<s:property value="#session.ENSEIGNE" />/img/attention-16.png" />
										</s:if>
										<s:else>
											<s:property value="%{ars}"/>
										</s:else>
									</td>
									<td align="center" onclick="javascript:newTab('<s:property value="%{id}"/>','redirectModifyIncident.action');"><s:property value="%{id}"/></td>
									<td align="center" onclick="javascript:newTab('<s:property value="%{id}"/>','redirectModifyIncident.action');"><s:property value="%{user.prenom}"/>&nbsp;<s:property value="%{user.nom}"/></td>
									<td align="center" onclick="javascript:newTab('<s:property value="%{id}"/>','redirectModifyIncident.action');">
										<s:date name="%{dateDebut}" format="dd/MM/yyyy HH:mm" />
									</td>
									<td align="center" onclick="javascript:newTab('<s:property value="%{id}"/>','redirectModifyIncident.action');"><s:property value="%{machine.nom}"/></td>
									<td align="center" onclick="javascript:newTab('<s:property value="%{id}"/>','redirectModifyIncident.action');">
										<s:iterator value="appMap.get(id)">
											<s:property value="%{applicatif}"/><br/>
										</s:iterator>
									</td>
									<td align="center" onclick="javascript:newTab('<s:property value="%{id}"/>','redirectModifyIncident.action');">
										<s:iterator value="hardMap.get(id)">
											<s:property value="%{libelle}"/><br/>
										</s:iterator>
									</td>
									<td align="center" onclick="javascript:newTab('<s:property value="%{id}"/>','redirectModifyIncident.action');"><s:property value="%{domaine.environnement}"/></td>
									<td align="center" onclick="javascript:newTab('<s:property value="%{id}"/>','redirectModifyIncident.action');"><s:property value="%{type.type}"/></td>
									<td align="center" onclick="javascript:newTab('<s:property value="%{id}"/>','redirectModifyIncident.action');">
										<s:if test="coupure == 0">Pas de coupure</s:if>
										<s:else>Coupure</s:else>
									<td align="center" onclick="javascript:newTab('<s:property value="%{id}"/>','redirectModifyIncident.action');"><s:property escapeHtml="false" value='%{observation.replace("\n","<br>")}' /></td>
									<s:if test="#session.USER_DROITS.contains('ICD_MOD')">
										<td align="center">
											<s:if test="dateFin != null">
												<s:a href="#" onclick="javascript:soumettre('%{id}','reinitialiserIncident.action');">
													<img class="icone" alt="Réinitialiser un incident" title="Réinitialiser un incident" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" />
												</s:a>
											</s:if>										
											<s:a href="#" onclick="javascript:modify('%{id}');">
												<img class="icone" alt="Modifier un incident" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
											</s:a>
											<a href="#" onclick="javascript:detail(<s:property value="%{id}"/>);">
												<img class="icone" alt="Détail" title="Détail" src="<s:property value="#session.ENSEIGNE" />/img/detail-16.png">
											</a>
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
									</s:if>
								</tr>
							</s:iterator>
						</s:else>
					</tbody>
					<tfoot>
						<tr>
							<td colspan=<s:if test="#session.USER_DROITS.contains('ICD_MOD')">"12"</s:if><s:else>"11"</s:else>>
								<s:include value="/jsp/user_pages/pagination/pagination_incident.jsp" />
							</td>
						</tr>
					</tfoot>
				</table>
			</s:if>
		</s:form>
	</div>
</body>
</html>