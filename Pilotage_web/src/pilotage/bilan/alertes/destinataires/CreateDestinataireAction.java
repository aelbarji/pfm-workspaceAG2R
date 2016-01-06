package pilotage.bilan.alertes.destinataires;

import java.text.MessageFormat;

import pilotage.database.bilan.AlertesDisquesDestinatairesDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class CreateDestinataireAction extends AbstractAction {
	private static final long serialVersionUID = -9031302477556827941L;
	private String mail;
	private String nom;
	private String prenom;
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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

	@Override
	protected boolean validateMetier() {
		try{
			if(AlertesDisquesDestinatairesDatabaseService.exists(null, mail)){
				error = MessageFormat.format(getText("alertes.destinataires.existe.deja"), mail);
				return false;
			}
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un destinataire d'alerte disque - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			AlertesDisquesDestinatairesDatabaseService.create(mail, nom, prenom);
			info = MessageFormat.format(getText("alertes.destinataires.creation.valide"), nom + " " + prenom);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.bilan.alerte.destinataire.creation"), new Object[]{nom + " " + prenom, AlertesDisquesDestinatairesDatabaseService.getId(mail)}) , (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_BILAN);

			return OK;
		} 
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un destinataire d'alerte disque - ", e);
			return ERROR;
		}
	}

}
