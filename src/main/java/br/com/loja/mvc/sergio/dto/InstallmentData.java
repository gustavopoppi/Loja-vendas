package br.com.loja.mvc.sergio.dto;


import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
public class InstallmentData {
	
	private Long idVenda;
	private Long idParcela;
	private String dataParcela;
	private String dataPagamento;
	private double valorParcela;

	@NotNull
	@DecimalMin("0.01")
	private double valorPago;
	private boolean ativa;
	private int numeroDaParcela;

}
