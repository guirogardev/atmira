package atmira.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtmiraServiceImpl implements AtmiraService {
	
	@Autowired
	private NasaService nasaService;

	@Override
	public void getWorstsAsteroids(Integer days) {		
		System.out.println("Ejecutando getWorstsAsteroids(Integer days)");
		this.nasaService.retrieveAsteroidsInfo(null, null);
		System.out.println("Fin de getWorstsAsteroids(Integer days)");
		
	}

	
}
