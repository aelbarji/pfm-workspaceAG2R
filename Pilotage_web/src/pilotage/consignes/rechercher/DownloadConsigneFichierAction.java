package pilotage.consignes.rechercher;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import pilotage.database.consigne.ConsigneFichierDatabaseService;
import pilotage.database.consigne.ConsignesDatabaseService;
import pilotage.framework.AbstractActionConsigne;
import pilotage.metier.Consignes_Fichier;
import pilotage.service.constants.PilotageConstants;

public class DownloadConsigneFichierAction extends AbstractActionConsigne{

	private static final long serialVersionUID = -185696776642459097L;
	
	private String titre;
	private InputStream downloadStream; 
	private Integer fichierID;
	private String inputPath;
	private Integer typeID;

	public Integer getFichierID() {
		return fichierID;
	}

	public void setFichierID(Integer fichierID) {
		this.fichierID = fichierID;
	}

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

	public Integer getTypeID() {
		return typeID;
	}

	public void setTypeID(Integer typeID) {
		this.typeID = typeID;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		
		try {
			String dossier = null;
			if(typeID != null)
				dossier = ConsignesDatabaseService.getType(typeID).getDossier();
			else
				dossier = ConsignesDatabaseService.getType(PilotageConstants.CONSIGNE_PERMANENTE).getDossier();
			
			//Récupération du répertoire en session et mise en forme avec des / au lieu des \. Terminaison par un / 
			String path = (String)session.get(PilotageConstants.DOCUMENT_FOLDER)+dossier; 
			String firstPath = path.replaceAll("\\\\", "/");
			if(! firstPath.endsWith("/"))
				firstPath += "/";
			
			// si on transmet l'id d'un fichier défini en base
			if(fichierID != null){
				Consignes_Fichier cFichier = ConsigneFichierDatabaseService.get(fichierID);
				titre = cFichier.getNomFichier();
				inputPath = firstPath + cFichier.getLocalisation();
			}
			//sinon on a le chemin relatif transmis
			else{
				inputPath = firstPath + inputPath.trim();
				titre = inputPath.substring(inputPath.lastIndexOf("/"));
			}
			
			//on évite les double / introduit par la concatenation
			inputPath.replaceAll("//", "/");
			
			File file = new File(inputPath);

			downloadStream = new FileInputStream(file);
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Télécharger des fichiers - " , e);
			return ERROR;
		}
		
	}

}
