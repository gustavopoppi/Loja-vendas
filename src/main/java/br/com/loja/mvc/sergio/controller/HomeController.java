package br.com.loja.mvc.sergio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.loja.mvc.sergio.model.Cliente;
import br.com.loja.mvc.sergio.model.Venda;
import br.com.loja.mvc.sergio.repository.ClienteRepository;
import br.com.loja.mvc.sergio.repository.VendaRepository;

@Controller
@RequestMapping("/home")
public class HomeController {
	
//	@Autowired
//	private PedidoRepository repository;
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping()
	public String home(Model model) {
//		List<Pedido> pedidos = repository.findAll();
//		model.addAttribute("pedidos", pedidos);
		
		List<Cliente> usuariosVendaEmAberto = clienteRepository.findUsuarioVendasEmAberto('N');
		model.addAttribute("usuariosVendaEmAberto", usuariosVendaEmAberto);
		
		List<Double> valoresTotaisClientesEmAberto = clienteRepository.findValorTotalClientesEmAberto();
		model.addAttribute("valoresTotais", valoresTotaisClientesEmAberto);
		
		List<Venda> vendas = vendaRepository.findAll();
//		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.ENTREGUE, paginacao);
		model.addAttribute("vendas", vendas);
		
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
