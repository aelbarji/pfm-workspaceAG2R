<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			<div class="contentTableBottom">
				<span class="champObligatoire">* Champs Obligatoires</span>
				<div class="pageRight">
					<s:a href="#" onclick="javascript:retour();" cssClass="boutonRetour">Retour</s:a>
					<s:a href="#" onclick="javascript:valider();" cssClass="boutonValider">Valider</s:a>
					<c:if test="${param.validerEtEnvoyer == 'true' }" >
						<s:a href="#" onclick="javascript:validerEtEnvoyer();" cssClass="boutonValiderEtEnvoyer">Valider et Envoyer</s:a>
					</c:if>
				</div>
			</div>
