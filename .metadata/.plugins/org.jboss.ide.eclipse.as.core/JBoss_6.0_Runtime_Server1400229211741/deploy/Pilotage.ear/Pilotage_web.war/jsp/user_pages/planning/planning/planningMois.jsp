<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function init() {
			$(document.getElementById("pickDate").childNodes[1]).hide();
		}

        $.subscribe('selectedDateChange',function(event,data) {
        	myChangeDate();
    	})

		function myChangeDate(){
			$("#selectedDateStr").val($('#pickDate').val()) ;
			submitData("showPlanningMoisAction.action");
		}

		function exportExcel() {
			document.getElementById('exportForm').submit();
		}
		var fenetreOuverte = false;
		
		function modifyVacation (jour, equipeID, vacation) {
			if (equipeID != "" ){
				mois = document.getElementById('pickDate').value;
				mois = mois.substring (2,10);
				if ((jour + mois).length < 10){
					jour = 0 + jour;
				}
				var date = jour + mois;
				if(!fenetreOuverte){
					var url = "showModifyEquipesVacationAction.action?equipeID=" + equipeID + "&date=" + date
							+ "&vacationID=" + vacation;
					var iWidth = 500;
					var iHeight = 600;
					var iTop = (window.screen.height - iHeight) / 2;
					var iLeft = (window.screen.width - iWidth) / 2;
					fenetreOuverte = true;
					window.showModalDialog(url,"Modification de vacation d'une équipe","dialogHeight:"+ iHeight+ "px; dialogWidth:"+ iWidth+ "px; toolbar:no; menubar:no; scrollbars:no; resizable:no; location:no; status:no; dialogLeft:"+ iLeft + "px; dialogTop:" + iTop + "px;");
					document.getElementById("mainForm").submit();
				}
			}
		}
	</script>
	<style type="text/css">
  		.alignDte {
	    	text-align: right;
	    	margin: 0;
	    }
  	</style>
