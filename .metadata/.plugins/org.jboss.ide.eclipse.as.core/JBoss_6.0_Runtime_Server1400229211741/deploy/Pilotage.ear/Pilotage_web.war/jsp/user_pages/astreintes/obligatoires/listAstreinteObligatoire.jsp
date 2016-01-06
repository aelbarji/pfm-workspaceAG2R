<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
	
		//action de suppression d'un utilisateur
		function supprimer(id) {
			if (confirm("Voulez-vous vraiment supprimer cette astreinte obligatoire?")) {
				changeData('selectRow', id);
				submitData('supprimerAstreinteObligatoireAction.action');
			}
		}
	
		//action de modification
		function modifier(id) {
			changeData('selectRow', id);
			submitData('redirectModifyAstreinteObligatoireAction.action');
		}

		//action d'ajout
		function ajouter(){
			submitData('redirectCreateAstreinteObligatoireAction.action');
		}
		
		//reinitialisation des filtres comme à l'entrée de la page
		function reinitFilterValues() {
			document.getElementById("mainForm").filtreDomaine.value = '<s:property value="filtreDomaine" />';
			document.getElementById("mainForm").filtreType.value = '<s:property value="filtreType" />';
			document.getElementById("mainForm").filtreIndicEnvoi.value = '<s:property value="filtreIndicEnvoi" />';
		}
	
		//action à la recherche avec filtre
		function lancerRecherche() {
			document.getElementById("mainForm").submit();
		}

        //reinitialisation des filtres 
		function reinitialiserLesFiltres(){
			document.getElementById("mainForm").filtreDomaine.value = '-1';
			document.getElementById("mainForm").filtreType.value = '-1';
			document.getElementById("mainForm").filtreIndicEnvoi.value = '';
			lancerRecherche();
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('AST_O_LST')" /></s:param>
	</s:include>
	
	<s:if test="filtreJson != null">
		<div id="filtreString" class="filtreString">
			<u><b>Filtres</b></u> <s:property value="%{filtreString}" /><br/>
			<a href="#" onclick="javascript:reinitialiserLesFiltres();">
				<img class="icone" alt="Réinitialiser les filtres" title="Réinitialiser les filtres" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" />
			</a>
		</div>
	</s:if>
	
	<div class="contentTable" style="width: 70%">
		<s:if test="#session.USER_DROITS.contains('AST_OBL_ADD')">
			<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
		</s:if>

		<table class="dataTable" rules="all">
			<col width="40%"/>
			<col width="40%"/>
			<col width="300px"/>
			<s:if test="#session.USER_DROITS.contains('AST_OBL_MOD') || #session.USER_DROITS.contains('AST_OBL_DEL')">
				<col width="100px"/>
			</s:if>
			<thead>

			<s:form id="mainForm" action="showAstreinteObligatoireAction" theme="simple">
			<tr>
				<td align="center" valign="top">Filtre domaine :<br>
					<s:hidden id="selectRow" name="selectRow"/>
					<s:hidden name="sort" id="sort" />
					<s:hidden name="sens" id="sens" />
					<s:hidden name="validForm" value="1" />
					<s:token></s:token>
					<s:select list="%{aDomaines}" listKey="id" listValue="domaine" name="filtreDomaine" headerKey="-1" headerValue="" onchange="javascript:lancerRecherche();"></s:select>
				</td>
				<td align="center" valign="top">Filtre type :<br>
					<s:select list="%{aTypes}" listKey="id" listValue="type" name="filtreType" headerKey="-1" headerValue="" onchange="javascript:lancerRecherche();"></s:select>
				</td>
				<td align="center">Filtre indic envoie :<br>
					<s:select list="{'oui','non'}" headerKey="" headerValue="" theme="simple" name="filtreIndicEnvoi" onchange="javascript:lancerRecherche();"></s:select>
				</td>
				<s:if test="#session.USER_DROITS.contains('AST_OBL_MOD') || #session.USER_DROITS.contains('AST_OBL_DEL')">
						<td>&nbsp;</td>
				</s:if>
			</tr>
			</s:form>

				<tr valign="middle">
					<td class="titreColonne">
						<a href="#" onclick="changeSort('domaine');">Domaine</a>
						<s:if test="sens == 'asc' && sort == 'domaine'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if>
						<s:elseif test="sens == 'desc' && sort == 'domaine'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</td>
					<td class="titreColonne">
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
					<td class="titreColonne">
						<a href="#" onclick="changeSort('indicEnvoi');">Indic_envoi</a>
						<s:if test="sens == 'asc' && sort == 'indicEnvoi'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if>
						<s:elseif test="sens == 'desc' && sort == 'indicEnvoi'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</td>
					<s:if test="#session.USER_DROITS.contains('AST_OBL_MOD') || #session.USER_DROITS.contains('AST_OBL_DEL')">
						<td class="titreColonne">Actions</td>
					</s:if>
				</tr>
			</thead>
			<tbody>
				<s:if test="aObligatoires.isEmpty()">
					<tr>
						<td class="emptyListText" colspan=<s:if test="#session.USER_DROITS.contains('AST_OBL_MOD') || #session.USER_DROITS.contains('AST_OBL_DEL')">"4"</s:if><s:else>"3"</s:else>>
							Aucune astreinte obligatoire trouvée
						</td>
					</tr>
				</s:if>
				<s:else>
					<s:iterator value="aObligatoires" id="ao" status="stuts">
						<tr>
							<td align="center">
								<s:property value="#ao.domaine.domaine" />
							</td>
							<td align="center">
								<s:property value="#ao.type.type" />
							</td>
							<s:if test="#ao.indicEnvoi == true">
								<td align="center">Oui</td>
							</s:if>
							<s:elseif test="#ao.indicEnvoi == false">
								<td align="center">Non</td>
							</s:elseif>
							<s:if test="#session.USER_DROITS.contains('AST_OBL_MOD') || #session.USER_DROITS.contains('AST_OBL_DEL')">
								<td align="center" valign="middle">
									<s:if test="#session.USER_DROITS.contains('AST_OBL_MOD')">
										<s:a href="#" onclick="javascript:modifier('%{#ao.id}');">
											<img class="icone" alt="Modifier" title="Modifier"  src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
										</s:a>
									</s:if>
									<s:if test="#session.USER_DROITS.contains('AST_OBL_DEL')">
										<s:a href="#" onclick="javascript:supprimer('%{#ao.id}');">
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
</body>
</html>