package pilotage.gup.admin.debordementnoc;


import java.util.List;

import pilotage.database.destinataires.DestinatairesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Destinataires;

public class RedirectCreateDestinataireNocAction extends AbstractAction{

	private static final long serialVersionUID = -3668899623237893047L;
	private List<Destinataires> listDest;
	
	public List<Destinataires> getListDest() {
		return listDest;
	}

	public void setListDest(List<Destinataires> listDest) {
		this.listDest = listDest;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			listDest = DestinatairesDatabaseService.getAll();
			return OK;
		} catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Redirection création destinataire débordement NOC - ", e);
			return ERROR;
		}
	}

}
