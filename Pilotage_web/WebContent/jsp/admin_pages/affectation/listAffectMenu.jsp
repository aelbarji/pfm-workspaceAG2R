<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/admin.css">
	<link rel="shortcut icon" type="image/x-icon" href="<s:property value="#session.ENSEIGNE" />/img/favicon.ico" />
	<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
	<script type="text/javascript" src="js/common_admin.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
		function onLoad(){
			if(document.getElementById("selectedProfil").value == ''){
				document.getElementById("boutonValider").style.display = "none";
				document.getElementById("tableauMenu").style.display = "none";
			}
		}
	
		function changeProfil(){
			//test si le profil est '' alors on cache le tableau, sinon on submit
			if(document.getElementById("selectedProfil").value == ''){
				document.getElementById("boutonValider").style.display = "none";
				document.getElementById("tableauMenu").style.display = "none";
			}
			else{
				submitData('selectChangedAffecAdminAction.action');
			}
		}

		function onCheckBoxChange(checkbox, id){
			//si on la coche, verification que les parents sont checked 
			if(checkbox.checked){
				if(id.indexOf('-') != -1){
					var parentID = id.substring(0, id.lastIndexOf('-'));
					var parent = document.getElementById(parentID);
					if(!parent.checked){
						parent.checked = true;
						onCheckBoxChange(parent, parentID);
					}
				}
			}

			//si on la décoche, on décoche les fils
			else{
				var i = 0;
				var childID = id + "-0";
				var child = document.getElementById(childID);
				while(child){
					if(child.checked){
						child.checked = false;
						onCheckBoxChange(child, childID);
					}
					++i;
					var childID = id + "-" + i;
					child = document.getElementById(childID);
				}
			}
		}
	</script>
</head>
<body onload="javascript:onLoad();">
	<div class="error" id="error"><s:label value="%{error}" cssClass="error" id="error"></s:label></div>
	<div class="info" id="info"><s:label value="%{info}" cssClass="info" id="info"></s:label></div>
	<div class="titrePage">
		<s:label value="Affectation des menus" cssClass="title"></s:label>
	</div>
	<div class="Pagecontents" style="width: 90%;">
		<s:form theme="simple" id="mainForm">
			<s:a>Profil : </s:a>
			<s:select list="%{listProfil}" listKey="id" listValue="libelle" name="selectedProfil" id="selectedProfil" emptyOption="true" onchange="javascript:changeProfil();"/>
			<s:if test="%{listMenu != null}">
				<div id=boutonValider>
					<s:a href="# " cssClass="valider" onclick="javascript:submitData('changeAffectationAdminAction.action')">VALIDER</s:a>
				</div>
				<table id="tableauMenu" border="1" rules="rows" cellpadding="2" cellspacing="0" width="100%" class="tabCenter">
					<thead>
						<tr>
							<th class="colTitle" width="70%">Libellé</th>
							<th class="colTitle" width="10%">ID</th>
							<th class="colTitle" width="10%">Place</th>
							<th class="colTitle" width="5%"></th>
						</tr>
					</thead>
					<tbody>
						<s:if test="%{listMenu.size() == 0}">
							<tr>
								<td class="emptyListText" colspan="4">
									Aucun menu n'est défini en base de données
								</td>
							</tr>
						</s:if>
						<s:else>
							<s:iterator value="listMenu" id="rt" status="status">
								<s:set name="id" value="#rt.menu.id"></s:set>
								<tr class="<s:if test="#status.odd == true">oddRow</s:if><s:else>evenRow</s:else>">
									<td>
										<c:forEach var="i" begin="1" end="${rt.level}" step="1">
											&emsp;&emsp;
										</c:forEach>
				 						<s:property value="#rt.menu.libelle" />
									</td>
									<td align="center"><s:property value="#rt.menu.id" /></td>
									<td align="center"><s:property value="#rt.menu.place" /></td>
									<td align="center">
										 <s:checkbox name="%{id}" id="%{menuID.get(#rt.menu.id)}" value="%{affectMenu.contains(#rt.menu.id)}" onclick="javascript:onCheckBoxChange(this, '%{menuID.get(#rt.menu.id)}');"/> 
										<!-- <s:checkbox name="#rt.menu.id" id="%{menuID.get(#rt.menu.id)}" value="%{affectMenu.contains(#rt.menu.id)}" onclick="javascript:onCheckBoxChange(this, '%{menuID.get(#rt.menu.id)}');"/>--> 
									</td>
								</tr>
							</s:iterator>
						</s:else>
					</tbody>
				</table>
			</s:if>
		</s:form>
	</div>
</body>
</html>