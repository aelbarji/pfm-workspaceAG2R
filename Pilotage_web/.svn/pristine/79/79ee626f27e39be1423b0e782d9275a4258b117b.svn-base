<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		
		function alerteDisque(){
			var form = document.getElementById("mainForm");
			form.action = "showAlertesDisques.action";
			form.submit();
		}
		
		function init(){
			 <s:if test="previousStatus == null || previousStatus != 1">$(".vac").hide();</s:if>
			<s:if test="previousStatus == null || previousStatus != 2">$(".flux").hide();</s:if>
			<s:if test="previousStatus == null || previousStatus != 3">	$(".espace").hide();</s:if>
			var debDate = new Date($.datepicker.parseDate('dd/mm/yy', $("#pickDate").val()));
			debDate.setHours(7);
			debDate.setMinutes(30);
			var currentDate = new Date();
			currentDate.setDate(currentDate.getDate() - 1);
			/*if (debDate < currentDate) {
					$("#colonneModifier").hide();
				}*/
			$("#selectedDate").val($("#pickDate").val() + " 07:30:00");
			if ($("#pickDateFin").val() != ""){
				myChangeDateFin();
			}
		}
		
		function myChangeDate() {
			var debDate = new Date($.datepicker.parseDate('dd/mm/yy', $("#pickDate").val()));
			var currentDate = new Date();
			currentDate.setDate(currentDate.getDate() - 1);
			/*if (debDate < currentDate) {
					$("#colonneModifier").hide();
					$(".vac").hide();
					$(".flux").hide();
					$(".espace").hide();
					document.getElementById("previousStatus").value = 0;
				}*/
			$("#selectedDate").val($("#pickDate").val() + " 07:30:00");
			submitData("showBilan.action");
		}
	
		function myChangeDateFin(){
			var debDate = new Date($.datepicker.parseDate('dd/mm/yy', $("#pickDate").val()));
			var finDate = new Date($.datepicker.parseDate('dd/mm/yy', $("#pickDateFin").val()));
			if (debDate >= finDate ) {
					alert("La date de fin doit être supérieur à la date de début.");
					$("#dateFin").focus();
				}
			$("#colonneModifier").hide();
			$(".vac").hide();
			$(".flux").hide();
			$(".espace").hide();
		}
		
		function reinitDateFin() {
			$("#colonneModifier").show();
			var debDate = new Date($.datepicker.parseDate('dd/mm/yy', $("#pickDate").val()));
			var currentDate = new Date();
			currentDate.setDate(currentDate.getDate() - 1);
			if (debDate < currentDate) {
					$("#colonneModifier").hide();
					$(".vac").hide();
					$(".flux").hide();
					$(".espace").hide();
					document.getElementById("previousStatus").value = 0;
				}
			$("#selectedDate").val($("#pickDate").val() + " 07:30:00");
			document.getElementById("mainForm").dateFin.value = '';
		}
		
		function show(para){
			var p = parseInt(para);
			switch(p){
			case 1:
				$(".vac").show();
				$(".flux").hide();
				$(".espace").hide();
				document.getElementById("previousStatus").value = 1;					
				break;
			case 2:
				$(".vac").hide();
				$(".flux").show();
				$(".espace").hide();
				document.getElementById("previousStatus").value = 2;
				break;
			case 3:
				$(".vac").hide();
				$(".flux").hide();
				$(".espace").show();
				document.getElementById("previousStatus").value = 3;
				break;
			
			default:
				$(".vac").hide();
				$(".flux").hide();
				$(".espace").hide();
				document.getElementById("previousStatus").value = 0;
				break;
			}
		}

		function saveCFTData() {
			var fluxArray = new Array();
			var array =  new Array();
			str = 'cft';
	        
			<s:iterator value="listFlux" status="status">
				array[<s:property value="#status.index"/>] = document.getElementById(str + '_' + <s:property value="id"/>);
			</s:iterator>
	        for (var i = 0; i < array.length; i++){
	        	fluxArray[i] = array[i].id + ":" + array[i].value;
	        }
	        document.getElementById("fluxArray").value = fluxArray;	        
	        submitData("saveFluxErreur.action");
	    }
	    
		function saveEspaceData() {
			var espaceArray = new Array();
			var seuilArray = new Array();
			var array =  new Array();
			str = 'espace';
			<s:iterator value="listDisques" status="status">
				array[<s:property value="#status.index"/>] = document.getElementById(str + '_' + <s:property value="id"/>);
			</s:iterator>
			for (var i = 0; i < array.length; i++) {
				if(!checkRate(array[i])){
					return false;
				} else{
					if(parseFloat(array[i].value) > 100){
						alert("Veuillez entrer un pourcentage inférieur à 100.");
						array[i].focus();
						return false;
					}
					espaceArray[i] = array[i].id + ":" + array[i].value.replace(/,/g,".");
				}
			}
			str = 'seuil';
			<s:iterator value="listDisques" status="status">
				array[<s:property value="#status.index"/>] = document.getElementById(str + '_' + <s:property value="id"/>);
			</s:iterator>
			for (var i = 0; i < array.length; i++) {
				if(!checkRate(array[i])){
					return false;
				} else {
					if(parseFloat(array[i].value) > 100){
						alert("Veuillez entrer un pourcentage inférieur à 100 pour le seuil");
						array[i].focus();
						return false;
					}
					seuilArray[i] = array[i].id + ":" + array[i].value.replace(/,/g,"."); 
				}
			}
			document.getElementById("seuilArray").value = seuilArray;
			document.getElementById("espaceArray").value = espaceArray;
			submitData("saveEspaceDisques.action");
	    }

		function checkRate(input) {
			if(!/^[0-9]+(\.{0,1}|,{0,1})[0-9]{0,2}$/.test(input.value) && input.value != "") {
				alert("L'espace disque n'accepte que les nombres avec 2 décimales au maximum.");
				input.focus();
				return false;
			}
			return true;
		}

		function checkChanged(vacM , vacJ, vacS){
			if('${vacMatin == null ? "" : vacMatin}' == vacM && '${vacJournee == null ? "" : vacJournee}' == vacJ && '${vacSoir == null ? "" : vacSoir}' == vacS){					
				return false;
			}
			return true;
		}
		
	    function checkVac(){
		    var vacM = document.getElementById("vacMatin").options[document.getElementById("vacMatin").options.selectedIndex].value;
		    var vacJ = document.getElementById("vacJournee").options[document.getElementById("vacJournee").options.selectedIndex].value;
		    var vacS = document.getElementById("vacSoir").options[document.getElementById("vacSoir").options.selectedIndex].value;
		    if(checkChanged(vacM,vacJ,vacS)){
				submitData('saveBilan.action');
			} else{
			    alert('Veuillez modifier une des vacations pour pouvoir sauvegarder');
			}
		}
		
		function newTab(destina){
			var form = document.getElementById("mainForm");
			form.target = "_blank";
			form.action = destina;
			form.submit();
			form.target = "_self";
		}
		
        $.subscribe('pickDateChange',function(event,data) {
        	myChangeDate();
    	})
		
        $.subscribe('dateFinChange',function(event,data) {
        	myChangeDateFin();
    	})
		
	</script>
