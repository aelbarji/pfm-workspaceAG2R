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
			if (confirm("Voulez-vous vraiment supprimer cet utilisateur ?")) {
				reinitFilterValues();
				changeData('selectRow', id); 
				submitData('deleteUserAction.action');
			}
		}

		function ajouter(){
			reinitFilterValues();
			submitData('showCreateUserAction.action');
		}

		//action de modification
		function modifier(id){
			reinitFilterValues();
			changeData('selectRow', id); 
			submitData('showModifyUserAction.action');
		}
	
		//reinitialisation des filtres comme à l'entrée de la page
		function reinitFilterValues() {
			document.getElementById("mainForm").filtreNom.value = '<s:property value="filtreNom" escape="false"/>';
			document.getElementById("mainForm").filtreLogin.value = '<s:property value="filtreLogin" escape="false"/>';
			document.getElementById("mainForm").filtreProfil.value = '<s:property value="filtreProfil"/>';
			document.getElementById("mainForm").filtrePrenom.value = '<s:property value="filtrePrenom" escape="false"/>';
			document.getElementById("mainForm").filtreEmail.value = '<s:property value="filtreEmail" escape="false"/>';
		}
	
		//action à la recherche avec filtre
		function lancerRecherche() {
			document.getElementById("mainForm").page.value = '1';
			document.getElementById("mainForm").submit();
		}

	     //reinitialisation des filtres 
		function reinitialiserLesFiltres(){
			document.getElementById("mainForm").filtreNom.value = '';
			document.getElementById("mainForm").filtreLogin.value = '';
			document.getElementById("mainForm").filtreProfil.value = '-1';
			document.getElementById("mainForm").filtrePrenom.value = '';
			document.getElementById("mainForm").filtreEmail.value = '';
			lancerRecherche();
			}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('USR_LST')" /></s:param>
	</s:include>
	
	<s:if test="filtreJson != null">
		<div id="filtreString" class="filtreString">
			<u><b>Filtres</b></u> <s:property value="%{filtreString}" /><br/>
			<a href="#" onclick="javascript:reinitialiserLesFiltres();">
				<img alt="Réinitialiser les filtres" title="Réinitialiser les filtres" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" border="0" >
			</a>
		</div>
	</s:if>

	<div class="contentTable" >
	
		<s:if test="#session.USER_DROITS.contains('USR_ADD')">
			<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
		</s:if>
		
		<table class="dataTable" rules="all" border="1">
			<col width="17%"/>
			<col width="17%"/>
			<col width="17%"/>
			<col width="24%"/>
			<col width="15%"/>
			<s:if test="#session.USER_DROITS.contains('USR_MOD') || #session.USER_DROITS.contains('USR_DEL')">
				<col width="10%"/>
			</s:if>
			<thead>
				<tr>
					<td colspan=<s:if test="#session.USER_DROITS.contains('USR_MOD') || #session.USER_DROITS.contains('USR_DEL')">"6"</s:if><s:else>"5"</s:else>>
						<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
					</td>
				</tr>
				
				<s:form id="mainForm" action="listAllUsersAction" theme="simple">
				<tr>
					<td align="left">Filtre nom :<br>
						<s:token></s:token>
						<s:hidden id="selectRow" name="selectRow" value=""/>
						<s:hidden name="sort" id="sort" />
						<s:hidden name="sens" id="sens" />
						<s:hidden name="page" id="page" />
						<s:hidden name="nrPerPage" id="nrPerPage"/>
						<s:hidden name="nrPages" value="%{pagination.nrPages}" />
						<s:hidden name="validForm" value="1" />
						<s:textfield size="16" name="filtreNom" onkeydown="lancerRechercherOnKeyPress(event);" onchange="javascript:lancerRecherche();"></s:textfield></td>
					<td align="left">Filtre pr&eacute;nom :<br>
					<s:textfield size="16" name="filtrePrenom" onkeydown="lancerRechercherOnKeyPress(event);" onchange="javascript:lancerRecherche();"></s:textfield></td>
					<td align="left">Filtre login :<br>
					<s:textfield size="15" name="filtreLogin" onkeydown="lancerRechercherOnKeyPress(event);" onchange="javascript:lancerRecherche();"></s:textfield></td>
					<td align="left">Filtre e-mail :<br>
					<s:textfield size="25" name="filtreEmail" onkeydown="lancerRechercherOnKeyPress(event);" onchange="javascript:lancerRecherche();"></s:textfield></td>
					<td align="left">Filtre profil :<br>
					<s:select list="%{listProfil}" listKey="id" listValue="libelle" name="filtreProfil" headerKey="-1" headerValue="" onkeydown="lancerRechercherOnKeyPress(event);" onchange="javascript:lancerRecherche();"></s:select></td>
					<s:if test="#session.USER_DROITS.contains('USR_MOD') || #session.USER_DROITS.contains('USR_DEL')">
						<td></td>
					</s:if>		
				</tr>
				</s:form>
				
				<tr valign="middle">
					<td class="titreColonne" >
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
					</td>
					
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
						<a href="#" onclick="changeSort('login');">Login</a>
						<s:if test="sens == 'asc' && sort == 'login'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if>
						<s:elseif test="sens == 'desc' && sort == 'login'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</td>
					<td class="titreColonne" >
						<a href="#" onclick="changeSort('email');">E-mail</a>
						<s:if test="sens == 'asc' && sort == 'email'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if>
						<s:elseif test="sens == 'desc' && sort == 'email'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</td>
					<td class="titreColonne" >
						<a href="#" onclick="changeSort('statut');">Profil</a>
						<s:if test="sens == 'asc' && sort == 'statut'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if>
						<s:elseif test="sens == 'desc' && sort == 'statut'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</td>
					<s:if test="#session.USER_DROITS.contains('USR_MOD') || #session.USER_DROITS.contains('USR_DEL')">
						<td class="titreColonne" >Action</td>
					</s:if>
				</tr>
			</thead>
			<tbody>
				<s:if test="upMap.isEmpty()">
					<tr>
						<td class="emptyListText" colspan=<s:if test="#session.USER_DROITS.contains('USR_MOD') || #session.USER_DROITS.contains('USR_DEL')">"6"</s:if><s:else>"5"</s:else>>
							Aucun utilisateur trouvé
						</td>
					</tr>
				</s:if>
				<s:else>
					<s:iterator value="upMap" id="mp" status="stuts">
						<tr>
							<td>&nbsp;&nbsp;<s:property value="#mp.key.nom" /></td>
							<td>&nbsp;&nbsp;<s:property value="#mp.key.prenom" /></td>
							<td>&nbsp;&nbsp;<s:property value="#mp.key.login" /></td>
							<td>&nbsp;&nbsp;<s:property value="#mp.key.email" /></td>
							<td align="center"><s:property value="#mp.value.libelle" /></td>
							<s:if test="#session.USER_DROITS.contains('USR_MOD') || #session.USER_DROITS.contains('USR_DEL')">
								<td align="center" valign="middle">
									<s:if test="#session.USER_DROITS.contains('USR_MOD')">
										<s:a href="#" onclick="javascript:modifier('%{#mp.key.id}');">
											<img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
										</s:a>
									</s:if>
									<s:if test="#session.USER_DROITS.contains('USR_DEL')">
										<s:a href="#" onclick="javascript:supprimer('%{#mp.key.id}');">
											<img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
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
					<td colspan=<s:if test="#session.USER_DROITS.contains('USR_MOD') || #session.USER_DROITS.contains('USR_DEL')">"6"</s:if><s:else>"5"</s:else>>
						<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>