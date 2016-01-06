<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<s:if test="!listIPs.isEmpty()">
				$('#textNoIP').hide();
			</s:if>
			<s:if test="!listAccesLocal.isEmpty() || !listLoginDomaine.isEmpty()">
				$('#textNoLogin').hide();
			</s:if>
			showDomaineLogin();
		}
		// fonction de validation du formulaire
		$(document).ready(function(){
    		$("#mainForm").validationEngine();
  			});
		
		function valider(){
		
			$("#mainForm").validationEngine('validate');
			}
	
		function retour(){
			<s:if test="detail == true">
				submitData('showDetailMachineListAction.action');
			</s:if>
			<s:else>
				submitData('showMachineListAction.action');
			</s:else>
		}
		
		function addIp() {
			var i = 0;
			while(document.getElementById("ip" + i)){
				i++;
			}
			
			var t = document.getElementById("tableIp");
		    var tr = t.insertRow(t.rows.length);
		    
			var td1 = tr.insertCell(0);
			td1.setAttribute("align", "center");
			td1.innerHTML = "<input type='text' id='ip" + i + "' name='ip" + i + "' maxlength='20' size='25' data-validation-engine='validate[required,custom[ipv4]]'/>"; 
			
			var td2 = tr.insertCell(1);
			td2.setAttribute("align", "center");
			td2.innerHTML = "<textarea id='commentaire" + i + "' name='commentaire" + i + "' style='width:80%' rows='3'/>";
			
			var td3 = tr.insertCell(2);
			td3.setAttribute("align", "center");
			var supprElement = '<a href="#" onclick="javascript:deleteIp(' + i + ');">'; 
			supprElement += "<img class='icone' alt='Supprimer' title='Supprimer' src='<s:property value='#session.ENSEIGNE' />/img/supprimer-16.png'/>";
			supprElement += '</a>';
			td3.innerHTML = supprElement;

			$('#textNoIP').hide();
		}
		function deleteIp(ligne){
			var i = ligne + 1;
			while(document.getElementById("ip" + i)){
				
				document.getElementById("ip" + (i - 1)).value = document.getElementById("ip" + i).value;
				document.getElementById("commentaire" + (i - 1)).value = document.getElementById("commentaire" + i).value;
				
				i++;
			}

			document.getElementById("tableIp").deleteRow(-1);
			if(! document.getElementById("ip0"))
				$('#textNoIP').show();
		}
		
		function addLogin() {
			var i = 0;
			while(document.getElementById("login" + i)){
				i++;
			}

			var t = document.getElementById("tableLogin");
		    var tr = t.insertRow(t.rows.length);
		    
			var td1 = tr.insertCell(0);
			td1.setAttribute("align", "center");
			td1.innerHTML = "<input type='text' id='login" + i + "' name='login" + i + "' style='width:90%' data-validation-engine='validate[required]'/>";

			var td2 = tr.insertCell(1);
			td2.setAttribute("align", "center");
			td2.innerHTML = "<input type='text' id='password" + i + "' name='password" + i + "' style='width:90%' data-validation-engine='validate[required]'/>";
			
			var td3 = tr.insertCell(2);
			td3.setAttribute("align", "center");
			var comboboxDomaine = "<select id='domaine" + i + "' name='domaine" + i + "' data-validation-engine='validate[required]'>";
			comboboxDomaine += "<option value=''></option>";
			<s:iterator value="listMachineDomaine">
				comboboxDomaine += "<option value='<s:property value='id'/>'><s:property value='nomDomaine'/></option>";
			</s:iterator>
			comboboxDomaine += "</select>";
			td3.innerHTML = comboboxDomaine;
			
			var td4 = tr.insertCell(3);
			td4.setAttribute("align", "center");
			var supprElement = '<a href="#" onclick="javascript:deleteLogin(' + i + ');">'; 
			supprElement += "<img class='icone' alt='Supprimer' title='Supprimer' src='<s:property value='#session.ENSEIGNE' />/img/supprimer-16.png'/>";
			supprElement += '</a>';
			td4.innerHTML = supprElement;
			

			$('#textNoLogin').hide();
		}
		
		function deleteLogin(ligne){

			var i = ligne + 1;
			while(document.getElementById("login" + i)){
				
				document.getElementById("login" + (i - 1)).value = document.getElementById("login" + i).value;
				document.getElementById("password" + (i - 1)).value = document.getElementById("password" + i).value;
				document.getElementById("domaine" + (i - 1)).value = document.getElementById("domaine" + i).value;
				
				i++;
			}

			document.getElementById("tableLogin").deleteRow(-1);
			
			if(($('#loginDomaine0').is(":hidden") || document.getElementById("loginDomaine0") == null)
					&& ($('#loginDomaineEnBase0').is(":hidden") || document.getElementById("loginDomaineEnBase0") == null)
					&& document.getElementById("login0") == null) {
				$('#textNoLogin').show();
			}
		}
		
		var numApplication = 0;

		function addApplication() {
			if (document.getElementById("appToAdd").value != -1) {
				submitData('showModifyMachineListAction.action');
			}
			else{
				alert('Veuillez sélectionner une application');
			}
		}
		function deleteApplication(id){
			changeData('deleteAppli', id);
			submitData('showModifyMachineListAction.action');
		}

		function showDomaineLogin() {
			$.ajax({
			  	type : 'POST',
				url : 'showDomaineLogin.action' ,
				data : 'idDomaine=' + document.getElementById("domaine").value ,
				success : function(data){
					var k = 0;
					while(document.getElementById("loginDomaineEnBase" + k)){
						$('#loginDomaineEnBase'+k).hide();
						k++;
					}
					
					var j = 0;
					while(document.getElementById("loginDomaine" + j)){
						$('#loginDomaine'+j).remove();
						j++;
					}
					
					if (($('#loginDomaine0').is(":hidden") || document.getElementById("loginDomaine0") == null)
							&& ($('#loginDomaineEnBase0').is(":hidden") || document.getElementById("loginDomaineEnBase0") == null)
							&& document.getElementById("login0") == null) {
						$('#textNoLogin').show();
					}

					var i=0;
					$.each(data.lignes, function (index, elem) {
						var t = document.getElementById("tableLogin");
					    var tr = t.insertRow(1);
					    tr.setAttribute("id", "loginDomaine"+i);
					    
						var td1 = tr.insertCell(0);
						td1.setAttribute("align", "center");
						td1.innerHTML = elem.login;

						var td2 = tr.insertCell(1);
						td2.setAttribute("align", "center");
						td2.innerHTML = elem.password;
						
						var td3 = tr.insertCell(2);
						td3.setAttribute("align", "center");
						td3.innerHTML = elem.nomDomaine;
						
						var td4 = tr.insertCell(3);
						td4.setAttribute("align", "center");
						td4.innerHTML = "";

						$('#textNoLogin').hide();
						i++;
					});
				}
			});
		}
	</script>
