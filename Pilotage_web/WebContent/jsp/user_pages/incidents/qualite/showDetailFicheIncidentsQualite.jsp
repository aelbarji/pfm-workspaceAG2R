<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function supprimerFiche(id){
		    if(confirm("Voulez-vous vraiment supprimer cette fiche d'incident qualité ?")){
				changeData('selectRow', id);
			  	submitData('deleteFicheIncidentsQualite.action');
		    }   
		}

		function soumettre(id, adresse){
			changeData('selectRow', id);
			submitData(adresse);
		}
		
		function supprimerAction(id){
		    if(confirm("Voulez-vous vraiment supprimer cette action ?")){
				changeData('selectRow', id);
				submitData('deleteActionIncidentsQualite.action');
		    }   
		}
		
		function valider(id, url){
			changeData('selectRow', id);
			submitData(url);
		}
		
		function ajouter() {
			submitData("redirectCreateActionIncidentsQualite.action");
		}

		function retour(){
			submitData('showFicheIncidentsQualite.action');
		}

	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('ICD_Q_A')" /></s:param>
	</s:include>
	
	<div class="contentTable" >
		<s:form id="mainForm" theme="simple" action="showDetailFicheIncidentsQualite">
			<s:token></s:token>
			<s:hidden name="selectRow" id="selectRow" value="" />
			<s:hidden name="sort" id="sort"/>
			<s:hidden name="sens" id="sens"/>
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage"/>
			<s:hidden name="nrPages" value="%{pagination.nrPages}" />
			<s:hidden name="ficheId" value="%{ficheIncidentsQualite.id}" />
			<s:hidden name="provenance" value="1" />
			
			<s:hidden name="sortFiche" id="sortFiche"/>
			<s:hidden name="sensFiche" id="sensFiche"/>
			<s:hidden name="pageFiche" id="pageFiche" />
			<s:hidden name="nrPerPageFiche" id="nrPerPageFiche"/>
			<s:hidden name="nrPagesFiche" id="nrPagesFiche" />
			
			<s:hidden name="filtreDate" id="filtreDate"/>
			<s:hidden name="filtreDescription" id="filtreDescription"/>
			<s:hidden name="filtreCause" id="filtreCause"/>
			<s:hidden name="filtreIncidence" id="filtreIncidence"/>
			<s:hidden name="filtreEcheance" id="filtreEcheance"/>
			<s:hidden name="filtreStatut" id="filtreStatut"/>
			<s:hidden name="filtreDateRes" id="filtreDateRes"/>
					
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
						<th class="titreColonne">Date de l'&eacute;v&egrave;nement</th>
						<th class="titreColonne">Description</th>
						<th class="titreColonne">Cause/Raison</th>
						<th class="titreColonne">Incidence</th>
						<th class="titreColonne">Ech&eacute;ance</th>
						<th class="titreColonne">Statut</th>
						<th class="titreColonne">Date de r&eacute;solution</th>
						<s:if test="#session.USER_DROITS.contains('ICD_QLT_MOD') || #session.USER_DROITS.contains('ICD_QLT_DEL')">
							<th class="titreColonne">Actions</th>
						</s:if>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center"><s:date name="%{ficheIncidentsQualite.dateEvenement}" format="dd/MM/yyyy" /></td>
						<td align="center"><s:property value="ficheIncidentsQualite.description" /></td>
						<td align="center"><s:property value="ficheIncidentsQualite.cause_raison" /></td>
						<td align="center"><s:property value="ficheIncidentsQualite.incidence" /></td>
						<td align="center"><s:property value="ficheIncidentsQualite.echeance" /></td>
						<td align="center"><s:property value="ficheIncidentsQualite.statut.statut" /></td>
						<td align="center"><s:date name="%{ficheIncidentsQualite.dateResolution}" format="dd/MM/yyyy" /></td>
						<s:if test="#session.USER_DROITS.contains('ICD_QLT_MOD') || #session.USER_DROITS.contains('ICD_QLT_DEL')">
							<td align="center">
								<s:if test="#session.USER_DROITS.contains('ICD_QLT_MOD')">
									<s:a href="#" onclick="javascript:soumettre('%{ficheIncidentsQualite.id}','redirectModifyFicheIncidentsQualite.action');">
										<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
									</s:a>
								</s:if>
								<s:if test="#session.USER_DROITS.contains('ICD_QLT_DEL')">
									<s:a href="#" onclick="javascript:supprimerFiche('%{ficheIncidentsQualite.id}');">
										<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
									</s:a>
								</s:if>
							</td>
						</s:if>
					</tr>
				</tbody>
			</table>
			<div class="contentTableBottom">
				<div class="pageRight">
					<s:a href="#" onclick="javascript:retour();" cssClass="boutonRetour">Retour</s:a>
				</div>
			</div>
			<br><br>
			<!-- Parite Action -->
			<s:if test="#session.USER_DROITS.contains('ICD_QLT_ACT_ADD')">
				<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
			</s:if>
			
			<table class="dataTable" rules="all">
				<col width="20%"/>
				<col width="65%"/>
				<col width="15%"/>
				<thead>
				<tr>
					<s:if test="#session.USER_DROITS.contains('ICD_QLT_ACT_MOD') || #session.USER_DROITS.contains('ICD_QLT_ACT_DEL')">
						<td colspan="3">
					</s:if>
					<s:else>
						<td colspan="2">
					</s:else>
						<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
					</td>
				</tr>
				<tr>
					<th class="titreColonne">
						<a href="#" onclick="changeSort('dateAction');">Date</a>
						<s:if test="sens == 'asc' && sort == 'dateAction'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if> 
						<s:elseif test="sens == 'desc' && sort == 'dateAction'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</th>
					<th class="titreColonne">
						<a href="#" onclick="changeSort('action');">Action</a>
						<s:if test="sens == 'asc' && sort == 'action'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if> 
						<s:elseif test="sens == 'desc' && sort == 'action'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</th>
					<s:if test="#session.USER_DROITS.contains('ICD_QLT_ACT_MOD') || #session.USER_DROITS.contains('ICD_QLT_ACT_DEL')">
						<th class="titreColonne">Actions</th>
					</s:if>
				</tr>
				</thead>
				<tbody>
					<s:if test="listActionIncidentsQualite.isEmpty()">
						<tr>
							<td colspan="3" class="emptyListText">
								Aucune action pour cette fiche d'incident qualit&eacute; enregistr&eacute;
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listActionIncidentsQualite">
							<tr>
								<td align="center"><s:date name="%{dateAction}" format="dd/MM/yyyy" /></td>
								<td align="center"><s:property value='action.replaceAll("<", "&lt;").replaceAll("\n", "<br/>")' escape="false"/></td>
								<s:if test="#session.USER_DROITS.contains('ICD_QLT_ACT_MOD') || #session.USER_DROITS.contains('ICD_QLT_ACT_DEL')">
									<td align="center">
										<s:if test="#session.USER_DROITS.contains('ICD_QLT_ACT_MOD')">
											<s:a href="#" onclick="javascript:soumettre('%{id}','redirectModifyActionIncidentsQualite.action');">
												<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
											</s:a>
										</s:if>
										<s:if test="#session.USER_DROITS.contains('ICD_QLT_ACT_DEL')">
											<s:a href="#" onclick="javascript:supprimerAction('%{id}');">
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
						<s:if test="#session.USER_DROITS.contains('ICD_QLT_ACT_MOD') || #session.USER_DROITS.contains('ICD_QLT_ACT_DEL')">
							<td colspan="3">
						</s:if>
						<s:else>
							<td colspan="2">
						</s:else>
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
				</tfoot>
			</table>
			
		</s:form>
	</div>
</body>
</html>
