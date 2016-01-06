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
			submitData(document.getElementById("provenance").value);
		}

		$(document).ready(function(){
	    	$("#mainForm").validationEngine();
	  	});

		function valider(){
			if($("#mainForm").validationEngine('validate')){
				submitData("sendIncidentsGup.action");
				}
		}
		

	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('ICDG_DET')" /></s:param>
	</s:include>
	
	<div class="contentTable" style="width: 72%">
		<s:form id="mainForm" theme="simple" action="modifyIncidentsGup" onsubmit="return false;">
		<s:token></s:token>
			<s:hidden name="filtreDomaine" id="filtreDomaine" />
			<s:hidden name="filtreService" id="filtreService" />
			<s:hidden name="filtreApplicatif" id="filtreApplicatif" />
			<s:hidden name="filtreDetection" id="filtreDetection" />
			<s:hidden name="filtreInterlocuteur" id="filtreInterlocuteur" />
			<s:hidden name="filtreEtat" id="filtreEtat" />
			<s:hidden name="filtreArs" id="filtreArs" />
			<s:hidden name="filtreDate" id="filtreDate" />
			<s:hidden name="filtreDateFin" id="filtreDateFin" />
			<s:hidden name="filtreImpact" id="filtreImpact" />
			<s:hidden name="filtreDescription" id="filtreDescription" />
			<s:hidden name="filtreResolution" id="filtreResolution" />
			<s:hidden name="filtrePilote" id="filtrePilote" />
			<s:hidden name="selectedID" id="selectedID" />
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage" />
			<s:hidden name="provenance" id="provenance" />
			<s:hidden name="nrPages" id="nrPages" />

			<table class="formTable" width="85%">
				<tr>
					<td align="left" width="24%"> <u> Domaine(s) <span class="champObligatoire">*</span> :</u></td>
						<td align="left"><s:property value="nomDomaines"/></td>
					<td align="left" width="24%"><u> Service(s)<span class="champObligatoire">*</span> :</u></td>
						<td align="left"><s:property value="nomServices"/></td>
					
				</tr>
				<tr>
					<td align="left" width="24%"><br/><u> Applicatif(s)<span class="champObligatoire">*</span> :</u></td>
					<td align="left"><br/><s:property value="nomApplis"/></td>
				<tr>
				</tr>
				<tr>
					<td align="left" width="24%"><br/><u> Détecter par :</u> </td>
					<td align="left"><br/><s:property value="nomDetections"/></td>	
				</tr>
				<tr>
					<td align="left"><br/><u>Etat du service : </u></td>
					<td align="left"><br/><s:property value="nomEtat"/></td>
				</tr>
				
				<tr>
					<td align="left"><br/><u>Impact utilisateur <span class="champObligatoire">*</span> :</u></td>
					<td align="left"><br/><s:property value="impact"/></td>
				</tr>			
				<tr>
					<td align="left"><br/><u>Date de début <span class="champObligatoire">*</span> :</u></td>
					<td align="left"><br/><s:property value="dateDebut"/></td>
					<td align="left"><br/><u>Heure de début <span class="champObligatoire">*</span> :</u></td>
					<td align="left"><br/><s:property value="heureDebut"/></td>
				</tr>
				<tr>
					<s:if test="dateFin != null">
						<td align="left"><br/><u>Date de fin:</u></td>
						<td align="left"><br/><s:property value="dateFin"/></td>
						<td align="left"><br/><u>Heure de fin:</u></td>
						<td align="left"><br/><s:property value="heureFin"/></td>
					</s:if>
				</tr>
				
				<tr>
					<td align="left"><br/><u>Description incident <span class="champObligatoire">*</span> :</u></td>
					<td align="left"><br/><s:property value="prob"/></td>
				</tr>
				<tr>
					<td align="left"><br/><u>Interlocuteur(s) concerné(s) :</u></td>
					<td align="left"><br/><s:property value="nomInterlocuteur"/></td> 
				</tr>
				<s:if test="ars != null">
					<tr>
						<td align="left"><br/><u>Incident ARS :</u></td>
						<td align="left"><br/><s:property value="ars"/></td> 
					</tr>
				</s:if>
				<tr>
					<td colspan="3">
						<div class="contentTableBottom">
							<span class="champObligatoire">* Champs Obligatoires</span>
								<div class="pageRight">
									<s:submit href="#" onclick="javascript:retour();" value="Retour" cssClass="boutonSubmit"></s:submit>
								</div>
						</div>
					</td>
		</tr>
			</table>
		</s:form>
	</div>
</body>
</html>