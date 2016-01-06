package pilotage.incidents.qualite;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.incidents.IncidentsQualiteDatabaseService;
import pilotage.database.incidents.IncidentsQualiteStatutDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Incidents_Qualite_Statut;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;

public class ModifyFicheIncidentsQualiteAction extends AbstractAction{

	private static final long serialVersionUID = -6164615654483001140L;
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	private int provenance;
	
	private Integer selectRow;

	private String dateEvenement;
	private String description;
	private String cause_raison;
	private String incidence;
	private String echeance;
	private Integer statut;
	private List<Incidents_Qualite_Statut> statutList;
	private String dateResolution;
	
	public int getProvenance() {
		return provenance;
	}

	public void setProvenance(int provenance) {
		this.provenance = provenance;
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

	public String getDateEvenement() {
		return dateEvenement;
	}

	public void setDateEvenement(String dateEvenement) {
		this.dateEvenement = dateEvenement;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCause_raison() {
		return cause_raison;
	}

	public void setCause_raison(String cause_raison) {
		this.cause_raison = cause_raison;
	}

	public String getIncidence() {
		return incidence;
	}

	public void setIncidence(String incidence) {
		this.incidence = incidence;
	}

	public String getEcheance() {
		return echeance;
	}

	public void setEcheance(String echeance) {
		this.echeance = echeance;
	}

	public Integer getStatut() {
		return statut;
	}

	public void setStatut(Integer statut) {
		this.statut = statut;
	}
	
	public List<Incidents_Qualite_Statut> getStatutList() {
		return statutList;
	}

	public void setStatutList(List<Incidents_Qualite_Statut> statutList) {
		this.statutList = statutList;
	}

	public String getDateResolution() {
		return dateResolution;
	}

	public void setDateResolution(String dateResolution) {
		this.dateResolution = dateResolution;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{				
			Date dateE = DateService.strToDate(dateEvenement);
			Date dateR = null;
			if (!dateResolution.equalsIgnoreCase("")){
				dateR = DateService.strToDate(dateResolution);
			}
			IncidentsQualiteDatabaseService.modify(selectRow, dateE, description, cause_raison, incidence, echeance, statut, dateR);
			
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.incidents.qualite.modification"), new Object[]{selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_INCIDENTS);
			info = getText("incidents.qualite.modification.valide");

			if(provenance == 1)
				return "action";
			else
				return OK;
		}
		catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'une fiche incident qualite", e);
			
			statutList = IncidentsQualiteStatutDatabaseService.getAll();
			return ERROR;
		}
	}
}
