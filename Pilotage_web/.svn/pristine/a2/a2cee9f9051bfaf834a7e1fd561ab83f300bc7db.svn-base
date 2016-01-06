<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function retour(){
			submitData('detailServiceMeteoAction.action');
		}
	
		function modifier(){
			submitData('showModifyPlageFonctServiceAction.action');
		}
		
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MTG_IND_D')" /></s:param>
	</s:include>
	<div class="contentTable" style="width:55%">
		<s:form theme="simple" id="mainForm">
			<s:hidden name="selectRow" id="selectRow"/>
			<s:hidden name="idIndic" id="idIndic" />
			<s:hidden name="typeIndic" id="typeIndic" />
			<s:hidden name="detail" id="detail" value="true"/>
			
			<table class="formTable">
				<tr>
					<td align="left">Nom du Service <span class="starObligatoire"></span> :</td>
					<td align="left"><b><s:property value="nomService"/></b></td>
				</tr>	
				<tr>
					<td align="left">Nom de l'Environnement <span class="starObligatoire"></span> :</td>
					<td align="left"><b><s:property value="nomEnvir"/></b></td>
				</tr>
			</table>
			
			<div class="titreTable"><b>Plage de Fonctionnement de l'Indicateur</b></div>
			
			<table class="dataTable" rules="all" border="1">
				<thead>
						<tr>
							<th class="titreColonne">Heure Début</th>
							<th class="titreColonne">Heure Fin</th>
							<th class="titreColonne">Zone</th>
							<s:if test='typeIndic.format.format.equals("liste")'>
								<th class="titreColonne">Etat désiré</th>
							</s:if>
							<s:if test='typeIndic.format.format.equals("reel")'>
								<th class="titreColonne">Etat perturbé</th>
								<th class="titreColonne">Etat KO</th>
							</s:if>
							<th class="titreColonne">Jours de fonctionnement</th>
						</tr>				
				</thead>
				<tbody>
					<s:if test="listPlageFonct.isEmpty()">
						<tr>
							<td colspan="9" class="emptyListText">
								Aucune Plage de Fonctionnement associé à cet indicateur
							</td>
						</tr>
					</s:if>
					<s:else>
					<s:iterator value="listPlageFonct" status="stat">
						<tr>
							<td align="center">
								&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="heureDebut"/>
							</td>
							<td align="center">
								&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="heureFin"/>
							</td>
							<td align="center">
								&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="zone"/>
							</td>
							<s:if test='%{typeIndic.format.format.equals("liste")} || %{typeIndic.format.format.equals("reel")}'>
								<td align="center">
									&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="etatDesire"/>
								</td>
							</s:if>
							<s:if test='%{typeIndic.format.format.equals("reel")}'>
								<td align="center">
									>&nbsp;<s:property value="perturbe_min"/><br>
									<&nbsp;<s:property value="perturbe_max"/><br>
								</td>
								<td align="center">
									>&nbsp;<s:property value="ko_min"/><br>
									<&nbsp;<s:property value="ko_max"/><br>
								</td>
							</s:if>
							<td align="center">
								 <table>
									<tr>
									 	<td align="center">L</td>
									 	<td align="center">Ma</td>
									 	<td align="center">Me</td>
										<td align="center">J</td>
										<td align="center">V</td>
										<td align="center">S</td>
										<td align="center">D</td>
										<td align="center">Ferié</td>
									</tr>
									<tr>
										<td><img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/<s:property value="lundi"/>.png"></td>
										<td><img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/<s:property value="mardi"/>.png"></td>
										<td><img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/<s:property value="mercredi"/>.png"></td>
										<td><img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/<s:property value="jeudi"/>.png"></td>
										<td><img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/<s:property value="vendredi"/>.png"></td>
										<td><img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/<s:property value="samedi"/>.png"></td>
										<td><img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/<s:property value="dimanche"/>.png"></td>
										<td><img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/<s:property value="ferie"/>.png"></td>
										<!-- <td><input name="checkbox" type="checkbox" value="ferie" checked disabled/></td>-->
									</tr>
								</table>
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
					<s:if test="#session.USER_DROITS.contains('MTG_IND_MOD')">
						<s:a href="#" onclick="javascript:modifier();" cssClass="boutonValider">Modifier</s:a>
					</s:if>
				</p>
			</div>
		</s:form>
	</div>
</body>
</html>