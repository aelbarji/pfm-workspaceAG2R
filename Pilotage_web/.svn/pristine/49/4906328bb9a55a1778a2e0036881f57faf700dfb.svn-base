package pilotage.meteo.saisie;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.meteo.EtatCourantDatabaseService;
import pilotage.database.meteo.EtatPossibleDatabaseService;
import pilotage.database.meteo.GroupeMeteoCommentaireDatabaseService;
import pilotage.database.meteo.GroupeMeteoDatabaseService;
import pilotage.database.meteo.GroupeMeteoHoraireDatabaseService;
import pilotage.database.meteo.MeteoCommentaireDatabaseService;
import pilotage.database.meteo.MeteoIndicateurDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_Environnement_Place;
import pilotage.metier.Meteo_EtatCourant;
import pilotage.metier.Meteo_EtatPossible;
import pilotage.metier.Meteo_GroupeMeteo;
import pilotage.metier.Meteo_Indicateur;
import pilotage.metier.Meteo_IndicateurBase;
import pilotage.metier.Meteo_TypeIndic_EtatPoss;
import pilotage.service.date.DateService;

public class SaveMeteoAction extends AbstractAction{

	private static final long serialVersionUID = 8092779027355255957L;

	private Integer groupeMeteoID;
	private String heure;
	private String heureExcept;
	private String heureMeteo;
	private String date;
	private String heureSaisie;
	private String exception;
	private String commentaire;
	private List<Map<String,String>> horaires;
	
	private List<Meteo_IndicateurBase> indicateurs;
	private List<List<Meteo_Environnement_Place>> listEnvirMeteo;
	private List<List<Meteo_Indicateur>> listEnvirTrie;
	private List<List<List<Meteo_TypeIndic_EtatPoss>>> listEtat;
	private List<List<String>> couleurs;
	private List<List<String>> tableau_th;
	private List<List<String>> comment_indicateur;
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

	public String getHeureExcept() {
		return heureExcept;
	}

	public void setHeureExcept(String heureExcept) {
		this.heureExcept = heureExcept;
	}

	public String getHeureMeteo() {
		return heureMeteo;
	}

	public void setHeureMeteo(String heureMeteo) {
		this.heureMeteo = heureMeteo;
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

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public List<Map<String, String>> getHoraires() {
		return horaires;
	}

	public void setHoraires(List<Map<String, String>> horaires) {
		this.horaires = horaires;
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

	public void setListEnvirMeteo(
			List<List<Meteo_Environnement_Place>> listEnvirMeteo) {
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

	public List<List<Meteo_IndicateurBase>> getListIndic() {
		return listIndic;
	}

	public void setListIndic(List<List<Meteo_IndicateurBase>> listIndic) {
		this.listIndic = listIndic;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		HttpServletRequest request = ServletActionContext.getRequest();
		try{
			horaires = new ArrayList<Map<String,String>>();
			Date heureGroupe;
			Date dateJour = new Date();
			SimpleDateFormat formater2 = new SimpleDateFormat("HH:mm:ss");
			Date heure_saisie = new Date();
			heureSaisie = formater2.format(heure_saisie);
			Meteo_GroupeMeteo groupe = GroupeMeteoDatabaseService.get(groupeMeteoID);
			List<Meteo_EtatCourant> listCourant = new ArrayList<Meteo_EtatCourant>();
			
			if(exception != null && exception.equals("1")){
				heureGroupe = DateService.getTimeFromString(heureExcept);
				GroupeMeteoHoraireDatabaseService.createException(groupe, dateJour, heureGroupe);
				heureMeteo = heureExcept;
			}else{
				heureGroupe = DateService.getTimeFromString(heure);
				heureMeteo = heure;
			}
			
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			date = formater.format(dateJour);
			dateJour=formater.parse(date);
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			format.format(dateJour);
			
			String etat;
			String datetime = date+" "+heureMeteo;
			
			List<Meteo_IndicateurBase> indicateurs = GroupeMeteoDatabaseService.createGroupeMeteo(groupeMeteoID, date, datetime, heureMeteo);
			
			for(int i=0;i<indicateurs.size();i++){
				String commentaireMeteo = request.getParameter("commentTexteMeteo"+indicateurs.get(i).getMeteo().getId());
				if(MeteoCommentaireDatabaseService.get(indicateurs.get(i).getMeteo(), dateJour, heureGroupe)==null && commentaireMeteo!=""){
					MeteoCommentaireDatabaseService.create(indicateurs.get(i).getMeteo(), commentaireMeteo, dateJour, heureGroupe);
				}
				
				Date heure_debut = null;
				Date heure_fin = null;	
				if(indicateurs.get(i).getFormat().equals("liste")){
					Meteo_EtatPossible etatPoss = EtatPossibleDatabaseService.getByName(request.getParameter("etat"+indicateurs.get(i).getId()));
					etat = etatPoss.getId().toString();
					if(indicateurs.get(i).getHeure_debut().equals("1") && indicateurs.get(i).getHeure_fin().equals("1")){
						if(request.getParameter("heureDebut"+indicateurs.get(i).getId())!="") heure_debut = DateService.getTimeFromString(request.getParameter("heureDebut"+indicateurs.get(i).getId()));
						if(request.getParameter("heureFin"+indicateurs.get(i).getId())!="") heure_fin = DateService.getTimeFromString(request.getParameter("heureFin"+indicateurs.get(i).getId()));
					}else if(indicateurs.get(i).getHeure_fin().equals("1") && request.getParameter("heureFin"+indicateurs.get(i).getId())!=""){
						heure_fin = DateService.getTimeFromString(request.getParameter("heureFin"+indicateurs.get(i).getId()));
					}else if(indicateurs.get(i).getHeure_debut().equals("1") && request.getParameter("heureDebut"+indicateurs.get(i).getId())!=""){
						heure_debut = DateService.getTimeFromString(request.getParameter("heureDebut"+indicateurs.get(i).getId()));
					}
				}
				else if(indicateurs.get(i).getFormat().equals("reel")){
					etat = request.getParameter("etat"+indicateurs.get(i).getId());
					etat = etat.replace(" ", "");
				}
				else{
					etat = request.getParameter("etat"+indicateurs.get(i).getId());
				}
				
				String commentaire = null;
				if(request.getParameter("commentTexte"+indicateurs.get(i).getId())!="") commentaire = request.getParameter("commentTexte"+indicateurs.get(i).getId());
				
				Meteo_EtatCourant etatCourant = new Meteo_EtatCourant();
				etatCourant.setIndicateur(MeteoIndicateurDatabaseService.get(indicateurs.get(i).getId()));
				etatCourant.setEtat(etat);
				etatCourant.setCommentaire(commentaire);
				etatCourant.setDate(dateJour);
				etatCourant.setHoraire(heureGroupe);
				etatCourant.setHeure_saisie(heure_saisie);
				etatCourant.setHeure_debut(heure_debut);
				etatCourant.setHeure_fin(heure_fin);
				
				listCourant.add(etatCourant);		
			}
			
			if(commentaire != null && !commentaire.equals(""))
			GroupeMeteoCommentaireDatabaseService.create(groupe, commentaire, dateJour, heureGroupe);
			
			EtatCourantDatabaseService.create3(listCourant);
			
			info = MessageFormat.format(getText("groupeMeteo.saisie.valide"),groupe.getNom_groupeMeteo(), date, heureMeteo);
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Sauvegarde d'un groupe météo - ", e);
			return ERROR;
		}
	}
}
