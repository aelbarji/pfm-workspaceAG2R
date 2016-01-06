package pilotage.metier;

public class Bilan_Destinataires {
	private Integer id;
	private String mail;
	private String nom;
	private String prenom;
	private Bilan_Envoie type_bilan;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return the type_bilan
	 */
	public Bilan_Envoie getType_bilan() {
		return type_bilan;
	}
	/**
	 * @param type_bilan the type_bilan to set
	 */
	public void setType_bilan(Bilan_Envoie type_bilan) {
		this.type_bilan = type_bilan;
	}

	
}
