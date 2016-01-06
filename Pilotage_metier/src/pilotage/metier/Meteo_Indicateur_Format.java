package pilotage.metier;

import java.io.Serializable;

public class Meteo_Indicateur_Format implements Serializable{

	private static final long serialVersionUID = 126660499269947728L;

	private Integer id;
	private String format;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	
}
