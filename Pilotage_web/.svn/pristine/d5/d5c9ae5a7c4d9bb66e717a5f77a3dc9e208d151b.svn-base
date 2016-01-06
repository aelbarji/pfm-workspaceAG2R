package pilotage.meteo.indicateur;

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
import pilotage.metier.Meteo_Service;

public class RedirectModifyIndicateurServiceAction  extends AbstractAction{

	private static final long serialVersionUID = -679301306789363248L;

	private Integer selectRow;
	private String nomService;
	private Meteo_Service service;
	private List<Meteo_Indicateur> indicList;
	private Integer deleteIndic;
	private List<Map<String, String>> listIndics;
	private boolean champheure;

	public Meteo_Service getService() {
		return service;
	}

	public void setService(Meteo_Service service) {
		this.service = service;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}
	
	public Integer getSelectRow() {
		return selectRow;
	}

	public String getNomService() {
		return nomService;
	}

	public void setNomService(String nomService) {
		this.nomService = nomService;
	}

	public List<Meteo_Indicateur> getIndicList() {
		return indicList;
	}

	public void setIndicList(List<Meteo_Indicateur> indicList) {
		this.indicList = indicList;
	}

	public Integer getDeleteIndic() {
		return deleteIndic;
	}

	public void setDeleteIndic(Integer deleteIndic) {
		this.deleteIndic = deleteIndic;
	}
	public List<Map<String, String>> getListIndics() {
		return listIndics;
	}

	public void setListIndics(List<Map<String, String>> listIndics) {
		this.listIndics = listIndics;
	}
	
	public boolean isChampheure() {
		return champheure;
	}

	public void setChampheure(boolean champheure) {
		this.champheure = champheure;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		HttpServletRequest request = ServletActionContext.getRequest();
		service = MeteoServiceDatabaseService.get(selectRow);
		
		
		//Si on a cliqué sur suppr d'un indicateur
		if((deleteIndic != null && deleteIndic != -1)){
			
			nomService = service.getService();
			
			//recupération des indicateurs à ajouter
			listIndics= new ArrayList<Map<String,String>>();
			getListIndicToAdd(request, listIndics);		
			
			//Si on a cliqué sur la suppression d'un indicateur
			if(deleteIndic != null && deleteIndic != -1){
				Map<String, String> mapToRemove = null;
				for(Map<String, String> indic : listIndics){
					if(indic.get("idIndic").equals(deleteIndic.toString())){
						mapToRemove = indic;
						break;
					}
					Meteo_Indicateur ind = MeteoIndicateurDatabaseService.get(Integer.parseInt(indic.get("idIndic")));
					if(!ind.getTypeIndic().getFormat().getFormat().equals("datetime")) champheure = true;
				}
				if(mapToRemove != null)
					listIndics.remove(mapToRemove);
			}
		}else{
			nomService = service.getService();
			List<Meteo_Indicateur> indics = MeteoIndicateurDatabaseService.getListIndicByService(MeteoServiceDatabaseService.get(selectRow));
			List<Map<String,String>> listIndic = new ArrayList<Map<String,String>>();
			
			champheure = false;
			for(Meteo_Indicateur ind : indics){
				if(!ind.getTypeIndic().getFormat().getFormat().equals("datetime")){
					champheure = true;
					break;
				}
			}
			
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
					
					listIndic.add(al);
				}
			}
			setListIndics(listIndic);
		}
		return OK;
	}

	public static void getListIndicToAdd(HttpServletRequest request, List<Map<String, String>> listIndics) {
		int i = 0;
		while(request.getParameter("envir" + i) != null){
			Map<String, String> al = new HashMap<String, String>();
			al.put("envir", request.getParameter("envir" + i));
			al.put("auto", request.getParameter("auto"+i));
			if(Integer.parseInt(request.getParameter("auto" + i))==1){
				al.put("autoOui", "checked");
				al.put("autoNon", "");
			}else{
				al.put("autoOui", "");
				al.put("autoNon", "checked");
			}
			if(request.getParameter("debut"+i)!=null){
				al.put("debut", request.getParameter("debut"+i));
				if(Integer.parseInt(request.getParameter("debut" + i))==1){
					al.put("debutOui", "checked");
					al.put("debutNon", "");
				}else{
					al.put("debutOui", "");
					al.put("debutNon", "checked");
				}
			}
			if(request.getParameter("fin"+i)!=null){
				al.put("fin", request.getParameter("fin"+i));
				if(Integer.parseInt(request.getParameter("fin" + i))==1){
					al.put("finOui", "checked");
					al.put("finNon", "");
				}else{
					al.put("finOui", "");
					al.put("finNon", "checked");
				}
			}
			al.put("type", request.getParameter("type" + i));
			al.put("idIndic", request.getParameter("idIndic"+i));
			listIndics.add(al);
			++i;
		}
	}

	

}
