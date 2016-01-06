<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/admin.css">
	<link rel="shortcut icon" type="image/x-icon" href="<s:property value="#session.ENSEIGNE" />/img/favicon.ico" />
	<meta http-equiv="content-type" content="text/html; charset=UTF-8;" />
	<meta http-equiv="cache-control" content="no-cache, must-revalidate" /> 
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

				<s:iterator value="listEscapedPara">
					if(value == "<s:property value="libelle" escape="false"/>"){
						document.getElementById("valeurParametre").value = "<s:property value="valeur" escape="false"/>";
					}
				</s:iterator>
			}
		}

		function supprimer(){
			if(confirm('Voulez-vous vraiment supprimer ce paramètre ?')){
				submitData('supprimerParaAdminAction.action');
			}
		}
	</script>
</head>
<body>
	<div class="error" id="error"><s:label value="%{error}" cssClass="error" id="error"></s:label></div>
	<div class="info" id="info"><s:label value="%{info}" cssClass="info" id="info"></s:label></div>
	<div class="titrePage">
		<s:label value="Paramètres" cssClass="title"></s:label>
	</div>
	<div class="Pagecontents">
		<s:form theme="simple" id="mainForm" >
			<s:div id="select">
				<s:a>Paramètre : </s:a>
				<select name="libelle" onchange="javascript:change(this)">
					<option value="" selected="selected"></option>
					<s:iterator value="listPara">
						<option value="<s:property value="libelle" />" >
							<s:property value="libelle"/>
						</option>
					</s:iterator>
				</select>
				<s:a href="showCreateParaAdminAction.action">
					<img id="ajouterimg" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" border="0">&nbsp;ajouter
				</s:a>
			</s:div>
			<table class="tabSaisie" width="600px;">
				<tr id="ligneValeur" style="display: none">
					<td>Valeur :</td>
					<td colspan="2"><s:textarea id="valeurParametre" name="valeur" rows="3" cols="50" onkeypress="if(event.keyCode == 13) return false;"></s:textarea></td>
				</tr>
				<tr id="ligneBouton" style="display: none">
					<td></td>
					<td colspan="2">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="50px"><s:a href="#" onclick="javascript:submitData('modifyParaAdminAction.action')" cssClass="valider">VALIDER</s:a></td>
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