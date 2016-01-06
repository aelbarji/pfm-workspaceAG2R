<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<s:if test="redirect != null">
	<head>
		<meta http-equiv="Refresh" content="1;URL=<s:property value="redirect" />"/>
		<link rel="shortcut icon" type="image/x-icon" href="<s:property value="#session.ENSEIGNE" />/img/favicon.ico" />
	</head>
	<body>
		Veuillez patienter ...
	</body>
</s:if>
<s:else>
	<head>
		<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	</head>
	<body>
		<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		</s:include>
		
		<div class="contentTable">
		</div>
	</body>
</s:else>
</html>