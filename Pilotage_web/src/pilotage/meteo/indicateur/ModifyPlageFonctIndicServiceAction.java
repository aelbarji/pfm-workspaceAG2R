package pilotage.meteo.indicateur;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.meteo.MeteoIndicateurDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.meteo.indicateur.CreatePlageFonctIndicServiceAction;
import pilotage.metier.Services_Liste;

public class ModifyPlageFonctIndicServiceAction extends AbstractAction{

	private static final long serialVersionUID = -6364202471879380281L;

	private Integer idIndic;
	private Integer selectRow;
	private Services_Liste service;
	private String nomService;
	private String nomEnvir;
	private List<Map<String,String>> listPlage;

	public Integer getIdIndic() {
		return idIndic;
	}

	public void setIdIndic(Integer idIndic) {
		this.idIndic = idIndic;
	}

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public Services_Liste getService() {
		return service;
	}

	public void setService(Services_Liste service) {
		this.service = service;
	}

	public String getNomService() {
		return nomService;
	}

	public void setNomService(String nomService) {
		this.nomService = nomService;
	}

	public String getNomEnvir() {
		return nomEnvir;
	}

	public void setNomEnvir(String nomEnvir) {
		this.nomEnvir = nomEnvir;
	}

	public List<Map<String, String>> getListPlage() {
		return listPlage;
	}

	public void setListPlage(List<Map<String, String>> listPlage) {
		this.listPlage = listPlage;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			listPlage = new ArrayList<Map<String,String>>();
			CreatePlageFonctIndicServiceAction.getListPlageToAdd(request, listPlage);		
			MeteoIndicateurDatabaseService.modifyPlageFonctionnement(idIndic, listPlage);		
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification des plages de fonctionnement - ", e);
			return ERROR;
		}
	}
}
