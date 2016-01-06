<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
	
		function soumettre(id, adresse){
			changeData('selectedID', id);
			submitData(adresse);
		}		

		function ajouter() {
			reinitPageValues();
			submitData("redirectCreateIncidentsGup.action");
		}

		//reinitialisation de page comme à l'entrée de la page
		function reinitPageValues() {
			document.getElementById("mainForm").page.value = '<s:property value="page" escape="false"/>';
			document.getElementById("mainForm").nrPerPage.value = '<s:property value="nrPerPage" escape="false"/>';
			document.getElementById("mainForm").nrPages.value = '<s:property value="nrPages" escape="false"/>';
		}

		function modify(id){		
			changeData('selectedID', id);
			submitData('redirectModifyBilanColonne.action');
		}

		function changeBilan(){
			//changeData('selectedID', id);
			submitData("showBilanColonne.action");
		}

		function supprimer(id){
			if(confirm("Etes vous sur de vouloir supprimer la colonne du Bilan ?")){
				changeData('selectedID', id);
				submitData('deleteBilanColonne.action');
			}
		}
		
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('ICD_LST')" /></s:param>
	</s:include>
	
	<div class="contentTable" >
		<s:form id="mainForm" theme="simple" action="showBilanColonne">
		<s:token></s:token>
			<s:hidden name="selectedID" id="selectedID"/>
			<s:hidden name="idInc" id="idInc"/>
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage"/>
			<s:hidden name="nrPages" value="%{pagination.nrPages}" />
				<s:if test="#session.USER_DROITS.contains('BIL_COL_MOD')">
					<s:a href="#" onclick="javascript:modify('%{id}');">
						<b>Modifier</b>
					</s:a>
				</s:if>	
				<br/>
					
				<s:select name="bilanSelected" list="listBilan" listKey="id" listValue="libelle"
					cssStyle="width:150px;" theme="simple" onchange="javascript:changeBilan();"></s:select>
				
			<table class="dataTable" rules="all">
				<col width="20%" />
				<col width="20%"/>
				<col width="20%" />
				<col width="20%" />
				<col width="20%" />
				<s:if test="#session.USER_DROITS.contains('BIL_COL_MOD')">
					<col width="5%" />
				</s:if>
				<thead>
					<tr>
						<td colspan="5">
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
					<tr>
						<th class="titreColonne">Nom colonne dans bilan</th>
						<th class="titreColonne">Position</th>
						<th class="titreColonne">Taille (en %)</th>
						<th class="titreColonne">Description colonne</th>
						<th class="titreColonne">Actions</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="listColonnes.isEmpty()">
						<tr>
							<td colspan="5" class="emptyListText">
								Aucune colonne de bilan associée
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listColonnes" status="status">
						<s:if test="date_fin == null"> <tr class="incidentEnCours"> </s:if>
								<s:else><tr></s:else>
								<td align="center"><s:property value="%{nomDsBilan}"/></td>
								<td align="center"><s:property value="%{position}"/></td>
								<td align="center"><s:property value="%{taille}"/></td>
								
								<td align="center"><s:property value="%{incidentColonne.description}"/></td>
							
								<td align="center">
									<s:if test="#session.USER_DROITS.contains('BIL_COL_DEL')">
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
						<td colspan="5">
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
				</tfoot>
			</table>
		</s:form>
	</div>
</body>
</html>
