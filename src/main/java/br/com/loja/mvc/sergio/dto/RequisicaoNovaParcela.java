package br.com.loja.mvc.sergio.dto;


import br.com.loja.mvc.sergio.model.Parcela;

public class RequisicaoNovaParcela {
	
	private Long idVenda;
	private Integer idParcela;
	private String dataParcela;
	private String dataPagamento;
	private double valorParcela;
	private double valorPago;
	private boolean ativa;
	private int parcela; //-> referente a qual parcela
	
	
	public Long getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Long idVenda) {
		this.idVenda = idVenda;
	}

	public Integer getIdParcela() {
		return idParcela;
	}

	public void setIdParcela(Integer idParcela) {
		this.idParcela = idParcela;
	}

	public String getDataParcela() {
		return dataParcela;
	}

	public void setDataParcela(String dataParcela) {
		this.dataParcela = dataParcela;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public double getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(double valorParcela) {
		this.valorParcela = valorParcela;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	public int getParcela() {
		return parcela;
	}

	public void setParcela(int parcela) {
		this.parcela = parcela;
	}

	public Parcela toParcela() {
		Parcela parcela = new Parcela();
		
		return parcela;
	}
}
