<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/validationEngine.jquery.css">
	<script type="text/javascript" src="js/jquery.validationEngine.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/languages/jquery.validationEngine-fr.js" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="js/time/jquery.timeentry.css"/>
	<script type="text/javascript" src="js/time/jquery.timeentry.js"></script>
	<script type="text/javascript" src="js/time/jquery.timeentry-fr.js"></script>
	<script type="text/javascript">

		function onLoad(){
			$('#horaireToAdd').timeEntry({show24Hours: true , spinnerImage: '', defaultTime: '23:59:00', showSeconds: true});
		}
	
		$(document).ready(function(){
	    	$("#mainForm").validationEngine();
	  	});
		function valider(){
			$("#mainForm").validationEngine('validate');
		}
		function addMeteo() {
			if (document.getElementById("meteoToAdd").value != -1) {
				submitData('showModifyGroupeMeteoAction.action');
			}
			else{
				alert('Veuillez sélectionner une météo');
			}
		}

		function addDestinataire(){
			if (document.getElementById("destinataireToAdd").value != -1) {
				submitData('showModifyGroupeMeteoAction.action');
			}
			else{
				alert("Veuillez sélectionner un destinataire");
			}
		}
		
		function supprimerMeteo(id){
			changeData('deleteMeteo', id);
			submitData('showModifyGroupeMeteoAction.action');
		}

		function supprimerDestinataire(id){
			changeData('deleteDestinataire', id);
			submitData('showModifyGroupeMeteoAction.action');
		}

		function addHoraire(){
			if (document.getElementById("horaireToAdd").value != "") {
				submitData('showModifyGroupeMeteoAction.action');
			}
			else{
				alert("Veuillez saisir un horaire");
			}
		}

		function supprimerHoraire(horaire){
			changeData('deleteHoraire', horaire);
			submitData('showModifyGroupeMeteoAction.action');
		}

		function retour(){
			submitData('showDetailGroupeMeteoAction.action');
		}
	</script>
