<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<div class="plusDiv">
		<div class="plus" >
			<s:a cssClass="ajouter" href="#" onclick="javascript:ajouter()">
				<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" >&nbsp;ajouter
			</s:a>
		</div>
		<c:if test="${param.envoyer == 'true'}" >
			<div class="plusDroite" >
				<s:a cssClass="ajouter" href="#" onclick="javascript:envoyer()">
					<img border="0" src="<s:property value="#session.ENSEIGNE" />/img/envoyer-16.png" />
				</s:a>
			</div>
		</c:if>
	</div>
	<br/>
		
