package br.com.loja.mvc.sergio.comuns;

public class Constant {

	public static final String QUERY_WHERE_PRIMEIRODIA_ULTIMODIA_MES_ATUAL = 
			"  AND STR_TO_DATE(P.dataParcela , '%d/%m/%Y') >= STR_TO_DATE(:dataPrimeiroDiaMes , '%Y/%m/%d')"
			+ "  AND STR_TO_DATE(P.dataParcela , '%d/%m/%Y') <= STR_TO_DATE(:dataUltimoDiaMes , '%Y/%m/%d')";
}
