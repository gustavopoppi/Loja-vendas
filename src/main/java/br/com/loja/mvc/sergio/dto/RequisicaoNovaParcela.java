package br.com.loja.mvc.sergio.dto;


import br.com.loja.mvc.sergio.model.Parcela;
import lombok.Data;

@Data
public class RequisicaoNovaParcela {
	
	private Long idVenda;
	private Integer idParcela;
	private String dataParcela;
	private String dataPagamento;
	private double valorParcela;
	private double valorPago;
	private boolean ativa;
	private int parcela; //-> referente a qual parcela

	public Parcela toParcela() {
		Parcela parcela = new Parcela();
		
		return parcela;
	}
}
