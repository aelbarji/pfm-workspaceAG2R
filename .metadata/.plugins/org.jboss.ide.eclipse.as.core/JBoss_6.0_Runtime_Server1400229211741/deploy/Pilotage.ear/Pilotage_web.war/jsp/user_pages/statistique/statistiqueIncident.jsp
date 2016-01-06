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
		function init() {
			$(("#pickDate").childNodes[1]).hide();
		}

        $.subscribe('selectedDateChange',function(event,data) {
        	myChangeDate();
    	})

		//recherche statistique mensuelle des incident sur les domaine 
		function lancerRechercheIncidentDomaineStat() {
			if(document.getElementById("anneeEnCourStr").value.length != 4 || !checkNombreEntier(document.getElementById("anneeEnCourStr").value)){
				alert("L'année n'est pas correcte");
				document.getElementById("anneeEnCourStr").value='${anneeEnCourStr}';
				document.getElementById("anneeEnCourStr").focus();
			}
			else if(document.getElementById("selectedAnneeS").value.length != 4 || !checkNombreEntier(document.getElementById("selectedAnneeS").value)){
				alert("L'année n'est pas correcte");
				document.getElementById("selectedAnneeS").value='${selectedAnneeS}';
				document.getElementById("selectedAnneeS").focus();
			}
			else{
				submitData("showStatIncident.action");
			}
		}
		
		function myChangeDate() {
			var d = new Date();
			document.getElementById("selectedDateStr").val($("#pickDate").val() + " " + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds());
			submitData("showStatIncident.action");
		}

		function openWin(environnement, environnementId, selectMois, selectAnnee, selectSemaine) {
			var url = "detailDomaine.action?environnement=" + environnement
					+ "&environnementId=" + environnementId + "&selectMois=" + escape(selectMois) + "&selectAnnee=" + selectAnnee + "&selectSemaine=" + selectSemaine;
			var iWidth = 800;
			var iHeight = 300;
			var iTop = (window.screen.height - iHeight - 100) / 2;
			var iLeft = (window.screen.width - iWidth) / 2;
			window.showModalDialog(
							url,
							"test",
							"dialogHeight:"
									+ iHeight
									+ "px; dialogWidth:"
									+ iWidth
									+ "px; toolbar:no; menubar:no; scrollbars:no; resizable:no; location:no; status:no; dialogLeft:"
									+ iLeft + "px; dialogTop:" + iTop + "px;");
		}

		function afficher(redirMois) {
			document.getElementById("redirMois").value = redirMois;
			submitData('showIncidentStatAction.action');
		}
		

		function afficherCoupure(redirMois) {
			document.getElementById("redirMois").value = redirMois;
			document.getElementById("coupure").value = 1;
			submitData('showIncidentStatAction.action');
		}
		
		function afficherResolu(redirMois) {
			document.getElementById("redirMois").value = redirMois;
			document.getElementById("resoluPil").value = 1;
			submitData('showIncidentStatAction.action');
		}
		
		function afficherCritique(redirMois) {
			document.getElementById("redirMois").value = redirMois;
			document.getElementById("type").value = 3;
			submitData('showIncidentStatAction.action');
		}

		function afficherCritiqueCoupure(redirMois) {
			document.getElementById("redirMois").value = redirMois;
			document.getElementById("type").value = 3;
			document.getElementById("coupure").value = 1;
			submitData('showIncidentStatAction.action');
		}

		function afficherAppelAstreinte(redirMois){
			document.getElementById("redirMois").value = redirMois;
			document.getElementById("hasAstreinte").value = 1;
			submitData('showIncidentStatAction.action');
		}

		function imprimer() {
			var url = "showChoixImpressionIncident.action";
			var iWidth = 300; 
			var iHeight = 300;
			var iTop = Math.round((screen.availHeight-iHeight)/2);
			var iLeft = Math.round((screen.availWidth-iWidth)/2);
			var returnValue = window.showModalDialog(url, "", "dialogHeight:" + iHeight + "px; dialogWidth:"+iWidth+"px; toolbar:no; menubar:no; scrollbars:no; resizable:no; location:no; status:no; dialogLeft:" + iLeft + "px; dialogTop:" + iTop +"px;");    

			if (returnValue != null) {
				var parties = returnValue.split(";");
				document.getElementById("mainForm").mensuel.value = "false";
				document.getElementById("mainForm").hebdomadaire.value = "false";
				document.getElementById("mainForm").resume.value = "false";
				document.getElementById("mainForm").serveur.value = "false";
				document.getElementById("mainForm").application.value = "false";
				document.getElementById("mainForm").service.value = "false";
				document.getElementById("mainForm").domaine.value = "false";
				for (var i=0; i<parties.length; i++) {
					if ("mensuel" == parties[i]) {
						document.getElementById("mainForm").mensuel.value = "true";
					}
					if ("hebdomadaire" == parties[i]) {
						document.getElementById("mainForm").hebdomadaire.value = "true";
					}
					if ("resume" == parties[i]) {
						document.getElementById("mainForm").resume.value = "true";
					}
					if ("serveur" == parties[i]) {
						document.getElementById("mainForm").serveur.value = "true";
					}
					if ("application" == parties[i]) {
						document.getElementById("mainForm").application.value = "true";
					}
					if ("service" == parties[i]) {
						document.getElementById("mainForm").service.value = "true";
					}
					if ("domaine" == parties[i]) {
						document.getElementById("mainForm").domaine.value = "true";
					}
				}

				var text = document.getElementById("pickDate").childNodes[0].value.toString();
				var str = text.split("-");
				for ( var i = 0; i < str.length; i++) {
					if (isNaN(str[i])) {
						return;
					}
				}
				document.getElementById("mainForm").selectedDateS.val($("#pickDate").val());

				document.getElementById("mainForm").selectedSemaineS.value = '<s:property value="mapSemaine.get(selectedSemaine)" />';
				document.getElementById("mainForm").moyenneSemaineS.value = '<s:text name="format.percent"><s:param value="moyenneSemain" /></s:text>';
				document.getElementById("mainForm").moyenneMoisS.value = '<s:text name="format.percent"><s:param value="moyenneMois" /></s:text>';

				var domaineMoisList = "";
				<s:iterator value="domaineDeMoisList">
					domaineMoisList += "&nbsp;&nbsp;&nbsp;-&nbsp;" + "<s:property value='key.environnement'/>" + "<br/>";
				</s:iterator>
				document.getElementById("mainForm").domaineDeMoisListS.value = domaineMoisList;

				var domaineSemaineList = "";
				<s:iterator value="domaineDeSemaineList">
					domaineSemaineList += "&nbsp;&nbsp;&nbsp;-&nbsp;" + "<s:property value='key.environnement'/>" + "<br/>";
				</s:iterator>
				document.getElementById("mainForm").domaineDeSemaineListS.value = domaineSemaineList;
				
				var form = document.getElementById("mainForm");
				form.target = "_blank";
				form.action = "imprimerStatIncident.action";
				form.submit();
				form.target = "_self";
			}
		}
	</script>
