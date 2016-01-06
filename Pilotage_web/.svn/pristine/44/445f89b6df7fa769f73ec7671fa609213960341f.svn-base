package pilotage.astreinte.destinataires;

import java.text.MessageFormat;

import pilotage.database.astreintes.AstreinteDestinatairesDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class CreateDestinataireAction extends AbstractAction {

	private static final long serialVersionUID = -4493266120094609631L;
	private String nom;
	private String prenom;
	private String mail;
	
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

	@Override
	protected boolean validateMetier() {
		try{
			if(AstreinteDestinatairesDatabaseService.exists(null, mail)){
				error = MessageFormat.format(getText("astreinte.destinataires.existe.deja"), mail);
				return false;
			}
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un destinataire pour l'envoi des astreintes ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			AstreinteDestinatairesDatabaseService.create(nom, prenom, mail);
			
			info = MessageFormat.format(getText("astreinte.destinataires.creation.valide"), nom + " " + prenom);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.bilan.destinataire.creation"), new Object[]{nom + " " + prenom, AstreinteDestinatairesDatabaseService.getId(mail)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ASTREINTES);

			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un destinataire pour l'envoi des astreintes - ", e);
			return ERROR;
		}
	}

}
