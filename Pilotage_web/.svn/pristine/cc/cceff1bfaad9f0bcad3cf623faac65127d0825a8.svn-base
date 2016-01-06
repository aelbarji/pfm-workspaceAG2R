package pilotage.meteo.meteo;

import java.text.MessageFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.meteo.MeteoDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_Meteo;
import pilotage.utils.Pagination;

public class CreateMeteoAction extends AbstractAction{

	private static final long serialVersionUID = 9222064199889849646L;
	
	private String nomMeteo;
	private List<Map<String, String>> listService;
	
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private Pagination<Meteo_Meteo> pagination;

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
		try {		
			HttpServletRequest request = ServletActionContext.getRequest();
			
			//recupération des services à ajouter
			listService= new ArrayList<Map<String,String>>();
			RedirectCreateMeteoAction.getListServiceToAdd(request, listService);
			
			//creation d'une nouvelle Météo
			nomMeteo.replace("'", "''''");
			MeteoDatabaseService.create(nomMeteo, listService);
			info = MessageFormat.format(getText("meteo.creation.valide"),nomMeteo);
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'une Météo - ", e);
			return ERROR;
		}
	}
}
