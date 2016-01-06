<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/validationEngine.jquery.css">
	<script type="text/javascript" src="js/jquery.validationEngine.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/languages/jquery.validationEngine-fr.js" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/jquery-ui/jquery-ui-1.8.18.custom.css"/>
	<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
	<script type="text/javascript">
		function onLoad(){
			<s:if test="!mapCycle.isEmpty()">
				$('#textNoCycle').hide();
			</s:if>
			<s:if test="!mapPilote.isEmpty()">
				$('#textNoPilote').hide();
			</s:if>
		}
	
		$(document).ready(function(){
	    	$("#mainForm").validationEngine();
	  	});
		function valider(){
			$("#mainForm").validationEngine('validate');
		}
		
		function changeDate(input) {
			var dateFr = input.value;
			var arrayDate = dateFr.split("/");
			var date = arrayDate[2] + "/" + arrayDate[1] + "/" + arrayDate[0];
			return date;
		}
		
		function retour(){
			submitData("showPlanningEquipesAction.action");
		}

		function ajouterCycle() {
			var i = 0;
			while(document.getElementById("cycle" + i)){
				i++;
			}

			var t = document.getElementById("tableCycle");
		    var tr = t.insertRow(t.rows.length);
		    
			var td1 = tr.insertCell(0);
			td1.setAttribute("align", "center");
			var comboboxCycle = "<select id='cycle" + i + "' name='cycle" + i + "' data-validation-engine='validate[required]'>";
			comboboxCycle += "<option value=''>Liste Cycle</option>";
			<s:iterator value="listCycle">
				comboboxCycle += "<option value='<s:property value='id'/>'><s:property value='nomCycle'/></option>";
			</s:iterator>
			comboboxCycle += "</select>";
			td1.innerHTML = comboboxCycle;

			var td2 = tr.insertCell(1);
			td2.setAttribute("align", "center");
			td2.innerHTML = "<input type='text' name='dateDebutC" + i + "' id='dateDebutC" + i + "' data-validation-engine='validate[required,custom[dateFR],pastFR[dateFinC]]'/>";
			$("#dateDebutC" + i).datepicker({dateFormat: "dd/mm/yy",});

			
			var td3 = tr.insertCell(2);
			td3.setAttribute("align", "center");
			td3.innerHTML = "<input type='text' name='dateFinC" + i + "' id='dateFinC" + i + "' data-validation-engine='validate[required,custom[dateFR],futureFR[dateDebutC]]'/>";
			$("#dateFinC" + i).datepicker({dateFormat: "dd/mm/yy",});

						
			var td4 = tr.insertCell(3);
			td4.setAttribute("align", "center");
			var supprElement = '<a href="#" onclick="javascript:deleteCycle(' + i + ');">'; 
			supprElement += "<img class='icone' alt='Supprimer' title='Supprimer' src='<s:property value='#session.ENSEIGNE' />/img/supprimer-16.png'/>";
			supprElement += '</a>';
			td4.innerHTML = supprElement;
			
			$('#textNoCycle').hide();
		}
		
		function deleteCycle(ligne){

			var i = ligne + 1;
			while(document.getElementById("cycle" + i)){
				document.getElementById("cycle" + (i - 1)).value = document.getElementById("cycle" + i).value;
				document.getElementById("dateDebutC" + (i - 1)).value = document.getElementById("dateDebutC" + i).value;
				document.getElementById("dateFinC" + (i - 1)).value = document.getElementById("dateFinC" + i).value;
				i++;
			}

			document.getElementById("tableCycle").deleteRow(-1);
			if(! document.getElementById("cycle0"))
				$('#textNoCycle').show();
		}

		function ajouterPilote() {
			var i = 0;
			while(document.getElementById("pilote" + i)){
				i++;
			}

			var t = document.getElementById("tablePilote");
		    var tr = t.insertRow(t.rows.length);
		    
			var td1 = tr.insertCell(0);
			td1.setAttribute("align", "center");
			var comboboxCycle = "<select id='pilote" + i + "' name='pilote" + i + "' data-validation-engine='validate[required]'>";
			comboboxCycle += "<option value=''>Liste Pilote</option>";
			<s:iterator value="listPilote">
				comboboxCycle += "<option value='<s:property value='id'/>'><s:property value='prenom'/>&nbsp;<s:property value='nom'/></option>";
			</s:iterator>
			comboboxCycle += "</select>";
			td1.innerHTML = comboboxCycle;

			var td2 = tr.insertCell(1);
			td2.setAttribute("align", "center");
			td2.innerHTML = "<input type='text' name='dateDebutP" + i + "' id='dateDebutP" + i + "' data-validation-engine='validate[required,custom[dateFR],pastFR[dateFinP]]'/>";
			$("#dateDebutP" + i).datepicker({dateFormat: "dd/mm/yy",});

			var td3 = tr.insertCell(2);
			td3.setAttribute("align", "center");
			td3.innerHTML = "<input type='text' name='dateFinP" + i + "' id='dateFinP" + i + "' data-validation-engine='validate[required,custom[dateFR],futureFR[dateDebutP]]'/>";
			$("#dateFinP" + i).datepicker({dateFormat: "dd/mm/yy",});
						
			var td4 = tr.insertCell(3);
			td4.setAttribute("align", "center");
			var supprElement = '<a href="#" onclick="javascript:deletePilote(' + i + ');">'; 
			supprElement += "<img class='icone' alt='Supprimer' title='Supprimer' src='<s:property value='#session.ENSEIGNE' />/img/supprimer-16.png'/>";
			supprElement += '</a>';
			td4.innerHTML = supprElement;
			
			$('#textNoPilote').hide();
		}
		
		function deletePilote(ligne){
			var i = ligne + 1;
			while(document.getElementById("pilote" + i)){
				document.getElementById("pilote" + (i - 1)).value = document.getElementById("pilote" + i).value;
				document.getElementById("piloteId" + (i - 1)).value = document.getElementById("piloteId" + i).value;
				document.getElementById("dateDebutP" + (i - 1)).value = document.getElementById("dateDebutP" + i).value;
				document.getElementById("dateFinP" + (i - 1)).value = document.getElementById("dateFinP" + i).value;
				i++;
			}

			document.getElementById("tablePilote").deleteRow(-1);
			if(! document.getElementById("pilote0")){
				$('#textNoPilote').show();
			}	
		}
		 $(document).ready(function() {
			 	var i = 0;
				while (document.getElementById("dateDebutC" + i) != null){
		        $("#dateDebutC"+i).datepicker({ dateFormat: "dd/mm/yy" });
		        i++;
				}
				i = 0;
				while (document.getElementById("dateFinC" + i) != null){
		        $("#dateFinC"+i).datepicker({ dateFormat: "dd/mm/yy" });
		        i++;
				}
				i = 0;
				while (document.getElementById("dateDebutP" + i) != null){
		        $("#dateDebutP"+i).datepicker({ dateFormat: "dd/mm/yy" });
		        i++;
				}
				i = 0;
				while (document.getElementById("dateFinP" + i) != null){
		        $("#dateFinP"+i).datepicker({ dateFormat: "dd/mm/yy" });
		        i++;
				}
		    });
	</script>
