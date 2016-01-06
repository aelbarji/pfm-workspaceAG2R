package pilotage.url;

import pilotage.database.url.ListeURLDatabaseService;
import pilotage.database.url.ListeURLfavorisDatabaseService;
import pilotage.framework.AbstractAction;

public class DeleteURLAction extends AbstractAction{

	private static final long serialVersionUID = -7517576291291458747L;

	private Integer urlID;
	
	public Integer getUrlID() {
		return urlID;
	}

	public void setUrlID(Integer urlID) {
		this.urlID = urlID;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			ListeURLfavorisDatabaseService.deleteAll(urlID);
			ListeURLDatabaseService.delete(urlID);
			return OK;
		} 
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'une URL - ", e);
			return ERROR;
		}
	}

}
