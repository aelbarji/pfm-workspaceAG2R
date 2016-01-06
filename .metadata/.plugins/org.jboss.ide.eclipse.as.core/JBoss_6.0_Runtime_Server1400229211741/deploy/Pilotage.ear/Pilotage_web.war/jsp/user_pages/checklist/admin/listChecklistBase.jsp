<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />

	<link rel="stylesheet" type="text/css" href="js/time/jquery.timeentry.css"/>
	<script type="text/javascript" src="js/time/jquery.timeentry.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
	<script type="text/javascript" src="js/time/jquery.timeentry-fr.js"></script>
	<script type="text/javascript" src="js/time/jquery.mousewheel.min.js"></script>
	<script type="text/javascript">

		function reinitFilterValues() {
			document.getElementById("mainForm").filtreNom.value = '<s:property value="filtreNom" />';
			document.getElementById("mainForm").filtreEnvironnement.value = '<s:property value="filtreEnvironnement" />';
			$("#dateDebut").val('<s:property value="filtreDateDebut" />');
			document.getElementById("mainForm").filtreEtat.value = '<s:property value="filtreEtat" />';
			document.getElementById("mainForm").filtreCriticite.value = '<s:property value="filtreCriticite" />';
		}
		
		function reinitialiserDateDebut() {
			$("#dateDebut").val("");
			lancerRecherche();
		}

		function lancerRecherche() {
			document.getElementById("mainForm").page.value = '1';
			document.getElementById("mainForm").submit();
		}

		function ajouter(){
			submitData('showCreateChecklistBaseAction.action');
		}

		function modifier(id){
			changeData('selectRow', id); 
			submitData('showModifyChecklistBaseAction.action');
		}

		function supprimer(id) {
			if (confirm("Voulez-vous vraiment supprimer cette tâche ?")) {
				changeData('selectRow', id); 
				submitData('deleteChecklistBaseAction.action');
			}
		}
		
		function changeAffichageEtat(){
			if (document.getElementById("avecEtatModif").checked) {				
				$("#idTable tr").show();//tous montré
			}
			if(document.getElementById("sansEtatModif").checked){
				$("#idTable tr").each(function() { //loop sur chaque ligne

		            if($(this).find("td:eq(3)").text() == "Modifiée") { //test valeurs td
		                $(this).hide(); //cacher la ligne 

		            }
				});
			}
		}
			
        //reinitialisation des filtres 
		function reinitialiserLesFiltres(){
			document.getElementById("mainForm").filtreNom.value = '';
			document.getElementById("mainForm").filtreEnvironnement.value = '-1';
			$("#dateDebut").val("");
			document.getElementById("mainForm").filtreEtat.value = '-1';
			document.getElementById("mainForm").filtreCriticite.value = '-1';
			lancerRecherche();
		}

        $.subscribe('filtreDateDebutChange',function(event,data) {
        	lancerRecherche();
    	})

    	</script>
