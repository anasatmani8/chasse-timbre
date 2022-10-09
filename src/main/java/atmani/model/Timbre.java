package atmani.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Timbre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nbrSedentaire")
	private int nbrSedentaire;
	
	@Column(name = "nbrMigrateur")
	private int nbrMigrateur;
	
	@Column(name = "nbrTouristique")
	private int nbrTouristique;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNbrSedentaire() {
		return nbrSedentaire;
	}

	public void setNbrSedentaire(int nbrSedentaire) {
		this.nbrSedentaire = nbrSedentaire;
	}

	public int getNbrMigrateur() {
		return nbrMigrateur;
	}

	public void setNbrMigrateur(int nbrMigrateur) {
		this.nbrMigrateur = nbrMigrateur;
	}

	public int getNbrTouristique() {
		return nbrTouristique;
	}

	public void setNbrTouristique(int nbrTouristique) {
		this.nbrTouristique = nbrTouristique;
	}

	public Timbre(int nbrSedentaire, int nbrMigrateur, int nbrTouristique) {
		super();
		this.nbrSedentaire = nbrSedentaire;
		this.nbrMigrateur = nbrMigrateur;
		this.nbrTouristique = nbrTouristique;
	}

	public Timbre() {
		super();
	}

	@Override
	public String toString() {
		return "Timbre [id=" + id + ", nbrSedentaire=" + nbrSedentaire + ", nbrMigrateur=" + nbrMigrateur
				+ ", nbrTouristique=" + nbrTouristique + "]";
	}
	
	

}
