package pilotage.checklist.current;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import pilotage.database.applicatif.EnvironmentDatabaseService;
import pilotage.database.checklist.ChecklistCriticiteDatabaseService;
import pilotage.database.checklist.ChecklistDatabaseService;
import pilotage.database.checklist.ChecklistStatutDatabaseService;
import pilotage.database.filtre.FiltreDatabaseService;
import pilotage.database.incidents.HeuresOceorDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Checklist_Annule;
import pilotage.metier.Checklist_Criticite;
import pilotage.metier.Checklist_Current;
import pilotage.metier.Checklist_Status;
import pilotage.metier.Environnement;
import pilotage.metier.Filtre;
import pilotage.metier.Heures_Oceor;
import pilotage.metier.Users;
import pilotage.service.checklist.ChecklistCurrentSorter;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;
import pilotage.service.string.StringConverter;
import pilotage.utils.Pagination;

public class ShowChecklistAction extends AbstractAction {

	private static final long serialVersionUID = 3952808607867940851L;

	private Boolean isToday;
	private Boolean isFutur;
	private Boolean filtrePresent = false;

	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private Integer filtreTypeListe = PilotageConstants.LIST_CHECKLIST_NORMALE;
	private Integer[] filtreEnvironnement;
	private JSONObject filtreJson;
	
	private String filtreChampHeure;
	private Integer filtreChampEnvironnement;
	private String filtreChampTache;
	private Integer filtreChampCriticite;
	
	private int validForm = 0;
	private String titrePage = "CKL_CUR";
	private Filtre filtre;
	private Integer filtreTypeListeBase;
	private Integer[] filtreEnvironnementBase;
	
	private Pagination<Checklist_Current> pagination;
	private List<Checklist_Current> listeTaches;
	
	private List<Environnement> listEnvironnement;
	private List<Checklist_Status> listStatus;
	private List<Checklist_Status> listStatusFutur;
	private Map<Integer,Boolean> mapToLate;	
	
	private List<Environnement> listChampEnvironnement;
	private List<Checklist_Criticite> listChampCriticite;
	
	private Date selectedDate;
	
	private String date;
	private String hour;
	private List<String[]> paysHeure = new ArrayList<String[]>();


	public List<String[]> getPaysHeure() {
		return paysHeure;
	}

	public void setPaysHeure(List<String[]> paysHeure) {
		this.paysHeure = paysHeure;
	}

	public Boolean getIsToday() {
		return isToday;
	}

	public void setIsToday(Boolean isToday) {
		this.isToday = isToday;
	}

	public Boolean getIsFutur() {
		return isFutur;
	}

	public void setIsFutur(Boolean isFutur) {
		this.isFutur = isFutur;
	}
	
	public Boolean getFiltrePresent() {
		return filtrePresent;
	}

	public void setFiltrePresent(Boolean filtrePresent) {
		this.filtrePresent = filtrePresent;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNrPages() {
		return nrPages;
	}

	public void setNrPages(int nrPages) {
		this.nrPages = nrPages;
	}

	public int getNrPerPage() {
		return nrPerPage;
	}

	public void setNrPerPage(int nrPerPage) {
		this.nrPerPage = nrPerPage;
	}

	public Integer getFiltreTypeListe() {
		return filtreTypeListe;
	}

	public void setFiltreTypeListe(Integer filtreTypeListe) {
		this.filtreTypeListe = filtreTypeListe;
	}

	public Integer[] getFiltreEnvironnement() {
		return filtreEnvironnement;
	}

	public void setFiltreEnvironnement(Integer[] filtreEnvironnement) {
		this.filtreEnvironnement = filtreEnvironnement;
	}


	public int getValidForm() {
		return validForm;
	}

	public void setValidForm(int validForm) {
		this.validForm = validForm;
	}

	public String getTitrePage() {
		return titrePage;
	}

	public void setTitrePage(String titrePage) {
		this.titrePage = titrePage;
	}

	public Filtre getFiltre() {
		return filtre;
	}

	public void setFiltre(Filtre filtre) {
		this.filtre = filtre;
	}

	public Integer getFiltreTypeListeBase() {
		return filtreTypeListeBase;
	}

	public void setFiltreTypeListeBase(Integer filtreTypeListeBase) {
		this.filtreTypeListeBase = filtreTypeListeBase;
	}

	public Integer[] getFiltreEnvironnementBase() {
		return filtreEnvironnementBase;
	}

	public void setFiltreEnvironnementBase(Integer[] filtreEnvironnementBase) {
		this.filtreEnvironnementBase = filtreEnvironnementBase;
	}

	public JSONObject getFiltreJson() {
		return filtreJson;
	}

	public void setFiltreJson(JSONObject filtreJson) {
		this.filtreJson = filtreJson;
	}

	public String getFiltreChampHeure() {
		return filtreChampHeure;
	}

	public void setFiltreChampHeure(String filtreChampHeure) {
		this.filtreChampHeure = filtreChampHeure;
	}

	public Integer getFiltreChampEnvironnement() {
		return filtreChampEnvironnement;
	}

	public void setFiltreChampEnvironnement(Integer filtreChampEnvironnement) {
		this.filtreChampEnvironnement = filtreChampEnvironnement;
	}

	public String getFiltreChampTache() {
		return filtreChampTache;
	}

	public void setFiltreChampTache(String filtreChampTache) {
		this.filtreChampTache = filtreChampTache;
	}
	
	public Integer getFiltreChampCriticite() {
		return filtreChampCriticite;
	}

	public void setFiltreChampCriticite(Integer filtreChampCriticite) {
		this.filtreChampCriticite = filtreChampCriticite;
	}

	public Pagination<Checklist_Current> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Checklist_Current> pagination) {
		this.pagination = pagination;
	}

