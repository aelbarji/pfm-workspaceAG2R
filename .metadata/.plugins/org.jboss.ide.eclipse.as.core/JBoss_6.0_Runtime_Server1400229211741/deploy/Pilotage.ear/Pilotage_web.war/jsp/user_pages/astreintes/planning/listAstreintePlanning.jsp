<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	
	<link rel="stylesheet" type="text/css" href="js/time/jquery.timeentry.css">
	<script type="text/javascript" src="js/time/jquery.timeentry.js"></script>
	<script type="text/javascript" src="js/time/jquery.timeentry-fr.js"></script>
	<script type="text/javascript" src="js/time/jquery.mousewheel.min.js"></script>
	<script type="text/javascript">
		//Réinitialisation des filtres de dates
		function reinitialiserDateDebut() {
			$("#dateDebut").val("");
			lancerRecherche();
		}
		function reinitialiserDateFin() {
			$("#dateFin").val("");
			lancerRecherche();
		}

		//action de suppression d'un utilisateur
		function supprimer(id) {
			if (confirm("Voulez-vous vraiment supprimer cette astreinte ?")) {
				changeData('selectRow', id);
				submitData('supprimerAstreintePlanningAction.action');
			}
		}
	
		//action de modification
		function modifier(id) {
			changeData('selectRow', id);
			submitData('redirectModifyAstreintePlanningAction.action');
		}
	
		//reinitialisation des filtres comme à l'entrée de la page
		function reinitFilterValues() {
			document.getElementById("mainForm").filtreType.value = '<s:property value="filtreType" />';
			document.getElementById("mainForm").filtreDomaine.value = '<s:property value="filtreDomaine" />';
			document.getElementById("mainForm").filtreAstreinte.value = '<s:property value="filtreAstreinte" />';
			$("#dateDebut").val(<s:property value="filtreDebDate" />);
			$("#dateFin").val(<s:property value="filtreFinDate" />);
			document.getElementById("mainForm").filtreTelephone.value = '<s:property value="filtreTelephone" escape="false"/>';
			document.getElementById("mainForm").filtreCommentaire.value = '<s:property value="filtreCommentaire" escape="false"/>';
		}
	
		//action à la recherche avec filtre
		function lancerRecherche() {
			document.getElementById("mainForm").page.value = '1';
			document.getElementById("mainForm").submit();
		}

		//action au changement de nombre de jour pour les astreintes obligatoires
		function lancerRechercheAstreinteObligatoire() {
			reinitFilterValues();
			document.getElementById("mainForm").submit();
		} 

		function selectDateDebut(){
			if ($("#exportAstreintePlanning").is(":hidden")) {
				$("#exportAstreintePlanning").show();
			}
			else{
				$("#exportAstreintePlanning").hide();
			}
			document.getElementById("exportAstreintePlanning").style.top = '17%';
			document.getElementById("exportAstreintePlanning").style.left = '90%';
		}
		
		function mailenquiry() {
			var dateDebAstrPlan = document.getElementById("dateDebutAstrPlanning").value;
			$.ajax({
			  	type : 'POST',
				url : 'ajaxAstreintePlanning.action' ,
				data : "dateDebAstrPlan=" + dateDebAstrPlan ,
				success : function(data){
					if (data.retour == "OK"){
						var information = $(".info");
						information.val( message_astreinte_ok);			
					} else {
						var erreur = $(".error");
						erreur.val(message_astreinte_ko);						
					}
					alert( "mail envoyé  :" + data.retour);
					$("#exportAstreintePlanning").hide();
				}
			});
		}
		function exportExcel() {
			document.getElementById('exportForm').submit();
		}
	
		function ajouter() {
			submitData('redirectCreateAstreintePlanningAction.action');
		}
		function ajouterByTypeDomaine(id) {
			changeData('astObligatoire', id);
			submitData('redirectCreateAstreintePlanningAction.action');
		}
		function reinitialiserLesFiltres(){
			document.getElementById("mainForm").filtreType.value = '';
			document.getElementById("mainForm").filtreDomaine.value = '';
			document.getElementById("mainForm").filtreAstreinte.value = '';
			$("#dateDebut").val("");
			$("#dateFin").val("");
			document.getElementById("mainForm").filtreTelephone.value = '';
			document.getElementById("mainForm").filtreCommentaire.value = '';
			lancerRecherche();
		}

        $.subscribe('datepickerChange',function(event,data) {
        	lancerRecherche();
    	});	   

    </script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('AST_P_LST')" /></s:param>
		<s:param name="mailastreinte" value="true"></s:param>
		<s:param name="export" value="true"></s:param>
	</s:include>
	
	<div id="exportAstreintePlanning" style="position: absolute; display: none;">
		<table id="tabMailAstrPlanning">
			<tr><td>Semaine à partir du:</td></tr>
			<tr><td><sj:datepicker name="dateDebutAstrPlanning" 
											id="dateDebutAstrPlanning" 
											displayFormat="dd/mm/yy" 
											showWeek="true"
											showOn="focus">
				</sj:datepicker></td></tr>
			<tr><td><s:a href="#" onclick="javascript:mailenquiry();" cssClass="boutonValider">Envoyer</s:a></td></tr>
			
		</table>
	</div>

	<div class="contentTable" >
		<s:form id="mainForm" theme="simple" action="showAstreintePlanningAction">
			<s:hidden id="astObligatoire" name="astObligatoire" value="" />
			<s:hidden id="selectRow" name="selectRow" value="" />
			<s:hidden name="page" id="page" />
			<s:hidden name="sort" id="sort" />
			<s:hidden name="sens" id="sens" />
			<s:hidden name="nrPerPage" id="nrPerPage"/>
			<s:hidden name="nrPages" value="%{pagination.nrPages}" />
			<s:hidden name="validForm" value="1" />
			<s:token></s:token>
			
			<table width="100%">
				<col width="50%"/>
				<col width="50%"/>
				<tr>
					<td align="center" colspan="2">
						Nombre de jours : 
						<s:select list="{'1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16',
										'17','18','19','20','21','22','23','24','25','26','27','28','29','30','31'}"
										theme="simple" name="dateNB" value="%{dateNB}" onchange="javascript:lancerRechercheAstreinteObligatoire();"/>
						<br/>
						<table class="dataTableAstreintesObligatoire" cellpadding="2" width="60%" rules="all">
							<col width="45%"/>
							<col width="45%"/>
							<s:if test="#session.USER_DROITS.contains('AST_PLN_ADD')">
								<col width="10%"/>
							</s:if>
							<thead>
								<tr valign="middle">
									<td class="titreColonne">Type</td>
									<td class="titreColonne">Domaine</td>
									<s:if test="#session.USER_DROITS.contains('AST_PLN_ADD')">
										<td class="titreColonne">Actions</td>
									</s:if>
								</tr>
							</thead>
							<tbody>
								<s:if test="aObligatoires.isEmpty()">
									<tr>
										<td class="emptyListText" colspan=<s:if test="#session.USER_DROITS.contains('AST_PLN_ADD')">"3"</s:if><s:else>"2"</s:else>>
											Toutes les astreintes obligatoires ont été couvertes
										</td>
									</tr>
								</s:if>
								<s:else>
									<s:iterator value="aObligatoires" id="aObligatoires">
										<tr class="astreintesObligatoires">
											<td align="center">
												<s:property value="#aObligatoires.type.type" />
											</td>
											<td align="center">
												<s:property value="#aObligatoires.domaine.domaine" />
											</td>
											<s:if test="#session.USER_DROITS.contains('AST_PLN_ADD')">
												<td align="center">
													<a class="ajouter" href=" #" onclick="javascript:ajouterByTypeDomaine('<s:property value="#aObligatoires.id" />');">
														<img class="icone" src="<s:property value="#session.ENSEIGNE"/>/img/ajouter-16.png" />
													</a>
												</td>
											</s:if>
										</tr>
									</s:iterator>
								</s:else>
							</tbody>
						</table>
					</td>
				</tr>
			</table>
			<s:if test="#session.USER_DROITS.contains('AST_PLN_ADD')">
				<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
			</s:if>
			<table class="dataTableAstreintes" rules="all">
				<col width="10%"/>
				<col width="10%"/>
				<col width="15%"/>
				<col width="15%"/>
				<col width="10%"/>
				<col width="12%"/>
				<col width="18%"/>
				<s:if test="#session.USER_DROITS.contains('AST_PLN_MOD') || #session.USER_DROITS.contains('AST_PLN_DEL')">
					<col width="10%"/>
				</s:if>
				<thead>
					<tr>
						<th colspan=<s:if test="#session.USER_DROITS.contains('AST_PLN_MOD') || #session.USER_DROITS.contains('AST_PLN_DEL')">"8"</s:if><s:else>"7"</s:else>>
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</th>
					</tr>
					
					<tr>
						<th align="left" valign="top">Filtre type :<br>
							<s:select list="%{aTypes}" listKey="id" listValue="type" name="filtreType" emptyOption="true" id="filtreType" onchange="javascript:lancerRecherche();"></s:select>
						</th>
						<th align="left" valign="top">Filtre domaine :<br>
							<s:select list="%{aDomaines}" listKey="id" listValue="domaine" name="filtreDomaine" emptyOption="true" onchange="javascript:lancerRecherche();"></s:select>
						</th>
						
						<th align="left" id="datetimepicker1" onkeydown="javascript:disableControls('dateDebut');" onmousedown="javascript:disableControls('dateDebut')">Filtre date d&eacute;but :<br>
							<sj:datepicker name="filtreDebDate" 
											id="dateDebut" 
											displayFormat="dd/mm/yy" 
											showOn="focus"
											onChangeTopics="datepickerChange">
							</sj:datepicker>
							<a href="#" onclick="javascript:reinitialiserDateDebut();">
								<img class="icone" alt="Réinitialiser la date" title="Réinitialiser la date" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" />
							</a>
						</th>
						<th align="left" onkeydown="javascript:disableControls('dateFin');" onmousedown="javascript:disableControls('dateFin')">Filtre date fin :<br>
							<sj:datepicker name="filtreFinDate" 
											id="dateFin" 
											displayFormat="dd/mm/yy" 
											showOn="focus"
											onChangeTopics="datepickerChange">
							</sj:datepicker>
							<a href="#" onclick="javascript:reinitialiserDateFin();">
								<img class="icone" alt="Réinitialiser la date" title="Réinitialiser la date" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" />
							</a>
						</th> 
						<th align="left" valign="top">Filtre astreinte :<br>
							<s:select list="%{astreintes}" listKey="id" listValue="prenom + ' ' + nom" name="filtreAstreinte" emptyOption="true" onchange="javascript:lancerRecherche();"></s:select>
						</th>
						<th align="left" valign="top">Filtre t&eacute;l&eacute;phone :<br>
							<s:textfield name="filtreTelephone" maxlength="20" size="12" onchange="javascript:lancerRecherche();"/>
						</th>
						<th align="left" valign="top">Filtre commentaire :<br>
							<s:textfield name="filtreCommentaire" size="25" onchange="javascript:lancerRecherche();"/>
						</th>
						<s:if test="#session.USER_DROITS.contains('AST_PLN_MOD') || #session.USER_DROITS.contains('AST_PLN_DEL')">
							<th></th>
						</s:if>
					</tr>
					
					<tr>
						<th class="titreColonne">
							<a href="#" onclick="changeSort('type');">Type</a>
							<s:if test="sens == 'asc' && sort == 'type'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if> 
							<s:elseif test="sens == 'desc' && sort == 'type'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif> 
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						
						<th class="titreColonne">
							<a href="#" onclick="changeSort('domaine');">Domaine</a>
							<s:if test="sens == 'asc' && sort == 'domaine'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if>
							<s:elseif test="sens == 'desc' && sort == 'domaine'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</th>
						<th class="titreColonne">
							<a href="#" onclick="changeSort('datedeb');">Date début</a>
							<s:if test="sens == 'asc' && sort == 'datedeb'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if>
							<s:elseif test="sens == 'desc' && sort == 'datedeb'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</th>
						<th class="titreColonne">
							<a href="#" onclick="changeSort('datefin');">Date de fin</a>
							<s:if test="sens == 'asc' && sort == 'datefin'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if>
							<s:elseif test="sens == 'desc' && sort == 'datefin'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</th>
						<th class="titreColonne">
							<a href="#" onclick="changeSort('astreinte');">Asteintes</a>
							<s:if test="sens == 'asc' && sort == 'astreinte'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if>
							<s:elseif test="sens == 'desc' && sort == 'astreinte'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</th>
						<th class="titreColonne">
							<a href="#" onclick="changeSort('tel');">Téléphone</a>
							<s:if test="sens == 'asc' && sort == 'tel'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if>
							<s:elseif test="sens == 'desc' && sort == 'tel'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</th>
						<th class="titreColonne">
							<a href="#" onclick="changeSort('commentaires');">Commentaires</a>
							<s:if test="sens == 'asc' && sort == 'commentaires'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if>
							<s:elseif test="sens == 'desc' && sort == 'commentaires'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</th>
						<s:if test="#session.USER_DROITS.contains('AST_PLN_MOD') || #session.USER_DROITS.contains('AST_PLN_DEL')">
							<th class="titreColonne">Actions</th>
						</s:if>
					</tr>
				</thead>
				<tbody>
					<s:if test="aPlannings.isEmpty()">
						<tr>
							<td class="emptyListText" colspan=<s:if test="#session.USER_DROITS.contains('AST_PLN_MOD') || #session.USER_DROITS.contains('AST_PLN_DEL')">"8"</s:if><s:else>"7"</s:else>>
								Aucun planning trouv&eacute;
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="aPlannings" id="aPlannings" status="stuts">
							<s:if test="(#aPlannings.datedeb).compareTo(new java.util.Date())>0">
								<s:if test="#stuts.odd==true">
									<tr class="astreintePlanningFuturOdd">
								</s:if>
								<s:else>
									<tr class="astreintePlanningFutur">
								</s:else>
							</s:if>
							<s:elseif test="(#aPlannings.datefin).compareTo(new java.util.Date())>0">
								<s:if test="#stuts.odd==true">
									<tr class="astreintePlanningEnCoursOdd">
								</s:if>
								<s:else>
									<tr class="astreintePlanningEnCours">
								</s:else>
							</s:elseif>
								<td align="left"><s:property value="#aPlannings.astreinte.type.type" /></td>
								<td align="left"><s:property value="#aPlannings.domaine.domaine" /></td>
								<td align="center">du&nbsp;<s:date name="#aPlannings.datedeb" format="dd/MM/yyyy" /></td>
								<td align="center">au&nbsp;<s:date name="#aPlannings.datefin" format="dd/MM/yyyy" /></td>
								<td align="center"><s:property value="#aPlannings.astreinte.prenom + ' ' + #aPlannings.astreinte.nom" /></td>
								<td align="center"><s:property value="#aPlannings.tel" /></td>
								<td align="left">
									<s:if test="(#aPlannings.infogene != null)">
										<s:property value="#aPlannings.infogene" /><br>
									</s:if>
									<s:property value="#aPlannings.commentaires" />
								</td>
								<s:if test="#session.USER_DROITS.contains('AST_PLN_MOD') || #session.USER_DROITS.contains('AST_PLN_DEL')">
									<td align="center">
										<s:if test="#session.USER_DROITS.contains('AST_PLN_MOD')">
											<s:a href="#" onclick="javascript:modifier('%{#aPlannings.id}');">
												<img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
											</s:a>
										</s:if>
										<s:if test="#session.USER_DROITS.contains('AST_PLN_DEL')">
											<s:a href="#" onclick="javascript:supprimer('%{#aPlannings.id}');">
												<img class="icone" alt="Supprimer" title="Supprimer"  src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
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
						<td colspan=<s:if test="#session.USER_DROITS.contains('AST_PLN_MOD') || #session.USER_DROITS.contains('AST_PLN_DEL')">"8"</s:if><s:else>"7"</s:else>>
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
				</tfoot>
			</table>
		</s:form>
	</div>
	<s:form action="exportAstreintePlanningAction" theme="simple" id="exportForm">
	</s:form>
</body>
</html>