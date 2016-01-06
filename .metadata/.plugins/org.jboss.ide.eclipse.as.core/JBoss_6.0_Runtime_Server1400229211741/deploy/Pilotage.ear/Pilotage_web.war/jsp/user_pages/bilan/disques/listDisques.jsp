<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<s:include value="/jsp/user_pages/commun/html_head.jsp" />
		<script type="text/javascript">
			function supprimer (selectedID){
				if(confirm("Voulez-vous vraiment supprimer ce disque ?")){
					valider(selectedID, "supprimerDisque.action");
				}		
			}
			
			function valider(selectedID, actionAddr){
				changeData("selectedID", selectedID);
				submitData(actionAddr);
			}

			function ajouter(){
				submitData('showCreateDisque.action');
			}
		</script>
	</head>
	<body>
		<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
			<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('DSQ_LST')" /></s:param>
		</s:include>
		
		<div class="contentTable"  style="width: 60%">
			<s:form id="mainForm" theme="simple">
				<s:token></s:token>
				<s:hidden name="selectedID" id="selectedID"/>
				
				<s:if test="#session.USER_DROITS.contains('BL_DSK_ADD')">
					<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
				</s:if>
				
				<table class="dataTable" border="1" rules="all">
					<col width="50%"/>
					<col width="20%"/>
					<col width="20%"/>
					<s:if test="#session.USER_DROITS.contains('BL_DSK_MOD') || #session.USER_DROITS.contains('BL_DSK_DEL')">
						<col width="10%"/>
					</s:if>
					<thead>
						<tr>
							<th class="titreColonne">Libellé</th>
							<th class="titreColonne">Filiale</th>
							<th class="titreColonne">Seuil</th>
							<s:if test="#session.USER_DROITS.contains('BL_DSK_MOD') || #session.USER_DROITS.contains('BL_DSK_DEL')">
								<th class="titreColonne">Actions</th>
							</s:if>
						</tr>
					</thead>
					<tbody>
						<s:if test="listDisques.isEmpty()">
							<tr>
								<td class="emptyListText" colspan=<s:if test="#session.USER_DROITS.contains('BL_DSK_MOD') || #session.USER_DROITS.contains('BL_DSK_DEL')">"4"</s:if><s:else>"3"</s:else>>
									Aucun disque trouvé
								</td>
							</tr>
						</s:if>
						<s:else>
							<s:iterator value="listDisques">
								<tr>
									<td align="left">&nbsp;&nbsp;<s:property value="%{libelle}"/></td>
									<td align="center"><s:if test="%{filiale}">Filiale</s:if><s:else>Non Filiale</s:else></td>
									<td align="center"><s:text name="format.percent"><s:param value="%{seuil}"/></s:text>%</td>
									<s:if test="#session.USER_DROITS.contains('BL_DSK_MOD') || #session.USER_DROITS.contains('BL_DSK_DEL')">
										<td align="center">
											<s:if test="#session.USER_DROITS.contains('BL_DSK_MOD')">
												<s:a href="#" onclick="javascript:valider('%{id}', 'showModifyDisque.action')">
													<img alt="Modifier" title="Modifier" class="icone" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
												</s:a>
											</s:if>
											<s:if test="#session.USER_DROITS.contains('BL_DSK_DEL')">
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