package pilotage.meteo.environnement;

import java.text.MessageFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.meteo.MeteoEnvironnementDatabaseService;
import pilotage.framework.AbstractAction;

public class CreateEnvironnementMeteoAction extends AbstractAction{

	private static final long serialVersionUID = 2074794634386428751L;
	private String nom_envir;
	private String technique;
	private String impact;
	private List<Map<String, String>> listCaisse;

	public String getNom_envir() {
		return nom_envir;
	}

	public void setNom_envir(String nom_envir) {
		this.nom_envir = nom_envir;
	}

	public String getTechnique() {
		return technique;
	}

	public void setTechnique(String technique) {
		this.technique = technique;
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
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			
			//recupération des caisses à ajouter
			listCaisse = new ArrayList<Map<String,String>>();
			RedirectCreateEnvironnementMeteoAction.getListCaisseToAdd(request, listCaisse);
			
			//creation d'un environnement
			MeteoEnvironnementDatabaseService.create(nom_envir, technique, listCaisse, Integer.parseInt(impact));
			info = MessageFormat.format(getText("envirMeteo.creation.valide"),nom_envir);
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un environnement - ", e);
			return ERROR;
		}
	}

}