</head>
<body onload="onLoad();">
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MTG_GRM_M')" /></s:param>
	</s:include>

	<div class="contentTable" style="width: 45%">
		<s:form id="mainForm" theme="simple" action="modifyGroupeMeteoAction" method="POST">
		
			<s:token></s:token>
			<s:hidden name="groupeMeteoID" id="groupeMeteoID" value="%{groupeMeteo.id}"/>
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage"/>
			<s:hidden name="nrPages" id="nrPages" />
			<s:hidden name="deleteMeteo" id="deleteMeteo" value="-1"/>
			<s:hidden name="deleteDestinataire" id="deleteDestinataire" value="-1"/>
			<s:hidden name="deleteHoraire" id="deleteHoraire" value="-1"/>

			<table class="formTable">
				<tr>
					<td align="left">Nom du Groupe Météo <span class="starObligatoire">*</span> :</td>
					<td align="left"><s:textfield id="nomGroupeMeteo" name="nomGroupeMeteo" value="%{nomGroupeMeteo}" size="45" data-validation-engine="validate[required]"/></td>
				</tr>	
				<tr>
					<td align="left">Format Excel du Groupe Météo <span class="starObligatoire">*</span> :</td>
					<td>
						<select id="idFormat" name="idFormat" data-validation-engine="validate[required]">
							<option value=''></option>
							<s:iterator value="listFormats" id="listFormats">
								<s:if test="id==idFormat">
									<option selected value=<s:property value="id" />><s:property value="format" /></option>
								</s:if>
								<s:else>
									<option value=<s:property value="id" />><s:property value="format" /></option>
								</s:else>
							</s:iterator>
						</select>
					</td>
				</tr>
				<tr>
					<td align="left">Timezone <span class="starObligatoire">*</span> :</td>
					<td>
						<select id="idFuseau" name="idFuseau" data-validation-engine="validate[required]">
							<option value=''></option>
							<s:iterator value="listFuseaux" id="listFuseaux">
								<s:if test="timezone.equals(fuseau)">
									<option selected value=<s:property value="id" />><s:property value="timezone" /></option>
								</s:if>
								<s:else>
									<option value=<s:property value="id" />><s:property value="timezone" /></option>
								</s:else>
							</s:iterator>
						</select>
					</td>
				</tr>		
			</table>
			<div class="titreTable"><b>Météos</b></div>
			<div class="plus">
				Ajouter :
				<s:select id="meteoToAdd" name="meteoToAdd" listKey="id" listValue="nom_meteo" headerKey="-1" headerValue="" list="%{meteoList}"/>
				<s:a href="#" onclick="javascript:addMeteo()">
					<img class="icone" alt="Ajouter" title="Ajouter" src="<s:property value="#session.ENSEIGNE" />/img/valider-16.png"/>
				</s:a>
			</div>
			<table class="dataTable" rules="all">
				<col width="80%"/>
				<col width="20%"/>
				<thead>
					<tr>
						<th class="titreColonne">Météo</th>
						<th class="titreColonne">Supprimer</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="listMeteos.isEmpty()">
						<tr>
							<td class="emptyListText" colspan="2">Aucune météo</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator id="listMeteos" value="listMeteos" status="stat">

							<tr>
								<td align="left">
									&nbsp;&nbsp;<s:property value="meteoName"/>
								</td>
								<td align="center">
									<a href="#" onclick="javascript:supprimerMeteo(<s:property value="meteoID"/>);">
										<img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
									</a>
									<input type="hidden" id="meteo<s:property value="#stat.index" />" name="meteo<s:property value="#stat.index" />" value="<s:property value="meteoID"/>"/>
								</td>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
			</table>
			
			<div class="titreTable"><b>Destinataires</b></div>
			<div class="plus">
				Ajouter :
				<select id="destinataireToAdd" name="destinataireToAdd" >
					<option value='-1'></option>
					<s:iterator value="destinataireList">							
					<option value='<s:property value='id'/>'><s:property value='nom'/> <s:property value='prenom'/></option>
					</s:iterator>
				</select>
				
				<s:a href="#" onclick="javascript:addDestinataire()">
					<img class="icone" alt="Ajouter" title="Ajouter" src="<s:property value="#session.ENSEIGNE" />/img/valider-16.png"/>
				</s:a>
			</div>
			<table class="dataTable" rules="all">
				<col width="80%"/>
				<col width="20%"/>
				<thead>
					<tr>
						<th class="titreColonne">Destinataire</th>
						<th class="titreColonne">Supprimer</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="listDestinataires.isEmpty()">
						<tr>
							<td class="emptyListText" colspan="2">Aucun destinataire</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listDestinataires" status="stat">
							<tr>
								<td align="left">
									&nbsp;&nbsp;<s:property value="destNom"/> <s:property value="destPrenom"/>
								</td>
								<td align="center">
									<a href="#" onclick="javascript:supprimerDestinataire(<s:property value="destId"/>);">
										<img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
									</a>
									<input type="hidden" id="destinataire<s:property value="#stat.index" />" name="destinataire<s:property value="#stat.index" />" value="<s:property value="destId"/>"/>
								</td>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
			</table>
			
			<div class="titreTable"><b>Horaires</b></div>
			<div class="plus">
				Ajouter :
				<input type="text" id="horaireToAdd" name="horaireToAdd" size=6/>
				
				<s:a href="#" onclick="javascript:addHoraire()">
					<img class="icone" alt="Ajouter" title="Ajouter" src="<s:property value="#session.ENSEIGNE" />/img/valider-16.png"/>
				</s:a>
			</div>
			<table class="dataTable" rules="all">
				<col width="80%"/>
				<col width="20%"/>
				<thead>
					<tr>
						<th class="titreColonne">Horaire</th>
						<th class="titreColonne">Supprimer</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="listHoraires.isEmpty()">
						<tr>
							<td class="emptyListText" colspan="2">Aucun horaire</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listHoraires" status="stat">
							<tr>
								<td align="left">
									&nbsp;&nbsp;<s:property value="horaire"/>
								</td>
								<td align="center">
									<a href="#" onclick='javascript:supprimerHoraire("<s:property value="horaire"/>");'>
										<img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
									</a>
									<input type="hidden" id="horaire<s:property value="#stat.index" />" name="horaire<s:property value="#stat.index" />" value="<s:property value="horaire"/>"/>
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