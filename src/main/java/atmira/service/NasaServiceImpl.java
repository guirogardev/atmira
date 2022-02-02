package atmira.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class NasaServiceImpl implements NasaService {

	@Override
	public void retrieveAsteroidsInfo(LocalDate start, LocalDate end) {
		System.out.println("Ejecutando retrieveAsteroidsInfo(LocalDate start, LocalDate end)");
		System.out.println("Fin de retrieveAsteroidsInfo(LocalDate start, LocalDate end)");
	}

}
