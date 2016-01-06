package pilotage.users.management;

import java.text.MessageFormat;
import java.util.List;

import pilotage.admin.metier.Profil;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
public class DeleteUserAction extends AbstractAction{

	private static final long serialVersionUID = -4247849760931789967L;

	private List<Profil> listProfil;
	private int selectRow;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;

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

	public List<Profil> getListProfil() {
		return listProfil;
	}

	public void setListProfil(List<Profil> listProfil) {
		this.listProfil = listProfil;
	}

	public int getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(int selectRow) {
		this.selectRow = selectRow;
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
			String login = UsersDatabaseService.get(selectRow).getLogin();
			
			//suppression du user
			UsersDatabaseService.delete(selectRow);

			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.users.suppression"), new Object[]{login, selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_USERS);

			//message de validation
			info = MessageFormat.format(getText("users.suppression.valide"), login);
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un utilisateur - ", e);
			return ERROR;
		}

	}
	
}