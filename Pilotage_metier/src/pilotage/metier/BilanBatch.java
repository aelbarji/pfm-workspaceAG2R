package pilotage.metier;

import java.io.Serializable;
import java.util.Date;

public class BilanBatch implements Serializable {

	private static final long serialVersionUID = 6311585301614459436L;

	// primary key
	private Date id;

	// fields
	private String cftdiv;
	private String cftdon;
	private String cftext;
	private String cftmvs1;
	private String cftsit;
	private String cftstf;
	private String cfttrf;
	private Short dons;
	private Short espacebdaf;
	private Short espacebdi;
	private Short espacebr;
	private Short espacecff;
	private Short espaceolympic;
	private Short espacesocram;
	private Short pfbiasp;
	private Short pfbsysbas;
	private Short reinitevolan;
	private Short reinitgestitres;
	private Short restoas400;
	private Short restounix;
	private Short restowindows;
	private Byte vacationaprem;
	private Byte vacationmatin;
	private Byte vacationnuit;
	
	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="Assigned"
     *  column="date"
     */
	public Date getId() {
		return id;
	}
	
	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId(Date id) {
		this.id = id;
	}
	
	/**
	 * Return the value associated with the column: cftdiv
	 */
	public String getCftdiv() {
		return cftdiv;
	}
	
	/**
	 * Set the value related to the column: cftdiv
	 * @param cftdiv the cftdiv value
	 */
	public void setCftdiv(String cftdiv) {
		this.cftdiv = cftdiv;
	}
	
	/**
	 * Return the value associated with the column: cftdon
	 */
	public String getCftdon() {
		return cftdon;
	}
	
	/**
	 * Set the value related to the column: cftdon
	 * @param cftdon the cftdon value
	 */
	public void setCftdon(String cftdon) {
		this.cftdon = cftdon;
	}
	
	/**
	 * Return the value associated with the column: cftext
	 */
	public String getCftext() {
		return cftext;
	}
	
	/**
	 * Set the value related to the column: cftext
	 * @param cftext the cftext value
	 */
	public void setCftext(String cftext) {
		this.cftext = cftext;
	}
	
	/**
	 * Return the value associated with the column: cftmvs1
	 */
	public String getCftmvs1() {
		return cftmvs1;
	}
	
	/**
	 * Set the value related to the column: cftmvs1
	 * @param cftmvs1 the cftmvs1 value
	 */
	public void setCftmvs1(String cftmvs1) {
		this.cftmvs1 = cftmvs1;
	}
	
	/**
	 * Return the value associated with the column: cftsit
	 */
	public String getCftsit() {
		return cftsit;
	}
	
	/**
	 * Set the value related to the column: cftsit
	 * @param cftsit the cftsit value
	 */
	public void setCftsit(String cftsit) {
		this.cftsit = cftsit;
	}
	
	/**
	 * Return the value associated with the column: cftstf
	 */
	public String getCftstf() {
		return cftstf;
	}
	
	/**
	 * Set the value related to the column: cftstf
	 * @param cftstf the cftstf value
	 */
	public void setCftstf(String cftstf) {
		this.cftstf = cftstf;
	}
	
	/**
	 * Return the value associated with the column: cfttrf
	 */
	public String getCfttrf() {
		return cfttrf;
	}
	
	/**
	 * Set the value related to the column: cfttrf
	 * @param cfttrf the cfttrf value
	 */
	public void setCfttrf(String cfttrf) {
		this.cfttrf = cfttrf;
	}
	
	/**
	 * Return the value associated with the column: dons
	 */
	public Short getDons() {
		return dons;
	}
	
	/**
	 * Set the value related to the column: dons
	 * @param dons the dons value
	 */
	public void setDons(Short dons) {
		this.dons = dons;
	}
	
	/**
	 * Return the value associated with the column: espacebdaf
	 */
	public Short getEspacebdaf() {
		return espacebdaf;
	}
	
	/**
	 * Set the value related to the column: espacebdaf
	 * @param espacebdaf the espacebdaf value
	 */
	public void setEspacebdaf(Short espacebdaf) {
		this.espacebdaf = espacebdaf;
	}
	
	/**
	 * Return the value associated with the column: espacebdi
	 */
	public Short getEspacebdi() {
		return espacebdi;
	}
	
	/**
	 * Set the value related to the column: espacebdi
	 * @param espacebdi the espacebdi value
	 */
	public void setEspacebdi(Short espacebdi) {
		this.espacebdi = espacebdi;
	}
	
	/**
	 * Return the value associated with the column: espacebr
	 */
	public Short getEspacebr() {
		return espacebr;
	}
	
	/**
	 * Set the value related to the column: espacebr
	 * @param espacebr the espacebr value
	 */
	public void setEspacebr(Short espacebr) {
		this.espacebr = espacebr;
	}
	
