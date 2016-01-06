<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function valider() {
			var mainForm = document.getElementById("mainForm");
			if(mainForm.bug.value.replace(/^\s+/g,'').replace(/\s+$/g,'').length == 0) {
				alert("Veuillez entrer le texte");
				mainForm.bug.focus();
				return false;
			}
			mainForm.submit();
		}

		function retour(){
			submitData('showFeedbackAction.action');
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp" >
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('FEED_ADD')" /></s:param>
	</s:include>
	
	<div class="contentTable">
		<s:form id="mainForm" theme="simple" action="createFeedbackAction">
			<s:token></s:token>
			<s:hidden name="filtreFeedbacks" id="filtreFeedbacks" />
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage" />
			<s:hidden name="nrPages" id="nrPages" />
			
			<table class="formTable">
				<tr>
					<td valign="top">Feedback <span class="starObligatoire">*</span> : </td>
					<td><s:textarea id="bug" name="bug" cols="80" rows="10"></s:textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom.jsp" />
					</td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>