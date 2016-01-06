<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function retour(){
			<s:if test="fromValidation">
				submitData('showValiderDerogationAction.action');
			</s:if>
			<s:else>
				document.getElementById("mainForm").submit();
			</s:else>
		}
	</script>
</head>
<body>
<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('DRG_DET')" /></s:param>
	</s:include>
	
	<div class="contentTable">
		<table class="formTable" width="100%">
			<tr>
				<td align="center">
					<s:a href="#" cssClass="boutonRetour" onclick="javascript:retour();">Retour</s:a>
				</td>
			</tr>
				
			<tr>
				<td align="center">
					<table width="80%" rules="all" cellpadding="2">
						<col width=""></col>
						<col width="50%"></col>
						<col width="50%"></col>
						<tr>
							<td valign="top"><img src="<s:property value="#session.ENSEIGNE" />/img/logo.png"/></td>
							<td>
								<table width="100%">
									<tr>
										<td class="detailDerogationTitle">Type de dérogation</td>
									</tr>
									<tr>
										<td class="detailDerogationText"><s:property value="derogation.idType.type"/></td>
									</tr>
								</table>
							</td>
							<td>
								<table width="100%">
									<tr>
										<td class="detailDerogationTitle">Date de demande</td>
									</tr>
									<tr>
										<td class="detailDerogationText"><s:date name="%{derogation.date_creation}" format="dd/MM/yyyy" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td class="detailDerogationTextPartie">
					Dérogation numéro: <s:property value="derogation.id"/>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center">
					<table class="detailDerogationTable" rules="all">
						<col width="40%"></col>
						<col width="60%"></col>
						<tr>
							<td class="detailDerogationTitreTableau" colspan="2">Partie Demandeur</td>
						</tr>
						<tr>
							<td class="detailDerogationKeyTableau">Nom du demandeur</td>
							<td class="detailDerogationValueTableau">
								<s:property value="derogation.idNomDemandeur.nom + ' ' + derogation.idNomDemandeur.prenom"/>
							</td>
						</tr>
						<tr>
							<td class="detailDerogationKeyTableau">T&eacute;l&eacute;phone</td>
							<td class="detailDerogationValueTableau">
								<s:property value="derogation.telephone"/>
							</td>
						</tr>
						<tr>
							<td class="detailDerogationKeyTableau">Nom Application</td>
							<td class="detailDerogationValueTableau">
								<s:property value="derogation.idAppli.applicatif"/>
							</td>
						</tr>
						<tr>
							<td class="detailDerogationKeyTableau">N° Fiche Ars</td>
							<td class="detailDerogationValueTableau">
								<s:property value="derogation.numars"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center">
					<table class="detailDerogationTable" rules="all">
						<col width="40%"></col>
						<col width="60%"></col>
						<tr>
							<td class="detailDerogationTitreTableau" colspan="2">Partie Risque</td>
						</tr>
						<tr>
							<td class="detailDerogationKeyTableau">Type de changement</td>
							<td class="detailDerogationValueTableau">
								<s:property value="derogation.typeChangement.typeChangement"/>
							</td>
						</tr>
						<tr>
							<td class="detailDerogationKeyTableau">Retour arri&egrave;re pr&eacute;vu</td>
							<td class="detailDerogationValueTableau">
								<s:if test="derogation.retourArriere = 1">Oui</s:if>
								<s:else>Non</s:else>
							</td>
						</tr>
						<tr>
							<td class="detailDerogationKeyTableau">Communication externe</td>
							<td class="detailDerogationValueTableau">
								<s:if test="derogation.externe = 1">Oui</s:if>
								<s:else>Non</s:else>
							</td>
						</tr>
						<tr>
							<td class="detailDerogationKeyTableau">Liste des services clients impactés</td>
							<td class="detailDerogationValueTableau">
								<s:property value="derogation.clientTouche"/>
							</td>
						</tr>
						<tr>
							<td class="detailDerogationKeyTableau">Liste des services impactés</td>
							<td class="detailDerogationValueTableau">
								<s:property value="derogation.serviceImpact"/>
							</td>
						</tr>
						<tr>
							<td class="detailDerogationKeyTableau">Justificatif du changement</td>
							<td class="detailDerogationValueTableau">
								<s:property value="derogation.justificatif"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center">
					<table class="detailDerogationTable" rules="all">
						<col width="40%"></col>
						<col width="60%"></col>
						<tr>
							<td class="detailDerogationTitreTableau" colspan="2">Partie Op&eacute;rationnelle</td>
						</tr>
						<tr>
							<td class="detailDerogationKeyTableau">Date et Heure demand&eacute;</td>
							<td class="detailDerogationValueTableau">
								<s:date name="%{derogation.timeDemande}" format="dd/MM/yyyy" /><br />
								<s:property value="derogation.heureDemande" />
							</td>
						</tr>
						<tr>
							<td class="detailDerogationKeyTableau">Nom de l'&eacute;quipe concern&eacute;e par la mise en production</td>
							<td class="detailDerogationValueTableau">
								<s:property value="derogation.service"/>
							</td>
						</tr>
						<tr>
							<td class="detailDerogationKeyTableau">Nom du r&eacute;alisateur de la mise en production</td>
							<td class="detailDerogationValueTableau">
								<s:property value="derogation.realisateur"/>
							</td>
						</tr>
						<tr>
							<td class="detailDerogationKeyTableau">Pendant ouverture de TP</td>
							<td class="detailDerogationValueTableau">
								<s:if test="derogation.tp = 1">Oui</s:if>
								<s:else>Non</s:else>
							</td>
						</tr>
						<tr>
							<td class="detailDerogationKeyTableau">Pr&eacute;sence des &eacute;tudes</td>
							<td class="detailDerogationValueTableau">
								<s:if test="derogation.etude = 1">Oui</s:if>
								<s:else>Non</s:else>
							</td>
						</tr>
						<tr>
							<td class="detailDerogationKeyTableau">Recette r&eacute;alis&eacute;e</td>
							<td class="detailDerogationValueTableau">
								<s:if test="derogation.recette = 1">Oui</s:if>
								<s:else>Non</s:else>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td class="detailDerogationTextPartie">
					Description de la d&eacute;rogation
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center">
					<s:property value='derogation.description.replaceAll("<", "&lt;").replaceAll("\n", "<br/>")' escape="false"/>
					
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center">
					<s:a href="#" cssClass="boutonRetour" onclick="javascript:retour();">Retour</s:a>
				</td>
			</tr>
		</table>
	</div>

	<s:form theme="simple" name="mainForm" action="showDerogationAction" method="POST" id="mainForm">
		<s:hidden name="sort" id="sort"/>
		<s:hidden name="sens" id="sens"/>
		<s:hidden name="page" id="page"/>
		<s:hidden name="nrPages" id="nrPages"/>
		<s:hidden name="nrPerPage" id="nrPerPage"/>
		
		<s:hidden name="filtreNumero" id="filtreNumero"/>
		<s:hidden name="filtreDate" id="filtreDate"/>
		<s:hidden name="filtreAppli" id="filtreAppli"/>
		<s:hidden name="filtreTp" id="filtreTp"/>
		<s:hidden name="filtreDtc" id="filtreDtc"/>
		<s:hidden name="filtreEtat" id="filtreEtat"/>
	</s:form>
</body>
</html>