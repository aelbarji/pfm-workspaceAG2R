package pilotage.meteo.meteo;

import java.text.MessageFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.meteo.MeteoDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_Service;
import pilotage.metier.Meteo_Service_Meteo;

public class ModifyMeteoAction extends AbstractAction{

	private static final long serialVersionUID = -8919001679886999803L;
	private int meteoID;
	private String nomMeteo;
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
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			
			//recupération des services
			listService = new ArrayList<Map<String,String>>();
			RedirectCreateMeteoAction.getListServiceToAdd(request, listService);
			
			//récupération des infos en base
			List<Meteo_Service> listServiceEnBase = new ArrayList<Meteo_Service>(); //MeteoDatabaseService.getListServices(meteoID);
			List<Meteo_Service_Meteo> listServiceMeteo =MeteoDatabaseService.getListMeteoService(meteoID);
			for(Meteo_Service_Meteo m : listServiceMeteo){
				if(m.getDateSuppression() == null){
					listServiceEnBase.add(m.getService());
				}
			}
			
			//Isolation des modifications des services
			List<Integer> serviceToDelete = new ArrayList<Integer>();
			List<Integer> serviceToAdd = new ArrayList<Integer>();
			for(Map<String,String> service : listService){
				boolean alreadyInBase = false;
				Integer id = Integer.parseInt(service.get(RedirectCreateMeteoAction.SERVICE_ID));
				for(Meteo_Service serv : listServiceEnBase){
					if(serv.getId().equals(id)){
						alreadyInBase = true;
						break;
					}
				}
				if(!alreadyInBase){
					serviceToAdd.add(id);
				}
			}
			for(Meteo_Service service : listServiceEnBase){
				boolean stillInList = false;
				String idString = service.getId().toString();
				for(Map<String,String> serv : listService){
					if(serv.get(RedirectCreateMeteoAction.SERVICE_ID).equals(idString)){
						stillInList = true;
						break;
					}
				}
				if(!stillInList){
					serviceToDelete.add(service.getId());
				}
			}
			
			nomMeteo.replace("'", "''''");
			MeteoDatabaseService.modify(meteoID, nomMeteo, serviceToAdd, serviceToDelete);
			
			info = MessageFormat.format(getText("meteo.modification.valide"), nomMeteo);
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'une Météo - ", e);
			return ERROR;
		}
	}

}
