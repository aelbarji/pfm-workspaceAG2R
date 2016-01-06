<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript" src="js/time/jquery.timeentry.js"></script>
	<script type="text/javascript" src="js/time/jquery.timeentry-fr.js"></script>
	<script type="text/javascript" src="js/time/jquery.mousewheel.min.js"></script>
	<link rel="stylesheet" type="text/css"
	href="<s:property value="#session.ENSEIGNE" />/css/validationEngine.jquery.css">
<script type="text/javascript" src="js/jquery.validationEngine.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="js/languages/jquery.validationEngine-fr.js" charset="utf-8"></script>
	<script type="text/javascript">
	$(document).ready(function(){
    	$("#mainForm").validationEngine();
  	});

	function valider(){
		$("#mainForm").validationEngine('validate');
	}
		$(function() {
			$('#heure').timeEntry({show24Hours: true , spinnerImage: ''});
		});

		function verifIncident(){
			var ref = document.getElementById("incident").value;
			if(ref == ""){
				alert("Veuillez renseigner la référence de l'incident.");
			}
			else{
				if(isNaN(ref)){
					 alert(ref+" n\'est pas un nombre.");
					 document.getElementById("incident").value="";
					 return false;
				}				
				
	            $.ajax({
	               type: "POST",
	               url: "verifExistIncidentAction.action",
	               data: "incident=" + ref,
	               success: function(response){
	            	    if(response==1){
							valider();
	                	}else{
	                		alert(ref+" ne correspond à aucun incident.");
	                    }
	            	   
	               },
		           error: function(e){
		           		alert('Error: ' + e);
		           }
				});		
			}	
		}

		function retour(){
			if(document.getElementById("last_provenance")!=null && document.getElementById("last_provenance").value!=""){
				submitData(document.getElementById("last_provenance").value);
				
			}
			else if(document.getElementById("provenance")!=null && document.getElementById("provenance").value!=""){
				submitData(document.getElementById("provenance").value);
			}
			else submitData('showAppelAstreinteAction.action');
		}

		function initDatepicker() {
			dojo.widget.byId("date");
		}

		dojo.addOnLoad(initDatepicker);
		
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('AST_APP_A')" /></s:param>
	</s:include>
	
	<div class="contentTable">
		<s:form id="mainForm" theme="simple" action="createAppelAstreinteAction" method="POST">
			<s:hidden id="provenance" name="provenance"/>
			<s:hidden id="last_provenance" name="last_provenance"/>
			<s:hidden id="redirAppelAst" name="redirAppelAst"/>
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage"/>
			<s:hidden name="nrPages" id="nrPages" />
			<s:hidden name="validAst" id="validAst" />
			<s:hidden name="selectedID" id="selectedID" />
			<s:hidden name="idInc" id="idInc"/>
			<s:hidden name="pageIncident" id="pageIncident" />
			<s:hidden name="nrPerPageIncident" id="nrPerPageIncident" />
			<s:hidden name="nrPagesIncident" id="nrPagesIncident" />
			<s:hidden name="typeSelected" id="typeSelected" />
			<s:token></s:token>
			
			<table class="formTable" border="0">
				<tr>
					<td align="left">Date <span class="starObligatoire">*</span> :</td>
					<td align="left">
						<sj:datepicker name="date" 
										id="date" 
										displayFormat="dd/mm/yy" 
										showOn="focus"
										onChangeTopics="datepickerChange"
										data-validation-engine="validate[required,custom[dateFR]]">
						</sj:datepicker>
					</td>
					<td align="left">Heure <span class="starObligatoire">*</span> :</td>
					<td align="left"><input type="text" id="heure" name="heure" value="<s:property value="heure"/>"/></td>
				</tr>
				<tr>
					<td align="left">Astreinte <span class="starObligatoire">*</span> :</td>
					<td align="left"><s:select list="%{astreintes}" id="astreinte" listKey="id" listValue="nom + ' ' + prenom" name="astreinte" emptyOption="true" data-validation-engine="validate[required]"/></td>
					<td align="left">Réf. Incident  <span class="starObligatoire">*</span> :</td>
					<td align="left" colspan="2"><s:textfield name="incident" id="incident" value="%{idInc}" data-validation-engine="validate[required]"/></td>
				</tr>
				<tr>
					<td align="left">Statut <span class="starObligatoire">*</span> :</td>
					<td align="left"><s:select list="%{statuts}" id="statut" listKey="id" listValue="statut" name="statut" emptyOption="true" data-validation-engine="validate[required]"/></td>
				</tr>
				<tr>
					<td align="left">Commentaire :</td>
					<td align="left" colspan="5">
						<s:textarea name="commentaire" cols="90" rows="10"></s:textarea>
					</td>
				</tr>
				<tr align="center">
					<td colspan="6">
						<div class="contentTableBottom">
							<span class="champObligatoire">* Champs Obligatoires</span>
							<div class="pageRight">
								<s:submit href="#" onclick="javascript:retour();" value="Retour" cssClass="boutonSubmit"></s:submit>
								<s:submit href="#" onclick="javascript:valider();" value="Valider" cssClass="boutonSubmit" ></s:submit>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>