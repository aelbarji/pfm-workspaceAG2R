package pilotage.database.checklist;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Checklist_Base;
import pilotage.metier.Checklist_Base_Soustache;
import pilotage.metier.Checklist_Consigne_Documents;
import pilotage.metier.Checklist_Consignes;
import pilotage.metier.Checklist_Criticite;
import pilotage.metier.Checklist_Current;
import pilotage.metier.Checklist_Etat;
import pilotage.metier.Checklist_Exceptionnel;
import pilotage.metier.Checklist_Ferie;
import pilotage.metier.Checklist_Horaire;
import pilotage.metier.Checklist_Jour;
import pilotage.metier.Checklist_Mensuel;
import pilotage.metier.Checklist_Parite;
import pilotage.metier.Environnement;
import pilotage.metier.Jour_Ferie;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;
import pilotage.session.PilotageSession;

public class ChecklistTachesDatabaseService {

	/**
	 * Création de la tache
	 * 
	 * @param session
	 * @param nom
	 * @param environnement
	 * @param dateDebut
	 * @param etat
	 * @param criticite
	 * @param typeDemande
	 * @param heureReception
	 * @param nomEmetteur
	 * @param numeroObs
	 * @param description
	 * @param typeDemande
	 * @param heureReception
	 * @param nomEmetteur
	 * @param idDemande
	 * @param numeroObs
	 * @param description
	 * @return
	 */
	private static Checklist_Base initChecklistBase(Session session,
			String nom, Integer environnement, Date dateDebut, Integer etat,
			Integer criticite, String typeDemande, Date heureReception, String nomEmetteur, String descriptionMail, String descriptionObs, String numeroObs) {

		Environnement env = (Environnement) session.load(Environnement.class,
				environnement);
		Checklist_Etat et = (Checklist_Etat) session.load(Checklist_Etat.class,
				etat);
		Checklist_Criticite crit = (Checklist_Criticite) session.load(
				Checklist_Criticite.class, criticite);

		Checklist_Base base = new Checklist_Base();
		

		base.setTache(nom);
		base.setEnvironnement(env);
		base.setDateDebut(dateDebut);
		base.setEtat(et);
		base.setCriticite(crit);
		base.setActif(Boolean.TRUE);
		
		base.setTypeDemande(typeDemande);
		
		if (heureReception != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date currentDate = new Date();
			String currentDateString = dateFormat.format(currentDate);
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
			String heure = dateFormat2.format(heureReception);
			String finalDate = currentDateString + " " + heure;
			SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			try {
				Date date = dateFormat3.parse(finalDate);
				Timestamp datetime = new Timestamp(date.getTime());
				base.setHeureReception(datetime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			base.setHeureReception(null);
		}
		
		base.setNomEmetteur(nomEmetteur);
		base.setDescriptionMail(descriptionMail);
		base.setDescriptionObs(descriptionObs);
		base.setNumeroObs(numeroObs);

		return base;
	}

	/**
	 * Création des horaires
	 * 
	 * @param base
	 * @param typeFrequenceHeure
	 * @param listHeuresPonctuelles
	 * @param heureDebut
	 * @param heureFin
	 * @param frequence
	 * @return
	 */
	private static List<Checklist_Horaire> initListChecklistHoraire(
			Checklist_Base base, Integer typeFrequenceHeure,
			List<Date> listHeuresPonctuelles, Date heureDebut, Date heureFin,
			Date frequence) {

		List<Checklist_Horaire> heures_ex = new ArrayList<Checklist_Horaire>();
		if (PilotageConstants.HEURE_FREQ_EXCEPTIONNELLE
				.equals(typeFrequenceHeure)) {
			for (Date heure : listHeuresPonctuelles) {
				Checklist_Horaire horaire = new Checklist_Horaire();
				horaire.setIdChecklist(base);
				horaire.setHoraire(heure);
				horaire.setHoraireStamp(DateService.getDecalageTimeStamp(null,
						heure));
				horaire.setActif(Boolean.TRUE);

				heures_ex.add(horaire);
			}
		} else if (PilotageConstants.HEURE_FREQ_REGULIER
				.equals(typeFrequenceHeure)) {
			while (heureDebut.before(heureFin) || heureDebut.equals(heureFin)) {
				Checklist_Horaire horaire = new Checklist_Horaire();
				horaire.setIdChecklist(base);
				horaire.setHoraire(heureDebut);
				horaire.setHoraireStamp(DateService.getDecalageTimeStamp(null,
						heureDebut));
				horaire.setActif(Boolean.TRUE);

				heures_ex.add(horaire);

				heureDebut = DateService.addTime(heureDebut, frequence);
			}
		}
		return heures_ex;
	}

	/**
	 * Création des checklist_consigne et sous taches
	 * 
	 * @param base
	 * @param listSousTaches
	 * @param listConsignes
	 * @param listBaseSousTaches
	 */
	private static void initConsignesEtSousTaches(Checklist_Base base,
			List<List<String>> listSousTaches,
			List<Checklist_Consignes> listConsignes,
			List<Checklist_Base_Soustache> listBaseSousTaches,
			List<Checklist_Consigne_Documents> listDocuments,
			Map<String, List<String>> documentsSousTaches) {

		for (List<String> stInfos : listSousTaches) {
			Date decalage = DateService.strToDate(DateService
					.strTimeToLong(stInfos.get(1) + ":" + stInfos.get(2)));

			Checklist_Consignes consigne = new Checklist_Consignes();
			consigne.setConsigne(stInfos.get(3));
			listConsignes.add(consigne);

			Checklist_Base_Soustache sousTache = new Checklist_Base_Soustache();
			sousTache.setIdBase(base);
			sousTache.setNomSousTache(stInfos.get(0));
			sousTache.setDecalageDate(decalage);
			sousTache.setDecalageStamp(DateService.strTimeToLong(stInfos.get(1)
					+ ":" + stInfos.get(2)));
			sousTache.setIdConsigne(consigne);
			sousTache.setActif(Boolean.TRUE);
			listBaseSousTaches.add(sousTache);
		}

		for (Checklist_Base_Soustache sousTache : listBaseSousTaches) {
			if (documentsSousTaches.get(sousTache.getNomSousTache()) != null) {
				for (Entry<String, List<String>> doc : documentsSousTaches
						.entrySet()) {
					if (doc.getKey().equals(sousTache.getNomSousTache())) {
						List<String> documents = doc.getValue();
						for (String documentValue : documents) {
							Checklist_Consigne_Documents document = new Checklist_Consigne_Documents();
							document.setDocument(documentValue);
							document.setIdConsigne(sousTache.getIdConsigne());
							listDocuments.add(document);
						}
					}
				}
			}
		}
	}

	/**
	 * Création de la liste des jours pour fréquence hebdo/mensuelle/semaine
	 * pair-impair
	 * 
	 * @param base
	 * @param lundi
	 * @param mardi
	 * @param mercredi
	 * @param jeudi
	 * @param vendredi
	 * @param samedi
	 * @param dimanche
	 * @param notFerie
	 * @return
	 */
	private static List<Checklist_Jour> initJour(Checklist_Base base,
			boolean lundi, boolean mardi, boolean mercredi, boolean jeudi,
			boolean vendredi, boolean samedi, boolean dimanche, boolean notFerie) {

		List<Checklist_Jour> listJour = new ArrayList<Checklist_Jour>();
		if (lundi) {
			Checklist_Jour jour = new Checklist_Jour();
			jour.setIdChecklist(base);
			jour.setJour(PilotageConstants.LUNDI);
			jour.setFerie(!notFerie);
			listJour.add(jour);
		}
		if (mardi) {
			Checklist_Jour jour = new Checklist_Jour();
			jour.setIdChecklist(base);
			jour.setJour(PilotageConstants.MARDI);
			jour.setFerie(!notFerie);
			listJour.add(jour);
		}
		if (mercredi) {
			Checklist_Jour jour = new Checklist_Jour();
			jour.setIdChecklist(base);
			jour.setJour(PilotageConstants.MERCREDI);
			jour.setFerie(!notFerie);
			listJour.add(jour);
		}
		if (jeudi) {
			Checklist_Jour jour = new Checklist_Jour();
			jour.setIdChecklist(base);
			jour.setJour(PilotageConstants.JEUDI);
			jour.setFerie(!notFerie);
			listJour.add(jour);
		}
		if (vendredi) {
			Checklist_Jour jour = new Checklist_Jour();
			jour.setIdChecklist(base);
			jour.setJour(PilotageConstants.VENDREDI);
			jour.setFerie(!notFerie);
			listJour.add(jour);
		}
		if (samedi) {
			Checklist_Jour jour = new Checklist_Jour();
			jour.setIdChecklist(base);
			jour.setJour(PilotageConstants.SAMEDI);
			jour.setFerie(!notFerie);
			listJour.add(jour);
		}
		if (dimanche) {
			Checklist_Jour jour = new Checklist_Jour();
			jour.setIdChecklist(base);
			jour.setJour(PilotageConstants.DIMANCHE);
			jour.setFerie(!notFerie);
			listJour.add(jour);
		}
		return listJour;
	}

	/**
	 * Création de la liste des jours exceptionnels
	 * 
	 * @param base
	 * @param listDatesExceptionnelles
	 * @param listChecklistExceptionnel
	 */
	private static List<Checklist_Exceptionnel> initListChecklistExceptionnel(
			Checklist_Base base, List<Date> listDatesExceptionnelles) {
		List<Checklist_Exceptionnel> listChecklistExceptionnel = new ArrayList<Checklist_Exceptionnel>();
		for (Date jour : listDatesExceptionnelles) {
			Checklist_Exceptionnel newJour = new Checklist_Exceptionnel();
			newJour.setIdChecklist(base);
			newJour.setJour(jour);

			listChecklistExceptionnel.add(newJour);
		}

		return listChecklistExceptionnel;
	}

	/**
	 * Création de la liste des jours fériés
	 * 
	 * @param session
	 * @param base
	 * @param veilleFerie
	 * @param jourFerie
	 * @param lendemainFerie
	 * @param listChecklistFerie
	 */
	private static List<Checklist_Ferie> initListChecklistFerie(
			Session session, Checklist_Base base, Integer[] veilleFerie,
			Integer[] jourFerie, Integer[] lendemainFerie) {

		List<Checklist_Ferie> listChecklistFerie = new ArrayList<Checklist_Ferie>();

		if (veilleFerie != null && veilleFerie.length != 0) {
			for (Integer ferieID : veilleFerie) {
				Jour_Ferie jour_ferie = (Jour_Ferie) session.load(
						Jour_Ferie.class, ferieID);

				Checklist_Ferie ferie = new Checklist_Ferie();
				ferie.setIdChecklist(base);
				ferie.setIdJourFerie(jour_ferie);
				ferie.setLendeveille(PilotageConstants.VEILLE_FERIE);

				listChecklistFerie.add(ferie);
			}
		}
		if (jourFerie != null && jourFerie.length != 0) {
			for (Integer ferieID : jourFerie) {
				Jour_Ferie jour_ferie = (Jour_Ferie) session.load(
						Jour_Ferie.class, ferieID);

				Checklist_Ferie ferie = new Checklist_Ferie();
				ferie.setIdChecklist(base);
				ferie.setIdJourFerie(jour_ferie);
				ferie.setLendeveille(PilotageConstants.JOUR_FERIE);

				listChecklistFerie.add(ferie);
			}
		}
		if (lendemainFerie != null && lendemainFerie.length != 0) {
			for (Integer ferieID : lendemainFerie) {
				Jour_Ferie jour_ferie = (Jour_Ferie) session.load(
						Jour_Ferie.class, ferieID);

				Checklist_Ferie ferie = new Checklist_Ferie();
				ferie.setIdChecklist(base);
				ferie.setIdJourFerie(jour_ferie);
				ferie.setLendeveille(PilotageConstants.LENDEMAIN_FERIE);

				listChecklistFerie.add(ferie);
			}
		}

		return listChecklistFerie;
	}

	/**
	 * Création de la liste des parités
	 * 
	 * @param base
	 * @param pair
	 * @param impair
	 * @return
	 */
	private static List<Checklist_Parite> initListChecklistParite(
			Checklist_Base base, boolean pair, boolean impair) {
		List<Checklist_Parite> listParite = new ArrayList<Checklist_Parite>();
		if (pair) {
			Checklist_Parite checklist_pair = new Checklist_Parite();
			checklist_pair.setIdChecklist(base);
			checklist_pair.setParite(PilotageConstants.PAIR);
			listParite.add(checklist_pair);
		}
		if (impair) {
			Checklist_Parite checklist_impair = new Checklist_Parite();
			checklist_impair.setIdChecklist(base);
			checklist_impair.setParite(PilotageConstants.IMPAIR);
			listParite.add(checklist_impair);
		}

		return listParite;
	}

	/**
	 * Création de la liste des mensuels
	 * 
	 * @param base
	 * @param semaine1
	 * @param semaine2
	 * @param semaine3
	 * @param semaine4
	 * @param semaineDer
	 * @return
	 */
	private static List<Checklist_Mensuel> initListChecklistMensuel(
			Checklist_Base base, boolean semaine1, boolean semaine2,
			boolean semaine3, boolean semaine4, boolean semaineDer) {
		List<Checklist_Mensuel> listMensuelle = new ArrayList<Checklist_Mensuel>();
		if (semaine1) {
			Checklist_Mensuel semaine = new Checklist_Mensuel();
			semaine.setIdChecklist(base);
			semaine.setMensuel(1);
			listMensuelle.add(semaine);
		}
		if (semaine2) {
			Checklist_Mensuel semaine = new Checklist_Mensuel();
			semaine.setIdChecklist(base);
			semaine.setMensuel(2);
			listMensuelle.add(semaine);
		}
		if (semaine3) {
			Checklist_Mensuel semaine = new Checklist_Mensuel();
			semaine.setIdChecklist(base);
			semaine.setMensuel(3);
			listMensuelle.add(semaine);
		}
		if (semaine4) {
			Checklist_Mensuel semaine = new Checklist_Mensuel();
			semaine.setIdChecklist(base);
			semaine.setMensuel(4);
			listMensuelle.add(semaine);
		}
		if (semaineDer) {
			Checklist_Mensuel semaine = new Checklist_Mensuel();
			semaine.setIdChecklist(base);
			semaine.setMensuel(5);
			listMensuelle.add(semaine);
		}

		return listMensuelle;
	}

	/**
	 * INSERT d'une nouvelle tache avec jours/horaires/sous taches pour une
	 * tache exceptionnelle
	 * 
	 * @param nom
	 * @param environnement
	 * @param dateDebut
	 * @param etat
	 * @param criticite
	 * @param typeDemande
	 * @param heureReception
	 * @param nomEmetteur
	 * @param numeroObs
	 * @param description
	 * @param listJoursExceptionnels
	 * @param typeFrequenceHeure
	 * @param listHeuresPonctuelles
	 * @param heureDebut
	 * @param heureFin
	 * @param frequence
	 * @param listSousTaches
	 * @throws Exception
	 */
	public static Integer saveExceptionnel(String nom, Integer environnement,
			Date dateDebut, Integer etat, Integer criticite, String typeDemande,
			Date heureReception, String nomEmetteur, String descriptionMail, String descriptionObs, String numeroObs,
			List<Date> listJoursExceptionnels, Integer typeFrequenceHeure,
			List<Date> listHeuresPonctuelles, Date heureDebut, Date heureFin,
			Date frequence, List<List<String>> listSousTaches,
			Map<String, List<String>> documentsSousTaches) throws Exception {

		Session session = PilotageSession.getCurrentSession();
		try {
			// tache
			Checklist_Base base = initChecklistBase(session, nom,
					environnement, dateDebut, etat, criticite, typeDemande, heureReception, nomEmetteur, descriptionMail, descriptionObs, numeroObs);

			// jours
			List<Checklist_Exceptionnel> jours_ex = initListChecklistExceptionnel(
					base, listJoursExceptionnels);

			// heures
			List<Checklist_Horaire> heures_ex = initListChecklistHoraire(base,
					typeFrequenceHeure, listHeuresPonctuelles, heureDebut,
					heureFin, frequence);

			// sous taches
			List<Checklist_Consignes> listConsignes = new ArrayList<Checklist_Consignes>();
			List<Checklist_Base_Soustache> listBaseSousTaches = new ArrayList<Checklist_Base_Soustache>();
			List<Checklist_Consigne_Documents> listDocuments = new ArrayList<Checklist_Consigne_Documents>();
			initConsignesEtSousTaches(base, listSousTaches, listConsignes,
					listBaseSousTaches, listDocuments, documentsSousTaches);

			// enregistrement en base
			session.save(base);
			for (Checklist_Exceptionnel jour : jours_ex) {
				session.save(jour);
			}
			for (Checklist_Horaire heure : heures_ex) {
				session.save(heure);
			}
			for (Checklist_Consignes consigne : listConsignes) {
				session.save(consigne);
			}
			for (Checklist_Base_Soustache sousTache : listBaseSousTaches) {
				session.save(sousTache);
			}
			for (Checklist_Consigne_Documents document : listDocuments) {
				session.save(document);
			}

			session.getTransaction().commit();
			return base.getId();
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}

	}

	/**
	 * INSERT d'une nouvelle tache avec jours/horaires/sous taches pour une
	 * tache autour des jours fériés
	 * 
	 * @param nom
	 * @param environnement
	 * @param dateDebut
	 * @param etat
	 * @param criticite
	 * @param typeDemande
	 * @param heureReception
	 * @param nomEmetteur
	 * @param numeroObs
	 * @param description
	 * @param veilleFerie
	 * @param jourFerie
	 * @param lendemainFerie
	 * @param typeFrequenceHeure
	 * @param listHeuresPonctuelles
	 * @param heureDebut
	 * @param heureFin
	 * @param frequence
	 * @param listSousTaches
	 * @throws Exception
	 */
	public static Integer saveFerie(String nom, Integer environnement,
			Date dateDebut, Integer etat, Integer criticite, String typeDemande,
			Date heureReception, String nomEmetteur, String descriptionMail, String descriptionObs, String numeroObs,
			Integer[] veilleFerie, Integer[] jourFerie,
			Integer[] lendemainFerie, Integer typeFrequenceHeure,
			List<Date> listHeuresPonctuelles, Date heureDebut, Date heureFin,
			Date frequence, List<List<String>> listSousTaches,
			Map<String, List<String>> documentsSousTaches) throws Exception {

		Session session = PilotageSession.getCurrentSession();
		try {
			// tache
			Checklist_Base base = initChecklistBase(session, nom,
					environnement, dateDebut, etat, criticite, typeDemande, heureReception, nomEmetteur, descriptionMail, descriptionObs, numeroObs);

			// jours
			List<Checklist_Ferie> listJours = initListChecklistFerie(session,
					base, veilleFerie, jourFerie, lendemainFerie);

			// heures
			List<Checklist_Horaire> heures_ex = initListChecklistHoraire(base,
					typeFrequenceHeure, listHeuresPonctuelles, heureDebut,
					heureFin, frequence);

			// sous taches
			List<Checklist_Consignes> listConsignes = new ArrayList<Checklist_Consignes>();
			List<Checklist_Base_Soustache> listBaseSousTaches = new ArrayList<Checklist_Base_Soustache>();
			List<Checklist_Consigne_Documents> listDocuments = new ArrayList<Checklist_Consigne_Documents>();
			initConsignesEtSousTaches(base, listSousTaches, listConsignes,
					listBaseSousTaches, listDocuments, documentsSousTaches);

			// enregistrement en base
			session.save(base);

			for (Checklist_Ferie jour : listJours) {
				session.save(jour);
			}
			for (Checklist_Horaire heure : heures_ex) {
				session.save(heure);
			}
			for (Checklist_Consignes consigne : listConsignes) {
				session.save(consigne);
			}
			for (Checklist_Base_Soustache sousTache : listBaseSousTaches) {
				session.save(sousTache);
			}
			for (Checklist_Consigne_Documents document : listDocuments) {
				session.save(document);
			}

			session.getTransaction().commit();
			return base.getId();
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * INSERT d'une nouvelle tache avec jours/horaires/sous taches pour une
	 * tache de fréquence semaine pair/impair
	 * 
	 * @param nom
	 * @param environnement
	 * @param dateDebut
	 * @param etat
	 * @param criticite
	 * @param typeDemande
	 * @param heureReception
	 * @param nomEmetteur
	 * @param numeroObs
	 * @param description
	 * @param pair
	 * @param impair
	 * @param lundi
	 * @param mardi
	 * @param mercredi
	 * @param jeudi
	 * @param vendredi
	 * @param samedi
	 * @param dimanche
	 * @param notFerie
	 * @param typeFrequenceHeure
	 * @param listHeuresPonctuelles
	 * @param heureDebut
	 * @param heureFin
	 * @param frequence
	 * @param listSousTaches
	 * @throws Exception
	 */
	public static Integer saveSemainePairImpair(String nom,
			Integer environnement, Date dateDebut, Integer etat,
			Integer criticite, String typeDemande,
			Date heureReception, String nomEmetteur, String descriptionMail, String descriptionObs, String numeroObs, boolean pair, boolean impair, boolean lundi,
			boolean mardi, boolean mercredi, boolean jeudi, boolean vendredi,
			boolean samedi, boolean dimanche, boolean notFerie,
			Integer typeFrequenceHeure, List<Date> listHeuresPonctuelles,
			Date heureDebut, Date heureFin, Date frequence,
			List<List<String>> listSousTaches,
			Map<String, List<String>> documentsSousTaches) throws Exception {

		Session session = PilotageSession.getCurrentSession();
		try {
			// tache
			Checklist_Base base = initChecklistBase(session, nom,
					environnement, dateDebut, etat, criticite, typeDemande, heureReception, nomEmetteur, descriptionMail, descriptionObs, numeroObs);

			// parité
			List<Checklist_Parite> listParite = initListChecklistParite(base,
					pair, impair);

			// jours
			List<Checklist_Jour> listJour = initJour(base, lundi, mardi,
					mercredi, jeudi, vendredi, samedi, dimanche, notFerie);

			// heures
			List<Checklist_Horaire> heures_ex = initListChecklistHoraire(base,
					typeFrequenceHeure, listHeuresPonctuelles, heureDebut,
					heureFin, frequence);

			// sous taches
			List<Checklist_Consignes> listConsignes = new ArrayList<Checklist_Consignes>();
			List<Checklist_Base_Soustache> listBaseSousTaches = new ArrayList<Checklist_Base_Soustache>();
			List<Checklist_Consigne_Documents> listDocuments = new ArrayList<Checklist_Consigne_Documents>();
			initConsignesEtSousTaches(base, listSousTaches, listConsignes,
					listBaseSousTaches, listDocuments, documentsSousTaches);

			// enregistrement en base
			session.save(base);
			for (Checklist_Parite parite : listParite) {
				session.save(parite);
			}
			for (Checklist_Jour jour : listJour) {
				session.save(jour);
			}
			for (Checklist_Horaire heure : heures_ex) {
				session.save(heure);
			}
			for (Checklist_Consignes consigne : listConsignes) {
				session.save(consigne);
			}
			for (Checklist_Base_Soustache sousTache : listBaseSousTaches) {
				session.save(sousTache);
			}
			for (Checklist_Consigne_Documents document : listDocuments) {
				session.save(document);
			}

			session.getTransaction().commit();
			return base.getId();
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * INSERT d'une nouvelle tache avec jours/horaires/sous taches pour une
	 * tache de fréquence mensuelle
	 * 
	 * @param nom
	 * @param environnement
	 * @param dateDebut
	 * @param etat
	 * @param criticite
	 * @param typeDemande
	 * @param heureReception
	 * @param nomEmetteur
	 * @param numeroObs
	 * @param description
	 * @param semaine1
	 * @param semaine2
	 * @param semaine3
	 * @param semaine4
	 * @param semaineDer
	 * @param lundi
	 * @param mardi
	 * @param mercredi
	 * @param jeudi
	 * @param vendredi
	 * @param samedi
	 * @param dimanche
	 * @param jour
	 * @param notFerie
	 * @param typeFrequenceHeure
	 * @param listHeuresPonctuelles
	 * @param heureDebut
	 * @param heureFin
	 * @param frequence
	 * @param listSousTaches
	 * @throws Exception
	 */
	// TODO : attente de GCE sur à quoi correspond l'info 'Jour'
	public static Integer saveMensuel(String nom, Integer environnement,
			Date dateDebut, Integer etat, Integer criticite, String typeDemande,
			Date heureReception, String nomEmetteur, String descriptionMail, String descriptionObs, String numeroObs, boolean semaine1,
			boolean semaine2, boolean semaine3, boolean semaine4,
			boolean semaineDer, boolean lundi, boolean mardi, boolean mercredi,
			boolean jeudi, boolean vendredi, boolean samedi, boolean dimanche,
			boolean jour, boolean notFerie, Integer typeFrequenceHeure,
			List<Date> listHeuresPonctuelles, Date heureDebut, Date heureFin,
			Date frequence, List<List<String>> listSousTaches,
			Map<String, List<String>> documentsSousTaches) throws Exception {

		Session session = PilotageSession.getCurrentSession();
		try {
			// tache
			Checklist_Base base = initChecklistBase(session, nom,
					environnement, dateDebut, etat, criticite, typeDemande, heureReception, nomEmetteur, descriptionMail, descriptionObs, numeroObs);

			// semaines
			List<Checklist_Mensuel> listMensuelle = initListChecklistMensuel(
					base, semaine1, semaine2, semaine3, semaine4, semaineDer);

			// jours
			List<Checklist_Jour> listJour = initJour(base, lundi, mardi,
					mercredi, jeudi, vendredi, samedi, dimanche, notFerie);

			// heures
			List<Checklist_Horaire> heures_ex = initListChecklistHoraire(base,
					typeFrequenceHeure, listHeuresPonctuelles, heureDebut,
					heureFin, frequence);

			// sous taches
			List<Checklist_Consignes> listConsignes = new ArrayList<Checklist_Consignes>();
			List<Checklist_Base_Soustache> listBaseSousTaches = new ArrayList<Checklist_Base_Soustache>();
			List<Checklist_Consigne_Documents> listDocuments = new ArrayList<Checklist_Consigne_Documents>();
			initConsignesEtSousTaches(base, listSousTaches, listConsignes,
					listBaseSousTaches, listDocuments, documentsSousTaches);

			// enregistrement en base
			session.save(base);
			for (Checklist_Mensuel mensuel : listMensuelle) {
				session.save(mensuel);
			}
			for (Checklist_Jour chkJour : listJour) {
				session.save(chkJour);
			}
			for (Checklist_Horaire heure : heures_ex) {
				session.save(heure);
			}
			for (Checklist_Consignes consigne : listConsignes) {
				session.save(consigne);
			}
			for (Checklist_Base_Soustache sousTache : listBaseSousTaches) {
				session.save(sousTache);
			}
			for (Checklist_Consigne_Documents document : listDocuments) {
				session.save(document);
			}
			session.getTransaction().commit();
			return base.getId();
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * INSERT d'une nouvelle tache avec jours/horaires/sous taches pour une
	 * tache de fréquence hebdo
	 * 
	 * @param nom
	 * @param environnement
	 * @param dateDebut
	 * @param etat
	 * @param criticite
	 * @param typeDemande
	 * @param heureReception
	 * @param nomEmetteur
	 * @param numeroObs
	 * @param description
	 * @param lundi
	 * @param mardi
	 * @param mercredi
	 * @param jeudi
	 * @param vendredi
	 * @param samedi
	 * @param dimanche
	 * @param notFerie
	 * @param typeFrequenceHeure
	 * @param listHeuresPonctuelles
	 * @param heureDebut
	 * @param heureFin
	 * @param frequence
	 * @param listSousTaches
	 * @throws Exception
	 */
	public static Integer saveHebdo(String nom, Integer environnement,
			Date dateDebut, Integer etat, Integer criticite, String typeDemande,
			Date heureReception, String nomEmetteur, String descriptionMail, String descriptionObs, String numeroObs, boolean lundi,
			boolean mardi, boolean mercredi, boolean jeudi, boolean vendredi,
			boolean samedi, boolean dimanche, boolean notFerie,
			Integer typeFrequenceHeure, List<Date> listHeuresPonctuelles,
			Date heureDebut, Date heureFin, Date frequence,
			List<List<String>> listSousTaches,
			Map<String, List<String>> documentsSousTaches) throws Exception {

		Session session = PilotageSession.getCurrentSession();
		try {
			// tache
			Checklist_Base base = initChecklistBase(session, nom,
					environnement, dateDebut, etat, criticite, typeDemande, heureReception, nomEmetteur, descriptionMail, descriptionObs, numeroObs);

			// jours
			List<Checklist_Jour> listJours = initJour(base, lundi, mardi,
					mercredi, jeudi, vendredi, samedi, dimanche, notFerie);

			// heures
			List<Checklist_Horaire> heures_ex = initListChecklistHoraire(base,
					typeFrequenceHeure, listHeuresPonctuelles, heureDebut,
					heureFin, frequence);

			// sous taches
			List<Checklist_Consignes> listConsignes = new ArrayList<Checklist_Consignes>();
			List<Checklist_Base_Soustache> listBaseSousTaches = new ArrayList<Checklist_Base_Soustache>();
			List<Checklist_Consigne_Documents> listDocuments = new ArrayList<Checklist_Consigne_Documents>();
			initConsignesEtSousTaches(base, listSousTaches, listConsignes,
					listBaseSousTaches, listDocuments, documentsSousTaches);

			// enregistrement en base
			session.save(base);

			for (Checklist_Jour jour : listJours) {
				session.save(jour);
			}
			for (Checklist_Horaire heure : heures_ex) {
				session.save(heure);
			}
			for (Checklist_Consignes consigne : listConsignes) {
				session.save(consigne);
			}
			for (Checklist_Base_Soustache sousTache : listBaseSousTaches) {
				session.save(sousTache);
			}
			for (Checklist_Consigne_Documents document : listDocuments) {
				session.save(document);
			}
			session.getTransaction().commit();
			return base.getId();
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * Initialisation des objets Checklist_exceptionnel à
	 * ajouter/modifier/supprimer
	 * 
	 * @param session
	 * @param base
	 * @param listChecklistExceptionnelToAdd
	 * @param listChecklistExceptionnelToDelete
	 * @param listChecklistExceptionnelObjectToAdd
	 * @param listChecklistExceptionnelObjectToDelete
	 */
	private static void initJoursExceptionnelsModificationLists(
			Session session, Checklist_Base base,
			List<Date> listChecklistExceptionnelToAdd,
			List<Integer> listChecklistExceptionnelToDelete,
			List<Checklist_Exceptionnel> listChecklistExceptionnelObjectToAdd,
			List<Checklist_Exceptionnel> listChecklistExceptionnelObjectToDelete) {

		for (Date dateToAdd : listChecklistExceptionnelToAdd) {
			Checklist_Exceptionnel jourExToAdd = new Checklist_Exceptionnel();
			jourExToAdd.setIdChecklist(base);
			jourExToAdd.setJour(dateToAdd);
			listChecklistExceptionnelObjectToAdd.add(jourExToAdd);
		}
		for (Integer jourToDeleteID : listChecklistExceptionnelToDelete) {
			Checklist_Exceptionnel jourExToDelete = (Checklist_Exceptionnel) session
					.load(Checklist_Exceptionnel.class, jourToDeleteID);
			listChecklistExceptionnelObjectToDelete.add(jourExToDelete);
		}
	}

	/**
	 * Initialisation des objets Checklist_Ferie à ajouter/modifier/supprimer
	 * 
	 * @param session
	 * @param base
	 * @param listChecklistFerieVeilleToAdd
	 * @param listChecklistFerieJourToAdd
	 * @param listChecklistFerieLendemainToAdd
	 * @param listChecklistFerieToDelete
	 * @param listChecklistFerieObjectToAdd
	 * @param listChecklistFerieObjectToDelete
	 */
	private static void initFeriesModificationLists(Session session,
			Checklist_Base base, List<Integer> listChecklistFerieVeilleToAdd,
			List<Integer> listChecklistFerieJourToAdd,
			List<Integer> listChecklistFerieLendemainToAdd,
			List<Integer> listChecklistFerieToDelete,
			List<Checklist_Ferie> listChecklistFerieObjectToAdd,
			List<Checklist_Ferie> listChecklistFerieObjectToDelete) {
		for (Integer ferieID : listChecklistFerieToDelete) {
			Checklist_Ferie ferieToDetele = (Checklist_Ferie) session.load(
					Checklist_Ferie.class, ferieID);
			listChecklistFerieObjectToDelete.add(ferieToDetele);
		}
		// veilles à ajouter
		for (Integer ferieID : listChecklistFerieVeilleToAdd) {
			Jour_Ferie jourFerie = (Jour_Ferie) session.load(Jour_Ferie.class,
					ferieID);

			Checklist_Ferie ferieToAdd = new Checklist_Ferie();
			ferieToAdd.setIdChecklist(base);
			ferieToAdd.setIdJourFerie(jourFerie);
			ferieToAdd.setLendeveille(PilotageConstants.VEILLE_FERIE);

			listChecklistFerieObjectToAdd.add(ferieToAdd);
		}
		// jours à ajouter
		for (Integer ferieID : listChecklistFerieJourToAdd) {
			Jour_Ferie jourFerie = (Jour_Ferie) session.load(Jour_Ferie.class,
					ferieID);

			Checklist_Ferie ferieToAdd = new Checklist_Ferie();
			ferieToAdd.setIdChecklist(base);
			ferieToAdd.setIdJourFerie(jourFerie);
			ferieToAdd.setLendeveille(PilotageConstants.JOUR_FERIE);

			listChecklistFerieObjectToAdd.add(ferieToAdd);
		}
		// lendemains à ajouter
		for (Integer ferieID : listChecklistFerieLendemainToAdd) {
			Jour_Ferie jourFerie = (Jour_Ferie) session.load(Jour_Ferie.class,
					ferieID);

			Checklist_Ferie ferieToAdd = new Checklist_Ferie();
			ferieToAdd.setIdChecklist(base);
			ferieToAdd.setIdJourFerie(jourFerie);
			ferieToAdd.setLendeveille(PilotageConstants.LENDEMAIN_FERIE);

			listChecklistFerieObjectToAdd.add(ferieToAdd);
		}
	}

	/**
	 * Initialisation des objets Checklist_Parite à ajouter/modifier/supprimer
	 * 
	 * @param session
	 * @param base
	 * @param listChecklistPariteToAdd
	 * @param listChecklistPariteToDelete
	 * @param listChecklistPariteObjectToAdd
	 * @param listChecklistPariteObjectToDelete
	 */
	private static void initParitesModificationLists(Session session,
			Checklist_Base base, List<Integer> listChecklistPariteToAdd,
			List<Integer> listChecklistPariteToDelete,
			List<Checklist_Parite> listChecklistPariteObjectToAdd,
			List<Checklist_Parite> listChecklistPariteObjectToDelete) {
		for (Integer pariteID : listChecklistPariteToDelete) {
			Checklist_Parite pariteToDetele = (Checklist_Parite) session.load(
					Checklist_Parite.class, pariteID);
			listChecklistPariteObjectToDelete.add(pariteToDetele);
		}
		for (Integer pairImpair : listChecklistPariteToAdd) {
			Checklist_Parite pariteToAdd = new Checklist_Parite();
			pariteToAdd.setIdChecklist(base);
			pariteToAdd.setParite(pairImpair);
			listChecklistPariteObjectToAdd.add(pariteToAdd);
		}
	}

	/**
	 * Initialisation des objets Checklist_Mensuel à ajouter/modifier/supprimer
	 * 
	 * @param session
	 * @param base
	 * @param listChecklistMensuelToAdd
	 * @param listChecklistMensuelToDelete
	 * @param listChecklistMensuelObjectToAdd
	 * @param listChecklistMensuelObjectToDelete
	 */
	private static void initMensuelsModificationLists(Session session,
			Checklist_Base base, List<Integer> listChecklistMensuelToAdd,
			List<Integer> listChecklistMensuelToDelete,
			List<Checklist_Mensuel> listChecklistMensuelObjectToAdd,
			List<Checklist_Mensuel> listChecklistMensuelObjectToDelete) {
		for (Integer mensuelleID : listChecklistMensuelToDelete) {
			Checklist_Mensuel mensuelToDetele = (Checklist_Mensuel) session
					.load(Checklist_Mensuel.class, mensuelleID);
			listChecklistMensuelObjectToDelete.add(mensuelToDetele);
		}
		for (Integer semaineDuMois : listChecklistMensuelToAdd) {
			Checklist_Mensuel mensuelToAdd = new Checklist_Mensuel();
			mensuelToAdd.setIdChecklist(base);
			mensuelToAdd.setMensuel(semaineDuMois);
			listChecklistMensuelObjectToAdd.add(mensuelToAdd);
		}
	}

	/**
	 * Initialisation des objets Checklist_Jour à ajouter/modifier/supprimer
	 * 
	 * @param session
	 * @param base
	 * @param listChecklistJourToAdd
	 * @param listChecklistJourToModify
	 * @param listChecklistJourToDelete
	 * @param notFerie
	 * @param listChecklistJourObjectToAdd
	 * @param listChecklistJourObjectToUpdate
	 * @param listChecklistJourObjectToDelete
	 */
	private static void initJoursModificationLists(Session session,
			Checklist_Base base, List<Integer> listChecklistJourToAdd,
			List<Integer> listChecklistJourToModify,
			List<Integer> listChecklistJourToDelete, boolean notFerie,
			List<Checklist_Jour> listChecklistJourObjectToAdd,
			List<Checklist_Jour> listChecklistJourObjectToUpdate,
			List<Checklist_Jour> listChecklistJourObjectToDelete) {

		for (Integer jourID : listChecklistJourToDelete) {
			Checklist_Jour jourToDelete = (Checklist_Jour) session.load(
					Checklist_Jour.class, jourID);
			listChecklistJourObjectToDelete.add(jourToDelete);
		}
		for (Integer jourID : listChecklistJourToModify) {
			Checklist_Jour jourToModify = (Checklist_Jour) session.load(
					Checklist_Jour.class, jourID);
			jourToModify.setFerie(!notFerie);
			listChecklistJourObjectToUpdate.add(jourToModify);
		}
		for (Integer jourDeLaSemaine : listChecklistJourToAdd) {
			Checklist_Jour jourToAdd = new Checklist_Jour();
			jourToAdd.setIdChecklist(base);
			jourToAdd.setJour(jourDeLaSemaine);
			jourToAdd.setFerie(!notFerie);
			listChecklistJourObjectToAdd.add(jourToAdd);
		}
	}

	/**
	 * COUNT test si une tache en checklist pointe vers la sous tache
	 * 
	 * @param session
	 * @param sousTache
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static boolean hasChecklistCurrentWithSousTache(Session session,
			Checklist_Base_Soustache sousTache) {

		Criteria criteria = session.createCriteria(Checklist_Current.class);
		criteria.add(Restrictions.eq("sousTache", sousTache));
		criteria.setProjection(Projections.rowCount());

		List<Long> results = criteria.list();

		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * COUNT test si une tache en checklist pointe vers l'horaire
	 * 
	 * @param session
	 * @param sousTache
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static boolean hasChecklistCurrentWithHoraire(Session session,
			Checklist_Horaire horaire) {

		Criteria criteria = session.createCriteria(Checklist_Current.class);
		criteria.add(Restrictions.eq("idHoraire", horaire));
		criteria.setProjection(Projections.rowCount());

		List<Long> results = criteria.list();

		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Modification d'une tache
	 * 
	 * @param id
	 * @param nom
	 * @param environnement
	 * @param dateDebut
	 * @param etat
	 * @param criticite
	 * @param typeDemande
	 * @param heureReception
	 * @param nomEmetteur
	 * @param numeroObs
	 * @param description
	 * @param typeFrequenceDate
	 * @param oldTypeFrequenceDate
	 * @param listChecklistJourToAdd
	 * @param listChecklistJourToModify
	 * @param listChecklistJourToDelete
	 * @param listChecklistMensuelToAdd
	 * @param listChecklistMensuelToDelete
	 * @param listChecklistPariteToAdd
	 * @param listChecklistPariteToDelete
	 * @param listChecklistFerieVeilleToAdd
	 * @param listChecklistFerieJourToAdd
	 * @param listChecklistFerieLendemainToAdd
	 * @param listChecklistFerieToDelete
	 * @param listChecklistExceptionnelToAdd
	 * @param listChecklistExceptionnelToDelete
	 * @param f1_lundi
	 * @param f1_mardi
	 * @param f1_mercredi
	 * @param f1_jeudi
	 * @param f1_vendredi
	 * @param f1_samedi
	 * @param f1_dimanche
	 * @param f1_notFerie
	 * @param f2_1er
	 * @param f2_2eme
	 * @param f2_3eme
	 * @param f2_4eme
	 * @param f2_der
	 * @param f2_lundi
	 * @param f2_mardi
	 * @param f2_mercredi
	 * @param f2_jeudi
	 * @param f2_vendredi
	 * @param f2_samedi
	 * @param f2_dimanche
	 * @param f2_jour
	 * @param f2_notFerie
	 * @param f3_pair
	 * @param f3_impair
	 * @param f3_lundi
	 * @param f3_mardi
	 * @param f3_mercredi
	 * @param f3_jeudi
	 * @param f3_vendredi
	 * @param f3_samedi
	 * @param f3_dimanche
	 * @param f3_notFerie
	 * @param veilleFerie
	 * @param jourFerie
	 * @param lendemainFerie
	 * @param listJoursExceptionnels
	 * @param typeFrequenceHeure
	 * @param oldTypeFrequenceHeure
	 * @param oldHeureDebutString
	 * @param oldHeureFinString
	 * @param oldFrequenceString
	 * @param heureDebutString
	 * @param heureFiString
	 * @param frequenceString
	 * @param listHeuresPonctuelles
	 * @param heureDebut
	 * @param heureFin
	 * @param frequence
	 * @param listSousTacheToAdd
	 * @param listSousTacheToUpdate
	 * @param listSousTacheToDelete
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void modify(Integer id, String nom, Integer environnement,
			Date dateDebut, Integer etat, Integer criticite, String typeDemande, Date heureReception, String nomEmetteur, String descriptionMail, String descriptionObs, String numeroObs,
			Integer typeFrequenceDate, Integer oldTypeFrequenceDate,
			List<Integer> listChecklistJourToAdd,
			List<Integer> listChecklistJourToModify,
			List<Integer> listChecklistJourToDelete,
			List<Integer> listChecklistMensuelToAdd,
			List<Integer> listChecklistMensuelToDelete,
			List<Integer> listChecklistPariteToAdd,
			List<Integer> listChecklistPariteToDelete,
			List<Integer> listChecklistFerieVeilleToAdd,
			List<Integer> listChecklistFerieJourToAdd,
			List<Integer> listChecklistFerieLendemainToAdd,
			List<Integer> listChecklistFerieToDelete,
			List<Date> listChecklistExceptionnelToAdd,
			List<Integer> listChecklistExceptionnelToDelete,

			boolean f1_lundi, boolean f1_mardi, boolean f1_mercredi,
			boolean f1_jeudi, boolean f1_vendredi, boolean f1_samedi,
			boolean f1_dimanche, boolean f1_notFerie, boolean f2_1er,
			boolean f2_2eme, boolean f2_3eme, boolean f2_4eme, boolean f2_der,
			boolean f2_lundi, boolean f2_mardi, boolean f2_mercredi,
			boolean f2_jeudi, boolean f2_vendredi, boolean f2_samedi,
			boolean f2_dimanche, boolean f2_jour, boolean f2_notFerie,
			boolean f3_pair, boolean f3_impair, boolean f3_lundi,
			boolean f3_mardi, boolean f3_mercredi, boolean f3_jeudi,
			boolean f3_vendredi, boolean f3_samedi, boolean f3_dimanche,
			boolean f3_notFerie, Integer[] veilleFerie, Integer[] jourFerie,
			Integer[] lendemainFerie, List<Date> listJoursExceptionnels,

			Integer typeFrequenceHeure, Integer oldTypeFrequenceHeure,
			String oldHeureDebutString, String oldHeureFinString,
			String oldFrequenceString, String heureDebutString,
			String heureFiString, String frequenceString,

			List<Date> listHeuresPonctuelles, Date heureDebut, Date heureFin,
			Date frequence,

			List<List<String>> listSousTacheToAdd,
			Map<Integer, List<String>> listSousTacheToUpdate,
			List<Integer> listSousTacheToDelete,
			Map<String, List<String>> listDocumentToAdd,
			Map<String, List<String>> documentsSousTache) throws Exception {

		Session session = PilotageSession.getCurrentSession();
		try {
			// déclarations des listes d'objets potentiels
			List<Checklist_Jour> listChecklistJourObjectToAdd = null;
			List<Checklist_Jour> listChecklistJourObjectToUpdate = null;
			List<Checklist_Jour> listChecklistJourObjectToDelete = null;
			List<Checklist_Mensuel> listChecklistMensuelObjectToAdd = null;
			List<Checklist_Mensuel> listChecklistMensuelObjectToDelete = null;
			List<Checklist_Parite> listChecklistPariteObjectToAdd = null;
			List<Checklist_Parite> listChecklistPariteObjectToDelete = null;
			List<Checklist_Ferie> listChecklistFerieObjectToAdd = null;
			List<Checklist_Ferie> listChecklistFerieObjectToDelete = null;
			List<Checklist_Exceptionnel> listChecklistExceptionnelObjectToAdd = null;
			List<Checklist_Exceptionnel> listChecklistExceptionnelObjectToDelete = null;
			List<Checklist_Horaire> listChecklistHoraireObjectToAdd = null;
			List<Checklist_Horaire> listChecklistHoraireObjectToDelete = null;
			List<Checklist_Horaire> listChecklistHoraireObjectToDesactivate = null;
			List<Checklist_Base_Soustache> listChecklistBaseSousTacheObjectToAdd = null;
			List<Checklist_Base_Soustache> listChecklistBaseSousTacheObjectToDelete = null;
			List<Checklist_Base_Soustache> listChecklistBaseSousTacheObjectToDesactivate = null;
			List<Checklist_Consignes> listChecklistConsigneObjectToDelete = null;
			List<Checklist_Consignes> listChecklistConsigneObjectToUpdate = null;
			List<Checklist_Consignes> listChecklistConsigneObjectToAdd = null;
			List<Checklist_Consigne_Documents> listDocumentsConsigneObjectToAdd = null;
			List<Checklist_Consigne_Documents> listDocumentsConsigneObjectToDelete = null;

			// modification de la tache de base
			Environnement env = (Environnement) session.load(
					Environnement.class, environnement);
			Checklist_Etat et = (Checklist_Etat) session.load(
					Checklist_Etat.class, etat);
			Checklist_Criticite crit = (Checklist_Criticite) session.load(
					Checklist_Criticite.class, criticite);

			Checklist_Base base = (Checklist_Base) session.load(
					Checklist_Base.class, id);
			base.setTache(nom);
			base.setEnvironnement(env);
			base.setDateDebut(dateDebut);
			base.setEtat(et);
			base.setCriticite(crit);
			base.setTypeDemande(typeDemande);
			
			if (heureReception != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date currentDate = new Date();
				String currentDateString = dateFormat.format(currentDate);
				SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
				String heure = dateFormat2.format(heureReception);
				String finalDate = currentDateString + " " + heure;
				SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				try {
					Date date = dateFormat3.parse(finalDate);
					Timestamp datetime = new Timestamp(date.getTime());
					base.setHeureReception(datetime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				base.setHeureReception(null);
			}
			
			base.setNomEmetteur(nomEmetteur);
			base.setNumeroObs(numeroObs);
			base.setDescriptionMail(descriptionMail);
			base.setDescriptionObs(descriptionObs);

			// Date : frequence non changée
			if (typeFrequenceDate.equals(oldTypeFrequenceDate)) {
				// frequence hebdo
				if (typeFrequenceDate.equals(PilotageConstants.DATE_FREQ_HEBDO)) {
					listChecklistJourObjectToAdd = new ArrayList<Checklist_Jour>();
					listChecklistJourObjectToUpdate = new ArrayList<Checklist_Jour>();
					listChecklistJourObjectToDelete = new ArrayList<Checklist_Jour>();

					// jours
					initJoursModificationLists(session, base,
							listChecklistJourToAdd, listChecklistJourToModify,
							listChecklistJourToDelete, f1_notFerie,
							listChecklistJourObjectToAdd,
							listChecklistJourObjectToUpdate,
							listChecklistJourObjectToDelete);

				}
				// frequence mensuelle
				else if (typeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_MENSUELLE)) {
					listChecklistMensuelObjectToAdd = new ArrayList<Checklist_Mensuel>();
					listChecklistMensuelObjectToDelete = new ArrayList<Checklist_Mensuel>();
					listChecklistJourObjectToAdd = new ArrayList<Checklist_Jour>();
					listChecklistJourObjectToUpdate = new ArrayList<Checklist_Jour>();
					listChecklistJourObjectToDelete = new ArrayList<Checklist_Jour>();

					// Mensuels
					initMensuelsModificationLists(session, base,
							listChecklistMensuelToAdd,
							listChecklistMensuelToDelete,
							listChecklistMensuelObjectToAdd,
							listChecklistMensuelObjectToDelete);

					// jours
					initJoursModificationLists(session, base,
							listChecklistJourToAdd, listChecklistJourToModify,
							listChecklistJourToDelete, f2_notFerie,
							listChecklistJourObjectToAdd,
							listChecklistJourObjectToUpdate,
							listChecklistJourObjectToDelete);
				}
				// frequence semaine pair/impair
				else if (typeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_PAIR_IMPAIR)) {
					listChecklistPariteObjectToAdd = new ArrayList<Checklist_Parite>();
					listChecklistPariteObjectToDelete = new ArrayList<Checklist_Parite>();
					listChecklistJourObjectToAdd = new ArrayList<Checklist_Jour>();
					listChecklistJourObjectToUpdate = new ArrayList<Checklist_Jour>();
					listChecklistJourObjectToDelete = new ArrayList<Checklist_Jour>();

					// Parite
					initParitesModificationLists(session, base,
							listChecklistPariteToAdd,
							listChecklistPariteToDelete,
							listChecklistPariteObjectToAdd,
							listChecklistPariteObjectToDelete);

					// jours
					initJoursModificationLists(session, base,
							listChecklistJourToAdd, listChecklistJourToModify,
							listChecklistJourToDelete, f3_notFerie,
							listChecklistJourObjectToAdd,
							listChecklistJourObjectToUpdate,
							listChecklistJourObjectToDelete);
				}
				// frequence jours fériés
				else if (typeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_FERIE)) {
					listChecklistFerieObjectToAdd = new ArrayList<Checklist_Ferie>();
					listChecklistFerieObjectToDelete = new ArrayList<Checklist_Ferie>();

					// Ferie
					initFeriesModificationLists(session, base,
							listChecklistFerieVeilleToAdd,
							listChecklistFerieJourToAdd,
							listChecklistFerieLendemainToAdd,
							listChecklistFerieToDelete,
							listChecklistFerieObjectToAdd,
							listChecklistFerieObjectToDelete);
				}
				// frequence jours exceptionnels
				else if (typeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_EXCEPTIONNELLE)) {
					listChecklistExceptionnelObjectToAdd = new ArrayList<Checklist_Exceptionnel>();
					listChecklistExceptionnelObjectToDelete = new ArrayList<Checklist_Exceptionnel>();

					// jours exceptionnels
					initJoursExceptionnelsModificationLists(session, base,
							listChecklistExceptionnelToAdd,
							listChecklistExceptionnelToDelete,
							listChecklistExceptionnelObjectToAdd,
							listChecklistExceptionnelObjectToDelete);
				}
			} else {
				// recupération des objets à supprimer de l'ancienne fréquence
				// ancienne frequence hebdo
				if (oldTypeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_HEBDO)) {
					listChecklistJourObjectToDelete = session
							.createCriteria(Checklist_Jour.class)
							.add(Restrictions.eq("idChecklist", base)).list();
				}
				// ancienne frequence mensuelle
				else if (oldTypeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_MENSUELLE)) {
					listChecklistMensuelObjectToDelete = session
							.createCriteria(Checklist_Mensuel.class)
							.add(Restrictions.eq("idChecklist", base)).list();
					listChecklistJourObjectToDelete = session
							.createCriteria(Checklist_Jour.class)
							.add(Restrictions.eq("idChecklist", base)).list();
				}
				// ancienne frequence semaine pair/impair
				else if (oldTypeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_PAIR_IMPAIR)) {
					listChecklistPariteObjectToDelete = session
							.createCriteria(Checklist_Parite.class)
							.add(Restrictions.eq("idChecklist", base)).list();
					listChecklistJourObjectToDelete = session
							.createCriteria(Checklist_Jour.class)
							.add(Restrictions.eq("idChecklist", base)).list();
				}
				// ancienne frequence jours fériés
				else if (oldTypeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_FERIE)) {
					listChecklistFerieObjectToDelete = session
							.createCriteria(Checklist_Ferie.class)
							.add(Restrictions.eq("idChecklist", base)).list();
				}
				// ancienne frequence jours exceptionnels
				else if (oldTypeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_EXCEPTIONNELLE)) {
					listChecklistExceptionnelObjectToDelete = session
							.createCriteria(Checklist_Exceptionnel.class)
							.add(Restrictions.eq("idChecklist", base)).list();
				}

				// récupération des objets à ajouter de la nouvelle fréquence
				// frequence hebdo
				if (typeFrequenceDate.equals(PilotageConstants.DATE_FREQ_HEBDO)) {
					listChecklistJourObjectToAdd = initJour(base, f1_lundi,
							f1_mardi, f1_mercredi, f1_jeudi, f1_vendredi,
							f1_samedi, f1_dimanche, f1_notFerie);
				}
				// frequence mensuelle
				else if (typeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_MENSUELLE)) {
					listChecklistMensuelObjectToAdd = initListChecklistMensuel(
							base, f2_1er, f2_2eme, f2_3eme, f2_4eme, f2_der);
					listChecklistJourObjectToAdd = initJour(base, f2_lundi,
							f2_mardi, f2_mercredi, f2_jeudi, f2_vendredi,
							f2_samedi, f2_dimanche, f2_notFerie);
				}
				// frequence semaine pair/impair
				else if (typeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_PAIR_IMPAIR)) {
					listChecklistPariteObjectToAdd = initListChecklistParite(
							base, f3_pair, f3_impair);
					listChecklistJourObjectToAdd = initJour(base, f3_lundi,
							f3_mardi, f3_mercredi, f3_jeudi, f3_vendredi,
							f3_samedi, f3_dimanche, f3_notFerie);
				}
				// frequence jours fériés
				else if (typeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_FERIE)) {
					listChecklistFerieObjectToAdd = initListChecklistFerie(
							session, base, veilleFerie, jourFerie,
							lendemainFerie);
				}
				// frequence jours exceptionnels
				else if (typeFrequenceDate
						.equals(PilotageConstants.DATE_FREQ_EXCEPTIONNELLE)) {
					listChecklistExceptionnelObjectToAdd = initListChecklistExceptionnel(
							base, listJoursExceptionnels);
				}
			}

			// Horaire
			if (oldTypeFrequenceHeure.equals(typeFrequenceHeure)) {
				List<Checklist_Horaire> listChecklistHoraireEnBase = session
						.createCriteria(Checklist_Horaire.class)
						.add(Restrictions.eq("idChecklist", base))
						.add(Restrictions.eq("actif", Boolean.TRUE)).list();
				List<Checklist_Horaire> listChecklistHoraireSurEcran = initListChecklistHoraire(
						base, oldTypeFrequenceHeure, listHeuresPonctuelles,
						heureDebut, heureFin, frequence);

				listChecklistHoraireObjectToDelete = new ArrayList<Checklist_Horaire>();
				listChecklistHoraireObjectToAdd = new ArrayList<Checklist_Horaire>();
				listChecklistHoraireObjectToDesactivate = new ArrayList<Checklist_Horaire>();

				for (Checklist_Horaire horaireEnBase : listChecklistHoraireEnBase) {
					Long timestampEnBase = horaireEnBase.getHoraireStamp();
					boolean stillOnScreen = false;
					for (Checklist_Horaire horaireSurEcran : listChecklistHoraireSurEcran) {
						if (horaireSurEcran.getHoraireStamp().equals(
								timestampEnBase)) {
							stillOnScreen = true;
							break;
						}
					}
					if (!stillOnScreen) {
						if (hasChecklistCurrentWithHoraire(session,
								horaireEnBase)) {
							horaireEnBase.setActif(Boolean.FALSE);
							listChecklistHoraireObjectToDesactivate
									.add(horaireEnBase);
						} else
							listChecklistHoraireObjectToDelete
									.add(horaireEnBase);
					}
				}
				for (Checklist_Horaire horaireSurEcran : listChecklistHoraireSurEcran) {
					Long timestampSurEcran = horaireSurEcran.getHoraireStamp();
					boolean alreadyInBase = false;
					for (Checklist_Horaire horaireEnBase : listChecklistHoraireEnBase) {
						if (horaireEnBase.getHoraireStamp().equals(
								timestampSurEcran)) {
							alreadyInBase = true;
							break;
						}
					}
					if (!alreadyInBase)
						listChecklistHoraireObjectToAdd.add(horaireSurEcran);
				}
			} else {
				// suppression de toutes les horaires et insertion de toutes les
				// nouvelles
				listChecklistHoraireObjectToDelete = new ArrayList<Checklist_Horaire>();
				listChecklistHoraireObjectToDesactivate = new ArrayList<Checklist_Horaire>();
				List<Checklist_Horaire> horairesEnBase = session
						.createCriteria(Checklist_Horaire.class)
						.add(Restrictions.eq("idChecklist", base))
						.add(Restrictions.eq("actif", Boolean.TRUE)).list();
				for (Checklist_Horaire horaire : horairesEnBase) {
					if (hasChecklistCurrentWithHoraire(session, horaire)) {
						horaire.setActif(Boolean.FALSE);
						listChecklistHoraireObjectToDesactivate.add(horaire);
					} else
						listChecklistHoraireObjectToDelete.add(horaire);
				}

				listChecklistHoraireObjectToAdd = initListChecklistHoraire(
						base, typeFrequenceHeure, listHeuresPonctuelles,
						heureDebut, heureFin, frequence);
			}

			// Sous taches
			listChecklistBaseSousTacheObjectToDelete = new ArrayList<Checklist_Base_Soustache>();
			listChecklistBaseSousTacheObjectToAdd = new ArrayList<Checklist_Base_Soustache>();
			listChecklistBaseSousTacheObjectToDesactivate = new ArrayList<Checklist_Base_Soustache>();
			listChecklistConsigneObjectToDelete = new ArrayList<Checklist_Consignes>();
			listChecklistConsigneObjectToUpdate = new ArrayList<Checklist_Consignes>();
			listChecklistConsigneObjectToAdd = new ArrayList<Checklist_Consignes>();
			listDocumentsConsigneObjectToAdd = new ArrayList<Checklist_Consigne_Documents>();
			listDocumentsConsigneObjectToDelete = new ArrayList<Checklist_Consigne_Documents>();

			// DELETE
			for (Integer sousTacheID : listSousTacheToDelete) {
				Checklist_Base_Soustache sousTacheToDelete = (Checklist_Base_Soustache) session
						.load(Checklist_Base_Soustache.class, sousTacheID);
				if (hasChecklistCurrentWithSousTache(session, sousTacheToDelete)) {
					sousTacheToDelete.setActif(Boolean.FALSE);
					listChecklistBaseSousTacheObjectToDesactivate
							.add(sousTacheToDelete);
				} else {
					listChecklistBaseSousTacheObjectToDelete
							.add(sousTacheToDelete);
					listChecklistConsigneObjectToDelete.add(sousTacheToDelete
							.getIdConsigne());
					List<Checklist_Consigne_Documents> listDocumentToDelete = session
							.createCriteria(Checklist_Consigne_Documents.class)
							.add(Restrictions.eq("idConsigne",
									sousTacheToDelete.getIdConsigne())).list();
					for (Checklist_Consigne_Documents document : listDocumentToDelete)
						listDocumentsConsigneObjectToDelete.add(document);
				}
			}

			// UPDATE
			for (Entry<Integer, List<String>> sousTacheEntry : listSousTacheToUpdate
					.entrySet()) {
				Checklist_Base_Soustache sousTacheToUpdate = (Checklist_Base_Soustache) session
						.load(Checklist_Base_Soustache.class,
								sousTacheEntry.getKey());
				sousTacheToUpdate.getIdConsigne().setConsigne(
						sousTacheEntry.getValue().get(3));
				listChecklistConsigneObjectToUpdate.add(sousTacheToUpdate
						.getIdConsigne());
				List<Checklist_Consigne_Documents> listDocumentToDelete = session
						.createCriteria(Checklist_Consigne_Documents.class)
						.add(Restrictions.eq("idConsigne",
								sousTacheToUpdate.getIdConsigne())).list();
				for (Checklist_Consigne_Documents document : listDocumentToDelete)
					listDocumentsConsigneObjectToDelete.add(document);

				for (Entry<String, List<String>> documentEntry : documentsSousTache
						.entrySet()) {
					if (documentEntry.getKey().equals(
							sousTacheToUpdate.getNomSousTache())) {
						List<String> listDocToUpdate = documentEntry.getValue();
						for (String name : listDocToUpdate) {
							Checklist_Consigne_Documents document = new Checklist_Consigne_Documents();
							document.setDocument(name);
							document.setIdConsigne(sousTacheToUpdate
									.getIdConsigne());
							listDocumentsConsigneObjectToAdd.add(document);
						}
					}
				}
			}

			if (!listSousTacheToAdd.isEmpty())
				initConsignesEtSousTaches(base, listSousTacheToAdd,
						listChecklistConsigneObjectToAdd,
						listChecklistBaseSousTacheObjectToAdd,
						listDocumentsConsigneObjectToAdd, listDocumentToAdd);

			// Sauvegarde en base
			// sous tache et consigne

			if (listChecklistConsigneObjectToUpdate != null) {
				for (Checklist_Consignes consigneToUpdate : listChecklistConsigneObjectToUpdate) {
					session.update(consigneToUpdate);
				}
			}
			if (listChecklistConsigneObjectToAdd != null) {
				for (Checklist_Consignes consigneToAdd : listChecklistConsigneObjectToAdd) {
					session.save(consigneToAdd);
				}
			}
			if (listDocumentsConsigneObjectToAdd != null) {
				for (Checklist_Consigne_Documents documentToAdd : listDocumentsConsigneObjectToAdd) {
					session.save(documentToAdd);
				}
			}
			if (listChecklistBaseSousTacheObjectToDelete != null) {
				for (Checklist_Base_Soustache sousTacheToDelete : listChecklistBaseSousTacheObjectToDelete) {
					session.delete(sousTacheToDelete);
				}
			}
			if (listChecklistBaseSousTacheObjectToAdd != null) {
				for (Checklist_Base_Soustache sousTacheToAdd : listChecklistBaseSousTacheObjectToAdd) {
					session.save(sousTacheToAdd);
				}
			}
			if (listDocumentsConsigneObjectToDelete != null) {
				for (Checklist_Consigne_Documents documentToDelete : listDocumentsConsigneObjectToDelete) {
					session.delete(documentToDelete);
				}
			}
			if (listChecklistConsigneObjectToDelete != null) {
				for (Checklist_Consignes consigneToDelete : listChecklistConsigneObjectToDelete) {
					session.delete(consigneToDelete);
				}
			}
			if (listChecklistBaseSousTacheObjectToDesactivate != null) {
				for (Checklist_Base_Soustache sousTacheToDesactivate : listChecklistBaseSousTacheObjectToDesactivate) {
					session.update(sousTacheToDesactivate);
				}
			}

			// horaires
			if (listChecklistHoraireObjectToDelete != null) {
				for (Checklist_Horaire horaireToDelete : listChecklistHoraireObjectToDelete) {
					session.delete(horaireToDelete);
				}
			}
			if (listChecklistHoraireObjectToDesactivate != null) {
				for (Checklist_Horaire horaireToDesactivate : listChecklistHoraireObjectToDesactivate) {
					session.update(horaireToDesactivate);
				}
			}
			if (listChecklistHoraireObjectToAdd != null) {
				for (Checklist_Horaire horaireToAdd : listChecklistHoraireObjectToAdd) {
					session.save(horaireToAdd);
				}
			}

			// jours exceptionnels
			if (listChecklistExceptionnelObjectToDelete != null) {
				for (Checklist_Exceptionnel exceptionneToDelete : listChecklistExceptionnelObjectToDelete) {
					session.delete(exceptionneToDelete);
				}
			}
			if (listChecklistExceptionnelObjectToAdd != null) {
				for (Checklist_Exceptionnel exceptionneToAdd : listChecklistExceptionnelObjectToAdd) {
					session.save(exceptionneToAdd);
				}
			}
			// fériés
			if (listChecklistFerieObjectToDelete != null) {
				for (Checklist_Ferie ferieToDelete : listChecklistFerieObjectToDelete) {
					session.delete(ferieToDelete);
				}
			}
			if (listChecklistFerieObjectToAdd != null) {
				for (Checklist_Ferie ferieToAdd : listChecklistFerieObjectToAdd) {
					session.save(ferieToAdd);
				}
			}
			// parités
			if (listChecklistPariteObjectToDelete != null) {
				for (Checklist_Parite pariteToDelete : listChecklistPariteObjectToDelete) {
					session.delete(pariteToDelete);
				}
			}
			if (listChecklistPariteObjectToAdd != null) {
				for (Checklist_Parite pariteToAdd : listChecklistPariteObjectToAdd) {
					session.save(pariteToAdd);
				}
			}
			// mensuels
			if (listChecklistMensuelObjectToDelete != null) {
				for (Checklist_Mensuel mensuelToDelete : listChecklistMensuelObjectToDelete) {
					session.delete(mensuelToDelete);
				}
			}
			if (listChecklistMensuelObjectToAdd != null) {
				for (Checklist_Mensuel mensuelToAdd : listChecklistMensuelObjectToAdd) {
					session.save(mensuelToAdd);
				}
			}
			// jours
			if (listChecklistJourObjectToDelete != null) {
				for (Checklist_Jour jourToDelete : listChecklistJourObjectToDelete) {
					session.delete(jourToDelete);
				}
			}
			if (listChecklistJourObjectToUpdate != null) {
				for (Checklist_Jour jourToUpdate : listChecklistJourObjectToUpdate) {
					session.update(jourToUpdate);
				}
			}
			if (listChecklistJourObjectToAdd != null) {
				for (Checklist_Jour jourToAdd : listChecklistJourObjectToAdd) {
					session.save(jourToAdd);
				}
			}
			// base
			session.update(base);

			// Checklist Current
			List<Checklist_Current> list_currents = session
					.createCriteria(Checklist_Current.class)
					.add(Restrictions.eq("tache", base)).list();
			Date now = new Date();
			Date today = DateService.getTodayWithoutHour();
			now = DateService.getTimeFromString(DateService.getTime(now,
					DateService.pt1));
			// Suppression des checks modifiés
			for (Checklist_Current current : list_currents) {
				Date heureExecution = current.getIdHoraire().getHoraire();
				if (heureExecution.after(now)
						&& current.getJour().equals(today)) {
					session.delete(current);
				}
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * COUNT test si une tache en checklist pointe vers la tache
	 * 
	 * @param session
	 * @param sousTache
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static boolean hasChecklistCurrentWithTache(Session session,
			Checklist_Base tache) {

		Criteria criteria = session.createCriteria(Checklist_Current.class);
		criteria.add(Restrictions.eq("tache", tache));
		criteria.setProjection(Projections.rowCount());

		List<Long> results = criteria.list();

		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * DELETE/UPDATE d'une tache suivant qu'il soit référencé par une
	 * checklist_current ou non
	 * 
	 * @param selectRow
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public static void delete(Integer selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try {
			Checklist_Base tache = (Checklist_Base) session.load(
					Checklist_Base.class, selectRow);
			if (hasChecklistCurrentWithTache(session, tache)) {
				tache.setActif(Boolean.FALSE);
				session.update(tache);
				List<Checklist_Current> list_currents = session
						.createCriteria(Checklist_Current.class)
						.add(Restrictions.eq("tache", tache)).list();
				Date now = new Date();
				now = DateService.getTimeFromString(DateService.getTime(now,
						DateService.pt1));
				for (Checklist_Current current : list_currents) {
					Date heureExecution = current.getIdHoraire().getHoraire();
					if (heureExecution.after(now)
							&& DateService.isToday(current.getJour())) {
						session.delete(current);
					}
				}
			} else {
				// sous taches et consignes
				List<Checklist_Base_Soustache> listSousTaches = session
						.createCriteria(Checklist_Base_Soustache.class)
						.add(Restrictions.eq("idBase", tache)).list();
				List<Checklist_Consignes> listConsignes = new ArrayList<Checklist_Consignes>();
				for (Checklist_Base_Soustache sousTache : listSousTaches) {
					listConsignes.add(sousTache.getIdConsigne());

				}
				List<Checklist_Consigne_Documents> listDocuments = new ArrayList<Checklist_Consigne_Documents>();
				for (Checklist_Consignes consigne : listConsignes) {
					List<Checklist_Consigne_Documents> documents = session
							.createCriteria(Checklist_Consigne_Documents.class)
							.add(Restrictions.eq("idConsigne", consigne))
							.list();
					for (Checklist_Consigne_Documents doc : documents)
						listDocuments.add(doc);
				}
				// horaires
				List<Checklist_Horaire> listHoraires = session
						.createCriteria(Checklist_Horaire.class)
						.add(Restrictions.eq("idChecklist", tache)).list();

				// jours
				List<Checklist_Jour> listJours = session
						.createCriteria(Checklist_Jour.class)
						.add(Restrictions.eq("idChecklist", tache)).list();

				// mensuels
				List<Checklist_Mensuel> listMensuels = session
						.createCriteria(Checklist_Mensuel.class)
						.add(Restrictions.eq("idChecklist", tache)).list();

				// parites
				List<Checklist_Parite> listParites = session
						.createCriteria(Checklist_Parite.class)
						.add(Restrictions.eq("idChecklist", tache)).list();

				// feries
				List<Checklist_Ferie> listFeries = session
						.createCriteria(Checklist_Ferie.class)
						.add(Restrictions.eq("idChecklist", tache)).list();

				// exceptionnels
				List<Checklist_Exceptionnel> listExceptionnels = session
						.createCriteria(Checklist_Exceptionnel.class)
						.add(Restrictions.eq("idChecklist", tache)).list();

				// suppression
				if (listSousTaches != null) {
					for (Checklist_Base_Soustache sousTache : listSousTaches) {
						session.delete(sousTache);
					}
				}
				if (listDocuments != null) {
					for (Checklist_Consigne_Documents document : listDocuments) {
						session.delete(document);
					}
				}
				if (listConsignes != null) {
					for (Checklist_Consignes consigne : listConsignes) {
						session.delete(consigne);
					}
				}

				if (listHoraires != null) {
					for (Checklist_Horaire horaire : listHoraires) {
						session.delete(horaire);
					}
				}

				if (listJours != null) {
					for (Checklist_Jour jour : listJours) {
						session.delete(jour);
					}
				}

				if (listMensuels != null) {
					for (Checklist_Mensuel mensuel : listMensuels) {
						session.delete(mensuel);
					}
				}

				if (listParites != null) {
					for (Checklist_Parite parite : listParites) {
						session.delete(parite);
					}
				}

				if (listFeries != null) {
					for (Checklist_Ferie ferie : listFeries) {
						session.delete(ferie);
					}
				}

				if (listExceptionnels != null) {
					for (Checklist_Exceptionnel exceptionnel : listExceptionnels) {
						session.delete(exceptionnel);
					}
				}

				session.delete(tache);
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	public static Integer getId(String tache, Integer etatID, Integer envID,
			Date dateDebut, Integer criticiteID) {
		Session session = PilotageSession.getCurrentSession();
		Checklist_Etat etat = (Checklist_Etat) session.load(
				Checklist_Etat.class, etatID);
		Environnement environnement = (Environnement) session.load(
				Environnement.class, envID);
		Checklist_Criticite criticite = (Checklist_Criticite) session.load(
				Checklist_Criticite.class, criticiteID);
		Checklist_Base cc = (Checklist_Base) session
				.createCriteria(Checklist_Base.class)
				.add(Restrictions.eq("tache", tache))
				.add(Restrictions.eq("etat", etat))
				.add(Restrictions.eq("environnement", environnement))
				.add(Restrictions.eq("dateDebut", dateDebut))
				.add(Restrictions.eq("criticite", criticite)).setMaxResults(1)
				.uniqueResult();
		session.getTransaction().commit();
		return cc.getId();
	}
}
