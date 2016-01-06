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
			var createform = document.getElementById("mainForm");
			if(createform.nomMod.value.replace(/^\s+/g,'').replace(/\s+$/g,'') == ""){
				alert("Veuillez entrer un libellé");
				createform.nom.focus();
				return false;
			}
			submitData('createModuleAdminAction.action');
		}

		function modifier(id){
			changeData('moduleID', id);
			submitData('showModifyModuleAdminAction.action');
		}

		function supprimer(id){
			if(confirm("Voulez-vous vraiment supprimer ce module et tous les sous-modules qui lui sont associés ?")){
				changeData("moduleID", id);
				submitData("deleteModuleAdminAction.action");
			}
		}
		
		function detail(id){
			changeData('moduleID', id); 
			submitData('showSousModuleAdminAction.action');
		}
	</script>
	
</head>
<body>
	<div class="error" id="error"><s:label value="%{error}" cssClass="error" id="error"></s:label></div>
	<div class="info" id="info"><s:label value="%{info}" cssClass="info" id="info"></s:label></div>
	<div class="titrePage">
		<s:label value="Liste des modules" cssClass="title"></s:label>
	</div>
	<div class="Pagecontents" style="width: 90%">
	<s:form theme="simple" id="mainForm">
	<s:token></s:token>
	<s:hidden id="moduleID" name="moduleID"/>
		<div class="plus" >
		
			<s:textfield id="nomMod" name="nomMod" maxlength="127" size="25" theme="simple"></s:textfield>
			<s:a href="#" cssClass="ajouter" onclick="javascript:ajouter()">	
				<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" />
			</s:a>
		</div>
			<table border="1" rules="rows" cellpadding="0" cellspacing="0" width="80%" class="tabCenter" align="left">
				<thead>
					<tr>
						<th class="colTitle" width="25%">Module</th>
						<th class="colTitle" width="15%">Actions</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="listModule" id="lsm" status="status">
						<tr>
						<s:if test="#status.odd == true"><tr class="oddRow"></s:if>
						<s:else><tr class="evenRow"></s:else>
							<td align="center"><s:property value="nom"/></td>
							<td align="center">
							<s:a href="#" onclick="javascript:modifier('%{id}');">
								<img border="0" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png"/>
							</s:a>
							<s:a href="#" onclick="javascript:detail('%{id}');"><img class="icone" alt="Détail" title="Détail" src="<s:property value="#session.ENSEIGNE" />/img/detail-16.png"/></s:a>
							<s:a href="#" onclick="javascript:supprimer('%{id}');">
								<img border="0" alt="Supprimer" title="Supprimer"  src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png"/>
							</s:a>
						</td>
						</tr>
					</s:iterator>	
								
				</tbody>
			</table>
		</s:form>
	</div>
</body>
</html>