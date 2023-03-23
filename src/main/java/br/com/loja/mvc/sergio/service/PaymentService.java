package br.com.loja.mvc.sergio.service;

import br.com.loja.mvc.sergio.comuns.StringExtensions;
import br.com.loja.mvc.sergio.dto.InstallmentData;
import br.com.loja.mvc.sergio.model.Cliente;
import br.com.loja.mvc.sergio.model.Parcela;
import br.com.loja.mvc.sergio.model.StatusParcela;
import br.com.loja.mvc.sergio.model.Venda;
import br.com.loja.mvc.sergio.repository.ParcelaRepository;
import br.com.loja.mvc.sergio.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ParcelaRepository parcelaRepository;

    public void updatePaidInstallment(InstallmentData installmentDataRequest) {
        Parcela installment = parcelaRepository.findById(installmentDataRequest.getIdParcela()).get();

        //TODO GUSTAVO validar no front quando o valor pago for nulo ou 0; no caso quando o usuário coloca 0 o códgio está passando e está setando como paga;
        if (installment.getStatus() == StatusParcela.AGUARDANDO) {

            installment.setValorPago(installmentDataRequest.getValorPago());
            installment.setStatus(getInstallmentStatus(installmentDataRequest, installment));
            setCostumerActiveSalesAmount(installment);
            installment.setDataPagamentoParcela(StringExtensions.dataDeHoje());
            installment.setAtiva('N');

            updateSaleAsPaidIfAllInstallmentsPaid(installmentDataRequest);

            parcelaRepository.save(installment);
        }
    }

    private void updateSaleAsPaidIfAllInstallmentsPaid(InstallmentData installmentDataRequest) {
        Long idVenda = installmentDataRequest.getIdVenda();
        if (parcelaRepository.findInstallmentsOpenByIdSale(idVenda).size() == 0) {
            Venda venda = vendaRepository.findByIdModificado(idVenda);
            venda.setFoiPaga('S');
            vendaRepository.save(venda);
        }
    }

    private void setCostumerActiveSalesAmount(Parcela installment) {
        Cliente costumer = installment.getVenda().getCliente();
        costumer.setQtdeComprasAtivas(costumer.getQtdeComprasAtivas());
    }

    private StatusParcela getInstallmentStatus(InstallmentData installmentDataRequest, Parcela parcela) {
        return installmentDataRequest.getValorPago() >= parcela.getValorParcela()
               ? StatusParcela.PAGA
               : StatusParcela.PARCIAL;
    }
}
