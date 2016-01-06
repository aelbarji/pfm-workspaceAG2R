package pilotage.gup.admin.debordementnoc;


import pilotage.database.destinataires.DestinatairesDatabaseService;
import pilotage.database.gup.DebordementNocDestinataireDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Destinataires;

public class CreateDestinataireNocAction extends AbstractAction{

	private static final long serialVersionUID = 1101774443646896215L;
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
				DebordementNocDestinataireDatabaseService.create(d, 1);
			else
				DebordementNocDestinataireDatabaseService.create(d, 0);
			return OK;
		} catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Redirection création destinataire débordement NOC - ", e);
			return ERROR;
		}
		
	}

}
