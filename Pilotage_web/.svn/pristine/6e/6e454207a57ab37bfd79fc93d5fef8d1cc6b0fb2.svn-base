package pilotage.gup.admin.destinataire.bilan;


import pilotage.database.destinataires.DestinatairesDatabaseService;
import pilotage.database.gup.ComBilanDestinataireDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Destinataires;

public class CreateDestinataireBilanAction extends AbstractAction {

	private static final long serialVersionUID = 6021379760504375741L;
	private String destinataire;
	private boolean cc;

	public boolean getCc() {
		return cc;
	}

	public void setCc(boolean cc) {
		this.cc = cc;
	}

	public String getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}
	
	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			Destinataires d = DestinatairesDatabaseService.get(Integer.parseInt(destinataire));
			if(cc)
				ComBilanDestinataireDatabaseService.create(d, 1);
			else
				ComBilanDestinataireDatabaseService.create(d, 0);
			return OK;
		} catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Redirection création destinataire bilan - ", e);
			return ERROR;
		}
	}

}
