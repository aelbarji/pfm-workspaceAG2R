<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
	
		//action de suppression d'un utilisateur
		function supprimer(id) {
			if (confirm("Voulez-vous vraiment supprimer cet environnement ?")) {
				changeData('selectRow', id); 
				submitData('supprimerEnvironnementAction.action');
			}
		}

		//action de modification
		function modifier(id){
			changeData('selectRow', id); 
			submitData('redirectModifierEnvironnementAction.action');
		}

		//action d'ajout
		function ajouter(){
			submitData('redirectCreateEnvironnementAction.action');
		}
	
		//reinitialisation des filtres comme à l'entrée de la page
		function reinitFilterValues() {
			document.getElementById("mainForm").filtreEnvironnement.value = '<s:property value="filtreEnvironnement" />';
			document.getElementById("mainForm").filtreType.value = '<s:property value="filtreType" />';
		}
	
		//action à la recherche avec filtre
		function lancerRecherche() {
			document.getElementById("mainForm").page.value = '1';
			document.getElementById("mainForm").submit();
		}

        //reinitialisation des filtres 
		function reinitialiserLesFiltres(){
			document.getElementById("mainForm").filtreEnvironnement.value = '';
			document.getElementById("mainForm").filtreType.value = '-1';
			lancerRecherche();
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('ENV_LST')" /></s:param>
	</s:include>
	
	<s:if test="filtreString != '' && filtreString != null">
		<div id="filtreString" class="filtreString">
			<u><b>Filtres</b></u> <s:property value="%{filtreString}" /><br/>
			<a href="#" onclick="javascript:reinitialiserLesFiltres();">
				<img class="icone" alt="Réinitialiser les filtres" title="Réinitialiser les filtres" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" />
			</a>
		</div>
	</s:if>
	
	<div class="contentTable" style="width: 70%">
		<s:if test="#session.USER_DROITS.contains('ENV_ADD')">
			<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
			<br/>
		</s:if>
		
		<table class="dataTable" rules="all" border="1">
			<col width="30%"/>
			<col width="30%"/>
			<s:if test="#session.USER_DROITS.contains('ENV_MOD') || #session.USER_DROITS.contains('ENV_DEL')">
				<col width="10%"/>
			</s:if>
			<thead>
				<tr>
					<td colspan=<s:if test="#session.USER_DROITS.contains('ENV_MOD') || #session.USER_DROITS.contains('ENV_DEL')">"3"</s:if><s:else>"2"</s:else>>
						<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
					</td>
				</tr>
				<s:form id="mainForm" action="showEnvironnementAction" theme="simple">
				<tr>
					<td align="left">Filtre environnement :<br>
						<s:token></s:token>
						<s:hidden id="selectRow" name="selectRow" value=""/>
						<s:hidden name="sort" id="sort" />
						<s:hidden name="sens" id="sens" />
						<s:hidden name="page" id="page" />
						<s:hidden name="nrPerPage" id="nrPerPage"/>
						<s:hidden name="nrPages" value="%{pagination.nrPages}" />
						<s:hidden name="validForm" value="1" />
						<s:textfield name="filtreEnvironnement" maxlength="20" size="25" onchange="javascript:lancerRecherche();" onkeydown="lancerRechercherOnKeyPress(event);"></s:textfield>
					</td>
					<td align="center">Filtre type :<br>
						<s:select list="%{environnement_Types}" listKey="id" listValue="type" name="filtreType" headerKey="-1" headerValue="" onkeydown="lancerRechercherOnKeyPress(event);" onchange="javascript:lancerRecherche();"></s:select>
					</td>
					<s:if test="#session.USER_DROITS.contains('ENV_MOD') || #session.USER_DROITS.contains('ENV_DEL')">
						<td>&nbsp;</td>
					</s:if>
				</tr>
				</s:form>
				<tr valign="middle">
					<td class="titreColonne">
						<a href="#" onclick="changeSort('environnement');">Environnement</a>
						<s:if test="sens == 'asc' && sort == 'environnement'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if> 
						<s:elseif test="sens == 'desc' && sort == 'environnement'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
						
					
					<td class="titreColonne" >
						<a href="#" onclick="changeSort('type');">Type</a>
						<s:if test="sens == 'asc' && sort == 'type'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if>
						<s:elseif test="sens == 'desc' && sort == 'type'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</td>
					<s:if test="#session.USER_DROITS.contains('ENV_MOD') || #session.USER_DROITS.contains('ENV_DEL')">
						<td class="titreColonne" >Actions</td>
					</s:if>
				</tr>
			</thead>
			<tbody>
				<s:if test="environnements.isEmpty()">
					<tr>
						<td class="emptyListText" colspan=<s:if test="#session.USER_DROITS.contains('ENV_MOD') || #session.USER_DROITS.contains('ENV_DEL')">"3"</s:if><s:else>"2"</s:else>>
							Aucune environnement trouvé
						</td>
					</tr>
				</s:if>
				<s:else>
					<s:iterator value="environnements" id="environnements" status="stuts">
						<tr>
							<td>&nbsp;&nbsp;<s:property value="#environnements.environnement" /></td>
							<td align="center"><s:property value="#environnements.type.type" /></td>
							<s:if test="#session.USER_DROITS.contains('ENV_MOD') || #session.USER_DROITS.contains('ENV_DEL')">
								<td align="center">
									<s:if test="#session.USER_DROITS.contains('ENV_MOD')">
										<s:a href="#" onclick="javascript:modifier('%{#environnements.id}');">
											<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
										</s:a>
									</s:if>
									<s:if test="#session.USER_DROITS.contains('ENV_DEL')">
										<s:a href="#" onclick="javascript:supprimer('%{#environnements.id}');">
											<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
										</s:a>
									</s:if>
								</td>
							</s:if>
						</tr>
					</s:iterator>
				</s:else>
			</tbody>
			<tfoot>
				<tr>
					<td colspan=<s:if test="#session.USER_DROITS.contains('ENV_MOD') || #session.USER_DROITS.contains('ENV_DEL')">"3"</s:if><s:else>"2"</s:else>>
						<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>