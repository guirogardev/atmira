package atmira.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import atmira.dto.AsteroidDto;

@Service
public interface NasaService {
	
	/**
	 * Dado un rango de fechas, devuelve información sobre los asteroides listados
	 * por la NASA para esos días.
	 * 
	 * @param start Fecha de inicio.
	 * @param end Fecha de fin.
	 * 
	 * @return Listado de asteroides.
	 */
	List<AsteroidDto> retrieveAsteroidListInfo(LocalDate start, LocalDate end);
}
