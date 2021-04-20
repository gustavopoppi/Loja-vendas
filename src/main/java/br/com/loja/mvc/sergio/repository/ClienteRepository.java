package br.com.loja.mvc.sergio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.loja.mvc.sergio.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Cliente findByNomeCliente(String nomeCliente);

	@Query("SELECT C " 
	       + "FROM Venda V " 
	       + "JOIN Cliente C ON V.cliente = C.id " 
		   + "JOIN Parcela P ON P.venda = V.id "
		  + "WHERE V.foiPaga = 'N'"
		  + "  AND STR_TO_DATE(P.dataParcela , '%d/%m/%Y') >= STR_TO_DATE(:dataPrimeiroDiaMes , '%Y/%m/%d')"
		  + "  AND STR_TO_DATE(P.dataParcela , '%d/%m/%Y') <= STR_TO_DATE(:dataUltimoDiaMes , '%Y/%m/%d')"
		  + "GROUP BY C.nomeCliente")
	List<Cliente> findUsuarioVendasEmAberto(@Param("dataPrimeiroDiaMes")String primeiroDiaMes, @Param("dataUltimoDiaMes")String ultimoDiaMes);

	@Query("SELECT SUM(V.valorTotal) as vlTotal, COUNT(C.nomeCliente)" 
	     + "  FROM Cliente C"
		 + "  JOIN Venda V ON C.id = V.cliente" 
		 + "  JOIN Parcela P ON P.venda = V.id " 
	     + " WHERE V.foiPaga = 'N'"
		 + "  AND STR_TO_DATE(P.dataParcela , '%d/%m/%Y') >= STR_TO_DATE(:dataPrimeiroDiaMes , '%Y/%m/%d')"
		 + "  AND STR_TO_DATE(P.dataParcela , '%d/%m/%Y') <= STR_TO_DATE(:dataUltimoDiaMes , '%Y/%m/%d')" 
		 + " GROUP BY C.nomeCliente")
	List<Double> findValorTotalClientesEmAberto(@Param("dataPrimeiroDiaMes")String primeiroDiaMes, @Param("dataUltimoDiaMes")String ultimoDiaMes);

	@Query("SELECT COUNT(C.nomeCliente)" 
	     + "  FROM Cliente C" 
		 + "  JOIN Venda V ON C.id = V.cliente"
		 + "  JOIN Parcela P ON P.venda = V.id "	     
		 + " WHERE V.foiPaga != ''"
		 + "   AND STR_TO_DATE(P.dataParcela , '%d/%m/%Y') >= STR_TO_DATE(:dataPrimeiroDiaMes , '%Y/%m/%d')"
		 + "   AND STR_TO_DATE(P.dataParcela , '%d/%m/%Y') <= STR_TO_DATE(:dataUltimoDiaMes , '%Y/%m/%d')" 
		 + " GROUP BY C.nomeCliente" 
		 + " ORDER BY C.nomeCliente")
	List<Long> findCountTotalPorClienteEmAberto(@Param("dataPrimeiroDiaMes")String primeiroDiaMes, @Param("dataUltimoDiaMes")String ultimoDiaMes);
	
	@Query("SELECT C"
		+  "  FROM Cliente C"
		+ "  WHERE C.id = :idCliente")
	Cliente findByIdModificado(@Param("idCliente")Long idCliente);
}
