package atmira.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import atmira.dto.AsteroidDto;
import atmira.service.AtmiraService;

@RestController
@RequestMapping("/atmira")
public class AtmiraController {

	@Autowired
	private AtmiraService service;
	
	@GetMapping(value = "/asteroids")
    public List<AsteroidDto> getWorstsAsteroids(@RequestParam(required = true) Integer days) {
        return this.service.getWorstsAsteroids(days);
    }
}
