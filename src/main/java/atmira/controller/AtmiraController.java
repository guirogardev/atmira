package atmira.controller;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import atmira.dto.FormattedAsteroidDto;
import atmira.service.AtmiraService;

@Validated
@RestController
@RequestMapping("/atmira")
public class AtmiraController {

	@Autowired
	private AtmiraService service;
	
	@GetMapping(value = "/asteroids")
	public List<FormattedAsteroidDto> getWorstsAsteroids(@RequestParam @NotNull @Min(1) @Max(7) Integer days) {
        return this.service.getWorstsAsteroids(days);
    }
}
