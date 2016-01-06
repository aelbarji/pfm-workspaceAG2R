<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function retour(){
			submitData('showMeteoAction.action');
		}
	
		function modifier(){
			submitData('showModifyMeteoAction.action');
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MTG_MET_D')" /></s:param>
	</s:include>
	<div class="contentTable" style="width:30%">
		<s:form theme="simple" id="mainForm">
			<s:hidden name="meteoID" 		id="meteoID" />
			<s:hidden name="detail" 		id="detail" 	value="true"/>
			
			<table class="formTable">
				<tr>
					<td align="left">Nom de la Météo <span class="starObligatoire"></span> :</td>
					<td align="left"><b><s:property value="nomMeteo"/></b></td>
				</tr>	
				
			</table>
			
			<div class="titreTable"><b>Services</b></div>
			<table class="dataTable" rules="all" border="1">
				<thead>
				
				<tr valign="middle">
						<td class="titreColonne">
							<a href="#" onclick="changeSort('nom');">Service</a>
						</td>
				</tr>
				
				</thead>
				<tbody>
					<s:if test="listService.isEmpty()">
						<tr>
							<td  class="emptyListText">
								Aucun service associé à cette Météo
							</td>
						</tr>
					</s:if>
					<s:else>
					<s:iterator value="listService" status="stat">
						<tr>
							<td align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="serviceName"/>
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
					<s:if test="#session.USER_DROITS.contains('MTG_MET_MOD')">
						<s:a href="#" onclick="javascript:modifier();" cssClass="boutonValider">Modifier</s:a>
					</s:if>
				</p>
			</div>
		</s:form>
	</div>
</body>
</html>