package br.com.loja.mvc.sergio.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Venda {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//private String nomeCliente;
	private String nomeProduto;
	private double valorTotal;
	private int qtdeParcelas;
	private String dataCompra;
	private String inicioPagamento;
	private char foiPaga;
	
	@ManyToOne(cascade = CascadeType.ALL) //um cliente pode ter uma ou muitas vendas
	private Cliente cliente;
	
//	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY) // -> uma venda pode ter uma ou muitas parcelas
//	private Parcela parcela;
	
	public void setFoiPaga(char foiPaga) {
		this.foiPaga = foiPaga;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public char getFoiPaga() {
		return foiPaga;
	}

	public void setCliente(Cliente cliente) {
		int qtdeCompras = cliente.getQtdeCompras() + 1;
		cliente.setQtdeCompras(qtdeCompras);
		cliente.setQtdeTotalCompras(qtdeCompras);
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}

	public String getInicioPagamento() {
		return inicioPagamento;
	}

	public void setInicioPagamento(String inicioPagamento) {
		this.inicioPagamento = inicioPagamento;
	}
}