	public List<Checklist_Current> getListeTaches() {
		return listeTaches;
	}

	public void setListeTaches(List<Checklist_Current> listeTaches) {
		this.listeTaches = listeTaches;
	}

	public List<Environnement> getListEnvironnement() {
		return listEnvironnement;
	}

	public void setListEnvironnement(List<Environnement> listEnvironnement) {
		this.listEnvironnement = listEnvironnement;
	}

	public List<Checklist_Status> getListStatus() {
		return listStatus;
	}

	public void setListStatus(List<Checklist_Status> listStatus) {
		this.listStatus = listStatus;
	}

	public List<Checklist_Status> getListStatusFutur() {
		return listStatusFutur;
	}

	public void setListStatusFutur(List<Checklist_Status> listStatusFutur) {
		this.listStatusFutur = listStatusFutur;
	}

	public List<Environnement> getListChampEnvironnement() {
		return listChampEnvironnement;
	}

	public void setListEnvironnment(List<Environnement> listChampEnvironnement) {
		this.listChampEnvironnement = listChampEnvironnement;
	}
	
	public List<Checklist_Criticite> getListChampCriticite() {
		return listChampCriticite;
	}

	public void setListChampCriticite(List<Checklist_Criticite> listChampCriticite) {
		this.listChampCriticite = listChampCriticite;
	}

	public Map<Integer,Boolean> getMapToLate() {
		return mapToLate;
	}

