package pilotage.meteo.typeIndicateur;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.meteo.EtatPossibleDatabaseService;
import pilotage.database.meteo.TypeIndicateurDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_EtatPossible;
import pilotage.metier.Meteo_TypeIndic_EtatPoss;
import pilotage.metier.Meteo_TypeIndicateur;

public class RedirectModifyTypeIndicateurAction extends AbstractAction{

	private static final long serialVersionUID = -8101490289498415227L;

	private Integer typeID;
	private String type;
	private Meteo_TypeIndicateur typeIndic;
	private List<Meteo_EtatPossible> etatList;
	private Integer etatToAdd;
	private Integer deleteEtat;
	private List<Map<String, String>> listEtat;

	public Integer getTypeID() {
		return typeID;
	}

	public void setTypeID(Integer typeID) {
		this.typeID = typeID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Meteo_TypeIndicateur getTypeIndic() {
		return typeIndic;
	}

	public void setTypeIndic(Meteo_TypeIndicateur typeIndic) {
		this.typeIndic = typeIndic;
	}

	public List<Meteo_EtatPossible> getEtatList() {
		return etatList;
	}

	public void setEtatList(List<Meteo_EtatPossible> etatList) {
		this.etatList = etatList;
	}

	public Integer getEtatToAdd() {
		return etatToAdd;
	}

	public void setEtatToAdd(Integer etatToAdd) {
		this.etatToAdd = etatToAdd;
	}

	public Integer getDeleteEtat() {
		return deleteEtat;
	}

	public void setDeleteEtat(Integer deleteEtat) {
		this.deleteEtat = deleteEtat;
	}

	public List<Map<String, String>> getListEtat() {
		return listEtat;
	}

	public void setListEtat(List<Map<String, String>> listEtat) {
		this.listEtat = listEtat;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		HttpServletRequest request = ServletActionContext.getRequest();
		typeIndic = TypeIndicateurDatabaseService.get(typeID);
		etatList = EtatPossibleDatabaseService.getAll();		
		
		//Si on a cliqué sur suppr/ajour d'un état
		if((deleteEtat != null && deleteEtat != -1) || (etatToAdd != null && etatToAdd != -1)){
			
			//recupération des états à ajouter
			listEtat= new ArrayList<Map<String,String>>();
			RedirectCreateTypeIndicateurAction.getListEtatToAdd(request, listEtat);
			
			//Si on a cliqué sur la suppression d'un état
			if(deleteEtat != null && deleteEtat != -1){
				Map<String, String> mapToRemove = null;
				for(Map<String, String> etat : listEtat){
					if(etat.get(RedirectCreateTypeIndicateurAction.ETAT_ID).equals(deleteEtat.toString())){
						mapToRemove = etat;
						break;
					}
				}
				if(mapToRemove != null)
					listEtat.remove(mapToRemove);
			}
			//si on a cliqué sur l'ajout d'un état, on l'ajoute aux états à ajouter
			else if(etatToAdd != null && etatToAdd != -1){
				for(Meteo_EtatPossible etat : etatList){
					if(etat.getId().equals(etatToAdd)){
						Map<String, String> e = new HashMap<String, String>();
						e.put(RedirectCreateTypeIndicateurAction.ETAT_ID, etatToAdd.toString());
						listEtat.add(e);
						break;
					}
				}
			}
			RedirectCreateTypeIndicateurAction.removeEtat(etatList, listEtat);
		}else{
			//init des infos du type indicateur
			type = typeIndic.getType();

			//init des états
			List<Meteo_TypeIndic_EtatPoss> listTypeEtat = TypeIndicateurDatabaseService.getEtats(typeIndic);
			listEtat = new ArrayList<Map<String,String>>();
			for(Meteo_TypeIndic_EtatPoss te : listTypeEtat){
				Map<String, String> etat = new HashMap<String, String>();
				etat.put(RedirectCreateTypeIndicateurAction.ETAT_ID, te.getEtatPoss().getId().toString());
				listEtat.add(etat);
			}
			
			RedirectCreateTypeIndicateurAction.removeEtat(etatList, listEtat);
		}
			return OK;
	}

}
