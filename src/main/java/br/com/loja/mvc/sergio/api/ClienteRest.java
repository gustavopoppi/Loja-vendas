package br.com.loja.mvc.sergio.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.loja.mvc.sergio.model.Cliente;
import br.com.loja.mvc.sergio.repository.ClienteRepository;

@RestController
@RequestMapping("/api")
public class ClienteRest {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("clientes")
	public List<Cliente> obterTodosClientes(Integer mes) {		
		return clienteRepository.findAll();
	}
}
