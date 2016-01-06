<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/validationEngine.jquery.css">
	<script type="text/javascript" src="js/jquery.validationEngine.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/languages/jquery.validationEngine-fr.js" charset="utf-8"></script>
	<script type="text/javascript">

		function onLoad(){
			modifyType('<s:property value="%{astreinte}" />');
		}
	
		function valider(){
			changeData('continuer', 'false');
			$("#mainform").validationEngine('validate');
		}

		function modifyType(id) {
			if(id == ''){
				document.getElementById('typeAstreinte').innerHTML = '';
				return;
			}
			<s:iterator value="astreintes">
				if(id == '<s:property value="%{id}" />'){
					document.getElementById('typeAstreinte').innerHTML = "<s:property value='%{type.type}' />";
					return;
				}
			</s:iterator>
		}
		// fonction de validation du formulaire
		$(document).ready(function(){
	    	$("#mainForm").validationEngine();
	  	});

		$.subscribe('completeDTDTopics', function(event,data) {
			var dtD = new Date($("#dateDebut").val().replace(/(\d{2})\/(\d{2})\/(.*)/,"$2/$1/$3"));
			var dtF = new Date($("#dateFin").val().replace(/(\d{2})\/(\d{2})\/(.*)/,"$2/$1/$3"));
			if (dtF < dtD) {
				$("#dateFin").addClass("outOffBoundDate");
			} else {
				$("#dateFin").removeClass("outOffBoundDate");
			}
	      });

		$.subscribe('beforeDTFTopics', function(event,data) {
			var dtD = new Date($("#dateDebut").val().replace(/(\d{2})\/(\d{2})\/(.*)/,"$2/$1/$3"));
			var dtF = new Date($("#dateFin").val().replace(/(\d{2})\/(\d{2})\/(.*)/,"$2/$1/$3"));
			if (dtF <= dtD) {
				$(data).datepicker('option','value',dtD);
			}
			$(data).datepicker('option','minDate',dtD);
			$("#dateFin").removeClass("outOffBoundDate");
	      });


		function showAstreinteTelephone(){
			$.ajax({
			  	type : 'POST',
				url : 'showAstreinteTelephone.action' ,
				data : 'idAstreinte=' + document.getElementById("astreinte").value ,
				success : function(data){
					$.each(data.telephone, function (index, elem) {
						document.getElementById("tel").value = elem.astreinteTelephone;
					});
					$("#tel").validationEngine('validate');
				}
			});

		}
		
		function retour() {
			submitData('showAstreintePlanningAction.action');
		}
	</script>
</head>
<body onload="onLoad();">
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('AST_P_MOD')" /></s:param>
	</s:include>
	
	<div class="contentTable">
		<s:form id="mainForm" theme="simple" method="POST" action='modifyAstreintePlanningAction' >
			<s:hidden id="selectRow" name="selectRow"/>
			<s:token></s:token>
			<s:hidden name="astObligatoire" id="astObligatoire"/>
			<s:hidden name="continuer" id="continuer" />
			<s:hidden name="dateNB" id="dateNB" />
			<s:hidden name="page" id="page" />
			<s:hidden name="sort" id="sort" />
			<s:hidden name="sens" id="sens" />
			<s:hidden name="nrPerPage" id="nrPerPage"/>
			<s:hidden name="nrPages" id="nrPages" />
			
			<s:hidden name="filtreType" id="filtreType" />
			<s:hidden name="filtreDomaine" id="filtreDomaine" />
			<s:hidden name="filtreAstreinte" id="filtreAstreinte" />
			<s:hidden name="filtreDebDate" id="filtreDebDate" />
			<s:hidden name="filtreFinDate" id="filtreFinDate" />
			<s:hidden name="filtreTelephone" id="filtreTelephone" />
			<s:hidden name="filtreCommentaire" id="filtreCommentaire" />
			
			<table class="formTable" border="0">
				<tr>
					<td align="left">Type :</td>
					<td align="left" id="typeAstreinte"></td>
					<td align="left">Domaine <span class="starObligatoire">*</span> :</td>
					<td align="left"><s:select list="%{aDomaines}" listKey="id" listValue="domaine" name="domaine" data-validation-engine="validate[required]"/></td>
				</tr>
				<tr>
					<td align="left">Astreinte <span class="starObligatoire">*</span> :</td>
					<td align="left"><s:select list="%{astreintes}" id="astreinte" listKey="id" listValue="nom + ' ' + prenom" name="astreinte" data-validation-engine="validate[required]" onchange="javascript:modifyType(this.value);showAstreinteTelephone();"/></td>
					<td align="left">Téléphone  <span class="starObligatoire">*</span> :</td>
					<td align="left" colspan="2"><s:textfield name="telephone" maxlength="14"  id="tel" size="15" data-validation-engine="validate[required,custom[phone]]"/></td>
				</tr>
				<tr>
					<td align="left">Du <span class="starObligatoire">*</span> :</td>
					<td align="left" >
						<sj:datepicker name="dateDeb" 
										id="dateDebut" 
										timepicker="true" 
										displayFormat="dd/mm/yy" 
										timepickerFormat="HH:mm" 
										showOn="focus" 
										value="%{dateDeb}"
										timepickerStepHour="1" 
										timepickerStepMinute="15" 
										timepickerGridHour="4" 
										timepickerGridMinute="15"
										onCompleteTopics="completeDTDTopics"
										cssClass="datepicker"
										data-validation-engine="validate[required,custom[DateTimeFR],pastFR[dateFin]]">
						</sj:datepicker>
					</td>


					<td align="left">Au <span class="starObligatoire">*</span> :</td>
					<td align="left" >
						<sj:datepicker name="dateFin"
										id="dateFin" 
										timepicker="true" 
										displayFormat="dd/mm/yy" 
										timepickerFormat="HH:mm" 
										showOn="focus" 
										value="%{dateFin}"
										timepickerStepHour="1" 
										timepickerStepMinute="15" 
										timepickerGridHour="4" 
										timepickerGridMinute="15"
										cssClass="datepicker"
										onBeforeTopics="beforeDTFTopics"
										data-validation-engine="validate[required,custom[DateTimeFR],futureFR[dateDeb]]">
						</sj:datepicker>
					</td>
				</tr>
				<tr>
					<td align="left">Informations générales :</td>
					<td align="left" colspan="5">
						<s:textfield name="infogene" maxlength="100"  id="infogene" size="118" />
					</td>
				</tr>
				<tr>
					<td align="left">Commentaire pour le pilotage :</td>
					<td align="left" colspan="5">
						<s:textarea name="commentaire" cols="90" rows="10"></s:textarea>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<div class="contentTableBottom">
							<span class="champObligatoire">* Champs Obligatoires</span>
							<div class="pageRight">
								<s:submit href="#" onclick="javascript:retour();"  value="Retour" cssClass="boutonSubmit"></s:submit>
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