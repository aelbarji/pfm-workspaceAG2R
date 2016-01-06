package pilotage.astreintes.actions.astreinte;

import java.text.MessageFormat;
import java.util.List;

import pilotage.database.astreintes.AstreinteDatabaseService;
import pilotage.database.astreintes.AstreinteTypeDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte;
import pilotage.metier.Astreinte_Type;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ModifyAstreinteAction extends AbstractAction{

	private static final long serialVersionUID = -7863362605523754385L;

	private Integer selectRow;
	private String nom;
	private String prenom;
	private String tel1;
	private String tel2;
	private Integer type;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private List<Astreinte_Type> aTypes;

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public List<Astreinte_Type> getATypes() {
		return aTypes;
	}

	public void setATypes(List<Astreinte_Type> aTypes) {
		this.aTypes = aTypes;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		
		try {	
			String historique = " ";
			Astreinte a = AstreinteDatabaseService.get(selectRow);
			if (!nom.equals(a.getNom())) {
				historique += "nom, ";
			}
			if (!prenom.equals(a.getPrenom())) {
				historique += "prenom, ";
			}
			if (!tel1.equals(a.getTel1())) {
				historique += "tel1, ";
			}
			if (!tel2.equals(a.getTel2())) {
				historique += "tel2, ";
			}
			if (!type.equals(a.getType().getId())) {
				historique += "type, ";
			}
			
			//modification d'une astreinte
			AstreinteDatabaseService.modify(selectRow, nom, prenom, tel1, tel2, type);
			HistoriqueDatabaseService.create(null,  MessageFormat.format(getText("historique.astreinte.astreinte.modification"), new Object[]{historique,selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ASTREINTES);

			info = MessageFormat.format(getText("astreinte.modification.valide"),new Object[]{nom + " " + prenom});
			return OK;
		} 
		catch (Exception e) {	
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'une astreinte - ", e);
			aTypes = AstreinteTypeDatabaseService.getAll();
			return ERROR;
		}
	}
}
