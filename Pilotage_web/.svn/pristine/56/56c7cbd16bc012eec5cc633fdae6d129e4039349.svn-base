<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">

		//action de suppression d'un service météo
		function supprimer(id) {
			if (confirm("Voulez-vous vraiment supprimer ce service météo?")) {
				changeData('selectRow', id); 
				submitData('deleteServiceMeteoAction.action');
			}
		}

		//action de modification
		function modifier(id){
			changeData('selectRow', id); 
			submitData('redirectModifyServiceMeteoAction.action');
		}

		//action d'ajout
		function ajouter(){
			var mainForm = document.getElementById("mainForm");
			if (mainForm.libelle.value.replace(/^\s+/g,'').replace(/\s+$/g,'') == '') {
				alert('Veuillez entrer un libellé');
				mainForm.libelle.focus();
				return false;
			}
			submitData("createServiceMeteoAction.action");
		}

	
		//reinitialisation des filtres comme à l'entrée de la page
		function reinitFilterValues() {
			document.getElementById("mainForm").filtreService.value = '<s:property value="filtreService" />';
		}
	
		//action à la recherche avec filtre
		function lancerRecherche() {
			document.getElementById("mainForm").page.value = '1';
			document.getElementById("mainForm").submit();
		}

	      //reinitialisation des filtres 
		function reinitialiserLesFiltres(){
			document.getElementById("mainForm").filtreService.value = '';
			lancerRecherche();
		}

		//Detail
		function detail(id){
			changeData('selectRow', id);
			submitData('detailServiceMeteoAction.action');
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('SRV_LST')" /></s:param>
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
		<s:form id="mainForm" action="showServiceMeteoAction" theme="simple">
			<s:if test="#session.USER_DROITS.contains('MTG_SRV_ADD')">
				<div class="plus">
					<s:textfield name="libelle" id="libelle" size="50" onkeydown=" ajouterOnKeyPress(event);"/>
					<s:a href="#" onclick="javascript:ajouter()" cssClass="ajouter">
						<img class="icone" alt="Ajouter" title="Ajouter" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" />
					</s:a>
				</div>
				<br/>
			</s:if>
			
			<table class="dataTable" rules="all" border="1">
				<col width="90%"/>
				<s:if test="#session.USER_DROITS.contains('MTG_SRV_MOD') || #session.USER_DROITS.contains('MTG_SRV_DEL')">
					<col width="10%"/>
				</s:if>
				<thead>
					<tr>
						<td <s:if test="#session.USER_DROITS.contains('MTG_SRV_MOD') || #session.USER_DROITS.contains('MTG_SRV_DEL')">colspan="2"</s:if>>
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
					
					<tr>
						<td align="left">Filtre nom du service:<br>
							<s:token></s:token>
							<s:hidden id="selectRow" name="selectRow"/>
							<s:hidden name="page" id="page" />
							<s:hidden name="nrPerPage" id="nrPerPage"/>
							<s:hidden name="nrPages" value="%{pagination.nrPages}" />
							<s:hidden name="validForm" value="1" />
							<s:textfield name="filtreService" maxlength="20" size="25" onchange="javascript:lancerRecherche();" onkeydown="lancerRechercherOnKeyPress(event);"></s:textfield>
						</td>
						<s:if test="#session.USER_DROITS.contains('MTG_SRV_MOD') || #session.USER_DROITS.contains('MTG_SRV_DEL')">
							<td>&nbsp;</td>
						</s:if>
					</tr>
					
					<tr valign="middle">
						<td class="titreColonne">Nom du Service</td>
						<s:if test="#session.USER_DROITS.contains('MTG_SRV_MOD') || #session.USER_DROITS.contains('MTG_SRV_DEL')">
							<td class="titreColonne" >Actions</td>
						</s:if>
					</tr>
				</thead>
				<tbody>
					<s:if test="services.isEmpty()">
						<tr>
							<td class="emptyListText" <s:if test="#session.USER_DROITS.contains('MTG_SRV_MOD') || #session.USER_DROITS.contains('MTG_SRV_DEL')">colspan="2"</s:if>>
								Aucune service météo trouvé
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="services" id="services" status="stuts">
							<tr>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#services.service" /></td>
								<s:if test="#session.USER_DROITS.contains('MTG_SRV_MOD') || #session.USER_DROITS.contains('MTG_SRV_DEL')">
									<td align="center">
										<s:if test="#session.USER_DROITS.contains('MTG_SRV_MOD')">
											<s:a href="#" onclick="javascript:modifier('%{#services.id}');">
												<img alt="Modifier" title="Modifier" class="icone" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
											</s:a>
										</s:if>
										<s:if test="#session.USER_DROITS.contains('MTG_SRV_DEL')">
											<s:a href="#" onclick="javascript:supprimer('%{#services.id}');">
												<img alt="Supprimer" title="Supprimer" class="icone" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
											</s:a>
										</s:if>
										<s:if test="#session.USER_DROITS.contains('MTG_SRV_DET')">
											<s:a href="#" onclick="javascript:detail('%{#services.id}');">
												<img class="icone" alt="Détail" title="Détail" src="<s:property value="#session.ENSEIGNE" />/img/detail-16.png">
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
						<td <s:if test="#session.USER_DROITS.contains('MTG_SRV_MOD') || #session.USER_DROITS.contains('MTG_SRV_DEL')">colspan="2"</s:if>>
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
				</tfoot>
			</table>
		</s:form>
	</div>
</body>
</html>