</head>
<body onload="javascript:init()">
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('BL_LST')" /></s:param>
		<s:param name="titreDate" value="true"></s:param>
		<s:param name="alertesDisques" value="true"></s:param>
	</s:include>

	<div class="contentTable">
		<s:form id="mainForm" theme="simple">
			<s:token></s:token>
			<s:hidden name="previousStatus" id="previousStatus"></s:hidden>
			<input type="hidden" name="fluxArray" id="fluxArray"></input>
			<input type="hidden" name="espaceArray" id="espaceArray"></input>
			<input type="hidden" name="seuilArray" id="seuilArray"></input>
			<input type="hidden" name="selectedDate" id="selectedDate" value="<fmt:formatDate value='${selectedDate}' pattern='dd/MM/yyyy HH:mm:ss' />"></input>
			
			<table class="formTable" width="800px">
				<tr>
					<td colspan="3">
						<table width="100%">
							<col width="25%"/>
							<col width="25%"/>
							<col width="50%"/>
							<tr>
								<td>&nbsp;</td>
								<td>Date de début : </td>
								<td>
									<sj:datepicker name="selectedDate" 
												id="pickDate" 
												displayFormat="dd/mm/yy"
												showOn="focus"
												onCompleteTopics="pickDateChange">
									</sj:datepicker>								
								</td>
							</tr>
						<s:if test="#session.USER_DROITS.contains('BIL_MOD')">
							<tr>
								<td>&nbsp;</td>
								<td>Date de fin : </td>
								<td>
									<sj:datepicker name="dateFin" 
												id="pickDateFin" 
												displayFormat="dd/mm/yy" 
												showOn="focus"
												onCompleteTopics="dateFinChange">
									</sj:datepicker>								
									<a href="#" onclick="javascript:reinitDateFin();">
										<img class="icone" alt="Réinitialiser la date de fin" title="Réinitialiser la date de fin" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" />
									</a>
								</td>
							</tr>
						</s:if>
						</table>
					</td>
				</tr>
			<s:if test="#session.USER_DROITS.contains('BIL_MOD')">
				<tr id="colonneModifier">
					<th align="center" width="25%">
						<a href="#" onclick="javascript:show('1')"><u>Responsable de vacation</u></a>
					</th>
					<th align="center" width="25%">
						<a href="#" onclick="javascript:show('2')"><u>Flux du jour</u></a>
					</th>
					<th align="center" width="25%">
						<a href="#" onclick="javascript:show('3')"><u>Espaces disques du jour</u></a>
					</th>
					<th align="center" width="25%">
						<a href="#" onclick="javascript:alerteDisque()"><u>Alertes disques</u></a>
					</th>
					
					
				</tr>
			</s:if>
				<tr>
					<td align="center" colspan="3">
						<table width="100%">
							<col width="50%"/>
							<col width="50%"/>
							<tr>
								<td align="center" colspan="2">
									<table width="100%">
										<col width="50%"/>
										<col width="50%"/>
										<tr>
											<td align="right">Type de Bilan:</td>
											<td align="left" >
												<select name="selectedType">
													<s:iterator value="listType">	
														<s:if test='nom.equals("Bilan de vacation")'>
															<option value=<s:property value="id"/> selected><s:property value="nom" /></option>
														</s:if>						
														<s:else>
														<option value=<s:property value="id" />><s:property value="nom" /></option>
														</s:else>
													</s:iterator>
												</select>
											</td>
										</tr>
										<tr>
											<td align="right">
												<s:a href="#" onclick="javascript:newTab('showBilanDetail.action')" cssClass="boutonValider">Afficher</s:a>
											</td>
											<td align="left">
											<s:if test="#session.USER_DROITS.contains('BIL_MOD')">
												<s:a href="#" onclick="javascript:submitData('sendBilan.action')" cssClass="boutonValider">Envoyer</s:a>
											</s:if>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<s:if test="#session.USER_DROITS.contains('BIL_MOD')">

							<tr class="vac">
								<td align="left">Vacation matin:</td>
								<td align="right">
									<s:select id="vacMatin" name="vacMatin" emptyOption="true" list="listUsers" listKey="id" listValue="nom + ' ' + prenom" theme="simple"></s:select>
								</td>
							</tr>
							<tr class="vac">
								<td align="left">Vacation journée:</td>
								<td align="right">
									<s:select id="vacJournee" name="vacJournee" emptyOption="true" list="listUsers" listKey="id" listValue="nom + ' ' + prenom" theme="simple"></s:select>		
								</td>
							</tr>
							<tr class="vac">
								<td align="left">Vacation soir:</td>
								<td align="right">
									<s:select id="vacSoir" name="vacSoir" emptyOption="true" list="listUsers" listKey="id" listValue="nom + ' ' + prenom" theme="simple"></s:select>
								</td>
							</tr>
							<tr class="vac">
								<td colspan="2" align="center">
									<s:a href="#" onclick="javascript:checkVac()" cssClass="boutonValider">Sauvegarder</s:a>
								</td>
							</tr>
							<s:if test="listFlux != null && !listFlux.isEmpty()">
								<s:iterator value="listFlux">
									<tr class="flux">
										<td align="left"><s:property value="libelle"/> : </td>
										<td align="right">
											<textarea rows="3" cols="35" name="cft_<s:property value="id"/>" style="resize: none;" id="cft_<s:property value="id"/>"><s:if test="cftErreurMap.get(id) != null"><s:property value="cftErreurMap.get(id)"/></s:if></textarea>
										</td>
									</tr>
								</s:iterator>
								<tr class="flux">
									<td colspan="2" align="center">
										<s:a href="#" onclick="javascript:saveCFTData()" cssClass="boutonValider">Sauvegarder</s:a>
									</td>
								</tr>
							</s:if>
							<s:else>
								<tr class="flux">
									<td colspan="2" align="center"><b>Aucun Flux CFT trouvé</b></td>
								</tr>
							</s:else>
												
							<s:if test="listDisques == null || listDisques.isEmpty()">
								<tr class="espace">
									<td colspan="2" align="center"><b>Aucun disque trouvé</b></td>
								</tr>
							</s:if>
							<s:else>
								<s:iterator value="listDisques">
									<tr class="espace">
										<td align="left"><s:property value="libelle"/> :</td>
										<td align="right">
											<input type="text" name="espace_<s:property value="id"/>" id="espace_<s:property value="id"/>" size="6" maxlength="6" value="<s:if test="espaceMap.get(id) != null"><s:text name="format.percent"><s:param value="espaceMap.get(id)"/></s:text></s:if>"/>%
																&nbsp;&nbsp;Seuil&nbsp;
											<input type="text" name="seuil_<s:property value="id"/>" id="seuil_<s:property value="id"/>" size="6" maxlength="6" value="<s:if test="seuilMap.get(id) != null"><s:text name="format.percent"><s:param value="seuilMap.get(id)"/></s:text></s:if>"/>%
										</td>
									</tr>
								</s:iterator>
								<tr class="espace">
									<td colspan="2" align="center">
										<s:a href="#" onclick="javascript:saveEspaceData()" cssClass="boutonValider">Sauvegarder</s:a>
									</td>
								</tr>
							</s:else>
							</s:if>					
						</table>
					</td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>
