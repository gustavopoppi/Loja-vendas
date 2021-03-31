package br.com.loja.mvc.sergio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loja.mvc.sergio.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	Cliente findByNomeCliente(String nomeCliente);
}
