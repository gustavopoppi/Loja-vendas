package br.com.loja.mvc.sergio.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.loja.mvc.sergio.dto.HomeDto;
import br.com.loja.mvc.sergio.model.Cliente;
import br.com.loja.mvc.sergio.model.Parcela;
import br.com.loja.mvc.sergio.model.Venda;
import br.com.loja.mvc.sergio.repository.ClienteRepository;
import br.com.loja.mvc.sergio.repository.ParcelaRepository;
import br.com.loja.mvc.sergio.repository.VendaRepository;

import static br.com.loja.mvc.sergio.comuns.StringExtensions.dataCompletaComUltimoDiaDoMes;
import static br.com.loja.mvc.sergio.comuns.StringExtensions.dataCompletaComOPrimeiroDiaMes;

@RestController
//@Controller
@RequestMapping("/api")
public class VendaRest {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ParcelaRepository parcelaRepository;

	@GetMapping("home")
	public HomeDto vendasHome(Integer mes, Integer ano) {
		HomeDto homeDto = new HomeDto();
		
		setPrimeiroEUltimoDiaMes(mes, ano, homeDto);
		setUsuariosVendaEmAberto(homeDto);
		setValorTotalVenda(homeDto);
		setValorTotalParcela(homeDto);
		setCountTotalPorClienteAberto(homeDto);
		setVendas(homeDto);
		setValorTotalParcelaRecebido(homeDto);
		setValorTotalVendas(homeDto);
		setParcelas(homeDto);
		setValorTotalParcelas(homeDto);
		homeDto.setDataAtual(LocalDate.now());
		setMatrizParcelas(homeDto);

		return homeDto;
	}

	private void setMatrizParcelas(HomeDto homeDto) {
		List<List<Parcela>> matrizParcelas = new ArrayList<>();

		int indexParcela = 0;
		int qtdeTotalClienteEmAberto = homeDto.getCountTotalPorClienteEmAberto().size();
		for (int i = 0; i < qtdeTotalClienteEmAberto; i++) {
			List<Parcela> arrayParcelas = new ArrayList<>();
			Long index = homeDto.getCountTotalPorClienteEmAberto().get(i);
			
			for (int j = 0; j < index; j++) {
				arrayParcelas.add(homeDto.getParcelas().get(indexParcela));
				if (j + 1 >= index)
					matrizParcelas.add(arrayParcelas);
				indexParcela++;
			}
		}
		homeDto.setMatrizParcelas(matrizParcelas);		
	}

	private void setValorTotalParcelas(HomeDto homeDto) {
		double valorTotalParcelas = 0;
		for (Parcela parcela : homeDto.getParcelas()) {
			valorTotalParcelas += parcela.getValorParcela();
		}
		homeDto.setValorTotalParcelas(valorTotalParcelas);		
	}

	private void setParcelas(HomeDto homeDto) {
		homeDto.setParcelas(parcelaRepository.findAllParcelasOrderByClienteEDataParcela(homeDto.getPrimeiroDiaMes(), homeDto.getUltimoDiaMes()));		
	}

	private void setValorTotalVendas(HomeDto homeDto) {
		double valorTotalVendas = 0;
		for (Venda venda : homeDto.getVendas()) {
			valorTotalVendas += venda.getValorTotal();
		}
		homeDto.setValorTotalVendas(valorTotalVendas);		
	}

	private void setValorTotalParcelaRecebido(HomeDto homeDto) {
		Double valorTotalRecebido = parcelaRepository.findValorTotalParcelasPagas(homeDto.getPrimeiroDiaMes(), homeDto.getUltimoDiaMes());
		
		if (valorTotalRecebido != null) 
			homeDto.setValorTotalRecebido(valorTotalRecebido);
		else 
			homeDto.setValorTotalRecebido(0);		
	}

	private void setVendas(HomeDto homeDto) {
		List<Venda> vendas = vendaRepository.findAllVendasOrderById(homeDto.getPrimeiroDiaMes(), homeDto.getUltimoDiaMes());
		homeDto.setVendas(vendas);		
	}

	private void setCountTotalPorClienteAberto(HomeDto homeDto) {
		List<Long> countTotalPorClienteAberto = clienteRepository.findCountTotalPorClienteOrderByCliente(homeDto.getPrimeiroDiaMes(), homeDto.getUltimoDiaMes());
		homeDto.setCountTotalPorClienteEmAberto(countTotalPorClienteAberto);
	}

	private void setValorTotalParcela(HomeDto homeDto) {
		List<Double> valorTotalParcela = clienteRepository.findValorTotalParcelaPorCliente(homeDto.getPrimeiroDiaMes(), homeDto.getUltimoDiaMes());
		homeDto.setValorTotalParcelaClientesEmAberto(valorTotalParcela);				
	}
	
	private void setValorTotalVenda(HomeDto homeDto) {
		List<Double> valorTotalVenda = clienteRepository.findValorTotalVendaPorCliente(homeDto.getPrimeiroDiaMes(), homeDto.getUltimoDiaMes());
		homeDto.setValorTotalVendaClientesEmAberto(valorTotalVenda);
	}

	private void setPrimeiroEUltimoDiaMes(Integer mes, Integer ano, HomeDto homeDto) {
		String parseMesToString = Integer.toString(mes);
		String parseAnoToString = Integer.toString(ano);

		homeDto.setUltimoDiaMes(dataCompletaComUltimoDiaDoMes(parseMesToString, parseAnoToString));
		homeDto.setPrimeiroDiaMes(dataCompletaComOPrimeiroDiaMes(parseMesToString, parseAnoToString));
	}
	
	private void setUsuariosVendaEmAberto(HomeDto homeDto) {
		List<Cliente> clienteVendaEmAberto = clienteRepository.findClienteVendaEmAberto(homeDto.getPrimeiroDiaMes(), homeDto.getUltimoDiaMes());
		homeDto.setUsuariosVendaEmAberto(clienteVendaEmAberto);		
	}
}
