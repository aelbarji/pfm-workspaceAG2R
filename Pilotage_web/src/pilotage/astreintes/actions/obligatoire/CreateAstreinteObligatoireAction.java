package pilotage.astreintes.actions.obligatoire;

import java.text.MessageFormat;
import java.util.List;

import pilotage.database.astreintes.AstreinteDomaineDatabaseService;
import pilotage.database.astreintes.AstreinteObligatoireDatabaseService;
import pilotage.database.astreintes.AstreinteTypeDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte_Domaine;
import pilotage.metier.Astreinte_Type;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class CreateAstreinteObligatoireAction extends AbstractAction{

	private static final long serialVersionUID = -4560386188808569423L;

	private Integer domaine;
	private Integer type;
	private Boolean indicEnvoi;
	
	private String sort;
	private String sens;

	private List<Astreinte_Domaine> aDomaines;
	private List<Astreinte_Type> aTypes;
	
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
	
	public Integer getDomaine() {
		return domaine;
	}

	public void setDomaine(Integer domaine) {
		this.domaine = domaine;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Boolean getIndicEnvoi() {
		return indicEnvoi;
	}

	public void setIndicEnvoi(Boolean indicEnvoi) {
		this.indicEnvoi = indicEnvoi;
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
		//test si l'astreinte obligatoire existe déjà
		try{
			if(AstreinteObligatoireDatabaseService.exists(null, domaine, type)){
				Astreinte_Domaine adomaine = AstreinteDomaineDatabaseService.get(domaine);
				Astreinte_Type aType = AstreinteTypeDatabaseService.get(type);
				error = MessageFormat.format(getText("astreinte.obligatoire.creation.existe.deja"), new Object[]{adomaine.getDomaine(), aType.getType()});
				
				//récupération des types et des domaines pour les filtres
				aDomaines = AstreinteDomaineDatabaseService.getAll();
				aTypes = AstreinteTypeDatabaseService.getAll();
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'une astreinte obligatoire - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		
		try {
			//creation d'une nouvelle astreinte obligatoire
			AstreinteObligatoireDatabaseService.create(domaine, type, indicEnvoi);
			Astreinte_Domaine adomaine = AstreinteDomaineDatabaseService.get(domaine);
			Astreinte_Type aType = AstreinteTypeDatabaseService.get(type);
			info = MessageFormat.format(getText("astreinte.obligatoire.creation.valide"), new Object[]{adomaine.getDomaine(), aType.getType()});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.astreinte.obligatoire.creation"), new Object[]{AstreinteObligatoireDatabaseService.getId(domaine, type, indicEnvoi),adomaine.getDomaine(), aType.getType()}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ASTREINTES);

			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'une astreinte obligatoire - ", e);
			//récupération des types et des domaines pour les filtres
			aDomaines = AstreinteDomaineDatabaseService.getAll();
			aTypes = AstreinteTypeDatabaseService.getAll();
			return ERROR;
		}
		
	}

}
