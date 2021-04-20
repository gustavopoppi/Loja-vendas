package br.com.loja.mvc.sergio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import br.com.loja.mvc.sergio.comuns.StringExtensions;
import br.com.loja.mvc.sergio.dto.RequisicaoNovaParcela;
import br.com.loja.mvc.sergio.model.Parcela;
import br.com.loja.mvc.sergio.model.StatusParcela;
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
		return "home";
	}

	@PostMapping("pagamento")
	@Transactional
	public String setBaixaParcela(@Valid RequisicaoNovaParcela requisicao, BindingResult result) {
		if (result.hasErrors()) {
			return "home";
		}
		
		Long idVenda = requisicao.getIdVenda();
		Parcela parcela = obterParcelaRequisicao(requisicao);		
		
		boolean entrarIf = true;
		if (parcela.getStatus() == StatusParcela.AGUARDANDO && entrarIf == true) {
			
			// criar um método para cada atividade necessária quando uma parcela é paga
			
			parcela.setValorPago(requisicao.getValorPago());

			StatusParcela statusParcela = requisicao.getValorPago() >= parcela.getValorParcela() ? StatusParcela.PAGA
					: StatusParcela.PARCIAL;
			parcela.setStatus(statusParcela);

			Integer qtdeComprasAtivas = parcela.getVenda().getCliente().getQtdeComprasAtivas();
			parcela.getVenda().getCliente().setQtdeComprasAtivas((qtdeComprasAtivas - 1));
			parcela.setDataPagamentoParcela(StringExtensions.dataDeHoje());			
			parcela.setAtiva('N');
			
			List<Parcela> listaParcelasEmAberto = parcelaRepository.findParcelaAbertaParaIdVenda(idVenda);
			if (listaParcelasEmAberto.size() == 0) {
				// update na venda setando o campo "foiPago" para 'S'
				
				Venda venda = vendaRepository.findByIdModificado(idVenda);				
				venda.setFoiPaga('S');
				vendaRepository.save(venda);
			}			
			
			parcelaRepository.save(parcela);
		}

		return "redirect:/home";
	}

	private Parcela obterParcelaRequisicao(@Valid RequisicaoNovaParcela requisicao) {
		List<Parcela> listaParcelasComIdVenda = parcelaRepository.findParcelasByIdVenda(requisicao.getIdVenda());
		
		return listaParcelasComIdVenda.get(0);
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
