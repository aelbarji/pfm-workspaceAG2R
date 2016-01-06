<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function retour(){
			submitData('showConsigneBbrAction.action');
		}
	
		function valider(){
			if(document.getElementById("origine").value.replace(/^\s+/g,'').replace(/\s+$/g,'')  == ""){
				alert("Veuillez entrer une origine pour BBr.");
				document.getElementById("origine").focus();
				return false;
			}
			if(document.getElementById("composant").value.replace(/^\s+/g,'').replace(/\s+$/g,'')  == ""){
				alert("Veuillez entrer un composant pour BBr");
				document.getElementById("composant").focus();
				return false;
			}
			if(document.getElementById("localisation").value == ""){
				alert("Veuillez choisir un fichier de consigne pour BBr.");
				document.getElementById("localisation").focus();
				return false;
			}			
			changeData("priority",setPriority(document.getElementById("composant")));
			submitData('createConsigneBbrAction.action');
		}
		
		function setPriority(composant) {
			var nom = composant.value.split("_");
			return (document.getElementById("type").value == "" || document.getElementById("type").value == null)?nom.length+".0":nom.length+".1";
		}

		function checkRate(input) {
			if(!/^[0-9]+(\.{0,1}|,{0,1})[0-9]{0,2}$/.test(input.value) && input.value != "") {
				alert("La priorité de bbr n'accepte que les nombres avec 2 décimales au maximum.");
				input.focus();
				return false;
			}
			return true;
		}
		
		function showDocumentationTree(){
			var url = "showDocumentationTree.action";
			var iWidth = 600; 
		    var iHeight = 600;
		    var iTop = Math.round((screen.availHeight-iHeight)/2);
		    var iLeft = Math.round((screen.availWidth-iWidth)/2);
		    var returnValue = window.showModalDialog(url, document.getElementById("localisation").value, "dialogHeight:" + iHeight + "px; dialogWidth:"+iWidth+"px; toolbar:no; menubar:no; scrollbars:no; resizable:no; location:no; status:no; dialogLeft:" + iLeft + "px; dialogTop:" + iTop +"px;");    
			document.getElementById("localisation").value = returnValue;
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp" >
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('BBR_ADD')" /></s:param>
	</s:include>
	
	<div class="contentTable">
		<s:form theme="simple" id="mainForm">
			<s:hidden name="sort" 		id="sort" />
			<s:hidden name="sens" 		id="sens" />
			<s:hidden name="page" 		id="page" />
			<s:hidden name="nrPerPage" 	id="nrPerPage"/>
			<s:hidden name="nrPages" 	id="nrPages" />
			<s:token></s:token>
			<s:hidden name="filtreOrigine" 			id="filtreOrigine" />
			<s:hidden name="filtreComposant" 		id="filtreComposant" />
			<s:hidden name="filtreType" 			id="filtreType" />
			<s:hidden name="filtreLocalisation" 	id="filtreLocalisation" />
			<s:hidden name="priority"				id="priority" />
								
			<table class="formTable" width="600px">
				<col width="35%"/>
				<col width="65%"/>
				<tr>
					<td align="left">BBrOrigine <span class="starObligatoire">*</span> : </td>
					<td align="left"><s:textfield id="origine" name="origine" maxlength="50" size="20"/></td>
				</tr>
				<tr>
					<td align="left">BBrComposant <span class="starObligatoire">*</span> : </td>
					<td align="left"><s:textfield id="composant" name="composant" maxlength="100" size="40"/></td>
				</tr>
				<tr>
					<td align="left">BBrType : </td>
					<td align="left"><s:textfield id="type" name="type" maxlength="30" size="10"/></td>
				</tr>
				<tr>
					<td align="left">Localisation <span class="starObligatoire">*</span> : </td>
					<td align="left">
						<a href="#" onclick="javascript:showDocumentationTree();">
							<textarea readonly="readonly" rows="1" style="width:90%;display=none" id="localisation" name="localisation"></textarea>
						</a>
					</td>
				</tr>
				<tr>
					<td colspan="2"><s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom.jsp" /></td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>