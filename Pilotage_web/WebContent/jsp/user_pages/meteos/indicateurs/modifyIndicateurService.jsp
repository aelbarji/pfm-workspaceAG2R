<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function valider() {
			var mainForm = document.getElementById("mainForm");
			mainForm.submit();
		}
		
		function supprimer(id){
			var idindic;
			idindic = parseInt(id);
			changeData('deleteIndic', idindic);
			submitData('showModifyIndicateurServiceAction.action');
		}
		
		function retour(){
			submitData('detailServiceMeteoAction.action');
		}

		//Ajout
		function ajouter(){
			submitData('showCreateIndicateurServiceAction.action');
		}
		
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MTG_IND_M')" /></s:param>
	</s:include>

	<div class="contentTable" style="width: 75%">
		<s:form id="mainForm" theme="simple" action="modifyIndicateurServiceAction" method="POST">
			<s:token></s:token>
			<s:hidden name="selectRow" id="selectRow" value="%{service.id}"/>
			<s:hidden name="deleteIndic" id="deleteIndic" value="-1"/>
			<s:hidden name="indicToAdd" id="indicToAdd" value="-1"/>

			<table class="formTable">
				<tr>
					<td align="left">Nom du Service :</td>
					<td align="left"><b><s:property value="nomService"/></b></td>
				</tr>	
				
			</table>
			
			<div class="titreTable"><b>Indicateurs</b></div>
			
			<s:if test="#session.USER_DROITS.contains('MTG_MET_ADD')">
					<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
			</s:if>
			
			<table class="dataTable" rules="all" id="tableIndic">
				<thead>
					<tr>
						<th class="titreColonne">Environnment</th>
						<th class="titreColonne">Type Indicateur</th>
						<th class="titreColonne">Automatique</th>
						<s:if test="champheure==true">
							<th class="titreColonne">Ajout champ heure début</th>
							<th class="titreColonne">Ajout champ heure fin</th>
						</s:if>
						<th class="titreColonne">Supprimer</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="listIndics.isEmpty()">
						<tr>
							<td class="emptyListText" colspan="6">Aucun indicateur</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listIndics" status="stat">
							<tr>
								<td align="left">
								    &nbsp;&nbsp;<s:property value="envir" />
									<input type=hidden  id="envir<s:property value="#stat.index" />" name="envir<s:property value="#stat.index" />" value="<s:property value="envir"/>"/>
								</td>
								<td align="center">
									<s:property value="type" />
									<input type=hidden  id="type<s:property value="#stat.index" />" name="type<s:property value="#stat.index" />" value="<s:property value="type"/>"/>
								</td>
								<td align="center">
									oui<input type='radio' value='1' <s:property value="autoOui"/> id="auto<s:property value="#stat.index" />" name="auto<s:property value="#stat.index" />"/>
									non<input type='radio' <s:property value="autoNon"/> value='0' id="auto<s:property value="#stat.index" />" name="auto<s:property value="#stat.index" />"/>
								</td>
								<s:if test="champheure">
									<td align="center">
									<s:if test='!type.equals("datetime")'>
										oui<input type='radio' <s:property value="debutOui"/>  value='1' id="debut<s:property value="#stat.index" />" name="debut<s:property value="#stat.index" />"/>
										non<input type='radio' <s:property value="debutNon"/> value='0'  id="debut<s:property value="#stat.index" />" name="debut<s:property value="#stat.index" />"/>
									</s:if>
									</td>
									<td align="center">
									<s:if test='!type.equals("datetime")'>
										oui<input type='radio' <s:property value="finOui"/> value='1' id="fin<s:property value="#stat.index" />" name="fin<s:property value="#stat.index" />"/>
										non<input type='radio' <s:property value="finNon"/> value='0' id="fin<s:property value="#stat.index" />" name="fin<s:property value="#stat.index" />"/>
									</s:if>
									</td>
								</s:if>
								<td align="center">
									<a href="#" onclick="javascript:supprimer(<s:property value="idIndic"/>);">
										<img class="icone" alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
									</a>
									<input type="hidden" id="idIndic<s:property value="#stat.index" />" name="idIndic<s:property value="#stat.index" />" value="<s:property value="idIndic"/>"/>
								</td>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
			</table>
			<s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom.jsp" />
		</s:form>
	</div>
</body>
</html>