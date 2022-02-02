package atmira.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import atmira.dto.AsteroidDto;

@Service
public class NasaServiceImpl implements NasaService {
	
	private static final String NASA_API_KEY = "zdUP8ElJv1cehFM0rsZVSQN7uBVxlDnu4diHlLSb";

	@Override
	public List<AsteroidDto> retrieveAsteroidListInfo(LocalDate start, LocalDate end) {
		
		start = LocalDate.now();
		end = LocalDate.now().plusDays(3);
		
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://api.nasa.gov/neo/rest/v1/feed?start_date=2022-02-02&end_date=2022-02-05&api_key=" + NASA_API_KEY;
		String jsonString = restTemplate.getForObject(uri, String.class);
		
		return this.nasaJsonToAsteroidList(jsonString, start, end);
	}
	
	
	/**
	 * Dado el JSON de respuesta de la NASA y su rango de fechas, convierte los asteroides
	 * de dicho JSON a una lista de objetos AsteroidDto.
	 * 
	 * @param jsonString JSON de la NASA.
	 * @param start Fecha de inicio.
	 * @param end Fecha de fin.
	 * 
	 * @return Listado de asteroides de tipo AsteroidDto.
	 */
	private List<AsteroidDto> nasaJsonToAsteroidList(String jsonString, LocalDate start, LocalDate end) {
		Gson gson = new Gson();
		JsonObject data = gson.fromJson(jsonString, JsonObject.class);
	    JsonObject nearEarthObjects = data.get("near_earth_objects").getAsJsonObject();
	    
	    List<AsteroidDto> asteroidesDto = new ArrayList<>();
	    while (start.isBefore(end)) {
	    	
	    	
	    	JsonArray asteroidsDateJson = nearEarthObjects.get(start.toString()).getAsJsonArray();

	    	
	    	for (JsonElement asteroidJsonElement : asteroidsDateJson) {
	    		AsteroidDto asteroide = new AsteroidDto();
	    		JsonObject asteroidJson = asteroidJsonElement.getAsJsonObject();
	    		JsonObject kilometersJson = asteroidJson.get("estimated_diameter").getAsJsonObject().get("kilometers").getAsJsonObject();
	    		JsonObject approachDataJson = asteroidJson.get("close_approach_data").getAsJsonArray().get(0).getAsJsonObject();
	    		
		    	asteroide.setName(asteroidJson.get("name").getAsString());
//		    	asteroide.setIsPotentiallyHazardous(asteroidJson.get("is_potentially_hazardous_asteroid").getAsBoolean());
		    	asteroide.setIsPotentiallyHazardous(true);
		    	asteroide.setEstimatedDiameterMax(kilometersJson.get("estimated_diameter_max").getAsDouble());
		    	asteroide.setEstimatedDiameterMin(kilometersJson.get("estimated_diameter_min").getAsDouble());
		    	
		    	asteroide.setRelativeVelocityKilometersPerHour(
		    			approachDataJson
		    			.get("relative_velocity").getAsJsonObject()
		    			.get("kilometers_per_hour").getAsDouble()
		    			);
		    	
		    	asteroide.setApproachDate(LocalDate.parse(approachDataJson.get("close_approach_date").getAsString()));
		    	
		    	asteroidesDto.add(asteroide);
	    	}
	    	start = start.plusDays(1);
	    }
	    
	    return asteroidesDto;
	}

}
