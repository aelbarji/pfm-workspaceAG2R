package pilotage.meteo.environnement;

import java.text.MessageFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.meteo.MeteoEnvironnementCaisseDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_Environnement_Caisse;
public class ModifyEnvironnementMeteoAction extends AbstractAction{

	private static final long serialVersionUID = -3859272696810570260L;
	private Integer envirID;
	private String technique;
	private String envirNOM;
	private String impact;
	private List<Map<String, String>> listCaisse;

	public Integer getEnvirID() {
		return envirID;
	}

	public void setEnvirID(Integer envirID) {
		this.envirID = envirID;
	}

	public String getTechnique() {
		return technique;
	}

	public void setTechnique(String technique) {
		this.technique = technique;
	}

	public String getEnvirNOM() {
		return envirNOM;
	}

	public void setEnvirNOM(String envirNOM) {
		this.envirNOM = envirNOM;
	}

	public String getImpact() {
		return impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
	}

	public List<Map<String, String>> getListCaisse() {
		return listCaisse;
	}

	public void setListCaisse(List<Map<String, String>> listCaisse) {
		this.listCaisse = listCaisse;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			
			//recupération des caisses
			listCaisse = new ArrayList<Map<String,String>>();
			RedirectCreateEnvironnementMeteoAction.getListCaisseToAdd(request, listCaisse);
			
			//récupération des infos en base
			List<Meteo_Environnement_Caisse> listCaisseEnBase = MeteoEnvironnementCaisseDatabaseService.getListCaisseByEnvir(envirID);
			
			//Isolation des modifications des caisses
			List<Integer> caisseToDelete = new ArrayList<Integer>();
			List<Integer> caisseToAdd = new ArrayList<Integer>();
			for(Map<String,String> caisse : listCaisse){
				boolean alreadyInBase = false;
				Integer id = Integer.parseInt(caisse.get(RedirectCreateEnvironnementMeteoAction.CAISSE_ID));
				for(Meteo_Environnement_Caisse envirCaisse : listCaisseEnBase){
					if(envirCaisse.getCaisse().getId().equals(id)){
						alreadyInBase = true;
						break;
					}
				}
				if(!alreadyInBase){
					caisseToAdd.add(id);
				}
			}
			for(Meteo_Environnement_Caisse envirCaisse : listCaisseEnBase){
				boolean stillInList = false;
				String idString = envirCaisse.getCaisse().getId().toString();
				for(Map<String,String> caisse : listCaisse){
					if(caisse.get(RedirectCreateEnvironnementMeteoAction.CAISSE_ID).equals(idString)){
						stillInList = true;
						break;
					}
				}
				if(!stillInList){
					caisseToDelete.add(envirCaisse.getCaisse().getId());
				}
			}
		
			MeteoEnvironnementCaisseDatabaseService.modify(envirID, envirNOM, caisseToAdd, caisseToDelete, Integer.parseInt(impact));
			
			info = MessageFormat.format(getText("envirMeteo.modification.valide"), envirNOM);
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un environnement - ", e);
			return ERROR;
		}
	
	}

}
