<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base target="_self" />
    <sj:head debug="true" compressed="false" jqueryui="true" />
	<title><s:property value="#session.TITLE_IN_SESSION.get('PLN_MOD')" /></title>
	<link rel="shortcut icon" type="image/x-icon" href="<s:property value="#session.ENSEIGNE" />/img/favicon.ico" />
	<link rel="stylesheet" type="text/css" href="<s:property value="#session.ENSEIGNE" />/css/pilotage.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv='Expires' content='-10'>
	<meta http-equiv='Pragma' content='No-cache'>
	<meta http-equiv='Cache-Control' content='private'>
	<script type="text/javascript">
		function closeDiag() {
			window.close();
		}

		function valider() {
			var radioChecked = false;
			var radios = document.getElementsByName("selectedVacation");
			for (var i=0; i<radios.length; i++) {
				if (radios[i].checked) {
					radioChecked = true;
					break;
				}
			}
			if(radioChecked==false) {
				alert("Veuillez choisir une vacation. ");
				return false;
			} else {
				document.getElementById("mainForm").submit();
			}
		}
	</script>
</head>
<body onload="window.focus();">
	<s:if test="%{status != 1}">
		<s:form theme="simple" id="mainForm" action="modifyEquipeVacationsAction">
			<s:hidden name="equipeID"></s:hidden>
			
			<div align="center">
				<s:property value="equipe" /><br/>
				<s:property value="date" /><br/>
				Vacation : <s:property value="vacation" /><br/><br/>
				<s:if test="userModif!=null">
					Dernière modification : <s:property value="userModif.nom + ' ' + userModif.prenom"/><br/><s:property value="dateModif" />
				</s:if>
				<br/><br/>
				
				Du 			<sj:datepicker name="dateDebut" 
										id="pickDateD" 
										displayFormat="dd/mm/yy"
										label="Du "
										showOn="focus"
										onChangeTopics="filtreDateDebutChange">
							</sj:datepicker>								
				au 			<sj:datepicker name="dateFin" 
										id="pickDateF" 
										displayFormat="dd/mm/yy" 
										label=" au "
										showOn="focus"
										onChangeTopics="filtreDateDebutChange">
							</sj:datepicker>								
				
				<table>
					<s:iterator value="listVacation">
						<tr>
							<td><font style="background-color:#<s:property value='codeCouleur'/>;">&nbsp;&nbsp;&nbsp;</font></td>
							<td align="left"><s:property value="libelle"/></td>
							<td><input type="radio" name="selectedVacation" value="<s:property value="libelle"/>" <s:if test="selectedVacation == id">checked="checked"</s:if>/></td>
						</tr>
					</s:iterator>
					<s:if test="#session.USER_PROFIL.admin">
						<s:if test="!vacationBackup">
							<tr>
								<td>&nbsp;*&nbsp;</td>
								<td align="left">Backup</td>
								<td><input type="radio" name="selectedVacation"
									value="Backup"
									<s:if test="vacation == 'Backup'">checked="checked"</s:if> />
								</td>
							</tr>
						</s:if>
						<s:if test="!vacationMaladie">
							<tr>
								<td>&nbsp;M&nbsp;</td>
								<td align="left">Maladie</td>
								<td><input type="radio" name="selectedVacation"
									value="Maladie"
									<s:if test="vacation == 'Maladie'">checked="checked"</s:if> />
								</td>
							</tr>
						</s:if>
						<s:if test="!vacationVisiteMedicale">
							<tr>
								<td>&nbsp;V&nbsp;</td>
								<td align="left">Visite Médicale</td>
								<td><input type="radio" name="selectedVacation"
									value="Visite Médicale"
									<s:if test="vacation == 'Visite Médicale'">checked="checked"</s:if> />
								</td>
							</tr>
						</s:if>
					</s:if>
				</table>
			
				<br/><br/><br/>
				<table>
					<tr>
						<td align="center"><s:a href="#" onclick="javascript:valider();" cssClass="boutonValider">Modifier</s:a></td>
						<td align="center"><s:a href="#" onclick="javascript:closeDiag();" cssClass="boutonRetour">Fermer</s:a></td>
					</tr>
				</table>
			</div>
		</s:form>
	</s:if>
	<s:else>
		<table class="questionTable">
			<tr>
				<td class="infoVacation" id="info"><s:property value="%{info}"/></td>
			</tr>
			<tr>
				<td class="errorVacation" id="error"><s:property value="%{error}"/> </td>
			</tr>
			<tr>
				<td align="center"><s:a href="#" onclick="javascript:closeDiag();" cssClass="boutonRetour">Fermer</s:a></td>
			</tr>
		</table>
	</s:else>
</body>
</html>
				