	public void setMapToLate(Map<Integer,Boolean> mapToLate) {
		this.mapToLate = mapToLate;
	}
	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected String executeMetier() {
		//nettoyage de la session
		Date dateFutureEnSession = (Date)session.get(PilotageConstants.CHECKLIST_FUTUR_DATE);
		if(dateFutureEnSession != null && !dateFutureEnSession.equals(selectedDate)){
			session.remove(PilotageConstants.CHECKLIST_FUTUR_DATE);
			session.remove(PilotageConstants.CHECKLIST_FUTUR);
		}
		
		Date now = new Date();
		date = DateService.dateToStr(now, DateService.p1);
		hour = DateService.getTime(now, DateService.pt2); 
		
		if(selectedDate == null)
			selectedDate = DateService.getTodayWithoutHour();
		
		if(selectedDate.before(DateService.getTodayWithoutHour())){
			isToday = false;
			isFutur = false;
		}
		else if(selectedDate.after(DateService.getTodayWithoutHour())){
			isToday = false;
			isFutur = true;
		}
		else{
			isToday = true;
			isFutur = false;
		}
		
		calculHeuresLocales(paysHeure);
		
		//gestion du filtre
		Users userLogged = (Users)session.get(PilotageConstants.USER_LOGGED);
		Integer userLoggedId = userLogged.getId();
		filtre = FiltreDatabaseService.getFiltre(userLoggedId,titrePage);
		Users userLog = UsersDatabaseService.get(userLoggedId);
		Integer nr = userLog.getNrPerpage();
		
		if(page < 1)
			page = 1;
		else if(nrPages != 0 && page > nrPages)
			page = nrPages;
		if(nrPerPage == 0){
			if(nr == null){
				nrPerPage = PilotageConstants.NB_TACHES_PER_PAGE;
			}else{
				nrPerPage = nr;
			}
		}
		if(sens == null || "".equals(sens))
			sens = "asc";
		if(sort == null || "".equals(sort))
			sort = "tache";
		
		pagination = new Pagination<Checklist_Current>(page, nrPerPage);

		listEnvironnement = EnvironmentDatabaseService.getAll();
		listStatus = ChecklistStatutDatabaseService.getAll();
		listStatusFutur = ChecklistStatutDatabaseService.getAllFuturStatut();
		
		listChampCriticite = ChecklistCriticiteDatabaseService.getAll();
		listChampEnvironnement = EnvironmentDatabaseService.getAll();
		listeTaches = new ArrayList<Checklist_Current>();
		
		//Si on veut consulter une checklist du passée ou celle du jour
		if(!isFutur){
			if(!ChecklistDatabaseService.isCreated(selectedDate)){
				createChecklistOfTheDay(selectedDate);
			}

			if (filtre != null){
				try {
					Integer filtreId = filtre.getId();
					reloadFiltreBase(filtre.getFiltreString());
					listeTaches = ChecklistDatabaseService.getListCurrent(pagination, selectedDate, filtreTypeListe, filtreEnvironnement, filtreChampEnvironnement, filtreChampHeure, filtreChampCriticite, filtreChampTache);
					filtreJson = filtreToString(filtreTypeListe, filtreEnvironnement, listEnvironnement, filtreChampHeure,filtreChampEnvironnement, filtreChampTache, 
							filtreChampCriticite, listChampCriticite);
					
					if (validForm == 1){
						if (filtreTypeListeBase != filtreTypeListe || filtreEnvironnementBase != filtreEnvironnement){
							filtreJson = filtreToString(filtreTypeListe, filtreEnvironnement,  listEnvironnement, filtreChampHeure,filtreChampEnvironnement, filtreChampTache, 
									filtreChampCriticite, listChampCriticite);
							FiltreDatabaseService.update(filtreId, filtreJson != null ? filtreJson.toString() : null);
						}
					}
					
				} catch (Exception e) {
					error = getText("error.message.generique") + " : " + e.getMessage();
					erreurLogger.error("Update de filtre checklist", e);
				}
			}
			else{
				try {
					listeTaches = ChecklistDatabaseService.getListCurrent(pagination, selectedDate, filtreTypeListe, filtreEnvironnement, filtreChampEnvironnement, filtreChampHeure, filtreChampCriticite, filtreChampTache);
					filtreJson = filtreToString(filtreTypeListe, filtreEnvironnement, listEnvironnement, filtreChampHeure,filtreChampEnvironnement, filtreChampTache, 
							filtreChampCriticite, listChampCriticite);
					
					if (validForm == 1){
						if (filtreTypeListe != null || filtreEnvironnement != null){
							FiltreDatabaseService.create(userLoggedId, titrePage, filtreJson != null ? filtreJson.toString() : null);
						}
					}
				} catch (Exception e) {
					error = getText("error.message.generique") + " : " + e.getMessage();
					erreurLogger.error("Creation de filtre checklist", e);
				}
			}	
		}
		//si c'est une checklist du futur
		else{
			List<Checklist_Current> listeComplete;
			
			Date listFuturDate = (Date)session.get(PilotageConstants.CHECKLIST_FUTUR_DATE);
			if(listFuturDate != null && listFuturDate.equals(selectedDate)){
				listeComplete = (List<Checklist_Current>) session.get(PilotageConstants.CHECKLIST_FUTUR);
			}
			else{
				//construction de la liste entière
				listeComplete = createLocalChecklistOfTheDay(selectedDate);

				//ajout des current généré les jours précédents mais concernant ce jour
				listeComplete.addAll(ChecklistDatabaseService.getListCurrent(pagination, selectedDate, null, null, null, null, null, null));
				Collections.sort(listeComplete, new ChecklistCurrentSorter());
				
				//suppression des current générés ne concernant pas le jour demandé
				List<Checklist_Current> currentToDelete = new ArrayList<Checklist_Current>();
				for(Checklist_Current currentTmp : listeComplete){
					if(!selectedDate.equals(currentTmp.getJour())){
						currentToDelete.add(currentTmp);
					}
				}
				listeComplete.removeAll(currentToDelete);
				
				//mise à jour des statuts suivant les annulations en base
				List<Checklist_Annule> listAnnulationOfTheDay = ChecklistDatabaseService.getAnnulationsOfTheDay(selectedDate);
			
				for(Checklist_Annule annulation : listAnnulationOfTheDay){
					for(Checklist_Current current : listeComplete){
						if(annulation.getTache().getId().equals(current.getSousTache().getId()) && annulation.getHoraire().getId().equals(current.getIdHoraire().getId())){
							current.setStatus(ChecklistStatutDatabaseService.get(PilotageConstants.STATUT_ANNULE));
							break;
						}
					}
				}
				
				for(int i = 0; i < listeComplete.size(); ++i){
					listeComplete.get(i).setId(i);
				}
				
				//mise en session
				session.put(PilotageConstants.CHECKLIST_FUTUR_DATE, selectedDate);
				session.put(PilotageConstants.CHECKLIST_FUTUR, listeComplete);
			}
			
			//selection suivant filtre et pagination
			List<Checklist_Current> listeFiltree = new ArrayList<Checklist_Current>();
			for(Checklist_Current current : listeComplete){
				//filtre type de liste exceptionnelle
				if(PilotageConstants.LIST_CHECKLIST_EXCEPTIONNEL.equals(filtreTypeListe) && ! current.getTache().getEtat().getId().equals(PilotageConstants.CHECKLIST_ETAT_EXCEPTIONNEL)){
					continue;
				}
				
				//filtre environnement
				if(filtreEnvironnement != null && filtreEnvironnement.length != 0){
					Integer idEnv = current.getTache().getEnvironnement().getId();
					boolean isInEnvFilter = false;
					for(Integer envElementID : filtreEnvironnement){
						if(idEnv.equals(envElementID)){
							isInEnvFilter = true;
							break;
						}
					}
					
					if(!isInEnvFilter)
						continue;
				}
				
				if(filtreChampEnvironnement != null && filtreChampEnvironnement != -1){
	                boolean isInChEnvFilter = false;
	                    if(filtreChampEnvironnement.equals(current.getTache().getEnvironnement().getId())){
	                    	isInChEnvFilter = true;
	                    }
	                    if(!isInChEnvFilter)
							continue;
				}
				
				if(filtreChampTache != null && !filtreChampTache.equals("")){
					boolean isInTachFilter = false;
					if(current.getTache().getTache().toLowerCase().contains(filtreChampTache)){
						isInTachFilter = true;
					}
					
					if(!isInTachFilter)
						continue;
				}
				
				if(filtreChampCriticite != null && filtreChampCriticite != -1){
				boolean isInCriticiteFilter = false;
		        	if(filtreChampCriticite.equals(current.getTache().getCriticite().getId())){
		            	isInCriticiteFilter = true;
		            } 
		        	
		        	if(!isInCriticiteFilter)
						continue;
				 }
				
				if(filtreChampHeure != null && !"".equals(filtreChampHeure) && !"hh:mm:ss".equals(filtreChampHeure)) {
					boolean isInHeureFilter = false;
					Date heureSaisie = DateService.getTimeFromString(filtreChampHeure);
	                Calendar filtreCal = Calendar.getInstance();
	                filtreCal.setTime(heureSaisie);
	                Calendar executionCal = Calendar.getInstance();
	                executionCal.setTime(heureExecution(current));
                    int diff = executionCal.compareTo(filtreCal);
                        if(diff == 0 || diff > 0)
                        	isInHeureFilter = true;
                        
                        if(!isInHeureFilter)
    						continue;
				}
				
				listeFiltree.add(current);
			}
			
			//Pagination
			listeTaches = new ArrayList<Checklist_Current>();
			for(int i = (page - 1) * nrPerPage; i < page * nrPerPage && i < listeFiltree.size(); ++i){
				listeTaches.add(listeFiltree.get(i));
			}
			pagination.setNrPages(listeFiltree.size() / nrPerPage + (listeFiltree.size() % nrPerPage == 0 ? 0 : 1));
			
		}
		
		mapToLate = new HashMap<Integer, Boolean>();
		Map<Integer, String> couleurs = (Map<Integer, String>) session.get(PilotageConstants.CHECKLIST_COULEUR);
		Map<Integer, String> retard1 = (Map<Integer, String>) session.get(PilotageConstants.CHECKLIST_COULEUR_RETARD1);
		Map<Integer, String> retard2 = (Map<Integer, String>) session.get(PilotageConstants.CHECKLIST_COULEUR_RETARD2);
		String retard1String = session.get(PilotageConstants.CHECKLIST_RETARD_1).toString();
		String retard2String = session.get(PilotageConstants.CHECKLIST_RETARD_2).toString();
		String tempsModifString = session.get(PilotageConstants.CHECKLIST_TEMPS_MODIFICATION).toString();
		Date tempsRetard1 = DateService.getTimeFromString(DateService.getTime(DateService.strToDate("01/01/1970", retard1String), null));
		Date tempsRetard2 = DateService.getTimeFromString(DateService.getTime(DateService.strToDate("01/01/1970", retard2String), null));
		Date tempsModif = DateService.getTimeFromString(DateService.getTime(DateService.strToDate("01/01/1970", tempsModifString), null));
		completeBeforeDisplay(listeTaches, couleurs, retard1, retard2, tempsRetard1, tempsRetard2, mapToLate, tempsModif, filtreTypeListe);

		filtreJson = filtreToString(filtreTypeListe, filtreEnvironnement, listEnvironnement, filtreChampHeure,filtreChampEnvironnement, filtreChampTache, 
				filtreChampCriticite, listChampCriticite);
		
		return OK;
	}

