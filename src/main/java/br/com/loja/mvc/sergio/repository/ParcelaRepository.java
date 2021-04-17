package br.com.loja.mvc.sergio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.loja.mvc.sergio.model.Parcela;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Long>{

	@Query("SELECT P"
			+ "  FROM Parcela P"
			+ "  JOIN Venda V ON P.venda = V.id"
			+ "  JOIN Cliente C ON V.cliente = C.id" 
			+ "  JOIN Parcela P ON P.venda = V.id "
			+ " WHERE P.valorPago = 0 "
			 + "  AND STR_TO_DATE(P.dataParcela , '%d/%m/%Y') >= STR_TO_DATE(:dataPrimeiroDiaMes , '%Y/%m/%d')"
			 + "  AND STR_TO_DATE(P.dataParcela , '%d/%m/%Y') <= STR_TO_DATE(:dataUltimoDiaMes , '%Y/%m/%d')" 
			+ " GROUP BY V.id" 
			+ " ORDER BY C.nomeCliente, P.dataParcela")
	List<Parcela> findAllByJoin(@Param("dataPrimeiroDiaMes")String primeiroDiaMes, @Param("dataUltimoDiaMes")String ultimoDiaMes);

	@Query("SELECT P"
		+ "   FROM PARCELA"
		+ "  WHERE id = :idParcela")			
	Parcela findById(@Param("idParcela")Integer idParcela);
}
