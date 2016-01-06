package pilotage.admin.actions.documents;

import java.io.File;
import java.text.MessageFormat;

import pilotage.database.admin.ParametreDatabaseService;
import pilotage.database.consigne.ConsignesDatabaseService;
import pilotage.framework.AbstractAdminAction;
import pilotage.metier.Consignes_Type;

public class SupprDocConsigneAdminAction extends AbstractAdminAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2186610547472533920L;

	private String document;
	private Integer repDestination;
	private String dirRoot;

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public Integer getRepDestination() {
		return repDestination;
	}

	public void setRepDestination(Integer repDestination) {
		this.repDestination = repDestination;
	}
	
	public String getDirRoot() {
		return dirRoot;
	}

	public void setDirRoot(String dirRoot) {
		this.dirRoot = dirRoot;
	}

	@Override
	protected boolean validateMetier() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected String executeMetier() {
		
		Consignes_Type consigneType = ConsignesDatabaseService.getType(repDestination);
		
		dirRoot = ParametreDatabaseService.get("DOCUMENT_FOLDER")
				.getValeur() + consigneType.getDossier();

		dirRoot = dirRoot.replace('\\', '/');
		if (dirRoot.charAt(dirRoot.length() - 1) != '/') {
			dirRoot += "/";
		}

		try {
			File file = new File(dirRoot + document);
			file.delete();
			info = MessageFormat.format(
					getText("consigne.document.admin.suppression.valide"),
					new Object[] {document});

		} catch (Exception e) {
			error = getText("Une erreur est survenue lors de la suppression du document")
					+ " : " + e.getMessage();
			erreurLogger.error("Chargement du document", e);
			return ERROR;
		}
		return OK;
	}

}
