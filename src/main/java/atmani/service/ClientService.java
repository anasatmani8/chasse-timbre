package atmani.service;

import java.util.List;

import atmani.model.Client;



public interface ClientService {
	
	List<Client> getAllClient();
	void saveClient(Client client);
	Client getClientById(long id);
	void deleteClientById(long id);

}
