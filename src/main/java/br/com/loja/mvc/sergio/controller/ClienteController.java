package br.com.loja.mvc.sergio.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.loja.mvc.sergio.dto.RequisicaoNovaVenda;
import br.com.loja.mvc.sergio.dto.RequisicaoNovoCliente;
import br.com.loja.mvc.sergio.model.Cliente;
import br.com.loja.mvc.sergio.repository.ClienteRepository;
import br.com.loja.mvc.sergio.repository.ParcelaRepository;
import br.com.loja.mvc.sergio.repository.VendaRepository;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ParcelaRepository parcelaRepository;
	
	@GetMapping()
	public String clientes(Model model) {				
		
		List<Cliente> listaClientes = clienteRepository.findAll();		
		model.addAttribute("listaClientes", listaClientes);
		
		return "cliente/home";
	}
	
	@GetMapping("read/")
	public String read(@RequestParam Long id, Model model) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		model.addAttribute("cliente", cliente);

		return "cliente/read";
	}
	
	@GetMapping("formulario")
	public String formulario(RequisicaoNovaVenda requisicao) {
		return "cliente/formulario";
	}

	@PostMapping("novo")
	@Transactional
	public String novo(@Valid RequisicaoNovoCliente requisicao, BindingResult result) {
		//TODO GUSTAVO não pode incluir quando um dos dados forem nulo
		if (result.hasErrors()) {
			return "cliente/formulario";
		}		

		Cliente cliente = requisicao.toCliente(requisicao);
		clienteRepository.save(cliente);		
		
		return "redirect:/venda/formulario";
	}
}
