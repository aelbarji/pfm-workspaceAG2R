<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	
	<script type="text/javascript">
		function submit(url) {
			var form = document.getElementById("mainForm");
			form.action = url;
			form.submit();
		}
		function init() {
			$(document.getElementById("pickDate").childNodes[1]).hide();
		}

        $.subscribe('pickDateChange',function(event,data) {
        	myChangeDate();
    	})

		function myChangeDate() {
			var d = new Date();
			document.getElementById("selectedDateStr").value = $("#pickDate").val() + " "
					+ d.getHours() + ":" + d.getMinutes() + ":"
					+ d.getSeconds();
			submitData("showStatBilan.action");
		}
	</script>
</head>
<body onload="javascript:init()">
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('STAT_BL')" /></s:param>
		<s:param name="titreDate" value="true"></s:param>
		<s:param name="datePicker" value="true"></s:param>
		<s:param name="datePickerUntilToday" value="false"></s:param>
	</s:include>
	
	<div class="contentTable">
		<form id="mainForm" method="post">
			<s:hidden name="selectedDateStr"></s:hidden>
		</form>
		
		<div align="center">
			Bilan du mois de ${month} de l'année <fmt:formatDate value="${selectedDate}" pattern="yyyy"/>
		</div>
		
		<table width="100%" class="dataTable" border="1" rules="all" cellpadding="2">
			<col width="10%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="20%"/>
			<col width="30%"/>
			<col width="20%"/>
			<thead>
				<tr>
					<th class="titreColonne">Nom de Bilan</th>
					<th class="titreColonne">Date de l'envoi</th>
					<th class="titreColonne">Heure de l'envoi</th>
					<th class="titreColonne">Nombre de bilans envoyés</th>
					<th class="titreColonne">Nombre de bilans attendus au jour d'aujourd'hui</th>
					<th class="titreColonne">Nombre de bilans attendus pour le mois</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${bilanStatList == null || fn:length(bilanStatList)== 0}">
						<tr>
							<td align="center" colspan="6">Aucune valeur disponible</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${bilanStatList}" var="item">
							<tr>
								<td align="center">${item.nomBilan}</td>
								<td align="center"><fmt:formatDate value="${item.date}" pattern="dd/MM/yyyy" /></td>
								<td align="center"><fmt:formatDate value="${item.heureEnvoi}" pattern="HH:mm:ss" /></td>
								<td align="center">${item.nbBilansActuels}</td>
								<td align="center">${item.nbBilansPresents}</td>
								<td align="center">${item.nbBilansAttendus}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</body>
</html>
