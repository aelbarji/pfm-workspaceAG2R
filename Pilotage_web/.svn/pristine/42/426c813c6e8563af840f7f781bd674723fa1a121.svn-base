package pilotage.bilan.alertes.disques;

import java.util.Date;
import java.util.List;

import pilotage.database.bilan.AlertesDisquesDatabaseService;
import pilotage.database.bilan.AlertesTypeDatabaseService;
import pilotage.database.machine.MachinesListesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Alertes_Disques;
import pilotage.metier.Alertes_Type;
import pilotage.metier.Machines_Liste;

public class RedirectAlertesAction extends AbstractAction {

	private static final long serialVersionUID = -5715161767899660505L;
	private Date selectedDate;
	private List<Machines_Liste> listServer;
	private List<Alertes_Type> listType;
	
	private Integer systeme;
	private String fs;
	private Integer type;
	private Integer alerte;
	private Integer seuil;
	private Integer selectedID;
	private Date dateAlerte;

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public List<Machines_Liste> getListServer() {
		return listServer;
	}

	public void setListServer(List<Machines_Liste> listServer) {
		this.listServer = listServer;
	}

	public List<Alertes_Type> getListType() {
		return listType;
	}

	public void setListType(List<Alertes_Type> listType) {
		this.listType = listType;
	}

	public Integer getSysteme() {
		return systeme;
	}

	public void setSysteme(Integer systeme) {
		this.systeme = systeme;
	}

	public String getFs() {
		return fs;
	}

	public void setFs(String fs) {
		this.fs = fs;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getAlerte() {
		return alerte;
	}

	public void setAlerte(Integer alerte) {
		this.alerte = alerte;
	}

	public Integer getSeuil() {
		return seuil;
	}

	public void setSeuil(Integer seuil) {
		this.seuil = seuil;
	}

	public Integer getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
	}

	public Date getDateAlerte() {
		return dateAlerte;
	}

	public void setDateAlerte(Date dateAlerte) {
		this.dateAlerte = dateAlerte;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		listServer = MachinesListesDatabaseService.getAll();
		listType = AlertesTypeDatabaseService.getAll();
		
		if(selectedID != null){
			Alertes_Disques ad = AlertesDisquesDatabaseService.get(selectedID);
			systeme = ad.getSysteme().getId();
			fs = ad.getFs();
			type = ad.getType().getId();
			alerte = ad.getAlerte();
			seuil = ad.getSeuil();
			dateAlerte = ad.getDate();
		}
		
		return OK;
	}

}
