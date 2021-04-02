package br.com.loja.mvc.sergio.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.loja.mvc.sergio.model.Cliente;
import br.com.loja.mvc.sergio.model.Parcela;
import br.com.loja.mvc.sergio.model.Venda;
import br.com.loja.mvc.sergio.repository.ClienteRepository;
import br.com.loja.mvc.sergio.repository.ParcelaRepository;
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
	
	@Autowired
	private ParcelaRepository parcelaRepository;	

	@GetMapping()
	public String home(Model model) {
//		List<Pedido> pedidos = repository.findAll();
//		model.addAttribute("pedidos", pedidos);

		List<Cliente> usuariosVendaEmAberto = clienteRepository.findUsuarioVendasEmAberto('N');
		model.addAttribute("usuariosVendaEmAberto", usuariosVendaEmAberto);

		List<Double> valoresTotaisClientesEmAberto = clienteRepository.findValorTotalClientesEmAberto();
		model.addAttribute("valoresTotais", valoresTotaisClientesEmAberto);

		List<Long> CountTotalPorClienteEmAberto = clienteRepository.findCountTotalPorClienteEmAberto();
		int teste2 = CountTotalPorClienteEmAberto.size();

		List<Venda> vendas = vendaRepository.findAllByJoin(); // falta ordenar
//		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.ENTREGUE, paginacao);
//		model.addAttribute("vendas", vendas);

		List<List<Venda>> listaVendasTeste = new ArrayList<>();

//		int x = 0;
//		while (x < CountTotalPorClienteEmAberto.size()) {

		
		int x = 0;
		for (int i = 0; i < CountTotalPorClienteEmAberto.size(); i++) {
			List<Venda> listaTeste = new ArrayList<>();
			Long index = CountTotalPorClienteEmAberto.get(i);
			for (int j = 0; j < index; j++) {

				Long testeCount = CountTotalPorClienteEmAberto.get(i);
				listaTeste.add(vendas.get(x));
				
				if (j+1>=index) 
					listaVendasTeste.add(listaTeste);
				
				x++;
			}
			
		}
		
//			x++;
//		}

		model.addAttribute("vendas", listaVendasTeste);

		List<Parcela> parcelas = parcelaRepository.findAllByJoin();
		model.addAttribute("parcelas", parcelas);
		
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
