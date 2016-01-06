package pilotage.meteo.saisie;

import java.io.FileOutputStream;


import java.io.InputStream;
import java.sql.Time;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import pilotage.database.admin.ParametreDatabaseService;
import pilotage.database.meteo.EtatCourantDatabaseService;
import pilotage.database.meteo.EtatPossibleDatabaseService;
import pilotage.database.meteo.GroupeMeteoCommentaireDatabaseService;
import pilotage.database.meteo.GroupeMeteoDatabaseService;
import pilotage.database.meteo.MeteoCommentaireDatabaseService;
import pilotage.database.meteo.MeteoDatabaseService;
import pilotage.database.meteo.MeteoDestinataireDatabaseService;
import pilotage.database.meteo.MeteoEnvironnementCaisseDatabaseService;
import pilotage.database.meteo.MeteoEnvironnementDatabaseService;
import pilotage.database.meteo.MeteoEnvoiDatabaseService;
import pilotage.database.meteo.MeteoGroupeMeteoDatabaseService;
import pilotage.database.meteo.MeteoIndicateurDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_Caisse;
import pilotage.metier.Meteo_Commentaire;
import pilotage.metier.Meteo_Environnement;
import pilotage.metier.Meteo_Environnement_Caisse;
import pilotage.metier.Meteo_Envoi;
import pilotage.metier.Meteo_EtatCourant;
import pilotage.metier.Meteo_EtatPossible;
import pilotage.metier.Meteo_GroupeMeteo;
import pilotage.metier.Meteo_GroupeMeteo_Meteo;
import pilotage.metier.Meteo_Indicateur;
import pilotage.metier.Meteo_Meteo;
import pilotage.metier.Meteo_Service;
import pilotage.metier.Meteo_Service_Meteo;
import pilotage.metier.Meteo_PlageFonct_Jour;
import pilotage.metier.Meteo_PlageFonctionnement;
import pilotage.metier.Meteo_SeuilPlage;
import pilotage.metier.Meteo_TypeIndic_EtatPoss;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;
import pilotage.service.excel.ExportExcel;
import pilotage.service.mail.MailService;

public class ExportGroupeMeteoAction extends AbstractAction{

	private static final long serialVersionUID = 379001970608500168L;
	
	private String titre;
	private InputStream excelStream;
	private Integer groupeMeteoID;
	private String heure;
	private String date;
	private String dateGMeteo;
	private String heureEnvoi;
	private String dateEnvoi;
	private List<Meteo_GroupeMeteo> listGroupeMeteo;
	private List<Map<String,String>> horaires;
	private Integer envoyerGroupe;
	private List<Meteo_Meteo> listMeteo;
	private List<String> listCommentaire;
	private List<List<Meteo_Service>> listService;
	private List<List<Meteo_Environnement>> listEnvironnement;
	private List<List<String>> listHeureDebut;
	private List<List<String>> listHeureFin;
	private List<List<List<Map<String,String>>>> listIndicateur;
	private List<List<List<Meteo_EtatCourant>>> listEtatCourant;
	
	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public Integer getGroupeMeteoID() {
		return groupeMeteoID;
	}

	public void setGroupeMeteoID(Integer groupeMeteoID) {
		this.groupeMeteoID = groupeMeteoID;
	}

