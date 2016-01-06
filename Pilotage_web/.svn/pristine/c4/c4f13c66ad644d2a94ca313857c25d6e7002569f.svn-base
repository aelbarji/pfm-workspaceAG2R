package pilotage.meteo.typeIndicateur;

import java.text.MessageFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.meteo.TypeIndicateurDatabaseService;
import pilotage.framework.AbstractAction;

public class CreateTypeIndicateurAction extends AbstractAction{

	private static final long serialVersionUID = 2316053140459466657L;

	private String type;
	private List<Map<String, String>> listEtat;
	
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
		try{
			if(TypeIndicateurDatabaseService.exists(null, type)){
				error = MessageFormat.format(getText("meteo.typeIndicateur.creation.existe.deja"), type);
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un type indicateur Météo - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			
			//recupération des états à ajouter
			listEtat= new ArrayList<Map<String,String>>();
			RedirectCreateTypeIndicateurAction.getListEtatToAdd(request, listEtat);
			
			//creation d'un nouveau type d'indicateur Météo
			TypeIndicateurDatabaseService.create(type, listEtat);
			return OK;
		} 
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Creation d'un type indicateur Météo - ", e);
			return ERROR;
		}
	}

}
