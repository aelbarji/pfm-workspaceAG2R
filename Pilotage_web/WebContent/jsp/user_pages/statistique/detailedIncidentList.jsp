<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	</head>
	<body>
		<div style="width: 100%" align="center">
			<table class="dataTable" rules="all" width="100%">
				<col width="3%"/>
				<col width="9%" />
				<col width="11%" />
				<col width="11%" />
				<col width="13%" />
				<col width="11%" />
				<col width="9%" />
				<col width="33%" />
				<thead>
					<tr>
						<th class="titreColonne">&nbsp;</th>
						<th class="titreColonne">Pilote</th>
						<th class="titreColonne">Date et heure</th>
						<th class="titreColonne">Serveur</th>
						<th class="titreColonne">Application</th>
						<th class="titreColonne">Domaine</th>
						<th class="titreColonne">Coupure service</th>
						<th class="titreColonne">Observations</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="listIncident.isEmpty()">
						<tr>
							<td colspan="9" class="emptyListText">Aucun incident <s:if test="typeSelected != -1">de ce type </s:if>en cours</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listIncident">
							<tr>
								<td align="center">
									<s:if test='ars == null || ars.length() == 0'>
										<img class="icone" alt="ARS absent" title="Pas de numéro d'incident attribué" src="<s:property value="#session.ENSEIGNE" />/img/attention-16.png" />
									</s:if>
								</td>
								<td align="center"><s:property value="%{user.prenom}"/>&nbsp;<s:property value="%{user.nom}"/></td>
								<td align="center">
									<s:date name="%{heureDebut}" format="dd/MM/yyyy" /><br/>
									<s:date name="%{heureDebut}" format="HH:mm" />
								</td>
								<td align="center"><s:property value="%{machine.nom}"/></td>
								<td align="center">
									<s:iterator value="appMap.get(id)">
										<s:property value="%{applicatif}"/><br/>
									</s:iterator>
								</td>
								<td align="center"><s:property value="%{domaine.environnement}"/></td>
								<td align="center">
									<s:if test="coupure == 0">Pas de coupure</s:if>
									<s:else>Coupure</s:else>
								</td>
								<td align="center"><s:property value="%{observation}"/></td>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
			</table>
		</div>
	</body>
</html>
