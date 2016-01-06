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

	$(document).ready(function(){
    	$("#mainForm").validationEngine();
  	});

	function valider(){
		$("#mainForm").validationEngine('validate');
	}
	function retour() {
		submitData("showDebordementNocAction.action");
	}

	
	</script>
</head>

<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('NOC_ADD')" /></s:param>
	</s:include>
	<div class="contentTable" style="width: 80%">
		<s:form id="mainForm" theme="simple" action="ModifyDebordementNocAction" method="POST">
		<s:token></s:token>
		<s:hidden name="selectedID" id="selectedID"/>
		<table class="formTable" width="85%">
		<tr>
			<td align="left">Date de l'appel <span class="champObligatoire">*</span> :</td>
			<td align="left">
				<sj:datepicker name="dateAppel" 
						id="dateAppel" 
						displayFormat="dd/mm/yy" 
						label="Choisir une date"
						data-validation-engine="validate[required, custom[dateFR]]"
						showOn="focus">
				</sj:datepicker>								
			</td>
			<td align="left">Heure de l'appel <span class="champObligatoire">*</span> :</td>
			<td align="left"><s:textfield type="text" name="heureAppel" id="heureAppel" data-validation-engine="validate[required, custom[timeWithoutSec]]"/></td>
		</tr>
		<tr>
			<td align="left">Opérateur <span class="starObligatoire">*</span> : </td>
			<td align="left"><s:textfield id="operateur" name="operateur" maxlength="40" size="30" data-validation-engine="validate[required]"/></td>
		</tr>
		<tr>
			<td align="left">N° incident NOC : </td>
			<td align="left"><s:textfield id="inc_noc" name="inc_noc" maxlength="19" size="30" data-validation-engine="validate[custom[onlyNumberSp]]"/></td>
		</tr>
		<tr>
			<td align="left">N° incident Opérateur : </td>
			<td align="left"><s:textfield id="inc_op" name="inc_op" maxlength="40" size="30" data-validation-engine="validate[custom[onlyLetterNumber]]"/></td>
		</tr>
		<tr>
			<td align="left">Information / Action demandée <span class="champObligatoire">*</span> :</td>
			<td align="left" colspan="4"><s:textarea name="info_act" id="info_act" rows="6" cols="85" data-validation-engine="validate[required]"></s:textarea></td>
		</tr>
		<tr>
					<td colspan="4"><s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom_submit.jsp" /></td>
				</tr>
		
		</table>
		</s:form>
	</div>
</body>