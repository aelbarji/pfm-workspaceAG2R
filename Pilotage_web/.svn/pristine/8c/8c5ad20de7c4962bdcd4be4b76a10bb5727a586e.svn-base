<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function retour(){
			submitData('showServiceMeteoAction.action');
		}
	
		function modifier(){
			submitData('showModifyIndicateurServiceAction.action');
		}
		//Detail
		function detailIndicateur(idI){
			var idindic;
			idindic = parseInt(idI);
			changeData('idIndic', idindic);
			submitData('showDetailIndicateurServiceAction.action');
		}
	</script>
	
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MTG_GRM_L')" /></s:param>
	</s:include>

	<div class="contentTable" style="width:75%">
		<s:form theme="simple" id="mainForm">
		    <s:hidden name="selectRow"  id="selectRow" />
			<s:hidden name="idIndic" 		id="idIndic" value="" />
			<s:hidden name="detail" 		id="detail" 	value="true"/>
			
			<table class="formTable">
				<tr>
					<td align="left">Nom du Service <span class="starObligatoire"></span> :</td>
					<td align="left"><b><s:property value="nomService"/></b></td>
				</tr>	
				
			</table>
		
			<div class="titreTable"><b>Indicateurs</b></div>
			
			<table class="dataTable" rules="all" border="1">
				<thead>
						<tr>
							<th class="titreColonne">Environnement</th>
							<th class="titreColonne">Type</th>
							<th class="titreColonne">Automatique</th>
							<s:if test="champheure==true">
								<th class="titreColonne">Ajouter champ heure début</th>
								<th class="titreColonne">Ajouter champ heure fin</th>
							</s:if>
							<th class="titreColonne" style="width:12%">Plage de fonctionnement</th>
						</tr>				
				</thead>
				<tbody>
					<s:if test="listIndic.isEmpty()">
						<tr>
							<td colspan="9" class="emptyListText">
								Aucun Indicateur associé à ce service
							</td>
						</tr>
					</s:if>
					<s:else>
					<s:iterator value="listIndic" status="stat">
						<tr>
							<td align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="envir"/>
							</td>
							<td align="center">
								<s:property value="type"/>
							</td>
							<td align="center">
								oui <img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/<s:property value="autoOui"/>.gif">
								non <img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/<s:property value="autoNon"/>.gif">
							</td>
							<s:if test="champheure==true">
								<td align="center">
								<s:if test='!type.equals("datetime")'>
									oui <img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/<s:property value="debutOui"/>.gif">
									non <img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/<s:property value="debutNon"/>.gif">
								</s:if>
								</td>
								<td align="center">
								<s:if test='!type.equals("datetime")'>
									oui <img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/<s:property value="finOui"/>.gif">
									non <img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/<s:property value="finNon"/>.gif">
								</s:if>
								</td>
							</s:if>
							<td align="center">
								<s:if test="#session.USER_PROFIL.admin">
									<s:a href="#" onclick="javascript:detailIndicateur('%{idIndic}');">
										<img class="icone" alt="Détail" title="Détail" src="<s:property value="#session.ENSEIGNE" />/img/detail-16.png">
									</s:a>
								</s:if>
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
					<s:if test="#session.USER_DROITS.contains('MAC_MOD')">
						<s:a href="#" onclick="javascript:modifier();" cssClass="boutonValider">Modifier</s:a>
					</s:if>
				</p>
			</div>
		</s:form>
	</div>
</body>
</html>