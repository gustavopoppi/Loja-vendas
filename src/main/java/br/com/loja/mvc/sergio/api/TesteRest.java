package br.com.loja.mvc.sergio.api;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.loja.mvc.sergio.dto.HomeDto;

@RestController
@RequestMapping("/api")
public class TesteRest {

	@GetMapping("teste")
	public LocalDate testeHome(Integer mes) {
		
		LocalDate localDate = LocalDate.of(2021,mes,22);
		
		return localDate;
	}
}
