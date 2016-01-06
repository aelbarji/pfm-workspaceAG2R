package pilotage.users.management;
import java.util.List;

import pilotage.admin.metier.Profil;
import pilotage.database.admin.ProfilDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
	public class ShowModifyUserAction extends AbstractAction {

		private static final long serialVersionUID = 1L;
		private int selectRow;
		
		private String sort;
		private String sens;
		private int page;
		private int nrPages;
		private int nrPerPage;
		
		private String nom;
		private String prenom;
		private String login;
		private String email;
		private Integer profil;
		private List<Profil> listProfil;

		public int getSelectRow() {
			return selectRow;
		}

		public void setSelectRow(int selectRow) {
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
		
		public void setListProfil(List<Profil> listProfil) {
			this.listProfil = listProfil;
		}

		public List<Profil> getListProfil() {
			return listProfil;
		}

		public void setProfil(Integer profil) {
			this.profil = profil;
		}

		public Integer getProfil() {
			return profil;
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
				listProfil=ProfilDatabaseService.getAll();	
			
				Users selectUser = UsersDatabaseService.get(selectRow);	
				nom = selectUser.getNom();
				prenom = selectUser.getPrenom();
				login = selectUser.getLogin();
				email = selectUser.getEmail();
				profil = selectUser.getStatut();
				
				return OK;
			} catch (Exception e) {
				return ERROR;
			}
			
		}

	}
