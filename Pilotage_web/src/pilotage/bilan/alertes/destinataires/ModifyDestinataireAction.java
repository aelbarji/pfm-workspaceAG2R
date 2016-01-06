package pilotage.bilan.alertes.destinataires;

import java.text.MessageFormat;

import pilotage.database.bilan.AlertesDisquesDestinatairesDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Alertes_Disques_Destinataires;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ModifyDestinataireAction extends AbstractAction {

	private static final long serialVersionUID = -5802018311388255306L;
	private Integer selectedID;
	private String mail;
	private String nom;
	private String prenom;
	private Boolean libelleChanged;

	/**
	 * @return the selectedID
	 */
	public Integer getSelectedID() {
		return selectedID;
	}

	/**
	 * @param selectedID the selectedID to set
	 */
	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	/**
	 * @return the libelleChanged
	 */
	public Boolean getLibelleChanged() {
		return libelleChanged;
	}
	
	/**
	 * @param libelleChanged the libelleChanged to set
	 */
	public void setLibelleChanged(Boolean libelleChanged) {
		this.libelleChanged = libelleChanged;
	}
	
	@Override
	protected boolean validateMetier() {
		try{
			if(libelleChanged && AlertesDisquesDestinatairesDatabaseService.exists(selectedID, mail)){
				error = MessageFormat.format(getText("alertes.destinataires.existe.deja"), mail);
				mail = AlertesDisquesDestinatairesDatabaseService.get(selectedID).getMail();
				return false;
			}
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un destinataire d'alertes disques - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			String historique = "";
			Alertes_Disques_Destinataires add = AlertesDisquesDestinatairesDatabaseService.get(selectedID);
			if (!mail.equals(add.getMail())) {
				historique += "mail, ";
			}
			if (!nom.equals(add.getNom())) {
				historique += "nom, ";
			}
			if (!prenom.equals(add.getPrenom())) {
				historique += "prenom, ";
			}
			AlertesDisquesDestinatairesDatabaseService.update(selectedID, mail, nom, prenom);
			info = MessageFormat.format(getText("alertes.destinataires.modification.valide"), nom + " " + prenom);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.bilan.alerte.destinataire.modification"), new Object[]{selectedID, historique}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_BILAN);

			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un destinataire d'alertes disques - ", e);
			return ERROR;
		}
	}
}