</head>
<body onload="init()">
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('PLN_MOIS')" /></s:param>
		<s:param name="filtre" value="false"></s:param>
		<s:param name="exportPlanningMois" value="true"></s:param>
	</s:include>
	<s:form theme="simple" id="mainForm" action="showPlanningMoisAction" >
		<s:token></s:token>
		<s:hidden name="selectedDateStr" id="selectedDateStr"></s:hidden>
		
		<div class="contentTable">			
			Sélectionner une équipe : 
			<s:select name="equipe" list="listEquipe" listKey="id" listValue="nomEquipe" headerKey="" headerValue="Equipe" onchange="javascript:submitData('showPlanningMoisAction.action')" />
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
			<br />

			<table class="dataTable" rules="all">
				<tr>
					<th align="left"><button name="Pre" value="Pre" onclick="javascript:submitData('showPlanningMoisAvantAction.action')">&lt;&lt;</button></th>
					<th align="center" colspan="5">
						Planning du mois : 
							<sj:datepicker name="selectedDate" 
										id="pickDate" 
										displayFormat="dd/mm/yy" 
										showOn="focus"
										onChangeTopics="selectedDateChange">
							</sj:datepicker>								
					</th>
					<th align="right"><button name="Next" value="Next" onclick="javascript:submitData('showPlanningMoisApresAction.action')">&gt;&gt;</button></th>
				</tr>
				<tr>
					
					<th class="titreColonne" width="14.3%" >Lundi</th>
					<th class="titreColonne" width="14.3%" >Mardi</th>
					<th class="titreColonne" width="14.2%" >Mercredi</th>
					<th class="titreColonne" width="14.3%" >Jeudi</th>
					<th class="titreColonne" width="14.3%" >Vendredi</th>
					<th class="titreColonne" width="14.3%" >Samedi</th>
					<th class="titreColonne" width="14.3%" >Dimanche</th>
					
				</tr>
				<tr>
					<s:if test="mapJour.get(0) != null" >
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(0)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(0)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(0))'/>;<s:if test="mapDark.get(mapJour.get(0))">color:white;</s:if>">
							<s:property value="mapJour.get(0)" />&nbsp;
							<p class="alignDte"> <s:property value="mapVacation.get(mapJour.get(0))" />&nbsp; </p> </td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(1) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(1)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(1)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(1))'/>;<s:if test="mapDark.get(mapJour.get(1))">color:white;</s:if>">
							<s:property value="mapJour.get(1)" />&nbsp;
							<p class="alignDte"> <s:property value="mapVacation.get(mapJour.get(1))" />&nbsp; </p> </td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>

					<s:if test="mapJour.get(2) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(2)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(2)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(2))'/>;<s:if test="mapDark.get(mapJour.get(2))">color:white;</s:if>">
						 	<s:property value="mapJour.get(2)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(2))" />&nbsp; </p> </td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(3) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(3)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(3)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(3))'/>;<s:if test="mapDark.get(mapJour.get(3))">color:white;</s:if>">
							<s:property value="mapJour.get(3)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(3))" />&nbsp;</p> </td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(4) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(4)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(4)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(4))'/>;<s:if test="mapDark.get(mapJour.get(4))">color:white;</s:if>">
							<s:property value="mapJour.get(4)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(4))" />&nbsp;</p> </td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
						
					<s:if test="mapJour.get(5) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(5)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(5)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(5))'/>;<s:if test="mapDark.get(mapJour.get(5))">color:white;</s:if>">
							<s:property value="mapJour.get(5)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(5))" />&nbsp;</p> </td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
						
					<s:if test="mapJour.get(6) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(6)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(6)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(6))'/>;<s:if test="mapDark.get(mapJour.get(6))">color:white;</s:if>">
							<s:property value="mapJour.get(6)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(6))" />&nbsp;</p> </td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
				</tr>
				<tr>
					<s:if test="mapJour.get(7) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(7)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(7)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(7))'/>;<s:if test="mapDark.get(mapJour.get(7))">color:white;</s:if>">
							<s:property value="mapJour.get(7)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(7))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
						
					<s:if test="mapJour.get(8) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(8)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(8)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(8))'/>;<s:if test="mapDark.get(mapJour.get(8))">color:white;</s:if>">
							<s:property value="mapJour.get(8)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(8))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(9) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(9)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(9)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(9))'/>;<s:if test="mapDark.get(mapJour.get(9))">color:white;</s:if>">
						<s:property value="mapJour.get(9)" />&nbsp;
						<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(9))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
						
					<s:if test="mapJour.get(10) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(10)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(10)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(10))'/>;<s:if test="mapDark.get(mapJour.get(10))">color:white;</s:if>">
							<s:property value="mapJour.get(10)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(10))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
						
					<s:if test="mapJour.get(11) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(11)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(11)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(11))'/>;<s:if test="mapDark.get(mapJour.get(11))">color:white;</s:if>">
							<s:property value="mapJour.get(11)" />&nbsp;
							<p class="alignDte"> <s:property value="mapVacation.get(mapJour.get(11))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
						
					<s:if test="mapJour.get(12) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(12)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(12)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(12))'/>;<s:if test="mapDark.get(mapJour.get(12))">color:white;</s:if>">
							<s:property value="mapJour.get(12)" />&nbsp;
							<p class="alignDte"> <s:property value="mapVacation.get(mapJour.get(12))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(13) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(13)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(13)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(13))'/>;<s:if test="mapDark.get(mapJour.get(13))">color:white;</s:if>">
							<s:property value="mapJour.get(13)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(13))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
				</tr>
				<tr>
					<s:if test="mapJour.get(14) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(14)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(14)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(14))'/>;<s:if test="mapDark.get(mapJour.get(14))">color:white;</s:if>">
							<s:property value="mapJour.get(14)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(14))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(15) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(15)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(15)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(15))'/>;<s:if test="mapDark.get(mapJour.get(15))">color:white;</s:if>">
							<s:property value="mapJour.get(15)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(15))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(16) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(16)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(16)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(16))'/>;<s:if test="mapDark.get(mapJour.get(16))">color:white;</s:if>">
							<s:property value="mapJour.get(16)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(16))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(17) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(17)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(17)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(17))'/>;<s:if test="mapDark.get(mapJour.get(17))">color:white;</s:if>">
							<s:property value="mapJour.get(17)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(17))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(18) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(18)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(18)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(18))'/>;<s:if test="mapDark.get(mapJour.get(18))">color:white;</s:if>">
							<s:property value="mapJour.get(18)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(18))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(19) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(19)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(19)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(19))'/>;<s:if test="mapDark.get(mapJour.get(19))">color:white;</s:if>">
						<s:property value="mapJour.get(19)" />&nbsp;
						<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(19))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(20) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(20)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(20)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(20))'/>;<s:if test="mapDark.get(mapJour.get(20))">color:white;</s:if>">
							<s:property value="mapJour.get(20)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(20))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
				</tr>
				<tr>
					<s:if test="mapJour.get(21) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(21)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(21)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(21))'/>;<s:if test="mapDark.get(mapJour.get(21))">color:white;</s:if>">
							<s:property value="mapJour.get(21)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(21))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(22) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(22)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(22)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(22))'/>;<s:if test="mapDark.get(mapJour.get(22))">color:white;</s:if>">
							<s:property value="mapJour.get(22)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(22))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(23) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(23)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(23)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(23))'/>;<s:if test="mapDark.get(mapJour.get(23))">color:white;</s:if>">
							<s:property value="mapJour.get(23)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(23))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(24) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(24)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(24)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(24))'/>;<s:if test="mapDark.get(mapJour.get(24))">color:white;</s:if>">
							<s:property value="mapJour.get(24)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(24))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(25) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(25)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(25)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(25))'/>;<s:if test="mapDark.get(mapJour.get(25))">color:white;</s:if>">
							<s:property value="mapJour.get(25)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(25))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(26) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(26)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(26)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(26))'/>;<s:if test="mapDark.get(mapJour.get(26))">color:white;</s:if>">
							<s:property value="mapJour.get(26)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(26))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(27) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(27)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(27)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(27))'/>;<s:if test="mapDark.get(mapJour.get(27))">color:white;</s:if>">
							<s:property value="mapJour.get(27)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(27))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
				</tr>
				<tr>
					<s:if test="mapJour.get(28) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(28)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(28)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(28))'/>;<s:if test="mapDark.get(mapJour.get(28))">color:white;</s:if>">
							<s:property value="mapJour.get(28)" />&nbsp;
							<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(28))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(29) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(29)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(29)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(29))'/>;<s:if test="mapDark.get(mapJour.get(29))">color:white;</s:if>">
						<s:property value="mapJour.get(29)" />&nbsp;
						<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(29))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(30) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(30)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(30)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(30))'/>;<s:if test="mapDark.get(mapJour.get(30))">color:white;</s:if>">
						<s:property value="mapJour.get(30)" />&nbsp;
						<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(30))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(31) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(31)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(31)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(31))'/>;<s:if test="mapDark.get(mapJour.get(31))">color:white;</s:if>">
						<s:property value="mapJour.get(31)" />&nbsp;
						<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(31))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(32) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(32)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(32)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(32))'/>;<s:if test="mapDark.get(mapJour.get(32))">color:white;</s:if>">
						<s:property value="mapJour.get(32)" />&nbsp;
						<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(32))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(33) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(33)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(33)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(33))'/>;<s:if test="mapDark.get(mapJour.get(33))">color:white;</s:if>">
						<s:property value="mapJour.get(33)" />&nbsp;
						<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(33))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(34) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(34)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(34)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(34))'/>;<s:if test="mapDark.get(mapJour.get(34))">color:white;</s:if>">
						<s:property value="mapJour.get(34)" />&nbsp;
						<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(34))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
				</tr>
				<tr>
					<s:if test="mapJour.get(35) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(35)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(35)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(35))'/>;<s:if test="mapDark.get(mapJour.get(35))">color:white;</s:if>">
						<s:property value="mapJour.get(35)" />&nbsp;
						<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(35))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(36) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(36)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(36)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(36))'/>;<s:if test="mapDark.get(mapJour.get(36))">color:white;</s:if>">
						<s:property value="mapJour.get(36)" />&nbsp;
						<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(36))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(37) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(37)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(37)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(37))'/>;<s:if test="mapDark.get(mapJour.get(37))">color:white;</s:if>">
						<s:property value="mapJour.get(37)" />&nbsp;
						<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(37))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(38) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(38)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(38)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(38))'/>;<s:if test="mapDark.get(mapJour.get(38))">color:white;</s:if>">
						<s:property value="mapJour.get(38)" />&nbsp;
						<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(38))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(39) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(39)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(39)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(39))'/>;<s:if test="mapDark.get(mapJour.get(39))">color:white;</s:if>">
						<s:property value="mapJour.get(39)" />&nbsp;
						<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(39))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(40) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(40)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(40)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(40))'/>;<s:if test="mapDark.get(mapJour.get(40))">color:white;</s:if>">
						<s:property value="mapJour.get(40)" />&nbsp;
						<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(40))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"></td>
					</s:else>
					
					<s:if test="mapJour.get(41) != null">
						<td onclick="javascript:modifyVacation('<s:property value="mapJour.get(41)"/>','<s:property value="equipe" />', '<s:property value="mapJourVacation.get(41)"/>')" style="cursor: pointer;background-color:#<s:property value='mapCouleur.get(mapJour.get(41))'/>;<s:if test="mapDark.get(mapJour.get(41))">color:white;</s:if>">
						<s:property value="mapJour.get(41)" />&nbsp;
						<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(41))" />&nbsp;</p></td>
					</s:if>
					<s:else>
						<td style="background-color:white;"><s:property value="mapJour.get(41)" />&nbsp;
						<p class="alignDte"><s:property value="mapVacation.get(mapJour.get(41))" />&nbsp;</td>
					</s:else>
				</tr>
			</table>
		</div>
	</s:form>
	<s:form action="exportPlanningMoisAction" theme="simple" id="exportForm">
	</s:form>
</body>
</html>
				