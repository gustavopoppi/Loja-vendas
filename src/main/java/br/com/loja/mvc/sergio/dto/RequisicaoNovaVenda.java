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
public class RequisicaoNovaVenda {

	private long id;
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
	

	public Venda toVenda() throws ParseException {
		
		Venda venda = new Venda();
		Cliente cliente = new Cliente();
		
		venda.setNomeProduto(nomeProduto);
		venda.setValorTotal(valorTotal);
		venda.setDataCompra(formatarDataVindoAoContrario(dataCompra));
		venda.setInicioPagamento(formatarDataVindoAoContrario(inicioPagamento));
		venda.setQtdeParcelas(qtdeParcelas);
		venda.setFoiPaga('N');

//		venda.setCliente(cliente);
//		cliente.setNomeCliente(nomeCliente);

		return venda;
	}

	public void toParcela(Venda venda, ParcelaRepository parcelaRepository) throws ParseException {
//		Parcela parcela = new Parcela();
		
//		String dataDeHoje = StringExtensions.dataDeHoje();
		//TODO GUSTAVO remodelar toda essa lógica de parcelas
		double valorParcela = calcularValorParcela(venda.getValorTotal(), venda.getQtdeParcelas());
		for (int i = 0; i < venda.getQtdeParcelas(); i++) {
			Parcela parcela = new Parcela();
			parcela.setDataParcela(StringExtensions.incrementarMes(
								   StringExtensions.formatarDataVindoAoContrario(inicioPagamento),i));
			parcela.setValorParcela(valorParcela);
			parcela.setValorPago(0); //vai sendo incrementado conforme for dando baixa nas parcelas;
			parcela.setAtiva('N');
			parcela.setNumeroDaParcela(i+1);
			parcela.setVenda(venda);
			//parcela.setDataPagamentoParcela(dataDeHoje);
			parcela.setStatus(StatusParcela.AGUARDANDO);
			parcelaRepository.save(parcela);
//			parcela = new Parcela();
		}
		
//		return parcela;
	}
	
	private double calcularValorParcela(double valorTotal, int qtdeParcelas) {
		return valorTotal / qtdeParcelas;
	}
	
	private String incrementarMes(String dataIncrementar, int mesesAIncrementar) throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //formato que eu quero ad ata
		Date data = dateFormat.parse(dataIncrementar); // conversão strig para Date
		Calendar cal = Calendar.getInstance(); // pega a data de hoje
		cal.setTime(data); // seta a hora para a que está na variável 
		cal.add(Calendar.MONTH, mesesAIncrementar);
		
		return dateFormat.format(cal.getTime()); // retornou a data incrementada em string
	}
	
	private String formatarDataVindoAoContrario(String data) throws ParseException {
		SimpleDateFormat dateFormularioFormat = new SimpleDateFormat("yyyy-MM-dddd");
		Date dataFormatada = dateFormularioFormat.parse(data);
		
		SimpleDateFormat dateFormatoCorreto = new SimpleDateFormat("dd/MM/yyyy");
		
		return dateFormatoCorreto.format(dataFormatada);
	}
}
