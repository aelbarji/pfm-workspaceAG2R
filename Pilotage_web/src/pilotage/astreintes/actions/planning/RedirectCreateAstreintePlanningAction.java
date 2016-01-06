package pilotage.astreintes.actions.planning;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pilotage.database.astreintes.AstreinteDatabaseService;
import pilotage.database.astreintes.AstreinteDomaineDatabaseService;
import pilotage.database.astreintes.AstreinteObligatoireDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte;
import pilotage.metier.Astreinte_Domaine;
import pilotage.metier.Astreinte_Obligatoire;
import pilotage.service.date.DateService;

public class RedirectCreateAstreintePlanningAction extends AbstractAction{

	private static final long serialVersionUID = 667984613825670978L;
	
	private List<Astreinte> astreintes;
	private List<Astreinte_Domaine> aDomaines;
	
	private List<Astreinte> astreintes2;
	
	private Date dateDeb;
	private Date dateFin;
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private Integer dateNB;
	
	private String astObligatoire;

	public List<Astreinte> getAstreintes() {
		return astreintes;
	}

	public void setAstreintes(List<Astreinte> astreintes) {
		this.astreintes = astreintes;
	}

	public List<Astreinte_Domaine> getADomaines() {
		return aDomaines;
	}

	public void setADomaines(List<Astreinte_Domaine> aDomaines) {
		this.aDomaines = aDomaines;
	}

	public List<Astreinte> getAstreintes2() {
		return astreintes2;
	}

	public void setAstreintes2(List<Astreinte> astreintes2) {
		this.astreintes2 = astreintes2;
	}

	public Date getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
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

	public Integer getDateNB() {
		return dateNB;
	}

	public void setDateNB(Integer dateNB) {
		this.dateNB = dateNB;
	}

	public String getAstObligatoire() {
		return astObligatoire;
	}

	public void setAstObligatoire(String astObligatoire) {
		this.astObligatoire = astObligatoire;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		//pour creation les astreintes plannings par type et domaine
		if(astObligatoire != null && !"".equals(astObligatoire)){
			Integer astObligatoireID = Integer.parseInt(astObligatoire);
			Astreinte_Obligatoire ao = AstreinteObligatoireDatabaseService.get(astObligatoireID);
			astreintes = AstreinteDatabaseService.getAstreintesByType(ao.getType());
			aDomaines = new ArrayList<Astreinte_Domaine>();
			aDomaines.add(ao.getDomaine());
			dateDeb = DateService.addHours(DateService.getTodayWithoutHour(),8);
			dateFin = DateService.addDays(dateDeb, 1);
		}
		else{
			astreintes = AstreinteDatabaseService.getAll();
			aDomaines = AstreinteDomaineDatabaseService.getAll();
			dateDeb = DateService.addHours(DateService.getTodayWithoutHour(),8);
			dateFin = DateService.addDays(dateDeb, 1);
		}
		
		return OK;
	}

}
