<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<s:include value="/jsp/user_pages/commun/html_head.jsp" />
		<script type="text/javascript">
			function supprimer (selectedID){
				if(confirm("voulez-vous vraiment supprimer ce destinataire ?")){
					valider(selectedID, "supprimerAlertesDestinataire.action");
				}		
			}
			
			function valider(selectedID, actionAddr){
				changeData("selectedID", selectedID);
				submitData(actionAddr);
			}

			function ajouter(){
				submitData('showCreateAlertesDestinataire.action');
			}
		</script>
	</head>
	<body>
		<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
			<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('ALT_DST')" /></s:param>
		</s:include>
	
		<div class="contentTable" style="width : 70%">
			<s:form id="mainForm" theme="simple">
				<s:token></s:token>
				<s:hidden name="selectedID" id="selectedID"/>
				
				<s:if test="#session.USER_DROITS.contains('AD_DES_ADD')">
					<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
				</s:if>
				
				<table class="dataTable" border="1" rules="all">
					<col width="35%"/>
					<col width="55%"/>
					<s:if test="#session.USER_DROITS.contains('AD_DES_MOD') || #session.USER_DROITS.contains('AD_DES_DEL')">
						<col width="10%"/>
					</s:if>
					<thead>
						<tr>
							<th class="titreColonne">Nom/Prénom</th>
							<th class="titreColonne">Mail</th>
							<s:if test="#session.USER_DROITS.contains('AD_DES_MOD') || #session.USER_DROITS.contains('AD_DES_DEL')">
								<th class="titreColonne">Actions</th>
							</s:if>
						</tr>
					</thead>
					<tbody>
						<s:if test="listDestina.isEmpty()">
							<tr>
								<td class="emptyListText" colspan=<s:if test="#session.USER_DROITS.contains('AD_DES_MOD') || #session.USER_DROITS.contains('AD_DES_DEL')">"3"</s:if><s:else>"2"</s:else>>
									Aucun destinataire trouvé
								</td>
							</tr>
						</s:if>
						<s:else>
							<s:iterator value="listDestina">
								<tr>
									<td align="left">&nbsp;&nbsp;<s:property value="%{prenom}"/>&nbsp;<s:property value="%{nom}"/></td>
									<td align="left">&nbsp;&nbsp;<s:property value="%{mail}"/></td>
									<s:if test="#session.USER_DROITS.contains('AD_DES_MOD') || #session.USER_DROITS.contains('AD_DES_DEL')">
										<td align="center">
											<s:if test="#session.USER_DROITS.contains('AD_DES_MOD')">
												<s:a href="#" onclick="javascript:valider('%{id}', 'showModifyAlertesDestinataire.action')">
													<img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
												</s:a>
											</s:if>
											<s:if test="#session.USER_DROITS.contains('AD_DES_DEL')">
												<s:a href="#" onclick="javascript:supprimer('%{id}')">
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