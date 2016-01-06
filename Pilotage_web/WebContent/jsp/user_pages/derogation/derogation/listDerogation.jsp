<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />

	<link rel="stylesheet" type="text/css" href="js/time/jquery.timeentry.css"/>
	<script type="text/javascript" src="js/time/jquery.timeentry.js"></script>
	<script type="text/javascript" src="js/time/jquery.timeentry-fr.js"></script>
	<script type="text/javascript" src="js/time/jquery.mousewheel.min.js"></script>
	<script type="text/javascript">

		//reinitialisation des filtres comme à l'entrée de la page
		function reinitFilterValues() {
			document.getElementById("mainForm").filtreNumero.value = '<s:property value="filtreNumero" />';
			$("#filtreDate").val('<s:property value="filtreDate" />');
			document.getElementById("mainForm").filtreAppli.value = '<s:property value="filtreAppli" />';
			document.getElementById("mainForm").filtreTp.value = '<s:property value="filtreTp" />';
			document.getElementById("mainForm").filtreDtc.value = '<s:property value="filtreDtc" />';
			document.getElementById("mainForm").filtreEtat.value = '<s:property value="filtreEtat" />';
		}
		//action à la recherche avec filtre
		function lancerRecherche() {
			document.getElementById("mainForm").page.value = '1';
			document.getElementById("mainForm").submit();
		}

		//action de changement de Nombre d'entrée par page
		function change() {
			var selectPage = document.getElementById("selectPage").value;
			changeData("selectNum", selectPage);
			document.getElementById("mainForm").submit();
		}

		//action de changement de page
		function changePage(nb) {
			changeData("page", nb);
			document.getElementById("mainForm").submit();
		}

		function valider(id, url){
			changeData('selectRow', id);
			submitData(url);
		}

		function supprimer(id){
		    if(confirm("Voulez-vous vraiment supprimer cette dérogation ?")){
			   changeData('selectRow', id);
			   submitData('supprimerDerogationAction.action');
		    }   
		}

		function ajouter(){
			submitData('redirectCreateDerogationAction.action');
		}
		
        //reinitialisation des filtres 
		function reinitialiserLesFiltres(){
			document.getElementById("mainForm").filtreNumero.value = '';
			$("#filtreDate").val("");
			document.getElementById("mainForm").filtreAppli.value = '-1';
			document.getElementById("mainForm").filtreTp.value = '-1';
			document.getElementById("mainForm").filtreDtc.value = '-1';
			document.getElementById("mainForm").filtreEtat.value = '-1';
			lancerRecherche();
		}	

		function reinitialiserFiltreDate(){
			$("#filtreDate").val("");
			lancerRecherche();
		}	

        $.subscribe('filtreDateChange',function(event,data) {
        	lancerRecherche();
    	})

	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('DRG_LST')" /></s:param>
	</s:include>
	
	<s:form id="mainForm" name="mainForm" action="showDerogationAction" theme="simple">
	<s:token></s:token>
	<div class="contentTable" >
		<s:if test="#session.USER_DROITS.contains('DRG_ADD')">
			<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
		</s:if>
		
		<table class="dataTable" rules="all">
			<thead>
				<tr>
					<td colspan="7">
						<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
					</td>
				</tr>
				
				<tr>
					<td align="left" valign="top">Filtre num&eacute;ro :<br/>
						<s:hidden name="selectRow" id="selectRow" value="" />
						<s:hidden name="id" id="id" />
			
						<s:hidden name="sort" id="sort" />
						<s:hidden name="sens" id="sens" />
						<s:hidden name="page" id="page" />
						<s:hidden name="nrPerPage" id="nrPerPage"/>
						<s:hidden name="nrPages" value="%{pagination.nrPages}" />
						<s:hidden name="validForm" value="1" />
						<s:textfield name="filtreNumero" id="filtreNumero" maxlength="8" size="10" onchange="javascript:lancerRecherche();"/>
					</td>
					<td align="left" id="datetimepicker1" onkeydown="javascript:disableControls('filtreDate');" onmousedown="javascript:disableControls('filtreDate')">Filtre date :<br/>
						<sj:datepicker name="filtreDate" 
										id="filtreDate" 
										displayFormat="dd/mm/yy" 
										showOn="focus"
										onChangeTopics="filtreDateChange">
						</sj:datepicker>								
						<a href="#" onclick="javascript:reinitialiserFiltreDate();">
							<img class="icone" alt="Réinitialiser la date" title="Réinitialiser la date" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" />
						</a>
					</td>
					<td align="left" valign="top">Filtre applicatif :<br/>
						<s:select name="filtreAppli" id="filtreAppli" list="appList" listKey="id" listValue="applicatif" headerValue="" headerKey="-1" onchange="javascript:lancerRecherche();"></s:select>
					</td>
					<td align="left" valign="top">Filtre tp/ouvert :<br/>
						<s:select name="filtreTp" id="filtreTp" list="#{'-1':'','1':'Oui','0':'Non'}" onchange="javascript:lancerRecherche();"/>
					</td>
					<td align="left" valign="top">Filtre type de changement :<br/>
						<s:select name="filtreDtc" id="filtreDtc" list="dtcList" listKey="id" listValue="typeChangement" headerKey="-1" headerValue="" onchange="javascript:lancerRecherche();"></s:select>
					</td>
					<td align="left" valign="top">Filtre &eacute;tat :<br/>
						<s:select name="filtreEtat" id="filtreEtat" list="etatList" listKey="id" listValue="etat" headerKey="-1" headerValue="" onchange="javascript:lancerRecherche();"></s:select>
					</td>
					<td></td>
				</tr>

				<tr>
					<th class="titreColonne">
						<a href="#" onclick="changeSort('id');">Numéro</a>
						<s:if test="sens == 'asc' && sort == 'id'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if> 
						<s:elseif test="sens == 'desc' && sort == 'id'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</th>
					<th class="titreColonne">
						<a href="#" onclick="changeSort('timeDemande');">Date de mise en place</a>
						<s:if test="sens == 'asc' && sort == 'timeDemande'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if> 
						<s:elseif test="sens == 'desc' && sort == 'timeDemande'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</th>
					<th class="titreColonne">
						<a href="#" onclick="changeSort('idAppli');">Application</a>
						<s:if test="sens == 'asc' && sort == 'idAppli'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if> 
						<s:elseif test="sens == 'desc' && sort == 'idAppli'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</th>
					<th class="titreColonne">
						<a href="#" onclick="changeSort('tp');">TP/Ouvert</a>
						<s:if test="sens == 'asc' && sort == 'tp'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if> 
						<s:elseif test="sens == 'desc' && sort == 'tp'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</th>
					<th class="titreColonne">
						<a href="#" onclick="changeSort('typeChangement');">Type de changement</a>
						<s:if test="sens == 'asc' && sort == 'typeChangement'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if> 
						<s:elseif test="sens == 'desc' && sort == 'typeChangement'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</th>
					<th class="titreColonne">
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
					</th>
					<th class="titreColonne">Action</th>
				</tr>
			</thead>
			<tbody>
				<s:if test="derogationList.isEmpty()">
					<tr>
						<td colspan="7" class="emptyListText">Aucune d&eacute;rogation trouv&eacute;e</td>
					</tr>
				</s:if>
				<s:else>
					<s:iterator value="derogationList">
						<tr>
							<td align="left">&nbsp;&nbsp;&nbsp;<s:property value="id" /></td>
							<td align="left">&nbsp;&nbsp;&nbsp;<s:property value="timeDemande"/></td>
							<td align="left">&nbsp;&nbsp;&nbsp;<s:property value="idAppli.applicatif"/></td>
							<td align="left">&nbsp;&nbsp;&nbsp;<s:if test="tp == 1">Oui</s:if><s:if test="tp == 0">Non</s:if></td>
							<td align="left">&nbsp;&nbsp;&nbsp;<s:property value="typeChangement.typeChangement"/></td>
							<td align="left">&nbsp;&nbsp;&nbsp;<s:property value="etat.etat"/></td>
							<td align="center">
								<s:a href="#" onclick="javascript:valider('%{id}', 'showDetailDerogationAction.action');"><img alt="Détail" title="Détail" src="<s:property value="#session.ENSEIGNE" />/img/detail-16.png" /></s:a>
								<s:if test="etat.id != 2 && etat.id != 3 && etat.id != 4 && etat.id != 5 && (#session.USER_LOGGED.id == idNomDemandeur.id || #session.USER_LOGGED.id == idNomCreateur.id)">
									<s:if test="#session.USER_DROITS.contains('DRG_MOD')">
										<s:a href="#" onclick="javascript:valider('%{id}', 'redirectModifyDerogationAction.action');"><img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" /></s:a>
									</s:if>
									<s:a href="#" onclick="javascript:valider('%{id}', 'envoyerDerogationAction.action');"><img class="icone" alt="Envoyer" title="Envoyer" src="<s:property value="#session.ENSEIGNE" />/img/envoyer-16.png" /></s:a>
									<s:if test="#session.USER_DROITS.contains('DRG_DEL')">
										<s:a href="#" onclick="javascript:supprimer('%{id}');"><img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" /></s:a>
									</s:if>
								</s:if>
							</td>
						</tr>
					</s:iterator>
				</s:else>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="7">
						<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
	</s:form>
</body>
</html>