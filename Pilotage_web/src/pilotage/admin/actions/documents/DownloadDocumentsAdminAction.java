package pilotage.admin.actions.documents;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import pilotage.framework.AbstractAdminAction;

public class DownloadDocumentsAdminAction extends AbstractAdminAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9122779606843039596L;

	private String titre;
	private InputStream downloadStream;
	private String inputPath;

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public InputStream getDownloadStream() {
		return downloadStream;
	}

	public void setDownloadStream(InputStream downloadStream) {
		this.downloadStream = downloadStream;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}


	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {

		try {

			inputPath = inputPath.trim();
			titre = inputPath.substring(inputPath.lastIndexOf("/"));

			// on évite les double / introduit par la concatenation
			inputPath.replaceAll("//", "/");

			File file = new File(inputPath);

			downloadStream = new FileInputStream(file);
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Télécharger des fichiers - ", e);
			return ERROR;
		}

	}

}
