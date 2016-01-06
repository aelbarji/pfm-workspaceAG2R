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

		function openWin(serviceStatus, serviceID, dayOfWeek) {
			var url = "detailIncident.action?serviceStatus=" + serviceStatus
					+ "&serviceID=" + serviceID + "&dayOfWeek=" + dayOfWeek
					+ "&selectedDateStr=" + "${selectedDateStr}";
			var iWidth = 800;
			var iHeight = 300;
			var iTop = (window.screen.height - iHeight - 100) / 2;
			var iLeft = (window.screen.width - iWidth) / 2;
			window
					.showModalDialog(
							url,
							"test",
							"dialogHeight:"
									+ iHeight
									+ "px; dialogWidth:"
									+ iWidth
									+ "px; toolbar:no; menubar:no; scrollbars:no; resizable:no; location:no; status:no; dialogLeft:"
									+ iLeft + "px; dialogTop:" + iTop + "px;");
		}
		
		function init() {
			$(document.getElementById("pickDate").childNodes[1]).hide();
		}

        $.subscribe('pickDateChange',function(event,data) {
        	myChangeDate();
    	})

		function myChangeDate() {
			document.getElementById("selectedDateStr").value = $("#pickDate").val();
			submitData("showStatService.action");
		}
	</script>
</head>
<body onload="javascript:init()">
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('STAT_SRV')" /></s:param>
		<s:param name="titreDate" value="true"></s:param>
		<s:param name="datePicker" value="true"></s:param>
		<s:param name="datePickerUntilToday" value="false"></s:param> 
	</s:include>

	<div class="contentTable">
		<form id="mainForm" method="post">
			<s:hidden name="selectedDateStr" id="selectedDateStr"></s:hidden>
		</form>

		<table width="500px" align="center">
			<tr>
				<th colspan="10">Statistiques Service du ${month>9 ? "" : "0"}${month}/${year}</th>
			</tr>

			<tr style="height: 32px">
				<th width="4%">
					<button name="Pre" value="Pre" onclick="javascript:submit('showStatServicePre.action')">&lt;&lt;</button>
				</th>
				<th width="43%" class="statServiceTableTitle" style="background-color: #088A68;">Services communs</th>
				<th width="32px" class="statServiceTableTitle">Lun</th>
				<th width="32px" class="statServiceTableTitle">Mar</th>
				<th width="32px" class="statServiceTableTitle">Mer</th>
				<th width="32px" class="statServiceTableTitle">Jeu</th>
				<th width="32px" class="statServiceTableTitle">Ven</th>
				<th width="32px" class="statServiceTableTitle">Sam</th>
				<th width="32px" class="statServiceTableTitle">Dim</th>
				<th width="4%">
					<button name="Next" value="Next" onclick="javascript:submit('showStatServiceNext.action')">&gt;&gt;</button>
				</th>
			</tr>
			<tr style="height: 32px">
				<th></th>
				<th class="statServiceTableTitle" style="background-color: #088A68;">Num</th>
				<c:forEach items="${dates}" var="item">
					<th class="statServiceTableDate">${item}</th>
				</c:forEach>
				<th></th>
			</tr>

			<c:forEach items="${listServices}" var="serviceItem">
				<tr style="height: 32px">
					<td></td>
					<td align="center" class="statServiceTableService">${serviceItem.nomService}</td>
					<c:forEach items="${servicesMap.get(serviceItem)}" var="serviceStatus" varStatus="status">
						<td align="center">
							<c:choose>
								<c:when test="${serviceStatus == 0}">
									<img src="<s:property value="#session.ENSEIGNE" />/img/smile1.png" alt="" />
								</c:when>
								<c:when test="${serviceStatus == 1}">
									<a href="#" onclick="javascript:openWin('${serviceStatus}','${serviceItem.id}','${status.index}')">
										<img src="<s:property value="#session.ENSEIGNE" />/img/smile2.png" alt="" />
									</a>
								</c:when>
								<c:when test="${serviceStatus == 2}">
									<a href="#" onclick="javascript:openWin('${serviceStatus}','${serviceItem.id}','${status.index}')">
										<img src="<s:property value="#session.ENSEIGNE" />/img/smile3.png" alt="" />
									</a>
								</c:when>
								<c:when test="${serviceStatus == 3}">
									<a href="#" onclick="javascript:openWin('${serviceStatus}','${serviceItem.id}','${status.index}')">
										<img src="<s:property value="#session.ENSEIGNE" />/img/smile4.png" alt="" />
									</a>
								</c:when>
							</c:choose>
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
