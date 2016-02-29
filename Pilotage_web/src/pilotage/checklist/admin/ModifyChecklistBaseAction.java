package pilotage.checklist.admin;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.checklist.ChecklistBaseDatabaseService;
import pilotage.database.checklist.ChecklistBaseSousTacheDatabaseService;
import pilotage.database.checklist.ChecklistDatabaseService;
import pilotage.database.checklist.ChecklistExceptionnelDatabaseService;
import pilotage.database.checklist.ChecklistFerieDatabaseService;
import pilotage.database.checklist.ChecklistHoraireDatabaseService;
import pilotage.database.checklist.ChecklistJourDatabaseService;
import pilotage.database.checklist.ChecklistMensuelDatabaseService;
import pilotage.database.checklist.ChecklistPariteDatabaseService;
import pilotage.database.checklist.ChecklistTachesDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Checklist_Base;
import pilotage.metier.Checklist_Base_Soustache;
import pilotage.metier.Checklist_Consigne_Documents;
import pilotage.metier.Checklist_Exceptionnel;
import pilotage.metier.Checklist_Ferie;
import pilotage.metier.Checklist_Horaire;
import pilotage.metier.Checklist_Jour;
import pilotage.metier.Checklist_Mensuel;
import pilotage.metier.Checklist_Parite;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;

public class ModifyChecklistBaseAction extends AbstractAction {
	private static final long serialVersionUID = 4761025588869118206L;

	private Boolean redirect = true;
	private Integer selectRow;

	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;

	private Integer typeFrequenceDate;
	private Integer typeFrequenceHeure;
	
	// Variables de base
	private String nom;
	private Integer environnement;
	private String dateDebut;
	private Integer etat;
	private Integer criticite;
	private Integer tzTache;
	
	private String typeDemande;
	private String heureReception;
	private String nomEmetteur;
	private String numeroObs;
	private String descriptionMail;
	private String descriptionObs;

	// Variables de la fréquence hebdo
	private boolean f1_lundi;
	private boolean f1_mardi;
	private boolean f1_mercredi;
	private boolean f1_jeudi;
	private boolean f1_vendredi;
	private boolean f1_samedi;
	private boolean f1_dimanche;
	private boolean f1_notFerie;

	// Variables de la fréquence mensuelle
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

	// Variables de la fréquence semaine pair/impair
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

	// Variables de la fréquence jour férié
	private Integer[] veilleFerie;
	private Integer[] jourFerie;
	private Integer[] lendemainFerie;
	private boolean checkAllVeilles;
	private boolean checkAllJours;
	private boolean checkAllLendemains;

	// Variables de la fréquence jour férié
	private List<String> listJoursExceptionnelsString;

	// Variables des horaires
	private List<String> listHeuresPonctuellesString;
	private String heureDebut;
	private String heureFin;
	private String frequence;

	// Variables des sous taches
	private List<List<String>> listSousTaches;

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
		if (!"".equals(dateDebut))
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
	
	public Integer getTzTache() {
		return tzTache;
	}

	public void setTzTache(Integer tzTache) {
		this.tzTache = tzTache;
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
		try {
			Integer oldTypeFrequenceDate = null;
			Integer oldTypeFrequenceHeure = null;
			String oldHeureDebut = null;
			String oldHeureFin = null;
			String oldFrequence = null;

			List<Integer> listSousTacheToDelete = null;
			Map<Integer, List<String>> listSousTacheToUpdate = null;
			Map<String, List<String>> listDocumentToAdd = null;
			List<List<String>> listSousTacheToAdd = null;

			List<Integer> listChecklistJourToDelete = null;
			List<Integer> listChecklistJourToModify = null;
			List<Integer> listChecklistJourToAdd = null;

			List<Integer> listChecklistExceptionnelToDelete = null;
			List<Date> listChecklistExceptionnelToAdd = null;

			List<Integer> listChecklistFerieToDelete = null;
			List<Integer> listChecklistFerieVeilleToAdd = null;
			List<Integer> listChecklistFerieJourToAdd = null;
			List<Integer> listChecklistFerieLendemainToAdd = null;

			List<Integer> listChecklistPariteToDelete = null;
			List<Integer> listChecklistPariteToAdd = null;

			List<Integer> listChecklistMensuelToDelete = null;
			List<Integer> listChecklistMensuelToAdd = null;

			List<Date> listHeuresPonctuelles = new ArrayList<Date>();
			List<Date> listJoursExceptionnels = new ArrayList<Date>();

			List<Date> listHoraireToAdd = new ArrayList<Date>();
			List<Integer> listHoraireToDelete = new ArrayList<Integer>();

			// Recup des infos non struts : init des variables
			int i;
			HttpServletRequest request = ServletActionContext.getRequest();

			// Recup des infos non struts : les heures ponctuelles
			if (PilotageConstants.HEURE_FREQ_EXCEPTIONNELLE
					.equals(typeFrequenceHeure)) {
				i = 0;
				listHeuresPonctuellesString = new ArrayList<String>();
				while (request.getParameter("heurePonctuelle" + i) != null) {
					String heurePonctuelle = request
							.getParameter("heurePonctuelle" + i);
					listHeuresPonctuellesString.add(heurePonctuelle);
					listHeuresPonctuelles.add(DateService
							.getTimeFromString(heurePonctuelle));
					++i;
				}
			}

			// Recup des infos non struts : les date exceptionnelles
			if (PilotageConstants.DATE_FREQ_EXCEPTIONNELLE
					.equals(typeFrequenceDate)) {
				i = 0;
				listJoursExceptionnelsString = new ArrayList<String>();
				while (request.getParameter("dateExceptionnelle" + i) != null) {
					String dateExceptionnelle = request
							.getParameter("dateExceptionnelle" + i);
					listJoursExceptionnelsString.add(dateExceptionnelle);
					listJoursExceptionnels.add(DateService
							.strToDate(dateExceptionnelle));
					++i;
				}
			}

			// Recup des infos non struts : les consignes sous taches
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
				if (!listDocuments.isEmpty())
					documentsSousTaches.put(nonSousTache, listDocuments);
				i++;
			}

			// Recup des infos non struts : les sous taches
			i = 0;
			listSousTaches = new ArrayList<List<String>>();
			while (request.getParameter("nomSousTache" + i) != null) {
				List<String> sousTache = new ArrayList<String>();
				sousTache.add(request.getParameter("nomSousTache" + i));
				sousTache.add(request
						.getParameter("decalageSousTacheHeure" + i));
				sousTache.add(request.getParameter("decalageSousTacheMinute"
						+ i));
				sousTache.add(request.getParameter("consigneSousTache" + i)
						.trim());
				// sousTache.add(request.getParameter("documentationSousTache" +
				// i));
				listSousTaches.add(sousTache);
				++i;
			}

			// Recuperation de l'ancienne fréquence
			if (ChecklistExceptionnelDatabaseService
					.isFrequenceExceptionnelle(selectRow)) {
				oldTypeFrequenceDate = PilotageConstants.DATE_FREQ_EXCEPTIONNELLE;
			} else if (ChecklistFerieDatabaseService
					.isFrequenceFerie(selectRow)) {
				oldTypeFrequenceDate = PilotageConstants.DATE_FREQ_FERIE;
			} else if (ChecklistPariteDatabaseService
					.isFrequenceParite(selectRow)) {
				oldTypeFrequenceDate = PilotageConstants.DATE_FREQ_PAIR_IMPAIR;
			} else if (ChecklistMensuelDatabaseService
					.isFrequenceMensuelle(selectRow)) {
				oldTypeFrequenceDate = PilotageConstants.DATE_FREQ_MENSUELLE;
			} else {
				oldTypeFrequenceDate = PilotageConstants.DATE_FREQ_HEBDO;
			}

			// récupération des modifications si on a pas changé de fréquence
			if (oldTypeFrequenceDate.equals(typeFrequenceDate)) {
				// fréquence exceptionnelle
				if (typeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_EXCEPTIONNELLE)) {
					listChecklistExceptionnelToDelete = new ArrayList<Integer>();
					listChecklistExceptionnelToAdd = new ArrayList<Date>();

					recuperationDateModificationsExceptionnelles(
							listJoursExceptionnels,
							listChecklistExceptionnelToAdd,
							listChecklistExceptionnelToDelete);
				}

				// fréquence fériée
				else if (typeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_FERIE)) {
					listChecklistFerieToDelete = new ArrayList<Integer>();
					listChecklistFerieVeilleToAdd = new ArrayList<Integer>();
					listChecklistFerieJourToAdd = new ArrayList<Integer>();
					listChecklistFerieLendemainToAdd = new ArrayList<Integer>();

					recuperationDateModificationsFeries(
							listChecklistFerieVeilleToAdd,
							listChecklistFerieJourToAdd,
							listChecklistFerieLendemainToAdd,
							listChecklistFerieToDelete);
				}

