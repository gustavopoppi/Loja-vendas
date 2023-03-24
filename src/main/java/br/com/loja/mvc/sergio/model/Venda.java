package br.com.loja.mvc.sergio.model;

import br.com.loja.mvc.sergio.dto.NewSaleData;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.text.ParseException;

import static br.com.loja.mvc.sergio.comuns.StringExtensions.formatarDataVindoAoContrario;

@Entity
@Data
@NoArgsConstructor
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

	public Venda(NewSaleData newSaleRequest, Cliente customer) throws ParseException {
		this.nomeProduto = newSaleRequest.getNomeProduto();
		this.valorTotal = newSaleRequest.getValorTotal();
		this.qtdeParcelas = newSaleRequest.getQtdeParcelas();
		this.dataCompra = formatarDataVindoAoContrario(newSaleRequest.getDataCompra());;
		this.inicioPagamento = formatarDataVindoAoContrario(newSaleRequest.getInicioPagamento());
		this.foiPaga = 'N';
		setCustomerAndIncrementAmountActivePurchasesAndTotal(customer);
	}

	//	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY) // -> uma venda pode ter uma ou muitas parcelas
//	private Parcela parcela;

	private void setCustomerAndIncrementAmountActivePurchasesAndTotal(Cliente customer) {
		incrementAmountActivePurchasesAndTotal(customer);
		this.cliente = customer;
	}

	private void incrementAmountActivePurchasesAndTotal(Cliente customer) {
		int incrementActivePurchases = customer.getQtdeComprasAtivas() + 1;
		customer.setQtdeComprasAtivas(incrementActivePurchases);
		customer.setQtdeTotalCompras(incrementActivePurchases);
	}
}