	public void reloadFiltreBase(String filtreString){
		if (filtreString != null){
			JSONObject jsonFiltre = JSONObject.fromObject(filtreString);
			if (jsonFiltre.containsKey("Type")){
				if (jsonFiltre.getString("Type").equalsIgnoreCase("Liste normale")){
					setFiltreTypeListeBase(0);
				}
				if (jsonFiltre.getString("Type").equalsIgnoreCase("Liste de toutes les tâches du jour")){
					setFiltreTypeListeBase(1);
				}
				if (jsonFiltre.getString("Type").equalsIgnoreCase("Liste des tâches exceptionnelles")){
					setFiltreTypeListeBase(2);
				}
			}
			if (jsonFiltre.containsKey("Environnements")){
				JSONArray envJson = jsonFiltre.getJSONArray("Environnements");
				Integer [] envBase = new Integer[envJson.size()];
				for (int i = 0 ; i < envJson.size() ; i++){
					for(Environnement env : listEnvironnement){
						if(env.getEnvironnement().equalsIgnoreCase(envJson.getString(i))){
							envBase[i] = env.getId();								 
						}
					}
				}
				setFiltreEnvironnementBase(envBase);
			}
			if (validForm == 0){
				setFiltreTypeListe(filtreTypeListeBase);
				setFiltreEnvironnement(filtreEnvironnementBase);
			}
		}
	}
	
