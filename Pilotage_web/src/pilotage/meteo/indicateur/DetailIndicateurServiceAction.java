package pilotage.meteo.indicateur;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

import pilotage.database.meteo.EtatPossibleDatabaseService;
import pilotage.database.meteo.MeteoIndicateurDatabaseService;
import pilotage.database.meteo.MeteoServiceDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_EtatPossible;
import pilotage.metier.Meteo_Indicateur;
import pilotage.metier.Meteo_PlageFonct_Jour;
import pilotage.metier.Meteo_PlageFonctionnement;
import pilotage.metier.Meteo_TypeIndicateur;
import pilotage.service.date.DateService;

public class DetailIndicateurServiceAction extends AbstractAction{

	private static final long serialVersionUID = 500289959925587937L;

	private Integer idIndic;
	private Integer selectRow;
	private String nomService;
	private String nomEnvir;
	private Meteo_TypeIndicateur typeIndic;
	private Meteo_Indicateur indicMeteo;
	private List<Map<String,String>> listPlageFonct;

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

	public Meteo_TypeIndicateur getTypeIndic() {
		return typeIndic;
	}

	public void setTypeIndic(Meteo_TypeIndicateur typeIndic) {
		this.typeIndic = typeIndic;
	}

	public Meteo_Indicateur getIndicMeteo() {
		return indicMeteo;
	}

	public void setIndicMeteo(Meteo_Indicateur indicMeteo) {
		this.indicMeteo = indicMeteo;
	}

	public List<Map<String, String>> getListPlageFonct() {
		return listPlageFonct;
	}

	public void setListPlageFonct(List<Map<String, String>> listPlageFonct) {
		this.listPlageFonct = listPlageFonct;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try
		{
			nomService = MeteoServiceDatabaseService.get(selectRow).getService();
			indicMeteo = MeteoIndicateurDatabaseService.get(idIndic);
			typeIndic = indicMeteo.getTypeIndic();
			nomEnvir = indicMeteo.getEnvironnement().getNom_envir();
			listPlageFonct = getPlageFonctionnement(MeteoIndicateurDatabaseService.getListPlageActive(indicMeteo));			
			return OK;
		} 
		catch (Exception e) 
		{
			error = getText("error.message.generique")+ " : " + e.getMessage();
			erreurLogger.error("Modification d'une liste des services", e);
			return ERROR;
		}
	}
	
	private List<Map<String,String>> getPlageFonctionnement(List<Meteo_PlageFonctionnement> plages){
		List<Map<String,String>> listPlage = new ArrayList<Map<String,String>>();
		for(Meteo_PlageFonctionnement plage : plages){
			Map<String, String> al = new HashMap<String, String>();
			List<Meteo_PlageFonct_Jour> jours = MeteoIndicateurDatabaseService.getListJoursByPlage(plage);
			al.put("dimanche", "BoutonCheckbox0");
			al.put("lundi", "BoutonCheckbox0");
			al.put("mardi", "BoutonCheckbox0");
			al.put("mercredi", "BoutonCheckbox0");
			al.put("jeudi", "BoutonCheckbox0");
			al.put("vendredi", "BoutonCheckbox0");
			al.put("samedi", "BoutonCheckbox0");
			for(Meteo_PlageFonct_Jour j: jours){
				if(j.getFerie()==1){
					al.put("ferie", "BoutonCheckbox1");
				}
				if(j.getJour()==0){
					al.put("dimanche", "BoutonCheckbox1");
				}
				if(j.getJour()==1){
					al.put("lundi", "BoutonCheckbox1");
				}
				if(j.getJour()==2){
					al.put("mardi", "BoutonCheckbox1");
				}
				if(j.getJour()==3){
					al.put("mercredi", "BoutonCheckbox1");
				}
				if(j.getJour()==4){
					al.put("jeudi", "BoutonCheckbox1");
				}
				if(j.getJour()==5){
					al.put("vendredi", "BoutonCheckbox1");
				}
				if(j.getJour()==6){
					al.put("samedi", "BoutonCheckbox1");
				}
			}
			
			al.put("heureDebut", DateService.getTime(plage.getHeureDebut(), null));
			al.put("heureFin", DateService.getTime(plage.getHeureFin(), null));
			al.put("zone", plage.getZone().getTimezone());
			if(indicMeteo.getTypeIndic().getFormat().getFormat().equals("liste")){
				Integer desire = Integer.parseInt(plage.getEtatDesire());
				Meteo_EtatPossible etat = EtatPossibleDatabaseService.get(desire);
				al.put("etatDesire", etat.getLibelle_etat());
			}else{
				al.put("etatDesire", plage.getEtatDesire());
				if(indicMeteo.getTypeIndic().getFormat().getFormat().equals("reel")){
					al.put("seuil", plage.getSeuil().toString());
					al.put("perturbe_max", plage.getSeuil().getPertu_max());
					al.put("perturbe_min", plage.getSeuil().getPertu_min());
					al.put("ko_max", plage.getSeuil().getKo_max());
					al.put("ko_min", plage.getSeuil().getKo_min());
				}
			}
			
			listPlage.add(al);
		}
		return listPlage;
	}

}
