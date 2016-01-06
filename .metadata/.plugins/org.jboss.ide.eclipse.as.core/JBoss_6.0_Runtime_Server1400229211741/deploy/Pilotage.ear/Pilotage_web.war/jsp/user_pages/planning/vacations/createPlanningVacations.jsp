<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	<script type="text/javascript" src="<s:property value="#session.ENSEIGNE" />/js/jscolor.js"></script>
	<script type="text/javascript">
		$(function() {
			$('#heureDebut').timeEntry({show24Hours: true , spinnerImage: ''});
			$('#heureFin').timeEntry({show24Hours: true , spinnerImage: ''});
		});
		$(document).ready(function(){
	    	$("#mainForm").validationEngine();
	  	});
		function valider(){
			$("#mainForm").validationEngine('validate');
		}
		function retour(){
			submitData("showPlanningVacationsAction.action");
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('PLN_VAC_C')" /></s:param>
		<s:param name="filtre" value="false"></s:param> 
	</s:include>
	
	<div class="contentTable">
		<s:form id="mainForm" theme="simple" action="createPlanningVacationsAction" method="POST">
			<s:token></s:token>
			
			<div class="contentTable" style="width: 70%">
				<table class="dataTable" rules="all">
					<col width="20%"/>
					<col width="25%"/>
					<col width="25%"/>
					<col width="30%"/>
					<col width="25%"/>
					<tr>
						<td class="titreColonne">Couleur</td>
						<td class="titreColonne">Heure de début</td>
						<td class="titreColonne">Heure de fin</td>
						<td class="titreColonne">Libelle</td>
						<td class="titreColonne">Partie Jour</td>
					</tr>
					<tr>
						<td align="center">
							<input class="color" name="codeCouleur" size="8" maxlength="6" value="<s:property value="codeCouleur"/>" data-validation-engine="validate[required]">
						</td>
						<td align="center">
							<input type="text" name="heureDebut" id="heureDebut" size="6" value="${heureDebut}" data-validation-engine="validate[required,custom[timeWithoutSec]]"/>
						</td>
						<td align="center">
							<input type="text" name="heureFin" id="heureFin" size="6" value="${heureFin}" data-validation-engine="validate[required,custom[timeWithoutSec]]"/>
						</td>
						<td align="center">
							<input type="text" name="libelle" value="<s:property value="libelle"/>" data-validation-engine="validate[required]"/>
						</td>
						<td align="center">
						<s:select name="partiJour"
							list="lpcpj" listKey="id" listValue="nom"
							cssStyle="width:150px;" theme="simple" data-validation-engine="validate[required]">
						</s:select>
					</td>
					</tr>
				</table>
				<table class="formTable" width="100%">
					<tr>
						<td><s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom_submit.jsp" /></td>
					</tr>
				</table>
			</div>
		</s:form>
	</div>
</body>
</html>