<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function ajouter(){
			submitData('showCreateMachineDomaineAction.action');
		}

		function modifier(id){
			changeData('selectRow', id);
			submitData('showModifyMachineDomaineAction.action');
		}
		
		function supprimer(id){
			if(confirm("Voulez-vous vraiment supprimer ce domaine ?")){
				changeData('selectRow', id);
				submitData('supprimerMachineDomaineAction.action');
			}
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MAC_DOM')" /></s:param>
	</s:include>
	
	<div class="contentTable" style="width:40%">
		<s:form theme="simple" id="mainForm" onsubmit="return false;" >
			<s:hidden id="selectRow" name="selectRow" value=""/>
			<s:token></s:token>
			
			<s:if test="#session.USER_DROITS.contains('MAC_DOM_ADD')">
				<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
			</s:if>
		</s:form>
		<br/>
		<table class="dataTable" border="1" rules="all">
			<col width="90%"/>
			<s:if test="#session.USER_DROITS.contains('MAC_DOM_MOD') || #session.USER_DROITS.contains('MAC_DOM_DEL')">
				<col width="10%"/>
			</s:if>
			<thead>
				<tr>
					<th class="titreColonne">Domaine</th>
					<s:if test="#session.USER_DROITS.contains('MAC_DOM_MOD') || #session.USER_DROITS.contains('MAC_DOM_DEL')">
						<th class="titreColonne">Actions</th>
					</s:if>
				</tr>
			</thead>
			<tbody>
				<s:if test="%{listMachineDomaine.isEmpty()}">
					<tr>
						<td class="emptyListText" <s:if test="#session.USER_DROITS.contains('MAC_DOM_MOD') || #session.USER_DROITS.contains('MAC_DOM_DEL')">colspan="2"</s:if>>
							Aucun domaine de machine trouvé
						</td>
					</tr>
				</s:if>
				<s:else>
					<s:iterator value="listMachineDomaine">
						<tr>
							<td align="left">&nbsp;&nbsp;&nbsp;<s:property value="nomDomaine"/></td>
							<s:if test="#session.USER_DROITS.contains('MAC_DOM_MOD') || #session.USER_DROITS.contains('MAC_DOM_DEL')">
								<td align="center">
									<s:if test="#session.USER_DROITS.contains('MAC_DOM_MOD')">
										<s:a href="#" onclick="javascript:modifier('%{id}');">
											<img class="icone" alt="modifier" title="modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png"/>
										</s:a>
									</s:if>
									<s:if test="#session.USER_DROITS.contains('MAC_DOM_DEL')">
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
	</div>
</body>
</html>