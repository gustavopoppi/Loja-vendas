package br.com.loja.mvc.sergio.controller;

import br.com.loja.mvc.sergio.dto.InstallmentData;
import br.com.loja.mvc.sergio.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping
    public String baixaParcelaPaga(@Valid InstallmentData installmentDataRequest, BindingResult result){
        if (result.hasErrors())
            return "home";

        paymentService.updatePaidInstallment(installmentDataRequest);

        return "redirect:/home";
    }
}
