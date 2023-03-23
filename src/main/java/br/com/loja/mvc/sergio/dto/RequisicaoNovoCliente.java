package br.com.loja.mvc.sergio.dto;

import javax.validation.Valid;

import br.com.loja.mvc.sergio.model.Cliente;
import lombok.Data;

@Data
public class RequisicaoNovoCliente {
	
//	private Long id;
	private String estado;
	private String cidade;
	private String nomeCliente;
	private String sigla;
	private Integer qtdeComprasAtivas;
	private Integer qtdeTotalCompras;
	private Integer qtdeTotalComprasFinalizadas;
	
	public Cliente toCliente(@Valid RequisicaoNovoCliente requisicao) {
		Cliente cliente = new Cliente();
		
		cliente.setCidade(requisicao.getCidade());
		cliente.setEstado(requisicao.getEstado());
		cliente.setNomeCliente(requisicao.getNomeCliente());
		cliente.setSigla(requisicao.getSigla());
		
		return cliente;
	}	
}
