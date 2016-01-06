package pilotage.metier;

import java.io.Serializable;

public class Meteo_Service implements Serializable{

	private static final long serialVersionUID = -4135734370995653552L;

	private Integer id;
	private String service;
	private String consigne;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getConsigne() {
		return consigne;
	}
	public void setConsigne(String consigne) {
		this.consigne = consigne;
	}
	
	
}
