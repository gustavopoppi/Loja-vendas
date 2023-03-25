package br.com.loja.mvc.sergio.controller;

import java.text.ParseException;

import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.loja.mvc.sergio.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.loja.mvc.sergio.dto.NewSaleData;

@Controller
@RequestMapping("/sale")
public class SaleController {

	@Autowired
	private SaleService saleService;

	@GetMapping("/form")
	public String formulario() {
		return "venda/formulario";
	}

	@PostMapping("/new")
	@Transactional
	public String novo(@Valid NewSaleData newSaleRequest, BindingResult result) throws ParseException {
		if (result.hasErrors())
			return "venda/formulario";

		saleService.createSaleAndInstallment(newSaleRequest);

		return "redirect:/home";
	}
}
