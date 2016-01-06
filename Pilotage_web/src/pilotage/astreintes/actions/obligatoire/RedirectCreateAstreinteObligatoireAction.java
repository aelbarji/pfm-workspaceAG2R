package pilotage.astreintes.actions.obligatoire;

import java.util.List;

import pilotage.database.astreintes.AstreinteDomaineDatabaseService;
import pilotage.database.astreintes.AstreinteTypeDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte_Domaine;
import pilotage.metier.Astreinte_Type;

public class RedirectCreateAstreinteObligatoireAction extends AbstractAction{

	private static final long serialVersionUID = -7053127726796742813L;

	private String sort;
	private String sens;
	
	private List<Astreinte_Domaine> aDomaines;
	private List<Astreinte_Type> aTypes;
	
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
	
	@Override
	protected boolean validateMetier() {
		
		return true;
	}

	@Override
	protected String executeMetier() {
		aDomaines = AstreinteDomaineDatabaseService.getAll();
		aTypes = AstreinteTypeDatabaseService.getAll();
		return OK;
	}

}
