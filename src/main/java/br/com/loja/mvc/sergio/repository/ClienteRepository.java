package br.com.loja.mvc.sergio.repository;

import java.util.List;

import javax.persistence.Column;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.loja.mvc.sergio.comuns.Constant;
import br.com.loja.mvc.sergio.comuns.StringExtensions;
import br.com.loja.mvc.sergio.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	Cliente findByNomeCliente(String nomeCliente);

	@Query("SELECT C"
		  + " FROM Cliente C"
		  + " JOIN Venda V ON C.id = V.cliente"
		  + " JOIN Parcela P ON P.venda = V.id "
		 + " WHERE V.foiPaga = 'N'"
		  + Constant.QUERY_WHERE_PRIMEIRODIA_ULTIMODIA_MES_ATUAL)
	List<Cliente> queryTeste(@Param("dataPrimeiroDiaMes")String primeiroDiaMes, @Param("dataUltimoDiaMes")String ultimoDiaMes);
	
	@Query("SELECT C " 
	       + "FROM Venda V " 
	       + "JOIN Cliente C ON V.cliente = C.id " 
		   + "JOIN Parcela P ON P.venda = V.id "
		  + "WHERE V.foiPaga = 'N'"
		  + Constant.QUERY_WHERE_PRIMEIRODIA_ULTIMODIA_MES_ATUAL
		  + "GROUP BY C.nomeCliente")
	List<Cliente> findClienteVendaEmAberto(@Param("dataPrimeiroDiaMes")String primeiroDiaMes, @Param("dataUltimoDiaMes")String ultimoDiaMes);

	@Query("SELECT SUM(V.valorTotal) as vlTotal, COUNT(C.nomeCliente)" 
	     + "  FROM Cliente C"
		 + "  JOIN Venda V ON C.id = V.cliente" 
		 + "  JOIN Parcela P ON P.venda = V.id " 
	     + " WHERE V.foiPaga != ''"
	     + Constant.QUERY_WHERE_PRIMEIRODIA_ULTIMODIA_MES_ATUAL
		 + " GROUP BY C.nomeCliente")
	List<Double> findValorTotalVendaPorCliente(@Param("dataPrimeiroDiaMes")String primeiroDiaMes, @Param("dataUltimoDiaMes")String ultimoDiaMes);

	@Query("SELECT SUM(P.valorParcela)" 
	     + "  FROM Cliente C"
		 + "  JOIN Venda V ON C.id = V.cliente" 
		 + "  JOIN Parcela P ON P.venda = V.id " 
	     + " WHERE V.foiPaga != ''"
	     + Constant.QUERY_WHERE_PRIMEIRODIA_ULTIMODIA_MES_ATUAL 
		 + " GROUP BY C.nomeCliente")
	List<Double> findValorTotalParcelaPorCliente(@Param("dataPrimeiroDiaMes")String primeiroDiaMes, @Param("dataUltimoDiaMes")String ultimoDiaMes);
	
	@Query("SELECT COUNT(C.nomeCliente)" 
	     + "  FROM Cliente C" 
		 + "  JOIN Venda V ON C.id = V.cliente"
		 + "  JOIN Parcela P ON P.venda = V.id "	     
		 + " WHERE V.foiPaga != ''"
		 + Constant.QUERY_WHERE_PRIMEIRODIA_ULTIMODIA_MES_ATUAL 
		 + " GROUP BY C.nomeCliente" 
		 + " ORDER BY C.nomeCliente")
	List<Long> findCountTotalPorClienteOrderByCliente(@Param("dataPrimeiroDiaMes")String primeiroDiaMes, @Param("dataUltimoDiaMes")String ultimoDiaMes);
}
