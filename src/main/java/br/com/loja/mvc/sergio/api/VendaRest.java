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
import br.com.loja.mvc.sergio.model.Cliente;
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

		
		
		String dataPrimeiroDiaMes = StringExtensions.retornaPrimeiroDiaMes(Integer.toString(mes));
		String dataUltimoDiaMes = StringExtensions.retornaUltimoDiaMes(Integer.toString(mes));
		
		homeDto.setUsuariosVendaEmAberto(
				clienteRepository.findClienteVendaEmAberto(dataPrimeiroDiaMes, dataUltimoDiaMes));
		
		homeDto.setValorTotalVendaClientesEmAberto(
				clienteRepository.findValorTotalVendaPorCliente(dataPrimeiroDiaMes, dataUltimoDiaMes));

		homeDto.setValorTotalParcelaClientesEmAberto(
				clienteRepository.findValorTotalParcelaPorCliente(dataPrimeiroDiaMes, dataUltimoDiaMes));

		homeDto.setCountTotalPorClienteEmAberto(
				clienteRepository.findCountTotalPorClienteOrderByCliente(dataPrimeiroDiaMes, dataUltimoDiaMes));
		
		homeDto.setVendas(vendaRepository.findAllByJoin(dataPrimeiroDiaMes, dataUltimoDiaMes)); // falta ordenar

		Double valorTotalRecebido = parcelaRepository.findValorTotalParcelasPagas(dataPrimeiroDiaMes, dataUltimoDiaMes);
		
		if (valorTotalRecebido != null) {
			homeDto.setValorTotalRecebido(valorTotalRecebido);
		} else {
			homeDto.setValorTotalRecebido(0);
		}

		double valorTotalVendas = 0;
		double valorTotalParcelas = 0;
		for (Venda venda : homeDto.getVendas()) {
			valorTotalVendas += venda.getValorTotal();
		}
		homeDto.setValorTotalVendas(valorTotalVendas);

		homeDto.setParcelas(parcelaRepository.findAllParcelasOrderByClienteEDataParcela(dataPrimeiroDiaMes, dataUltimoDiaMes));

		for (Parcela parcela : homeDto.getParcelas()) {
			valorTotalParcelas += parcela.getValorParcela();
		}
		homeDto.setValorTotalParcelas(valorTotalParcelas);

		homeDto.setDataAtual(LocalDate.now());

		List<List<Parcela>> listaParcelas = new ArrayList<>();

		int indexParcela = 0;
		for (int i = 0; i < homeDto.getCountTotalPorClienteEmAberto().size(); i++) {
			List<Parcela> listaAuxiliarParcelas = new ArrayList<>();
			Long index = homeDto.getCountTotalPorClienteEmAberto().get(i);
			for (int j = 0; j < index; j++) {
				listaAuxiliarParcelas.add(homeDto.getParcelas().get(indexParcela));
				if (j + 1 >= index)
					listaParcelas.add(listaAuxiliarParcelas);
				indexParcela++;
			}
		}
		homeDto.setListaParcelas(listaParcelas);

		return homeDto;
	}
}
