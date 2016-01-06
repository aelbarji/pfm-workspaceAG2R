<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />

	
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/validationEngine.jquery.css">
	<script type="text/javascript" src="js/jquery.validationEngine.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/languages/jquery.validationEngine-fr.js" charset="utf-8"></script>
	
	<link rel="stylesheet" type="text/css" href="js/time/jquery.timeentry.css"/>
	<script type="text/javascript" src="js/time/jquery.timeentry.js"></script>
	<script type="text/javascript" src="js/time/jquery.timeentry-fr.js"></script>
	<script type="text/javascript" src="js/time/jquery.mousewheel.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#mainForm").validationEngine();
	});

	function retour() {
		submitData("showAdminDestinataireNoc.action");
	}

	function valider(){
		$("#mainForm").validationEngine('validate');
	}
	
	</script>
</head>

<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('DNOC_ADD')" /></s:param>
	</s:include>
	<div class="contentTable" style="width: 65%">
		<s:form id="mainForm" theme="simple" action="CreateAdminDestinataireNoc" method="POST">
		<s:token></s:token>
		
		<table class="formTable" width="60%">
		<tr height="30"></tr>
		<tr>
			<td width="120px">Destinataire <span class="starObligatoire">*</span> :  </td>
			<td><s:select list="%{listDest}" listKey="id" listValue="nom + ' ' + prenom" name="destinataire" id="destinataire" emptyOption="true" data-validation-engine="validate[required]"></s:select></td>
		</tr>
		<tr>
		<td align="left">Cc : </td>
			<td align="left">
				<s:checkbox name="cc"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
					<s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom_submit.jsp" />
			</td>
				</tr>
		
		</table>
		</s:form>
	</div>
</body>