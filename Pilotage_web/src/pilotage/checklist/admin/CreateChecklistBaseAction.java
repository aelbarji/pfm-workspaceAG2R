package pilotage.checklist.admin;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.checklist.ChecklistBaseSousTacheDatabaseService;
import pilotage.database.checklist.ChecklistDatabaseService;
import pilotage.database.checklist.ChecklistHoraireDatabaseService;
import pilotage.database.checklist.ChecklistTachesDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Checklist_Base_Soustache;
import pilotage.metier.Checklist_Horaire;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;

public class CreateChecklistBaseAction extends AbstractAction {
	private static final long serialVersionUID = 4761025588869118206L;
	
	private Boolean redirect = true;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;

	private Integer typeFrequenceDate;
	private Integer typeFrequenceHeure;
	
	//Variables de base
	private String nom;
	private Integer environnement;
	private String dateDebut;
	private Integer etat;
	private Integer criticite;
	
	private String typeDemande;
	private String heureReception;
	private String nomEmetteur;
	private String numeroObs;
	private String descriptionMail;
	private String descriptionObs;
	
	//Variables de la fréquence hebdo
	private boolean f1_lundi;
	private boolean f1_mardi;
	private boolean f1_mercredi;
	private boolean f1_jeudi;
	private boolean f1_vendredi;
	private boolean f1_samedi;
	private boolean f1_dimanche;
	private boolean f1_notFerie;

	//Variables de la fréquence mensuelle
	private boolean f2_1er;
	private boolean f2_2eme;
	private boolean f2_3eme;
	private boolean f2_4eme;
	private boolean f2_der;
	private boolean f2_lundi;
	private boolean f2_mardi;
	private boolean f2_mercredi;
	private boolean f2_jeudi;
	private boolean f2_vendredi;
	private boolean f2_samedi;
	private boolean f2_dimanche;
	private boolean f2_jour;
	private boolean f2_notFerie;
	
	//Variables de la fréquence semaine pair/impair
	private boolean f3_pair;
	private boolean f3_impair;
	private boolean f3_lundi;
	private boolean f3_mardi;
	private boolean f3_mercredi;
	private boolean f3_jeudi;
	private boolean f3_vendredi;
	private boolean f3_samedi;
	private boolean f3_dimanche;
	private boolean f3_notFerie;
	
	//Variables de la fréquence jour férié
	private Integer[] veilleFerie;
	private Integer[] jourFerie;
	private Integer[] lendemainFerie;
	private boolean checkAllVeilles;
	private boolean checkAllJours;
	private boolean checkAllLendemains;
	
	//Variables de la fréquence jour férié
	private List<String> listJoursExceptionnelsString;
	
	//Variables des horaires
	private List<String> listHeuresPonctuellesString;
	private String heureDebut;
	private String heureFin;
	private String frequence;
	
	//Variables des sous taches
	private List<List<String>> listSousTaches;

	public Boolean getRedirect() {
		return redirect;
	}

