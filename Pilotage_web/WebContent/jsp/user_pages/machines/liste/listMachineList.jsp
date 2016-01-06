<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		//affichage des filtres
		function afficherFiltres() {
			if ($("#filtres").is(":hidden")) {
				reinitFilterValues();
				$("#filtres").slideDown('fast');
				$("#filtreString").slideUp('fast');
			} else {
				$("#filtres").slideUp('fast');
				$("#filtreString").slideDown('fast');
				reinitFilterValues();
			}
		}
	
		//action de suppression d'une machine
		function supprimer(id,nom){
			if(confirm("Voulez-vous vraiment supprimer la machine " + nom + " ?")){
				reinitFilterValues();
				changeData('selectRow', id);
				submitData('supprimerMachineListAction.action');
			}
		}
	
		function ajouter(){
			reinitFilterValues();
			submitData('showCreateMachineListAction.action');
		}
	
		//action de modification
		function modifier(id){
			reinitFilterValues();
			changeData('selectRow', id); 
			submitData('showModifyMachineListAction.action');
		}

		//action de détail
		function detail(id){
			reinitFilterValues();
			changeData('selectRow', id); 
			submitData('showDetailMachineListAction.action');
		}
		
	
		//reinitialisation des filtres comme à l'entrée de la page
		function reinitFilterValues() {
			document.getElementById("mainForm").filtreNom.value = '<s:property value="filtreNom" escape="false"/>';
			document.getElementById("mainForm").filtreIP.value = '<s:property value="filtreIP" escape="false"/>';
			document.getElementById("mainForm").filtreType.value = '<s:property value="filtreType" />';
			document.getElementById("mainForm").filtreSite.value = '<s:property value="filtreSite" />';
			document.getElementById("mainForm").filtreOs.value = '<s:property value="filtreOs" />';
			document.getElementById("mainForm").filtreDomaine.value = '<s:property value="filtreDomaine" />';
			document.getElementById("mainForm").filtreInterlocuteur.value = '<s:property value="filtreInterlocuteur" />';
			document.getElementById("mainForm").filtreEnvironnement.value = '<s:property value="filtreEnvironnement" />';
		}
	
		//action à la recherche avec filtre
		function lancerRecherche() {
			document.getElementById("mainForm").page.value = '1';
			document.getElementById("mainForm").submit();
		}

        //reinitialisation des filtres 
		function reinitialiserLesFiltres(){
			document.getElementById("mainForm").filtreNom.value = '';
			document.getElementById("mainForm").filtreIP.value = '';
			document.getElementById("mainForm").filtreType.value = '-1';
			document.getElementById("mainForm").filtreSite.value = '-1';
			document.getElementById("mainForm").filtreOs.value = '-1';
			document.getElementById("mainForm").filtreDomaine.value = '-1';
			document.getElementById("mainForm").filtreInterlocuteur.value = '-1';
			document.getElementById("mainForm").filtreEnvironnement.value = '-1';
			lancerRecherche();
			}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MAC_LST')" /></s:param>
		<s:param name="filtre" value="true"></s:param> 
	</s:include>
	
	<s:if test="filtreJson != null">
		<div id="filtreString" class="filtreString">
			<u><b>Filtres</b></u> <s:property value="%{filtreString}" /><br/>
			<a href="#" onclick="javascript:reinitialiserLesFiltres();">
				<img class="icone" alt="Réinitialiser les filtres" title="Réinitialiser les filtres" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" border="0" >
			</a>
		</div>
	</s:if>
	
	<s:form id="mainForm" action="showMachineListAction" theme="simple">
		<div id="filtres" class="filterBox">
			<table class="tabFilter3Col">
			<col width="20%"/>
			<col width="80%"/>
				<tr>
					<td align="left">IP : </td>
					<td align="left"><s:textfield id="filtreIP" name="filtreIP" maxlength="15" size="20" onchange="javascript:lancerRecherche();" /></td>
				</tr>
			</table>
		</div>
	
		<div class="contentTable" >
			<s:if test="#session.USER_DROITS.contains('MAC_ADD')">
				<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
			</s:if>
			
			<table class="dataTable" rules="all">
				<col width="15%"/>
				<col width="10%"/>
				<col width="10%"/>
				<col width="18%"/>
				<col width="10%"/>
				<col width="15%"/>
				<col width="15%"/>
				<col width="7%"/>
				<thead>
					<tr>
						<td colspan="8">
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
	
					<tr>
						<td align="left">Filtre nom :<br>
							<s:hidden id="selectRow" name="selectRow" value=""/>
							<s:hidden name="sort" id="sort" />
							<s:hidden name="sens" id="sens" />
							<s:hidden name="page" id="page" />
							<s:token></s:token>
							<s:hidden name="nrPerPage" id="nrPerPage"/>
							<s:hidden name="nrPages" value="%{pagination.nrPages}" />
							<s:hidden name="validForm" value="1" />
							<s:textfield id="filtreNom" name="filtreNom" maxlength="40" size="35" onchange="javascript:lancerRecherche();"/>
						</td>
						<td align="left">Filtre type :<br>
							<s:select cssStyle="width:100%" id="filtreType" name="filtreType" list="%{listMachineType}" listKey="id" listValue="type" headerKey="-1" headerValue="" onchange="javascript:lancerRecherche();"/>
						</td>
						<td align="left">Filtre os :<br>
							<s:select cssStyle="width:100%" id="filtreOs" name="filtreOs" list="%{listMachineOS}" listKey="id" listValue="Nom_OS" headerKey="-1" headerValue="" onchange="javascript:lancerRecherche();"/>
						</td>
						<td align="left">Filtre domaine :<br>
							<s:select cssStyle="width:100%" id="filtreDomaine" name="filtreDomaine" list="%{listMachineDomaine}" listKey="id" listValue="nomDomaine" headerKey="-1" headerValue="" onchange="javascript:lancerRecherche();"/>
						</td>
						<td align="left">Filtre site :<br>
							<s:select cssStyle="width:100%" id="filtreSite" name="filtreSite" list="%{listMachineSite}" listKey="id" listValue="site" headerKey="-1" headerValue="" onchange="javascript:lancerRecherche();"/>
						</td>
						<td align="left">Filtre service g&eacute;rant :<br>
							<s:select cssStyle="width:100%" id="filtreInterlocuteur" name="filtreInterlocuteur" list="%{listMachineInterlocuteur}" listKey="id" listValue="nomService" headerKey="-1" headerValue="" onchange="javascript:lancerRecherche();"/>
						</td>
						
						<td align="left">Filtre environnement :<br>
							<s:select cssStyle="width:100%" id="filtreEnvironnement" name="filtreEnvironnement" list="%{listMachineEnvironnement}" listKey="id" listValue="environnement" headerKey="-1" headerValue="" onchange="javascript:lancerRecherche();"/>
						</td>
						<td></td>
					</tr>
	
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
							<a href="#" onclick="changeSort('id_type');">Type</a>
							<s:if test="sens == 'asc' && sort == 'id_type'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if>
							<s:elseif test="sens == 'desc' && sort == 'id_type'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</td>
						<td class="titreColonne" >
							<a href="#" onclick="changeSort('id_os');">OS</a>
							<s:if test="sens == 'asc' && sort == 'id_os'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if>
							<s:elseif test="sens == 'desc' && sort == 'id_os'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</td>
						<td class="titreColonne" >
							<a href="#" onclick="changeSort('id_domaine');">Domaine</a>
							<s:if test="sens == 'asc' && sort == 'id_domaine'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if>
							<s:elseif test="sens == 'desc' && sort == 'id_domaine'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</td>
						<td class="titreColonne" >
							<a href="#" onclick="changeSort('id_site');">Site</a>
							<s:if test="sens == 'asc' && sort == 'id_site'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if>
							<s:elseif test="sens == 'desc' && sort == 'id_site'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</td>
						<td class="titreColonne" >
							<a href="#" onclick="changeSort('id_interlocuteur');">Service g&eacute;rant</a>
							<s:if test="sens == 'asc' && sort == 'id_interlocuteur'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if>
							<s:elseif test="sens == 'desc' && sort == 'id_interlocuteur'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</td>
						<td class="titreColonne" >
							<a href="#" onclick="changeSort('id_environnement');">Environnement</a>
							<s:if test="sens == 'asc' && sort == 'id_environnement'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if>
							<s:elseif test="sens == 'desc' && sort == 'id_environnement'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</td>
						<td class="titreColonne" >Action</td>
						
					</tr>
				</thead>
				<tbody>
					<s:if test="%{listMachine.size() == 0}">
						<tr>
							<td class="emptyListText" colspan="8">
								Aucune machine n'a été trouvée
							</td>
						</tr>
					</s:if>
		
					<s:else>
						<s:iterator value="listMachine">
							<tr>
								<td align="left">&nbsp;&nbsp;<s:property value="nom"/> </td>
								<td align="left">&nbsp;&nbsp;<s:property value="id_type.type"/> </td>
								<td align="left">&nbsp;&nbsp;<s:property value="id_os.Nom_OS"/> </td>
								<td align="left">&nbsp;&nbsp;<s:property value="id_domaine.nomDomaine"/> </td>
								<td align="left">&nbsp;&nbsp;<s:property value="id_site.site"/> </td>
								<td align="left">&nbsp;&nbsp;<s:property value="id_interlocuteur.nomService"/> </td>
								<td align="left">&nbsp;&nbsp;<s:property value="id_environnement.environnement"/> </td>
								<td align="center">
									<s:if test="#session.USER_DROITS.contains('MAC_MOD')">
										<s:a href="#" onclick="javascript:modifier('%{id}');"><img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png"/></s:a>
									</s:if>
									<s:a href="#" onclick="javascript:detail('%{id}');"><img class="icone" alt="Détail" title="Détail" src="<s:property value="#session.ENSEIGNE" />/img/detail-16.png"/></s:a>
									<s:if test="#session.USER_DROITS.contains('MAC_DEL')">
										<s:a href="#" onclick="javascript:supprimer('%{id}','%{nom}');"><img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png"/></s:a>
									</s:if>
								</td>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="8">
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</s:form>
</body>
</html>