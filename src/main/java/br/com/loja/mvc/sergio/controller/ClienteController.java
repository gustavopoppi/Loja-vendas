package br.com.loja.mvc.sergio.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.loja.mvc.sergio.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import br.com.loja.mvc.sergio.dto.CustomerData;
import br.com.loja.mvc.sergio.model.Cliente;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	final String REDIRECT_HOME_CUSTOMER = "redirect:/cliente";

	@Autowired
	CustomerService customerService;

	@GetMapping()
	public String clientes(Model model) {
		model.addAttribute("listaClientes", customerService.getAllCustomers());
		
		return "cliente/home";
	}
	
	@GetMapping("read/")
	public String read(@RequestParam Long id, Model model) {
		//TODO GUSTAVO caso digite um parâmetro de cliente que não existe, ele está dando um erro, corrigir isso (exemplo: http://localhost:8080/cliente/read/?id=777)
		model.addAttribute("cliente", customerService.getCustomerById(id));

		return "cliente/read";
	}

	@GetMapping("/update")
	public String update(@RequestParam Long id, Model model){
		model.addAttribute("cliente", customerService.getCustomerById(id));

		return "cliente/update";
	}

	@PostMapping("saveUpdate")
	@Transactional
	//TODO GUSTAVO alterar nome parâmetro client para customer, testar para ver se tenho que alterar nome no front também.
	public String saveUpdate(Cliente client){
		customerService.saveIfSomethingUpdated(client);

		return REDIRECT_HOME_CUSTOMER;
	}

	@PostMapping("delete")
	@Transactional
	public String delete(@RequestParam Long id){
		customerService.deleteInstallmentsSalesAndCustomer(id);

		//TODO GUSTAVO criar uma página de feedback para usuário que foi removido com sucesso
		return REDIRECT_HOME_CUSTOMER;
	}

	@GetMapping("formulario")
	public String formulario(CustomerData customerData) {
		return "cliente/formulario";
	}

	@PostMapping("novo")
	@Transactional
	public String novo(@Valid CustomerData customerData, BindingResult result) {
		//TODO GUSTAVO não pode inserir cliente quando um dos dados forem nulo
		if (result.hasErrors())
			return "cliente/formulario";

		customerService.createCustomer(customerData);

		return "redirect:/sale/form";
	}
}
