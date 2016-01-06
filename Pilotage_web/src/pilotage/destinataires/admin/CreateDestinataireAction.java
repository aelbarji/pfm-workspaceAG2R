package pilotage.destinataires.admin;

import pilotage.database.destinataires.DestinatairesDatabaseService;
import pilotage.framework.AbstractAction;

public class CreateDestinataireAction extends AbstractAction{

	private static final long serialVersionUID = 6359747452751291772L;
	private String nom;
	private String prenom;
	private String mail;

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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			DestinatairesDatabaseService.create(nom, mail, prenom);
			return OK;
			
		} catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création destinataire - ", e);
			return ERROR;
		}
	}

}
