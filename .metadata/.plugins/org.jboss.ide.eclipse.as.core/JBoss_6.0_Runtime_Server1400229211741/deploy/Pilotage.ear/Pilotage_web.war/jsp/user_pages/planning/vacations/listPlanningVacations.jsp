<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function ajouter(){
			submitData('showCreatePlanningVacationsAction.action');
		}

		function modifier(id){
			changeData('selectRow', id);
			submitData('showModifyPlanningVacationsAction.action');
		}
	
		function supprimer(id){
			if(confirm("Voulez-vous vraiment supprimer cette vacation ?")){
				changeData('selectRow', id);
				submitData('supprimerPlanningVacationsAction.action');
			}
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('PLN_VAC')" /></s:param>
		<s:param name="filtre" value="false"></s:param>
	</s:include>
	<s:form theme="simple" id="mainForm" onsubmit="return false;" >
		<s:hidden id="selectRow" name="selectRow" value=""/>
		<s:token></s:token>
		
		<div class="contentTable" style="width: 70%">
			<s:if test="#session.USER_DROITS.contains('PLN_VAC_ADD')">
				<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
			</s:if>
			
			<table class="dataTable" rules="all">
				<col width="25%"/>
				<col width="25%"/>
				<col width="25%"/>
				<col width="30%"/>
				<s:if test="#session.USER_DROITS.contains('PLN_VAC_MOD') || #session.USER_DROITS.contains('PLN_VAC_DEL')">
					<col width="20%"/>
				</s:if>
				<thead>
					<tr>
						<td class="titreColonne" >Heure de début</td>
						<td class="titreColonne" >Heure de fin</td>
						<td class="titreColonne" >Libelle</td>
						<td class="titreColonne" >Partie Jour</td>
						<s:if test="#session.USER_DROITS.contains('PLN_VAC_MOD') || #session.USER_DROITS.contains('PLN_VAC_DEL')">
							<td class="titreColonne" >Action</td>
						</s:if>
					</tr>
				</thead>
				<tbody>
					<s:if test="listPlanningVacation.isEmpty()">
						<tr>
							<td class="emptyListText" colspan=<s:if test="#session.USER_DROITS.contains('PLN_VAC_MOD') || #session.USER_DROITS.contains('PLN_VAC_DEL')">"4"</s:if><s:else>"3"</s:else>>
								Aucune vacation trouvée
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listPlanningVacation">
							<tr>
								<td align="left">&nbsp;&nbsp;<s:date name="%{heureDebut}" format="HH:mm"/></td>
								<td align="left">&nbsp;&nbsp;<s:date name="%{heureFin}" format="HH:mm"/></td>
								<td align="left"><font style="background-color:#<s:property value='codeCouleur'/>;"><s:property value="libelle"/></font></td>
								<td align="left">&nbsp;&nbsp;<s:property value="partiJour.nom"/> </td>
								<s:if test="#session.USER_DROITS.contains('PLN_VAC_MOD') || #session.USER_DROITS.contains('PLN_VAC_DEL')">
									<td align="center" valign="middle">
										<s:if test="#session.USER_DROITS.contains('PLN_VAC_MOD')">
											<s:a href="#" onclick="javascript:modifier('%{id}');">
												<img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
											</s:a>
										</s:if>
										<s:if test="#session.USER_DROITS.contains('PLN_VAC_DEL')">
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
		</div>
	</s:form>
</body>
</html>
				