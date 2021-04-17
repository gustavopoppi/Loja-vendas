package br.com.loja.mvc.sergio.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.loja.mvc.sergio.dto.RequisicaoNovaParcela;
import br.com.loja.mvc.sergio.model.Parcela;
import br.com.loja.mvc.sergio.model.StatusParcela;
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
	public String home(@Valid RequisicaoNovaParcela requisicao, Model model){
		return "home";
	}

	@GetMapping("pagamento")
	@Transactional
	public String setBaixaParcela(@Valid RequisicaoNovaParcela requisicao, BindingResult result) {
		if (result.hasErrors()) {
			return "home";
		}
		
		// pegueui a parcela de acordo com o código passado na requisição
		Parcela parcela = parcelaRepository.findById(requisicao.getId());
		Integer qtdeComprasAtivas = parcela.getVenda().getCliente().getQtdeComprasAtivas();
		
		parcela.setValorPago(requisicao.getValorPago());
		
		StatusParcela statusParcela = requisicao.getValorPago() >= parcela.getValorParcela() ? StatusParcela.PAGA : StatusParcela.PARCIAL;
		parcela.setStatus(statusParcela);
		
		parcela.getVenda().getCliente().setQtdeComprasAtivas((qtdeComprasAtivas-1));
		
		parcelaRepository.save(parcela);
		
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
