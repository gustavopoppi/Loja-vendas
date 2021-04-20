package br.com.loja.mvc.sergio.dto;

import javax.validation.Valid;

import br.com.loja.mvc.sergio.model.Cliente;
import br.com.loja.mvc.sergio.model.Parcela;

public class RequisicaoNovoCliente {
	
//	private Long id;
	private String estado;
	private String cidade;
	private String nomeCliente;
	private Integer qtdeComprasAtivas;
	private Integer qtdeTotalCompras;
	private Integer qtdeTotalComprasFinalizadas;
	
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
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
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public Cliente toCliente(@Valid RequisicaoNovoCliente requisicao) {
		Cliente cliente = new Cliente();
		
		cliente.setCidade(requisicao.getCidade());
		cliente.setEstado(requisicao.getEstado());
		cliente.setNomeCliente(requisicao.getNomeCliente());
		
		return cliente;
	}	
}
