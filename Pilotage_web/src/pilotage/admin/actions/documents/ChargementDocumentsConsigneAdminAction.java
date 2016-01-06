package pilotage.admin.actions.documents;

import java.io.File;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;

import pilotage.database.admin.ParametreDatabaseService;
import pilotage.database.consigne.ConsignesDatabaseService;
import pilotage.framework.AbstractAdminAction;
import pilotage.metier.Consignes_Type;
import pilotage.service.constants.PilotageConstants;

public class ChargementDocumentsConsigneAdminAction extends AbstractAdminAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4687128755868497332L;

	private File docConsigne;
	private String docConsigneFileName;
	private String docConsigneContentType;
	private Integer repDestination;

	private HttpServletRequest servletRequest;

	public File getDocConsigne() {
		return docConsigne;
	}

	public void setDocConsigne(File docConsigne) {
		this.docConsigne = docConsigne;
	}

	public String getDocConsigneFileName() {
		return docConsigneFileName;
	}

	public void setDocConsigneFileName(String docConsigneFileName) {
		this.docConsigneFileName = docConsigneFileName;
	}

	public String getDocConsigneContentType() {
		return docConsigneContentType;
	}

	public void setDocConsigneContentType(String docConsigneContentType) {
		this.docConsigneContentType = docConsigneContentType;
	}

	public Integer getRepDestination() {
		return repDestination;
	}

	public void setRepDestination(Integer repDestination) {
		this.repDestination = repDestination;
	}

	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	@Override
	protected boolean validateMetier() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected String executeMetier() {
		Consignes_Type repertoireType = null;

		switch (repDestination) {
		case 1:
			repertoireType = ConsignesDatabaseService
					.getType(PilotageConstants.CONSIGNE_PERMANENTE);
			break;
		case 2:
			repertoireType = ConsignesDatabaseService
					.getType(PilotageConstants.CONSIGNE_QUOTIDIENNE);
			break;
		case 3:
			repertoireType = ConsignesDatabaseService
					.getType(PilotageConstants.CONSIGNE_INTEREQUIPE);
			break;
		default:
			break;
		}

		String dirRoot = ParametreDatabaseService.get("DOCUMENT_FOLDER")
				.getValeur() + repertoireType.getDossier();

		dirRoot = dirRoot.replace('\\', '/');
		if (dirRoot.charAt(dirRoot.length() - 1) != '/') {
			dirRoot += "/";
		}

		try {
			System.out.println("File name:" + docConsigne);
			File sauvegardeDoc = new File(dirRoot, this.docConsigneFileName);
			FileUtils.copyFile(this.docConsigne, sauvegardeDoc);
			info = MessageFormat.format(getText("consigne.document.admin.creation.valide"), new Object[]{docConsigneFileName,dirRoot});
		} catch (Exception e) {
			error = getText("Une erreur est survenue lors de l envoie du fichier") + " : "+ e.getMessage();
			erreurLogger.error("Chargement du document",e);
			return ERROR;
		}

		return OK;

	}

}
