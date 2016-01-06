<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/validationEngine.jquery.css">
	<script type="text/javascript" src="js/jquery.validationEngine.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/languages/jquery.validationEngine-fr.js" charset="utf-8"></script>
	<script type="text/javascript">
		function onLoad(){
			<s:if test="!listSemaine.isEmpty()">
				$('#textNoSemaine').hide();
			</s:if>
		}
		$(document).ready(function(){
    		$("#mainForm").validationEngine();
  		});
		function valider(){
			if(!validerSemaine()){
				return false;
				}
			$("#mainForm").validationEngine('validate');
		}
		
		function validerSemaine(){
			if(document.getElementById("semaine1") == null){
				alert("Vous devez ajouter au moins une semaine au cycle");
				return false;
			}
			return true;
		}

		function retour(){
			submitData("showPlanningCyclesAction.action");
		}

		function ajouter() {
			var i = 1;
			while(document.getElementById("semaine" + i)){
				i++;
			}

			var t = document.getElementById("tableSemaine");
		    var tr = t.insertRow(t.rows.length);
		    
			var td1 = tr.insertCell(0);
			td1.setAttribute("align", "center");
			td1.innerHTML = "<input type='hidden' id='semaine" + i + "' name='semaine" + i + "' value='" + i + "'/>Semaine " + i;

			var td2 = tr.insertCell(1);
			td2.setAttribute("align", "center");
			var comboboxLundi = "<select id='lundi" + i + "' name='lundi" + i + "' data-validation-engine='validate[required]'>";
			comboboxLundi += "<option value=''>Vacation</option>";
			<s:iterator value="listVacation">
				comboboxLundi += "<option value='<s:property value='id'/>'><s:property value='libelle'/></option>";
			</s:iterator>
			comboboxLundi += "</select>";
			td2.innerHTML = comboboxLundi;
			
			var td3 = tr.insertCell(2);
			td3.setAttribute("align", "center");
			var comboboxMardi = "<select id='mardi" + i + "' name='mardi" + i + "' data-validation-engine='validate[required]'>";
			comboboxMardi += "<option value=''>Vacation</option>";
			<s:iterator value="listVacation">
				comboboxMardi += "<option value='<s:property value='id'/>'><s:property value='libelle'/></option>";
			</s:iterator>
			comboboxMardi += "</select>";
			td3.innerHTML = comboboxMardi;
			
			var td4 = tr.insertCell(3);
			td4.setAttribute("align", "center");
			var comboboxMercredi = "<select id='mercredi" + i + "' name='mercredi" + i + "' data-validation-engine='validate[required]'>";
			comboboxMercredi += "<option value=''>Vacation</option>";
			<s:iterator value="listVacation">
				comboboxMercredi += "<option value='<s:property value='id'/>'><s:property value='libelle'/></option>";
			</s:iterator>
			comboboxMercredi += "</select>";
			td4.innerHTML = comboboxMercredi;
			
			var td5 = tr.insertCell(4);
			td5.setAttribute("align", "center");
			var comboboxJeudi = "<select id='jeudi" + i + "' name='jeudi" + i + "' data-validation-engine='validate[required]'>";
			comboboxJeudi += "<option value=''>Vacation</option>";
			<s:iterator value="listVacation">
				comboboxJeudi += "<option value='<s:property value='id'/>'><s:property value='libelle'/></option>";
			</s:iterator>
			comboboxJeudi += "</select>";
			td5.innerHTML = comboboxJeudi;
			
			var td6 = tr.insertCell(5);
			td6.setAttribute("align", "center");
			var comboboxVendredi = "<select id='vendredi" + i + "' name='vendredi" + i + "' data-validation-engine='validate[required]'>";
			comboboxVendredi += "<option value=''>Vacation</option>";
			<s:iterator value="listVacation">
				comboboxVendredi += "<option value='<s:property value='id'/>'><s:property value='libelle'/></option>";
			</s:iterator>
			comboboxVendredi += "</select>";
			td6.innerHTML = comboboxVendredi;
						
			var td7 = tr.insertCell(6);
			td7.setAttribute("align", "center");
			var comboboxSamedi = "<select id='samedi" + i + "' name='samedi" + i + "' data-validation-engine='validate[required]'>";
			comboboxSamedi += "<option value=''>Vacation</option>";
			<s:iterator value="listVacation">
				comboboxSamedi += "<option value='<s:property value='id'/>'><s:property value='libelle'/></option>";
			</s:iterator>
			comboboxSamedi += "</select>";
			td7.innerHTML = comboboxSamedi;

			var td8 = tr.insertCell(7);
			td8.setAttribute("align", "center");
			var comboboxDimanche = "<select id='dimanche" + i + "' name='dimanche" + i + "' data-validation-engine='validate[required]'>";
			comboboxDimanche += "<option value=''>Vacation</option>";
			<s:iterator value="listVacation">
				comboboxDimanche += "<option value='<s:property value='id'/>'><s:property value='libelle'/></option>";
			</s:iterator>
			comboboxDimanche += "</select>";
			td8.innerHTML = comboboxDimanche;
			
			var td9 = tr.insertCell(8);
			td9.setAttribute("align", "center");
			var supprElement = '<a href="#" onclick="javascript:deleteSemaine(' + i + ');">'; 
			supprElement += "<img class='icone' alt='Supprimer' title='Supprimer' src='<s:property value='#session.ENSEIGNE' />/img/supprimer-16.png'/>";
			supprElement += '</a>';
			td9.innerHTML = supprElement;
			
			$('#textNoSemaine').hide();
		}
		
		function deleteSemaine(ligne){

			var i = ligne + 1;
			while(document.getElementById("semaine" + i)){
				document.getElementById("semaine" + (i - 1)).value = document.getElementById("semaine" + i).value;
				document.getElementById("lundi" + (i - 1)).value = document.getElementById("lundi" + i).value;
				document.getElementById("mardi" + (i - 1)).value = document.getElementById("mardi" + i).value;
				document.getElementById("mercredi" + (i - 1)).value = document.getElementById("mercredi" + i).value;
				document.getElementById("jeudi" + (i - 1)).value = document.getElementById("jeudi" + i).value;
				document.getElementById("vendredi" + (i - 1)).value = document.getElementById("vendredi" + i).value;
				document.getElementById("samedi" + (i - 1)).value = document.getElementById("samedi" + i).value;
				document.getElementById("dimanche" + (i - 1)).value = document.getElementById("dimanche" + i).value;
				
				i++;
			}

			document.getElementById("tableSemaine").deleteRow(-1);
			if(! document.getElementById("semaine1"))
				$('#textNoSemaine').show();
		}
		
	</script>
