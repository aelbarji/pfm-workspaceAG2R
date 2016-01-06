<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function validerSupprimer(typeID) {
			if (confirm("Voulez-vous vraiment supprimer cet outil ?")) {
				valider(typeID, "deleteOutilIncident.action");
			}
		}

		function valider(outilID, actionAddr) {
			changeData("outilID", outilID);
			submitData(actionAddr);
		}

		function ajouter(){
			var mainForm = document.getElementById("mainForm");
			if (mainForm.nomOutils.value.replace(/^\s+/g,'').replace(/\s+$/g,'') == '') {
				alert('Veuillez entrer un libellé');
				mainForm.nomOutils.focus();
				return false;
			} 
			submitData('createOutilIncident.action');
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('ICD_OUT')" /></s:param>
	</s:include>
	<div class="contentTable" style="width: 45%">
		<s:form id="mainForm" theme="simple">
			<s:hidden name="outilID" id="outilID"/>
			
			<s:if test="#session.USER_DROITS.contains('ICD_OTL_ADD')">
				<div class="plus">
					<s:textfield id="nomOutils" name="nomOutils" maxlength="127" size="60" onkeypress="ajouterOnKeyPress(event)"/>
					<s:a href="#" onclick="javascript:ajouter()" cssClass="ajouter">
						<img class="icone" alt="Ajouter" title="Ajouter" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" />
					</s:a>
				</div>
				<br/>
			</s:if>
			
			<table width="100%" class="dataTable" border="1" rules="all" cellpadding="2">
				<col width="90%"/>
				<s:if test="#session.USER_DROITS.contains('ICD_OTL_MOD') || #session.USER_DROITS.contains('ICD_OTL_DEL')">
					<col width="10%"/>
				</s:if>
				<thead>
					<tr>
						<th class="titreColonne">Nom de l'outil</th>
						<s:if test="#session.USER_DROITS.contains('ICD_OTL_MOD') || #session.USER_DROITS.contains('ICD_OTL_DEL')">
							<th class="titreColonne">Actions</th>
						</s:if>
					</tr>
				</thead>
				<tbody>
					<s:if test="listOutils.isEmpty()">
						<tr>
							<td class="emptyListText" <s:if test="#session.USER_DROITS.contains('ICD_OTL_MOD') || #session.USER_DROITS.contains('ICD_OTL_DEL')">colspan="2"</s:if>>
								Aucun outil trouvé
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listOutils">
							<tr>
								<td align="left">&nbsp;&nbsp;&nbsp;<s:property value="%{nomOutils}"/></td>
								<s:if test="#session.USER_DROITS.contains('ICD_OTL_MOD') || #session.USER_DROITS.contains('ICD_OTL_DEL')">
									<td align="center">
										<s:if test="#session.USER_DROITS.contains('ICD_OTL_MOD')">
											<s:a href="#" onclick="javascript:valider('%{id}', 'showModifyOutilIncident.action')">
												<img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
											</s:a>
										</s:if>
										<s:if test="#session.USER_DROITS.contains('ICD_OTL_DEL')">
											<s:a href="#" onclick="javascript:validerSupprimer('%{id}')">
												<img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
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