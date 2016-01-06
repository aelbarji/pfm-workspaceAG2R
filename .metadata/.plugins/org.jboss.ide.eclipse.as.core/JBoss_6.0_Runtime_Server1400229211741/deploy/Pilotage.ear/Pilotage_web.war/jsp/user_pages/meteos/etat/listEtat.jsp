<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv='Expires' content='-10'>
	<meta http-equiv='Pragma' content='No-cache'>
	<meta http-equiv='Cache-Control' content='private'>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function ajouter(){
			submitData('showCreateEtatMeteoAction.action');
		}
	
		function modifier(id){
			changeData('selectRow', id);
			submitData('showModifyEtatMeteoAction.action');
		}
	
		function supprimer(id){
			if(confirm("Voulez-vous vraiment supprimer cet état ?")){
				changeData('selectRow', id);
				submitData('deleteEtatMeteoAction.action');
			}
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MTG_ETA_L')" /></s:param>
	</s:include>
	<div class="contentTable" style="width:50%">
		<s:form id="mainForm" method="POST" theme="simple">
			<s:hidden id="selectRow" name="selectRow"/>
			<s:token></s:token>
		
		
			<s:if test="#session.USER_DROITS.contains('PLN_VAC_ADD')">
				<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
			</s:if>
			
			<table class="dataTable" rules="all">
				<col width="20%"/>
				<col width="5%"/>
				<col width="40%"/>
				<s:if test="#session.USER_DROITS.contains('PLN_VAC_MOD') || #session.USER_DROITS.contains('PLN_VAC_DEL')">
					<col width="20%"/>
				</s:if>
				<thead>
					<tr>
						<td class="titreColonne" >Libellé</td>
						<td class="titreColonne" >Couleur</td>
						<td class="titreColonne" >Définition</td>
						<td class="titreColonne" >Actions</td>
					</tr>
				</thead>
				<tbody>
					<s:if test="listEtat.isEmpty()">
						<tr>
							<td colspan="4" class="emptyListText">
								Aucun état trouvé
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listEtat" id="listEtat">
							<tr>
								<td>&nbsp;&nbsp;&nbsp;<s:property value="#listEtat.libelle_etat"/></td>
								<td style="background-color:<s:property value="#listEtat.couleur"/>;"></td>
								<td>&nbsp;&nbsp;&nbsp;<s:property value="#listEtat.definition"/></td>
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
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
			</table>
		
	</s:form>
	</div>
</body>
</html>