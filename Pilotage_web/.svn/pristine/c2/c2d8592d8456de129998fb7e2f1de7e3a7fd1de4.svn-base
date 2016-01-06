<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		//action de suppression d'un utilisateur
		function supprimer(id) {
			changeData('typeID', id);
			submitData('deleteTypeIndicateurAction.action');
		}
	
		//action de modification
		function modifier(id) {
			changeData('typeID', id);
			submitData('showModifyTypeIndicateurAction.action');
		}

		//action d'ajout
		function ajouter(){
			submitData('showCreateTypeIndicateurAction.action');
		}

		//Detail
		function detail(id){
			changeData("typeID", id);
			submitData('showDetailTypeIndicateurAction.action');
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MTG_TYP_L')" /></s:param>
	</s:include>
	
	<div class="contentTable" style="width:40%">
		<s:form id="mainForm">
			<s:hidden id="typeID" name="typeID"/>
			<s:token></s:token>
			<s:if test="#session.USER_DROITS.contains('MTG_MET_ADD')">
					<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
			</s:if>
		
			<table class="dataTable" rules="all" cellpadding="2">
				<col width="90%"/>
				<s:if test="#session.USER_DROITS.contains('MTG_TYP_MOD') || #session.USER_DROITS.contains('MTG_TYP_DEL')">
					<col width="10%"/>
				</s:if>
				<thead>
					<tr>
						<td class="titreColonne">Type Indicateur</td>
						<s:if test="#session.USER_DROITS.contains('MTG_TYP_MOD') || #session.USER_DROITS.contains('MTG_TYP_DEL')">
							<td class="titreColonne">Actions</td>
						</s:if>
					</tr>
				</thead>
				<tbody>
					<s:if test="listTypeIndic.isEmpty()">
						<tr>
							<td class="emptyListText" <s:if test="#session.USER_DROITS.contains('MTG_TYP_MOD') || #session.USER_DROITS.contains('MTG_TYP_DEL')">colspan="2"</s:if>>
								Aucun type trouvé
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listTypeIndic">
							<tr>
								<td align="left">&nbsp;&nbsp;&nbsp; <s:property value="%{type}" /></td>
								<s:if test="#session.USER_DROITS.contains('MTG_TYP_MOD') || #session.USER_DROITS.contains('MTG_TYP_DEL')">
									<td align="center">
										<s:if test="#session.USER_DROITS.contains('MTG_TYP_MOD')">
										    <s:a href="#" onclick="javascript:modifier('%{id}');">
												<img alt="Modifier" title="Modifier" class="icone" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
											</s:a>
										</s:if>
										<s:if test="#session.USER_DROITS.contains('MTG_TYP_DEL')">
											<s:a href="#" onclick="javascript:supprimer('%{id}');">
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