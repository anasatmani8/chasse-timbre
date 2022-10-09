package atmani.controller;

import javax.validation.Valid;

//BLACKBOX
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import atmani.model.Client;
import atmani.repository.ClientRpository;
//import atmani.repository.TimbreRepository;
import atmani.service.ClientService;
import atmani.service.TimbreService;

@Controller
@ComponentScan
public class ClientController {

	@Autowired
	private ClientService clientService;

	@Autowired
	private ClientRpository clientRespo;

	/*
	 * @Autowired private TimbreRepository timbreRepository;
	 */

	@Autowired
	private TimbreService timbreService;

	// Test Variabls
	private int result = 0;

	// Display the total price

	// display list of customers
	@GetMapping("/")
	public String viewHomePage(Model model) {

		/*
		 * int result = clientRespo.testExistanceSedentaire("atmani", "5");
		 * System.out.println(result);
		 */

		model.addAttribute("listClient", clientService.getAllClient());

		int somme = clientRespo.nbrMigrateur() * 150 + clientRespo.nbrSedentaire() * 150
				+ clientRespo.nbrTouristique() * 800;
		model.addAttribute("somme", somme);

		return "index";
	}

	@GetMapping("/afficherListTimbres")
	public String listTimbre(Model model) {
		int somme = clientRespo.nbrMigrateur() * 150 + clientRespo.nbrSedentaire() * 150
				+ clientRespo.nbrTouristique() * 800;
		model.addAttribute("somme", somme);
		model.addAttribute("listTimbre", timbreService.getAllTimbre());
		return "timbre";
	}

	@GetMapping(value = "/chercherChasseurNom")
	public String chercherNom(Model model, @RequestParam(name = "mc1") String mc) {
		model.addAttribute("listClient", clientRespo.chercherNom("%" + mc + "%"));
		int somme = clientRespo.nbrMigrateur() * 150 + clientRespo.nbrSedentaire() * 150
				+ clientRespo.nbrTouristique() * 800;
		model.addAttribute("somme", somme);
		return "index";

	}

	@GetMapping(value = "/chercherChasseurPermis")
	public String chercherPermis(Model model, @RequestParam(name = "mc2") String mc) {
		model.addAttribute("listClient", clientRespo.chercherPermis("%" + mc + "%"));
		int somme = clientRespo.nbrMigrateur() * 150 + clientRespo.nbrSedentaire() * 150
				+ clientRespo.nbrTouristique() * 800;
		model.addAttribute("somme", somme);
		return "index";

	}

	@GetMapping("/afficherAjoutClient")
	public String showClientForm(Model model) {
		Client client = new Client();
		model.addAttribute("client", client);
		return "new_client";
	}

	@PostMapping("/ajouterClient")
	public String ajouterClient(@Valid Client client, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "new_client";

		}
		
		System.out.println("//////////// DEBUT DAJOUT ////////////");
		int nbrMig, mig = 0;
		int nbrSed, sed = 0;
		int nbrTou, tou = 0;
		System.out.println("affichage des donnes");
		System.out.println(client.toString());

		if (!client.getGibier_migrateur().isEmpty()) {
			System.out.println("//////////// insertion de  migrateur ////////////");
			System.out.println(client.getNom() + client.getGibier_migrateur());
			result = clientRespo.testExistanceMigrateur(client.getNom(), client.getPermis(),
					client.getGibier_migrateur(), client.getSaison());
			System.out.println("//////////// le retenue dexistance de migrateur est " + result + " ////////////");
			nbrMig = clientRespo.nbrMigrateur();
			System.out.println(nbrMig + " est le nbr de migrateur");
			mig = nbrMig + 1;
			System.out.println(mig);

		}

		if (!client.getGibier_sedentaire().isEmpty()) {
			System.out.println("//////////// insertion de  sedentaire ////////////");
			System.out.println(client.getNom() + client.getGibier_sedentaire());
			result = clientRespo.testExistanceSedentaire(client.getNom(), client.getPermis(),
					client.getGibier_sedentaire(), client.getSaison());
			System.out.println("//////////// le retenue dexistance de sedentaire est " + result + " ////////////");
			System.out.println(result + " est le retenue");
			nbrSed = clientRespo.nbrSedentaire();
			System.out.println(nbrSed + " est le nbr de sendetaire");
			sed = nbrSed + 1;
			System.out.println(sed);

		}

