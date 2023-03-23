package br.com.loja.mvc.sergio.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Parcela {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String dataParcela;
	private String dataPagamentoParcela;
	private double valorParcela;
	private double valorPago;
	private char ativa;
	private int parcela; //-> referente a qual parcela
	
	@Enumerated(EnumType.STRING)
	private StatusParcela status;
	
	@ManyToOne(cascade = CascadeType.ALL/* , fetch = FetchType.LAZY*/) // varias parcelas para uma venda
	private Venda venda;
	
	public StatusParcela getStatus() {
		return status;
	}

	public void setStatus(StatusParcela status) {
		this.status = status;
	}

	public char getAtiva() {
		return ativa;
	}

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

	public String getDataPagamentoParcela() {
		return dataPagamentoParcela;
	}

	public void setDataPagamentoParcela(String dataPagamento) {
		this.dataPagamentoParcela = dataPagamento;
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
