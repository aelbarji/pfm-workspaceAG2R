<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		var fenetreOuverte = false;
		function modifyVacation(pilote, date, couleur) {
			if(!fenetreOuverte){
				var url = "showModifyPiloteVacationsAction.action?piloteID=" + pilote + "&date=" + date + "&vacationID=" + couleur;
				var iWidth = 500;
				var iHeight = 600;
				var iTop = (window.screen.height - iHeight) / 2;
				var iLeft = (window.screen.width - iWidth) / 2;
				fenetreOuverte = true;
				window.showModalDialog(url,"Modification de vacation d'un pilote","dialogHeight:"+ iHeight+ "px; dialogWidth:"+ iWidth+ "px; toolbar:no; menubar:no; scrollbars:no; resizable:no; location:no; status:no; dialogLeft:"+ iLeft + "px; dialogTop:" + iTop + "px;");
				document.getElementById("mainForm").submit();
			}
		}

		function changeDate(){
			submitData('showPlanningSemaineAction.action');
		}

		function exportExcel() {
			document.getElementById('exportForm').submit();
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('PLN_SEM')" /></s:param>
		<s:param name="filtre" value="false"></s:param>
		<s:param name="exportPlanningSemaine" value="true"></s:param>
	</s:include>
	<s:form theme="simple" id="mainForm" action="showPlanningSemaineAction" >
		<s:token></s:token>
		<s:hidden name="selectedDateStr"></s:hidden>
		
		<div class="contentTable">
			Sélectionner un cycle : 
				<s:select name="cycle" list="listCycle" listKey="id" listValue="nomCycle" headerKey="" headerValue="Cycle" onchange="javascript:submitData('showPlanningSemaineAction.action')" />
			<br/><br/>
			
			<!-- légende vacation -->
			<table border="1" class="dataTable" rules="all" width="400px">
				<tr>
					<th align="center" >Légende vacation</th>
				</tr>
				<tr>
					<td align="center">
						<s:if test="!listPlanningVacation.isEmpty()">
						<s:iterator value="listPlanningVacation">
							&nbsp;<span style="background-color:#<s:property value='codeCouleur'/>;"><s:property value="libelle"/></span>&nbsp;					
						</s:iterator>
						</s:if>
					</td>
				</tr>
			</table>
			<br/>
	
			<table width="800px" align="center" class="dataTable" rules="all">
				<tr>
					<th align="left"><button name="Pre" value="Pre" onclick="javascript:submitData('showPlanningSemaineAvantAction.action')">&lt;&lt;</button></th>
					<th align="center" colspan="6">
						Planning hebdomadaire des pilotes<br/>
						<s:select name="selectedAnnee" id="selectedAnnee" list="%{mapAnnee}" listKey="key" listValue="value" onchange="javascript:changeDate();"/>
						<s:select name="selectedSemaine" id="selectedSemaine" list="%{mapNumSemaine}" onchange="javascript:changeDate();"/>
					</th>
					<th align="right"><button name="Next" value="Next" onclick="javascript:submitData('showPlanningSemaineApresAction.action')">&gt;&gt;</button></th>
				</tr>
	
				<tr>
					<th class="titreColonne" width="12.5%">Pilote</th>
					<th class="titreColonne"width="12.5%">Lundi <s:property value="mapJour.get(1)" /></th>
					<th class="titreColonne" width="12.5%">Mardi <s:property value="mapJour.get(2)" /></th>
					<th class="titreColonne" width="12.5%">Mercredi <s:property value="mapJour.get(3)" /></th>
					<th class="titreColonne" width="12.5%">Jeudi <s:property value="mapJour.get(4)" /></th>
					<th class="titreColonne" width="12.5%">Vendredi <s:property value="mapJour.get(5)" /></th>
					<th class="titreColonne" width="12.5%">Samedi <s:property value="mapJour.get(6)" /></th>
					<th class="titreColonne" width="12.5%">Dimanche <s:property value="mapJour.get(7)" /></th>
				</tr>
	
				<s:iterator value="mapSemaine" status="stat">
					<tr>
						<td><s:property value="pilote"/></td>
						<td style="background-color:#<s:property value='lundi'/>;<s:if test="lundiDark">color:white;</s:if>">
							<a style="cursor: pointer;" <s:if test="#session.USER_DROITS.contains('PLN_MOD_PONC')">onclick="javascript:modifyVacation('<s:property value="piloteID"/>','<s:date name="mapJourDate.get(1)" format="dd/MM/yyyy"/>','<s:property value='lundiVacationID'/>')"</s:if>>
								<div style="width:100%;text-align:center;">
									<s:property value='lundiText'/>&nbsp;
								</div>
							</a>
						</td>
						<td style="background-color:#<s:property value='mardi'/>;<s:if test="mardiDark">color:white;</s:if>">
							<a style="cursor: pointer;" <s:if test="#session.USER_DROITS.contains('PLN_MOD_PONC')">onclick="javascript:modifyVacation('<s:property value="piloteID"/>','<s:date name="mapJourDate.get(2)" format="dd/MM/yyyy"/>','<s:property value='mardiVacationID'/>')"</s:if>>
								<div style="width:100%;text-align:center;">
									<s:property value='mardiText'/>&nbsp;
								</div>
							</a>
						</td>
						<td style="background-color:#<s:property value='mercredi'/>;<s:if test="mercrediDark">color:white;</s:if>">
							<a style="cursor: pointer;" <s:if test="#session.USER_DROITS.contains('PLN_MOD_PONC')">onclick="javascript:modifyVacation('<s:property value="piloteID"/>','<s:date name="mapJourDate.get(3)" format="dd/MM/yyyy"/>','<s:property value='mercrediVacationID'/>')"</s:if>>
								<div style="width:100%;text-align:center;">
									<s:property value='mercrediText'/>&nbsp;
								</div>
							</a>
						</td>
						<td style="background-color:#<s:property value='jeudi'/>;<s:if test="jeudiDark">color:white;</s:if>">
							<a style="cursor: pointer;" <s:if test="#session.USER_DROITS.contains('PLN_MOD_PONC')">onclick="javascript:modifyVacation('<s:property value="piloteID"/>','<s:date name="mapJourDate.get(4)" format="dd/MM/yyyy"/>','<s:property value='jeudiVacationID'/>')"</s:if>>
								<div style="width:100%;text-align:center;">
									<s:property value='jeudiText'/>&nbsp;
								</div>
							</a>
						</td>
						<td style="background-color:#<s:property value='vendredi'/>;<s:if test="vendrediDark">color:white;</s:if>">
							<a style="cursor: pointer;" <s:if test="#session.USER_DROITS.contains('PLN_MOD_PONC')">onclick="javascript:modifyVacation('<s:property value="piloteID"/>','<s:date name="mapJourDate.get(5)" format="dd/MM/yyyy"/>','<s:property value='vendrediVacationID'/>')"</s:if>>
								<div style="width:100%;text-align:center;">
									<s:property value='vendrediText'/>&nbsp;
								</div>
							</a>
						</td>
						<td style="background-color:#<s:property value='samedi'/>;<s:if test="samediDark">color:white;</s:if>">
							<a style="cursor: pointer;" <s:if test="#session.USER_DROITS.contains('PLN_MOD_PONC')">onclick="javascript:modifyVacation('<s:property value="piloteID"/>','<s:date name="mapJourDate.get(6)" format="dd/MM/yyyy"/>','<s:property value='samediVacationID'/>')"</s:if>>
								<div style="width:100%;text-align:center;">
									<s:property value='samediText'/>&nbsp;
								</div>
							</a>
						</td>
						<td style="background-color:#<s:property value='dimanche'/>;<s:if test="dimancheDark">color:white;</s:if>">
							<a style="cursor: pointer;" <s:if test="#session.USER_DROITS.contains('PLN_MOD_PONC')">onclick="javascript:modifyVacation('<s:property value="piloteID"/>','<s:date name="mapJourDate.get(7)" format="dd/MM/yyyy"/>','<s:property value='dimancheVacationID'/>')"</s:if>>
								<div style="width:100%;text-align:center;">
									<s:property value='dimancheText'/>&nbsp;
								</div>
							</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
	</s:form>
	<s:form action="exportPlanningSemaineAction" theme="simple" id="exportForm">
	</s:form>
</body>
</html>
				