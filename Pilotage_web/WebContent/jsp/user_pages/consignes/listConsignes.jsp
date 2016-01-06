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
			if(confirm("Voulez-vous vraiment supprimer cette consigne ?")){
				changeData("consigneID", id);
				submitData("deleteConsigneAction.action");
			}
		}

		//Ajout
		function ajouter(type){
			changeData("typeConsigne", type);
			submitData('showCreateConsigneAction.action');
		}

		//Modification
		function modifier(id){
			changeData("consigneID", id);
			submitData("showModifyConsigneAction.action");
		}

		<s:if test="#session.USER_PROFIL.pilote">
		//validation
		function valider(id){
			changeData("consigneID", id);
			submitData("valideConsigneAction.action");
		}

		//Question d'un pilote		
		function question(idConsigne){
			var url = "sendQuestionAction.action?consigneID=" + idConsigne;
			var iWidth = 400; 
		    var iHeight = 400;
		    var iTop = Math.round((screen.availHeight-iHeight)/2);
		    var iLeft = Math.round((screen.availWidth-iWidth)/2);
		    window.showModalDialog(url, "<s:property value="#session.TITLE_IN_SESSION.get('CSG_QST')" />", "dialogHeight:" + iHeight + "px; dialogWidth:"+iWidth+"px; toolbar:no; menubar:no; scrollbars:no; resizable:no; location:no; status:no; dialogLeft:" + iLeft + "px; dialogTop:" + iTop +"px;");    
		}
		</s:if>

		<s:if test="#session.USER_DROITS.contains('CSG_DET')">
		//Détail
		function detail(id){
			changeData("consigneID", id);
			submitData("showDetailConsigneAction.action");
		}
		</s:if>
		
		function downloadFile(path, type){
			changeData('inputPath', path);
			changeData("typeID", type);
			document.getElementById('downloadForm').submit();
		}

		//ouvrir dans un autre onglet	
		function redirectIncident(id, cloture){	
			var form =document.getElementById("mainForm");
			form.target = "_blank";
			changeData('envirSelected', id);
			changeData('clotureDemiJournee', cloture);
			submitData('showIncidents.action');
			form.target = "_self";		
		}

		function addComment(i, e){
			x = (navigator.appName.substring(0,3) == "Net") ? e.pageX : event.x+document.body.scrollLeft;
			y = (navigator.appName.substring(0,3) == "Net") ? e.pageY : event.y+document.body.scrollTop;
			if ($("#divcomment" + i).is(":hidden")) {
				$("#divcomment" + i).show();
			}
			else{
				$("#divcomment" + i).hide();
			}
			document.getElementById("divcomment" + i).style.position = "absolute";
			document.getElementById("divcomment" + i).style.top = y + 50;
			document.getElementById("divcomment" + i).style.left = x;
		}

		function closeComment(i){
			$("#divcomment" + i).hide();
			if(document.getElementById("comment" + i).value!=""){
				document.getElementById("img" + i).src = '<s:property value="#session.ENSEIGNE" />/img/warning.png';
			}
			else{
				document.getElementById("img" + i).src = '<s:property value="#session.ENSEIGNE" />/img/comment.png';
			}
		}

		function saveComment(id){
			var comment = document.getElementById("comment"+id).value; 
            $.ajax({
               type: "POST",
               url: "saveTdBComment.action",
               data: "envir=" + id + "&comment=" + comment,
               success: function(response){
                   alert(response);
            	   closeComment(id);
            	   
               },
 	           error: function(e){
	           		alert('Error: ' + e);
	           }
			});		
		}
				
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('TDB_LST')" /></s:param>
	</s:include>
	
	<div class="contentTable" style="width:90%">
		<s:form id="mainForm" method="POST" action="showConsignesAction" theme="simple">
			<s:token></s:token>
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage"/>
			<s:hidden name="nrPages" value="%{pagination.nrPages}" />
			<s:hidden name="pageQuotidienne" id="pageQuotidienne" value="%{paginationQuotidienne.page}"/>
			<s:hidden name="nrPerPageQuotidienne" id="nrPerPageQuotidienne"/>
			<s:hidden name="nrPagesQuotidienne" value="%{paginationQuotidienne.nrPages}" />
			<s:hidden name="pageInterEquipe" id="pageInterEquipe" value="%{paginationInterEquipe.page}"/>
			<s:hidden name="nrPerPageInterEquipe" id="nrPerPageInterEquipe"/>
			<s:hidden name="nrPagesInterEquipe" value="%{paginationInterEquipe.nrPages}" />
			<s:hidden name="consigneID" id="consigneID" value=""/>
			<s:hidden name="envirSelected" id="envirSelected" value=""/>
			<s:hidden name="filtreIncidents" id="filtreIncidents" value=""/>
			<s:hidden name="clotureDemiJournee" id="clotureDemiJournee" value=""/>
			<s:hidden name="typeConsigne" id="typeConsigne" value=""/>
			
			<div id="infoInterEquipe" style="width:70%; margin-left:auto; margin-right:auto;">
				<s:if test="#session.USER_DROITS.contains('CSG_ADD')">
						<div class="plusDiv">
							<div id="plus_consigne_interEquipe" >
								<s:a cssClass="ajouter" href="#" onclick="javascript:ajouter(3)">
									<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" >&nbsp;ajouter
								</s:a>
							</div>
						</div>
				</s:if>
				
				<table class="dataTable" cellpadding="5" cellspacing="0" rules="all">
					<col width="15%">
					<col width="">
					<col width="10%">
					<thead>
						<tr>
							<td colspan="3">
								<s:include value="/jsp/user_pages/pagination/pagination_consignesInterEquipe.jsp" />
							</td>
						</tr>
						<tr>
							<th class="titreColonne">Date</th>
							<th class="titreColonne">Consignes Inter-Equipe</th>
							<th class="titreColonne">Action</th>
					</tr>
					</thead>
					<tbody>
						<s:if test="consignesInterEquipe.isEmpty()">
							<tr>
								<td colspan="3" class="emptyListText">Aucune consignes en cours</td>
							</tr>
						</s:if>
						<s:else>
							<s:iterator value="consignesInterEquipe">
								<tr>
									<s:if test="couleur">
										<td align="center"><span class="ligneImportante"><s:property value="dateFormattee"/><br/><s:property value="heureFormattee"/></span></td>
										<td align="left"><span class="ligneImportante">
											<s:property escapeHtml="false" value='%{text.replace("\n","<br>")}' /><br>
											<a href='javascript:downloadFile("<s:property value="fichierconsigne" />", <s:property value="type.id" />)'><s:property value="fichierconsigne" /></a>
										</span></td>
									</s:if>
									<s:else>
										<td align="center"><s:property value="dateFormattee"/><br/><s:property value="heureFormattee"/></td>
										<td align="left">
											<s:property escapeHtml="false" value='%{text.replace("\n","<br>")}' /><br>
											<a href='javascript:downloadFile("<s:property value="fichierconsigne" />", <s:property value="type.id" />)'><s:property value="fichierconsigne" /></a>
										</td>
									</s:else>
									<td class="td" align="center">
										<s:if test="#session.USER_DROITS.contains('CSG_MOD')">
											<s:a href="#" onclick="javascript:modifier('%{id}');">
												<img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png">
											</s:a>
										</s:if>
										<s:if test="#session.USER_DROITS.contains('CSG_DEL')">
											<s:a href="#" onclick="javascript:supprimer('%{id}');">
												<img class="icone"  alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png">
											</s:a>
										</s:if>
										<s:if test="#session.USER_PROFIL.pilote">
											<s:if test="! listValidatedConsigneID.contains(id)">
												<s:a href="#" onclick="javascript:valider('%{id}');">
													<img class="icone" alt="Valider" title="Valider" src="<s:property value="#session.ENSEIGNE" />/img/valider-16.png">
												</s:a>
											</s:if>
											<s:a href="#" onclick="javascript:question('%{id}');">
												<img class="icone" alt="Question" title="Question" src="<s:property value="#session.ENSEIGNE" />/img/question-16.png">
											</s:a>
										</s:if>
										
										<s:if test="#session.USER_DROITS.contains('CSG_DET')">
											<s:a href="#" onclick="javascript:detail('%{id}');">
												<img class="icone" alt="Détail" title="Détail" src="<s:property value="#session.ENSEIGNE" />/img/detail-16.png">
											</s:a>
										</s:if>
									</td>
								</tr>
							</s:iterator>
						</s:else>
					</tbody>
				</table>
			</div>
			</br>
			
			<div id="tableauDeBord" style="width:80%;margin-left:auto;margin-right:auto;">
				<s:if test="environnements.isEmpty()">
					Aucun incident en cours
				</s:if>
				<s:else>
					<s:iterator value="environnements" status="ind">
						<div style='box-shadow:0px 3px 3px 0px rgba(0, 0, 0, 0.5),0px 3px 3px 0px rgba(255, 255, 255, 0.5) inset;  margin:10px; vertical-align:middle; display:inline-block; line-height:<s:property value="%{tailleDiv[#ind.index]}"/>; width:200px; height:60px; background-color:<s:property value="%{couleursEnCours[#ind.index]}"/>'>							
							<s:if test='commentaires[#ind.index]==null || commentaires[#ind.index].equals("")'>
								<a onclick="javascript:addComment(<s:property value="id"/>, event)"><img class="imgTDB" id="img<s:property value="id"/>" style="cursor:pointer; float:left; clear:right; margin-left:6px; margin-top:6px; width:16px; height:16px;" title="Ajouter commentaire" src='<s:property value="#session.ENSEIGNE" />/img/comment.png'/></a>
							</s:if>
							<s:else>
								<a onclick="javascript:addComment(<s:property value="id"/>, event)"><img class="imgTDB" id="img<s:property value="id"/>" style="cursor:pointer; float:left; clear:right; margin-left:6px; margin-top:6px; width:16px; height:16px;" title="Ajouter commentaire" src='<s:property value="#session.ENSEIGNE" />/img/warning.png'/></a>
							</s:else>
							<div style="position: absolute; display:none; width:320px;" id="divcomment<s:property value="id"/>">
								<table style="background:#948683;" border="1" rules="none" bgcolor="#FFFFFF" cellpadding="5">
									<col width="16%"/>
									<col width="68%"/>
									<col width="16%"/>
									<tr>
										<td></td>
										<td>
										    Commentaire <b><s:property value="environnement"/></b>
											<textarea rows="5" cols="40" id="comment<s:property value="id"/>" name="comment<s:property value="id"/>"><s:property value="%{commentaires[#ind.index]}"/></textarea>
											<input type="button" value="Save" onclick="javascript:saveComment(<s:property value="id"/>)"/>
										</td>
										<td align="right" valign="top">
											<a onclick="javascript:closeComment(<s:property value="id"/>);">
												<img alt="Fermer" src='<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png'/>
											</a>
										</td>
									</tr>
								</table>
							</div>
						
							<img class="imgTDB" onclick="javascript:redirectIncident(<s:property value="id"/>, 1)" style="cursor:pointer; float:right; clear:right; margin-right:6px; margin-top:6px; width:16px; height:16px;" title="Incident <12h" src='<s:property value="#session.ENSEIGNE" />/img/<s:property value="%{couleursResolu[#ind.index]}" />'/>
							
							<span class="envirTDB" style="cursor:pointer;" title="Incidents en cours" onclick="javascript:redirectIncident(<s:property value="id"/>, 0)"><s:property value="environnement"/></span>
						</div>					
					</s:iterator>
				</s:else>
			</div>
			
			<div id="consigne_quotidienne" style="width:48%; float:left;">
				<s:if test="#session.USER_DROITS.contains('CSG_ADD')">
						<div class="plusDiv">
							<div class="plus" >
								<s:a cssClass="ajouter" href="#" onclick="javascript:ajouter(2)">
									<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" >&nbsp;ajouter
								</s:a>
							</div>
						</div>
				</s:if>
				
				<table class="dataTable" cellpadding="5" cellspacing="0" rules="all">
					<col width="15%">
					<col width="">
					<col width="10%">
					<thead>
						<tr>
							<td colspan="3">
								<s:include value="/jsp/user_pages/pagination/pagination_consignesQuotidienne.jsp" />
							</td>
						</tr>
						<tr>
							<th class="titreColonne">Date</th>
							<th class="titreColonne">Consignes Quotidiennes</th>
							<th class="titreColonne">Action</th>
					</tr>
					</thead>
					<tbody>
						<s:if test="consignesQuotidienne.isEmpty()">
							<tr>
								<td colspan="3" class="emptyListText">Aucune consignes en cours</td>
							</tr>
						</s:if>
						<s:else>
							<s:iterator value="consignesQuotidienne">
								<tr>
									<s:if test="couleur">
										<td align="center"><span class="ligneImportante"><s:property value="dateFormattee"/><br/><s:property value="heureFormattee"/></span></td>
										<td align="left"><span class="ligneImportante">
											<s:property escapeHtml="false" value='%{text.replace("\n","<br>")}' /><br>
											<a href='javascript:downloadFile("<s:property value="fichierconsigne" />", <s:property value="type.id" />)'><s:property value="fichierconsigne" /></a>
										</span></td>
									</s:if>
									<s:else>
										<td align="center"><s:property value="dateFormattee"/><br/><s:property value="heureFormattee"/></td>
										<td align="left">
											<s:property escapeHtml="false" value='%{text.replace("\n","<br>")}' /><br>
											<a href='javascript:downloadFile("<s:property value="fichierconsigne" />", <s:property value="type.id" />)'><s:property value="fichierconsigne" /></a>
										</td>
									</s:else>
									<td class="td" align="center">
										<s:if test="#session.USER_DROITS.contains('CSG_MOD')">
											<s:a href="#" onclick="javascript:modifier('%{id}');">
												<img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png">
											</s:a>
										</s:if>
										<s:if test="#session.USER_DROITS.contains('CSG_DEL')">
											<s:a href="#" onclick="javascript:supprimer('%{id}');">
												<img class="icone"  alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png">
											</s:a>
										</s:if>
										<s:if test="#session.USER_PROFIL.pilote">
											<s:if test="! listValidatedConsigneID.contains(id)">
												<s:a href="#" onclick="javascript:valider('%{id}');">
													<img class="icone" alt="Valider" title="Valider" src="<s:property value="#session.ENSEIGNE" />/img/valider-16.png">
												</s:a>
											</s:if>
											<s:a href="#" onclick="javascript:question('%{id}');">
												<img class="icone" alt="Question" title="Question" src="<s:property value="#session.ENSEIGNE" />/img/question-16.png">
											</s:a>
										</s:if>
										
										<s:if test="#session.USER_DROITS.contains('CSG_DET')">
											<s:a href="#" onclick="javascript:detail('%{id}');">
												<img class="icone" alt="Détail" title="Détail" src="<s:property value="#session.ENSEIGNE" />/img/detail-16.png">
											</s:a>
										</s:if>
									</td>
								</tr>
							</s:iterator>
						</s:else>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3">
								<s:include value="/jsp/user_pages/pagination/pagination_consignesQuotidienne.jsp" />
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			
			<div id="consigne_permanente" style="width:48%; float:right;">
				<s:if test="#session.USER_DROITS.contains('CSG_ADD')">
					<div class="plusDiv">
						<div class="plus" >
							<s:a cssClass="ajouter" href="#" onclick="javascript:ajouter(1)">
								<img class="icone" src="<s:property value="#session.ENSEIGNE" />/img/ajouter-16.png" >&nbsp;ajouter
							</s:a>
						</div>
					</div>
				</s:if>
				
				<table class="dataTable" cellpadding="5" cellspacing="0" rules="all">
					<col width="15%">
					<col width="70%">
					<col width="10%">
					<thead>
						<tr>
							<td colspan="3">
								<s:include value="/jsp/user_pages/pagination/pagination.jsp" />
							</td>
						</tr>
						<tr>
							<th class="titreColonne">Date</th>
							<th class="titreColonne">Consignes Permanentes</th>
							<th class="titreColonne">Action</th>
					</tr>
					</thead>
					<tbody>
						<s:if test="consignesPermanente.isEmpty()">
							<tr>
								<td colspan="3" class="emptyListText">Aucune consignes en cours</td>
							</tr>
						</s:if>
						<s:else>
							<s:iterator value="consignesPermanente">
								<tr>
									<s:if test="couleur">
										<td align="center"><span class="ligneImportante"><s:property value="dateFormattee"/><br/><s:property value="heureFormattee"/></span></td>
										<td align="left"><span class="ligneImportante">
											<s:property escapeHtml="false" value='%{text.replace("\n","<br>")}' /><br>
											<a href='javascript:downloadFile("<s:property value="fichierconsigne" />", <s:property value="type.id" />)'><s:property value="fichierconsigne" /></a>
										</span></td>
									</s:if>
									<s:else>
										<td align="center"><s:property value="dateFormattee"/><br/><s:property value="heureFormattee"/></td>
										<td align="left">
											<s:property escapeHtml="false" value='%{text.replace("\n","<br>")}' /><br>
											<a href='javascript:downloadFile("<s:property value="fichierconsigne" />", <s:property value="type.id" />)'><s:property value="fichierconsigne" /></a>
										</td>
									</s:else>
									<td class="td" align="center">
										<s:if test="#session.USER_DROITS.contains('CSG_MOD')">
											<s:a href="#" onclick="javascript:modifier('%{id}');">
												<img class="icone" alt="Modifier" title="Modifier" src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png">
											</s:a>
										</s:if>
										<s:if test="#session.USER_DROITS.contains('CSG_DEL')">
											<s:a href="#" onclick="javascript:supprimer('%{id}');">
												<img class="icone"  alt="Supprimer" title="Supprimer" src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png">
											</s:a>
										</s:if>
										<s:if test="#session.USER_PROFIL.pilote">
											<s:if test="! listValidatedConsigneID.contains(id)">
												<s:a href="#" onclick="javascript:valider('%{id}');">
													<img class="icone" alt="Valider" title="Valider" src="<s:property value="#session.ENSEIGNE" />/img/valider-16.png">
												</s:a>
											</s:if>
											<s:a href="#" onclick="javascript:question('%{id}');">
												<img class="icone" alt="Question" title="Question" src="<s:property value="#session.ENSEIGNE" />/img/question-16.png">
											</s:a>
										</s:if>
										
										<s:if test="#session.USER_DROITS.contains('CSG_DET')">
											<s:a href="#" onclick="javascript:detail('%{id}');">
												<img class="icone" alt="Détail" title="Détail" src="<s:property value="#session.ENSEIGNE" />/img/detail-16.png">
											</s:a>
										</s:if>
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
			</div>
			
			<br/>
		</s:form>
		<s:form id="downloadForm" theme="simple" action="downloadConsigneFichierAction">
			<s:hidden id="inputPath" name="inputPath" value=""/>
			<s:hidden id="typeID" name="typeID"/>
		</s:form>
	</div>
</body>
</html>