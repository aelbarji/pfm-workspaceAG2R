package pilotage.metier;

import java.io.Serializable;

public class Machines_Applicatifs implements Serializable{
	private static final long serialVersionUID = -3116916570271905295L;

	private Integer id;
	private Applicatifs_Liste applicatif;
	private Machines_Liste machine;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Applicatifs_Liste getApplicatif() {
		return applicatif;
	}
	public void setApplicatif(Applicatifs_Liste applicatif) {
		this.applicatif = applicatif;
	}
	public Machines_Liste getMachine() {
		return machine;
	}
	public void setMachine(Machines_Liste machine) {
		this.machine = machine;
	}
	
}
