<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function retour(){
			submitData('showMachineListAction.action');
		}
	
		function modifier(){
			submitData('showModifyMachineListAction.action');
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MAC_DET')" /></s:param>
	</s:include>
	
	<div class="contentTable">
		<s:form theme="simple" id="mainForm">
			<s:hidden name="selectRow" 		id="selectRow" />
			<s:hidden name="detail" 		id="detail" 	value="true"/>
		
			<s:hidden name="sort" 		id="sort" />
			<s:hidden name="sens" 		id="sens" />
			<s:hidden name="page" 		id="page" />
			<s:hidden name="nrPerPage" 	id="nrPerPage"/>
			<s:hidden name="nrPages" 	id="nrPages" />

			<s:hidden name="filtreNom" 				id="filtreNom" />
			<s:hidden name="filtreIP" 				id="filtreIP" />
			<s:hidden name="filtreType" 			id="filtreType" />
			<s:hidden name="filtreSite" 			id="filtreSite" />
			<s:hidden name="filtreOs" 				id="filtreOs" />
			<s:hidden name="filtreInterlocuteur" 	id="filtreInterlocuteur" />
			<s:hidden name="filtreDomaine" 			id="filtreDomaine" />
			<s:hidden name="filtreEnvironnement" 	id="filtreEnvironnement" />
			
			<s:hidden name="deleteAppli" id="deleteAppli" value="-1"/>

			<table class="formTable" width="800px">
				<col width="15%"/>
				<col width="35%"/>
				<col width="15%"/>
				<col width="35%"/>
				<tr>
					<td align="left"><u>Nom</u> : </td>
					<td align="left"><s:property value="nom"/></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td align="left"><u>Type</u> : </td>
					<td align="left"><s:property value="nomType"/></td>
					<td align="left"><u>Site</u> : </td>
					<td align="left"><s:property value="nomSite"/></td>
				</tr>
				<tr>
					<td align="left"><u>OS</u> : </td>
					<td align="left"><s:property value="nomOS"/></td>
					<td align="left"><u>Service gérant</u> : </td>
					<td align="left"><s:property value="nomInterlocuteur"/></td>
				</tr>
				<tr>
					<td align="left"><u>Domaine</u> : </td>
					<td align="left"><s:property value="nomDomaine"/></td>
					<td align="left"><u>Environnement</u> : </td>
					<td align="left"><s:property value="nomEnvironnement"/></td>
				</tr>
				<tr>
					<td align="left"><u>Commentaire</u> : </td>
					<td align="left"><s:property value="commentaire"/></td>
					<td></td>
					<td></td>
				</tr>
			</table>
			
			<div class="titreTable"><b>Applications</b></div>	
			<table class="dataTable" rules="all">
				<thead>
					<tr>
						<th class="titreColonne">Application</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="listApplications.isEmpty()">
						<tr>
							<td class="emptyListText">Aucune application</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listApplications" status="stat">
							<tr>
								<td align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="appName"/>
								</td>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
			</table>
			
			<div class="contentTableLeft">
				<div class="titreTable"><b>Adresses IP</b></div>
				<table class="dataTable" rules="all" id="tableIp">
					<col width="40%"/>
					<col width="60%"/>
					<thead>
						<tr>
							<th class="titreColonne">IP</th>
							<th class="titreColonne">Commentaire</th>
						</tr>
					</thead>
					<tbody>
						<s:if test="listIPs.isEmpty()">
							<tr id="textNoIP">
								<td class="emptyListText" colspan="2">Aucune adresse IP</td>
							</tr>
						</s:if>
						<s:else>
							<s:iterator value="listIPs">
								<tr>
									<td align="left">
										&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="IP"/>
									</td>
									<td align="left">
										&nbsp;&nbsp;&nbsp;&nbsp;<s:property  value="Commentaire"/>
									</td>
								</tr>
							</s:iterator>
						</s:else>
					</tbody>
				</table>
			</div>
			
			<div class="contentTableRight">
				<div class="titreTable"><b>Acc&egrave;s local à la machine</b></div>
				<table class="dataTable" rules="all" id="tableLogin">
					<col width="40%"/>
					<col width="40%"/>
					<col width="20%"/>
					<thead>
						<tr>
							<th class="titreColonne">Login</th>
							<th class="titreColonne">Mot de passe</th>
							<th class="titreColonne">Domaine</th>
						</tr>
					</thead>
					<tbody>
						<s:if test="listAccesLocal.isEmpty() && listLoginDomaine.isEmpty()">
							<tr id="textNoLogin">
								<td class="emptyListText" colspan="3">Aucun accès local</td>
							</tr>
						</s:if>
						<s:iterator value="listLoginDomaine">
							<tr>
								<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="login"/></td>
								<s:if test="#session.USER_PROFIL.pilote || #session.USER_PROFIL.admin">
									<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="password"/></td>
								</s:if>
								<s:else>
									<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;</td>
								</s:else>
								<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="domaine.nomDomaine"/></td>
							</tr>
						</s:iterator>
						<s:iterator value="listAccesLocal">
							<tr>
								<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="login"/></td>
								<s:if test="#session.USER_PROFIL.pilote || #session.USER_PROFIL.admin">
									<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="password"/></td>
								</s:if>
								<s:else>
									<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;</td>
								</s:else>
								<td align="left">
									<s:if test="domaine != '-1'">
										<s:iterator value="listMachineDomaine">
											<s:if test="domaine == id">
												&nbsp;&nbsp;&nbsp;&nbsp;<s:property value='nomDomaine'/>
											</s:if>
										</s:iterator>
									</s:if>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			
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