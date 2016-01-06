<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">

	//Réinitialisation des filtres de dates	
	function reinitialiserDate() {
		$("#date").val("");
		lancerRecherche();
	}
	
	function ajouter() {
		changeData("astreinte", "");
		changeData("incident", "");
		if(document.getElementById("provenance").value == null || document.getElementById("provenance").value == ""){
			if(document.getElementById("provenance").value != null && document.getElementById("last_provenance").value == "redirectModifyIncident"){
				document.getElementById("last_provenance").value = "showAppelAstreinteAction";
			}
		}
		submitData('redirectCreateAppelAstreinteAction.action');
	}

	function detail(idAst, idInc){
		changeData("astreinte", idAst);
		changeData("incident", idInc);
		submitData('detailAppelAstreinteAction.action');
	}

	//action à la recherche avec filtre
	function lancerRecherche() {
		document.getElementById("mainForm").submit();
	}
	
	function supprimer(idAst, idInc){
		if(confirm("Etes vous sur de vouloir supprimer tous les appels?")){
			changeData("astreinte", idAst);
			changeData("incident", idInc);
			submitData('deleteAllAppelAstreinteAction.action');
		}
	}

	//ouvrir dans un autre onglet	
	function detailIncident(id){
		document.getElementById("incFromAstreinte").value = 1; // on vient des appels astreintes			
		changeData('selectedID', id);
		submitData('detailGestionIncident.action');	
	}
	
	function retour(){
		if(document.getElementById("last_provenance") != null && document.getElementById("last_provenance").value != "")
			submitData(document.getElementById("last_provenance").value);
		else if(document.getElementById("provenance") != null && document.getElementById("provenance").value != "")
				submitData(document.getElementById("provenance").value);
		else submitData('showIncidents.action');
	}

    $.subscribe('datepickerChange',function(event,data) {
    	lancerRecherche();
	})
	</script>
	</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('AST_APP_L')" /></s:param>
	</s:include>
	<div class="contentTable" style="width:70%">
		
	<s:form id="mainForm" action="showAppelAstreinteAction" theme="simple">
		<s:hidden id="provenance" name="provenance"/>
		<s:hidden id="incFromAstreinte" name="incFromAstreinte"/>
		<s:hidden id="last_provenance" name="last_provenance"/>
		<s:hidden id="selectedID" name="selectedID"/>
		<s:hidden id="astreinte" name="astreinte"/>
		<s:hidden id="incident" name="incident"/>
		<s:hidden id="idInc" name="idInc"/>
		<s:hidden id="validAst" name="validAst"/>
		<s:hidden name="page" id="page" />
		<s:hidden name="nrPerPage" id="nrPerPage"/>
		<s:hidden name="nrPages" value="%{pagination.nrPages}" />
		<s:hidden name="pageIncident" id="pageIncident" />
		<s:hidden name="nrPerPageIncident" id="nrPerPageIncident" />
		<s:hidden name="nrPagesIncident" id="nrPagesIncident" />
		<s:hidden name="typeSelected" id="typeSelected" />
		
	<s:if test="#session.USER_DROITS.contains('AST_APP_ADD')">
			<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
	</s:if>
	
	<table class="dataTable" rules="all" border="1">
				<thead>
				<tr>
					<td colspan="6">
						<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
					</td>
				</tr>
				<tr>
					<td align="left" onkeydown="javascript:disableControls('date');" onmousedown="javascript:disableControls('date')">Filtre date :<br>
						<sj:datepicker name="filtreDateDay" 
											id="date" 
											displayFormat="dd/mm/yy" 
											showOn="focus"
											readonly="true"
											onChangeTopics="datepickerChange">
							</sj:datepicker>
						<a href="#" onclick="javascript:reinitialiserDate();">
							<img class="icone" alt="Réinitialiser la date" title="Réinitialiser la date" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" />
						</a>
					</td>
					<td align="left" valign="top">Filtre astreinte :<br>
						<s:select list="%{astreintes}" listKey="id" listValue="prenom + ' ' + nom" name="filtreAstreinte" emptyOption="true" onchange="javascript:lancerRecherche();"></s:select>
					</td>
					<td align="left" valign="top">Filtre Incident :<br>
						<s:textfield name="filtreIncident" onchange="javascript:lancerRecherche();"/>
					</td>
					<td align="left" valign="top">Filtre Statut :<br>
						<s:select list="%{statuts}" listKey="id" listValue="statut" name="filtreStatut" emptyOption="true" onchange="javascript:lancerRecherche();"></s:select>
					</td>
					<td align="left" valign="top">Filtre Nb appel :<br>
						<s:textfield name="filtreNbAppel" onchange="javascript:lancerRecherche();"/>
					</td>
					<s:if test="#session.USER_DROITS.contains('AST_APP_MOD') || #session.USER_DROITS.contains('AST_APP_DEL')">
						<td></td>
					</s:if>
				</tr>
				<tr>
					<td class="titreColonne">Date - Premier Appel</td>
					<td class="titreColonne">Astreinte</td>
					<td class="titreColonne">Incident</td>
					<td class="titreColonne">Statut</td>
					<td class="titreColonne">Nb appel</td>
					<td class="titreColonne">Actions</td>
				</tr>
				</thead>
				<tbody>
					<s:if test="listAppelAstreinte.isEmpty()">
						<tr>
							<td class="emptyListText" colspan=<s:if test="#session.USER_DROITS.contains('AST_PLN_MOD') || #session.USER_DROITS.contains('AST_PLN_DEL')">"8"</s:if><s:else>"7"</s:else>>
								Aucune astreinte trouv&eacute;e
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listAppelAstreinte" id="listAppelAstreinte" status="stat">
							<tr>
								<td align="center">&nbsp;<s:date name="#listAppelAstreinte.mindate" format="dd/MM/yyyy" /> - <s:date name="#listAppelAstreinte.mindate" format="HH:mm" /></td>
								<td align="left"><s:property value="#listAppelAstreinte.astreinte.prenom+ ' ' + #listAppelAstreinte.astreinte.nom" /></td>
								<td align="center" onclick="javascript:detailIncident(<s:property value="%{incident.id}"/>);"><s:property value="#listAppelAstreinte.incident.id"/></td>
								<td align="center"><s:property value="#listAppelAstreinte.statut.statut" /></td>
								<td align="center"><s:property value="#listAppelAstreinte.nbAppel" /></td>
								<td class="td" align="center">							
									<s:if test="#session.USER_DROITS.contains('AST_APP_DET')">
										<a href="#" onclick="javascript:detail(<s:property value="#listAppelAstreinte.astreinte.id"/>, <s:property value="#listAppelAstreinte.incident.id"/>);">
											<img class="icone" alt="Détail" title="Détail" src="<s:property value="#session.ENSEIGNE" />/img/detail-16.png">
										</a>
									</s:if>
									<s:if test="#session.USER_DROITS.contains('AST_APP_DEL')">
										<a href="#" onclick="javascript:supprimer(<s:property value="#listAppelAstreinte.astreinte.id"/>, <s:property value="#listAppelAstreinte.incident.id"/>);">
											<img class="icone"  alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png">
										</a>
									</s:if>	
								</td>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="6">
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
				</tfoot>
			</table>
			<s:if test="validAst == 2">
						<tr align="center">
							<td colspan="6">
								<div class="contentTableBottom">
									<span class="champObligatoire">* Champs Obligatoires</span>
									<div class="pageRight">
										<s:a href="#" onclick="javascript:retour();" cssClass="boutonRetour">Retour</s:a>
									</div>
								</div>
							</td>
						</tr>
			</s:if>
		</s:form>
	</div>
</body>
</html>