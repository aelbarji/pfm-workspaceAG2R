<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />

	<link rel="stylesheet" type="text/css" href="js/time/jquery.timeentry.css"/>
	<script type="text/javascript" src="js/time/jquery.timeentry.js"></script>
	<script type="text/javascript" src="js/time/jquery.timeentry-fr.js"></script>
	<script type="text/javascript" src="js/time/jquery.mousewheel.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/validationEngine.jquery.css">
	<script type="text/javascript" src="js/jquery.validationEngine.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/languages/jquery.validationEngine-fr.js" charset="utf-8"></script>
	
	<script type="text/javascript">
		$(function() {
			$('#heureDemandee').timeEntry({show24Hours: true , spinnerImage: ''});
		});

		$(document).ready(function(){
			$("#mainForm").validationEngine();
				});
		
		function valider() {
			$("#mainForm").validationEngine('validate');
		}

		function retour(){
			<s:if test="fromValidation">
				submitData('showValiderDerogationAction.action');
			</s:if>
			<s:else>
				submitData('showDerogationAction.action');
			</s:else>
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('DRG_ADD')" /></s:param>
	</s:include>
	<div class="contentTable">
		<s:form theme="simple" name="mainForm" action="modifyDerogationAction" method="POST" id="mainForm">
			<s:hidden name="fromValidation"/>
			<s:hidden name="selectRow" value="%{selectRow}" />
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
	
			<table class="formTable" width="800px">
				<tr>
					<td align="center">
						<table width="100%">
							<col width="35%" />
							<col width="65%" />
							<tr>
								<td align="left">Type de dérogation <span class="starObligatoire">*</span> :</td>
								<td align="left">
									<s:select name="dtID" id="dtID" list="dtList" listKey="id" listValue="type" emptyOption="true" data-validation-engine="validate[required]"/>
								</td>
							</tr>
							<tr>
								<td align="left">Nom du demandeur <span class="starObligatoire">*</span> :</td>
								<td align="left">
									<s:select name="demandeurID" id="demandeurID" list="userList" listKey="id" listValue="prenom + ' ' + nom" emptyOption="true" data-validation-engine="validate[required]"/>
								</td>
							</tr>
							<tr>
								<td align="left">Numéro du téléphone:</td>
								<td align="left"><s:textfield name="numTele" id="numTele" maxlength="14" size="16" data-validation-engine="validate[custom[phone]]"/></td>
							</tr>
							<tr>
								<td align="left">Numéro du maintenance <span class="starObligatoire">*</span> :</td>
								<td align="left"><s:textfield name="numARS" id="numARS" data-validation-engine="validate[required]"/></td>
							</tr>
							<tr>
								<td align="left">Nom de l'application <span class="starObligatoire">*</span> :</td>
								<td align="left">
									<s:select name="appID" id="appID" list="appList" listKey="id" listValue="applicatif" emptyOption="true" data-validation-engine="validate[required]"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<hr/>
					</td>
				</tr>
				<tr>
					<td align="center">
						<table width="100%">
							<col width="35%" />
							<col width="65%" />
							<tr>
								<td align="left">Description <span class="starObligatoire">*</span> :</td>
								<td align="left"><s:textarea name="descriptionText" id="descriptionText" cols="60" rows="5" data-validation-engine="validate[required]"/></td>
							</tr>
							<tr>
								<td align="left">Equipe(s) concernée(s) par la MEP:</td>
								<td align="left"><s:textarea name="service" id="service" cols="60" rows="5"/>
								</td>
							</tr>
							<tr>
								<td align="left">Nom des réalisateurs de la MEP:</td>
								<td align="left"><s:textarea name="realisateur" id="realisateur" cols="60" rows="5"/></td>
							</tr>
							<tr>
								<td align="left">Liste des services clients touchés <span class="starObligatoire">*</span> :</td>
								<td align="left"><s:textarea name="clientT" id="clientT" cols="60" rows="5" data-validation-engine="validate[required]"/></td>
							</tr>
							<tr>
								<td align="left">Liste des services impactés <span class="starObligatoire">*</span> :</td>
								<td align="left"><s:textarea name="serviceImp" id="serviceImp" cols="60" rows="5" data-validation-engine="validate[required]"/></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<hr/>
					</td>
				</tr>
				
				<tr>
					<td align="center">
						<table width="100%">
							<col width="35%" />
							<col width="65%" />
							<tr>
								<td align="left">Réalisable dans le TP <span class="starObligatoire">*</span> :</td>
								<td align="left">
									<input type="radio" name="rTP" id="rTP"  value="1" data-validation-engine="validate[required]"  
									<s:if test="rTP == 1">checked="checked"</s:if>/>Oui
									<input type="radio" name="rTP" id="rTP"  value="0" data-validation-engine="validate[required]"
									<s:if test="rTP == 0">checked="checked"</s:if>/>Non
								</td>
							</tr>
							<tr>
								<td align="left">Présence nécessaire de l’édition <span class="starObligatoire">*</span> :</td>
								<td align="left">
									<input type="radio" name="etude" id="etude"  value="1" data-validation-engine="validate[required]"
									<s:if test="etude == 1">checked="checked"</s:if>/>Oui
									<input type="radio" name="etude" id="etude"  value="0" data-validation-engine="validate[required]"
									<s:if test="etude == 0">checked="checked"</s:if>/>Non
								</td>
							</tr>
							<tr>
								<td align="left">Retour arrière prévu <s:label value="*" cssClass="ligneImportante"/> :</td>
								<td align="left">
									<input type="radio" name="retourArriere" id="retourArriere"  value="1" data-validation-engine="validate[required]"
									<s:if test="retourArriere == 1">checked="checked"</s:if>/>Oui
									<input type="radio" name="retourArriere" id="retourArriere"  value="0" data-validation-engine="validate[required]"
									<s:if test="retourArriere == 0">checked="checked"</s:if>/>Non
								</td>
							</tr>
							<tr>
								<td align="left">Communication externe nécessaire <span class="starObligatoire">*</span> :</td>
								<td align="left">
									<input type="radio" name="externe" id="externe"  value="1" data-validation-engine="validate[required]"
									<s:if test="externe == 1">checked="checked"</s:if>/>Oui
									<input type="radio" name="externe" id="externe"  value="0" data-validation-engine="validate[required]"
									<s:if test="externe == 0">checked="checked"</s:if>/>Non
								</td>
							</tr>
							<tr>
								<td align="left">Présence du PVR de recette <span class="starObligatoire">*</span>:</td>
								<td align="left">
									<input type="radio" name="recette" id="recette"  value="1" data-validation-engine="validate[required]"
									<s:if test="recette == 1">checked="checked"</s:if>/>Oui
									<input type="radio" name="recette" id="recette"  value="0" data-validation-engine="validate[required]"
									<s:if test="recette == 0">checked="checked"</s:if>/>Non
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<hr/>
					</td>
				</tr>
				<tr>
					<td align="center">
						<table width="100%">
							<col width="35%" />
							<col width="65%" />
							<tr>
								<td align="left">Date demand&eacute;e <span class="starObligatoire">*</span>:</td>
								<td align="left" onkeydown="javascript:disableControls('dateDemandee');" onmousedown="javascript:disableControls('dateDemandee')">
									<sj:datepicker name="dateDemandee" 
													id="dateDemandee" 
													displayFormat="dd/mm/yy" 
													showOn="focus" data-validation-engine="validate[required,custom[dateFR]]">
									</sj:datepicker>
								</td>
							</tr>
							<tr>
								<td align="left">Heure demand&eacute;e <span class="starObligatoire">*</span>:</td>
								<td align="left">
									<input type="text" name="heureDemandee" id="heureDemandee" value="${heureDemandee}" data-validation-engine="validate[required,custom[timeWithoutSec]]"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<hr/>
					</td>
				</tr>
				<tr>
					<td align="center">
						<table width="100%">
							<col width="35%" />
							<col width="65%" />
							<tr>
								<td align="left">Type de changement <span class="starObligatoire">*</span> :</td>
								<td align="left">
									<s:iterator value="dtcList">
									<input type="radio" name="dtcID" id="dtc" value="<s:property value="id" />" 
									<s:if test="dtcID == id">checked="checked"</s:if> data-validation-engine="validate[required]"/><s:property value="typeChangement"/>
								</s:iterator>
								</td>
							</tr>
							<tr>
								<td align="left">Justificatif du type de changement <span class="starObligatoire">*</span> :</td>
								<td align="left"><s:textarea name="justificatif" id="justificatif"  cols="60" rows="5" data-validation-engine="validate[required]"/></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<hr/>
					</td>
				</tr>
				<tr>
					<td>
						<s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom_submit.jsp" />
					</td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>