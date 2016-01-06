package pilotage.consignes.bbr;

import java.text.MessageFormat;

import pilotage.database.consigne.ConsigneBbrDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class SupprimerConsigneBbrAction extends AbstractAction{

	private static final long serialVersionUID = 5215317819756935397L;
	private long selectRow;

	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;

	private String filtreOrigine;
	private String filtreComposant;
	private String filtreType;
	private Integer filtreLocalisation;

	public long getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(long selectRow) {
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

	public String getFiltreOrigine() {
		return filtreOrigine;
	}

	public void setFiltreOrigine(String filtreOrigine) {
		this.filtreOrigine = filtreOrigine;
	}

	public String getFiltreComposant() {
		return filtreComposant;
	}

	public void setFiltreComposant(String filtreComposant) {
		this.filtreComposant = filtreComposant;
	}

	public String getFiltreType() {
		return filtreType;
	}

	public void setFiltreType(String filtreType) {
		this.filtreType = filtreType;
	}

	public Integer getFiltreLocalisation() {
		return filtreLocalisation;
	}

	public void setFiltreLocalisation(Integer filtreLocalisation) {
		this.filtreLocalisation = filtreLocalisation;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			String origine = ConsigneBbrDatabaseService.get(selectRow).getBbrorigine();
			ConsigneBbrDatabaseService.delete(selectRow);
			info = MessageFormat.format(getText("consigne.bbr.suppression.valide"), new Object[]{origine});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.consigne.bbr.suppression") ,new Object[]{selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_CONSIGNES);
			return OK;
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'une consigne bbr - ", e);
			return ERROR;
		}
	}
}
