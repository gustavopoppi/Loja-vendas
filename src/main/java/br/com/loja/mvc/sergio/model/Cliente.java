package br.com.loja.mvc.sergio.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Cliente {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nomeCliente;
	private String estado;
	private String cidade;
	private String sigla;
	private int qtdeComprasAtivas;
	private int qtdeTotalCompras;
	private int qtdeTotalComprasFinalizadas;

//	@ManyToOne(fetch = FetchType.LAZY)
//	private Setor setor;
	
	public Cliente() {
		this.qtdeComprasAtivas = 0;
		this.qtdeTotalCompras = 0;
		this.qtdeTotalComprasFinalizadas = 0;
	}
}
