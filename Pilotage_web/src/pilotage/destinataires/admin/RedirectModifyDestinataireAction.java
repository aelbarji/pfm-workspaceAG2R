package pilotage.destinataires.admin;

import pilotage.database.destinataires.DestinatairesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Destinataires;

public class RedirectModifyDestinataireAction extends AbstractAction{

	private static final long serialVersionUID = -244953101569682388L;
	private Integer selectedID;
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

	public Integer getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			Destinataires d = DestinatairesDatabaseService.get(selectedID);
			nom = d.getNom();
			prenom = d.getPrenom();
			mail = d.getMail();
			return OK;
		} catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Redirection modification destinataire - ", e);
			return ERROR;
		}
	}
}
