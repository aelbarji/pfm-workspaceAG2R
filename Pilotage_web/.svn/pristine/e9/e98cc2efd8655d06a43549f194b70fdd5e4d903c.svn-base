package pilotage.machines.actions.service;

import java.util.List;

import pilotage.database.machine.MachineInterlocuteurDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Interlocuteur;
import pilotage.metier.Users;

public class RedirectModifyMachineInterlocuteurAction extends AbstractAction {
	private static final long serialVersionUID = -2136820498896120263L;
	private int selectRow;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;

	private List<Users> listUsers;
	
	private String libelle;
	private String nomComplet;
	private Integer idResponsable;
	private String balService;

	public int getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(int selectRow) {
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

	public List<Users> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<Users> listUsers) {
		this.listUsers = listUsers;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getNomComplet() {
		return nomComplet;
	}

	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}
	
	public Integer getIdResponsable() {
		return idResponsable;
	}

	public void setIdResponsable(Integer idResponsable) {
		this.idResponsable = idResponsable;
	}

	public String getBalService() {
		return balService;
	}

	public void setBalService(String balService) {
		this.balService = balService;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			Interlocuteur selectInterlocuteur = MachineInterlocuteurDatabaseService.get(selectRow);
			libelle = selectInterlocuteur.getNomService();
			nomComplet = selectInterlocuteur.getNomComplet();
			idResponsable = selectInterlocuteur.getIdResponsable().getId();
			balService = selectInterlocuteur.getBalService();
			
			listUsers = UsersDatabaseService.getAll();
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un service gérant - ", e);
			return ERROR;
		}
	}
}
