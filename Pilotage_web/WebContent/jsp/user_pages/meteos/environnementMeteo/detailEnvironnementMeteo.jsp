<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function retour(){
			submitData('showEnvironnementMeteoAction.action');
		}

		function modifier(){
			submitData('showModifyEnvironnementMeteoAction.action');
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MTG_ENV_D')" /></s:param>
	</s:include>
	<div class="contentTable" style="width:30%">
		<s:form theme="simple" id="mainForm">
			<s:token></s:token>
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage"/>
			<s:hidden name="nrPages" id="nrPages" />
			<s:hidden name="envirID" 		id="envirID" />
			<s:hidden name="detail" 		id="detail" 	value="true"/>
			
			<table class="formTable">
				<tr>
					<td align="left">Nom de l'environnement Météo <span class="starObligatoire"></span> :</td>
					<td align="left"><b><s:property value="envirNOM"/></b></td>
				</tr>	
				<tr>
					<td align="left">Environnment technique?<span class="starObligatoire">*</span> :</td>
					<td align="left">
						oui <img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/<s:property value="techniqueOui"/>.gif">
						non <img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/<s:property value="techniqueNon"/>.gif">
					</td>
				</tr>
			</table>
			
			<div class="titreTable"><b>Caisses</b></div>
			<table class="dataTable" rules="all" border="1">
				<thead>
				
				<tr valign="middle">
						<td class="titreColonne">
							<a href="#" onclick="changeSort('nom');">Caisse</a>
						</td>
				</tr>
				
				</thead>
				<tbody>
					<s:if test="listCaisse.isEmpty()">
						<tr>
							<td  class="emptyListText">
								Aucune Caisse associée à cet environnement
							</td>
						</tr>
					</s:if>
					<s:else>
					<s:iterator value="listCaisse" status="stat">
						<tr>
							<td align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="caisseName"/>
							</td>
						</tr>
					</s:iterator>
					</s:else>
				</tbody>
				<tfoot>
				
				</tfoot>
			</table>
			
			<div class="contentTableBottom">
				<p class="boutonRight">
					<s:a href="javascript:retour();" cssClass="boutonRetour">Retour</s:a>
					<s:if test="#session.USER_DROITS.contains('MTG_ENV_MOD')">
						<s:a href="#" onclick="javascript:modifier();" cssClass="boutonValider">Modifier</s:a>
					</s:if>
				</p>
			</div>
		</s:form>
	</div>
</body>
</html>