<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href='<s:property value="#session.ENSEIGNE" />/css/pilotage.css' />
	<title>Statistique Incidents</title>
</head>
<body onload="javascript:window.print()">
	<div class="contentTable">
		<%if("true".equals(request.getParameter("mensuel"))){ %>
			<table class="formTableInfo">
				<tr>
					<td class="titreColonne" colspan="2">
						<span class="titreTableBis">Statistiques mensuelles : </span>
						<%out.print(request.getParameter("moisEnCourStr"));%>
						<%out.print(request.getParameter("anneeEnCourStr"));%>
					</td>
				</tr>
				<tr>
					<td>
						<span class="titreTableBis">&nbsp;Liste des domaines :</span><br/>
						<%out.print(request.getParameter("domaineDeMoisListS"));%>
					</td>
					<td>
						&nbsp;&nbsp;Nombre d'incidents du mois : <%out.print(request.getParameter("nbIncidentsMois"));%><br />
						&nbsp;&nbsp;Nombre d'incidents critiques du mois : <%out.print(request.getParameter("nbIncidentsCritiquesMois"));%><br />
						&nbsp;&nbsp;Nombre d'incidents avec coupure du mois : <%out.print(request.getParameter("nbIncidentsCoupuresMois"));%><br />
						&nbsp;&nbsp;Nombre d'appels astreintes du mois : <%out.print(request.getParameter("nbAppelsAstreinteMois"));%><br />
					</td>
				</tr>
			</table>
		<%} %>
		
		<br/>
		
		<%if("true".equals(request.getParameter("hebdomadaire"))){ %>
			<table class="formTableInfo">
				<tr>
					<td class="titreColonne" colspan="2">
						<span class="titreTableBis">Statistiques Hebdomadaires : </span>
						<%out.print(request.getParameter("selectedAnneeS"));%>
						<%out.print(request.getParameter("selectedSemaineS"));%>
					</td>
				</tr>
				<tr>
					<td>
						<span class="titreTableBis">&nbsp;Liste des domaines :</span><br/>
						<%out.print(request.getParameter("domaineDeSemaineListS"));%>
					</td>
					<td>
						&nbsp;&nbsp;Nombre d'incidents de la semaine : <%out.print(request.getParameter("nbIncidentsSemaine"));%><br />
						&nbsp;&nbsp;Nombre d'incidents critiques de la semaine : <%out.print(request.getParameter("nbIncidentsCritiquesSemaine"));%><br />
						&nbsp;&nbsp;Nombre d'incidents avec coupure de la semaine : <%out.print(request.getParameter("nbIncidentsCoupuresSemaine"));%><br />
						&nbsp;&nbsp;Nombre d'appels astreintes de la semaine : <%out.print(request.getParameter("nbAppelsAstreinteSemaine"));%><br />
					</td>
				</tr>
			</table>
		<%} %>
		
		<br />
		
		<%if("true".equals(request.getParameter("resume")) || "true".equals(request.getParameter("serveur")) || "true".equals(request.getParameter("application")) || "true".equals(request.getParameter("service")) || "true".equals(request.getParameter("domaine"))){ %>
			<table class="formTableInfo">
				<col width="351px"/>
				<col width="351px"/>
				<col width="351px"/>
				<tr>
					<td colspan="3" class="titreColonne">
						<span class="titreTableBis">Statistiques journalières du <span class="dateValue"><%out.print(request.getParameter("selectedDateS"));%></span></span>
					</td>
				</tr>
				<%if("true".equals(request.getParameter("resume"))){ %>
					<tr>
						<td align="center" class="titreTd"><span class="titreTable">Données sur les incidents</span></td>
						<td align="center" class="titreTd"><span class="titreTable">Données sur les incidents de productions</span></td>
						<td align="center" class="titreTd"><span class="titreTable">Données sur les incidents des autres environnements</span></td>
					</tr>
					<tr>
						<td valign="top">
							<table rules="none" class="formTable">
								<tr>
									<td style="border: none;">Incidents de la journée :</td>
									<td style="border: none;"><%out.print(request.getParameter("incidentJour"));%></td>
								</tr>
								<tr>
									<td style="border: none;">Incidents de la semaine :</td>
									<td style="border: none;"><%out.print(request.getParameter("incidentSemaine"));%></td>
								</tr>
								<tr>
									<td style="border: none;">Incidents du mois :</td>
									<td style="border: none;"><%out.print(request.getParameter("incidentMois"));%></td>
								</tr>
								<tr>
									<td style="border: none;">Incidents des 30 dernier jours :</td>
									<td style="border: none;"><%out.print(request.getParameter("incident30jours"));%></td>
								</tr>
								<tr>
									<td style="border: none;">Moyenne hebdomadaire par jour :</td>
									<td style="border: none;"><%out.print(request.getParameter("moyenneSemaineS"));%></td>
								</tr>
								<tr>
									<td style="border: none;">Moyenne mensuelle par jour :</td>
									<td style="border: none;"><%out.print(request.getParameter("moyenneMoisS"));%></td>
								</tr>
							</table>
						</td>
						<td valign="top">
							<table rules="none" class="formTable">
								<tr>
									<td style="border: none;">Incidents de la journée :</td>
									<td style="border: none;"><%out.print(request.getParameter("incidentProdJour"));%></td>
								</tr>
								<tr>
									<td style="border: none;">Incidents de la semaine :</td>
									<td style="border: none;"><%out.print(request.getParameter("incidentProdSemaine"));%></td>
								</tr>
								<tr>
									<td style="border: none;">Incidents du mois :</td>
									<td style="border: none;"><%out.print(request.getParameter("incidentProdMois"));%></td>
								</tr>
							</table>
						</td>
						<td valign="top">
							<table rules="none" class="formTable">
								<tr>
									<td style="border: none;">Incidents de la journée :</td>
									<td style="border: none;"><%out.print(request.getParameter("incidentAutreJour"));%></td>
								</tr>
								<tr>
									<td style="border: none;">Incidents de la semaine :</td>
									<td style="border: none;"><%out.print(request.getParameter("incidentAutreSemaine"));%></td>
								</tr>
								<tr>
									<td style="border: none;">Incidents du mois :</td>
									<td style="border: none;"><%out.print(request.getParameter("incidentAutreMois"));%></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td><img alt="incident" src="<%out.print(request.getParameter("incidentURL"));%>" /></td>
						<td><img alt="incidentPro" src="<%out.print(request.getParameter("incidentProURL"));%>" /></td>
						<td><img alt="incidentAutre" src="<%out.print(request.getParameter("incidentAutreURL"));%>" /></td>
					</tr>
				<%} %>
			</table>
		<%} %>
		
		<br />
		
		<%if("true".equals(request.getParameter("serveur")) || "true".equals(request.getParameter("application")) || "true".equals(request.getParameter("service")) || "true".equals(request.getParameter("domaine"))){ %>
			<table class="formTableInfo">
				<tr>
					<td colspan="2" class="titreColonne">
						<span class="titreTableBis">Les Top 5 : </span>
					</td>
				</tr>
				<%if("true".equals(request.getParameter("serveur"))){ %>
					<tr>
						<td align="center" colspan="2" class="titreTd"><span class="titreTable">Serveurs</span></td>
					</tr>
					<tr>
						<td align="center"><img alt="topFiveServer" src="<%out.print(request.getParameter("topFiveServerDeMoisURL"));%>" /></td>
						<td align="center"><img alt="topFiveServer" src="<%out.print(request.getParameter("topFiveServerDer30JURL"));%>" /></td>
					</tr>
				<%} %>
				<%if("true".equals(request.getParameter("application"))){ %>
					<tr>
						<td align="center" colspan="2" class="titreTd"><span class="titreTable">Applications</span></td>
					</tr>
					<tr>
						<td align="center"><img alt="topFiveApp" src="<%out.print(request.getParameter("topFiveAppDeMoisURL"));%>" /></td>
						<td align="center"><img alt="topFiveApp" src="<%out.print(request.getParameter("topFiveAppDer30JURL"));%>" /></td>
					</tr>
				<%} %>
				<%if("true".equals(request.getParameter("service"))){ %>
					<tr>
						<td align="center" colspan="2" class="titreTd"><span class="titreTable">Services</span></td>
					</tr>
					<tr>
						<td align="center"><img alt="topFiveService" src="<%out.print(request.getParameter("topFiveServiceDeMoisURL"));%>" /></td>
						<td align="center"><img alt="topFiveService" src="<%out.print(request.getParameter("topFiveServiceDer30JURL"));%>" /></td>
					</tr>
				<%} %>
				<%if("true".equals(request.getParameter("domaine"))){ %>
					<tr>
						<td align="center" colspan="2" class="titreTd"><span class="titreTable">Domaines</span></td>
					</tr>
					<tr>
						<td align="center"><img alt="topFiveEnviro" src="<%out.print(request.getParameter("topFiveEnviroDeMoisURL"));%>" /></td>
						<td align="center"><img alt="topFiveEnviro" src="<%out.print(request.getParameter("topFiveEnviroDer30JURL"));%>" /></td>
					</tr>
				<%} %>
			</table>
		<%} %>
	</div>
</body>
</html>
