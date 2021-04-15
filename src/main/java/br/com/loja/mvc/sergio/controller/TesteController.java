package br.com.loja.mvc.sergio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teste")
public class TesteController {

	@GetMapping()
	public String teste(Model model){	
		
		return "teste";
	}
}
