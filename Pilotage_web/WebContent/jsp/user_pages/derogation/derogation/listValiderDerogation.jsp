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
			document.getElementById("mainForm").submit();
		}

		function valider(id, url){
			changeData('selectRow', id);
			submitData(url);
		}

		function annuler(id){
		    if(confirm("Voulez-vous vraiment annuler cette dérogation ?")){
			   changeData('selectRow', id);
			   submitData('annulerDerogationAction.action');
		    }   
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
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('DRG_V_L')" /></s:param>
	</s:include>
	<s:form id="mainForm" name="mainForm" action="showValiderDerogationAction" theme="simple">
	<s:if test="isValideur == true">
		<s:if test="filtreString != '' && filtreString != null">
			<div id="filtreString" class="filtreString">
				<u><b>Filtres</b></u> <s:property value="%{filtreString}" /><br/>
				<a href="#" onclick="javascript:reinitialiserLesFiltres();">
					<img class="icone" alt="Réinitialiser les filtres" title="Réinitialiser les filtres" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" />
				</a>
			</div>
		</s:if>
		
		<div class="contentTable" >	
			<table class="dataTable" rules="all">
				<thead>
					<tr>
						<td align="left" valign="top">Filtre num&eacute;ro :<br/>
							<s:hidden name="fromValidation" value="true"/>
							<s:hidden name="selectRow" id="selectRow" value="" />
							<s:hidden name="id" id="id" />
					
							<s:hidden name="sort" id="sort" />
							<s:hidden name="sens" id="sens" />
							<s:hidden name="page" id="page" />
							<s:hidden name="nrPerPage" id="nrPerPage"/>
							<s:hidden name="nrPages" value="%{pagination.nrPages}" />
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
							<td colspan="7" class="emptyListText">Aucune d&eacute;rogation &agrave; valider</td>
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
									<s:a href="#" onclick="javascript:valider('%{id}', 'showDetailDerogationAction.action');"><img class="icone" alt="Détail" title="Détail" src="<s:property value="#session.ENSEIGNE" />/img/detail-16.png" /></s:a>
									<s:a href="#" onclick="javascript:valider('%{id}', 'envoyerDerogationValidationAction.action');"><img class="icone" alt="Envoyer" title="Envoyer" src="<s:property value="#session.ENSEIGNE" />/img/envoyer-16.png" /></s:a>
									<s:a href="#" onclick="javascript:valider('%{id}', 'redirectModifyDerogationAction.action');"><img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" /></s:a>
									<s:a href="#" onclick="javascript:valider('%{id}', 'validerDerogationAction.action');"><img class="icone" alt="Valider" title="Valider" src="<s:property value="#session.ENSEIGNE" />/img/valider-16.png" /></s:a>
									<s:a href="#" onclick="javascript:valider('%{id}', 'retournerDerogationAction.action');"><img class="icone" alt="Retourner" title="Retourner" src="<s:property value="#session.ENSEIGNE" />/img/retourner-16.png" /></s:a>
									<s:a href="#" onclick="javascript:valider('%{id}', 'suspendreDerogationAction.action');"><img class="icone" alt="Suspendre" title="Suspendre" src="<s:property value="#session.ENSEIGNE" />/img/suspendre-16.png" /></s:a>
									<s:a href="#" onclick="javascript:annuler('%{id}');"><img class="icone" alt="Annuler" title="Annuler" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" /></s:a>
								</td>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
			</table>
		</div>		
	</s:if>
	</s:form>
</body>
</html>