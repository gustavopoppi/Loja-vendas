package br.com.loja.mvc.sergio.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.loja.mvc.sergio.model.Cliente;
import br.com.loja.mvc.sergio.model.Parcela;
import br.com.loja.mvc.sergio.model.Venda;
import lombok.Data;

//TODO GUSTAVO implementar builder
@Data
public class HomeDto {

	private List<Cliente> usuariosVendaEmAberto = new ArrayList<>();
	private List<Double> valorTotalVendaClientesEmAberto = new ArrayList<>();
	private List<Double> valorTotalParcelaClientesEmAberto = new ArrayList<>();
	private List<Long> countTotalPorClienteEmAberto = new ArrayList<>();
	private List<Venda> vendas = new ArrayList<>();
	private List<Parcela> parcelas = new ArrayList<>();
//	private List<List<Venda>> listaVendas = new ArrayList<>();
	private List<List<Parcela>> matrizParcelas = new ArrayList<>();
	private LocalDate dataAtual;
	private double valorTotalVendas;
	private double valorTotalParcelas;
	private double ValorTotalRecebido;
	private String primeiroDiaMes;
	private String ultimoDiaMes;
}
