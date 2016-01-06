<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript" src="<s:property value="#session.ENSEIGNE" />/js/jscolor.js"></script>
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/validationEngine.jquery.css">
	<script type="text/javascript" src="js/jquery.validationEngine.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/languages/jquery.validationEngine-fr.js" charset="utf-8"></script>
	<script type="text/javascript">
	
	$(document).ready(function(){
		$("#mainForm").validationEngine();
	});

	function valider(){
		$("#mainForm").validationEngine('validate');
	}
	
		function retour(){
			submitData("showEtatAction.action");
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MTG_ETA_M')" /></s:param>
		<s:param name="filtre" value="false"></s:param> 
	</s:include>
	
	<div class="contentTable">
		<s:form id="mainForm" theme="simple" action="modifyEtatMeteoAction" method="POST">
			<s:hidden id="selectRow" name="selectRow"/>
			<s:token></s:token>
			
			<div class="contentTable" style="width: 70%">
				<table class="dataTable" rules="all">
					<col width="20%"/>
					<col width="5%"/>
					<col width="40%"/>
					<tr>
						<td class="titreColonne">Libellé</td>
						<td class="titreColonne">Couleur</td>
						<td class="titreColonne">Définition</td>
						<td class="titreColonne">Impact</td>
					</tr>
					<tr>
					<td align="center">
							<input type="text" name="libelle" id="libelle" value="<s:property value="etat.libelle_etat"/>" data-validation-engine="validate[required]"/>
						</td>
						<td align="center">
							<input class="color" name="codeCouleur" id="codeCouleur" size="8" maxlength="6" value="<s:property value="etat.couleur"/>">
						</td>
						<td align="center">
							<input type="text" name="definition" id="definition" value="<s:property value="etat.definition"/>" data-validation-engine="validate[required, maxSize[11]]"/>
						</td>
						<td align="center">
							<select id="impact" name="impact" data-validation-engine="validate[required]">
								<option value=''></option>
								<s:if test="etat.impact==0">
									<option value='0' selected>Service OK</option>
								</s:if>
								<s:else>
									<option value='0' >Service OK</option>
								</s:else>
								<s:if test="etat.impact==1">
									<option value='1' selected>Impact service</option>
								</s:if>
								<s:else>
									<option value='1'>Impact service</option>
								</s:else>
							</select>
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