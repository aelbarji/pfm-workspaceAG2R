<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/admin.css">
	<link rel="shortcut icon" type="image/x-icon" href="<s:property value="#session.ENSEIGNE" />/img/favicon.ico" />
	<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
	<script type="text/javascript" src="js/common_admin.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">

		function ajouter(){
			submitData('showCreateSousModuleAdminAction.action');
		}
	
		function modifier(id){
			changeData("sousModuleID", id);
			submitData('showModifySousModuleAdminAction.action');
		}

		function supprimer(id){
			changeData("sousModuleID", id);
			submitData('deleteSousModuleAdminAction.action');
		}
	</script>
	
</head>
<body>
   <div class="error" id="error"><s:label value="%{error}" cssClass="error" id="error"></s:label></div>
	<div class="info" id="info"><s:label value="%{info}" cssClass="info" id="info"></s:label></div>
	<div class="titrePage">
		<s:label value="Liste des sous-modules" cssClass="title"></s:label>
	</div>
	<div class="Pagecontents" style="width: 90%">
		<s:form theme="simple" id="mainForm">
		<div class="plus" >
		<s:token></s:token>
		<s:hidden id="moduleID" name="moduleID"/>
			<s:a href="#" cssClass="ajouter" onclick="javascript:ajouter()">	
				<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" />
			</s:a>
		</div>
			<s:hidden name="sousModuleID" id="sousModuleID" />
			<table border="1" rules="rows" cellpadding="0" cellspacing="0" width="100%" class="tabCenter" align="left">
				<thead>
					<tr>
						<th class="colTitle" width="20%">Sous-Module</th>
						<th class="colTitle" width="15%">Droit ajout</th>
						<th class="colTitle" width="15%">Droit modification</th>
						<th class="colTitle" width="15%">Droit suppression</th>
						<th class="colTitle" width="15%">Droit Détail</th>
						<th class="colTitle" width="5%">Actions</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="listSousModule.isEmpty()">
						<tr>
							<td colspan="10" class="emptyListText">
								Aucun sous-module pour ce module
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listSousModule" id="lsm" status="status">
							<tr>
							<s:if test="#status.odd == true"><tr class="oddRow"></s:if>
							<s:else><tr class="evenRow"></s:else>
								<td align="center"><s:property value="nom"/></td>
								
								<td align="center"> 
									<s:if test="#lsm.idAjout != NULL">
										<s:property value="#lsm.idAjout.getLibelle()"/>
									 </s:if>
								</td>
								
								<td align="center">
									<s:if test="#lsm.idModif != NULL">
										<s:property value="#lsm.idModif.getLibelle()"/>
										</s:if>
								</td>
								
								<td align="center">
									<s:if test="#lsm.idSuppr != NULL">
										<s:property value="#lsm.idSuppr.getLibelle()"/>
									</s:if> 
								</td>
								
								<td align="center">
									<s:if test="#lsm.idDetail != NULL">
										<s:property value="#lsm.idDetail.getLibelle()"/>
									</s:if> 
								</td>
								
								<td align="center">
								<s:a href="#" onclick="javascript:modifier('%{id}');">
									<img border="0" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png"/>
								</s:a>
								<s:a href="#" onclick="javascript:supprimer('%{id}');">
									<img border="0" alt="Supprimer" title="Supprimer"  src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png"/>
								</s:a>
							</td>
							</tr>
						</s:iterator>	
					</s:else>
				</tbody>
				
			</table>
				<table border="0" cellspacing="0" cellpadding="0" align="right">
					<tr>
						<td width="50px"><s:a href="showModuleAdminAction.action" cssClass="annuler">RETOUR</s:a></td>
					</tr>
				</table>
		</s:form>
		
		
	</div>
</body>
</html>