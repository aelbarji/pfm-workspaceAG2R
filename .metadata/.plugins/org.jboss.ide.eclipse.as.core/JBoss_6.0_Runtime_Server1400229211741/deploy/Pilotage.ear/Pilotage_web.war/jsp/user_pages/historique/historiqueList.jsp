<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
	
		//reinitialisation des filtres comme à l'entrée de la page
		function reinitFilterValues() {
			document.getElementById("mainForm").filtreAction.value = '<s:property value="filtreAction" escape="false"/>';
			document.getElementById("mainForm").filtreUtilisateur.value = '<s:property value="filtreUtilisateur" />';
			document.getElementById("mainForm").filtreModule.value = '<s:property value="filtreModule" escape="false"/>';
		}
	
		//action à la recherche avec filtre
		function lancerRecherche() {
			document.getElementById("mainForm").page.value = '1';	
			document.getElementById("mainForm").submit();
		}

        //reinitialisation des filtres 
		function reinitialiserLesFiltres(){
			document.getElementById("mainForm").filtreAction.value = '';
			document.getElementById("mainForm").filtreUtilisateur.value = '-1';
			document.getElementById("mainForm").filtreModule.value = '';
			lancerRecherche();
		}
		
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('HIST')" /></s:param>
	</s:include>
	
	<s:if test="filtreJson != null">
		<div id="filtreString" class="filtreString">
			<u><b>Filtres</b></u> <s:property value="%{filtreString}" /><br/>
			<a href="#" onclick="javascript:reinitialiserLesFiltres();">
				<img class="icone" alt="Réinitialiser les filtres" title="Réinitialiser les filtres" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" />
			</a>
		</div>
	</s:if>

	<div class="contentTable" >
		<table class="dataTable" rules="all">
			<col width="20%"/>
			<col width="40%"/>
			<col width="20%"/>
			<col width="20%"/>
			<thead>
				<tr>
					<td colspan="4">
						<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
					</td>
				</tr>
				
				<s:form id="mainForm" action="showHistoriqueAction" theme="simple">
				<tr>
					<td>
						<s:hidden id="selectRow" name="selectRow" value=""/>
						<s:hidden name="sort" id="sort" />
						<s:hidden name="sens" id="sens" />
						<s:hidden name="page" id="page" />
						<s:token></s:token>
						<s:hidden name="nrPerPage" id="nrPerPage"/>
						<s:hidden name="nrPages" value="%{pagination.nrPages}" />
						<s:hidden name="validForm" value="1" />
					</td>
					<td align="left">Filtre action :<br>
						<s:textfield id="filtreAction" name="filtreAction" maxlength="40" size="35" onchange="javascript:lancerRecherche();"/>
					</td>
					<td align="left">Filtre utilisateur :<br> 
						<s:select id="filtreUtilisateur" name="filtreUtilisateur" list="%{listUsers}" listKey="id" listValue="nom + ' ' + prenom" headerKey="-1" headerValue="" onchange="javascript:lancerRecherche();"/>
					</td>	
					<td align="left">Filtre module :<br>
						<s:textfield id="filtreModule" name="filtreModule" maxlength="40" size="35" onchange="javascript:lancerRecherche();"/>
					</td>
				</tr>
				</s:form>
				
				<tr valign="middle">
					<td class="titreColonne" >
						<a href="#" onclick="changeSort('dateAction');">Date</a>
						<s:if test="sens == 'asc' && sort == 'dateAction'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if> 
						<s:elseif test="sens == 'desc' && sort == 'dateAction'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
						
					</td>
					<td class="titreColonne" >
						<a href="#" onclick="changeSort('action');">Action</a>
						<s:if test="sens == 'asc' && sort == 'action'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if>
						<s:elseif test="sens == 'desc' && sort == 'action'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</td>
					<td class="titreColonne" >
						<a href="#" onclick="changeSort('utilisateur');">Utilisateur</a>
						<s:if test="sens == 'asc' && sort == 'utilisateur'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if>
						<s:elseif test="sens == 'desc' && sort == 'utilisateur'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</td>
					<td class="titreColonne" >
						<a href="#" onclick="changeSort('module');">Module</a>
						<s:if test="sens == 'asc' && sort == 'module'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
						</s:if>
						<s:elseif test="sens == 'desc' && sort == 'module'">
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
						</s:elseif>
						<s:else>
							<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
						</s:else>
					</td>
				</tr>
			</thead>
			<tbody>
				<s:if test="%{listHistorique.size() == 0}">
					<tr>
						<td class="emptyListText" colspan="4">
							Aucune historique n'a été trouvée
						</td>
					</tr>
				</s:if>
	
				<s:else>
					<s:iterator value="listHistorique">
						<tr>
							<td align="center">&nbsp;&nbsp;<s:date name="%{dateAction}" format="dd/MM/yyyy HH:mm" /> </td>
							<td align="left">&nbsp;&nbsp;<s:property value="action"/> </td>
							<td align="left">&nbsp;&nbsp;<s:property value="utilisateur.nom"/>&nbsp;<s:property value="utilisateur.prenom"/> </td>
							<td align="left">&nbsp;&nbsp;<s:property value="module"/> </td>
						</tr>
					</s:iterator>
				</s:else>
			</tbody>
		</table>
	</div>
</body>
</html>