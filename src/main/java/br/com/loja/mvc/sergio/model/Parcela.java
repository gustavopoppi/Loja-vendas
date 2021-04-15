package br.com.loja.mvc.sergio.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Parcela {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String dataParcela;
	private String dataPagamento;
	private double valorParcela;
	private double valorPago;
	private char ativa;
	private int parcela; //-> referente a qual parcela
	
	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY) // varias parcelas para uma venda
	private Venda venda;

	
	public int getParcela() {
		return parcela;
	}
	public void setParcela(int parcela) {
		this.parcela = parcela;
	}
	public String getDataParcela() {
		return dataParcela;
	}
	public void setDataParcela(String dataParcela) {
		this.dataParcela = dataParcela;
	}
	public String getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public double getValorParcela() {
		return valorParcela;
	}
	public void setValorParcela(double valorParcela) {
		this.valorParcela = valorParcela;
	}
	public double getValorPago() {
		return valorPago;
	}
	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}
	public char isAtiva() {
		return ativa;
	}
	public void setAtiva(char ativa) {
		this.ativa = ativa;
	}	
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
}
