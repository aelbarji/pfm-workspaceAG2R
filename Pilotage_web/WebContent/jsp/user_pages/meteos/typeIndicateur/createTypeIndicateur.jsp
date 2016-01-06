<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/validationEngine.jquery.css">
	<script type="text/javascript" src="js/jquery.validationEngine.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/languages/jquery.validationEngine-fr.js" charset="utf-8"></script>
	<script type="text/javascript">
	
	$(document).ready(function(){
		$("#mainForm").validationEngine();
	});

	function valider(){
		$("#mainForm").validationEngine('validate');
	}

		function addEtat() {
			if (document.getElementById("etatToAdd").value != -1) {
				submitData('showCreateTypeIndicateurAction.action');
			}
			else{
				alert('Veuillez sélectionner un état');
			}
		}

		function supprimerEtat(id){
			changeData('deleteEtat', id);
			submitData('showCreateTypeIndicateurAction.action');
		}

		function retour(){
			submitData('showTypeIndicateurAction.action');
		}
		
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MTG_TYP_A')" /></s:param>
	</s:include>

	<div class="contentTable" style="width: 45%">
		<s:form id="mainForm" theme="simple" action="createTypeIndicateurAction" method="POST">
			<s:token></s:token>
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage"/>
			<s:hidden name="nrPages" id="nrPages" />
			<s:hidden name="deleteEtat" id="deleteEtat" value="-1"/>

			<table class="formTable">
				<tr>
					<td align="left">Nom du type d'indicateur <span class="starObligatoire">*</span> :</td>
					<td align="left"><s:textfield name="type" value="%{type}" size="35" data-validation-engine="validate[required]"/></td>
				</tr>	
			</table>
			
			<div class="titreTable"><b>Etats</b></div>
			<div class="plus">
				Ajouter :
				<s:select id="etatToAdd" name="etatToAdd" listKey="id" listValue="libelle_etat" headerKey="-1" headerValue="" list="%{etatList}"/>
				<s:a href="#" onclick="javascript:addEtat()">
					<img class="icone" alt="Ajouter" title="Ajouter" src="<s:property value="#session.ENSEIGNE" />/img/valider-16.png"/>
				</s:a>
			</div>
			<table class="dataTable" rules="all">
				<col width="20%"/>
				<col width="5%"/>
				<col width="40%"/>
				<thead>
					<tr>
						<td class="titreColonne" >Libellé</td>
						<td class="titreColonne" >Couleur</td>
						<td class="titreColonne" >Définition</td>
						<td class="titreColonne" >Actions</td>
					</tr>
				</thead>
				<tbody>
					<s:if test="listEtat.isEmpty()">
						<tr>
							<td class="emptyListText" colspan="4">Aucun état</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listEtat" status="stat">
							<tr>
								<td>&nbsp;&nbsp;<s:property value="etatName"/></td>
								<td style="background-color:<s:property value="etatCouleur"/>;"></td>
								<td>&nbsp;&nbsp;&nbsp;<s:property value="etatDefinition"/></td>
								<td align="center">
									<a href="#" onclick="javascript:supprimerEtat(<s:property value="etatID"/>);">
										<img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
									</a>
									<input type="hidden" id="etat<s:property value="#stat.index" />" name="etat<s:property value="#stat.index" />" value="<s:property value="etatID"/>"/>
								</td>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
			</table>
	
			<s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom_submit.jsp" />
		</s:form>
	</div>
</body>
</html>