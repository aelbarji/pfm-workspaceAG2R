<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		
		function supprimer(id){
			if(confirm("Voulez-vous vraiment supprimer ce type de environnement ?")){
				changeData('selectRow', id);
				submitData('supprimerEnvironnementTypeAction.action');
			}
		}
		
		function setPrincipal(id,url){
			changeData('selectRow', id);
			submitData(url);	
			}
		
		function ajouter(){
			var mainForm = document.getElementById("mainForm");
			if (mainForm.libelle.value.replace(/^\s+/g,'').replace(/\s+$/g,'') == '') {
				alert('Veuillez entrer un libellé');
				mainForm.libelle.focus();
				return false;
			}
			submitData("createEnvironnementTypeAction.action");
		}

		function modifier(id, url){
			changeData('selectRow', id);
			submitData(url);
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('ENV_TYP_L')" /></s:param>
	</s:include>
	
	<div class="contentTable" style="width:50%">
		<s:form id="mainForm" theme="simple" action="showEnvironnementTypeAction">
			<s:token></s:token>
			<s:hidden name="selectRow" id="selectRow"/>
			
			<s:if test="#session.USER_DROITS.contains('ENV_TYP_ADD')">
				<div class="plus">
					<s:textfield id="libelle" name="libelle" maxlength="25" size="30" onkeypress="ajouterOnKeyPress(event)"/>
					<s:a href="#" onclick="javascript:ajouter()" cssClass="ajouter">
						<img class="icone" alt="Ajouter" title="Ajouter" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" />
					</s:a>
				</div>
				<br/>
			</s:if>
								
			<table class="dataTable" rules="all">
				<col width="80%"/>
				<s:if test="#session.USER_DROITS.contains('ENV_TYP_MOD') || #session.USER_DROITS.contains('ENV_TYP_DEL')">
					<col width="20%"/>
				</s:if>
				<thead>
					<tr>
						<th class="titreColonne">Type</th>
						<s:if test="#session.USER_DROITS.contains('ENV_TYP_MOD') || #session.USER_DROITS.contains('ENV_TYP_DEL')">
							<th class="titreColonne">Principal</th>
							<th class="titreColonne">Actions</th>
						</s:if>
					</tr>
				</thead>
				<tbody>
					<s:if test="environnement_Types.isEmpty()">
						<tr>
							<td class="emptyListText" <s:if test="#session.USER_DROITS.contains('ENV_TYP_MOD') || #session.USER_DROITS.contains('ENV_TYP_DEL')">colspan="2"</s:if>>
								Aucun type trouvé
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="environnement_Types">
							<tr>
								<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="type" /></td>
								<td align="center"><input type="radio" id="prinTrue" name="principalSelected" value="1"
						 			<s:if test="principal== 1">checked="checked"</s:if> onclick="javascript:setPrincipal('<s:property value="id"/>', 'changePrincipalTypeAction.action');"/></td>
								<s:if test="#session.USER_DROITS.contains('ENV_TYP_MOD') || #session.USER_DROITS.contains('ENV_TYP_DEL')">
									<td align="center" valign="middle">
										<s:if test="%{id!=0 && id!=1}">
											<s:if test="#session.USER_DROITS.contains('ENV_TYP_MOD')">
												<s:a href="#" onclick="javascript:modifier('%{id}', 'redirectModificationEnvironnementTypeAction.action');">
													<img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE"/>/img/modifier-16.png" />
												</s:a>
											</s:if>
											<s:if test="#session.USER_DROITS.contains('ENV_TYP_DEL')">
												<s:a href="#" onclick="javascript:supprimer('%{id}');">
													<img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE"/>/img/supprimer-16.png" />
												</s:a>
											</s:if>
										</s:if>
										<s:else>
											Non modifiable
										</s:else>
									</td>
								</s:if>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
			</table>
			<span class="champObligatoire">* Principal permet la visualisation des statistiques de l'environnement type selectionné.</span>
		</s:form>
	</div>
</body>
</html>