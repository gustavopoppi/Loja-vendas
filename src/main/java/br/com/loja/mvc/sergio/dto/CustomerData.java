package br.com.loja.mvc.sergio.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CustomerData {
	
//	private Long id;

	@NotBlank
	private String estado;
	private String cidade;

	@NotBlank
	private String nomeCliente;
	private String sigla;
	private Integer qtdeComprasAtivas;
	private Integer qtdeTotalCompras;
	private Integer qtdeTotalComprasFinalizadas;
}
