<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function supprimer(id){
			if (confirm("Voulez-vous vraiment supprimer ce valideur ?")) {
				changeData("valideurID", id);
				submitData("supprimerDerogationValideurAction.action");
			}
		}

		function valider(){
			submitData('ajouterDerogationValideurAction.action');
		}
	</script>

</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('DRG_VAL')" /></s:param> 
	</s:include>
	
	<div class="contentTable" style="width: 80%">
		<s:form id="mainForm" theme="simple">
			<s:hidden name="valideurID" id="valideurID" value=""></s:hidden>
			<s:token></s:token>
			
			<s:if test="#session.USER_DROITS.contains('DRG_VAL_ADD')">
				<table>
					<tr>
						<td><s:select name="userSelected" list="listUserNonValideurs" listValue="nom + ' ' + prenom" listKey="id"></s:select></td>
						<td><s:a href="#" onclick="javascript:valider();" cssClass="boutonValider">Valider</s:a></td>
					</tr>
				</table>
			</s:if>
			
			<table class="dataTable" rules="all">
				<col width="40%"/>
				<col width="50%"/>
				<s:if test="#session.USER_DROITS.contains('DRG_VAL_DEL')">
					<col width="10%"/>
				</s:if>
				<thead>
					<tr>
						<th class="titreColonne">Nom/Prénom</th>
						<th class="titreColonne">Email</th>
						<s:if test="#session.USER_DROITS.contains('DRG_VAL_DEL')">
							<th class="titreColonne">Action</th>
						</s:if>
					</tr>
				</thead>
				<tbody>
					<s:if test="listUserValideurs.isEmpty()">
						<tr>
							<td class="emptyListText" colspan=<s:if test="#session.USER_DROITS.contains('DRG_VAL_DEL')">"3"</s:if><s:else>"2"</s:else>>
								Aucun valideur n'est défini
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listUserValideurs">
							<tr>
								<td align="center">&nbsp;&nbsp;&nbsp;
									<s:property value="valideur.nom" />&nbsp;<s:property value="valideur.prenom" />
								</td>
								<td align="center">&nbsp;&nbsp;&nbsp;
									<s:property value="valideur.email" />
								</td>
								<s:if test="#session.USER_DROITS.contains('DRG_VAL_DEL')">
									<td align="center" valign="middle">
										<s:a href="#" onclick="javascript:supprimer('%{id}');">
											<img alt="Supprimer" title="Supprimer" class="icone" src="<s:property value="#session.ENSEIGNE"/>/img/supprimer-16.png" />
										</s:a>
									</td>
								</s:if>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
			</table>
		</s:form>
	</div>
</body>
</html>