</head>
<body onload="javascript:init()">
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('STAT_ICD')" /></s:param>
		<s:param name="impression" value="true"></s:param> 
	</s:include>

	<div class="contentTable">
		<form id="mainForm" method="post">
		<s:token></s:token>
			<s:hidden name="coupure" id="coupure"/>
			<s:hidden name="resoluPil" id="resoluPil"/>
			<s:hidden name="hasAstreinte" id="hasAstreinte"/>
			<s:hidden name="redirMois" id="redirMois"/>
			<s:hidden name="type" id="type"/>
			<s:hidden name="selectedDateStr" id="selectedDateStr"></s:hidden>
			<s:hidden name="selectedDateS" />
			<s:hidden name="selectedDate" />
			<s:hidden name="selectedSemaineS" />
			<s:hidden name="moyenneSemaineS" />
			<s:hidden name="selectedMois" />
			<s:hidden name="moyenneMoisS" />
			<s:hidden name="domaineDeSemaineListS" />
			<s:hidden name="domaineDeMoisListS" />
			<s:hidden name="mensuel" />
			<s:hidden name="hebdomadaire" />
			<s:hidden name="resume" />
			<s:hidden name="serveur" />
			<s:hidden name="application" />
			<s:hidden name="service" />
			<s:hidden name="domaine" />
			<s:hidden name="nbIncidentsMois" value="%{nbIncidentsMois}"/>
			<s:hidden name="nbIncidentsCritiquesMois" value="%{nbIncidentsCritiquesMois}"/> 
			<s:hidden name="nbIncidentsCoupuresMois" value="%{nbIncidentsCoupuresMois}"/>
			<s:hidden name="nbIncidentsCritiquesAvecCoupureMois" value="%{nbIncidentsCritiquesAvecCoupureMois}"/>
			<s:hidden name="nbIncidentsResoluPilotageMois" value="%{nbIncidentsResoluPilotageMois}"/>
			<s:hidden name="nbAppelsAstreinteMois" value="%{nbAppelsAstreinteMois}"/>
			<s:hidden name="nbIncidentsSemaine" value="%{nbIncidentsSemaine}"/>
			<s:hidden name="nbIncidentsCritiquesSemaine" value="%{nbIncidentsCritiquesSemaine}"/>
			<s:hidden name="nbIncidentsCoupuresSemaine" value="%{nbIncidentsCoupuresSemaine}"/>
			<s:hidden name="nbIncidentsCritiquesAvecCoupureSemaine" value="%{nbIncidentsCritiquesAvecCoupureSemaine}"/>
			<s:hidden name="nbIncidentsResoluPilotageSemaine" value="%{nbIncidentsResoluPilotageSemaine}"/>
			<s:hidden name="nbAppelsAstreinteSemaine" value="%{nbAppelsAstreinteSemaine}"/>
			<s:hidden name="monday" id="monday"/>
			<s:hidden name="sunday" id="sunday"/>
			<s:hidden name="incidentJour" value="%{incidentJour}"/>
			<s:hidden name="incidentSemaine" value="%{incidentSemaine}"/>
			<s:hidden name="incidentMois" value="%{incidentsMois}"/>
			<s:hidden name="incident30jours" value="%{incident30jours}"/>
			<s:hidden name="incidentProdJour" value="%{incidentProdJour}"/>
			<s:hidden name="incidentProdSemaine" value="%{incidentProdSemaine}"/>
			<s:hidden name="incidentProdMois" value="%{incidentProdMois}"/>
			<s:hidden name="incidentAutreJour" value="%{incidentAutreJour}"/>
			<s:hidden name="incidentAutreSemaine" value="%{incidentAutreSemaine}"/>
			<s:hidden name="incidentAutreMois" value="%{incidentAutreMois}"/>
			<s:hidden name="incidentURL" value="%{incidentURL}"/>
			<s:hidden name="incidentProURL" value="%{incidentProURL}"/>
			<s:hidden name="incidentAutreURL" value="%{incidentAutreURL}"/>
			<s:hidden name="topFiveServerDeMoisURL" value="%{topFiveServerDeMoisURL}"/>
			<s:hidden name="topFiveServerDer30JURL" value="%{topFiveServerDer30JURL}"/>
			<s:hidden name="topFiveAppDeMoisURL" value="%{topFiveAppDeMoisURL}"/>
			<s:hidden name="topFiveAppDer30JURL" value="%{topFiveAppDer30JURL}"/>
			<s:hidden name="topFiveServiceDeMoisURL" value="%{topFiveServiceDeMoisURL}"/>
			<s:hidden name="topFiveServiceDer30JURL" value="%{topFiveServiceDer30JURL}"/>
			<s:hidden name="topFiveEnviroDeMoisURL" value="%{topFiveEnviroDeMoisURL}"/>
			<s:hidden name="topFiveEnviroDer30JURL" value="%{topFiveEnviroDer30JURL}"/>

			<table class="formTableInfo">
				<tr>
					<td class="titreColonne" colspan="2">
						<span class="titreTableBis">Statistiques mensuelles : </span>
						<s:select list="{'janvier','fevrier','mars','avril','mai','juin','juillet','aout','septembre','octobre','novembre','decembre'}"
						theme="simple" name="moisEnCourStr" value="%{moisEnCourStr}" onchange="javascript:lancerRechercheIncidentDomaineStat();"/>
						<s:textfield name="anneeEnCourStr" id="anneeEnCourStr" size="4" maxlength="4" onchange="javascript:lancerRechercheIncidentDomaineStat();" theme="simple"/>
					</td>
				</tr>
				<tr>
					<td>
						<span class="titreTableBis">&nbsp;Liste des domaines :</span><br/>
							<c:forEach items="${domaineDeMoisList}" var="environnementItem">
								&nbsp;&nbsp;&nbsp;-&nbsp;<a href="#" onclick="javascript:openWin('${environnementItem.key.environnement}','${environnementItem.key.id}','${moisEnCourStr}','${anneeEnCourStr}', '')">
								${environnementItem.key.environnement}<br />
								</a>					
							</c:forEach>
					</td>
					
					<td>
						<s:a href="#" onclick="javascript:afficher(1);">&nbsp;&nbsp;Nombre d'incidents du mois : ${nbIncidentsMois}<br/></s:a>
						<s:a href="#" onclick="javascript:afficherResolu(1);">&nbsp;&nbsp;Nombre d'incidents résolus par le pilotage du mois : ${nbIncidentsResoluPilotageMois}<br/> </s:a>
						<s:a href="#" onclick="javascript:afficherCritique(1);">&nbsp;&nbsp;Nombre d'incidents critiques du mois : ${nbIncidentsCritiquesMois}<br /> </s:a>
						<!-- <s:a href="#" onclick="javascript:afficherCoupure(1);">&nbsp;&nbsp;Nombre d'incidents avec coupure du mois : ${nbIncidentsCoupuresMois}<br /> </s:a> -->
						<s:a href="#" onclick="javascript:afficherCritiqueCoupure(1);">&nbsp;&nbsp;Nombre d'incidents critiques avec coupure du mois : ${nbIncidentsCritiquesAvecCoupureMois}<br /></s:a>
						<s:a href="#" onclick="javascript:afficherAppelAstreinte(1);">&nbsp;&nbsp;Nombre d'appels astreintes du mois : ${nbAppelsAstreinteMois}<br /> </s:a>
					</td>
				</tr>
			</table>
			
			<br/>
			
			<table class="formTableInfo">
				<tr>
					<td class="titreColonne" colspan="2">
						<span class="titreTableBis">Statistiques Hebdomadaires : </span>
						<s:textfield name="selectedAnneeS" id="selectedAnneeS" size="4" maxlength="4" onchange="javascript:lancerRechercheIncidentDomaineStat();" theme="simple"/>
						<s:select name="selectedSemaine" id="selectedSemaine" list="%{mapSemaine}" emptyOption="false" onchange="javascript:lancerRechercheIncidentDomaineStat()" theme="simple"/>
					</td>
				</tr>
				<tr>
					<td>
						<span class="titreTableBis">&nbsp;Liste des domaines :</span><br/>
							<c:forEach items="${domaineDeSemaineList}" var="environnementItem">
								&nbsp;&nbsp;&nbsp;-&nbsp;<a href="#" onclick="javascript:openWin('${environnementItem.key.environnement}','${environnementItem.key.id}','','${anneeEnCourStr}', '${selectedSemaine}')">
								${environnementItem.key.environnement}<br />
								</a>					
							</c:forEach>
					</td>
					
					<td>
						<s:a href="#" onclick="javascript:afficher(0);">&nbsp;&nbsp;Nombre d'incidents de la semaine : ${nbIncidentsSemaine}<br /> </s:a>
						<s:a href="#" onclick="javascript:afficherResolu(0);">&nbsp;&nbsp;Nombre d'incidents résolus par le pilotage de la semaine : ${nbIncidentsResoluPilotageSemaine}<br/></s:a>
						<s:a href="#" onclick="javascript:afficherCritique(0);">&nbsp;&nbsp;Nombre d'incidents critiques de la semaine : ${nbIncidentsCritiquesSemaine}<br /> </s:a>
					<!--  <s:a href="#" onclick="javascript:afficherCoupure(0);">&nbsp;&nbsp;Nombre d'incidents avec coupure de la semaine : ${nbIncidentsCoupuresSemaine}<br /> </s:a> -->
						<s:a href="#" onclick="javascript:afficherCritiqueCoupure(0);">&nbsp;&nbsp;Nombre d'incidents critiques avec coupure de la semaine : ${nbIncidentsCritiquesAvecCoupureSemaine}<br /></s:a>
						<s:a href="#" onclick="javascript:afficherAppelAstreinte(0);">&nbsp;&nbsp;Nombre d'appels astreintes de la semaine : ${nbAppelsAstreinteSemaine}<br /> </s:a>
					</td>
				</tr>
			</table>
		
		</form>
		<br />
		<table class="formTableInfo">
			<col width="351px"/>
			<col width="351px"/>
			<col width="351px"/>
			<tr>
				<td colspan="3" class="titreColonne">
					<span class="titreTableBis">Statistiques journalières du <span class="dateValue"></span><sj:datepicker name="selectedDate" 
																														id="pickDate" 
																														displayFormat="dd/mm/yy" 
																														showOn="button"
																														maxDate="+0"
																														onChangeTopics="selectedDateChange">
																											</sj:datepicker></span>
				</td>
			</tr>
			<tr>
				<td align="center" class="titreTd"><span class="titreTable">Données sur les incidents</span></td>
				<td align="center" class="titreTd"><span class="titreTable">Données sur les incidents de l'environnement <s:property value="enviroTypePrincipale"/></span></td>
				<td align="center" class="titreTd"><span class="titreTable">Données sur les incidents des autres environnements</span></td>
			</tr>
			<tr>
				<td valign="top">
					<table rules="none" class="formTable">
						<tr>
							<td style="border: none;">Incidents de la journée :</td>
							<td style="border: none;">${incidentJour}</td>
						</tr>
						<tr>
							<td style="border: none;">Incidents de la semaine :</td>
							<td style="border: none;">${incidentSemaine}</td>
						</tr>
						<tr>
							<td style="border: none;">Incidents du mois :</td>
							<td style="border: none;">${incidentMois}</td>
						</tr>
						<tr>
							<td style="border: none;">Incidents des 30 dernier jours :</td>
							<td style="border: none;">${incident30jours}</td>
						</tr>
						<tr>
							<td style="border: none;">Moyenne hebdomadaire par jour :</td>
							<td style="border: none;">
								<fmt:formatNumber 	pattern="#,#0.00" type="number" value="${moyenneSemain}" 
													maxFractionDigits="2" minFractionDigits="2"/>
							</td>
						</tr>
						<tr>
							<td style="border: none;">Moyenne mensuelle par jour :</td>
							<td style="border: none;">
								<fmt:formatNumber 	pattern="#,#0.00" type="number" value="${moyenneMois}" 
													maxFractionDigits="2" minFractionDigits="2"/>
							</td>
						</tr>
					</table>
				</td>
				<td valign="top">
					<table rules="none" class="formTable">
						<tr>
							<td style="border: none;">Incidents de la journée :</td>
							<td style="border: none;">${incidentProdJour}</td>
						</tr>
						<tr>
							<td style="border: none;">Incidents de la semaine :</td>
							<td style="border: none;">${incidentProdSemaine}</td>
						</tr>
						<tr>
							<td style="border: none;">Incidents du mois :</td>
							<td style="border: none;">${incidentProdMois}</td>
						</tr>
					</table>
				</td>
				<td valign="top">
					<table rules="none" class="formTable">
						<tr>
							<td style="border: none;">Incidents de la journée :</td>
							<td style="border: none;">${incidentAutreJour}</td>
						</tr>
						<tr>
							<td style="border: none;">Incidents de la semaine :</td>
							<td style="border: none;">${incidentAutreSemaine}</td>
						</tr>
						<tr>
							<td style="border: none;">Incidents du mois :</td>
							<td style="border: none;">${incidentAutreMois}</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<table class="formTable">					
			<tr>
				<td><img alt="incident" src="${incidentURL}" /></td>
				<td><img alt="incidentPro" src="${incidentProURL}" /></td>
				<td><img alt="incidentAutre" src="${incidentAutreURL}" /></td>
				<td></td>
			</tr>
		</table>
		<br />
		<table class="formTableInfo">
			<tr>
				<td colspan="2" class="titreColonne">
					<span class="titreTableBis">Les Top 5 : </span>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2" class="titreTd"><span class="titreTable">Serveurs</span></td>
			</tr>
			<tr>
				<td align="center"><img alt="topFiveServer" src="${topFiveServerDeMoisURL}" /></td>
				<td align="center"><img alt="topFiveServer" src="${topFiveServerDer30JURL}" /></td>
			</tr>
			<tr>
				<td align="center" colspan="2" class="titreTd"><span class="titreTable">Applications</span></td>
			</tr>
			<tr>
				<td align="center"><img alt="topFiveApp" src="${topFiveAppDeMoisURL}" /></td>
				<td align="center"><img alt="topFiveApp" src="${topFiveAppDer30JURL}" /></td>
			</tr>
			<tr>
				<td align="center" colspan="2" class="titreTd"><span class="titreTable">Services</span></td>
			</tr>
			<tr>
				<td align="center"><img alt="topFiveService" src="${topFiveServiceDeMoisURL}" /></td>
				<td align="center"><img alt="topFiveService" src="${topFiveServiceDer30JURL}" /></td>
			</tr>
			<tr>
				<td align="center" colspan="2" class="titreTd"><span class="titreTable">Domaines</span></td>
			</tr>
			<tr>
				<td align="center"><img alt="topFiveEnviro" src="${topFiveEnviroDeMoisURL}" /></td>
				<td align="center"><img alt="topFiveEnviro" src="${topFiveEnviroDer30JURL}" /></td>
			</tr>
		</table>
		
	</div>
</body>
</html>
