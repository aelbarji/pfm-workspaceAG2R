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

<link rel="stylesheet" type="text/css"
	href="<s:property value="#session.ENSEIGNE" />/css/validationEngine.jquery.css">
<script type="text/javascript" src="js/jquery.validationEngine.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="js/languages/jquery.validationEngine-fr.js" charset="utf-8"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#mainForm").validationEngine();
	});

	function checkFile(e) {
		 
	    var file = e.target.files[0];
	    
	    var consFileName = file.name;
		if (consFileName != "") {
			var docExtension = consFileName.split('.')[consFileName.split('.').length - 1]
					.toLowerCase();
			var iFileSize = file.size;
	        var iConvert = (file.size / 1048576).toFixed(2);
			if (!(docExtension === "doc" || docExtension === "docx"
					|| docExtension === "pdf" || docExtension === "xls" || docExtension === "xlsx" || docExtension === "txt")|| iFileSize > 10485760) {
				message = "Format du document : " + docExtension + "\n";
				message += "Taille du document : "+ iConvert + " MB \n\n";
				message += "Ce document n'est pas autorisé.\nSeuls Formats autorisés : pdf, txt, doc, docx, xls et xlsx.\n";
				message += "Taille maximale autorisée : 10 MB";
				alert(message);
				document.getElementById("docConsigne").value = "";
			}
		}
	}

	function valider() {
			$("#mainForm").validationEngine('validate');
	}

</script>
</head>
<body>
	<div class="info" id="info">
		<s:property value="%{info}" />
	</div>
	<div class="error" id="error">
		<s:property value="%{error}" />
	</div>
	<s:actionerror />
	<div class="titrePage">
		<s:label value="Chargement de fichiers" cssClass="title"></s:label>
	</div>
	<div class="Pagecontents">
		<s:form id="mainForm" theme="simple" method="POST"
			action="uploadDocumentConsigne" enctype="multipart/form-data">
			<s:token></s:token>
			<div align="center">
				<s:a>
					<b>Sélectionner le répertoire de destination :</b>
				</s:a>
				<br /> <input type="radio" name="repDestination" value="1"
					data-validation-engine="validate[required]" />Consignes
				Permanentes<br /><input type="radio" name="repDestination"
					value="2" data-validation-engine="validate[required]" />Consignes
				Quotidiennes<br /> <input type="radio" name="repDestination"
					value="3" data-validation-engine="validate[required]" />Consignes
				Inter-Equipes<br />
				<div>
					<br />
				</div>
				<s:a>
					<b>Sélectionner un document :</b>
				</s:a>
				<s:file name="docConsigne" id="docConsigne" labelposition="top"
					data-validation-engine="validate[required]" onchange="javascript:checkFile(event);"/>
				<div>
					<br />
				</div>
				<s:submit href="#" cssClass="valider" value="Envoyer"
					onclick="javascript:valider();" />
			</div>
		</s:form>
	</div>
</body>
</html>