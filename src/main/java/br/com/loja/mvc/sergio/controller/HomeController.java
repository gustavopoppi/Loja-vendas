package br.com.loja.mvc.sergio.controller;

import br.com.loja.mvc.sergio.dto.InstallmentData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/home")
public class HomeController {

	@GetMapping()
	public String home(InstallmentData installmentDataRequest) {
		return "home";
	}

	@GetMapping("/{status}")
	public String porStatus(@PathVariable("status") String status, Model model) {
//		List<Pedido> pedidos = repository.findAll();
//		model.addAttribute("pedidos", pedidos);
//		model.addAttribute("status", status);
		return "home";
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}
}
