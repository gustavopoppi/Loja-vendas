package br.com.loja.mvc.sergio.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Venda {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nomeProduto;
	private double valorTotal;
	private int qtdeParcelas;
	private String dataCompra;
	private String inicioPagamento;
	private char foiPaga;
	
	@ManyToOne(cascade = CascadeType.ALL) //um cliente pode ter uma ou muitas vendas
	@Setter(value = AccessLevel.NONE)
	private Cliente cliente;
	
//	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY) // -> uma venda pode ter uma ou muitas parcelas
//	private Parcela parcela;

	public void setClienteEIncrementaQtdeComprasAtivasETotal(Cliente cliente) {
		IncrementaQtdeComprasAtivasETotal(cliente);
		this.cliente = cliente;
	}

	private void IncrementaQtdeComprasAtivasETotal(Cliente cliente) {
		int qtdeCompras = cliente.getQtdeComprasAtivas() + 1;
		cliente.setQtdeComprasAtivas(qtdeCompras);
		cliente.setQtdeTotalCompras(qtdeCompras);
	}
}
