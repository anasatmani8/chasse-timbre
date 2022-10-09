package atmani.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import atmani.model.Timbre;
import atmani.repository.TimbreRepository;

@Service
public class TimbreServiceIMP implements TimbreService {
	
	@Autowired
	private TimbreRepository timbreRepository;

	@Override
	public List<Timbre> getAllTimbre() {
		// TODO Auto-generated method stub
		return timbreRepository.findAll();
	}

}
