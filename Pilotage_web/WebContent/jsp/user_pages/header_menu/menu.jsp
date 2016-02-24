<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

	<script type="text/javascript">
		var timerCloseMenu = null;
		var lastLeave = new Array();
		var index = 0;
	
		jQuery(document).ready(function($) {
			// set up the options to be used for jqDock...
			var dockOptions = {
				align : 'left' // horizontal menu, with expansion DOWN from a fixed TOP edge
				,
				size : <s:property value="#session.MENU_ICONE_SIZE" /> //taille de l'icone au repos
				,
				labels : true // add labels (defaults to 'br')
				,
				setLabel : function(txt, i, el) {
					//append a clone of the relevant sub-menu to the label, div.jqDockLabel...
					//Note that I don't need to add the inner wrapper - div.jqDockLabelText - 
					//because I can reposition the sub-menus within div.jqDockLabel itself
					$(el).append($('#submenus>li>ul').eq(i).clone(true))
					//...and intercept mousemoves to prevent propagation and stop
					//the dock resizing as the cursor moves over the sub-menus...
					.bind('mousemove', function() {
						return false;
					})
					//Affichage du titre du menu en tete
					.find('ul')
					.eq(0).prepend('<li class="titre_menu"><a><span style="filter: inherit;">'+txt+'</span></a></li>')
					.one('mouseenter', function() {
						return false;
					}).end()
					//Modification de la position si le menu depasse la page
					.parents('a').bind('mouseenter',function() {
						$('.jqDockLabel',this).css('margin-top','0');
						var windHeight = (document.all) ? screen.availHeight : window.innerHeight;
						var pageYOffset = (document.all) ? $('html').scrollTop() - window.screenTop : window.pageYOffset;
						var anchorHeight = $(this).height()/2;
						var anchorOffsetTop = $(this).offset().top;
						$('.jqDockLabel', this).show();
						var uloH = $('.jqDockLabel>ul',this).outerHeight();
						var lioh = $('.jqDockLabel>ul>li',this).outerHeight();
						var delta = windHeight + pageYOffset - anchorOffsetTop - anchorHeight - uloH - lioh; //40 : marge de secutite par rapport au bas
						if (delta < 0) {
							$('.jqDockLabel',this).css('margin-top',delta);
						}
					})
					//pour chaque sous menu, quand on pose la souris dessus, on ferme les autres sous menus deja ouverts
					.find('a').hover(function(ev) {
						for(var iTmp2 = index - 1; iTmp2 >= 0; iTmp2--){
							var toClose = lastLeave[iTmp2];
							$('>ul', toClose)['hide']();
							$('>ul', toClose).css('margin-top',0);
							lastLeave[iTmp2] = null;
						}
						index = 0;
					})
					//Mouse leave : on ajoute les menus quittes dans une liste a fermer
					//Mouse enter : si le menu que la souris pointe est un des menus a fermer, on ne le ferme plus. Sinon on ferme tous les menus a fermer. Puis on affiche le sous menu adequat.
					.next('ul').parent().hover(function(ev) {
						var mLeave = ev.type == 'mouseleave';
						if (!mLeave) {
							//suppression des menus ouverts qui ne sont pas de ce menu
							if(index > 0){
								if(this == lastLeave[0]){
									for(i = 1; i < index; ++i){
										lastLeave[i-1] = lastLeave[i];
									}
									index--;
								}
								else{
									for(var iTmp2 = index - 1; iTmp2 >= 0; iTmp2--){
										var toClose = lastLeave[iTmp2];
										$('>ul', toClose)['hide']();
										$('>ul', toClose).css('margin-top',0);
										lastLeave[iTmp2] = null;
									}
									index = 0;
								}
							}

							//affichage du menu
							$('>ul', this)['show']();
							var windHeight = (document.all) ? screen.availHeight : window.innerHeight;
							var pageYOffset = (document.all) ?  $('html').scrollTop() - window.screenTop : window.pageYOffset;
							var liHeight = $('>ul>li',this).outerHeight();
							var ultop = $(this).offset().top;
							var UlOHeight = $('>ul',this).outerHeight();
							var delta = windHeight + pageYOffset - ultop - UlOHeight - liHeight;
							if (delta < 0) {
								var decal = Math.ceil(delta/liHeight-1)*liHeight;
								$('>ul',this).css('margin-top',decal);
							}
							
							Dock.OnDock = 1;
						} else {
							//ajout dans la liste des menus a fermer si on passe sur un autre menu
							lastLeave[index] = this;
							index++;
						}
						return mLeave;
					})
					//fleche indiquant qu'on a un sous menu
					.find('>a').append("<"+"img src='<s:property value="#session.ENSEIGNE" />/img/submenu.png' alt='' border='0' />");
					return false;
				}
			};
			// ...and apply...
			$('#menu').jqDock(dockOptions);
		});
	</script>



    <!-- -------------------------------INSERT MENU HERE------------------------------- -->
		<!-- icones -->
		<div id='menu'>
		    <s:iterator value="#session.MENUS" id="root">
		    	<a class="menuLink" href='<s:property value="#root.lien"/>'>
		    		<s:if test="#root.menu.icone != null">
		    			<img src='getImageAction.action?imageID=<s:property value="#root.menu.icone"/>' title='<s:property value="#root.menu.libelle" />' alt='<s:property value="#root.menu.libelle" />'/>
		    		</s:if>
		    		<s:else>
		    			<img src='<s:property value="#session.ENSEIGNE" />/img/Inconnu.png' title='<s:property value="#root.menu.libelle" />' alt='<s:property value="#root.menu.libelle" />'/>
		    		</s:else>
		    	</a>  
		    </s:iterator>
		</div>
		    
		<!--sous menu-->
		<ul id='submenus'>
			<!-- 1er niveau -->
		    <s:iterator value="#session.MENUS" id="rt">
			    <li>
			        <ul>
			        	<!-- 2ème niveau -->
			        	<s:iterator value="#rt.children" id="sm1">
			        		<li>
			        			<a class="menuLink" href='<s:property value="#sm1.lien"/>'><span><s:property value="#sm1.menu.libelle"/></span></a> 
			        			
			       				<s:if test="#sm1.children.size()>0">
			        				<ul>
			        					<!-- 3eme niveau -->
			        					<s:iterator value="#sm1.children" id="sm2">
								        	<li>
								        		<a class="menuLink" href='<s:property value="#sm2.lien"/>'><span><s:property value="#sm2.menu.libelle"/></span></a>
			        							<s:if test="#sm2.children.size()>0">
			        								<ul>
			        									<!-- 4ème niveau -->
			        									<s:iterator value="#sm2.children" id="sm3">
			        										<li>
			        											<a class="menuLink" href='<s:property value="#sm3.lien"/>'><span><s:property value="#sm3.menu.libelle"/></span></a>
			        										</li>
			        									</s:iterator>
			        								</ul>
			        							</s:if>
			        						</li>
			        					</s:iterator>
			        				</ul>
			        			</s:if>
			        		</li>
			        		
				        </s:iterator>
			        </ul>
			    </li>
		    </s:iterator>
		</ul>
