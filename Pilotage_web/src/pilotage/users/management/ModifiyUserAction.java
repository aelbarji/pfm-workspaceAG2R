package pilotage.users.management;

import java.text.MessageFormat;
import java.util.List;

import pilotage.admin.metier.Profil;
import pilotage.database.admin.ProfilDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ModifiyUserAction extends AbstractAction {

	private static final long serialVersionUID = -3616654797068547514L;

	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;

	private Integer selectRow;
	private String nom;
	private String prenom;
	private String login;
	private String email;
	private Integer profil;
	private List<Profil> listProfil;

	private Boolean loginChanged;

	public Boolean getLoginChanged() {
		return loginChanged;
	}

	public void setLoginChanged(Boolean loginChanged) {
		this.loginChanged = loginChanged;
	}

	public List<Profil> getListProfil() {
		return listProfil;
	}

	public void setListProfil(List<Profil> listProfil) {
		this.listProfil = listProfil;
	}

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getProfil() {
		return profil;
	}

	public void setProfil(Integer profil) {
		this.profil = profil;
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
	protected String executeMetier() {
		try {
			String historique = "";
			Users u = UsersDatabaseService.get(selectRow);
			if (!nom.equals(u.getNom())) {
				historique += "nom, ";
			}
			if (!prenom.equals(u.getPrenom())) {
				historique += "prenom, ";
			}
			if (!login.equals(u.getLogin())) {
				historique += "login, ";
			}
			if (!email.equals(u.getEmail())) {
				historique += "email, ";
			}
			if (!profil.equals(u.getStatut())) {
				historique += "profil, ";
			}
			//modification du user
			UsersDatabaseService.modify(selectRow, nom, prenom, login, email, profil);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.users.modification"), new Object[]{login, selectRow, historique}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_USERS);

			info = MessageFormat.format(getText("users.modification.valide"), login);

			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un utilisateur - ", e);
			listProfil = ProfilDatabaseService.getAll();

			return ERROR;	
		}
	}

	@Override
	protected boolean validateMetier() {
		if(loginChanged && UsersDatabaseService.loginExists(login)){
			error = MessageFormat.format(getText("users.creation.login.existe.deja"), login);
			login = UsersDatabaseService.get(selectRow).getLogin();
			listProfil = ProfilDatabaseService.getAll();
			return false;
		}
		return true;
	}
}
