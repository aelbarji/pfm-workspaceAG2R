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

	function valider(){
		var modifyForm = document.getElementById("mainForm");
		if (modifyForm.nom.value.replace(/^\s+/g,'').replace(/\s+$/g,'') == '') {
			alert('Veuillez entrer un libellé');
			modifyForm.nom.focus();
			return false;
		} 
		else {
			submitData('modifySousModuleAdminAction.action');
		}		
	}

	function retour(){
		submitData('showSousModuleAdminAction.action');
	}
	
	function supprimer(id, nomDroit){
		$.ajax({
            type: "POST",
            url: "deleteDroitSousModule.action",
            data: "id=" + id,
            success: function(response){
         	  if($.trim(response) == '1'){
             	 if($.trim(nomDroit) == 'nomSuppr'){
         			document.getElementById("mainForm").nomSuppr.value = '';
         			document.getElementById("mainForm").nomSuppr.disabled = true;
         			document.getElementById("mainForm").descriptionSuppr.value = '';
         			document.getElementById("mainForm").descriptionSuppr.disabled = true;
             	 }
             	if($.trim(nomDroit) == 'nomModif'){
         			document.getElementById("mainForm").nomModif.value = '';
         			document.getElementById("mainForm").nomModif.disabled = true;
         			document.getElementById("mainForm").descriptionModif.value = '';
         			document.getElementById("mainForm").descriptionModif.disabled = true;
             	 }
             	if($.trim(nomDroit) == 'nomAjout'){
         			document.getElementById("mainForm").nomAjout.value = '';
         			document.getElementById("mainForm").nomAjout.disabled = true;
         			document.getElementById("mainForm").descriptionAjout.value = '';
         			document.getElementById("mainForm").descriptionAjout.disabled = true;
             	 }
             	if($.trim(nomDroit) == 'nomDetail'){
         			document.getElementById("mainForm").nomDetail.value = '';
         			document.getElementById("mainForm").nomDetail.disabled = true;
         			document.getElementById("mainForm").descriptionDetail.value = '';
         			document.getElementById("mainForm").descriptionDetail.disabled = true;
             	 }
             }
         	  else alert('Suppression impossible');
         	   
            },
	           error: function(e){
	           		alert('Error: ' + e);
	           }
			});	
	}
	
	</script>
	
</head>
<body>

	<s:label value="Modification d'un sous-module" cssClass="title"></s:label>
	<div class="Pagecontents" style="width: 700px">
		<s:form theme="simple" id="mainForm" action="modifySousModuleAdminAction">
		<s:token></s:token>
			<s:hidden id="sousModuleID" name="sousModuleID"></s:hidden>
			<s:hidden id="idAjout" name="idAjout" value="%{sm.idAjout.id}" ></s:hidden>
			<s:hidden id="idModif" name="idModif" value="%{sm.idModif.id}"></s:hidden>
			<s:hidden id="idSuppr" name="idSuppr" value="%{sm.idSuppr.id}"></s:hidden>
			<s:hidden id="idDetail" name="idDetail" value="%{sm.idDetail.id}"></s:hidden>
			<s:hidden id="moduleID" name="moduleID" value="%{sm.idParent.id}"></s:hidden>
		<s:div align="center">
			<s:a>Sous-module <span class="starObligatoire">*</span> : </s:a>
			<s:textfield id="nom" name="nom" maxlength="127" size="40" value="%{sm.nom}" ></s:textfield>
		</s:div>
		<table class="tabSaisie" width=100%>
			<colgroup>
				<col width="20%"/>
				<col width="30%"/>
				<col width="50%"/>
			</colgroup>
			<thead>
				<tr>
					<th>&nbsp;</th>
					<th class="colTitle">Nom des droits  <span class="starObligatoire">*</span> : </th>
					<th class="colTitle">description</th>
				</tr>
			</thead>
			<tbody>
				<tr class="oddRow">
					<td>d'ajout : </td>
					<td><s:textfield id="nomAjout" name="nomAjout" value="%{sm.idAjout.libelle}" maxlength="127" size="25"></s:textfield></td>
					<td><s:textfield id="descriptionAjout" name="descriptionAjout" value="%{sm.idAjout.description}" maxlength="127" size="40"></s:textfield>
					<s:a href="#" onclick="javascript:supprimer('%{sm.idAjout.id}', 'nomAjout');">
						<img border="0" alt="Supprimer" title="Supprimer"  src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png"/>
					</s:a> 
					</td>
				</tr>
				<tr class="evenRow">
					<td>de modification : </td>
					<td><s:textfield id="nomModif" name="nomModif" value="%{sm.idModif.libelle}" maxlength="127" size="25"></s:textfield></td>
					<td><s:textfield id="descriptionModif" name="descriptionModif" value="%{sm.idModif.description}" maxlength="127" size="40"></s:textfield>
					<s:a href="#" onclick="javascript:supprimer('%{sm.idModif.id}', 'nomModif');">
						<img border="0" alt="Supprimer" title="Supprimer"  src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png"/>
					</s:a> 
					</td>
				</tr>
				<tr class="oddRow">
					<td>de suppression : </td>
					<td><s:textfield id="nomSuppr" name="nomSuppr" value="%{sm.idSuppr.libelle}" maxlength="127" size="25"></s:textfield></td>
					<td><s:textfield id="descriptionSuppr" name="descriptionSuppr" value="%{sm.idSuppr.description}" maxlength="127" size="40"></s:textfield>
					<s:a href="#" onclick="javascript:supprimer('%{sm.idSuppr.id}', 'nomSuppr');">
						<img border="0" alt="Supprimer" title="Supprimer"  src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png"/>
					</s:a> 
					</td>
				</tr>
				<tr class="evenRow">
					<td>de Détail : </td>
					<td><s:textfield id="nomDetail" name="nomDetail" value="%{sm.idDetail.libelle}" maxlength="127" size="25"></s:textfield></td>
					<td><s:textfield id="descriptionDetail" name="descriptionDetail" value="%{sm.idDetail.description}" maxlength="127" size="40"></s:textfield>
					<s:a href="#" onclick="javascript:supprimer('%{sm.idDetail.id}', 'nomDetail');">
						<img border="0" alt="Supprimer" title="Supprimer"  src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png"/>
					</s:a> 
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="10">
						<table align="right">
							<tr>
								<td align="right"><s:a href="#" onclick="javascript:retour();"cssClass="annuler">ANNULER</s:a></td>
								<td width="50px"><s:a href="#" onclick="javascript:valider();" cssClass="valider">VALIDER</s:a></td>
							</tr>
						</table>
					</td>
				</tr>
			</tfoot>
		
		</table>
		</s:form>
	</div>

</body>
</html>