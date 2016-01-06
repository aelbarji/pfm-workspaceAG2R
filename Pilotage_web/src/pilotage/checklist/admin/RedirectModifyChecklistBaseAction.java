package pilotage.checklist.admin;

import java.util.ArrayList;
import java.util.List;

import pilotage.database.applicatif.EnvironmentDatabaseService;
import pilotage.database.checklist.ChecklistBaseDatabaseService;
import pilotage.database.checklist.ChecklistBaseSousTacheDatabaseService;
import pilotage.database.checklist.ChecklistCriticiteDatabaseService;
import pilotage.database.checklist.ChecklistEtatDatabaseService;
import pilotage.database.checklist.ChecklistExceptionnelDatabaseService;
import pilotage.database.checklist.ChecklistFerieDatabaseService;
import pilotage.database.checklist.ChecklistHoraireDatabaseService;
import pilotage.database.checklist.ChecklistJourDatabaseService;
import pilotage.database.checklist.ChecklistMensuelDatabaseService;
import pilotage.database.checklist.ChecklistPariteDatabaseService;
import pilotage.database.checklist.JourFerieDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Checklist_Base;
import pilotage.metier.Checklist_Base_Soustache;
import pilotage.metier.Checklist_Consigne_Documents;
import pilotage.metier.Checklist_Criticite;
import pilotage.metier.Checklist_Etat;
import pilotage.metier.Checklist_Exceptionnel;
import pilotage.metier.Checklist_Ferie;
import pilotage.metier.Checklist_Horaire;
import pilotage.metier.Checklist_Jour;
import pilotage.metier.Checklist_Mensuel;
import pilotage.metier.Checklist_Parite;
import pilotage.metier.Environnement;
import pilotage.metier.Jour_Ferie;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;

public class RedirectModifyChecklistBaseAction extends AbstractAction {

	private static final long serialVersionUID = -3479688520307800625L;

	private Boolean redirect = false;
	
	private Integer selectRow;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private List<Environnement> listEnvironnement;
	private List<Checklist_Etat> listEtat;
	private List<Checklist_Criticite> listCriticite;
	private List<Jour_Ferie> listFerie;
	
	private Integer typeFrequenceDate;
	private Integer typeFrequenceHeure;
	
	//Variables de base
	private String nom;
	private Integer environnement;
	private String dateDebut;
	private Integer etat;
	private Integer criticite;
	
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
	
	//Variables de la fréquence jour férié
	private List<String> listJoursExceptionnelsString;
	
	//Variables des horaires
	private List<String> listHeuresPonctuellesString;
	private String heureDebut;
	private String heureFin;
	private String frequence;
	
	//Variables des sous taches
	private List<List<String>> listSousTaches;
	private List<List<String>> documentsSousTaches;
	
	public Boolean getRedirect() {
		return redirect;
	}

	public void setRedirect(Boolean redirect) {
		this.redirect = redirect;
	}

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
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

	public List<Environnement> getListEnvironnement() {
		return listEnvironnement;
	}

	public void setListEnvironnement(List<Environnement> listEnvironnement) {
		this.listEnvironnement = listEnvironnement;
	}

	public List<Checklist_Etat> getListEtat() {
		return listEtat;
	}

	public void setListEtat(List<Checklist_Etat> listEtat) {
		this.listEtat = listEtat;
	}

	public List<Checklist_Criticite> getListCriticite() {
		return listCriticite;
	}

	public void setListCriticite(List<Checklist_Criticite> listCriticite) {
		this.listCriticite = listCriticite;
	}

	public List<Jour_Ferie> getListFerie() {
		return listFerie;
	}

