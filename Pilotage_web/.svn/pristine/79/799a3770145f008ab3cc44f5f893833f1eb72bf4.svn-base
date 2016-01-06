package pilotage.gup.debordement_noc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pilotage.database.gup.DebordementNOCDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Debordement_NOC;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ShowDebordementNOCAction extends AbstractAction{

	private static final long serialVersionUID = 6697094474112410499L;
	
	private Pagination<Debordement_NOC> pagination;
	private List<Debordement_NOC> listDebNoc;
	private List<Date> listDate;
	
	private int page;
	private int nrPages;
	private int nrPerPage;
	
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

	public Pagination<Debordement_NOC> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Debordement_NOC> pagination) {
		this.pagination = pagination;
	}

	public List<Debordement_NOC> getListDebNoc() {
		return listDebNoc;
	}

	public void setListDebNoc(List<Debordement_NOC> listDebNoc) {
		this.listDebNoc = listDebNoc;
	}

	public List<Date> getListDate() {
		return listDate;
	}

	public void setListDate(List<Date> listDate) {
		this.listDate = listDate;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			if(page < 1)
				page = 1;
			else if(nrPages != 0 && page > nrPages)
				page = nrPages;		
			if(nrPerPage == 0)
				nrPerPage = PilotageConstants.NB_INCIDENTS_PER_PAGE;
			pagination = new Pagination<Debordement_NOC>(page, nrPerPage);
			
			listDebNoc = DebordementNOCDatabaseService.getAll(pagination);
			listDate = new ArrayList<Date>();
			for(Debordement_NOC dn : listDebNoc){
				Date d = dn.getDatetime().toDate();
				listDate.add(d);
			}
			return OK;
			
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage liste debordement NOC - ", e);
			return ERROR;	
		}
	}

}
