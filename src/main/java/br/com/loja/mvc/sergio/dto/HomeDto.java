package br.com.loja.mvc.sergio.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.loja.mvc.sergio.model.Cliente;
import br.com.loja.mvc.sergio.model.Parcela;
import br.com.loja.mvc.sergio.model.Venda;

public class HomeDto {

	private List<Venda> consultaTeste = new ArrayList<>();
	private List<Cliente> usuariosVendaEmAberto = new ArrayList<>();
	private List<Double> valoresTotaisClientesEmAberto = new ArrayList<>();
	private List<Long> CountTotalPorClienteEmAberto = new ArrayList<>();
	private List<Venda> vendas = new ArrayList<>();
	private List<Parcela> parcelas = new ArrayList<>();	 														
	private List<List<Venda>> listaVendas = new ArrayList<>();
	private LocalDate dataAtual;
	private List<Integer> contadorParcelas = new ArrayList<>();
	
	public List<Integer> getContador() {
		return contadorParcelas;
	}
	public void setContador(List<Integer> contador) {
		this.contadorParcelas = contador;
	}
	public LocalDate getDataAtual() {
		return dataAtual;
	}
	public void setDataAtual(LocalDate dataAtual) {
		this.dataAtual = dataAtual;
	}
	public List<List<Venda>> getListaVendas() {
		return listaVendas;
	}
	public void setListaVendas(List<List<Venda>> listaVendas) {
		this.listaVendas = listaVendas;
	}
	private double valorTotalVendas;
	
	public double getValorTotalVendas() {
		return valorTotalVendas;
	}
	public void setValorTotalVendas(double valorTotalVendas) {
		this.valorTotalVendas = valorTotalVendas;
	}
	public List<Venda> getConsultaTeste() {
		return consultaTeste;
	}
	public void setConsultaTeste(List<Venda> consultaTeste) {
		this.consultaTeste = consultaTeste;
	}
	public List<Cliente> getUsuariosVendaEmAberto() {
		return usuariosVendaEmAberto;
	}
	public void setUsuariosVendaEmAberto(List<Cliente> usuariosVendaEmAberto) {
		this.usuariosVendaEmAberto = usuariosVendaEmAberto;
	}
	public List<Double> getValoresTotaisClientesEmAberto() {
		return valoresTotaisClientesEmAberto;
	}
	public void setValoresTotaisClientesEmAberto(List<Double> valoresTotaisClientesEmAberto) {
		this.valoresTotaisClientesEmAberto = valoresTotaisClientesEmAberto;
	}
	public List<Long> getCountTotalPorClienteEmAberto() {
		return CountTotalPorClienteEmAberto;
	}
	public void setCountTotalPorClienteEmAberto(List<Long> countTotalPorClienteEmAberto) {
		CountTotalPorClienteEmAberto = countTotalPorClienteEmAberto;
	}
	public List<Venda> getVendas() {
		return vendas;
	}
	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}
	public List<Parcela> getParcelas() {
		return parcelas;
	}
	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}
}
