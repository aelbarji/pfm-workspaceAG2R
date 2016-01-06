package pilotage.admin.actions.sousmodule;

import java.text.MessageFormat;
import java.util.List;

import pilotage.admin.metier.Profil_Droits;
import pilotage.admin.metier.Sous_Module;
import pilotage.database.admin.DroitsListeDatabaseService;
import pilotage.database.admin.ProfilDroitsDatabaseService;
import pilotage.database.admin.SousModuleDatabaseService;
import pilotage.framework.AbstractAdminAction;

public class DeleteSousModuleAdminAction extends AbstractAdminAction {

	private static final long serialVersionUID = -4994060910228267244L;

	private Integer sousModuleID;
	private Integer moduleID;

	public Integer getSousModuleID() {
		return sousModuleID;
	}

	public void setSousModuleID(Integer sousModuleID) {
		this.sousModuleID = sousModuleID;
	}

	public Integer getModuleID() {
		return moduleID;
	}

	public void setModuleID(Integer moduleID) {
		this.moduleID = moduleID;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			Sous_Module sm = SousModuleDatabaseService.get(sousModuleID);
			moduleID = sm.getIdParent().getId();
			int i = deleteSousModule(sm);
			if(i == 0)
				error = MessageFormat.format(getText("suppression impossible car les droits sont affectés à un profil"),"nom");
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un sous-module - validation ", e);
			return ERROR;
		}
	}

	public static int deleteSousModule(Sous_Module sm) throws Exception {
		List<Profil_Droits> listProfilDroit = ProfilDroitsDatabaseService.getAll();
		Integer idAjout = null, idModif = null, idSuppr = null, idDetail = null;
		boolean aProfil = false;
		if (sm.getIdAjout() != null)
			idAjout = sm.getIdAjout().getId();
		if (sm.getIdModif() != null)
			idModif = sm.getIdModif().getId();
		if (sm.getIdSuppr() != null)
			idSuppr = sm.getIdSuppr().getId();
		if (sm.getIdDetail() != null)
			idDetail = sm.getIdDetail().getId();

		for (Profil_Droits pdr : listProfilDroit) {
			if ((sm.getIdAjout() != null && idAjout.equals(pdr.getId_droits().getId())) 
					|| (sm.getIdModif() != null && idModif.equals(pdr.getId_droits().getId()))
					|| (sm.getIdSuppr()!= null  && idSuppr.equals(pdr.getId_droits().getId()))
					|| (sm.getIdDetail()!= null  && idDetail.equals(pdr.getId_droits().getId()))) {
				aProfil = true;
				break;
			}
		}
		if (!aProfil) {
			SousModuleDatabaseService.delete(sm.getId());
			if (idAjout != null)
				DroitsListeDatabaseService.delete(idAjout);
			if (idModif != null)
				DroitsListeDatabaseService.delete(idModif);
			if (idSuppr != null)
				DroitsListeDatabaseService.delete(idSuppr);
			if (idDetail != null)
				DroitsListeDatabaseService.delete(idDetail);
			return 1;
		} else return 0;
	}

}
