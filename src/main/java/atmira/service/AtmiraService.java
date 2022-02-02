package atmira.service;

import java.util.List;

import org.springframework.stereotype.Service;

import atmira.dto.AsteroidDto;

@Service
public interface AtmiraService {
	
	/**
	 * Dado un número de días, devuelve el top 3 de asteroides más grandes con potencial 
	 * riesgo de impacto en el planeta Tierra entre el día de hoy y la fecha obtenida de 
	 * sumar a la fecha de hoy el número de días mencionado.
	 * 
	 * @param days Número de días.
	 * 
	 * @return Listado con el top 3 de asteroides.
	 */
	List<AsteroidDto> getWorstsAsteroids(Integer days);
}
