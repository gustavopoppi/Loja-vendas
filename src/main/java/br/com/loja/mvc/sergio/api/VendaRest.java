package br.com.loja.mvc.sergio.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
	public HomeDto testeHome(Model model, LocalDate data) {
		if (data == null)
			data = LocalDate.now();
		
		HomeDto homeDto = new HomeDto();

		String dataPrimeiroDiaMes = StringExtensions.retornaPrimeiroDiaMes();
		String dataUltimoDiaMes = StringExtensions.retornaUltimoDiaMes();

		homeDto.setConsultaTeste(vendaRepository.consultaTeste(dataPrimeiroDiaMes, dataUltimoDiaMes));
		homeDto.setUsuariosVendaEmAberto(clienteRepository.findUsuarioVendasEmAberto(dataPrimeiroDiaMes,
				dataUltimoDiaMes));
		homeDto.setValoresTotaisClientesEmAberto(clienteRepository
				.findValorTotalClientesEmAberto(dataPrimeiroDiaMes, dataUltimoDiaMes));
		homeDto.setCountTotalPorClienteEmAberto(clienteRepository.findCountTotalPorClienteEmAberto(dataPrimeiroDiaMes,
				dataUltimoDiaMes));
		homeDto.setVendas(vendaRepository.findAllByJoin(dataPrimeiroDiaMes, dataUltimoDiaMes)); // falta ordenar

		double valorTotalVendas = 0;
		for (Venda venda : homeDto.getVendas()) {
			valorTotalVendas += venda.getValorTotal();
		}
		homeDto.setValorTotalVendas(valorTotalVendas);

		List<List<Venda>> listaVendas = new ArrayList<>();

		int x = 0;
		for (int i = 0; i < homeDto.getCountTotalPorClienteEmAberto().size(); i++) {
			List<Venda> listaTeste = new ArrayList<>();
			Long index = homeDto.getCountTotalPorClienteEmAberto().get(i);
			for (int j = 0; j < index; j++) {

				Long testeCount = homeDto.getCountTotalPorClienteEmAberto().get(i);
				listaTeste.add(homeDto.getVendas().get(x));

				if (j + 1 >= index)
					listaVendas.add(listaTeste);

				x++;
			}
		}
		homeDto.setListaVendas(listaVendas);		
		homeDto.setParcelas(parcelaRepository.findAllByJoin(dataPrimeiroDiaMes, dataUltimoDiaMes));
		homeDto.setDataAtual(LocalDate.now());
		
		List<Integer> listaContador = new ArrayList<Integer>();
		for (int i = 0; i < homeDto.getParcelas().size(); i++) {
			listaContador.add(i);
		}
		homeDto.setContador(listaContador);			

		return homeDto;
	}
}
