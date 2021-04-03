package br.com.loja.mvc.sergio.comuns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class StringExtensions {

	public static String incrementarMes(String dataIncrementar, int mesesAIncrementar) throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // formato que eu quero ad ata
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

	public static String retornaPrimeiroDiaMes() {
		LocalDate dataAtual = LocalDate.now();

		String PrimeiroDiaMes = String.format("%02d", (dataAtual.withDayOfMonth(1).getDayOfMonth()));
		String mesAtual = retornaMesAtual();
		String anoAtual = retornaAnoAtual();		

		return anoAtual + '/' + mesAtual + '/' + PrimeiroDiaMes;
	}

	public static String retornaUltimoDiaMes() {
		LocalDate dataAtual = LocalDate.now();
		
		String UltimoDiaMes = String.format("%02d",(dataAtual.withDayOfMonth(dataAtual.lengthOfMonth()).getDayOfMonth()));
		String mesAtual = retornaMesAtual();
		String anoAtual = retornaAnoAtual();
		
		return anoAtual + '/' + mesAtual + '/' + UltimoDiaMes;			
	}
	
	private static String retornaMesAtual() {
		LocalDate dataAtual = LocalDate.now();

		return String.format("%02d", (dataAtual.getMonth().getValue()));
	}
	
	private static String retornaAnoAtual() {
		LocalDate dataAtual = LocalDate.now();

		return String.format("%02d", (dataAtual.getYear()));
	}
}
