package pilotage.admin.actions.sousmodule;

import pilotage.admin.metier.Droits_Liste;
import pilotage.database.admin.DroitsListeDatabaseService;
import pilotage.database.admin.SousModuleDatabaseService;
import pilotage.framework.AbstractAdminAction;

public class ModifySousModuleAdminAction extends AbstractAdminAction{

	private static final long serialVersionUID = 3675972537481753388L;
	private Integer sousModuleID;
	private String nom;
	private Integer idModif;
	private Integer idSuppr;
	private Integer idAjout;
	private Integer idDetail;
	private String nomModif;
	private String nomAjout;
	private String nomSuppr;
	private String nomDetail;
	private String descriptionModif;
	private String descriptionAjout;
	private String descriptionSuppr;
	private String descriptionDetail;
	private Integer moduleID;

	public Integer getSousModuleID() {
		return sousModuleID;
	}

	public void setSousModuleID(Integer sousModuleID) {
		this.sousModuleID = sousModuleID;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getIdModif() {
		return idModif;
	}

	public void setIdModif(Integer idModif) {
		this.idModif = idModif;
	}

	public Integer getIdSuppr() {
		return idSuppr;
	}

	public void setIdSuppr(Integer idSuppr) {
		this.idSuppr = idSuppr;
	}

	public Integer getIdAjout() {
		return idAjout;
	}

	public void setIdAjout(Integer idAjout) {
		this.idAjout = idAjout;
	}

	public Integer getIdDetail() {
		return idDetail;
	}

	public void setIdDetail(Integer idDetail) {
		this.idDetail = idDetail;
	}

	public String getNomModif() {
		return nomModif;
	}

	public void setNomModif(String nomModif) {
		this.nomModif = nomModif;
	}

	public String getNomAjout() {
		return nomAjout;
	}

	public void setNomAjout(String nomAjout) {
		this.nomAjout = nomAjout;
	}

	public String getNomSuppr() {
		return nomSuppr;
	}

	public void setNomSuppr(String nomSuppr) {
		this.nomSuppr = nomSuppr;
	}

	public String getNomDetal() {
		return nomDetail;
	}

	public void setNomDetail(String nomDetail) {
		this.nomDetail = nomDetail;
	}

	public String getDescriptionModif() {
		return descriptionModif;
	}

	public void setDescriptionModif(String descriptionModif) {
		this.descriptionModif = descriptionModif;
	}

	public String getDescriptionAjout() {
		return descriptionAjout;
	}

	public void setDescriptionAjout(String descriptionAjout) {
		this.descriptionAjout = descriptionAjout;
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
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			Droits_Liste idAj = null, idMod = null, idSup = null, idDet = null;
			
				if(nomModif != null && !nomModif.equals("") && idModif != null){
					DroitsListeDatabaseService.modify(idModif, nomModif, descriptionModif);
					idMod = DroitsListeDatabaseService.getById(idModif);
				}
				else if(nomModif != null && !nomModif.equals("") && idModif == null){
					idMod = DroitsListeDatabaseService.create(nomModif, descriptionModif);
				}
				
				if(nomAjout != null && !nomAjout.equals("") && idAjout != null){
					DroitsListeDatabaseService.modify(idAjout, nomAjout, descriptionAjout);
					idAj = DroitsListeDatabaseService.getById(idAjout);
				}
				else if(nomAjout != null && !nomAjout.equals("") && idAjout == null){
					idAj = DroitsListeDatabaseService.create(nomAjout, descriptionAjout);
				}
			
				if(nomSuppr != null && !nomSuppr.equals("") && idSuppr != null ){
					DroitsListeDatabaseService.modify(idSuppr, nomSuppr, descriptionSuppr);
					idSup = DroitsListeDatabaseService.getById(idSuppr);
				}
				else if(nomSuppr != null && !nomSuppr.equals("") && idSuppr == null){
					idSup = DroitsListeDatabaseService.create(nomSuppr, descriptionSuppr);
				}
				if(nomDetail != null && !nomDetail.equals("") && idDetail != null ){
					DroitsListeDatabaseService.modify(idDetail, nomDetail, descriptionDetail);
					idDet = DroitsListeDatabaseService.getById(idDetail);
				}
				else if(nomDetail != null && !nomDetail.equals("") && idDetail == null){
					idDet = DroitsListeDatabaseService.create(nomDetail, descriptionDetail);
				}
			SousModuleDatabaseService.modify(sousModuleID, nom, idAj, idMod, idSup, idDet);
			moduleID = SousModuleDatabaseService.get(sousModuleID).getIdParent().getId();
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un sous-module - validation ", e);
			return ERROR;
		}
	}

}
