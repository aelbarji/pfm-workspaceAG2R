package pilotage.url;

import java.io.File;
import java.text.MessageFormat;

import pilotage.database.url.ListeURLDatabaseService;
import pilotage.framework.AbstractAction;

public class CreateURLAction extends AbstractAction{

	private static final long serialVersionUID = 6713280561825840356L;

	private String appli;
	private String adresse;
	private File image;
	
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
		//Vérification de l'existance de l'url
		if (ListeURLDatabaseService.existAppli(appli) != null) {
			error = MessageFormat.format(getText("urlAppli.creation.existe.deja"), appli);
			return false;
		}
		if (ListeURLDatabaseService.existAdresse(adresse) != null) {
			error = MessageFormat.format(getText("urlAdresse.creation.existe.deja"), adresse);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			ListeURLDatabaseService.create(appli, adresse, image);
			return OK;
		} 
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Creation d'une URL - ", e);
			return ERROR;
		}
	}

}
