package pilotage.bilan.destinataires;

import java.text.MessageFormat;
import java.util.List;

import pilotage.database.bilan.BilanDestinatairesDatabaseService;
import pilotage.database.bilan.BilanEnvoieDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Bilan_Envoie;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class CreateDestinataireAction extends AbstractAction {

	private static final long serialVersionUID = -4493266120094609631L;
	private Integer selectedType;
	private String nom;
	private String prenom;
	private String mail;
	
	private String typeLibelle;
	
	private List<Bilan_Envoie> listEnvoie;
	
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
	 * @return the listEnvoie
	 */
	public List<Bilan_Envoie> getListEnvoie() {
		return listEnvoie;
	}

	/**
	 * @param listEnvoie the listEnvoie to set
	 */
	public void setListEnvoie(List<Bilan_Envoie> listEnvoie) {
		this.listEnvoie = listEnvoie;
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
			if(BilanDestinatairesDatabaseService.exists(null, mail, selectedType)){
				error = MessageFormat.format(getText("bilan.destinataires.existe.deja"), mail);
				typeLibelle = BilanEnvoieDatabaseService.get(selectedType).getNom();
				return false;
			}
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un destinataire pour un type de bilan - ", e);
			typeLibelle = BilanEnvoieDatabaseService.get(selectedType).getNom();
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			BilanDestinatairesDatabaseService.create(nom, prenom, mail, selectedType);
			
			info = MessageFormat.format(getText("bilan.destinataires.creation.valide"), nom + " " + prenom);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.bilan.destinataire.creation"), new Object[]{nom + " " + prenom, BilanDestinatairesDatabaseService.getId(mail,selectedType), selectedType}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_BILAN);

			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un destinataire pour un type de bilan - ", e);
			typeLibelle = BilanEnvoieDatabaseService.get(selectedType).getNom();
			return ERROR;
		}
	}

}
