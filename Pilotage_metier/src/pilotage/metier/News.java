package pilotage.metier;
/*
 * author by WWei
 * @Groupe HN
 * @18/06/2011
 */
import java.io.Serializable;
import java.util.Date;

public class News implements Serializable{
	private static final long serialVersionUID = 5341565977731434724L;

	private Integer id;
	private Date date;
	private String text;
	
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
}
