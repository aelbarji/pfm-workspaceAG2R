<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<s:include value="/jsp/user_pages/commun/html_head.jsp" />
	<script type="text/javascript">
		function responseQuestion(idQuestion, idPilote) {
			var url = "responseQuestionAction.action?questionID=" + idQuestion + "&piloteID=" + idPilote;
			var iWidth = 400;
			var iHeight = 500;
			var iTop = (window.screen.height - iHeight - 100) / 2;
			var iLeft = (window.screen.width - iWidth) / 2;
			var title = "<s:property value='#session.TITLE_IN_SESSION.get("CSG_REP")' />";
			window.showModalDialog(
					url,
					title,
					"dialogHeight:" + iHeight + "px; dialogWidth:" + iWidth + "px; toolbar:no; menubar:no; scrollbars:no; resizable:no; location:no; status:no; dialogLeft:" + iLeft + "px; dialogTop:" + iTop +"px;");
		}
	</script>
</head>
<body>
	<s:include value="/jsp/user_pages/commun/html_body_top.jsp">
		<s:param name="titre"><s:property value="#session.TITLE_IN_SESSION.get('CSG_DET')" /></s:param>
	</s:include>
	
	<div class="contentTable">
		<table class="formTable" >
			<col width="28%"/>
			<col width="5%"/>
			<col width="28%"/>
			<col width="5%"/>
			<col width="28%"/>
			<col width="5%"/>
		
			<tr>
				<td align="center" colspan="2">
					<img alt="Camembert de consultation" src="drawChartAction.action?type=pie&titre=Consultation des consignes&legende=true&tips=true&width=350&height=300&is3D=false&n0=Pilotes ayant consultés les consignes&v0=${piloteConsultNum}&g0=170&n1=Pilotes n'ayant pas consulté les consignes&v1=${piloteNonConsult}&r1=255" />
				</td>
				<td align="center" colspan="2">
					<img alt="Camembert de lecture" src="drawChartAction.action?type=pie&titre=Lecture de la consigne&legende=true&tips=true&width=350&height=300&is3D=false&n0=Pilotes ayant lu la consigne&v0=${piloteLuNum}&g0=170&n1=Pilotes n'ayant pas lu la consigne&v1=${piloteNonLu}&r1=255" />
				</td>
				<td align="center" colspan="2">
					<img alt="Camembert de compréhension" src="drawChartAction.action?type=pie&titre=Compréhension de la consigne&legende=true&tips=true&width=350&height=300&is3D=false&n0=Pilotes ayant compris la consigne&v0=${piloteComp}&g0=170&n1=Pilotes n'ayant pas compris la consigne&v1=${piloteNonComp}&r1=255" />
				</td>
			</tr>
			
			<tr>
				<td colspan="6" align="center">&nbsp;</td>
			</tr>
			
			<tr>
				<td colspan="6" align="center">
					Nombre de pilotes : <s:property value="piloteNum"/>
				</td>
			</tr>
						
			<tr>
				<td align="left">Nombre de pilote ayant consulté la page des consignes depuis la publication :</td>
				<td align="left"><s:property value="%{piloteConsultNum}" /></td>
				<td align="left">Nombre de pilotes ayant lu la consigne :</td>
				<td align="left"><s:property value="%{piloteLuNum}" /></td>
				<td align="left">Nombre de pilote ayant compris la consigne :</td>
				<td align="left"><s:property value="%{piloteComp}" /></td>
			</tr>
									
			<tr>
				<td align="left">Pourcentage de consultation :</td>
				<td align="left">
					<fmt:formatNumber value="${piloteConsultNum/piloteNum}" pattern="#.##%" minFractionDigits="2"></fmt:formatNumber>
				</td>
				<td align="left">Pourcentage de lecture :</td>
				<td align="left">
					<fmt:formatNumber value="${piloteLuNum/piloteNum}" pattern="#.##%" minFractionDigits="2"></fmt:formatNumber>
				</td>
				<td align="left">Pourcentage de compréhension :</td>
				<td align="left">
					<fmt:formatNumber value="${piloteComp/piloteLuNum}" pattern="#.##%" minFractionDigits="2"></fmt:formatNumber>
				</td>
			</tr>
		</table>
		
		<br/>
		<table class="dataTable" cellpadding="2" cellspacing="0" border="1">
			<col width="20%"/>
			<col width="20%"/>
			<col width="55%"/>
			<col width="5%"/>
			
			<tr valign="middle">
				<th class="titreColonne" colspan="4">${consigne.text}</th>
			</tr>	
			<s:if test="listValidation.isEmpty()">
				<tr>
					<td colspan="4" class="emptyListText">Aucun pilote n'a encore consult&eacute; la consigne</td>
				</tr>
			</s:if>
			<s:else>
				<s:iterator value="listValidation">
					<tr>
						<td align="left"><s:property value="idPilote.nom + ' ' + idPilote.prenom"/></td>
						<td align="left"><s:property value="valid.libelleStatut"/></td>
						<td align="left"><s:property value='question.replaceAll("<", "&lt;").replaceAll("\n", "<br/>")' escape="false"/></td>
						<td align="center">
							<s:if test="valid.id == 1">
								<s:a href="#" onclick="javascript:responseQuestion('%{id}','%{idPilote.id}');">
									<img alt="Répondre" title="Répondre" class="icone" src="<s:property value="#session.ENSEIGNE" />/img/repondre-16.png" />
								</s:a>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</s:else>
		</table>
	</div>
</body>
</html>