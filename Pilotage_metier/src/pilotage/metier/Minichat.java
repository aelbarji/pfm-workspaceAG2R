package pilotage.metier;
/*
 * author by WWei
 * @Groupe HN
 * @18/06/2011
 */
import java.io.Serializable;
import java.util.Date;

public class Minichat implements Serializable{
	private static final long serialVersionUID = 872691219267149447L;

	private Integer id;
	private Date date;
	private String text;
	private Integer idUser;
	
	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.Assigned"
     *  column="id"
     */
	public Integer getId() {
		return id;
	}
	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Return the value associated with the column: date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * Set the value related to the column: date
	 * @param date the date value
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * Return the value associated with the column: text
	 */
	public String getText() {
		return text;
	}
	/**
	 * Set the value related to the column: text
	 * @param text the text value
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * Return the value associated with the column: idUser
	 */
	public Integer getIdUser() {
		return idUser;
	}
	/**
	 * Set the value related to the column: idUser
	 * @param idUser the idUser value
	 */
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
}
