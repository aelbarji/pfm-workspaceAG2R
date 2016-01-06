package pilotage.bilan.destinataires;

import pilotage.database.bilan.BilanDestinatairesDatabaseService;
import pilotage.database.bilan.BilanEnvoieDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Bilan_Destinataires;

public class RedirectDestinataireAction extends AbstractAction {

	private static final long serialVersionUID = 6445351440166809568L;
	private Integer selectedType;
	private String typeLibelle;
	
	private Integer selectedID;
	
	private String nom;
	private String prenom;
	private String mail;
	
	public Integer getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(Integer selectedType) {
		this.selectedType = selectedType;
	}

	public String getTypeLibelle() {
		return typeLibelle;
	}

	public void setTypeLibelle(String typeLibelle) {
		this.typeLibelle = typeLibelle;
	}

	public Integer getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
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
		typeLibelle = BilanEnvoieDatabaseService.get(selectedType).getNom();
		
		if(selectedID != null){
			Bilan_Destinataires bd = BilanDestinatairesDatabaseService.get(selectedID);
			nom = bd.getNom();
			prenom = bd.getPrenom();
			mail = bd.getMail();
		}
		return OK;
	}

}