	/**
	 * Met les filtres sous forme string pour affichage
	 * @param filtreTypeListe
	 * @param filtreCriticite
	 * @param filtreEnvironnement
	 * @param listCriticite
	 * @param listEnvironnement
	 * @return
	 */
	public static JSONObject filtreToString(Integer filtreTypeListe, Integer[] filtreEnvironnement, 
			List<Environnement> listEnvironnement, String filtreChampHeure,Integer filtreChampEnvironnement, String filtreChampTache, 
			Integer filtreChampCriticite, List<Checklist_Criticite> listChampCriticite) {
		
		StringBuffer buffer = new StringBuffer();
		if (filtreTypeListe != null && filtreTypeListe != -1) {
			buffer.append("{");
			buffer.append(PilotageConstants.SEPARATEUR_5 + "Type" + PilotageConstants.SEPARATEUR_5 + " : ");
			switch (filtreTypeListe) {
			case 0:
				buffer.append(PilotageConstants.SEPARATEUR_5 + "Liste normale" + PilotageConstants.SEPARATEUR_5);
				break;
			case 1:
				buffer.append(PilotageConstants.SEPARATEUR_5 + "Liste de toutes les tâches du jour" + PilotageConstants.SEPARATEUR_5);
				break;
			case 2:
				buffer.append(PilotageConstants.SEPARATEUR_5 + "Liste des tâches exceptionnelles" + PilotageConstants.SEPARATEUR_5);
				break;
			default:
				break;
			}
			buffer.append(PilotageConstants.SEPARATEUR_3);
		}

		if (filtreEnvironnement != null && filtreEnvironnement.length != 0) {
			buffer.append(PilotageConstants.SEPARATEUR_5 + "Environnements" + PilotageConstants.SEPARATEUR_5 + " : ");
			buffer.append("[");
			
			for(Integer envID : filtreEnvironnement){
				for(Environnement env : listEnvironnement){
					if(env.getId().equals(envID)){
						buffer.append(PilotageConstants.SEPARATEUR_5 + env.getEnvironnement() + PilotageConstants.SEPARATEUR_5);
						buffer.append(", ");
						break;
					}
				}
			}
			buffer.append("]");

			buffer.append(PilotageConstants.SEPARATEUR_3);
		}
		
		if (filtreChampHeure != null && !filtreChampHeure.equals("") && !filtreChampHeure.equals("hh:mm:ss")) {
			buffer.append(StringConverter.toJson("Heure", filtreChampHeure));
		}
		
		if (filtreChampEnvironnement!= null && filtreChampEnvironnement != -1) {
			Environnement env = EnvironmentDatabaseService.get(filtreChampEnvironnement);
			buffer.append(StringConverter.toJson("Environnement", env.getEnvironnement()));
		}
		
		if (filtreChampTache != null && !filtreChampTache.equals("")) {
			buffer.append(StringConverter.toJson("Tache", filtreChampTache));
		}
		
		if (filtreChampCriticite!= null && filtreChampCriticite != -1) {
			for(Checklist_Criticite criticite : listChampCriticite){
				if(criticite.getId().equals(filtreChampCriticite)){
					buffer.append(StringConverter.toJson("Criticité", criticite.getLibelle()));
					break;
				}
			}
		}
		
		if (buffer.length() != 0){
			String result =  buffer.substring(0, buffer.lastIndexOf(PilotageConstants.SEPARATEUR_3));
			result += "}";
			JSONObject jsonFiltre = JSONObject.fromObject(result);
			return jsonFiltre;
		}
		else
			return null;
	}

	
	/**
	 * Met les filtres visibles sous forme string pour affichage
	 * @param filtreHeure
	 * @param filtreEnvironnment
	 * @param filtreTache 
	 * @param filtreCriticité
	 
	public static String filtreVisibleToString(String filtreChampHeure,Integer filtreChampEnvironnement, String filtreChampTache, 
		Integer filtreChampCriticite, List<Checklist_Criticite> listChampCriticite) {
		StringBuffer buffer = new StringBuffer();
		
		if (filtreChampHeure != null && !filtreChampHeure.equals("") && !filtreChampHeure.equals("hh:mm:ss")) {
			buffer.append("Heure : ");
			buffer.append(filtreChampTache);
			buffer.append(PilotageConstants.SEPARATEUR);
		}
		
		if (filtreChampEnvironnement!= null && filtreChampEnvironnement != -1) {
			Environnement env = EnvironmentDatabaseService.get(filtreChampEnvironnement);
			buffer.append("Environnement : ");
			buffer.append(env.getEnvironnement());
			buffer.append(PilotageConstants.SEPARATEUR);
		}
		
		if (filtreChampTache != null && !filtreChampTache.equals("")) {
			buffer.append("Tache : ");
			buffer.append(filtreChampTache);
			buffer.append(PilotageConstants.SEPARATEUR);
		}
		
		if (filtreChampCriticite!= null && filtreChampCriticite != -1) {
			buffer.append("Criticité : ");
			for(Checklist_Criticite criticite : listChampCriticite){
				if(criticite.getId().equals(filtreChampCriticite)){
					buffer.append(criticite.getLibelle());
					break;
				}
			}

			buffer.append(PilotageConstants.SEPARATEUR);
		}
		if(buffer.length() != 0)
			return buffer.substring(0, buffer.lastIndexOf(PilotageConstants.SEPARATEUR));
		else
			return null;
	}*/
	
	
	
