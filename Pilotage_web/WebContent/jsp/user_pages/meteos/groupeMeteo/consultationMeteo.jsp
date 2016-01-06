<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	
	<script type="text/javascript">

		function execute(){
			if(document.getElementById("heure").value!="-1"){
				submitData('showConsultationMeteoAction.action');
			}
		}

		function horaires(){
			if(document.getElementById("heure") != null)
			document.getElementById("heure").value="-1";
			submitData('showConsultationMeteoAction.action');
		}

		function changeDate(){
			document.getElementById("dateChanged").value = "1";
			submitData('showConsultationMeteoAction.action');
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

		function envoyer(verif){
			if(confirm("Etes-vous sûr de vouloir envoyer la météo ?")){
				if(verif==1)
				submitData('exportGroupeMeteoAction.action');
			}
		}

		function modifier(){
			submitData('showModifySaisieMeteoAction.action');
		}
	
        $.subscribe('datepickerChange',function(event,data) {
        	changeDate();
    	})
		
		</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MTG_COM')" /></s:param>
	</s:include>
	<div class="contentTable" style="width:68%">
		<s:form id="mainForm" theme="simple"  method="POST">
		
			<!--<s:hidden name="groupeMeteoID" id="groupeMeteoID" />-->
			<s:hidden name="dateChanged" id="dateChanged" value="0"/>
			<s:token></s:token>

				<b>Sélectionnez le Groupe Météo </b>
				<select id="groupeMeteoID" name="groupeMeteoID" onchange="javascript:horaires();">
					<option value='-1'></option>
					<s:iterator value="listGroupeMeteo" id="listGroupeMeteo">
						<s:if test="id==groupeMeteoID">
							<option selected value=<s:property value="id" />><s:property value="nom_groupeMeteo" /></option>
						</s:if>
						<s:else>
						<option value=<s:property value="id" />><s:property value="nom_groupeMeteo" /></option>
						</s:else>
					</s:iterator>
				</select>
			
			<br/><br/>
			<s:if test="!horaires.isEmpty()">
				<b>Indiquez le jour et l'heure du Groupe Météo à envoyer</b>
				<sj:datepicker name="date" 
								id="date" 
								displayFormat="dd/mm/yy"
								showOn="focus" 
								value="%{date}"
								onChangeTopics="datepickerChange">
				</sj:datepicker>
				<select id="heure" name="heure" onchange="javascript:execute();">
					<option value='-1'></option>
					<s:iterator value="horaires" id="horaires">	
						<s:if test="horaire.equals(heure)">
							<option selected  value=<s:property value="horaire" />><s:property value="horaire" /></option>
						</s:if>						
						<s:else>
						<option  value=<s:property value="horaire" />><s:property value="horaire" /></option>
						</s:else>
					</s:iterator>
				</select>
			</s:if>
			
			<s:if test="!listIndic.isEmpty()">
				<br/><br/>Dernier enregistrement le <s:property value="date"/> à <s:property value="heureSaisie"/><br/>
				<input type="hidden" id="dateGMeteo" name="dateGMeteo" value="<s:property value="date"/>"/>
				<s:if test='!dateEnvoi.equals("")'>
					Dernier envoi le <s:property value="dateEnvoi"/> à <s:property value="heureEnvoi"/>
				</s:if>
				<s:else>
					<b style="color:red">Le groupe météo n'a pas été envoyé.</b>
				</s:else>
			
				<div class="titreTable"><b>Commentaire global <s:property value="groupeMeteoNAME"/></b>
					<textarea style="align:center;width:99%;" readonly rows="3" id="commentaire" name="commentaire"><s:property value="commentaire"/></textarea>
				</div>
			
				<s:iterator value="listIndic" status="ind">
				<s:if test='#ind.index==0 || (#ind.index!=0 && !listIndic[#ind.index-1][0].meteo.nom_meteo.equals(listIndic[#ind.index][0].meteo.nom_meteo))'>
					<div class="titreTable"><b><s:property value="%{listIndic[#ind.index][0].meteo.nom_meteo}"/></b>			
					
					<s:if test='commentaire_meteo[#ind.index]!=null && !commentaire_meteo[#ind.index].equals("")'>
						<a onmouseover="javascript:addCommentMeteo(<s:property value="%{listIndic[#ind.index][0].meteo.id}"/>, event)"><img id="img<s:property value="%{listIndic[#ind.index][0].meteo.id}"/>" class="icone2" alt="Commentaire" src="<s:property value="#session.ENSEIGNE" />/img/warning.png"/></a>
					</s:if>
					<div style="position: absolute; display: none; width: 400px;" id="commentMeteo<s:property value="%{listIndic[#ind.index][0].meteo.id}"/>">
						<table style="-moz-border-radius:10px;background:#948683;" border="1" rules="none" bgcolor="#FFFFFF" cellpadding="5" width="200px">
							<col width="90%"/>
							<col width="10%"/>
							<tr>
								<td>
								    Commentaire <b><s:property value="%{listIndic[#ind.index][0].meteo.nom_meteo}"/></b>
									<textarea rows="10" cols="100" readonly id="commentTexteMeteo<s:property value="%{listIndic[#ind.index][0].meteo.id}"/>" name="commentTexteMeteo<s:property value="%{listIndic[#ind.index][0].meteo.id}"/>"><s:property value="%{commentaire_meteo[#ind.index]}"/></textarea>
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
										
										<s:if test='listIndic[#ind.index][#id.index].format.equals("reel")'>
											<td id='td<s:property value="listIndic[#ind.index][#id.index].id"/>' align="center" style=width:240px;background-color:<s:property value="couleurs[#ind.index][#id.index]"/>>
												<s:property value="listIndic[#ind.index][#id.index].etat_desire"/>
												
												<s:if test='comment_indicateur[#ind.index][#id.index]!=null && !comment_indicateur[#ind.index][#id.index].equals("")' >
													<a onmouseover="javascript:addComment(<s:property value="listIndic[#ind.index][#id.index].id"/>, event)"><img id="img<s:property value="#ind.index"/><s:property value="#stat.index"/><s:property value="#st.index"/>" class="icone2" alt="Commentaire" src="<s:property value="#session.ENSEIGNE" />/img/warning.png"/></a>
												</s:if>
												
												<div style="position: absolute; display: none; width: 300px;" id="comment<s:property value="listIndic[#ind.index][#id.index].id"/>">
													<table style="-moz-border-radius:10px;background:#948683;" border="1" rules="none" bgcolor="#FFFFFF" cellpadding="5" width="200px">
														<col width="90%"/>
														<col width="10%"/>
														<tr>
															<td>
															    Commentaire <b><s:property  value="%{listIndic[#ind.index][#id.index].service.service}"/> - <s:property value="%{listIndic[#ind.index][#id.index].environnement.nom_envir}"/></b>
																<textarea rows="3" cols="50" readonly id="commentTexte<s:property value="listIndic[#ind.index][#id.index].id"/>" name="commentTexte<s:property value="listIndic[#ind.index][#id.index].id"/>"><s:property value="%{comment_indicateur[#ind.index][#id.index]}"/></textarea>
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
												<s:property value="listIndic[#ind.index][#id.index].etat_desire"/>
												
												<s:if test='comment_indicateur[#ind.index][#id.index]!=null && !comment_indicateur[#ind.index][#id.index].equals("")' >
													<a onmouseover="javascript:addComment(<s:property value="listIndic[#ind.index][#id.index].id"/>, event)"><img id="img<s:property value="#ind.index"/><s:property value="#stat.index"/><s:property value="#st.index"/>" class="icone2" alt="Commentaire" src="<s:property value="#session.ENSEIGNE" />/img/warning.png"/></a>
												</s:if>
											
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
													<s:if test='!listIndic[#ind.index][#id.index].heure_debut.equals("1")'>
														<s:property value="listIndic[#ind.index][#id.index].heure_debut"/>
													</s:if>
												</td>
											</s:if>
											<s:if test='listIndic[#ind.index][#id.index].indic_heure==2'>
												<td align="center">
													<s:if test='!listIndic[#ind.index][#id.index].heure_fin.equals("1")'>
														<s:property value="listIndic[#ind.index][#id.index].heure_fin"/>	
													</s:if> 
												</td>
											</s:if>
											<s:if test='listIndic[#ind.index][#id.index].indic_heure==3'>
												<td align="center">
													<s:if test='!listIndic[#ind.index][#id.index].heure_debut.equals("1")'>
														<s:property value="listIndic[#ind.index][#id.index].heure_debut"/>
													</s:if>
												</td>
												<td align="center">
													<s:if test='!listIndic[#ind.index][#id.index].heure_fin.equals("1")'>
														<s:property value="listIndic[#ind.index][#id.index].heure_fin"/>
													</s:if>
										  		</td>
											</s:if>
										</s:elseif>
										<s:elseif test='listIndic[#ind.index][#id.index].format.equals("datetime")'>
											<td id='td<s:property value="listIndic[#ind.index][#id.index].id"/>' align="center" style=width:240px;>
												
												<s:property value="listIndic[#ind.index][#id.index].etat_desire"/>
												
												<s:if test='comment_indicateur[#ind.index][#id.index]!=null && !comment_indicateur[#ind.index][#id.index].equals("")' >
													<a onclick="javascript:addComment(<s:property value="listIndic[#ind.index][#id.index].id"/>, event)"><img id="img<s:property value="listIndic[#ind.index][#id.index].id"/>" class="icone2" alt="Commentaire" title="Commentaire" src="<s:property value="#session.ENSEIGNE" />/img/warning.png"/></a>
												</s:if>
												
												<div style="position: absolute; display: none; width: 300px;" id="comment<s:property value="listIndic[#ind.index][#id.index].id"/>">
													<table style="-moz-border-radius:10px;background:#948683;" border="1" rules="none" bgcolor="#FFFFFF" cellpadding="5" width="200px">
														<col width="90%"/>
														<col width="10%"/>
														<tr>
															<td>
															    Commentaire <b><s:property  value="%{listIndic[#ind.index][#id.index].service.service}"/> - <s:property value="%{listIndic[#ind.index][#id.index].environnement.nom_envir}"/></b>
																<textarea rows="3" cols="50" readonly id="commentTexte<s:property value="listIndic[#ind.index][#id.index].id"/>" name="commentTexte<s:property value="listIndic[#ind.index][#id.index].id"/>"><s:property value="%{comment_indicateur[#ind.index][#id.index]}"/></textarea>
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
												<s:property value="listIndic[#ind.index][#id.index].etat_desire"/>
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
				<s:if test="#session.USER_DROITS.contains('MTG_GM_ME')">
					<p class="boutonRight">
						<s:a href="javascript:modifier();" cssClass="boutonValider">Modifier</s:a>
						<s:a href="javascript:envoyer(1);" cssClass="boutonValider">Envoyer</s:a>
					</p>
				</s:if>
				</div>
			</s:if>
		</s:form>
	</div>
</body>
</html>