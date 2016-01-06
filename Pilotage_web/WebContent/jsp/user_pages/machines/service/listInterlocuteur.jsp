<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">

		//action de suppression
		function supprimer(id,interlocuteur){
			if(confirm("Voulez-vous vraiment supprimer ce service gérant ?")){
				reinitFilterValues();
				changeData('selectRow', id);
				submitData('supprimerMachineInterlocuteurAction.action');
			}
		}

		//action d'ajout
		function ajouter(){
			reinitFilterValues();
			submitData('showCreateMachineInterlocuteurAction.action');
		}

		//action de modification
		function modifier(id){
			reinitFilterValues();
			changeData('selectRow', id); 
			submitData('showModifyMachineInterlocuteurAction.action');
		}
	
		//reinitialisation des filtres comme à l'entrée de la page
		function reinitFilterValues() {
			document.getElementById("mainForm").filtreNom.value = '<s:property value="filtreNom" escape="false"/>';
			document.getElementById("mainForm").filtreNomComplet.value = '<s:property value="filtreNomComplet" escape="false"/>';
			document.getElementById("mainForm").filtreResponsable.value = '<s:property value="filtreResponsable" />';
			document.getElementById("mainForm").filtreBAI.value = '<s:property value="filtreBAI" escape="false"/>';
		}
	
		//action à la recherche avec filtre
		function lancerRecherche() {
			document.getElementById("mainForm").page.value = '1';
			document.getElementById("mainForm").submit();
		}

		   //reinitialisation des filtres 
		function reinitialiserLesFiltres(){
			document.getElementById("mainForm").filtreNom.value = '';
			document.getElementById("mainForm").filtreNomComplet.value = '';
			document.getElementById("mainForm").filtreResponsable.value = '-1';
			document.getElementById("mainForm").filtreBAI.value = '';
			lancerRecherche();
			}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MAC_SGE')" /></s:param>
	</s:include>
	<s:if test="filtreJson != null">
		<div id="filtreString" class="filtreString">
			<u><b>Filtres</b></u> <s:property value="%{filtreString}" /><br/>
			<a href="#" onclick="javascript:reinitialiserLesFiltres();">
				<img alt="Réinitialiser les filtres" title="Réinitialiser les filtres" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" border="0" />
			</a>
		</div>
	</s:if>
	<div class="contentTable" style="width:80%">
		<s:if test="#session.USER_DROITS.contains('MAC_SRV_ADD')">
			<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
		</s:if>

		<table class="dataTable" rules="all" border="1">
			<col width="18%"/>
			<col width="18%"/>
			<col width="18%"/>
			<col width="38%"/>
			<s:if test="#session.USER_DROITS.contains('MAC_SRV_MOD') || #session.USER_DROITS.contains('MAC_SRV_DEL')">
				<col width="8%"/>
			</s:if>
			<thead>
				<tr>
					<td colspan=<s:if test="#session.USER_DROITS.contains('MAC_SRV_MOD') || #session.USER_DROITS.contains('MAC_SRV_DEL')">"5"</s:if><s:else>"4"</s:else>>
						<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
					</td>
				</tr>
				<s:form id="mainForm" action="showMachineInterlocuteurAction" theme="simple">
				<tr>
					<td align="left">Filtre nom service:<br/>
						<s:hidden id="selectRow" name="selectRow" value=""/>
						<s:hidden name="sort" id="sort" />
						<s:hidden name="sens" id="sens" />
						<s:hidden name="page" id="page" />
						<s:token></s:token>
						<s:hidden name="nrPerPage" id="nrPerPage"/>
						<s:hidden name="nrPages" value="%{pagination.nrPages}" />
						<s:hidden name="validForm" value="1" />
						<s:textfield name="filtreNom" onchange="javascript:lancerRecherche();"></s:textfield>
					</td>
					<td align="left">Filtre nom complet :<br/>
						<s:textfield name="filtreNomComplet" onchange="javascript:lancerRecherche();"></s:textfield>
					</td>
					<td align="left">Filtre responsable :<br/>
						<s:select list="%{listUsers}" listKey="id" listValue="nom + ' ' + prenom" name="filtreResponsable" headerKey="-1" headerValue="" onchange="javascript:lancerRecherche();"></s:select>
					</td>
					<td align="left">Filtre BAL service :<br/>
						<s:textfield name="filtreBAI" onchange="javascript:lancerRecherche();"></s:textfield>
					</td>
					<s:if test="#session.USER_DROITS.contains('MAC_SRV_MOD') || #session.USER_DROITS.contains('MAC_SRV_DEL')">
						<td></td>
					</s:if>
				</tr>
				</s:form>
				<tr valign="middle">
					<td class="titreColonne" >
						<a href="#" onclick="changeSort('nomService');">Nom Service</a>
						<s:if test="sens == 'asc' && sort == 'nomService'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if> 
						<s:elseif test="sens == 'desc' && sort == 'nomService'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>	
					</td>
					<td class="titreColonne" >
						<a href="#" onclick="changeSort('nomComplet');">Nom Complet</a>
						<s:if test="sens == 'asc' && sort == 'nomComplet'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if> 
						<s:elseif test="sens == 'desc' && sort == 'nomComplet'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>		
					</td>
					<td class="titreColonne" >
						<a href="#" onclick="changeSort('idResponsable');">Responsable</a>
						<s:if test="sens == 'asc' && sort == 'idResponsable'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if>
						<s:elseif test="sens == 'desc' && sort == 'idResponsable'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</td>
					<td class="titreColonne" >
						<a href="#" onclick="changeSort('balService');">BAL Service</a>
						<s:if test="sens == 'asc' && sort == 'balService'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if>
						<s:elseif test="sens == 'desc' && sort == 'balService'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</td>
					<s:if test="#session.USER_DROITS.contains('MAC_SRV_MOD') || #session.USER_DROITS.contains('MAC_SRV_DEL')">
						<td class="titreColonne" >Action</td>
					</s:if>
				</tr>
			</thead>
			<tbody>
				<s:if test="listInterlocuteur.isEmpty()">
					<tr>
						<td class="emptyListText" colspan=<s:if test="#session.USER_DROITS.contains('MAC_SRV_MOD') || #session.USER_DROITS.contains('MAC_SRV_DEL')">"5"</s:if><s:else>"4"</s:else>>
							Aucun service gérant trouvé
						</td>
					</tr>
				</s:if>
				<s:else>
					<s:iterator value="listInterlocuteur">
						<tr>
							<td>&nbsp;&nbsp;<s:property value="nomService" /></td>
							<td>&nbsp;&nbsp;<s:property value="nomComplet" /></td>
							<td>&nbsp;&nbsp;<s:property value="idResponsable.nom" /> <s:property value="idResponsable.prenom" /></td>
							<td>
								&nbsp;&nbsp;
								<a href="mailto:<s:property value="balService" />" class="mailto">
									<s:property value="balService" />
								</a>
							</td>
							<s:if test="#session.USER_DROITS.contains('MAC_SRV_MOD') || #session.USER_DROITS.contains('MAC_SRV_DEL')">
								<td align="center" valign="middle">
									<s:if test="#session.USER_DROITS.contains('MAC_SRV_MOD')">
										<s:a href="#" onclick="javascript:modifier('%{id}');">
											<img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
										</s:a>
									</s:if>
									<s:if test="#session.USER_DROITS.contains('MAC_SRV_DEL')">
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
			<tfoot>
				<tr>
					<td colspan=<s:if test="#session.USER_DROITS.contains('MAC_SRV_MOD') || #session.USER_DROITS.contains('MAC_SRV_DEL')">"5"</s:if><s:else>"4"</s:else>>
						<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>