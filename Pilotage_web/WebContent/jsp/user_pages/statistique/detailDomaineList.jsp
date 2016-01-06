<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	</head>
	<body>
		<table class="contentTable">
			<tr>
				<td>
				<div class="titreDomaine">
				<span class="titre">
					<s:if test="selectMois != null && selectMois != ''">
						Mois s&eacute;lectionn&eacute; : <s:property value="selectMois"/>
					</s:if>
					<s:elseif test="selectSemaine != null && selectSemaine != ''">
						Semaine : <s:property value="selectSemaine"/> - du <s:date name="monday" format="dd/MM/yyyy"/> au <s:date name="sunday" format="dd/MM/yyyy"/>
					</s:elseif>
				</span><br />
				<span class="titre">Domaine s&eacute;lectionn&eacute; : </span><s:property value="environnement"/>
				</div>
				<div class="domaineDetail">
					Le nombre d'incident : <s:property value="nIncident"/><br/>
					Le nombre d'incident critique : <s:property value="nIncidentCritique"/><br/>
					Le nombre d'incident avec coupure de service: <s:property value="nIncidentCoupureService"/><br/>
					Le nombre d'appel Astreintes : <s:property value="nAppelAstreintes"/><br/>
				</div>
				</td>
			</tr>
		</table>
	</body>
</html>
