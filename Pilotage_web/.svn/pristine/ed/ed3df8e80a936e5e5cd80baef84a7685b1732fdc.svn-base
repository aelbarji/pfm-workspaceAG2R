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

		//reinitialisation des filtres 
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
    	})
		
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('AST_HISTO')" /></s:param>
	</s:include>

	<s:form id="mainForm" theme="simple" action="showHistoricAstreintePlanning">
		<s:hidden id="astObligatoire" name="astObligatoire" value="" />
		<s:hidden id="selectRow" name="selectRow" value="" />
		<s:hidden name="page" id="page" />
		<s:hidden name="sort" id="sort" />
		<s:hidden name="sens" id="sens" />
		<s:hidden name="nrPerPage" id="nrPerPage"/>
		<s:hidden name="nrPages" value="%{pagination.nrPages}" />
		<s:hidden name="validForm" value="1" />
		<s:token></s:token>
		
		<div class="contentTable">
			<table class="dataTableAstreinteHistorique" rules="all">
				<col width="10%"/>
				<col width="10%"/>
				<col width="18%"/>
				<col width="18%"/>
				<col width="10%"/>
				<col width="12%"/>
				<col width="22%"/>
				<thead>
					<tr>
						<td colspan="7">
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
					
					<tr>
						<td align="left" valign="top">Filtre type :<br>
							<s:select list="%{aTypes}" listKey="id" listValue="type" name="filtreType" emptyOption="true" id="filtreType" onchange="javascript:lancerRecherche();"></s:select>
						</td>
						<td align="left" valign="top">Filtre domaine :<br>
							<s:select list="%{aDomaines}" listKey="id" listValue="domaine" name="filtreDomaine" emptyOption="true" onchange="javascript:lancerRecherche();"></s:select>
						</td>
						
						<td align="left" id="datetimepicker1" onkeydown="javascript:disableControls('dateDebut');" onmousedown="javascript:disableControls('dateDebut')">Filtre date d&eacute;but :<br>
							<sj:datepicker name="filtreDebDate" 
											id="dateDebut" 
											displayFormat="dd/mm/yy" 
											showOn="focus"
											onChangeTopics="datepickerChange">
							</sj:datepicker>
							<a href="#" onclick="javascript:reinitialiserDateDebut();">
								<img class="icone" alt="Réinitialiser la date" title="Réinitialiser la date" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" />
							</a>
						</td>
						<td align="left" onkeydown="javascript:disableControls('dateFin');" onmousedown="javascript:disableControls('dateFin')">Filtre date fin :<br>
							<sj:datepicker name="filtreFinDate" 
											id="dateFin" 
											displayFormat="dd/mm/yy" 
											showOn="focus"
											onChangeTopics="datepickerChange">
							</sj:datepicker>
							<a href="#" onclick="javascript:reinitialiserDateFin();">
								<img class="icone" alt="Réinitialiser la date" title="Réinitialiser la date" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" />
							</a>
						</td> 
						<td align="left" valign="top">Filtre astreinte :<br>
							<s:select list="%{astreintes}" listKey="id" listValue="prenom + ' ' + nom" name="filtreAstreinte" emptyOption="true" onchange="javascript:lancerRecherche();"></s:select>
						</td>
						<td align="left" valign="top">Filtre t&eacute;l&eacute;phone :<br>
							<s:textfield name="filtreTelephone" maxlength="20" size="12" onchange="javascript:lancerRecherche();"/>
						</td>
						<td align="left" valign="top">Filtre commentaire :<br>
							<s:textfield name="filtreCommentaire" size="25" onchange="javascript:lancerRecherche();"/>
						</td>
					</tr>					
					<tr>
						<td class="titreColonne">
							<a href="#" onclick="changeSort('type');">Type</a>
							<s:if test="sens == 'asc' && sort == 'type'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if> <s:elseif test="sens == 'desc' && sort == 'type'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif> <s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</td>
						<td class="titreColonne">
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
						</td>
						<td class="titreColonne">
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
						</td>
						<td class="titreColonne">
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
						</td>
						<td class="titreColonne">
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
						</td>
						<td class="titreColonne">
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
						</td>
						<td class="titreColonne">
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
						</td>
					</tr>
				</thead>
				<tbody>
					<s:if test="aPlannings.isEmpty()">
						<tr>
							<td colspan="7" class="emptyListText">Aucun planning trouv&eacute;</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="aPlannings" id="aPlannings" status="stuts">
							<s:if test="#stuts.odd==true">
								<tr class="astreintePlanningPasseeOdd">
							</s:if>
							<s:else>
								<tr class="astreintePlanningPassee">
							</s:else>
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
									<s:property value="#aPlannings.commentaires" /></td>
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