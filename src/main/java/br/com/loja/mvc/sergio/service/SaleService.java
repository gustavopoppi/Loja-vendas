package br.com.loja.mvc.sergio.service;

import br.com.loja.mvc.sergio.dto.NewSaleData;
import br.com.loja.mvc.sergio.model.Cliente;
import br.com.loja.mvc.sergio.model.Parcela;
import br.com.loja.mvc.sergio.model.Venda;
import br.com.loja.mvc.sergio.repository.ClienteRepository;
import br.com.loja.mvc.sergio.repository.ParcelaRepository;
import br.com.loja.mvc.sergio.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

import static br.com.loja.mvc.sergio.comuns.StringExtensions.incrementaMesesDadoMesesAIncrementar;
import static br.com.loja.mvc.sergio.model.StatusParcela.AGUARDANDO;

@Service
public class SaleService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    ParcelaRepository parcelaRepository;

    public void createSaleAndInstallment(NewSaleData newSaleData) throws ParseException {
		Venda sale = createSale(newSaleData);
        createInstallment(sale);
    }

    private Venda createSale(NewSaleData newSaleData) throws ParseException {
        Cliente customer = clienteRepository.findById(newSaleData.getIdCliente()).get();
        Venda sale = new Venda(newSaleData, customer);
        vendaRepository.save(sale);

        return sale;
    }

    private void createInstallment(Venda sale) throws ParseException {
        for (int index = 0; index < sale.getQtdeParcelas(); index++) {
            int installmentNumber = index + 1;

            //TODO GUSTAVO implementar um builder
            Parcela installment = new Parcela();
            installment.setDataParcela(incrementaMesesDadoMesesAIncrementar(sale.getInicioPagamento(), index));
            installment.setValorParcela(calculateInstallmentValue(sale));
            installment.setValorPago(0);
            installment.setAtiva('N');
            installment.setNumeroDaParcela(installmentNumber);
            installment.setStatus(AGUARDANDO);
            installment.setVenda(sale);

            parcelaRepository.save(installment);
        }
    }

    private double calculateInstallmentValue(Venda sale) {
        return sale.getValorTotal() / sale.getQtdeParcelas();
    }
}
