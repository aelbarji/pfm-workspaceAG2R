package pilotage.astreintes.actions.astreinte;

import java.text.MessageFormat;

import pilotage.database.astreintes.AstreinteDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class SupprimerAstreinteAction extends AbstractAction{

	private static final long serialVersionUID = -2114766797435863816L;

	private Integer selectRow;
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;

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

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		
		try {
			//supprression d'une astreinte
			Astreinte astreinte = AstreinteDatabaseService.get(selectRow);
			String astreinteNom = astreinte.getNom() + " " + astreinte.getPrenom();
			AstreinteDatabaseService.delete(selectRow);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.astreinte.astreinte.suppression"), new Object[]{astreinteNom, selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ASTREINTES);

			info = MessageFormat.format(getText("astreinte.suppression.valide"), new Object[]{astreinteNom});
			return OK;
	
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'une astreinte - ", e);
			return ERROR;
		}
	}

}
