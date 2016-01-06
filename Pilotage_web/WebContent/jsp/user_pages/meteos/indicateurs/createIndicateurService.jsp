<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function valider() {
			var mainForm = document.getElementById("mainForm");
			if(document.getElementById("envir").value==-1){
				alert("Merci de renseigner l'environnement.");
			}else if(document.getElementById("type").value==-1){
				alert("Merci de renseigner le type de l'indicateur.");
			}else{
				mainForm.submit();
			}
		}

		function retour(){
			submitData('showModifyIndicateurServiceAction.action');
		}

		function verifType(value){
			var format = value.split('-')[0];
			if(format==2){
				var th = document.createElement("th");
				th.setAttribute("class","titreColonne");
				th.setAttribute("id","th_debut");
				var text = document.createTextNode("Ajout champ heure début");
				th.appendChild(text);
				var th2 = document.createElement("th");
				th2.setAttribute("class","titreColonne");
				th2.setAttribute("id","th_fin");
				var text2 = document.createTextNode("Ajout champ heure fin");
				th2.appendChild(text2);
				document.getElementById("tr_th").appendChild(th);
				document.getElementById("tr_th").appendChild(th2);

				var td = document.createElement("td");
				td.setAttribute("align", "center");
				td.setAttribute("id", "td_debut");
				var oui = document.createTextNode("oui");
				var input = document.createElement("input");
				input.setAttribute("type", "radio");
				input.setAttribute("value", "1");
				input.setAttribute("id", "debut");
				input.setAttribute("name", "debut");
				var non = document.createTextNode("non");
				var input2 = document.createElement("input");
				input2.setAttribute("type", "radio");
				input2.setAttribute("value", "0");
				input2.setAttribute("id", "debut");
				input2.setAttribute("name", "debut");
				input2.setAttribute("checked", "CHECKED");
				td.appendChild(oui);
				td.appendChild(input);
				td.appendChild(non);
				td.appendChild(input2);

				var td2 = document.createElement("td");
				td2.setAttribute("align", "center");
				td2.setAttribute("id", "td_fin");
				var oui2 = document.createTextNode("oui");
				var input3 = document.createElement("input");
				input3.setAttribute("type", "radio");
				input3.setAttribute("value", "1");
				input3.setAttribute("id", "fin");
				input3.setAttribute("name", "fin");
				var non2 = document.createTextNode("non");
				var input4 = document.createElement("input");
				input4.setAttribute("type", "radio");
				input4.setAttribute("value", "0");
				input4.setAttribute("id", "fin");
				input4.setAttribute("name", "fin");
				input4.setAttribute("checked", "CHECKED");
				td2.appendChild(oui2);
				td2.appendChild(input3);
				td2.appendChild(non2);
				td2.appendChild(input4);

				document.getElementById("tr_body").appendChild(td);
				document.getElementById("tr_body").appendChild(td2);
			}
			else{
				if(document.getElementById("th_debut")!=null){
					document.getElementById("tr_th").removeChild(document.getElementById("th_debut"));
					document.getElementById("tr_th").removeChild(document.getElementById("th_fin"));
					document.getElementById("tr_body").removeChild(document.getElementById("td_debut"));
					document.getElementById("tr_body").removeChild(document.getElementById("td_fin"));
				}
			}
		}
		
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('MTG_IND_A')" /></s:param>
	</s:include>

	<div class="contentTable" style="width: 70%">
		<s:form id="mainForm" theme="simple" action="showCreatePlageIndicateurServiceAction" method="POST">
		
			<s:token></s:token>
			<s:hidden name="selectRow" id="selectRow" value="%{service.id}"/>
			<s:hidden name="deleteIndic" id="deleteIndic" value="-1"/>

			<table class="formTable">
				<tr>
					<td align="left">Nom du Service :</td>
					<td align="left"><b><s:property value="nomService"/></b></td>
				</tr>	
				
			</table>
			<div class="titreTable"><b>Indicateurs</b></div>
			<table class="dataTable" rules="all" id="tableIndic">
				<thead>
					<tr id="tr_th">
						<th class="titreColonne">Environnment</th>
						<th class="titreColonne">Type Indicateur</th>
						<th class="titreColonne">Automatique</th>
					</tr>
				</thead>
				<tbody>
					<tr id="tr_body">
						<td align="center">
							<select id='envir' name='envir'>
								<option value='-1'></option>
								<s:iterator value="listEnvir">
								<option value='<s:property value='id'/>'>
								<s:property value='nom_envir'/></option>
								</s:iterator>
							</select>
						</td>
						<td align="center">
							<select id='type' name='type' onChange="javascript:verifType(this.options[this.selectedIndex].value);">
								<option value='-1'></option>
								<s:iterator value="listTypeIndic">
								<option value='<s:property value='%{format.id}'/>-<s:property value='id'/>'><s:property value='type'/></option>
								</s:iterator>
							</select>
						</td>
						<td align="center">
							oui<input type='radio' value='1' id='auto' name='auto'/>
							non<input type='radio' value='0' CHECKED id='auto' name='auto'/>
						</td>
						<!-- <td align="center">
							oui<input type='radio' value='1' id='debut' name='debut'/>
							non<input type='radio' value='0' CHECKED id='debut' name='debut'/>
						</td>
						<td align="center">
							oui<input type='radio' value='1' id='fin' name='fin'/>
							non<input type='radio' value='0' CHECKED id='fin' name='fin'/>
						</td>-->
					</tr>
				</tbody>
			</table>
			<s:include value="/jsp/user_pages/commun/html_body_div_contentTableBottom.jsp" />
		</s:form>
	</div>
</body>
</html>