package pilotage.admin.actions.documents;

import pilotage.database.admin.ParametreDatabaseService;
import pilotage.database.consigne.ConsignesDatabaseService;
import pilotage.framework.AbstractAdminAction;
import pilotage.metier.Consignes_Type;
import pilotage.service.constants.PilotageConstants;

public class ShowSupprDocConsigneAdminAction extends AbstractAdminAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8276101141037747890L;
	
	private String dirRoot;
	private Integer repDestination;
	
	
	public String getDirRoot() {
		return dirRoot;
	}

	public void setDirRoot(String dirRoot) {
		this.dirRoot = dirRoot;
	}
	
	public Integer getRepDestination() {
		return repDestination;
	}

	public void setRepDestination(Integer repDestination) {
		this.repDestination = repDestination;
	}

	@Override
	protected boolean validateMetier() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected String executeMetier() {
		Consignes_Type consigneType = null;
		
		if(repDestination !=null){
			consigneType = ConsignesDatabaseService.getType(repDestination);
		}else{
			consigneType = ConsignesDatabaseService.getType(PilotageConstants.CONSIGNE_PERMANENTE);
			repDestination = 1;
		}
	
//		String path = ParametreDatabaseService.get("DOCUMENT_FOLDER").getValeur();
//		listFiles = getFilesInPath(path);
		
		dirRoot = ParametreDatabaseService.get("DOCUMENT_FOLDER").getValeur()+consigneType.getDossier();
		
		dirRoot = dirRoot.replace('\\', '/');
		if (dirRoot.charAt(dirRoot.length()-1) != '/') {
		    dirRoot += "/";
		}
		
		return OK;
	}

}
