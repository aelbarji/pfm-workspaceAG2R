/**
 * pilotage.framework
 * 8 juil. 2011
 */
package pilotage.consignes;

import java.util.List;

import pilotage.database.consigne.ConsignePiloteValidationDatabaseService;
import pilotage.database.consigne.ConsignesDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractActionConsigne;
import pilotage.metier.Consignes;
import pilotage.metier.Consignes_Pilotes_Validation;
import pilotage.service.constants.PilotageConstants;

/**
 * @author xxu
 * 
 */
public class ShowDetailConsigneAction extends AbstractActionConsigne {

	private static final long serialVersionUID = 1664782211274191792L;
	private Integer consigneID;
	private Consignes consigne;
	private List<Consignes_Pilotes_Validation> listValidation;
	private Integer piloteNum;
	private Integer piloteConsultNum;
	private Integer piloteLuNum;
	private Integer piloteComp;
	private Integer piloteNonLu;
	private Integer piloteNonComp;
	private Integer piloteNonConsult;

	/**
	 * 
	 * @return le nombre de pilotes n'ayant pas lu la consigne
	 */
	public Integer getPiloteNonLu() {
		return piloteNonLu;
	}

	/**
	 * 
	 * @param piloteNonLu
	 */
	public void setPiloteNonLu(Integer piloteNonLu) {
		this.piloteNonLu = piloteNonLu;
	}

	/**
	 * 
	 * @return le nombre de pilotes n'ayant pas compris la consigne
	 */
	public Integer getPiloteNonComp() {
		return piloteNonComp;
	}

	/**
	 * 
	 * @param piloteNonComp
	 */
	public void setPiloteNonComp(Integer piloteNonComp) {
		this.piloteNonComp = piloteNonComp;
	}

	/**
	 * 
	 * @return le nombre de pilotes n'ayant pas consulté la consigne
	 */
	public Integer getPiloteNonConsult() {
		return piloteNonConsult;
	}

	/**
	 * 
	 * @param piloteNonConsult
	 */
	public void setPiloteNonConsult(Integer piloteNonConsult) {
		this.piloteNonConsult = piloteNonConsult;
	}

	/**
	 * @return the consigneID
	 */
	public Integer getConsigneID() {
		return consigneID;
	}

	/**
	 * @param consigneID
	 *            the consigneID to set
	 */
	public void setConsigneID(Integer consigneID) {
		this.consigneID = consigneID;
	}

	/**
	 * @return the piloteNum
	 */
	public Integer getPiloteNum() {
		return piloteNum;
	}

	/**
	 * @param piloteNum
	 *            the piloteNum to set
	 */
	public void setPiloteNum(Integer piloteNum) {
		this.piloteNum = piloteNum;
	}

	/**
	 * @return the piloteConsultNum
	 */
	public Integer getPiloteConsultNum() {
		return piloteConsultNum;
	}

	/**
	 * @param piloteConsultNum
	 *            the piloteConsultNum to set
	 */
	public void setPiloteConsultNum(Integer piloteConsultNum) {
		this.piloteConsultNum = piloteConsultNum;
	}

	/**
	 * @return the piloteLuNum
	 */
	public Integer getPiloteLuNum() {
		return piloteLuNum;
	}

	/**
	 * @param piloteLuNum
	 *            the piloteLuNum to set
	 */
	public void setPiloteLuNum(Integer piloteLuNum) {
		this.piloteLuNum = piloteLuNum;
	}

	/**
	 * @return the piloteComp
	 */
	public Integer getPiloteComp() {
		return piloteComp;
	}

	/**
	 * @param piloteComp
	 *            the piloteComp to set
	 */
	public void setPiloteComp(Integer piloteComp) {
		this.piloteComp = piloteComp;
	}

	/**
	 * @return the consigne
	 */
	public Consignes getConsigne() {
		return consigne;
	}

	/**
	 * @param consigne
	 *            the consigne to set
	 */
	public void setConsigne(Consignes consigne) {
		this.consigne = consigne;
	}

	/**
	 * @return the listValidation
	 */
	public List<Consignes_Pilotes_Validation> getListValidation() {
		return listValidation;
	}

	/**
	 * @param listValidation
	 *            the listValidation to set
	 */
	public void setListValidation(
			List<Consignes_Pilotes_Validation> listValidation) {
		this.listValidation = listValidation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pilotage.framework.AbstractAction#validateMetier()
	 */
	@Override
	protected boolean validateMetier() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pilotage.framework.AbstractAction#executeMetier()
	 */
	@Override
	protected String executeMetier() {
		//récupération de la consigne
		consigne = ConsignesDatabaseService.get(consigneID);

		//récupération de la liste des consignes_pilotes_validation
		listValidation = ConsignePiloteValidationDatabaseService.getByConsigne(consigne);
		
		//récupération du nombre de pilotes
		piloteNum = UsersDatabaseService.getNombrePilotes();
		
		//récupération du nombre de pilotes ayant consulté la page des consignes depuis la publication de cette consigne
		piloteConsultNum = listValidation.size();
		
		//récupération du nombre de pilotes ayant lu la consigne et le nombre de pilotes ayant compris
		piloteLuNum = 0;
		piloteComp = 0;
		for (Consignes_Pilotes_Validation cpv : listValidation) {
			if (cpv.getValid().getId().equals(PilotageConstants.CONSIGNE_LUE_NON_COMPRISE)) {
				piloteLuNum++;
			} else if (cpv.getValid().getId().equals(PilotageConstants.CONSIGNE_LUE_COMPRISE)) {
				piloteLuNum++;
				piloteComp++;
			}
		}

		//calcul du nombre de pilote n'ayant pas consulté la page, n'ayant pas lu la consigne, et n'ayant pas compris
		piloteNonConsult = piloteNum - piloteConsultNum;
		piloteNonLu = piloteNum - piloteLuNum;
		piloteNonComp = piloteLuNum - piloteComp;
		
		return OK;
	}

}
