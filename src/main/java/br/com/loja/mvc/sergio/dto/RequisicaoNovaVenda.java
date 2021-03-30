package br.com.loja.mvc.sergio.dto;

import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import br.com.loja.mvc.sergio.model.Cliente;
import br.com.loja.mvc.sergio.model.Parcela;
import br.com.loja.mvc.sergio.model.Venda;

public class RequisicaoNovaVenda {

	@NotBlank
	private String nomeCliente;
	@NotBlank
	private String nomeProduto;
	@NotBlank
	private double valorTotal;
	@NotBlank
	private int qtdeParcelas;
	@NotBlank
	private Date dataCompra;
	@NotBlank
	private Date inicioPagamento;

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getQtdeParcelas() {
		return qtdeParcelas;
	}

	public void setQtdeParcelas(int qtdeParcelas) {
		this.qtdeParcelas = qtdeParcelas;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Date getInicioPagamento() {
		return inicioPagamento;
	}

	public void setInicioPagamento(Date inicioPagamento) {
		this.inicioPagamento = inicioPagamento;
	}

	public Venda toVenda() {
		Venda venda = new Venda();
		Cliente cliente = new Cliente();
		Parcela parcela = new Parcela();
		
		venda.setNomeProduto(nomeProduto);
		venda.setValorTotal(valorTotal);
		venda.setDataCompra(dataCompra);
		venda.setInicioPagamento(inicioPagamento);
		venda.setQtdeParcelas(qtdeParcelas);
		
		venda.setCliente(cliente);
		cliente.setNomeCliente(nomeCliente);
				
		
		double valorParcela = calcularValorParcela(venda.getValorTotal(), venda.getQtdeParcelas());
		for (int i = 0; i < qtdeParcelas; i++) {

			parcela.setDataParcela(incrementarMes(venda.getDataCompra(), i));
			//parcela.setDataPagamento(inicioPagamento); data quando pagar
			parcela.setValorParcela(valorParcela);
			//parcela.setValorPago(); vai sendo incrementado conforme for dando baixa nas parcelas;
			parcela.setAtiva(false);
			parcela.setParcela(i+1);
			parcela.setVenda(venda);
		}
		
		return venda;
	}

	private double calcularValorParcela(double valorTotal, int qtdeParcelas) {
		return valorTotal / qtdeParcelas;
	}
	
	private Date incrementarMes(Date data, int mesesAIncrementar) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);	
		cal.add(Calendar.MONTH, mesesAIncrementar);
		
		return cal.getTime();
	}
}
