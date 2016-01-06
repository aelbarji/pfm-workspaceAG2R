package pilotage.meteo.saisie;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pilotage.database.meteo.EtatCourantDatabaseService;
import pilotage.database.meteo.EtatPossibleDatabaseService;
import pilotage.database.meteo.GroupeMeteoCommentaireDatabaseService;
import pilotage.database.meteo.GroupeMeteoDatabaseService;
import pilotage.database.meteo.GroupeMeteoHoraireDatabaseService;
import pilotage.database.meteo.MeteoCommentaireDatabaseService;
import pilotage.database.meteo.MeteoDatabaseService;
import pilotage.database.meteo.MeteoEnvironnementDatabaseService;
import pilotage.database.meteo.MeteoEnvoiDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_Commentaire;
import pilotage.metier.Meteo_Environnement_Place;
import pilotage.metier.Meteo_Envoi;
import pilotage.metier.Meteo_EtatCourant;
import pilotage.metier.Meteo_GroupeMeteo;
import pilotage.metier.Meteo_Indicateur;
import pilotage.metier.Meteo_IndicateurBase;
import pilotage.metier.Meteo_Service_Meteo;
import pilotage.metier.Meteo_SeuilPlage;
import pilotage.metier.Meteo_TypeIndic_EtatPoss;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;

public class ModifySaisieMeteoAction extends AbstractAction{

	private static final long serialVersionUID = -5435153358449128968L;
	
	private Integer groupeMeteoID;
	private Meteo_GroupeMeteo groupe;
	private String heure;
	private String date;
	private String heureSaisie;
	private String heureEnvoi;
	private String dateEnvoi;
	private String commentaire;
	private List<Map<String,String>> horaires;
	private List<Meteo_GroupeMeteo> listGroupeMeteo;
	
	private List<Meteo_IndicateurBase> indicateurs;
	private List<List<Meteo_Environnement_Place>> listEnvirMeteo;
	private List<List<Meteo_Indicateur>> listEnvirTrie;
	private List<List<List<Meteo_TypeIndic_EtatPoss>>> listEtat;
	private List<List<String>> couleurs;
	private List<List<String>> tableau_th;
	private List<List<String>> comment_indicateur;
	private List<String> commentaire_meteo;
	
	private String dateGMeteo;
	
	private List<List<Meteo_IndicateurBase>> listIndic;

	public Integer getGroupeMeteoID() {
		return groupeMeteoID;
	}

	public void setGroupeMeteoID(Integer groupeMeteoID) {
		this.groupeMeteoID = groupeMeteoID;
	}

