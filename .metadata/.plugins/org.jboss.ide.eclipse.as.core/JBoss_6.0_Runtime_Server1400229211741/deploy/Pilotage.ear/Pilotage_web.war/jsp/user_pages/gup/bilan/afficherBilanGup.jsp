<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/debordement_noc.css" />
	<title>Bilan</title>
</head>
<body>
	<table width="100%" align="center" class="outTable">
			<tr align="center">
				<td class="logo">
					<img alt="Logo" src='<s:property value="#session.ENSEIGNE" />/img/head_debordement.jpg' /><br/>
				</td>
			</tr>
	</table>

		<!-- ************************************** -->
		<!-- ***** start of the incidents part **** -->
		<!-- ************************************** -->

				<table width="40%" align="center" class="dataTable">
				<thead>
					<tr>
						<th colspan="3" class="BigTitle">Incidents en cours</th>
					</tr>
				</thead>
					<tr>
						<td width="6%" 	align="center"	class="colonne1">Applicatif</td>
						<td width="6%" 	align="center"	class="colonne1">Domaine</td>
						<td width="6%" 	align="center"	class="colonne1">Date et Heure incident</td>
					</tr>
					<s:if test="!listIncidentsEC.isEmpty()">
					<s:iterator value="listIncidentsEC" status="status">
						<tr>
							<td class="colonne" align="center"><s:property value="%{listAppliEC[#status.index]}"/></td>
							<td class="colonne" align="center"><s:property value="%{listDomaineEC[#status.index]}"/></td>
							<td class="colonne" align="center"><s:property value="%{listDateDebEC[#status.index]}"/></td>			
							</tr>
					</s:iterator>
					<s:else> 
						<tr><td colspan="3" class="colonne" align="center"> RAS </td></tr>
					</s:else>
					</s:if>
				</table>


				<table width="40%" align="center" class="dataTable">
					<thead>
					<tr>
						<th colspan="3" class="BigTitle">Incidents clôturés </th>
					</tr>
					</thead>
					<tr>
						<td width="6%" 	align="center"	class="colonne1">Applicatif</td>
						<td width="6%" 	align="center"	class="colonne1">Domaine</td>
						<td width="7%" 	align="center"	class="colonne1">Date et Heure incident</td>
					</tr>
					<s:if test="!listIncidentsCL.isEmpty()">
					<s:iterator value="listIncidentsCL" status="status">
						<tr>
							<td class="colonne" align="center"><s:property value="%{listAppliCL[#status.index]}"/></td>
							<td class="colonne" align="center"><s:property value="%{listDomaineCL[#status.index]}"/></td>
							<td class="colonne" align="center"><s:property value="%{'le ' + listDateDebCL[#status.index] + listDateFinCL[#status.index]}"/></td>							
							</tr>
					</s:iterator>
					</s:if>
					<s:else> 
						<tr><td colspan="3" class="colonne" align="center"> RAS </td></tr>
					</s:else>
				</table>
		
		<table width="100%" align="center" class="outTable">
			<tr align="center">
				<td class="logo">
					<img alt="Logo" src='<s:property value="#session.ENSEIGNE" />/img/foot_noc.jpg' /><br/>
				</td>
			</tr>
		</table>

		<!-- ************************************** -->
		<!-- ***** end of the incident part ******* -->
		<!-- ************************************** -->
</body>
</html>