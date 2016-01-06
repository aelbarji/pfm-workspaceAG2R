package pilotage.services.actions.liste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pilotage.database.meteo.MeteoIndicateurDatabaseService;
import pilotage.database.meteo.MeteoServiceDatabaseService;
import pilotage.database.services.ServicesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_Indicateur;
import pilotage.metier.Services_Liste;

public class DetailServicesListeAction extends AbstractAction{

	private static final long serialVersionUID = 1281809524563163410L;

	private Integer selectRow;
	private String nomService;
	private Services_Liste service;
	private List<Map<String,String>> listIndic;
	private boolean champheure;

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

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public List<Map<String,String>> getListIndic() {
		return listIndic;
	}

	public void setListIndic(List<Map<String,String>> listIndic) {
		this.listIndic = listIndic;
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
		try
		{
			service = ServicesDatabaseService.get(selectRow);
			nomService = service.getNomService();
			listIndic = new ArrayList<Map<String,String>>();
			List<Meteo_Indicateur> indics = MeteoIndicateurDatabaseService.getListIndicByService(MeteoServiceDatabaseService.get(selectRow));
			
			champheure = false;
			for(Meteo_Indicateur m : indics){
				if(!m.getTypeIndic().getFormat().getFormat().equals("datetime")){
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
						al.put("autoOui", "BoutonRadio1");
						al.put("autoNon", "BoutonRadio0");
					}else{
						al.put("autoOui", "BoutonRadio0");
						al.put("autoNon", "BoutonRadio1");
					}
					al.put("debut", Integer.toString(m.getHeureDebut()));
					if(m.getHeureDebut()==1){
						al.put("debutOui", "BoutonRadio1");
						al.put("debutNon", "BoutonRadio0");
					}else{
						al.put("debutOui", "BoutonRadio0");
						al.put("debutNon", "BoutonRadio1");
					}
					al.put("fin", Integer.toString(m.getHeureFin()));
					if(m.getHeureFin()==1){
						al.put("finOui", "BoutonRadio1");
						al.put("finNon", "BoutonRadio0");
					}else{
						al.put("finOui", "BoutonRadio0");
						al.put("finNon", "BoutonRadio1");
					}
					al.put("type", m.getTypeIndic().getFormat().getFormat());
					
					listIndic.add(al);
				}
			}
			
			return OK;
		} 
		catch (Exception e) 
		{
			error = getText("error.message.generique")+ " : " + e.getMessage();
			erreurLogger.error("Détail indicateurs des services", e);
			return ERROR;
		}
	}

}