	public String getHeure() {
		return heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHeureSaisie() {
		return heureSaisie;
	}

	public void setHeureSaisie(String heureSaisie) {
		this.heureSaisie = heureSaisie;
	}

	public Meteo_GroupeMeteo getGroupe() {
		return groupe;
	}

	public void setGroupe(Meteo_GroupeMeteo groupe) {
		this.groupe = groupe;
	}

	public String getHeureEnvoi() {
		return heureEnvoi;
	}

	public void setHeureEnvoi(String heureEnvoi) {
		this.heureEnvoi = heureEnvoi;
	}

	public String getDateEnvoi() {
		return dateEnvoi;
	}

	public void setDateEnvoi(String dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public List<Map<String,String>> getHoraires() {
		return horaires;
	}

	public void setHoraires(List<Map<String,String>> horaires) {
		this.horaires = horaires;
	}

	public List<Meteo_GroupeMeteo> getListGroupeMeteo() {
		return listGroupeMeteo;
	}

	public void setListGroupeMeteo(List<Meteo_GroupeMeteo> listGroupeMeteo) {
		this.listGroupeMeteo = listGroupeMeteo;
	}

	public List<Meteo_IndicateurBase> getIndicateurs() {
		return indicateurs;
	}

	public void setIndicateurs(List<Meteo_IndicateurBase> indicateurs) {
		this.indicateurs = indicateurs;
	}

	public List<List<Meteo_Environnement_Place>> getListEnvirMeteo() {
		return listEnvirMeteo;
	}

	public void setListEnvirMeteo(List<List<Meteo_Environnement_Place>> listEnvirMeteo) {
		this.listEnvirMeteo = listEnvirMeteo;
	}

	public List<List<Meteo_Indicateur>> getListEnvirTrie() {
		return listEnvirTrie;
	}

	public void setListEnvirTrie(List<List<Meteo_Indicateur>> listEnvirTrie) {
		this.listEnvirTrie = listEnvirTrie;
	}

	public List<List<List<Meteo_TypeIndic_EtatPoss>>> getListEtat() {
		return listEtat;
	}

	public void setListEtat(List<List<List<Meteo_TypeIndic_EtatPoss>>> listEtat) {
		this.listEtat = listEtat;
	}

	public List<List<Meteo_IndicateurBase>> getListIndic() {
		return listIndic;
	}

	public void setListIndic(List<List<Meteo_IndicateurBase>> listIndic) {
		this.listIndic = listIndic;
	}

	public List<List<String>> getCouleurs() {
		return couleurs;
	}

	public void setCouleurs(List<List<String>> couleurs) {
		this.couleurs = couleurs;
	}

	public List<List<String>> getTableau_th() {
		return tableau_th;
	}

	public void setTableau_th(List<List<String>> tableau_th) {
		this.tableau_th = tableau_th;
	}

	public List<List<String>> getComment_indicateur() {
		return comment_indicateur;
	}

	public void setComment_indicateur(List<List<String>> comment_indicateur) {
		this.comment_indicateur = comment_indicateur;
	}

	public List<String> getCommentaire_meteo() {
		return commentaire_meteo;
	}

	public void setCommentaire_meteo(List<String> commentaire_meteo) {
		this.commentaire_meteo = commentaire_meteo;
	}

	public String getDateGMeteo() {
		return dateGMeteo;
	}

	public void setDateGMeteo(String dateGMeteo) {
		this.dateGMeteo = dateGMeteo;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			horaires = new ArrayList<Map<String,String>>();
			DateFormat formatD = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat formatBase = new SimpleDateFormat("yyyy-MM-dd");
			Date dateMeteo = DateService.strToDate(dateGMeteo);
			date = formatD.format(dateMeteo);
			String dateBase = formatBase.format(dateMeteo);
			dateEnvoi = "";
			
			listGroupeMeteo = GroupeMeteoDatabaseService.getAll();
			listIndic = new ArrayList<List<Meteo_IndicateurBase>>();
			
			indicateurs = new ArrayList<Meteo_IndicateurBase>();
			
			List<Date> listHoraire = GroupeMeteoHoraireDatabaseService.getHoraires(groupeMeteoID, null);
			for(Date date : listHoraire){
				Map<String,String> mapHoraire = new HashMap<String,String>();
				mapHoraire.put(PilotageConstants.HORAIRE, date.toString());
				horaires.add(mapHoraire);
			}

			if(groupeMeteoID != null && groupeMeteoID != -1 && (heure != null && !heure.equals("-1"))){
				
				String datetime;

				datetime = dateBase+" "+heure;
				indicateurs = GroupeMeteoDatabaseService.createGroupeMeteo(groupeMeteoID, dateBase, datetime, heure);
				listEnvirTrie = new ArrayList<List<Meteo_Indicateur>>();
				listEtat = new ArrayList<List<List<Meteo_TypeIndic_EtatPoss>>>();
				List<List<Meteo_TypeIndic_EtatPoss>> listEtatService = new ArrayList<List<Meteo_TypeIndic_EtatPoss>>();
				listEnvirMeteo =  new ArrayList<List<Meteo_Environnement_Place>>();
				List<Meteo_Environnement_Place> listEnvir = new ArrayList<Meteo_Environnement_Place>();
				List<Meteo_IndicateurBase> indicMeteo = new ArrayList<Meteo_IndicateurBase>();
				couleurs = new ArrayList<List<String>>();
				List<String> couleur = new ArrayList<String>();
				commentaire_meteo = new ArrayList<String>();
				comment_indicateur = new ArrayList<List<String>>();
				List<String> commentaire_indic = new ArrayList<String>();
				
				tableau_th = new ArrayList<List<String>>();
				List<String> tableau_th_envir = new ArrayList<String>();
				List<Integer> indic_envir = new ArrayList<Integer>();
				
				int index = 0;
				int j; int k = index;
				int ind_th = 0;
				int debut = 0;
				int fin = 0;
				
				groupe = GroupeMeteoDatabaseService.get(groupeMeteoID);
				Date dateHoraire = DateService.getTimeFromString(heure);
							
				commentaire = "";
				if(GroupeMeteoCommentaireDatabaseService.get(groupe, dateMeteo, dateHoraire) != null)
					commentaire = GroupeMeteoCommentaireDatabaseService.get(groupe, dateMeteo, dateHoraire).getCommentaire();
				
				boolean place = false;
				
		
				for(j=0;j<indicateurs.size();j++){
					
					listEnvir =  MeteoEnvironnementDatabaseService.getPlaceEnvirMeteo(indicateurs.get(j).getMeteo());

					Meteo_EtatCourant etatDernier = null;
					String etatVoulu = null;
					String indic_comment = "";
					etatDernier = EtatCourantDatabaseService.getByIndicDate2(indicateurs.get(j).getId(), dateMeteo, dateHoraire);
					if(etatDernier != null){
						etatVoulu = indicateurs.get(j).getEtat_desire();
						indicateurs.get(j).setEtat_desire(etatDernier.getEtat());
						if(etatDernier.getCommentaire()!=null) indic_comment = etatDernier.getCommentaire();
						heureSaisie = etatDernier.getHeure_saisie().toString();
					}
					
					indicateurs.get(j).setIndic_heure(0);
					if(ind_th==0){
						for(int i=0;i<listEnvir.size();i++) indic_envir.add(0);
					}
					
					k = index;
					while(!place && k<listEnvir.size()){
						ind_th = k;
						if(listEnvir.get(k).getEnvironnement().getId()==indicateurs.get(j).getEnvironnement().getId()){
							indicMeteo.add(indicateurs.get(j));
							place = true;
							if(indicateurs.get(j).getFormat().equals("liste")){
								couleur.add(EtatPossibleDatabaseService.get(Integer.parseInt(indicateurs.get(j).getEtat_desire())).getCouleur());
								listEtatService.add(EtatPossibleDatabaseService.getEtatsByType(indicateurs.get(j).getTypeIndicateur()));
								commentaire_indic.add(indic_comment);
							}else if(indicateurs.get(j).getFormat().equals("reel")){
								
								int totalGAB = Integer.parseInt(etatVoulu);
								Meteo_SeuilPlage s = indicateurs.get(j).getPlage().getSeuil();
								
								int val = Integer.parseInt(indicateurs.get(j).getEtat_desire());
								String color = PilotageConstants.OK;
																
								if(!s.getPertu_min().equals("")){
									int pertu_min = totalGAB-Math.round(totalGAB*Integer.parseInt(s.getPertu_min())/100);
									if(val<pertu_min) color = PilotageConstants.PERTURBE;
								}
								if(!s.getKo_min().equals("")){
									int ko_min = totalGAB-Math.round(totalGAB*Integer.parseInt(s.getKo_min())/100);
									if(val<ko_min) color = PilotageConstants.KO;
								}
								if(!s.getPertu_max().equals("")){
									int pertu_max = totalGAB+Math.round(totalGAB*Integer.parseInt(s.getPertu_max())/100);
									if(val>pertu_max) color = PilotageConstants.PERTURBE;
								}
								if(!s.getKo_max().equals("")){
									int ko_max = totalGAB+Math.round(totalGAB*Integer.parseInt(s.getKo_max())/100);
									if(val>ko_max) color = PilotageConstants.KO;
								}
								
								couleur.add(color);
								
								listEtatService.add(null);
								commentaire_indic.add(indic_comment);
							}else if(indicateurs.get(j).getFormat().equals("datetime") || indicateurs.get(j).getFormat().equals("varchar")){
								commentaire_indic.add(indic_comment);
							}else{
								listEtatService.add(null);
								commentaire_indic.add(null);
							}
							
							if(indicateurs.get(j).getHeure_debut().equals("1") && indicateurs.get(j).getHeure_fin().equals("1")){
								indic_envir.set(ind_th, 3);
								indicateurs.get(j).setIndic_heure(3);
								if(etatDernier.getHeure_debut()!=null) indicateurs.get(j).setHeure_debut(etatDernier.getHeure_debut().toString());
								if(etatDernier.getHeure_fin()!=null) indicateurs.get(j).setHeure_fin(etatDernier.getHeure_fin().toString());
							}else{
								if(indicateurs.get(j).getHeure_debut().equals("1")){
									if(indic_envir.get(ind_th)==2){ indic_envir.set(ind_th, 3); indicateurs.get(j).setIndic_heure(3); }
									else{ indic_envir.set(ind_th, 1); indicateurs.get(j).setIndic_heure(1);}
									if(etatDernier.getHeure_debut() != null) indicateurs.get(j).setHeure_debut(etatDernier.getHeure_debut().toString());
								}
								if(indicateurs.get(j).getHeure_fin().equals("1")){
									if(indic_envir.get(ind_th)==1){ indic_envir.set(ind_th, 3); indicateurs.get(j).setIndic_heure(3);}
									else{ indic_envir.set(ind_th, 2); indicateurs.get(j).setIndic_heure(2);}
									if(etatDernier.getHeure_fin() != null) indicateurs.get(j).setHeure_fin(etatDernier.getHeure_fin().toString());
								}
							}
							
						}else{
							indicMeteo.add(null);
							couleur.add(null);
							listEtatService.add(null);
							commentaire_indic.add(null);
						}
						index++;
						k++;
					}
					place = false;
					
					if((j+1<indicateurs.size() && !indicateurs.get(j).getService().getService().equals(indicateurs.get(j+1).getService().getService())) || j==indicateurs.size()-1){
						if(indicMeteo.size()!=listEnvir.size()){
							for(int i=index;i<listEnvir.size();i++){
								indicMeteo.add(null);
								couleur.add(null);
								listEtatService.add(null);
							}
						}
						listIndic.add(indicMeteo);
						couleurs.add(couleur);
						comment_indicateur.add(commentaire_indic);
						listEnvirMeteo.add(listEnvir);
						listEtat.add(listEtatService);
						index = 0;
						couleur = new ArrayList<String>();
						listEtatService = new ArrayList<List<Meteo_TypeIndic_EtatPoss>>();
						indicMeteo = new ArrayList<Meteo_IndicateurBase>();
						commentaire_indic = new ArrayList<String>();
					}
					
					if((j+1<indicateurs.size() && indicateurs.get(j).getMeteo().getId()!=indicateurs.get(j+1).getMeteo().getId()) || j==indicateurs.size()-1){
						for(int l=0;l<listEnvir.size();l++){
							tableau_th_envir.add(listEnvir.get(l).getEnvironnement().getNom_envir());
							if(indic_envir.get(l)==1){
								tableau_th_envir.add("Heure D�but");
							}else if(indic_envir.get(l)==2){
								tableau_th_envir.add("Heure Fin");
							}else if(indic_envir.get(l)==3){
								tableau_th_envir.add("Heure D�but");
								tableau_th_envir.add("Heure Fin");
							}
						}

						Date horaire = DateService.getTimeFromString(heure);
						Date dateAndHoraire = DateService.addTime(dateMeteo, horaire);
						List<Meteo_Service_Meteo> meteo_service = MeteoDatabaseService.getListMeteoServiceActive(indicateurs.get(j).getMeteo().getId(), dateAndHoraire);
						
						fin = fin + meteo_service.size();
						for(int m=debut;m<fin;m++){
							tableau_th.add(tableau_th_envir);
							Meteo_Commentaire comment = MeteoCommentaireDatabaseService.get(indicateurs.get(j).getMeteo(), dateMeteo, dateHoraire);
							if(comment!=null && comment.getCommentaire()!=null)
								commentaire_meteo.add(comment.getCommentaire());
							else commentaire_meteo.add("");
						}
						debut = fin;
						ind_th = 0;
						indic_envir = new ArrayList<Integer>();
						tableau_th_envir = new ArrayList<String>();
						
					}
					
				}
				
				Meteo_Envoi envoi = MeteoEnvoiDatabaseService.get(groupe, dateMeteo, dateHoraire);
				if(envoi != null){
					DateFormat dform = new SimpleDateFormat("dd/MM/yyyy");
					dateEnvoi = dform.format(envoi.getDate());
					heureEnvoi = envoi.getHeure_envoi().toString();
				}
				
			}
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Saisie d'un groupe m�t�o - ", e);
			return ERROR;
		}
	}

	

	
}
