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
				
	// fonction de validation du formulaire
	$(document).ready(function(){
		$("#mainForm").validationEngine();
			});
	
	function valider(){
		$("#mainForm").validationEngine('validate');
		}

		function retour(){
			submitData('showFicheIncidentsQualite.action');
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp" >
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('ICD_QLT_C')" /></s:param>
	</s:include>
	
	<div class="contentTable">
		<s:form theme="simple" name="mainForm" action="createFicheIncidentsQualite" method="POST" id="mainForm">
			<s:token></s:token>
			<s:hidden name="sort" id="sort"/>
			<s:hidden name="sens" id="sens"/>
			<s:hidden name="page" id="page"/>
			<s:hidden name="nrPages" id="nrPages"/>
			<s:hidden name="nrPerPage" id="nrPerPage"/>
			
			<s:hidden name="filtreDate" id="filtreDate"/>
			<s:hidden name="filtreDescription" id="filtreDescription"/>
			<s:hidden name="filtreCause" id="filtreCause"/>
			<s:hidden name="filtreIncidence" id="filtreIncidence"/>
			<s:hidden name="filtreEcheance" id="filtreEcheance"/>
			<s:hidden name="filtreStatut" id="filtreStatut"/>
			<s:hidden name="filtreDateRes" id="filtreDateRes"/>
		
			<table class="formTable" width="800px">
				<tr>
					<td align="center">
						<table width="100%">
							<col width="25%" />
							<col width="75%" />
							<tr>
								<td align="left" valign="top">Date de l'&eacute;v&egrave;nement <span class="starObligatoire">*</span> :</td>
								<td align="left" onkeydown="javascript:disableControls('dateEvenement');" onmousedown="javascript:disableControls('dateEvenement')">
									<sj:datepicker name="dateEvenement" 
												id="dateEvenement" 
												displayFormat="dd/mm/yy" 
												label="Choisir une date"
												showOn="focus"
												onChangeTopics="dateEvenementChange" 
												 data-validation-engine="validate[required,custom[dateFR]]" >
									</sj:datepicker>								
								</td>
							</tr>
							<tr>
								<td align="left" valign="top">Description <span class="starObligatoire">*</span> :</td>
								<td align="left"><s:textarea name="description" id="description" cols="60" rows="5"  data-validation-engine="validate[required]" /></td>
							</tr>
							<tr>
								<td align="left" valign="top">Cause/Raison :</td>
								<td align="left"><s:textarea name="cause_raison" id="cause_raison" cols="60" rows="5"/></td>
							</tr>
							<tr>
								<td align="left" valign="top">Incidence :</td>
								<td align="left"><s:textarea name="incidence" id="incidence" cols="60" rows="5"/></td>
							</tr>
							<tr>
								<td align="left" valign="top">Ech&eacute;ance :</td>
								<td align="left"><s:textarea name="echeance" id="echeance" cols="60" rows="5"/></td>
							</tr>
							<tr>
								<td align="left">Statut :</td>
								<td align="left">
									<s:select name="statut" id="statut" list="statutList" listKey="id" listValue="statut" />
								</td>
							</tr>
							<tr>
								<td align="left">Date de r&eacute;solution :</td>
								<td align="left" onkeydown="javascript:disableControls('dateResolution');" onmousedown="javascript:disableControls('dateResolution')">
									<sj:datepicker name="dateResolution" 
												id="dateResolution" 
												displayFormat="dd/mm/yy" 
												label="Choisir une date"
												showOn="focus"
												onChangeTopics="dateResolutionChange">
									</sj:datepicker>								
								</td>
							</tr>
						</table>
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