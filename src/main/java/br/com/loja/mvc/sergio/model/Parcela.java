package br.com.loja.mvc.sergio.model;

import java.util.Date;

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
	
	private Date dataParcela;
	private Date dataPagamento;
	private double valorParcela;
	private double valorPago;
	private boolean ativa;
	private int parcela; //-> referente a qual parcela
	
	@ManyToOne(fetch = FetchType.LAZY) // varias parcelas para uma venda
	private Venda venda;

	
	public int getParcela() {
		return parcela;
	}
	public void setParcela(int parcela) {
		this.parcela = parcela;
	}
	public Date getDataParcela() {
		return dataParcela;
	}
	public void setDataParcela(Date dataParcela) {
		this.dataParcela = dataParcela;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
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
	public boolean isAtiva() {
		return ativa;
	}
	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}	
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
}
