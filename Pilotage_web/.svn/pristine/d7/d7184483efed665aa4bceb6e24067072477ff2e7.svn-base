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
import pilotage.service.convertor.MD5Convertor;


public class CreateUserAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private String nom;
	private String prenom;
	private String login;
	private String email;
	private String password;
	private Integer profil;
	private List<Profil> listProfil;

	public Integer getProfil() {
		return profil;
	}

	public void setProfil(Integer profil) {
		this.profil = profil;
	}

	public List<Profil> getListProfil() {
		return listProfil;
	}

	public void setListProfil(List<Profil> listProfil) {
		this.listProfil = listProfil;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
			//conversion du mot de passe en MD5 et enregistrement
			String md5password = MD5Convertor.crypt(password, (String)session.get(PilotageConstants.CLE_ENCODAGE));
			UsersDatabaseService.createNewUser(nom, prenom, login, email, md5password, profil);

			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.users.creation"), new Object[]{login, UsersDatabaseService.getId(login)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_USERS);

			info = MessageFormat.format(getText("users.creation.valide"), login);
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un utilisateur - ", e);
			listProfil = ProfilDatabaseService.getAll();
			return ERROR;	
		}
	}

	@Override
	protected boolean validateMetier() {
		if(UsersDatabaseService.loginExists(login)){
			error = MessageFormat.format(getText("users.creation.login.existe.deja"), login);
			listProfil = ProfilDatabaseService.getAll();
			return false;
		}
		return true;
	}
}


