<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		//action d'effacer le champ
		function clearValue(name){
			document.getElementById("mainForm").name.value="";
		}

		//action de suppression d'un utilisateur
		function supprimer(id) {
			if (confirm("Voulez-vous vraiment supprimer cette application ?")) {
				reinitFilterValues();
				changeData('selectRow', id); 
				submitData('deleteApplicationAction.action');
			}
		}

		function ajouter(){
			reinitFilterValues();
			submitData('showCreateApplicationAction.action');
		}

		//action de modification
		function modifier(id){
			reinitFilterValues();
			changeData('selectRow', id); 
			submitData('showModifyApplicationAction.action');
		}

		//reinitialisation des filtres comme à l'entrée de la page
		function reinitFilterValues() {
			var mainForm = document.getElementById("mainForm");
			mainForm.filtreNom.value = '<s:property value="filtreNom" escape="false"/>';
			mainForm.filtreDescription.value = '<s:property value="filtreDescription" escape="false"/>';
			mainForm.filtreDocument.value = '<s:property value="filtreDocument" escape="false"/>';
		}

		//action à la recherche avec filtre
		function lancerRecherche() {
			document.getElementById("mainForm").page.value = '1';
			document.getElementById("mainForm").submit();
		}

	     //reinitialisation des filtres 
		function reinitialiserLesFiltres(){
			var mainForm = document.getElementById("mainForm");
			mainForm.filtreNom.value = '';
			mainForm.filtreDescription.value = '';
			mainForm.filtreDocument.value = '';
			lancerRecherche();
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('APP_LST')" /></s:param>
	</s:include>
	
	<s:if test="filtreString != '' && filtreString != null">
		<div id="filtreString" class="filtreString">
			<u><b>Filtres</b></u> <s:property value="%{filtreString}" /><br/>
			<a href="#" onclick="javascript:reinitialiserLesFiltres();">
				<img alt="Réinitialiser les filtres" title="Réinitialiser les filtres" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" border="0" >
			</a>
		</div>
	</s:if>

	<div class="contentTable" >
		<s:if test="#session.USER_DROITS.contains('APP_ADD')">
			<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
		</s:if>
		
		<table class="dataTable" rules="all" border="1">
			<col width="25%"/>
			<col width="25%"/>
			<col width=""/>
			<s:if test="#session.USER_DROITS.contains('APP_MOD') || #session.USER_DROITS.contains('APP_DEL')">
				<col width="10%"/>
			</s:if>
			<thead>
				<tr>
					<td colspan=<s:if test="#session.USER_DROITS.contains('APP_MOD') || #session.USER_DROITS.contains('APP_DEL')">"4"</s:if><s:else>"3"</s:else>>
						<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
					</td>
				</tr>
				
				<tr>
					<s:form id="mainForm" action="showApplicationsListeAction" theme="simple">
						<s:token></s:token>
						<s:hidden id="selectRow" name="selectRow" value=""/>
						<s:hidden name="sort" id="sort" />
						<s:hidden name="sens" id="sens" />
						<s:hidden name="page" id="page" />
						<s:hidden name="nrPerPage" id="nrPerPage"/>
						<s:hidden name="nrPages" value="%{pagination.nrPages}" />
						
						<td align="left">Filtre nom :<br>
							<s:textfield size="16" name="filtreNom" onkeydown="lancerRechercherOnKeyPress(event);" onchange="javascript:lancerRecherche();"></s:textfield>
						</td>
						<td align="left">Filtre description :<br>
							<s:textfield size="15" name="filtreDescription" onkeydown="lancerRechercherOnKeyPress(event);" onchange="javascript:lancerRecherche();"></s:textfield>
						</td>
						<td align="left">Filtre document :<br>
							<s:textfield size="25" name="filtreDocument" onkeydown="lancerRechercherOnKeyPress(event);" onchange="javascript:lancerRecherche();"></s:textfield>
						</td>
						<s:if test="#session.USER_DROITS.contains('APP_MOD') || #session.USER_DROITS.contains('APP_DEL')">
							<td></td>
						</s:if>
					</s:form>	
				</tr>

				<tr valign="middle">
					<td class="titreColonne" >
						<a href="#" onclick="changeSort('applicatif');">Nom</a>
						<s:if test="sens == 'asc' && sort == 'applicatif'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if> 
						<s:elseif test="sens == 'desc' && sort == 'applicatif'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</td>
					<td class="titreColonne" >
						<a href="#" onclick="changeSort('description');">Description</a>
						<s:if test="sens == 'asc' && sort == 'description'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if>
						<s:elseif test="sens == 'desc' && sort == 'description'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</td>
					<td class="titreColonne" >
						<a href="#" onclick="changeSort('documentation');">Documentation</a>
						<s:if test="sens == 'asc' && sort == 'documentation'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if>
						<s:elseif test="sens == 'desc' && sort == 'documentation'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</td>
					<s:if test="#session.USER_DROITS.contains('APP_MOD') || #session.USER_DROITS.contains('APP_DEL')">
						<td class="titreColonne" >Action</td>
					</s:if>
				</tr>
			</thead>
			<tbody>
				<s:if test="%{%{upMap == null} || upMap.isEmpty()}">
					<tr>
						<td class="emptyListText" colspan=<s:if test="#session.USER_DROITS.contains('APP_MOD') || #session.USER_DROITS.contains('APP_DEL')">"4"</s:if><s:else>"3"</s:else>>
							Aucune application trouv&eacute;e
						</td>
					</tr>
				</s:if>
				<s:else>
					<s:iterator value="listeApplications"><!-- id="mp" status="stuts" -->
						<tr>
							<td>&nbsp;&nbsp;<s:property value="applicatif" /></td>
							<td>&nbsp;&nbsp;
							<s:if test="%{description != null}">
								<s:property value='description.replaceAll("<", "&lt;").replaceAll("\n", "<br/>&nbsp;&nbsp;")' escape="false"/>
							</s:if>
							</td>
							<td>&nbsp;&nbsp;
							<s:if test="%{documentation != null}">
								<s:property value='documentation.replaceAll(";", "<br/>&nbsp;&nbsp;")' escape="false"/>
							</s:if>
							</td>
							<s:if test="#session.USER_DROITS.contains('APP_MOD') || #session.USER_DROITS.contains('APP_DEL')">
								<td align="center" valign="middle">
									<s:if test="#session.USER_DROITS.contains('APP_MOD')">
										<a href="#" onclick="javascript:modifier('<s:property value="id" />');">
											<img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
										</a>
									</s:if>
									<s:if test="#session.USER_DROITS.contains('APP_DEL')">
										<a href="#" onclick="javascript:supprimer('<s:property value="id" />');">
											<img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
										</a>
									</s:if>
								</td>
							</s:if>
						</tr>
					</s:iterator>
				</s:else>
			</tbody>
			<tfoot>
				<tr>
					<td colspan=<s:if test="#session.USER_DROITS.contains('APP_MOD') || #session.USER_DROITS.contains('APP_DEL')">"4"</s:if><s:else>"3"</s:else>>
						<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>