<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
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
	<script type="text/javascript" src="js/time/jquery.mousewheel.min.js"></script>
	<script type="text/javascript">
	
		function retour() {
			reinitPageValues();
			submitData("ShowIncidentsGup.action");
		}

		$(function() {
			$('#heureFin').timeEntry({show24Hours: true , spinnerImage: ''});
			$('#heureDebut').timeEntry({show24Hours: true , spinnerImage: ''});
		});

		//reinitialisation de page comme à l'entrée de la page
		function reinitPageValues() {
			document.getElementById("mainForm").page.value = '<s:property value="page" escape="false"/>';
			document.getElementById("mainForm").nrPerPage.value = '<s:property value="nrPerPage" escape="false"/>';
			document.getElementById("mainForm").nrPages.value = '<s:property value="nrPages" escape="false"/>';
		}
		
		$(document).ready(function(){
	    	$("#mainForm").validationEngine();
	  	});

		function valider(){
			$("#mainForm").validationEngine('validate');
		}
		

	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('ICD_ADD')" /></s:param>
	</s:include>
	
	<div class="contentTable" style="width: 72%">
		<s:form id="mainForm" theme="simple" action="createIncidentsGup" method="POST">
		<s:token></s:token>
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage" />
			<s:hidden name="nrPages" id="nrPages" />
			<s:hidden name="provenance" id="provenance" />
			<table class="formTable" width="85%">
				<tr>
					<td align="left" width="24%">
						Domaine(s) <span class="champObligatoire">*</span> :<br/>
						<s:select name="domaineSelected" id="listD" size="6" multiple="true"
							list="listDomaine" listKey="id" listValue="nom" 
							cssStyle="width:100%;" theme="simple" 
							data-validation-engine="validate[required]">
						</s:select>
					</td>
					<td align="left" width="24%">
						Service(s)<span class="champObligatoire">*</span> :<br/>
						<s:select name="serviceSelected" id="listS" size="6" multiple="true"
							list="listService" listKey="id" listValue="nom"
							cssStyle="width:100%;" theme="simple"
							data-validation-engine="validate[required]">
						</s:select>
					</td>
					<td align="left" width="24%">
						Applicatif(s)<span class="champObligatoire">*</span> :<br/>
						<s:select name="appliSelected" id="listA" size="6" multiple="true"
							list="listAppli" listKey="id" listValue="applicatif" 
							cssStyle="width:100%;" theme="simple"
							data-validation-engine="validate[required]">
						</s:select>
					</td>
					<td align="center" width="4%">
					</td>
				</tr>
				
				<tr>
				<td align="left" width="24%"> Détecter par : </td>
						<td><s:select name="detectionSelected" id="listDe" size="6" multiple="true"
							list="listInterloc" listKey="id" listValue="nom" 
							cssStyle="width:100%;" theme="simple" >
						</s:select>
						</td>
				</tr>
				<tr>
					<td align="left">Etat du service :</td>
					<td align="left" colspan="3">
						<s:select name="etatSelected"
							list="listEtat" listKey="id" listValue="libelle" headerValue="" headerKey="-1"
							cssStyle="width:150px;" theme="simple">
						</s:select>
					</td>
				</tr>
				
				<tr>
					<td align="left">Impact utilisateur <span class="champObligatoire">*</span> :</td>
					<td align="left" colspan="3">
						<input type="radio" id="impTrue" name="impactSelected" value="1" data-validation-engine="validate[required]"
						<s:if test="impactSelected == 1">checked="checked"</s:if>/>Oui
						
						<input type="radio" id="impFalse" name="impactSelected" value="0" data-validation-engine="validate[required]"
						<s:if test="impactSelected == 0">checked="checked"</s:if>/>Non
					</td>
				</tr>			
				<tr>
					<td align="left">Date de début <span class="champObligatoire">*</span> :</td>
					<td align="left">
						<sj:datepicker name="dateDebut" 
									id="dateDebut" 
									displayFormat="dd/mm/yy" 
									label="Choisir une date"
									showOn="focus"
									data-validation-engine="validate[required, custom[dateFR]]">
						</sj:datepicker>								
					</td>
					<td align="left">Heure de début <span class="champObligatoire">*</span> :</td>
					<td align="left"><s:textfield type="text" name="heureDebut" id="heureDebut" data-validation-engine="validate[required, custom[timeWithoutSec]]"/></td>
				</tr>
				<tr>
					<td align="left">Date de fin:</td>
					<td align="left">
						<sj:datepicker name="dateFin" 
									id="dateFin" 
									displayFormat="dd/mm/yy" 
									label="Choisir une date"
									showOn="focus"
									data-validation-engine="validate[custom[dateFR], condRequired[heureFin]]">
						</sj:datepicker>								
					</td>
					<td align="left">Heure de fin:</td>
					<td align="left"><s:textfield type="text" name="heureFin" id="heureFin" data-validation-engine="validate[custom[timeWithoutSec], condRequired[dateFin]]"/></td>
				</tr>
				
				<tr>
					<td align="left">Commentaire <span class="champObligatoire">*</span> :</td>
					<td align="left" colspan="4"><s:textarea name="prob" id="descriptionID" rows="6" cols="85" data-validation-engine="validate[required]"></s:textarea></td>
				</tr>
				<tr>
					<td align="left">Interlocuteur(s) concerné(s) <span class="champObligatoire">*</span>:</td>
					<td><s:select name="interlocSelected" id="listI" size="5" multiple="true"
							list="listInterloc" listKey="id" listValue="nom" 
							cssStyle="width:100%;" theme="simple"
							data-validation-engine="validate[required]">
						</s:select> </td>
				</tr>
				<tr>
					<td align="left">Incident ARS</td>
					<td align="left" colspan="4"><s:textfield name="ars" maxLength="10"></s:textfield></td>
				</tr>
				<tr>
					<td colspan="4"><s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom_submit.jsp" /></td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>