<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/admin.css" />
	<link rel="shortcut icon" type="image/x-icon" href="<s:property value="#session.ENSEIGNE" />/img/favicon.ico" />
	<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
	<script type="text/javascript" src="js/common_admin.js"></script>
	<script type="text/javascript">	
		function change(select) {
			$("#info").hide();
			$("#error").hide();
			var value = select.options[select.selectedIndex].value;
			if (select.selectedIndex == 0) {
				$("#ligneValeur").hide();
				$("#ligneBouton").hide();
			} else {
				$("#ligneValeur").show();
				$("#ligneBouton").show();

				<s:iterator value="listTitreEscaped">
					if(value == "<s:property value="id" />"){
						document.getElementById("valeurTitre").value = "<s:property value="libelle" escape="false"/>";
					}
				</s:iterator>
			}
		}

		function supprimer(){
			if(confirm('Voulez-vous vraiment supprimer ce titre ?')){
				submitData('supprimerTitreAdminAction.action');
			}
		}
	</script>
</head>

<body>
	<div class="error" id="error"><s:label value="%{error}" cssClass="error" id="error"></s:label></div>
	<div class="info" id="info"><s:label value="%{info}" cssClass="info" id="info"></s:label></div>
	<div class="titrePage">
		<s:label value="Modification d'un titre de page" cssClass="title"></s:label>
	</div>
	<div class="Pagecontents" style="width: 600px;">
		<s:form theme="simple" id="mainForm">
			<s:div id="select">
				<s:a>Page : </s:a>
				<select name="id"  onchange="javascript:change(this)">
					<option value="" selected="selected"></option>
					<s:iterator value="listTitre">
						<option value="<s:property value="id" />">
							<s:property value="description" />
						</option>
					</s:iterator>
				</select>
				<s:a href="showCreateTitreAdminAction.action">
					<img id="ajouterimg" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" border="0"/>&nbsp;ajouter
				</s:a>
			</s:div>
			<table class="tabSaisie" width="600px;">
				<tr id="ligneValeur" style="display: none">
					<td>Titre :</td>
					<td colspan="2"><s:textarea id="valeurTitre" name="titre" rows="3" cols="50" onkeypress="if(event.keyCode == 13) return false;"></s:textarea></td>
				</tr>
			
				<tr id="ligneBouton" style="display: none">
					<td></td>
					<td colspan="2">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="50px"><s:a href="#" onclick="javascript:submitData('modifyTitreAdminAction.action')" cssClass="valider">VALIDER</s:a></td>
								<td>&nbsp;</td>
								<td><s:a href="#" onclick="javascript:supprimer();" cssClass="annuler">SUPPRIMER</s:a></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>