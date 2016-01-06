<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	<s:include value="/jsp/user_pages/header_menu/head.jsp" />
	<div class="headerContent"></div>
	<s:include value="/jsp/user_pages/header_menu/menu.jsp" />
	<div class="info" id="info"><s:property value="%{info}" /></div>
	<div class="error" id="error"><s:property value="%{error}" /></div>
	<div class="titleTop">
		<a>${param.titre} 
			<c:if test="${param.titreDate == 'true' }" >
				&nbsp;<span class="dateValue"><fmt:formatDate value="${selectedDate}" pattern="dd/MM/yyyy" /></span>
			</c:if>
		</a>
		
		<c:if test="${param.datePicker == 'true' && param.datePickerUntilToday == 'true'}" >
			<sj:datepicker name="selectedDate" id="pickDate" showOn="button" buttonImageOnly="true" displayFormat="dd/mm/yy" onChangeTopics="pickDateChange" maxDate="+0" ></sj:datepicker>
		</c:if>
		<c:if test="${param.datePicker == 'true' && (param.datePickerUntilToday == null || param.datePickerUntilToday == 'false')}" >
			<sj:datepicker name="selectedDate" id="pickDate" showOn="button" buttonImageOnly="true" displayFormat="dd/mm/yy" onChangeTopics="pickDateChange"></sj:datepicker>
		</c:if>
		
		<div class="titleTopRight">
			<p>
				<c:if test="${param.export == 'true' }" >
					<a href="#" onclick="javascript:exportExcel();">
						<img class="iconeOption" alt="Export" title="Export des 2 semaines à venir" src="<s:property value="#session.ENSEIGNE" />/img/export-16.png"/>
					</a>
				</c:if>	
				
				<c:if test="${param.exportPlanningSemaine == 'true' }" >
					<a href="#" onclick="javascript:exportExcel();">
						<img class="iconeOption" alt="Export" title="Export du planning de la semaine " src="<s:property value="#session.ENSEIGNE" />/img/export-16.png"/>
					</a>
				</c:if>	
				
				<c:if test="${param.exportPlanningMois == 'true' }" >
					<a href="#" onclick="javascript:exportExcel();">
						<img class="iconeOption" alt="Export" title="Export du planning du mois" src="<s:property value="#session.ENSEIGNE" />/img/export-16.png"/>
					</a>
				</c:if>	
				
				<c:if test="${param.exportPlanningMoisPilote == 'true' }" >
					<a href="#" onclick="javascript:exportExcel();">
						<img class="iconeOption" alt="Export" title="Export du planning du mois par pilote" src="<s:property value="#session.ENSEIGNE" />/img/export-16.png"/>
					</a>
				</c:if>	
				
				<c:if test="${param.exportCRA == 'true' }" >
					<a href="#" onclick="javascript:exportCRAF();">
						<img class="iconeOptionExport" alt="Export" title="Export du CRA" src="<s:property value="#session.ENSEIGNE" />/img/export-cra.png"/>
					</a>
				</c:if>	
						
				<c:if test="${param.filtre == 'true' }" >
					<a href="#" onclick="javascript:afficherFiltres();">
						<img class="iconeOption" alt="Filtres" title="Filtres" src="<s:property value="#session.ENSEIGNE" />/img/filtre-16.png"/>
					</a>
				</c:if>
				<c:if test="${param.alertesDisques == 'false' }" >
					<a href="#" onclick="javascript:alerteDisque();">
						<img class="iconeOption" alt="Alertes disques" title="Alertes disques" src="<s:property value="#session.ENSEIGNE" />/img/alerte_disque-16.png"/>
					</a>
				</c:if>
				<c:if test="${param.impression == 'true' }" >
					<a href="#" onclick="javascript:imprimer();">
						<img class="iconeOption" alt="Impression" title="Impression des statistiques incident" src="<s:property value="#session.ENSEIGNE" />/img/imprimer-16.png"/>
					</a>
				</c:if>	
				<c:if test="${param.mailastreinte == 'true' }" >
					<a href="#" onclick="javascript:selectDateDebut();">
						<img class="iconeOption" alt="Envoi de mail" title="Envoi du planning des astreintes" src="<s:property value="#session.ENSEIGNE" />/img/mail_send.png"/>
					</a>
				</c:if>	
			</p>
		</div>
	</div>
	
	