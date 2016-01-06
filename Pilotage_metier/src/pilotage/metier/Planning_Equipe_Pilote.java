package pilotage.metier;

import java.io.Serializable;
import java.util.Date;

public class Planning_Equipe_Pilote implements Serializable {

	private static final long serialVersionUID = -4868941830446992890L;

	private Integer id;
	private Planning_Nom_Equipe idNomEquipe;
	private Users idUser;
	private Date dateDebut;
	private Date dateFin;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Planning_Nom_Equipe getIdNomEquipe() {
		return idNomEquipe;
	}
	public void setIdNomEquipe(Planning_Nom_Equipe idNomEquipe) {
		this.idNomEquipe = idNomEquipe;
	}
	public Users getIdUser() {
		return idUser;
	}
	public void setIdUser(Users idUser) {
		this.idUser = idUser;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
}