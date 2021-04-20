package br.com.loja.mvc.sergio.api;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.loja.mvc.sergio.comuns.StringExtensions;
import br.com.loja.mvc.sergio.dto.HomeDto;
import br.com.loja.mvc.sergio.model.Parcela;
import br.com.loja.mvc.sergio.model.Venda;
import br.com.loja.mvc.sergio.repository.ClienteRepository;
import br.com.loja.mvc.sergio.repository.ParcelaRepository;
import br.com.loja.mvc.sergio.repository.VendaRepository;

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
	public HomeDto vendasHome(Integer mes) {
//			data = LocalDate.now().toString();
		
		HomeDto homeDto = new HomeDto();
		
		//mes++;
		String dataPrimeiroDiaMes = StringExtensions.retornaPrimeiroDiaMes(Integer.toString(mes));
		String dataUltimoDiaMes = StringExtensions.retornaUltimoDiaMes(Integer.toString(mes));

		Object teste = clienteRepository.findUsuarioVendasEmAberto(dataPrimeiroDiaMes,
				dataUltimoDiaMes);
		homeDto.setUsuariosVendaEmAberto(clienteRepository.findUsuarioVendasEmAberto(dataPrimeiroDiaMes,
				dataUltimoDiaMes));
		homeDto.setValoresTotaisVendaClientesEmAberto(clienteRepository
				.findValorTotalClientesEmAberto(dataPrimeiroDiaMes, dataUltimoDiaMes));
		
		List<Double> remover = clienteRepository.findValorTotalParcelaClientesEmAberto(dataPrimeiroDiaMes, dataUltimoDiaMes);
		homeDto.setValoresTotaisParcelaClientesEmAberto(remover);	
		
		List<Long> teste2 = clienteRepository.findCountTotalPorClienteEmAberto(dataPrimeiroDiaMes, dataUltimoDiaMes);
		homeDto.setCountTotalPorClienteEmAberto(clienteRepository.findCountTotalPorClienteEmAberto(dataPrimeiroDiaMes,
				dataUltimoDiaMes));
		homeDto.setVendas(vendaRepository.findAllByJoin(dataPrimeiroDiaMes, dataUltimoDiaMes)); // falta ordenar	
		
		Double valorTotalRecebido = parcelaRepository.findValorTotalRecebido(dataPrimeiroDiaMes, dataUltimoDiaMes);
		if (valorTotalRecebido != null) {
			homeDto.setValorTotalRecebido(valorTotalRecebido);
		}else {
			homeDto.setValorTotalRecebido(0);
		}	
		
		
		double valorTotalVendas = 0;
		double valorTotalParcelas = 0;
		for (Venda venda : homeDto.getVendas()) {
			valorTotalVendas += venda.getValorTotal();			
		}
		homeDto.setValorTotalVendas(valorTotalVendas);
		
		
		
		List<Parcela> vmtesta = parcelaRepository.findAllByJoin(dataPrimeiroDiaMes, dataUltimoDiaMes);
		homeDto.setParcelas(parcelaRepository.findAllByJoin(dataPrimeiroDiaMes, dataUltimoDiaMes));
		for (Parcela parcela: homeDto.getParcelas()) {
			valorTotalParcelas += parcela.getValorParcela();
		}
		homeDto.setValorTotalParcelas(valorTotalParcelas);
		
		homeDto.setDataAtual(LocalDate.now());			
		
		List<List<Parcela>> listaParcelas = new ArrayList<>();

		int x = 0;
		int testeCount = homeDto.getCountTotalPorClienteEmAberto().size();
		for (int i = 0; i < homeDto.getCountTotalPorClienteEmAberto().size(); i++) {
			List<Parcela> listaAuxiliarParcelas = new ArrayList<>();
			Long index = homeDto.getCountTotalPorClienteEmAberto().get(i);
			for (int j = 0; j < index; j++) {
				List<Parcela> testaLista = listaAuxiliarParcelas;
				listaAuxiliarParcelas.add(homeDto.getParcelas().get(x));
				if (j + 1 >= index)
					listaParcelas.add(listaAuxiliarParcelas);
				x++;
			}
		}
		homeDto.setListaParcelas(listaParcelas);	

		return homeDto;
	}
}



