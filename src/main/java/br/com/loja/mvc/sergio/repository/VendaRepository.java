package br.com.loja.mvc.sergio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.loja.mvc.sergio.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

//	List<Pedido> findByStatus(StatusPedido aguardando);
	
}