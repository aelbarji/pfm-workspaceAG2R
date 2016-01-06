<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function ajouter() {
			submitData('showCreateFeedbackAction.action');
		}
		
		function supprimer(id, url) {
			if(confirm("Voulez-vous vraiment supprimer ce feedback ?")) {
				changeData("selectRow", id);
				submitData(url);
			}
		}

		function valider(id, url) {           
			changeData("selectRow", id);
			submitData(url);              
		}
			
		function changeFiltre(a) {
			reinitPageValues();
			document.getElementById("mainForm").submit();
		}
		
		//reinitialisation des filtres comme à l'entrée de la page
		function reinitFilterValues() {
			document.getElementById("mainForm").filtreFeedbacks.value = '<s:property value="filtreFeedbacks" escape="false"/>';
		}
		
		//reinitialisation de page comme à l'entrée de la page
		function reinitPageValues() {
			document.getElementById("mainForm").page.value = '1';
			document.getElementById("mainForm").nrPerPage.value = '<s:property value="nrPerPage" escape="false"/>';
			document.getElementById("mainForm").nrPages.value = '<s:property value="nrPages" escape="false"/>';
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('FEED_LST')" /></s:param>
	</s:include>
	
	<div class="contentTable" >
		<s:form id="mainForm" theme="simple">
			<s:token></s:token>
			<s:hidden id="selectRow" name="selectRow" value=""></s:hidden>  
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage" />
			<s:hidden name="nrPages" id="nrPages" />
			
			<s:if test="#session.USER_DROITS.contains('FED_ADD')">
				<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
				<br/>
			</s:if>
			
			<div class="plusDiv">
				Filtre : 
				<input type="radio" id="etatTous" name="filtreFeedbacks"
					value="0" onclick="javascript:changeFiltre(0)"
					<s:if test="filtreFeedbacks == 0">checked="checked"</s:if>/>
					tous les feedbacks
		
				<input type="radio" id="etatEnAttente" name="filtreFeedbacks"
					value="1" onclick="javascript:changeFiltre(1)"
					<s:if test="filtreFeedbacks == 1">checked="checked"</s:if>/>
					feedbacks en attente
		
				<input type="radio" id="etatValide" name="filtreFeedbacks"
					value="2" onclick="javascript:changeFiltre(2)"
					<s:if test="filtreFeedbacks == 2">checked="checked"</s:if>/>
					feedbacks validés
	
				<input type="radio" id="etatAnnule" name="filtreFeedbacks"
					value="3" onclick="javascript:changeFiltre(3)"
					<s:if test="filtreFeedbacks == 3">checked="checked"</s:if>/>
					feedbacks annulés
			</div>
		</s:form>

		<table class="dataTable" rules="all" cellpadding="5px">
			<thead>
				<tr>
					<td colspan="5">
						<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
					</td>
				</tr>
				<tr>
					<th class="titreColonne">Feedback</th>
					<th class="titreColonne">Date</th>
					<th class="titreColonne">Etat</th>
					<th class="titreColonne">L’auteur du feedback</th>
					<th class="titreColonne">Action</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="bugList">
					<tr>
						<td width="50%" align="left"><s:property value='bug.replaceAll("<", "&lt;").replaceAll("\n", "<br/>")'  escape="false"/></td>
						<td width="17%" align="center"><s:date name="%{date}" format="dd/MM/yyyy HH:mm:ss" /></td>
						<td width="8%" 	align="left">&nbsp;&nbsp;<s:property value="etat" /></td>
						<td width="16%" align="left">&nbsp;&nbsp;<s:property value="idUser.nom + ' ' + idUser.prenom" /></td>
						<td width="9%"	align="center">
							<s:if test="#session.USER_PROFIL.admin">
								<s:if test="etat == 'En attente'">
									<s:if test="#session.USER_DROITS.contains('FED_MOD')">
										<s:a href="#" onclick="javascript:valider('%{id}','redirectModifyFeedbackAction.action');">
											<img class="icone" src="<s:property value="#session.ENSEIGNE"/>/img/modifier-16.png" title="Modifier" />
										</s:a>
									</s:if>
									<s:a href="#" onclick="javascript:valider('%{id}','validerFeedbackAction.action');">
										<img class="icone" src="<s:property value="#session.ENSEIGNE"/>/img/valider-16.png" title="Valider" />
									</s:a>
									<s:a href="#" onclick="javascript:valider('%{id}','annulerFeedbackAction.action');">
										<img class="icone" src="<s:property value="#session.ENSEIGNE"/>/img/annuler-16.png" title="Annuler" />
									</s:a>
									<s:if test="#session.USER_DROITS.contains('FED_DEL')">
										<s:a href="#" onclick="javascript:supprimer('%{id}','supprimerFeedbackAction.action');">
											<img class="icone" src="<s:property value="#session.ENSEIGNE"/>/img/supprimer-16.png" title="Supprimer" />
										</s:a>
									</s:if>
								</s:if>
								<s:else>
									<s:a href="#" onclick="javascript:valider('%{id}','attenteFeedbackAction.action');">
										<img class="icone" src="<s:property value="#session.ENSEIGNE"/>/img/attention-16.png" title=" Mise en attente" />
									</s:a>
								</s:else>
							</s:if>
							<s:else>&nbsp</s:else>
						</td>
					</tr>
				</s:iterator>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="5"><s:include value="/jsp/user_pages/pagination/pagination.jsp" /></td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>