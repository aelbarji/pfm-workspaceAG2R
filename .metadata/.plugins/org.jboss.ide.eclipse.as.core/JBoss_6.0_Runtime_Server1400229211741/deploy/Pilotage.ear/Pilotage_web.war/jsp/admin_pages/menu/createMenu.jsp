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
			if(document.getElementById("libelle").value.replace(/^\s+/g,'').replace(/\s+$/g,'') == ""){
				alert('Veuillez entrer un libellé');
				document.getElementById("libelle").focus();
				return false;
			}
			else if(document.getElementById("place").value != "" && !checkChiffres(document.getElementById("place").value)){
				alert('Veuillez entrer un nombre positif pour la place');
				document.getElementById("place").focus();
				return false;
			}
			submitData('createMenuAdminAction.action');
		}

		function modifyImage(id){
			if(id == -1){
				document.getElementById("iconeDiv").style.display = 'none';
			}
			else{
				document.getElementById("iconeDiv").style.display = 'block';
				document.getElementById("iconeImage").src = 'getImageAction.action?imageID=' + id;
			}
		}

		function fileSelected(){
			document.getElementById("icone").value = -1;
			document.getElementById("iconeDiv").style.display = 'none';
		}
	</script>
</head>
<body>
	<div class="error" id="error"><s:label value="%{error}" cssClass="error" id="error"></s:label></div>
	<div class="info" id="info"><s:label value="%{info}" cssClass="info" id="info"></s:label></div>
	<div class="titrePage">
		<s:label value="Création d'un menu" cssClass="title"></s:label>
	</div>
	<div class="Pagecontents">
		<s:form theme="simple" id="mainForm" enctype="multipart/form-data" method="POST">
			<table class="tabSaisie" width="600px;">
				<tr>
					<td width="120px">Parent : </td>
					<td><s:select list="%{menuList}" listKey="menu.id" listValue="parents" name="parent" id="parent" emptyOption="true"></s:select></td>
				</tr>
				<tr>
					<td>Place : </td>
					<td><s:textfield name="place" id="place" maxlength="3" size="5"></s:textfield></td>
				</tr>
				<tr>
					<td>Libellé <span class="starObligatoire">*</span> : </td>
					<td><s:textfield name="libelle" id="libelle" maxlength="127" size="50"></s:textfield></td>
				</tr>
				<tr>
					<td>Lien : </td>
					<td><s:textarea id="lien" name="lien"  rows="3" cols="50" onkeypress="if(event.keyCode == 13) return false;"></s:textarea></td>
				</tr>
				<tr>
					<td>Image icône : </td>
					<td>
						<s:select id="icone" name="icone" list="%{imagesList}" listKey="id" listValue="nom" headerKey="-1" headerValue="" onchange="modifyImage(this.value)"/> ou <s:file name="imageUpload" onchange="fileSelected()"/>
						<br/>
						<div id="iconeDiv" <s:if test="icone == null">style="display:none;"</s:if>>
							<s:if test="icone != null">
								<img alt="Icone" title="Icone" src="getImageAction.action?imageID=%{icone}" id="iconeImage"/>
							</s:if>
							<s:else>
								<img alt="Icone" title="Icone" src="" id="iconeImage"/>
							</s:else>
						</div>
					</td>
				</tr>
				<tr>
					<td>Interop&eacute;rabilit&eacute; : </td>
					<td><s:checkbox name="interop"/></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="50px"><s:a href="showMenuAdminAction.action" cssClass="annuler">ANNULER</s:a></td>
								<td>&nbsp;</td>
								<td><s:a href="#" onclick="javascript:valider();" cssClass="valider">VALIDER</s:a></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr align="left">
					<td colspan="2"><span class="champObligatoire">* Champs Obligatoires</span></td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>