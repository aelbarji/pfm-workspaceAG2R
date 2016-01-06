<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function init(){
			<s:if test="statChecklistType == null || statChecklistType != 1">
				$(".jour").hide();
			</s:if>
			<s:if test="statChecklistType == null || statChecklistType != 2">
				$(".semaine").hide();
			</s:if>
			<s:if test="statChecklistType == null || statChecklistType != 3">
				$(".annee").hide();
			</s:if>
		}

		function lancerRechercheJour(){
			if ($("#pickDate").val() == "") {
				alert("Veuillez sélectionner un jour. ");
				document.getElementById("pickDate").focus();
			} else {
				submitData("showStatChecklistJour.action");
			}
		}

		function lancerRechercheSemaine() {
			if (document.getElementById("selectedSemaine").value == "") {
				alert("Veuillez sélectionner une semaine. ");
				document.getElementById("selectedSemaine").focus();
			} else {
				submitData("showStatChecklistSemaine.action");
			}
		}

		function lancerRechercheMois() {
			if (document.getElementById("selectedMois").value == "00") {
				alert("Veuillez sélectionner un mois. ");
				document.getElementById("selectedMois").focus();
			}
			else if (document.getElementById("selectedAnneeM").value == "") {
				alert("Veuillez sélectionner une année. ");
				document.getElementById("selectedAnneeM").focus();
			} else {
				submitData("showStatChecklistMois.action");
			}
		}

		function showSemaine() {
			if (document.getElementById("selectedAnneeS").value == "") {
				alert("Veuillez sélectionner une année. ");
				document.getElementById("selectedAnneeS").focus();
			} else {
				submitData("showStatChecklist.action");
			}
		}

        $.subscribe('selectedDateChange',function(event,data) {
        	lancerRechercheJour();
    	})

	</script>
</head>
<body onload="javascript:init()">
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('STAT_CKL')" /></s:param>
	</s:include>

	<div class="contentTable">
		<form id="mainForm" method="post">
			<s:hidden name="statChecklistType" id="statChecklistType"/>
			
			<table class="formTable" width="60%">
				<tr>
					<th align="left" width="30%">
						<a href="#" onclick="javascript:changeData('statChecklistType',1); submitData('showStatChecklistJour.action');"><u>Statistique du jour</u></a>
					</th>
					<th align="center" width="40%">
						<a href="#" onclick="javascript:changeData('statChecklistType',2);submitData('showStatChecklistSemaine.action');"><u>Statistique de la semaine</u></a>
					</th>
					<th align="right" width="30%">
						<a href="#" onclick="javascript:changeData('statChecklistType',3);submitData('showStatChecklistMois.action');"><u>Statistique du mois</u></a>
					</th>
				</tr>
			</table>
			<br/>
			
			<div class="jour" align="center">
				<b>Veuillez sélectionner le jour. </b><br/><br/>Statistique sur le jour<sj:datepicker name="selectedDate" 
																									id="pickDate" 
																									displayFormat="dd/mm/yy" 
																									showOn="button"
																									maxDate="+0"
																									onChangeTopics="selectedDateChange">
																						</sj:datepicker>								
				<br/><br/>
				<div class="resultatJ" align="center">
					<s:property value="%{resultatJ}" /><br/><br/>
					<s:property value="%{tempsControleStr}"/><br/><br/>
					<img alt="Nombre tache par heure" src="<s:property value='%{nombreTacheParHeureUrl}'/>" />
				</div>
			</div>
			
			<div class="semaine" align="center">
				<b>Veuillez sélectionner l'année. </b><br/><br/>
				<s:select name="selectedAnneeS" id="selectedAnneeS" list="%{mapAnnee}" listKey="key" listValue="value" emptyOption="true"/>
				&nbsp;&nbsp;<input type="button" onclick="javascript:showSemaine();" value="Valider" />
				<br/><br/>
				<b>Veuillez sélectionner la semaine. </b><br/><br/>
				<s:select name="selectedSemaine" id="selectedSemaine" list="%{mapSemaine}" emptyOption="true"/>
				&nbsp;&nbsp;<input type="button" onclick="javascript:lancerRechercheSemaine();" value="Valider" />
				<br/><br/>
				<div class="resultatS" align="center"><s:property value="%{resultatS}" /></div>
			</div>
			
			<div class="annee" align="center">
				<b>Veuillez sélectionner le mois et l'année. </b><br/><br/>
				Statistique sur le mois 
				<s:select list="#{'00':'','01':'janvier','02':'février','03':'mars','04':'avril','05':'mai','06':'juin','07':'juillet','08':'août','09':'septembre','10':'octobre','11':'novembre','12':'décembre'}"
						theme="simple" name="selectedMois" id="selectedMois" value="%{selectedMois}" />
				de l'année
				<s:select name="selectedAnneeM" id="selectedAnneeM" list="%{mapAnnee}" emptyOption="true"/>
				&nbsp;&nbsp;<input type="button" onclick="javascript:lancerRechercheMois();" value="Valider" />	  
				<br/><br/>
				<div class="resultatM" align="center"><s:property value="%{resultatM}" /></div>
			</div>
			
		</form>
	</div>
</body>
</html>
