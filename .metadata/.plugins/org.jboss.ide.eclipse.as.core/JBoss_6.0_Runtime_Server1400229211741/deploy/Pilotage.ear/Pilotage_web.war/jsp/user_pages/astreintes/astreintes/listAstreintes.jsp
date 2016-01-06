<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		//action de suppression d'un utilisateur
		function supprimer(id) {
			if (confirm("Voulez-vous vraiment supprimer cette astreinte ?")) {
				changeData('selectRow', id); 
				submitData('supprimerAstreinteAction.action');
			}
		}

		//action de modification
		function modifier(id){
			changeData('selectRow', id); 
			submitData('redirectModifyAstreinteAction.action');
		}

		//action d'ajout
		function ajouter(){
			submitData('redirectCreateAstreinteAction.action');
		}
	
		//reinitialisation des filtres comme à l'entrée de la page
		function reinitFilterValues() {
			document.getElementById("mainForm").filtreNom.value = '<s:property value="filtreNom" escape="false"/>';
			document.getElementById("mainForm").filtreTel1.value = '<s:property value="filtreTel1" escape="false"/>';
			document.getElementById("mainForm").filtreType.value = '<s:property value="filtreType" />';
			document.getElementById("mainForm").filtrePrenom.value = '<s:property value="filtrePrenom" escape="false"/>';
			document.getElementById("mainForm").filtreTel2.value = '<s:property value="filtreTel2" escape="false"/>';
		}
	
		//action à la recherche avec filtre
		function lancerRecherche() {
			document.getElementById("mainForm").page.value = '1';
			document.getElementById("mainForm").submit();
		}
        //reinitialisation des filtres 
		function reinitialiserLesFiltres(){
			document.getElementById("mainForm").filtreNom.value = '';
			document.getElementById("mainForm").filtreTel1.value = '';
			document.getElementById("mainForm").filtreType.value = '-1';
			document.getElementById("mainForm").filtrePrenom.value = '';
			document.getElementById("mainForm").filtreTel2.value = '';
			lancerRecherche();
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('AST_LST')" /></s:param>
	</s:include>
	
	<s:if test="filtreJson != null">
		<div id="filtreString" class="filtreString">
			<u><b>Filtres</b></u> <s:property value="%{filtreString}" /><br/>
			<a href="#" onclick="javascript:reinitialiserLesFiltres();">
				<img class="icone" alt="Réinitialiser les filtres" title="Réinitialiser les filtres" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" />
			</a>
		</div>
	</s:if>
	
	<div class="contentTable">
		<s:if test="#session.USER_DROITS.contains('AST_ADD')">
			<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
		</s:if>
		
		<table class="dataTable" rules="all" border="1">
			<col width="25%"/>
			<col width="23%"/>
			<col width="15%"/>
			<col width="15%"/>
			<col width="12%"/>
			<s:if test="#session.USER_DROITS.contains('AST_MOD') || #session.USER_DROITS.contains('AST_DEL')">
				<col width="10%"/>
			</s:if>
			<thead>
				<tr>
					<td colspan=<s:if test="#session.USER_DROITS.contains('AST_MOD') || #session.USER_DROITS.contains('AST_DEL')">"6"</s:if><s:else>"5"</s:else>>
						<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
					</td>
				</tr>
				
				<s:form id="mainForm" action="showAstreintesAction" theme="simple">	
				<tr>
					<td align="left">Filtre nom :<br>
						<s:hidden id="selectRow" name="selectRow" value=""/>
						<s:hidden name="sort" id="sort" />
						<s:hidden name="sens" id="sens" />
						<s:hidden name="page" id="page" />
						<s:hidden name="nrPerPage" id="nrPerPage"/>
						<s:hidden name="nrPages" value="%{pagination.nrPages}" />
						<s:hidden name="validForm" value="1" />
						<s:token></s:token>
						<s:textfield name="filtreNom" maxlength="20" size="25" onchange="javascript:lancerRecherche();"></s:textfield></td>
					<td align="left">Filtre pr&eacute;nom :<br>
					<s:textfield name="filtrePrenom" maxlength="20" size="25" onchange="javascript:lancerRecherche();"></s:textfield></td>
					<td align="left">Filtre t&eacute;l&eacute;phone 1 :<br>
					<s:textfield name="filtreTel1" maxlength="20" size="15" onchange="javascript:lancerRecherche();"></s:textfield></td>
					<td align="left">Filtre t&eacute;l&eacute;phone 2 :<br>
					<s:textfield name="filtreTel2" maxlength="20" size="15" onchange="javascript:lancerRecherche();"></s:textfield></td>
					<td align="left">Filtre type :<br>
					<s:select list="%{aTypes}" listKey="id" listValue="type" name="filtreType" headerKey="-1" headerValue="" onchange="javascript:lancerRecherche();"></s:select></td>
					<s:if test="#session.USER_DROITS.contains('AST_MOD') || #session.USER_DROITS.contains('AST_DEL')">
						<td>&nbsp;</td>
					</s:if>
				</tr>
				</s:form>
				
				<tr valign="middle">
					<td class="titreColonne">
						<a href="#" onclick="changeSort('nom');">Nom</a>
						<s:if test="sens == 'asc' && sort == 'nom'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if> 
						<s:elseif test="sens == 'desc' && sort == 'nom'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
						
					
					<td class="titreColonne" >
						<a href="#" onclick="changeSort('prenom');">Prenom</a>
						<s:if test="sens == 'asc' && sort == 'prenom'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if>
						<s:elseif test="sens == 'desc' && sort == 'prenom'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</td>
					<td class="titreColonne" >
						<a href="#" onclick="changeSort('tel1');">Téléphone 1</a>
						<s:if test="sens == 'asc' && sort == 'tel1'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if>
						<s:elseif test="sens == 'desc' && sort == 'tel1'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</td>
					<td class="titreColonne" >
						<a href="#" onclick="changeSort('tel2');">Téléphone 2</a>
						<s:if test="sens == 'asc' && sort == 'tel2'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if>
						<s:elseif test="sens == 'desc' && sort == 'tel2'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</td>
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
					<s:if test="#session.USER_DROITS.contains('AST_MOD') || #session.USER_DROITS.contains('AST_DEL')">
						<td class="titreColonne" >Actions</td>
					</s:if>
				</tr>
			</thead>
			<tbody>
				<s:if test="astreintes.isEmpty()">
					<tr>
						<td class="emptyListText" colspan=<s:if test="#session.USER_DROITS.contains('AST_MOD') || #session.USER_DROITS.contains('AST_DEL')">"6"</s:if><s:else>"5"</s:else>>
							Aucune astreinte trouvé
						</td>
					</tr>
				</s:if>
				<s:else>
					<s:iterator value="astreintes" id="astreintes" status="stuts">
						<tr>
							<td>&nbsp;&nbsp;<s:property value="#astreintes.nom" /></td>
							<td>&nbsp;&nbsp;<s:property value="#astreintes.prenom" /></td>
							<td>&nbsp;&nbsp;<s:property value="#astreintes.tel1" /></td>
							<td>&nbsp;&nbsp;<s:property value="#astreintes.tel2" /></td>
							<td align="center"><s:property value="#astreintes.type.type" /></td>
							<s:if test="#session.USER_DROITS.contains('AST_MOD') || #session.USER_DROITS.contains('AST_DEL')">
								<td align="center">
									<s:if test="#session.USER_DROITS.contains('AST_MOD')">
										<s:a href="#" onclick="javascript:modifier('%{#astreintes.id}');">
											<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
										</s:a>
									</s:if>
									<s:if test="#session.USER_DROITS.contains('AST_DEL')">
										<s:a href="#" onclick="javascript:supprimer('%{#astreintes.id}');">
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
					<td colspan=<s:if test="#session.USER_DROITS.contains('AST_MOD') || #session.USER_DROITS.contains('AST_DEL')">"6"</s:if><s:else>"5"</s:else>>
						<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>