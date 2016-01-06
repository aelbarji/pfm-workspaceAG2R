package pilotage.metier;

import java.io.Serializable;

public class Meteo_Format implements Serializable{

	private static final long serialVersionUID = 6182163437233293387L;

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
