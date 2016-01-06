package pilotage.meteo.meteo;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.meteo.MeteoDatabaseService;
import pilotage.database.meteo.MeteoServiceDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_Meteo;
import pilotage.metier.Meteo_Service;
import pilotage.metier.Meteo_Service_Meteo;

public class RedirectModifyMeteoAction extends AbstractAction{

	private static final long serialVersionUID = -4630337775321556990L;
	private int meteoID;
	private String nomMeteo;
	private Meteo_Meteo meteo;
	private List<Meteo_Service> serviceList;
	private Integer serviceToAdd;
	private Integer deleteService;
	private List<Map<String, String>> listService;

	public int getMeteoID() {
		return meteoID;
	}

	public void setMeteoID(int meteoID) {
		this.meteoID = meteoID;
	}

	public String getNomMeteo() {
		return nomMeteo;
	}

	public void setNomMeteo(String nomMeteo) {
		this.nomMeteo = nomMeteo;
	}

	public Meteo_Meteo getMeteo() {
		return meteo;
	}

	public void setMeteo(Meteo_Meteo meteo) {
		this.meteo = meteo;
	}

	public List<Meteo_Service> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<Meteo_Service> serviceList) {
		this.serviceList = serviceList;
	}

	public Integer getServiceToAdd() {
		return serviceToAdd;
	}

	public void setServiceToAdd(Integer serviceToAdd) {
		this.serviceToAdd = serviceToAdd;
	}

	public Integer getDeleteService() {
		return deleteService;
	}

	public void setDeleteService(Integer deleteService) {
		this.deleteService = deleteService;
	}

	public List<Map<String, String>> getListService() {
		return listService;
	}

	public void setListService(List<Map<String, String>> listService) {
		this.listService = listService;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		HttpServletRequest request = ServletActionContext.getRequest();
		meteo = MeteoDatabaseService.get(meteoID);
		serviceList = MeteoServiceDatabaseService.getAll();		
		
		//Si on a cliqué sur suppr/ajour d'un service
		if((deleteService != null && deleteService != -1) || (serviceToAdd != null && serviceToAdd != -1)){
			
			//recupération des services à ajouter
			listService= new ArrayList<Map<String,String>>();
			RedirectCreateMeteoAction.getListServiceToAdd(request, listService);
			
			//Si on a cliqué sur la suppression d'un service
			if(deleteService != null && deleteService != -1){
				Map<String, String> mapToRemove = null;
				for(Map<String, String> service : listService){
					if(service.get(RedirectCreateMeteoAction.SERVICE_ID).equals(deleteService.toString())){
						mapToRemove = service;
						break;
					}
				}
				if(mapToRemove != null)
					listService.remove(mapToRemove);
			}
			//si on a cliqué sur l'ajout d'un service, on l'ajoute aux services à ajouter
			else if(serviceToAdd != null && serviceToAdd != -1){
				for(Meteo_Service service : serviceList){
					if(service.getId().equals(serviceToAdd)){
						Map<String, String> serv = new HashMap<String, String>();
						serv.put(RedirectCreateMeteoAction.SERVICE_ID, serviceToAdd.toString());
						listService.add(serv);
						break;
					}
				}
			}
			RedirectCreateMeteoAction.removeService(serviceList, listService);
		}else{
			//init des infos de la Météo
			nomMeteo = meteo.getNom_meteo();

			//init des services
			List<Meteo_Service_Meteo> listMeteoService = MeteoDatabaseService.getListMeteoService(meteo.getId());
			listService = new ArrayList<Map<String,String>>();
			for(Meteo_Service_Meteo ms : listMeteoService){
				Map<String, String> serv = new HashMap<String, String>();
				if(ms.getDateSuppression() == null){
					serv.put(RedirectCreateMeteoAction.SERVICE_ID, ms.getService().getId().toString());
					listService.add(serv);
				}
			}
			
			RedirectCreateMeteoAction.removeService(serviceList, listService);
		}
			return OK;
	}

}
