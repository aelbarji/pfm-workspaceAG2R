package pilotage.meteo.caisse;

import pilotage.framework.AbstractAction;

public class RedirectCreateCaisseAction extends AbstractAction{
	
	private static final long serialVersionUID = 4207160779199737032L;
	
	private String libelleCaisse;
	private String nomCaisseComplet;
	private Integer caisseID;

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

	@Override
	protected boolean validateMetier() {
		return true;
	}
	
	@Override
	protected String executeMetier() {
		return OK;
	}
	
}
