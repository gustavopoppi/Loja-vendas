package br.com.loja.mvc.sergio.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SaleData {

	private long idCliente;

	@NotBlank
	private String nomeCliente;

	@NotBlank
	private String nomeProduto;

	@NotNull
	@DecimalMin("0.01")
	private double valorTotal;

	@NotNull
	@Min(2)
	private int qtdeParcelas;

	@NotBlank
	private String dataCompra;

	@NotBlank
	private String inicioPagamento;
}
