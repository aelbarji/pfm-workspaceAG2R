<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		
		
		function newTab(destina){
			var form = document.getElementById("mainForm");
			form.target = "_blank";
			form.action = destina;
			form.submit();
			form.target = "_self";
		}

		
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('BL_LST')" /></s:param>
		<s:param name="titreDate" value="true"></s:param>
	</s:include>

	<div class="contentTable">
		<s:form id="mainForm" theme="simple">
			<s:token></s:token>
	
			<!--  <input type="hidden" name="selectedDate" id="selectedDate" value="<fmt:formatDate value='${selectedDate}' pattern='dd/MM/yyyy' />"></input>
			 -->
			<table class="formTable" width="800px">
				<tr>
					<td colspan="3">
						<table width="100%">
							<col width="25%"/>
							<col width="25%"/>
							<col width="50%"/>
							<tr>
								<td>&nbsp;</td>
								<td>Date : </td>
								<td>
									<sj:datepicker name="selectedDate" 
												id="pickDate" 
												displayFormat="dd/mm/yy"
												maxDate="+0" 
												showOn="focus"
												onCompleteTopics="pickDateChange">
									</sj:datepicker>								
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<table width="80%">
						<col width="50%"/>
						<col width="50%"/>
						<tr> 
							<td>&nbsp;</td> 
							<td>&nbsp;</td>
						</tr>
							<tr>
								<td align="center">
									<s:a href="#" onclick="javascript:newTab('ShowDetailBilanGup.action')" cssClass="boutonValider">Afficher</s:a>
								</td>
								<s:if test="#session.USER_DROITS.contains('ICD_BIL_ENV')">
									<td align="left">
										<s:a href="#" onclick="javascript:submitData('SendBilanIncidentsGup.action')" cssClass="boutonValider">Envoyer</s:a>
									</td>
								</s:if>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>
