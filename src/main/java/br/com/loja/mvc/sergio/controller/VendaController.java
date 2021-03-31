package br.com.loja.mvc.sergio.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.loja.mvc.sergio.dto.RequisicaoNovaVenda;
import br.com.loja.mvc.sergio.model.Parcela;
import br.com.loja.mvc.sergio.model.Venda;
import br.com.loja.mvc.sergio.repository.ParcelaRepository;
import br.com.loja.mvc.sergio.repository.VendaRepository;

@Controller
@RequestMapping("venda")
public class VendaController {

	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ParcelaRepository parcelaRepository;
	
	@GetMapping("formulario")
	public String formulario(RequisicaoNovaVenda requisicao) {
		return "venda/formulario";
	}

	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovaVenda requisicao, BindingResult result) throws ParseException {
		if (result.hasErrors()) {
			return "venda/formulario";
		}
		
		Venda venda = requisicao.toVenda();		
		vendaRepository.save(venda);
		
		Parcela parcela = requisicao.toParcela(venda, parcelaRepository);
		//parcelaRepository.save(parcela);		

		return "redirect:/home";
	}
}
