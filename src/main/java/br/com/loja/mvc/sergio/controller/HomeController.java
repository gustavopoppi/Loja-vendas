package br.com.loja.mvc.sergio.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import br.com.loja.mvc.sergio.comuns.StringExtensions;
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
	public String home(Model model){
		
		String dataPrimeiroDiaMes = StringExtensions.retornaPrimeiroDiaMes();
		String dataUltimoDiaMes = StringExtensions.retornaUltimoDiaMes(); 
		
		List<Venda> consultaTeste = vendaRepository.consultaTeste(dataPrimeiroDiaMes, dataUltimoDiaMes);																
		
		List<Cliente> usuariosVendaEmAberto = clienteRepository.findUsuarioVendasEmAberto(dataPrimeiroDiaMes, dataUltimoDiaMes);
		model.addAttribute("usuariosVendaEmAberto", usuariosVendaEmAberto);

		List<Double> valoresTotaisClientesEmAberto = clienteRepository.findValorTotalClientesEmAberto(dataPrimeiroDiaMes, dataUltimoDiaMes);
		model.addAttribute("valoresTotais", valoresTotaisClientesEmAberto);

		List<Long> CountTotalPorClienteEmAberto = clienteRepository.findCountTotalPorClienteEmAberto(dataPrimeiroDiaMes, dataUltimoDiaMes);		

		List<Venda> vendas = vendaRepository.findAllByJoin(dataPrimeiroDiaMes, dataUltimoDiaMes); // falta ordenar
		
		double valorTotalVendas = 0;
		for (Venda venda : vendas) {
			valorTotalVendas += venda.getValorTotal();
		}
		model.addAttribute("valorTotalVendas", valorTotalVendas);

		List<List<Venda>> listaVendasTeste = new ArrayList<>();
		
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
		model.addAttribute("vendas", listaVendasTeste);

		List<Parcela> parcelas = parcelaRepository.findAllByJoin(dataPrimeiroDiaMes, dataUltimoDiaMes);
		model.addAttribute("parcelas", parcelas);		
		
		model.addAttribute("localDate", LocalDate.now());		
		
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
