<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	<script type="text/javascript">

		$(function() {
			$('#heureFin').timeEntry({show24Hours: true , spinnerImage: ''});
			$('#heureDebut').timeEntry({show24Hours: true , spinnerImage: ''});
		});

		//Initialisation des listes
		function initList(){
			
			//initialise la liste des applicatifs
			<s:if test="appliSelected != null">
				var liApp = ['${fn:join(appliSelected,"\',\'")}'];
				var objSelect = document.getElementById("listA");
				var len = objSelect.options.length;
				for (var i = 0; i < len; i++){
					for(var j = 0; j < liApp.length; j++){
						if(objSelect.options[i].value == liApp[j]){
							objSelect.options[i].selected = true;
						} 
					}
				} 
			</s:if>
		
			//initialise la liste des services
			<s:if test="serviceSelected != null">
				var li = ['${fn:join(serviceSelected,"\',\'")}'];
				var objSelect = document.getElementById("listS");
				var len = objSelect.options.length;
				for (var i = 0; i < len; i++){
					for(var j = 0; j < li.length; j++){
						if(objSelect.options[i].value == li[j]){
							objSelect.options[i].selected = true;
						} 
					}
				} 
			</s:if>
			
			//initialise la liste des hardware
			<s:if test="hardsoftSelected != null">
				var li = ['${fn:join(hardsoftSelected,"\',\'")}'];
				var objSelect = document.getElementById("listH");
				var len = objSelect.options.length;
				for (var i = 0; i < len; i++){
					for(var j = 0; j < li.length; j++){
						if(objSelect.options[i].value == li[j]){
							objSelect.options[i].selected = true;
						} 
					}
				} 
			</s:if>
			
			document.getElementById("listM").disabled="true";
			document.getElementById("listA").disabled="true";
			document.getElementById("listS").disabled="true";
			document.getElementById("listE").disabled="true";
			document.getElementById("couTrue").disabled="true";
			document.getElementById("couFalse").disabled="true";
			document.getElementById("listT").disabled="true";
			document.getElementById("outilSelected").disabled ="true";
			document.getElementById("oceorSelected").disabled ="true";
			document.getElementById("listH").disabled ="true";
			document.getElementById("dateDebut").disabled="true";
			document.getElementById("dateFin").disabled="true";
			document.getElementById("heureDebut").disabled="true";
			document.getElementById("heureFin").disabled="true";
			document.getElementById("observationID").disabled = "true";
			document.getElementById("actionID").disabled = "true";
			document.getElementById("incidentARS").disabled = "true";
			var element = document.getElementById("resolu");
			if(element != null) {
				document.getElementById("resFalse").disabled="true";
				document.getElementById("resTrue").disabled="true";
			}
		}

		//Mise à jour des listes lors des changements de sélection
		function onSelectChanged(obj, e, num){
			if(num == 2 || num == 3 ){
				if(!testChange(obj,e)){
					return;
				}
			}
			reloadList(num,obj);
		}

		// reloadList Ajax
		function reloadList(num,obj){
			var valueM = "";
			var valueA = "";
			var valueS = "";
			var valueE = "";
			
			if(document.getElementById("listM").value != ""){
				valueM = "&listM=";
				valueM = valueM.concat($("#listM").val());
				document.getElementById("mainForm").style.cursor="wait";
			}
			if(document.getElementById("listA").value != null && document.getElementById("listA").value != ""){
				valueA = "&listA=";
			  	var str = "";
			    $("select[name='appliSelected'] option:selected").each(function () {
			    	str += $(this).val() + ",";
			    });
			    valueA = valueA.concat(str);
			    valueA = valueA.substring(0,valueA.length-1);
			    document.getElementById("mainForm").style.cursor="wait";
			}
			if(document.getElementById("listS").value != null && document.getElementById("listS").value != ""){
				valueS = "&listS=";
				var str = "";
			    $("select[name='serviceSelected'] option:selected").each(function () {
			    	str += $(this).val() + ",";
			    });
			    valueS = valueS.concat(str);
			    valueS = valueS.substring(0,valueS.length-1);
			    document.getElementById("mainForm").style.cursor="wait";
			}
			if(document.getElementById("listE").value != ""){
				valueE = "&listE=";
				valueE = valueE.concat($("#listE").val());
				document.getElementById("mainForm").style.cursor="wait";
			}	
			if (num == 5){
				$("#listM option:selected").val("");
				$("#listA option:selected").val("");
				$("#listS option:selected").val("");
				$("#listE option:selected").val("");
				document.getElementById("mainForm").style.cursor="wait";
			}		 
			
			$.ajax({
			  	type : 'POST',
				url : 'listChange_ModifyIncAjax.action',
				data : 'selectAction='+num +valueM+valueA+valueS+valueE,
				success : function(data){
					$.each(data.liste, function (index, elem) {
						if(elem.listMachine == null){
							if (valueA == "") {
								var alist = elem.listApp;
								$("#listA").html("");
								reLoadListApp(alist, valueA);
							}
							if (valueS == "") {
								var slist = elem.listService;
								$("#listS").html("");
								reLoadListService(slist, valueS);
							}
							if (valueE == "") {
								var elist = elem.listEnviron;
								$("#listE").html("");
								reLoadListEnvironement(elist, valueE);	
							}
						}
						else if(elem.listApp == null){
							if (valueM == "") {
								var mlist = elem.listMachine;
								$("#listM").html("");
								reLoadListMachine(mlist, valueM);
							}
							if (valueS == "") {
								var slist = elem.listService;
								$("#listS").html("");
								reLoadListService(slist, valueS);
							}
							if (valueE == "") {
								var elist = elem.listEnviron;
								$("#listE").html("");
								reLoadListEnvironement(elist, valueE);	
							}
						}						
						else if(elem.listService == null){
							if (valueM == "") {
								var mlist = elem.listMachine;
								$("#listM").html("");
								reLoadListMachine(mlist, valueM);
							}
							if (valueA == "") {
								var alist = elem.listApp;
								$("#listA").html("");
								reLoadListApp(alist, valueA);
							}
							if (valueE == "") {
								var elist = elem.listEnviron;
								$("#listE").html("");
								reLoadListEnvironement(elist, valueE);	
							}
						}
						else if(elem.listEnviron == null){
							if (valueM == "") {
								var mlist = elem.listMachine;
								$("#listM").html("");
								reLoadListMachine(mlist, valueM);
							}
							if (valueA == "") {
								var alist = elem.listApp;
								$("#listA").html("");
								reLoadListApp(alist, valueA);
							}
							if (valueS == "") {
								var slist = elem.listService;
								$("#listS").html("");
								reLoadListService(slist, valueS);
							}
						}
						else if (elem.listMachine != "" && elem.listApp != "" && elem.listService != "" && elem.listEnviron != ""){
							var mlist = elem.listMachine;
							$("#listM").html("");
							$.each(mlist, function (i, item){
								var option = "<option value="+item.id+">"+item.nom+"</option>";
								$("#listM").append(option);
							});
							var alist = elem.listApp;
							$("#listA").html("");
							$.each(alist, function (i, item){
								var option = "<option value="+item.id+">"+item.applicatif+"</option>";
								$("#listA").append(option);
							});
							var slist = elem.listService;
							$("#listS").html("");
							$.each(slist, function (i, item){
								var option = "<option value="+item.id+">"+item.nomService+"</option>";
								$("#listS").append(option);
							});
							var elist = elem.listEnviron;
							$("#listE").html("");
							$.each(elist, function (i, item){
								var option = "<option value="+item.id+">"+item.environement+"</option>";
								$("#listE").append(option);
							});
						}
					});
					document.getElementById("mainForm").style.cursor="default";
				}
			});
		
		}
		// recharger les listes
		function reLoadListApp(alist, valueA){
			if (alist != null){
				$.each(alist, function (i, item){
					var option = "<option value="+item.id+">"+item.applicatif+"</option>";
					$("#listA").append(option);
				});
				if (valueA != ""){
					valueA = valueA.substring(7,valueA.length);
					if (valueA.lastIndexOf(",") != -1){
						var elem = valueA.split(',');
						var selectObject = document.getElementById("listA");
						var selectObjectLength = selectObject.options.length;
						for (j=0; j<selectObjectLength; j++){
							for (i=0; i<elem.length ; i++){
								if (selectObject.options[j].value == elem[i]){
									selectObject.options[j].selected = true;
								}
							}
						}
					}
					else {
						$("#listA").val(valueA);
					}
				}
			}

		}
		function reLoadListMachine(mlist, valueM){
			if (mlist != null){
				$.each(mlist, function (i, item){
					var option = "<option value="+item.id+">"+item.nom+"</option>";
					$("#listM").append(option);
				});
				if (valueM != ""){
					valueM = valueM.substring(7,valueM.length);
					$("#listM").val(valueM);
				}
			}
		}
		function reLoadListService(slist, valueS){
			if (slist != null){
				$.each(slist, function (i, item){
					var option = "<option value="+item.id+">"+item.nomService+"</option>";
					$("#listS").append(option);
				});
				if (valueS != ""){
					valueS = valueS.substring(7,valueS.length);
					if (valueS.lastIndexOf(",") != -1){
						var elem = valueS.split(',');
						var selectObject = document.getElementById("listS");
						var selectObjectLength = selectObject.options.length;
						for (j=0; j<selectObjectLength; j++){
							for (i=0; i<elem.length ; i++){
								if (selectObject.options[j].value == elem[i]){
									selectObject.options[j].selected = true;
								}
							}
						}
					}
					else {
						$("#listS").val(valueS);
					}
				}
			}	
		}
		function reLoadListEnvironement(elist, valueE){
			if (elist != null){
				$.each(elist, function (i, item){
					var option = "<option value="+item.id+">"+item.environement+"</option>";
					$("#listE").append(option);
				});
				if (valueE != ""){
					valueE = valueE.substring(7,valueE.length);
					$("#listE").val(valueE);
				}
			}
		}

		function testChange(obj, e) {
			if(v_check){
				if(e.currentTarget){
					if(e.relatedTarget != obj){
						if(obj != e.relatedTarget.parentNode){
							v_check = false;
							return true;
						}
					}
				}
				else{
					if(e.toElement != obj){
						if(obj != e.toElement.parentNode){
							v_check = false;
							return true;
						}
					}
				}
			}
			return false; 
		}
		
		function estFini() {
			if(document.getElementById("dateFin").value != "" && document.getElementById("heureFin").value != "" && document.getElementById("nbResPil").value < 1){
				var tr = document.createElement("tr");
				tr.id = 'resolu';
				var td = document.createElement("td"); 
				td.style.align = 'left';
				var resolu = document.createTextNode("Résolu par le pilotage : ");
				var etoile = document.createTextNode("*");
				var span = document.createElement("span");
				span.style.color = 'red';
				span.appendChild(etoile);
				td.appendChild(resolu);
				td.appendChild(span);
				tr.appendChild(td);

				var td2 = document.createElement("td"); 
				td2.style.align = 'left';
				td2.style.colspan = '3';
				var input = document.createElement("input");
				input.setAttribute('type','radio');
				input.id = 'resTrue';
				input.setAttribute('name','resoluPilSelected');
				input.setAttribute('value','1');
				<s:if test="resoluPilSelected == 1"> 
					input.setAttribute('checked','checked');
				</s:if>
				var oui = document.createTextNode("Oui");

				var input2 = document.createElement("input");
				input2.setAttribute('type','radio');
				input2.id = 'resFalse';
				input2.setAttribute('name','resoluPilSelected');
				input2.setAttribute('value','0');
				<s:if test="resoluPilSelected == 0"> 
					input2.setAttribute('checked','checked');
				</s:if>
				var non = document.createTextNode("Non");
				td2.appendChild(input);
				td2.appendChild(oui);
				td2.appendChild(input2);
				td2.appendChild(non);
				tr.appendChild(td2);
				
				var i = document.getElementById("nbResPil").value;
				document.getElementById("nbResPil").value = parseInt(i) + 1;
				var parentDiv = obs.parentNode;
				parentDiv.insertBefore(tr, obs);
				document.getElementById("resolu").style.visibility = 'visible';
				}
		}

			function create(html){
		    var fragment = document.createDocumentFragment();
		    var elem = document.createElement('div');
		    elem.innerHTML = html;
		    while (elem.firstChild) {
		        fragment.appendChild(elem.firstChild);
		    }
		    return fragment;
		}

			//fonction de validation
			function valider(){
				reinitFilterValues();
				reinitPageValues();
				document.getElementById("idInc").value = document.getElementById("selectedID").value;
				document.getElementById("validAst").value = 2; // affection de la valeur ici 1: ajouter, 2 : voir astreintes
				document.getElementById("mainForm").typeSelected.value = '<s:property value="typeSelected" escape="false"/>';
				document.getElementById("last_provenance").value = "detailGestionIncident";
				submitData('showAppelAstreinteAction');
			}

		function retour() {
			reinitFilterValues();
			reinitPageValues();
			document.getElementById("selectedID").value = null;
			document.getElementById("idInc").value = null;
			document.getElementById("mainForm").showResult.value = '1';
			document.getElementById("mainForm").typeSelected.value = '<s:property value="typeSelected" escape="false"/>';
			if(document.getElementById("provenance").value != null && document.getElementById("provenance").value != "")
				submitData(document.getElementById("provenance").value);
			else submitData("showAppelAstreinteAction");
		}

		function modify() {	
			reinitFilterValues();
			reinitPageValues();
			document.getElementById("mainForm").typeSelected.value = '<s:property value="typeSelected" escape="false"/>';
			submitData("redirectModifyIncident.action");
		}
			
		//reinitialisation des filtres comme à l'entrée de la page
		function reinitFilterValues() {
			document.getElementById("mainForm").filtreIncidents.value = '<s:property value="filtreIncidents" escape="false"/>';
		}

		//reinitialisation de page comme à l'entrée de la page
		function reinitPageValues() {
			document.getElementById("mainForm").pageIncident.value = '<s:property value="pageIncident" escape="false"/>';
			document.getElementById("mainForm").nrPerPageIncident.value = '<s:property value="nrPerPageIncident" escape="false"/>';
			document.getElementById("mainForm").nrPagesIncident.value = '<s:property value="nrPagesIncident" escape="false"/>';
		}

			$.subscribe('onFinish', function(event,data) {
			    estFini();
			}); 
			
	</script>
