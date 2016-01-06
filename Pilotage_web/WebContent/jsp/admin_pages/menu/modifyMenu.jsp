<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/admin.css">
	<link rel="shortcut icon" type="image/x-icon" href="<s:property value="#session.ENSEIGNE" />/img/favicon.ico" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
	<script type="text/javascript" src="js/common_admin.js"></script>
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
			submitData('modifyMenuAdminAction.action');
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
	<div class="error" id="error"><s:label value="%{error}" cssClass="error" id="error"></s:label>
		<s:actionerror/>
	</div>
	<div class="info" id="info"><s:label value="%{info}" cssClass="info" id="info"></s:label></div>
	<div class="titrePage">
		<s:label value="Modification d'un menu" cssClass="title"></s:label>
	</div>
	<div class="Pagecontents">
		<s:form theme="simple" id="mainForm" enctype="multipart/form-data" method="POST">
			<s:hidden name="selectRow" />
			<table class="tabSaisie" width="600px;">
				<tr>
					<td width="120px">ID : </td>
					<td><s:label value="%{selectRow}"/></td>
				</tr>
				<tr>
					<td>Parent : </td>
					<td>
					<s:if test="menuToEdit.getId_parent() == null">
						<s:select cssClass="select" name="parent" list="%{menuList}" listKey="menu.id" listValue="parents" emptyOption="true" value=''/>
					</s:if>
					<s:else>
						<!-- <s:select cssClass="select" name="parent" list="%{menuList}" listKey="menu.id" listValue="parents" emptyOption="true" value="%{(menuToEdit.getId_parent().equals(NULL) ? '' : menuToEdit.getId_parent())}"/> -->
						<s:select cssClass="select" name="parent" list="%{menuList}" listKey="menu.id" listValue="parents" emptyOption="true" value="%{(menuToEdit.getId_parent().equals(NULL) ? '' : menuToEdit.getId_parent())}"/>
					</s:else>
					</td>
				</tr>
				<tr>
					<td>Place : </td>
					<td><s:textfield name="place" id="place" maxlength="3" size="5" value="%{menuToEdit.getPlace()}"/></td>
				</tr>
				<tr>
					<td>Libellé <span class="starObligatoire">*</span> : </td>
					<td><s:textfield name="libelle" id="libelle" maxlength="127" size="50" value="%{menuToEdit.getLibelle()}"/></td>
				</tr>
				<tr>
					<td>Lien : </td>
					<td><s:textarea id="lien" name="lien"  rows="3" cols="50" onkeypress="if(event.keyCode == 13) return false;" value="%{menuToEdit.getLien()}"></s:textarea></td>
				</tr>
				<tr>
					<td>Image icône : </td>
					<td>
						<s:if test= "menuToEdit.getIcone() == null">
						<s:select id="icone" name="icone" list="%{imagesList}" listKey="id" listValue="nom" headerKey="-1" headerValue="" value="-1" onchange="modifyImage(this.value)"/>
						</s:if>
						<s:else>
							<s:select id="icone" name="icone" list="%{imagesList}" listKey="id" listValue="nom" headerKey="-1" headerValue="" value="%{(menuToEdit.getIcone().equals(NULL) ? '-1' : menuToEdit.getIcone())}" onchange="modifyImage(this.value)"/>
						</s:else>
							ou <s:file name="imageUpload" onchange="fileSelected()"/>
						<div id="iconeDiv" <s:if test="menuToEdit.getIcone() == null">style="display:none;"</s:if>>
							<s:if test="menuToEdit.getIcone() != null">
								<img alt="Icone" title="Icone" src="getImageAction.action?imageID=<s:property value="menuToEdit.getIcone()"/>" id="iconeImage"/>
							</s:if>
							<s:else>
								<img alt="Icone" title="Icone" src="" id="iconeImage"/>
							</s:else>
						</div>
					</td>
				</tr>
				<tr>
					<td>Interop&eacute;rabilit&eacute; : </td>
					<td><s:checkbox name="interop" value="%{menuToEdit.getInterop()}"/></td>
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