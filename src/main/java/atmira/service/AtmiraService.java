package atmira.service;

import org.springframework.stereotype.Service;

@Service
public interface AtmiraService {
	void getWorstsAsteroids(Integer days);
}