	/**
	 * Complément d'information sur les taches avant affichage
	 * Mise à jour de l'heure quand executer la tache
	 * Mise à jour des couleurs
	 * @param listeTaches
	 */
	public static void completeBeforeDisplay(List<Checklist_Current> listeTaches, Map<Integer, String> couleurs, Map<Integer, String> retard1, Map<Integer, String> retard2, Date tempsRetard1, Date tempsRetard2, Map<Integer,Boolean> mapToLate, Date tempsModif, Integer filtreTypeListe) {
		if (listeTaches == null) return;
		if (listeTaches.isEmpty()) return;
		Date jourHeureActuel = new Date();
		
		for(Checklist_Current current : listeTaches){
			String couleur = null;
			Date heureExecution = heureExecution(current);
			current.setHeureExecution(heureExecution);
			Date jourHeureExecution = DateService.getDateHeure(current.getJour(), heureExecution);
			
			//constitution de la Map du toLate to Modify
			if (jourHeureActuel.after(DateService.addTime(jourHeureExecution, tempsModif)) && !PilotageConstants.LIST_CHECKLIST_NORMALE.equals(filtreTypeListe)) {
				mapToLate.put(current.getId(),true);
			} else {
				mapToLate.put(current.getId(),false);
			}
			
			//Récupération des couleurs, la miseà jour des status est réalisé via l'event scheduler de MySQL
			//si on est en retard pour prise en charge
			if(current.getStatus().getId().equals(PilotageConstants.STATUT_A_VENIR) && jourHeureActuel.after(jourHeureExecution)){
				if(jourHeureActuel.after(DateService.addTime(jourHeureExecution, tempsRetard2))){
					couleur = retard2.get(PilotageConstants.STATUT_RETARD);
				}
				else if(jourHeureActuel.after(DateService.addTime(jourHeureExecution, tempsRetard1))){
					couleur = retard1.get(PilotageConstants.STATUT_RETARD);
				}
				else{
					couleur = couleurs.get(PilotageConstants.STATUT_A_VENIR);
				}
			}
			//si on a une tache en retard
			else if(current.getStatus().getId().equals(PilotageConstants.STATUT_RETARD)){
				if(jourHeureActuel.after(DateService.addTime(jourHeureExecution, tempsRetard2))){
					couleur = retard2.get(PilotageConstants.STATUT_RETARD);
				}
				else if(jourHeureActuel.after(DateService.addTime(jourHeureExecution, tempsRetard1))){
					couleur = retard1.get(PilotageConstants.STATUT_RETARD);
				}
				else{
					couleur = couleurs.get(PilotageConstants.STATUT_RETARD);
				}
			}
			else if(current.getStatus().getId().equals(PilotageConstants.STATUT_OK)){
				if(current.getHeure().after(DateService.addTime(jourHeureExecution, tempsRetard2))){
					couleur = retard2.get(PilotageConstants.STATUT_OK);
				}
				else if(current.getHeure().after(DateService.addTime(jourHeureExecution, tempsRetard1))){
					couleur = retard1.get(PilotageConstants.STATUT_OK);
				}
				else{
					couleur = couleurs.get(PilotageConstants.STATUT_OK);
				}
			}
			else{
				couleur = couleurs.get(current.getStatus().getId());
			}
			current.setColor(couleur == null ? "FFFFFF" : couleur);
		}
	}

	public static Date heureExecution(Checklist_Current current) {
		Date heureExecution = null;
		
		if(current.getSousTache() != null)
			heureExecution = DateService.addTime(current.getIdHoraire().getHoraire(), current.getSousTache().getDecalageStamp());
		else
			heureExecution = current.getIdHoraire().getHoraire();
		
		//recupération de l'heure uniquement pour eviter les décalages de jours car en BDD current, jour = le jour reel de l'execution 
		heureExecution = DateService.getTimeFromString(DateService.getTime(heureExecution, DateService.pt1));
		
		return heureExecution;
	}

