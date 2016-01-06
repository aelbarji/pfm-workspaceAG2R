package pilotage.checklist.current;

import java.text.MessageFormat;

import pilotage.database.checklist.ChecklistDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Checklist_Current;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class AddCommentAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private int tacheID;
	private String libelleTache;
	private String commentaire;
	private int status;

	public int getTacheID() {
		return tacheID;
	}

	public void setTacheID(int tacheID) {
		this.tacheID = tacheID;
	}

	public String getLibelleTache() {
		return libelleTache;
	}

	public void setLibelleTache(String libelleTache) {
		this.libelleTache = libelleTache;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		status = 0;
		if (commentaire == null) {
			Checklist_Current current = ChecklistDatabaseService.get(tacheID);
			commentaire = current.getCommentaire();
			libelleTache = current.getTache().getTache() + " - " + current.getSousTache().getNomSousTache();
			return OK;
		} 
		else {
			try{
				ChecklistDatabaseService.modifyComment(tacheID, commentaire);
				
				HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.checklist.modification.commentaire"),new Object[]{tacheID}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_CHECKLIST);

				info = getText("checklist.commentaire.valide");
				status = 1;
				return OK;
			}
			catch (Exception e) {
				error = getText("error.message.generique") + " : " + e.getMessage();
				erreurLogger.error("Checklist : Sauvegarde d'un commentaire - ", e);
				return ERROR;
			}
		}
	}

}
