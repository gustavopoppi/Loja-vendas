package br.com.loja.mvc.sergio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.loja.mvc.sergio.comuns.Constant;
import br.com.loja.mvc.sergio.model.Parcela;
import br.com.loja.mvc.sergio.model.Venda;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Long>{

	@Query("SELECT P"
			+ "  FROM Parcela P"
			+ "  JOIN Venda V ON P.venda = V.id"
			+ "  JOIN Cliente C ON V.cliente = C.id" 
			+ "  JOIN Parcela P ON P.venda = V.id "
			+ " WHERE P.valorPago >= 0"
			+ Constant.QUERY_WHERE_PRIMEIRODIA_ULTIMODIA_MES_ATUAL 
			+ " GROUP BY V.id" 
			+ " ORDER BY C.nomeCliente, P.dataParcela")
	List<Parcela> findAllParcelasOrderByClienteEDataParcela(@Param("dataPrimeiroDiaMes")String primeiroDiaMes, @Param("dataUltimoDiaMes")String ultimoDiaMes);

	@Query("SELECT P"
		+ "   FROM Parcela P"
		+ "  WHERE P.venda.id = :idVenda"
		+ "    AND P.status = 'AGUARDANDO'"
		+ "  ORDER BY P.id")			
	List<Parcela> findParcelasByIdVendaWhereStatusAguardando(@Param("idVenda")Long idVenda);

	@Query("SELECT P"
			+ "   FROM Parcela P"
			+ "  WHERE P.venda.id = :idVenda"
			+ "    AND P.status = 'AGUARDANDO'"
			+ "    AND P.ativa = 'S'"
			+ "  ORDER BY P.id")
	List<Parcela> findInstallmentsOpenByIdSale(@Param("idVenda")Long idVenda);
	
	@Query("SELECT SUM(valorPago) "
		 + "  FROM Parcela P"
		 + " WHERE numeroDaParcela.status != 'AGUARDANDO'"
		 + Constant.QUERY_WHERE_PRIMEIRODIA_ULTIMODIA_MES_ATUAL)
	Double findValorTotalParcelasPagas(@Param("dataPrimeiroDiaMes")String primeiroDiaMes, @Param("dataUltimoDiaMes")String ultimoDiaMes);

	@Query("SELECT P "
			+ "  FROM Parcela P"
			+ " WHERE P.venda.id = :idSale")
    List<Parcela> findInstallmentsBySaleId(@Param("idSale")Long idSale);
}