</head>
<body onload="changeAffichageEtat();">
	<s:token></s:token>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('CKL_LST')" /></s:param>
	</s:include>
	
	<div class="contentTable">
		<s:form id="mainForm" action="showChecklistBaseAction" theme="simple">
		<div class="contentTable" >
			<s:if test="#session.USER_DROITS.contains('CKL_ADD')">
				<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
			</s:if>
			
			<table class="dataTable" id="idTable" rules="all" border="1">
				<col width="25%"/>
				<col width="20%"/>
				<col width="20%"/>
				<col width="10%"/>
				<col width="10%"/>
				<s:if test="#session.USER_DROITS.contains('CKL_MOD') || #session.USER_DROITS.contains('CKL_DEL')">
					<col width="15%"/>
				</s:if>
				<thead>
					<tr>
						<td colspan=<s:if test="#session.USER_DROITS.contains('CKL_MOD') || #session.USER_DROITS.contains('CKL_DEL')">"6"</s:if><s:else>"5"</s:else>>
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
					
					<tr>
						<td align="left" valign="top">Filtre nom :<br>
							<s:hidden id="selectRow" name="selectRow" value=""/>
							<s:hidden name="sort" id="sort" />
							<s:hidden name="sens" id="sens" />
							<s:hidden name="page" id="page" />
							<s:hidden name="nrPerPage" id="nrPerPage"/>
							<s:hidden name="nrPages" value="%{pagination.nrPages}" />
							<s:hidden name="validForm" value="1" />
							<s:textfield name="filtreNom" onchange="javascript:lancerRecherche();"/></td>
						<td align="left" valign="top">Filtre environnement :<br>
						<s:select list="%{listEnvironnement}" listKey="id" listValue="environnement" name="filtreEnvironnement" headerKey="-1" headerValue="" onchange="javascript:lancerRecherche();"/></td>

						<td align="left" valign="top" id="datetimepicker1" onkeydown="javascript:disableControls('dateDebut');" onmousedown="javascript:disableControls('dateDebut')">Filtre date d&eacute;but :<br>
							<sj:datepicker name="filtreDateDebut" 
										id="dateDebut" 
										displayFormat="dd/mm/yy" 
										showOn="focus"
										onChangeTopics="filtreDateDebutChange">
							</sj:datepicker>								
							<a href="#" onclick="javascript:reinitialiserDateDebut();">
								<img class="icone" alt="Réinitialiser la date" title="Réinitialiser la date" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" />
							</a>
						</td>
						
						<td align="left" valign="top">Filtre &eacute;tat :<br>
						<s:select list="%{listEtat}" listKey="id" listValue="etat" name="filtreEtat" headerKey="-1" headerValue="" onchange="javascript:lancerRecherche();"/></td>
						<td align="left" valign="top">Filtre criticit&eacute; :<br>
						<s:select list="%{listCriticite}" listKey="id" listValue="libelle" name="filtreCriticite" headerKey="-1" headerValue="" onchange="javascript:lancerRecherche();"/></td>
						<s:if test="#session.USER_DROITS.contains('CKL_MOD') || #session.USER_DROITS.contains('CKL_DEL')">
							<td align="left" valign="top">Affichage des tâches Modifiées :<br>
							&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="etatModif" id="avecEtatModif"  value="1" onchange="javascript: changeAffichageEtat()"/>Oui
							<input type="radio" name="etatModif" id="sansEtatModif"  value="0" checked="checked" onchange="javascript: changeAffichageEtat()"/>Non
							</td>
						</s:if>
					</tr>
					
					<tr valign="middle">
						<td class="titreColonne" >
							<a href="#" onclick="changeSort('tache');">Nom</a>
							<s:if test="sens == 'asc' && sort == 'tache'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if> 
							<s:elseif test="sens == 'desc' && sort == 'tache'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
										
						
						<td class="titreColonne" >
							<a href="#" onclick="changeSort('environnement');">Environnement</a>
							<s:if test="sens == 'asc' && sort == 'environnement'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if>
							<s:elseif test="sens == 'desc' && sort == 'environnement'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</td>
						<td class="titreColonne" >
							<a href="#" onclick="changeSort('dateDebut');">Date de d&eacute;but</a>
							<s:if test="sens == 'asc' && sort == 'dateDebut'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if>
							<s:elseif test="sens == 'desc' && sort == 'dateDebut'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</td>
						<td class="titreColonne" >
							<a href="#" onclick="changeSort('etat');">Etat</a>
							<s:if test="sens == 'asc' && sort == 'etat'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if>
							<s:elseif test="sens == 'desc' && sort == 'etat'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</td>
						<td class="titreColonne" >
							<a href="#" onclick="changeSort('criticite');">Criticité</a>
							<s:if test="sens == 'asc' && sort == 'criticite'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if>
							<s:elseif test="sens == 'desc' && sort == 'criticite'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</td>
						<s:if test="#session.USER_DROITS.contains('CKL_MOD') || #session.USER_DROITS.contains('CKL_DEL')">
							<td class="titreColonne" >Actions</td>
						</s:if>
					</tr>
				</thead>
				<tbody>
					<s:if test="listeTaches.isEmpty()">
						<tr>
							<td class="emptyListText" colspan=<s:if test="#session.USER_DROITS.contains('CKL_MOD') || #session.USER_DROITS.contains('CKL_DEL')">"6"</s:if><s:else>"5"</s:else>>
								Aucune tâche trouvée
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listeTaches" status="stuts">
							<tr>
								<td align="left">&nbsp;&nbsp;<s:property value="%{tache}" /></td>
								<td align="center"><s:property value="%{environnement.environnement}" /></td>
								<td align="center"><s:date name="%{dateDebut}" format="dd/MM/yyyy" /></td>
								<td align="center"><s:property value="%{etat.etat}" /></td>
								<td align="center"><s:property value="%{criticite.libelle}" /></td>
								<s:if test="#session.USER_DROITS.contains('CKL_MOD') || #session.USER_DROITS.contains('CKL_DEL')">
									<td align="center" valign="middle">
										<s:if test="#session.USER_DROITS.contains('CKL_MOD')">
											<s:a href="#" onclick="javascript:modifier('%{id}');">
												<img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
											</s:a>
										</s:if>
										<s:if test="#session.USER_DROITS.contains('CKL_DEL')">
											<s:a href="#" onclick="javascript:supprimer('%{id}');">
												<img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
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
						<td colspan=<s:if test="#session.USER_DROITS.contains('CKL_MOD') || #session.USER_DROITS.contains('CKL_DEL')">"6"</s:if><s:else>"5"</s:else>>
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
				</tfoot>
			</table>
			
		</div>
		</s:form>
	</div>
</body>
</html>