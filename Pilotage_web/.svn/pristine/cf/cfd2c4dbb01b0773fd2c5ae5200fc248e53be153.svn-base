package pilotage.astreinte.destinataires;

import java.text.MessageFormat;
import java.util.List;

import pilotage.database.astreintes.AstreinteDestinatairesDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte_Destinataires;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ModifyDestinataireAction extends AbstractAction {

	private static final long serialVersionUID = -7550101209753976514L;

	private Integer selectedID;
	private String nom;
	private String prenom;
	private String mail;

	private Boolean mailChanged;
	
	private List<Astreinte_Destinataires> listDestina;

	/**
	 * @return the listDestina
	 */
	public List<Astreinte_Destinataires> getListDestina() {
		return listDestina;
	}

	/**
	 * @param listDestina the listDestina to set
	 */
	public void setListDestina(List<Astreinte_Destinataires> listDestina) {
		this.listDestina = listDestina;
	}

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

	/**
	 * @return the mailChanged
	 */
	public Boolean getMailChanged() {
		return mailChanged;
	}
	
	/**
	 * @param mailChanged the mailChanged to set
	 */
	public void setMailChanged(Boolean mailChanged) {
		this.mailChanged = mailChanged;
	}

	@Override
	protected boolean validateMetier() {
		try{
			if(mailChanged && AstreinteDestinatairesDatabaseService.exists(selectedID, mail)){
				error = MessageFormat.format(getText("astreinte.destinataires.existe.deja"), mail);
				mail = AstreinteDestinatairesDatabaseService.get(selectedID).getMail();
				return false;
			}
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un destinataire pour l'envoi des astreintes - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			String historique = "";
			Astreinte_Destinataires be = AstreinteDestinatairesDatabaseService.get(selectedID);
			if (!nom.equals(be.getNom())) {
				historique += "nom, ";
			}
			if (!prenom.equals(be.getPrenom())) {
				historique += "prenom, ";
			}
			if (!mail.equals(be.getMail())) {
				historique += "mail, ";
			}
			AstreinteDestinatairesDatabaseService.update(selectedID, nom, prenom, mail);
			info = MessageFormat.format(getText("astreinte.destinataires.modification.valide"), mail);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.astreinte.destinataire.modification"), new Object[]{selectedID, historique}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ASTREINTES);
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un destinataire pour l'envoi des astreinte - ", e);
			return ERROR;
		}
	}

}
