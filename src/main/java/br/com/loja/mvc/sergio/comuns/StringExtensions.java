package br.com.loja.mvc.sergio.comuns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringExtensions {

	public static String incrementarMes(String dataIncrementar, int mesesAIncrementar) throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //formato que eu quero ad ata
		Date data = dateFormat.parse(dataIncrementar); // conversão strig para Date
		Calendar cal = Calendar.getInstance(); // pega a data de hoje
		cal.setTime(data); // seta a hora para a que está na variável 
		cal.add(Calendar.MONTH, mesesAIncrementar);
		
		return dateFormat.format(cal.getTime()); // retornou a data incrementada em string
	}
	
	public static String formatarDataVindoAoContrario(String data) throws ParseException {
		SimpleDateFormat dateFormularioFormat = new SimpleDateFormat("yyyy-MM-dddd");
		Date dataFormatada = dateFormularioFormat.parse(data);
		
		SimpleDateFormat dateFormatoBrasil = new SimpleDateFormat("dd/MM/yyyy");
		
		return dateFormatoBrasil.format(dataFormatada);
	}
	
	public static String dataDeHoje() {
		SimpleDateFormat dateFormatoBrasil = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		return dateFormatoBrasil.format(cal.getTime());		
	}
}
