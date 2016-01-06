package pilotage.url;

import java.io.File;

import pilotage.database.url.ListeURLDatabaseService;
import pilotage.framework.AbstractAction;

public class ModifyURLAction extends AbstractAction{

	private static final long serialVersionUID = 1259764617338268279L;

	private Integer urlID;
	private String appli;
	private String adresse;
	private File image;
	
	public Integer getUrlID() {
		return urlID;
	}

	public void setUrlID(Integer urlID) {
		this.urlID = urlID;
	}

	public String getAppli() {
		return appli;
	}

	public void setAppli(String appli) {
		this.appli = appli;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			ListeURLDatabaseService.update(urlID, appli, adresse, image);
			return OK;
		} 
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'une URL - ", e);
			return ERROR;
		}
	}

}
