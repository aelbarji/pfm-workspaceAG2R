<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

	<script type="text/javascript">
		function deconnection() {
			window.location.replace("logoutAction.action");
		}
		
		function feedback() {
			window.location.replace("showFeedbackAction.action");
		}
				
		function modifyProfil(){
			submitData('showModifyUserProfilAction.action');
		}
		
	</script>
	<div class="header">
		<div class="headerHaut">
			<div class="headerHautGauche"></div>
			<div class="headerHautDroite"></div>
		</div>
		<div class="headerLogo">
			<a class="Logo"><img alt="Logo" src='<s:property value="#session.ENSEIGNE" />/img/logo.png' /></a>
		</div>
		<div class="headerTitle">
			<p><s:property value="#session.TITRE_APPLICATION" /></p>
		</div>
		<div class="headerInfo">
			<div class="headerInfoTop">
				<p>
					<a href="#" class="headerLink" onclick="javascript:modifyProfil();">Profil</a>&nbsp;&nbsp;
					<a href="#" class="headerLink" onclick="javascript:feedback()">Feedback</a>&nbsp;&nbsp;
					<a href="#" onclick="deconnection()" class="headerLink">D&eacute;connexion</a>
				</p>
			</div>
			<div class="headerInfoBottom">
				<p>
					<a><s:property value="#session.USER_LOGGED.nom" />&nbsp;<s:property value="#session.USER_LOGGED.prenom" /></a>
					<a>&nbsp;&nbsp;Le <s:date name="#session.DATE" format="dd/MM/yyyy"/></a>
				</p>
			</div>
	    </div>		
	</div>
	<div class="headerBottom">&nbsp;</div>