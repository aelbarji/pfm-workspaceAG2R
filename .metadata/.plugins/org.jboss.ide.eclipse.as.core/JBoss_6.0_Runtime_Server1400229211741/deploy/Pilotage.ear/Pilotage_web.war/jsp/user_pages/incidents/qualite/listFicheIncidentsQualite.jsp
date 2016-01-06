<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function supprimer(id){
		    if(confirm("Voulez-vous vraiment supprimer cette fiche d'incident qualité ?")){
				changeData('selectRow', id);
			   	submitData('deleteFicheIncidentsQualite.action');
		    }   
		}

		function valider(id, url){
			changeData('selectRow', id);
			submitData(url);
		}
		
		function ajouter() {
			submitData("redirectCreateFicheIncidentsQualite.action");
		}

		function soumettre(id, adresse){
			changeData('selectRow', id);
			submitData(adresse);
		}

		function reinitialiserFiltreDate(){
			$("#filtreDate").val("");
			lancerRecherche();
		}

		function reinitialiserFiltreDateRes(){
			$("#filtreDateRes").val("");
			lancerRecherche();
		}

		//action à la recherche avec filtre
		function lancerRecherche() {
			var mainForm = document.getElementById("mainForm");
			mainForm.page.value = '1';
			mainForm.submit();
		}

		//reinitialisation des filtres 
		function reinitialiserLesFiltres(){
			var mainForm = document.getElementById("mainForm");
			$("#filtreDate").val("");
			mainForm.filtreDescription.value = '';
			mainForm.filtreCause.value = '';
			mainForm.filtreIncidence.value = '';
			mainForm.filtreEcheance.value = '';
			mainForm.filtreStatut.value = '-1';
			$("#filtreDateRes").val("");
			lancerRecherche();
		}	

        $.subscribe('filtreDateChange',function(event,data) {
        	lancerRecherche();
    	})
       
        $.subscribe('filtreDateResChange',function(event,data) {
        	lancerRecherche();
    	})

	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('ICD_QLT')" /></s:param>
	</s:include>
	
	<s:if test="filtreString != '' && filtreString != null">
		<div id="filtreString" class="filtreString">
			<u><b>Filtres</b></u> <s:property value="%{filtreString}" /><br/>
			<a href="#" onclick="javascript:reinitialiserLesFiltres();">
				<img alt="Réinitialiser les filtres" title="Réinitialiser les filtres" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" border="0" >
			</a>
		</div>
	</s:if>
	
	<div class="contentTable" >
		<s:form id="mainForm" theme="simple" action="showFicheIncidentsQualite">
			<s:token></s:token>
			<s:hidden name="selectRow" id="selectRow" value="" />
			<s:hidden name="sort" id="sort"/>
			<s:hidden name="sens" id="sens"/>
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage"/>
			<s:hidden name="nrPages" value="%{pagination.nrPages}" />
			<s:hidden name="provenance" value="0" />
			<s:hidden name="validForm" value="1" />
			
			<s:if test="#session.USER_DROITS.contains('ICD_QLT_ADD')">
				<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
			</s:if>
			
			<table class="dataTable" rules="all">
				<col width="12%"/>
				<col width="16%" />
				<col width="14%" />
				<col width="13%" />
				<col width="13%" />
				<col width="8%" />
				<col width="12%" />
				<col width="12%" />
				<thead>
					<tr>
						<td colspan="8">
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
					<tr>
						<td align="left" id="datetimepicker1" onkeydown="javascript:disableControls('filtreDate');" onmousedown="javascript:disableControls('filtreDate')">Filtre date :<br>
							<sj:datepicker name="filtreDate" 
											id="filtreDate" 
											displayFormat="dd/mm/yy" 
											showOn="focus"
											onChangeFocus="filtreDateChange">
							</sj:datepicker>
							<a href="#" onclick="javascript:reinitialiserFiltreDate();">
								<img class="icone" alt="Réinitialiser la date" title="Réinitialiser la date" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" />
							</a>
						</td>
						<td align="left" valign="top">Filtre description :<br>
							<s:textfield name="filtreDescription" id="filtreDescription" size="10" onchange="javascript:lancerRecherche();"/>
						</td>
						<td align="left" valign="top">Filtre cause :<br>
							<s:textfield name="filtreCause" id="filtreCause" size="10" onchange="javascript:lancerRecherche();"/>
						</td>
						<td align="left" valign="top">Filtre incidence :<br>
							<s:textfield name="filtreIncidence" id="filtreIncidence" size="10" onchange="javascript:lancerRecherche();"/>
						</td>
						<td align="left" valign="top">Filtre &eacute;ch&eacute;ance :<br>
							<s:textfield name="filtreEcheance" id="filtreEcheance" size="10" onchange="javascript:lancerRecherche();"/>
						</td>
						<td align="left" valign="top">Filtre statut :<br>
							<s:select name="filtreStatut" id="filtreStatut" list="statutList" listKey="id" listValue="statut" headerKey="-1" headerValue="" onchange="javascript:lancerRecherche();"></s:select>
						</td>
						<td align="left" id="datetimepicker1" onkeydown="javascript:disableControls('filtreDateRes');" onmousedown="javascript:disableControls('filtreDateRes')">Filtre date résolution :<br>
							<sj:datepicker name="filtreDateRes" 
											id="filtreDateRes" 
											displayFormat="dd/mm/yy" 
											showOn="focus"
											onChangeFocus="filtreDateResChange">
							</sj:datepicker>
							<a href="#" onclick="javascript:reinitialiserFiltreDateRes();">
								<img class="icone" alt="Réinitialiser la date" title="Réinitialiser la date" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" />
							</a>
						</td>
						<s:if test="#session.USER_DROITS.contains('ICD_QLT_MOD') || #session.USER_DROITS.contains('ICD_QLT_DEL')">
							<td>&nbsp;</td>
						</s:if>
					</tr>
					<tr>
						<th class="titreColonne">
							<a href="#" onclick="changeSort('dateEvenement');">Date de l'&eacute;v&egrave;nement</a>
							<s:if test="sens == 'asc' && sort == 'dateEvenement'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if> 
							<s:elseif test="sens == 'desc' && sort == 'dateEvenement'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</th>
						<th class="titreColonne">
							<a href="#" onclick="changeSort('description');">Description</a>
							<s:if test="sens == 'asc' && sort == 'description'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if> 
							<s:elseif test="sens == 'desc' && sort == 'description'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</th>
						<th class="titreColonne">
							<a href="#" onclick="changeSort('cause_raison');">Cause/Raison</a>
							<s:if test="sens == 'asc' && sort == 'cause_raison'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if> 
							<s:elseif test="sens == 'desc' && sort == 'cause_raison'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</th>
						<th class="titreColonne">
						<a href="#" onclick="changeSort('incidence');">Incidence</a>
							<s:if test="sens == 'asc' && sort == 'incidence'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if> 
							<s:elseif test="sens == 'desc' && sort == 'incidence'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</th>
						<th class="titreColonne">
						<a href="#" onclick="changeSort('echeance');">Ech&eacute;ance</a>
							<s:if test="sens == 'asc' && sort == 'echeance'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if> 
							<s:elseif test="sens == 'desc' && sort == 'echeance'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</th>
						<th class="titreColonne">
						<a href="#" onclick="changeSort('statut');">Statut</a>
							<s:if test="sens == 'asc' && sort == 'statut'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if> 
							<s:elseif test="sens == 'desc' && sort == 'statut'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</th>
						<th class="titreColonne">
						<a href="#" onclick="changeSort('dateResolution');">Date de r&eacute;solution</a>
							<s:if test="sens == 'asc' && sort == 'dateResolution'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if> 
							<s:elseif test="sens == 'desc' && sort == 'dateResolution'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</th>
						<s:if test="#session.USER_DROITS.contains('ICD_QLT_MOD') || #session.USER_DROITS.contains('ICD_QLT_DEL')">
							<th class="titreColonne">Actions</th>
						</s:if>
					</tr>
				</thead>
				<tbody>
					<s:if test="listFicheIncidentsQualite.isEmpty()">
						<tr>
							<td colspan="8" class="emptyListText">
								Aucun incident qualit&eacute; enregistr&eacute;
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listFicheIncidentsQualite">
							<tr>
								<td align="center"><s:date name="%{dateEvenement}" format="dd/MM/yyyy" /></td>
								<td align="center"><s:property value='description.replaceAll("<", "&lt;").replaceAll("\n", "<br/>")' escape="false"/></td>
								<td align="center"><s:property value='cause_raison.replaceAll("<", "&lt;").replaceAll("\n", "<br/>")' escape="false"/></td>
								<td align="center"><s:property value='incidence.replaceAll("<", "&lt;").replaceAll("\n", "<br/>")' escape="false"/></td>
								<td align="center"><s:property value='echeance.replaceAll("<", "&lt;").replaceAll("\n", "<br/>")' escape="false"/></td>
								<td align="center"><s:property value="statut.statut" /></td>
								<td align="center"><s:date name="%{dateResolution}" format="dd/MM/yyyy" /></td>
								<s:if test="#session.USER_DROITS.contains('ICD_QLT_MOD') || #session.USER_DROITS.contains('ICD_QLT_DEL')">
									<td align="center">
										<s:a href="#" onclick="javascript:valider('%{id}', 'showDetailFicheIncidentsQualite.action');"><img alt="Détail" title="Détail" src="<s:property value="#session.ENSEIGNE" />/img/detail-16.png" /></s:a>
										<s:if test="#session.USER_DROITS.contains('ICD_QLT_MOD')">
											<s:a href="#" onclick="javascript:soumettre('%{id}','redirectModifyFicheIncidentsQualite.action');">
												<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
											</s:a>
										</s:if>
										<s:if test="#session.USER_DROITS.contains('ICD_QLT_DEL')">
											<s:a href="#" onclick="javascript:supprimer('%{id}');">
												<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
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
						<td colspan="8">
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
				</tfoot>
			</table>
		</s:form>
	</div>
</body>
</html>
