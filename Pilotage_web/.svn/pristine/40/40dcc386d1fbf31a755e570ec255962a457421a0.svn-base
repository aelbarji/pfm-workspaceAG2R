package pilotage.machines.actions.service;

import java.text.MessageFormat;
import java.util.List;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.machine.MachineInterlocuteurDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Interlocuteur;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ModifyMachineInterlocuteurAction extends AbstractAction{

	private static final long serialVersionUID = 7013818341148152623L;
	private Integer selectRow;
	private Boolean libelleChanged;
	
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

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public Boolean getLibelleChanged() {
		return libelleChanged;
	}

	public void setLibelleChanged(Boolean libelleChanged) {
		this.libelleChanged = libelleChanged;
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
		try{
			if(libelleChanged && MachineInterlocuteurDatabaseService.exists(selectRow, libelle)){
				error = MessageFormat.format(getText("machine.gerant.creation.existe.deja"), libelle);
				libelle = MachineInterlocuteurDatabaseService.get(selectRow).getNomService();;
				listUsers = UsersDatabaseService.getAll();
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un service gérant - ", e);
			listUsers = UsersDatabaseService.getAll();
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			String historique = " ";
			Interlocuteur i = MachineInterlocuteurDatabaseService.get(selectRow);
			if (!libelle.equals(i.getNomService())) {
				historique += "nomService, ";
			}
			if (!nomComplet.equals(i.getNomComplet())) {
				historique += "nomComplet, ";
			}
			if (!idResponsable.equals(i.getIdResponsable().getId())) {
				historique += "responsable, ";
			}
			if (!balService.equals(i.getBalService())) {
				historique += "balService, ";
			}
			MachineInterlocuteurDatabaseService.modify(selectRow, libelle, nomComplet, idResponsable, balService);
			info = MessageFormat.format(getText("machine.gerant.modification.valide"), new Object[]{libelle});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.machine.gerant.modification"), new Object[]{historique,selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_MACHINES);

			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un service gérant - ", e);
			listUsers = UsersDatabaseService.getAll();
			return ERROR;
		}
	}
}
