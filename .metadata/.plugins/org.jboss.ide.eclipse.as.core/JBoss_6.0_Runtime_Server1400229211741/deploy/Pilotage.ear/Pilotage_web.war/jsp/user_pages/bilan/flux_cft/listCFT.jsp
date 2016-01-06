<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<s:include value="/jsp/user_pages/commun/html_head.jsp" />
		<script type="text/javascript">
			function supprimer (selectedID){
				if(confirm("voulez-vous vraiment supprimer ce flux ?")){
					valider(selectedID, "supprimerFluxCFT.action");
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
			<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('FLX_LST')" /></s:param>
		</s:include>
		
		<div class="contentTable" style="width: 60%">
			<s:form id="mainForm" theme="simple" action="createFluxCFT" onsubmit="return false;">
				<s:token></s:token>
				<s:hidden name="selectedID" id="selectedID"/>
				
				<s:if test="#session.USER_DROITS.contains('BL_FLX_ADD')">
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
					<s:if test="#session.USER_DROITS.contains('BL_FLX_MOD') || #session.USER_DROITS.contains('BL_FLX_DEL')">
						<col width="10%"/>
					</s:if>
					<thead>
						<tr>
							<th class="titreColonne">Libellé</th>
							<s:if test="#session.USER_DROITS.contains('BL_FLX_MOD') || #session.USER_DROITS.contains('BL_FLX_DEL')">
								<th class="titreColonne">Actions</th>
							</s:if>
						</tr>
					</thead>
					<tbody>
						<s:if test="listCFT.isEmpty()">
							<tr>
								<td class="emptyListText" <s:if test="#session.USER_DROITS.contains('BL_FLX_MOD') || #session.USER_DROITS.contains('BL_FLX_DEL')">colspan="2"</s:if>>
									Aucun flux trouvé
								</td>
							</tr>
						</s:if>
						<s:else>
							<s:iterator value="listCFT">
								<tr>
									<td align="left">&nbsp;&nbsp;<s:property value="%{libelle}"/></td>
									<s:if test="#session.USER_DROITS.contains('BL_FLX_MOD') || #session.USER_DROITS.contains('BL_FLX_DEL')">
										<td align="center">
											<s:if test="#session.USER_DROITS.contains('BL_FLX_MOD')">
												<s:a href="#" onclick="javascript:valider('%{id}', 'showModifyFluxCFT.action')">
													<img border="0" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
												</s:a>
											</s:if>
											<s:if test="#session.USER_DROITS.contains('BL_FLX_DEL')">
												<s:a href="#" onclick="javascript:supprimer('%{id}')">
													<img border="0" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
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