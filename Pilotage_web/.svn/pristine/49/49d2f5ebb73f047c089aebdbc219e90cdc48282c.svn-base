<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript" src="js/time/jquery.timeentry.js"></script>
	<script type="text/javascript" src="js/time/jquery.timeentry-fr.js"></script>
	<script type="text/javascript" src="js/time/jquery.mousewheel.min.js"></script>
	<script type="text/javascript">

		$(function() {
			$('#heure').timeEntry({show24Hours: true , spinnerImage: ''});
		});
	
		function valider(){
			submitData('modifyAppelAstreinteAction.action');
		}

		function retour(){
			submitData('showAppelAstreinteAction.action');
		}
		
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('AST_APP_M')" /></s:param>
	</s:include>
	
	<div class="contentTable" style="width:60%">
		<s:form id="mainForm" theme="simple" method="POST">
			<s:hidden id="selectRow" name="selectRow"/>
			<s:hidden id="last_provenance" name="last_provenance"/>
			<s:hidden id="provenance" name="provenance"/>
			<s:hidden id="astreinte" name="astreinte"/>
			<s:hidden id="incident" name="incident"/>
			<s:hidden id="validAst" name="validAst"/>
			<s:hidden id="date" name="date"/>
			<s:hidden id="typeSelected" name="typeSelected"/>
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage"/>
			<s:hidden name="nrPages" id="nrPages" />
			<s:hidden name="pageIncident" id="pageIncident" />
			<s:hidden name="nrPerPageIncident" id="nrPerPageIncident" />
			<s:hidden name="nrPagesIncident" id="nrPagesIncident" />
		
			<div class="titreCenter">Appel du <b><s:property value="date"/></b> de l'astreinte <b><s:property value="nomAstreinte" /></b>
			pour l'incident de r&eacute;f&eacute;rence <b><s:property value="incident"/></b></div><br/>
			
			<table class="formTable" border="0">
				<tr>
					<td align="left">Heure <span class="starObligatoire">*</span> :</td>
					<td align="left"><input type="text" name="heure" id="heure" size="6" value="${heure}"/></td>
				</tr>
				<tr>
					<td align="left">Statut <span class="starObligatoire">*</span> :</td>
					<td align="left"><s:select list="%{statuts}" id="statut" listKey="id" listValue="statut" name="statut" key="%{statut}"/></td>
				</tr>
				<tr>
					<td align="left">Commentaire :</td>
					<td align="left" colspan="5">
						<s:textarea name="commentaire" id="commentaire" cols="90" rows="10"></s:textarea>
					</td>
				</tr>
				<tr align="center">
					<td colspan="6">
						<div class="contentTableBottom">
							<span class="champObligatoire">* Champs Obligatoires</span>
							<div class="pageRight">
								<s:a href="#" onclick="javascript:retour();" cssClass="boutonRetour">Retour</s:a>
								<s:a href="#" onclick="javascript:valider();" cssClass="boutonValider">Valider</s:a>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>