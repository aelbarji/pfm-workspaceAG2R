package pilotage.checklist.admin;

import java.util.Date;
import java.util.List;

import pilotage.database.applicatif.EnvironmentDatabaseService;
import pilotage.database.checklist.ChecklistCriticiteDatabaseService;
import pilotage.database.checklist.ChecklistEtatDatabaseService;
import pilotage.database.checklist.JourFerieDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Checklist_Criticite;
import pilotage.metier.Checklist_Etat;
import pilotage.metier.Environnement;
import pilotage.metier.Jour_Ferie;
import pilotage.service.date.DateService;

public class RedirectCreateChecklistBaseAction extends AbstractAction {
	private static final long serialVersionUID = 4761025588869118206L;
	
	private Boolean redirect = false;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private String dateDebut;
	
	private List<Environnement> listEnvironnement;
	private List<Checklist_Etat> listEtat;
	private List<Checklist_Criticite> listCriticite;
	private List<Jour_Ferie> listFerie;
	
	private Integer typeFrequenceDate;
	private Integer typeFrequenceHeure;
	
	//Variables de la fréquence hebdo
	private boolean f1_lundi;
	private boolean f1_mardi;
	private boolean f1_mercredi;
	private boolean f1_jeudi;
	private boolean f1_vendredi;
	private boolean f1_samedi;
	private boolean f1_dimanche;

	//Variables de la fréquence mensuelle
	private boolean f2_lundi;
	private boolean f2_mardi;
	private boolean f2_mercredi;
	private boolean f2_jeudi;
	private boolean f2_vendredi;
	private boolean f2_samedi;
	private boolean f2_dimanche;

	public Boolean getRedirect() {
		return redirect;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public void setRedirect(Boolean redirect) {
		this.redirect = redirect;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSens() {
		return sens;
	}

	public void setSens(String sens) {
		this.sens = sens;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNrPages() {
		return nrPages;
	}

	public void setNrPages(int nrPages) {
		this.nrPages = nrPages;
	}

	public int getNrPerPage() {
		return nrPerPage;
	}

	public void setNrPerPage(int nrPerPage) {
		this.nrPerPage = nrPerPage;
	}

	public List<Environnement> getListEnvironnement() {
		return listEnvironnement;
	}

	public void setListEnvironnement(List<Environnement> listEnvironnement) {
		this.listEnvironnement = listEnvironnement;
	}

	public List<Checklist_Etat> getListEtat() {
		return listEtat;
	}

	public void setListEtat(List<Checklist_Etat> listEtat) {
		this.listEtat = listEtat;
	}

	public List<Checklist_Criticite> getListCriticite() {
		return listCriticite;
	}

	public void setListCriticite(List<Checklist_Criticite> listCriticite) {
		this.listCriticite = listCriticite;
	}

	public List<Jour_Ferie> getListFerie() {
		return listFerie;
	}

	public void setListFerie(List<Jour_Ferie> listFerie) {
		this.listFerie = listFerie;
	}

	public Integer getTypeFrequenceDate() {
		return typeFrequenceDate;
	}

	public void setTypeFrequenceDate(Integer typeFrequenceDate) {
		this.typeFrequenceDate = typeFrequenceDate;
	}

	public Integer getTypeFrequenceHeure() {
		return typeFrequenceHeure;
	}

	public void setTypeFrequenceHeure(Integer typeFrequenceHeure) {
		this.typeFrequenceHeure = typeFrequenceHeure;
	}

	public boolean isF1_lundi() {
		return f1_lundi;
	}

	public void setF1_lundi(boolean f1_lundi) {
		this.f1_lundi = f1_lundi;
	}

	public boolean isF1_mardi() {
		return f1_mardi;
	}

	public void setF1_mardi(boolean f1_mardi) {
		this.f1_mardi = f1_mardi;
	}

	public boolean isF1_mercredi() {
		return f1_mercredi;
	}

	public void setF1_mercredi(boolean f1_mercredi) {
		this.f1_mercredi = f1_mercredi;
	}

	public boolean isF1_jeudi() {
		return f1_jeudi;
	}

	public void setF1_jeudi(boolean f1_jeudi) {
		this.f1_jeudi = f1_jeudi;
	}

	public boolean isF1_vendredi() {
		return f1_vendredi;
	}

	public void setF1_vendredi(boolean f1_vendredi) {
		this.f1_vendredi = f1_vendredi;
	}

	public boolean isF1_samedi() {
		return f1_samedi;
	}

	public void setF1_samedi(boolean f1_samedi) {
		this.f1_samedi = f1_samedi;
	}

	public boolean isF1_dimanche() {
		return f1_dimanche;
	}

	public void setF1_dimanche(boolean f1_dimanche) {
		this.f1_dimanche = f1_dimanche;
	}

	public boolean isF2_lundi() {
		return f2_lundi;
	}

	public void setF2_lundi(boolean f2_lundi) {
		this.f2_lundi = f2_lundi;
	}

	public boolean isF2_mardi() {
		return f2_mardi;
	}

	public void setF2_mardi(boolean f2_mardi) {
		this.f2_mardi = f2_mardi;
	}

	public boolean isF2_mercredi() {
		return f2_mercredi;
	}

	public void setF2_mercredi(boolean f2_mercredi) {
		this.f2_mercredi = f2_mercredi;
	}

	public boolean isF2_jeudi() {
		return f2_jeudi;
	}

	public void setF2_jeudi(boolean f2_jeudi) {
		this.f2_jeudi = f2_jeudi;
	}

	public boolean isF2_vendredi() {
		return f2_vendredi;
	}

	public void setF2_vendredi(boolean f2_vendredi) {
		this.f2_vendredi = f2_vendredi;
	}

	public boolean isF2_samedi() {
		return f2_samedi;
	}

	public void setF2_samedi(boolean f2_samedi) {
		this.f2_samedi = f2_samedi;
	}

	public boolean isF2_dimanche() {
		return f2_dimanche;
	}

	public void setF2_dimanche(boolean f2_dimanche) {
		this.f2_dimanche = f2_dimanche;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		listCriticite = ChecklistCriticiteDatabaseService.getAll();
		listEnvironnement = EnvironmentDatabaseService.getAll();
		listEtat = ChecklistEtatDatabaseService.getAll();
		listFerie = JourFerieDatabaseService.getAll();
		
		if(!redirect){
			dateDebut = DateService.dateToStr(new Date(), DateService.p1);
			
			typeFrequenceDate = 1;
			typeFrequenceHeure = 1;
			
			f1_lundi = true;
			f1_mardi = true;
			f1_mercredi = true;
			f1_jeudi = true;
			f1_vendredi = true;
			
			f2_lundi = true;
			f2_mardi = true;
			f2_mercredi = true;
			f2_jeudi = true;
			f2_vendredi = true;
		}
		
		return OK;
	}

}
