<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			<div class="contentTableBottom">
				<span class="champObligatoire">* Champs Obligatoires</span>
				<div class="pageRight">
					<s:submit href="#" onclick="javascript:retour();" value="Retour" cssClass="boutonSubmit"></s:submit>
					<s:submit href="#" onclick="javascript:valider();" value="Valider" cssClass="boutonSubmit" ></s:submit>
					<c:if test="${param.validerEtEnvoyer == 'true' }" >
						<s:submit href="#" onclick="javascript:validerEtEnvoyer();" cssClass="boutonValiderEtEnvoyer" value="Valider et Envoyer"></s:submit>
					</c:if>
				</div>
			</div>
