package pilotage.bilan.alertes.destinataires;

import pilotage.database.bilan.AlertesDisquesDestinatairesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Alertes_Disques_Destinataires;

public class RedirectModifyDestinataireAction extends AbstractAction {

	private static final long serialVersionUID = 259664748215750192L;
	private Integer selectedID;
	private String mail;
	private String nom;
	private String prenom;

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

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		Alertes_Disques_Destinataires add = AlertesDisquesDestinatairesDatabaseService.get(selectedID);
		mail = add.getMail();
		nom = add.getNom();
		prenom = add.getPrenom();
		
		return OK;
	}

}
