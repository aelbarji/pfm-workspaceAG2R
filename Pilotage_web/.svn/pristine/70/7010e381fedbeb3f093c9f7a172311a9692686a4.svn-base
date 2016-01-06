<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">		
		function supprimer(id){
			if(confirm("Voulez-vous vraiment supprimer ce type de dérogation ?")){
				valider(id, "supprimerDerogationTypeAction.action");
			}
		}

		function valider(id, url){
			changeData('id', id);
			submitData(url);
		}

		function ajouter(){
			var mainForm = document.getElementById("mainForm");
			if (mainForm.libelle.value.replace(/^\s+/g,'').replace(/\s+$/g,'') == '') {
				alert('Veuillez entrer un libellé');
				mainForm.libelle.focus();
				return false;
			}
			submitData('createDerogationTypeAction.action');
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('DRG_TYPE')" /></s:param>
	</s:include>
	
	<div class="contentTable" style="width: 35%">
		<s:form id="mainForm" theme="simple">
			<s:hidden name="id" id="id"/>
			<s:token></s:token>
			
			<s:if test="#session.USER_DROITS.contains('DRG_TYP_ADD')">
				<div class="plus">
					<s:textfield name="libelle" id="libelle" maxlength="6" size="10" theme="simple" onkeypress="ajouterOnKeyPress(event)"/>
					<s:a href="#" onclick="javascript:ajouter()" cssClass="ajouter">
						<img class="icone" alt="Ajouter" title="Ajouter" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" />
					</s:a>
				</div>
				<br/>
			</s:if>
			
			<table class="dataTable" rules="all">
				<col width="90%"/>
				<s:if test="#session.USER_DROITS.contains('DRG_TYP_MOD') || #session.USER_DROITS.contains('DRG_TYP_DEL')">
					<col width="10%"/>
				</s:if>
				<thead>
					<tr>
						<th class="titreColonne">Type</th>
						<s:if test="#session.USER_DROITS.contains('DRG_TYP_MOD') || #session.USER_DROITS.contains('DRG_TYP_DEL')">
							<th class="titreColonne">Actions</th>
						</s:if>
					</tr>
				</thead>
				<tbody>
					<s:if test="listDT.isEmpty()">
						<tr>
							<td class="emptyListText" <s:if test="#session.USER_DROITS.contains('DRG_TYP_MOD') || #session.USER_DROITS.contains('DRG_TYP_DEL')">colspan="2"</s:if>>
								Aucun type trouvé
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listDT">
							<tr>
								<td align="left">&nbsp;&nbsp;&nbsp;<s:property value="type" /></td>
								<s:if test="#session.USER_DROITS.contains('DRG_TYP_MOD') || #session.USER_DROITS.contains('DRG_TYP_DEL')">
									<td align="center" valign="middle">
										<s:if test="#session.USER_DROITS.contains('DRG_TYP_MOD')">
											<s:a href="#" onclick="javascript:valider('%{id}', 'redirectModifyDerogationTypeAction.action');">
												<img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE"/>/img/modifier-16.png" />
											</s:a>
										</s:if>
										<s:if test="#session.USER_DROITS.contains('DRG_TYP_DEL')">
											<s:a href="#" onclick="javascript:supprimer('%{id}');">
												<img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE"/>/img/supprimer-16.png" />
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