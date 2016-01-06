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

		//Initialisation des listes
		function initList(){
		}
		
		//reinitialisation de page comme à l'entrée de la page
		function reinitPageValues() {
		}

		$(document).ready(function(){
	    	$("#mainForm").validationEngine();
	  	});

		function valider(){
			var nb = 0;
			if(document.getElementsByName("deb")[0].checked == true)
				nb = nb + 1;
			if(document.getElementsByName("suivi")[0].checked == true)
				nb = nb + 1;
			if(document.getElementsByName("fin")[0].checked == true)
				nb = nb + 1;
			if($("#mainForm").validationEngine('validate')){
				if(nb > 1)
					alert("Veuillez cochez une seule case.");	
				else if(nb == 0)
					alert("Veuillez cochez une case.");	
				else submitData("sendIncidentsGup.action");
			}
		}
		

	</script>
</head>
<body onload="javascript:initList()">
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('ICDG_DES')" /></s:param>
	</s:include>
	
	<div class="contentTable" style="width: 72%">
		<s:form id="mainForm" theme="simple" action="sendIncidentsGup" onsubmit="return false;">
		<s:token></s:token>
			<s:hidden name="selectedID" id="selectedID" />
			<s:hidden name="provenance" id="provenance"/>
			<s:hidden name="nomDomaines" id="nomDomaines" />
			<s:hidden name="nomServices" id="nomServices" />
			<s:hidden name="nomInterlocuteur" id="nomInterlocuteur"/>
			<s:hidden name="nomApplis" id="nomApplis" />
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage" />
			<s:hidden name="nrPages" id="nrPages" />
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

			<table class="formTable" width="85%">
				<tr>
					<td align="left" width="24%"> <u> Domaine(s) <span class="champObligatoire">*</span> :</u></td>
						<td align="left"><s:property value="nomDomaines"/></td>
					<td align="left" width="24%"><u> Service(s)<span class="champObligatoire">*</span> :</u></td>
						<td align="left"><s:property value="nomServices"/></td>
					
				</tr>
				<tr>
					<td align="left" width="24%"><u> Applicatif(s)<span class="champObligatoire">*</span> :</u></td>
					<td align="left"><s:property value="nomApplis"/></td>
				<td align="left" width="24%"><u> Détecter par :</u> </td>
					<td align="left"><s:property value="nomDetections"/></td>	
				</tr>
				<tr>
					<td align="left"><u>Etat du service : </u></td>
					<td align="left"><s:property value="nomEtat"/></td>
				</tr>
				
				<tr>
					<td align="left"><u>Impact utilisateur <span class="champObligatoire">*</span> :</u></td>
					<s:if test="impactSelected == 0"> <td>Non</td></s:if>
					<s:if test="impactSelected == 1"> <td>Oui</td></s:if>
				</tr>			
				<tr>
					<td align="left"><u>Date de début <span class="champObligatoire">*</span> :</u></td>
					<td align="left"><s:property value="dateDebut"/></td>
					<td align="left"><u>Heure de début <span class="champObligatoire">*</span> :</u></td>
					<td align="left"><s:property value="heureDebut"/></td>
				</tr>
				<tr>
				<s:if test="dateFin != ''">
					<td align="left"><u>Date de fin:</u></td>
					<td align="left"><s:property value="dateFin"/></td>
					<td align="left"><u>Heure de fin:</u></td>
					<td align="left"><s:property value="heureFin"/></td>
				</s:if>
				</tr>
				<tr>
					<td align="left"><u>Description incident <span class="champObligatoire">*</span> :</u></td>
					<td colspan="3"><s:property value="prob"/></td>
				</tr>
				<tr>
					<td align="left"><u>Interlocuteur(s) concerné(s) :</u></td>
					<td align="left"><s:property value="nomInterlocuteur"/></td> 
					<s:if test="ars != null && ars != ''">
						<td align="left"><br/><u>Incident ARS :</u></td>
						<td align="left"><br/><s:property value="ars"/></td> 
					</s:if>
				</tr>
				<tr>
					<td align="left"><br/> <s:checkbox name="deb"/> Début</td>
					<td align="left"><br/> <s:checkbox name="suivi"/> Suivi</td>
					<td align="left"><br/> <s:checkbox name="fin"/> Fin</td>
				</tr>
				<tr>
					<td align="left"><br/>Destinataire(s) concerné(s) <span class="champObligatoire">*</span>:</td>
					<td><br/><s:select name="interlocCSelected" id="listI" size="5" multiple="true"
							list="listInt" listKey="id" listValue="nom" 
							cssStyle="width:100%;" theme="simple"
							data-validation-engine="validate[required]">
						</s:select> </td>
				</tr>
				<tr>
					<td align="left">Destinataire(s) exceptionnel(s) :</td>
					<td><s:select name="interlocESelected" id="listI" size="5" multiple="true"
							list="listDes" listKey="id" listValue="nom + ' ' + prenom" 
							cssStyle="width:100%;" theme="simple">
						</s:select> </td>
				</tr>
				<tr>
					<td colspan="4"><s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom.jsp" /></td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>