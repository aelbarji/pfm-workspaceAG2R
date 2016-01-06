package pilotage.meteo.indicateur;

import java.util.List;

import pilotage.database.meteo.MeteoEnvironnementDatabaseService;
import pilotage.database.meteo.MeteoIndicateurDatabaseService;
import pilotage.database.meteo.MeteoServiceDatabaseService;
import pilotage.database.meteo.TypeIndicateurDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_Environnement;
import pilotage.metier.Meteo_Indicateur;
import pilotage.metier.Meteo_Service;
import pilotage.metier.Meteo_TypeIndicateur;

public class RedirectCreateIndicateurServiceAction extends AbstractAction{

	private static final long serialVersionUID = 3816086192097792297L;

	private Integer selectRow;
	private Meteo_Service service;
	private String nomService;
	private List<Meteo_Environnement> listEnvir;
	private List<Meteo_TypeIndicateur> listTypeIndic;
	
	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public Meteo_Service getService() {
		return service;
	}

	public void setService(Meteo_Service service) {
		this.service = service;
	}

	public String getNomService() {
		return nomService;
	}

	public void setNomService(String nomService) {
		this.nomService = nomService;
	}

	public List<Meteo_Environnement> getListEnvir() {
		return listEnvir;
	}

	public void setListEnvir(List<Meteo_Environnement> listEnvir) {
		this.listEnvir = listEnvir;
	}

	public List<Meteo_TypeIndicateur> getListTypeIndic() {
		return listTypeIndic;
	}

	public void setListTypeIndic(List<Meteo_TypeIndicateur> listTypeIndic) {
		this.listTypeIndic = listTypeIndic;
	}
	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		service = MeteoServiceDatabaseService.get(selectRow);
		nomService = service.getService();
		listEnvir = MeteoEnvironnementDatabaseService.getAll();
		listTypeIndic = TypeIndicateurDatabaseService.getAll();
		List<Meteo_Indicateur> listIndic = MeteoIndicateurDatabaseService.getListIndicByService(service);
		
		for(Meteo_Indicateur mi : listIndic){
			for(int i=0;i<listEnvir.size();i++){
				if(listEnvir.get(i).getId()==mi.getEnvironnement().getId()){
					listEnvir.remove(i);
				}
			}
		}
		
		return OK;
	}

}
