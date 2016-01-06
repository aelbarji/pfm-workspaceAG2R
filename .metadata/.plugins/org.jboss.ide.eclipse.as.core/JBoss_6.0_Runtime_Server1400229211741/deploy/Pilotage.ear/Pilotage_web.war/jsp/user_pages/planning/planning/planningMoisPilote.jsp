<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
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

		function init() {
			$(document.getElementById("pickDate").childNodes[1]).hide();
		}

        $.subscribe('selectedDateChange',function(event,data) {
        	myChangeDate();
    	})

    	function myChangeDate(){
			$("#selectedDateStr").val($('#pickDate').val()) ;
			submitData("showPlanningMoisPiloteAction.action");
		}

        function exportCRAF() {            
        	if("<s:property value="#session.USER_DROITS.contains('PLN_CRA_EXP')"/>" == 'true'){
        		submitData("exportCRA.action");
            }
        	else alert("Vous n'avez pas le droit d'export du CRA.");
		}

		function exportExcel() {
			document.getElementById('exportForm').submit();
		}
	
	</script>
</head>
<body onload="init()">
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('PLN_M_PIL')" /></s:param>
		<s:param name="filtre" value="false"></s:param>
		<s:param name="exportPlanningMoisPilote" value="true"></s:param>
		<s:param name="exportCRA" value="true"></s:param>
	</s:include>
	<s:form theme="simple" id="mainForm" action="showPlanningMoisPiloteAction" >
		<s:token></s:token>
		<s:hidden name="selectedDateStr" id="selectedDateStr"></s:hidden>
		
		<div class="contentTable">
			Sélectionner un cycle : 
				<s:select name="cycle" list="listCycle" listKey="id" listValue="nomCycle" headerKey="" headerValue="Cycle" onchange="javascript:submitData('showPlanningMoisPiloteAction.action')" />
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
			<br>
			<!-- planning -->
			<table width="800px" align="center" class="dataTable" rules="all">
				<tr>
					<th align="left"><button name="Pre" value="Pre" onclick="javascript:submitData('showPlanningMoisPiloteAvantAction.action')">&lt;&lt;</button></th>
					<th align="center" colspan="<s:property value='nbDayOfTheMonth - 1' />">
						Planning mensuelle des pilotes : 
							<sj:datepicker name="selectedDate" 
										id="pickDate" 
										displayFormat="dd/mm/yy" 
										showOn="focus"
										onChangeTopics="selectedDateChange">
							</sj:datepicker>								
					</th>
					<th align="right"><button name="Next" value="Next" onclick="javascript:submitData('showPlanningMoisPiloteApresAction.action')">&gt;&gt;</button></th>
				</tr>
	
				<tr>
					<th class="titreColonne" width="12.5%">Pilote</th>
					<s:iterator status="stat" value="listJour" >
						<th class="titreColonne" width="2%"><s:property value="top" /></th>
					</s:iterator>
				</tr>
	
				<s:iterator value="listUsers">
					<tr>
						<td><s:property value="nom + ' ' + prenom"/></td>
						 <s:iterator status="stat" value="listJour" >
						 	<td style=background-color:#<s:property value='mapMoisVacation.get(id).get(top).get("couleur")'/>;>
						 
						 		<a style="cursor: pointer;" <s:if test="#session.USER_DROITS.contains('PLN_MOD_PONC')">
						 			onclick="javascript:modifyVacation('<s:property value="id"/>','<s:date name="mapJourDate.get(top)" format="dd/MM/yyyy"/>','<s:property value='mapMoisVacation.get(id).get(top).get("vacationID")'/>')"</s:if>>
						 			<s:property value='mapMoisVacation.get(id).get(top).get("textCell")'/>
									<div style="width:100%;text-align:center;" title="<s:date name='mapJourDate.get(top)' format='EEEE dd MMMM yyyy'/> - <s:property value='mapMoisVacation.get(id).get(top).get("vacationText")'/>">
									&nbsp;
									</div>
						 		</a>
						 	</td>
						 </s:iterator>
					</tr>
				</s:iterator>
			</table>
		</div>
	</s:form>
	
	<s:form action="exportPlanningMoisPiloteAction" theme="simple" id="exportForm">
	</s:form>
	
		<s:form action="exportCRA" theme="simple" id="exportCRA">
		</s:form>
</body>
</html>
				