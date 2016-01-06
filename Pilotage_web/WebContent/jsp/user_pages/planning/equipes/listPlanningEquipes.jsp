<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function ajouter(){
			submitData('showCreatePlanningEquipesAction.action');
		}

		function modifier(id){
			changeData('selectRow', id);
			submitData('showModifyPlanningEquipesAction.action');
		}
	
		function supprimer(id){
			if(confirm("Voulez-vous vraiment supprimer cette équipe ?")){
				changeData('selectRow', id);
				submitData('supprimerPlanningEquipesAction.action');
			}
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('PLN_EQU_L')" /></s:param>
		<s:param name="filtre" value="false"></s:param>
	</s:include>
	<s:form theme="simple" id="mainForm" onsubmit="return false;" >
		<s:hidden id="selectRow" name="selectRow" value=""/>
		<s:token></s:token>
		
		<div class="contentTable" style="width: 70%">
			<s:if test="#session.USER_DROITS.contains('PLN_EQU_ADD')">
				<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
			</s:if>
			
			<table class="dataTable" rules="all">
				<col width="70%"/>
				<s:if test="#session.USER_DROITS.contains('PLN_EQU_MOD') || #session.USER_DROITS.contains('PLN_EQU_DEL')">
					<col width="30%"/>
				</s:if>
				<thead>
					<tr>
						<td class="titreColonne" >Equipe</td>
						<s:if test="#session.USER_DROITS.contains('PLN_EQU_MOD') || #session.USER_DROITS.contains('PLN_EQU_DEL')">
							<td class="titreColonne" >Action</td>
						</s:if>
					</tr>
				</thead>
				<tbody>
					<s:if test="listPlanningEquipe.isEmpty()">
						<tr>
							<td class="emptyListText" <s:if test="#session.USER_DROITS.contains('PLN_EQU_MOD') || #session.USER_DROITS.contains('PLN_EQU_DEL')">colspan="2"</s:if>>
								Aucune équipe trouvée
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listPlanningEquipe">
							<tr>
								<td align="left"><s:property value="nomEquipe"/></td>
								<s:if test="#session.USER_DROITS.contains('PLN_EQU_MOD') || #session.USER_DROITS.contains('PLN_EQU_DEL')">
									<td align="center" valign="middle">
										<s:if test="#session.USER_DROITS.contains('PLN_EQU_MOD')">
											<s:a href="#" onclick="javascript:modifier('%{id}');">
												<img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
											</s:a>
										</s:if>
										<s:if test="#session.USER_DROITS.contains('PLN_EQU_DEL')">
											<s:a href="#" onclick="javascript:supprimer('%{id}');">
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
			<div class="contentTableBottom">
			</div>
		</div>
	</s:form>
</body>
</html>
				