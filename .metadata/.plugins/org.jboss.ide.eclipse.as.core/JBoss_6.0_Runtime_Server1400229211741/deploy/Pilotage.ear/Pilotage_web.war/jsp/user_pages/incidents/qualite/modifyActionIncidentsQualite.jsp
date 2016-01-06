<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />

	<link rel="stylesheet" type="text/css" href="js/time/jquery.timeentry.css"/>
	<script type="text/javascript" src="js/time/jquery.timeentry.js"></script>
	<script type="text/javascript" src="js/time/jquery.timeentry-fr.js"></script>
	<script type="text/javascript" src="js/time/jquery.mousewheel.min.js"></script>	
	<script type="text/javascript">
				
		function valider() {
			var mainForm = document.getElementById("mainForm");

			if($("#dateAction").val() == ""){
				alert("Veuillez entrer la date de l'action.");
				return false;
			}
			if (mainForm.action.value.replace(/^\s+/g,'').replace(/\s+$/g,'').length == 0) {
				alert("Veuillez entrer une action");
				mainForm.description.focus();
				return false;
			}	
			
			document.getElementById("mainForm").submit();
		}

		function retour(){
			document.getElementById("selectRow").value = "";
			submitData('showDetailFicheIncidentsQualite.action');
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp" >
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('ICD_Q_A_M')" /></s:param>
	</s:include>
	
	<div class="contentTable">
		<s:form theme="simple" name="mainForm" action="modifyActionIncidentsQualite" method="POST" id="mainForm">
			<s:hidden name="selectRow" id="selectRow" value="%{selectRow}" />	
			<s:hidden name="sort" id="sort"/>
			<s:hidden name="sens" id="sens"/>
			<s:hidden name="page" id="page"/>
			<s:hidden name="nrPages" id="nrPages"/>
			<s:hidden name="nrPerPage" id="nrPerPage"/>
			<s:hidden name="ficheId" id="ficheId"/>
			
			<s:hidden name="sortFiche" id="sortFiche"/>
			<s:hidden name="sensFiche" id="sensFiche"/>
			<s:hidden name="pageFiche" id="pageFiche" />
			<s:hidden name="nrPerPageFiche" id="nrPerPageFiche"/>
			<s:hidden name="nrPagesFiche" id="nrPagesFiche" />
			
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
								<td align="left" valign="top">Date de l'action<span class="starObligatoire">*</span> :</td>
								<td align="left" onkeydown="javascript:disableControls('dateAction');" onmousedown="javascript:disableControls('dateAction')">
									<sj:datepicker name="dateAction" 
												id="dateAction" 
												displayFormat="dd/mm/yy" 
												showOn="focus">
									</sj:datepicker>								
								</td>
							</tr>
							<tr>
								<td align="left" valign="top">Action <span class="starObligatoire">*</span> :</td>
								<td align="left"><s:textarea name="action" id="action" cols="60" rows="5"/></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom.jsp" />
					</td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>