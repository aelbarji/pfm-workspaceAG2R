package pilotage.bilan.destinataires;

import java.text.MessageFormat;
import java.util.List;

import pilotage.database.bilan.BilanDestinatairesDatabaseService;
import pilotage.database.bilan.BilanEnvoieDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Bilan_Destinataires;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ModifyDestinataireAction extends AbstractAction {

	private static final long serialVersionUID = -7550101209753976514L;

	private Integer selectedID;
	private Integer selectedType;
	
	private String typeLibelle;
	private String nom;
	private String prenom;
	private String mail;

	private Boolean mailChanged;
	
	private List<Bilan_Destinataires> listDestina;

	/**
	 * @return the listDestina
	 */
	public List<Bilan_Destinataires> getListDestina() {
		return listDestina;
	}

	/**
	 * @param listDestina the listDestina to set
	 */
	public void setListDestina(List<Bilan_Destinataires> listDestina) {
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
	 * @return the selectedType
	 */
	public Integer getSelectedType() {
		return selectedType;
	}

	/**
	 * @param selectedType the selectedType to set
	 */
	public void setSelectedType(Integer selectedType) {
		this.selectedType = selectedType;
	}
	
	/**
	 * @return the typeLibelle
	 */
	public String getTypeLibelle() {
		return typeLibelle;
	}

	/**
	 * @param typeLibelle the typeLibelle to set
	 */
	public void setTypeLibelle(String typeLibelle) {
		this.typeLibelle = typeLibelle;
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
			if(mailChanged && BilanDestinatairesDatabaseService.exists(selectedID, mail, selectedType)){
				error = MessageFormat.format(getText("bilan.destinataires.existe.deja"), mail);
				typeLibelle = BilanEnvoieDatabaseService.get(selectedType).getNom();
				mail = BilanDestinatairesDatabaseService.get(selectedID).getMail();
				return false;
			}
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un destinataire pour un type de bilan - ", e);
			typeLibelle = BilanEnvoieDatabaseService.get(selectedType).getNom();
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			String historique = "";
			Bilan_Destinataires be = BilanDestinatairesDatabaseService.get(selectedID);
			if (!nom.equals(be.getNom())) {
				historique += "nom, ";
			}
			if (!prenom.equals(be.getPrenom())) {
				historique += "prenom, ";
			}
			if (!mail.equals(be.getMail())) {
				historique += "mail, ";
			}
			BilanDestinatairesDatabaseService.update(selectedID, nom, prenom, mail, selectedType);
			info = MessageFormat.format(getText("bilan.destinataires.modification.valide"), mail);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.bilan.destinataire.modification"), new Object[]{selectedID, historique, selectedType}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_BILAN);
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un destinataire pour un type de bilan - ", e);
			typeLibelle = BilanEnvoieDatabaseService.get(selectedType).getNom();
			return ERROR;
		}
	}

}
