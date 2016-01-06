package pilotage.astreintes.actions.planning;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import pilotage.database.astreintes.AstreinteDatabaseService;
import pilotage.database.astreintes.AstreinteDomaineDatabaseService;
import pilotage.database.astreintes.AstreintePlanningDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte;
import pilotage.metier.Astreinte_Domaine;
import pilotage.metier.Astreinte_Planning;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;

public class ModifyAstreintePlanningAction extends AbstractAction{

	private static final long serialVersionUID = -5805308374861079014L;

	private Integer selectRow;
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

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
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

	public Integer getDateNB() {
		return dateNB;
	}

	public void setDateNB(Integer dateNB) {
		this.dateNB = dateNB;
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

			String historique = " ";
			Astreinte_Planning ap = AstreintePlanningDatabaseService.get(selectRow);
			if (!datedeb.equals(ap.getDatedeb())) {
				historique += "datedeb, ";
			}
			if (!datefin.equals(ap.getDatefin())) {
				historique += "datefin, ";
			}
			if (!astreinte.equals(ap.getAstreinte().getId())) {
				historique += "astreinte, ";
			}
			if (!domaine.equals(ap.getDomaine().getId())) {
				historique += "domaine, ";
			}
			if (!commentaire.equals(ap.getCommentaires())) {
				historique += "commentaire, ";
			}
			if (!infogene.equals(ap.getInfogene())) {
				historique += "infogene, ";
			}
			if (!telephone.equals(ap.getTel())) {
				historique += "telephone, ";
			}
			//creation d'une nouvelle astreinte
			AstreintePlanningDatabaseService.modify(selectRow, datedeb, datefin, astreinte, domaine, commentaire, telephone, infogene);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.astreinte.planning.modification"),new Object[]{historique,selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ASTREINTES);

			info = getText("astreinte.planning.modification.valide");

			return OK;
			
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un planning d'astreinte - ", e);
			astreintes = AstreinteDatabaseService.getAll();
			aDomaines = AstreinteDomaineDatabaseService.getAll();
			return ERROR;
		}

	}
}
