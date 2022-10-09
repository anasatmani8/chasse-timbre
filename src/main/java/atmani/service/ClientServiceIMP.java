package atmani.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import atmani.model.Client;
import atmani.repository.ClientRpository;

@Service
public class ClientServiceIMP implements ClientService {
	
	@Autowired
	private ClientRpository clientRepository;

	@Override
	public List<Client> getAllClient() {
		// TODO Auto-generated method stub
		return clientRepository.findAll();
	}

	@Override
	public void saveClient(Client client) {
		// TODO Auto-generated method stub
		clientRepository.save(client);
	}

	@Override
	public Client getClientById(long id) {
		// TODO Auto-generated method stub
		Optional<Client> optional = this.clientRepository.findById(id);
		Client client = null;
		if (optional.isPresent()) {
			client = optional.get();
		}else
			throw new RuntimeException(" Client not found for id :: " + id);
		
		return client;
		
	}

	@Override
	public void deleteClientById(long id) {
		// TODO Auto-generated method stub
		this.clientRepository.deleteById(id);

	}
	
	

}
