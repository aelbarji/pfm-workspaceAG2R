package pilotage.consignes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import pilotage.admin.metier.Profil;
import pilotage.database.consigne.ConsignePiloteValidationDatabaseService;
import pilotage.database.consigne.ConsignesDatabaseService;
import pilotage.database.environnement.EnvironnementDatabaseService;
import pilotage.database.incidents.IncidentsDatabaseService;
import pilotage.database.tdb.TdBCommentDatabaseService;
import pilotage.framework.AbstractActionConsigne;
import pilotage.metier.Consignes;
import pilotage.metier.Environnement;
import pilotage.metier.TdB_Commentaire;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ShowConsignesAction extends AbstractActionConsigne{

	private static final long serialVersionUID = -2537912605959924525L;

	private int page;
	private int nrPages;
	private int nrPerPage;
	private int pageQuotidienne;
	private int nrPagesQuotidienne;
	private int nrPerPageQuotidienne;
	private int pageInterEquipe;
	private int nrPagesInterEquipe;
	private int nrPerPageInterEquipe;
	
	private Pagination<Consignes> pagination;
	private Pagination<Consignes> paginationQuotidienne;
	private Pagination<Consignes> paginationInterEquipe;
	
	private List<Consignes> consignesPermanente;
	private List<Integer> listValidatedConsigneID;
	private List<Consignes> consignesInterEquipe;
	private List<Consignes> consignesQuotidienne;
	
	private List<Environnement> environnements;
	private List<String> couleursEnCours;
	private List<String> couleursResolu;
	private List<String> commentaires;
	private List<String> tailleDiv;
	
	private DateTime datetime;
	private Date date;
	
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

	public int getPageQuotidienne() {
		return pageQuotidienne;
	}

	public void setPageQuotidienne(int pageQuotidienne) {
		this.pageQuotidienne = pageQuotidienne;
	}

	public int getNrPagesQuotidienne() {
		return nrPagesQuotidienne;
	}

	public void setNrPagesQuotidienne(int nrPagesQuotidienne) {
		this.nrPagesQuotidienne = nrPagesQuotidienne;
	}

	public int getNrPerPageQuotidienne() {
		return nrPerPageQuotidienne;
	}

	public void setNrPerPageQuotidienne(int nrPerPageQuotidienne) {
		this.nrPerPageQuotidienne = nrPerPageQuotidienne;
	}

	public int getPageInterEquipe() {
		return pageInterEquipe;
	}

	public void setPageInterEquipe(int pageInterEquipe) {
		this.pageInterEquipe = pageInterEquipe;
	}

	public int getNrPagesInterEquipe() {
		return nrPagesInterEquipe;
	}

	public void setNrPagesInterEquipe(int nrPagesInterEquipe) {
		this.nrPagesInterEquipe = nrPagesInterEquipe;
	}

	public int getNrPerPageInterEquipe() {
		return nrPerPageInterEquipe;
	}

	public void setNrPerPageInterEquipe(int nrPerPageInterEquipe) {
		this.nrPerPageInterEquipe = nrPerPageInterEquipe;
	}

	public Pagination<Consignes> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Consignes> pagination) {
		this.pagination = pagination;
	}

	public Pagination<Consignes> getPaginationQuotidienne() {
		return paginationQuotidienne;
	}

	public void setPaginationQuotidienne(Pagination<Consignes> paginationQuotidienne) {
		this.paginationQuotidienne = paginationQuotidienne;
	}

	public Pagination<Consignes> getPaginationInterEquipe() {
		return paginationInterEquipe;
	}

	public void setPaginationInterEquipe(Pagination<Consignes> paginationInterEquipe) {
		this.paginationInterEquipe = paginationInterEquipe;
	}

	public List<Consignes> getConsignesPermanente() {
		return consignesPermanente;
	}

	public void setConsignesPermanente(List<Consignes> consignesPermanente) {
		this.consignesPermanente = consignesPermanente;
	}

	public List<Consignes> getConsignesInterEquipe() {
		return consignesInterEquipe;
	}

	public void setConsignesInterEquipe(List<Consignes> consignesInterEquipe) {
		this.consignesInterEquipe = consignesInterEquipe;
	}

	public List<Consignes> getConsignesQuotidienne() {
		return consignesQuotidienne;
	}

	public void setConsignesQuotidienne(List<Consignes> consignesQuotidienne) {
		this.consignesQuotidienne = consignesQuotidienne;
	}

	public List<Integer> getListValidatedConsigneID() {
		return listValidatedConsigneID;
	}

	public void setListValidatedConsigneID(List<Integer> listValidatedConsigneID) {
		this.listValidatedConsigneID = listValidatedConsigneID;
	}

	public List<Environnement> getEnvironnements() {
		return environnements;
	}

	public void setEnvironnements(List<Environnement> environnements) {
		this.environnements = environnements;
	}

	public List<String> getCouleursEnCours() {
		return couleursEnCours;
	}

	public void setCouleursEnCours(List<String> couleursEnCours) {
		this.couleursEnCours = couleursEnCours;
	}

	public List<String> getCouleursResolu() {
		return couleursResolu;
	}

	public void setCouleursResolu(List<String> couleursResolu) {
		this.couleursResolu = couleursResolu;
	}

	public DateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(DateTime datetime) {
		this.datetime = datetime;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<String> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(List<String> commentaires) {
		this.commentaires = commentaires;
	}

	public List<String> getTailleDiv() {
		return tailleDiv;
	}

	public void setTailleDiv(List<String> tailleDiv) {
		this.tailleDiv = tailleDiv;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			Users user = (Users)session.get(PilotageConstants.USER_LOGGED);
			Profil profil = (Profil)session.get(PilotageConstants.USER_PROFIL);
			
			environnements = new ArrayList<Environnement>();
			couleursEnCours = new ArrayList<String>();
			couleursResolu = new ArrayList<String>();
			commentaires = new ArrayList<String>();
			tailleDiv = new ArrayList<String>();
			List<Environnement> environnementsBase = EnvironnementDatabaseService.getAll();
			Integer incidentEnCours = null;
			Integer incidentResolu = null;
			String comment = null;
			TdB_Commentaire tdbComment = null;
			
			DateTime datetimeCourant = new DateTime();
			datetime = datetimeCourant.minusHours(12);
			date = datetime.toDate();
			
			//Récupération des incidents par environnement
			int index = 0;
			for(Environnement envir : environnementsBase){
				incidentEnCours = IncidentsDatabaseService.hasIncidentEnCoursWithEnvironnement(envir);
				incidentResolu = IncidentsDatabaseService.hasIncidentResoluWithEnvironnement(envir, datetime.toDate());
				tdbComment = TdBCommentDatabaseService.getByDateEnvir(datetime, envir);
				if(tdbComment!=null) comment = tdbComment.getCommentaire();
				
				if((incidentEnCours!=null && (incidentEnCours==PilotageConstants.IMPACT_CRITIQUE || incidentEnCours==PilotageConstants.IMPACT_INCIDENT))
						|| (incidentResolu!=null && (incidentResolu==PilotageConstants.IMPACT_CRITIQUE || incidentResolu==PilotageConstants.IMPACT_INCIDENT))){
					environnements.add(envir);
					if(envir.getEnvironnement().length()>20) tailleDiv.add("30px");
					else tailleDiv.add("60px");
					
					couleursEnCours.add(PilotageConstants.OK);
					couleursResolu.add("Circle_Green.png");
					commentaires.add(comment);
				
					if(incidentEnCours!=null){
						if(incidentEnCours==PilotageConstants.IMPACT_CRITIQUE) couleursEnCours.set(index, PilotageConstants.KO);
						else if(incidentEnCours==PilotageConstants.IMPACT_INCIDENT) couleursEnCours.set(index, PilotageConstants.PERTURBE);
					}
					
					if(incidentResolu!=null){
						if(incidentResolu==PilotageConstants.IMPACT_CRITIQUE) couleursResolu.set(index, "Circle_Red.png");
						else if(incidentResolu==PilotageConstants.IMPACT_INCIDENT) couleursResolu.set(index, "Circle_Orange.png");
					}
					index++;
				}
				incidentEnCours = null;
				incidentResolu = null;
				tdbComment = null;
				comment = null;
			}
					
			//récupération de la liste des consignes courantes et la liste des consignes validées par le pilote
			if(page < 1)
				page = 1;
			else if(nrPages != 0 && page > nrPages)
				page = nrPages;
			if(nrPerPage == 0)
				nrPerPage = PilotageConstants.NB_CONSIGNES_PER_PAGE;
			pagination = new Pagination<Consignes>(page, nrPerPage);
						
			consignesPermanente = ConsignesDatabaseService.getAllCurrentConsignesByType(PilotageConstants.CONSIGNE_PERMANENTE, pagination, "date", "desc");
			if(profil.getPilote())
				listValidatedConsigneID = ConsignePiloteValidationDatabaseService.getValidatedConsignesID(user);
	
			//pour les pilotes, on check si on a la ligne dans consigne_pilote_validation pour chaque consigne
			//si des lignes manquent, on les crée 
			ShowConsignesAction.createPiloteValidation(user, profil, consignesPermanente);
			
			//récupération de la liste des consignes inter equipe
			if(pageInterEquipe < 1)
				pageInterEquipe = 1;
			else if(nrPagesInterEquipe != 0 && pageInterEquipe > nrPagesInterEquipe)
				pageInterEquipe = nrPagesInterEquipe;
			if(nrPerPageInterEquipe == 0)
				nrPerPageInterEquipe = PilotageConstants.NB_INFO_INTEREQUIPE_PER_PAGE;
			paginationInterEquipe = new Pagination<Consignes>(pageInterEquipe, nrPerPageInterEquipe);
			paginationInterEquipe.setNrPages(nrPagesInterEquipe);
			
			consignesInterEquipe = ConsignesDatabaseService.getAllCurrentConsignesByType(PilotageConstants.CONSIGNE_INTEREQUIPE, paginationInterEquipe, "date", "desc");
			
			//récupération de la liste des consignes quotidiennes
			if(pageQuotidienne < 1)
				pageQuotidienne = 1;
			else if(nrPagesQuotidienne != 0 && pageQuotidienne > nrPagesQuotidienne)
				pageQuotidienne = nrPagesQuotidienne;
			if(nrPerPageQuotidienne == 0)
				nrPerPageQuotidienne = PilotageConstants.NB_CONSIGNES_PER_PAGE;
			paginationQuotidienne = new Pagination<Consignes>(pageQuotidienne, nrPerPageQuotidienne);
			paginationQuotidienne.setNrPages(nrPagesQuotidienne);
			
			consignesQuotidienne = ConsignesDatabaseService.getAllCurrentConsignesByType(PilotageConstants.CONSIGNE_QUOTIDIENNE, paginationQuotidienne, "date", "desc");		
			
			return OK;

		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Liste des consignes - ", e);
			return ERROR;
		}
	}
	
	public static void createPiloteValidation(Users user, Profil profil, List<Consignes> consignes) throws Exception{
		if(profil.getPilote()){
			for(Consignes consigne : consignes){
				if (!ConsignePiloteValidationDatabaseService.validationLineAlreadyExists(user, consigne)) {
					ConsignePiloteValidationDatabaseService.create(consigne, user);
				}
			}
		}
	}

}
