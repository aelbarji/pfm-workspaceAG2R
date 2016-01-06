<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:include value="/jsp/user_pages/commun/html_head.jsp" />
<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
<script type="text/javascript">
	//action de supression
	function validerSupprimer(typeID) {
		if (confirm("voulez-vous vraiment supprimer ce type ?")) {
			changeData('typeID', typeID);
			submitData('deleteTypeIncident.action');
		}
	}
	//action de modification
	function modifier(id) {
		changeData('typeID', id);
		submitData('showModifyTypeIncident.action');
	}
	//action d'ajout
	function ajouter() {
		submitData('redirectCreateTypeIncidentAction.action');
	}

	//Method helper pour le drag et methode
	var fixHelperModified = function(e, tr) {
		var $originals = tr.children();
		var $helper = tr.clone();
		$helper.children().each(function(index) {
			$(this).width($originals.eq(index).width())
		});
		return $helper;
	}, updateIndex = function(e, ui) {
		$('td.index', ui.item.parent()).each(function(i) {
			$(this).html(i + 1);
		});
	};
	$(function() {
		$("#sort tbody").sortable({
			opacity: 0.6,
			cursor: 'move',
			helper : fixHelperModified,
			update : updateIndex, 
			stop : saveImpactPosition
		}).disableSelection();
	});

	var saveImpactPosition = function(e,ui){
		var table = document.getElementById("sort");
		var map = {};
		var rowLength = table.rows.length;

		for(var i=1; i<rowLength; i+=1){
		  var row = table.rows[i];
		  key = row.cells[0].innerHTML;
		  value = row.cells[1].innerHTML;
		  map[key] = value;
	
		}
	    $.ajax({
            type: "POST",
            url: "saveImpactTypesIncidentAction.action?map="+JSON.stringify(map),
            contentType : 'application/json',
            success: function(response){
            	 $('#info').html(response); 
            },
	           error: function(e){
	           		alert('Error: ' + e);
	           }
			});	
		
	}
	

</script>
</head>
<body >
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre">
			<s:property value="#session.TITLE_IN_SESSION.get('ICD_TYPE')" />
		</s:param>
	</s:include>
	<div id="info" style="color: green;"></div>
	<div class="contentTable" style="width: 45%">
		<s:form id="mainForm" theme="simple" action="showTypesIncidents" >
			<s:hidden name="typeID" id="typeID" />
			<s:if test="#session.USER_DROITS.contains('ENV_ADD')">
				<s:include value="/jsp/user_pages/commun/html_body_div_plus.jsp" />
				<br />
			</s:if>
			<table id="sort" class="dataTable" width="100%" border="1"
				rules="all" cellpadding="2">
				<col width="5%" />
				<s:if
					test="#session.USER_DROITS.contains('ICD_TYP_MOD') || #session.USER_DROITS.contains('ICD_TYP_DEL')">
					<col width="30%" />
				</s:if>
				<thead>
					<tr>
						<th class="titreColonne">Impact</th>
						<th class="titreColonne">Type</th>
						<th class="titreColonne">Description</th>
						<th class="titreColonne">Titre Bilan</th>
						<s:if
							test="#session.USER_DROITS.contains('ICD_TYP_MOD') || #session.USER_DROITS.contains('ICD_TYP_DEL')">
							<th class="titreColonne">Actions</th>
						</s:if>
					</tr>
				</thead>
				<tbody>
					<s:if test="listType.isEmpty()">
						<tr>
							<td class="emptyListText"
								<s:if test="#session.USER_DROITS.contains('ICD_TYP_MOD') || #session.USER_DROITS.contains('ICD_TYP_DEL')">colspan="2"</s:if>>
								Aucun type trouvé</td>
							<td class="emptyListText"
								<s:if test="#session.USER_DROITS.contains('ICD_TYP_MOD') || #session.USER_DROITS.contains('ICD_TYP_DEL')">colspan="2"</s:if>>
								Aucun</td>
							<td class="emptyListText"
								<s:if test="#session.USER_DROITS.contains('ICD_TYP_MOD') || #session.USER_DROITS.contains('ICD_TYP_DEL')">colspan="2"</s:if>>
								Aucun</td>
							<td class="emptyListText"
								<s:if test="#session.USER_DROITS.contains('ICD_TYP_MOD') || #session.USER_DROITS.contains('ICD_TYP_DEL')">colspan="2"</s:if>>
								Aucun</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="listType">
							<tr>
								<td class="index" align="center"><s:property
										value="%{impact}" /></td>
								<td align="left"><s:property
										value="%{type}" /></td>
								<td align="left"><s:property
										value="%{description}" /></td>
								<td align="left"><s:property
										value="%{titre_bilan}" /></td>
								<s:if
									test="#session.USER_DROITS.contains('ICD_TYP_MOD') || #session.USER_DROITS.contains('ICD_TYP_DEL')">
									<td align="center"><s:if test="%{id!=1 && id!=2 && id!=3}">
											<s:if test="#session.USER_DROITS.contains('ICD_TYP_MOD')">
												<s:a href="#" onclick="javascript:modifier('%{id}')">
													<img class="icone" alt="Modifier" title="Modifier"
														src="<s:property value="#session.ENSEIGNE" />/img/modifier-16.png" />
												</s:a>
											</s:if>
											<s:if test="#session.USER_DROITS.contains('ICD_TYP_DEL')">
												<s:a href="#" onclick="javascript:validerSupprimer('%{id}')">
													<img class="icone" alt="Supprimer" title="Supprimer"
														src="<s:property value="#session.ENSEIGNE" />/img/supprimer-16.png" />
												</s:a>
											</s:if>
										</s:if> <s:else>
											Non modifiable
										</s:else>
									</td>
								</s:if>
							</tr>
						</s:iterator>
					</s:else>
				</tbody>
			</table>
			<span class="champObligatoire">* Réorganiser l'ordre des impacts avec la fonction "Drag And Drop" (Glisser/Déposer).</span>
		</s:form>
	</div>
</body>
</html>
