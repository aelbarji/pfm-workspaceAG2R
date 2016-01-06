package pilotage.admin.actions.profil;

import java.text.MessageFormat;
import java.util.List;

import pilotage.admin.metier.Profil;
import pilotage.database.admin.ProfilDatabaseService;
import pilotage.database.admin.ProfilDroitsDatabaseService;
import pilotage.framework.AbstractAdminAction;

public class SupprimerProfilAdminAction extends AbstractAdminAction {

	private static final long serialVersionUID = 5215317819756935397L;
	private int selectRow;
	private List<Profil> listProfil;
	
	/**
	 * @return the listProfil
	 */
	public List<Profil> getListProfil() {
		return listProfil;
	}

	/**
	 * @param listProfil the listProfil to set
	 */
	public void setListProfil(List<Profil> listProfil) {
		this.listProfil = listProfil;
	}
	/**
	 * @return the selectRow
	 */
	public int getSelectRow() {
		return selectRow;
	}

	/**
	 * @param selectRow the selectRow to set
	 */
	public void setSelectRow(int selectRow) {
		this.selectRow = selectRow;
	}

	@Override
	protected boolean validateMetier() {
		if(ProfilDatabaseService.hasUserWithProfil(selectRow)){
			listProfil = ProfilDatabaseService.getAll();
			error = getText("profil.suppression.user.affecte");
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			ProfilDroitsDatabaseService.delete(selectRow);
			ProfilDatabaseService.delete(selectRow);
			info = MessageFormat.format(getText("profil.suppression.valide"), new Object[]{selectRow});
			return OK;
		}catch (Exception e){
			erreurLogger.error("Suppression de profil ", e);
			return ERROR;
		}
		finally{
			listProfil = ProfilDatabaseService.getAll();
		}
	}
}
