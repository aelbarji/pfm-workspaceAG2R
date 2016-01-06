<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">

		//Suppression
		function supprimer(id){
			if(confirm("Voulez-vous vraiment supprimer cet environnement ?")){
				changeData("envirID", id);
				submitData("deleteEnvironnementMeteoAction.action");
			}
		}

		//Modification
		function modifier(id){
			changeData("envirID", id);
			submitData('showModifyEnvironnementMeteoAction.action');
		}

		//Ajout
		function ajouter(){
			submitData('showCreateEnvironnementMeteoAction.action');
		}

		//Detail
		function detail(id){
			changeData('envirID', id);
			submitData('showDetailEnvironnementMeteoAction.action');
		}
		
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MTG_ENV_L')" /></s:param>
	</s:include>
	<div class="contentTable" style="width:50%">
		<s:form id="mainForm" method="POST" action="showEnvironnementMeteoAction" theme="simple">
			<s:token></s:token>
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage"/>
			<s:hidden name="nrPages" value="%{pagination.nrPages}" />
			<s:hidden name="envirID" id="envirID" />
			
	
			<s:if test="#session.USER_DROITS.contains('MTG_ENV_ADD')">
					<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
			</s:if>
			<table class="dataTable" rules="all" border="1">
				<col width="">
				<col width="200px">
				<thead>
				<tr>
					<td colspan="3">
						<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
					</td>
				</tr>
				<tr valign="middle">
						<td class="titreColonne">
							<a href="#" onclick="changeSort('nom');">Nom Environnement Météo</a>
						</td>
						<td class="titreColonne" >Actions
						</td>
				</thead>
				<tbody>
					<s:if test="environnements.isEmpty()">
							<tr>
								<td colspan="9" class="emptyListText">
									Aucun environnement Météo 
								</td>
							</tr>
						</s:if>
						<s:else>
							<s:iterator value="environnements" id="environnements">
									<tr>
										<td>&nbsp;&nbsp;<s:property value="#environnements.nom_envir" /></td>
										<td class="td" align="center">
										<s:if test="#session.USER_DROITS.contains('MTG_ENV_MOD')">
											<s:a href="#" onclick="javascript:modifier('%{id}');">
												<img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png">
											</s:a>
										</s:if>
										<s:if test="#session.USER_DROITS.contains('MTG_ENV_DEL')">
											<s:a href="#" onclick="javascript:supprimer('%{id}');">
												<img class="icone"  alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png">
											</s:a>
										</s:if>
										<s:if test="#session.USER_DROIT.admin">
											<s:a href="#" onclick="javascript:detail('%{id}');">
												<img class="icone" alt="Détail" title="Détail" src="<s:property value="#session.ENSEIGNE" />/img/detail-16.png">
											</s:a>
										</s:if>
									</td>
									</tr>
							</s:iterator>
					</s:else>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="3">
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
				</tfoot>
			</table>
		</s:form>
	</div>
</body>
</html>