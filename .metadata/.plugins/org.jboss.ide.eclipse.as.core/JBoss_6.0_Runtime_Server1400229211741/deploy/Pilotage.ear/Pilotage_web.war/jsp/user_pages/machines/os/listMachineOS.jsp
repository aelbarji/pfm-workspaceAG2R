<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function ajouter(){
			if(document.getElementById("libelle").value.replace(/^\s+/g,'').replace(/\s+$/g,'')  == ""){
				alert("Veuillez entrer un libellé");
				document.getElementById("libelle").focus();
				return false;
			}
			submitData('createMachineOSAction.action');
		}

		function modifier(id){
			changeData('selectRow', id);
			submitData('showModifyMachineOSAction.action');
		}
		
		function supprimer(id,os){
			if(confirm("Voulez-vous vraiment supprimer cet OS ?")){
				changeData('selectRow', id);
				submitData('supprimerMachineOSAction.action');
			}
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MAC_OS')" /></s:param>
	</s:include>
	
	<div class="contentTable" style="width:40%">
		<s:form theme="simple" id="mainForm" onsubmit="return false;">
			<s:hidden id="selectRow" name="selectRow" value=""/>
			<s:token></s:token>
			
			<s:if test="#session.USER_DROITS.contains('MAC_OS_ADD')">
				<div class="plus">
					<s:textfield id="libelle" name="libelle" maxlength="127" size="60" onkeypress="ajouterOnKeyPress(event)"/>
					<s:a href="#" onclick="javascript:ajouter()" cssClass="ajouter">
						<img class="icone" alt="Ajouter" title="Ajouter" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" />
					</s:a>
				</div>
				<br/>
			</s:if>
			
			<table class="dataTable" rules="all">
				<col width="90%"/>
				<s:if test="#session.USER_DROITS.contains('MAC_OS_MOD') || #session.USER_DROITS.contains('MAC_OS_DEL')">
					<col width="10%"/>
				</s:if>
				<thead>
					<tr>
						<th class="titreColonne">OS</th>
						<s:if test="#session.USER_DROITS.contains('MAC_OS_MOD') || #session.USER_DROITS.contains('MAC_OS_DEL')">
							<th class="titreColonne">Actions</th>
						</s:if>
					</tr>
				</thead>
				<tbody>
					<s:if test="%{listMachineOS.isEmpty()}">
						<tr>
							<td class="emptyListText" <s:if test="#session.USER_DROITS.contains('MAC_OS_MOD') || #session.USER_DROITS.contains('MAC_OS_DEL')">colspan="2"</s:if>>
								Aucun OS trouvé
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listMachineOS">
							<tr>
								<td align="left">&nbsp;&nbsp;&nbsp;<s:property value="Nom_OS"/></td>
								<s:if test="#session.USER_DROITS.contains('MAC_OS_MOD') || #session.USER_DROITS.contains('MAC_OS_DEL')">
									<td align="center">
										<s:if test="#session.USER_DROITS.contains('MAC_OS_MOD')">
											<s:a href="#" onclick="javascript:modifier('%{id}');">
												<img class="icone" alt="modifier" title="modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png"/>
											</s:a>
										</s:if>
										<s:if test="#session.USER_DROITS.contains('MAC_OS_DEL')">
											<s:a href="#" onclick="javascript:supprimer('%{id}');">
												<img class="icone" alt="supprimer" title="supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png"/>
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
