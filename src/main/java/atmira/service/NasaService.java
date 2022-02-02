package atmira.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public interface NasaService {
	void retrieveAsteroidsInfo(LocalDate start, LocalDate end);
}
