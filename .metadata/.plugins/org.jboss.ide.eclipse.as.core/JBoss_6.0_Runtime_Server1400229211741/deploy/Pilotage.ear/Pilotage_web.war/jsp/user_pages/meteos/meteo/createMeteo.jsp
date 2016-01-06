<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
	
		function valider() {
			var mainForm = document.getElementById("mainForm");
			if (mainForm.nomMeteo.value.replace(/^\s+/g, '').replace(/\s+$/g, '') == "") {
				alert("Veuillez entrer le nom");
				document.getElementById("nomMeteo").focus();
				return false;
			}
			mainForm.submit();
		}	

		function addService() {
			if (document.getElementById("serviceToAdd").value != -1) {
				submitData('showCreateMeteoAction.action');
			}
			else{
				alert('Veuillez sélectionner un service');
			}
		}

		function supprimerService(id){
			changeData('deleteService', id);
			submitData('showCreateMeteoAction.action');
		}

		function retour(){
			submitData('showMeteoAction.action');
		}
		
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MTG_MET_A')" /></s:param>
	</s:include>

	<div class="contentTable" style="width: 45%">
		<s:form id="mainForm" theme="simple" action="createMeteoAction" method="POST">
			<s:token></s:token>
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage"/>
			<s:hidden name="nrPages" id="nrPages" />
			<s:hidden name="deleteService" id="deleteService" value="-1"/>

			<table class="formTable">
				<tr>
					<td align="left">Nom de la Météo <span class="starObligatoire">*</span> :</td>
					<td align="left"><s:textfield name="nomMeteo" value="%{nomMeteo}" maxlength="100" size="35"/></td>
				</tr>	
			</table>
			
			<div class="titreTable"><b>Services</b></div>
			<div class="plus">
				Ajouter :
				<s:select id="serviceToAdd" name="serviceToAdd" listKey="id" listValue="service" headerKey="-1" headerValue="" list="%{serviceList}"/>
				<s:a href="#" onclick="javascript:addService()">
					<img class="icone" alt="Ajouter" title="Ajouter" src="<s:property value="#session.ENSEIGNE" />/img/valider-16.png"/>
				</s:a>
			</div>
			<table class="dataTable" rules="all">
				<col width="80%"/>
				<col width="20%"/>
				<thead>
					<tr>
						<th class="titreColonne">Service</th>
						<th class="titreColonne">Supprimer</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="listService.isEmpty()">
						<tr>
							<td class="emptyListText" colspan="2">Aucun service</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listService" status="stat">
							<tr>
								<td align="left">
									&nbsp;&nbsp;<s:property value="serviceName"/>
								</td>
								<td align="center">
									<a href="#" onclick="javascript:supprimerService(<s:property value="serviceID"/>);">
										<img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
									</a>
									<input type="hidden" id="service<s:property value="#stat.index" />" name="service<s:property value="#stat.index" />" value="<s:property value="serviceID"/>"/>
								</td>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
			</table>
	
			<s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom.jsp" />
		</s:form>
	</div>
</body>
</html>