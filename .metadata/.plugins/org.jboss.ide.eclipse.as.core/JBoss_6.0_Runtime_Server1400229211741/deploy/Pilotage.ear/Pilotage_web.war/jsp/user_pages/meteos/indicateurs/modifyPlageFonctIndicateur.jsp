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

		function debut(i){
			$('#heureDebut'+i).timeEntry({show24Hours: true , spinnerImage: ''});
		}

		function fin(i){
			$('#heureFin'+i).timeEntry({show24Hours: true , spinnerImage: ''});
		}
	
		function valider() {
			var mainForm = document.getElementById("mainForm");
			var nbPlage;
			nbPlage = document.getElementById("nbPlages").value;
			var format = document.getElementById("format").value;
			var rempli=1;
			var i;
			for(i=0;i<nbPlage;i++){
				if((format == "datetime" || format == "varchar") && document.getElementById("heureDebut"+i).value=="" || 
						document.getElementById("heureFin"+i).value=="" || 	
						document.getElementById("zone"+i).value==-1){
					rempli=0;
				}
				else if((format == "liste" || format == "reel") && document.getElementById("heureDebut"+i).value=="" || 
						document.getElementById("heureFin"+i).value=="" || 	
						document.getElementById("zone"+i).value==-1 ||
						(document.getElementById("desire"+i)!=null && document.getElementById("desire"+i).value==-1 )){
					rempli=0;
				}
				if(document.getElementById("lundi"+i).checked==false &&
						document.getElementById("mardi"+i).checked==false &&
						document.getElementById("mercredi"+i).checked==false &&
						document.getElementById("jeudi"+i).checked==false &&
						document.getElementById("vendredi"+i).checked==false &&
						document.getElementById("samedi"+i).checked==false &&
						document.getElementById("dimanche"+i).checked==false &&
						document.getElementById("ferie"+i).checked==false){
					rempli=0;
				}
			}

			if(rempli==0){
				alert("Merci de renseigner tous les champs.");
			}
			else{
				mainForm.submit();
			}
		}

		function addPlage(){
			i=document.getElementById("nbPlages").value;
			var format = document.getElementById("format").value;
			
			if(i==1){
				var td0;
				td0=document.getElementById("supprimer0");
				td0.innerHTML= "<a href=# onclick=javascript:deletePlage(0); >"
				+"<img class=icone alt=Supprimer title='Supprimer' src='<s:property value='#session.ENSEIGNE' />/img/supprimer-16.png'/>";
			}

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

			var comboboxtype;
			if(format == "liste"){
				comboboxtype = "<select id='desire" + i + "' name='desire" + i + "'><option value='-1'></option><s:iterator value='listEtatByType'>" +
				"<option value='<s:property value='%{etatPoss.id}'/>'><s:property value='%{etatPoss.libelle_etat}'/></option></s:iterator></select>";

				var td4 = tr.insertCell(cell);
				td4.setAttribute("align", "center");
				td4.innerHTML = comboboxtype;
				cell++;
			}

			if(format == "reel"){
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
			td5.setAttribute("align", "center");
			td5.setAttribute("id","tdTable"+i);
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
			document.getElementById("nbPlages").value=i++;

			$('#textNoPlage').hide();
		}

		function deletePlage(ligne){
			var j = ligne + 1;
			while(document.getElementById("heureDebut" + j)){
				document.getElementById("heureDebut" + (j - 1)).value = document.getElementById("heureDebut" + j).value;
				document.getElementById("heureFin" + (j - 1)).value = document.getElementById("heureFin" + j).value;
				document.getElementById("zone" + (j - 1)).value = document.getElementById("zone" + j).value;
				document.getElementById("desire" + (j - 1)).value = document.getElementById("desire" + j).value;
				document.getElementById("lundi" + (j - 1)).checked = document.getElementById("lundi" + j).checked;
				document.getElementById("mardi" + (j - 1)).checked = document.getElementById("mardi" + j).checked;
				document.getElementById("mercredi" + (j - 1)).checked = document.getElementById("mercredi" + j).checked;
				document.getElementById("jeudi" + (j - 1)).checked = document.getElementById("jeudi" + j).checked;
				document.getElementById("vendredi" + (j - 1)).checked = document.getElementById("vendredi" + j).checked;
				document.getElementById("samedi" + (j - 1)).checked = document.getElementById("samedi" + j).checked;
				document.getElementById("dimanche" + (j - 1)).checked = document.getElementById("dimanche" + j).checked;
				document.getElementById("ferie" + (j - 1)).checked = document.getElementById("ferie" + j).checked;
				
				j++;
			}
			document.getElementById("tablePlage").deleteRow(-1);
			
			document.getElementById("nbPlages").value=document.getElementById("nbPlages").value-1;

			if(document.getElementById("nbPlages").value==1){
				document.getElementById("supprimer0").innerHTML="";
			}
		}

		function retour(){
			submitData('showDetailIndicateurServiceAction.action');
		}
		
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MTG_PL_M')" /></s:param>
	</s:include>

	<div class="contentTable" style="width: 70%">
		<s:form id="mainForm" theme="simple" action="modifyPlageFonctIndicateurServiceAction" method="POST">
		
			<s:token></s:token>
			<s:hidden name="selectRow" id="selectRow" value="%{service.id}"/>
			<s:hidden name="idIndic" id="idIncid"/>
			<s:hidden name="nbPlages" id="nbPlages" />
			<s:hidden name="type" id="type" />
			<s:hidden name="format" id="format" value="%{type.format.format}"/>
			<s:hidden name="deleteIndic" id="deleteIndic" value="-1"/>

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
				<s:a href="#" onclick="javascript:addPlage();">
					<img class="icone" alt="Ajouter" title="Ajouter" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" />
				</s:a>
			</div>
			<table class="dataTable" rules="all" id="tablePlage">
				<!-- <col width="80%"/>
				<col width="20%"/>-->
				<thead>
					<tr>
						<th class="titreColonne">Heure Début</th>
						<th class="titreColonne">Heure Fin</th>
						<th class="titreColonne">Zone</th>
						<s:if test='type.format.format.equals("liste")'>
							<th class="titreColonne">Etat désiré</th>
						</s:if>
						<s:if test='type.format.format.equals("reel")'>
							<th class="titreColonne">Etat perturbé</th>
							<th class="titreColonne">Etat KO</th>
						</s:if>
						<th class="titreColonne">Jours</th>
						<th class="titreColonne">Supprimer</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="listPlage.isEmpty()">
						<tr>
							<td colspan="9" class="emptyListText">
								Aucune Plage de Fonctionnement associé à cet indicateur
							</td>
						</tr>
					</s:if>
					<s:else>
					<s:iterator value="listPlage" status="stat">
						<tr>
							<td align="center">
								<input type="hidden" name="idPlage<s:property value="#stat.index"/>" id="idPlage<s:property value="#stat.index"/>" value=<s:property value="idPlage" /> />
								<input type="text" onmousedown="javascript:debut(<s:property value="#stat.index" />)" id="heureDebut<s:property value="#stat.index" />" name="heureDebut<s:property value="#stat.index" />"  size=6 value="<s:property value="heureDebut"/>">
							</td>
							<td align="center">
								<input type="text" onmousedown="javascript:fin(<s:property value="#stat.index" />)" id="heureFin<s:property value="#stat.index" />" name="heureFin<s:property value="#stat.index" />"  size=6 value="<s:property value="heureFin"/>">
							</td>
							<td align="center">
								<select id="zone<s:property value="#stat.index" />" name="zone<s:property value="#stat.index" />">	
									<option value='-1'></option>
									<s:iterator value="listZone">							
									<s:if test="idZone==id">
									<option selected value='<s:property value='id'/>'><s:property value='timezone'/></option>
									</s:if>
									<s:else>
									<option value='<s:property value='id'/>'><s:property value='timezone'/></option>
									</s:else>
									</s:iterator>
								</select>
							</td>
	
							<s:if test='type.format.format.equals("liste")'>
								<td align="center">
									<select id="desire<s:property value="#stat.index" />" name="desire<s:property value="#stat.index" />">
										<option value='-1'></option>
										<s:iterator value='listEtatByType'>
										<s:if test="%{etatDesire==etatPoss.id}">
											<option selected value='<s:property value='%{etatPoss.id}'/>'><s:property value='%{etatPoss.libelle_etat}'/></option>
										</s:if>
										<s:else>
											<option value='<s:property value='%{etatPoss.id}'/>'><s:property value='%{etatPoss.libelle_etat}'/></option>
										</s:else>
										</s:iterator>
									</select>
								</td>
							</s:if>
						
							<s:if test='type.format.format.equals("reel")'>
								<input type="hidden" name="seuil<s:property value="#stat.index" />" id="seuil<s:property value="#stat.index" />" value="<s:property value="seuil"/>" />
								<td align="center">
									> <input id="perturbe_min<s:property value="#stat.index" />" name="perturbe_min<s:property value="#stat.index" />" value="<s:property value="perturbe_min" />" type='text' maxlength='10' size='10'/><br/>
									< <input id="perturbe_max<s:property value="#stat.index" />" name="perturbe_max<s:property value="#stat.index" />" value="<s:property value="perturbe_max" />" type='text' maxlength='10' size='10'/>
								</td>
								<td align="center">
									> <input id='ko_min<s:property value="#stat.index" />' name='ko_min<s:property value="#stat.index" />' value="<s:property value="ko_min" />" type='text' maxlength='10' size='10'/><br/>
									< <input id='ko_max<s:property value="#stat.index" />' name='ko_max<s:property value="#stat.index" />' value="<s:property value="ko_max" />" type='text' maxlength='10' size='10'/>
								</td>
							</s:if>
							<td align="center">
								 <table id="table<s:property value="#stat.index" />">
									<tr>
									 	<td align="center">L</td>
									 	<td align="center">Ma</td>
									 	<td align="center">Me</td>
										<td align="center">J</td>
										<td align="center">V</td>
										<td align="center">S</td>
										<td align="center">D</td>
										<td align="center">Ferié</td>
									</tr>
									<tr>
										<td><input type="checkbox" name="lundi<s:property value="#stat.index" />" id="lundi<s:property value="#stat.index" />" <s:property value="lundi"/> /></td>
										<td><input type="checkbox" name="mardi<s:property value="#stat.index" />" id="mardi<s:property value="#stat.index" />" <s:property value="mardi"/>/></td>
										<td><input type="checkbox" name="mercredi<s:property value="#stat.index" />" id="mercredi<s:property value="#stat.index" />" <s:property value="mercredi"/>/></td>
										<td><input type="checkbox" name="jeudi<s:property value="#stat.index" />" id="jeudi<s:property value="#stat.index" />" <s:property value="jeudi"/>/></td>
										<td><input type="checkbox" name="vendredi<s:property value="#stat.index" />" id="vendredi<s:property value="#stat.index" />" <s:property value="vendredi"/>/></td>
										<td><input type="checkbox" name="samedi<s:property value="#stat.index" />" id="samedi<s:property value="#stat.index" />" <s:property value="samedi"/>/></td>
										<td><input type="checkbox" name="dimanche<s:property value="#stat.index" />" id="dimanche<s:property value="#stat.index" />" <s:property value="dimanche"/>/></td>
										<td><input type="checkbox" name="ferie<s:property value="#stat.index" />" id="ferie<s:property value="#stat.index" />" <s:property value="ferie"/>/></td>
									</tr>
								
								</table>
							</td>
							<td align="center" id="supprimer<s:property value="#stat.index" />">
								<s:if test="listPlage.size()>1">
								<a href="#" onclick="javascript:deletePlage(<s:property value="#stat.index"/>);" >
								<img class='icone' alt='Supprimer' title='Supprimer' src='<s:property value='#session.ENSEIGNE' />/img/supprimer-16.png'/>
								</a>
								</s:if>
							</td>
						</tr>
					</s:iterator>
					</s:else>
				</tbody>
				<tfoot>
				
				</tfoot>
				</tbody>
			</table>
			<s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom.jsp" />
		</s:form>
	</div>
</body>
</html>