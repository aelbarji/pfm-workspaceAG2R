<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/validationEngine.jquery.css">
	<script type="text/javascript" src="js/jquery.validationEngine.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/languages/jquery.validationEngine-fr.js" charset="utf-8"></script>
	
	<script type="text/javascript">
	
		// fonction de validation du formulaire
		$(document).ready(function(){
	    	$("#mainForm").validationEngine();
	  	});
	
		function valider(){
			$("#mainForm").validationEngine('validate');
		}

		function retour(){
			submitData('showConsignesAction.action');
		}
		
		function showDocumentationTree(i){
			var url = "showDocumentationTree.action";
			var iWidth = 600; 
		    var iHeight = 400;
		    var iTop = Math.round((screen.availHeight-iHeight)/2);
		    var iLeft = Math.round((screen.availWidth-iWidth)/2);
		    var returnValue = window.showModalDialog(url, document.getElementById("fichierConsigne").value, "dialogHeight:" + iHeight + "px; dialogWidth:"+iWidth+"px; dialogLeft:" + iLeft + "px; dialogTop:" + iTop +"px;");
		    if (typeof returnValue=="undefined"){
				   return false;
			   }   
			document.getElementById("fichierC").innerHTML = "<a href='javascript:downloadFile(\""+ returnValue + "\")'>" + returnValue + "</a>" ;
			document.getElementById("fichierConsigne").value =  returnValue ;
		}
		
		function downloadFile(path){
			changeData('inputPath', path);
			document.getElementById('downloadForm').submit();
		}
		
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('CSG_MOD')" /></s:param>
	</s:include>
	
	<div class="contentTable" style="width: 80%">
		<s:form id="mainForm" theme="simple" action="modifyConsigneAction" method="POST">
			<s:token></s:token>
			<s:hidden name="consigneID" id="consigneID" value="%{consigne.id}"/>
			<s:hidden name="fichierConsigne" id="fichierConsigne" value="%{consigne.fichierconsigne}"/>
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage"/>
			<s:hidden name="nrPages" id="nrPages" />
			
			<table class="formTable">
				<colgroup>
					<col width=30% />
					<col width=70% />
				</colgroup>
				<tr>
					<td align="left">Texte de la consigne <span class="starObligatoire">*</span> :</td>
					<td align="left"><s:textarea name="consigneText" cols="60" rows="5" value="%{consigne.text}" data-validation-engine="validate[required]"/></td>
				</tr>
				<tr>
					<td align="left"> fichier de consigne associé :
						<a href="#" onclick="javascript:showDocumentationTree();">
						<img class="icone2" border="0" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" />
						</a>
					</td>
					<td align="left">
						<s:div id='fichierC' name='fichierC' align='left'>
							<s:if test="%{consigne.fichierconsigne != null}"> 
								<a href='javascript:downloadFile("<s:property value="%{consigne.fichierconsigne}"/>")'><s:property value="%{consigne.fichierconsigne}"/></a>
							</s:if>
						</s:div>
					</td>			
				<tr>
				<tr>
					<td align="left">Important :</td>
					<td align="left"><s:checkbox name="important" value="%{consigne.couleur}"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom_submit.jsp" />
					</td>
				</tr>
			</table>
		</s:form>
		<s:form id="downloadForm" theme="simple" action="downloadConsigneFichierAction">
			<s:hidden id="inputPath" name="inputPath" value=""/>
		</s:form>
	</div>
</body>
</html>