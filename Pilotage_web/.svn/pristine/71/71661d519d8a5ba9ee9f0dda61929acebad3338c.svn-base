package pilotage.astreintes.actions.obligatoire;

import java.text.MessageFormat;
import java.util.List;

import pilotage.database.astreintes.AstreinteDomaineDatabaseService;
import pilotage.database.astreintes.AstreinteObligatoireDatabaseService;
import pilotage.database.astreintes.AstreinteTypeDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte_Domaine;
import pilotage.metier.Astreinte_Obligatoire;
import pilotage.metier.Astreinte_Type;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ModifyAstreinteObligatoireAction extends AbstractAction{

	private static final long serialVersionUID = -6242513613444983675L;

	private Integer selectRow;
	private Integer domaine;
	private Integer type;
	private Boolean indicEnvoi;
	private Boolean indicChanged;
	private Boolean domaineTypeChanged;
	
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

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
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

	public Boolean getIndicChanged() {
		return indicChanged;
	}

	public void setIndicChanged(Boolean indicChanged) {
		this.indicChanged = indicChanged;
	}

	public Boolean getDomaineTypeChanged() {
		return domaineTypeChanged;
	}

	public void setDomaineTypeChanged(Boolean domaineTypeChanged) {
		this.domaineTypeChanged = domaineTypeChanged;
	}

	@Override
	protected boolean validateMetier() {
		
		try{
			if(domaineTypeChanged && AstreinteObligatoireDatabaseService.exists(selectRow, domaine, type)){
				Astreinte_Domaine adomaine = AstreinteDomaineDatabaseService.get(domaine);
				Astreinte_Type aType = AstreinteTypeDatabaseService.get(type);
				error = MessageFormat.format(getText("astreinte.obligatoire.creation.existe.deja"), new Object[]{adomaine.getDomaine(), aType.getType()});
				
				Astreinte_Obligatoire ao = AstreinteObligatoireDatabaseService.get(selectRow);
				type = ao.getType().getId();
				domaine = ao.getDomaine().getId();
				//récupération des types et des domaines pour les filtres
				aDomaines = AstreinteDomaineDatabaseService.getAll();
				aTypes = AstreinteTypeDatabaseService.getAll();
				

				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'une astreinte obligatoire - ", e);
			//récupération des types et des domaines pour les filtres
			aDomaines = AstreinteDomaineDatabaseService.getAll();
			aTypes = AstreinteTypeDatabaseService.getAll();
			
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		
		try {
			String historique = " ";
			Astreinte_Obligatoire ao = AstreinteObligatoireDatabaseService.get(selectRow);
			if (!domaine.equals(ao.getDomaine().getId())) {
				historique += "domaine, ";
			}
			if (!type.equals(ao.getType().getId())) {
				historique += "type, ";
			}
			if (!indicEnvoi.equals(ao.getIndicEnvoi())) {
				historique += "indicEnvoi, ";
			}
			AstreinteObligatoireDatabaseService.modify(selectRow, domaine, type, indicEnvoi);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.astreinte.obligatoire.modification"),new Object[]{historique,selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ASTREINTES);

			Astreinte_Domaine adomaine = AstreinteDomaineDatabaseService.get(domaine);
			Astreinte_Type aType = AstreinteTypeDatabaseService.get(type);
			info = MessageFormat.format(getText("astreinte.obligatoire.modification.valide"), new Object[]{adomaine.getDomaine(), aType.getType()});

			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'une astreinte obligatoire - ", e);
			//récupération des types et des domaines pour les filtres
			aDomaines = AstreinteDomaineDatabaseService.getAll();
			aTypes = AstreinteTypeDatabaseService.getAll();
			return ERROR;
		}
	}

}
