<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/validationEngine.jquery.css">
	<script type="text/javascript" src="js/jquery.validationEngine.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/languages/jquery.validationEngine-fr.js" charset="utf-8"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#mainForm").validationEngine();
			});
	
		function valider(){
		$("#mainForm").validationEngine('validate');
		}
		//Initialisation des listes
		function initList(){
			//initialise la liste des machines
			<s:if test="machineSelected != null">
				var liMac = ['${fn:join(machineSelected,"\',\'")}'];
				var objSelect = document.getElementById("listM");
				var len = objSelect.options.length;
				for (var i = 0; i < len; i++){
					for(var j = 0; j < liMac.length; j++){
						if(objSelect.options[i].value == liMac[j]){
							objSelect.options[i].selected = true;
						} 
					}
				} 
			</s:if>
		
			//initialise la liste des environnements
			<s:if test="environnementSelected != null">
				var liEnv = ['${fn:join(environnementSelected,"\',\'")}'];
				var objSelect = document.getElementById("listE");
				var len = objSelect.options.length;
				for (var i = 0; i < len; i++){
					for(var j = 0; j < liEnv.length; j++){
						if(objSelect.options[i].value == liEnv[j]){
							objSelect.options[i].selected = true;
						} 
					}
				} 
			</s:if>
		
			//initialise la liste des interlocuteurs
			<s:if test="interlocuteurSelected != null">
				var liInterloc = ['${fn:join(interlocuteurSelected,"\',\'")}'];
				var objSelect = document.getElementById("listI");
				var len = objSelect.options.length;
				for (var i = 0; i < len; i++){
					for(var j = 0; j < liInterloc.length; j++){
						if(objSelect.options[i].value == liInterloc[j]){
							objSelect.options[i].selected = true;
						} 
					}
				} 
			</s:if>

			//initialise la liste des services
			<s:if test="serviceSelected != null">
				var liServ = ['${fn:join(serviceSelected,"\',\'")}'];
				var objSelect = document.getElementById("listS");
				var len = objSelect.options.length;
				for (var i = 0; i < len; i++){
					for(var j = 0; j < liServ.length; j++){
						if(objSelect.options[i].value == liServ[j]){
							objSelect.options[i].selected = true;
						} 
					}
				} 
			</s:if>
		}

		function retour() {
			submitData("showApplicationsListeAction.action");
		}

		function showDocumentationTree(){
			var url = "showDocumentationTree.action";
			var iWidth = 400; 
		    var iHeight = 400;
		    var iTop = Math.round((screen.availHeight-iHeight)/2);
		    var iLeft = Math.round((screen.availWidth-iWidth)/2);
		    var returnValue = window.showModalDialog(url, document.getElementById("documentation").value, "dialogHeight:" + iHeight + "px; dialogWidth:"+iWidth+"px; toolbar:no; menubar:no; scrollbars:no; resizable:no; location:no; status:no; dialogLeft:" + iLeft + "px; dialogTop:" + iTop +"px;");    
			document.getElementById("documentation").value = returnValue;
			document.getElementById("documentationDisplay").innerHTML = returnValue.replace(/;/g,"<br/>");
		}
	</script>
</head>
<body onload="javascript:initList()">
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('APP_MOD')" /></s:param>
	</s:include>
	
	<div class="contentTable" style="width: 80%">
		<s:form id="mainForm" theme="simple" action="modifyApplicationAction" method="POST">
			<s:token></s:token>
			<s:hidden name="sort" id="sort" />
			<s:hidden name="sens" id="sens" />
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPages" id="nrPages" />
			<s:hidden name="nrPerPage" id="nrPerPage" />
			<s:hidden name="selectRow" id="selectRow" />
			
			<s:hidden name="filtreNom" id="filtreNom" />
			<s:hidden name="filtreDescription" id="filtreDescription" />
			<s:hidden name="filtreDocument" id="filtreDocument" />
			
			<s:hidden name="documentation" id="documentation" />
			
			<table class="formTable" width="70%">
				<tr>
					<td></td>
					<td align="left">Nom <span class="champObligatoire">*</span> :</td>
					<td align="left" colspan="2"><s:textfield name="nom" id="nom" data-validation-engine="validate[required]"/></td>
				</tr>
				<tr>
					<td></td>
					<td align="left">Description <span class="champObligatoire">*</span> :</td>
					<td align="left" colspan="2"><s:textarea name="description" id="description" cols="50" rows="5" data-validation-engine="validate[required]"/></td>
				</tr>
				<tr>
					<td></td>
					<td align="left">Documentation :</td>
					<td align="left" colspan="2">
						<a href="#" onclick="javascript:showDocumentationTree();">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" />
						</a>
						<div id="documentationDisplay">
							<s:if test="%{documentation != null}">
								<s:property value='documentation.replaceAll(";", "<br/>")' escape="false"/>
							</s:if>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4"><hr/></td>
				</tr>
				<tr>
					<td align="left" width="25%">
						Serveur(s) :<br/>
						<s:select name="machineSelected" id="listM" size="15" multiple="true"
							list="listeMachine" listKey="id" listValue="nom"
							cssStyle="width:100%;" theme="simple">
						</s:select>
					</td>
					<td align="left" width="25%">
						Environnement(s) :<br/>
						<s:select name="environnementSelected" id="listE" size="15" multiple="true"
							list="listeEnvironnement" listKey="id" listValue="environnement"
							cssStyle="width:100%;" theme="simple">
						</s:select>
					</td>
					<td align="left" width="25%">
						Service(s) :<br/>
						<s:select name="serviceSelected" id="listS" size="15" multiple="true"
							list="listeService" listKey="id" listValue="nomService"
							cssStyle="width:100%;" theme="simple">
						</s:select>
					</td>
					<td align="left" width="25%">
						Gérant(s) :<br/>
						<s:select name="interlocuteurSelected" id="listI" size="15" multiple="true"
							list="listeInterlocuteur" listKey="id" listValue="nomService"
							cssStyle="width:100%;" theme="simple">
						</s:select>
					</td>
				</tr>
				
				<tr>
					<td colspan="4"><s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom_submit.jsp" /></td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>