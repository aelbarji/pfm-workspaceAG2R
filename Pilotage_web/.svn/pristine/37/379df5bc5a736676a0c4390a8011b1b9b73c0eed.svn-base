<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<link rel="stylesheet" type="text/css" href="js/time/jquery.timeentry.css">
	<script type="text/javascript" src="js/time/jquery.timeentry.js"></script>
	<script type="text/javascript" src="js/time/jquery.timeentry-fr.js"></script>
	<script type="text/javascript" src="js/time/jquery.mousewheel.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
	<script type="text/javascript" src="js/time/timepicker.js"></script>
	
	<script type="text/javascript">
		function createMeteo(){
			submitData('showSaisieMeteoAction.action');

		}

		function changeCouleur(format, seuil, totalGab, i){
			var etat;
			if(format == "liste"){
				etat = document.getElementById('etat'+i).options[document.getElementById('etat'+i).selectedIndex].value;
			}
			else{
				etat = document.getElementById("etat"+i).value;
				if(isNaN(etat)){
					 alert(etat+" n\'est pas un nombre.");
					 document.getElementById("etat"+i).value="";
					 return false;
				}				
			}
			var td = document.getElementById("td"+i); 
            $.ajax({
               type: "POST",
               url: "refreshCouleurEtat.action",
               data: "etat=" + etat + "&format=" + format + "&seuil=" + seuil + "&totalGab=" + totalGab,
               success: function(response){
            	   td.style.backgroundColor = response;
            	   
               },
	           error: function(e){
	           		alert('Error: ' + e);
	           }
			});			
		}

		function enregistrer(){
			/*var i, j, k;
			for(i=0;i<nb_meteo;i++){
				var nbService = document.getElementById("nbService"+i).value;
				for(j=0;j<nbService;j++){
					var nbEnvir = document.getElementById("nbEnvir"+i+j).value;
					for(k=0;k<nbEnvir;k++){
						/*var etat = document.getElementById("etat"+i);
						if(etat != null){
							if(document.getElementById("format"+i).value!="liste"){
								if(etat.value==""){ alert("Merci de remplir tous les indicateurs"); return; }
							}
							else if((etat.options[etat.selectedIndex].value=="OK" 
									|| etat.options[etat.selectedIndex].value=="KO")
									&& document.getElementById("champFin"+i).value=="1"
									&&  document.getElementById("heureFin"+i).value==""){
								alert("Lorsque l'état du service est OK ou KO, vous devez obligatoirement renseigner l'heure de fin.");
								document.getElementById("heureFin"+i+j+k).focus();
								return;
							}
						}
					}
				}
			}*/
			submitData('saveModifySaisieMeteoAction.action');
		}

		function addComment(i, e){
			x = (navigator.appName.substring(0,3) == "Net") ? e.pageX : event.x+document.body.scrollLeft;
			y = (navigator.appName.substring(0,3) == "Net") ? e.pageY : event.y+document.body.scrollTop;
			if ($("#comment" + i).is(":hidden")) {
				$("#comment" + i).show();
			}
			else{
				$("#comment" + i).hide();
			}
			document.getElementById("comment" + i).style.position = "absolute";
			document.getElementById("comment" + i).style.top = y + 50;
			document.getElementById("comment" + i).style.left = x;
		}

		function closeComment(i){
			
			$("#comment" + i).hide();
			if(document.getElementById("commentTexte" + i).value!=""){
				document.getElementById("img" + i).src = '<s:property value="#session.ENSEIGNE" />/img/warning.png';
			}
			else{
				document.getElementById("img" + i).src = '<s:property value="#session.ENSEIGNE" />/img/comment.png';
			}
		}

		function addCommentMeteo(i, e){
			x = (navigator.appName.substring(0,3) == "Net") ? e.pageX : event.x+document.body.scrollLeft;
			y = (navigator.appName.substring(0,3) == "Net") ? e.pageY : event.y+document.body.scrollTop;
			if ($("#commentMeteo" + i).is(":hidden")) {
				$("#commentMeteo" + i).show();
			}
			else{
				$("#commentMeteo" + i).hide();
			}
			document.getElementById("commentMeteo" + i).style.position = "absolute";
			document.getElementById("commentMeteo" + i).style.top = y + 50;
			document.getElementById("commentMeteo" + i).style.left = x;
		}

		function closeCommentMeteo(i){
			
			$("#commentMeteo" + i).hide();
			if(document.getElementById("commentTexteMeteo" + i).value!=""){
				document.getElementById("imgMeteo" + i).src = '<s:property value="#session.ENSEIGNE" />/img/warning.png';
			}
			else{
				document.getElementById("imgMeteo" + i).src = '<s:property value="#session.ENSEIGNE" />/img/comment.png';
			}
		}

		function saisir(){
			if(document.getElementById("heure")!=null)
			document.getElementById("heure").selectedIndex=-1;
			submitData('showSaisieMeteoAction.action');
		}

		function recupererInfo(){
			if(document.getElementById("recupInfo")!=null && document.getElementById("recupInfo").value==0){
				document.getElementById("recupInfo").value = 1;
			}else{
				document.getElementById('recupInfo').value = 0;
			}
			submitData('showSaisieMeteoAction.action');
		}

		function saisirHoraire(){
			if(document.getElementById("exception").value == 1){
				document.getElementById("exception").value=0;
				document.getElementById("heureExcept").value=-1;
			}
			submitData('showSaisieMeteoAction.action');
		}

		function changeValue(){
			if(document.getElementById("exception").value == 0){
				document.getElementById("exception").value=1;
				d = new Date();
				h = d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
				hour = d.toLocaleTimeString();
				changeData('heureExcept', hour);
				document.getElementById("heure").selectedIndex=-1; 
			}else{
				document.getElementById("exception").value=0;
				document.getElementById("heureExcept").value=-1;
			}
			saisir();
		}

		function charge(){
			$(".champheure").timeEntry({show24Hours: true , spinnerImage: '', defaultTime: '00:00'});
			$(".datetime").datetimepicker({dateFormat:"dd/mm/yy"});
		}

		function retour(){
			submitData('showConsultationMeteoAction.action');
		}
	</script>