	public void setListFerie(List<Jour_Ferie> listFerie) {
		this.listFerie = listFerie;
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
	
	public List<List<String>> getDocumentsSousTaches() {
		return documentsSousTaches;
	}

	public void setDocumentsSousTaches(List<List<String>> documentsSousTaches) {
		this.documentsSousTaches = documentsSousTaches;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			listCriticite = ChecklistCriticiteDatabaseService.getAll();
			listEnvironnement = EnvironmentDatabaseService.getAll();
			listEtat = ChecklistEtatDatabaseService.getAll();
			listFerie = JourFerieDatabaseService.getAll();
			
			if(!redirect){
				//base
				Checklist_Base base = ChecklistBaseDatabaseService.get(selectRow);
				nom = base.getTache();
				environnement = base.getEnvironnement().getId();
				dateDebut = DateService.dateToStr(base.getDateDebut(), DateService.p1);
				etat = base.getEtat().getId();
				criticite = base.getCriticite().getId();
				
				//jours exceptionnels
				List<Checklist_Exceptionnel> listJoursExceptionnels = ChecklistExceptionnelDatabaseService.getListFromBase(selectRow);
				if(listJoursExceptionnels != null && !listJoursExceptionnels.isEmpty()){
					typeFrequenceDate = PilotageConstants.DATE_FREQ_EXCEPTIONNELLE;
					listJoursExceptionnelsString = new ArrayList<String>();
					for(Checklist_Exceptionnel jourExceptionnel : listJoursExceptionnels){
						listJoursExceptionnelsString.add(DateService.dateToStr(jourExceptionnel.getJour(),DateService.p1));
					}
				}
				
				//jours fériés
				if(typeFrequenceDate == null){
					List<Checklist_Ferie> listJoursFeries = ChecklistFerieDatabaseService.getListFromBase(selectRow);
					if(listJoursFeries != null && !listJoursFeries.isEmpty()){
						typeFrequenceDate = PilotageConstants.DATE_FREQ_FERIE;
						List<Integer> veillesID = new ArrayList<Integer>();
						List<Integer> joursID = new ArrayList<Integer>();
						List<Integer> lendemainsID = new ArrayList<Integer>();
						for(Checklist_Ferie jourFerie : listJoursFeries){
							if(jourFerie.getLendeveille() == PilotageConstants.VEILLE_FERIE){
								veillesID.add(jourFerie.getIdJourFerie().getId());
							}
							else if(jourFerie.getLendeveille() == PilotageConstants.JOUR_FERIE){
								joursID.add(jourFerie.getIdJourFerie().getId());
							}
							else if(jourFerie.getLendeveille() == PilotageConstants.LENDEMAIN_FERIE){
								lendemainsID.add(jourFerie.getIdJourFerie().getId());
							}
						}
						veilleFerie = new Integer[veillesID.size()];
						jourFerie = new Integer[joursID.size()];
						lendemainFerie = new Integer[lendemainsID.size()];
						
						for(int i = 0; i < veillesID.size(); ++i){
							veilleFerie[i] = veillesID.get(i);
						}
						for(int i = 0; i < joursID.size(); ++i){
							jourFerie[i] = joursID.get(i);
						}
						for(int i = 0; i < lendemainsID.size(); ++i){
							lendemainFerie[i] = lendemainsID.get(i);
						}
					}
				}
				
				//semaines pairs/impairs
				if(typeFrequenceDate == null){
					List<Checklist_Parite> listParite = ChecklistPariteDatabaseService.getListFromBase(selectRow);
					if(listParite != null && !listParite.isEmpty()){
						typeFrequenceDate = PilotageConstants.DATE_FREQ_PAIR_IMPAIR;
						for(Checklist_Parite parite : listParite){
							if(parite.getParite() == PilotageConstants.PAIR){
								f3_pair = true;
							}
							else if(parite.getParite() == PilotageConstants.IMPAIR){
								f3_impair = true;
							}
						}
						
						List<Checklist_Jour> listJours = ChecklistJourDatabaseService.getListFromBase(selectRow);
						if(listJours != null && !listJours.isEmpty()){
							for(Checklist_Jour jour : listJours){
								if(jour.getJour() == PilotageConstants.LUNDI)
									f3_lundi = true;
								else if(jour.getJour() == PilotageConstants.MARDI)
									f3_mardi = true;
								else if(jour.getJour() == PilotageConstants.MERCREDI)
									f3_mercredi = true;
								else if(jour.getJour() == PilotageConstants.JEUDI)
									f3_jeudi = true;
								else if(jour.getJour() == PilotageConstants.VENDREDI)
									f3_vendredi = true;
								else if(jour.getJour() == PilotageConstants.SAMEDI)
									f3_samedi = true;
								else if(jour.getJour() == PilotageConstants.DIMANCHE)
									f3_dimanche = true;
								
								if(!jour.isFerie())
									f3_notFerie = true;
							}
						}
					}
				}
				
				//mensuelles
				if(typeFrequenceDate == null){
					List<Checklist_Mensuel> listMensuelles = ChecklistMensuelDatabaseService.getListFromBase(selectRow);
					if(listMensuelles != null && !listMensuelles.isEmpty()){
						typeFrequenceDate = PilotageConstants.DATE_FREQ_MENSUELLE;
						for(Checklist_Mensuel mensuel : listMensuelles){
							if(mensuel.getMensuel() == 1)
								f2_1er = true;
							else if(mensuel.getMensuel() == 2)
								f2_2eme = true;
							else if(mensuel.getMensuel() == 3)
								f2_3eme = true;
							else if(mensuel.getMensuel() == 4)
								f2_4eme = true;
							else if(mensuel.getMensuel() == 5)
								f2_der = true;
						}
						
						List<Checklist_Jour> listJours = ChecklistJourDatabaseService.getListFromBase(selectRow);
						if(listJours != null && !listJours.isEmpty()){
							for(Checklist_Jour jour : listJours){
								if(jour.getJour() == PilotageConstants.LUNDI)
									f2_lundi = true;
								else if(jour.getJour() == PilotageConstants.MARDI)
									f2_mardi = true;
								else if(jour.getJour() == PilotageConstants.MERCREDI)
									f2_mercredi = true;
								else if(jour.getJour() == PilotageConstants.JEUDI)
									f2_jeudi = true;
								else if(jour.getJour() == PilotageConstants.VENDREDI)
									f2_vendredi = true;
								else if(jour.getJour() == PilotageConstants.SAMEDI)
									f2_samedi = true;
								else if(jour.getJour() == PilotageConstants.DIMANCHE)
									f2_dimanche = true;
								
								if(!jour.isFerie())
									f2_notFerie = true;
							}
						}
					}
				}
				
				//hebdomadaire
				if(typeFrequenceDate == null){
					List<Checklist_Jour> listJours = ChecklistJourDatabaseService.getListFromBase(selectRow);
					if(listJours != null && !listJours.isEmpty()){
						typeFrequenceDate = PilotageConstants.DATE_FREQ_HEBDO;
						for(Checklist_Jour jour : listJours){
							if(jour.getJour() == PilotageConstants.LUNDI)
								f1_lundi = true;
							else if(jour.getJour() == PilotageConstants.MARDI)
								f1_mardi = true;
							else if(jour.getJour() == PilotageConstants.MERCREDI)
								f1_mercredi = true;
							else if(jour.getJour() == PilotageConstants.JEUDI)
								f1_jeudi = true;
							else if(jour.getJour() == PilotageConstants.VENDREDI)
								f1_vendredi = true;
							else if(jour.getJour() == PilotageConstants.SAMEDI)
								f1_samedi = true;
							else if(jour.getJour() == PilotageConstants.DIMANCHE)
								f1_dimanche = true;
							
							if(!jour.isFerie())
								f1_notFerie = true;
						}
					}
				}
				
				
				//horaires
				List<Checklist_Horaire> listHoraires = ChecklistHoraireDatabaseService.getListFromBase(selectRow);
				if(isFrequenceHoraire(listHoraires)){
					typeFrequenceHeure = PilotageConstants.HEURE_FREQ_REGULIER;
					
					heureDebut = DateService.getTime(listHoraires.get(0).getHoraire(), null);
					heureFin = DateService.getTime(listHoraires.get(listHoraires.size() - 1).getHoraire(), null);
					
					Long ecart = listHoraires.get(1).getHoraireStamp() - listHoraires.get(0).getHoraireStamp();
					frequence = DateService.getTime(ecart);
				}
				else{
					typeFrequenceHeure = PilotageConstants.HEURE_FREQ_EXCEPTIONNELLE;
					listHeuresPonctuellesString = new ArrayList<String>();
					for(Checklist_Horaire horaire : listHoraires){
						listHeuresPonctuellesString.add(DateService.getTime(horaire.getHoraire(), null));
					}
				}
				
				
				//sous taches
				List<Checklist_Base_Soustache> listBaseSousTaches = ChecklistBaseSousTacheDatabaseService.getListFromBase(selectRow);
				listSousTaches = new ArrayList<List<String>>();
				documentsSousTaches = new ArrayList<List<String>>();
				for(Checklist_Base_Soustache sousTache : listBaseSousTaches){
					String[] strDecalage = sousTache.getDecalageString().split(":");
					List<String> sousTacheString = new ArrayList<String>();
					sousTacheString.add(sousTache.getNomSousTache());
					sousTacheString.add(strDecalage[0]);
					sousTacheString.add(strDecalage[1]);
					sousTacheString.add(sousTache.getIdConsigne().getConsigne());
					//sousTacheString.add(sousTache.getIdConsigne().getDocumentConsigne());
					List<Checklist_Consigne_Documents> listDocuments = ChecklistBaseSousTacheDatabaseService.getAllDocumentsForSousTache(sousTache.getIdConsigne().getId());
					List<String> listDocumentsString = new ArrayList<String>();
					
					for(Checklist_Consigne_Documents document : listDocuments ){
						listDocumentsString.add(document.getDocument());
					 }
					
					documentsSousTaches.add(listDocumentsString);
					listSousTaches.add(sousTacheString);
				}
			}
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Redirection vers la modification d'une tache - ", e);
			
			return ERROR;
		}
	}

	/**
	 * Test pour savoir si on a une fréquence horaire
	 * @param listHoraires
	 * @return
	 */
	public static boolean isFrequenceHoraire(List<Checklist_Horaire> listHoraires) {
		if(listHoraires == null || listHoraires.size() < 3){
			return false;
		}
		else{
			Long heurePrecedente = listHoraires.get(0).getHoraireStamp();
			Long heureSuivante = listHoraires.get(1).getHoraireStamp();
			Long ecart = heureSuivante - heurePrecedente;
			
			for(int i = 2; i < listHoraires.size(); ++i){
				heurePrecedente = heureSuivante;
				heureSuivante = listHoraires.get(i).getHoraireStamp();
				
				if(ecart != (heureSuivante - heurePrecedente)){
					return false;
				}
			}
			return true;
		}
	}

}