</head>
<body onload="javascript:initList()">
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('ICD_CNS')" /></s:param>
	</s:include>
	
	<div class="contentTable" style="width: 80%">
		<s:form id="mainForm" theme="simple" action="modifyIncident" onsubmit="return false;">
			<s:token></s:token>
			<s:hidden name="provenance" id="provenance"/>
			<s:hidden name="last_provenance" id="last_provenance"/>
			<s:hidden id="incFromAstreinte" name="incFromAstreinte"/>
			<s:hidden name="validAst" id="validAst"></s:hidden>
			<s:hidden name="idInc" id="idInc"></s:hidden>
			<s:hidden name="nbResPil" id="nbResPil" value="0"></s:hidden>
			<s:hidden name="selectAction" id="selectAction"></s:hidden>
			<s:hidden name="selectedID" id="selectedID"></s:hidden>
			<s:hidden name="pageIncident" id="pageIncident" />
			<s:hidden name="nrPerPageIncident" id="nrPerPageIncident" />
			<s:hidden name="nrPagesIncident" id="nrPagesIncident" />
			<s:hidden name="filtreIncidents" id="filtreIncidents" />
			<s:hidden name="typeSelected" id="typeSelected" />
			<s:hidden name="showResult"/>
			<table class="formTable" width="85%">
				<tr>
					<td align="left" width="20%">
						Server <span class="champObligatoire">*</span> :<br/>
						<s:select name="machineSelected" id="listM" size="6"
							list="listMachine" listKey="id" listValue="nom" 
							onchange="javascript:onSelectChanged(this,event,1)"
							cssStyle="width:100%;" theme="simple">
						</s:select>
					</td>
					<td align="left" width="20%">
						Applicatif(s) :<br/>
						<s:select name="appliSelected" id="listA" size="6" multiple="true"
							list="listApp" listKey="id" listValue="applicatif" 
							onchange="javascript:changeStatus()" onmouseout="javascript:onSelectChanged(this,event,2)"
							cssStyle="width:100%;" theme="simple">
						</s:select>
					</td>
					<td align="left" width="20%"> Hardware(s) :<br/>
						<s:select name="hardsoftSelected" id="listH" size="6" multiple="true"
							list="listHard" listKey="id" listValue="libelle" 
							cssStyle="width:100%;" theme="simple">
						</s:select>
					</td>
					<td align="left" width="20%">
						Service(s)<span class="champObligatoire">*</span> :<br/>
						<s:select name="serviceSelected" id="listS" size="6" multiple="true"
							list="listService" listKey="id" listValue="nomService"
							onchange="javascript:changeStatus()" onmouseout="javascript:onSelectChanged(this,event,3)"
							cssStyle="width:100%;" theme="simple">
						</s:select>
					</td>
					<td align="left" width="20%">
						Environment <span class="champObligatoire">*</span> :<br/>
						<s:select name="enviroSelected" id="listE" size="6"
							list="listEnviron" listKey="id" listValue="environnement"
							onchange="javascript:onSelectChanged(this,event,4)"
							cssStyle="width:100%;" theme="simple">
						</s:select>
					</td>
				</tr>
				<tr>
					<td align="left">Coupure service <span class="champObligatoire">*</span> :</td>
					<td align="left" colspan="3">
						<input type="radio" id="couTrue" name="coupureSelected" value="1" onchange="javascript:changeCoupure()"
						<s:if test="coupureSelected == 1">checked="checked"</s:if>/>Oui
						
						<input type="radio" id="couFalse" name="coupureSelected" value="0" onchange="javascript:changeCoupure()"
						<s:if test="coupureSelected == 0">checked="checked"</s:if>/>Non
					</td>
				</tr>
				<tr>
					<td align="left">Type <span class="champObligatoire">*</span> :</td>
					<td align="left" colspan="3"> 
						<s:select id="listT" name="typeInciSelected" 
							list="listTypes" listKey="id" listValue="type"
							headerKey="-1" headerValue=""
							cssStyle="width:150px;" theme="simple">
						</s:select>
					</td>
				</tr>
				<tr>
					<td align="left">Outil de détection :</td>
					<td align="left" colspan="3">
						<s:select name="outilSelected" id="outilSelected" headerValue="" headerKey="-1"
							list="listOutils" listKey="id" listValue="nomOutils"
							cssStyle="width:150px;" theme="simple">
						</s:select>
					</td>
				</tr>
				<tr>
					<td align="left">Océor</td>
					<td align="left" colspan="3">
						<s:select name="oceorSelected" id="oceorSelected"
							list="listOceor" listKey="id" listValue="nom"
							cssStyle="width:150px;" theme="simple">
						</s:select>
					</td>
				</tr>
				<tr>
					<td align="left">Date et Heure de début <span class="starObligatoire">*</span> :</td>
					<td align="left">
						<sj:datepicker name="dateDeb" 
										id="dateDebut" 
										timepicker="true" 
										displayFormat="dd/mm/yy" 
										timepickerFormat="HH:mm" 
										showOn="focus" 
										value="%{dateDebut}"
										timepickerStepHour="1" 
										timepickerStepMinute="15" 
										timepickerGridHour="4" 
										timepickerGridMinute="15"
										onCompleteTopics="completeDTDTopics"
										cssClass="datepicker"
										data-validation-engine="validate[required,custom[DateTimeFR],pastFR[dateFin]]">
						</sj:datepicker>
					</td>
					<td align="left">Date et Heure de fin:</td>
					<td align="left">
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
										data-validation-engine="validate[custom[DateTimeFR],futureFR[dateDeb]]">
						</sj:datepicker>
					</td>
				</tr>
				<tr>
					<td align="left">Application ordonnanceur:</td>
					<td align="left"><input type="text" disabled name="appli_ordonnanceur" value="${appli_ordonnanceur}" maxLength="10"/></td>
					<td align="left">Job:</td>
					<td align="left"><input type="text" disabled name="job" value="${job}" maxLength="10"/></td>
				</tr>
				<s:if test='heureFin != "" && heureFin != null'>
				<tr id="resolu">
					<td align="left">Résolu par le pilotage <span class="champObligatoire">*</span> :</td>
					<td align="left" colspan="3">
						<input type="radio" id="resTrue" name="resoluPilSelected" value="1" 
						<s:if test="resoluPilSelected == 1">checked="checked"</s:if>/>Oui
						
						<input type="radio" id="resFalse" name="resoluPilSelected" value="0"
						<s:if test="resoluPilSelected == 0">checked="checked"</s:if>/>Non
					</td>
				</tr>
				</s:if>
				<tr id="obs">
					<td align="left">Observation <span class="champObligatoire">*</span> :</td>
					<td align="left" colspan="4"><s:textarea disabled="true" name="observation" id="observationID" rows="6" cols="85"></s:textarea></td>
				</tr>
				<tr>
					<td align="left">Action <span class="champObligatoire">*</span> :</td>
					<td align="left" colspan="4"><s:textarea disabled="true" name="actionInc" id="actionID" rows="5" cols="85"></s:textarea>
					</td>
				</tr>
				<tr>
				<s:if test="nbAppelAstreinte>0">
					<td align="left">Astreinte appelée</td>
					<td>
						<s:a href="#" onclick="javascript:valider();">Voir astreintes </s:a>
					</td>
				</s:if>
				</tr>
				<tr>
					<td align="left">Incident ARS</td>
					<td align="left" colspan="4"> <s:textfield name="incidentARS" id="incidentARS" maxLength="10"></s:textfield></td>
				</tr>			
				<tr align="center">
					<td colspan="6">
						<div class="contentTableBottom">
							<span class="champObligatoire">* Champs Obligatoires</span>
							<div class="pageRight">
							<s:if test="#session.USER_DROITS.contains('ICD_MOD')">
								<s:a href="#" onclick="javascript:modify();" cssClass="boutonValider">Modifier</s:a>
							</s:if>
								<s:a href="#" onclick="javascript:retour();" cssClass="boutonRetour">Retour</s:a>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>