	/**
	 * Récupération des base et sous taches du jour passé en paramètre
	 * @param dateList 
	 * @return 
	 *
	private Map<Integer, List<Integer>> getChecklistOfTheDay(Date dateList) {
		Date today = dateList;
		
		//récupération des taches exceptionnelles du jour
		List<Checklist_Exceptionnel> listTodayExceptionnel = ChecklistExceptionnelDatabaseService.getFromDate(today);
		
		
		//récupération des taches fériés du jour
		Integer veilleFerieID = DateService.getJourFerie(today, PilotageConstants.VEILLE_FERIE);
		Integer jourFerieID = DateService.getJourFerie(today, PilotageConstants.JOUR_FERIE);
		Integer lendemainFerieID = DateService.getJourFerie(today, PilotageConstants.LENDEMAIN_FERIE);
		
		List<Checklist_Ferie> listTacheVeilleFerie = ChecklistFerieDatabaseService.getFromDate(veilleFerieID, PilotageConstants.VEILLE_FERIE);
		List<Checklist_Ferie> listTacheJourFerie = ChecklistFerieDatabaseService.getFromDate(jourFerieID, PilotageConstants.JOUR_FERIE);
		List<Checklist_Ferie> listTacheLendemainFerie = ChecklistFerieDatabaseService.getFromDate(lendemainFerieID, PilotageConstants.LENDEMAIN_FERIE);
		
		
		//récupération des taches pairs/impairs du jour
		Integer parite = DateService.getWeekParite(today);
		List<Checklist_Parite> listTodayTacheParite = ChecklistPariteDatabaseService.getFromParite(parite);
														
		List<Checklist_Base> listBaseParite = new ArrayList<Checklist_Base>();
		for(Checklist_Parite checkParite : listTodayTacheParite){
			listBaseParite.add(checkParite.getIdChecklist());
		}
		List<Checklist_Jour> listJourParite = ChecklistJourDatabaseService.getListFromDayAndBaseList(DateService.getJour(today), listBaseParite);
												
														
		//récupération des taches mensuelles du jour
		List<Integer> placesID = new ArrayList<Integer>();
		placesID.add(DateService.getPlaceOfDayInMonth(today));
		if(DateService.isLastPlaceOfDayInMonth(today))
			placesID.add(5);
		List<Checklist_Mensuel> listTodayTacheMensuel = ChecklistMensuelDatabaseService.getFromPlacesList(placesID);

		List<Checklist_Base> listBaseMensuel = new ArrayList<Checklist_Base>();
		for(Checklist_Mensuel checkMensuel : listTodayTacheMensuel){
			listBaseMensuel.add(checkMensuel.getIdChecklist());
		}
		List<Checklist_Jour> listJourMensuel = ChecklistJourDatabaseService.getListFromDayAndBaseList(DateService.getJour(today), listBaseMensuel);
		
		
		//récupération des taches hebdos du jour
		List<Checklist_Jour> listAllChecklistJourToday = ChecklistJourDatabaseService.getListFromDay(DateService.getJour(today), (jourFerieID != null));
		List<Checklist_Jour> listJourHebdo = new ArrayList<Checklist_Jour>();
		for(Checklist_Jour checkJour : listAllChecklistJourToday){
			if(!ChecklistMensuelDatabaseService.isFrequenceMensuelle(checkJour.getIdChecklist().getId())
					&& !ChecklistPariteDatabaseService.isFrequenceParite(checkJour.getIdChecklist().getId())){
				listJourHebdo.add(checkJour);
			}
		}

		
		//récupération de toutes les bases
		Map<Integer, List<Integer>> baseEtSousTaches = new HashMap<Integer, List<Integer>>();
		for(Checklist_Exceptionnel checkExcep : listTodayExceptionnel){
			if(! tacheEligible(checkExcep.getIdChecklist(), today) ){
				continue;
			}
			List<Checklist_Base_Soustache> listSousTache = ChecklistBaseSousTacheDatabaseService.getListFromBase(checkExcep.getIdChecklist().getId());
			List<Integer> listSousTacheID = new ArrayList<Integer>();
			for(Checklist_Base_Soustache sousTache : listSousTache){
				listSousTacheID.add(sousTache.getId());
			}
			baseEtSousTaches.put(checkExcep.getIdChecklist().getId(), listSousTacheID);
		}
		for(Checklist_Ferie checkFerie : listTacheVeilleFerie){
			if(! tacheEligible(checkFerie.getIdChecklist(), today) ){
				continue;
			}

			List<Checklist_Base_Soustache> listSousTache = ChecklistBaseSousTacheDatabaseService.getListFromBase(checkFerie.getIdChecklist().getId());
			List<Integer> listSousTacheID = new ArrayList<Integer>();
			for(Checklist_Base_Soustache sousTache : listSousTache){
				listSousTacheID.add(sousTache.getId());
			}
			baseEtSousTaches.put(checkFerie.getIdChecklist().getId(), listSousTacheID);
		}
		
		for(Checklist_Ferie checkFerie : listTacheJourFerie){
			if(! tacheEligible(checkFerie.getIdChecklist(), today) ){
				continue;
			}
				
			List<Checklist_Base_Soustache> listSousTache = ChecklistBaseSousTacheDatabaseService.getListFromBase(checkFerie.getIdChecklist().getId());
			List<Integer> listSousTacheID = new ArrayList<Integer>();
			for(Checklist_Base_Soustache sousTache : listSousTache){
				listSousTacheID.add(sousTache.getId());
			}
			baseEtSousTaches.put(checkFerie.getIdChecklist().getId(), listSousTacheID);
		}
		for(Checklist_Ferie checkFerie : listTacheLendemainFerie){
			if(! tacheEligible(checkFerie.getIdChecklist(), today) ){
				continue;
			}

			List<Checklist_Base_Soustache> listSousTache = ChecklistBaseSousTacheDatabaseService.getListFromBase(checkFerie.getIdChecklist().getId());
			List<Integer> listSousTacheID = new ArrayList<Integer>();
			for(Checklist_Base_Soustache sousTache : listSousTache){
				listSousTacheID.add(sousTache.getId());
			}
			baseEtSousTaches.put(checkFerie.getIdChecklist().getId(), listSousTacheID);
		}
		for(Checklist_Jour checkParite : listJourParite){
			if(! tacheEligible(checkParite.getIdChecklist(), today) ){
				continue;
			}

			List<Checklist_Base_Soustache> listSousTache = ChecklistBaseSousTacheDatabaseService.getListFromBase(checkParite.getIdChecklist().getId());
			List<Integer> listSousTacheID = new ArrayList<Integer>();
			for(Checklist_Base_Soustache sousTache : listSousTache){
				listSousTacheID.add(sousTache.getId());
			}
			baseEtSousTaches.put(checkParite.getIdChecklist().getId(), listSousTacheID);
		}
		for(Checklist_Jour checkMensuel : listJourMensuel){
			if(! tacheEligible(checkMensuel.getIdChecklist(), today) ){
				continue;
			}

			List<Checklist_Base_Soustache> listSousTache = ChecklistBaseSousTacheDatabaseService.getListFromBase(checkMensuel.getIdChecklist().getId());
			List<Integer> listSousTacheID = new ArrayList<Integer>();
			for(Checklist_Base_Soustache sousTache : listSousTache){
				listSousTacheID.add(sousTache.getId());
			}
			baseEtSousTaches.put(checkMensuel.getIdChecklist().getId(), listSousTacheID);
		}
		for(Checklist_Jour checkHebdo : listJourHebdo){
			if(! tacheEligible(checkHebdo.getIdChecklist(), today) ){
				continue;
			}
				
			List<Checklist_Base_Soustache> listSousTache = ChecklistBaseSousTacheDatabaseService.getListFromBase(checkHebdo.getIdChecklist().getId());
			List<Integer> listSousTacheID = new ArrayList<Integer>();
			for(Checklist_Base_Soustache sousTache : listSousTache){
				listSousTacheID.add(sousTache.getId());
			}
			baseEtSousTaches.put(checkHebdo.getIdChecklist().getId(), listSousTacheID);
		}
		
		return baseEtSousTaches;
	}
	
	private boolean tacheEligible(Checklist_Base tache, Date today) {
		return tache != null && tache.getId() != null && tache.getActif() && !today.before(tache.getDateDebut()) && (PilotageConstants.CHECKLIST_ETAT_ACTIF.equals(tache.getEtat().getId()) || PilotageConstants.CHECKLIST_ETAT_EXCEPTIONNEL.equals(tache.getEtat().getId()));
	}*/

