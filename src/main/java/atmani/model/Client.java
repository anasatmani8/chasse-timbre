package atmani.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
public class Client implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(min=4, max=15) @NotBlank(message = "nom est obligatoir")
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "Touristique")
	private String Touristique;
	
	@Column(name = "prenom")
	private String prenom;
	
	@Column(name = "permis")
	private String permis;
	
	@Column(name = "timbre")
	private String timbre;
	
	@Column(name = "saison")
	private String saison;
	
	@Column(name = "CIN")
	private String CIN;
	
	
	@Column(name = "Gibier_migrateur")
	private String Gibier_migrateur;
	
	@Column(name = "Gibier_sedentaire")
	private String Gibier_sedentaire;
	
	

	public Client(String nom, String touristique, String prenom, String permis, String saison, String gibier_migrateur,
			String gibier_sedentaire) {
		super();
		this.nom = nom;
		Touristique = touristique;
		this.prenom = prenom;
		this.permis = permis;
		this.saison = saison;
		Gibier_migrateur = gibier_migrateur;
		Gibier_sedentaire = gibier_sedentaire;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPermis() {
		return permis;
	}

	public void setPermis(String permis) {
		this.permis = permis;
	}

	public String getTimbre() {
		return timbre;
	}

	public void setTimbre(String timbre) {
		this.timbre = timbre;
	}

	public String getCIN() {
		return CIN;
	}

	public void setCIN(String cIN) {
		CIN = cIN;
	}



	public String getSaison() {
		return saison;
	}

	public void setSaison(String saison) {
		this.saison = saison;
	}

	
	

	public Client(String nom, String touristique, String prenom, String permis, String timbre, String saison,
			String cIN, String gibier_migrateur, String gibier_sedentaire) {
		super();
		this.nom = nom;
		this.Touristique = touristique;
		this.prenom = prenom;
		this.permis = permis;
		this.timbre = timbre;
		this.saison = saison;
		CIN = cIN;
		this.Gibier_migrateur = gibier_migrateur;
		this.Gibier_sedentaire = gibier_sedentaire;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", Touristique=" + Touristique + ", prenom=" + prenom + ", permis="
				+ permis + ", timbre=" + timbre + ", saison=" + saison + ", CIN=" + CIN + ", Gibier_migrateur="
				+ Gibier_migrateur + ", Gibier_sedentaire=" + Gibier_sedentaire + "]";
	}

	public String getGibier_migrateur() {
		return Gibier_migrateur;
	}

	public void setGibier_migrateur(String gibier_migrateur) {
		Gibier_migrateur = gibier_migrateur;
	}

	public String getGibier_sedentaire() { 
		return Gibier_sedentaire;
	}

	public void setGibier_sedentaire(String gibier_sedentaire) {
		Gibier_sedentaire = gibier_sedentaire;
	}

	public Client() {
		super();
	}

	

	public String getTouristique() {
		return Touristique;
	}

	public void setTouristique(String touristique) {
		Touristique = touristique;
	}

	
	
	

}
