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
	
	private static final String NEAR_EARTH_OBJECTS_JSON_KEY = "near_earth_objects";
	private static final String ESTIMATED_DIAMETER_JSON_KEY = "estimated_diameter";
	private static final String KILOMETERS_JSON_KEY = "kilometers";
	private static final String CLOSE_APPROACH_DATA_JSON_KEY = "close_approach_data";
	private static final String NAME_JSON_KEY = "name";
	private static final String HAZARDOUS_JSON_KEY = "is_potentially_hazardous_asteroid";
	private static final String DIAMETER_MAX_JSON_KEY = "estimated_diameter_max";
	private static final String DIAMETER_MIN_JSON_KEY = "estimated_diameter_min";
	private static final String RELATIVE_VELOCITY_JSON_KEY = "relative_velocity";
	private static final String KILOMETERS_HOUR_JSON_KEY = "kilometers_per_hour";
	private static final String CLOSE_APPROACH_DATE_JSON_KEY = "close_approach_date";
	private static final String ORBITING_BODY_JSON_KEY = "orbiting_body";

	@Override
	public List<AsteroidDto> retrieveAsteroidListInfo(LocalDate start, LocalDate end) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://api.nasa.gov/neo/rest/v1/feed?start_date=" + start.toString() + "&end_date=" + end.toString() + "&api_key=" + NASA_API_KEY;
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
	    JsonObject nearEarthObjects = data.get(NEAR_EARTH_OBJECTS_JSON_KEY).getAsJsonObject();
	    
	    List<AsteroidDto> asteroidesDto = new ArrayList<>();
	    while (start.isBefore(end)) {

	    	JsonArray asteroidsDateJson = nearEarthObjects.get(start.toString()).getAsJsonArray();

	    	for (JsonElement asteroidJsonElement : asteroidsDateJson) {
	    		AsteroidDto asteroide = new AsteroidDto();
	    		JsonObject asteroidJson = asteroidJsonElement.getAsJsonObject();
	    		JsonObject kilometersJson = asteroidJson.get(ESTIMATED_DIAMETER_JSON_KEY).getAsJsonObject().get(KILOMETERS_JSON_KEY).getAsJsonObject();
	    		JsonObject approachDataJson = asteroidJson.get(CLOSE_APPROACH_DATA_JSON_KEY).getAsJsonArray().get(0).getAsJsonObject();
	    		
		    	asteroide.setName(asteroidJson.get(NAME_JSON_KEY).getAsString());
		    	asteroide.setIsPotentiallyHazardous(asteroidJson.get(HAZARDOUS_JSON_KEY).getAsBoolean());
		    	
		    	asteroide.setEstimatedDiameterMax(kilometersJson.get(DIAMETER_MAX_JSON_KEY).getAsDouble());
		    	asteroide.setEstimatedDiameterMin(kilometersJson.get(DIAMETER_MIN_JSON_KEY).getAsDouble());
		    	
		    	asteroide.setRelativeVelocityKilometersPerHour(
		    			approachDataJson
		    			.get(RELATIVE_VELOCITY_JSON_KEY).getAsJsonObject()
		    			.get(KILOMETERS_HOUR_JSON_KEY).getAsDouble()
		    			);
		    	
		    	asteroide.setApproachDate(LocalDate.parse(approachDataJson.get(CLOSE_APPROACH_DATE_JSON_KEY).getAsString()));
		    	asteroide.setOrbitingBody(approachDataJson.get(ORBITING_BODY_JSON_KEY).getAsString());
		    	asteroidesDto.add(asteroide);
	    	}
	    	start = start.plusDays(1);
	    }
	    
	    return asteroidesDto;
	}

}
