package br.com.loja.mvc.sergio.comuns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class StringExtensions {

	public static String incrementaMesesDadoMesesAIncrementar(String dataIncrementar, int mesesAIncrementar) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date parseDateFormatToDate = dateFormat.parse(dataIncrementar);
		Calendar todayDate = Calendar.getInstance();
		todayDate.setTime(parseDateFormatToDate);
		todayDate.add(Calendar.MONTH, mesesAIncrementar);

		return dateFormat.format(todayDate.getTime()); // retornou a parseDateFormatToDate incrementada em string
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

	public static String retornaPrimeiroDiaMes(String mes) {
		LocalDate dataAtual = LocalDate.now();
		
		if (mes == null) 
			mes = String.format("%02d", dataAtual.getMonth());		
		
		LocalDate localDate = LocalDate.of(dataAtual.getYear(), Integer.parseInt(mes), dataAtual.getDayOfMonth());
		
		Calendar cal = Calendar.getInstance();
		cal.set(2021, 03, 06);
		Date oi = cal.getTime();

		String PrimeiroDiaMes = String.format("%02d", (localDate.withDayOfMonth(1).getDayOfMonth()));
		String mesAtual = retornaMesAtual(mes);
		String anoAtual = retornaAnoAtual(mes);		

		return anoAtual + '/' + mesAtual + '/' + PrimeiroDiaMes;
	}

	public static String retornaUltimoDiaMes(String mes) {
		LocalDate dataAtual = LocalDate.now();
		
		if (mes == null) 
			mes = String.format("%02d", dataAtual.getMonth());
		
		//if (mes == 2)
			
			
	 	//dataAtual.getDayOfMonth()	
		
		LocalDate localDate = LocalDate.of(dataAtual.getYear(), Integer.parseInt(mes), dataAtual.getDayOfMonth());
		
		String UltimoDiaMes = String.format("%02d",(localDate.withDayOfMonth(localDate.lengthOfMonth()).getDayOfMonth()));
		String mesAtual = retornaMesAtual(mes);
		String anoAtual = retornaAnoAtual(mes);
		
		return anoAtual + '/' + mesAtual + '/' + UltimoDiaMes;			
	}
	
	private static String retornaMesAtual(String mes) {
		LocalDate dataAtual = LocalDate.now();

		LocalDate localDate = LocalDate.of(dataAtual.getYear(), Integer.parseInt(mes), dataAtual.getDayOfMonth());
		
		return String.format("%02d", (localDate.getMonth().getValue()));
	}
	
	private static String retornaAnoAtual(String mes) {
		LocalDate dataAtual = LocalDate.now();

		LocalDate localDate = LocalDate.of(dataAtual.getYear(), Integer.parseInt(mes), dataAtual.getDayOfMonth());
		
		return String.format("%02d", (localDate.getYear()));
	}
}
