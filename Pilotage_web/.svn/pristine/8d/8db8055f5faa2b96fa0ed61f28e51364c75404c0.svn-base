package pilotage.admin.actions.sousmodule;

import java.text.MessageFormat;
import java.util.List;

import pilotage.admin.metier.Droits_Liste;
import pilotage.admin.metier.Module;
import pilotage.admin.metier.Sous_Module;
import pilotage.database.admin.DroitsListeDatabaseService;
import pilotage.database.admin.ModuleDatabaseService;
import pilotage.database.admin.SousModuleDatabaseService;
import pilotage.framework.AbstractAdminAction;

public class CreateSousModuleAdminAction extends AbstractAdminAction{

	private static final long serialVersionUID = -5776878545074746871L;
	
	private String nom;
	private Integer moduleID;
	private String nomAjout;
	private String nomModif;
	private String nomSuppr;
	private String nomDetail;
	private String descriptionAjout;
	private String descriptionModif;
	private String descriptionSuppr;
	private String descriptionDetail;

	private Droits_Liste idAjout;
	private Droits_Liste idModif;
	private Droits_Liste idSuppr;
	private Droits_Liste idDetail;

	private Module idParent;


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Module getIdParent() {
		return idParent;
	}

	public void setIdParent(Module idParent) {
		this.idParent = idParent;
	}

	public String getNomAjout() {
		return nomAjout;
	}

	public void setNomAjout(String nomAjout) {
		this.nomAjout = nomAjout;
	}

	public String getNomModif() {
		return nomModif;
	}

	public void setNomModif(String nomModif) {
		this.nomModif = nomModif;
	}

	public String getNomSuppr() {
		return nomSuppr;
	}

	public void setNomSuppr(String nomSuppr) {
		this.nomSuppr = nomSuppr;
	}

	public String getNomDetail() {
		return nomDetail;
	}

	public void setNomDetail(String nomDetail) {
		this.nomDetail = nomDetail;
	}

	public String getDescriptionAjout() {
		return descriptionAjout;
	}

	public void setDescriptionAjout(String descriptionAjout) {
		this.descriptionAjout = descriptionAjout;
	}

	public String getDescriptionModif() {
		return descriptionModif;
	}

	public void setDescriptionModif(String descriptionModif) {
		this.descriptionModif = descriptionModif;
	}

	public String getDescriptionSuppr() {
		return descriptionSuppr;
	}

	public void setDescriptionSuppr(String descriptionSuppr) {
		this.descriptionSuppr = descriptionSuppr;
	}

	public String getDescriptionDetail() {
		return descriptionDetail;
	}

	public void setDescriptionDetail(String descriptionDetail) {
		this.descriptionDetail = descriptionDetail;
	}

	public Integer getModuleID() {
		return moduleID;
	}

	public void setModuleID(Integer moduleID) {
		this.moduleID = moduleID;
	}

	@Override
	protected boolean validateMetier(){
		List<Droits_Liste> listDroitListe = DroitsListeDatabaseService.getAll();
		List<Sous_Module> listSousModule = SousModuleDatabaseService.getAll();
		for(Droits_Liste dl : listDroitListe){
			if(dl.getLibelle().equals(nomAjout) || dl.getLibelle().equals(nomModif) || dl.getLibelle().equals(nomSuppr) || dl.getLibelle().equals(nomDetail)){
				error = MessageFormat.format(getText(" la creation est impossible car un droit existe déja"), nom);
				return false;
			}
		}
		for(Sous_Module sm : listSousModule){
			if(sm.getNom().equals(nom)){
				error = MessageFormat.format(getText("ce sous-module existe déjà"), nom);
				return false;
			}
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			
			if(!nomAjout.equals("") && !descriptionAjout.equals("")){
				idAjout = DroitsListeDatabaseService.create(nomAjout, descriptionAjout);
			}
			if(!nomModif.equals("") && !descriptionModif.equals("")){
				idModif = DroitsListeDatabaseService.create(nomModif, descriptionModif);
			}
			if(!nomSuppr.equals("") && !descriptionSuppr.equals("")){
				idSuppr = DroitsListeDatabaseService.create(nomSuppr, descriptionSuppr);
			}
			if(!nomDetail.equals("") && !descriptionDetail.equals("")){
				idDetail = DroitsListeDatabaseService.create(nomDetail, descriptionDetail);
			}
			idParent = ModuleDatabaseService.get(moduleID);
			SousModuleDatabaseService.create(nom, idParent, idAjout, idModif, idSuppr, idDetail);
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un sous-module - validation ", e);
			return ERROR;
		}
	}

}
