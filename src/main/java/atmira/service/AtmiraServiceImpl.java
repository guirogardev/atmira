package atmira.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import atmira.dto.AsteroidDto;

@Service
public class AtmiraServiceImpl implements AtmiraService {
	
	@Autowired
	private NasaService nasaService;

	@Override
	public List<AsteroidDto> getWorstsAsteroids(Integer days) {
		LocalDate start = LocalDate.now();
		LocalDate end = LocalDate.now().plusDays(days);
		List<AsteroidDto> asteroides = this.nasaService.retrieveAsteroidListInfo(start, end);

		return calculateTopAsteroids(asteroides);
		
	}
	
	/**
	 * Dada una lista de asteroides, devuelve el top 3 de asteroides más grandes con potencial 
	 * riesgo de impacto en el planeta Tierra.
	 * 
	 * @param asteroides Listado de asteroides.
	 * 
	 * @return Listado con el top 3 de asteroides.
	 */
	private List<AsteroidDto> calculateTopAsteroids(List<AsteroidDto> asteroides) {
		List<AsteroidDto> resultado = asteroides.stream()
				 .filter(a -> a.getIsPotentiallyHazardous())
				 .sorted(Comparator.comparingDouble(AsteroidDto::getAverageDiameter).reversed())
				 .collect(Collectors.toList());
		
		return resultado.subList(0, 3);
	}

	
}
