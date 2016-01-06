<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv='Expires' content='-10'>
	<meta http-equiv='Pragma' content='No-cache'>
	<meta http-equiv='Cache-Control' content='private'>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">

		//Suppression
		function supprimer(id){
			if(confirm("Voulez-vous vraiment supprimer l'URL ?")){
				changeData("urlID", id);
				submitData("deleteURLAction.action");
			}
		}

		//Modification
		function modifier(id){
			changeData("urlID", id);
			submitData('showModifyURLAction.action');
		}

		//Ajout
		function ajouter(){
			submitData('showCreateURLAction.action');
		}

		//action à la recherche avec filtre
		function lancerRecherche() {
			document.getElementById("mainForm").page.value = '1';
			document.getElementById("mainForm").submit();
		}

		function ajouterFavori(id){
			changeData("urlID", id);
			changeData("addFavori", 1);
			submitData('showURLAction.action');
		}

		function supprimerFavori(id){
			changeData("urlID", id);
			changeData("delFavori", 1);
			submitData('showURLAction.action');
		}

		//ouvrir dans un autre onglet	
		function newTab(destina){			
			window.open(destina); return false;
		}
		
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('URL_LST')" /></s:param>
	</s:include>
	
	<div class="contentTable" style="width:70%">
		<s:form id="mainForm" method="POST" action="showURLAction" theme="simple">
			<s:token></s:token>
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage"/>
			<s:hidden name="nrPages" value="%{pagination.nrPages}" />
			<s:hidden name="urlID" id="urlID" value=""/>
			<s:hidden name="addFavori" id="addFavori" value="0"/>
			<s:hidden name="delFavori" id="delFavori" value="0"/>
	
			<div id="url_favoris">
				<s:iterator value="listURLfavoris" status="ind">
					<s:a href="#" onclick="javascript:newTab('%{adresse}');">
						<img style="padding:10px; width:170px; height:60px" src='getImageFavoriAction.action?urlID=<s:property value="id"/>'/>
					</s:a>
				</s:iterator>
			</div>
	
			<s:if test="#session.USER_DROITS.contains('URL_ADD')">
					<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
			</s:if>
			<table class="dataTable" rules="all" border="1">
				<col width="20%">
				<col width="70%">
				<thead>
				<tr>
					<td colspan="3">
						<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
					</td>
				</tr>
				<tr valign="middle">
						<td class="titreColonne">
							<a href="#" onclick="changeSort('application');">Application</a>
							<s:if test="sens == 'asc' && sort == 'application'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if> 
							<s:elseif test="sens == 'desc' && sort == 'application'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</td>
						<td class="titreColonne">
							<a href="#" onclick="changeSort('adresse');">Adresse</a>
							<s:if test="sens == 'asc' && sort == 'adresse'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if> 
							<s:elseif test="sens == 'desc' && sort == 'adresse'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</td>
						<td class="titreColonne" >Actions
						</td>
				</thead>
				<tbody>
					<s:if test="listURLpagination.isEmpty()">
						<tr>
							<td colspan="3" class="emptyListText">
								Aucune URL 
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listURLpagination" status="ind">
							<tr>
								<td>&nbsp;&nbsp;<s:property value="application" /></td>
								<td>&nbsp;&nbsp;<s:a href="#" onclick="javascript:newTab('%{adresse}');"><s:property value="adresse" /></s:a></td>
								<td class="td" align="center">
								<s:if test="#session.USER_DROITS.contains('URL_MOD')">
									<s:a href="#" onclick="javascript:modifier('%{id}');">
										<img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png">
									</s:a>
								</s:if>
								<s:if test="#session.USER_DROITS.contains('URL_DEL')">
									<s:a href="#" onclick="javascript:supprimer('%{id}');">
										<img class="icone"  alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png">
									</s:a>
								</s:if>
								<s:if test="listURLfavoris.contains(listURLpagination[#ind.index])">
									<s:a href="#" onclick="javascript:supprimerFavori('%{id}');">
										<img class="icone"  alt="Favori" title="Favori" src="<s:property value="#session.ENSEIGNE" />/img/star_full.png">
									</s:a>
								</s:if>
								<s:else>
									<s:a href="#" onclick="javascript:ajouterFavori('%{id}');">
										<img class="icone"  alt="Favori" title="Favori" src="<s:property value="#session.ENSEIGNE" />/img/star_empty.png">
									</s:a>
								</s:else>
								</td>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="3">
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
				</tfoot>
			</table>
		</s:form>
	</div>
</body>
</html>