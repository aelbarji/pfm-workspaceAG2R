package pilotage.astreintes.actions.obligatoire;

import java.util.List;

import pilotage.database.astreintes.AstreinteDomaineDatabaseService;
import pilotage.database.astreintes.AstreinteObligatoireDatabaseService;
import pilotage.database.astreintes.AstreinteTypeDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte_Domaine;
import pilotage.metier.Astreinte_Obligatoire;
import pilotage.metier.Astreinte_Type;

public class RedirectModifyAstreinteObligatoireAction extends AbstractAction{

	private static final long serialVersionUID = 6680399096588625048L;

	private Integer selectRow;
	private List<Astreinte_Domaine> aDomaines;
	private List<Astreinte_Type> aTypes;
	private Integer domaine;
	private Integer type;
	private Boolean indicEnvoi;
	
	private String sort;
	private String sens;
	
	public List<Astreinte_Domaine> getADomaines() {
		return aDomaines;
	}

	public void setADomaines(List<Astreinte_Domaine> aDomaines) {
		this.aDomaines = aDomaines;
	}

	public List<Astreinte_Type> getATypes() {
		return aTypes;
	}

	public void setATypes(List<Astreinte_Type> aTypes) {
		this.aTypes = aTypes;
	}
	
	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public void setDomaine(Integer domaine) {
		this.domaine = domaine;
	}

	public Integer getDomaine() {
		return domaine;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}

	public void setIndicEnvoi(Boolean indicEnvoi) {
		this.indicEnvoi = indicEnvoi;
	}

	public Boolean getIndicEnvoi() {
		return indicEnvoi;
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

	@Override
	protected boolean validateMetier() {
		
		return true;
	}

	@Override
	protected String executeMetier() {
		Astreinte_Obligatoire aObligatoire = AstreinteObligatoireDatabaseService.get(selectRow);
		domaine = aObligatoire.getDomaine().getId();
		type = aObligatoire.getType().getId();
		indicEnvoi = aObligatoire.getIndicEnvoi();
		aDomaines = AstreinteDomaineDatabaseService.getAll();
		aTypes = AstreinteTypeDatabaseService.getAll();
		return OK;
	}
}
