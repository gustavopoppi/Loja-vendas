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

	public static String dataCompletaComOPrimeiroDiaMes(String mes, String ano) {
		LocalDate dataAtualAlterandoComOMesParametro = retornaDataAtualDeAcordoComOMes(mes);
		String primeiroDiaMes = String.format("%02d", (dataAtualAlterandoComOMesParametro.withDayOfMonth(1).getDayOfMonth()));

		return String.format("%s/%s/%s", ano, getMesAtualDadoIndexMes(mes), primeiroDiaMes);
	}

	public static String dataCompletaComUltimoDiaDoMes(String mes, String ano) {
		LocalDate dataAtualAlterandoComOMesParametro = retornaDataAtualDeAcordoComOMes(mes);
		String ultimoDiaMes = String.format("%02d",(dataAtualAlterandoComOMesParametro.withDayOfMonth(dataAtualAlterandoComOMesParametro.lengthOfMonth()).getDayOfMonth()));

		return String.format("%s/%s/%s", ano, getMesAtualDadoIndexMes(mes), ultimoDiaMes);
	}
	
	private static String getMesAtualDadoIndexMes(String mes) {
		LocalDate dataAtual = LocalDate.now();

		LocalDate localDate = LocalDate.of(dataAtual.getYear(), Integer.parseInt(mes), dataAtual.getDayOfMonth());
		
		return String.format("%02d", (localDate.getMonth().getValue()));
	}

	private static LocalDate retornaDataAtualDeAcordoComOMes(String mes){
		LocalDate dataAtual = LocalDate.now();

		if (mes == null)
			mes = String.format("%02d", dataAtual.getMonth().getValue());

		return LocalDate.of(dataAtual.getYear(), Integer.parseInt(mes), dataAtual.getDayOfMonth());
	}
}
