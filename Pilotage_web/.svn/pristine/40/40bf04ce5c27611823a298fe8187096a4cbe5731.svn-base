package pilotage.meteo.indicateur;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.meteo.MeteoIndicateurDatabaseService;
import pilotage.database.meteo.MeteoServiceDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_Indicateur;

public class ModifyIndicateurServiceAction extends AbstractAction{

	private static final long serialVersionUID = -3032514402734603351L;

	private Integer selectRow;
	private String nomService;
	private List<Map<String, String>> listIndics;

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public String getNomService() {
		return nomService;
	}

	public void setNomService(String nomService) {
		this.nomService = nomService;
	}

	public List<Map<String, String>> getListIndics() {
		return listIndics;
	}

	public void setListIndics(List<Map<String, String>> listIndics) {
		this.listIndics = listIndics;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			
			//recupération des indicateurs
			listIndics = new ArrayList<Map<String,String>>();
			RedirectModifyIndicateurServiceAction.getListIndicToAdd(request, listIndics);
			
			//récupération des infos en base
			List<Meteo_Indicateur> indics = MeteoIndicateurDatabaseService.getListIndicByService(MeteoServiceDatabaseService.get(selectRow));
			List<Map<String,String>> listIndicEnBase = new ArrayList<Map<String,String>>();
			for(Meteo_Indicateur m : indics)
			{
				if(m.getDateSuppression()==null)
				{
					Map<String, String> al = new HashMap<String, String>();
					al.put("idIndic", Integer.toString(m.getId()));
					al.put("idServ", Integer.toString(m.getService().getId()));
					al.put("idEnvir", Integer.toString(m.getEnvironnement().getId()));
					al.put("envir", m.getEnvironnement().getNom_envir());
					al.put("auto", Integer.toString(m.getAutomatique()));
					if(m.getAutomatique()==1){
						al.put("autoOui", "checked");
						al.put("autoNon", "");
					}else{
						al.put("autoOui", "");
						al.put("autoNon", "checked");
					}
					al.put("debut", Integer.toString(m.getHeureDebut()));
					if(m.getHeureDebut()==1){
						al.put("debutOui", "checked");
						al.put("debutNon", "");
					}else{
						al.put("debutOui", "");
						al.put("debutNon", "checked");
					}
					al.put("fin", Integer.toString(m.getHeureFin()));
					if(m.getHeureFin()==1){
						al.put("finOui", "checked");
						al.put("finNon", "");
					}else{
						al.put("finOui", "");
						al.put("finNon", "checked");
					}
					al.put("type", m.getTypeIndic().getFormat().getFormat());
					
					listIndicEnBase.add(al);
				}
			}
			
			//Isolation des modifications des indicateurs
			List<Integer> indicToDelete = new ArrayList<Integer>();
			List<Map<String, String>> indicToChange = new ArrayList<Map<String, String>>();
			
			for(Map<String,String> indic : listIndics){
				boolean alreadyInBase = false;
				Map<String,String> add = indic;
				for(Map<String,String> indicEnBase : listIndicEnBase){				
					if(indic.get("idIndic").equals(indicEnBase.get("idIndic"))
						&& indicEnBase.get("auto").equals(indic.get("auto"))
						&& indicEnBase.get("debut").equals(indic.get("debut"))
						&& indicEnBase.get("fin").equals(indic.get("fin"))){
						alreadyInBase = true;
						break;
					}
				}
				if(!alreadyInBase){
					indicToChange.add(add);
				}
			}
			
			for(Map<String,String> indicEnBase : listIndicEnBase){
				boolean stillInList = false;
				for(Map<String,String> indic : listIndics){
					if(indic.get("idIndic").equals(indicEnBase.get("idIndic"))){
						stillInList = true;
						break;
					}
				}
				if(!stillInList){
					Integer id = Integer.parseInt(indicEnBase.get("idIndic"));
					indicToDelete.add(id);
				}
			}
			
			MeteoIndicateurDatabaseService.modifyIndicateur(selectRow, indicToChange, indicToDelete);
			nomService = MeteoServiceDatabaseService.get(selectRow).getService();
			
			info = MessageFormat.format(getText("indicateur.modification.valide"), nomService);
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification des indicateurs d'un service - ", e);
			return ERROR;
		}
	
	}

}
