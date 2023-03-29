package br.com.loja.mvc.sergio.dto;


import lombok.Data;

@Data
public class InstallmentData {
	
	private Long idVenda;
	private Long idParcela;
	private String dataParcela;
	private String dataPagamento;
	private double valorParcela;
	private double valorPago;
	private boolean ativa;
	private int numeroDaParcela;

}
