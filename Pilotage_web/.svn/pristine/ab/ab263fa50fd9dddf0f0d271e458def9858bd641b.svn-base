package pilotage.meteo.meteo;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.meteo.MeteoServiceDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_Meteo;
import pilotage.metier.Meteo_Service;
import pilotage.utils.Pagination;

public class RedirectCreateMeteoAction extends AbstractAction{

	private static final long serialVersionUID = 8856030299569614945L;

	public static final String SERVICE_ID = "serviceID";
	public static final String SERVICE_NAME = "serviceName";
	
	private List<Meteo_Service> serviceList;
	private Integer serviceToAdd;
	private Integer deleteService;
	private List<Map<String,String>> listService;
	private String nomMeteo;
	
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private Pagination<Meteo_Meteo> pagination;

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

	public List<Map<String,String>> getListService() {
		return listService;
	}

	public void setListService(List<Map<String,String>> listService) {
		this.listService = listService;
	}

	public String getNomMeteo() {
		return nomMeteo;
	}

	public void setNomMeteo(String nomMeteo) {
		this.nomMeteo = nomMeteo;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNrPages() {
		return nrPages;
	}

	public void setNrPages(int nrPages) {
		this.nrPages = nrPages;
	}

	public int getNrPerPage() {
		return nrPerPage;
	}

	public void setNrPerPage(int nrPerPage) {
		this.nrPerPage = nrPerPage;
	}

	public Pagination<Meteo_Meteo> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Meteo_Meteo> pagination) {
		this.pagination = pagination;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		HttpServletRequest request = ServletActionContext.getRequest();
		serviceList = MeteoServiceDatabaseService.getAll();
		
		//recupération des caisses à ajouter
		listService= new ArrayList<Map<String,String>>();
		getListServiceToAdd(request, listService);
		
		if((deleteService != null && deleteService != -1) || (serviceToAdd != null && serviceToAdd != -1)){
		
			nomMeteo = request.getParameter("nomMeteo");
			
			//Si on a cliqué sur la suppression d'un service
			if(deleteService != null && deleteService != -1){
				Map<String, String> mapToRemove = null;
				for(Map<String, String> service : listService){
					if(service.get(SERVICE_ID).equals(deleteService.toString())){
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
						Map<String, String> serviceMap = new HashMap<String, String>();
						serviceMap.put(SERVICE_ID, serviceToAdd.toString());
						listService.add(serviceMap);
						break;
					}
				}
			}
		}
		
		//suppression de la liste des services déjà affectés à la météo
		removeService(serviceList, listService);
		
		return OK;
	}
	
	/**
	 * Suppression des services de arg1 présents dans arg2
	 * @param serviceList
	 * @param listService
	 */
	public static void removeService(List<Meteo_Service> serviceList, List<Map<String, String>> listService) {
		List<Meteo_Service> serviceToRemove = new ArrayList<Meteo_Service>();
		for(Meteo_Service service : serviceList){
			for(Map<String, String> serviceToAdd : listService){
				if(service.getId().toString().equals(serviceToAdd.get(SERVICE_ID))){
					serviceToAdd.put(SERVICE_NAME, service.getService());
					serviceToRemove.add(service);
				}
			}
		}
		serviceList.removeAll(serviceToRemove);
	}

	/**
	 * Récupération des services que l'utilisateur veut ajouter
	 * @param request
	 * @param listService
	 */
	public static void getListServiceToAdd(HttpServletRequest request, List<Map<String, String>> listService) {
		int i = 0;
		while(request.getParameter("service" + i) != null){
			Map<String, String> service = new HashMap<String, String>();
			service.put(SERVICE_ID, request.getParameter("service" + i));
			listService.add(service);
			
			++i;
		}

	}


}
