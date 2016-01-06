<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:if test="isToday">
		<meta http-equiv="Refresh" content="1800">
	</s:if>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />

	<link rel="stylesheet" type="text/css" href="js/time/jquery.timeentry.css"/>
	<script type="text/javascript" src="js/time/jquery.timeentry.js"></script>
	<script type="text/javascript" src="js/time/jquery.timeentry-fr.js"></script>
	<script type="text/javascript" src="js/time/jquery.mousewheel.min.js"></script>
	<script type="text/javascript">
		var dateRefresh = '<s:property value="date"/>';
		var heureRefresh = '<s:property value="hour"/>';

		function onLoad(){
			<s:if test="isToday">
				setInterval("refreshList()", 5000);
			</s:if>
			setInterval("refreshHeure()", 20000); //heure Dom Tom
		}
		
		function afficherFiltres() {
			if ($("#filtres").is(":hidden")) {
				$("#filtres").slideDown('fast');
				$("#filtreString").slideUp('fast');
			} else {
				$("#filtres").slideUp('fast');
				$("#filtreString").slideDown('fast');
				reinitFilterValues();
			}
		}
		
		//action à la recherche avec filtre
		function lancerRecherche() {
			document.getElementById("mainForm").page.value = '1';
			document.getElementById("mainForm").submit();
		}
		
		function lancerRechercheHeure(elem) {
			if (event.keyCode == 13) {
				document.getElementById("mainForm").page.value = '1';
				document.getElementById("mainForm").submit();
			}else if (event.keyCode == 8) {
				clearContents(elem);
			}
		}
		
		function reinitFilterValues() {
			var mainForm = document.getElementById("mainForm");
			document.getElementById("filtreTypeListe" + '<s:property value="filtreTypeListe"/>').checked = true;
			document.getElementById("mainForm").filtreChampHeure.value = '<s:property value="filtreChampHeure" />';
			document.getElementById("mainForm").filtreChampTache.value = '<s:property value="filtreChampTache" />';
			document.getElementById("mainForm").filtreChampEnvironnement.value = '<s:property value="filtreChampEnvironnement" />';
			document.getElementById("mainForm").filtreChampCriticite.value = '<s:property value="filtreChampCriticite" />';
			var objSelect = document.getElementById("filtreEnvironnement");
			var len = objSelect.options.length;
			for (var i = 0; i < len; i++){
				objSelect.options[i].selected = false ;

				<s:iterator value="%{filtreEnvironnement}">
					if(objSelect.options[i].value == <s:property/>){
						objSelect. options[i].selected = true;
					}
				</s:iterator>
			}
		}
		
		function lancerRecherche() {
			document.getElementById("mainForm").page.value = '1';
			document.getElementById("mainForm").submit();
		}

		function reinitialiserLesFiltres(){
			document.getElementById("filtreTypeListe0").checked = true;
			document.getElementById("mainForm").filtreChampHeure.value = '';
			document.getElementById("mainForm").filtreChampEnvironnement.value = '-1';
			document.getElementById("mainForm").filtreChampTache.value = '';
			document.getElementById("mainForm").filtreChampCriticite.value = '-1';

			var objSelect = document.getElementById("filtreEnvironnement");
			var len = objSelect.options.length;
			for (var i = 0; i < len; i++){
				objSelect.options[i].selected = false;
			}

			lancerRecherche();
		}

		function modifyStatut(id, value){
			if(document.getElementById("userModif" + id).innerHTML.replace(/^\s+/g,'').replace(/\s+$/g,'') != "" && document.getElementById("userModif" + id).innerHTML != "<s:property escape='false' value='#session.USER_LOGGED.nom' /> <s:property escape='false' value='#session.USER_LOGGED.prenom' />"){
				if(!confirm("Cette tache a été prise par un autre pilote. Voulez-vous vous vraiment changer son statut ?")){
					return;
				}
			}
			
			var statut;
			var idLigne;
			var color;

			var enviroSelected;
			var dateDebut;
			var heureDebut;
			var observation;

			var error;
			
			$.ajax({
			  	type : 'POST',
				url : 'modifyChecklistAction.action' ,
				data : 'selectRow=' + id + '&selectedStatut=' + value  + '&selectedDate=' + document.getElementById("selectedDate").value ,
				success : function(data){
					$.each(data.modif, function (index, elem) {
						var id = elem.id;

						if(id == "statut"){
							statut = elem.value;
						} else if(id == "id"){
							idLigne = elem.value;
						} else if(id == "color"){
							color = elem.value;
						} else if(id == "enviroSelected"){
							enviroSelected = elem.value;
						} else if(id == "dateDebut"){
							dateDebut = elem.value;
						} else if(id == "heureDebut"){
							heureDebut = elem.value;
						} else if(id == "observation"){
							observation = elem.value;
						};
					});

					if(statut == "ok"){
						$('#ligne' + idLigne).css("background-color", '#' + color);
						$('#ligne' + idLigne).hover(
							function(){
								$(this).css("background-color", '#EEEEEE'); //mouseover
							},
							function(){
								$(this).css("background-color", '#' + color); // mouseout
							}
						);
					} else if(statut == "incident"){
						changeData('enviroSelected', enviroSelected);
						changeData('dateDebut', dateDebut);
						changeData('heureDebut', heureDebut);
						changeData('observation', observation);
						submitData("redirectCreateIncident.action");
					}
				}
			});
		}

		function afficheConsigneAvecSautsLigne(id){
			if (document.getElementById("consigneTexte" + id) != null) {
				var affiche = document.getElementById("consigneTexte" + id).innerHTML.toString().replace('\n','<br/>','g');
				document.getElementById("consigneTexte" + id).innerHTML = affiche;
			}
		}
		function loadDocuments(idConsigne,id){
			var elementDocuments = "";
			$.ajax({
			  	type : 'POST',
				url : 'loadChecklistDocumentsAction.action' ,
				data : 'idConsigne=' + idConsigne ,
				success : function(data){
					elementDocuments += '<u>Fichiers associés </u> :<br />';
					$.each(data.doc, function (index, elem) {
					//	var id = elem.id;
					elementDocuments +=	'<a href="javascript:downloadFile(\''+elem.textDoc+'\')">';
					elementDocuments += '&nbsp;&nbsp;-&nbsp;'+elem.textDoc;
					elementDocuments += '</a>';	
					elementDocuments += '<br/>';
					});
					document.getElementById("documentTexte" + id).innerHTML = elementDocuments;
				}
			});
			}
		function displayConsigne(id, e){
			x = (navigator.appName.substring(0,3) == "Net") ? e.pageX : event.x+document.body.scrollLeft;
			y = (navigator.appName.substring(0,3) == "Net") ? e.pageY : event.y+document.body.scrollTop;
			if ($("#consigne" + id).is(":hidden")) {
				$("#consigne" + id).show();
			}
			else{
				$("#consigne" + id).hide();
			}
			document.getElementById("consigne" + id).style.position = "absolute";
			document.getElementById("consigne" + id).style.top = y + 50;
			document.getElementById("consigne" + id).style.left = x;
		}

		function closeConsigne(id){
			$("#consigne" + id).hide();
		}

		function refreshList(){
			
			$.ajax({
			  	type : 'POST',
				url : 'refreshChecklist.action' ,
				data : 'date=' + dateRefresh + '&heure=' + heureRefresh ,
				success : function(data){
					var elements = false;
					var newDate = '';
					var newHour = '';
					$.each(data.lignes, function (index, elem) {
						var id = elem.id;

						if(id == "date"){
							newDate = elem.date;
						} else if(id == "hour"){
							newHour = elem.hour;
						} else{
							if(elem.statut == 1 || elem.statut == 5){
								$("#ligne" + id).remove();
							} else{
								$("#consigneTexte" + id).html(elem.consigne);
								afficheConsigneAvecSautsLigne(id);
								$("#statut" + id).val(elem.statut);
								$("#userModif" + id).html(elem.user);
								if(elem.user != "") {
									$("#dateHeureModif" + id).html(elem.updateTime);
								}
		
								$('#ligne' + id).css("background-color", '#' + elem.color);
								$('#ligne' + id).hover(
									function(){
										$(this).css("background-color", '#EEEEEE'); //mouseover
									},
									function(){
										$(this).css("background-color", '#' + elem.color); // mouseout
									}
								);
							}
							elements = true;
						}
					});
					if(elements && newDate != '' && newHour != ''){
						dateRefresh = newDate;
						heureRefresh = newHour;
					}
				}
			});
		}

		function refreshHeure(){
			$.ajax({
			  	type : 'POST',
				url : 'showHeureChecklist.action',
	            success: function(data) {
		            for(i=0; i<5;i++)
		            	changer_jjs(data[i].heure, data[i].pays);
				}
			});
		}

		function changeDate(){
			$(".dateValue").text($("#pickDate").val());
			document.getElementById("selectedDate").value = $("#pickDate").val();
			submitData("showChecklistAction.action");
		}

        $.subscribe('pickDateChange',function(event,data) {changeDate();});

		<s:if test="!isFutur">
			function addComment(id){
				var url = "addCommentAction.action?tacheID=" + id;
				var iWidth = 400; 
			    var iHeight = 500;
			    var iTop = Math.round((screen.availHeight-iHeight)/2);
			    var iLeft = Math.round((screen.availWidth-iWidth)/2);
			    window.showModalDialog(url, "<s:property value="#session.TITLE_IN_SESSION.get('CKL_COM')"/>", "dialogHeight:" + iHeight + "px; dialogWidth:"+iWidth+"px; toolbar:no; menubar:no; scrollbars:no; resizable:no; location:no; status:no; dialogLeft:" + iLeft + "px; dialogTop:" + iTop +"px;");    
			}
		
			function modifyConsigne(id){
				var url = "modifyChecklistConsigneAction.action?tacheID=" + id;
				var iWidth = 400; 
			    var iHeight = 500;
			    var iTop = Math.round((screen.availHeight-iHeight)/2);
			    var iLeft = Math.round((screen.availWidth-iWidth)/2);
			    window.showModalDialog(url, "<s:property value="#session.TITLE_IN_SESSION.get('CKL_CSG')"/>", "dialogHeight:" + iHeight + "px; dialogWidth:"+iWidth+"px; toolbar:no; menubar:no; scrollbars:no; resizable:no; location:no; status:no; dialogLeft:" + iLeft + "px; dialogTop:" + iTop +"px;");

				//refresh pour avoir la consigne à jour
			    document.getElementById("mainForm").submit();
			}
		</s:if>

		function clearContents(element) {
			  element.value = '';
			}
		
		$(function() {
			$('#filtreChampHeure').timeEntry({show24Hours: true ,showSeconds: false, defaultTime: '00:00:00', spinnerImage: ''});
		});

		function downloadFile(path){
			changeData('inputPath', path);
			document.getElementById('downloadForm').submit();
		}
	</script>
</head>
<body onload="onLoad();">
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('CKL_CUR')" /></s:param>
		<s:param name="titreDate" value="true"></s:param>
		<s:param name="datePicker" value="true"></s:param>
		<s:param name="datePickerUntilToday" value="false"></s:param>
		<s:param name="filtre" value="true"></s:param> 
	</s:include>

	<script language="javascript" src="horloge/heure.js"></script>
			<div id="heureLocal" style="margin-left:auto;margin-right:auto;width:500px;" >
				<s:iterator value="paysHeure" var="ville">
					<div class="heure">
						<span> <s:property value="#ville[0]" /></span><br />
						<img src="rev/0.gif" id="im1<s:property value='#ville[0]' />"><img src="" id="im2<s:property value='#ville[0]' />" ><img src="horloge/p.gif" width="7" height="21"><img src="" id="im3<s:property value='#ville[0]' />" ><img src="" id="im4<s:property value='#ville[0]' />">
					</div>
					
				<script>
					changer_jjs("<s:property value='#ville[1]' />", "<s:property value='#ville[0]' />");
				</script>
				</s:iterator>
			</div>
		<br />

	<s:if test="filtreJson != null">
		<div id="filtreString" class="filtreString">
<!--			<u><b>Filtres</b></u> <s:property value="%{filtreString}" /><br/>-->
			<a href="#" onclick="javascript:reinitialiserLesFiltres();">
				<img alt="Réinitialiser les filtres" title="Réinitialiser les filtres" src="<s:property value="#session.ENSEIGNE" />/img/reinitialiser-16.png" border="0" />
			</a>
		</div>
	</s:if>

<s:form id="mainForm" action="showChecklistAction" theme="simple">
	<div id="filtres" class="filterBox">
			<s:hidden id="enviroSelected" name="enviroSelected" value=""/>
			<s:hidden id="dateDebut" name="dateDebut" value=""/>
			<s:hidden id="heureDebut" name="heureDebut" value=""/>
			<s:hidden id="observation" name="observation" value=""/>
						
			<s:hidden id="selectRow" name="selectRow" value=""/>
			<s:hidden name="selectedDate" id="selectedDate" />
			<s:hidden name="selectedStatut" id="selectedStatut" />
									
			<s:hidden name="sort" id="sort" />
			<s:hidden name="sens" id="sens" />
			<s:hidden name="page" id="page" />
			<s:hidden name="nrPerPage" id="nrPerPage"/>
			<s:hidden name="nrPages" value="%{pagination.nrPages}" />
			<s:hidden name="validForm" value="1" />					
			<table class="tabFilter3Col">
				<col width="200px">
				<col width="200px">
				<tr>
					<td align="left">
						<b>Type de liste</b><br/>
						<input type="radio" id="filtreTypeListe0" name="filtreTypeListe" value="0" <s:if test="%{filtreTypeListe == 0}">checked="checked"</s:if>/>Liste normale (tâches &agrave; r&eacute;aliser)<br/>
						<input type="radio" id="filtreTypeListe1" name="filtreTypeListe" value="1" <s:if test="%{filtreTypeListe == 1}">checked="checked"</s:if>/>Liste de toutes les tâches du jour<br/>
						<input type="radio" id="filtreTypeListe2" name="filtreTypeListe" value="2" <s:if test="%{filtreTypeListe == 2}">checked="checked"</s:if>/>Liste des tâches exceptionnelles<br/>
					</td>
					<td align="left" rowspan="2">
						<b>Liste des environnements</b><br/>
						<s:select 	name="filtreEnvironnement" id="filtreEnvironnement" size="10" multiple="true"
									list="listEnvironnement" listKey="id" listValue="environnement" 
									cssStyle="width:100%;" theme="simple"/>
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td align="right" colspan="2">
						<a href=" #" onclick="javascript:lancerRecherche();" class="boutonRechercher">Rechercher</a>
					</td>
				</tr>
			</table>
	</div>
	

	<div class="contentTable">
		<table class="dataTableChecklist" rules="rows" cellpadding="5" border="1">
			<col width="16px"/>
			<col width="18%"/>
			<col width="18%"/>
			<col width="30%"/>
			<col width="12%"/>
			<col width="5%"/>
			<col width="5%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="8%"/>
			<thead>
				<tr>
					<td colspan="10">
						<s:include value="/jsp/user_pages/pagination/pagination_checklist.jsp" />
					</td>
				</tr>	
				<tr>
					<td class="titreColonne" ></td>
					<td class="titreColonne" >Heure <br/> 
						A partir de: <input style="width:40%" value="${filtreChampHeure}" id="filtreChampHeure" name="filtreChampHeure" maxlength="8" size="15" onKeyDown="javascript:lancerRechercheHeure(this);"/>
					</td>
					<td class="titreColonne" >Environnement <br/>
						<s:select list="%{listChampEnvironnement}" name="filtreChampEnvironnement" id="filtreChampEnvironnement" cssStyle="width:100%"  listKey="id" listValue="environnement" headerKey="-1" headerValue="" theme="simple" onchange="javascript:lancerRecherche();"/>
					</td>
					<td class="titreColonne" >Tache <br/>
						<s:textfield cssStyle="width:80%" id="filtreChampTache" name="filtreChampTache" maxlength="40" theme="simple" size="35" onchange="javascript:lancerRecherche();"/>
					</td>
					<td class="titreColonne" >Criticit&eacute; <br/>
						<s:select 	name="filtreChampCriticite" cssStyle="width:100%" id="filtreChampCriticite" list="%{listChampCriticite}" listKey="id" listValue="libelle" theme="simple" headerKey="-1" headerValue="" onchange="javascript:lancerRecherche();"/>
					</td>
					<td class="titreColonne" >Modif consigne</td>
					<td class="titreColonne" >Commentaire</td>
					<td class="titreColonne" >Statut</td>
					<td class="titreColonne" >Valid&eacute; par</td>
					<td class="titreColonne" >Heure validation</td>
				</tr>
			</thead>
			<tbody>
				<s:if test="listeTaches.isEmpty()">
					<tr>
						<td colspan="10" class="emptyListText">
							Aucune tache pour cette journée
						</td>
					</tr>
				</s:if>
				<s:else>
					<s:iterator value="listeTaches" id="tache" status="stat">
						<tr id="ligne<s:property value="id"/>" onmouseover="this.bgColor='#EEEEEE'" onmouseout="this.bgColor='#<s:property value='#tache.color'/>'" bgcolor="#<s:property value='#tache.color'/>">
							<td align="left">
								<a onclick="javascript:loadDocuments(<s:property value="%{sousTache.idConsigne.id}"/>,<s:property value="id"/>);javascript:displayConsigne(<s:property value="id"/>, event);">
									<img alt="Consigne" title="Consigne" src='<s:property value="#session.ENSEIGNE" />/img/fleche-droite-16.png'/>
								</a>
							</td>
							<td align="center">
								<div style="position: absolute; display: none; width: 500px;" id="consigne<s:property value="id"/>">
									<table border="1" rules="none" bgcolor="#FFFFFF" cellpadding="5" width="500px">
										<col width="90%"/>
										<col width="10%"/>
										<tr>
											<td id="consigneTexte<s:property value="id"/>"><s:property value='%{sousTache.idConsigne.consigne.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>")}' escape="false"/></td>
											<td align="right" valign="top">
												<a href="javascript:closeConsigne(<s:property value="id"/>);">
													<img alt="Fermer" src='<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png'/>
												</a>
											</td>
										</tr>
										<tr>
											<td colspan="2" id="documentTexte<s:property value="id"/>">
						<!-- 						<s:if test='sousTache.idConsigne.documentConsigne != null && sousTache.idConsigne.documentConsigne != ""'>
													<u>Fichiers associ&eacute;s </u> :<br/>
													<s:iterator value='sousTache.idConsigne.documentConsigne.split(";")'>&nbsp;&nbsp;- <a href="javascript:downloadFile('<s:property/>')"><s:property/></a><br/>
													</s:iterator>
												</s:if> -->
											</td>
										</tr>
									</table>
								</div>
												
								<s:date name="%{heureExecution}" format="HH:mm:ss" />
							</td>
							<td align="center"><s:property value="%{tache.environnement.environnement}"/></td>
							<td align="left">
								<b><s:property value="%{tache.tache}"/></b>
								<s:if test="%{sousTache.nomSousTache != null && sousTache.nomSousTache != ''}">
									: <s:property value="%{sousTache.nomSousTache}"/>
								</s:if>
							</td>
							<td align="center"><s:property value="%{tache.criticite.libelle}"/></td>
							<td align="center">
								<s:if test="!isFutur">
									<a href="javascript:modifyConsigne('<s:property value="id"/>');"><img alt="Consigne" title="Consigne" src="<s:property value="#session.ENSEIGNE" />/img/consigne-32.png"/></a>
								</s:if>
							</td>
							<td align="center">
								<s:if test="!isFutur">
									<a href="javascript:addComment('<s:property value="id"/>');"><img alt="Commentaire" title="commentaire" src="<s:property value="#session.ENSEIGNE" />/img/commentaire-32.png"/></a>
								</s:if>
							</td>
							<td align="center">
								<s:if test="isFutur">
									<select name="statut<s:property value="id"/>" id="statut<s:property value="id"/>" onchange="modifyStatut(<s:property value="id"/>, this.value)" <s:if test="#session.USER_PROFIL.pilote == false" > disabled </s:if> >
										<s:iterator value="%{listStatusFutur}" id="statusIterator">
											<option value="<s:property value="#statusIterator.id"/>" <s:if test="%{#tache.status.id == #statusIterator.id}">selected="selected"</s:if>><s:property value="%{#statusIterator.status}"/></option>
										</s:iterator>
									</select>
								</s:if>
								<s:else>
									<s:if test="mapToLate[id]">
										<s:property value="#tache.status.status"/>
									</s:if>
									<s:else>
										<select name="statut<s:property value="id"/>" id="statut<s:property value="id"/>" onchange="modifyStatut(<s:property value="id"/>, this.value);afficheConsigneAvecSautsLigne(<s:property value="id"/>)" <s:if test="#session.USER_PROFIL.pilote == false" > disabled </s:if> >
											<s:iterator value="%{listStatus}" id="statusIterator">
												<option value="<s:property value="#statusIterator.id"/>" <s:if test="%{#tache.status.id == #statusIterator.id}">selected="selected"</s:if>><s:property value="%{#statusIterator.status}"/></option>
											</s:iterator>
										</select>
									</s:else>
								</s:else>
							</td>
							<td align="center" id="userModif<s:property value="id"/>"><s:property value="%{user.nom}"/> <s:property value="%{user.prenom}"/></td>
							<td align="center" id="dateHeureModif<s:property value="id"/>">
								<s:if test="%{user.id != 0}">
									<s:property value="heureDateString"/><br/>
									<s:property value="heureHeureString"/>
								</s:if>
							</td>
						</tr>
					</s:iterator>
				</s:else>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="10">
						<s:include value="/jsp/user_pages/pagination/pagination_checklist.jsp" />
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
	</s:form>
	<s:form id="downloadForm" theme="simple" action="downloadConsigneFichierAction">
		<s:hidden id="inputPath" name="inputPath" value=""/>
	</s:form>
</body>
</html>