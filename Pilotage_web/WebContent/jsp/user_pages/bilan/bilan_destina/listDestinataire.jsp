<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<s:include value="/jsp/user_pages/commun/html_head.jsp" />
		<script type="text/javascript">
			function supprimer (selectedID){
				if(confirm("Voulez-vous vraiment supprimer ce destinataire ?")){
					valider(selectedID, "supprimerDestinataire.action");
				}		
			}
			
			function valider(selectedID, actionAddr){
				changeData("selectedID", selectedID);
				submitData(actionAddr);
			}
	
			function changeList(){
				submitData("showDestinataire.action");
			}
	
			function ajouter() {
				submitData('showCreateDestinataire.action');
			}
		</script>
	</head>
	<body>
		<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
			<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('BL_DEST_L')" /></s:param>
		</s:include>
		
		<div class="contentTable"  style="width: 70%">
			<s:form id="mainForm" theme="simple">
				<s:token></s:token>
				<s:hidden name="selectedID" id="selectedID"/>
				
				<s:if test="#session.USER_DROITS.contains('BL_DES_ADD')">
					<s:if test="selectedType != null && selectedType != -1">
						<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
					</s:if>
				</s:if>
				<div class="plusDroite" >
					<div class="plusDiv">
						<s:select list="listEnvoie" id="listE" onchange="javascript:changeList()" theme="simple" name="selectedType" listKey="id" listValue="nom" headerKey="-1" headerValue="--Type de bilan--"></s:select>
					</div>
				</div>
				<br/>
				
				<table class="dataTable" border="1" rules="all">
					<col width="35%"/>
					<col width="55%"/>
					<s:if test="#session.USER_DROITS.contains('BL_DES_MOD') || #session.USER_DROITS.contains('BL_DES_DEL')">
						<col width="10%"/>
					</s:if>
					<thead>
						<tr>
							<th class="titreColonne">Nom/Prénom</th>
							<th class="titreColonne">Adressse Mail</th>
							<s:if test="#session.USER_DROITS.contains('BL_DES_MOD') || #session.USER_DROITS.contains('BL_DES_DEL')">
								<th class="titreColonne">Actions</th>
							</s:if>
						</tr>
					</thead>
					<tbody>
						<s:if test="listDestina.isEmpty()">
							<tr>
								<td class="emptyListText" colspan=<s:if test="#session.USER_DROITS.contains('BL_DES_MOD') || #session.USER_DROITS.contains('BL_DES_DEL')">"3"</s:if><s:else>"2"</s:else>>
									<s:if test="selectedType == null || selectedType == -1">
										Veuillez sélectionner un type de bilan
									</s:if>
									<s:else>
										Aucun destinataire trouvé
									</s:else>
								</td>
							</tr>
						</s:if>
						<s:else>
							<s:iterator value="listDestina">
								<tr>
									<td align="left">&nbsp;&nbsp;<s:property value="%{nom}"/>&nbsp;<s:property value="%{prenom}"/></td>
									<td align="left">&nbsp;&nbsp;<s:property value="%{mail}"/></td>
									<s:if test="#session.USER_DROITS.contains('BL_DES_MOD') || #session.USER_DROITS.contains('BL_DES_DEL')">
										<td align="center">
											<s:if test="#session.USER_DROITS.contains('BL_DES_MOD')">
												<s:a href="#" onclick="javascript:valider('%{id}', 'showModifyDestinataire.action')">
													<img alt="Modifier" title="Modifier" class="icone" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
												</s:a>
											</s:if>
											<s:if test="#session.USER_DROITS.contains('BL_DES_DEL')">
												<s:a href="#" onclick="javascript:supprimer('%{id}')">
													<img alt="Supprimer" title="Supprimer" class="icone" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
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