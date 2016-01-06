<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<s:include value="/jsp/user_pages/commun/html_head.jsp" />
		<script type="text/javascript">
			function supprimer (selectedID){
				if(confirm("Voulez-vous vraiment supprimer ce type ?")){
					valider(selectedID, "supprimerAlertesType.action");
				}		
			}
			
			function valider(selectedID, actionAddr){
				changeData("selectedID", selectedID);
				submitData(actionAddr);
			}
			
			function ajouter(){
				if(document.getElementById("libelle").value.replace(/^\s+/g,'').replace(/\s+$/g,'')  == ""){
					alert("Veuillez entrer un libellé");
					document.getElementById("libelle").focus();
					return false;
				}
				document.getElementById("mainForm").submit();
			}
		</script>
	</head>
	<body>
		<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
			<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('ALT_TYPE')" /></s:param>
		</s:include>

		<div class="contentTable" style="width: 60%">
			<s:form id="mainForm" theme="simple" action="createAlertesType" onsubmit="return false;">
				<s:token></s:token>
				<s:hidden name="selectedID" id="selectedID"/>
				
				<s:if test="#session.USER_DROITS.contains('AD_TYP_ADD')">
					<div class="plus">
						<s:textfield id="libelle" name="libelle" maxlength="127" size="60" onkeypress="ajouterOnKeyPress(event)"/>
						<s:a href="#" onclick="javascript:ajouter()" cssClass="ajouter">
							<img class="icone" alt="Ajouter" title="Ajouter" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" />
						</s:a>
					</div>
					<br/>
				</s:if>
	
				<table class="dataTable" border="1" rules="all">
					<col width="90%"/>
					<s:if test="#session.USER_DROITS.contains('AD_TYP_MOD') || #session.USER_DROITS.contains('AD_TYP_DEL')">
						<col width="10%"/>
					</s:if>
					<thead>
						<tr>
							<th class="titreColonne">Type</th>
							<s:if test="#session.USER_DROITS.contains('AD_TYP_MOD') || #session.USER_DROITS.contains('AD_TYP_DEL')">
								<th class="titreColonne">Actions</th>
							</s:if>
						</tr>
					</thead>
					<tbody>
						<s:if test="listType.isEmpty()">
							<tr>
								<td class="emptyListText" <s:if test="#session.USER_DROITS.contains('AD_TYP_MOD') || #session.USER_DROITS.contains('AD_TYP_DEL')">colspan="2"</s:if>>
									Aucun type trouvé
								</td>
							</tr>
						</s:if>
						<s:else>
							<s:iterator value="listType">
								<tr>
									<td align="left"><s:property value="%{type}"/></td>
									<s:if test="#session.USER_DROITS.contains('AD_TYP_MOD') || #session.USER_DROITS.contains('AD_TYP_DEL')">
										<td align="center">
											<s:if test="#session.USER_DROITS.contains('AD_TYP_MOD')">
												<s:a href="#" onclick="javascript:valider('%{id}', 'showModifyAlertesType.action')">
													<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
												</s:a>
											</s:if>
											<s:if test="#session.USER_DROITS.contains('AD_TYP_DEL')">
												<s:a href="#" onclick="javascript:supprimer('%{id}')">
													<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
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