	public List<Meteo_GroupeMeteo> getListGroupeMeteo() {
		return listGroupeMeteo;
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

	public String getDateGMeteo() {
		return dateGMeteo;
	}

	public void setDateGMeteo(String dateGMeteo) {
		this.dateGMeteo = dateGMeteo;
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

	public void setListGroupeMeteo(List<Meteo_GroupeMeteo> listGroupeMeteo) {
		this.listGroupeMeteo = listGroupeMeteo;
	}

	public List<Map<String, String>> getHoraires() {
		return horaires;
	}

	public void setHoraires(List<Map<String, String>> horaires) {
		this.horaires = horaires;
	}

	public Integer getEnvoyerGroupe() {
		return envoyerGroupe;
	}

	public void setEnvoyerGroupe(Integer envoyerGroupe) {
		this.envoyerGroupe = envoyerGroupe;
	}

	public List<Meteo_Meteo> getListMeteo() {
		return listMeteo;
	}

	public void setListMeteo(List<Meteo_Meteo> listMeteo) {
		this.listMeteo = listMeteo;
	}

	public List<String> getListCommentaire() {
		return listCommentaire;
	}

	public void setListCommentaire(List<String> listCommentaire) {
		this.listCommentaire = listCommentaire;
	}

	public List<List<Meteo_Service>> getListService() {
		return listService;
	}

	public void setListService(List<List<Meteo_Service>> listService) {
		this.listService = listService;
	}

	public List<List<Meteo_Environnement>> getListEnvironnement() {
		return listEnvironnement;
	}

	public void setListEnvironnement(
			List<List<Meteo_Environnement>> listEnvironnement) {
		this.listEnvironnement = listEnvironnement;
	}

	public List<List<String>> getListHeureDebut() {
		return listHeureDebut;
	}

	public void setListHeureDebut(List<List<String>> listHeureDebut) {
		this.listHeureDebut = listHeureDebut;
	}

	public List<List<String>> getListHeureFin() {
		return listHeureFin;
	}

	public void setListHeureFin(List<List<String>> listHeureFin) {
		this.listHeureFin = listHeureFin;
	}

	public List<List<List<Map<String, String>>>> getListIndicateur() {
		return listIndicateur;
	}

	public void setListIndicateur(
			List<List<List<Map<String, String>>>> listIndicateur) {
		this.listIndicateur = listIndicateur;
	}

	public List<List<List<Meteo_EtatCourant>>> getListEtatCourant() {
		return listEtatCourant;
	}

	public void setListEtatCourant(
			List<List<List<Meteo_EtatCourant>>> listEtatCourant) {
		this.listEtatCourant = listEtatCourant;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			horaires = new ArrayList<Map<String,String>>();
			listGroupeMeteo = GroupeMeteoDatabaseService.getAll();
			
			Meteo_GroupeMeteo groupe = GroupeMeteoDatabaseService.get(groupeMeteoID);
			Date horaire = DateService.getTimeFromString(heure);
			Date dateMeteo = DateService.strToDate(dateGMeteo);
			DateFormat dform = new SimpleDateFormat("dd/MM/yyyy");
			Date dEnvoi =new Date();
			dateEnvoi = dform.format(new Date());
			DateFormat dateform = new SimpleDateFormat("HH:mm:ss");
			Date hEnvoi = DateService.getTimeFromString(dateform.format(new Date()));
			Time tenvoi = Time.valueOf(dateform.format(new Date()));
			heureEnvoi = tenvoi.toString();
			List<Meteo_EtatPossible> listEtat = new ArrayList<Meteo_EtatPossible>();
			listCommentaire = new ArrayList<String>();
			
			Date dateAndHoraire = DateService.addTime(dateMeteo, horaire);
			
			listMeteo = new ArrayList<Meteo_Meteo>();
			List<Meteo_GroupeMeteo_Meteo> listGroupeMeteo_Meteo = MeteoGroupeMeteoDatabaseService.getUnionByGroupeMeteoList(groupeMeteoID);
			for(Meteo_GroupeMeteo_Meteo mg : listGroupeMeteo_Meteo){
				if(dateAndHoraire.getTime()>=mg.getDateCreation().getTime() && (mg.getDateSuppression()==null || mg.getDateSuppression().getTime()>dateAndHoraire.getTime())){
					listMeteo.add(mg.getIdMeteos());
				}
			}
			
			for(int i=0;i<listMeteo.size();i++){
				Meteo_Commentaire meteoComment = MeteoCommentaireDatabaseService.get(listMeteo.get(i), dateMeteo, horaire);
				String commentMeteo = "";
				if(meteoComment != null) commentMeteo = meteoComment.getCommentaire();
				listCommentaire.add(commentMeteo);
			}
			
			listService = new ArrayList<List<Meteo_Service>>();
			for(Meteo_Meteo meteo : listMeteo){
				List<Meteo_Service> ls = new ArrayList<Meteo_Service>();
				List<Meteo_Service_Meteo> services = MeteoDatabaseService.getListMeteoService(meteo.getId());
				if(services != null && !services.isEmpty()){
					for(Meteo_Service_Meteo s : services){
						if(dateAndHoraire.getTime()>=s.getDateCreation().getTime() && (s.getDateSuppression()==null || s.getDateSuppression().getTime()>dateAndHoraire.getTime())){
							ls.add(s.getService());
						}
					}
					listService.add(ls);
				}
			}
			
			listIndicateur = new ArrayList<List<List<Map<String,String>>>>();
			listEnvironnement = new ArrayList<List<Meteo_Environnement>>();
			listHeureDebut = new ArrayList<List<String>>();
			listHeureFin = new ArrayList<List<String>>();
			List<String> champHeureDebut = new ArrayList<String>();
			List<String> champHeureFin = new ArrayList<String>();
			Date heure_saisie = null;
			int nbColonneEnvir = 0;
			int incident = 0;
			
			for(List<Meteo_Service> services : listService){
				List<Meteo_Environnement> environnement = MeteoEnvironnementDatabaseService.getListEnvirByListService(services);
				listEnvironnement.add(environnement);
				
				if(nbColonneEnvir<environnement.size()) nbColonneEnvir = environnement.size();
				
				List<List<Map<String,String>>> indicateurs = new ArrayList<List<Map<String, String>>>();
				if(champHeureDebut.isEmpty()){
					for(int i=0;i<environnement.size();i++){
						champHeureDebut.add("0");
						champHeureFin.add("0");
					}
				}
				
				int se=0;
				for(Meteo_Service service : services){			
					List<Map<String,String>> listIndic = new ArrayList<Map<String,String>>();
					for(Meteo_Environnement envir : environnement){
						Map<String,String> listString = new HashMap<String,String>();
						String couleur;

						Meteo_Indicateur indicateur = MeteoIndicateurDatabaseService.getIndicateurByServiceAndEnvir(service, envir);
						
						if(indicateur == null
								|| dateAndHoraire.getTime()<indicateur.getDateCreation().getTime() 
								|| (indicateur.getDateSuppression()!=null && indicateur.getDateSuppression().getTime()<dateAndHoraire.getTime())){
							listString.put(PilotageConstants.ID_TYPE, "-1");
							listString.put(PilotageConstants.COLOR, "#b8cce4");
							listString.put(PilotageConstants.COMMENTAIRE, null);
							listString.put(PilotageConstants.ETAT, "NA");
							listString.put(PilotageConstants.FORMAT, "NA");
						}
						else if(indicateur != null){
							List<Meteo_PlageFonctionnement> listPlages = MeteoIndicateurDatabaseService.getListPlageFonctionnement2(indicateur, dateAndHoraire);
							Meteo_PlageFonctionnement pl = null;
							
							DateFormat df = new SimpleDateFormat("HH:mm:ss");
							Time t = Time.valueOf(df.format(horaire));
							int jour = DateService.getJour(dateMeteo);
							
							boolean trouve = false;
							int j=0;
							while(j<listPlages.size() && !trouve){
								if(t.getTime()>=listPlages.get(j).getHeureDebut().getTime() && t.getTime()<=listPlages.get(j).getHeureFin().getTime()){
									List<Meteo_PlageFonct_Jour> plageJour = MeteoIndicateurDatabaseService.getListJoursByPlage(listPlages.get(j));	
									int i=0;
									while(i<plageJour.size() && !trouve){
										if(plageJour.get(i).getJour()==jour){
											trouve = true;
											pl=listPlages.get(j);
										}
										i++;
									}
									
								}
								j++;
							}
							
							listString.put(PilotageConstants.ID_TYPE, indicateur.getTypeIndic().getId().toString());
							
							if(pl==null){
								listString.put(PilotageConstants.ETAT, "NA");
								listString.put(PilotageConstants.ID_TYPE, "-1");
								listString.put(PilotageConstants.COLOR, "#b8cce4");
								listString.put(PilotageConstants.COMMENTAIRE, null);
								listString.put(PilotageConstants.ETAT, "NA");
								listString.put(PilotageConstants.FORMAT, "NA");
							}
							else{
								Meteo_EtatCourant etatCourant = EtatCourantDatabaseService.getByIndicDate(indicateur, dateMeteo, horaire);
								if(etatCourant==null){
									listString.put(PilotageConstants.ETAT, "NA");
									listString.put(PilotageConstants.ID_TYPE, "-1");
									listString.put(PilotageConstants.COLOR, "#b8cce4");
									listString.put(PilotageConstants.COMMENTAIRE, null);
									listString.put(PilotageConstants.ETAT, "NA");
									listString.put(PilotageConstants.FORMAT, "NA");
								}else{
								listString.put(PilotageConstants.COMMENTAIRE, etatCourant.getCommentaire());
								heure_saisie = etatCourant.getHeure_saisie();
								
								String heureDebut;
								if(indicateur.getHeureDebut()==0) heureDebut = "-1";
								else if(etatCourant.getHeure_debut()==null) heureDebut = "";
								else heureDebut = etatCourant.getHeure_debut().toString().substring(0, 5);;
								listString.put(PilotageConstants.HEURE_DEBUT, heureDebut);
								heureDebut = null;
								
								String heureFin;
								if(indicateur.getHeureFin()==0) heureFin = "-1";
								else if(etatCourant.getHeure_fin()==null) heureFin = "";
								else heureFin = etatCourant.getHeure_fin().toString().substring(0, 5);
								listString.put(PilotageConstants.HEURE_FIN, heureFin);
								heureFin = null;
								
								listString.put(PilotageConstants.FORMAT, indicateur.getTypeIndic().getFormat().getFormat());
								
								if(indicateur.getTypeIndic().getFormat().getFormat().equals("liste")){
									//listEtat.removeAll(listEtat);
									List<Meteo_TypeIndic_EtatPoss> listEtatPoss = EtatPossibleDatabaseService.getEtatsByType(indicateur.getTypeIndic());
									
									boolean ind = false;
									for(Meteo_TypeIndic_EtatPoss m :listEtatPoss){
										for(Meteo_EtatPossible l :listEtat){
											if(m.getEtatPoss().getId()==l.getId()) ind = true;
										}
										if(!ind){
											listEtat.add(m.getEtatPoss());
										}
										ind = false;
									}
									
									//String etatstring = String.valueOf(etatCourant.getEtat().intValue());
									//if(!etatCourant.getEtat().equals(pl.getEtatDesire())) incident = 1;
										
									Meteo_EtatPossible etat = EtatPossibleDatabaseService.get(Integer.parseInt(etatCourant.getEtat()));
									
									if(envir.getImpact()==1 && etat.getImpact()==1) incident = 1;
									
									listString.put(PilotageConstants.ETAT, etat.getLibelle_etat());
									listString.put(PilotageConstants.COLOR, etat.getCouleur());}
								else if(indicateur.getTypeIndic().getFormat().getFormat().equals("reel")){
									listString.put(PilotageConstants.ETAT, etatCourant.getEtat());
									int seuil = pl.getSeuil().getId();
									Meteo_SeuilPlage s = MeteoIndicateurDatabaseService.getSeuil(seuil, null);
									couleur = PilotageConstants.OK;
									
									int totalGab = Integer.parseInt(pl.getEtatDesire());
									if(!s.getPertu_min().equals("")){
										int pertu_min = totalGab-Math.round(totalGab*Integer.parseInt(s.getPertu_min())/100);
										if(Integer.parseInt(etatCourant.getEtat())<pertu_min){
											couleur = PilotageConstants.PERTURBE;
											if(envir.getImpact()==1) incident = 1;
										}
									}
									if(!s.getKo_min().equals("")){
										int ko_min = totalGab-Math.round(totalGab*Integer.parseInt(s.getKo_min())/100);
										if(Integer.parseInt(etatCourant.getEtat())<ko_min){
											couleur = PilotageConstants.KO;
											if(envir.getImpact()==1) incident =1;
										}
									}
									if(!s.getPertu_max().equals("")){
										int pertu_max = totalGab+Math.round(totalGab*Integer.parseInt(s.getPertu_max())/100);
										if(Integer.parseInt(etatCourant.getEtat())>pertu_max){
											couleur = PilotageConstants.PERTURBE;
											if(envir.getImpact()==1) incident =1;
										}
									}
									if(!s.getKo_max().equals("")){
										int ko_max = totalGab+Math.round(totalGab*Integer.parseInt(s.getKo_max())/100);
										if(Integer.parseInt(etatCourant.getEtat())>ko_max){
											couleur = PilotageConstants.KO;
											if(envir.getImpact()==1) incident = 1;
										}
									}
									listString.put(PilotageConstants.COLOR, couleur);
								}
								else if(indicateur.getTypeIndic().getFormat().getFormat().equals("datetime") || indicateur.getTypeIndic().getFormat().getFormat().equals("varchar")){
									listString.put(PilotageConstants.ETAT, etatCourant.getEtat());
								}
								}
							}
						}
						listIndic.add(listString);
					}
					indicateurs.add(listIndic);
					for(int i=0;i<environnement.size();i++){
						if(indicateurs.get(se).get(i).get(PilotageConstants.FORMAT).equals("liste")){
							if(!indicateurs.get(se).get(i).get(PilotageConstants.HEURE_DEBUT).equals("-1")) champHeureDebut.set(i, "1");
							if(!indicateurs.get(se).get(i).get(PilotageConstants.HEURE_FIN).equals("-1")) champHeureFin.set(i, "1");
						}
					}
					se++;
				}
				listIndicateur.add(indicateurs);
				listHeureDebut.add(champHeureDebut);
				listHeureFin.add(champHeureFin);
				champHeureDebut = new ArrayList<String>();
				champHeureFin = new ArrayList<String>();
			}
			
			boolean estDans = false;
			List<Meteo_Environnement> envirList = new ArrayList<Meteo_Environnement>();	
			for(List<Meteo_Environnement> listEnvir : listEnvironnement){
				for(Meteo_Environnement envir : listEnvir){
					for(Meteo_Environnement env :envirList){
						if(envir.getId()==env.getId()){
							estDans = true;
							break;
						}
					}
					if(!estDans){
						envirList.add(envir);
					}
					estDans = false;
				}
			}
			
			List<List<Meteo_Caisse>> listCaisse = new ArrayList<List<Meteo_Caisse>>();
			for(Meteo_Environnement environnement : envirList){
				List<Meteo_Environnement_Caisse> CaisseList = MeteoEnvironnementCaisseDatabaseService.getListCaisseByEnvir(environnement.getId());
				List<Meteo_Caisse> caisses = new ArrayList<Meteo_Caisse>();
				for(Meteo_Environnement_Caisse envirCaisse : CaisseList){
					caisses.add(envirCaisse.getCaisse());
				}
				listCaisse.add(caisses);
			}
			
			String commentaire = "";
			if(GroupeMeteoCommentaireDatabaseService.get(groupe, dateMeteo, horaire) != null)
				commentaire = GroupeMeteoCommentaireDatabaseService.get(groupe, dateMeteo, horaire).getCommentaire();
			
			//constitution du tableau excel
			String path_img = ParametreDatabaseService.get("IMAGE_METEO").getValeur();
			HSSFWorkbook lExcelDoc = null;
			if(groupe.getFormat().getId()==1)
				lExcelDoc = ExportExcel.exportGroupeMeteo(groupe.getNom_groupeMeteo(), commentaire, date, heure, heure_saisie, listMeteo, listCommentaire, listService,
					listEnvironnement, nbColonneEnvir, listIndicateur, envirList, listCaisse, listEtat, path_img, listHeureDebut, listHeureFin, groupe.getTimezone());
			
			if(groupe.getFormat().getId()==2)
				lExcelDoc = ExportExcel.exportGroupeMeteoDetail(groupe.getNom_groupeMeteo(), commentaire, date, heure, heure_saisie, listMeteo, listCommentaire, listService,
						listEnvironnement, nbColonneEnvir, listIndicateur, envirList, listCaisse, listEtat, path_img);
			
			String dateName = date.replace("/", "-");			
			String[] tabHoraire = heure.split(":");
			String horaireName = tabHoraire[0]+"h"+tabHoraire[1];
			
			String nomGroupeMeteo = groupe.getNom_groupeMeteo().replace("é", "e");
			String name = "meteo_"+nomGroupeMeteo+"_"+dateName+"_"+horaireName;
			
			String path = ParametreDatabaseService.get("DOCUMENT_METEO").getValeur();
			
			FileOutputStream fileOut = new FileOutputStream(path+name+".xls");
			lExcelDoc.write(fileOut);
			fileOut.close();
			
			//envoi
			List<Users> listDestinataires = MeteoDestinataireDatabaseService.getDestinataires(groupeMeteoID);
			
			String[] listDestinataireArray = new String[listDestinataires.size()];
			for(int i = 0; i < listDestinataires.size(); ++i){
				listDestinataireArray[i] = listDestinataires.get(i).getEmail();
			}
			
			String objet = "Météo "+groupe.getNom_groupeMeteo()+" -";
			if(incident == 1){
				objet += " Impact services";
			}else{
				objet += " Service OK";
			}
			
			String signature = ParametreDatabaseService.get("SIGNATURE_PILOTAGE").getValeur();
			
			String body = "Bonjour, <br/><br/> Vous trouverez ci-joint le Groupe Météo "+groupe.getNom_groupeMeteo()+" du "+date+" à "+heure+".<br/><br/>Cordialement.<br/><br/>"+signature;
			Map<String, String> images = new HashMap<String, String>();
			images.put("logo", this.getClass().getClassLoader().getResource("../" + session.get(PilotageConstants.ENSEIGNE) + "/img/logo.png").getPath());
			
			boolean checkSend = MailService.sendEmailPieceJointe(listDestinataireArray, objet, body , null, images, path+name+".xls");
			if(!checkSend){
				error = getText("bilan.envoi.error");
				return ERROR;
			}else{
				info = MessageFormat.format(getText("groupeMeteo.envoi.valide"),groupe.getNom_groupeMeteo(), date, heure);
				Meteo_Envoi envoi = MeteoEnvoiDatabaseService.get(groupe, dateMeteo, horaire);
				
				if(envoi != null){
					MeteoEnvoiDatabaseService.modify(groupe, dateMeteo, horaire, dEnvoi, hEnvoi);
				}else{
					MeteoEnvoiDatabaseService.create(groupe, dateMeteo, horaire, dEnvoi, hEnvoi);
				}
			}
				
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Saisie d'un groupe météo - ", e);
			return ERROR;
		}
	}

}