	/**
	 * Return the value associated with the column: espacecff
	 */
	public Short getEspacecff() {
		return espacecff;
	}
	
	/**
	 * Set the value related to the column: espacecff
	 * @param espacecff the espacecff value
	 */
	public void setEspacecff(Short espacecff) {
		this.espacecff = espacecff;
	}
	
	/**
	 * Return the value associated with the column: espaceolympic
	 */
	public Short getEspaceolympic() {
		return espaceolympic;
	}
	
	/**
	 * Set the value related to the column: espaceolympic
	 * @param espaceolympic the espaceolympic value
	 */
	public void setEspaceolympic(Short espaceolympic) {
		this.espaceolympic = espaceolympic;
	}
	
	/**
	 * Return the value associated with the column: espacesocram
	 */
	public Short getEspacesocram() {
		return espacesocram;
	}
	
	/**
	 * Set the value related to the column: espacesocram
	 * @param espacesocram the espacesocram value
	 */
	public void setEspacesocram(Short espacesocram) {
		this.espacesocram = espacesocram;
	}
	
	/**
	 * Return the value associated with the column: pfbiasp
	 */
	public Short getPfbiasp() {
		return pfbiasp;
	}
	
	/**
	 * Set the value related to the column: pfbiasp
	 * @param pfbiasp the pfbiasp value
	 */
	public void setPfbiasp(Short pfbiasp) {
		this.pfbiasp = pfbiasp;
	}
	
	/**
	 * Return the value associated with the column: pfbsysbas
	 */
	public Short getPfbsysbas() {
		return pfbsysbas;
	}
	
	/**
	 * Set the value related to the column: pfbsysbas
	 * @param pfbsysbas the pfbsysbas value
	 */
	public void setPfbsysbas(Short pfbsysbas) {
		this.pfbsysbas = pfbsysbas;
	}
	
	/**
	 * Return the value associated with the column: reinitevolan
	 */
	public Short getReinitevolan() {
		return reinitevolan;
	}
	
	/**
	 * Set the value related to the column: reinitevolan
	 * @param reinitevolan the reinitevolan value
	 */
	public void setReinitevolan(Short reinitevolan) {
		this.reinitevolan = reinitevolan;
	}
	
	/**
	 * Return the value associated with the column: reinitgestitres
	 */
	public Short getReinitgestitres() {
		return reinitgestitres;
	}
	
	/**
	 * Set the value related to the column: reinitgestitres
	 * @param reinitgestitres the reinitgestitres value
	 */
	public void setReinitgestitres(Short reinitgestitres) {
		this.reinitgestitres = reinitgestitres;
	}
	
	/**
	 * Return the value associated with the column: restoas400
	 */
	public Short getRestoas400() {
		return restoas400;
	}
	
	/**
	 * Set the value related to the column: restoas400
	 * @param restoas400 the restoas400 value
	 */
	public void setRestoas400(Short restoas400) {
		this.restoas400 = restoas400;
	}
	
	/**
	 * Return the value associated with the column: restounix
	 */
	public Short getRestounix() {
		return restounix;
	}
	
	/**
	 * Set the value related to the column: restounix
	 * @param restounix the restounix value
	 */
	public void setRestounix(Short restounix) {
		this.restounix = restounix;
	}
	
	/**
	 * Return the value associated with the column: restowindows
	 */
	public Short getRestowindows() {
		return restowindows;
	}
	
	/**
	 * Set the value related to the column: restowindows
	 * @param restowindows the restowindows value
	 */
	public void setRestowindows(Short restowindows) {
		this.restowindows = restowindows;
	}
	
	/**
	 * Return the value associated with the column: vacationaprem
	 */
	public Byte getVacationaprem() {
		return vacationaprem;
	}
	
	/**
	 * Set the value related to the column: vacationaprem
	 * @param vacationaprem the vacationaprem value
	 */
	public void setVacationaprem(Byte vacationaprem) {
		this.vacationaprem = vacationaprem;
	}
	
	/**
	 * Return the value associated with the column: vacationmatin
	 */
	public Byte getVacationmatin() {
		return vacationmatin;
	}
	
	/**
	 * Set the value related to the column: vacationmatin
	 * @param vacationmatin the vacationmatin value
	 */
	public void setVacationmatin(Byte vacationmatin) {
		this.vacationmatin = vacationmatin;
	}
	
	/**
	 * Return the value associated with the column: vacationnuit
	 */
	public Byte getVacationnuit() {
		return vacationnuit;
	}
	
	/**
	 * Set the value related to the column: vacationnuit
	 * @param vacationnuit the vacationnuit value
	 */
	public void setVacationnuit(Byte vacationnuit) {
		this.vacationnuit = vacationnuit;
	}
	
	
	
}