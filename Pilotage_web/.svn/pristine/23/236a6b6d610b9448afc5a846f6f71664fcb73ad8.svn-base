<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/bilan.css" />
	<title><s:property value="titre" /></title>
</head>
<body>
<s:set name="indType" value="0"/>
	<table width="95%" align="center" class="outTable">
		<tr>
			<td>
				<table width="100%" class="innerTable" cellpadding="0px" rules="all">
					<tr class="header">
						<td class="logo">
							<img alt="Logo" src='<s:property value="#session.ENSEIGNE" />/img/logo.png' /><br/>
							<s:property value="service" escape="false" />
						</td>
						<td width="
						<s:if test="typeDeBilan.vacation == 1 && dateFin == null">
							50%
						</s:if>
						<s:else>
							70%
						</s:else>
						" class="BigTitle">
							<s:property value="typeDeBilan.libelle"/> du <s:date name="selectedDate" format="dd/MM/yyyy" />
							<s:if test="dateFin != null"> au <s:date name="dateFin" format="dd/MM/yyyy" /></s:if>
						</td>
						<s:if test="typeDeBilan.vacation == 1 && dateFin == null">
							<td align="center" width="20%">
								<table width="100%" border="0">
									<tr align="center">
										<td colspan="2" style="border: none;" class="title">Responsables de vacations</td>
									</tr>
									<tr>
										<td class="vacationHours" style="border: none;">07h15/15h00 :</td>
										<td class="vacationName" style="border: none;">
											<s:if test="bilan.vacationmatin == null">Non v&eacute;rifi&eacute;</s:if>
											<s:else><s:property value="bilan.vacationmatin.nom"/>&nbsp;<s:property value="bilan.vacationmatin.prenom"/></s:else>
										</td>
									</tr>
									<tr>
										<td class="vacationHours" style="border: none;">14h45/22h30 :</td>
										<td class="vacationName" style="border: none;">
											<s:if test="bilan.vacationaprem == null">Non v&eacute;rifi&eacute;</s:if>
											<s:else><s:property value="bilan.vacationaprem.nom"/>&nbsp;<s:property value="bilan.vacationaprem.prenom"/></s:else>
										</td>
									</tr>
									<tr>
										<td class="vacationHours" style="border: none;">22h15/07h30 :</td>
										<td class="vacationName" style="border: none;">
											<s:if test="bilan.vacationnuit == null">Non v&eacute;rifi&eacute;</s:if>
											<s:else><s:property value="bilan.vacationnuit.nom"/>&nbsp;<s:property value="bilan.vacationnuit.prenom"/></s:else>
										</td>
									</tr>
								</table>
							</td>
						</s:if>
					</tr>
					<s:if test="typeDeBilan.actionEPI && dateFin == null">
						<tr>
							<td colspan="3" style="border: none;">
								<table width="100%" cellpadding="0px" cellspacing="0px" class="outTable" border="0px">
									<tr>
										<td colspan="14" class="title">Pilotage Opérationnel</td>
									</tr>
									<tr>
										<td colspan="10" class="operationTitle">Résumé du jour</td>
										<td colspan="4" class="operationTitle">Résumé du mois</td>
									</tr>
									
									<tr>
										<td width="6%" class="operationTitle">Nombre d’incidents</td>
										<td width="6%" class="operationTitle">Incidents critiques</td>
										<td width="6%" class="operationTitle">Incidents résolus</td>
										<td width="6%" class="operationTitle">Incidents de production</td>
										<td width="6%" class="operationTitle">Incidents « autres environnements »</td>										
										<td width="6%" class="operationTitle">Nombre d’alertes</td>
										<td width="6%" class="operationTitle">Alertes résolus</td>
										<td width="6%" class="operationTitle">Alertes de production</td>
										<td width="6%" class="operationTitle">Alertes « autres environnements »</td>										
										<td width="6%" class="operationTitle">Appels astreintes</td>									
										<td width="5%" class="operationTitle">Nombre d’incidents</td>
										<td width="5%" class="operationTitle">Nombre moyen d’incidents</td>
										<td width="5%" class="operationTitle">Nombre d’alertes</td>
										<td width="5%" class="operationTitle">Nombre moyen d’alertes</td>
									</tr>
									<tr>
										<td class="operationValue"><s:property value="incidentsAuJour"/></td>
										<td class="operationValue"><s:property value="incidentsCritiques"/></td>
										<td class="operationValue"><s:property value="incidentsResolu"/></td>
										<td class="operationValue"><s:property value="incidentsDeProduction"/></td>
										<td class="operationValue"><s:property value="incidentsAutreEnvir"/></td>
										<td class="operationValue"><s:property value="alertesAuJour"/></td>
										<td class="operationValue"><s:property value="alertesResolu"/></td>
										<td class="operationValue"><s:property value="alertesDeProduction"/></td>
										<td class="operationValue"><s:property value="alertesAutreEnvir"/></td>
										<td class="operationValue"><s:property value="astreintesAppel"/></td>
										<td class="operationValue"><s:property value="incidentsAuMois"/></td>
										<td class="operationValue"><s:property value="incidentsMoyenAuMois"/></td>
										<td class="operationValue"><s:property value="alertesAuMois"/></td>
										<td class="operationValue"><s:property value="alertesMoyenAuMois"/></td>
									</tr>
								</table>
							</td>
						</tr>
					</s:if>
				</table>
			</td>
		</tr>
		<!-- ************************************** -->
		<!-- ***** start of the incidents part **** -->
		<!-- ************************************** -->

		<tr>
			<td class="separation"></td>
		</tr>
		<tr>
			<td class="title">Incident de Production</td>
		</tr>
		
		<s:set name="complet" value="false"/>
		<s:iterator value="listTypeIncident" status="index">
			<s:if test='incidentMap.get(incidentDeProduction) == null || incidentMap.get(incidentDeProduction).isEmpty() || (#complet==false && id!=incidentMap.get(incidentDeProduction)[0].type.id)'>
				<tr>
					<td>
						<table width="100%" align="center" class="innerTable">
							<tr>
								<td colspan='<s:property value="listBilanColonnes.size()"/>' class="incidentTableTitle">
									<s:property value="titre_bilan"/>
								</td>
							</tr>
		
							<tr>
								<s:iterator value="listBilanColonnes" status="ind">
									<td width="<s:property value="%{listBilanColonnes[#ind.index].taille}"/>%" class="incidentTableTH"><s:property value="%{listBilanColonnes[#ind.index].nomDsBilan}"/></td>
								</s:iterator>
							</tr>
							
						</table>
					</td>
				</tr>
				
				<tr>
					<td class="separation"></td>
				</tr>
			</s:if>
			<s:else>
				<s:set name="complet" value="true"/>
				<s:if test="id==incidentMap.get(incidentDeProduction)[0].type.id">
					<s:set name="indType" value="#index.index"/>
				</s:if>
			</s:else>
		</s:iterator>
		
		<s:set name="complet" value="false"/>
		<s:if test="incidentMap.get(incidentDeProduction) != null && !incidentMap.get(incidentDeProduction).isEmpty()">
			<s:iterator value="incidentMap.get(incidentDeProduction)" id="incidentProd" status="ind">
				
			<s:if test="#ind.index==0 || incidentMap.get(incidentDeProduction)[#ind.index-1].type.id!=incidentMap.get(incidentDeProduction)[#ind.index].type.id">
					<tr>
						<td class="separation"></td>
					</tr>
					<tr>
						<td>
							<table width="100%" align="center" class="innerTable">
								<tr>
									<td colspan='<s:property value="listBilanColonnes.size()"/>' class="incidentTableTitle">
										<s:property value="%{listTypeIncident[#indType].titre_bilan}"/>
									</td>
								</tr>
			
								<tr>
								<s:iterator value="listBilanColonnes" status="ind">
									<td width="<s:property value="%{listBilanColonnes[#ind.index].taille}"/>%" class="incidentTableTH"><s:property value="%{listBilanColonnes[#ind.index].nomDsBilan}"/></td>
								</s:iterator>
								</tr>
				</s:if>
								<tr>
									<s:iterator value="listBilanColonnes" id="bilancolonne" status="stat">
										<td>
											<s:if test='incidentColonne.nomColonne.equals("machine")'>
												<s:property value="%{machine.nom}"/>
											</s:if>
											<s:elseif test='incidentColonne.nomColonne.equals("applicatif")'>
												<s:iterator value='appMap.get(#incidentProd.id)' id="app" status="status">
													<s:property value='%{appMap.get(#incidentProd.id)[#status.index].applicatif}'/>
													<s:if test="! #status.last">
														<br/>
													</s:if>
												</s:iterator>
											</s:elseif>
											<s:elseif test='incidentColonne.nomColonne.equals("hard_software")'>
												<s:iterator value="hardMap.get(#incidentProd.id)" status="status">
													<s:property value="%{hardMap.get(#incidentProd.id)[#status.index].libelle}"/>
													<s:if test="! #status.last">
														<br/>
													</s:if>
												</s:iterator>
											</s:elseif>
											<s:elseif test='incidentColonne.nomColonne.equals("idOutil")'>
												<s:property value="%{idOutil.nomOutils}"/>
											</s:elseif>
											<s:elseif test='incidentColonne.nomColonne.equals("datedebut")'>
												<s:date name="%{dateDebut}" format="dd/MM/yyyy" /><br/><s:date name="%{dateDebut}" format="HH:mm" />
											</s:elseif>
											<s:elseif test='incidentColonne.nomColonne.equals("datedebut_gmt")'>
												<s:if test="%{heureDebutOceorMap.get(#incidentProd.id) != null}">
													<s:date name="heureDebutOceorMap.get(#incidents.id)" format="dd/MM/yyyy" /><br/><s:date name="heureDebutOceorMap.get(#incidents.id)" format="HH:mm" />
												</s:if>
											</s:elseif>
											<s:elseif test='incidentColonne.nomColonne.equals("observation")'>
												<s:property value="observation.replaceAll('\n','<br/>')" escape="false"/>
											</s:elseif>
											<s:elseif test='incidentColonne.nomColonne.equals("action")'>
												<s:property value="action.replaceAll('\n','<br/>')" escape="false"/>
											</s:elseif>
											<s:elseif test='incidentColonne.nomColonne.equals("ars")'>
												<s:property value="ars"/>
											</s:elseif>
											<s:elseif test='incidentColonne.nomColonne.equals("datefin")'>
												<s:if test="dateFin == null"> En cours</s:if>
												<s:else>
													<s:date name="%{dateFin}" format="dd/MM/yyyy" /><br/><s:date name="%{dateFin}" format="HH:mm" />
												</s:else>
											</s:elseif>
											<s:elseif test='incidentColonne.nomColonne.equals("datefin_gmt")'>
												<s:if test="dateFin == null && heureDebutOceorMap.get(#incidentProd.id) != null"> En cours</s:if>
												<s:else>
													<s:if test="%{heureFinOceorMap.get(#incidents.id) != null}">
														<s:date name="heureFinOceorMap.get(#incidents.id)" format="HH:mm" />
													</s:if>
												</s:else>
											</s:elseif>
											<s:elseif test='incidentColonne.nomColonne.equals("aAstreinte")'>
												<s:iterator value="astreinteMap.get(#incidentProd.id)" status="status">
													<s:property value="astreinte.nom"/>&nbsp;<s:property value="astreinte.prenom"/>
													<s:if test="! #status.last">
														<br/>
													</s:if>
												</s:iterator>
											</s:elseif>
											<s:elseif test='incidentColonne.nomColonne.equals("appli_ordonnanceur")'>
												<s:property value="appli_ordonnanceur"/>
											</s:elseif>
											<s:elseif test='incidentColonne.nomColonne.equals("job")'>
												<s:property value="job"/>
											</s:elseif>
											<s:elseif test='incidentColonne.nomColonne.equals("reprise")'>
												<s:if test="dateFin == null"> KO</s:if>
												<s:if test="dateFin != null"> OK</s:if>
											</s:elseif>
										</td>
									</s:iterator>
								</tr>
				<s:if test="#ind.index+1==incidentMap.get(incidentDeProduction).size || incidentMap.get(incidentDeProduction)[#ind.index].type.id!=incidentMap.get(incidentDeProduction)[#ind.index+1].type.id">
							</table>
						</td>
					</tr>
					<s:set name="indType" value="%{#indType+1}"/>
								
					<s:if test="#indType<5 && incidentMap.get(incidentDeProduction).size>#ind.index+1 && listTypeIncident[#indType].id!=incidentMap.get(incidentDeProduction)[#ind.index+1].type.id">
						<s:iterator value="listTypeIncident" begin="#indType" status="index">
							<s:if test='#complet==false && id!=incidentMap.get(incidentDeProduction)[#ind.index+1].type.id'>
								<tr>
									<td class="separation"></td>
								</tr>
								<tr>
									<td>
										<table width="100%" align="center" class="innerTable">
											<tr>
												<td colspan='<s:property value="listBilanColonnes.size()"/>' class="incidentTableTitle">
													<s:property value="titre_bilan"/>
												</td>
											</tr>
						
											<tr>
												<s:iterator value="listBilanColonnes" status="ind">
													<td width="<s:property value="%{listBilanColonnes[#ind.index].taille}"/>%" class="incidentTableTH"><s:property value="%{listBilanColonnes[#ind.index].nomDsBilan}"/></td>
												</s:iterator>
											</tr>
											
										</table>
									</td>
								</tr>
								<s:set name="indType" value="%{#indType+1}"/>
							</s:if>
							<s:else>
								<s:set name="complet" value="true"/>
								<s:if test="id==incidentMap.get(incidentDeProduction)[0].type.id">
									<s:set name="indType" value="#index.index"/>
								</s:if>
							</s:else>
						</s:iterator>
				</s:if>
				</s:if>
			</s:iterator>
		</s:if>
		
		<tr>
			<td class="separation"></td>
		</tr>
		
		<s:set name="complet" value="false"/>
		<s:iterator value="listTypeIncident" status="in">
			<s:if test="#complet==true">
				<tr>
					<td>
						<table width="100%" align="center" class="innerTable">
							<tr>
								<td colspan='<s:property value="listBilanColonnes.size()"/>' class="incidentTableTitle">
									<s:property value="titre_bilan"/>
								</td>
							</tr>
		
							<tr>
								<s:iterator value="listBilanColonnes" status="ind">
									<td width="<s:property value="%{listBilanColonnes[#ind.index].taille}"/>%" class="incidentTableTH"><s:property value="%{listBilanColonnes[#ind.index].nomDsBilan}"/></td>
								</s:iterator>
							</tr>
							
						</table>
					</td>
				</tr>
				<tr>
					<td class="separation"></td>
				</tr>
			</s:if>
		
			<s:if test='id==incidentMap.get(incidentDeProduction)[incidentMap.get(incidentDeProduction).size-1].type.id'>
				<s:set name="complet" value="true"/>
			</s:if>
		</s:iterator>
		
		<!-- Other Incidents -->
		
		<s:iterator value="listEnvironType" id="envType">
			<s:set name="indType" value="0"/>
			<s:if test="incidentMap.get(#envType) != null && !incidentMap.get(#envType).isEmpty()">
				<tr>
					<td class="separation"></td>
				</tr>
				<tr>
					<td colspan='<s:property value="listBilanColonnes.size()"/>' class="title"> Incident de <s:property value="type"/></td>
				</tr>
				
				<s:set name="complet" value="false"/>
				<s:iterator value="listTypeIncident" status="index">
					<s:if test='incidentMap.get(#envType) == null || incidentMap.get(#envType).isEmpty() || (#complet==false && id!=incidentMap.get(#envType)[0].type.id)'>
						<tr>
							<td>
								<table width="100%" align="center" class="innerTable">
									<tr>
										<td colspan='<s:property value="listBilanColonnes.size()"/>' class="incidentTableTitle">
											<s:property value="titre_bilan"/>
										</td>
									</tr>
				
									<tr>
										<s:iterator value="listBilanColonnes" status="ind">
											<td width="<s:property value="%{listBilanColonnes[#ind.index].taille}"/>%" class="incidentTableTH"><s:property value="%{listBilanColonnes[#ind.index].nomDsBilan}"/></td>
										</s:iterator>
									</tr>
									
								</table>
							</td>
						</tr>
						
						<tr>
							<td class="separation"></td>
						</tr>
					</s:if>
					<s:else>
						<s:set name="complet" value="true"/>
						<s:if test="id==incidentMap.get(#envType)[0].type.id">
							<s:set name="indType" value="#index.index"/>
						</s:if>
					</s:else>
				</s:iterator>
				
				<s:set name="complet" value="false"/>
				<s:if test="incidentMap.get(#envType) != null && !incidentMap.get(#envType).isEmpty()">
					<s:iterator value="incidentMap.get(#envType)" id="incidents" status="ind">
						
						<s:if test="#ind.index==0 || incidentMap.get(#envType)[#ind.index-1].type.id!=incidentMap.get(#envType)[#ind.index].type.id">
							<tr>
								<td class="separation"></td>
							</tr>
							<tr>
							<td>
								<table width="100%" align="center" class="innerTable">
									<tr>
										<td colspan='<s:property value="listBilanColonnes.size()"/>' class="incidentTableTitle">
											<s:property value="%{listTypeIncident[#indType].titre_bilan}"/>
										</td>
									</tr>
				
									<tr>
									<s:iterator value="listBilanColonnes" status="ind">
										<td width="<s:property value="%{listBilanColonnes[#ind.index].taille}"/>%" class="incidentTableTH"><s:property value="%{listBilanColonnes[#ind.index].nomDsBilan}"/></td>
									</s:iterator>
									</tr>
						</s:if>
									<tr>
										<s:iterator value="listBilanColonnes" id="bilancolonne" status="stat">
											<td>
												<s:if test='incidentColonne.nomColonne.equals("machine")'>
													<s:property value="%{machine.nom}"/>
												</s:if>
												<s:elseif test='incidentColonne.nomColonne.equals("applicatif")'>
													<s:iterator value='appMap.get(#incidents.id)' id="app" status="status">
														<s:property value='%{appMap.get(#incidents.id)[#status.index].applicatif}'/>
														<s:if test="! #status.last">
															<br/>
														</s:if>
													</s:iterator>
												</s:elseif>
												<s:elseif test='incidentColonne.nomColonne.equals("hard_software")'>
													<s:iterator value="hardMap.get(#incidents.id)" status="status">
														<s:property value="%{hardMap.get(#incidents.id)[#status.index].libelle}"/>
														<s:if test="! #status.last">
															<br/>
														</s:if>
													</s:iterator>
												</s:elseif>
												<s:elseif test='incidentColonne.nomColonne.equals("idOutil")'>
													<s:property value="%{idOutil.nomOutils}"/>
												</s:elseif>
												<s:elseif test='incidentColonne.nomColonne.equals("datedebut")'>
													<s:date name="%{dateDebut}" format="dd/MM/yyyy" /><br/><s:date name="%{dateDebut}" format="HH:mm" />
												</s:elseif>
												<s:elseif test='incidentColonne.nomColonne.equals("datedebut_gmt")'>
													<s:if test="%{heureDebutOceorMap.get(#incidents.id) != null}">
													<s:date name="heureDebutOceorMap.get(#incidents.id)" format="dd/MM/yyyy" /><br/><s:date name="heureDebutOceorMap.get(#incidents.id)" format="HH:mm" />
												</s:if>
												</s:elseif>
												<s:elseif test='incidentColonne.nomColonne.equals("observation")'>
													<s:property value="observation.replaceAll('\n','<br/>')" escape="false"/>
												</s:elseif>
												<s:elseif test='incidentColonne.nomColonne.equals("action")'>
													<s:property value="action.replaceAll('\n','<br/>')" escape="false"/>
												</s:elseif>
												<s:elseif test='incidentColonne.nomColonne.equals("ars")'>
													<s:property value="ars"/>
												</s:elseif>
												<s:elseif test='incidentColonne.nomColonne.equals("datefin")'>
													<s:if test="dateFin == null"> En cours</s:if>
													<s:else><s:date name="dateFin" format="HH:mm" /></s:else>
												</s:elseif>
												<s:elseif test='incidentColonne.nomColonne.equals("datefin_gmt")'>
													<s:if test="dateFin == null && heureDebutOceorMap.get(#incidents.id) != null"> En cours</s:if>
													<s:else>
														<s:if test="%{heureFinOceorMap.get(#incidents.id) != null}">
															<s:date name="heureFinOceorMap.get(#incidents.id)" format="HH:mm" />
														</s:if>
													</s:else>
												</s:elseif>
												<s:elseif test='incidentColonne.nomColonne.equals("aAstreinte")'>
													<s:iterator value="astreinteMap.get(incidentMap.get(#incidents.id)" status="status">
														<s:property value="nom"/>&nbsp;<s:property value="prenom"/>
														<s:if test="! #status.last">
															<br/>
														</s:if>
													</s:iterator>
												</s:elseif>
												<s:elseif test='incidentColonne.nomColonne.equals("appli_ordonnanceur")'>
													<s:property value="appli_ordonnanceur"/>
												</s:elseif>
												<s:elseif test='incidentColonne.nomColonne.equals("job")'>
													<s:property value="job"/>
												</s:elseif>
												<s:elseif test='incidentColonne.nomColonne.equals("reprise")'>
													<s:if test="dateFin == null"> KO</s:if>
													<s:if test="dateFin != null"> OK</s:if>
												</s:elseif>
											</td>
										</s:iterator>
									</tr>
						<s:if test="#ind.index+1==incidentMap.get(#envType).size || incidentMap.get(#envType)[#ind.index].type.id!=incidentMap.get(#envType)[#ind.index+1].type.id">
									</table>
								</td>
							</tr>
							<s:set name="indType" value="%{#indType+1}"/>
						
							<s:if test="#indType<5 && incidentMap.get(#envType).size>#ind.index+1 && listTypeIncident[#indType].id!=incidentMap.get(#envType)[#ind.index+1].type.id">
								<s:iterator value="listTypeIncident" begin="#indType" >
									<s:if test='#complet==false && id!=incidentMap.get(#envType)[#ind.index+1].type.id'>
										<tr>
											<td class="separation"></td>
										</tr>
										<tr>
											<td>
												<table width="100%" align="center" class="innerTable">
													<tr>
														<td colspan='<s:property value="listBilanColonnes.size()"/>' class="incidentTableTitle">
															<s:property value="titre_bilan"/>
														</td>
													</tr>
								
													<tr>
														<s:iterator value="listBilanColonnes" status="ind">
															<td width="<s:property value="%{listBilanColonnes[#ind.index].taille}"/>%" class="incidentTableTH"><s:property value="%{listBilanColonnes[#ind.index].nomDsBilan}"/></td>
														</s:iterator>
													</tr>
													
												</table>
											</td>
										</tr>
										<s:set name="indType" value="%{#indType+1}"/>
									</s:if>
									<s:else>
										<s:set name="complet" value="true"/>
										<s:if test="id==incidentMap.get(#envType)[0].type.id">
											<s:set name="indType" value="#index.index"/>
										</s:if>
									</s:else>
								</s:iterator>
								
							</s:if>
						</s:if>
					</s:iterator>
				</s:if>
			</s:if>
		</s:iterator>
		
		<tr>
			<td class="separation"></td>
		</tr>
		
		<s:if test="incidentMap.get(#envType) != null && !incidentMap.get(#envType).isEmpty()">
			<s:if test='#indType<listTypeIncident.size'>
				<s:iterator value="listTypeIncident" begin="#indType" status="ind">
					<tr>
						<td>
							<table width="100%" align="center" class="innerTable">
								<tr>
									<td colspan='<s:property value="listBilanColonnes.size()"/>' class="incidentTableTitle">
										<s:property value="%{listTypeIncident[#indType].titre_bilan}"/>
									</td>
								</tr>
			
								<tr>
								<s:iterator value="listBilanColonnes" status="ind">
									<td width="<s:property value="%{listBilanColonnes[#ind.index].taille}"/>%" class="incidentTableTH"><s:property value="%{listBilanColonnes[#ind.index].nomDsBilan}"/></td>
								</s:iterator>
								</tr>
							</table>
						</td>
					</tr>
					<s:set name="indType" value="%{#indType+1}"/>
					<tr>
						<td class="separation"></td>
					</tr>
				</s:iterator>
			</s:if>
		</s:if>
		
		<!-- ************************************** -->
		<!-- ***** end of the incident part ******* -->
		<!-- ************************************** -->
		
		<!-- ************************************** -->
		<!-- ***** 		espace disque part 	******* -->
		<!-- ************************************** -->
		<tr>
			<td class="separation"></td>
		</tr>
		<s:if test="typeDeBilan.espaceDisk">
			<s:if test="typeDeBilan.disknonOCEOR">
				<tr>
					<td class="title"> Etat des espaces disques </td>
				</tr>
				<s:if test="listEspaceDate == null || listEspaceDate.isEmpty()">
					<tr>
						<td>
							<table width="100%" align="center" class="innerTable">
								<tr>
									<td width="30%" class="espaceDisqueTH">Serveur</td>
									<td width="30%" class="espaceDisqueTH">% d'occupation J-1</td>
									<td width="30%" class="espaceDisqueTH">% d'occupation J</td>
									<td width="10%" class="espaceDisqueTH">Seuil</td>
								</tr>
								<s:iterator value="listDisques">
									<tr>
										<td class="espaceDisqueString"><s:property value="libelle"/></td>
										<td colspan="3" class="noData"> Pas d'espace disque</td>
									</tr>
								</s:iterator>
							</table>
						</td>
					</tr>
				</s:if>
				<s:else>
					<s:iterator value="listEspaceDate" id="dateEspaceItem">
						<tr>
							<td class="title"><s:property value="dateEspaceItem" /></td>
						</tr>
						<tr>
							<td>
								<table width="100%" align="center" class="innerTable">
									<tr>
										<td width="30%" class="espaceDisqueTH">Serveur</td>
										<td width="30%" class="espaceDisqueTH">% d'occupation J-1</td>
										<td width="30%" class="espaceDisqueTH">% d'occupation J</td>
										<td width="10%" class="espaceDisqueTH">Seuil</td>
									</tr>
									<s:iterator value="listDisques">
										<s:if test="espaceMap.get(#dateEspaceItem).get(id) != null && ((filiale && typeDeBilan.disknonOCEOR) || (!filiale && typeDeBilan.espaceDisk))">
											<tr>
												<td class="espaceDisqueString"><s:property value="libelle" /></td>
												<td class="espaceDisqueNumber">
													<s:if test="espaceMap.get(#dateEspaceItem).get(id)[1] != null">
														<s:text name="format.percent"><s:param value="espaceMap.get(#dateEspaceItem).get(id)[1]" /></s:text>%
													</s:if>
												</td>
												<td class="espaceDisqueNumber">
													<s:if test="espaceMap.get(#dateEspaceItem).get(id)[2] != null">
														<s:text name="format.percent"><s:param value="espaceMap.get(#dateEspaceItem).get(id)[2]" /></s:text>%
													</s:if>
												</td>
												<td class="espaceDisqueNumber">
													<s:if test="espaceMap.get(#dateEspaceItem).get(id)[0] != null">
														<s:text name="format.percent"><s:param value="espaceMap.get(#dateEspaceItem).get(id)[0]" /></s:text>%
													</s:if>
												</td>
											</tr>
										</s:if>
									</s:iterator>
								</table>
							</td>
						</tr>
					</s:iterator>
				</s:else>
			</s:if>

			<!-- ************************************** -->
			<!-- ***** 	alertes disques part 	******* -->
			<!-- ************************************** -->
			<tr>
				<td class="separation"></td>
			</tr>
			<tr>
				<td class="title"> Alertes FileSystem/TableSpace </td>
			</tr>
			<s:if test="listAlertesDate == null || listAlertesDate.isEmpty()">
				<tr>
					<td>
						<table width="100%" align="center" class="innerTable">
							<tr>
								<td class="alerteDisqueTH" width="25%">Heure</td>
								<td class="alerteDisqueTH" width="15%">Systeme</td>
								<td class="alerteDisqueTH" width="15%">FS</td>
								<td class="alerteDisqueTH" width="15%">Type</td>
								<td class="alerteDisqueTH" width="15%">Seuil</td>
								<td class="alerteDisqueTH" width="15%">Alerte</td>
							</tr>
							<tr>
								<td colspan="6" class="noData">Pas d’alertes saisies </td>
							</tr>
						</table>
					</td>
				</tr>
			</s:if>
			<s:else>
				<s:iterator value="listAlertesDate" id="dateAlertesItem">
					<tr>
						<td class="title"><s:property value="dateAlertesItem" /></td>
					</tr>
					<tr>
						<td>
							<table width="100%" align="center" class="innerTable">
								<tr>
									<td class="alerteDisqueTH" width="25%">Heure</td>
									<td class="alerteDisqueTH" width="15%">Systeme</td>
									<td class="alerteDisqueTH" width="15%">FS</td>
									<td class="alerteDisqueTH" width="15%">Type</td>
									<td class="alerteDisqueTH" width="15%">Seuil</td>
									<td class="alerteDisqueTH" width="15%">Alerte</td>
								</tr>
								<s:iterator value="alerteMap.get(#dateAlertesItem)">
									<tr>
										<td class="alerteDisqueString"><s:date name="date" format="dd/MM/yyyy HH:mm" /></td>
										<td class="alerteDisqueString"><s:property value="systeme.nom" /></td>
										<td class="alerteDisqueString"><s:property value="fs" /></td>
										<td class="alerteDisqueString"><s:property value="type.type" /></td>
										<td class="alerteDisqueNumber"><s:property value="seuil" />%</td>
										<td class="alerteDisqueNumber"><s:property value="alerte" />%</td>
									</tr>
								</s:iterator>
							</table>
						</td>
					</tr>
				</s:iterator>
			</s:else>
		</s:if>
		
		<!-- ************************************** -->
		<!-- ***** 		flux CFT part 		******* -->
		<!-- ************************************** -->
		<tr>
			<td class="separation"></td>
		</tr>
		<s:if test="typeDeBilan.etatCFT">
			<tr>
				<td class="title">Flux CFT en erreur</td>
			</tr>
			<s:if test="listFluxDate == null || listFluxDate.isEmpty()">
				<tr>
					<td>
						<table width="100%" align="center" class="innerTable">
							<s:iterator value="listFlux">
								<tr>
									<td class="fluxName"><s:property value="libelle" /></td>
								</tr>
								<tr>
									<td class="fluxValue">Pas d’incident</td>
								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
			</s:if>
			<s:else>
				<s:iterator value="listFluxDate" id="dateFluxItem">
					<tr>
						<td class="title"><s:property value="dateFluxItem" /></td>
					</tr>
					<tr>
						<td>
							<table width="100%" align="center" class="innerTable">
								<s:iterator value="listFlux">
									<tr>
										<td class="fluxName"><s:property value="libelle" /></td>
									</tr>
									<tr>
										<td class="fluxValue">
											<s:if test="fluxMap.get(#dateFluxItem).get(id) == null">
												Pas d’incident
											</s:if>
											<s:else>
												<s:property value="fluxMap.get(#dateFluxItem).get(id).replaceAll('<', '&lt;').replaceAll('\n', '<br/>')" escape="false" />
											</s:else>
										</td>
									</tr>
								</s:iterator>
							</table>
						</td>
					</tr>
				</s:iterator>
			</s:else>
		</s:if>
	</table>
</body>
</html>