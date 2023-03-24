package br.com.loja.mvc.sergio.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.loja.mvc.sergio.comuns.StringExtensions;
import br.com.loja.mvc.sergio.model.Cliente;
import br.com.loja.mvc.sergio.model.Parcela;
import br.com.loja.mvc.sergio.model.StatusParcela;
import br.com.loja.mvc.sergio.model.Venda;
import br.com.loja.mvc.sergio.repository.ParcelaRepository;
import lombok.Data;

@Data
public class NewSaleData {

	private long idCliente;
	//@NotBlank
	private String nomeCliente;
	//@NotBlank
	private String nomeProduto;
	//@NotBlank
	private double valorTotal;
	//@NotBlank
	private int qtdeParcelas;
	//@NotBlank
	private String dataCompra;
	//@NotBlank
	private String inicioPagamento;
}