</head>
<body onload="onLoad();">
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MAC_MOD')" /></s:param>
	</s:include>
	
	<div class="contentTable">
		<s:form theme="simple" id="mainForm" action="modifyMachineListAction" method="POST">
			<s:hidden name="selectRow" 		id="selectRow" />
			<s:hidden name="nomOrigine" 	id="nomOrigine"	/>
			<s:token></s:token>
			<s:hidden name="detail" 		id="detail"/>
		
			<s:hidden name="sort" 		id="sort" />
			<s:hidden name="sens" 		id="sens" />
			<s:hidden name="page" 		id="page" />
			<s:hidden name="nrPerPage" 	id="nrPerPage"/>
			<s:hidden name="nrPages" 	id="nrPages" />

			<s:hidden name="filtreNom" 				id="filtreNom" />
			<s:hidden name="filtreIP" 				id="filtreIP" />
			<s:hidden name="filtreType" 			id="filtreType" />
			<s:hidden name="filtreSite" 			id="filtreSite" />
			<s:hidden name="filtreOs" 				id="filtreOs" />
			<s:hidden name="filtreInterlocuteur" 	id="filtreInterlocuteur" />
			<s:hidden name="filtreDomaine" 			id="filtreDomaine" />
			<s:hidden name="filtreEnvironnement" 	id="filtreEnvironnement" />
			
			<s:hidden name="deleteAppli" id="deleteAppli" value="-1"/>
			
			<table class="formTable" width="800px">
				<col width="15%"/>
				<col width="35%"/>
				<col width="15%"/>
				<col width="35%"/>
				<tr>
					<td align="left">Nom <span class="starObligatoire">*</span> : </td>
					<td align="left"><s:textfield id="nom" name="nom" maxlength="40" size="45" data-validation-engine="validate[required]"/></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td align="left">Type <span class="starObligatoire">*</span> : </td>
					<td align="left"><s:select id="type" name="type" list="%{listMachineType}" listKey="id" listValue="type" emptyOption="true" data-validation-engine="validate[required]" /></td>
					<td align="left">Site <span class="starObligatoire">*</span> : </td>
					<td align="left"><s:select id="site" name="site" list="%{listMachineSite}" listKey="id" listValue="site" emptyOption="true" data-validation-engine="validate[required]" /></td>
				</tr>
				<tr>
					<td align="left">OS <span class="starObligatoire">*</span> : </td>
					<td align="left"><s:select id="os" name="os" list="%{listMachineOS}" listKey="id" listValue="Nom_OS" emptyOption="true" data-validation-engine="validate[required]" /></td>
					<td align="left">Service gérant : </td>
					<td align="left"><s:select id="interlocuteur" name="interlocuteur" list="%{listMachineInterlocuteur}"listKey="id" listValue="nomService" headerKey="-1" headerValue="" /></td>
				</tr>
				<tr>
					<td align="left">Domaine <span class="starObligatoire">*</span> : </td>
					<td align="left"><s:select id="domaine" name="domaine" list="%{listMachineDomaine}" listKey="id" listValue="nomDomaine" onchange="showDomaineLogin()" emptyOption="true" data-validation-engine="validate[required]"/></td>
					<td align="left">Environnement : </td>
					<td align="left"><s:select id="environnement" name="environnement" list="%{listMachineEnvironnement}" listKey="id" listValue="environement" headerKey="-1" headerValue="" /></td>
				</tr>
				<tr>
					<td align="left">Commentaire : </td>
					<td align="left" >
					<textarea id="commentaire<s:property value="#stat.index" />" name="commentaire<s:property value="#stat.index" />" style='width:97%' rows='3'><s:property  value="commentaire"/></textarea></td>
				</tr>
			</table>
			 
			<div class="titreTable"><b>Applications</b></div>
			<div class="plus">
				Ajouter :
				<s:select id="appToAdd" name="appToAdd" listKey="id" listValue="applicatif" headerKey="-1" headerValue="" list="%{listApplicatif}"/>
				<s:a href="#" onclick="javascript:addApplication()">
					<img class="icone" alt="Ajouter" title="Ajouter" src="<s:property value="#session.ENSEIGNE" />/img/valider-16.png" border="0"/>
				</s:a>
			</div>
			
			<table class="dataTable" rules="all">
				<col width="80%"/>
				<col width="20%"/>
				<thead>
					<tr>
						<th class="titreColonne">Application</th>
						<th class="titreColonne">Supprimer</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="listApplications.isEmpty()">
						<tr>
							<td class="emptyListText" colspan="2">Aucune application</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listApplications" status="stat">
							<tr>
								<td align="left">
									&nbsp;&nbsp;<s:property value="appName"/>
								</td>
								<td align="center">
									<a href="#" onclick="javascript:deleteApplication(<s:property value="appID"/>);">
										<img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
									</a>
									<input type="hidden" id="application<s:property value="#stat.index" />" name="application<s:property value="#stat.index" />" value="<s:property value="appID"/>"/>
								</td>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
			</table>
			
			<div class="contentTableLeft">
				<div class="titreTable"><b>Adresses IP</b></div>
				<div class="plus">
					<s:a href="#" onclick="javascript:addIp()">
						<img class="icone" alt="Ajouter" title="Ajouter" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" border="0"/>
					</s:a>
				</div>
			
				<table class="dataTable" rules="all" id="tableIp">
					<col width="40%"/>
					<col width="40%"/>
					<col width="20%"/>
					<thead>
						<tr>
							<th class="titreColonne">IP</th>
							<th class="titreColonne">Commentaire</th>
							<th class="titreColonne">Supprimer</th>
						</tr>
					</thead>
					<tbody>
						<tr id="textNoIP">
							<td class="emptyListText" colspan="3">Aucune adresse IP</td>
						</tr>
								<s:iterator value="listIPs" status="stat">
							<tr>
								<td align="center" id="IPNumber<s:property value="#stat.index" />">
									<input type="text" id="ip<s:property value="#stat.index" />" name="ip<s:property value="#stat.index" />" value="<s:property value="IP"/>" data-validation-engine="validate[required,custom[ipv4]]"/>
								</td>
								<td align="center" id="IPComment<s:property value="#stat.index" />">
									<textarea id="commentaire<s:property value="#stat.index" />" name="commentaire<s:property value="#stat.index"/>" style='width:80%' rows='3'><s:property  value="Commentaire"/></textarea>
								</td>
								<td align="center">
									<a href="#" onclick="javascript:deleteIp(<s:property value="#stat.index" />);">
										<img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
									</a>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			
			<div class="contentTableRight">
				<div class="titreTable"><b>Acc&egrave;s local à la machine</b></div>
				<div class="plus">
					<s:a href="#" onclick="javascript:addLogin()">
						<img class="icone" alt="Ajouter" title="Ajouter" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" border="0"/>
					</s:a>
				</div>
				
				<table class="dataTable" rules="all" id="tableLogin">
					<col width="35%"/>
					<col width="35%"/>
					<col width="20%"/>
					<col width="10%"/>
					<thead>
						<tr>
							<th class="titreColonne">Login</th>
							<th class="titreColonne">Mot de passe</th>
							<th class="titreColonne">Domaine</th>
							<th class="titreColonne">Supprimer</th>
						</tr>
					</thead>
					<tbody>
						<tr id="textNoLogin">
							<td class="emptyListText" colspan="4">Aucun accès local</td>
						</tr>
						
						<s:iterator value="listLoginDomaine" status="stat">
							<tr id="loginDomaineEnBase<s:property value="#stat.index" />">
								<td align="center"><s:property value="login"/></td>
								<s:if test="#session.USER_PROFIL.pilote || #session.USER_PROFIL.admin"  >
									<td align="center"><s:property value="password"/></td>
								</s:if>
								<s:else>
									<td align="center"></td>
								</s:else>
								<td align="center"><s:property value="domaine.nomDomaine"/></td>
								<td>&nbsp;</td>
							</tr>
						</s:iterator>
						
						<s:iterator value="listAccesLocal" status="stat">
							<tr id="listAccesLocal">
								<td align="center"><input type="text" name="login<s:property value="#stat.index" />" id="login<s:property value="#stat.index" />" value="<s:property value="login"/>" style="width:90%" data-validation-engine="validate[required]"/></td>
								<s:if test="#session.USER_PROFIL.pilote || #session.USER_PROFIL.admin">
									<td align="center"><input type="text" name="password<s:property value="#stat.index" />" id="password<s:property value="#stat.index" />" value="<s:property value="password"/>" style="width:90%" data-validation-engine="validate[required]"/></td>
								</s:if>
								<s:else>
									<td align="center"></td>
								</s:else>
								<td align="center">
									<select id='domaine<s:property value="#stat.index" />' name='domaine<s:property value="#stat.index" />' data-validation-engine="validate[required]">
										<option value='' <s:if test="domaine == ''">SELECTED</s:if>></option>
										<s:iterator value="listMachineDomaine">
											<option value='<s:property value='id'/>' <s:if test="domaine == id">SELECTED</s:if>><s:property value='nomDomaine'/></option>
										</s:iterator>
									</select>
								</td>
								<td align="center">
									<a href="#" onclick="javascript:deleteLogin(<s:property value="#stat.index" />);">
										<img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
									</a>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			<s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom_submit.jsp" />	
		</s:form>
	</div>
</body>
</html>