package pilotage.planning.planning;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import pilotage.database.planning.PlanningCycleEquipeDatabaseService;
import pilotage.database.planning.PlanningEquipePiloteDatabaseService;
import pilotage.database.planning.PlanningModifPonctuelleDatabaseService;
import pilotage.database.planning.PlanningSemaineDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Cycle_Equipe;
import pilotage.metier.Planning_Equipe_Pilote;
import pilotage.metier.Planning_Modif_Ponctuelle;
import pilotage.metier.Planning_Semaine;
import pilotage.metier.Planning_Vacation;
import pilotage.metier.Users;
import pilotage.service.date.DateService;
import pilotage.service.excel.ExportExcel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class ExportCRA extends AbstractAction {

	private static final long serialVersionUID = 275642187051368285L;
	private InputStream excelStream;
	private String titre;

	private List<List<List<Planning_Vacation>>> listCRA;
	private Integer nbJourMois;
	private List<Integer> listIdE;

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public List<List<List<Planning_Vacation>>> getListCRA() {
		return listCRA;
	}

	public void setListCRA(List<List<List<Planning_Vacation>>> listCRA) {
		this.listCRA = listCRA;
	}

	public Integer getNbJourMois() {
		return nbJourMois;
	}

	public void setNbJourMois(Integer nbJourMois) {
		this.nbJourMois = nbJourMois;
	}

	public List<Integer> getListIdE() {
		return listIdE;
	}

	public void setListIdE(List<Integer> listIdE) {
		this.listIdE = listIdE;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			Users user = (Users) session.get("USER_LOGGED");
			listCRA = new ArrayList<List<List<Planning_Vacation>>>();

			// premier jour du mois
			Calendar cFirstDayOfMonth = GregorianCalendar.getInstance();
			cFirstDayOfMonth.set(Calendar.DAY_OF_MONTH, 1);
			//Date dep = cFirstDayOfMonth.getTime();

			// dernier jour du mois
			Calendar cLastDayOfMonth = Calendar.getInstance();
			int nbJourMax = cLastDayOfMonth
					.getActualMaximum(Calendar.DAY_OF_MONTH);
			cLastDayOfMonth.set(Calendar.DAY_OF_MONTH, nbJourMax);

			listIdE = new ArrayList<Integer>();
			//LocalDate h = new LocalDate();
			LocalDate lastDay = new LocalDate(cLastDayOfMonth.getTime());
			LocalDate fDay = new LocalDate(cFirstDayOfMonth.getTime());
			DateTime lastDayDT = new DateTime(cLastDayOfMonth.getTime());
			DateTime fDayDT = new DateTime(cFirstDayOfMonth.getTime());

			Date dateDebutMois = null;
			Date dateFinMois = null;
			try {
				dateDebutMois = DateService.getFirstDayOfMonth(cFirstDayOfMonth.getTime());
				dateFinMois = DateService.getLastDayOfMonth(cFirstDayOfMonth.getTime());
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			List<Planning_Equipe_Pilote> lpep = PlanningEquipePiloteDatabaseService
					.getListEquipeByPiloteWithDate(user,
							cFirstDayOfMonth.getTime(), cLastDayOfMonth.getTime());

			List<Planning_Cycle_Equipe> pceList = new ArrayList<Planning_Cycle_Equipe>();
			List<Planning_Cycle_Equipe> pceListFinal = new ArrayList<Planning_Cycle_Equipe>();
			for (Planning_Equipe_Pilote pep : lpep) {
				Date pepDateDebut = pep.getDateDebut();
				Date pepDateFin = pep.getDateFin();
				if ((pepDateFin == null || pepDateFin.after(dateDebutMois) || pepDateFin.equals(dateDebutMois))
						&& (pepDateDebut.before(dateFinMois) || pepDateDebut.equals(dateFinMois))) {
					// récupération de(s) cycle(s)
					pceList = PlanningCycleEquipeDatabaseService
							.getCycleByEquipeAndDate(pep.getIdNomEquipe().getId(), dateDebutMois, dateFinMois);
					
				}

				
				for (Planning_Cycle_Equipe p : pceList) {
					if(p.getDateFin() == null || p.getDateFin().after(pep.getDateFin()))
						p.setDateFin(pepDateFin);
					if(p.getDateDebut().before(pep.getDateDebut()) && pep.getDateDebut().after(cFirstDayOfMonth.getTime()))
						p.setDateDebut(pepDateDebut);
					pceListFinal.add(p);
				}
			}
			
			cFirstDayOfMonth.setMinimalDaysInFirstWeek(1);
			
			// boucle sur les idCycle pour créer le planning
			List<List<List<Planning_Vacation>>> listCycleTheorique = null;
			Integer premierJour = null, premiereSemaine = null;
			Integer nbMaxSem;
			
			//Changement d'équipe en milieu de semaine
			boolean middle = false;
			List<List<Planning_Vacation>> listJourCRA = null;
			
			for (Planning_Cycle_Equipe pce : pceListFinal) {
				nbMaxSem = 0;
				// calcul du nombre de semaine maximum du Cycle
				List<Planning_Semaine> pse = PlanningSemaineDatabaseService.getSemaineByCycleAndDate(pce.getIdNomCycle().getId(), fDayDT, lastDayDT);
				for(Planning_Semaine ps : pse)
					if(ps.getNumeroSemaine() > nbMaxSem)
						nbMaxSem = ps.getNumeroSemaine();

				// calcul de la premiere semaine et du premier jour
				Planning_Cycle_Equipe ptemp = PlanningCycleEquipeDatabaseService.get(pce.getId());
				//LocalDate debCycle = new LocalDate(ptemp.getDateDebut()); // théorique
				LocalDate debCycle2 = new LocalDate(pce.getDateDebut()); // réelle
				LocalDate finCycle = null;
				
				if(pce.getDateFin() != null)
					finCycle = new LocalDate(pce.getDateFin());
				
				//DateTime debCycleDT = new DateTime(ptemp.getDateDebut());
				DateTime debCycle2DT = new DateTime(pce.getDateDebut());
				Integer index = null;
		
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(ptemp.getDateDebut());
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);					
				DateTime debCycle3DT = new DateTime(calendar.getTime());
				
				if(debCycle2.isAfter(fDay)){
					Long écart = debCycle2DT.getMillis() - debCycle3DT.getMillis();
					Integer nbJour = (int) (écart / (1000 * 60 * 60 * 24));
					premierJour = nbJour % 7;
					//premierJour = 0;
					premiereSemaine = (((nbJour - premierJour) / 7) % nbMaxSem);
					index = debCycle2.getDayOfMonth() - 1 ;
				}
				else{
					Long écart = cFirstDayOfMonth.getTimeInMillis() - debCycle3DT.getMillis();
					Integer nbJour = (int) (écart / (1000 * 60 * 60 * 24));
					premierJour = nbJour % 7;
					premiereSemaine = (((nbJour - premierJour) / 7) % nbMaxSem);
					index = 0;
				}

				listCycleTheorique = new ArrayList<List<List<Planning_Vacation>>>();
				List<List<Planning_Vacation>> listCycle1 = new ArrayList<List<Planning_Vacation>>();
				List<Planning_Vacation> listJour;
				
				List<List<Planning_Semaine>> lps = new ArrayList<List<Planning_Semaine>>();
				List<Planning_Semaine> listPS = new ArrayList<Planning_Semaine>();
				
				for(int i = 1; i <= nbMaxSem; i++){
					List<Planning_Semaine> pse2 = PlanningSemaineDatabaseService.getBySemAndCycle(pce.getIdNomCycle().getId(),i, fDayDT, lastDayDT);
					lps.add(pse2);
				}
				//récupération du planning semaine
				for(List<Planning_Semaine> ps3 : lps){
					listCycle1 = new ArrayList<List<Planning_Vacation>>();
					for (Planning_Semaine p : ps3) {
						listJour = new ArrayList<Planning_Vacation>();
						listJour.add(p.getLundi());
						listJour.add(p.getMardi());
						listJour.add(p.getMercredi());
						listJour.add(p.getJeudi());
						listJour.add(p.getVendredi());
						listJour.add(p.getSamedi());
						listJour.add(p.getDimanche());
						listCycle1.add(listJour);
						listPS.add(p);
					}
					listCycleTheorique.add(listCycle1);
				}
				
				if(pce.getDateFin() == null)
					nbJourMax = lastDay.getDayOfMonth();
				else
					nbJourMax = finCycle.getDayOfMonth();
				
				int j = 0;
				boolean passe = false;
				Integer cptSem = premiereSemaine;
				Integer cptJour = premierJour;
				List<Planning_Vacation> listVacation = null;
		
				while (index < nbJourMax) {
					if(!middle){
						if(cptJour.equals(0) || !passe)
							listJourCRA = new ArrayList<List<Planning_Vacation>>();
						
						while (j < premierJour && !passe) {
							listVacation = new ArrayList<Planning_Vacation>();
							listVacation.add(new Planning_Vacation());
							listVacation.add(new Planning_Vacation());
							listJourCRA.add(listVacation);
							j++;
						}
					}
					middle = false;
					passe = true;
					j = 0;
					//boolean r = false;
					listVacation = new ArrayList<Planning_Vacation>();
					listVacation.add(null);
					listVacation.add(null);
					
					// Traitement des modification ponctuelle
					List<Planning_Modif_Ponctuelle> lp = PlanningModifPonctuelleDatabaseService
							.getByUserAndDate(user, cFirstDayOfMonth.getTime(), cLastDayOfMonth.getTime());
		
					LocalDate dayCurrent = new LocalDate(cFirstDayOfMonth.getTime());
					dayCurrent = dayCurrent.plusDays(index);
					for(Planning_Modif_Ponctuelle pmp : lp){
						LocalDate modifDeb = new LocalDate(pmp.getDateDebut());
						LocalDate modifFin = new LocalDate(pmp.getDateFin());
						
						if((dayCurrent.isBefore(modifFin) && dayCurrent.isAfter(modifDeb)) || dayCurrent.equals(modifDeb) || dayCurrent.equals(modifFin)){
							listVacation.set(1, pmp.getIdVacation());
							listJourCRA.add(cptJour, listVacation);
							//r = true;
						}
					}
					
					//if(!r){
					boolean p = false;
					Integer g = 0;
						while(!p){
							LocalDate dateFin2 = null;
							
							if(lps.get(cptSem).get(g).getDateFin() != null)
								dateFin2 = new LocalDate(lps.get(cptSem).get(g).getDateFin());
	
							if(((new LocalDate(lps.get(cptSem).get(g).getDateDeb()).isBefore(dayCurrent)) || new LocalDate(lps.get(cptSem).get(g).getDateDeb()).equals(dayCurrent)) && 
								(dateFin2 == null || dateFin2.isAfter(dayCurrent)) ){
								listVacation.set(0, listCycleTheorique.get(cptSem).get(g).get(cptJour));	
								listJourCRA.add(cptJour, listVacation);
									p = true;
							} else{
								g++;
							}
						}
					//}
					
					index++;
					cptJour++;
					boolean add = false;
					if (cptJour == 7) {	
						listCRA.add(listJourCRA);
						listJourCRA = new ArrayList<List<Planning_Vacation>>();
						
						cptSem++;
						cptJour = 0;
						add = true;
					}
					
					if (!add && index.equals(nbJourMax) && lastDayDT.getDayOfMonth() == index) {	
						listCRA.add(listJourCRA);
						listJourCRA = new ArrayList<List<Planning_Vacation>>();
						
						cptSem++;
						cptJour = 0;
						add = true;
					}
					
					if(!add) middle = true;
					

					if (cptSem == nbMaxSem)
						cptSem = 0;
					
				}
			}
			
			// export Excel
			String nomIntervenant = user.getNom() + " " + user.getPrenom();
			HSSFWorkbook lExcelDoc = ExportExcel.exportCRA(nomIntervenant,
					listCRA, nbJourMax);

			// mise dans un flux
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			lExcelDoc.write(baos);
			setExcelStream(new ByteArrayInputStream(baos.toByteArray()));
			String[] nomMois = { "Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre" };
			String mois = nomMois[cFirstDayOfMonth.get(Calendar.MONTH)];
			setTitre("CRA-" + nomIntervenant+"-"+ mois + "-"
					+ cFirstDayOfMonth.get(Calendar.YEAR) + ".xls");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return OK;
	}
}
