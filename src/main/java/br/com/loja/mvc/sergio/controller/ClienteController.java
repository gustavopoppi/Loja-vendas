package br.com.loja.mvc.sergio.controller;

import java.text.ParseException;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.loja.mvc.sergio.dto.RequisicaoNovaVenda;
import br.com.loja.mvc.sergio.dto.RequisicaoNovoCliente;
import br.com.loja.mvc.sergio.model.Cliente;
import br.com.loja.mvc.sergio.model.Venda;
import br.com.loja.mvc.sergio.repository.ClienteRepository;
import br.com.loja.mvc.sergio.repository.ParcelaRepository;
import br.com.loja.mvc.sergio.repository.VendaRepository;

@Controller
@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ParcelaRepository parcelaRepository;
	
	@GetMapping("formulario")
	public String formulario(RequisicaoNovaVenda requisicao) {
		return "cliente/formulario";
	}

	@PostMapping("novo")
	@Transactional
	public String novo(@Valid RequisicaoNovoCliente requisicao, BindingResult result) throws ParseException {
		if (result.hasErrors()) {
			return "cliente/formulario";
		}		
				
		Cliente cliente = requisicao.toCliente(requisicao);
		clienteRepository.save(cliente);		
		
		return "redirect:/venda/formulario";
	}
}
