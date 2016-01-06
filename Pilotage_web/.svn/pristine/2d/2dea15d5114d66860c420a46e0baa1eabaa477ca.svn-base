<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function retour(){
			submitData('showGroupeMeteoAction.action');
		}
	
		function modifier(){
			submitData('showModifyGroupeMeteoAction.action');
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MTG_GRM_D')" /></s:param>
	</s:include>
	<div class="contentTable" style="width:30%">
		<s:form theme="simple" id="mainForm">
			<s:hidden name="groupeMeteoID" 		id="groupeMeteoID" />
			<s:hidden name="detail" 		id="detail" 	value="true"/>
			
			<s:hidden name="meteoID" id="meteoID" value=""/>
			
			<table class="formTable">
				<tr>
					<td align="left">Nom du Groupe Météo :</td>
					<td align="left"><b><s:property value="nomGroupeMeteo"/></b></td>
				</tr>	
				<tr>
					<td align="left">Format Excel du Groupe Météo :</td>
					<td align="left"><b><s:property value="stringFormat"/></b></td>
				</tr>
				<tr>
					<td align="left">Timezone du Groupe Météo :</td>
					<td align="left"><b><s:property value="fuseau"/></b></td>
				</tr>
			</table>
			
			<div class="titreTable"><b>Météos</b></div>
			<table class="dataTable" rules="all" border="1">
				<thead>
				
				<tr valign="middle">
						<td class="titreColonne">
							<a href="#" onclick="changeSort('nom');">Météo</a>
						</td>
				</tr>
				
				</thead>
				<tbody>
					<s:if test="listMeteos.isEmpty()">
						<tr>
							<td  class="emptyListText">
								Aucune Meteo associée à ce groupe Météo
							</td>
						</tr>
					</s:if>
					<s:else>
					<s:iterator value="listMeteos" status="stat">
						<tr>
							<td align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="meteoName"/>
							</td>
						</tr>
					</s:iterator>
					</s:else>
				</tbody>
				<tfoot>
				
				</tfoot>
			</table>
			
			<div class="titreTable"><b>Destinataires</b></div>
			<table class="dataTable" rules="all" border="1">
				<thead>
				
				<tr valign="middle">
						<td class="titreColonne">Destinataires
						</td>
				</tr>
				
				</thead>
				<tbody>
					<s:if test="listDestinataires.isEmpty()">
						<tr>
							<td  class="emptyListText">
								Aucun destinataire associé à ce groupe Météo
							</td>
						</tr>
					</s:if>
					<s:else>
					<s:iterator value="listDestinataires" status="stat">
						<tr>
							<td align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="destNom"/> <s:property value="destPrenom"/>
							</td>
						</tr>
					</s:iterator>
					</s:else>
				</tbody>
				<tfoot>
				
				</tfoot>
			</table>
			
			<div class="titreTable"><b>Horaires</b></div>
			<table class="dataTable" rules="all" border="1">
				<thead>
				
				<tr valign="middle">
						<td class="titreColonne">Horaires
						</td>
				</tr>
				
				</thead>
				<tbody>
					<s:if test="listHoraires.isEmpty()">
						<tr>
							<td  class="emptyListText">
								Aucun horaire associé à ce groupe Météo
							</td>
						</tr>
					</s:if>
					<s:else>
					<s:iterator value="listHoraires" status="stat">
						<tr>
							<td align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="horaire"/>
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
					<s:if test="#session.USER_DROITS.contains('MTG_GRM_MOD')">
						<s:a href="#" onclick="javascript:modifier();" cssClass="boutonValider">Modifier</s:a>
					</s:if>
				</p>
			</div>
		</s:form>
	</div>
</body>
</html>