		if (!client.getTouristique().isEmpty()) {
			System.out.println("//////////// insertion de  touristique ////////////");
			System.out.println(client.getNom() + client.getTouristique());
			result = clientRespo.testExistanceTouristique(client.getNom(), client.getPermis(), client.getTouristique(),
					client.getSaison());
			System.out.println("//////////// le retenue dexistance de touristique est " + result + " ////////////");
			nbrTou = clientRespo.nbrTouristique();
			System.out.println(nbrTou + " est le nbr de Tour");
			tou = nbrTou + 1;
			System.out.println(tou);

		}

		System.out.println(result + " le retenue dehors les tests");

		if (result == 0) {
			System.out.println("//////////// l'insertion est nouvelle / aucun double ////////////");
			if (mig != 0) {
				int somme = clientRespo.nbrMigrateur() * 150 + clientRespo.nbrSedentaire() * 150
						+ clientRespo.nbrTouristique() * 800;
				System.out.println(somme + " est la somme lors linsertion dun migrateur");
				System.out.println(mig);
				if ((mig * 150) + somme >= 2799) {
					System.out.println("rah fat plafont");
					Client cc = new Client("-------", "-------", "-------", "-------", "-------", "-------", "-------");
					clientRespo.save(cc);
					//clientRespo.soulignementRouge();
					clientRespo.resetSomme();
				} else {

					System.out.println(mig + " est le nouveau nbr des timbres mig");
					clientRespo.insertMigrateur(mig);
					clientService.saveClient(client);
					System.out.println("new Client " + client);
				}
			}

			if (sed != 0) {
				int somme = clientRespo.nbrMigrateur() * 150 + clientRespo.nbrSedentaire() * 150
						+ clientRespo.nbrTouristique() * 800;
				System.out.println(somme + " est la somme lors linsertion dun sedentaire");
				System.out.println(sed);
				if ((sed * 150) + somme >= 2799) {
					System.out.println("rah fat plafont");
					Client cc = new Client("-------", "-------", "-------", "-------", "-------", "-------", "-------");
					clientRespo.save(cc);
					//clientRespo.soulignementRouge();
					clientRespo.resetSomme();
				} else {
				System.out.println(sed + " est le nouveau nbr des timbres sed");
				clientRespo.insertSedentaire(sed);
				clientService.saveClient(client);
				System.out.println("new Client " + client);
			}}
			if (tou != 0) {
				
				int somme = clientRespo.nbrMigrateur() * 150 + clientRespo.nbrSedentaire() * 150
						+ clientRespo.nbrTouristique() * 800;
				System.out.println(somme + " est la somme lors linsertion dun touristique");
				System.out.println(tou);
				if ((tou * 800) + somme >= 2799) {
					System.out.println("rah fat plafont");
					Client cc = new Client("-------", "-------", "-------", "-------", "-------", "-------", "-------");
					clientRespo.save(cc);
					//clientRespo.soulignementRouge();
					clientRespo.resetSomme();
				} else {
				
				System.out.println(tou + " est le nouveau nbr des timbres tour");
				clientRespo.insertTouristique(tou);
				clientService.saveClient(client);
				System.out.println("new Client " + client);
			}}
			
			return "redirect:/";

		} else

			return "errorMessage";

	}

	@GetMapping(value = "/modifierChasseurForm")
	public String editForm(Model model, @RequestParam(name = "id", defaultValue = "0") Long id) {

		model.addAttribute("client", clientService.getClientById(id));
		return "editClient";
	}

	@PostMapping(value = "/modifierChasseur")
	public String edit(@ModelAttribute("client") Client client) {
		clientRespo.modifierChasseur(client.getNom(), client.getPrenom(), client.getPermis(), client.getSaison(),
				client.getId());
		return "redirect:/";

	}

	@GetMapping(value = "/ajoutPourMemeChasseur")
	public String ajoutPourMemeChasseur(Model model, @ModelAttribute("client") Client client,
			@RequestParam(name = "id", defaultValue = "0") Long id) {
		model.addAttribute("client", clientService.getClientById(id));
		return "new_client";
	}

}
