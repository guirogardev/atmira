package atmira.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NasaServiceImpl implements NasaService {
	
	private static final String NASA_API_KEY = "zdUP8ElJv1cehFM0rsZVSQN7uBVxlDnu4diHlLSb";

	@Override
	public void retrieveAsteroidsInfo(LocalDate start, LocalDate end) {
		
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://api.nasa.gov/neo/rest/v1/feed?start_date=2021-12-09&end_date=2021-12-12&api_key=" + NASA_API_KEY;
	    String result = restTemplate.getForObject(uri, String.class);
	    
	    System.out.println(result);
	}

}