	public void setRedirect(Boolean redirect) {
		this.redirect = redirect;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSens() {
		return sens;
	}

	public void setSens(String sens) {
		this.sens = sens;
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

	public Integer getTypeFrequenceDate() {
		return typeFrequenceDate;
	}

	public void setTypeFrequenceDate(Integer typeFrequenceDate) {
		this.typeFrequenceDate = typeFrequenceDate;
	}

	public Integer getTypeFrequenceHeure() {
		return typeFrequenceHeure;
	}

	public void setTypeFrequenceHeure(Integer typeFrequenceHeure) {
		this.typeFrequenceHeure = typeFrequenceHeure;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getEnvironnement() {
		return environnement;
	}

	public void setEnvironnement(Integer environnement) {
		this.environnement = environnement;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		if(!"".equals(dateDebut))
			this.dateDebut = dateDebut;
	}

	public Integer getEtat() {
		return etat;
	}

	public void setEtat(Integer etat) {
		this.etat = etat;
	}

	public Integer getCriticite() {
		return criticite;
	}

	public void setCriticite(Integer criticite) {
		this.criticite = criticite;
	}

	public boolean isF1_lundi() {
		return f1_lundi;
	}

	public void setF1_lundi(boolean f1_lundi) {
		this.f1_lundi = f1_lundi;
	}

	public boolean isF1_mardi() {
		return f1_mardi;
	}

	public void setF1_mardi(boolean f1_mardi) {
		this.f1_mardi = f1_mardi;
	}

	public boolean isF1_mercredi() {
		return f1_mercredi;
	}

	public void setF1_mercredi(boolean f1_mercredi) {
		this.f1_mercredi = f1_mercredi;
	}

	public boolean isF1_jeudi() {
		return f1_jeudi;
	}

	public void setF1_jeudi(boolean f1_jeudi) {
		this.f1_jeudi = f1_jeudi;
	}

	public boolean isF1_vendredi() {
		return f1_vendredi;
	}

	public void setF1_vendredi(boolean f1_vendredi) {
		this.f1_vendredi = f1_vendredi;
	}

	public boolean isF1_samedi() {
		return f1_samedi;
	}

	public void setF1_samedi(boolean f1_samedi) {
		this.f1_samedi = f1_samedi;
	}

	public boolean isF1_dimanche() {
		return f1_dimanche;
	}

	public void setF1_dimanche(boolean f1_dimanche) {
		this.f1_dimanche = f1_dimanche;
	}

	public boolean isF1_notFerie() {
		return f1_notFerie;
	}

	public void setF1_notFerie(boolean f1_notFerie) {
		this.f1_notFerie = f1_notFerie;
	}

	public boolean isF2_1er() {
		return f2_1er;
	}

	public void setF2_1er(boolean f2_1er) {
		this.f2_1er = f2_1er;
	}

	public boolean isF2_2eme() {
		return f2_2eme;
	}

	public void setF2_2eme(boolean f2_2eme) {
		this.f2_2eme = f2_2eme;
	}

	public boolean isF2_3eme() {
		return f2_3eme;
	}

	public void setF2_3eme(boolean f2_3eme) {
		this.f2_3eme = f2_3eme;
	}

	public boolean isF2_4eme() {
		return f2_4eme;
	}

	public void setF2_4eme(boolean f2_4eme) {
		this.f2_4eme = f2_4eme;
	}

	public boolean isF2_der() {
		return f2_der;
	}

	public void setF2_der(boolean f2_der) {
		this.f2_der = f2_der;
	}

	public boolean isF2_lundi() {
		return f2_lundi;
	}

	public void setF2_lundi(boolean f2_lundi) {
		this.f2_lundi = f2_lundi;
	}

	public boolean isF2_mardi() {
		return f2_mardi;
	}

	public void setF2_mardi(boolean f2_mardi) {
		this.f2_mardi = f2_mardi;
	}

	public boolean isF2_mercredi() {
		return f2_mercredi;
	}

	public void setF2_mercredi(boolean f2_mercredi) {
		this.f2_mercredi = f2_mercredi;
	}

	public boolean isF2_jeudi() {
		return f2_jeudi;
	}

	public void setF2_jeudi(boolean f2_jeudi) {
		this.f2_jeudi = f2_jeudi;
	}

	public boolean isF2_vendredi() {
		return f2_vendredi;
	}

	public void setF2_vendredi(boolean f2_vendredi) {
		this.f2_vendredi = f2_vendredi;
	}

	public boolean isF2_samedi() {
		return f2_samedi;
	}

	public void setF2_samedi(boolean f2_samedi) {
		this.f2_samedi = f2_samedi;
	}

	public boolean isF2_dimanche() {
		return f2_dimanche;
	}

	public void setF2_dimanche(boolean f2_dimanche) {
		this.f2_dimanche = f2_dimanche;
	}

	public boolean isF2_jour() {
		return f2_jour;
	}

	public void setF2_jour(boolean f2_jour) {
		this.f2_jour = f2_jour;
	}

	public boolean isF2_notFerie() {
		return f2_notFerie;
	}

	public void setF2_notFerie(boolean f2_notFerie) {
		this.f2_notFerie = f2_notFerie;
	}

	public boolean isF3_pair() {
		return f3_pair;
	}

	public void setF3_pair(boolean f3_pair) {
		this.f3_pair = f3_pair;
	}

	public boolean isF3_impair() {
		return f3_impair;
	}

	public void setF3_impair(boolean f3_impair) {
		this.f3_impair = f3_impair;
	}

	public boolean isF3_lundi() {
		return f3_lundi;
	}

	public void setF3_lundi(boolean f3_lundi) {
		this.f3_lundi = f3_lundi;
	}

	public boolean isF3_mardi() {
		return f3_mardi;
	}

	public void setF3_mardi(boolean f3_mardi) {
		this.f3_mardi = f3_mardi;
	}

	public boolean isF3_mercredi() {
		return f3_mercredi;
	}

	public void setF3_mercredi(boolean f3_mercredi) {
		this.f3_mercredi = f3_mercredi;
	}

	public boolean isF3_jeudi() {
		return f3_jeudi;
	}

	public void setF3_jeudi(boolean f3_jeudi) {
		this.f3_jeudi = f3_jeudi;
	}

	public boolean isF3_vendredi() {
		return f3_vendredi;
	}

	public void setF3_vendredi(boolean f3_vendredi) {
		this.f3_vendredi = f3_vendredi;
	}

	public boolean isF3_samedi() {
		return f3_samedi;
	}

	public void setF3_samedi(boolean f3_samedi) {
		this.f3_samedi = f3_samedi;
	}

	public boolean isF3_dimanche() {
		return f3_dimanche;
	}

	public void setF3_dimanche(boolean f3_dimanche) {
		this.f3_dimanche = f3_dimanche;
	}

	public boolean isF3_notFerie() {
		return f3_notFerie;
	}

	public void setF3_notFerie(boolean f3_notFerie) {
		this.f3_notFerie = f3_notFerie;
	}

	public Integer[] getVeilleFerie() {
		return veilleFerie;
	}

	public void setVeilleFerie(Integer[] veilleFerie) {
		this.veilleFerie = veilleFerie;
	}

	public Integer[] getJourFerie() {
		return jourFerie;
	}

	public void setJourFerie(Integer[] jourFerie) {
		this.jourFerie = jourFerie;
	}

	public Integer[] getLendemainFerie() {
		return lendemainFerie;
	}

	public void setLendemainFerie(Integer[] lendemainFerie) {
		this.lendemainFerie = lendemainFerie;
	}

	public boolean isCheckAllVeilles() {
		return checkAllVeilles;
	}

	public void setCheckAllVeilles(boolean checkAllVeilles) {
		this.checkAllVeilles = checkAllVeilles;
	}

	public boolean isCheckAllJours() {
		return checkAllJours;
	}

	public void setCheckAllJours(boolean checkAllJours) {
		this.checkAllJours = checkAllJours;
	}

	public boolean isCheckAllLendemains() {
		return checkAllLendemains;
	}

	public void setCheckAllLendemains(boolean checkAllLendemains) {
		this.checkAllLendemains = checkAllLendemains;
	}

	public List<String> getListJoursExceptionnelsString() {
		return listJoursExceptionnelsString;
	}

	public void setListJoursExceptionnelsString(
			List<String> listJoursExceptionnelsString) {
		this.listJoursExceptionnelsString = listJoursExceptionnelsString;
	}

	public List<String> getListHeuresPonctuellesString() {
		return listHeuresPonctuellesString;
	}

	public void setListHeuresPonctuellesString(
			List<String> listHeuresPonctuellesString) {
		this.listHeuresPonctuellesString = listHeuresPonctuellesString;
	}

	public String getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(String heureDebut) {
		this.heureDebut = heureDebut;
	}

	public String getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(String heureFin) {
		this.heureFin = heureFin;
	}

	public String getFrequence() {
		return frequence;
	}

	public void setFrequence(String frequence) {
		this.frequence = frequence;
	}

	public List<List<String>> getListSousTaches() {
		return listSousTaches;
	}

	public void setListSousTaches(List<List<String>> listSousTaches) {
		this.listSousTaches = listSousTaches;
	}

	public String getTypeDemande() {
		return typeDemande;
	}

	public void setTypeDemande(String typeDemande) {
		this.typeDemande = typeDemande;
	}

	public String getHeureReception() {
		return heureReception;
	}

	public void setHeureReception(String heureReception) {
		this.heureReception = heureReception;
	}

	public String getNomEmetteur() {
		return nomEmetteur;
	}

	public void setNomEmetteur(String nomEmetteur) {
		this.nomEmetteur = nomEmetteur;
	}

	public String getNumeroObs() {
		return numeroObs;
	}

	public void setNumeroObs(String numeroObs) {
		this.numeroObs = numeroObs;
	}

	public String getDescriptionMail() {
		return descriptionMail;
	}

	public void setDescriptionMail(String descriptionMail) {
		this.descriptionMail = descriptionMail;
	}

	public String getDescriptionObs() {
		return descriptionObs;
	}

	public void setDescriptionObs(String descriptionObs) {
		this.descriptionObs = descriptionObs;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			List<Date> listHeuresPonctuelles = new ArrayList<Date>();
			List<Date> listJoursExceptionnels = new ArrayList<Date>();
			
			//Recup des infos non struts : init des variables
			int i;
			HttpServletRequest request = ServletActionContext.getRequest();
			
			//Recup des infos non struts : les heures ponctuelles
			if(PilotageConstants.HEURE_FREQ_EXCEPTIONNELLE.equals(typeFrequenceHeure)){
				i = 0;
				listHeuresPonctuellesString = new ArrayList<String>();
				while(request.getParameter("heurePonctuelle" + i) != null){
					String heurePonctuelle = request.getParameter("heurePonctuelle" + i);
					listHeuresPonctuellesString.add(heurePonctuelle);
					listHeuresPonctuelles.add(DateService.getTimeFromString(heurePonctuelle));
					++i;
				}
			}
			
			//Recup des infos non struts : les date exceptionnelles
			if(PilotageConstants.DATE_FREQ_EXCEPTIONNELLE.equals(typeFrequenceDate)){
				i = 0;
				listJoursExceptionnelsString = new ArrayList<String>();
				while(request.getParameter("dateExceptionnelle" + i) != null){
					String dateExceptionnelle = request.getParameter("dateExceptionnelle" + i);
					listJoursExceptionnelsString.add(dateExceptionnelle);
					listJoursExceptionnels.add(DateService.strToDate(dateExceptionnelle));
					++i;
				}
			}
			
			// Recup des infos non struts : les documents sous taches
			i = 0;
			Map<String, List<String>> documentsSousTaches = new HashMap<String, List<String>>();
			while (request.getParameter("nomSousTache" + i) != null) {
				int j = 0;
				List<String> listDocuments = new ArrayList<String>();
				String nonSousTache = request.getParameter("nomSousTache" + i);
				while (request.getParameter("documentSousTache" + i + j) != null) {
					listDocuments.add(request.getParameter(
							"documentSousTache" + i + j).trim());
					j++;
				}
				if(!listDocuments.isEmpty())
					documentsSousTaches.put(nonSousTache, listDocuments);
				
				i++;
			}
			
			//Recup des infos non struts : les sous taches
			i = 0;
			listSousTaches = new ArrayList<List<String>>();
			while(request.getParameter("nomSousTache" + i) != null){
				List<String> sousTache = new ArrayList<String>();
				sousTache.add(request.getParameter("nomSousTache" + i));
				sousTache.add(request.getParameter("decalageSousTacheHeure" + i));
				sousTache.add(request.getParameter("decalageSousTacheMinute" + i));
				sousTache.add(request.getParameter("consigneSousTache" + i).trim());
				//sousTache.add(request.getParameter("documentationSousTache" + i));
				listSousTaches.add(sousTache);
				++i;
			}

			//Sauvegarde en base
			Integer id = null;
			if(PilotageConstants.DATE_FREQ_HEBDO.equals(typeFrequenceDate)){
				id = ChecklistTachesDatabaseService.saveHebdo(nom, environnement, DateService.strToDate(dateDebut), etat, criticite, typeDemande, DateService.getTimeFromString(heureReception), nomEmetteur, descriptionMail, descriptionObs, numeroObs,
						f1_lundi, f1_mardi, f1_mercredi, f1_jeudi, f1_vendredi, f1_samedi, f1_dimanche, f1_notFerie,
						typeFrequenceHeure, listHeuresPonctuelles, DateService.getTimeFromString(heureDebut), DateService.getTimeFromString(heureFin), DateService.getTimeFromString(frequence),
						listSousTaches,documentsSousTaches);
			}
			else if(PilotageConstants.DATE_FREQ_MENSUELLE.equals(typeFrequenceDate)){
				id = ChecklistTachesDatabaseService.saveMensuel(nom, environnement, DateService.strToDate(dateDebut), etat, criticite, typeDemande, DateService.getTimeFromString(heureReception), nomEmetteur, descriptionMail, descriptionObs, numeroObs,
						f2_1er, f2_2eme, f2_3eme, f2_4eme, f2_der,
						f2_lundi, f2_mardi, f2_mercredi, f2_jeudi, f2_vendredi, f2_samedi, f2_dimanche, f2_jour, f2_notFerie,
						typeFrequenceHeure, listHeuresPonctuelles, DateService.getTimeFromString(heureDebut), DateService.getTimeFromString(heureFin), DateService.getTimeFromString(frequence),
						listSousTaches,documentsSousTaches);
			}
			else if(PilotageConstants.DATE_FREQ_PAIR_IMPAIR.equals(typeFrequenceDate)){
				id = ChecklistTachesDatabaseService.saveSemainePairImpair(nom, environnement, DateService.strToDate(dateDebut), etat, criticite, typeDemande, DateService.getTimeFromString(heureReception), nomEmetteur, descriptionMail, descriptionObs, numeroObs,
						f3_pair, f3_impair,
						f3_lundi, f3_mardi, f3_mercredi, f3_jeudi, f3_vendredi, f3_samedi, f3_dimanche, f3_notFerie,
						typeFrequenceHeure, listHeuresPonctuelles, DateService.getTimeFromString(heureDebut), DateService.getTimeFromString(heureFin), DateService.getTimeFromString(frequence),
						listSousTaches,documentsSousTaches);
			}
			else if(PilotageConstants.DATE_FREQ_FERIE.equals(typeFrequenceDate)){
				id = ChecklistTachesDatabaseService.saveFerie(nom, environnement, DateService.strToDate(dateDebut), etat, criticite, typeDemande, DateService.getTimeFromString(heureReception), nomEmetteur, descriptionMail, descriptionObs, numeroObs,
						veilleFerie, jourFerie, lendemainFerie,
						typeFrequenceHeure, listHeuresPonctuelles, DateService.getTimeFromString(heureDebut), DateService.getTimeFromString(heureFin), DateService.getTimeFromString(frequence),
						listSousTaches,documentsSousTaches);
			}
			else if(PilotageConstants.DATE_FREQ_EXCEPTIONNELLE.equals(typeFrequenceDate)){
				id = ChecklistTachesDatabaseService.saveExceptionnel(nom, environnement, DateService.strToDate(dateDebut), etat, criticite, typeDemande, DateService.getTimeFromString(heureReception), nomEmetteur, descriptionMail, descriptionObs, numeroObs,
						listJoursExceptionnels,
						typeFrequenceHeure, listHeuresPonctuelles, DateService.getTimeFromString(heureDebut), DateService.getTimeFromString(heureFin), DateService.getTimeFromString(frequence),
						listSousTaches,documentsSousTaches);
			}
			
			//Si la checklist du jour est déjà crée, on ajoute la nouvelle tache si l'heure n'est pas passée
			Date today = DateService.getTodayWithoutHour();
			boolean jourCompatible = false;
			boolean dateDebutCompatible = false;
			List<Checklist_Horaire> listChecklistHoraireToAdd = new ArrayList<Checklist_Horaire>();
			List<Integer> listSousTachesID = new ArrayList<Integer>();

			if(ChecklistDatabaseService.isCreated(today)){
				//test si la date de debut est compatible
				if(!DateService.strToDate(dateDebut).after(today)){
					dateDebutCompatible = true;
				}
				
				//Exceptionnel
				if(PilotageConstants.DATE_FREQ_EXCEPTIONNELLE.equals(typeFrequenceDate)){
					for(Date dateExcep : listJoursExceptionnels){
						if(today.equals(dateExcep)){
							jourCompatible = true;
							break;
						}
					}
				}
				//ferie
				else if(PilotageConstants.DATE_FREQ_FERIE.equals(typeFrequenceDate)){
					Integer veilleFerieID = DateService.getJourFerie(today, PilotageConstants.VEILLE_FERIE);
					Integer jourFerieID = DateService.getJourFerie(today, PilotageConstants.JOUR_FERIE);
					Integer lendemainFerieID = DateService.getJourFerie(today, PilotageConstants.LENDEMAIN_FERIE);
					
					if(veilleFerie != null){
						for(Integer elementVeilleFerie : veilleFerie){
							if(elementVeilleFerie.equals(veilleFerieID)){
								jourCompatible = true;
								break;
							}
						}
					}
					
					if(!jourCompatible && jourFerie != null){
						for(Integer elementJourFerie : jourFerie){
							if(elementJourFerie.equals(jourFerieID)){
								jourCompatible = true;
								break;
							}
						}
					}
					
					if(!jourCompatible && lendemainFerie != null){
						for(Integer elementLendemainFerie : lendemainFerie){
							if(elementLendemainFerie.equals(lendemainFerieID)){
								jourCompatible = true;
								break;
							}
						}
					}
				}
				//semaines paires/impaires
				else if(PilotageConstants.DATE_FREQ_PAIR_IMPAIR.equals(typeFrequenceDate)){
					Integer parite = DateService.getWeekParite(today);
					if((parite.equals(0) && f3_pair) || (parite.equals(1) && f3_impair)){
						Integer jourToday = DateService.getJour(today);
						if((jourToday.equals(PilotageConstants.LUNDI) && f3_lundi)
								|| (jourToday.equals(PilotageConstants.MARDI) && f3_mardi)
								|| (jourToday.equals(PilotageConstants.MERCREDI) && f3_mercredi)
								|| (jourToday.equals(PilotageConstants.JEUDI) && f3_jeudi)
								|| (jourToday.equals(PilotageConstants.VENDREDI) && f3_vendredi)
								|| (jourToday.equals(PilotageConstants.SAMEDI) && f3_samedi)
								|| (jourToday.equals(PilotageConstants.DIMANCHE) && f3_dimanche)){
							jourCompatible = true;
						}
					}
				}
				//frequence mensuelle
				else if(PilotageConstants.DATE_FREQ_MENSUELLE.equals(typeFrequenceDate)){
					Integer place = DateService.getPlaceOfDayInMonth(today);
					Boolean lastPlace = DateService.isLastPlaceOfDayInMonth(today);
					
					if((place.equals(1) && f2_1er) || (place.equals(2) && f2_2eme) || (place.equals(3) && f2_3eme) || (place.equals(4) && f2_4eme) || (lastPlace && f2_der)){
						Integer jourToday = DateService.getJour(today);
						if((jourToday.equals(PilotageConstants.LUNDI) && f2_lundi)
								|| (jourToday.equals(PilotageConstants.MARDI) && f2_mardi)
								|| (jourToday.equals(PilotageConstants.MERCREDI) && f2_mercredi)
								|| (jourToday.equals(PilotageConstants.JEUDI) && f2_jeudi)
								|| (jourToday.equals(PilotageConstants.VENDREDI) && f2_vendredi)
								|| (jourToday.equals(PilotageConstants.SAMEDI) && f2_samedi)
								|| (jourToday.equals(PilotageConstants.DIMANCHE) && f2_dimanche)){
							jourCompatible = true;
						}
					}
				}
				//frequence hebdo
				else if(PilotageConstants.DATE_FREQ_HEBDO.equals(typeFrequenceDate)){
					Integer jourToday = DateService.getJour(today);
					if((jourToday.equals(PilotageConstants.LUNDI) && f1_lundi)
							|| (jourToday.equals(PilotageConstants.MARDI) && f1_mardi)
							|| (jourToday.equals(PilotageConstants.MERCREDI) && f1_mercredi)
							|| (jourToday.equals(PilotageConstants.JEUDI) && f1_jeudi)
							|| (jourToday.equals(PilotageConstants.VENDREDI) && f1_vendredi)
							|| (jourToday.equals(PilotageConstants.SAMEDI) && f1_samedi)
							|| (jourToday.equals(PilotageConstants.DIMANCHE) && f1_dimanche)){
						jourCompatible = true;
					}
				}

				//recupération des horaires compatibles
				if(dateDebutCompatible && jourCompatible){
					Date now = new Date();
					List<Checklist_Horaire> listChecklistHoraire = ChecklistHoraireDatabaseService.getListFromBase(id);
					for(Checklist_Horaire checkHoraire : listChecklistHoraire){
						Date dateExecution = DateService.getDateHeure(today, checkHoraire.getHoraire());
						if(dateExecution.after(now)){
							listChecklistHoraireToAdd.add(checkHoraire);
						}
					}
					List<Checklist_Base_Soustache> listChecklistSousTache = ChecklistBaseSousTacheDatabaseService.getListFromBase(id);
					for(Checklist_Base_Soustache checklistSousTache : listChecklistSousTache)
						listSousTachesID.add(checklistSousTache.getId());
					
					if(!listSousTachesID.isEmpty() && !listChecklistHoraireToAdd.isEmpty())
						ChecklistDatabaseService.addInChecklistOfTheDay(((Users)session.get(PilotageConstants.USER_LOGGED)).getLogin(), today, listSousTachesID, listChecklistHoraireToAdd);
				}
			}
			
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.checklist.tache.creation"), new Object[]{nom,ChecklistTachesDatabaseService.getId(nom, etat, environnement, DateService.strToDate(dateDebut), criticite)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_CHECKLIST);

			info = MessageFormat.format(getText("checklist.tache.creation.valide"), new Object[]{nom});
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'une tache - ", e);
			
			return ERROR;
		}
	}

}