				// fréquence parité
				else if (typeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_PAIR_IMPAIR)) {
					listChecklistPariteToAdd = new ArrayList<Integer>();
					listChecklistPariteToDelete = new ArrayList<Integer>();
					listChecklistJourToAdd = new ArrayList<Integer>();
					listChecklistJourToModify = new ArrayList<Integer>();
					listChecklistJourToDelete = new ArrayList<Integer>();

					recuperationDateModificationsParites(
							listChecklistPariteToAdd,
							listChecklistPariteToDelete,
							listChecklistJourToAdd, listChecklistJourToModify,
							listChecklistJourToDelete);
				}

				// fréquence mensuelle
				else if (typeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_MENSUELLE)) {
					listChecklistMensuelToDelete = new ArrayList<Integer>();
					listChecklistMensuelToAdd = new ArrayList<Integer>();
					listChecklistJourToAdd = new ArrayList<Integer>();
					listChecklistJourToModify = new ArrayList<Integer>();
					listChecklistJourToDelete = new ArrayList<Integer>();

					recuperationDateModificationsMensuelles(
							listChecklistMensuelToAdd,
							listChecklistMensuelToDelete,
							listChecklistJourToAdd, listChecklistJourToModify,
							listChecklistJourToDelete);
				}

				// fréquence hebdo
				else if (typeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_HEBDO)) {
					listChecklistJourToAdd = new ArrayList<Integer>();
					listChecklistJourToModify = new ArrayList<Integer>();
					listChecklistJourToDelete = new ArrayList<Integer>();

					recuperationDateModificationsHebdomadaires(
							listChecklistJourToAdd, listChecklistJourToModify,
							listChecklistJourToDelete);
				}
			}

			// récupération des horaires en base
			List<Checklist_Horaire> listHorairesEnBase = ChecklistHoraireDatabaseService
					.getListFromBase(selectRow);
			if (RedirectModifyChecklistBaseAction
					.isFrequenceHoraire(listHorairesEnBase)) {
				oldTypeFrequenceHeure = PilotageConstants.HEURE_FREQ_REGULIER;

				oldHeureDebut = DateService.getTime(listHorairesEnBase.get(0)
						.getHoraire(), null);
				oldHeureFin = DateService.getTime(
						listHorairesEnBase.get(listHorairesEnBase.size() - 1)
								.getHoraire(), null);

				Long ecart = listHorairesEnBase.get(1).getHoraireStamp()
						- listHorairesEnBase.get(0).getHoraireStamp();
				oldFrequence = DateService.getTime(ecart);
			} else {
				oldTypeFrequenceHeure = PilotageConstants.HEURE_FREQ_EXCEPTIONNELLE;

				listHoraireToDelete = new ArrayList<Integer>();
				listHoraireToAdd = new ArrayList<Date>();
				recuperationModificationsHoraire(listHeuresPonctuelles,
						listHoraireToAdd, listHoraireToDelete);
			}

			// recupération des modifications des sous taches à faire
			listSousTacheToDelete = new ArrayList<Integer>();
			listSousTacheToUpdate = new HashMap<Integer, List<String>>();
			listSousTacheToAdd = new ArrayList<List<String>>();
			listDocumentToAdd = new HashMap<String, List<String>>();
			List<Checklist_Base_Soustache> listSousTachesEnBase = ChecklistBaseSousTacheDatabaseService
					.getListFromBase(selectRow);

			for (Checklist_Base_Soustache sousTacheEnBase : listSousTachesEnBase) {
				boolean stillOnScreen = false;
				boolean toUpdate = false;
				String nomSousTacheEnBase = sousTacheEnBase.getNomSousTache();
				String decalageSousTacheEnBase = sousTacheEnBase
						.getDecalageString().substring(0, 5);
				String consigneSousTacheEnBase = sousTacheEnBase
						.getIdConsigne().getConsigne();
				// String documentationSousTacheEnBase =
				// sousTacheEnBase.getIdConsigne().getDocumentConsigne();
				List<Checklist_Consigne_Documents> listDocuments = ChecklistBaseSousTacheDatabaseService
						.getAllDocumentsForSousTache(sousTacheEnBase
								.getIdConsigne().getId());
				List<String> listDocumentsValues = new ArrayList<String>();

				for (Checklist_Consigne_Documents document : listDocuments)
					listDocumentsValues.add(document.getDocument());

				for (List<String> sousTacheSurEcran : listSousTaches) {
					if (sousTacheSurEcran.get(0).equals(nomSousTacheEnBase)
							&& (sousTacheSurEcran.get(1) + ":" + sousTacheSurEcran
									.get(2)).equals(decalageSousTacheEnBase)) {
						if (sousTacheSurEcran.get(3).equals(
								consigneSousTacheEnBase.trim())
								&& compareList(listDocumentsValues,
										documentsSousTaches
												.get(nomSousTacheEnBase))) {
							stillOnScreen = true;
							break;
						} else {
							toUpdate = true;
							listSousTacheToUpdate.put(sousTacheEnBase.getId(),
									sousTacheSurEcran);
							break;
						}
					}
				}
				if (!toUpdate && !stillOnScreen)
					listSousTacheToDelete.add(sousTacheEnBase.getId());
			}

			for (List<String> sousTacheSurEcran : listSousTaches) {
				boolean alreadyInBase = false;
				for (Checklist_Base_Soustache sousTacheEnBase : listSousTachesEnBase) {
					String nomSousTacheEnBase = sousTacheEnBase
							.getNomSousTache();
					String decalageSousTacheEnBase = sousTacheEnBase
							.getDecalageString().substring(0, 5);
					;

					if (sousTacheSurEcran.get(0).equals(nomSousTacheEnBase)
							&& (sousTacheSurEcran.get(1) + ":" + sousTacheSurEcran
									.get(2)).equals(decalageSousTacheEnBase)) {
						alreadyInBase = true;
						break;
					}
				}
				if (!alreadyInBase) {
					listSousTacheToAdd.add(sousTacheSurEcran);
					listDocumentToAdd.put(sousTacheSurEcran.get(0),
							documentsSousTaches.get(sousTacheSurEcran.get(0)));
				}

			}

			String historique = " ";
			Checklist_Base cb = ChecklistBaseDatabaseService.get(selectRow);
			if (!nom.equals(cb.getTache())) {
				historique += "du nom, ";
			}
			if (!environnement.equals(cb.getEnvironnement().getId())) {
				historique += "d'environnement, ";
			}
			if (!DateService.strToDate(dateDebut).equals(cb.getDateDebut())) {
				historique += "de date début, ";
			}
			if (!etat.equals(cb.getEtat().getId())) {
				historique += "d'état, ";
			}
			if (!criticite.equals(cb.getCriticite().getId())) {
				historique += "de criticité, ";
			}
		
			for (Entry<Integer, List<String>> in : listSousTacheToUpdate
					.entrySet()) {
				historique += "de ";
				Checklist_Base_Soustache cbst = ChecklistBaseSousTacheDatabaseService
						.get(in.getKey());

				if (!cbst.getNomSousTache().equals(in.getValue().get(0))) {
					historique += "nom sous tache, ";
				}
				if (!cbst.getDecalageString().equals(
						in.getValue().get(1) + ":" + in.getValue().get(2)
								+ ":00")) {
					historique += "decalage, ";
				}
				if (!cbst.getIdConsigne().getConsigne()
						.equals(in.getValue().get(3))) {
					historique += "consigne, ";
				}
				for (Entry<String, List<String>> doc : documentsSousTaches
						.entrySet()) {
					List<String> listDocValue = new ArrayList<String>();
					List<Checklist_Consigne_Documents> listDoc = ChecklistBaseSousTacheDatabaseService
							.getAllDocumentsForSousTache(cbst.getIdConsigne()
									.getId());
					for (Checklist_Consigne_Documents document : listDoc)
						listDocValue.add(document.getDocument());
					if (!compareList(listDocValue, doc.getValue())) {
						historique += "documentation, ";
					}
				}
				historique += "dans la sous tache " + in.getKey() + ", ";
			}

			// modification en base
			ChecklistTachesDatabaseService.modify(
					selectRow,
					nom,
					environnement,
					DateService.strToDate(dateDebut),
					etat,
					criticite,
					typeDemande,
					DateService.getTimeFromString(heureReception),
					nomEmetteur,
					descriptionMail,
					descriptionObs,
					numeroObs,
					// argument sur les dates à modifier si les frequences sont
					// =
					typeFrequenceDate,
					oldTypeFrequenceDate,
					listChecklistJourToAdd,
					listChecklistJourToModify,
					listChecklistJourToDelete,
					listChecklistMensuelToAdd,
					listChecklistMensuelToDelete,
					listChecklistPariteToAdd,
					listChecklistPariteToDelete,
					listChecklistFerieVeilleToAdd,
					listChecklistFerieJourToAdd,
					listChecklistFerieLendemainToAdd,
					listChecklistFerieToDelete,
					listChecklistExceptionnelToAdd,
					listChecklistExceptionnelToDelete,
					// argument sur les dates à modifier si les frequences sont
					// !=
					f1_lundi, f1_mardi, f1_mercredi, f1_jeudi, f1_vendredi,
					f1_samedi, f1_dimanche, f1_notFerie, f2_1er, f2_2eme,
					f2_3eme, f2_4eme, f2_der, f2_lundi, f2_mardi, f2_mercredi,
					f2_jeudi, f2_vendredi, f2_samedi, f2_dimanche, f2_jour,
					f2_notFerie, f3_pair, f3_impair, f3_lundi, f3_mardi,
					f3_mercredi, f3_jeudi,
					f3_vendredi,
					f3_samedi,
					f3_dimanche,
					f3_notFerie,
					veilleFerie,
					jourFerie,
					lendemainFerie,
					listJoursExceptionnels,

					// argument sur les horaires
					typeFrequenceHeure, oldTypeFrequenceHeure, oldHeureDebut,
					oldHeureFin, oldFrequence, heureDebut, heureFin,
					frequence,
					listHeuresPonctuelles,
					DateService.getTimeFromString(heureDebut),
					DateService.getTimeFromString(heureFin),
					DateService.getTimeFromString(frequence),

					// arguments pour les sous taches
					listSousTacheToAdd, listSousTacheToUpdate,
					listSousTacheToDelete, listDocumentToAdd,
					documentsSousTaches);

			// mise à jour de la checklist du jour
			// Ajout des nouveaux cheks
			Date today = DateService.getTodayWithoutHour();
			boolean jourCompatible = false;
			boolean dateDebutCompatible = false;
		
			System.out.println(today);
			
		if (ChecklistDatabaseService.isCreated(today)) {
			// test si la date de debut est compatible
			if (!DateService.strToDate(dateDebut).after(today)) {
				dateDebutCompatible = true;
			}

			// Exceptionnel
			if (PilotageConstants.DATE_FREQ_EXCEPTIONNELLE
					.equals(typeFrequenceDate)) {
				for (Date dateExcep : listJoursExceptionnels) {
					if (today.equals(dateExcep)) {
						jourCompatible = true;
						break;
					}
				}
			}
			// ferie
			else if (PilotageConstants.DATE_FREQ_FERIE
					.equals(typeFrequenceDate)) {
				Integer veilleFerieID = DateService.getJourFerie(today,
						PilotageConstants.VEILLE_FERIE);
				Integer jourFerieID = DateService.getJourFerie(today,
						PilotageConstants.JOUR_FERIE);
				Integer lendemainFerieID = DateService.getJourFerie(today,
						PilotageConstants.LENDEMAIN_FERIE);

				if (veilleFerie != null) {
					for (Integer elementVeilleFerie : veilleFerie) {
						if (elementVeilleFerie.equals(veilleFerieID)) {
							jourCompatible = true;
							break;
						}
					}
				}

				if (!jourCompatible && jourFerie != null) {
					for (Integer elementJourFerie : jourFerie) {
						if (elementJourFerie.equals(jourFerieID)) {
							jourCompatible = true;
							break;
						}
					}
				}

				if (!jourCompatible && lendemainFerie != null) {
					for (Integer elementLendemainFerie : lendemainFerie) {
						if (elementLendemainFerie.equals(lendemainFerieID)) {
							jourCompatible = true;
							break;
						}
					}
				}
			}
			// semaines paires/impaires
			else if (PilotageConstants.DATE_FREQ_PAIR_IMPAIR
					.equals(typeFrequenceDate)) {
				Integer parite = DateService.getWeekParite(today);
				if ((parite.equals(0) && f3_pair)
						|| (parite.equals(1) && f3_impair)) {
					Integer jourToday = DateService.getJour(today);
					if ((jourToday.equals(PilotageConstants.LUNDI) && f3_lundi)
							|| (jourToday.equals(PilotageConstants.MARDI) && f3_mardi)
							|| (jourToday
									.equals(PilotageConstants.MERCREDI) && f3_mercredi)
							|| (jourToday.equals(PilotageConstants.JEUDI) && f3_jeudi)
							|| (jourToday
									.equals(PilotageConstants.VENDREDI) && f3_vendredi)
							|| (jourToday.equals(PilotageConstants.SAMEDI) && f3_samedi)
							|| (jourToday
									.equals(PilotageConstants.DIMANCHE) && f3_dimanche)) {
						jourCompatible = true;
					}
				}
			}
			// frequence mensuelle
			else if (PilotageConstants.DATE_FREQ_MENSUELLE
					.equals(typeFrequenceDate)) {
				Integer place = DateService.getPlaceOfDayInMonth(today);
				Boolean lastPlace = DateService
						.isLastPlaceOfDayInMonth(today);

				if ((place.equals(1) && f2_1er)
						|| (place.equals(2) && f2_2eme)
						|| (place.equals(3) && f2_3eme)
						|| (place.equals(4) && f2_4eme)
						|| (lastPlace && f2_der)) {
					Integer jourToday = DateService.getJour(today);
					if ((jourToday.equals(PilotageConstants.LUNDI) && f2_lundi)
							|| (jourToday.equals(PilotageConstants.MARDI) && f2_mardi)
							|| (jourToday.equals(PilotageConstants.MERCREDI) && f2_mercredi)
							|| (jourToday.equals(PilotageConstants.JEUDI) && f2_jeudi)
							|| (jourToday
									.equals(PilotageConstants.VENDREDI) && f2_vendredi)
							|| (jourToday.equals(PilotageConstants.SAMEDI) && f2_samedi)
							|| (jourToday
									.equals(PilotageConstants.DIMANCHE) && f2_dimanche)) {
						jourCompatible = true;
					}
				}
			}
			// frequence hebdo
			else if (PilotageConstants.DATE_FREQ_HEBDO
					.equals(typeFrequenceDate)) {
				Integer jourToday = DateService.getJour(today);
				if ((jourToday.equals(PilotageConstants.LUNDI) && f1_lundi)
						|| (jourToday.equals(PilotageConstants.MARDI) && f1_mardi)
						|| (jourToday.equals(PilotageConstants.MERCREDI) && f1_mercredi)
						|| (jourToday.equals(PilotageConstants.JEUDI) && f1_jeudi)
						|| (jourToday.equals(PilotageConstants.VENDREDI) && f1_vendredi)
						|| (jourToday.equals(PilotageConstants.SAMEDI) && f1_samedi)
						|| (jourToday.equals(PilotageConstants.DIMANCHE) && f1_dimanche)) {
					jourCompatible = true;
				}
			}
			
			if (dateDebutCompatible && jourCompatible) {
			Date now = new Date();
			now = DateService.getTimeFromString(DateService.getTime(now,
					DateService.pt2));
			List<Checklist_Horaire> listChecklistHoraireToAdd = new ArrayList<Checklist_Horaire>();
			List<Integer> listSousTachesID = new ArrayList<Integer>();
			List<Checklist_Horaire> list_Horaires = ChecklistHoraireDatabaseService
					.getListFromBase(selectRow);
			for (Checklist_Horaire horairetoAdd : list_Horaires) {
				Date dateExecution = horairetoAdd.getHoraire();
				if (dateExecution.after(now)) {
					listChecklistHoraireToAdd.add(horairetoAdd);
				}
			}
			List<Checklist_Base_Soustache> list_Soustaches = ChecklistBaseSousTacheDatabaseService
					.getListFromBase(selectRow);
			for (Checklist_Base_Soustache checklistSousTache : list_Soustaches)
				listSousTachesID.add(checklistSousTache.getId());
			Checklist_Base base = ChecklistBaseDatabaseService.get(selectRow);
			
			if (!listSousTachesID.isEmpty()
					&& !listChecklistHoraireToAdd.isEmpty()&& base.getEtat().getId() != 2)
				ChecklistDatabaseService.addInChecklistOfTheDay(today,
						listSousTachesID, listChecklistHoraireToAdd);
			}
		}
			
			if (!typeFrequenceDate.equals(oldTypeFrequenceDate)) {
				historique += "de type de fréquence date, ";
			} else {
				// fréquence exceptionnelle
				if (typeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_EXCEPTIONNELLE)) {
					if (listChecklistExceptionnelToAdd != null) {
						for (Date d : listChecklistExceptionnelToAdd) {
							historique += "ajout de jour exceptionnel "
									+ ChecklistExceptionnelDatabaseService
											.getId(cb, d) + ", ";
						}
					}
					if (listChecklistExceptionnelToDelete != null) {
						for (Integer in : listChecklistExceptionnelToDelete) {
							historique += "suppression de jour exceptionnel "
									+ in + ", ";
						}
					}
				}

				// fréquence fériée
				else if (typeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_FERIE)) {
					if (listChecklistFerieToDelete != null) {
						for (Integer in : listChecklistFerieToDelete) {
							historique += "suppression de jour férié " + in
									+ ", ";
						}
					}
					if (listChecklistFerieVeilleToAdd != null) {
						for (Integer in : listChecklistFerieVeilleToAdd) {
							historique += "ajout de la veille "
									+ ChecklistFerieDatabaseService.getId(cb,
											in, PilotageConstants.VEILLE_FERIE)
									+ ", ";
						}
					}
					if (listChecklistFerieJourToAdd != null) {
						for (Integer in : listChecklistFerieJourToAdd) {
							historique += "ajout du jour "
									+ ChecklistFerieDatabaseService.getId(cb,
											in, PilotageConstants.JOUR_FERIE)
									+ ", ";
						}
					}
					if (listChecklistFerieLendemainToAdd != null) {
						for (Integer in : listChecklistFerieLendemainToAdd) {
							historique += "ajout de lendemain "
									+ ChecklistFerieDatabaseService.getId(cb,
											in,
											PilotageConstants.LENDEMAIN_FERIE)
									+ ", ";
						}
					}
				}

				// fréquence parité
				else if (typeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_PAIR_IMPAIR)) {
					if (listChecklistPariteToAdd != null) {
						for (Integer in : listChecklistPariteToAdd) {
							historique += "ajout de parité "
									+ ChecklistPariteDatabaseService.getId(cb,
											in) + ", ";
						}
					}
					if (listChecklistPariteToDelete != null) {
						for (Integer in : listChecklistPariteToDelete) {
							historique += "suppression de parité " + in + ", ";
						}
					}
					if (listChecklistJourToAdd != null) {
						for (Integer in : listChecklistJourToAdd) {
							historique += "ajout de jour "
									+ ChecklistJourDatabaseService
											.getId(cb, in) + ", ";
						}
					}
					if (listChecklistJourToModify != null) {
						for (Integer in : listChecklistJourToModify) {
							historique += "modification de jour " + in + ", ";
						}
					}
					if (listChecklistJourToDelete != null) {
						for (Integer in : listChecklistJourToDelete) {
							historique += "suppression de jour " + in + ", ";
						}
					}
				}

				// fréquence mensuelle
				else if (typeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_MENSUELLE)) {
					if (listChecklistMensuelToAdd != null) {
						for (Integer in : listChecklistMensuelToAdd) {
							historique += "ajout de mensuel "
									+ ChecklistMensuelDatabaseService.getId(cb,
											in) + ", ";
						}
					}
					if (listChecklistMensuelToDelete != null) {
						for (Integer in : listChecklistMensuelToDelete) {
							historique += "suppression de mensuel " + in + ", ";
						}
					}
					if (listChecklistJourToAdd != null) {
						for (Integer in : listChecklistJourToAdd) {
							historique += "ajout de jour "
									+ ChecklistJourDatabaseService
											.getId(cb, in) + ", ";
						}
					}
					if (listChecklistJourToModify != null) {
						for (Integer in : listChecklistJourToModify) {
							historique += "modification de jour " + in + ", ";
						}
					}
					if (listChecklistJourToDelete != null) {
						for (Integer in : listChecklistJourToDelete) {
							historique += "suppression de jour " + in + ", ";
						}
					}
				}

				// fréquence hebdo
				else if (typeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_HEBDO)) {
					if (listChecklistJourToAdd != null) {
						for (Integer in : listChecklistJourToAdd) {
							historique += "ajout de jour "
									+ ChecklistJourDatabaseService
											.getId(cb, in) + ", ";
						}
					}
					if (listChecklistJourToModify != null) {
						for (Integer in : listChecklistJourToModify) {
							historique += "modification de jour " + in + ", ";
						}
					}
					if (listChecklistJourToDelete != null) {
						for (Integer in : listChecklistJourToDelete) {
							historique += "suppression de jour " + in + ", ";
						}
					}
				}
			}
			if (!typeFrequenceHeure.equals(oldTypeFrequenceHeure)) {
				historique += "de type de fréquence heure, ";
			} else {
				if (typeFrequenceHeure
						.equals(PilotageConstants.HEURE_FREQ_REGULIER)) {
					if (!heureDebut.equals(oldHeureDebut)) {
						historique += "de heure debut, ";
					}
					if (!heureFin.equals(oldHeureFin)) {
						historique += "de heure fin, ";
					}
					if (!frequence.equals(oldFrequence)) {
						historique += "de frequence, ";
					}
				} else {
					for (Integer in : listHoraireToDelete) {
						historique += "suppression d'horaire " + in + ", ";
					}
					for (Date d : listHoraireToAdd) {
						historique += "ajout d'horaire "
								+ ChecklistHoraireDatabaseService.getId(cb, d)
								+ ", ";
					}
				}
			}
			if (listSousTacheToDelete != null) {
				for (Integer in : listSousTacheToDelete) {
					historique += "suppression de sous tache " + in + ", ";
				}
			}
			if (listSousTacheToAdd != null) {
				for (List<String> in : listSousTacheToAdd) {
					historique += "ajout de sous tache "
							+ ChecklistBaseSousTacheDatabaseService.getId(in)
							+ ", ";
				}
			}
			HistoriqueDatabaseService.create(null, MessageFormat.format(
					getText("historique.checklist.tache.modification"),
					new Object[] { historique, selectRow }), (Users) session
					.get(PilotageConstants.USER_LOGGED),
					PilotageConstants.HISTORIQUE_MODULE_CHECKLIST);

			info = MessageFormat.format(
					getText("checklist.tache.modification.valide"), nom);
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'une tache - ", e);

			return ERROR;
		}
	}

	/**
	 * Recupération des modifications des dates faites sur la tache hebdo
	 * 
	 * @param listChecklistJourToAdd
	 * @param listChecklistJourToModify
	 * @param listChecklistJourToDelete
	 */
	private void recuperationDateModificationsHebdomadaires(
			List<Integer> listChecklistJourToAdd,
			List<Integer> listChecklistJourToModify,
			List<Integer> listChecklistJourToDelete) {
		// jours
		List<Checklist_Jour> listJoursEnBase = ChecklistJourDatabaseService
				.getListFromBase(selectRow);
		boolean lundiAlreadyInBase = false;
		boolean mardiAlreadyInBase = false;
		boolean mercrediAlreadyInBase = false;
		boolean jeudiAlreadyInBase = false;
		boolean vendrediAlreadyInBase = false;
		boolean samediAlreadyInBase = false;
		boolean dimancheAlreadyInBase = false;
		boolean lundiToModify = false;
		boolean mardiToModify = false;
		boolean mercrediToModify = false;
		boolean jeudiToModify = false;
		boolean vendrediToModify = false;
		boolean samediToModify = false;
		boolean dimancheToModify = false;
		for (Checklist_Jour jourEnBase : listJoursEnBase) {
			Integer jourDeLaSemaine = jourEnBase.getJour();
			boolean ferie = jourEnBase.isFerie();
			if (jourDeLaSemaine == PilotageConstants.LUNDI) {
				if (!f1_lundi) {
					listChecklistJourToDelete.add(jourEnBase.getId());
				} else if (ferie == f1_notFerie) {
					listChecklistJourToModify.add(jourEnBase.getId());
					lundiToModify = true;
				} else {
					lundiAlreadyInBase = true;
				}
			} else if (jourDeLaSemaine == PilotageConstants.MARDI) {
				if (!f1_mardi) {
					listChecklistJourToDelete.add(jourEnBase.getId());
				} else if (ferie == f1_notFerie) {
					listChecklistJourToModify.add(jourEnBase.getId());
					mardiToModify = true;
				} else {
					mardiAlreadyInBase = true;
				}
			} else if (jourDeLaSemaine == PilotageConstants.MERCREDI) {
				if (!f1_mercredi) {
					listChecklistJourToDelete.add(jourEnBase.getId());
				} else if (ferie == f1_notFerie) {
					listChecklistJourToModify.add(jourEnBase.getId());
					mercrediToModify = true;
				} else {
					mercrediAlreadyInBase = true;
				}
			} else if (jourDeLaSemaine == PilotageConstants.JEUDI) {
				if (!f1_jeudi) {
					listChecklistJourToDelete.add(jourEnBase.getId());
				} else if (ferie == f1_notFerie) {
					listChecklistJourToModify.add(jourEnBase.getId());
					jeudiToModify = true;
				} else {
					jeudiAlreadyInBase = true;
				}
			} else if (jourDeLaSemaine == PilotageConstants.VENDREDI) {
				if (!f1_vendredi) {
					listChecklistJourToDelete.add(jourEnBase.getId());
				} else if (ferie == f1_notFerie) {
					listChecklistJourToModify.add(jourEnBase.getId());
					vendrediToModify = true;
				} else {
					vendrediAlreadyInBase = true;
				}
			} else if (jourDeLaSemaine == PilotageConstants.SAMEDI) {
				if (!f1_samedi) {
					listChecklistJourToDelete.add(jourEnBase.getId());
				} else if (ferie == f1_notFerie) {
					listChecklistJourToModify.add(jourEnBase.getId());
					samediToModify = true;
				} else {
					samediAlreadyInBase = true;
				}
			} else if (jourDeLaSemaine == PilotageConstants.DIMANCHE) {
				if (!f1_dimanche) {
					listChecklistJourToDelete.add(jourEnBase.getId());
				} else if (ferie == f1_notFerie) {
					listChecklistJourToModify.add(jourEnBase.getId());
					dimancheToModify = true;
				} else {
					dimancheAlreadyInBase = true;
				}
			}
		}

		if (f1_lundi && !lundiAlreadyInBase && !lundiToModify)
			listChecklistJourToAdd.add(PilotageConstants.LUNDI);
		if (f1_mardi && !mardiAlreadyInBase && !mardiToModify)
			listChecklistJourToAdd.add(PilotageConstants.MARDI);
		if (f1_mercredi && !mercrediAlreadyInBase && !mercrediToModify)
			listChecklistJourToAdd.add(PilotageConstants.MERCREDI);
		if (f1_jeudi && !jeudiAlreadyInBase && !jeudiToModify)
			listChecklistJourToAdd.add(PilotageConstants.JEUDI);
		if (f1_vendredi && !vendrediAlreadyInBase && !vendrediToModify)
			listChecklistJourToAdd.add(PilotageConstants.VENDREDI);
		if (f1_samedi && !samediAlreadyInBase && !samediToModify)
			listChecklistJourToAdd.add(PilotageConstants.SAMEDI);
		if (f1_dimanche && !dimancheAlreadyInBase && !dimancheToModify)
			listChecklistJourToAdd.add(PilotageConstants.DIMANCHE);
	}

	/**
	 * Recupération des modifications des dates faites sur la tache mensuelle
	 * 
	 * @param listChecklistMensuelToAdd
	 * @param listChecklistMensuelToDelete
	 * @param listChecklistJourToAdd
	 * @param listChecklistJourToModify
	 * @param listChecklistJourToDelete
	 */
	private void recuperationDateModificationsMensuelles(
			List<Integer> listChecklistMensuelToAdd,
			List<Integer> listChecklistMensuelToDelete,
			List<Integer> listChecklistJourToAdd,
			List<Integer> listChecklistJourToModify,
			List<Integer> listChecklistJourToDelete) {
		// mensuel
		List<Checklist_Mensuel> listChecklistMensuelEnBase = ChecklistMensuelDatabaseService
				.getListFromBase(selectRow);
		boolean premierAlreadyInBase = false;
		boolean deuxiemeAlreadyInBase = false;
		boolean troisiemeAlreadyInBase = false;
		boolean quatriemeAlreadyInBase = false;
		boolean dernierAlreadyInBase = false;
		for (Checklist_Mensuel mensuelEnBase : listChecklistMensuelEnBase) {
			if (mensuelEnBase.getMensuel() == 1) {
				if (!f2_1er)
					listChecklistMensuelToDelete.add(mensuelEnBase.getId());
				else
					premierAlreadyInBase = true;
			} else if (mensuelEnBase.getMensuel() == 2) {
				if (!f2_2eme)
					listChecklistMensuelToDelete.add(mensuelEnBase.getId());
				else
					deuxiemeAlreadyInBase = true;
			} else if (mensuelEnBase.getMensuel() == 3) {
				if (!f2_3eme)
					listChecklistMensuelToDelete.add(mensuelEnBase.getId());
				else
					troisiemeAlreadyInBase = true;
			} else if (mensuelEnBase.getMensuel() == 4) {
				if (!f2_4eme)
					listChecklistMensuelToDelete.add(mensuelEnBase.getId());
				else
					quatriemeAlreadyInBase = true;
			} else if (mensuelEnBase.getMensuel() == 5) {
				if (!f2_der)
					listChecklistMensuelToDelete.add(mensuelEnBase.getId());
				else
					dernierAlreadyInBase = true;
			}
		}

		if (f2_1er && !premierAlreadyInBase)
			listChecklistMensuelToAdd.add(1);
		if (f2_2eme && !deuxiemeAlreadyInBase)
			listChecklistMensuelToAdd.add(2);
		if (f2_3eme && !troisiemeAlreadyInBase)
			listChecklistMensuelToAdd.add(3);
		if (f2_4eme && !quatriemeAlreadyInBase)
			listChecklistMensuelToAdd.add(4);
		if (f2_der && !dernierAlreadyInBase)
			listChecklistMensuelToAdd.add(5);

		// jours
		List<Checklist_Jour> listJoursEnBase = ChecklistJourDatabaseService
				.getListFromBase(selectRow);
		boolean lundiAlreadyInBase = false;
		boolean mardiAlreadyInBase = false;
		boolean mercrediAlreadyInBase = false;
		boolean jeudiAlreadyInBase = false;
		boolean vendrediAlreadyInBase = false;
		boolean samediAlreadyInBase = false;
		boolean dimancheAlreadyInBase = false;
		boolean lundiToModify = false;
		boolean mardiToModify = false;
		boolean mercrediToModify = false;
		boolean jeudiToModify = false;
		boolean vendrediToModify = false;
		boolean samediToModify = false;
		boolean dimancheToModify = false;
		for (Checklist_Jour jourEnBase : listJoursEnBase) {
			Integer jourDeLaSemaine = jourEnBase.getJour();
			boolean ferie = jourEnBase.isFerie();
			if (jourDeLaSemaine == PilotageConstants.LUNDI) {
				if (!f2_lundi) {
					listChecklistJourToDelete.add(jourEnBase.getId());
				} else if (ferie == f2_notFerie) {
					listChecklistJourToModify.add(jourEnBase.getId());
					lundiToModify = true;
				} else {
					lundiAlreadyInBase = true;
				}
			} else if (jourDeLaSemaine == PilotageConstants.MARDI) {
				if (!f2_mardi) {
					listChecklistJourToDelete.add(jourEnBase.getId());
				} else if (ferie == f2_notFerie) {
					listChecklistJourToModify.add(jourEnBase.getId());
					mardiToModify = true;
				} else {
					mardiAlreadyInBase = true;
				}
			} else if (jourDeLaSemaine == PilotageConstants.MERCREDI) {
				if (!f2_mercredi) {
					listChecklistJourToDelete.add(jourEnBase.getId());
				} else if (ferie == f2_notFerie) {
					listChecklistJourToModify.add(jourEnBase.getId());
					mercrediToModify = true;
				} else {
					mercrediAlreadyInBase = true;
				}
			} else if (jourDeLaSemaine == PilotageConstants.JEUDI) {
				if (!f2_jeudi) {
					listChecklistJourToDelete.add(jourEnBase.getId());
				} else if (ferie == f2_notFerie) {
					listChecklistJourToModify.add(jourEnBase.getId());
					jeudiToModify = true;
				} else {
					jeudiAlreadyInBase = true;
				}
			} else if (jourDeLaSemaine == PilotageConstants.VENDREDI) {
				if (!f2_vendredi) {
					listChecklistJourToDelete.add(jourEnBase.getId());
				} else if (ferie == f2_notFerie) {
					listChecklistJourToModify.add(jourEnBase.getId());
					vendrediToModify = true;
				} else {
					vendrediAlreadyInBase = true;
				}
			} else if (jourDeLaSemaine == PilotageConstants.SAMEDI) {
				if (!f2_samedi) {
					listChecklistJourToDelete.add(jourEnBase.getId());
				} else if (ferie == f2_notFerie) {
					listChecklistJourToModify.add(jourEnBase.getId());
					samediToModify = true;
				} else {
					samediAlreadyInBase = true;
				}
			} else if (jourDeLaSemaine == PilotageConstants.DIMANCHE) {
				if (!f2_dimanche) {
					listChecklistJourToDelete.add(jourEnBase.getId());
				} else if (ferie == f2_notFerie) {
					listChecklistJourToModify.add(jourEnBase.getId());
					dimancheToModify = true;
				} else {
					dimancheAlreadyInBase = true;
				}
			}
		}

		if (f2_lundi && !lundiAlreadyInBase && !lundiToModify)
			listChecklistJourToAdd.add(PilotageConstants.LUNDI);
		if (f2_mardi && !mardiAlreadyInBase && !mardiToModify)
			listChecklistJourToAdd.add(PilotageConstants.MARDI);
		if (f2_mercredi && !mercrediAlreadyInBase && !mercrediToModify)
			listChecklistJourToAdd.add(PilotageConstants.MERCREDI);
		if (f2_jeudi && !jeudiAlreadyInBase && !jeudiToModify)
			listChecklistJourToAdd.add(PilotageConstants.JEUDI);
		if (f2_vendredi && !vendrediAlreadyInBase && !vendrediToModify)
			listChecklistJourToAdd.add(PilotageConstants.VENDREDI);
		if (f2_samedi && !samediAlreadyInBase && !samediToModify)
			listChecklistJourToAdd.add(PilotageConstants.SAMEDI);
		if (f2_dimanche && !dimancheAlreadyInBase && !dimancheToModify)
			listChecklistJourToAdd.add(PilotageConstants.DIMANCHE);
	}

	/**
	 * Recupération des modifications des dates faites sur la tache
	 * paires/impaires
	 * 
	 * @param listChecklistPariteToAdd
	 * @param listChecklistPariteToDelete
	 * @param listChecklistJourToAdd
	 * @param listChecklistJourToModify
	 * @param listChecklistJourToDelete
	 */
	private void recuperationDateModificationsParites(
			List<Integer> listChecklistPariteToAdd,
			List<Integer> listChecklistPariteToDelete,
			List<Integer> listChecklistJourToAdd,
			List<Integer> listChecklistJourToModify,
			List<Integer> listChecklistJourToDelete) {
		// parité
		List<Checklist_Parite> listChecklistPariteEnBase = ChecklistPariteDatabaseService
				.getListFromBase(selectRow);
		boolean pairAlreadyInBase = false;
		boolean impairAlreadyInBase = false;
		for (Checklist_Parite pariteEnBase : listChecklistPariteEnBase) {
			if (pariteEnBase.getParite() == PilotageConstants.PAIR) {
				if (!f3_pair)
					listChecklistPariteToDelete.add(pariteEnBase.getId());
				else
					pairAlreadyInBase = true;
			} else if (pariteEnBase.getParite() == PilotageConstants.IMPAIR) {
				if (!f3_impair)
					listChecklistPariteToDelete.add(pariteEnBase.getId());
				else
					impairAlreadyInBase = true;
			}
		}

		if (f3_pair && !pairAlreadyInBase)
			listChecklistPariteToAdd.add(PilotageConstants.PAIR);
		if (f3_impair && !impairAlreadyInBase)
			listChecklistPariteToAdd.add(PilotageConstants.IMPAIR);

		// jours
		List<Checklist_Jour> listJoursEnBase = ChecklistJourDatabaseService
				.getListFromBase(selectRow);
		boolean lundiAlreadyInBase = false;
		boolean mardiAlreadyInBase = false;
		boolean mercrediAlreadyInBase = false;
		boolean jeudiAlreadyInBase = false;
		boolean vendrediAlreadyInBase = false;
		boolean samediAlreadyInBase = false;
		boolean dimancheAlreadyInBase = false;
		boolean lundiToModify = false;
		boolean mardiToModify = false;
		boolean mercrediToModify = false;
		boolean jeudiToModify = false;
		boolean vendrediToModify = false;
		boolean samediToModify = false;
		boolean dimancheToModify = false;
		for (Checklist_Jour jourEnBase : listJoursEnBase) {
			Integer jourDeLaSemaine = jourEnBase.getJour();
			boolean ferie = jourEnBase.isFerie();
			if (jourDeLaSemaine == PilotageConstants.LUNDI) {
				if (!f3_lundi) {
					listChecklistJourToDelete.add(jourEnBase.getId());
				} else if (ferie == f3_notFerie) {
					listChecklistJourToModify.add(jourEnBase.getId());
					lundiToModify = true;
				} else {
					lundiAlreadyInBase = true;
				}
			} else if (jourDeLaSemaine == PilotageConstants.MARDI) {
				if (!f3_mardi) {
					listChecklistJourToDelete.add(jourEnBase.getId());
				} else if (ferie == f3_notFerie) {
					listChecklistJourToModify.add(jourEnBase.getId());
					mardiToModify = true;
				} else {
					mardiAlreadyInBase = true;
				}
			} else if (jourDeLaSemaine == PilotageConstants.MERCREDI) {
				if (!f3_mercredi) {
					listChecklistJourToDelete.add(jourEnBase.getId());
				} else if (ferie == f3_notFerie) {
					listChecklistJourToModify.add(jourEnBase.getId());
					mercrediToModify = true;
				} else {
					mercrediAlreadyInBase = true;
				}
			} else if (jourDeLaSemaine == PilotageConstants.JEUDI) {
				if (!f3_jeudi) {
					listChecklistJourToDelete.add(jourEnBase.getId());
				} else if (ferie == f3_notFerie) {
					listChecklistJourToModify.add(jourEnBase.getId());
					jeudiToModify = true;
				} else {
					jeudiAlreadyInBase = true;
				}
			} else if (jourDeLaSemaine == PilotageConstants.VENDREDI) {
				if (!f3_vendredi) {
					listChecklistJourToDelete.add(jourEnBase.getId());
				} else if (ferie == f3_notFerie) {
					listChecklistJourToModify.add(jourEnBase.getId());
					vendrediToModify = true;
				} else {
					vendrediAlreadyInBase = true;
				}
			} else if (jourDeLaSemaine == PilotageConstants.SAMEDI) {
				if (!f3_samedi) {
					listChecklistJourToDelete.add(jourEnBase.getId());
				} else if (ferie == f3_notFerie) {
					listChecklistJourToModify.add(jourEnBase.getId());
					samediToModify = true;
				} else {
					samediAlreadyInBase = true;
				}
			} else if (jourDeLaSemaine == PilotageConstants.DIMANCHE) {
				if (!f3_dimanche) {
					listChecklistJourToDelete.add(jourEnBase.getId());
				} else if (ferie == f3_notFerie) {
					listChecklistJourToModify.add(jourEnBase.getId());
					dimancheToModify = true;
				} else {
					dimancheAlreadyInBase = true;
				}
			}
		}

		if (f3_lundi && !lundiAlreadyInBase && !lundiToModify)
			listChecklistJourToAdd.add(PilotageConstants.LUNDI);
		if (f3_mardi && !mardiAlreadyInBase && !mardiToModify)
			listChecklistJourToAdd.add(PilotageConstants.MARDI);
		if (f3_mercredi && !mercrediAlreadyInBase && !mercrediToModify)
			listChecklistJourToAdd.add(PilotageConstants.MERCREDI);
		if (f3_jeudi && !jeudiAlreadyInBase && !jeudiToModify)
			listChecklistJourToAdd.add(PilotageConstants.JEUDI);
		if (f3_vendredi && !vendrediAlreadyInBase && !vendrediToModify)
			listChecklistJourToAdd.add(PilotageConstants.VENDREDI);
		if (f3_samedi && !samediAlreadyInBase && !samediToModify)
			listChecklistJourToAdd.add(PilotageConstants.SAMEDI);
		if (f3_dimanche && !dimancheAlreadyInBase && !dimancheToModify)
			listChecklistJourToAdd.add(PilotageConstants.DIMANCHE);
	}

	/**
	 * Recupération des modifications des dates faites sur la tache fériées
	 * 
	 * @param listChecklistFerieVeilleToAdd
	 * @param listChecklistFerieJourToAdd
	 * @param listChecklistFerieLendemainToAdd
	 * @param listChecklistFerieToDelete
	 */
	private void recuperationDateModificationsFeries(
			List<Integer> listChecklistFerieVeilleToAdd,
			List<Integer> listChecklistFerieJourToAdd,
			List<Integer> listChecklistFerieLendemainToAdd,
			List<Integer> listChecklistFerieToDelete) {
		List<Checklist_Ferie> listVeillesFerieesEnBase = ChecklistFerieDatabaseService
				.getListFromBase(selectRow, PilotageConstants.VEILLE_FERIE);
		List<Checklist_Ferie> listJoursFeriesEnBase = ChecklistFerieDatabaseService
				.getListFromBase(selectRow, PilotageConstants.JOUR_FERIE);
		List<Checklist_Ferie> listLendemainsFeriesEnBase = ChecklistFerieDatabaseService
				.getListFromBase(selectRow, PilotageConstants.LENDEMAIN_FERIE);

		if (veilleFerie == null)
			veilleFerie = new Integer[0];
		if (jourFerie == null)
			jourFerie = new Integer[0];
		if (lendemainFerie == null)
			lendemainFerie = new Integer[0];

		// veilles à supprimer
		for (Checklist_Ferie veilleEnBase : listVeillesFerieesEnBase) {
			Integer idFerie = veilleEnBase.getIdJourFerie().getId();
			Boolean stillOnScreen = false;
			for (Integer veilleIDInScreen : veilleFerie) {
				if (veilleIDInScreen.equals(idFerie)) {
					stillOnScreen = true;
					break;
				}
			}

			if (!stillOnScreen)
				listChecklistFerieToDelete.add(veilleEnBase.getId());
		}
		// veilles à ajouter
		for (Integer veilleIDInScreen : veilleFerie) {
			Boolean alreadyInBase = false;
			for (Checklist_Ferie veilleEnBase : listVeillesFerieesEnBase) {
				if (veilleIDInScreen.equals(veilleEnBase.getIdJourFerie()
						.getId())) {
					alreadyInBase = true;
					break;
				}
			}

			if (!alreadyInBase)
				listChecklistFerieVeilleToAdd.add(veilleIDInScreen);
		}

		// jours à supprimer
		for (Checklist_Ferie jourEnBase : listJoursFeriesEnBase) {
			Integer idFerie = jourEnBase.getIdJourFerie().getId();
			Boolean stillOnScreen = false;
			for (Integer jourIDInScreen : jourFerie) {
				if (jourIDInScreen.equals(idFerie)) {
					stillOnScreen = true;
					break;
				}
			}

			if (!stillOnScreen)
				listChecklistFerieToDelete.add(jourEnBase.getId());
		}
		// jours à ajouter
		for (Integer jourIDInScreen : jourFerie) {
			Boolean alreadyInBase = false;
			for (Checklist_Ferie jourEnBase : listJoursFeriesEnBase) {
				if (jourIDInScreen.equals(jourEnBase.getIdJourFerie().getId())) {
					alreadyInBase = true;
					break;
				}
			}

			if (!alreadyInBase)
				listChecklistFerieJourToAdd.add(jourIDInScreen);
		}

		// lendemains à supprimer
		for (Checklist_Ferie lendemainEnBase : listLendemainsFeriesEnBase) {
			Integer idFerie = lendemainEnBase.getIdJourFerie().getId();
			Boolean stillOnScreen = false;
			for (Integer lendemainIDInScreen : lendemainFerie) {
				if (lendemainIDInScreen.equals(idFerie)) {
					stillOnScreen = true;
					break;
				}
			}

			if (!stillOnScreen)
				listChecklistFerieToDelete.add(lendemainEnBase.getId());
		}
		// lendemains à ajouter
		for (Integer lendemainIDInScreen : lendemainFerie) {
			Boolean alreadyInBase = false;
			for (Checklist_Ferie lendemainEnBase : listLendemainsFeriesEnBase) {
				if (lendemainIDInScreen.equals(lendemainEnBase.getIdJourFerie()
						.getId())) {
					alreadyInBase = true;
					break;
				}
			}

			if (!alreadyInBase)
				listChecklistFerieLendemainToAdd.add(lendemainIDInScreen);
		}
	}

	/**
	 * Recupération des modifications des dates faites sur la tache
	 * exceptionnelle
	 * 
	 * @param listJoursExceptionnels
	 * @param listChecklistExceptionnelToAdd
	 * @param listChecklistExceptionnelToDelete
	 */
	private void recuperationDateModificationsExceptionnelles(
			List<Date> listJoursExceptionnels,
			List<Date> listChecklistExceptionnelToAdd,
			List<Integer> listChecklistExceptionnelToDelete) {
		List<Checklist_Exceptionnel> listJoursExceptionnelsEnBase = ChecklistExceptionnelDatabaseService
				.getListFromBase(selectRow);
		for (Checklist_Exceptionnel jourExceptionnelEnBase : listJoursExceptionnelsEnBase) {
			Date dateEnBase = jourExceptionnelEnBase.getJour();
			boolean stillOnScreen = false;
			for (Date dateSurEcran : listJoursExceptionnels) {
				if (dateSurEcran.equals(dateEnBase)) {
					stillOnScreen = true;
					break;
				}
			}

			if (!stillOnScreen)
				listChecklistExceptionnelToDelete.add(jourExceptionnelEnBase
						.getId());
		}
		for (Date dateSurEcran : listJoursExceptionnels) {
			boolean alreadyInBase = false;
			for (Checklist_Exceptionnel jourExceptionnelEnBase : listJoursExceptionnelsEnBase) {
				if (dateSurEcran.equals(jourExceptionnelEnBase.getJour())) {
					alreadyInBase = true;
					break;
				}
			}

			if (!alreadyInBase)
				listChecklistExceptionnelToAdd.add(dateSurEcran);
		}
	}

	/**
	 * Recupération des modifications des horaires faites
	 * 
	 * @param listJoursExceptionnels
	 * @param listChecklistExceptionnelToAdd
	 * @param listChecklistExceptionnelToDelete
	 */
	private void recuperationModificationsHoraire(List<Date> listHoraires,
			List<Date> listHoraireToAdd, List<Integer> listHoraireToDelete) {
		List<Checklist_Horaire> listHoraireEnBase = ChecklistHoraireDatabaseService
				.getListFromBase(selectRow);
		for (Checklist_Horaire horaireEnBase : listHoraireEnBase) {
			Date hEnBase = horaireEnBase.getHoraire();
			boolean stillOnScreen = false;
			for (Date hSurEcran : listHoraires) {
				if (hSurEcran.equals(hEnBase)) {
					stillOnScreen = true;
					break;
				}
			}

			if (!stillOnScreen)
				listHoraireToDelete.add(horaireEnBase.getId());
		}
		for (Date hSurEcran : listHoraires) {
			boolean alreadyInBase = false;
			for (Checklist_Horaire hEnBase : listHoraireEnBase) {
				if (hSurEcran.equals(hEnBase.getHoraire())) {
					alreadyInBase = true;
					break;
				}
			}

			if (!alreadyInBase)
				listHoraireToAdd.add(hSurEcran);
		}
	}

	/**
	 * Comparaison deux listes de String
	 * 
	 * 1er list String 2eme list de String
	 */
	private boolean compareList(List<String> prevList, List<String> modelList) {
		if (modelList != null) {
			if (prevList.size() == modelList.size()) {
				for (String modelListdata : modelList) {
					if (prevList.contains(modelListdata)) {
						continue;
					} else {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
