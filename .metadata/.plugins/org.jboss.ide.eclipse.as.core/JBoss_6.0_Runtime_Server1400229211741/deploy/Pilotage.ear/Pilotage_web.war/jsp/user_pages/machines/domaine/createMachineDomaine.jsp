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
			<s:if test="!listLogin.isEmpty()">
				$('#textNoLogin').hide();
			</s:if>
		}
	
		function retour(){
			submitData('showMachineDomaineAction.action');
		}
		
		$(document).ready(function(){
			$("#mainForm").validationEngine();
		});
		
		function valider(){
			$("#mainForm").validationEngine('validate');
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
			var supprElement = '<a href="#" onclick="javascript:deleteLogin(' + i + ');">'; 
			supprElement += "<img class='icone' alt='Supprimer' title='Supprimer' src='<s:property value='#session.ENSEIGNE' />/img/supprimer-16.png'/>";
			supprElement += '</a>';
			td3.innerHTML = supprElement;
			
			$('#textNoLogin').hide();
		}
		
		function deleteLogin(ligne){

			var i = ligne + 1;
			while(document.getElementById("login" + i)){
				
				document.getElementById("login" + (i - 1)).value = document.getElementById("login" + i).value;
				document.getElementById("password" + (i - 1)).value = document.getElementById("password" + i).value;
				
				i++;
			}

			document.getElementById("tableLogin").deleteRow(-1);
			if(! document.getElementById("login0"))
				$('#textNoLogin').show();
		}
	</script>
</head>
<body onload="onLoad();">
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp" >
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MAC_DOM_ADD')" /></s:param>
	</s:include>
	
	<div class="contentTable" style="width: 40%">
		<s:form theme="simple" action="createMachineDomaineAction" id="mainForm">
			<s:token></s:token>
			
			<table class="formTable" width="800px">
				<col width="15%"/>
				<col width="85%"/>
				<tr>
					<td align="left">Libellé <span class="champObligatoire">*</span> : </td>
					<td align="left"><s:textfield id="libelle" name="libelle" maxlength="40" size="20" data-validation-engine="validate[required]"/></td>
				</tr>
			</table>
			
			<div class="plus">
				<s:a href="#" onclick="javascript:addLogin()">
					<img class="icone" alt="Ajouter" title="Ajouter" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png"/>Ajouter login
				</s:a>
			</div>
			
			<table class="dataTable" rules="all" id="tableLogin">
				<col width="45%"/>
				<col width="45%"/>
				<col width="10%"/>
				<thead>
					<tr>
						<th class="titreColonne">Login</th>
						<th class="titreColonne">Mot de passe</th>
						<th class="titreColonne">Supprimer</th>
					</tr>
				</thead>
				<tbody>
					<tr id="textNoLogin">
						<td class="emptyListText" colspan="3">Aucun login</td>
					</tr>
										
					<s:iterator value="listLogin" status="stat">
						<tr>
							<td align="center"><input type="text" name="login<s:property value="#stat.index" />" id="login<s:property value="#stat.index" />" value="<s:property value="login"/>" style="width:90%" data-validation-engine="validate[required]"/></td>
							<td align="center"><input type="text" name="password<s:property value="#stat.index" />" id="password<s:property value="#stat.index" />" value="<s:property value="password"/>" style="width:90%" data-validation-engine="validate[required]"/></td>
							<td align="center">
								<a href="#" onclick="javascript:deleteLogin(<s:property value="#stat.index" />);">
									<img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
								</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom_submit.jsp" />
		</s:form>
	</div>
</body>
</html>