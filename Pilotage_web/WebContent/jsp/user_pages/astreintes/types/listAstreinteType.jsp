<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		//action de suppression d'un utilisateur
		function supprimer(id) {
			if (confirm("Voulez-vous vraiment supprimer ce type?")) {
				changeData('typeID', id);
				submitData('supprimerAstreinteTypeAction.action');
			}
		}
	
		//action de modification
		function modifier(id) {
			changeData('typeID', id);
			submitData('redirectModifyAstreinteTypeAction.action');
		}

		//action d'ajout
		function ajouter(){
			var createform = document.getElementById("mainForm");
			if(createform.astreinteType.value.replace(/^\s+/g,'').replace(/\s+$/g,'') == ""){
				alert("Veuillez entrer le libellé");
				createform.astreinteType.focus();
				return false;
			}
			submitData('createAstreinteTypeAction.action');
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('AST_T_LST')" /></s:param>
	</s:include>
	
	<div class="contentTable" style="width:40%">
		<s:form id="mainForm">
			<s:hidden id="typeID" name="typeID"/>
			<s:token></s:token>
			<s:if test="#session.USER_DROITS.contains('AST_TYP_ADD')">
				<div class="plus">
					<s:textfield name="astreinteType" maxlength="20" size="25" theme="simple" onkeypress="ajouterOnKeyPress(event)"/>
					<s:a cssClass="ajouter" href="#" onclick="javascript:ajouter()">
						<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" />
					</s:a>
				</div>
			</s:if>
		
			<table class="dataTable" rules="all" cellpadding="2">
				<col width="90%"/>
				<s:if test="#session.USER_DROITS.contains('AST_TYP_MOD') || #session.USER_DROITS.contains('AST_TYP_DEL')">
					<col width="10%"/>
				</s:if>
				<thead>
					<tr>
						<td class="titreColonne">Type</td>
						<s:if test="#session.USER_DROITS.contains('AST_TYP_MOD') || #session.USER_DROITS.contains('AST_TYP_DEL')">
							<td class="titreColonne">Actions</td>
						</s:if>
					</tr>
				</thead>
				<tbody>
					<s:if test="aTypes.isEmpty()">
						<tr>
							<td class="emptyListText" <s:if test="#session.USER_DROITS.contains('AST_TYP_MOD') || #session.USER_DROITS.contains('AST_TYP_DEL')">colspan="2"</s:if>>
								Aucun type trouvé
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="aTypes">
							<tr>
								<td align="left">&nbsp;&nbsp;&nbsp; <s:property value="%{type}" /></td>
								<s:if test="#session.USER_DROITS.contains('AST_TYP_MOD') || #session.USER_DROITS.contains('AST_TYP_DEL')">
									<td align="center">
										<s:if test="#session.USER_DROITS.contains('AST_TYP_MOD')">
										    <s:a href="#" onclick="javascript:modifier('%{id}');">
												<img alt="Modifier" title="Modifier" class="icone" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
											</s:a>
										</s:if>
										<s:if test="#session.USER_DROITS.contains('AST_TYP_DEL')">
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