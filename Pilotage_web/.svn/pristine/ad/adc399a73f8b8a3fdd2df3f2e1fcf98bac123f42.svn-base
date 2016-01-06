<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<s:include value="/jsp/user_pages/commun/html_head.jsp" />
		<script type="text/javascript">
			function supprimer (selectedID){
				if(confirm("Voulez-vous vraiment supprimer ce type de bilan ?")){
					valider(selectedID, "supprimerEnvoie.action");
				}		
			}
			
			function valider(selectedID, actionAddr){
				changeData("selectedID", selectedID);
				submitData(actionAddr);
			}

			function ajouter(){
				submitData('showCreateEnvoie.action');
			}
		</script>
	</head>
	<body>
		<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
			<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('BL_TYPE')" /></s:param>
		</s:include>
		
		<div class="contentTable"  style="width: 90%">
			<s:form id="mainForm" theme="simple">
				<s:token></s:token>
				<s:hidden name="selectedID" id="selectedID" value=""/>

				<s:if test="#session.USER_DROITS.contains('BL_TYP_ADD')">
					<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
				</s:if>
				
				<table class="dataTable" border="1" rules="all">
					<col width="15%"/>
					<col width="15%"/>
					<col width="23%"/>
					<col width="7%"/>
					<col width="7%"/>
					<col width="7%"/>
					<col width="7%"/>
					<col width="7%"/>
					<col width="7%"/>
					<s:if test="#session.USER_DROITS.contains('BL_TYP_MOD') || #session.USER_DROITS.contains('BL_TYP_DEL')">
						<col width="5%"/>
					</s:if>
					<thead>
						<tr>
							<th class="titreColonne" rowspan="2">Nom</th>
							<th class="titreColonne" rowspan="2">Libell&eacute;</th>
							<th class="titreColonne" rowspan="2">Environnements</th>
							<th class="titreColonne" rowspan="2">Nombre d'envois attendus / jour</th>
							<th class="titreColonne" colspan="6">Affichage</th>
							<s:if test="#session.USER_DROITS.contains('BL_TYP_MOD') || #session.USER_DROITS.contains('BL_TYP_DEL')">
								<th class="titreColonne" rowspan="2">Action</th>
							</s:if>
						</tr>
						<tr>
							<th class="titreColonne">Vacations</th>
							<th class="titreColonne">Résumé</th>
							<th class="titreColonne">Espaces disques</th>
							<th class="titreColonne">Espaces disques des filiales</th>
							<th class="titreColonne">Flux en erreur</th>
							<th class="titreColonne">Statistiques</th>
						</tr>
					</thead>
					<tbody>
						<s:if test="listEnvoie.isEmpty()">
							<tr>
								<td class="emptyListText" colspan=<s:if test="#session.USER_DROITS.contains('BL_TYP_MOD') || #session.USER_DROITS.contains('BL_TYP_DEL')">"11"</s:if><s:else>"10"</s:else>>
									Aucun type de bilan trouvé
								</td>
							</tr>
						</s:if>
						<s:else>
							<s:iterator value="listEnvoie">
								<tr>
									<td align="left">&nbsp;&nbsp;<s:property value="%{nom}"/></td>
									<td align="left">&nbsp;&nbsp;<s:property value="%{libelle}"/></td>
									<td align="left">
										<s:if test='null == clauseSelect || "".equals(clauseSelect)'>&nbsp;&nbsp;Tous</s:if>
										<s:else>
											<s:iterator value="listEnvironnement.get(id)">
												&nbsp;&nbsp;<s:property value="%{environnement}"/><br/>
											</s:iterator>
										</s:else>
									</td>
									<td align="center">&nbsp;&nbsp;<s:property value="%{nbDailySent}"/></td>
									<td align="center">
										<s:if test="vacation == true">Oui</s:if>
										<s:else>Non</s:else>
									</td>
									<td align="center">
										<s:if test="actionEPI == true">Oui</s:if>
										<s:else>Non</s:else>
									</td>
									<td align="center">
										<s:if test="espaceDisk == true">Oui</s:if>
										<s:else>Non</s:else>
									</td>
									<td align="center">
										<s:if test="disknonOCEOR == true">Oui</s:if>
										<s:else>Non</s:else>
									</td>
									<td align="center">
										<s:if test="etatCFT == true">Oui</s:if>
										<s:else>Non</s:else>
									</td>
									<td align="center">
										<s:if test="statEPI == true">Oui</s:if>
										<s:else>Non</s:else>
									</td>
									<s:if test="#session.USER_DROITS.contains('BL_TYP_MOD') || #session.USER_DROITS.contains('BL_TYP_DEL')">
										<td align="center">
											<s:if test="#session.USER_DROITS.contains('BL_TYP_MOD')">
												<s:a href="#" onclick="javascript:valider('%{id}', 'showModifyEnvoie.action')">
													<img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
												</s:a>
											</s:if>
											<s:if test="#session.USER_DROITS.contains('BL_TYP_DEL')">
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