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

		function retour(){
			submitData('showBilanColonne.action')
		}


		function changeBilan(){
			submitData("redirectModifyBilanColonne.action");
		}		

		function ajouter() {
			document.getElementById("tab2").style.visibility = "visible";
			document.getElementById("tab2").style.display = "table-row";
		}

		function ajouter2(){
			var i=0;
			var nb = document.getElementById("nbColonnes").value;
			for(i=0 ; i< nb; i++){
				if(document.getElementById("incidentColonne"+i).value == document.getElementById("incidentColonneAj").value){
						alert("Vous ne pouvez pas affecter deux fois la même colonne");
						return false;
				}
			}
			i=0;
			
			for(i=0 ; i< nb; i++){
				var v = parseInt(document.getElementById("nbColonnes").value) + parseInt(1);
				if(document.getElementById("positionAj").value > v){
					alert("Veuillez mettre une position compris entre 1 et " + v);
					return false;
				}
			}

			var total = 0;
			for(i=0; i<nb; i++){
					total = parseInt(total) + parseInt(document.getElementById("taille"+i).value);
			}
			total = parseInt(total) + parseInt(document.getElementById("tailleAj").value);
			if(total > 100){
				var p = parseInt(total - 100);
				alert("Veuillez diminuer " + p + "% d'une autre colonne pour pouvoir affecter " + document.getElementById("tailleAj").value + " à la colonne voulue");
				document.getElementById("tailleAj").value = parseInt(document.getElementById("tailleAj").value) - p;
			}	

			if(isNaN(document.getElementById("positionAj").value) == true){
				alert("La position n'est pas un nombre");
				return false;
			}

			if(isNaN(document.getElementById("tailleAj").value) == true){
				alert("La taille n'est pas un nombre");
				return false;
			}

			if(document.getElementById("positionAj").value == ""){
				alert("Veuillez renseigner une position.");
				return false;
			}
			if(document.getElementById("tailleAj").value == ""){
				alert("Veuillez renseigner une taille.");
				return false;
			}
			if(document.getElementById("nomDsBilanAj").value == ""){
				alert("Veuillez renseigner un nom de colonne.");
				return false;
			}

			if(document.getElementById("incidentColonneAj").value == '-1'){
				alert("Veuillez renseigner une colonne.");
				return false;
			}
			submitData("createBilanColonne.action");
		}

		function modify(id, index){ 		
			var nb = parseInt(document.getElementById("nbColonnes").value);

			var i=0;
			for(i=0 ; i< nb; i++){
				if(i != index && document.getElementById("incidentColonne"+i).value == document.getElementById("incidentColonne"+index).value){
						alert("Vous ne pouvez pas affecter deux fois la même colonne");
						return false;
				}
			}
			
			i=0;
			for(i=0; i< nb; i++){
				if(document.getElementById("position"+index).value > nb){
					var v = parseInt(nb);
					if(v == 1){
						alert("Veuillez mettre la position 1");
					}
					else alert("Veuillez mettre une position compris entre 1 et " + v);
					return false;
				}
			}	
			var total = 0;
			for(i=0; i<nb; i++){
					total = parseInt(total) + parseInt(document.getElementById("taille"+i).value);
			}
			if(total > 100){
				var p = parseInt(total - 100);
				alert("Veuillez diminuer de " + p + "% dans une autre colonne");
				document.getElementById("taille"+index).value = parseInt(document.getElementById("taille"+index).value) - parseInt(p);
			}		
			
			if(isNaN(document.getElementById("position"+index).value) == true){
				alert("La position n'est pas un nombre");
				return false;
			}

			if(isNaN(document.getElementById("taille"+index).value) == true){
				alert("La taille n'est pas un nombre");
				return false;
			}
			
			changeData('selectedID', id);
			document.getElementById("position").value = document.getElementById("position"+index).value;
			document.getElementById("taille").value = document.getElementById("taille"+index).value;
			document.getElementById("nomDsBilan").value = document.getElementById("nomDsBilan"+index).value;
			document.getElementById("incidentColonne").value = document.getElementById("incidentColonne"+index).value;
			submitData('modifyBilanColonne.action');	
		}

		function init(){
			document.getElementById("tab2").style.visibility = "hidden";
			document.getElementById("tab2").style.display = "none";
		}

		function supprimer(id){
			if(confirm("Etes vous sur de vouloir supprimer la colonne du Bilan ?")){
				changeData('selectedID', id);
				submitData('deleteBilanColonne.action');
			}
		}
		
	</script>
