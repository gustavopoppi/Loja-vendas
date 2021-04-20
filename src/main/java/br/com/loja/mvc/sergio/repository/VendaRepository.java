package br.com.loja.mvc.sergio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.loja.mvc.sergio.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

	@Query("SELECT V "
		   + "FROM Venda V "
		   + "JOIN Cliente C ON V.cliente = C.id "
		   + "JOIN Parcela P ON P.venda = V.id "
		  + "WHERE V.foiPaga != ''"
		  + "  AND STR_TO_DATE(P.dataParcela , '%d/%m/%Y') >= STR_TO_DATE(:dataPrimeiroDiaMes , '%Y/%m/%d')"
		  + "  AND STR_TO_DATE(P.dataParcela , '%d/%m/%Y') <= STR_TO_DATE(:dataUltimoDiaMes , '%Y/%m/%d')"
		  + "ORDER BY V.id")
	List<Venda> findAllByJoin(@Param("dataPrimeiroDiaMes")String primeiroDiaMes, @Param("dataUltimoDiaMes")String ultimoDiaMes);

	@Query("SELECT V "
		   + "FROM Venda V "
		   + "JOIN Cliente C ON V.cliente = C.id "
		   + "JOIN Parcela P ON P.venda = V.id "
		  + "WHERE V.foiPaga = 'N'"
		  + "  AND STR_TO_DATE(P.dataParcela , '%d/%m/%Y') >= STR_TO_DATE(:dataPrimeiroDiaMes , '%Y/%m/%d')"
		  + "  AND STR_TO_DATE(P.dataParcela , '%d/%m/%Y') <= STR_TO_DATE(:dataUltimoDiaMes , '%Y/%m/%d')"
		  + " ORDER BY V.id")
	List<Venda> consultaTeste(@Param("dataPrimeiroDiaMes")String primeiroDiaMes, @Param("dataUltimoDiaMes")String ultimoDiaMes);

	@Query("SELECT V "
		 + "  FROM Venda V"
		 + " WHERE V.id = :idVenda")
	Venda findByIdModificado(@Param("idVenda")Long idVenda);	

//	List<Pedido> findByStatus(StatusPedido aguardando);
	
}