</head>
<body onload="onLoad();">
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('PLN_EQU_M')" /></s:param>
		<s:param name="filtre" value="false"></s:param> 
	</s:include>
	
	<div class="contentTable">
		<s:form id="mainForm" theme="simple" action="modifyPlanningEquipesAction" method="POST">
			<s:hidden id="selectRow" name="selectRow"/>
			<s:token></s:token>
			
			<table class="formTable">
				<tr>
					<td>Nom de l'équipe <span class="champObligatoire">*</span> : </td>
					<td><s:textfield id="nomEquipe" name="nomEquipe" maxlength="127" size="60" data-validation-engine="validate[required]"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="plus" >
							<s:a cssClass="ajouter" href="#" onclick="javascript:ajouterPilote()">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" >&nbsp;ajouter un pilote
							</s:a>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table class="dataTable" rules="all" id="tablePilote">
							<col width="30%"/>
							<col width="25%"/>
							<col width="25%"/>
							<col width="20%"/>
							<thead>
								<tr>
									<th class="titreColonne">Pilote</th>
									<th class="titreColonne">Date de début</th>
									<th class="titreColonne">Date de fin</th>
									<th class="titreColonne">Action</th>
								</tr>
							</thead>
							<tbody>
								<s:if test="mapPilote.isEmpty()">
								<tr id="textNoPilote">
									<td class="emptyListText" colspan="4">Aucun pilote</td>
								</tr>
								</s:if>		
								<s:else>		
								<s:iterator value="mapPilote" status="stat">
									<tr>
										<td align="center">
											<select id='pilote<s:property value="#stat.index" />' name='pilote<s:property value="#stat.index" />' data-validation-engine="validate[required]">
												<option value=''>Liste Pilote</option>
												<s:iterator value="listPilote">
													<option value='<s:property value='id'/>' <s:if test="pilote == id">SELECTED</s:if>><s:property value='prenom'/>&nbsp;<s:property value='nom'/></option>
												</s:iterator>
											</select>
										</td>
										<td>
											<input type='hidden' id='piloteId<s:property value="#stat.index"/>' value='<s:property value="id"/>' name='piloteId<s:property value="#stat.index"/>'/>
											<input type='text' name='dateDebutP<s:property value="#stat.index" />' id='dateDebutP<s:property value="#stat.index" />' value="<s:property value="dateDebut"/>" data-validation-engine="validate[required,custom[dateFR],pastFR[dateFinP]]"/>
										</td>
										<td>
											<input type='text' name='dateFinP<s:property value="#stat.index" />' id='dateFinP<s:property value="#stat.index" />' value="<s:property value="dateFin"/>" data-validation-engine="validate[required,custom[dateFR],futureFR[dateDebutP]]"/>
										</td>
										<td align="center">
											<a href="#" onclick="javascript:deletePilote(<s:property value="#stat.index" />);">
												<img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
											</a>
										</td>
									</tr>
								</s:iterator>
								</s:else>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="plus" >
							<s:a cssClass="ajouter" href="#" onclick="javascript:ajouterCycle()">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" >&nbsp;ajouter un cycle
							</s:a>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table class="dataTable" rules="all" id="tableCycle">
							<col width="30%"/>
							<col width="25%"/>
							<col width="25%"/>
							<col width="20%"/>
							<thead>
								<tr>
									<th class="titreColonne">Cycle de travail</th>
									<th class="titreColonne">Date de début</th>
									<th class="titreColonne">Date de fin</th>
									<th class="titreColonne">Action</th>
								</tr>
							</thead>
							<tbody>
								<s:if test="mapCycle.isEmpty()">
								<tr id="textNoCycle">
									<td class="emptyListText" colspan="4">Aucun cycle de travail</td>
								</tr>
								</s:if>
								<s:else>				
								<s:iterator value="mapCycle" status="stat">
									<tr>
										<td align="center">
											<select id='cycle<s:property value="#stat.index" />' name='cycle<s:property value="#stat.index" />' data-validation-engine="validate[required]">
												<option value=''>Liste Cycle</option>
												<s:iterator value="listCycle">
													<option value='<s:property value='id'/>' <s:if test="cycle == id">SELECTED</s:if>><s:property value='nomCycle'/></option>
												</s:iterator>
											</select>
										</td>
										<td>
											<input type='hidden' id='cycleId<s:property value="#stat.index"/>' value='<s:property value="id"/>' name='cycleId<s:property value="#stat.index"/>'/>
											<input type='text' name='dateDebutC<s:property value="#stat.index" />' id='dateDebutC<s:property value="#stat.index" />' value="<s:property value="dateDebut"/>" data-validation-engine="validate[required,custom[dateFR],pastFR[dateFinC]]"/>
										</td>
										<td>
											<input type='text' name='dateFinC<s:property value="#stat.index" />' id='dateFinC<s:property value="#stat.index" />' value="<s:property value="dateFin"/>" data-validation-engine="validate[required,custom[dateFR],futureFR[dateDebutC]]"/>
										</td>
										<td align="center">
											<a href="#" onclick="javascript:deleteCycle(<s:property value="#stat.index" />);">
												<img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
											</a>
										</td>
									</tr>
								</s:iterator>
								</s:else>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom_submit.jsp" />							
					</td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>