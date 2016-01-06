<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript" src="js/time/jquery.timeentry.js"></script>
	<script type="text/javascript" src="js/time/jquery.timeentry-fr.js"></script>
	<script type="text/javascript" src="js/time/jquery.mousewheel.min.js"></script>
	<script type="text/javascript">
		var i=0;
	
		function valider() {
			var mainForm = document.getElementById("mainForm");
			var nbPlage, j;
			nbPlage = i;
			var rempli=1;
			for(j=0;j<nbPlage;j++){
				if(document.getElementById("heureDebut"+j).value=="" || 
						document.getElementById("heureFin"+j).value=="" || 	
						document.getElementById("zone"+j).value==-1){
					if(document.getElementById("etatdesire").value==true && document.getElementById("desire"+j).value==-1 ){
					rempli=0;
					}
				}
				if(document.getElementById("lundi"+j).checked==false &&
						document.getElementById("mardi"+j).checked==false &&
						document.getElementById("mercredi"+j).checked==false &&
						document.getElementById("jeudi"+j).checked==false &&
						document.getElementById("vendredi"+j).checked==false &&
						document.getElementById("samedi"+j).checked==false &&
						document.getElementById("dimanche"+j).checked==false &&
						document.getElementById("ferie"+j).checked==false){
					rempli=0;
				}
			}

			if(i==0){
				alert("Vous devez saisir une plage de fonctionnement.");
			} 
			else if(rempli==0){
				alert("Merci de renseigner tous les champs.");
			}
			else{
				mainForm.submit();
			}
		}

		
		
		function addPlage(){
			var t = document.getElementById("tablePlage");
		    var tr = t.insertRow(t.rows.length);
		    var cell=0;
		    
		    var td1 = tr.insertCell(cell);
			td1.setAttribute("align", "center");
			td1.innerHTML = '<input type="hidden" id="idPlage'+i+'" name="idPlage'+i+'" value="'+i+'" /><input type="text" id="heureDebut' + i + '" name="heureDebut' + i + '" size=6/>';
			$('#heureDebut'+i).timeEntry({show24Hours: true , spinnerImage: '', defaultTime: '00:00',});
			cell++;
			
			var td2 = tr.insertCell(cell);
			td2.setAttribute("align", "center");
			td2.innerHTML = '<input type="text" id="heureFin' + i + '" name="heureFin' + i + '" size=6/>';
			$('#heureFin'+i).timeEntry({show24Hours: true , spinnerImage: '', defaultTime: '23:59'});
			cell++;
			
			var td3 = tr.insertCell(cell);
			td3.setAttribute("align", "center");
			var comboboxZone = "<select id='zone" + i + "' name='zone" + i + "'>";
			comboboxZone += "<option value='-1'></option>";
			<s:iterator value="listZone">
			comboboxZone += "<option value='<s:property value='id'/>'><s:property value='timezone'/></option>";
			</s:iterator>
			comboboxZone += "</select>";
			td3.innerHTML = comboboxZone;
			cell++;

			var type;
			type = document.getElementById("type").value;
			if(type == "liste"){
				var comboboxtype;
				comboboxtype = "<select id='desire" + i + "' name='desire" + i + "'><option value='-1'></option><s:iterator value='listEtatByType'>" +
				"<option value='<s:property value='%{etatPoss.id}'/>'><s:property value='%{etatPoss.libelle_etat}'/></option></s:iterator></select>";
				
				var td4 = tr.insertCell(cell);
				td4.setAttribute("align", "center");
				td4.innerHTML = comboboxtype;
				cell++;
			}

			if(type == "reel"){
				var comboboxperturbe;
				comboboxperturbe = "> <input id='perturbe_min" + i + "' name='perturbe_min" + i + "' type='text' maxlength='10' size='10'/></br>";
				comboboxperturbe += "   < <input id='perturbe_max" + i + "' name='perturbe_max" + i + "' type='text' maxlength='10' size='10'/>";
				var tdperturbe = tr.insertCell(cell);
				tdperturbe.setAttribute("align", "center");
				tdperturbe.innerHTML = comboboxperturbe;
				cell++;
				
				var comboboxko;
				comboboxko = "> <input id='ko_min" + i + "' name='ko_min" + i + "' type='text' maxlength='10' size='10'/></br>";
				comboboxko += "   < <input id='ko_max" + i + "' name='ko_max" + i + "' type='text' maxlength='10' size='10'/>";
				var tdko = tr.insertCell(cell);
				tdko.setAttribute("align", "center");
				tdko.innerHTML = comboboxko;
				cell++;
			}

			

			var td5 = tr.insertCell(cell);
			var comboboxJours = '<table><tr><td align="center">L</td><td align="center">Ma</td><td align="center">Me</td>';
			comboboxJours += '<td align="center">J</td><td align="center">V</td><td align="center">S</td><td align="center">D</td>';
			comboboxJours += '<td align="center">Ferié</td></tr>';
			comboboxJours += '<tr><td><input type="checkbox" checked name="lundi'+i+'" id="lundi'+i+'"/></td>';
			comboboxJours += '<td><input type="checkbox" checked name="mardi'+i+'" id="mardi'+i+'"/></td>';
			comboboxJours += '<td><input type="checkbox" checked name="mercredi'+i+'" id="mercredi'+i+'"/></td>';
			comboboxJours += '<td><input type="checkbox" checked name="jeudi'+i+'" id="jeudi'+i+'"/></td>';
			comboboxJours += '<td><input type="checkbox" checked name="vendredi'+i+'" id="vendredi'+i+'"/></td>';
			comboboxJours += '<td><input type="checkbox" name="samedi'+i+'" id="samedi'+i+'"/></td>';
			comboboxJours += '<td><input type="checkbox" name="dimanche'+i+'" id="dimanche'+i+'"/></td>';
			comboboxJours += '<td><input type="checkbox" name="ferie'+i+'" id="ferie'+i+'"/></td>';
			comboboxJours += "</tr></table>";
			td5.innerHTML = comboboxJours;
			cell++;
			
			var td6 = tr.insertCell(cell);
			td6.setAttribute("align", "center");
			var supprElement = '<a href="#" onclick="javascript:deletePlage(' + i + ');">'; 
			supprElement += "<img class='icone' alt='Supprimer' title='Supprimer' src='<s:property value='#session.ENSEIGNE' />/img/supprimer-16.png'/>";
			supprElement += '</a>';
			td6.innerHTML = supprElement;

			i++;

			$('#textNoPlage').hide();
		}
		
		function deletePlage(ligne){
			var j = ligne + 1;
			while(document.getElementById("idPlage" + j)){
				
				document.getElementById("idPlage" + (j - 1)).value = document.getElementById("idPlage" + j).value;
				document.getElementById("heureDebut" + (j - 1)).value = document.getElementById("heureDebut" + j).value;
				document.getElementById("heureFin" + (j - 1)).value = document.getElementById("heureFin" + j).value;
				document.getElementById("zone" + (j - 1)).value = document.getElementById("zone" + j).value;
				document.getElementById("desire" + (j - 1)).value = document.getElementById("desire" + j).value;
				document.getElementById("lundi" + (j - 1)).value = document.getElementById("lundi" + j).value;
				document.getElementById("mardi" + (j - 1)).value = document.getElementById("mardi" + j).value;
				document.getElementById("mercredi" + (j - 1)).value = document.getElementById("mercredi" + j).value;
				document.getElementById("jeudi" + (j - 1)).value = document.getElementById("jeudi" + j).value;
				document.getElementById("vendredi" + (j - 1)).value = document.getElementById("vendredi" + j).value;
				document.getElementById("samedi" + (j - 1)).value = document.getElementById("samedi" + j).value;
				document.getElementById("dimanche" + (j - 1)).value = document.getElementById("dimanche" + j).value;
				document.getElementById("ferie" + (j - 1)).value = document.getElementById("ferie" + j).value;
				
				j++;
			}

			document.getElementById("tablePlage").deleteRow(-1);
			i--;
		}

		function retour(){
			submitData('showModifyIndicateurServiceAction.action');
		}
		
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MTG_PLF_A')" /></s:param>
	</s:include>

	<div class="contentTable" style="width: 70%">
		<s:form id="mainForm" theme="simple" action="createPlageFonctIndicateurServiceAction" method="POST">
		
			<s:token></s:token>
			<s:hidden name="selectRow" id="selectRow" value="%{service.id}"/>
			<s:hidden name="deleteIndic" id="deleteIndic" value="-1"/>
			<s:hidden name="envir" id="envir" />
			<s:hidden name="type" id="type" value="%{typeIndic.format.format}"/>
			<s:hidden name="auto" id="auto"/>
			<s:hidden name="debut" id="debut"/>
			<s:hidden name="fin" id="fin"/>
			<s:hidden name="etatdesire" id="etatdesire"/>
			<s:hidden name="typeIndic" id="typeIndic"/>

			<table class="formTable">
				<tr>
					<td align="left">Nom du Service :</td>
					<td align="left"><b><s:property value="nomService"/></b></td>
				</tr>	
				<tr>
					<td align="left">Nom de l'environnement :</td>
					<td align="left"><b><s:property value="nomEnvir"/></b></td>
				</tr>
			</table>
			<div class="titreTable"><b>Plage de fonctionnement</b></div>
			<div class="plus">
				<s:a href="#" onclick="javascript:addPlage()">
					<img class="icone" alt="Ajouter" title="Ajouter" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" />
				</s:a>
			</div>
			<table class="dataTable" rules="all" id="tablePlage">
				<thead>
					<tr>
						<th class="titreColonne">Heure Début</th>
						<th class="titreColonne">Heure Fin</th>
						<th class="titreColonne">Zone</th>
						<s:if test="etatdesire==true">
							<th class="titreColonne">Etat désiré</th>
						</s:if>
						<s:if test='typeIndic.format.format.equals("reel")'>
							<th class="titreColonne">Etat perturbé</th>
							<th class="titreColonne">Etat KO</th>
						</s:if>
						<th class="titreColonne">Jours</th>
						<th class="titreColonne">Supprimer</th>
					</tr>
				</thead>
				<tbody>
					<tr id="textNoPlage">
						<td class="emptyListText" colspan="10">Aucune plage de fonctionnement</td>
					</tr>
				</tbody>
			</table>
			<s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom.jsp" />
		</s:form>
	</div>
</body>
</html>