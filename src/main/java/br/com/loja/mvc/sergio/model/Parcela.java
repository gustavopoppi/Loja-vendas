package br.com.loja.mvc.sergio.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Parcela {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String dataParcela;
	private String dataPagamentoParcela;
	private double valorParcela;
	private double valorPago;
	private char ativa;
	private int numeroDaParcela;
	
	@Enumerated(EnumType.STRING)
	private StatusParcela status;
	
	@ManyToOne(cascade = CascadeType.ALL/* , fetch = FetchType.LAZY*/) // varias parcelas para uma venda
	private Venda venda;
}
