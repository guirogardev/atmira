package atmira.pruebatecnica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import atmira.dto.FormattedAsteroidDto;

@SpringBootTest
class PruebaTecnicaApplicationTests {
	
	private static final String NOT_NULL_DAYS_MSG = "Required request parameter 'days' for method parameter type Integer is present but converted to null";
	private static final String MIN_DAYS_GREATER_EQUAL_ONE_MSG = "getWorstsAsteroids.days: tiene que ser mayor o igual que 1";
	private static final String MIN_DAYS_LESS_EQUAL_SEVEN_MSG = "getWorstsAsteroids.days: tiene que ser menor o igual que 7";
	private static final Integer MAX_NUMBER_ASTEROIDS = 3;
	
	@Test
	void retrieveAsteroidsTest() {
		List<FormattedAsteroidDto> asteroides = this.callAtmiraAPI(4);
		assertNotEquals(asteroides, null);
		assertTrue(asteroides.size() <= MAX_NUMBER_ASTEROIDS);
	}
	
	@Test
	void daysCannotBeNullTest() {
		try {
			this.callAtmiraAPI(null);
		} catch(HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.BAD_REQUEST);
			assertTrue(e.getResponseBodyAsString().contains(NOT_NULL_DAYS_MSG));
		}
		
	}
	
	@Test
	void daysMinMustBeOneTest() {
		try {
			this.callAtmiraAPI(0);
		} catch(HttpServerErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
			assertTrue(e.getResponseBodyAsString().contains(MIN_DAYS_GREATER_EQUAL_ONE_MSG));
		}
	}
	
	@Test
	void daysMaxMustBeSevenTest() {
		try {
			this.callAtmiraAPI(9);
		} catch(HttpServerErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
			assertTrue(e.getResponseBodyAsString().contains(MIN_DAYS_LESS_EQUAL_SEVEN_MSG));
		}
	}
	
	private List<FormattedAsteroidDto> callAtmiraAPI(Integer days) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:8080/atmira/asteroids?days=" + (days != null ? days.toString() : "");
		FormattedAsteroidDto[] response = restTemplate.getForObject(uri, FormattedAsteroidDto[].class);

		return (List<FormattedAsteroidDto>)(Object) Arrays.asList(response);
	}

}
