<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<s:property value="#session.ENSEIGNE" />/css/admin.css">
<link rel="shortcut icon" type="image/x-icon"
	href="<s:property value="#session.ENSEIGNE" />/img/favicon.ico" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8;" />
<meta http-equiv="cache-control" content="no-cache, must-revalidate" />
<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
<script type="text/javascript" src="js/common_admin.js"></script>
<script type="text/javascript" src="js/jquery.jqDock.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/i18n/message-fr.js"></script>
<script src="js/jqueryFileTree.js" type="text/javascript"></script>
<link href="js/jqueryFileTree.css" media="screen" rel="stylesheet" type="text/css" />	

<script type="text/javascript">

$(document).ready( function() {
	$('#treeDiv').fileTree( {
		root: '<s:property value="dirRoot" />', 
		script: '/pilotage/jsp/admin_pages/documents_consigne/jqueryFileTree.jsp'
	}, 
	function(file) {
	//éventuellement, ajuster le nom de fichier, de base, c'est le chemin entier qui est enregistré
	// Ajustement du nom au chemin relatif de dirRoot...
		fileSelected = file.replace('<s:property value="dirRoot" />',"/").replace('^\s+|\s+$','');
		window.returnValue = fileSelected;
	});
});

function displayDocs(val){
	document.getElementById("repDestination").value = val;
	submitData('showSupprDocumentsAdminAction.action');
}

function supprimer(file){
	if(confirm("Voulez vous vraiment supprimer le document "+file+ "?")){
		changeData("document",file); 
		document.getElementById('mainForm').submit();
		}
	}
	
function downloadFile(path){
	changeData('inputPath', path);
	document.getElementById('downloadForm').submit();
}

</script>
</head>
	<div class="info" id="info">
		<s:property value="%{info}" />
	</div>
	<div class="error" id="error">
		<s:property value="%{error}" />
	</div>
	<div class="titrePage">
		<s:label value="Gestion de fichiers" cssClass="title"></s:label>
	</div>
	<div><br/><br/></div>
	<s:form id="mainForm" theme="simple" action="supprimerDocumentAction">
	<s:token></s:token>
	<s:hidden name="document" id="document" />
	<div align="center">
	<s:a>
		<b>Sélectionner un répertoire :</b>
	</s:a><br/> 
	&nbsp<input type="radio"  id="repDestination" name="repDestination" value="1" 
	<s:if test="repDestination == 1">checked="checked"</s:if> onchange="javascript:displayDocs(this.value);"/>
	Consignes Permanentes<br/> 
	&nbsp<input type="radio" id="repDestination" name="repDestination" value="2" 
	<s:if test="repDestination == 2">checked="checked"</s:if> onchange="javascript:displayDocs(this.value);"/>
	Consignes Quotidiennes<br/> 
	&nbsp<input type="radio" id="repDestination" name="repDestination"  value="3" 
	<s:if test="repDestination == 3">checked="checked"</s:if> onchange="javascript:displayDocs(this.value);"/>
	Consignes Inter-Equipes<br/>
	<br/> 
	<div id="treeDiv" class="demo" style='overflow-Y:scroll;height:600px;overflow-Y:auto;'></div>
	</s:form>
	</div>
	<s:form id="downloadForm" theme="simple" action="downloadDocumentAction">
			<s:hidden id="inputPath" name="inputPath" value=""/>
	</s:form>
</body>
</html>