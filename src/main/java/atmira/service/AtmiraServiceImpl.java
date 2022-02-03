package atmira.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import atmira.dto.AsteroidDto;
import atmira.dto.FormattedAsteroidDto;

@Service
public class AtmiraServiceImpl implements AtmiraService {
	
	private static final Integer NUMERO_ASTEROIDES_RESPONSE = 3;
	private static final String REQUIRED_ORBITING_BODY_NAME = "Earth";
	
	@Autowired
	private NasaService nasaService;

	@Override
	public List<FormattedAsteroidDto> getWorstsAsteroids(Integer days) {
		LocalDate start = LocalDate.now();
		LocalDate end = LocalDate.now().plusDays(days);
		List<AsteroidDto> asteroides = this.nasaService.retrieveAsteroidListInfo(start, end);

		return calculateTopFormattedAsteroids(asteroides);
		
	}
	
	/**
	 * Dada una lista de asteroides, devuelve el top 3 de asteroides más grandes con potencial 
	 * riesgo de impacto en el planeta Tierra formateados.
	 * 
	 * @param asteroides Listado de asteroides.
	 * 
	 * @return Listado con el top 3 de asteroides formateados.
	 */
	private List<FormattedAsteroidDto> calculateTopFormattedAsteroids(List<AsteroidDto> asteroides) {
		List<FormattedAsteroidDto> resultado = new ArrayList<>();
		List<AsteroidDto> asteroidesDto = asteroides.stream()
				 .filter(a -> a.getIsPotentiallyHazardous() && REQUIRED_ORBITING_BODY_NAME.equals(a.getOrbitingBody()))
				 .sorted(Comparator.comparingDouble(AsteroidDto::getAverageDiameter).reversed())
				 .collect(Collectors.toList());
		
		asteroidesDto = asteroidesDto.size() <= NUMERO_ASTEROIDES_RESPONSE ? asteroidesDto : asteroidesDto.subList(0, 3);
		
		for (AsteroidDto asteroid: asteroidesDto) {
			resultado.add(asteroidDtoToFormattedAsteroidDto(asteroid));
		}
		
		return resultado;
	}
	
	/**
	 * Dado un asteroide de tipo AsteroidDto, lo transforma a FormattedAsteroidDto.
	 * 
	 * @param asteroid Asteroide de tipo AsteroidDto.
	 * 
	 * @return Asteroide de tipo FormattedAsteroidDto.
	 */
	private FormattedAsteroidDto asteroidDtoToFormattedAsteroidDto(AsteroidDto asteroid) {
		FormattedAsteroidDto formattedAsteroid = new FormattedAsteroidDto();
		formattedAsteroid.setNombre(asteroid.getName());
		formattedAsteroid.setDiametro(asteroid.getAverageDiameter());
		formattedAsteroid.setVelocidad(asteroid.getRelativeVelocityKilometersPerHour());
		formattedAsteroid.setFecha(asteroid.getApproachDate());
		formattedAsteroid.setPlaneta(asteroid.getOrbitingBody());		
		return formattedAsteroid;
	}
	

	
}
