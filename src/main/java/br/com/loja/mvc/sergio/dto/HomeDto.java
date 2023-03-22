package br.com.loja.mvc.sergio.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.loja.mvc.sergio.model.Cliente;
import br.com.loja.mvc.sergio.model.Parcela;
import br.com.loja.mvc.sergio.model.Venda;

public class HomeDto {

	private List<Cliente> usuariosVendaEmAberto = new ArrayList<>();//
	private List<Double> valorTotalVendaClientesEmAberto = new ArrayList<>();
	private List<Double> valorTotalParcelaClientesEmAberto = new ArrayList<>();
	private List<Long> countTotalPorClienteEmAberto = new ArrayList<>();//
	private List<Venda> vendas = new ArrayList<>();//
	private List<Parcela> parcelas = new ArrayList<>();	 	//													
//	private List<List<Venda>> listaVendas = new ArrayList<>();
	private List<List<Parcela>> matrizParcelas = new ArrayList<>();
	private LocalDate dataAtual;//
	private double valorTotalVendas;//
	private double valorTotalParcelas;
	private double ValorTotalRecebido;
	private String primeiroDiaMes;
	private String ultimoDiaMes;
	
	public String getPrimeiroDiaMes() {
		return primeiroDiaMes;
	}

	public void setPrimeiroDiaMes(String primeiroDiaMes) {
		this.primeiroDiaMes = primeiroDiaMes;
	}

	public String getUltimoDiaMes() {
		return ultimoDiaMes;
	}

	public void setUltimoDiaMes(String ultimoDiaMes) {
		this.ultimoDiaMes = ultimoDiaMes;
	}

	public double getValorTotalRecebido() {
		return ValorTotalRecebido;
	}

	public void setValorTotalRecebido(double valorTotalRecebido) {
		ValorTotalRecebido = valorTotalRecebido;
	}

	public double getValorTotalParcelas() {
		return valorTotalParcelas;
	}

	public void setValorTotalParcelas(double valorTotalParcelas) {
		this.valorTotalParcelas = valorTotalParcelas;
	}

	public List<Double> getValorTotalParcelaClientesEmAberto() {
		return valorTotalParcelaClientesEmAberto;
	}

	public void setValorTotalParcelaClientesEmAberto(List<Double> valorTotalParcelaClientesEmAberto) {
		this.valorTotalParcelaClientesEmAberto = valorTotalParcelaClientesEmAberto;
	}

	public List<Parcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public List<List<Parcela>> getMatrizParcelas() {
		return matrizParcelas;
	}

	public void setMatrizParcelas(List<List<Parcela>> matrizParcelas) {
		this.matrizParcelas = matrizParcelas;
	}

	public LocalDate getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(LocalDate dataAtual) {
		this.dataAtual = dataAtual;
	}

	public double getValorTotalVendas() {
		return valorTotalVendas;
	}

	public void setValorTotalVendas(double valorTotalVendas) {
		this.valorTotalVendas = valorTotalVendas;
	}

	public List<Cliente> getUsuariosVendaEmAberto() {
		return usuariosVendaEmAberto;
	}

	public void setUsuariosVendaEmAberto(List<Cliente> usuariosVendaEmAberto) {
		this.usuariosVendaEmAberto = usuariosVendaEmAberto;
	}

	public List<Double> getValorTotalVendaClientesEmAberto() {
		return valorTotalVendaClientesEmAberto;
	}

	public void setValorTotalVendaClientesEmAberto(List<Double> valorTotalVendaClientesEmAberto) {
		this.valorTotalVendaClientesEmAberto = valorTotalVendaClientesEmAberto;
	}

	public List<Long> getCountTotalPorClienteEmAberto() {
		return countTotalPorClienteEmAberto;
	}

	public void setCountTotalPorClienteEmAberto(List<Long> countTotalPorClienteEmAberto) {
		this.countTotalPorClienteEmAberto = countTotalPorClienteEmAberto;
	}
}
