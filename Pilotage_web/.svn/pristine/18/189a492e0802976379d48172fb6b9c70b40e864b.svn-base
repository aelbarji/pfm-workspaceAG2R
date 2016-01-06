<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<link rel="stylesheet" type="text/css" href="js/time/jquery.timeentry.css">
	<script type="text/javascript" src="js/time/jquery.timeentry.js"></script>
	<script type="text/javascript" src="js/time/jquery.timeentry-fr.js"></script>
	<script type="text/javascript" src="js/time/jquery.mousewheel.min.js"></script>
	<script type="text/javascript">

	function ajouter() {
		submitData('redirectCreateAppelAstreinteAction.action');
	}

	function retour(){
		submitData('showAppelAstreinteAction.action');
	}

	function modifier(id){
		changeData("selectRow", id);
		submitData('redirectModifyAppelAstreinteAction.action');
	}

	function supprimer(id){
		changeData("selectRow", id);
		submitData('deleteAppelAstreinteAction.action');
	}
	
	</script>
	</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('AST_APP_D')" /></s:param>
	</s:include>
	<div class="contentTable" style="width:60%">
		<s:form id="mainForm" theme="simple">
			<s:hidden id="astreinte" name="astreinte" />
			<s:hidden id="provenance" name="provenance"/>
			<s:hidden id="last_provenance" name="last_provenance"/>
			<s:hidden id="incident" name="incident"/>
			<s:hidden id="selectRow" name="selectRow"/>
			<s:hidden id="selectedID" name="selectedID"/>
			<s:hidden id="validAst" name="validAst"/>
			<s:hidden id="typeSelected" name="typeSelected"/>
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage"/>
			<s:hidden name="nrPages" id="nrPages" />
			<s:hidden name="pageIncident" id="pageIncident" />
			<s:hidden name="nrPerPageIncident" id="nrPerPageIncident" />
			<s:hidden name="nrPagesIncident" id="nrPagesIncident" />
				
			<div class="titreCenter">Liste des appels du <b><s:property value="date"/></b> de l'astreinte <b><s:property value="listAppel[0].astreinte.prenom+ ' ' + listAppel[0].astreinte.nom" /></b>
			pour l'incident de r&eacute;f&eacute;rence <b><s:property value="listAppel[0].incident.id"/></b></div>
			
			<s:if test="#session.USER_DROITS.contains('MTG_GRM_ADD')">
					<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
			</s:if>
			
			<table class="dataTable" rules="all">
				<thead>
				<tr valign="middle">
						<td class="titreColonne">Heure</td>
						<td class="titreColonne" >Statut</td>
						<td class="titreColonne" >Commentaire</td>
						<td class="titreColonne" >Actions</td>
				</thead>
				<tbody>
					<s:if test="listAppel.isEmpty()">
						<tr>
							<td class="emptyListText" colspan=<s:if test="#session.USER_DROITS.contains('AST_APP_MOD') || #session.USER_DROITS.contains('AST_APP_DEL')">"8"</s:if><s:else>"7"</s:else>>
								Aucun appel trouv&eacute;
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listAppel" id="listAppel" status="stat">
							<tr>
								<td align="center"><s:date name="%{#listAppel.date}" format=" dd/MM/yyyy HH:mm:ss" /></td>
								<td align="center"><s:property value="#listAppel.statut.statut" /></td>
								<td align="center"><s:property value="#listAppel.commentaire"/></td>
								<td class="td" align="center">
									<s:if test="#session.USER_DROITS.contains('AST_APP_MOD')">
										<a href="#" onclick="javascript:modifier(<s:property value="#listAppel.id"/>);">
											<img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png">
										</a>
									</s:if>
									<s:if test="#session.USER_DROITS.contains('AST_APP_DEL')">
										<a href="#" onclick="javascript:supprimer(<s:property value="#listAppel.id"/>);">
											<img class="icone"  alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png">
										</a>
									</s:if>		
								</td>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
			</table>
			<div class="contentTableBottom">
				<div class="pageRight">
					<s:a href="#" onclick="javascript:retour();" cssClass="boutonRetour">Retour</s:a>
				</div>
			</div>
		</s:form>
	</div>
</body>
</html>