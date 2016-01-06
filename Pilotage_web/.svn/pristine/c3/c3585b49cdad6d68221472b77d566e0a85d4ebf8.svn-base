<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">

		//reinitialisation des filtres comme à l'entrée de la page
		function reinitFilterValues() {
			document.getElementById("mainForm").filtreSite.value = '<s:property value="filtreSite" escape="false"/>';
			document.getElementById("mainForm").filtreAdresse.value = '<s:property value="filtreAdresse" escape="false"/>';
			document.getElementById("mainForm").filtreCP.value = '<s:property value="filtreCP" />';
		}

		function ajouter(){
			reinitFilterValues();
			submitData('showCreateMachineSiteAction.action');
		}

		function modifier(id){
			reinitFilterValues();
			changeData('selectRow', id);
			submitData('showModifyMachineSiteAction.action');
		}
	
		function supprimer(id){
			if(confirm("Voulez-vous vraiment supprimer ce site ?")){
				reinitFilterValues();
				changeData('selectRow', id);
				submitData('supprimerMachineSiteAction.action');
			}
		}

		//action à la recherche avec filtre
		function lancerRecherche() {
			var cp = document.getElementById("mainForm").filtreCP.value;
			if(cp != "" && (isNaN(parseInt(cp)) || cp.length != 5)){
				alert('Veuillez entrer un nombre à 5 chiffres pour le code postal');
				return;
			}
			document.getElementById("mainForm").submit();
		}

        //reinitialisation des filtres 
		function reinitialiserLesFiltres(){
			document.getElementById("mainForm").filtreSite.value = '';
			document.getElementById("mainForm").filtreAdresse.value = '';
			document.getElementById("mainForm").filtreCP.value = '';
			lancerRecherche();
			}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MAC_SITE')" /></s:param>
	</s:include>
	
	<s:if test="filtreJson != null">
		<div id="filtreString" class="filtreString">
			<u><b>Filtres</b></u> <s:property value="%{filtreString}" /><br/>
			<a href="#" onclick="javascript:reinitialiserLesFiltres();">
				<img alt="Réinitialiser les filtres" title="Réinitialiser les filtres" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" border="0" >
			</a>
		</div>
	</s:if>

	<div class="contentTable" style="width: 70%">
		<s:if test="#session.USER_DROITS.contains('MAC_SIT_ADD')">
			<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
		</s:if>
		
		<table class="dataTable" rules="all">
			<col width="25%"/>
			<col width="40%"/>
			<col width="25%"/>
			<s:if test="#session.USER_DROITS.contains('MAC_SIT_MOD') || #session.USER_DROITS.contains('MAC_SIT_DEL')">
				<col width="10%"/>
			</s:if>
			<thead>
				
				<s:form id="mainForm" action="showMachineSiteAction" theme="simple">
				<tr>
					<td align="left">Filtre site :<br>
						<s:hidden id="selectRow" name="selectRow" value=""/>
						<s:hidden name="sort" id="sort" />
						<s:hidden name="sens" id="sens" />
						<s:hidden name="validForm" value="1" />
						<s:token></s:token>

						<s:textfield name="filtreSite" onchange="javascript:lancerRecherche();"></s:textfield>
					</td>
					<td align="left">Filtre adresse :<br>
						<s:textfield name="filtreAdresse" size="25" onchange="javascript:lancerRecherche();"></s:textfield>
					</td>
					<td align="left">Filtre code postal :<br>
						<s:textfield name="filtreCP" maxlength="5" size="20" onchange="javascript:lancerRecherche();"></s:textfield>
					</td>
					<s:if test="#session.USER_DROITS.contains('MAC_SIT_MOD') || #session.USER_DROITS.contains('MAC_SIT_DEL')">
						<td></td>
					</s:if>
				</tr>
				</s:form>
				
				<tr>
					<td class="titreColonne" >
						<a href="#" onclick="changeSort('site');">Site</a>
						<s:if test="sens == 'asc' && sort == 'site'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if> 
						<s:elseif test="sens == 'desc' && sort == 'site'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>				
					</td>
					<td class="titreColonne" >
						<a href="#" onclick="changeSort('adresse1');">Adresse</a>
						<s:if test="sens == 'asc' && sort == 'adresse1'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if>
						<s:elseif test="sens == 'desc' && sort == 'adresse1'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</td>
					<td class="titreColonne" >
						<a href="#" onclick="changeSort('cp');">Code Postal</a>
						<s:if test="sens == 'asc' && sort == 'cp'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if>
						<s:elseif test="sens == 'desc' && sort == 'cp'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</td>
					<s:if test="#session.USER_DROITS.contains('MAC_SIT_MOD') || #session.USER_DROITS.contains('MAC_SIT_DEL')">
						<td class="titreColonne" >Action</td>
					</s:if>
				</tr>
			</thead>
			<tbody>
				<s:if test="listMachineSite.isEmpty()">
					<tr>
						<td class="emptyListText" colspan=<s:if test="#session.USER_DROITS.contains('MAC_SIT_MOD') || #session.USER_DROITS.contains('MAC_SIT_DEL')">"4"</s:if><s:else>"3"</s:else>>
							Aucun site trouvé
						</td>
					</tr>
				</s:if>
				<s:else>
					<s:iterator value="listMachineSite">
						<tr>
							<td align="left">&nbsp;&nbsp;<s:property value="site"/> </td>
							<td align="left">
								&nbsp;&nbsp;<s:property value="adresse1"/>
								<s:if test="adresse2 != ''">
									-&nbsp;<s:property value="adresse2"/>
								</s:if>
							</td>
							<td align="center"><s:property value="cp"/></td>
							<s:if test="#session.USER_DROITS.contains('MAC_SIT_MOD') || #session.USER_DROITS.contains('MAC_SIT_DEL')">
								<td align="center" valign="middle">
									<s:if test="#session.USER_DROITS.contains('MAC_SIT_MOD')">
										<s:a href="#" onclick="javascript:modifier('%{id}');">
											<img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
										</s:a>
									</s:if>
									<s:if test="#session.USER_DROITS.contains('MAC_SIT_DEL')">
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
		</table>
	</div>
</body>
</html>
				