</head>
<body onload="javascript:init()">
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
			<s:hidden name="position" id="position"/>
			<s:hidden name="taille" id="taille"/>
			<s:hidden name="nomDsBilan" id="nomDsBilan"/>
			<s:hidden name="incidentColonne" id="incidentColonne"/>
			<s:hidden name="nbColonnes" id="nbColonnes"/>
			
				<s:if test="#session.USER_DROITS.contains('BIL_COL_ADD')">
					<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
				</s:if>
				
				<s:select name="bilanSelected" id="bilanSelected" list="listBilan" listKey="id" listValue="libelle"
					cssStyle="width:150px;" theme="simple" onchange="javascript:changeBilan();"></s:select>
				
			<table class="dataTable" rules="all" id="tableau">
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
						<tr class="incidentEnCours" id="tab">
								<td align="center"><s:textfield id="nomDsBilan%{#status.index}" value="%{nomDsBilan}"/></td>
								<td align="center"><s:textfield id="position%{#status.index}" value="%{position}"/></td>
								<td align="center"><s:textfield id="taille%{#status.index}" value="%{taille}"/></td>
								
								<td align="center">
									<s:select id="incidentColonne%{#status.index}" name="incidentColonne%{#status.index}" value="%{incidentColonne.id}" list="listIncidentCol" listKey="id" 
									listValue="description" headerValue="" headerKey="-1" cssStyle="width:150px;" theme="simple">
									</s:select>
								</td>
							
								<td align="center">
									<s:if test="#session.USER_DROITS.contains('BIL_COL_MOD')">
										<s:a href="#" onclick="javascript:modify('%{id}', '%{#status.index}');">
												<img class="icone"  alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/valider-16.png" />
										</s:a>
									</s:if>
									<s:if test="#session.USER_DROITS.contains('BIL_COL_DEL')">
									<s:a href="#" onclick="javascript:supprimer('%{id}');">
											<img class="icone"  alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
									</s:a>
								</s:if>
								</td>
							</tr>
							
							</s:iterator>
							</s:else>
							<tr class="incidentEnCours" id="tab2">
								<td align="center"><s:textfield name="nomDsBilanAj" id="nomDsBilanAj"/></td>
								<td align="center"><s:textfield name="positionAj" id="positionAj"/></td>
								<td align="center"><s:textfield name="tailleAj" id="tailleAj"/></td>
								
								<td align="center">
									<s:select id="incidentColonneAj" name="incidentColonneAj" list="listIncidentCol" listKey="id" 
									listValue="description" headerValue="" headerKey="-1" cssStyle="width:150px;" theme="simple">
									</s:select>
								</td>
							
								<td align="center">
									<s:if test="#session.USER_DROITS.contains('BIL_COL_MOD')">
										<s:a href="#" onclick="javascript:ajouter2();">
												<img class="icone"  alt="Ajouter" title="Ajouter" src="<s:property value="#session.ENSEIGNE" />/img/valider-16.png" />
										</s:a>
									</s:if>
									<s:if test="#session.USER_DROITS.contains('BIL_COL_DEL')">
										<s:a href="#" onclick="javascript:supprimer('%{id}');">
												<img class="icone"  alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
										</s:a>
									</s:if>
								</td>
							</tr>
					
					
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5">
							<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
						</td>
					</tr>
					<tr>
					
					</tr>
				</tfoot>
			</table>
			
			<div class="contentTableBottom">
				<div class="pageRight">
					<s:a href="#" onclick="javascript:retour();" cssClass="boutonRetour">Retour</s:a>
				</div>
			</div>
			
		</s:form>
	</div>
</body>
</html>
