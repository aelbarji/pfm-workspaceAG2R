<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<s:include value="/jsp/user_pages/commun/html_head.jsp" />
		<script type="text/javascript">
			function supprimer (selectedID){
				if(confirm("Voulez-vous vraiment supprimer cette alerte ?")){
					valider(selectedID, "supprimerAlertesDisques.action");
				}		
			}
			
			function valider(selectedID, actionAddr){
				changeData("selectedID", selectedID);
				submitData(actionAddr);
			}
	
			function init(){
				$(document.getElementById("pickDate").childNodes[1]).hide();
			}
			

	        $.subscribe('pickDateChange',function(event,data) {
	        	myChangeDate();
	    	})
			
			function myChangeDate(){
				$(".dateValue").text($("#pickDate").val());
				document.getElementById("selectedDate").value = $("#pickDate").val() + " 07:30:00";
				submitData("showAlertesDisques.action");
			}

			function envoyer(){
				<s:if test="listAlertes.isEmpty()">
					alert("Il n'y a aucune alerte pour ce jour.");
				</s:if>
				<s:else>
					submitData('envoieAlertesDisques.action');
				</s:else>
			}

			function ajouter(){
				submitData('showCreateAlertesDisques.action');
			}
		</script>
	</head>
	<body onload="javascript:init()">
		<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
			<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('ADSK_LST')" /></s:param>
			<s:param name="titreDate" value="true"></s:param>
			<s:param name="datePicker" value="true"></s:param>
			<s:param name="datePickerUntilToday" value="true"></s:param>
		</s:include>
		
		<div class="contentTable" >
			<s:form id="mainForm" theme="simple">
				<s:token></s:token>
				<input type="hidden" name="selectedDate" id="selectedDate" value="<fmt:formatDate value='${selectedDate}' pattern='dd/MM/yyyy HH:mm:ss' />"></input>
				<s:hidden name="selectedID" id="selectedID" value=""/>
				
				<s:if test="#session.USER_DROITS.contains('AD_ADD')">
					<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" >
						<s:param name="envoyer" value="true"></s:param>
					</s:include>
				</s:if>
				
				<table class="dataTable" rules="all" border="1" cellpadding="2">
					<col width="8%"/>
					<col width="20%"/>
					<col width="20%"/>
					<col width="20%"/>
					<col width="8%"/>
					<col width="20%"/>
					<s:if test="#session.USER_DROITS.contains('AD_MOD') || #session.USER_DROITS.contains('AD_DEL')">
						<col width="4%"/>
					</s:if>
					<thead>
						<tr>
							<th class="titreColonne">Date</th>
							<th class="titreColonne">Système</th>
							<th class="titreColonne">FS/Table Space</th>
							<th class="titreColonne">Type</th>
							<th class="titreColonne">Alerte</th>
							<th class="titreColonne">Seuil</th>
							<s:if test="#session.USER_DROITS.contains('AD_MOD') || #session.USER_DROITS.contains('AD_DEL')">
								<th class="titreColonne">Actions</th>
							</s:if>
						</tr>
					</thead>
					<tbody>
						<s:if test="listAlertes.isEmpty()">
							<tr>
								<td class="emptyListText" colspan=<s:if test="#session.USER_DROITS.contains('AD_MOD') || #session.USER_DROITS.contains('AD_DEL')">"7"</s:if><s:else>"6"</s:else>>
									Aucun alerte disque trouvé
								</td>
							</tr>
						</s:if>
						<s:else>
							<s:iterator value="listAlertes">
								<tr>
									<td align="center">&nbsp;&nbsp;<fmt:formatDate value="${date}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
									<td align="center">&nbsp;&nbsp;<s:property value="%{systeme.nom}"/></td>
									<td align="center">&nbsp;&nbsp;<s:property value="%{fs}"/></td>
									<td align="center">&nbsp;&nbsp;<s:property value="%{type.type}"/></td>
									<td align="center">&nbsp;&nbsp;<s:property value="%{alerte}"/></td>
									<td align="center">&nbsp;&nbsp;<s:property value="%{seuil}"/></td>
									<s:if test="#session.USER_DROITS.contains('AD_MOD') || #session.USER_DROITS.contains('AD_DEL')">
										<td align="center">
											<s:if test="#session.USER_DROITS.contains('AD_MOD')">
												<s:a href="#" onclick="javascript:valider('%{id}', 'showModifyAlertesDisques.action')">
													<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
												</s:a>
											</s:if>
											<s:if test="#session.USER_DROITS.contains('AD_DEL')">
												<s:a href="#" onclick="javascript:supprimer('%{id}')">
													<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
												</s:a>
											</s:if>
										</td>
									</s:if>
								</tr>
							</s:iterator>
						</s:else>
					</tbody>
				</table>
			</s:form>
		</div>
	</body>
</html>