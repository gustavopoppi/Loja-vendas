package br.com.loja.mvc.sergio.dto;

import java.util.Date;

public class RequisicaoNovaVenda {

	private String nomeCliente;
	private String nomeProduto;
	private double valorTotal;
	private int qtdeParcelas;
	private Date dataCompra;
	private Date inicioPagamento;
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public int getQtdeParcelas() {
		return qtdeParcelas;
	}
	public void setQtdeParcelas(int qtdeParcelas) {
		this.qtdeParcelas = qtdeParcelas;
	}
	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}
	public Date getInicioPagamento() {
		return inicioPagamento;
	}
	public void setInicioPagamento(Date inicioPagamento) {
		this.inicioPagamento = inicioPagamento;
	}
	
	
	
}
