package pilotage.bilan.disques;

import java.text.NumberFormat;

import pilotage.database.bilan.DisqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Disques;

public class RedirectModifyDisquesAction extends AbstractAction {

	private static final long serialVersionUID = 4338840591441636445L;
	private Integer selectedID;
	private String libelle;
	private String seuil;
	private Integer filiale;

	public Integer getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getSeuil() {
		return seuil;
	}

	public void setSeuil(String seuil) {
		this.seuil = seuil;
	}

	public Integer getFiliale() {
		return filiale;
	}

	public void setFiliale(Integer filiale) {
		this.filiale = filiale;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		Disques disque = DisqueDatabaseService.get(selectedID);
		libelle = disque.getLibelle();
		filiale = disque.isFiliale()? 1 : 0;
		seuil = nf.format(disque.getSeuil());
		return OK;
	}

}
