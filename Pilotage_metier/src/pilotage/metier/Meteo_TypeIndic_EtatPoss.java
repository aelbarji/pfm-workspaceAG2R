package pilotage.metier;

import java.io.Serializable;

public class Meteo_TypeIndic_EtatPoss implements Serializable{

	private static final long serialVersionUID = -4657579661512669584L;

	private Integer id;
	private Meteo_TypeIndicateur typeIndic;
	private Meteo_EtatPossible etatPoss;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Meteo_TypeIndicateur getTypeIndic() {
		return typeIndic;
	}
	public void setTypeIndic(Meteo_TypeIndicateur typeIndic) {
		this.typeIndic = typeIndic;
	}
	public Meteo_EtatPossible getEtatPoss() {
		return etatPoss;
	}
	public void setEtatPoss(Meteo_EtatPossible etatPoss) {
		this.etatPoss = etatPoss;
	}
	
}
