<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<s:include value="/jsp/user_pages/commun/html_head.jsp" />
		<script type="text/javascript">
	
			function valider() {
				var mainForm = document.getElementById("mainForm");
				if (mainForm.nom.value.replace(/^\s+/g,'').replace(/\s+$/g,'') == '') {
					alert('Veuillez entrer un nom');
					mainForm.nom.focus();
					return false;
				}
				else if (mainForm.libelle.value.replace(/^\s+/g,'').replace(/\s+$/g,'') == '') {
					alert('Veuillez entrer un libellé');
					mainForm.libelle.focus();
					return false;
				}
				else if (mainForm.nbDailySent.value.replace(/^\s+/g,'').replace(/\s+$/g,'') == ''){
					alert('Veuillez entrer le nombre d\'envois journalier attendu');
					mainForm.nbDailySent.focus();
					return false;
				}
				else if(! checkNombreEntier(mainForm.nbDailySent.value)){
					alert('Veuillez entrer un nombre entier pour le nombre d\'envoi journalier');
					mainForm.nbDailySent.focus();
					return false;
				}
				mainForm.submit();
				
			}
			
			function initList(){
				<s:if test="clauseSelect != null">
					var liApp = ['${fn:join(clauseSelect,"\',\'")}'];
					var objSelect = document.getElementById("listE");
					var len = objSelect.options.length;
					for (var i = 0; i < len; i++){
						for(var j = 0; j < liApp.length; j++){
							if(objSelect.options[i].value == liApp[j]){
								objSelect.options[i].selected = true;
							} 
						}
					} 
				</s:if>
			}

			function retour(){
				submitData('showTypesBilan.action');
			}
		</script>
	</head>
	<body onload="javascript:initList()">
		<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
			<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('BL_TYPE_C')" /></s:param>
		</s:include>
		
		<div class="contentTable">
			<s:form id="mainForm" action="createEnvoie" theme="simple" onsubmit="return false;">
				<s:token></s:token>
									
				<table class="formTable">
					<col width="50%"/>
					<col width="50%"/>

					<tr>
						<td  align="left">Nom <span class="champObligatoire">*</span> : </td>
						<td  align="left"><s:textfield name="nom" id="nom" maxlength="24" size="60"></s:textfield></td>
					</tr>
					<tr>
						<td  align="left">Libellé <span class="champObligatoire">*</span> : </td>
						<td  align="left"><s:textfield name="libelle" id="libelle" maxlength="127" size="60"></s:textfield></td>
					</tr>
					<tr>
						<td  align="left">Environnement des incidents du bilan : </td>
						<td  align="left"><s:select id="listE" name="clauseSelect" list="listEnvi" multiple="true" listKey="id" listValue="environnement" size="5"></s:select> </td>
					</tr>
					<tr>
						<td  align="left">Affichage des vacations : </td>
						<td  align="left"><s:checkbox name="vacation"></s:checkbox></td>
					</tr>
					<tr>
						<td  align="left">Affichage du tableau récapitulatif : </td>
						<td  align="left"><s:checkbox name="actionEPI"></s:checkbox></td>
					</tr>
					<tr>
						<td  align="left">Affichage des espaces disques : </td>
						<td  align="left"><s:checkbox name="espaceDisk"></s:checkbox></td>
					</tr>
					<tr>
						<td  align="left">Affichage des espaces disques des filiales : </td>
						<td  align="left"><s:checkbox name="disknonOCEOR"></s:checkbox></td>
					</tr>
					<tr>
						<td  align="left">Affichage des flux en erreur : </td>
						<td  align="left"><s:checkbox name="etatCFT"></s:checkbox></td>
					</tr>
					<tr>
						<td  align="left">Nombre d'envois de ce type attendus par jour <span class="champObligatoire">*</span> : </td>
						<td  align="left"><s:textfield name="nbDailySent" id="nbDailySent" maxlength="5" size="6"></s:textfield></td>
					</tr>
					<tr>
						<td colspan="2"><s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom.jsp" /></td>
					</tr>
				</table>
			</s:form>
		</div>
	</body>
</html>