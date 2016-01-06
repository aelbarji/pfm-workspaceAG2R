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
	$(document).ready(function(){
		$("#mainForm").validationEngine();
	});
	
	function valider(){
		$("#mainForm").validationEngine('validate');
	}

		function addCaisse() {
			if (document.getElementById("caisseToAdd").value != -1) {
				submitData('showModifyEnvironnementMeteoAction.action');
			}
			else{
				alert('Veuillez sélectionner une caisse');
			}
		}

		function change(){
			var test;
			test=0;
			changeData('changeTechnique', test);
	
			submitData('showModifyEnvironnementMeteoAction.action');
		}

		function supprimer(id){
			changeData('deleteCaisse', id);
			submitData('showModifyEnvironnementMeteoAction.action');
		}

		function retour(){
			submitData('showEnvironnementMeteoAction.action');
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MTG_ENV_M')" /></s:param>
	</s:include>

	<div class="contentTable" style="width: 45%">
		<s:form id="mainForm" theme="simple" action="modifyEnvironnementMeteoAction" method="POST">
			<s:token></s:token>
			<s:hidden name="envirID" id="envirID" value="%{envirMeteo.id}"/>
			<s:hidden name="deleteCaisse" id="deleteCaisse" value="-1"/>
			<s:hidden name="changeTechnique" id="changeTechnique" value="-1" />

			<table class="formTable">
				<tr>
					<td align="left">Nom de l'environnement Météo <span class="starObligatoire">*</span> :</td>
					<td align="left"><s:textfield name="envirNOM" maxlength="50" size="35" value="%{envirMeteo.nom_envir}" data-validation-engine="validate[required]"/></td>
				</tr>
				<tr>
					<td align="left">Environnment technique?<span class="starObligatoire">*</span> :</td>
					<td align="left">
					<input type="radio"  name="technique" value="1" data-validation-engine="validate[required]"
					<s:if test="technique == 1">checked="checked"</s:if>/>Oui
					<input type="radio" name="technique" value="0" data-validation-engine="validate[required]"
					<s:if test="technique == 0">checked="checked"</s:if>/>Non
					</td>
				</tr>	
				<tr>
					<td align="left">Impact<span class="starObligatoire">*</span> :</td>
					<td align="left">
						<select id="impact" name="impact" data-validation-engine="validate[required]">
							<option value=''></option>
							<s:if test="envirMeteo.impact==0">
								<option value='0' selected>Service OK</option>
							</s:if>
							<s:else>
								<option value='0' >Service OK</option>
							</s:else>
							<s:if test="envirMeteo.impact==1">
								<option value='1' selected>Impact service</option>
							</s:if>
							<s:else>
								<option value='1'>Impact service</option>
							</s:else>
						</select>
					</td>
				</tr>
			</table>	
			
			<div class="titreTable"><b>Caisses</b></div>
			<div class="plus">
				Ajouter :
				<s:select id="caisseToAdd" name="caisseToAdd" listKey="id" listValue="nomCaisse" headerKey="-1" headerValue="" list="%{caisseList}"/>
				<s:a href="#" onclick="javascript:addCaisse()">
					<img class="icone" alt="Ajouter" title="Ajouter" src="<s:property value="#session.ENSEIGNE" />/img/valider-16.png"/>
				</s:a>
			</div>
			<table class="dataTable" rules="all">
				<col width="80%"/>
				<col width="20%"/>
				<thead>
					<tr>
						<th class="titreColonne">Caisse</th>
						<th class="titreColonne">Supprimer</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="listCaisse.isEmpty()">
						<tr>
							<td class="emptyListText" colspan="2">Aucune caisse</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listCaisse" status="stat">
							<tr>
								<td align="left">
									&nbsp;&nbsp;<s:property value="caisseName"/>
								</td>
								<td align="center">
									<a href="#" onclick="javascript:supprimer('<s:property value="caisseID"/>');">
										<img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
									</a>
									<input type="hidden" id="caisse<s:property value="#stat.index" />" name="caisse<s:property value="#stat.index" />" value="<s:property value="caisseID"/>"/>
								</td>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
			</table>	

			<s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom_submit.jsp" />

		</s:form>
	</div>
</body>
</html>