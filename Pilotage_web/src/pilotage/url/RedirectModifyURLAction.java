package pilotage.url;

import pilotage.database.url.ListeURLDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Liste_URL;

public class RedirectModifyURLAction extends AbstractAction{

	private static final long serialVersionUID = -8874379169097391243L;

	private Integer urlID;
	private Liste_URL url;
	
	public Integer getUrlID() {
		return urlID;
	}

	public void setUrlID(Integer urlID) {
		this.urlID = urlID;
	}

	public Liste_URL getUrl() {
		return url;
	}

	public void setUrl(Liste_URL url) {
		this.url = url;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			url = ListeURLDatabaseService.get(urlID);
			return OK;
		}catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage modification de l'url", e);
			return ERROR;
		}
	}

	
}
