package pilotage.meteo.caisse;

import java.text.MessageFormat;
import java.util.List;

import pilotage.database.meteo.MeteoCaisseDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_Caisse;
import pilotage.utils.Pagination;

public class ModifyCaisseAction extends AbstractAction{

	private static final long serialVersionUID = -6151588044234249596L;
	private Integer caisseID;
	private String  libelleCaisse;
	private Boolean  libelleCaisseChanged;
	private String nomCaisseComplet;

	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private Pagination<Meteo_Caisse> pagination;
	private List<Meteo_Caisse> listCaisses;
	private Meteo_Caisse caisse;

	public Integer getCaisseID() {
		return caisseID;
	}

	public void setCaisseID(Integer caisseID) {
		this.caisseID = caisseID;
	}

	public String getLibelleCaisse() {
		return libelleCaisse;
	}

	public void setLibelleCaisse(String libelleCaisse) {
		this.libelleCaisse = libelleCaisse;
	}
	
	public String getNomCaisseComplet() {
		return nomCaisseComplet;
	}

	public void setNomCaisseComplet(String nomCaisseComplet) {
		this.nomCaisseComplet = nomCaisseComplet;
	}

	public Boolean getLibelleCaisseChanged() {
		return libelleCaisseChanged;
	}

	public void setLibelleCaisseChanged(Boolean libelleCaisseChanged) {
		this.libelleCaisseChanged = libelleCaisseChanged;
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

	public Pagination<Meteo_Caisse> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Meteo_Caisse> pagination) {
		this.pagination = pagination;
	}

	public List<Meteo_Caisse> getListCaisses() {
		return listCaisses;
	}

	public void setListCaisses(List<Meteo_Caisse> listCaisses) {
		this.listCaisses = listCaisses;
	}

	public Meteo_Caisse getCaisse() {
		return caisse;
	}

	public void setCaisse(Meteo_Caisse caisse) {
		this.caisse = caisse;
	}

	@Override
	protected boolean validateMetier() {
		try {
			//vérifie que le nouveau nom du service n'existe pas deja
			if (libelleCaisseChanged && MeteoCaisseDatabaseService.exists(caisseID, libelleCaisse)) {
				error = MessageFormat.format(getText("services.liste.creation.existe.deja"), libelleCaisse);
				libelleCaisse = MeteoCaisseDatabaseService.get(caisseID).getNomCaisse();
				nomCaisseComplet = MeteoCaisseDatabaseService.get(caisseID).getNomCaisseComplet();
				return false;
			}
		} 
		catch (Exception e) {
			error = getText("error.message.generique")+ " : " + e.getMessage();
			erreurLogger.error("Modification d'une caisse", e );
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			if(libelleCaisseChanged)
				MeteoCaisseDatabaseService.modifier(caisseID, libelleCaisse,nomCaisseComplet);

			info = MessageFormat.format(getText("caisse.modification.valide"), new Object[]{libelleCaisse});
			
			return OK;
		} 
		catch (Exception e) {
			error = getText("error.message.generique")+ " : " + e.getMessage();
			erreurLogger.error("Modification d'une caisse", e);
			return ERROR;
		}
	}

}
