package pilotage.metier;

import java.io.Serializable;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;


public class Checklist_Base_Soustache implements Serializable {

	private static final long serialVersionUID = -46982478825265595L;

	private Integer id;

	private String nomSousTache;
	private Checklist_Base idBase;
	private Checklist_Consignes idConsigne;
	private Date decalageDate;
	private Long decalageStamp;
	private String decalageString;
	private Boolean actif;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomSousTache() {
		return nomSousTache;
	}
	public void setNomSousTache(String nomSousTache) {
		this.nomSousTache = nomSousTache;
	}
	public Checklist_Base getIdBase() {
		return idBase;
	}
	public void setIdBase(Checklist_Base idBase) {
		this.idBase = idBase;
	}
	public Checklist_Consignes getIdConsigne() {
		return idConsigne;
	}
	public void setIdConsigne(Checklist_Consignes idConsigne) {
		this.idConsigne = idConsigne;
	}
	public Date getDecalageDate() {
		return decalageDate;
	}
	public void setDecalageDate(Date decalage) {
		this.decalageDate = decalage;
		Long MilliTime = decalage.getTime();
		Duration duree = new Duration(MilliTime);
		Long HH = duree.getStandardHours();
		Long mm = duree.getStandardMinutes()-duree.getStandardHours()*60;
		Long ss = duree.getStandardSeconds()-duree.getStandardMinutes()*60;
		String heureString = HH.toString();
		if(heureString.length()==1) heureString = "0"+heureString;
		this.decalageString = heureString + (mm.toString().length() == 1 ? ":0" : ":") + mm.toString() + (ss.toString().length() == 1 ? ":0" : ":") + ss.toString();
	}
	public Long getDecalageStamp() {
		return decalageStamp;
	}
	public void setDecalageStamp(Long decalageStamp) {
		this.decalageStamp = decalageStamp;
	}
	public String getDecalageString() {
		return decalageString;
	}
	public void setDecalageString(String decalageString) {
		this.decalageString = decalageString;
		String[] time = decalageString.split(":");
		long milliTime =(((Long.parseLong(time[0]) * 60 + Long.parseLong(time[1]) ) * 60 + Long.parseLong(time[2]) ) * 1000);
		DateTimeZone dtzUTC = DateTimeZone.UTC;
		this.decalageDate = new DateTime(milliTime,dtzUTC).toDate();
		/* TODO procedure erronee 
		 *  retourne une heure local au lieu du heure GMT (interval)
		 */
	}
	public Boolean getActif() {
		return actif;
	}
	public void setActif(Boolean actif) {
		this.actif = actif;
	}
}