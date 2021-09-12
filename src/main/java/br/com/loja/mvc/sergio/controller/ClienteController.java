package br.com.loja.mvc.sergio.controller;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.loja.mvc.sergio.model.Parcela;
import br.com.loja.mvc.sergio.model.Venda;
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

	final String REDIRECT_HOME_CLIENTE = "redirect:/cliente";

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

	@GetMapping("update")
	public String update(@RequestParam Long id, Model model){
		Optional<Cliente> client = clienteRepository.findById(id);
		model.addAttribute("cliente", client);
		return "cliente/update";
	}

	@PostMapping("saveUpdate")
	@Transactional
	public String saveUpdate(Cliente client){
		Cliente oldClient = (clienteRepository.findById(client.getId())).get();

		if(validIfSomethingUpdated(client, oldClient))
			return REDIRECT_HOME_CLIENTE;

		if(nameChanged(client, oldClient))
			oldClient.setNomeCliente(client.getNomeCliente());
		if(stateChanged(client, oldClient))
			oldClient.setEstado(client.getEstado());
		if (cityChanged(client, oldClient))
			oldClient.setCidade(client.getCidade());

		clienteRepository.save(oldClient);
		return REDIRECT_HOME_CLIENTE;
	}
	@PostMapping("delete")
	@Transactional
	public String delete(@RequestParam Long id){
		deleteCustomer(id);

		//TODO GUSTAVO criar uma página de feedback para usuário que foi removido com sucesso
		return REDIRECT_HOME_CLIENTE;
	}

	@GetMapping("formulario")
	public String formulario(RequisicaoNovaVenda requisicao) {
		return "cliente/formulario";
	}

	@PostMapping("novo")
	@Transactional
	public String novo(@Valid RequisicaoNovoCliente requisicao, BindingResult result) {
		//TODO GUSTAVO não pode inserir cliente quando um dos dados forem nulo
		if (result.hasErrors()) {
			return "cliente/formulario";
		}		

		Cliente cliente = requisicao.toCliente(requisicao);
		clienteRepository.save(cliente);		
		
		return "redirect:/venda/formulario";
	}

	private boolean validIfSomethingUpdated(Cliente client, Cliente oldClient){
		return oldClient.getNomeCliente().equals(client.getNomeCliente())
				&& oldClient.getEstado().equals(client.getEstado())
				&& oldClient.getCidade().equals(client.getCidade());
	}

	private boolean nameChanged(Cliente client, Cliente oldClient) {
		return !oldClient.getNomeCliente().equals(client.getNomeCliente());
	}

	private boolean stateChanged(Cliente client, Cliente oldClient) {
		return !oldClient.getEstado().equals(client.getEstado());
	}

	private boolean cityChanged(Cliente client, Cliente oldClient) {
		return !oldClient.getCidade().equals(client.getCidade());
	}

	private void deleteCustomer(Long id) {
		Cliente customer = (clienteRepository.findById(id)).get();
		List<Venda> sales = vendaRepository.findSalesByIdClient(customer.getId());

		for (Venda sale: sales) {
			List<Parcela> installments = parcelaRepository.findInstallmentsByVendaId(sale.getId());
			parcelaRepository.deleteAll(installments);
			vendaRepository.delete(sale);
		}
		clienteRepository.delete(customer);
	}
}
