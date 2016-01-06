package pilotage.bilan.flux.cft;

import java.util.List;

import pilotage.database.bilan.FluxCFTDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Flux_CFT;

public class ShowFluxCFTAction extends AbstractAction {

	private static final long serialVersionUID = -4004864399706971408L;
	private String libelle;
	private List<Flux_CFT> listCFT;
	
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Flux_CFT> getListCFT() {
		return listCFT;
	}

	public void setListCFT(List<Flux_CFT> listCFT) {
		this.listCFT = listCFT;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		if(error == null || error.equals(""))
			libelle = null;
		
		listCFT = FluxCFTDatabaseService.getAll();
		return OK;
	}
}
