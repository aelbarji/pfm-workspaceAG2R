<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		//action de suppression d'une machine
		function supprimer(id,origine){
			if(confirm("Voulez-vous vraiment supprimer la consigne bbr d'origine " + origine + " ?")){
				reinitFilterValues();
				changeData('selectRow', id);
				submitData('supprimerConsigneBbrAction.action');
			}
		}
	
		function ajouter(){
			reinitFilterValues();
			submitData('showCreateConsigneBbrAction.action');
		}
	
		//action de modification
		function modifier(id){
			reinitFilterValues();
			changeData('selectRow', id); 
			submitData('showModifyConsigneBbrAction.action');
		}
	
		//reinitialisation des filtres comme à l'entrée de la page
		function reinitFilterValues() {
			document.getElementById("mainForm").filtreOrigine.value = '<s:property value="filtreOrigine" escape="false"/>';
			document.getElementById("mainForm").filtreComposant.value = '<s:property value="filtreComposant" escape="false"/>';
			document.getElementById("mainForm").filtreType.value = '<s:property value="filtreType" escape="false"/>';
			document.getElementById("mainForm").filtreLocalisation.value = '<s:property value="filtreLocalisation" />';
		}
	
		//action à la recherche avec filtre
		function lancerRecherche() {
			document.getElementById("mainForm").page.value = '1';
			document.getElementById("mainForm").submit();
		}

        //reinitialisation des filtres 
		function reinitialiserLesFiltres(){
			document.getElementById("mainForm").filtreOrigine.value = '';
			document.getElementById("mainForm").filtreComposant.value = '';
			document.getElementById("mainForm").filtreType.value = '';
			document.getElementById("mainForm").filtreLocalisation.value = '';
			lancerRecherche();
			}
		//
		function downloadFile(id) {
			changeData('consigneFichierID',id);
			document.getElementById("downloadForm").submit();
		}
		
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('BBR_LST')" /></s:param>
	</s:include>
	
	<s:if test="filtreString != '' && filtreString != null">
		<div id="filtreString" class="filtreString">
			<u><b>Filtres</b></u> <s:property value="%{filtreString}" /><br/>
			<a href="#" onclick="javascript:reinitialiserLesFiltres();">
				<img class="icone" alt="Réinitialiser les filtres" title="Réinitialiser les filtres" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" />
			</a>
		</div>
	</s:if>
	
	<s:form id="mainForm" action="showConsigneBbrAction" theme="simple">
		<s:hidden id="selectRow" name="selectRow" value=""/>
		<s:hidden name="sort" id="sort" />
		<s:hidden name="sens" id="sens" />
		<s:hidden name="page" id="page" />
		<s:token></s:token>
		<s:hidden name="nrPerPage" id="nrPerPage"/>
		<s:hidden name="nrPages" value="%{pagination.nrPages}" />
	
		<div class="contentTable" >
			<s:if test="#session.USER_DROITS.contains('CSG_BBR_ADD')">
				<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
			</s:if>

			<table class="dataTable" rules="all">
				<col width="12%"/>
				<col width="20%"/>
				<col width="10%"/>
				<col width="50%"/>
				<s:if test="#session.USER_DROITS.contains('CSG_BBR_MOD') || #session.USER_DROITS.contains('CSG_BBR_DEL')">
					<col width="8%" />
				</s:if>
				<thead>
					<tr>
						<td colspan=<s:if test="#session.USER_DROITS.contains('CSG_BBR_MOD') || #session.USER_DROITS.contains('CSG_BBR_DEL')">"5"</s:if><s:else>"4"</s:else>>
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
					<tr>
						<td align="left">Filtre BBrOrigine :<br>
							<s:textfield id="filtreOrigine" name="filtreOrigine" maxlength="40" size="15" onchange="javascript:lancerRecherche();"/>
						</td>
						<td align="left">Filtre BBrComposant :<br>
							<s:textfield id="filtreComposant" name="filtreComposant" maxlength="40" size="20" onchange="javascript:lancerRecherche();"/>
						</td>
						<td align="left">Filtre BBrType :<br>
							<s:textfield id="filtreType" name="filtreType" maxlength="40" size="10" onchange="javascript:lancerRecherche();"/>
						</td>
						<td align="left">Filtre Localisation :<br>
							<s:textfield id="filtreLocalisation" name="filtreLocalisation" maxlength="100" size="50" onchange="javascript:lancerRecherche();"/>
						</td>
						<s:if test="#session.USER_DROITS.contains('CSG_BBR_MOD') || #session.USER_DROITS.contains('CSG_BBR_DEL')">
							<td>&nbsp;</td>
						</s:if>
					</tr>
	
					<tr valign="middle">
						<td class="titreColonne" >
							<a href="#" onclick="changeSort('bbrorigine');">BBrOrigine</a>
							<s:if test="sens == 'asc' && sort == 'bbrorigine'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if> 
							<s:elseif test="sens == 'desc' && sort == 'bbrorigine'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
							
						</td>
						<td class="titreColonne" >
							<a href="#" onclick="changeSort('bbrcomposant');">BBrComposant</a>
							<s:if test="sens == 'asc' && sort == 'bbrcomposant'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if>
							<s:elseif test="sens == 'desc' && sort == 'bbrcomposant'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</td>
						<td class="titreColonne" >
							<a href="#" onclick="changeSort('bbrtype');">BBrType</a>
							<s:if test="sens == 'asc' && sort == 'bbrtype'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if>
							<s:elseif test="sens == 'desc' && sort == 'bbrtype'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</td>
						<td class="titreColonne" >
							<a href="#" onclick="changeSort('localisation');">Localisation</a>
							<s:if test="sens == 'asc' && sort == 'localisation'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if>
							<s:elseif test="sens == 'desc' && sort == 'localisation'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</td>
						<s:if test="#session.USER_DROITS.contains('CSG_BBR_MOD') || #session.USER_DROITS.contains('CSG_BBR_DEL')">
							<td class="titreColonne" >Action</td>
						</s:if>
					</tr>
				</thead>
				<tbody>
					<s:if test="%{listConsigneBbr.size() == 0}">
						<tr>
							<td class="emptyListText" colspan=<s:if test="#session.USER_DROITS.contains('CSG_BBR_MOD') || #session.USER_DROITS.contains('CSG_BBR_DEL')">"5"</s:if><s:else>"4"</s:else>>
								Aucune consigne bbr n'a été trouvée
							</td>
						</tr>
					</s:if>
		
					<s:else>
						<s:iterator value="listConsigneBbr">
							<tr>
								<td align="left">&nbsp;&nbsp;<s:property value="bbrorigine"/> </td>
								<td align="left">&nbsp;&nbsp;<s:property value="bbrcomposant"/> </td>
								<td align="left">&nbsp;&nbsp;<s:property value="bbrtype"/> </td>
								<td align="left">&nbsp;&nbsp;<s:property value="localisation"/> </td>
								<s:if test="#session.USER_DROITS.contains('CSG_BBR_MOD') || #session.USER_DROITS.contains('CSG_BBR_DEL')">
									<td align="center">
										<s:if test="#session.USER_DROITS.contains('CSG_BBR_MOD')">
											<s:a href="#" onclick="javascript:modifier('%{id}','%{bbrorigine}');"><img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png"/></s:a>
										</s:if>
										<s:if test="#session.USER_DROITS.contains('CSG_BBR_DEL')">
											<s:a href="#" onclick="javascript:supprimer('%{id}','%{bbrorigine}');"><img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png"/></s:a>
										</s:if>
									</td>
								</s:if>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
				<tfoot>
					<tr>
						<td colspan=<s:if test="#session.USER_DROITS.contains('CSG_BBR_MOD') || #session.USER_DROITS.contains('CSG_BBR_DEL')">"5"</s:if><s:else>"4"</s:else>>
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</s:form>
	<s:form id="downloadForm" theme="simple" action="downloadConsigneFichierAction">
		<s:hidden id="consigneFichierID" name="fichierID"/>
	</s:form>
</body>
</html>