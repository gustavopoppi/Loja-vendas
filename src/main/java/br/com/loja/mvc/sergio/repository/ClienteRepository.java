package br.com.loja.mvc.sergio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.loja.mvc.sergio.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	Cliente findByNomeCliente(String nomeCliente);
	
//	@Query("SELECT C.nomeCliente "
//		   + "FROM Venda V "
//		   + "JOIN Cliente C ON V.cliente = C.id "
//		   + "WHERE V.foiPaga = :foiPaga ")
//	Cliente findUsuarioVendasEmAberto(@Param("foiPaga")char foiPaga);

	@Query("SELECT C "
	       + "FROM Venda V "
		   + "JOIN Cliente C ON V.cliente = C.id "
		  + "WHERE V.foiPaga = :foiPaga "
		  + "GROUP BY C.nomeCliente")
	List<Cliente> findUsuarioVendasEmAberto(@Param("foiPaga")char foiPaga);
	
	@Query("SELECT SUM(V.valorTotal) as vlTotal"
			+ "  FROM Cliente C"
			+ "  JOIN Venda V ON C.id = V.cliente"
			+ " WHERE V.foiPaga = 'N'"
			+ " GROUP BY C.nomeCliente")
	List<Double> findValorTotalClientesEmAberto();
	
	
//	@Query("select p from Pedido p join p.user u where u.username = :username")
//	List<Pedido> findAllByUsuario(@Param("username")String username);
//
//	@Query("select p from Pedido p join p.user u where u.username = :username and p.status = :status")
//	List<Pedido> findByStatusEUsuario(@Param("status")StatusPedido status, @Param("username")String username);
	
//	List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.ENTREGUE, paginacao);
}
