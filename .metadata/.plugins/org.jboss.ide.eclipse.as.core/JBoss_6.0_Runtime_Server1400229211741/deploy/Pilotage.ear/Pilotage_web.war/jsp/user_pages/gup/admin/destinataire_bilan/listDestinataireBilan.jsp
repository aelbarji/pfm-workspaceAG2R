<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">

	function ajouter() {
		submitData("redirectCreateAdminDestinataireBilan.action");
	}

	function supprimer(id){
		if(confirm("Etes vous sur de vouloir supprimer le destinataire ?")){
			changeData('selectedID', id);
			submitData('DeleteDestinataireBilan.action');
		}
	}
	
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('DST_BIL_L')" /></s:param>
	</s:include>
	<div class="contentTable" >
		<s:form id="mainForm" theme="simple" action="showAdminDestinataireBilan">
			<s:token></s:token>
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage"/>
			<s:hidden name="nrPages" value="%{pagination.nrPages}" />
			<s:hidden name="selectedID" id="selectedID"/>
			<s:if test="#session.USER_DROITS.contains('DEST_BIL_ADD')">
				<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
			</s:if>
			<br/>
		<table class="dataTable" rules="all">
				<col width="19%" />
				<col width="20%"/>
				<col width="30%" />
				<col width="10%" />
				<col width="10%" />
				
				<thead>
					<tr>
						<td colspan="6">
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
					<tr>
						<th class="titreColonne">Nom</th>
						<th class="titreColonne">Prénom</th>
						<th class="titreColonne">Mail</th>
						<th class="titreColonne">Cc</th>
						<th class="titreColonne">Actions</th>
					</tr>
				</thead>
				<tbody>
				<s:if test="destBil.isEmpty()">
						<tr>
							<td colspan="6" class="emptyListText">
								Aucun destinataire
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="destBil" status="status">
							<tr>
								<td align="center"> <s:property value="%{destinataire.nom}"/></td>
								<td align="center"> <s:property value="%{destinataire.prenom}"/></td>
								<td align="center"> <s:property value="%{destinataire.mail}"/></td>
								<s:if test="cc == 0">
								<td align="center"> Non</td>
								</s:if>
								<s:else><td align="center"> Oui</td></s:else>
								<td align="center"> 
									<s:if test="#session.USER_DROITS.contains('DEST_BIL_DEL')">
										<s:a href="#" onclick="javascript:supprimer('%{id}');">
												<img class="icone"  alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
										</s:a>
									</s:if>
								</td>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="6">
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
				</tfoot>
		</table>
		</s:form>
	</div>
</body>
</html>