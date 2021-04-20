package br.com.loja.mvc.sergio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nomeCliente;
	private String estado;
	private String cidade;
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
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getQtdeTotalCompras() {
		return qtdeTotalCompras;
	}
	public int getQtdeComprasAtivas() {
		return qtdeComprasAtivas;
	}
	public void setQtdeComprasAtivas(int qtdeComprasAtivas) {
		this.qtdeComprasAtivas = qtdeComprasAtivas;
	}
	public void setQtdeTotalCompras(int qtdeTotalCompras) {
		this.qtdeTotalCompras = qtdeTotalCompras;
	}
	public int getQtdeTotalComprasFinalizadas() {
		return qtdeTotalComprasFinalizadas;
	}
	public void setQtdeTotalComprasFinalizadas(int qtdeTotalComprasFinalizadas) {
		this.qtdeTotalComprasFinalizadas = qtdeTotalComprasFinalizadas;
	}
	public int getQtdeCompras() {
		return qtdeComprasAtivas;
	}
	public void setQtdeCompras(int qtdeCompras) {
		this.qtdeComprasAtivas = qtdeCompras;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
}
