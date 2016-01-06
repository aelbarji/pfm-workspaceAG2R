package pilotage.astreintes.actions.planning;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pilotage.database.astreintes.AstreinteDatabaseService;
import pilotage.database.astreintes.AstreinteDomaineDatabaseService;
import pilotage.database.astreintes.AstreinteObligatoireDatabaseService;
import pilotage.database.astreintes.AstreintePlanningDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte;
import pilotage.metier.Astreinte_Domaine;
import pilotage.metier.Astreinte_Obligatoire;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;

public class CreateAstreintePlanningAction extends AbstractAction{

	private static final long serialVersionUID = 3026241526956914770L;

	private Boolean continuer;
	private String astObligatoire;
	
	private Integer astreinte;
	private Integer domaine;
	private String telephone;
	private String dateDeb;
	private String dateFin;
	private String heureDebut;
	private String heureFin;
	private String commentaire;
	private String infogene;
	
	private List<Astreinte> astreintes;
	private List<Astreinte_Domaine> aDomaines;
	
	private Integer dateNB;
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;

	public Boolean getContinuer() {
		return continuer;
	}

	public void setContinuer(Boolean continuer) {
		this.continuer = continuer;
	}

	public Integer getAstreinte() {
		return astreinte;
	}

	public void setAstreinte(Integer astreinte) {
		this.astreinte = astreinte;
	}

	public Integer getDomaine() {
		return domaine;
	}

	public void setDomaine(Integer domaine) {
		this.domaine = domaine;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(String dateDeb) {
		this.dateDeb = dateDeb;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
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

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getInfogene() {
		return infogene;
	}

	public void setInfogene(String infogene) {
		this.infogene = infogene;
	}

	public List<Astreinte> getAstreintes() {
		return astreintes;
	}

	public void setAstreintes(List<Astreinte> astreintes) {
		this.astreintes = astreintes;
	}

	public List<Astreinte_Domaine> getADomaines() {
		return aDomaines;
	}

	public void setADomaines(List<Astreinte_Domaine> aDomaines) {
		this.aDomaines = aDomaines;
	}

	public Integer getDateNB() {
		return dateNB;
	}

	public void setDateNB(Integer dateNB) {
		this.dateNB = dateNB;
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

	public String getAstObligatoire() {
		return astObligatoire;
	}

	public void setAstObligatoire(String astObligatoire) {
		this.astObligatoire = astObligatoire;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		
		try {
			//Conversion de format de datetime
			Date datedeb = DateService.strToDate(dateDeb);
			Date datefin = DateService.strToDate(dateFin);
			
			//creation d'une nouvelle astreinte
			AstreintePlanningDatabaseService.create(datedeb, datefin, astreinte, domaine, commentaire, telephone,infogene);
			info = getText("astreinte.planning.creation.valide");
			
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.astreinte.planning.creation"),new Object[]{AstreintePlanningDatabaseService.getId(datedeb, datefin, astreinte, domaine, commentaire, telephone)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ASTREINTES);

			//Clic sur 'continuer' : on retourne sur la page de création
			if(continuer){
				//reset de tous les champs
				domaine = null;
				astreinte = null;
				telephone = null;
				dateDeb = null;
				dateFin = null;
				heureDebut = null;
				heureFin = null;
				commentaire = null;
				infogene = null;
				
				if(astObligatoire != null && !"".equals(astObligatoire)){
					Integer astObligatoireID = Integer.parseInt(astObligatoire);
					Astreinte_Obligatoire ao = AstreinteObligatoireDatabaseService.get(astObligatoireID);
					astreintes = AstreinteDatabaseService.getAstreintesByType(ao.getType());
					aDomaines = new ArrayList<Astreinte_Domaine>();
					aDomaines.add(ao.getDomaine());
				}
				else{
					astreintes = AstreinteDatabaseService.getAll();
					aDomaines = AstreinteDomaineDatabaseService.getAll();
				}
				
				return SUITE;
			}
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Creation d'un planning d'astreinte - ", e);
			//pour creation les astreintes plannings par type et domaine
			if(astObligatoire != null && !"".equals(astObligatoire)){
				Integer astObligatoireID = Integer.parseInt(astObligatoire);
				Astreinte_Obligatoire ao = AstreinteObligatoireDatabaseService.get(astObligatoireID);
				astreintes = AstreinteDatabaseService.getAstreintesByType(ao.getType());
				aDomaines = new ArrayList<Astreinte_Domaine>();
				aDomaines.add(ao.getDomaine());
			}
			else{
				astreintes = AstreinteDatabaseService.getAll();
				aDomaines = AstreinteDomaineDatabaseService.getAll();
			}
			return ERROR;
		}
	}

}
