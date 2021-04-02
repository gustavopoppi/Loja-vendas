package br.com.loja.mvc.sergio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.loja.mvc.sergio.model.Parcela;

public interface ParcelaRepository extends JpaRepository<Parcela, Long>{

	@Query("SELECT P"
			+ "  FROM Parcela P"
			+ "  JOIN Venda V ON P.venda = V.id"
			+ "  JOIN Cliente C ON V.cliente = C.id"
			+ " WHERE P.valorPago = 0 "
			+ " GROUP BY V.id"
			+ " ORDER BY C.nomeCliente")
	List<Parcela> findAllByJoin();

}
