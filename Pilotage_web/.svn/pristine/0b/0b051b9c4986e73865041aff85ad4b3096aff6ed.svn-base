package pilotage.astreinte.destinataires;

import pilotage.database.astreintes.AstreinteDestinatairesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte_Destinataires;

public class RedirectDestinataireAction extends AbstractAction {

	private static final long serialVersionUID = 6445351440166809568L;
	private Integer selectedID;
	
	private String nom;
	private String prenom;
	private String mail;
	
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
		
		if(selectedID != null){
			Astreinte_Destinataires ad = AstreinteDestinatairesDatabaseService.get(selectedID);
			nom = ad.getNom();
			prenom = ad.getPrenom();
			mail = ad.getMail();
		}
		return OK;
	}

}