</head>
<body onLoad="charge();">
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MTG_SAI_A')" /></s:param>
	</s:include>
	<div class="contentTable" style="width:75%">
		<s:form id="mainForm" theme="simple" method="POST">
			<s:hidden name="heureExcept" id="heureExcept" />
			<input type=hidden id="date" name="date" value="<s:property value="date"/>"/>	
			<input type=hidden id="heure" name="heure" value="<s:property value="heure"/>"/>
			<input type=hidden id="groupeMeteoID" name="groupeMeteoID" value="<s:property value="groupeMeteoID"/>"/>	
			<s:token></s:token>

			<b>Groupe Météo <s:property value="groupe.nom_groupeMeteo"/> du <s:property value="date"/> à <s:property value="heure"/>  </b>
			
			<br/><br/>Dernier enregistrement le <s:property value="date"/> à <s:property value="heureSaisie"/><br/><br/>
			<input type="hidden" id="dateGMeteo" name="dateGMeteo" value="<s:property value="date"/>"/>	
			<s:if test='!dateEnvoi.equals("")'>
				Dernier envoi le <s:property value="dateEnvoi"/> à <s:property value="heureEnvoi"/>
			</s:if>
			<s:else>
				<b style="color:red">Le groupe météo n'a pas été envoyé.</b>
			</s:else>
			
			<s:if test="!listIndic.isEmpty()">
			
			
			<div class="titreTable"><b>Commentaire global <s:property value="groupeMeteoNAME"/></b>
				<textarea style="align:center;width:99%;" rows="3" id="commentaire" name="commentaire"><s:property value="commentaire"/></textarea>
			</div>
			
			<s:iterator value="listIndic" status="ind">
				<s:if test='#ind.index==0 || (#ind.index!=0 && !listIndic[#ind.index-1][0].meteo.nom_meteo.equals(listIndic[#ind.index][0].meteo.nom_meteo))'>
					<div class="titreTable"><b><s:property value="%{listIndic[#ind.index][0].meteo.nom_meteo}"/></b>			
					
					<s:if test='commentaire_meteo[#ind.index].equals("")'>
						<a onclick="javascript:addCommentMeteo(<s:property value="%{listIndic[#ind.index][0].meteo.id}"/>, event)"><img id="imgMeteo<s:property value="%{listIndic[#ind.index][0].meteo.id}"/>" class="icone2" alt="Commentaire" title="Commentaire" src="<s:property value="#session.ENSEIGNE" />/img/comment.png"/></a>
					</s:if>
					<s:else>
						<a onclick="javascript:addCommentMeteo(<s:property value="%{listIndic[#ind.index][0].meteo.id}"/>, event)"><img id="imgMeteo<s:property value="%{listIndic[#ind.index][0].meteo.id}"/>" class="icone2" alt="Commentaire" title="Commentaire" src="<s:property value="#session.ENSEIGNE" />/img/warning.png"/></a>
					</s:else>
					<div style="position: absolute; display: none; width: 400px;" id="commentMeteo<s:property value="%{listIndic[#ind.index][0].meteo.id}"/>">
						<table style="-moz-border-radius:10px;background:#948683;" border="1" rules="none" bgcolor="#FFFFFF" cellpadding="5" width="200px">
							<col width="90%"/>
							<col width="10%"/>
							<tr>
								<td>
								    Commentaire <b><s:property value="%{listIndic[#ind.index][0].meteo.nom_meteo}"/></b>
									<textarea rows="10" cols="100" id="commentTexteMeteo<s:property value="%{listIndic[#ind.index][0].meteo.id}"/>" name="commentTexteMeteo<s:property value="%{listIndic[#ind.index][0].meteo.id}"/>"><s:property value="%{commentaire_meteo[#ind.index]}"/></textarea>
								</td>
								<td align="right" valign="top">
									<a onclick="javascript:closeCommentMeteo(<s:property value="%{listIndic[#ind.index][0].meteo.id}"/>);">
										<img alt="Fermer" src='<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png'/>
									</a>
								</td>
							</tr>
						</table>
					</div>
					<table class="tableSaisieMeteo" rules="all" border="1">
						<thead>
							<tr>
								<th class="titreColonne">Services</th>
								<s:iterator value="tableau_th[#ind.index]" status="id">
									<th class="titreColonne"><s:property value="%{tableau_th[#ind.index][#id.index]}"/></th>
								</s:iterator>
							</tr>
				</s:if>
				
					
					
					<tr>
								<td>
									<s:iterator value="listIndic[#ind.index]" status="serv">
										<s:if test='listIndic[#ind.index][#serv.index]!=null'>
											<s:set name="nomService" value="%{listIndic[#ind.index][#serv.index].service.service}"/>
										</s:if>
									</s:iterator>
									<s:property  value="%{#nomService}"/>
									<s:if test='listIndic[#ind.index][0].service.consigne!=null'>
										<img id="img" class="icone2" title="<s:property value="%{listIndic[#ind.index][0].service.consigne}"/>" src="<s:property value="#session.ENSEIGNE" />/img/consigne.png"/>
									</s:if>
								</td>

							
							<s:iterator value="listIndic[#ind.index]" status="id">
								
								<input type="hidden" id='id<s:property value="#ind.index"/><s:property value="#id.index"/>' value='<s:property value="listIndic[#ind.index][#id.index].id"/>'/>
							
								<s:if test='#ind.index==0 || (#ind.index!=0 && !listIndic[#ind.index-1][0].meteo.nom_meteo.equals(listIndic[#ind.index][0].meteo.nom_meteo))'>
								
								</s:if>
							
										<s:if test='listIndic[#ind.index][#id.index]==null'>
											<td align="center" style="width:240px;background-color:#b8cce4">NA</td>
										</s:if>
										
										<input type="hidden" id='format<s:property value="#ind.index"/>' value='<s:property value="listIndic[#ind.index][#id.index].format"/>'/>
										
										<s:if test='listIndic[#ind.index][#id.index].format.equals("reel")'>
											<td id='td<s:property value="listIndic[#ind.index][#id.index].id"/>' align="center" style=width:240px;background-color:<s:property value="couleurs[#ind.index][#id.index]"/>>
												<input type="text" value="<s:property value="listIndic[#ind.index][#id.index].etat_desire"/>" onblur='javascript:changeCouleur("<s:property value="listIndic[#ind.index][#id.index].format"/>", <s:property value="listIndic[#ind.index][#id.index].plage.seuil.id"/>, <s:property value="listIndic[#ind.index][#id.index].etat_desire"/>, <s:property value="listIndic[#ind.index][#id.index].id"/>)' id="etat<s:property value="listIndic[#ind.index][#id.index].id"/>" name="etat<s:property value="listIndic[#ind.index][#id.index].id"/>" size="8"/>
												
												<s:if test='comment_indicateur[#ind.index][#id.index].equals("")' >
													<a onclick="javascript:addComment(<s:property value="listIndic[#ind.index][#id.index].id"/>, event)"><img id="img<s:property value="listIndic[#ind.index][#id.index].id"/>" class="icone2" alt="Commentaire" title="Commentaire" src="<s:property value="#session.ENSEIGNE" />/img/comment.png"/></a>
												</s:if>
												<s:else>
													<a onclick="javascript:addComment(<s:property value="listIndic[#ind.index][#id.index].id"/>, event)"><img id="img<s:property value="listIndic[#ind.index][#id.index].id"/>" class="icone2" alt="Commentaire" title="Commentaire" src="<s:property value="#session.ENSEIGNE" />/img/warning.png"/></a>
												</s:else>
												
												<div style="position: absolute; display: none; width: 300px;" id="comment<s:property value="listIndic[#ind.index][#id.index].id"/>">
													<table style="-moz-border-radius:10px;background:#948683;" border="1" rules="none" bgcolor="#FFFFFF" cellpadding="5" width="200px">
														<col width="90%"/>
														<col width="10%"/>
														<tr>
															<td>
															    Commentaire <b><s:property  value="%{listIndic[#ind.index][#id.index].service.service}"/> - <s:property value="%{listIndic[#ind.index][#id.index].environnement.nom_envir}"/></b>
																<textarea rows="3" cols="50" id="commentTexte<s:property value="listIndic[#ind.index][#id.index].id"/>" name="commentTexte<s:property value="listIndic[#ind.index][#id.index].id"/>"><s:property value="%{comment_indicateur[#ind.index][#id.index]}"/></textarea>
															</td>
															<td align="right" valign="top">
																<a onclick="javascript:closeComment(<s:property value="listIndic[#ind.index][#id.index].id"/>);">
																	<img alt="Fermer" src='<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png'/>
																</a>
															</td>
														</tr>
													</table>
												</div>
											</td>
											<s:if test='listIndic[#ind.index][#id.index].indic_heure==1'><td></td></s:if>
											<s:if test='listIndic[#ind.index][#id.index].indic_heure==2'><td></td></s:if>
											<s:if test='listIndic[#ind.index][#id.index].indic_heure==3'><td></td><td></td></s:if>
										</s:if>
										
										<s:elseif test='listIndic[#ind.index][#id.index].format.equals("liste")'>
											<td id='td<s:property value="listIndic[#ind.index][#id.index].id"/>' align="center" style=width:240px;background-color:<s:property value="couleurs[#ind.index][#id.index]"/>>
												<select onchange='javascript:changeCouleur("<s:property value="listIndic[#ind.index][#id.index].format"/>", "null", <s:property value="listIndic[#ind.index][#id.index].etat_desire"/>, <s:property value="listIndic[#ind.index][#id.index].id"/>)' id='etat<s:property value="listIndic[#ind.index][#id.index].id"/>' name='etat<s:property value="listIndic[#ind.index][#id.index].id"/>'>
													<s:iterator value="listEtat[#ind.index][#id.index]" status="s">
														<s:if test='listIndic[#ind.index][#id.index].etat_desire==listEtat[#ind.index][#id.index][#s.index].etatPoss.id'>
															<option selected><s:property value="listEtat[#ind.index][#id.index][#s.index].etatPoss.libelle_etat"/></option>
														</s:if>
														<s:else>
															<option><s:property value="listEtat[#ind.index][#id.index][#s.index].etatPoss.libelle_etat"/></option>
														</s:else>
													</s:iterator>
												</select>
												<s:if test='comment_indicateur[#ind.index][#id.index].equals("")' >
													<a onclick="javascript:addComment(<s:property value="listIndic[#ind.index][#id.index].id"/>, event)"><img id="img<s:property value="listIndic[#ind.index][#id.index].id"/>" class="icone2" alt="Commentaire" title="Commentaire" src="<s:property value="#session.ENSEIGNE" />/img/comment.png"/></a>
												</s:if>
												<s:else>
													<a onclick="javascript:addComment(<s:property value="listIndic[#ind.index][#id.index].id"/>, event)"><img id="img<s:property value="listIndic[#ind.index][#id.index].id"/>" class="icone2" alt="Commentaire" title="Commentaire" src="<s:property value="#session.ENSEIGNE" />/img/warning.png"/></a>
												</s:else>
											
												<div style="position: absolute; display: none; width: 300px;" id="comment<s:property value="listIndic[#ind.index][#id.index].id"/>">
													<table style="-moz-border-radius:10px;background:#948683;" border="1" rules="none" bgcolor="#FFFFFF" cellpadding="5" width="200px">
														<col width="90%"/>
														<col width="10%"/>
														<tr>
															<td>
															    Commentaire <b><s:property  value="%{listIndic[#ind.index][#id.index].service.service}"/> - <s:property value="%{listIndic[#ind.index][#id.index].environnement.nom_envir}"/></b>
																<textarea rows="3" cols="50" id="commentTexte<s:property value="listIndic[#ind.index][#id.index].id"/>" name="commentTexte<s:property value="listIndic[#ind.index][#id.index].id"/>"><s:property value="%{comment_indicateur[#ind.index][#id.index]}"/></textarea>
															</td>
															<td align="right" valign="top">
																<a onclick="javascript:closeComment(<s:property value="listIndic[#ind.index][#id.index].id"/>);">
																	<img alt="Fermer" src='<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png'/>
																</a>
															</td>
														</tr>
													</table>
												</div>
											</td>
											<s:if test='listIndic[#ind.index][#id.index].indic_heure==1'>
												<td align="center">
													<s:if test='listIndic[#ind.index][#id.index].heure_debut.equals("1")'>
														<input type="text" class="champheure" name="heureDebut<s:property value="listIndic[#ind.index][#id.index].id"/>" size=6/>
													</s:if>
													<s:else>
														<input type="text" class="champheure" name="heureDebut<s:property value="listIndic[#ind.index][#id.index].id"/>" size=6 value="<s:property value="listIndic[#ind.index][#id.index].heure_debut"/>"/>
													</s:else>
												</td>
											</s:if>
											<s:if test='listIndic[#ind.index][#id.index].indic_heure==2'>
												<td align="center">
													<s:if test='listIndic[#ind.index][#id.index].heure_fin.equals("1")'>
														<input type="text" class="champheure" id='heureFin<s:property value="listIndic[#ind.index][#id.index].id"/>' name="heureFin<s:property value="listIndic[#ind.index][#id.index].id"/>" size=6/>
													</s:if>
													<s:else>
														<input type="text" class="champheure" id='heureFin<s:property value="listIndic[#ind.index][#id.index].id"/>' name="heureFin<s:property value="listIndic[#ind.index][#id.index].id"/>" value="<s:property value="listIndic[#ind.index][#id.index].heure_fin"/>" size=6/>
													</s:else>
												</td>
											</s:if>
											<s:if test='listIndic[#ind.index][#id.index].indic_heure==3'>
												<td align="center">
													<s:if test='listIndic[#ind.index][#id.index].heure_debut.equals("1")'>
														<input type="text" class="champheure" name="heureDebut<s:property value="listIndic[#ind.index][#id.index].id"/>" size=6/>
													</s:if>
													<s:else>
														<input type="text" class="champheure" name="heureDebut<s:property value="listIndic[#ind.index][#id.index].id"/>" size=6 value="<s:property value="listIndic[#ind.index][#id.index].heure_debut"/>"/>
													</s:else>
												</td>
												<td align="center">
													<s:if test='listIndic[#ind.index][#id.index].heure_fin.equals("1")'>
														<input type="text" class="champheure" id="heureFin<s:property value="listIndic[#ind.index][#id.index].id"/>" name="heureFin<s:property value="listIndic[#ind.index][#id.index].id"/>" size=6/>
													</s:if>
													<s:else>
														<input type="text" class="champheure" id="heureFin<s:property value="listIndic[#ind.index][#id.index].id"/>" name="heureFin<s:property value="listIndic[#ind.index][#id.index].id"/>" value="<s:property value="listIndic[#ind.index][#id.index].heure_fin"/>" size=6/>
													</s:else>
										  		</td>
											</s:if>
										</s:elseif>
										<s:elseif test='listIndic[#ind.index][#id.index].format.equals("datetime")'>
											<td id='td<s:property value="listIndic[#ind.index][#id.index].id"/>' align="center" style=width:240px;>
												<input type="text" value="<s:property value="listIndic[#ind.index][#id.index].etat_desire"/>" class="datetime" id="etat<s:property value="#ind.index]"/>" name="etat<s:property value="listIndic[#ind.index][#id.index].id"/>" id="etat<s:property value="listIndic[#ind.index][#id.index].id"/>" size="15"/>
												
												<s:if test='comment_indicateur[#ind.index][#id.index].equals("")' >
													<a onclick="javascript:addComment(<s:property value="listIndic[#ind.index][#id.index].id"/>, event)"><img id="img<s:property value="listIndic[#ind.index][#id.index].id"/>" class="icone2" alt="Commentaire" title="Commentaire" src="<s:property value="#session.ENSEIGNE" />/img/comment.png"/></a>
												</s:if>
												<s:else>
													<a onclick="javascript:addComment(<s:property value="listIndic[#ind.index][#id.index].id"/>, event)"><img id="img<s:property value="listIndic[#ind.index][#id.index].id"/>" class="icone2" alt="Commentaire" title="Commentaire" src="<s:property value="#session.ENSEIGNE" />/img/warning.png"/></a>
												</s:else>
												
												<div style="position: absolute; display: none; width: 300px;" id="comment<s:property value="listIndic[#ind.index][#id.index].id"/>">
													<table style="-moz-border-radius:10px;background:#948683;" border="1" rules="none" bgcolor="#FFFFFF" cellpadding="5" width="200px">
														<col width="90%"/>
														<col width="10%"/>
														<tr>
															<td>
															    Commentaire <b><s:property  value="%{listIndic[#ind.index][#id.index].service.service}"/> - <s:property value="%{listIndic[#ind.index][#id.index].environnement.nom_envir}"/></b>
																<textarea rows="3" cols="50" id="commentTexte<s:property value="listIndic[#ind.index][#id.index].id"/>" name="commentTexte<s:property value="listIndic[#ind.index][#id.index].id"/>"><s:property value="%{comment_indicateur[#ind.index][#id.index]}"/></textarea>
															</td>
															<td align="right" valign="top">
																<a onclick="javascript:closeComment(<s:property value="listIndic[#ind.index][#id.index].id"/>);">
																	<img alt="Fermer" src='<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png'/>
																</a>
															</td>
														</tr>
													</table>
												</div>
											</td>
											<s:if test='listIndic[#ind.index][#id.index].indic_heure==1'><td></td></s:if>
											<s:if test='listIndic[#ind.index][#id.index].indic_heure==2'><td></td></s:if>
											<s:if test='listIndic[#ind.index][#id.index].indic_heure==3'><td></td><td></td></s:if>
										</s:elseif>
										<s:elseif test='listIndic[#ind.index][#id.index].format.equals("varchar")'>
											<td id='td<s:property value="listIndic[#ind.index][#id.index].id"/>' align="center" style=width:240px;>
												<input type="text" value="<s:property value="listIndic[#ind.index][#id.index].etat_desire"/>" id="etat<s:property value="listIndic[#ind.index][#id.index].id"/>" name="etat<s:property value="listIndic[#ind.index][#id.index].id"/>" size="30"/>
											</td>
											<s:if test='listIndic[#ind.index][#id.index].indic_heure==1'><td></td></s:if>
											<s:if test='listIndic[#ind.index][#id.index].indic_heure==2'><td></td></s:if>
											<s:if test='listIndic[#ind.index][#id.index].indic_heure==3'><td></td><td></td></s:if>
										</s:elseif>
								
						</s:iterator>
						
						<s:if test='#ind.index+1==listIndic.size || (#ind.index+1!=listIndic.size && !listIndic[#ind.index][0].meteo.nom_meteo.equals(listIndic[#ind.index+1][0].meteo.nom_meteo))'>
					</thead>
				</table>
				</div>
				
				</s:if>
			
					
			
			</s:iterator>	
		
			<div id="pied_page" class="contentTableBottom">
				<p class="boutonRight">
					<s:a href="javascript:retour();" cssClass="boutonValider">Retour</s:a>
					<s:a href="javascript:enregistrer();" cssClass="boutonValider">Enregistrer</s:a>
				</p>
			</div>
			</s:if>
			</s:form>
	</div>
</body>
</html>