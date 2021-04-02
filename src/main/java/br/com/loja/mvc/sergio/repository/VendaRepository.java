package br.com.loja.mvc.sergio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.loja.mvc.sergio.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

	@Query("SELECT V "
		   + "FROM Venda V "
		   + "JOIN Cliente C ON V.cliente = C.id "
		  + "WHERE V.foiPaga = 'N'"
		  + "ORDER BY C.nomeCliente")
	List<Venda> findAllByJoin();

//	List<Pedido> findByStatus(StatusPedido aguardando);
	
}