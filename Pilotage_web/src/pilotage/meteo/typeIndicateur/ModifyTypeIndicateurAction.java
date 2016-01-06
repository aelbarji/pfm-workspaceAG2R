package pilotage.meteo.typeIndicateur;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.meteo.TypeIndicateurDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_EtatPossible;
import pilotage.metier.Meteo_TypeIndic_EtatPoss;
import pilotage.metier.Meteo_TypeIndicateur;

public class ModifyTypeIndicateurAction extends AbstractAction{

	private static final long serialVersionUID = 5081910240284669737L;

	private Integer typeID;
	private String type;
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
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			Meteo_TypeIndicateur typeIndic = TypeIndicateurDatabaseService.get(typeID);
			
			//recupération des états
			listEtat = new ArrayList<Map<String,String>>();
			RedirectCreateTypeIndicateurAction.getListEtatToAdd(request, listEtat);
			
			//récupération des infos en base
			List<Meteo_EtatPossible> listEtatEnBase = new ArrayList<Meteo_EtatPossible>(); 
			List<Meteo_TypeIndic_EtatPoss> listTypeEtat = TypeIndicateurDatabaseService.getEtats(typeIndic);
			for(Meteo_TypeIndic_EtatPoss t : listTypeEtat){
				listEtatEnBase.add(t.getEtatPoss());
			}
			
			//Isolation des modifications des états
			List<Integer> etatToDelete = new ArrayList<Integer>();
			List<Integer> etatToAdd = new ArrayList<Integer>();
			for(Map<String,String> etat : listEtat){
				boolean alreadyInBase = false;
				Integer id = Integer.parseInt(etat.get(RedirectCreateTypeIndicateurAction.ETAT_ID));
				for(Meteo_EtatPossible et : listEtatEnBase){
					if(et.getId().equals(id)){
						alreadyInBase = true;
						break;
					}
				}
				if(!alreadyInBase){
					etatToAdd.add(id);
				}
			}
			for(Meteo_EtatPossible etat : listEtatEnBase){
				boolean stillInList = false;
				String idString = etat.getId().toString();
				for(Map<String,String> et : listEtat){
					if(et.get(RedirectCreateTypeIndicateurAction.ETAT_ID).equals(idString)){
						stillInList = true;
						break;
					}
				}
				if(!stillInList){
					etatToDelete.add(etat.getId());
				}
			}
			
			TypeIndicateurDatabaseService.modify(typeID, type, etatToAdd, etatToDelete);
			
			info = MessageFormat.format(getText("meteo.typeIndicateur.modification.valide"), type);
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'une Météo - ", e);
			return ERROR;
		}
	}

}
