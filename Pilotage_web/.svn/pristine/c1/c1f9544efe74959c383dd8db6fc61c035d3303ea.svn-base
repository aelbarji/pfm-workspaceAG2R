<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		//action de suppression 
		function supprimer(id) {
			if (confirm("Voulez-vous vraiment supprimer ce domaine?")) {
				changeData('domaineID', id);
				submitData('supprimerAstreinteDomaineAction.action');
			}
		}
	
		//action de modification
		function modifier(id) {
			changeData('domaineID', id);
			submitData('redirectModifyAstreinteDomaineAction.action');
		}

		//action d'ajout
		function ajouter(){
			var createform = document.getElementById("mainForm");
			if(createform.astreinteDomaine.value.replace(/^\s+/g,'').replace(/\s+$/g,'') == ""){
				alert("Veuillez entrer un libellé");
				createform.astreinteDomaine.focus();
				return false;
			}
			submitData('createAstreinteDomaineAction.action');
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('AST_D_LST')" /></s:param>
	</s:include>
	
	<div class="contentTable" style="width:40%">
		<s:form id="mainForm">
			<s:hidden id="domaineID" name="domaineID"/>
			<s:token></s:token>
			
			<s:if test="#session.USER_DROITS.contains('AST_DOM_ADD')">
				<div class="plus">
					<s:textfield name="astreinteDomaine" maxlength="50" size="60" theme="simple" onkeypress="ajouterOnKeyPress(event)"/>
					<s:a cssClass="ajouter" href="#" onclick="javascript:ajouter()">
						<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" />
					</s:a>
				</div>
			</s:if>
			
			<table class="dataTable" rules="all" cellpadding="2">
				<col width="90%"/>
				<s:if test="#session.USER_DROITS.contains('AST_DOM_MOD') || #session.USER_DROITS.contains('AST_DOM_DEL')">
					<col width="10%"/>
				</s:if>
				<thead>
					<tr>
						<td class="titreColonne">Domaine</td>
						<s:if test="#session.USER_DROITS.contains('AST_DOM_MOD') || #session.USER_DROITS.contains('AST_DOM_DEL')">
							<td class="titreColonne">Actions</td>
						</s:if>
					</tr>
				</thead>
				<tbody>
					<s:if test="aDomaines.isEmpty()">
						<tr>
							<td class="emptyListText" <s:if test="#session.USER_DROITS.contains('AST_DOM_MOD') || #session.USER_DROITS.contains('AST_DOM_DEL')">colspan="2"</s:if>>
								Aucun domaine trouvé
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="aDomaines">
							<tr>
								<td align="left">
								     &nbsp;&nbsp;&nbsp;<s:property value="%{domaine}" />
								</td>
								<s:if test="#session.USER_DROITS.contains('AST_DOM_MOD') || #session.USER_DROITS.contains('AST_DOM_DEL')">
									<td align="center">
										<s:if test="#session.USER_DROITS.contains('AST_DOM_MOD')">
											<s:a href="#" onclick="javascript:modifier('%{id}');">
												<img alt="Modifier" title="Modifier" class="icone" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
											 </s:a>
										 </s:if>
										 <s:if test="#session.USER_DROITS.contains('AST_DOM_DEL')">
											 <s:a href="#" onclick="javascript:supprimer('%{id}');">
												<img alt="Supprimer" title="Supprimer" class="icone" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
											 </s:a>
										 </s:if>
									</td>
								</s:if>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
			</table>
		</s:form>
	</div>
</body>
</html>