</head>
<body onload="onLoad();">
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('PLN_CYC_M')" /></s:param>
		<s:param name="filtre" value="false"></s:param> 
	</s:include>
	
	<div class="contentTable">
		<s:form id="mainForm" theme="simple" action="modifyPlanningCyclesAction" method="POST">
			<s:hidden id="selectRow" name="selectRow"/>
			<s:token></s:token>
			
			Nom du cycle : <s:textfield name="nomCycle" id="nomCycle" data-validation-engine="validate[required]"/><br/>
			
			<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
			<table class="dataTable" rules="all" id="tableSemaine">
				<col width="12%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
				<thead>
					<tr>
						<th class="titreColonne">Semaine</th>
						<th class="titreColonne">Lundi</th>
						<th class="titreColonne">Mardi</th>
						<th class="titreColonne">Mercredi</th>
						<th class="titreColonne">Jeudi</th>
						<th class="titreColonne">Vendredi</th>
						<th class="titreColonne">Samedi</th>
						<th class="titreColonne">Dimanche</th>
						<th class="titreColonne">Action</th>
					</tr>
				</thead>
				<tbody>
					<tr id="textNoSemaine">
						<td class="emptyListText" colspan="9">Aucune semaine du cycle</td>
					</tr>
										
					<s:iterator value="mapSemaine" status="stat">
						<tr>
							<td align="center">
								<input type="hidden" id="semaine<s:property value="#stat.index+1" />" name="semaine<s:property value="#stat.index+1" />" value="<s:property value="#stat.index+1" />"/>
								Semaine <s:property value="#stat.index+1" />
							</td>
							<td align="center">
								<select id='lundi<s:property value="#stat.index+1" />' name='lundi<s:property value="#stat.index+1" />' data-validation-engine="validate[required]">
									<option value=''>Vacation<s:if test="lundi == '-1'">SELECTED</s:if></option>
									<s:iterator value="listVacation">
										<option value='<s:property value='id'/>' <s:if test="lundi == id">SELECTED</s:if>><s:property value='libelle'/></option>
									</s:iterator>
								</select>
							</td>
							<td align="center">
								<select id='mardi<s:property value="#stat.index+1" />' name='mardi<s:property value="#stat.index+1" />' data-validation-engine="validate[required]">
									<option value=''>Vacation</option>
									<s:iterator value="listVacation">
										<option value='<s:property value='id'/>' <s:if test="mardi == id">SELECTED</s:if>><s:property value='libelle'/></option>
									</s:iterator>
								</select>
							</td>
							<td align="center">
								<select id='mercredi<s:property value="#stat.index+1" />' name='mercredi<s:property value="#stat.index+1" />' data-validation-engine="validate[required]">
									<option value=''>Vacation</option>
									<s:iterator value="listVacation">
										<option value='<s:property value='id'/>' <s:if test="mercredi == id">SELECTED</s:if>><s:property value='libelle'/></option>
									</s:iterator>
								</select>
							</td>
							<td align="center">
								<select id='jeudi<s:property value="#stat.index+1" />' name='jeudi<s:property value="#stat.index+1" />' data-validation-engine="validate[required]">
									<option value=''>Vacation</option>
									<s:iterator value="listVacation">
										<option value='<s:property value='id'/>' <s:if test="jeudi == id">SELECTED</s:if>><s:property value='libelle'/></option>
									</s:iterator>
								</select>
							</td>
							<td align="center">
								<select id='vendredi<s:property value="#stat.index+1" />' name='vendredi<s:property value="#stat.index+1" />' data-validation-engine="validate[required]">
									<option value=''>Vacation</option>
									<s:iterator value="listVacation">
										<option value='<s:property value='id'/>' <s:if test="vendredi == id">SELECTED</s:if>><s:property value='libelle'/></option>
									</s:iterator>
								</select>
							</td>
							<td align="center">
								<select id='samedi<s:property value="#stat.index+1" />' name='samedi<s:property value="#stat.index+1" />' data-validation-engine="validate[required]">
									<option value=''>Vacation</option>
									<s:iterator value="listVacation">
										<option value='<s:property value='id'/>' <s:if test="samedi == id">SELECTED</s:if>><s:property value='libelle'/></option>
									</s:iterator>
								</select>
							</td>
							<td align="center">
								<select id='dimanche<s:property value="#stat.index+1" />' name='dimanche<s:property value="#stat.index+1" />' data-validation-engine="validate[required]">
									<option value=''>Vacation</option>
									<s:iterator value="listVacation">
										<option value='<s:property value='id'/>' <s:if test="dimanche == id">SELECTED</s:if>><s:property value='libelle'/></option>
									</s:iterator>
								</select>
							</td>
							<td align="center">
								<a href="#" onclick="javascript:deleteSemaine(<s:property value="#stat.index+1" />);">
									<img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
								</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<table class="formTable" width="100%">
				<tr>
					<td><s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom_submit.jsp" /></td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>