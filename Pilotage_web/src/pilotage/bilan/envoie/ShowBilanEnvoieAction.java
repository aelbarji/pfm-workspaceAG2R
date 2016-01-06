package pilotage.bilan.envoie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pilotage.database.bilan.BilanEnvoieDatabaseService;
import pilotage.database.environnement.EnvironnementDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Bilan_Envoie;
import pilotage.metier.Environnement;

public class ShowBilanEnvoieAction extends AbstractAction {

	private static final long serialVersionUID = 2802244414634829295L;
	private List<Bilan_Envoie> listEnvoie;
	private Map<Integer, List<Environnement>> listEnvironnement;

	public List<Bilan_Envoie> getListEnvoie() {
		return listEnvoie;
	}

	public void setListEnvoie(List<Bilan_Envoie> listEnvoie) {
		this.listEnvoie = listEnvoie;
	}

	public Map<Integer, List<Environnement>> getListEnvironnement() {
		return listEnvironnement;
	}

	public void setListEnvironnement(
			Map<Integer, List<Environnement>> listEnvironnement) {
		this.listEnvironnement = listEnvironnement;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		listEnvoie = BilanEnvoieDatabaseService.getAll();
		
		listEnvironnement = new HashMap<Integer, List<Environnement>>();
		for(Bilan_Envoie typeBilan : listEnvoie){
			List<Integer> listEnvID = new ArrayList<Integer>();
			String listEnvString = typeBilan.getClauseSelect();
			if(listEnvString != null && !listEnvString.equals("")){
				String[] listID = listEnvString.split(";");
				for(String idString : listID){
					if(idString != null && !idString.trim().equals("")){
						listEnvID.add(Integer.parseInt(idString));
					}
				}
			}
			List<Environnement> listEnv = EnvironnementDatabaseService.getMultiple(listEnvID);
			
			listEnvironnement.put(typeBilan.getId(), listEnv);
		}
		
		return OK;
	}
}
