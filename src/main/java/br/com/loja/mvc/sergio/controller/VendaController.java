package br.com.loja.mvc.sergio.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.loja.mvc.sergio.dto.RequisicaoNovaVenda;

@Controller
@RequestMapping("venda")
public class VendaController {

	@GetMapping("formulario")
	public String formulario (RequisicaoNovaVenda requisicao) {
		return "venda/formulario";
	}
	
	@PostMapping("novo")
	public String novo() {
//		if(result.hasErrors()) {
//			return "pedido/formulario";
//		}
		
		// IMPLEMENTAR
		
		return "redirect:/home";
	}
	
//	public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result) {
//		if(result.hasErrors()) {
//			return "pedido/formulario";
//		}
//		
//		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//		
//		User usuario = userRepository.findByUsername(username);
//		Pedido pedido = requisicao.toPedido();
//		pedido.setUser(usuario);
//		pedidoRepository.save(pedido);
//		return "redirect:/home";
//	}
}
