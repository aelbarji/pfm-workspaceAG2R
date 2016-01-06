<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Choix d'un document</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/pilotage.css"/>
	<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script src="js/jqueryFileTree.js" type="text/javascript"></script>
	<link href="js/jqueryFileTree.css" media="screen" rel="stylesheet" type="text/css" />	
	
	<script type="text/javascript">
			
			$(document).ready( function() {
				
				$('#treeDiv').fileTree( {
					root: '<s:property value="dirRoot" />', 
					script: '/pilotage/jsp/user_pages/checklist/admin/jqueryFileTree.jsp'
				}, 
				function(file) {
				//éventuellement, ajuster le nom de fichier, de base, c'est le chemin entier qui est enregistré
				// Ajustement du nom au chemin relatif de dirRoot...
					fileSelected = file.replace('<s:property value="dirRoot" />',"/").replace('^\s+|\s+$','');
					window.returnValue = fileSelected;
				});
			});

		    function getSelectedValue() {
				window.close();
			}
				
	</script>
	
	
	</head>
	<body>
		
		<div id="treeDiv" class="demo"> 		
		</div>
		<s:a href="#" onclick="javascript:getSelectedValue();" cssClass="boutonValider">OK</s:a>
	</body>
</html>
	