	/**
	 * Création de la checklist du jour
	 * @param dateList
	 */
	private void createChecklistOfTheDay(Date dateList) {
		//Map<Integer, List<Integer>> baseEtSousTaches = getChecklistOfTheDay(dateList);
		ChecklistDatabaseService.createChecklistOfTheDay(((Users)session.get(PilotageConstants.USER_LOGGED)).getLogin(), dateList);
	}
	
	/**
	 * Création sans sauvegarde en base de la checklist du jour
	 * @param dateList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Checklist_Current> createLocalChecklistOfTheDay(Date dateList) {
		List<Checklist_Current> listCurrent = ChecklistDatabaseService.createLocalChecklistOfTheDay(dateList);
		
		mapToLate = new HashMap<Integer, Boolean>();
		Map<Integer, String> couleurs = (Map<Integer, String>) session.get(PilotageConstants.CHECKLIST_COULEUR);
		Map<Integer, String> retard1 = (Map<Integer, String>) session.get(PilotageConstants.CHECKLIST_COULEUR_RETARD1);
		Map<Integer, String> retard2 = (Map<Integer, String>) session.get(PilotageConstants.CHECKLIST_COULEUR_RETARD2);
		String retard1String = session.get(PilotageConstants.CHECKLIST_RETARD_1).toString();
		String retard2String = session.get(PilotageConstants.CHECKLIST_RETARD_2).toString();
		String tempsModifString = session.get(PilotageConstants.CHECKLIST_TEMPS_MODIFICATION).toString();
		Date tempsRetard1 = DateService.getTimeFromString(DateService.getTime(DateService.strToDate("01/01/1970", retard1String), null));
		Date tempsRetard2 = DateService.getTimeFromString(DateService.getTime(DateService.strToDate("01/01/1970", retard2String), null));
		Date tempsModif = DateService.getTimeFromString(DateService.getTime(DateService.strToDate("01/01/1970", tempsModifString), null));
		completeBeforeDisplay(listCurrent, couleurs, retard1, retard2, tempsRetard1, tempsRetard2, mapToLate, tempsModif, filtreTypeListe);

		Collections.sort(listCurrent, new ChecklistCurrentSorter());
		
		return listCurrent;
	}
	
	public static void calculHeuresLocales(List<String[]> paysHeure){
		List<Heures_Oceor> listHeure = HeuresOceorDatabaseService.getAllByHourAsc();
		Calendar c = Calendar.getInstance();
		TimeZone z = c.getTimeZone();
		int offsetHrs = 0;
		if(!z.inDaylightTime(new Date())){
			offsetHrs = 1;
		}
		
		for (int i = 0; i < listHeure.size(); i++){
			Heures_Oceor heure = (Heures_Oceor) listHeure.get(i);
			int decalage = Integer.parseInt(heure.getHeure()) + offsetHrs;
			Date DateheureDomTom = DateService.addHours( new Date(), decalage);
			String heureDomTom = DateService.getTime(DateheureDomTom, DateService.pt1);
			String ville = heure.getTimezone().split("/")[1];
			String[] tab = {ville, heureDomTom};
			if (!ville.equals("Paris"))
				paysHeure.add(tab);
		}
	}
}
