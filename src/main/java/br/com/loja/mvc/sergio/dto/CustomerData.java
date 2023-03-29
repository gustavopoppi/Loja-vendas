package br.com.loja.mvc.sergio.dto;

import lombok.Data;

@Data
public class CustomerData {
	
//	private Long id;
	private String estado;
	private String cidade;
	private String nomeCliente;
	private String sigla;
	private Integer qtdeComprasAtivas;
	private Integer qtdeTotalCompras;
	private Integer qtdeTotalComprasFinalizadas;
}
