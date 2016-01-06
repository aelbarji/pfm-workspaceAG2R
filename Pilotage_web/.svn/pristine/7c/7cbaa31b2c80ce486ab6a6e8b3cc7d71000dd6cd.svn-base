<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		//action de rechercher
		function effacer(){
			submitData('showConsignesRechercherAction.action');
		}
	
		function focusData(elem){
			$(elem).select();
		}
	
		function ChangeData(elem){
			var rechercherform = document.getElementById("mainForm");
			rechercherform.motCle.value = $(elem).val();
		}
	
		function downloadFile(id) {
			changeData('consigneFichierID',id);
			document.getElementById("downloadForm").submit();
		}

		$.subscribe('submitForm', function(event,data) {
			  	data.focus();
				lancer();
      	});

		function lancer() {
				var rechercherform = document.getElementById("mainForm");
				var elem = $(document.activeElement);
				rechercherform.motCle.value = elem.val();
				if(document.getElementById("validName").value != "true"){
					rechercherform.motCle.value = "";
				}
				if (rechercherform.motCle.value == "") {
					elem.focus();
					return false;
				}
				document.getElementById("mainForm").page.value = '1';
				document.getElementById("mainForm").nrPages.value = '1';
				rechercherform.submit();
  		};
		</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('CSG_REC')" /></s:param>
	</s:include>
	<div class="contentTable">
			<s:url action="rechercherMotsAction" var="recherchemots" />
			<s:form id="mainForm" action="rechercherConsignesAction" onsubmit="return lancer();" cssClass="autoselect">
				<s:hidden name="rechercherString"></s:hidden>
				<s:hidden name="sens" id="sens" />
				<s:hidden name="sort" id="sort" />
				<s:hidden name="page" id="page" />
				<s:hidden name="nrPerPage" id="nrPerPage"/>
				<s:hidden name="nrPages" value="%{pagination.nrPages}" />
				<s:hidden id="validName" name="validName" value="true"/>
				<sj:autocompleter 	id="motCle"
									name="motCle"
									label="Recherche par mot clé "
     								placeholder="Choissez un mot"
 									parentTheme="xhtml"
 									autocomplete="on"
									href="%{recherchemots}"
       								delay="50"
       								loadMinimumCount="2"
       								onChangeTopics="submitForm"
       								onFocus="focusData(this)"
        							/>
    			</s:form>
       		<div class="autoselectButton">
       			 <img class="AutoselectIcone" src="<s:property value="#session.ENSEIGNE" />/img/go.png" />        		
       		</div>	
			<div id="rechercherString" class="filtreString">
				<br>
				<s:if test="rechercherString != '' && rechercherString != null">
					<b>Mots recherchés : </b><s:property value="%{rechercherString}" />
					<s:a href="#" onclick="javascript:effacer();" cssClass="boutonValider">Effacer</s:a><br>
				</s:if>
				<br>
			</div>
			<s:if test="fichierNombre != 0 && fichierNombre != null">
				<s:if test="!cFichiers.isEmpty()">
					<div id="filtreString" class="filtreString">
						<u><b>Fichier(s) trouvé(s) de <s:property value="%{debNombre}" /> à <s:property value="%{finNombre}" /> (Total : <s:property value="%{fichierNombre}" />) </b> </u>
					</div>
				</s:if>
				<table class="dataTable" rules="all" cellpadding="4" width="100%">
			    	<tr>
			   			<td colspan="4">
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
			    		</td>
			    	</tr>
					<tr>
						<td class="titreColonne"><a href="#" onclick="changeSort('nomFichier');">Nom du fichier</a> 
							<s:if test="sens == 'asc' && sort == 'nomFichier'">
								<img class="icone"src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if>
							<s:elseif test="sens == 'desc' && sort == 'nomFichier'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</td>
						<td class="titreColonne" colspan="3"><a href="#" onclick="changeSort('localisation');">Localisation</a>
							<s:if test="sens == 'asc' && sort == 'localisation'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_asc.png" />
							</s:if>
							<s:elseif test="sens == 'desc' && sort == 'localisation'">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri_desc.png" />
							</s:elseif>
							<s:else>
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/tri.png" />
							</s:else>
						</td>
					</tr>
					<s:if test="cFichiers.isEmpty()">
						<tr>
							<td colspan="2" class="emptyListText">Aucun fichier trouvé</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="cFichiers">
							<tr>
								<td align="left">&nbsp;&nbsp;<a href="#" onclick="javascript:downloadFile('<s:property value="%{id}" />');"> <s:property value="nomFichier" /></a>
								</td>
								<td align="left">&nbsp;&nbsp;<a href="#" onclick="javascript:downloadFile('<s:property value="%{id}" />');"> <s:property value="localisation" /></a>
								</td>
							</tr>
						</s:iterator>
					</s:else>
					<tr>
				    	<td colspan="4">
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
				    	</td>
					</tr>
				</table>
			</s:if>
		<s:form id="downloadForm" theme="simple" action="downloadConsigneFichierAction">
			<s:hidden id="consigneFichierID" name="fichierID"/>
		</s:form>
	</div>
</body>
</html>