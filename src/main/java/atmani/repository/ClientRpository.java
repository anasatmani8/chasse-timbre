package atmani.repository;


import java.util.List;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import atmani.model.Client;

@Repository
@Transactional
public interface ClientRpository extends JpaRepository<Client, Long> {
	
	@Query(value = "SELECT nbr_migrateur FROM chasse.timbre where id = 1", nativeQuery = true)
    int nbrMigrateur();
	
	@Query(value = "SELECT nbr_sedentaire FROM chasse.timbre where id = 1", nativeQuery = true)
    int nbrSedentaire();
	
	@Query(value = "SELECT nbr_touristique FROM chasse.timbre where id = 1", nativeQuery = true)
    int nbrTouristique();
	
	@Modifying
	@Query(value="UPDATE `chasse`.`timbre` SET `nbr_migrateur`=:nbr WHERE (`id` = '1');", nativeQuery = true)
	public void insertMigrateur(@Param("nbr") int cart); 
	
	@Modifying
	@Query(value="UPDATE `chasse`.`timbre` SET `nbr_sedentaire`=:nbr WHERE (`id` = '1');", nativeQuery = true)
	public void insertSedentaire(@Param("nbr") int cart); 
	
	@Modifying
	@Query(value="UPDATE `chasse`.`timbre` SET `nbr_touristique`=:nbr WHERE (`id` = '1');", nativeQuery = true)
	public void insertTouristique(@Param("nbr") int nbr); 
	
	@Query(value="SELECT COUNT(*) FROM DUAL WHERE EXISTS ( SELECT * FROM chasse.client WHERE nom =:nom AND permis=:permis AND gibier_sedentaire =:sed AND saison=:saison)", nativeQuery = true)
	int testExistanceSedentaire(@Param("nom") String nom, @Param("permis") String permis, @Param("sed") String sed, @Param("saison") String saison );

	@Query(value="SELECT COUNT(*) FROM DUAL WHERE EXISTS ( SELECT * FROM chasse.client WHERE nom =:nom AND permis=:permis AND gibier_migrateur =:mig AND saison=:saison)", nativeQuery = true)
	int testExistanceMigrateur(@Param("nom") String nom, @Param("permis") String permis, @Param("mig") String mig, @Param("saison") String saison );
	
	@Query(value="SELECT COUNT(*) FROM DUAL WHERE EXISTS ( SELECT * FROM chasse.client WHERE nom =:nom AND permis=:permis AND touristique =:touris AND saison=:saison)", nativeQuery = true)
	int testExistanceTouristique(@Param("nom") String nom, @Param("permis") String permis, @Param("touris") String touris, @Param("saison") String saison );
	
	@Query(value="SELECT * FROM chasse.client where nom like :x", nativeQuery = true)
	public List<Client> chercherNom(@Param("x") String mc);
	
	@Query(value="SELECT * FROM chasse.client where permis like :x", nativeQuery = true)
	public List<Client> chercherPermis(@Param("x") String mc);
	
	@Modifying
	@Query(value="UPDATE `chasse`.`client` SET `nom` =:nom, `prenom` =:prenom, `permis`=:permis, `saison`=:saison WHERE (`id` =:id) ;", nativeQuery = true)
	public void modifierChasseur(@Param("nom") String nom, @Param("prenom") String prenom,@Param("permis" )String permis , @Param("saison") String saison, @Param("id")Long id);
	
	@Query(value="INSERT INTO `chasse`.`client` (`nom`, `permis`, `prenom`, `saison`, `gibier_migrateur`, `gibier_sedentaire`, `touristique`) VALUES ('-------', '-------', '-------', '-------', '-------', '-------', '-------');", nativeQuery = true)
	public void soulignementRouge();
	
	@Modifying
	@Query(value="UPDATE `chasse`.`timbre` SET `nbr_migrateur` = '0', `nbr_sedentaire` = '0', `nbr_touristique` = '0' WHERE (`id` = '1');", nativeQuery = true)
	public void resetSomme();
}
