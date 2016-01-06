<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="pageLeft">
</div>
<div class="pageCenter">
	<s:if test="pageInterEquipe > 1">
		<a href="#" onclick="changePageInterEquipe('1');">
			<img class="icone" alt="Première page" title="Première page" src="<s:property value="#session.ENSEIGNE" />/img/premier-16.png" />
		</a>
	</s:if>
	<s:else>
		<a href="#" style="cursor:none">
			<img class="icone" alt="Première page" title="Première page" src="<s:property value="#session.ENSEIGNE" />/img/premier-pale-16.png" />
		</a>
	</s:else>
	<s:if test="pageInterEquipe > 1">
		<a href="#" onclick="changePageInterEquipe('<s:property value="%{paginationInterEquipe.pagePrecedente}"/>');">
			<img class="icone" alt="Page précédente" title="Page précédente" src="<s:property value="#session.ENSEIGNE" />/img/precedent-16.png" />
		</a>
	</s:if>
	<s:else>
		<a href="#" style="cursor:none">
			<img class="icone" alt="Page précédente" title="Page précédente" src="<s:property value="#session.ENSEIGNE" />/img/precedent-pale-16.png" />
		</a>
	</s:else>
	<s:textfield name="pageInput" id="pageInput" value="%{pageInterEquipe}" maxlength="3" size="4" cssStyle="text-align:center;" theme="simple" onchange="changePageInterEquipe(this.value)"/>
	/<s:property value="%{paginationInterEquipe.nrPages}" />
	<s:if test="pageInterEquipe lt paginationInterEquipe.nrPages">
		<a href="#" onclick="changePageInterEquipe('<s:property value="%{paginationInterEquipe.pageSuivante}"/>');">
			<img class="icone" alt="Page suivante" title="Page suivante" src="<s:property value="#session.ENSEIGNE" />/img/suivant-16.png" />
		</a>
	</s:if>
	<s:else>                                                                                                                                                                                                                                                                                                                            
		<a href="#" style="cursor:none">
			<img class="icone" alt="Page suivante" title="Page suivante" src="<s:property value="#session.ENSEIGNE" />/img/suivant-pale-16.png" />
		</a>
	</s:else>
	<s:if test="pageInterEquipe lt paginationInterEquipe.nrPages">
		<a href="#" onclick="changePageInterEquipe('<s:property value="%{paginationInterEquipe.nrPages}" />');">
			<img class="icone" alt="Dernière page" title="Dernière page" src="<s:property value="#session.ENSEIGNE" />/img/dernier-16.png" />
		</a>
	</s:if>
	<s:else>
		<a href="#" style="cursor:none">
			<img class="icone" alt="Dernière page" title="Dernière page" src="<s:property value="#session.ENSEIGNE" />/img/dernier-pale-16.png" />
		</a>
	</s:else>
</div>
<div class="pageRight">     
    <%
      int tous =10000000;     
      java.util.HashMap map = new java.util.LinkedHashMap();
      map.put(3,"3");
      map.put(10,"10");
      map.put(20,"20");
      map.put(30,"30");
      map.put(40,"40");
      map.put(50,"50");
      map.put(100,"100");
      map.put(150,"150");
      map.put(tous,"tous");
      request.setAttribute("map",map);
    %>    
    <s:select list="#request.map" name="nrPerPageInput" value="%{paginationInterEquipe.pageSize}" theme="simple" onchange="changeNrPerPageInterEquipe(this.value)"/>
    <!--<s:select list="{10,20,30,40,50,100,150,'tous'}"  name="nrPerPageInput" value="%{pagination.pageSize}" theme="simple" onchange="changeNrPerPage(this.value)"/>--> 
</div>
